package net.jeeshop.web.controller.manage.product;

import net.jeeshop.biz.base.bean.PageBean;
import net.jeeshop.biz.base.bean.PageQueryBean;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.product.bean.ProductClassBean;
import net.jeeshop.biz.product.model.ProductClass;
import net.jeeshop.biz.product.model.ProductClassExample;
import net.jeeshop.biz.product.model.ProductLabel;
import net.jeeshop.biz.product.service.ProductClassService;
import net.jeeshop.web.controller.manage.ManageBaseController;
import net.jeeshop.web.util.LoginUserHolder;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhaodh
 * Date: 16/5/31
 * Time: 16:04
 */
@Controller
@RequestMapping("manage/product/class")
public class ClassController extends ManageBaseController<ProductClass,ProductClassExample> {

    @Autowired
    private ProductClassService productClassService;

    @Override
    public BaseService getService() {
        return productClassService;
    }

    ClassController(){
        super.page_toList = "/manage/product/class/toProductClass";
    }



    /**
     * 初始化表格数据
     * @param queryParams
     * @param pageQueryBean
     * @return
     */
    @RequestMapping("loadData")
    @ResponseBody
    public PageBean<ProductClassBean> loadData(ProductClassBean queryParams, PageQueryBean pageQueryBean) {
        logger.debug("加载数据....");
        ProductClassExample productClassExample = new ProductClassExample();
        if (LoginUserHolder.getLoginUser() == null) {
            return null;
        }
        String khid =  LoginUserHolder.getLoginUser().getKhid();
        queryParams.setKhid(khid);
        productClassExample.createCriteria().andKhidEqualTo(khid);
//        productClassExample.setOrderByClause(getOrderColumnData(request));
        productClassExample.setOrderByClause("createtime desc");
//        PageBean pager = productClassService.selectPageList(productClassExample, pageQueryBean);

        PageBean pager = productClassService.selectPageBean(productClassExample,pageQueryBean);

        return pager;
    }


    /**
     * 根据id删除
     * @param id
     * @return
     */
    @RequestMapping("delByID")
    @ResponseBody
    public String delByID(Long id){
        int rows =  productClassService.deleteById(id);
        if(rows>0){
            logger.debug("成功");
            return "success";
        }else{
            logger.debug("失败");
            return "fail";
        }
    }



    /**
     * 添加
     */

    @RequestMapping("insert")
    public String insert(@ModelAttribute("e") ProductClass productClass, RedirectAttributes flushAttrs) {
        logger.debug("插入类别{}", productClass);
        return save0(productClass, flushAttrs);
    }

    /**
     * 保存
     * @param e
     * @param flushAttrs
     * @return
     */
    private String save0(ProductClass e, RedirectAttributes flushAttrs) {
        logger.info("save productClass..." + e.getId() + "," + e.getName());
        String khid =  LoginUserHolder.getLoginUser().getKhid();
        if (e.getId() == null) {//添加
            e.setStatus("1"); // 启用
            e.setCreatetime(new Date());
            e.setKhid(khid);
            productClassService.insert(e);

        } else {//修改

        }
        flushAttrs.addFlashAttribute("message", "操作成功!");
        return "redirect:back";
    }


    @RequestMapping("queryClassJson")
    @ResponseBody
    public List queryClassJson(){
        ProductClassExample productClassExample = new ProductClassExample();
        String khid =  LoginUserHolder.getLoginUser().getKhid();
        productClassExample.createCriteria().andKhidEqualTo(khid).andStatusEqualTo("1");
       List classList =  productClassService.selectByExample(productClassExample);
        logger.debug("分类：{}",classList);
        return classList;
    }


    /**
     * ajax验证输入的字符的唯一性
     *
     * @return
     * @throws IOException
     */
    @RequestMapping("unique")
    @ResponseBody
    public String unique(@ModelAttribute("e") ProductLabel e, HttpServletResponse response) throws IOException {
        logger.debug("验证输入的字符的唯一性:" + e);
        String khid =  LoginUserHolder.getLoginUser().getKhid();
        response.setCharacterEncoding("utf-8");
        if (StringUtils.isNotBlank(e.getName())) {//验证昵称是否被占用
            logger.debug("验证昵称是否被占用:" + e.getName());
            ProductClassExample example = new ProductClassExample();
            example.createCriteria().andNameEqualTo(e.getName()).andKhidEqualTo(khid);
            ProductClass productClass = productClassService.selectUniqueByExample(example);

            if (productClass == null) {
                //数据库中不存在此编码
                return "{\"ok\":\"类别名称可以使用!\"}";
            } else {
//                if (e.getId() != null && e.getId().equals(user.getId())) {
//                    //update操作，又是根据自己的编码来查询的，所以当然可以使用啦
//                    return "{\"ok\":\"标签可以使用!\"}";
//                } else {
//                    //当前为insert操作，但是编码已经存在，则只可能是别的记录的编码
                return "{\"error\":\"类别已经存在!\"}";
//                }
            }
        } else if (StringUtils.isNotBlank(e.getName())) {//验证用户名是否被占用
            logger.debug("验证类别是否被占用, name:" + e.getName());
            return "{\"ok\":\"类别可以使用!\"}";
        }
        return null;
    }
}
