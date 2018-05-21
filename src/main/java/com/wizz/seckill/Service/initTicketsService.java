package com.wizz.seckill.Service;

import com.wizz.seckill.Mapper.actMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component//被spring容器管理
@Order(1)//如果多个自定义ApplicationRunner，用来标明执行顺序
public class initTicketsService implements ApplicationRunner {
    @Autowired
    private actMapper actmap;

    @Autowired
    private RedisService cacheService;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        Long tickets = actmap.getTickets(2);
        assert(tickets != null);
        cacheService.set("ticketsSum","" + tickets,3600 * 24);
    }
}
