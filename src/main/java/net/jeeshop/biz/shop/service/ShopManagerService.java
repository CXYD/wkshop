package net.jeeshop.biz.shop.service;

import net.jeeshop.biz.base.bean.PageBean;
import net.jeeshop.biz.base.bean.PageQueryBean;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.product.model.ProductClass;
import net.jeeshop.biz.product.model.ProductClassExample;
import net.jeeshop.biz.shop.client.ShopManagerMapper;
import net.jeeshop.biz.system.client.SysUserMapper;
import net.jeeshop.biz.system.model.SysUser;
import net.jeeshop.biz.system.model.SysUserExample;
import net.jeeshop.web.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/6/1 0001.
 */
@Service
public class ShopManagerService extends BaseService<SysUser,SysUserExample>{
    @Autowired
    ShopManagerMapper shopManagerMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    public void addManager(SysUser sysUser) {
        shopManagerMapper.addManager(sysUser);
    }

    public SysUser getManagerByName(SysUser sysUser) {
        return shopManagerMapper.getManagerByName(sysUser);
    }

    public Integer getUserCount(String khid) {
        return shopManagerMapper.getUserCount(khid);
    }

    public List<SysUser> getUserList(Page<SysUser> page) {
        return shopManagerMapper.getUserList(page);
    }

    public SysUser getUser(String username) {
        return shopManagerMapper.getUser(username);
    }

    public void upManager(SysUser sysUser) {
        shopManagerMapper.upManager(sysUser);
    }

    public SysUser getManagerNoID(SysUser sysUser) {
        return shopManagerMapper.getManagerNoID(sysUser);
    }

    public void delManager(SysUser sysUser) {
        shopManagerMapper.delManager(sysUser);
    }


    /**
     * 分页查询数据
     *
     * @param
     * @return
     */
    public PageBean<SysUser> selectPageList(final SysUserExample example, PageQueryBean pageQueryBean) {


        PageBean<SysUser> pagerModel =  executePageQuery(new PageQueryExecutor<SysUser>() {
            @Override
            public List<SysUser> executeQuery() {
                return sysUserMapper.selectByExample(example);
            }
        }, pageQueryBean);
        return pagerModel;
    }

    @Override
    protected BaseMapper<SysUser, SysUserExample> getMapper() {
        return shopManagerMapper;
    }
}
