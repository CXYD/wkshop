package net.jeeshop.web.controller.common;

import com.google.common.collect.Lists;
import net.jeeshop.biz.system.bean.AreaItem;
import net.jeeshop.biz.system.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author dylan
 * @date 16/4/9 11:32
 * Email: dinguangx@163.com
 */
@Controller("common.areaController")
@RequestMapping("area")
public class AreaController {

    @Autowired
    AreaService areaService;
    /**
     *
     * @return
     */
    @RequestMapping("loadAreasByParentId")
    @ResponseBody
    public List<AreaItem> loadAreasByParentId(Long parentId) {
        return areaService.loadAreasByPid(parentId, false);
    }

    @RequestMapping("loadAreasByParentCode")
    @ResponseBody
    public List<AreaItem> loadAreasByParentCode(String parentCode) {
        AreaItem item = areaService.queryAreaByCode(parentCode);
        if(item == null) {
            return Lists.newArrayList();
        }
        return loadAreasByParentId(item.getId());
    }

}
