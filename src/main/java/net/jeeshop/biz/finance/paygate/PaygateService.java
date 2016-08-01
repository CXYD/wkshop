package net.jeeshop.biz.finance.paygate;

import net.jeeshop.biz.finance.paygate.alipay.AlipayService;
import net.jeeshop.biz.finance.paygate.bean.PayRequestBean;
import net.jeeshop.biz.finance.paygate.bean.PayResponseBean;
import net.jeeshop.biz.finance.paygate.bean.PaygateResponse;
import net.jeeshop.web.bean.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dylan
 * @date 16/3/13 12:17
 * Email: dinguangx@163.com
 */
@Component
public class PaygateService {

    @Autowired
    AlipayService alipayService;

    /**
     * 请求支付网关进行支付
     *
     * @param payRequest
     * @return
     */
    public ResultBean<PayResponseBean> requestPay(PayRequestBean payRequest) {
        //TODO 记录请求日志
        //TODO 细分处理结果
        ResultBean<PayResponseBean> resultBean = new ResultBean<PayResponseBean>();
        PayResponseBean payResponseBean = new PayResponseBean();
        resultBean.setData(payResponseBean);
        payResponseBean.setOrderNum(payRequest.getOrderNum());
        PaygateType paygateType = payRequest.getPaygateType();

        PaygateResponse paygateResponse = null;
        if (paygateType == PaygateType.ALIPAY) {
            paygateResponse = alipayService.createPayURL(payRequest);
        } else {
            throw new IllegalArgumentException();//TODO 其他类型不支持
        }
        payResponseBean.setPayUrl(paygateResponse.getPayUrl());
        return resultBean;
    }
}
