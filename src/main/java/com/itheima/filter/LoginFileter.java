package com.itheima.filter;

import com.alibaba.fastjson.JSONObject;
import com.itheima.Utils.JwtUtils;
import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
/*@WebFilter(urlPatterns = "/*")*/
public class LoginFileter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String url = httpServletRequest.getRequestURL().toString();
        System.out.println(url);
        if (url.contains("login")) {//如果为登录则放行
            log.info("登录请求放行");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        //获取令牌
        String jwt = httpServletRequest.getHeader("token");
        //没有jwt令牌
        if (!StringUtils.hasLength(jwt)){
            Result error = Result.error("NOT_LOGIN");
            String notlogin = JSONObject.toJSONString(error);
            httpServletResponse.getWriter().write(notlogin);
            return;
        }


        try {
            JwtUtils.parseJWT(jwt);
            log.info("令牌合法放行");
            filterChain.doFilter(servletRequest,servletResponse);
        } catch (Exception e) {//jwt令牌不正确
            e.printStackTrace();
            log.info("令牌不正确");
            Result error = Result.error("NOT_LOGIN");
            String notlogin = JSONObject.toJSONString(error);
            httpServletResponse.getWriter().write(notlogin);
        }

    }
}
