package com.general.component.log.core.aspect;

import com.general.component.common.jackson.JacksonSupport;
import com.general.component.common.system.Host;
import com.general.component.log.core.annotation.OpLog;
import com.general.component.log.core.model.OperateLogMessage;
import com.general.component.mvc.core.context.WebContext;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

/**
 * @description: 日志切面
 * @author: liuyan
 * @create: 2022−10-23 9:11 PM
 */
@Aspect
public class OpLogAspect {

    private static final Logger log = LoggerFactory.getLogger(OpLogAspect.class);


    @Pointcut("( @within(cn.teleinfo.summer.log.annotation.OpLog))" +
            "|| (@annotation(cn.teleinfo.summer.log.annotation.OpLog))) "
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
        operateLogMessage.setLogScene(logAnnotation.scene());
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
        //如果HttpServletRequest 不为空时，则代表时restful 接口请求
        if (RequestContextHolder.getRequestAttributes() != null) {
            HttpServletRequest requestWrapper = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
            List<Object> list = new ArrayList<>(10);
            for (Object arg : point.getArgs()) {
                if (arg instanceof MultipartFile) {
                    MultipartFile temp = (MultipartFile) arg;
                    list.add("文件名称：" + temp.getOriginalFilename());
                } else {
                    list.add(arg);
                }
            }
            operateLogMessage.setParam(JacksonSupport.objToJsonStr(list));
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
            String ip = Host.getIpAddr(requestWrapper);
            operateLogMessage.setUserIp(ip);
            Method method = signature.getMethod();
            String declaringClass = method.getDeclaringClass().getName();
            operateLogMessage.setClassPath(declaringClass);
            return;
        }
        //如果HttpServletRequest 为空时，则代表时非restful 接口请求
        operateLogMessage.setParam(JacksonSupport.objToJsonStr(point.getArgs()));
        operateLogMessage.setMethodName(point.getSignature().getName());
        Method method = signature.getMethod();
        String declaringClass = method.getDeclaringClass().getName();
        operateLogMessage.setClassPath(declaringClass);


    }

}
