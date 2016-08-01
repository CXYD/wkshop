package net.jeeshop.web.controller.manage.product;

import net.jeeshop.biz.base.bean.PageBean;
import net.jeeshop.biz.base.bean.PageQueryBean;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.product.model.SpecInfo;
import net.jeeshop.biz.product.model.SpecInfoExample;
import net.jeeshop.biz.product.service.NormService;
import net.jeeshop.web.controller.manage.ManageBaseController;
import net.jeeshop.web.util.LoginUserHolder;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhaodh
 * Date: 16/5/31
 * Time: 16:05
 */
@Controller
@RequestMapping("manage/product/norm")
public class NormController  extends ManageBaseController{


    @Autowired
    private NormService normService;

    @Override
    public BaseService getService() {
        return normService;
    }


    @RequestMapping("toAddNorm")
    public String toAddNorm(){return "/manage/product/norm/toAddNorm";}


    /**
     * 初始化表格数据
     * @param queryParams
     * @param pageQueryBean
     * @return
     */
    @RequestMapping("loadData")
    @ResponseBody
    public PageBean<SpecInfo> loadData(SpecInfo queryParams, PageQueryBean pageQueryBean) {
        logger.debug("加载数据....");

        if (LoginUserHolder.getLoginUser() == null) {
            return null;
        }

        SpecInfoExample specInfoExample = new SpecInfoExample();
        String khid =  LoginUserHolder.getLoginUser().getKhid();
        specInfoExample.createCriteria().andKhidEqualTo(khid);
        specInfoExample.setOrderByClause("create_time desc");
//        specInfoExample.setOrderByClause(getOrderColumnData(request));
        PageBean pager = normService.selectPageList(specInfoExample, pageQueryBean);
        return pager;
    }

    @ResponseBody
    @RequestMapping("save")
    public String save(String id,String name,@RequestParam(value="value[]")String[] value){
        logger.debug("name={},value={}", name, value);
        logger.debug("规格id={}", id);
        String khid =  LoginUserHolder.getLoginUser().getKhid();
        long i = 0;
        SpecInfo specInfo = new SpecInfo();
        if(StringUtils.isBlank(id)){


            SpecInfoExample example = new SpecInfoExample();
            example.createCriteria().andNameEqualTo(name).andKhidEqualTo(khid);
            SpecInfo specInfoTemp = normService.selectUniqueByExample(example);

            if(specInfoTemp==null||StringUtils.isBlank(specInfoTemp.getName())){
                specInfo.setName(name);
                specInfo.setValue(Arrays.toString(value));
                specInfo.setKhid(khid);
                i = normService.insert(specInfo);
            }
        }else{
            specInfo.setValue(Arrays.toString(value));
            specInfo.setId(Long.valueOf(id));
            specInfo.setName(name);

           i = normService.update(specInfo);
        }


        if(i>0)
             return "success";
        else
            return "fail";
    }
    @RequestMapping("delByID")
    public @ResponseBody String delByID(long id){
       int i = normService.deleteById(id);
        if(i>0)
            return "success";
        else
            return "fail";
    }


    @RequestMapping("querySpecJson")
    @ResponseBody
    public List querySpecJson(){
        SpecInfoExample specInfoExample = new SpecInfoExample();
        String khid =  LoginUserHolder.getLoginUser().getKhid();
        specInfoExample.createCriteria().andKhidEqualTo(khid);

        List specList =  normService.selectByExample(specInfoExample);
        logger.debug("规格：{}",specList);
        return specList;
    }


    @RequestMapping("queryPackageSpecJson")
    @ResponseBody
    public List queryPackageSpecJson(){
        String khid =  LoginUserHolder.getLoginUser().getKhid();
        SpecInfoExample specInfoExample = new SpecInfoExample();
        SpecInfoExample.Criteria criteria = specInfoExample.createCriteria();
        criteria.andKhidEqualTo(khid);
        criteria.andNameIn(new ArrayList<String>(){{add("套餐资费");add("合约期限");}});
        List specList =  normService.selectByExample(specInfoExample);
        logger.debug("规格：{}",specList);
        return specList;
    }




    /**
     * ajax验证输入的字符的唯一性
     *
     * @return
     * @throws IOException
     */
    @RequestMapping("unique")
    @ResponseBody
    public String unique(@ModelAttribute("e") SpecInfo e, HttpServletResponse response) throws IOException {
        logger.debug("验证输入的字符的唯一性:" + e);
        response.setCharacterEncoding("utf-8");
        String khid =  LoginUserHolder.getLoginUser().getKhid();
        if (StringUtils.isNotBlank(e.getName())) {//验证昵称是否被占用
            logger.debug("验证昵称是否被占用:" + e.getName());
              SpecInfoExample example = new SpecInfoExample();
            example.createCriteria().andNameEqualTo(e.getName()).andKhidEqualTo(khid);
            SpecInfo specInfo = normService.selectUniqueByExample(example);

            if (specInfo == null) {
                //数据库中不存在此编码
                return "{\"ok\":\"规格名称可以使用!\"}";
            } else {
//                if (e.getId() != null && e.getId().equals(user.getId())) {
//                    //update操作，又是根据自己的编码来查询的，所以当然可以使用啦
//                    return "{\"ok\":\"标签可以使用!\"}";
//                } else {
//                    //当前为insert操作，但是编码已经存在，则只可能是别的记录的编码
                return "{\"error\":\"规格已经存在!\"}";
//                }
            }
        } else if (StringUtils.isNotBlank(e.getName())) {//验证用户名是否被占用
            logger.debug("验证规格是否被占用, name:" + e.getName());
            return "{\"ok\":\"规格可以使用!\"}";
        }
        return null;
    }
}
