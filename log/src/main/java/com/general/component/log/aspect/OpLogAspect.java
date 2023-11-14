package com.general.component.log.aspect;

import com.general.component.common.jackson.JacksonSupport;
import com.general.component.log.annotation.OpLog;
import com.general.component.log.model.OperateLogMessage;
import com.general.component.log.resolver.HostInfo;
import com.general.component.log.resolver.WebContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Objects;

/**
 * @description: 日志切面
 * @author: liuyan
 * @create: 2022−10-23 9:11 PM
 */
@Aspect
public class OpLogAspect {

    private static final Logger log = LoggerFactory.getLogger(OpLogAspect.class);


    @Pointcut("( @within(com.general.component.log.annotation.OpLog) && bean(*Controller))" +
            "|| (@annotation(com.general.component.log.annotation.OpLog)) && bean(*Controller)) "
    )
    public void logPointCut() {

    }

    @SuppressWarnings({"unchecked"})
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result;
        OperateLogMessage operateLogMessage = new OperateLogMessage();
        OpLog logAnnotation = null;
        try {
            operateLogMessage.setCostMillis(System.currentTimeMillis());
            MethodSignature signature = (MethodSignature) point.getSignature();
            // 初始化日志信息
            initOperateLogMessage(signature, operateLogMessage, point);
            // 执行方法
            result = point.proceed();
            logAnnotation = getLogAnnotation(point, signature);
            if (logAnnotation == null) {
                return result;
            }
            //更新日志信息
            updateOperateLogMessage(logAnnotation, operateLogMessage, result);
            WebContext.getBean(logAnnotation.callback()).callback(operateLogMessage);
            return result;
        } catch (Exception e) {
            // todo 添加异常信息
            log.error("error ", e);
            operateLogMessage.setExCode(e.getMessage());
            if (logAnnotation != null) {
                WebContext.getBean(logAnnotation.callback()).callback(operateLogMessage);
            }
            throw e;
        }
    }

    /**
     * 更新操作日志
     *
     * @param logAnnotation
     * @param operateLogMessage
     * @param result
     */
    private void updateOperateLogMessage(OpLog logAnnotation,
                                         OperateLogMessage operateLogMessage,
                                         Object result) {
        operateLogMessage.setDesc(logAnnotation.value());
        //更新返回值及耗时
        operateLogMessage.setReturnObj(result);
        operateLogMessage.setCostMillis(System.currentTimeMillis() - operateLogMessage.getCostMillis());
    }

    /**
     * 获取日志注解
     *
     * @param point     point
     * @param signature signature
     * @return Log
     */
    private OpLog getLogAnnotation(ProceedingJoinPoint point, MethodSignature signature) {
        OpLog logAnnotation = AnnotationUtils.findAnnotation(point.getTarget().getClass(), OpLog.class);
        if (logAnnotation == null) {
            logAnnotation = signature.getMethod().getAnnotation(OpLog.class);
        }
        return logAnnotation;
    }

    /**
     * 初始化日志
     *
     * @throws Exception
     */
    private void initOperateLogMessage(MethodSignature signature,
                                       OperateLogMessage operateLogMessage,
                                       ProceedingJoinPoint point) throws Exception {
        HttpServletRequest requestWrapper = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        operateLogMessage.setParam(JacksonSupport.objToJsonStr(point.getArgs()));
        String requestUri = Objects.requireNonNull(requestWrapper).getRequestURI();
        operateLogMessage.setMethodName(requestUri);
        operateLogMessage.setRequestMethodType(requestWrapper.getMethod());
        Enumeration<String> headers = requestWrapper.getHeaderNames();
        while (headers.hasMoreElements()) {
            String headerName = headers.nextElement();
            if ("user-agent".equals(headerName)) {
                operateLogMessage.setUserAgent(requestWrapper.getHeader(headerName));
            }
            if ("host".equals(headerName)) {
                operateLogMessage.setHost(requestWrapper.getHeader(headerName));
            }
        }
        String ip = HostInfo.getIpAddr(requestWrapper);
        operateLogMessage.setUserIp(ip);
        Method method = signature.getMethod();
        String declaringClass = method.getDeclaringClass().getName();
        operateLogMessage.setClassPath(declaringClass);
    }

}
