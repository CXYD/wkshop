package net.jeeshop.biz.cms.client;

import java.util.List;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.cms.model.FriendLink;
import net.jeeshop.biz.cms.model.FriendLinkExample;

public interface FriendLinkMapper extends BaseMapper<FriendLink, FriendLinkExample> {
    int countByExample(FriendLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FriendLink record);

    int insertSelective(FriendLink record);

    List<FriendLink> selectByExample(FriendLinkExample example);

    FriendLink selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FriendLink record);

    int updateByPrimaryKey(FriendLink record);
}