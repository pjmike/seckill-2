package com.wizz.seckill.Controller;

import com.wizz.seckill.Model.reqInfo;
import com.wizz.seckill.Model.reqRes;
import com.wizz.seckill.Mapper.reqInfoMapper;
import com.wizz.seckill.Service.RedisService;
import com.wizz.seckill.Util.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
class seckillController{

    private static Logger logger = Logger.getLogger(seckillController.class.getName());

    @Autowired
    private reqInfoMapper infomap;

    @Autowired
    private RedisService cacheService;

    static boolean firstTime = true;
    static Long sum = new Long(2);
    static Long curCnt = new Long(0);

    @RequestMapping(value = "/tickets" , method = RequestMethod.POST)
    reqRes resHandler(HttpServletRequest request, @RequestBody Map<String, Object> params){

        if(firstTime){
            initCache();
            firstTime = false;
        }

        String phoneNum = (String)params.get("phoneNum");
        String stuId = (String)params.get("stuId");
        String stuName = (String)params.get("name");
        String ip = IpUtil.getIpAddr(request);

        logger.log(Level.INFO,"收到请求:" + "  : phoneNum = " + phoneNum
                + " ,stuId = " + stuId
                + " ,name = " + stuName + "  from :  " + ip);


        //合法性校验
        if(infomap.isDuplicatePhoneNum(phoneNum) != 0) {
            logger.log(Level.INFO,"丢弃请求, 手机号重复 : "
                    + phoneNum + "  due to duplication");

        return new reqRes("false","duplicate phoneNum");
    }

        //下面的都是有效数据
        if(curCnt >= sum){
            infomap.updateInfo(1, phoneNum, stuId, stuName);
            logger.log(Level.INFO, "余额不足, 数据库中有  " + curCnt );
            return new reqRes("false","Out of tickets");
        }


        infomap.updateInfo(0, phoneNum, stuId, stuName);
        curCnt = cacheService.incrCnt("curCnt");
        logger.log(Level.INFO,"存储第< " +  curCnt + "> 条抢票成功记录"
                + "  : phoneNum = " + phoneNum
                + ",stuId = " + stuId
                + ",name = " + stuName);


        return new reqRes("true","");
    }

    void initCache(){
        //sum 每次初始化为 总量 - 数据库余量
        cacheService.setCnt("curCnt",new Long(0));
    }
}
