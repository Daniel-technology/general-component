package com.general.component.log.core.prop;

/**
 * 日志配置
 *
 * @author littlesnail
 * @create 2023−04-18 2:37 PM
 * @since 0.6.0
 */
public abstract class LogProperties {

    /**
     * 是否开启 true开启 false关闭
     */
    private boolean enable;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
