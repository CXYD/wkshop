package net.jeeshop.biz.member.service;

import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.member.client.UserAddressMapper;
import net.jeeshop.biz.member.model.UserAddress;
import net.jeeshop.biz.member.model.UserAddressExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pengdong on 2016/7/12.
 */
@Service
public class UserAddressService extends BaseService<UserAddress, UserAddressExample> {
    @Autowired
    private UserAddressMapper userAddressMapper;
    @Override
    protected BaseMapper<UserAddress, UserAddressExample> getMapper() {
        return userAddressMapper;
    }

    public List<UserAddress> selectUserAddress(String username) {
        UserAddressExample userAddressExample = new UserAddressExample();
        UserAddressExample.Criteria criteria = userAddressExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        userAddressExample.setOrderByClause("defaulttype desc");
        return userAddressMapper.selectByExample(userAddressExample);
    }

    public void updateDefault() {
        userAddressMapper.updateDefault();
    }
}
