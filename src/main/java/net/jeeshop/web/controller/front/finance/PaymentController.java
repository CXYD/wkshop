package net.jeeshop.web.controller.front.finance;

import com.google.gson.Gson;
import net.jeeshop.biz.finance.bean.PaymentBean;
import net.jeeshop.biz.finance.bean.PaymentResultBean;
import net.jeeshop.biz.finance.enums.PaymentResult;
import net.jeeshop.biz.finance.model.Payment;
import net.jeeshop.biz.finance.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author dylan
 * @date 16/3/2 22:02
 * Email: dinguangx@163.com
 */
@Controller
@RequestMapping("payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    /**
     * 请求支付
     * @param message
     * @param sign
     * @return
     */
    @RequestMapping("requestPay")
    public String requestPay(String message, String sign) {
        //TODO 1. 参数验签
        PaymentBean paymentBean = new Gson().fromJson(message, PaymentBean.class);
        //TODO 2. 参数校验
        Payment payment = paymentService.savePaymentRequest(paymentBean);
        PaymentResultBean paymentResult = paymentService.payPayment(payment.getId());
        PaymentResult result = paymentResult.getPaymentResult();
        if(result == PaymentResult.SUCCESS) {
            //支付成功
        } else if(result == PaymentResult.WAIT_PAY){
            //等待用户支付，跳转到支付页面
        } else {
            //支付失败
        }
        return "finance/payResult";
    }

}
