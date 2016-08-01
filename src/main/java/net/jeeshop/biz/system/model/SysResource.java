package net.jeeshop.biz.system.model;

import java.io.Serializable;
import net.jeeshop.biz.base.model.BaseModel;
import net.jeeshop.biz.system.enums.ResourceType;

public class SysResource extends BaseModel implements Serializable {
    /** èµ„æº�å��ç§° */
    private String resourceName;

    /** èµ„æº�ç±»åž‹ */
    private ResourceType resourceType;

    /** èµ„æº�å€¼ */
    private String resourceValue;

    /** é¡ºåº� */
    private Integer ordinal;

    /** æ˜¯å�¦æœ‰æ•ˆ,1-æ˜¯0-å�¦ */
    private Boolean isValid;

    /** çˆ¶çº§ID */
    private Long parentId;

    private static final long serialVersionUID = 1L;

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName == null ? null : resourceName.trim();
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceValue() {
        return resourceValue;
    }

    public void setResourceValue(String resourceValue) {
        this.resourceValue = resourceValue == null ? null : resourceValue.trim();
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}