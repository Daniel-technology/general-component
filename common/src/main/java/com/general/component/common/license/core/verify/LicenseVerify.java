package com.general.component.common.license.core.verify;


/**
 * @description: 校验参数
 * @author: liuyan
 * @create: 2022−05-18 2:11 PM
 */
public class LicenseVerify {
    /**
     * 证书subject
     */
    private String subject;

    /**
     * 别称
     */
    private String alias;

    /**
     * 访问公钥库的密码
     */
    private String storePass;

    /**
     * 证书生成路径
     */
    private String licensePath;

    /**
     * 公钥库存储路径
     */
    private String publicCertsStorePath;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getStorePass() {
        return storePass;
    }

    public void setStorePass(String storePass) {
        this.storePass = storePass;
    }

    public String getLicensePath() {
        return licensePath;
    }

    public void setLicensePath(String licensePath) {
        this.licensePath = licensePath;
    }

    public String getPublicCertsStorePath() {
        return publicCertsStorePath;
    }

    public void setPublicCertsStorePath(String publicCertsStorePath) {
        this.publicCertsStorePath = publicCertsStorePath;
    }
}
