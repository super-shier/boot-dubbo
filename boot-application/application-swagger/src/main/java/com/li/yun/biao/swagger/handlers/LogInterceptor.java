package com.li.yun.biao.swagger.handlers;

import com.alibaba.fastjson.JSON;
import com.li.yun.biao.common.utils.AbstractRequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: liyunbiao
 * @Date: 2020/2/7 12:37 PM
 * @description 请求日志拦截
 */
public class LogInterceptor extends HandlerInterceptorAdapter {
    private static Logger logger = LoggerFactory.getLogger(LogInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) {
        request.setAttribute("REQUEST_START_TIME", System.currentTimeMillis());
        if (logger.isInfoEnabled()) {
            final StringBuilder builder = new StringBuilder();
            builder.append("@[");
            builder.append(request.getRequestURI()).append(":");
            builder.append(request.getMethod()).append(":");
            builder.append("] @Request params >>");
            builder.append(AbstractRequestUtils.getRequestBody(request));
            request.getParameterMap().forEach((k, v) ->
                    builder.append(" -").append(k).append(":")
                            .append(JSON.toJSONString(v)));
            logger.info(builder.toString());
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
        logger.info("此次请求共消耗了{}毫秒", (System.currentTimeMillis()
                - Long.parseLong(request.getAttribute("REQUEST_START_TIME").toString())));
    }
}
