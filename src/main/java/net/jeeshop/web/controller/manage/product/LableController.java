package net.jeeshop.web.controller.manage.product;

import net.jeeshop.biz.base.bean.PageBean;
import net.jeeshop.biz.base.bean.PageQueryBean;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.product.model.ProductLabel;
import net.jeeshop.biz.product.model.ProductLabelExample;
import net.jeeshop.biz.product.service.ProductLabelService;
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
@RequestMapping("manage/product/lable")
public class LableController extends ManageBaseController<ProductLabel,ProductLabelExample> {

    @Autowired
    private ProductLabelService productLabelService;

    @Override
    public BaseService getService() {
        return productLabelService;
    }


    LableController(){
        super.page_toList = "/manage/product/lable/toProductLable";
    }


    /**
     * 初始化表格数据
     * @param queryParams
     * @param pageQueryBean
     * @return
     */
    @RequestMapping("loadData")
    @ResponseBody
    public PageBean<ProductLabel> loadData(ProductLabel queryParams, PageQueryBean pageQueryBean) {
        logger.debug("加载数据....");

        if (LoginUserHolder.getLoginUser() == null) {
            return null;
        }

        ProductLabelExample lableExample = new ProductLabelExample();
        String khid =  LoginUserHolder.getLoginUser().getKhid();
        lableExample.createCriteria().andKhdidEqualTo(khid);
//        lableExample.setOrderByClause(getOrderColumnData(request));
        lableExample.setOrderByClause("createtime desc");
        PageBean pager = productLabelService.selectPageList(lableExample, pageQueryBean);
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
        logger.debug("删除id={}",id);
       int rows =  productLabelService.deleteByPrimaryKey(id);
        if(rows>0){
            logger.debug("成功");
            return "success";
        }else{
            logger.debug("失败");
            return "fail";
        }
    }

    /**
     * 根据id更新状态
     * @return
     */
    @RequestMapping("updateStatus")
    @ResponseBody
    public String updateStatus(long id, String status){

        if(id>0){

            ProductLabel lable = productLabelService.selectById(id);
            lable.setStatus(status);
            int rows = productLabelService.update(lable);
            if(rows>0){
                return "success";
            }else{
                return "fail";
            }
        }
        return "fail";

    }

    @RequestMapping("queryLabelJson")
    @ResponseBody
    public List queryClassJson(){
        ProductLabelExample lableExample = new ProductLabelExample();
        String khid =  LoginUserHolder.getLoginUser().getKhid();
        lableExample.createCriteria().andKhdidEqualTo(khid).andStatusEqualTo("1");
        List labelList =  productLabelService.selectByExample(lableExample);
        logger.debug("标签：{}",labelList);
        return labelList;
    }


    /**
     * 添加标签
     */

    @RequestMapping("insert")
    public String insert(@ModelAttribute("e") ProductLabel lable, RedirectAttributes flushAttrs) {
        logger.debug("插入标签{}",lable);
        return save0(lable, flushAttrs);
    }


    private String save0(ProductLabel e, RedirectAttributes flushAttrs) {
        logger.info("save productLable..." + e.getId() + "," + e.getName());
        String khid =  LoginUserHolder.getLoginUser().getKhid();
        if (e.getId() == null) {//添加
             e.setStatus("1"); // 启用
            e.setCreatetime(new Date());
            e.setKhdid(khid);
            productLabelService.insert(e);

        } else {//修改

        }
        flushAttrs.addFlashAttribute("message", "操作成功!");
        return "ok";
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
        response.setCharacterEncoding("utf-8");
        String khid =  LoginUserHolder.getLoginUser().getKhid();
        if (StringUtils.isNotBlank(e.getName())) {//验证昵称是否被占用
            logger.debug("验证昵称是否被占用:" + e.getName());
            ProductLabelExample example = new ProductLabelExample();
            example.createCriteria().andNameEqualTo(e.getName()).andKhdidEqualTo(khid);
            ProductLabel productLable = productLabelService.selectUniqueByExample(example);

            if (productLable == null) {
            //数据库中不存在此编码
            return "{\"ok\":\"标签名称可以使用!\"}";
            } else {
//                if (e.getId() != null && e.getId().equals(user.getId())) {
//                    //update操作，又是根据自己的编码来查询的，所以当然可以使用啦
//                    return "{\"ok\":\"标签可以使用!\"}";
//                } else {
//                    //当前为insert操作，但是编码已经存在，则只可能是别的记录的编码
                    return "{\"error\":\"标签已经存在!\"}";
//                }
            }
        } else if (StringUtils.isNotBlank(e.getName())) {//验证用户名是否被占用
            logger.debug("验证标签是否被占用, name:" + e.getName());
            return "{\"ok\":\"标签可以使用!\"}";
        }
        return null;
    }
}
