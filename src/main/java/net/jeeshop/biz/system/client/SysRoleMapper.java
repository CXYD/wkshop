package net.jeeshop.biz.system.client;

import java.util.List;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.system.model.SysRole;
import net.jeeshop.biz.system.model.SysRoleExample;

public interface SysRoleMapper extends BaseMapper<SysRole, SysRoleExample> {
    int countByExample(SysRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    List<SysRole> selectByExample(SysRoleExample example);

    SysRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
}