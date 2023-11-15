package com.general.component.log.core.resolver;

import com.general.component.common.jackson.JacksonSupport;
import com.general.component.common.system.Host;
import com.general.component.log.core.model.HttpLogMessage;
import com.general.component.mvc.core.wrapper.CachingSupportServletRequestWrapper;
import com.general.component.mvc.core.wrapper.CachingSupportServletResponseWrapper;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @description: http日志解析器
 * @author: liuyan
 * @create: 2022−10-21 6:12 PM
 */
public class HttpLogResolver implements LogResolver {

    private static final Logger log = LoggerFactory.getLogger(HttpLogResolver.class);

    /**
     * requestWrapper
     */
    private final CachingSupportServletRequestWrapper requestWrapper;
    /**
     * responseWrapper
     */
    private final CachingSupportServletResponseWrapper responseWrapper;

    /**
     * 日志信息
     */
    private final HttpLogMessage httpLogMessage;
    /**
     * 开始时间
     */
    private final long startTime;

    /**
     * 是否展示地址
     */
    private final boolean showRegion;

    /**
     * 构造方法
     *
     * @param requestWrapper  requestWrapper
     * @param responseWrapper responseWrapper
     */
    public HttpLogResolver(CachingSupportServletRequestWrapper requestWrapper,
                           CachingSupportServletResponseWrapper responseWrapper,
                           boolean showRegion,
                           HttpLogMessage httpLogMessage) {
        this.requestWrapper = requestWrapper;
        this.responseWrapper = responseWrapper;
        this.startTime = System.currentTimeMillis();
        this.showRegion = showRegion;
        this.httpLogMessage = httpLogMessage;
    }


    /**
     * 日志解析前
     *
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Override
    public HttpLogMessage beforeHandle() throws Exception {
        String paraName;
        httpLogMessage.setParam(initParam());

        if (showRegion) {
            try {
                String region = Host.searchRegion(Host.getIpAddr(requestWrapper));
                httpLogMessage.setRegion(region);
            } catch (Exception e) {
                log.error("HttpLogResolver-ipv6暂时不支持解析地址 ipv6:{}", Host.getIpAddr(requestWrapper));
            }
        }
        String requestUri = Objects.requireNonNull(requestWrapper).getRequestURI();
        httpLogMessage.setMethodName(requestUri);
        httpLogMessage.setRequestMethodType(requestWrapper.getMethod());
        Enumeration<String> headers = requestWrapper.getHeaderNames();
        Map<String, Object> mapHeaders = new HashMap<>();
        String ip = Host.getIpAddr(requestWrapper);
        mapHeaders.put("userIp", ip);
        while (headers.hasMoreElements()) {
            String headerName = headers.nextElement();
            paraName = requestWrapper.getHeader(headerName);
            mapHeaders.put(headerName, paraName);
        }
        httpLogMessage.setHeaders(mapHeaders);
        if ("POST".equals(httpLogMessage.getRequestMethodType())
                && mapHeaders.get("content-type") != null
                && mapHeaders.get("content-type").toString().startsWith("multipart/")) {
            httpLogMessage.setExtMsg("当前请求包含文件，文件不序列化");
        }
        return httpLogMessage;
    }

    /**
     * 初始化参数
     *
     * @return
     * @throws Exception
     */
    private String initParam() throws Exception {
        Map<String, String> mapParam = new HashMap<>();
        try {
            mapParam.put("reqParameter",
                    JacksonSupport.objToJsonStr(requestWrapper.getParameterMap()));
            if (requestWrapper.getMethod().equals(RequestMethod.GET.name())) {
            } else {
                if (!requestWrapper.isUpLoadFile()) {
                    requestWrapper.cacheRequest();
                    String requestBody = new String(IOUtils.toByteArray(requestWrapper.getInputStream()));
                    if (StringUtils.isNotBlank(requestBody)
                            && requestBody.contains("password")) {
                        Map<String, Object> mapRequestBody = new HashMap<>(16);
                        mapRequestBody.put("username", "*******");
                        mapRequestBody.put("password", "*******");
                        requestBody = JacksonSupport.objToJsonStr(mapRequestBody);
                    }
                    mapParam.put("reqBody", requestBody);
                    requestWrapper.getInputStream().reset();
                }
            }
            return JacksonSupport.objToJsonStr(mapParam);
        } catch (Exception e) {
            log.error("RequestLogInterceptor-参数序列化异常, parameter:{}",
                    requestWrapper.getParameterMap(), e);
            throw e;
        }
    }

    /**
     * 日志解析前
     *
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Override
    public HttpLogMessage afterHandle() throws Exception {
        httpLogMessage.setReturnObj(new String(responseWrapper.getContentAsByteArray()));
        httpLogMessage.setCostMillis(System.currentTimeMillis() - startTime);
        responseWrapper.copyBodyToResponse();
        return httpLogMessage;
    }

    /**
     * http日志解析
     */
    @Override
    public void resolve() {
        log.info("");
        log.info("================  HttpLog Start  ================");
        log.info("===  {}: {}: 耗时:{}ms",
                httpLogMessage.getRequestMethodType(),
                httpLogMessage.getMethodName(),
                httpLogMessage.getCostMillis());
        log.info("=== param ===  {}", httpLogMessage.getParam());
        if (StringUtils.isNotBlank(httpLogMessage.getExtMsg())) {
            log.info("===ext-msg===  {}", httpLogMessage.getExtMsg());
        }
        if (StringUtils.isNotBlank(httpLogMessage.getRegion())) {
            log.info("===region ===  {}", httpLogMessage.getRegion());
        }
//        Map<String, Object> mapHeaders = httpLogMessage.getHeaders();
//        for (Map.Entry<String, Object> entry : mapHeaders.entrySet()) {
//            log.info("===Headers===  {} : {}", entry.getKey(), entry.getValue());
//        }
        log.info("===returnMsg===  {}", httpLogMessage.getReturnObj());
        log.info("================  HttpLog End   ================");
        log.info("");

    }
}
