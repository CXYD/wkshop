package net.jeeshop.biz.data.client;

import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.data.model.NuberInfo;
import net.jeeshop.biz.data.model.NuberInfoExample;

import java.util.List;

public interface NuberInfoMapper extends BaseMapper<NuberInfo, NuberInfoExample> {
    int countByExample(NuberInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NuberInfo record);

    int insertSelective(NuberInfo record);

    int batchInsertNumber(List<NuberInfo>nuberInfoList);

    List<NuberInfo> selectByExample(NuberInfoExample example);

    NuberInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NuberInfo record);

    int updateByPrimaryKey(NuberInfo record);

    List selectKeyValue(String khid);

    int delByPici(String pici);

    List randTenPhoneNum(int numberid);


    void updateExpires();
}