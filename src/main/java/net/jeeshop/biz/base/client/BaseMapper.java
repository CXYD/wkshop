package net.jeeshop.biz.base.client;

import net.jeeshop.biz.base.model.BaseModel;

import java.util.List;

/**
 * Created by dylan on 15-12-16.
 */
public interface BaseMapper<E extends BaseModel, Example> {
    int countByExample(Example example);

    int deleteByPrimaryKey(Long id);

    int insert(E record);

    int insertSelective(E record);

    List<E> selectByExample(Example example);

    E selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(E record);

    int updateByPrimaryKey(E record);


}
