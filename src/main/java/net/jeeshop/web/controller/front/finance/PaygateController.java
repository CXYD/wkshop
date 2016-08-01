package net.jeeshop.web.controller.front.finance;

import net.jeeshop.biz.finance.paygate.PaygateService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dylan
 * @date 16/3/13 16:25
 * Email: dinguangx@163.com
 */
@Controller
@RequestMapping("finance/paygate")
public class PaygateController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private PaygateService paygateService;
    /**
     * 支付结果通知
     * @param bgFlag
     * @param paygateType
     */
    @RequestMapping("payNotify/{bgFlag}/{type}")
    public String payNotify(@PathVariable("bgFlag") String bgFlag,
                          @PathVariable("type") String paygateType,
                          HttpServletRequest request,
                          HttpServletResponse response,
                            ModelMap model) {
        logger.info("payNotify, bgFlag: {}, paygateType:{}", bgFlag, paygateType);
        boolean bgNotify = false;
        if("1".equals(bgFlag)){
            bgNotify = true;
        }
        try {
            request.setCharacterEncoding("UTF-8");
            // 返回结果
            Map<String, String> formData = extractResponseData(bgNotify, request);
            logger.info("返回的支付结果: {}", formData);
            return null;
            // 请求处理银行支付结果
        } catch (Throwable thr) {
            logger.error("", thr);
            //TODO 异常处理
            return null;
        }
    }
    protected Map<String, String> extractResponseData(boolean bgNotify, HttpServletRequest request) {
        Map<String, String> formData = new HashMap<String, String>();
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String name = paramNames.nextElement();
            formData.put(name, request.getParameter(name));
        }
        return formData;
    }
}
