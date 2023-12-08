package com.general.component.common.base;

import java.io.Serializable;

/**
 * 租户
 *
 * @author Daniel-SESA735395
 * @date 2023/12/8
 */
public class BaseTenantEntity implements Serializable {
    /**
     * 租户id
     */
    private Integer tenantId;
    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }
}
