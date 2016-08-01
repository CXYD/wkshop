package net.jeeshop.biz.cms.service;

import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.cms.client.HotQueryMapper;
import net.jeeshop.biz.cms.model.HotQuery;
import net.jeeshop.biz.cms.model.HotQueryExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yue
 * 2016年3月5日 下午3:45:39
 */
@Service
public class HotQueryService extends BaseService<HotQuery, HotQueryExample> {
    @Autowired
    private HotQueryMapper hotQueryMapper;
    
    @Override
    protected BaseMapper<HotQuery, HotQueryExample> getMapper() {
        return hotQueryMapper;
    }
    
    
}
