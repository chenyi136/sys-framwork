package com.enterprise.framwork.filter;


import com.enterprise.framwork.jwt.JwtProperties;
import com.enterprise.framwork.jwt.JwtUtil;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author chenyi
 */
@Slf4j
public class AuthFilter implements Filter {

    @Autowired
    private JwtUtil jwtUtil;



    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
        FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String token = httpServletRequest.getParameter("token");
//        if (this.check(token)) {
//            beginScope();
//            filterChain.doFilter(servletRequest, servletResponse);
//        } else {
//            HttpServletResponse response = (HttpServletResponse) servletResponse;
////            String redirect = serverHost + "/login?redirect=" + httpServletRequest.getRequestURL();
//            response.setContentType("application/json;charset=utf-8");
//            response.setCharacterEncoding("utf-8");
//            response.getWriter().write(JSON.toJSONString(R.fail("token验证失败")));
////            response.sendRedirect(redirect);
//        }

    }

    private boolean check(String jwt) throws Exception {
//        try {
            if (jwt == null || jwt.trim().length() == 0) {
                return false;
            }
            jwtUtil.decode(jwt);
            return true;
//        } catch (Exception e) {
//            log.error("认证失败", e);
//
//        }
    }
}
