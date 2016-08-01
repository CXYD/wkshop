package net.jeeshop.biz.system.client;

import java.util.List;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.member.model.Member;
import net.jeeshop.biz.system.model.SysUser;
import net.jeeshop.biz.system.model.SysUserExample;

public interface SysUserMapper extends BaseMapper<SysUser, SysUserExample> {
    int countByExample(SysUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    List<SysUser> selectByExample(SysUserExample example);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser getUserByName(SysUser user);


}