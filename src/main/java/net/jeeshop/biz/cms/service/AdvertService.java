package net.jeeshop.biz.cms.service;

import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.cms.client.AdvertMapper;
import net.jeeshop.biz.cms.model.Advert;
import net.jeeshop.biz.cms.model.AdvertExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dinguangx@163.com
 * @date 2015-12-19 00:14
 */
@Service
public class AdvertService extends BaseService<Advert, AdvertExample> {
    @Autowired
    AdvertMapper advertMapper;

    @Override
    protected BaseMapper<Advert, AdvertExample> getMapper() {
        return advertMapper;
    }
    
}
