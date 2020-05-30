package com.li.yun.biao.job.admin.controller.interceptor;

import com.li.yun.biao.job.admin.controller.annotation.PermissionLimit;
import com.li.yun.biao.job.admin.core.model.JobUser;
import com.li.yun.biao.job.admin.core.util.I18nUtil;
import com.li.yun.biao.job.admin.service.LoginService;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: liyunbiao
 * @date: 2020/5/28 1:54 下午
 * @description 权限拦截
 */
@Component
public class PermissionInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return super.preHandle(request, response, handler);
        }

        // if need login
        boolean needLogin = true;
        boolean needAdminuser = false;
        HandlerMethod method = (HandlerMethod) handler;
        PermissionLimit permission = method.getMethodAnnotation(PermissionLimit.class);
        if (permission != null) {
            needLogin = permission.limit();
            needAdminuser = permission.adminUser();
        }

        if (needLogin) {
            JobUser loginUser = loginService.ifLogin(request, response);
            if (loginUser == null) {
                response.sendRedirect(request.getContextPath() + "/toLogin");
                //request.getRequestDispatcher("/toLogin").forward(request, response);
                return false;
            }
            if (needAdminuser && loginUser.getRole() != 1) {
                throw new RuntimeException(I18nUtil.getString("system_permission_limit"));
            }
            request.setAttribute(LoginService.LOGIN_IDENTITY_KEY, loginUser);
        }

        return super.preHandle(request, response, handler);
    }

}
