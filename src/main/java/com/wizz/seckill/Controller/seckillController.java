package com.wizz.seckill.Controller;

import com.wizz.seckill.Model.reqInfo;
import com.wizz.seckill.Model.reqRes;
import com.wizz.seckill.Mapper.reqInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
class seckillController{

    @Autowired
    reqInfoMapper infomap;

    @RequestMapping(value = "/tickets" , method = RequestMethod.GET)
    reqRes resHandler(){
        //reqInfo rI = new reqInfo("李卓","15010180018","15092825097",0);
        infomap.updateInfo(0,"15092825097","15010180018","李卓");
        return new reqRes("true","");
    }
}
