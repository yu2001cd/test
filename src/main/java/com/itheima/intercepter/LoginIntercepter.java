package com.itheima.intercepter;

import com.alibaba.fastjson.JSONObject;
import com.itheima.Utils.JwtUtils;
import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Slf4j
@Component
public class LoginIntercepter implements HandlerInterceptor {
    @Override//目标资源方法运行前运行 true放行
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        String url = httpServletRequest.getRequestURL().toString();
        //获取令牌
        String jwt = httpServletRequest.getHeader("token");
        //没有jwt令牌
        if (!StringUtils.hasLength(jwt)){
            Result error = Result.error("NOT_LOGIN");
            String notlogin = JSONObject.toJSONString(error);
            httpServletResponse.getWriter().write(notlogin);
            return false ;
        }

        try {
            JwtUtils.parseJWT(jwt);
            log.info("令牌合法放行");
            return true;
        } catch (Exception e) {//jwt令牌不正确
            e.printStackTrace();
            log.info("令牌不正确");
            Result error = Result.error("NOT_LOGIN");
            String notlogin = JSONObject.toJSONString(error);
            httpServletResponse.getWriter().write(notlogin);
            return false;
        }
    }

    @Override//目标资源后放行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override//视图渲染完毕后运行，最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
