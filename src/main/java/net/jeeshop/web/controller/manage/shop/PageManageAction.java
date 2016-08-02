
package net.jeeshop.web.controller.manage.shop;

import com.alibaba.fastjson.JSONObject;
import net.jeeshop.biz.base.bean.PageBean;
import net.jeeshop.biz.base.bean.PageQueryBean;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.shop.model.CustomPage;
import net.jeeshop.biz.shop.model.CustomPageExample;
import net.jeeshop.biz.shop.service.PageManageService;
import net.jeeshop.web.controller.manage.ManageBaseController;
import net.jeeshop.web.util.LoginUserHolder;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

/** Created with IntelliJ IDEA.
 * User: zhaodh
 * Date: 16/5/30
 * Time: 17:41
 */
@Controller
@RequestMapping("/manage/pageManage")
public class PageManageAction extends ManageBaseController{

    @Autowired
    private PageManageService pageManageService;

    PageManageAction(){
        super.page_toAdd = "/manage/shop/pageManage/toTplManager";
//        super.page_toEdit = "/manage/shop/pageManage/toTplManager";
        super.page_toEdit = "/manage/shop/pageManage/newTplManage";
        super.page_toList = "/manage/shop/pageManage/pageList";
    }


    @RequestMapping("toAddPage")
    public String toAddPage(Model model){
//        model.addAttribute("pageid", System.currentTimeMillis() / 1000);

       return page_toAdd;
    }


    @RequestMapping("toAddPageNew")
    public String toAddPageNew(Model model){

        if (LoginUserHolder.getLoginUser() == null) {
            return "redirect:/manage/user/login";
        }
        model.addAttribute("pageid", System.currentTimeMillis() / 1000);
        return "/manage/shop/pageManage/newTplManage";
    }

    /**
     * 跳转到编辑页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "toEditPage",method = RequestMethod.GET)
    public ModelAndView toEditPage(long id,ModelAndView model){

         CustomPage  customPage = pageManageService.selectById(id);
        model.setViewName(page_toEdit);
        model.addObject("id", id);
        logger.debug("编辑；id={},content={}", id, customPage);

        return model;
    }

    /**
     * 根据id查询页面配置
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("queryPage")
    @ResponseBody
    public Object queryPage(long id,Model model){
        CustomPage  customPage = pageManageService.selectById(id);
        return customPage;
    }

    /**
     * 微店模板管理
     * @return
     */
    @RequestMapping("edit")
    public String toTplManager(){
        return page_toEdit;
    }

    /**
     * 插入新模块
     * @return
     */
    @RequestMapping("NewModel")
    public String toNewModel(){
        return "/manage/shop/pageManage/toNewModel";
    }

    /**
     * 插入图片导航
     * @return
     */
    @RequestMapping("PicNav")
    public String toPicNav(){
        return "/manage/shop/pageManage/toPicNav";
    }

    /**
     * 插入文本导航
     * @return
     */
    @RequestMapping("TextNav")
    public String toTextNav(){
        return "/manage/shop/pageManage/toTextNav";
    }

    /**
     * 插入广告
     * @param idx   图片编号，取值对应程序内的map
     * @param model
     * @return
     */
    @RequestMapping("Adv/{idx}")
    public String toAdv(@PathVariable("idx") String idx,Model model){
        HashMap<String,String> map = new HashMap<String, String>();
        map.put("21","dpzxsyt-21,建议尺寸640 x 高度不限");
        map.put("31","dpzxsyt-31,建议尺寸640 x 高度不限，且多图尺寸需相同");
        String tmp = map.get(idx);
        String picname = tmp.split(",")[0];
        String desc = tmp.split(",")[1];
        model.addAttribute("picname",picname);
        model.addAttribute("desc",desc);

        return "/manage/shop/pageManage/toAdv";
    }

    /**
     * 插入两列广告
     * @return
     */
    @RequestMapping("TwoColAdv")
    public String toTwoColAdv(){
        return "/manage/shop/pageManage/toTwoColAdv";
    }

    /**
     * 插入商品列表
     * @return
     */
    @RequestMapping("ProductList")
    public String toProductList(){
        return "/manage/shop/pageManage/toProductList";
    }

    /**
     * 插入公告
     * @return
     */
    @RequestMapping("Notice")
    public String toNotice(){
        return "/manage/shop/pageManage/toNotice";
    }


