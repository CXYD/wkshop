package net.jeeshop.biz.finance.service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import net.jeeshop.biz.finance.bean.PaymentBean;
import net.jeeshop.biz.finance.bean.PaymentItemBean;
import net.jeeshop.biz.finance.bean.PaymentResultBean;
import net.jeeshop.biz.finance.client.PaymentItemMapper;
import net.jeeshop.biz.finance.client.PaymentMapper;
import net.jeeshop.biz.finance.enums.PaymentResult;
import net.jeeshop.biz.finance.enums.PaymentStatus;
import net.jeeshop.biz.finance.enums.PaymentTypeCode;
import net.jeeshop.biz.finance.enums.RefundStatus;
import net.jeeshop.biz.finance.model.Payment;
import net.jeeshop.biz.finance.model.PaymentItem;
import net.jeeshop.biz.finance.model.PaymentItemExample;
import net.jeeshop.biz.finance.paygate.PaygateService;
import net.jeeshop.biz.finance.paygate.PaygateType;
import net.jeeshop.biz.finance.paygate.bean.PayRequestBean;
import net.jeeshop.biz.finance.paygate.bean.PayResponseBean;
import net.jeeshop.web.bean.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 支付服务
 * @author dylan
 * @date 16/3/8 21:41
 * Email: dinguangx@163.com
 */
@Service
public class PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;
    @Autowired
    private PaymentItemMapper paymentItemMapper;

    @Autowired
    private PaygateService paygateService;
    /**
     * 请求支付
     * @param paymentBean
     * @return
     */
    @Transactional
    public Payment savePaymentRequest(PaymentBean paymentBean) {
        //1. 创建payment&paymentItem
        Payment payment = buildPayment(paymentBean);
        paymentMapper.insert(payment);
        List<PaymentItem> paymentItems = buildPaymentItems(payment, paymentBean.getPaymentItems());
        for(PaymentItem item : paymentItems) {
            paymentItemMapper.insert(item);
        }
        return payment;
    }

    /**
     *
     * @param paymentId
     * @return
     */
    public PaymentResultBean payPayment(Long paymentId) {
        PaymentResultBean resultBean = new PaymentResultBean();

        Payment payment = paymentMapper.selectByPrimaryKey(paymentId);
        if(payment == null) {
            //TODO paymentId不存在
        }
        resultBean.setResCode("00");
        resultBean.setResMsg("处理成功");
        resultBean.setRequestNum(payment.getRequestNum());
        resultBean.setMemberId(payment.getMemberId());
        resultBean.setAmount(payment.getAmount());
        resultBean.setPaymentResult(PaymentResult.SUCCESS);

        PaymentItemExample example = new PaymentItemExample();
        PaymentItemExample.Criteria criteria = example.createCriteria();
        criteria.andPaymentIdEqualTo(paymentId);
        List<PaymentItem> paymentItems = paymentItemMapper.selectByExample(example);

        //处理支付结果item
        for(PaymentItem  item : paymentItems) {
            if(item.getPaymentStatus() == PaymentStatus.PAID) {
                //已经支付成功
                continue;
            }
            PaymentTypeCode paymentType = item.getPaymentType();
            PaygateType paygateType = paymentType.getPaygateType();
            if(paygateType != null) {
                PayRequestBean payRequestBean = new PayRequestBean();
                payRequestBean.setPaygateType(paygateType);
                payRequestBean.setOrderNum(item.getPaymentNum());
                payRequestBean.setComment(payment.getRemark());
                payRequestBean.setAmount(item.getAmount());
                payRequestBean.setFgNotifyUrl("");
                payRequestBean.setBgNotifyUrl("");
                payRequestBean.setBankCode("");
                ResultBean<PayResponseBean> paygateResultBean = paygateService.requestPay(payRequestBean);
                if(paygateResultBean.isSuccess()) {
                    PayResponseBean paygateResponse = paygateResultBean.getData();
                    if(paygateResponse.isPayCompleted()) {
                        //支付成功的处理
                    } else {
                        resultBean.setPayUrl(paygateResponse.getPayUrl());
                        resultBean.setWaitPayAmount(item.getAmount());
                        resultBean.setPaymentResult(PaymentResult.WAIT_PAY);
                    }
                }
            } else if(paymentType == PaymentTypeCode.POINTS) {
                //TODO 积分支付
            } else if(paymentType == PaymentTypeCode.OFFLINE) {
                //TODO 线下支付，
            }
        }
        return resultBean;
    }

    private Payment buildPayment(PaymentBean paymentBean) {
        Payment payment = new Payment();
        payment.setMemberId(paymentBean.getMemberId());
        payment.setOrderId(paymentBean.getOrderId());
        payment.setAmount(paymentBean.getAmount());
        payment.setRequestNum(paymentBean.getRequestNum());
        payment.setRefundedAmount(0.00);
        payment.setRemark(paymentBean.getRemark());
        payment.setPaymentStatus(PaymentStatus.INIT);
        payment.setRefundStatus(RefundStatus.NONE);
        payment.setCreateTime(new Date());
        payment.setUpdateTime(new Date());
        payment.setCreateAccount("FIN-SYS");
        payment.setUpdateAccount("FIN-SYS");
        return payment;
    }

    private List<PaymentItem> buildPaymentItems(final Payment payment, List<PaymentItemBean> paymentItems) {

        return Lists.transform(paymentItems, new Function<PaymentItemBean, PaymentItem>() {
            @Override
            public PaymentItem apply(PaymentItemBean input) {
                PaymentItem item = new PaymentItem();
                item.setMemberId(payment.getMemberId());
                item.setPaymentId(payment.getId());
                item.setPaymentType(input.getPaymentType());
//                item.setPaymentTypeId();
                item.setAmount(input.getAmount());
                item.setPaymentStatus(PaymentStatus.INIT);
                item.setRefundedAmount(0.00);
                item.setCreateTime(new Date());
                item.setUpdateTime(new Date());
                item.setCreateAccount("FIN-SYS");
                item.setUpdateAccount("FIN-SYS");
                return item;
            }
        });
    }
}
