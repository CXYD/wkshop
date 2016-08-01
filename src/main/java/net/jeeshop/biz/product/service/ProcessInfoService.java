package net.jeeshop.biz.product.service;

import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.product.client.ProcessInfoMapper;
import net.jeeshop.biz.product.model.ProcessInfo;
import net.jeeshop.biz.product.model.ProcessInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: zhaodh
 * Date: 16/6/12
 * Time: 14:58
 */

@Service
public class ProcessInfoService  extends BaseService<ProcessInfo,ProcessInfoExample>{

    @Autowired
    private ProcessInfoMapper processInfoMapper;

    @Override
    protected BaseMapper<ProcessInfo, ProcessInfoExample> getMapper() {
        return processInfoMapper;
    }
}
