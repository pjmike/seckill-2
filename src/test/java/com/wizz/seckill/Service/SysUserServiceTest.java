package com.wizz.seckill.Service;


import com.wizz.seckill.Controller.seckillControllerTest;
import com.wizz.seckill.Model.SysUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.logging.Level;
import java.util.logging.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserServiceTest {

    private Logger log = Logger.getLogger(seckillControllerTest.class.getName());

    @Autowired
    private SysUserService userService;
    @Test
    public void findUserById() {

//        Assert.assertNotNull(userService.findUserById(1));
    }

    @Test
    public void findUserByName() {

//        Assert.assertNotNull(userService.findUserByName("pj"));
    }

    @Test
    public void addSysUser() {

        //SysUser user = new SysUser("pjmike233", "123456");
        //log.log(Level.INFO, "user: {}" + userService.addSysUser(user));
    }
}