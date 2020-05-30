package com.li.yun.biao.redis.aspect;

import com.li.yun.biao.redis.annotations.DistributedLock;
import com.li.yun.biao.redis.lock.IDistributedLock;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import javax.annotation.Resource;
import java.lang.reflect.Method;

import static org.apache.log4j.Logger.getLogger;


/**
 * @author: liyunbiao
 * @date: 2020/5/27 7:25 下午
 * @description 定义切面
 */
@Aspect
@Configuration
@ConditionalOnClass(IDistributedLock.class)
@AutoConfigureAfter(DistributedLockAspect.class)
public class DistributedLockAspect {
    private static final Logger logger = getLogger(DistributedLockAspect.class);
    @Resource
    private IDistributedLock distributedLock;

    private ExpressionParser parser = new SpelExpressionParser();

    private LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();

    /**
     * 定义切入点
     */
    @Pointcut("@annotation(com.li.yun.biao.redis.annotations.DistributedLock)")
    private void lockPoint() {
    }

    /**
     * 环绕通知
     *
     * @param pjp pjp
     * @return 方法返回结果
     * @throws Throwable throwable
     */
    @Around("lockPoint()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        //获取注解信息
        DistributedLock lockAction = method.getAnnotation(DistributedLock.class);
        //生成key
        String logKey = getLogKey(lockAction, pjp, method);
        //获取请求次数
        int retryTimes = lockAction.action().equals(DistributedLock.LockFailAction.CONTINUE) ? lockAction.retryTimes() : 0;
        //获取锁
        boolean lock = distributedLock.lock(logKey, lockAction.keepMills(), retryTimes, lockAction.sleepMills());
        if (!lock) {
            logger.debug("get lock failed : " + logKey);
            return null;
        }

        //得到锁,执行方法，释放锁
        logger.debug("get lock success : " + logKey);
        try {
            return pjp.proceed();
        } catch (Exception e) {
            logger.error("execute locked method occured an exception", e);
        } finally {
            boolean releaseResult = distributedLock.releaseLock(logKey);
            logger.debug("release lock : " + logKey + (releaseResult ? " success" : " failed"));
        }
        return null;
    }

    /**
     * 获得分布式缓存的key
     *
     * @param lockAction 注解对象
     * @param pjp        pjp
     * @param method     method
     * @return String
     */
    private String getLogKey(DistributedLock lockAction, ProceedingJoinPoint pjp, Method method) {
        String name = lockAction.name();
        String value = lockAction.value();
        Object[] args = pjp.getArgs();
        return parse(name, method, args) + "_" + parse(value, method, args);
    }

    /**
     * 解析spring EL表达式
     *
     * @param key    key
     * @param method method
     * @param args   args
     * @return parse result
     */
    private String parse(String key, Method method, Object[] args) {
        String[] params = discoverer.getParameterNames(method);
        if (null == params || params.length == 0 || !key.contains("#")) {
            return key;
        }
        EvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < params.length; i++) {
            context.setVariable(params[i], args[i]);
        }
        return parser.parseExpression(key).getValue(context, String.class);
    }

}
