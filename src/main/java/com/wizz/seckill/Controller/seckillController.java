package com.wizz.seckill.Controller;

import com.wizz.seckill.Mapper.actMapper;
import com.wizz.seckill.Model.reqInfo;
import com.wizz.seckill.Model.reqRes;
import com.wizz.seckill.Mapper.reqInfoMapper;
import com.wizz.seckill.Service.RedisService;
import com.wizz.seckill.Util.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@CrossOrigin
@RestController
class seckillController{

    private static Logger logger = Logger.getLogger(seckillController.class.getName());

    @Autowired
    private reqInfoMapper infomap;

    @Autowired
    private actMapper actmap;

    @Autowired
    private RedisService cacheService;

    private Long ipLimit = new Long(999999);

    @RequestMapping(value = "/tickets" , method = RequestMethod.POST)
    reqRes resHandler(HttpServletRequest request, @RequestBody Map<String, Object> params){

        String phoneNum = (String)params.get("phoneNum");
        String stuId = (String)params.get("stuId");
        String stuName = (String)params.get("stuName");
        String ip = IpUtil.getIpAddr(request);

        logger.log(Level.INFO,"收到请求:" + "  : phoneNum = " + phoneNum
                + " ,stuId = " + stuId
                + " ,name = " + stuName + "  from :  " + ip);


        //合法性校验
        Long ipCnt = cacheService.increment(ip);
        if(ipCnt  >= ipLimit){
            logger.log(Level.INFO, "丢弃请求: IP<" + ip + "> 请求次数过多 :" + ipCnt);
           return new reqRes("false","too many ip requests");
        }

        if(infomap.isDuplicatePhoneNum(phoneNum) != 0) {
            logger.log(Level.INFO,"丢弃请求, 手机号重复 : "
                    + phoneNum + "  due to duplication");
            return new reqRes("false","duplicate phoneNum");

        }

        Long sum = Long.valueOf((String)cacheService.get("ticketsSum"));

        Long curCnt = cacheService.incrCnt("curCnt");
        int state = curCnt >= sum ? 1 : 0;


        infomap.updateInfo(state, phoneNum, stuId, stuName);

        logger.log(Level.INFO,"存储第< " +  curCnt + "> 条抢票记录"
                + "  : phoneNum = " + phoneNum
                + ",stuId = " + stuId
                + ",name = " + stuName
                +", result = " +  state);



        if(curCnt >= sum){
            return new reqRes("false","Out of tickets");
        }

        return new reqRes("true","");
    }

    @RequestMapping(value = "/tickets" , method = RequestMethod.GET)
    String sqlGet(){
        return infomap.getResult().toString();
    }

}
