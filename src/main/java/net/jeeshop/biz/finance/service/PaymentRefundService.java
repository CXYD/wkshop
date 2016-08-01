package net.jeeshop.biz.finance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 支付退款服务
 * @author dylan
 * @date 16/3/8 21:41
 * Email: dinguangx@163.com
 */
@Service
public class PaymentRefundService {
    @Autowired
    private PaymentService paymentService;
}
