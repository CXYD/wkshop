package net.jeeshop.biz.system.client;

import java.util.List;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.system.model.Express;
import net.jeeshop.biz.system.model.ExpressExample;

public interface ExpressMapper extends BaseMapper<Express, ExpressExample> {
    int countByExample(ExpressExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Express record);

    int insertSelective(Express record);

    List<Express> selectByExample(ExpressExample example);

    Express selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Express record);

    int updateByPrimaryKey(Express record);
}