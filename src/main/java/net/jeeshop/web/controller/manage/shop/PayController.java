package net.jeeshop.web.controller.manage.shop;

import net.jeeshop.biz.shop.model.PayAccount;
import net.jeeshop.biz.shop.service.PayAccountService;
import net.jeeshop.biz.shop.model.PaySet;
import net.jeeshop.biz.shop.service.PaySetService;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.system.model.SysUser;
import net.jeeshop.web.controller.manage.ManageBaseController;
import net.jeeshop.web.util.LoginUserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhaodh
 * Date: 16/5/30
 * Time: 17:45
 */
@Controller
@RequestMapping("manage/payManage")
public class PayController extends ManageBaseController {
    @Autowired
    PayAccountService payAccountService;
    @Autowired
    PaySetService paySetService;
    @Override
    public BaseService getService() {
        return null;
    }

    PayController(){
        super.page_toList = "";
        super.page_toEdit = "manage/shop/payManage/editPay";
        super.page_toAdd = "manage/shop/payManage/accountAdd";
    }

    @RequestMapping("accountAdd")
    public String accountAdd(Map<String, Object> map){
        SysUser loginUser = LoginUserHolder.getLoginUser();
        PayAccount payAccount=payAccountService.getAccount(loginUser.getKhid());
        if(payAccount==null){
            payAccount = new PayAccount("","","");
        }
        logger.debug("==payAcont==",payAccount);
        map.put("payAccount",payAccount);
        return page_toAdd;
    }
    @RequestMapping("edit")
    public String edit(Map<String, Object> map){
        SysUser loginUser = LoginUserHolder.getLoginUser();
        String khid=loginUser.getKhid();
        if (loginUser == null) {
            return "redirect:/manage/user/login";
        }
        if(khid==null||khid.equals("")){
            return "/manage/shop/shopInfo/addShop";
        }
        PaySet paySet = paySetService.getPaySet(new PaySet(khid));
        if(paySet==null){
            paySet = new PaySet(0,0,0);
        }
        map.put("paySet",paySet);
        return page_toEdit;
    }

    @RequestMapping("addAccount")
    public void addAccount(PayAccount payAccount){
        SysUser loginUser = LoginUserHolder.getLoginUser();
        String khid=loginUser.getKhid();
        payAccount.setKhid(khid);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        payAccount.setCreatedate(df.format(new Date()));
        if(payAccountService.getAccount(khid)!=null){
            payAccountService.upAccount(payAccount);
        }else {
            payAccountService.addAccount(payAccount);
        }
    }
    @RequestMapping("addSet")
    public void addSet(PaySet paySet){
        SysUser loginUser = LoginUserHolder.getLoginUser();
        paySet.setKhid(loginUser.getKhid());
        if(paySetService.getPaySet(paySet)!=null){
            paySetService.upPaySet(paySet);
        }else {
            paySetService.addPaySet(paySet);
        }
    }

}

