package com.general.component.log.core.filter;


import com.general.component.log.core.model.HttpLogMessage;
import com.general.component.log.core.resolver.HttpLogResolver;
import com.general.component.mvc.core.wrapper.CachingSupportServletRequestWrapper;
import com.general.component.mvc.core.wrapper.CachingSupportServletResponseWrapper;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: HttpRequestLogFilter
 * @author: liuyan
 * @create: 2022−10-21 5:46 PM
 */
public class HttpRequestLogFilter extends OncePerRequestFilter {


    /**
     * 是否展示ip地址
     */
    private final boolean showRegion;

    public HttpRequestLogFilter(boolean showRegion) {
        this.showRegion = showRegion;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        CachingSupportServletRequestWrapper requestWrapper = new CachingSupportServletRequestWrapper(request);
        CachingSupportServletResponseWrapper responseWrapper = new CachingSupportServletResponseWrapper(response);
        HttpLogResolver httpLogResolver = new HttpLogResolver(requestWrapper, responseWrapper, showRegion, new HttpLogMessage());
        try {
            httpLogResolver.beforeHandle();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        filterChain.doFilter(requestWrapper, responseWrapper);
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
