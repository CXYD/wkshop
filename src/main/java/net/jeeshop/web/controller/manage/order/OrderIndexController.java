package net.jeeshop.web.controller.manage.order;

import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.order.bean.OrderBean;
import net.jeeshop.biz.order.model.OrderExample;
import net.jeeshop.biz.order.service.OrderService;
import net.jeeshop.web.controller.manage.ManageBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * 订单概况
 * Created by pengdong on 2016/6/5.
 */
@Controller
@RequestMapping("manage/order/orderIndex")
public class OrderIndexController extends ManageBaseController {
    private static final long serialVersionUID = 1L;
    @Autowired
    OrderService orderService;
    private static final String page_toList = "manage/order/orderIndex";

    private OrderIndexController() {
        super.page_toList = page_toList;
    }

    @Override
    public BaseService getService() {
        return null;
    }
    @RequestMapping("toOrderIndex")
    public  String toOrderList(HttpServletRequest request){

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        String first = format.format(c.getTime());
        logger.debug("===============first:" + first);
        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = format.format(ca.getTime());
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andCreateTimeBetween(c.getTime(),ca.getTime());
        List<OrderBean> createNumList = orderService.selectByExample(orderExample);
        request.setAttribute("createNumList",createNumList);
        int payNum=0;
        Double totalprice=0.00;
        for(int i=0;i<createNumList.size();i++){
            OrderBean orderBean = createNumList.get(i);
            String isPaid = orderBean.getIsPaid();
            if("1".equals(isPaid)){
                Double amount = orderBean.getAmount();
                totalprice+=amount;
                payNum++;
            }
        }
        request.setAttribute("totalprice",totalprice);
        request.setAttribute("payNum",payNum);
        return page_toList;

    }

}