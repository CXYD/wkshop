package net.jeeshop.biz.member.client;

import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.member.model.UserAddress;
import net.jeeshop.biz.member.model.UserAddressExample;

import java.util.List;

public interface UserAddressMapper extends BaseMapper<UserAddress, UserAddressExample> {
    int countByExample(UserAddressExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserAddress record);

    int insertSelective(UserAddress record);

    List<UserAddress> selectByExample(UserAddressExample example);

    UserAddress selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserAddress record);

    int updateByPrimaryKey(UserAddress record);

    void updateDefault();
}