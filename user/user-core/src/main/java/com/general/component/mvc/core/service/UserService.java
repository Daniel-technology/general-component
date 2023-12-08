package com.general.component.mvc.core.service;

import com.general.component.mvc.core.model.Menu;
import com.general.component.mvc.core.model.Role;
import com.general.component.mvc.core.model.User;

/**
 * 用户服务
 *
 * @author Daniel-SESA735395
 * @date 2023/12/8
 */
public interface UserService<T extends User, R extends Role, M extends Menu> {

    /**
     * 用户登录
     *
     * @param account  账号
     * @param password 密码
     * @return 用户
     */
    T login(String account, String password);


}
