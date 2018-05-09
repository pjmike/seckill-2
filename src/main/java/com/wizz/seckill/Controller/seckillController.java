package com.wizz.seckill.Controller;

import com.wizz.seckill.Model.reqRes;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
class seckillController{
    @RequestMapping(value = "/tickets" , method = RequestMethod.POST)
    reqRes resHandler(@RequestBody Map<String, Object> params){


    }
}
