package com.general.component.mvc.core.wrapper;

import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * CachingSupportServletResponseWrapper
 *
 * @author littlesnail
 * @create 2023−04-07 4:42 PM
 * @since 0.5.0
 */
public class CachingSupportServletResponseWrapper extends ContentCachingResponseWrapper {

    private ByteArrayOutputStream buffer;
    private ServletOutputStream out;
    private PrintWriter writer;


    public CachingSupportServletResponseWrapper(HttpServletResponse resp) throws IOException {
        super(resp);
        // 真正存储数据的流
        buffer = new ByteArrayOutputStream();
        out = new CachingOutputStream(buffer);
        writer = new PrintWriter(new OutputStreamWriter(buffer, this.getCharacterEncoding()));
    }


    /**
     * 重载父类获取outputstream的方法
     */
    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return out;
    }

    /**
     * 重载父类获取writer的方法
     */
    @Override
    public PrintWriter getWriter() throws UnsupportedEncodingException {
        return writer;
    }

    /**
     * 重载父类获取flushBuffer的方法
     */
    @Override
    public void flushBuffer() throws IOException {
        if (out != null) {
            out.flush();
        }
        if (writer != null) {
            writer.flush();
        }
    }

    @Override
    public void reset() {
        buffer.reset();
    }

    /**
     * 将out、writer中的数据强制输出到WapperedResponse的buffer里面，否则取不到数据
     */
    public byte[] getResponseData() throws IOException {
        flushBuffer();
        return buffer.toByteArray();
    }

}
