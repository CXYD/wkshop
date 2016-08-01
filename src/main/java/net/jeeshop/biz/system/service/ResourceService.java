package net.jeeshop.biz.system.service;

import com.google.common.collect.Maps;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.system.bean.MenuItem;
import net.jeeshop.biz.system.client.SysResourceMapper;
import net.jeeshop.biz.system.client.SysResourceMapperExt;
import net.jeeshop.biz.system.enums.ResourceType;
import net.jeeshop.biz.system.model.SysResource;
import net.jeeshop.biz.system.model.SysResourceExample;
import net.jeeshop.biz.system.model.SysUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author dinguangx@163.com
 * @date 2015-12-21 23:13
 */
@Service
public class ResourceService extends BaseService<SysResource, SysResourceExample> {
    @Autowired
    private SysResourceMapperExt sysResourceMapperExt;

    @Autowired
    private SysResourceMapper sysResourceMapper;

    @Override
    protected BaseMapper<SysResource, SysResourceExample> getMapper() {
        return sysResourceMapperExt;
    }

    /**
     * 加载用户可用菜单项
     *
     * @param u
     * @return
     */
    public Collection<MenuItem> loadMenus(SysUser u) 
    {
//		param.put("pid", pid);//菜单父ID
        List<SysResource> menus = sysResourceMapperExt.selectByRoleId(u.getRoleId());
        //创建菜单集合
        LinkedHashMap<Long, MenuItem> root = new LinkedHashMap<Long, MenuItem>();
        //循环添加菜单到菜单集合
        for (SysResource menu : menus)
        {
            MenuItem item = new MenuItem(menu.getResourceName(), null);
            item.setId(menu.getId());
            item.setPid(menu.getParentId());
            item.setMenuType(menu.getResourceType());
            item.setUrl(StringUtils.trimToEmpty(menu.getResourceValue()));
            if (item.isRootMenu()) {
                root.put(item.getId(), item);
            }
        }
        for (SysResource menu : menus) {
            MenuItem item = new MenuItem(menu.getResourceName(), null);
            item.setId(menu.getId());
            item.setPid(menu.getParentId());
            item.setMenuType(menu.getResourceType());
            item.setUrl(StringUtils.trimToEmpty(menu.getResourceValue()));
            if (!item.isRootMenu() && !item.isButton()) {
                MenuItem parentItem = root.get(item.getPid());
                if (parentItem != null) {
                    parentItem.addClild(item);
                } else {
                    logger.warn("菜单项{}({})没有对应的父级菜单", item.getName(), item.getId());
                }
            }
        }
        return root.values();
    }

    //加载根节点
    public List<MenuItem> loadMenus(SysUser u, long pid, String url) {
        Map<String, Object> params = Maps.newHashMap();
        if (u != null && u.getRoleId() != null) {
            params.put("rid", u.getRoleId());
        }
        params.put("pid", pid);
//        List<SysResource> menus = sysResourceMapperExt.selectByCondition(params);
        SysResourceExample example = new SysResourceExample();
        SysResourceExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(Long.valueOf(pid));
        List<SysResource>menus = sysResourceMapper.selectByExample(example);
        // 创建菜单集合
        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        // 循环添加菜单到菜单集合
		for (Iterator<SysResource> it = menus.iterator(); it.hasNext();) {
			SysResource sysResource = it.next();
            MenuItem menuItem = new MenuItem(sysResource.getResourceName(), null);
            menuItem.setId(sysResource.getId());
            menuItem.setPid(sysResource.getParentId());
            menuItem.setMenuType(sysResource.getResourceType());
            if (url != null) {
                menuItem.setUrl(url);
            } else {
                menuItem.setUrl(sysResource.getResourceValue());
            }
            menuItems.add(menuItem);
        }
        // 加载子菜单，注意 只加载type为模块级或页面级的
        for (int i = 0; i < menuItems.size(); i++) {
            MenuItem menuItem = menuItems.get(i);
            if (StringUtils.isEmpty(menuItem.getType().toString()) || menuItem.getType().equals(ResourceType.button)) {
                continue;
            }
            SysResource sysResource = new SysResource();
            sysResource.setParentId(menuItem.getId());
            loadChildrenByPid(menuItem, sysResource, url, u);
        }

        return menuItems;
    }


    /**
     * 根据父ID加载子节点
     * @param item
     * @param menu
     * @param url
     * @param user
     */
    private void loadChildrenByPid(MenuItem item, SysResource menu, String url, SysUser user) {
        Map<String, Object> param = Maps.newHashMap();
        if (user != null && user.getRoleId() != null) {
            param.put("rid", String.valueOf(user.getRoleId()));
        }
        param.put("pid", String.valueOf(menu.getParentId()));
        // 加载菜单节点
        List<SysResource> data = sysResourceMapperExt.selectByCondition(param);
        if (data == null || data.size() == 0) {
            return;
        }
        if (item.getChildren() == null) {
            item.setChildren(new ArrayList<MenuItem>());
        }
        // 创建菜单节点
        for (int i = 0; i < data.size(); i++) {
            SysResource entryMenu = data.get(i);
            MenuItem addItem = new MenuItem(entryMenu.getResourceName(), null);
            addItem.setId(entryMenu.getId());
            addItem.setPid(entryMenu.getParentId());
            addItem.setMenuType(entryMenu.getResourceType());
            if (url != null) {
                addItem.setUrl(url);
            } else {
                addItem.setUrl(entryMenu.getResourceValue());
            }
            item.getChildren().add(addItem);
        }
        // 根据菜单节点进行递归加载
        for (int i = 0; i < item.getChildren().size(); i++) {
            SysResource itemMenu = new SysResource();
            itemMenu.setParentId(item.getChildren().get(i).getId());
            loadChildrenByPid(item.getChildren().get(i), itemMenu, url, user);
        }
    }

    @Transactional
    public boolean addOrUpdate(SysResource menu, SysResource itemMenu) {
        if (itemMenu != null) {
            // 添加子菜单
            sysResourceMapper.insert(itemMenu);
        }
        // 修改父菜单
        sysResourceMapper.updateByPrimaryKey(menu);
        return true;
    }
}
