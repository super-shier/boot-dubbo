package com.li.yun.biao.swagger.fitter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: liyunbiao
 * @Date: 2020/2/7 12:37 PM
 * @description
 */
public class MyFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //TODO something
    }

    @Override
    public void destroy() {
        //TODO something
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");
        if (path.contains("/oss/")) {
            filterChain.doFilter(request, response);
        } else {
            //取Body数据
            MyRequestWrapper requestWrapper = new MyRequestWrapper(request);
            String body = requestWrapper.getBody();
            //TODO something
            filterChain.doFilter(requestWrapper, servletResponse);
        }
    }
}