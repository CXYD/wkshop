package net.jeeshop.biz.task;

import net.jeeshop.biz.data.service.NumSerivce;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: zhaodh
 * Date: 16/7/18
 * Time: 17:48
 */
@Component
public class NumTaskJob {

    @Autowired
    private NumSerivce numSerivce;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


//    秒    0-59    , - * /
//    分    0-59    , - * /
//    小时    0-23    , - * /
//    日期    1-31    , - * ? / L W C
//    月份    1-12 或者 JAN-DEC    , - * /
//    星期    1-7 或者 SUN-SAT    , - * ? / L C #
//    年（可选）    留空, 1970-2099    , - * /
//            - 区间
//    * 通配符
//    ? 你不想设置那个字段

    @Scheduled(cron="0 * * * * *")
    public void checkNumExpires(){
        logger.info("校验占用过期号码");
        numSerivce.updateExpires();
    }

}
