package com.enterprise.framwork.interceptor;

import static com.enterprise.framwork.web.Scope.beginScope;
import static com.enterprise.framwork.web.Scope.endScope;

import com.auth0.jwt.interfaces.Claim;
import com.enterprise.framwork.jwt.JwtProperties;
import com.enterprise.framwork.jwt.JwtUtil;
import com.enterprise.framwork.web.AuthScope;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author chenyi
 */
@Component
@Slf4j
public class RequestInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {
//        String ignoreUrl = jwtProperties.getIgnoreUrl();
//        String[] ignoreUrls = ignoreUrl.split(",");
//        for (int i = 0; i < ignoreUrls.length; i++) {
//            if (request.getServletPath().startsWith(ignoreUrls[i])) {
//                return true;
//            }
//        }
        String method = request.getMethod();
        if("OPTIONS".equalsIgnoreCase(method)){
            return true;
        }
        String token = request.getHeader("token");
        log.info("token:{}", token);
        Map<String, Claim> claimMap = null;
        claimMap = jwtUtil.decode(token);
        beginScope();
        AuthScope.setLoginUser(claimMap.get("name").asString());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {
        endScope();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
        Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

}
