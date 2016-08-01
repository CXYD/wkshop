package net.jeeshop.biz.product.client;

import java.util.List;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.product.model.ProcessInfo;
import net.jeeshop.biz.product.model.ProcessInfoExample;

public interface ProcessInfoMapper extends BaseMapper<ProcessInfo, ProcessInfoExample> {
    int countByExample(ProcessInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProcessInfo record);

    int insertSelective(ProcessInfo record);

    List<ProcessInfo> selectByExampleWithBLOBs(ProcessInfoExample example);

    List<ProcessInfo> selectByExample(ProcessInfoExample example);

    ProcessInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProcessInfo record);

    int updateByPrimaryKeyWithBLOBs(ProcessInfo record);

    int updateByPrimaryKey(ProcessInfo record);
}