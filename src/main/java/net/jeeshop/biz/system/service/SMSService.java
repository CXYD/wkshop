package net.jeeshop.biz.system.service;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.base.service.BaseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhaodh
 * Date: 16/6/20
 * Time: 13:40
 */
@Service
public class SMSService extends BaseService{



    private static final String SMS_URL = "app.cloopen.com";
    private static final String SMS_PORT = "8883";
    private static final String AppId = "aaf98f89505b6c0801506099ab10054e";
    private static final String Account_key = "8a48b5515018a0f401504b8607685b09";
    private static final String Account_value = "d675d513012841d9b619899de6aa082b";


    @Override
    protected BaseMapper getMapper() {
        return null;
    }


    /**
     * 发送短信
     * @param phone 手机号
     * @param tpl 模板id
     * @param code1 内容1
     * @param code2 内容2
     * @param code3 内容3
     * @return
     */
    public Object send(String phone,String tpl,String code1,String code2,String code3){

        Map<String,Object> result = new HashMap();

        logger.info("发送短信：phone={}，tpl={}",phone,tpl);
        logger.info("发送内容：1={},2={},3={}",code1,code2);

        if(StringUtils.isBlank(phone)){
            result.put("msg","手机号不能为空");
            result.put("state","error");
            logger.error("手机为空");
        }

        if(StringUtils.isBlank(tpl)){
            result.put("msg","模板不能为空");
            result.put("state","error");
            logger.error("模板为空");
        }


        //荣联SDK
        CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
        restAPI.init(SMS_URL, SMS_PORT);
        restAPI.setAccount(Account_key, Account_value);
        restAPI.setAppId(AppId);

        result = restAPI.sendTemplateSMS(phone,tpl ,new String[]{code1,code2,code3});

        logger.info("调用短信返回结果;{}",result);

        return result;
    }
}
