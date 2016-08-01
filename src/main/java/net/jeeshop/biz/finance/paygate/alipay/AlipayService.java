package net.jeeshop.biz.finance.paygate.alipay;

import net.jeeshop.biz.finance.paygate.bean.PayRequestBean;
import net.jeeshop.biz.finance.paygate.bean.PaygateResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dylan
 * @date 16/3/13 12:13
 * Email: dinguangx@163.com
 */
@Component
public class AlipayService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    AlipayProperties alipayProperties;
    public PaygateResponse createPayURL(PayRequestBean payRequest){
        String partner = alipayProperties.getPartner();
        // 支付接口地址
        String paymentURL = alipayProperties.getRequestUrl();

        PaygateResponse response = new PaygateResponse();
        Map<String, String> paramsEncode = new HashMap<String, String>();
        response.setExtraInfos(paramsEncode);

        String inputCharset = AlipayCore.CHARSET_UTF8;
        try {
            paramsEncode.put("_input_charset", inputCharset);
            // 银行后台通知url(回调地址)
            paramsEncode.put("notify_url", payRequest.getBgNotifyUrl());
            // 外部交易号（流水号）
            paramsEncode.put("out_trade_no", payRequest.getOrderNum());
            // 合作伙伴
            paramsEncode.put("partner", partner);
            // 支付类型,强制为"1"
            paramsEncode.put("payment_type", "1");
            // 银行前台通知url
            paramsEncode.put("return_url", payRequest.getFgNotifyUrl());

            // 卖家Email
            paramsEncode.put("seller_email", alipayProperties.getSellerEmail());

            // 接口名称
            paramsEncode.put("service", AlipayServiceName.TT_SINGLE_PAYMENT.serviceName());
            // 商品名称
            paramsEncode.put("subject", payRequest.getComment());
            // 金额
            paramsEncode.put("total_fee", String.valueOf(payRequest.getAmount()));//TODO 两位小数
            //银行信息
            if (StringUtils.isNotBlank(payRequest.getBankCode())) {
                paramsEncode.put("paymethod", "bankPay");
                paramsEncode.put("defaultbank", payRequest.getBankCode());
            }
            // 签名
            StringBuilder url = new StringBuilder(AlipayCore.createLinkStringWithEncode(paramsEncode, inputCharset));
            String encodeParams = AlipayCore.createLinkString(paramsEncode);
            String sign = AlipaySignMD5.sign(encodeParams, alipayProperties.getSignKey(), inputCharset);
            url.append("&sign=").append(sign);
            // 签名方式
            url.append("&sign_type=").append(alipayProperties.getSignType());

            response.setPayUrl(StringUtils.trim(paymentURL + "?" + url.toString()));
            logger.info("bankPayUrl : {}", response.getPayUrl());
        } catch (Throwable thr) {
            logger.error("", thr);
        }
        return response;
    }
}
