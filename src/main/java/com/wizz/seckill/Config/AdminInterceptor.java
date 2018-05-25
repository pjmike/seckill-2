package com.wizz.seckill.Config;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.interfaces.Claim;
import com.wizz.seckill.Model.SysUser;
import com.wizz.seckill.Model.reqRes;
import com.wizz.seckill.Service.RedisService;
import com.wizz.seckill.Service.SysUserService;
import com.wizz.seckill.Util.jwt.JwtToken;
import com.wizz.seckill.exception.ObjectException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

/**
 * 拦截器
 *
 * @author pjmike
 * @create 2018-05-24 20:39
 */
@Component
@Slf4j
public class AdminInterceptor implements HandlerInterceptor {
    @Autowired
    private SysUserService userService;
    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取Authorization字段
        String authorization = request.getHeader("Authorization");
        String userId = request.getHeader("userId");

        if (StringUtils.isBlank(authorization)) {
            throw new ObjectException("用户未登录,请前往登录");
        }
        if (JwtToken.verifyTokenTime(authorization)) {
            log.info("JWT已过期,判断redis中存储的token是否过期");
            //jwt过期，如果redis中的token没过期，即refresh时间，refresh时间是jwt过期时间的两倍,在refresh有效期内重新下发token给客户端
            if (redisService.get("JWT-" + userId) == null) {
                throw new ObjectException("身份信息已过期,请重新登录");
            }
            String refreshToken = JwtToken.createToken(Long.valueOf(userId));
            response.setHeader("Authorization", refreshToken);
            //再次设置redis
            //将jwt放在redis中，设置jwt过期时间的两倍，即refresh刷新时间
            long refreshTime = 36000L;
            redisService.set("JWT-" + userId, refreshToken, refreshTime);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf-8");
            PrintWriter printWriter = response.getWriter();
            //下发新的jwt
            printWriter.write(JSON.toJSONString(new reqRes("true","new jwt")));
            return false;
        } else {
            //再次验证token的用户userid和用户信息
            Map<String, Claim> map = JwtToken.verifyToken(authorization);
            Integer Id = map.get("userid").asInt();
            log.info("用户的id：{}",Id);
            SysUser user = userService.findUserById(Id);
            if (user == null) {
                throw new ObjectException("用户不存在");
            }
            log.info("用户 {} 已成功登录", user.getUsername());
            return true;
        }
    }
}
