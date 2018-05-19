package com.wizz.seckill.Model;

import lombok.Data;

/**
 * 账号
 *
 * @author pjmike
 * @create 2018-05-19 14:55
 */
@Data
public class SysUser {
    private Integer id;
    private String username;
    private String password;

    public SysUser() {
    }

    public SysUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
