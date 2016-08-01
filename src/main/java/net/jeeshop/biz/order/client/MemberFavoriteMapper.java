package net.jeeshop.biz.order.client;

import java.util.List;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.order.model.MemberFavorite;
import net.jeeshop.biz.order.model.MemberFavoriteExample;

public interface MemberFavoriteMapper extends BaseMapper<MemberFavorite, MemberFavoriteExample> {
    int countByExample(MemberFavoriteExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MemberFavorite record);

    int insertSelective(MemberFavorite record);

    List<MemberFavorite> selectByExample(MemberFavoriteExample example);

    MemberFavorite selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MemberFavorite record);

    int updateByPrimaryKey(MemberFavorite record);
}