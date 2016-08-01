package net.jeeshop.biz.finance;

import net.jeeshop.biz.finance.paygate.alipay.AlipayCore;
import net.jeeshop.biz.finance.paygate.alipay.AlipayParser;
import net.jeeshop.biz.finance.paygate.alipay.AlipayServiceName;
import net.jeeshop.biz.finance.paygate.alipay.AlipaySignMD5;
import net.jeeshop.biz.finance.paygate.alipay.model.QueryResultModel;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dylan
 * @date 16/3/10 21:52
 * Email: dinguangx@163.com
 */
public class AlipayTest {
    String partner = "XXXXXXXXX";
    String charset = AlipayCore.CHARSET_UTF8;
    String signKey = "XXXXXXXXXXXXXXXXXXXXXXX";
    String alipayUrl = "https://mapi.alipay.com/gateway.do?_input_charset=utf-8";

    /**
     * 请求支付宝
     */
    private String requestAlipay(Map<String, String> params) {
        if (!params.containsKey("partner")) {
            params.put("partner", partner);
        }
        if (!params.containsKey("_input_charset")) {
            params.put("_input_charset", AlipayCore.CHARSET_UTF8);
        }
        String linkStr = AlipayCore.createLinkString(params);
        String sign = AlipaySignMD5.sign(linkStr, signKey, AlipayCore.CHARSET_UTF8);
        params.put("sign", sign);
        params.put("sign_type", "MD5");
        System.out.println(String.format("请求支付宝参数:%s", params));
        return AlipayCore.reqAlipay(alipayUrl, params, AlipayCore.CHARSET_UTF8);
    }

    /**
     * 单笔交易查询接口
     */
    @Test
    public void testQuery() {
        Map<String, String> params = Maps.of(
                "service", AlipayServiceName.TT_QUERY.serviceName(),
                "partner", partner,
                "_input_charset", AlipayCore.CHARSET_UTF8,
                "out_trade_no", "201601031001002"
        );
        String response = requestAlipay(params);
        System.out.println(String.format("查询订单支付返回结果:%s", response));

        Map<String, String> responseData = new HashMap<String, String>();
        QueryResultModel model = AlipayParser.parseQueryResult(response);
        responseData.putAll(model.requestDatas());
        responseData.putAll(model.tradeDatas());
        responseData.put("is_success", model.is_success);
        responseData.put("sign", model.sign);
        responseData.put("sign_type", model.sign_type);
        System.out.println(AlipayCore.createLinkString(AlipayCore.paraFilter(model.tradeDatas())));
        boolean verify = AlipaySignMD5.verify(AlipayCore.createLinkString(AlipayCore.paraFilter(model.tradeDatas())), model.sign, signKey,
                AlipayCore.CHARSET_UTF8);
        if (verify) {// 验签成功
            System.out.println("验签成功");
        } else {
            System.out.println("验签失败");
        }
    }

    /**
     * 退款查询
     */
    @Test
    public void testQueryRefund() {
        Map<String, String> params = Maps.of(
                "service", AlipayServiceName.TT_REFUND_QUERY.serviceName(),
                "partner", partner,
                "_input_charset", charset,
                "trade_no", "20160325",
                "batch_no", "20140331057"
        );
        String response = requestAlipay(params);
        System.out.println("退款查询结果:" + response);
    }

    /**
     * 签约
     */
    @Test
    public void testSignPartner() throws Exception {
        Map<String, String> params = Maps.of(
                "service", "sign_protocol_with_partner",
                "partner", partner,
                "_input_charset", charset,
                "email", "dinguangx@163.com"
//			"sign_channel":"normal"//normal不显示自动支付按钮
        );

        String linkStr = AlipayCore.createLinkString(params);
        String sign = AlipaySignMD5.sign(linkStr, signKey, charset);
        params.put("sign", sign);
        params.put("sign_type", AlipayCore.SIGN_TYPE_MD5);

        StringBuilder url = new StringBuilder("https://mapi.alipay.com/gateway.do?");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            url.append(String.format("%s=%s", entry.getKey(), entry.getValue()));
        }
        System.out.println("签约URL:::" + url);
    }

}
