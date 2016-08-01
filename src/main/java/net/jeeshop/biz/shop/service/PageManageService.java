package net.jeeshop.biz.shop.service;

import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.shop.client.CustomPageMapper;
import net.jeeshop.biz.shop.model.CustomPage;
import net.jeeshop.biz.shop.model.CustomPageExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: zhaodh
 * Date: 16/6/21
 * Time: 18:52
 */
@Service
public class PageManageService  extends BaseService<CustomPage,CustomPageExample>{

    @Autowired
    private CustomPageMapper customPageMapper;

    @Override
    protected BaseMapper<CustomPage, CustomPageExample> getMapper() {
        return customPageMapper;
    }

    public int cancelPageHome(String khid){return customPageMapper.updatePageHomeStatus(khid);};
}
