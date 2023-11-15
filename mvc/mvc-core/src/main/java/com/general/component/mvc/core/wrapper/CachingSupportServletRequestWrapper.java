package com.general.component.mvc.core.wrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * @description: CachingSupportServletRequestWrapper
 * @author: liuyan
 * @create: 2022−10-21 6:12 PM
 */
public class CachingSupportServletRequestWrapper extends HttpServletRequestWrapper {


    private static final Logger log = LoggerFactory.getLogger(CachingSupportServletRequestWrapper.class);

    private boolean cached = false;

    private byte[] byteArray = null;

    private ServletInputStream inputStream;

    private BufferedReader reader;


    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request the {@link HttpServletRequest} to be wrapped.
     * @throws IllegalArgumentException if the request is null
     */
    public CachingSupportServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public BufferedReader getReader() throws IOException {
        if (this.reader == null) {
            this.reader = new BufferedReader(new InputStreamReader(getInputStream(), getCharacterEncoding()));
        }
        return this.reader;
    }


    /**
     * 判断是否上传文件
     *
     * @return
     */
    public boolean isUpLoadFile() {
        if ("POST".equals(this.getMethod())
                && this.getHeader("content-type") != null
                && this.getHeader("content-type").startsWith("multipart/")) {
            return true;
        }
        return false;
    }

    @Override
    public synchronized ServletInputStream getInputStream() throws IOException {
        if ("GET".equals(this.getMethod())) {
            return null;
        }
        if (this.inputStream == null) {
            if (cached) {
                byteArray = StreamUtils.copyToByteArray(super.getInputStream());
                this.inputStream = new CachingInputStream(byteArray);
            } else {
                this.inputStream = super.getInputStream();
            }
        }
        return this.inputStream;
    }

    @Override
    public String getCharacterEncoding() {
        String enc = super.getCharacterEncoding();
        return (enc != null ? enc : WebUtils.DEFAULT_CHARACTER_ENCODING);
    }

    public boolean isCached() {
        return cached;
    }

    public void cacheRequest() {
        if (!this.cached) {
            this.cached = true;
            try {
                getInputStream();
            } catch (IOException e) {
                log.error("cache request failed", e);
                throw new RuntimeException(e);
            }
        }
    }

    public byte[] getByteArray() {
        return byteArray;
    }

    public void setByteArray(byte[] byteArray) {
        this.byteArray = byteArray;
    }
}
