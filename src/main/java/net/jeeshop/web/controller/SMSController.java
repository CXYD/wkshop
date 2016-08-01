package net.jeeshop.web.controller;

import net.jeeshop.biz.system.service.SMSService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: zhaodh
 * Date: 16/6/20
 * Time: 13:55
 */

@Controller
@RequestMapping("sms")
public class SMSController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SMSService smsService;

    /**
     * 发送短信
     * @param phone
     * @param tpl
     * @param code1
     * @param code2
     * @param code3
     * @return
     */

    @RequestMapping("sentTpl")
    @ResponseBody
    public Object send(String phone,String tpl,String code1,String code2,String code3,HttpServletRequest request){
        request.getSession().setAttribute("randCheckCode", String.valueOf(code1));
        return  smsService.send(phone,tpl,code1,code2,code3);
    }


    @RequestMapping("sendCode")
    @ResponseBody
    public Object sendCode(String phone,HttpServletRequest request){
        //        phoneNum , templateid (43699下发验证码)， code1 （验证码）
        int code = (int) (Math.random()*9000+1000);
        if(code>9999){
            code = 9999;
        }
        logger.info("下发验证码："+code);

        request.getSession().setAttribute("randCheckCode", String.valueOf(code));

        return  smsService.send(phone,"43699", String.valueOf(code),"","");
    }

    /**
     * 校验短信验证码
     * @param randCode
     * @param request
     * @return
     */
    @RequestMapping("checkRandCode")
    @ResponseBody
    public boolean checkRandCode(String randCode , HttpServletRequest request){

        String sessionRandCode = (String) request.getSession().getAttribute("randCheckCode");

        logger.debug("发送验证码:" + sessionRandCode);
        logger.debug("接受的验证码：" + randCode);

        if(StringUtils.isEmpty(sessionRandCode)){
            logger.error("未获取到session中的验证码！");
            return false;
        }

        return randCode.equalsIgnoreCase(sessionRandCode);
    }

}
