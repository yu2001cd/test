package com.itheima.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
/*@WebFilter(urlPatterns = "/*")*///过滤器顺序是按照类名排序的 放行到下一个过滤器 最后一个过滤器放行到访问资源 放行后逻辑从最后一个过滤器执行到第一个过滤器
public class DemoFilter implements Filter {
    @Override//初始化方法调用一次
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化方法执行");
    }

    @Override//拦截到请求后调用 多次
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("拦截到了请求");
        //放行
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {//销毁方法，调用一次
        System.out.println("销毁方法执行");
    }
}
