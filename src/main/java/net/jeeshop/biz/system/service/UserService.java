package net.jeeshop.biz.system.service;

import net.jeeshop.biz.base.bean.PageBean;
import net.jeeshop.biz.base.bean.PageQueryBean;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.member.model.Member;
import net.jeeshop.biz.system.bean.SysUserBean;
import net.jeeshop.biz.system.client.SysUserMapper;
import net.jeeshop.biz.system.client.SysUserMapperExt;
import net.jeeshop.biz.system.model.SysUser;
import net.jeeshop.biz.system.model.SysUserExample;
import net.jeeshop.core.util.MD5;
import net.jeeshop.web.util.LoginUserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author dinguangx@163.com
 * @date 2015-12-19 00:14
 */
@Service
public class UserService extends BaseService<SysUser, SysUserExample> {
    @Autowired
    SysUserMapper sysUserMapper;
    
    @Autowired
    SysUserMapperExt sysUserMapperExt;



    @Override
    protected BaseMapper<SysUser, SysUserExample> getMapper() {
        return sysUserMapper;
    }

    public SysUserBean selectUserBeanById(long uid) {
        return sysUserMapperExt.selectUserBeanById(uid);
    }
    public PageBean<SysUserBean> selectPageBean(final SysUserBean params, PageQueryBean pageQueryBean) {
        return executePageQuery(new PageQueryExecutor<SysUserBean>() {
            @Override
            public List<SysUserBean> executeQuery() {
                return sysUserMapperExt.selectByParams(params);
            }
        }, pageQueryBean);
    }

    /**
     * 由用户名密码查找用户
     *
     * @param username
     * @param plainPassword
     * @return
     */
    public SysUser selectByUsernamePassowrd(String username, String plainPassword) {
        String password = MD5.md5(plainPassword);

        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andPasswordEqualTo(password);
        return selectUniqueByExample(example);
    }

    /**
     * 更改用户密码
     *
     * @param id
     * @param newPassword
     */
    @Transactional
    public void updatePassword(Long id, String newPassword) {
        SysUser user = new SysUser();
        user.setId(id);
        user.setPassword(MD5.md5(newPassword));
        sysUserMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 新增用户
     *
     * @param newUser
     */
    @Transactional
    public int addUser(SysUser newUser) {
       /* SysUser loginUser = LoginUserHolder.getLoginUser();
        newUser.setCreateAccount(loginUser.getUsername());*/
        if (newUser.getIsValid() == null) {
            newUser.setIsValid(true);
        }
        newUser.setPassword(MD5.md5(newUser.getPassword()));
        return sysUserMapper.insert(newUser);
    }

    /**
     * 更新用户
     *
     * @param user
     */
    @Transactional
    public void updateUser(SysUser user) {
        SysUser loginUser = LoginUserHolder.getLoginUser();
        //更新操作跳过对密码的修改
        user.setPassword(null);
        user.setUpdateAccount(loginUser.getUsername());
        sysUserMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 根据昵称查找用户
     *
     * @param nickName
     * @return
     */
    public SysUser selectByNickName(String nickName) {
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        criteria.andNicknameEqualTo(nickName);
        return selectUniqueByExample(example);
    }

    /**
     * 根据username查找用户
     *
     * @param username
     * @return
     */
    public SysUser selectByUsername(String username) {
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        return selectUniqueByExample(example);
    }
    public SysUser getUserByName(SysUser user){
        return sysUserMapper.getUserByName(user);
    }

}
