package net.jeeshop.biz.member.client;

import java.util.List;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.member.model.MemberFeedback;
import net.jeeshop.biz.member.model.MemberFeedbackExample;

public interface MemberFeedbackMapper extends BaseMapper<MemberFeedback, MemberFeedbackExample> {
    int countByExample(MemberFeedbackExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MemberFeedback record);

    int insertSelective(MemberFeedback record);

    List<MemberFeedback> selectByExample(MemberFeedbackExample example);

    MemberFeedback selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MemberFeedback record);

    int updateByPrimaryKey(MemberFeedback record);
}