    /**
     * 预览
     * @param pageContent
     * @param modelAndView
     * @return
     */
    @RequestMapping("previewPage")
    public ModelAndView previewPage(String pageContent, ModelAndView modelAndView){
        logger.debug("进入预览：{}",pageContent);
        modelAndView.setViewName("/manage/shop/pageManage/previewPage");
        modelAndView.addObject("pageContent", pageContent);
        return modelAndView;
    }

    /**
     * 根据id进行预览
     * @param id
     * @param modelAndView
     * @return
     */
    @RequestMapping("pview/{id}")
    public ModelAndView pviewPage(@PathVariable long id,ModelAndView modelAndView){
        logger.debug("根据id进行预览");

        modelAndView.setViewName("/manage/shop/pageManage/previewPage");
        modelAndView.addObject("pageid", id);
        return modelAndView;
    }

    /**
     * 根据id查询模板
     * @param id
     * @return
     */
    @RequestMapping("queryTpl")
    @ResponseBody
    public Object queryTplById(long id){
        logger.debug("更加id查询自定义页面{}", id);
        CustomPage page = pageManageService.selectById(id);
        return page;
    }

    @RequestMapping("tpl")
    public String getTpl(){
        return "/manage/shop/pageManage/tpl";
    }


    /**
     * 加载右侧模板
     * @param type
     * @return
     */
    @RequestMapping("loadRightModule")
    public Object loadRightModule(String type){
        logger.info("加载右侧页面type={}",type);
        if(StringUtils.isEmpty(type)){
            return  "/manage/shop/pageManage/setPage";
        }
        return  "/manage/shop/pageManage/module_"+type;
    }


    /**
     * 加载左侧页面模板
     * @param type
     * @return
     */
    @RequestMapping("loadLeftModule")
    public Object loadLeftModule(String type){
        logger.info("加载右侧页面type={}", type);
        if(StringUtils.isEmpty(type)){
            return  "";
        }
        return  "/manage/shop/pageManage/e"+type;
    }


    /**
     * 保存
     * @param page
     * @return
     */
    @RequestMapping("savePage")
    @ResponseBody
    public long savePage(CustomPage page){

        String khid = LoginUserHolder.getLoginUser().getKhid();
        page.setKhid(khid);
        JSONObject pageInfo = JSONObject.parseObject(page.getPagecontent());
        page.setTitle((String) pageInfo.get("title"));
        page.setDsp((String) pageInfo.get("desc"));



        logger.debug("自定义页面;{}", page);
        logger.debug("id={}", pageInfo.get("id"));
        long i = 0;

        if(pageInfo.get("id")==null || "".equals(pageInfo.get("id"))) {
            logger.info("保存一个页面");
            i =   pageManageService.insert(page);
        }else{
            logger.info("修改一个页面");
            page.setId(Long.valueOf((String) pageInfo.get("id")));
            i=  pageManageService.update(page);
        }
        logger.debug("是否保存成功{}", i > 0);
        return page.getId();
    }


    /**
     * 初始化表格数据
     * @param customPage
     * @param pageQueryBean
     * @return
     */
    @RequestMapping("loadData")
    @ResponseBody
    public PageBean<CustomPage> loadData(CustomPage customPage, PageQueryBean pageQueryBean) {
        logger.debug("加载数据....");
        if (LoginUserHolder.getLoginUser() == null) {
            return null;
        }
        CustomPageExample customPageExample = new CustomPageExample();
        String khid =  LoginUserHolder.getLoginUser().getKhid();
//        customPageExample.createCriteria().andKhdidEqualTo(khid);
        customPageExample.setOrderByClause(getOrderColumnData(request));
        PageBean pager = pageManageService.selectPageList(customPageExample, pageQueryBean);
        return pager;
    }


    @RequestMapping("delByID")
    @ResponseBody
    public Object delByID(long id){
        int i= pageManageService.deleteById(id);
        if(i>0){
            return "success";
        }else{
            return "error";
        }
    }

    /**
     * 设置为首页
     * @param customPage
     * @return
     */
    @RequestMapping("setHomePage")
    @ResponseBody
    public Object setHome(CustomPage customPage){
        String khid = LoginUserHolder.getLoginUser().getKhid();

        pageManageService.cancelPageHome(khid);

        customPage.setKhid(khid);
        customPage.setIsindex("1");
        int i =  pageManageService.update(customPage);
        if(i>0){
            return "success";
        }else{
            return "error";
        }
    }

    @Override
    public BaseService getService() {
        return pageManageService;
    }
}
