package net.jeeshop.biz.cms.client;

import java.util.List;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.cms.model.HotQuery;
import net.jeeshop.biz.cms.model.HotQueryExample;

public interface HotQueryMapper extends BaseMapper<HotQuery, HotQueryExample> {
    int countByExample(HotQueryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HotQuery record);

    int insertSelective(HotQuery record);

    List<HotQuery> selectByExample(HotQueryExample example);

    HotQuery selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HotQuery record);

    int updateByPrimaryKey(HotQuery record);
}