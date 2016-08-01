package net.jeeshop.biz.data.service;

import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.data.client.NuberInfoMapper;
import net.jeeshop.biz.data.model.NuberInfo;
import net.jeeshop.biz.data.model.NuberInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhaodh
 * Date: 16/6/13
 * Time: 18:07
 */
@Service
public class NumSerivce extends BaseService<NuberInfo,NuberInfoExample> {

    @Autowired
    private NuberInfoMapper nuberInfoMapper;

    @Override
    protected BaseMapper<NuberInfo, NuberInfoExample> getMapper() {
        return nuberInfoMapper;
    }


    public int batchInsertNumber(List<NuberInfo> nuberInfoList){

        return nuberInfoMapper.batchInsertNumber(nuberInfoList);
    }

    public List selectKeyValue(String khid){
        return nuberInfoMapper.selectKeyValue(khid);
    }

    public int delByPici(Integer pici){
        return nuberInfoMapper.delByPici(pici);
    }

    public int updateStateByPic(NuberInfo nuberInfo){return nuberInfoMapper.updateStateByPici(nuberInfo);}

    public List randTenPhoneNum(Integer numberid){
        return  nuberInfoMapper.randTenPhoneNum(numberid);
    }

    public void updateExpires(){ nuberInfoMapper.updateExpires();}

}
