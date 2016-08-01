package net.jeeshop.web.controller.manage.system;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.jeeshop.biz.base.bean.PageBean;
import net.jeeshop.biz.base.bean.PageQueryBean;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.system.model.Express;
import net.jeeshop.biz.system.model.ExpressExample;
import net.jeeshop.biz.system.service.ExpressService;
import net.jeeshop.web.controller.manage.ManageBaseController;

/**
 *	配送方式管理Controller
 *
 * @author Leolione
 * @email leolione@outlook.com
 * @since V1.0
 */
@Controller
@RequestMapping("/manage/express")
public class ExpressController extends  ManageBaseController<Express, ExpressExample>{
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	@Autowired
	ExpressService expressService;
	
    @Override
    public BaseService<Express, ExpressExample> getService() {
        return expressService;
    }
    
    public ExpressController() {
        super.page_toList = "manage/system/express/expressList";
        super.page_toEdit = "manage/system/express/editExpress";
        super.page_toAdd = "manage/system/express/editExpress";
    }

    @RequestMapping("loadData")
    @ResponseBody
    public PageBean<Express> loadData(Express queryParams, PageQueryBean pageQueryBean) {
        ExpressExample example = new ExpressExample();
        ExpressExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(queryParams.getExpressCode())) {
            criteria.andExpressCodeEqualTo(queryParams.getExpressCode());
        }
        example.setOrderByClause("id desc");
        PageBean<Express> pager = expressService.selectPageList(example, pageQueryBean);
        return pager;
    }
    
    /**
     * 添加配送方式
     */
    @Override
    @RequestMapping("insert")
    public String insert(@ModelAttribute("e") Express express, RedirectAttributes flushAttrs){
        return super.insert(express, flushAttrs);
    }
    
    /**
     * 修改配送方式
     */
    @Override
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@ModelAttribute("e") Express express, RedirectAttributes flushAttrs) {
        return super.update(express, flushAttrs);
    }
    /**
     * ajax验证输入的字符的唯一性
     *
     * @return
     * @throws IOException
     */
    @RequestMapping("unique")
    @ResponseBody
    public String unique(@ModelAttribute("e") Express e, HttpServletResponse response) throws IOException {
        logger.debug("验证输入的字符的唯一性:" + e);
        response.setCharacterEncoding("utf-8");
        if (StringUtils.isNotBlank(e.getExpressCode())) {//验证快递编码是否存在
            logger.debug("验证快递编码是否存在:" + e.getExpressCode());
            Express express = expressService.selectByCode(e.getExpressCode());

            if (express == null) {
                //数据库中不存在此编码
                return "{\"ok\":\"该快递编码可以使用!\"}";
            } else {
                if (e.getId() != null && e.getId().equals(express.getId())) {
                    //update操作
                    return "{\"ok\":\"该快递编码可以使用!\"}";
                } else {
                    //insert操作
                    return "{\"error\":\"该快递编码已经存在!\"}";
                }
            }
        }
        return null;
    }
    
    /**
     * 删除配送方式
     */
    @Override
    @RequestMapping(value = "deletes", method = RequestMethod.POST)
    public String deletes(Long[] ids, RedirectAttributes flushAttrs) {
    	return super.deletes(ids, flushAttrs);
    }
}
