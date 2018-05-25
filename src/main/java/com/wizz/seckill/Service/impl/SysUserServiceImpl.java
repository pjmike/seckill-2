package com.wizz.seckill.Service.Impl;

import com.auth0.jwt.interfaces.Claim;
import com.wizz.seckill.Mapper.SysUserMapper;
import com.wizz.seckill.Model.SysUser;
import com.wizz.seckill.Service.SysUserService;
import com.wizz.seckill.Util.jwt.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author pjmike
 * @create 2018-05-19 15:04
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper userMapper;
    @Override
    public SysUser findUserById(Integer id) {
        return userMapper.findUserById(id);
    }

    @Override
    public SysUser findUserByName(String username) {
        return userMapper.findUserByName(username);
    }

    @Override
    public SysUser addSysUser(SysUser user) {
        userMapper.insertUser(user);
        return user;
    }

    @Override
    public Integer getUserIdByToken(String token) throws UnsupportedEncodingException {
        Map<String, Claim> map = JwtToken.verifyToken(token);
        Integer Id = map.get("userid").asInt();
        return Id;
    }
}
