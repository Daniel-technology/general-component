package com.general.component.common.license.core;


/**
 * @description: LicenseExtra
 * @author: liuyan
 * @create: 2022−05-20 4:24 PM
 */
public class LicenseExtra {

    /**
     * 服务器硬件信息
     */
    private String machineCode;
    /**
     * 版本
     */
    private Double version;

    public LicenseExtra(String machineCode, Double version) {
        this.machineCode = machineCode;
        this.version = version;
    }

    public String getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    public Double getVersion() {
        return version;
    }

    public void setVersion(Double version) {
        this.version = version;
    }
}
