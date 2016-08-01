package net.jeeshop.biz.cms.client;

import java.util.List;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.cms.model.NotifyTemplate;
import net.jeeshop.biz.cms.model.NotifyTemplateExample;

public interface NotifyTemplateMapper extends BaseMapper<NotifyTemplate, NotifyTemplateExample> {
    int countByExample(NotifyTemplateExample example);

    int deleteByPrimaryKey(Long id);

    int insert(NotifyTemplate record);

    int insertSelective(NotifyTemplate record);

    List<NotifyTemplate> selectByExample(NotifyTemplateExample example);

    NotifyTemplate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NotifyTemplate record);

    int updateByPrimaryKey(NotifyTemplate record);
}