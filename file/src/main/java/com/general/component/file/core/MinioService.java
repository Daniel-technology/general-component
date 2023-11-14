package com.general.component.file.core;

import com.general.component.file.config.FileProperty;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * minio服务
 *
 * @author littlesnail
 * @create 2023−03-11 9:01 PM
 */
@Configuration
@ConditionalOnBean(FileProperty.class)
@ConditionalOnProperty(prefix = "file", name = "provider", havingValue = "minio")
public class MinioService extends FileService {

    /**
     * 默认桶
     */
    private static final String DEFAUT_BUCKET = "data";


    /**
     * minio服务
     */
    private MinioClient minioClient;

    public MinioService(FileProperty fileProperty) {
        super(fileProperty);
        minioClient = MinioClient.builder().endpoint(fileProperty.getServerUrl())
                .credentials(fileProperty.getUser(), fileProperty.getPassword()).build();
    }


    /**
     * 上传文件
     *
     * @param file file
     * @return
     */
    @Override
    public String upload(MultipartFile file) throws Exception {
        String bucket = this.fileProperty.getRootPath() == null || this.fileProperty.getRootPath().isEmpty() ?
                DEFAUT_BUCKET : this.fileProperty.getRootPath();
        String objName = UUID.randomUUID() + file.getOriginalFilename();
        PutObjectArgs putObjectArgs = PutObjectArgs.builder().
                bucket(bucket)
                .object(objName)
                .contentType(getMimeType(file.getOriginalFilename()))
                .stream(file.getInputStream(), -1, 10485760)
                .build();
        ObjectWriteResponse objectWriteResponse = minioClient.putObject(putObjectArgs);
        return fileProperty.getServerUrl() + "/" + bucket + "/" + objName;
    }


    /**
     * 获取文件类型
     *
     * @param filePath
     * @return
     */
    private static String getMimeType(String filePath) {
        String contentType = URLConnection.getFileNameMap().getContentTypeFor(filePath);
        if (null == contentType) {
            if (filePath.endsWith(".css")) {
                contentType = "text/css";
            } else if (filePath.endsWith(".js")) {
                contentType = "application/x-javascript";
            }
        }
        if (null == contentType) {
            contentType = getMimeType(Paths.get(filePath));
        }
        return contentType;
    }

    private static String getMimeType(Path file) {
        try {
            return Files.probeContentType(file);
        } catch (IOException var2) {
            throw new RuntimeException(var2);
        }
    }

}
