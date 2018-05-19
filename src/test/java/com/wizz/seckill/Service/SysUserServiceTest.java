package com.wizz.seckill.Service;

import com.wizz.seckill.Model.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SysUserServiceTest {
    @Autowired
    private SysUserService userService;
    @Test
    public void findUserById() {
        Assert.assertNotNull(userService.findUserById(1));
    }

    @Test
    public void findUserByName() {
        Assert.assertNotNull(userService.findUserByName("pj"));
    }

    @Test
    public void addSysUser() {
        SysUser user = new SysUser("pjmike233", "123456");
        log.info("user: {}",userService.addSysUser(user));
    }
}