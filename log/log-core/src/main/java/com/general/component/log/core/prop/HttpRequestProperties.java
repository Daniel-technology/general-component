package com.general.component.log.core.prop;

/**
 * http日志信息
 *
 * @author littlesnail
 * @create 2023−04-18 3:24 PM
 * @since 0.6.0
 */
public class HttpRequestProperties extends LogProperties {

    /**
     * 是否展示地区
     */
    private boolean showRegion;

    /**
     * 过滤的前缀
     */
    private String[] urlPatterns;

    /**
     * 执行顺序，默认是 Integer.MIN_VALUE + 3
     */
    private Integer order;

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }


    public boolean isShowRegion() {
        return showRegion;
    }

    public void setShowRegion(boolean showRegion) {
        this.showRegion = showRegion;
    }

    public String[] getUrlPatterns() {
        return urlPatterns;
    }

    public void setUrlPatterns(String[] urlPatterns) {
        this.urlPatterns = urlPatterns;
    }
}
