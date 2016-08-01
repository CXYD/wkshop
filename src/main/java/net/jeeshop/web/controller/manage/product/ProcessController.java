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
            String khid = LoginUserHolder.getLoginUser().getKhid();
            ProcessInfoExample example = new ProcessInfoExample();
            example.createCriteria().andNameEqualTo(name).andKhidEqualTo(khid);
            ProcessInfo processInfo = processInfoService.selectUniqueByExample(example);

            if (processInfo == null ) {
                //数据库中不存在此编码
                return "ok";
            } else {
                return "已被占用";
            }
        } else
            return "";
    }
}
