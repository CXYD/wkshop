package net.jeeshop.biz.system.client;

import java.util.List;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.system.model.SysArea;
import net.jeeshop.biz.system.model.SysAreaExample;

public interface SysAreaMapper extends BaseMapper<SysArea, SysAreaExample> {
    int countByExample(SysAreaExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysArea record);

    int insertSelective(SysArea record);

    List<SysArea> selectByExample(SysAreaExample example);

    SysArea selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysArea record);

    int updateByPrimaryKey(SysArea record);
}