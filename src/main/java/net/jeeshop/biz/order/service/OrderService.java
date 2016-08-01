package net.jeeshop.biz.order.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.jeeshop.biz.base.bean.PageBean;
import net.jeeshop.biz.base.bean.PageQueryBean;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.data.client.NuberInfoMapper;
import net.jeeshop.biz.data.model.NuberInfo;
import net.jeeshop.biz.order.bean.OrderBean;
import net.jeeshop.biz.order.client.OrderItemMapper;
import net.jeeshop.biz.order.client.OrderMapper;
import net.jeeshop.biz.order.client.OrderPayMapper;
import net.jeeshop.biz.order.model.*;
import net.jeeshop.biz.product.client.GoodsInfoMapper;
import net.jeeshop.biz.product.client.ProductInfoMapper;
import net.jeeshop.biz.product.model.GoodsInfo;
import net.jeeshop.biz.product.model.ProductInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zdh on 2016/5/31.
 */
@Service
public class OrderService extends BaseService<OrderBean, OrderExample> {

    static final String STATUS_TODO = "04"; //待办理（已经支付，货到付款）
    static final String STATUS_TOPAY = "03"; //待支付

    static final String PAYTYPE_ALI = "22" ;
    static final String PAYTYPE_LL = "21" ;
    static final String PAYTYPE_NO = "23" ; //货到付款，不需要支付

    static final String PRODUCT_TYPE_NEW = "1";
    static final String PRODUCT_TYPE_XF= "2";
    static final String PRODUCT_TYPE_PHONE= "3";
    static final String PRODUCT_TYPE_CARD= "4";
    static final String PRODUCT_TYPE_QT= "5";


    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private NuberInfoMapper nuberInfoMapper;
    @Autowired
    private ProductInfoMapper productInfoMapper;
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;
    @Autowired
    private OrderPayMapper orderPayMapper;


    @Override
    protected BaseMapper<OrderBean, OrderExample> getMapper() {
        return orderMapper;
    }

    public PageBean<OrderBean> selectPageBean(final OrderBean params, PageQueryBean pageQueryBean) {
        return executePageQuery(new PageQueryExecutor<OrderBean>() {
            @Override
            public List<OrderBean> executeQuery() {
                OrderExample orderExample = new OrderExample();
                OrderExample.Criteria criteria = orderExample.createCriteria();
                if(StringUtils.isNotBlank(params.getOrderNum())){
                    criteria.andOrderNumLike("%"+params.getOrderNum()+"%");
                }
                if(StringUtils.isNotBlank(params.getLinkman())){
                    criteria.andLinkmanLike("%"+params.getLinkman()+"%");
                }
                logger.debug("手机号："+String.valueOf(params.getContractmobile()));
                if(params.getContractmobile()!=null ){
                    criteria.andContractmobileEqualTo(params.getContractmobile());
                }
                if(StringUtils.isNotBlank(params.getPaytype())){
                    criteria.andPaytypeEqualTo(params.getPaytype());
                }
                if(StringUtils.isNotBlank(params.getOrderstatus())){
                    criteria.andOrderNumEqualTo(params.getOrderstatus());
                }
                return orderMapper.selectByExample(orderExample);
            }
        }, pageQueryBean);
    }


    public PageBean<OrderBean> selectBalPayList(final OrderExample orderExample, PageQueryBean pageQueryBean) {
        return executePageQuery(new PageQueryExecutor<OrderBean>() {
            @Override
            public List<OrderBean> executeQuery() {
                return orderMapper.selectBalPay(orderExample);
            }
        }, pageQueryBean);
    }

    /**
     * 分页
     * @param orderExample
     * @param pageQueryBean
     * @return
     */
    public PageBean<OrderBean> queryOrder(final OrderExample orderExample, PageQueryBean pageQueryBean) {
        return executePageQuery(new PageQueryExecutor<OrderBean>() {
            @Override
            public List<OrderBean> executeQuery() {
                return orderMapper.queryOrder(orderExample);
            }
        }, pageQueryBean);
    }

    /**
     * 查询详情
     * @param example
     * @return
     */
    public List<OrderBean> queryOrderDetail(OrderExample example) {
        return orderMapper.queryOrder(example);
    }

    public int updateByPrimaryKeySelective(Order order) {

        return orderMapper.updateByPrimaryKeySelective(order);
    }


    /**
     *  创建订单（如果存在购物车，orderItem改为List）
     * @param order
     * @param orderItem
     * @return
     */
    @Transactional
    public boolean createOrder(Order order ,OrderItem orderItem,ProductInfo productInfo,GoodsInfo goodsInfo){

        //货到付款
            if (PAYTYPE_NO.equals(order.getPaytype())) {
                order.setOrderstatus(STATUS_TODO);
                //更新订单关联的号码状态，并添加上订单号
                updatePhoneNum(orderItem.getNewmob());

            } else {
                order.setOrderstatus(STATUS_TOPAY);
            }


        //非宽带类， 修改产品库存
            if(!PRODUCT_TYPE_NEW.equals(order.getPaytype()) && !PRODUCT_TYPE_XF.equals(order.getPaytype())){

                    goodsInfo.setStoreNums(goodsInfo.getStoreNums()-order.getQuantity());

                     goodsInfoMapper.updateByPrimaryKeySelective(goodsInfo);

                    productInfo.setStoreNums(productInfo.getStoreNums()-order.getQuantity());

                     productInfoMapper.updateByPrimaryKeySelective(productInfo);

            }

        orderMapper.insert(order);
        orderItem.setOrderId(order.getId());
        orderItemMapper.insert(orderItem);


        return true;
    }



    /**
     * 支付返回处理
     * @param orderPay
     * @return
     */
    @Transactional
    public boolean llPayReturn(OrderPay orderPay,Order order) {


        orderPayMapper.insert(orderPay);

        order.setOrderstatus(STATUS_TODO); //待办理
        order.setIsPaid("1");
        orderMapper.updateByPrimaryKeySelective(order);


        //更新号码状态
        OrderItemExample orderItemExample = new OrderItemExample();
        orderItemExample.createCriteria().andOrderIdEqualTo(order.getId());
        OrderItem orderItem = orderItemMapper.selectByExample(orderItemExample).get(0);
        updatePhoneNum(orderItem.getNewmob());

        return true;
    }

    /**
     * 更新新号状态
     * @param numsObj
     * @return
     */
    @Transactional
    public boolean updatePhoneNum(String numsObj){
        JSONObject numInfo = JSONObject.parseObject(numsObj);
        if(numInfo!=null){
            JSONArray nums = JSONArray.parseArray(numInfo.getString("num"));
            for (int i = 0; i <nums.size() ; i++) {
                String pici = numInfo.getString("pici");
                JSONObject num = (JSONObject) nums.get(i);
                NuberInfo tmpItem = new NuberInfo();
                tmpItem.setPhonenum(num.getString("phone"));
                tmpItem.setNumberid(Integer.parseInt(pici));
                tmpItem.setState("3"); //已购买

                nuberInfoMapper.updateStateByPici(tmpItem);
            }
        }
        return true;
    }

    public List<OrderBean> querySettleRecords(OrderExample orderExample) {
        return orderMapper.querySettleRecords(orderExample);
    }

}
