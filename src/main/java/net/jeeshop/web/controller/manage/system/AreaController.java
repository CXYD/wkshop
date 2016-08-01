package net.jeeshop.web.controller.manage.system;

import com.google.gson.Gson;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.system.bean.AreaItem;
import net.jeeshop.biz.system.model.SysArea;
import net.jeeshop.biz.system.model.SysAreaExample;
import net.jeeshop.biz.system.service.AreaService;
import net.jeeshop.web.controller.manage.ManageBaseController;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * 区域管理
 * @author alafighting
 */
@Controller
@RequestMapping("/manage/area")
public class AreaController extends ManageBaseController<SysArea, SysAreaExample> {

    private static final String page_areaTree = "/manage/system/area/areaTree";
    private static final String page_toEdit = "/manage/system/area/edit";

    public AreaController() 
    {
        super.page_toEdit = page_toEdit;
        super.page_toList = page_areaTree;
        super.page_toAdd = page_toEdit;
    }

    @Autowired
    private AreaService areaService;

    @Override
    public BaseService<SysArea, SysAreaExample> getService() {
        return areaService;
    }

    @RequestMapping("areaTree")
    public String list(ModelMap model) {
        beforeToList(model);
        return page_areaTree;
    }

    protected void beforeToList(ModelMap model) {
        Collection<AreaItem> areas = areaService.loadAreasByPid(0);
        model.put("provinces", areas);
    }

    protected void beforeToEdit(SysArea area, ModelMap model) {
        beforeToAdd(area, model);
    }

    @Override
    protected void beforeToAdd(SysArea area, ModelMap model) {
        long pidValue = 0;
        if(area != null && area.getParentId() != null) {
            pidValue = area.getParentId();
        }

        if (pidValue != 0) {
            area = areaService.selectById(pidValue);
            model.put("pid", area.getParentId());
            model.put("pname", area.getAreaName());
        }
    }

    @RequestMapping("loadAreasByPid")
    @ResponseBody
    public String loadAreasByPid(@RequestParam(required = false, defaultValue = "0") String pid) {
        long pidValue = 0;
        try {
            pidValue = Long.parseLong(pid);
            if (pidValue < 0) {
                pidValue = 0;
            }
        } catch (Exception e) {
        }

        Collection<AreaItem> areas = areaService.loadAreasByPid(pidValue);
        return writer(areas);
    }

    //输出菜单到页面
    private String writer(Collection list) 
    {
        return new Gson().toJson(list);
    }

}
