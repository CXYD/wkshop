package net.jeeshop.biz.member.client;

import java.util.List;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.member.model.MemberAddress;
import net.jeeshop.biz.member.model.MemberAddressExample;

public interface MemberAddressMapper extends BaseMapper<MemberAddress, MemberAddressExample> {
    int countByExample(MemberAddressExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MemberAddress record);

    int insertSelective(MemberAddress record);

    List<MemberAddress> selectByExample(MemberAddressExample example);

    MemberAddress selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MemberAddress record);

    int updateByPrimaryKey(MemberAddress record);
}