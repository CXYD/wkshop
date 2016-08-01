package net.jeeshop.biz.system.service;

import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.system.bean.AreaItem;
import net.jeeshop.biz.system.client.SysAreaMapper;
import net.jeeshop.biz.system.model.SysArea;
import net.jeeshop.biz.system.model.SysAreaExample;
import net.jeeshop.core.exception.JShopException;
import net.jeeshop.core.util.CollectionUtils;
import net.jeeshop.core.util.transform.Transform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 区域管理
 * @author alafighting
 */
@Service
public class AreaService extends BaseService<SysArea, SysAreaExample> {

    @Autowired
    private SysAreaMapper sysAreaMapper;

    @Override
    protected BaseMapper<SysArea, SysAreaExample> getMapper() {
        return sysAreaMapper;
    }

    private static class AreaItemTransform implements Transform<SysArea, AreaItem> {
        @Override
        public AreaItem convert(SysArea area) {
            AreaItem item = new AreaItem();
            item.setId(area.getId());
            item.setParentId(area.getParentId());
            item.setAreaName(area.getAreaName());
            item.setAreaCode(area.getAreaCode());
            item.setParentAreaCode(area.getParentAreaCode());
            return item;
        }

        @Override
        public boolean apply(SysArea sysArea) {
            return true;
        }
    }

    /**
     * 查询区域
     * @param areaCode
     * @return
     */
    public AreaItem queryAreaByCode(String areaCode) {
        SysAreaExample example = new SysAreaExample();
        SysAreaExample.Criteria criteria = example.createCriteria();
        criteria.andAreaCodeEqualTo(areaCode);
        SysArea area = super.selectUniqueByExample(example);
        if(area == null) {
            return null;
        }

        return new AreaItemTransform().convert(area);
    }
    /**
     * 查询所有区域集合
     * @return
     */
    public List<AreaItem> loadAllAreas() {
        return loadAreasByPid(0L, true);
    }

    /**
     * 根据父节点查询区域列表
     * @param pid 0-查询根节点
     * @return
     */
    public List<AreaItem> loadAreasByPid(long pid) {
        return loadAreasByPid(pid, true);
    }


    /**
     * 根据父节点查询区域列表
     * @param pid null-查询所有节点
     * @param recursionLoadChildren 是否递归加载子区域
     * @return
     */
    public List<AreaItem> loadAreasByPid(long pid, final boolean recursionLoadChildren) {
        // 拼装查询条件
        SysAreaExample query = new SysAreaExample();
        query.createCriteria().andParentIdEqualTo(pid);

        List<SysArea> areas = sysAreaMapper.selectByExample(query);

        List<AreaItem> areaItems = CollectionUtils.convert(areas, new Transform<SysArea, AreaItem>() {
            @Override
            public AreaItem convert(SysArea area) {
                AreaItem item = new AreaItem();
                item.setId(area.getId());
                item.setParentId(area.getParentId());
                item.setAreaName(area.getAreaName());
                item.setAreaCode(area.getAreaCode());
                item.setParentAreaCode(area.getParentAreaCode());
                if(recursionLoadChildren) {
                    item.setChildren(loadAreasByPid(area.getId(), recursionLoadChildren));
                }
                return item;
            }

            @Override
            public boolean apply(SysArea sysArea) {
                return true;
            }
        });
        return areaItems;
    }

    @Override
    public long insert(SysArea sysArea) {
        if (sysArea != null) {
            if (sysArea.getParentId() == null) {
                sysArea.setParentId(0L);
            }
        }
        return super.insert(sysArea);
    }

    @Override
    public int deleteById(long id) {
        List<AreaItem> areas = loadAreasByPid(id, false);
        if (areas != null) {
            throw new JShopException("当前区域有子区域，不能删除！");
        }
        logger.info("delete area, areaId:{}", id);
        return super.deleteById(id);
    }
}
