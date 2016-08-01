package net.jeeshop.biz.cms.service;

import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.cms.client.FriendLinkMapper;
import net.jeeshop.biz.cms.model.FriendLink;
import net.jeeshop.biz.cms.model.FriendLinkExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by machenike on 2016/1/7.
 */
@Service
public class FriendLinkService extends BaseService<FriendLink, FriendLinkExample> {
    @Autowired
    private FriendLinkMapper friendLinkMapper;
    
    @Override
    protected BaseMapper<FriendLink, FriendLinkExample> getMapper() {
        return friendLinkMapper;
    }
    
    
}
