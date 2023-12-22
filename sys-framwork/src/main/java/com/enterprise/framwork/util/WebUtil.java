package com.enterprise.framwork.util;

import cn.hutool.core.lang.PatternPool;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;


/**
 * Miscellaneous utilities for web applications.
 */
@Slf4j
public class WebUtil extends WebUtils {

	public static final String USER_AGENT_HEADER = "user-agent";


	/**
	 * 读取cookie
	 *
	 * @param name cookie name
	 * @return cookie value
	 */
	@Nullable
	public static String getCookieVal(String name) {
		HttpServletRequest request = WebUtil.getRequest();
		Assert.notNull(request, "request from RequestContextHolder is null");
		return getCookieVal(request, name);
	}

	/**
	 * 读取cookie
	 *
	 * @param request HttpServletRequest
	 * @param name    cookie name
	 * @return cookie value
	 */
	@Nullable
	public static String getCookieVal(HttpServletRequest request, String name) {
		Cookie cookie = WebUtils.getCookie(request, name);
		return cookie != null ? cookie.getValue() : null;
	}

	/**
	 * 清除 某个指定的cookie
	 *
	 * @param response HttpServletResponse
	 * @param key      cookie key
	 */
	public static void removeCookie(HttpServletResponse response, String key) {
		setCookie(response, key, null, 0);
	}

	/**
	 * 设置cookie
	 *
	 * @param response        HttpServletResponse
	 * @param name            cookie name
	 * @param value           cookie value
	 * @param maxAgeInSeconds maxage
	 */
	public static void setCookie(HttpServletResponse response, String name, @Nullable String value, int maxAgeInSeconds) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath(StringPool.SLASH);
		cookie.setMaxAge(maxAgeInSeconds);
		cookie.setHttpOnly(true);
		response.addCookie(cookie);
	}

	/**
	 * 获取 HttpServletRequest
	 *
	 * @return {HttpServletRequest}
	 */
	public static HttpServletRequest getRequest() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		return (requestAttributes == null) ? null : ((ServletRequestAttributes) requestAttributes).getRequest();
	}

	/**
	 * 返回json
	 *
	 * @param response HttpServletResponse
	 * @param result   结果对象
	 */
	public static void renderJson(HttpServletResponse response, Object result) {
		renderJson(response, result, MediaType.APPLICATION_JSON_VALUE);
	}

	/**
	 * 返回json
	 *
	 * @param response    HttpServletResponse
	 * @param result      结果对象
	 * @param contentType contentType
	 */
	public static void renderJson(HttpServletResponse response, Object result, String contentType) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType(contentType);
		try (PrintWriter out = response.getWriter()) {
			out.append(JsonUtils.ObjectToJson(result));
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 获取ip
	 *
	 * @return {String}
	 */
	public static String getIP() {
		return getIP(WebUtil.getRequest());
	}

	private static final String[] IP_HEADER_NAMES = new String[]{
		"x-forwarded-for",
		"Proxy-Client-IP",
		"WL-Proxy-Client-IP",
		"HTTP_CLIENT_IP",
		"HTTP_X_FORWARDED_FOR"
	};

	private static final Predicate<String> IP_PREDICATE = (ip) -> org.apache.commons.lang3.StringUtils.isBlank(ip) || StringPool.UNKNOWN.equalsIgnoreCase(ip);

	/**
	 * 获取ip
	 *
	 * @param request HttpServletRequest
	 * @return {String}
	 */
	@Nullable
	public static String getIP(@Nullable HttpServletRequest request) {
		if (request == null) {
			return StringPool.EMPTY;
		}
		String ip = null;
		for (String ipHeader : IP_HEADER_NAMES) {
			ip = request.getHeader(ipHeader);
			if (!IP_PREDICATE.test(ip)) {
				break;
			}
		}
		if (IP_PREDICATE.test(ip)) {
			ip = request.getRemoteAddr();
		}
		if (!StringUtils.hasText(ip)) {
			StringUtils.delimitedListToStringArray(ip, StringPool.COMMA, " \t\n\n\f");
			// 如果ip中包含端口号等其他内容，只返回ip
			Matcher matcher = PatternPool.IPV4.matcher(ip);
			if (matcher.find()) {
				return matcher.group(0);
			}
			matcher = PatternPool.IPV6.matcher(ip);
			if (matcher.find()) {
				return matcher.group(0);
			}
		}
		return null;
	}




	/**
	 * 获取请求头的值
	 *
	 * @param name 请求头名称
	 * @return 请求头
	 */
	public static String getHeader(String name) {
		HttpServletRequest request = getRequest();
		return Objects.requireNonNull(request).getHeader(name);
	}

	/**
	 * 获取请求头的值
	 *
	 * @param name 请求头名称
	 * @return 请求头
	 */
	public static Enumeration<String> getHeaders(String name) {
		HttpServletRequest request = getRequest();
		return Objects.requireNonNull(request).getHeaders(name);
	}

	/**
	 * 获取所有的请求头
	 *
	 * @return 请求头集合
	 */
	public static Enumeration<String> getHeaderNames() {
		HttpServletRequest request = getRequest();
		return Objects.requireNonNull(request).getHeaderNames();
	}

	/**
	 * 获取请求参数
	 *
	 * @param name 请求参数名
	 * @return 请求参数
	 */
	public static String getParameter(String name) {
		HttpServletRequest request = getRequest();
		return Objects.requireNonNull(request).getParameter(name);
	}

	public static String getHostFromRequest(HttpServletRequest request) {
		String header = request.getHeader("x-forwarded-host");
		if (!StringUtils.hasText(header)) {
			return null;
		}
		if(header.indexOf(":") > -1){
            return header.split(":")[0];
        }
		return header;
	}
}

