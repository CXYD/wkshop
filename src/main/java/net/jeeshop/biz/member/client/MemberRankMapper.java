package net.jeeshop.biz.member.client;

import java.util.List;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.member.model.MemberRank;
import net.jeeshop.biz.member.model.MemberRankExample;

public interface MemberRankMapper extends BaseMapper<MemberRank, MemberRankExample> {
    int countByExample(MemberRankExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MemberRank record);

    int insertSelective(MemberRank record);

    List<MemberRank> selectByExample(MemberRankExample example);

    MemberRank selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MemberRank record);

    int updateByPrimaryKey(MemberRank record);
}