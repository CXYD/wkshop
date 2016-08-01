/**
 * 2012-7-5
 * jqsl2012@163.com
 */
package net.jeeshop.biz.system.bean;

import net.jeeshop.biz.system.enums.ResourceType;
import net.jeeshop.core.SystemManager;

import java.util.ArrayList;
import java.util.List;


/**
 * 页面上显示的菜单项，每一个MenuItem对应一个节点
 */
public class MenuItem {
    private Long id;// 0：根节点，否则是子节点
    private Long pid;// 菜单项的父亲节点
    private String url;// 菜单的URL地址
    private String target = "rightFrame";// 打开的目标
    private String name;// 菜单名称
    private boolean open = false;// 是否展开
    private boolean checked;// true:勾选
    private List<MenuItem> children;// 子节点
    private ResourceType type = ResourceType.module;// module：模块 ,page：页面 ,button：功能
    private String icon;// 节点图标

    /**
     * 指定菜单的功能类型
     *
     * @param menuType
     */
    public void setMenuType(ResourceType menuType) {
        if (menuType == null) {
            this.type = null;
            return;
        } else {
            this.type = menuType;
            //为z-tree自定义图标
            SystemSettingBean systemSetting = SystemManager.getInstance().getSystemSetting();
            if (menuType == (ResourceType.page)) {
//                this.setIcon(systemSetting.getManageLeftTreeLeafIcon());
//				this.setIcon("/myshop/resource/images/letter.gif");
//				this.setIcon(manageHttp+"../resource/images/letter.gif");
            } else if (menuType == (ResourceType.button)) {
//                this.setIcon(systemSetting.getManageLeftTreeLeafIcon());
//				this.setIcon(manageHttp+"../resource/images/reply.gif");
//				this.setIcon("/myshop/resource/images/reply.gif");
            }
        }
    }

    /**
     * 判断是否是非按钮的功能
     *
     * @return
     */
    public boolean isButton() {
        if (this.type != null && this.type.equals(ResourceType.button)) {
            return true;
        }
        return false;
    }

    public boolean isRootMenu() {
        return new Long(0).equals(pid) || pid == null;
    }

    public void addClild(MenuItem item) {
        if (children == null) {
            children = new ArrayList<MenuItem>();
        }
        children.add(item);
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<MenuItem> getChildren() {
        return children;
    }

    public void setChildren(List<MenuItem> children) {
        this.children = children;
    }

    public MenuItem(String name, List<MenuItem> children) {
        super();
        this.name = name;
        this.children = children;
    }

    public MenuItem() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

}
