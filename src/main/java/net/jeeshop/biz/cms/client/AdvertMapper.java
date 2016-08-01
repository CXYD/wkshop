package net.jeeshop.biz.cms.client;

import java.util.List;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.cms.model.Advert;
import net.jeeshop.biz.cms.model.AdvertExample;

public interface AdvertMapper extends BaseMapper<Advert, AdvertExample> {
    int countByExample(AdvertExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Advert record);

    int insertSelective(Advert record);

    List<Advert> selectByExample(AdvertExample example);

    Advert selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Advert record);

    int updateByPrimaryKey(Advert record);
}