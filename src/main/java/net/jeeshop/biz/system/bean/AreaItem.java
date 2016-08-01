/**
 * 2012-7-5
 * jqsl2012@163.com
 */
package net.jeeshop.biz.system.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 区域节点，每一个AreaItem对应一个节点
 * @author alafighting
 */
public class AreaItem
{
    private Long id;// 0：根节点，否则是子节点
    private Long parentId;// 父级节点
    private String areaCode;//区域编码
    private String parentAreaCode;//父级区域编码
    private String areaName;// 区域名称

    private List<AreaItem> children;// 子节点

    public AreaItem() {
        super();
    }

    public boolean isRoot() {
        return new Long(0).equals(parentId);
    }

    public void addChild(AreaItem item) {
        if (children == null) {
            children = new ArrayList<AreaItem>();
        }
        children.add(item);
    }

    public List<AreaItem> getChildren() {
        return children;
    }

    public void setChildren(List<AreaItem> children) {
        this.children = children;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getParentAreaCode() {
        return parentAreaCode;
    }

    public void setParentAreaCode(String parentAreaCode) {
        this.parentAreaCode = parentAreaCode;
    }
}
