package com.general.component.mvc.core.wrapper;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.ByteArrayOutputStream;

/**
 * CachingOutputStream
 *
 * @author littlesnail
 * @create 2023−04-06 3:43 PM
 * @since 0.5.0
 */
public class CachingOutputStream extends ServletOutputStream {

    private ByteArrayOutputStream bos;

    public CachingOutputStream(ByteArrayOutputStream bos) {
        this.bos = bos;
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {

    }

    @Override
    public void write(int b) {
        bos.write(b);
    }

    @Override
    public void write(byte[] b) {
        bos.write(b, 0, b.length);
    }
}
