package net.jeeshop.web.controller.manage.product;

import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.product.model.ProcessInfo;
import net.jeeshop.biz.product.model.ProcessInfoExample;
import net.jeeshop.biz.product.service.ProcessInfoService;
import net.jeeshop.web.controller.manage.ManageBaseController;
import net.jeeshop.web.util.LoginUserHolder;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhaodh
 * Date: 16/6/12
 * Time: 15:03
 */
@Controller
@RequestMapping("manage/product/process")
public class ProcessController extends ManageBaseController {

    @Autowired
    private ProcessInfoService processInfoService;

    @Override
    public BaseService getService() {
        return processInfoService;
    }


    @RequestMapping("queryProcessJson")
    @ResponseBody
    public List queryProcessJson(){
        String khid =  LoginUserHolder.getLoginUser().getKhid();
        ProcessInfoExample example = new ProcessInfoExample();
        example.createCriteria().andKhidEqualTo(khid);
        List specList =  processInfoService.selectByExample(example);
        logger.debug("流程：{}",specList);
        return specList;
    }


    @RequestMapping("save")
    @ResponseBody
    public String save(ProcessInfo info){
        logger.debug("流程信息：={}", info);
        String khid =  LoginUserHolder.getLoginUser().getKhid();
        info.setKhid(khid);
        long i = processInfoService.insert(info);

        if(i>0) {
            return "success";
        }else{
            return "fail";
        }
    }



    /**
     * ajax验证输入的字符的唯一性
     *
     * @return
     * @throws IOException
     */
    @RequestMapping("unique")
    @ResponseBody
    public String unique( String name){
        logger.debug("验证输入的字符的唯一性:" + name);

        if (StringUtils.isNotBlank(name)) {//验证昵称是否被占用
            logger.debug("验证是否被占用:" + name);
            String khid =  LoginUserHolder.getLoginUser().getKhid();
            ProcessInfoExample example = new ProcessInfoExample();
            example.createCriteria().andNameEqualTo(name).andKhidEqualTo(khid);
            ProcessInfo processInfo = processInfoService.selectUniqueByExample(example);

            if (processInfo == null) {
                //数据库中不存在此编码
                return "ok";
            } else {
//                if (e.getId() != null && e.getId().equals(user.getId())) {
//                    //update操作，又是根据自己的编码来查询的，所以当然可以使用啦
//                    return "{\"ok\":\"标签可以使用!\"}";
//                } else {
//                    //当前为insert操作，但是编码已经存在，则只可能是别的记录的编码
                return "error";
//                }
            }
        } else if (StringUtils.isNotBlank(name)) {//验证用户名是否被占用
            logger.debug("验证流程是否被占用, name:" + name);
            return "ok";
        }
        return null;
    }
}
