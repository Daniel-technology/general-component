package com.general.component.file.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 日志配置
 *
 * @author littlesnail
 * @create 2023−03-11 8:42 PM
 */
@Configuration
@ConfigurationProperties(prefix = "file")
@ConditionalOnProperty(prefix = "file", name = "enable", havingValue = "true")
public class FileProperty {
    /**
     * minio
     */
    private String provider;

    private String serverUrl;

    private String rootPath;


    private String user;

    private String password;


    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

