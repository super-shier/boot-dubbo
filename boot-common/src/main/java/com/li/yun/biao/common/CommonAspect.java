package com.li.yun.biao.common;


import com.alibaba.fastjson.JSON;
import com.li.yun.biao.common.annotation.OperationLogDetail;
import com.li.yun.biao.common.enums.EnumErrorCode;
import com.li.yun.biao.common.model.DubboResponse;
import com.li.yun.biao.common.model.OperationLog;
import com.li.yun.biao.common.utils.IpHelper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.*;


/**
 * @Author: liyunbiao
 * @Date: 2019/11/26 5:31 PM
 * @description 日志打印
 */
public class CommonAspect {
    private static final Logger logger = LoggerFactory.getLogger(CommonAspect.class);

    @Pointcut("@annotation(com.li.yun.biao.common.annotation.OperationLogDetail)")
    //@Pointcut("execution(* com.li.yun.biao.*.service..*(..))")
    public void operationLog() {
    }

    /**
     * 环绕增强，相当于MethodInterceptor
     */
    @Around("operationLog()")
    public DubboResponse doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        DubboResponse response = null;
        long time = System.currentTimeMillis();//1、开始时间
        HttpServletRequest request = getHttpServletRequest();
        String uri = Objects.nonNull(request) ? request.getRequestURI() : "";
        String ip = Objects.nonNull(request) ? IpHelper.getIpAddr(request) : "";
        String methodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        logger.info("开始计时:{}  URI:{}  ip:{}  请求方法:{}  请求参数: {}", time, uri, ip, methodName, JSON.toJSONString(joinPoint.getArgs()));
        //调用实际方法
        long endTime = System.currentTimeMillis();
        try {
            response = (DubboResponse) joinPoint.proceed();
            time = endTime - time;
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            response = handlerException(e);
            return response;
        } finally {
            try {
                logger.info("结束计时:{}  URI:{}  耗时:{}  请求返回:{}", endTime, uri, endTime - time, JSON.toJSONString(response));
                //方法执行完成后增加日志
                addOperationLog(joinPoint, response, time);
            } catch (Exception e) {
                logger.error("LogAspect 操作失败:{}", e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private HttpServletRequest getHttpServletRequest() {
        try {
            ServletRequestAttributes requestAttr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            return requestAttr.getRequest();
        } catch (IllegalStateException e) {
            return null;
        }
    }

    private void addOperationLog(JoinPoint joinPoint, Object res, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        OperationLog operationLog = new OperationLog();
        operationLog.setRunTime(time);
        operationLog.setReturnValue(JSON.toJSONString(res));
        operationLog.setId(UUID.randomUUID().toString());
        operationLog.setArgs(JSON.toJSONString(joinPoint.getArgs()));
        operationLog.setCreateTime(new Date());
        operationLog.setMethod(signature.getDeclaringTypeName() + "." + signature.getName());
        operationLog.setUserId("#{currentUserId}");
        operationLog.setUserName("#{currentUserName}");
        OperationLogDetail annotation = signature.getMethod().getAnnotation(OperationLogDetail.class);
        if (annotation != null) {
            operationLog.setLevel(annotation.level());
            operationLog.setDescribe(getDetail(((MethodSignature) joinPoint.getSignature()).getParameterNames(), joinPoint.getArgs(), annotation));
            operationLog.setOperationType(annotation.operationType().getValue());
            operationLog.setOperationUnit(annotation.operationUnit().getValue());
        }
        logger.info("记录日志:{}", JSON.toJSONString(operationLog));
    }

    /**
     * 对当前登录用户和占位符处理
     *
     * @param argNames   方法参数名称数组
     * @param args       方法参数数组
     * @param annotation 注解信息
     * @return 返回处理后的描述
     */
    private String getDetail(String[] argNames, Object[] args, OperationLogDetail annotation) {
        Map<Object, Object> map = new HashMap<>(4);
        for (int i = 0; i < argNames.length; i++) {
            map.put(argNames[i], args[i]);
        }
        String detail = annotation.detail();
        try {
            detail = "'" + "#{currentUserName}" + "'=》" + annotation.detail();
            for (Map.Entry<Object, Object> entry : map.entrySet()) {
                Object k = entry.getKey();
                Object v = entry.getValue();
                detail = detail.replace("{{" + k + "}}", JSON.toJSONString(v));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }


    private DubboResponse<?> handlerException(Exception e) {
        if (e instanceof MethodArgumentNotValidException || e instanceof ConstraintViolationException) {
            return new DubboResponse(EnumErrorCode.VALID_EXCEPTION_CODE.getErrorCode(), e.getMessage());
        }
        logger.info("未知异常:{}", e.getMessage());
        return new DubboResponse<>(EnumErrorCode.SYSTEM_ERROR);
    }
}
