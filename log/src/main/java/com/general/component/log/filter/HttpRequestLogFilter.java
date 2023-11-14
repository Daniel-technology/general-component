package com.general.component.log.filter;

import com.general.component.log.model.HttpLogMessage;
import com.general.component.log.resolver.HttpLogResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: HttpRequestLogFilter
 * @author: liuyan
 * @create: 2022−10-21 5:46 PM
 */
public class HttpRequestLogFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(HttpRequestLogFilter.class);

    /**
     * 是否展示ip地址
     */
    private final boolean showRegion;

    public HttpRequestLogFilter(boolean showRegion) {
        this.showRegion = showRegion;
    }

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("--------------- HttpRequestLogFilter 初始化完成  ---------------");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        CachingSupportServletRequestWrapper requestWrapper = new CachingSupportServletRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) response);
        HttpLogResolver httpLogResolver = new HttpLogResolver(requestWrapper, responseWrapper, showRegion, new HttpLogMessage());
        try {
            httpLogResolver.beforeHandle();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        chain.doFilter(requestWrapper, responseWrapper);
        try {
            httpLogResolver.afterHandle();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        httpLogResolver.resolve();
    }

    @Override
    public void destroy() {

    }
}
