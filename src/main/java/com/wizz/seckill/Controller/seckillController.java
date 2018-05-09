package com.wizz.seckill.Controller;

import com.wizz.seckill.Model.reqInfo;
import com.wizz.seckill.Model.reqRes;
import com.wizz.seckill.Mapper.reqInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
class seckillController{

    @Autowired
    reqInfoMapper infomap;

    @RequestMapping(value = "/tickets" , method = RequestMethod.POST)
    reqRes resHandler(@RequestBody Map<String, Object> params){
        String phoneNum = (String)params.get("phoneNum");
        String stuId = (String)params.get("stuId");
        String stuName = (String)params.get("name");
        //state 是IP查重 + 手机号查重通过
        infomap.updateInfo(0,"15092825097","15010180018","李卓");
        return new reqRes("true","");
    }
}
