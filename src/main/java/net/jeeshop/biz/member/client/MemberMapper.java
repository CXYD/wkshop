package net.jeeshop.biz.member.client;

import java.util.List;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.member.model.Member;
import net.jeeshop.biz.member.model.MemberExample;

public interface MemberMapper extends BaseMapper<Member, MemberExample> {
    int countByExample(MemberExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Member record);

    int insertSelective(Member record);

    List<Member> selectByExample(MemberExample example);

    Member selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);

    void regMember(Member member);

}