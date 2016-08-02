package net.jeeshop.web.controller.front.product;

import com.alibaba.fastjson.JSONObject;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.data.model.NuberInfo;
import net.jeeshop.biz.data.model.NuberInfoExample;
import net.jeeshop.biz.data.service.NumSerivce;
import net.jeeshop.biz.order.bean.OrderBean;
import net.jeeshop.biz.order.client.OrderItemMapper;
import net.jeeshop.biz.order.client.OrderMapper;
import net.jeeshop.biz.order.client.OrderPayMapper;
import net.jeeshop.biz.order.client.OrderPayRequestMapper;
import net.jeeshop.biz.order.model.*;
import net.jeeshop.biz.product.bean.ProductInfoBean;
import net.jeeshop.biz.product.client.PackageSpecInfoMapper;
import net.jeeshop.biz.product.model.*;
import net.jeeshop.biz.product.service.GoodsService;
import net.jeeshop.biz.product.service.PackageService;
import net.jeeshop.biz.product.service.ProcessInfoService;
import net.jeeshop.biz.product.service.ProductInfoService;
import net.jeeshop.core.util.BillNumberBuilder;
import net.jeeshop.web.controller.common.AvoidDuplicateSubmission;
import net.jeeshop.web.controller.manage.ManageBaseController;
import net.jeeshop.web.util.CookieUtil;
import net.jeeshop.web.util.pay.config.PayConfig;
import net.jeeshop.web.util.pay.util.PaySubmit;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.*;

/**
 * 产品controller
 */
@Controller
@RequestMapping("/product")
public class ProductController extends ManageBaseController{

    static final String STATUS_TODO = "04"; //待办理（已经支付）
    static final String STATUS_TOPAY = "03"; //待支付

    static final String PAYTYPE_ALI = "22" ;
    static final String PAYTYPE_LL = "21" ;
    static final String PAYTYPE_NO = "23" ;

    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private ProcessInfoService processInfoService;

    @Autowired
    private PackageService packageService;
    @Autowired
    private PackageSpecInfoMapper packageSpecInfoMapper;

    @Autowired
    private OrderPayRequestMapper orderPayRequestMapper;
    @Autowired
    private OrderPayMapper orderPayMapper;

    @Autowired
    NumSerivce numSerivce ;


    @Override
    public BaseService getService() {
        return productInfoService;
    }

    /**
     * 根据产品id查询所有规格商品
     * @param productid
     * @return
     */
    @RequestMapping(value = "goods/{productid}")
    @ResponseBody
    public Object queryGoodsByProductId(@PathVariable long productid){

        logger.debug("根据产品id查询规格商品");

        GoodsInfoExample goodsInfoExample = new GoodsInfoExample();
        GoodsInfoExample.Criteria criteria = goodsInfoExample.createCriteria();
        criteria.andProductIdEqualTo(productid);

        List<GoodsInfo> goodsInfoList = goodsService.selectByExampleWithBLOBs(goodsInfoExample);


        return goodsInfoList;
    }

    /**
     * 更多
     * @param type
     * @return
     */
    @RequestMapping("loadMoreGoods")
    public String moreGoods(String type){
        logger.debug("更多商品:{}",type);
        // 查询销量高的4个
        return "front/product/moreGoods";
    }

    /**
     * 推荐商品
     * @param productID
     * @return
     */
    @RequestMapping("loadRecommendGoods")
    public String loadRecommendGoods(String productID){
        logger.debug("推荐商品={}", productID);
        return "front/product/recommendGoods";
    }

    /**
     * 商品规格商品确认提交
     * @return
     */
    @RequestMapping("goodsSubmit")
    @AvoidDuplicateSubmission(needSaveToken = true)
    public String goodsSubmit(@RequestParam(required = false) Long productID,@RequestParam(required = false) Long goodsID,@RequestParam(required = false) Integer nums,Model model){

       // String productID = CookieUtil.getCookieByName(request,"productID");
//        String processInfoString = CookieUtil.getCookieByName(request,"processInfo");

        if (productID == null){
            try {
                productID = Long.parseLong(CookieUtil.getCookieByName(request, "productID"));
                goodsID = Long.parseLong(CookieUtil.getCookieByName(request, "goodsID"));
            }catch (Exception e){
                logger.error("解析错误：{},跳回商品页面", e.getMessage());
                return "redirect:/v1/goods/42";
            }
        }

        ProductInfoExample productInfoExample = new ProductInfoExample();
        ProductInfoExample.Criteria criteria =  productInfoExample.createCriteria();
        criteria.andIdEqualTo(productID);
        List<ProductInfoBean> infoList = productInfoService.selectByProductId(productInfoExample);
        ProductInfo productInfo = infoList.get(0);



        if(productInfo.getProcessid()==0){
            //不存在流程，直接进入订单支付
            return "redirect:goodsPreSubmitSave";
        }else {
            //存在流程，查询出对应流程
            ProcessInfo processInfo = processInfoService.selectById(productInfo.getProcessid());
            CookieUtil.addCookie(response,"processInfo",JSONObject.toJSONString(processInfo));
            model.addAttribute("processInfo", processInfo);

            if(processInfo.getPackageid()!=0){
                PackageInfo packageInfo = packageService.selectById(processInfo.getPackageid());
                PackageSpecInfoExample packageSpecInfoExample = new PackageSpecInfoExample();
                packageSpecInfoExample.createCriteria().andPackageIdEqualTo(String.valueOf(processInfo.getPackageid()));
                List<PackageSpecInfo> specInfos = packageSpecInfoMapper.selectByExampleWithBLOBs(packageSpecInfoExample);

//                model.addAttribute("packageInfo",packageInfo);
                model.addAttribute("specInfos",specInfos);
                model.addAttribute("packageName",packageInfo.getName());
                return "front/product/selPackage";
            }else if(processInfo.getNewnumId() >0){
                model.addAttribute("newnumId",processInfo.getNewnumId());
                return "front/product/selNewNum";
            }else if(processInfo.getOldnumId()>0){
                model.addAttribute("oldNumID",processInfo.getOldnumId());
                return "front/product/checkOldNum";
            }else if("on".equalsIgnoreCase(processInfo.getUsername())||"on".equalsIgnoreCase(processInfo.getUsertel())
                    || "on".equalsIgnoreCase(processInfo.getUserid()) || "on".equalsIgnoreCase(processInfo.getUseraddr())){
                return "front/product/enterUserInfo";
            }else if("on".equalsIgnoreCase(processInfo.getUpidcard())){
                return "front/product/inNetInfoUpload";
            }else{

                return "redirect:goodsPreSubmitSave";
            }


        }
    }


    /**
     * 选择套餐到下一步
     * @return
     */
    @RequestMapping("submitSelPackage")
    public Object submitSelPackage(Model model){

        String processInfoString = CookieUtil.getCookieByName(request,"processInfo");
        try {
            processInfoString = URLDecoder.decode(processInfoString,"UTF-8");
        } catch (Exception e) {
            logger.error("解析错误：{},跳回商品页面", e.getMessage());
            return "redirect:/v1/goods/"+CookieUtil.getCookieByName(request,"productID");
        }

        JSONObject processInfo = JSONObject.parseObject(processInfoString);
        logger.debug("流程信息：{}",processInfo);

        model.addAttribute("nums", processInfo.getString("newnums"));
        model.addAttribute("processID",processInfo.getLong("id"));

        if(processInfo.getIntValue("newnumId")>0){
            model.addAttribute("newnumId",processInfo.getIntValue("newnumId"));
            return "front/product/selNewNum";
        }else if(processInfo.getIntValue("oldnumId")>0){
            model.addAttribute("oldNumID",processInfo.getIntValue("oldnumId"));
            return "front/product/checkOldNum";
        }else if("on".equalsIgnoreCase(processInfo.getString("username"))||"on".equalsIgnoreCase(processInfo.getString("usertel"))
                || "on".equalsIgnoreCase(processInfo.getString("userid")) || "on".equalsIgnoreCase(processInfo.getString("useraddr"))){
            return "front/product/enterUserInfo";
        }else if("on".equalsIgnoreCase(processInfo.getString("upidcard"))){
            return "front/product/inNetInfoUpload";
        }else{

            return "redirect:goodsPreSubmitSave";
        }
    }

    /**
     * 选择新号到下一步
     *
     */
    @RequestMapping("submitSelNewNum")
    public String submitSelNewNum(Model model){

        String processInfoString = CookieUtil.getCookieByName(request, "processInfo");
        try {
            processInfoString = URLDecoder.decode(processInfoString,"UTF-8");
        } catch (Exception e) {
            logger.error("解析错误：{},跳回商品页面", e.getMessage());
            return "redirect:/v1/goods/"+CookieUtil.getCookieByName(request,"productID");
        }

        JSONObject processInfo = JSONObject.parseObject(processInfoString);

        if(processInfo.getIntValue("oldnumId")>0){
            model.addAttribute("oldNumID",processInfo.getIntValue("oldnumId"));
            return "front/product/checkOldNum";
        }else if("on".equalsIgnoreCase(processInfo.getString("username"))||"on".equalsIgnoreCase(processInfo.getString("usertel"))
                || "on".equalsIgnoreCase(processInfo.getString("userid")) || "on".equalsIgnoreCase(processInfo.getString("useraddr"))){
            return "front/product/enterUserInfo";
        }else if("on".equalsIgnoreCase(processInfo.getString("upidcard"))){
            return "front/product/inNetInfoUpload";
        }else{
            return "redirect:goodsPreSubmitSave";
        }
    }

    /**
     * 校验老号码到下一步
     * @return
     */
    @RequestMapping("submitCheckOldNum")
    public String submitCheckOldNum(){
        String processInfoString = CookieUtil.getCookieByName(request,"processInfo");
        try {
            processInfoString = URLDecoder.decode(processInfoString,"UTF-8");
        } catch (Exception e) {
            logger.error("解析错误：{},跳回商品页面",e.getMessage());
            return "redirect:/v1/goods/"+CookieUtil.getCookieByName(request,"productID");
        }

        JSONObject processInfo = JSONObject.parseObject(processInfoString);

        if("on".equalsIgnoreCase(processInfo.getString("username"))||"on".equalsIgnoreCase(processInfo.getString("usertel"))
                || "on".equalsIgnoreCase(processInfo.getString("userid")) || "on".equalsIgnoreCase(processInfo.getString("useraddr"))){
            return "front/product/enterUserInfo";
        }else if("on".equalsIgnoreCase(processInfo.getString("upidcard"))){
            return "front/product/inNetInfoUpload";
        }else{

            return "redirect:goodsPreSubmitSave";
        }
    }

    /**
     * 校验成功
     * @return
     */
    @RequestMapping("checkOldNumSucc")
    public String checkOldNumSucc(String phone,Model model){
        model.addAttribute("phone", phone);
        return "front/product/checkOldNumSucc";
    }

    /**
     * 填写用户信息下一步
     * @param
     * @return
     */
    @RequestMapping("enterUserInfo")
    public String enterUserInfo(){

        String processInfoString = CookieUtil.getCookieByName(request,"processInfo");
        try {
            processInfoString = URLDecoder.decode(processInfoString,"UTF-8");
        } catch (Exception e) {
            logger.error("解析错误：{},跳回商品页面",e.getMessage());
            return "redirect:/v1/goods/"+CookieUtil.getCookieByName(request,"productID");
        }

        JSONObject processInfo = JSONObject.parseObject(processInfoString);
        logger.debug("获取正确");
        if("on".equalsIgnoreCase(processInfo.getString("upidcard"))){
            return "front/product/inNetInfoUpload";
        }else{

            return "redirect:goodsPreSubmitSave";
        }
    }

    /**
     * 上传用户身份证信息页面
     * @param
     * @return
     */
    @RequestMapping("toUplaoduserIDImg")
    public String uploadUserIDImg(){
//        return "front/product/uploadUserIDImg";
        return "front/product/uploadUserID";
    }

    /**
     * 商品确认页面
     * @return
     */
    @AvoidDuplicateSubmission(needSaveToken = true)
    @RequestMapping("goodsPreSubmitSave")
    public String goodsPreSubmitSave(Long productID,Long goodsID ,Model model){
        if (productID == null){
            try {
                productID = Long.parseLong(CookieUtil.getCookieByName(request, "productID"));
                goodsID = Long.parseLong(CookieUtil.getCookieByName(request, "goodsID"));
            }catch (Exception e){
                logger.error("解析错误：{},跳回商品页面", e.getMessage());
                return "redirect:/v1/goods/42";
            }
        }
        ProductInfoExample productInfoExample = new ProductInfoExample();
        ProductInfoExample.Criteria criteria =  productInfoExample.createCriteria();
        criteria.andIdEqualTo(productID);
        List<ProductInfoBean> infoList = productInfoService.selectByProductId(productInfoExample);
        ProductInfo productInfo = infoList.get(0);

        GoodsInfo goodsInfo = goodsService.selectById(goodsID);
        Map result = new HashMap();
        result.put("productID",productID);
        result.put("goodsID",goodsID);
        result.put("sellPrice",goodsInfo.getSellPrice());
        result.put("emsFee",productInfo.getFreight());
        result.put("receiptPrice",productInfo.getReceiptPrice());
        result.put("khid",productInfo.getKhid());
        result.put("productName",productInfo.getProductName());
        result.put("imgurl",productInfo.getImg());
        result.put("selSpec",goodsInfo.getSpecArray());

        // 计算费用
        result.put("totalPrice", goodsInfo.getSellPrice() + productInfo.getFreight());
        model.addAttribute("info",result);
        //根据产品查看是否有选择流程

        return "front/product/goodsPreSubmit";
    }

    /**
     * 保存预支付订单 (此处只接收，进行重定向后保存)
     * @param order
     * @param goodsid
     * @param productid
     * @return
     */
    @RequestMapping("savePrePayOrder")
    @ResponseBody
//    @AvoidDuplicateSubmission(needRemoveToken = true)
    public Object savePerPayOrder(Order order ,OrderItem orderItem,long goodsid,long productid,String orderid,String salesId,RedirectAttributes attr){

        logger.debug("提交订单={},id={}", order, orderid);
        logger.debug("产品信息：={},={}", productid, goodsid);
        logger.debug("订单信息：{}",order.toString());
        logger.debug("订单详情：{}",orderItem.toString());

        try {
            if (StringUtils.isNotBlank(orderid)) {
                return orderid;
            }

            order.setCreateTime(new Date());
            ProductInfoExample productInfoExample = new ProductInfoExample();
            ProductInfoExample.Criteria criteria = productInfoExample.createCriteria();
            criteria.andIdEqualTo(productid);
            List<ProductInfoBean> infoList = productInfoService.selectByProductId(productInfoExample);
            ProductInfoBean productInfo = infoList.get(0);

            // 查询不到产品，返回错误页面

            GoodsInfo goodsInfo = goodsService.selectById(goodsid);

            ProcessInfo processInfo = processInfoService.selectById(productInfo.getProcessid());

            // 修改产品库存


            //计算价格
            double total = 0;
            // 各种运费+促销产品费用+产品费用
            total = productInfo.getSellPrice();
            order.setAmount(total);
            //新装
            if ("1".equals(productInfo.getType())) {
                order.setOrderstatus(STATUS_TODO);
            } else
                order.setOrderstatus(STATUS_TOPAY);
            String orderno = BillNumberBuilder.getOrderNo();
            order.setOrderNum(orderno);
            order.setKhid(productInfo.getKhid());
            order.setProductAmount(productInfo.getSellPrice());
            order.setShipAmount(productInfo.getFreight());// 运费
            order.setQuantity(1);
            order.setIsPaid("0");


            orderMapper.insert(order);
            //保存订单时，捕获异常进行跳转


            orderItem.setPrice(productInfo.getSellPrice());
            orderItem.setQuantity(1);
            orderItem.setAmount(productInfo.getSellPrice() * 1);
            orderItem.setCreateTime(new Date());
            orderItem.setOrderId(order.getId());
            orderItem.setProductId(productid);
            orderItem.setProductName(productInfo.getProductName());
            orderItemMapper.insert(orderItem);

            logger.info("创建订单成功：{}", orderno);

            attr.addFlashAttribute("orderno", orderno);
            attr.addFlashAttribute("payType", order.getPaytype());

            logger.debug("去支付");
            return order.getOrderNum();
        }catch (Exception e){
            logger.error("购买失败：{}",e.getMessage());
            return 0;
        }

    }

    /**
     * 去支付
     * @return
     */
    @RequestMapping("toPay")
    public String toPay(@ModelAttribute("payInfo") HashMap <String,String>payInfo,String orderno,String payType,RedirectAttributes attr){
        logger.debug("支付类型 orderno={}：type={}",orderno, payType);


        if(PAYTYPE_NO.equals(payType)){
            attr.addFlashAttribute("orderno", orderno);
            return "redirect:submitSucc";
        }else {
            try {
                OrderExample orderExample = new OrderExample();
                orderExample.createCriteria().andOrderNumEqualTo(orderno);
                List orderList = orderMapper.selectByExample(orderExample);

                OrderBean order = (OrderBean) orderList.get(0);

                payInfo.put("notify_url","http://61.135.193.158:8080/wkshop/product/notifyForLLPay");

                payInfo.put("retrun_url","http://61.135.193.158:8080/wkshop/product/returnForLLPay");
                //记录支付日志和记录
                OrderPayRequest orderPayRequest = new OrderPayRequest();
                orderPayRequest.setCreateTime(new Date());
                orderPayRequest.setAmount(order.getAmount());
                orderPayRequest.setOrderId(order.getId());
                orderPayRequest.setOrderPayId(System.currentTimeMillis());
                orderPayRequest.setOrderNum(order.getOrderNum());


                if (PAYTYPE_ALI.equals(payType)) {
                    payInfo.put("trade_no", "23423432432423423");
                    payInfo.put("return_url", "1");
                    payInfo.put("subject", "zhwjxf");
                    payInfo.put("total_fee", "0.01");
                    attr.addFlashAttribute("payInfo", payInfo);
                    orderPayRequest.setPaymentType(PAYTYPE_ALI);

                    orderPayRequestMapper.insert(orderPayRequest);

                    return "redirect:alipayapi";
                } else {
                    payInfo.put("adslaccount", System.currentTimeMillis()+"");
                    payInfo.put("trade_no", System.currentTimeMillis()+"");
                    payInfo.put("return_url", "1");
                    payInfo.put("subject", "zhwjxf");
                    payInfo.put("total_fee", "0.01");
                    payInfo.put("name_goods", "测试商品");
                    attr.addFlashAttribute("payInfo", payInfo);

                    orderPayRequest.setPaymentType(PAYTYPE_LL);

                    orderPayRequestMapper.insert(orderPayRequest);
                    return "redirect:llpayapi";
                }

            }catch (Exception e){
                logger.debug("支付失败{}",e.getMessage());
                return "redirect:returnPre";
            }
        }
    }

    /**
     * 失败返回上一页
     * @return
     */
    @RequestMapping("returnPre")
    public Object toPreview(){

        return "front/pay/payFail";
    }

    /**
     * 交易成功POST地址
     * @param res_data
     * @return
     */
    @RequestMapping("returnForLLPay")
    public String returnForLLPay(String res_data,@ModelAttribute("orderno")String orderno,RedirectAttributes attributes){
        JSONObject resDataJson = JSONObject.parseObject(res_data);
        //此处支付是连的php，没有商户key等信息，无法进行认证，只能通过合订单查询金额是否正确

        String result_pay = resDataJson.getString("result_pay");

        String money_order = resDataJson.getString("money_order"); //交易额
        String no_order = resDataJson.getString("no_order"); //订单号
        String oid_paybill = resDataJson.getString("oid_paybill");//支付单号

        // 正在支付中，未成功
        if(!"SUCCESS".equals(result_pay)){
            logger.error("正在支付中");
            return "";
        }


        OrderExample orderExample =  new OrderExample();
        OrderExample.Criteria criteria  = orderExample.createCriteria();
        criteria.andOrderNumEqualTo(no_order);


        List<OrderBean> orderList = orderMapper.selectByExample(orderExample);

        if(orderList.size()>0){
            Order order = orderList.get(0);

            if(!String.valueOf(order.getAmount()).equals(money_order)){
                logger.error("支付失败，金额不符{}",money_order);
                return "";
            }

            // 查找订单状态，未支付进行更新
            if (STATUS_TOPAY.equals(order.getOrderstatus())){
                //判断订单金额是否等于支付金额

                //记录支付日志
                OrderPay orderPay = new OrderPay();
                orderPay.setOrderNum(orderno);
                orderPay.setPaymentType(PAYTYPE_LL);
                orderPay.setOrderId(order.getId());
                orderPay.setPaymentType(order.getPaytype());
                orderPay.setAmount(order.getAmount());
                orderPay.setCreateTime(new Date());
                orderPay.setPayStatus(STATUS_TODO); // 支付成功
                orderPay.setRequestNum(oid_paybill);

                orderPayMapper.insert(orderPay);

                order.setOrderstatus(STATUS_TODO); //已支付
                orderMapper.updateByPrimaryKeySelective(order);
            }
        }


        //更新支付交易状态
        orderno = no_order;

        attributes.addFlashAttribute("orderno",orderno);

        return "redirect:submitSucc";

    }

    /**
     *  支付成功异步通知
     * @param request
     * @return
     */
    @RequestMapping("notifyUrlForLLPay")
    @ResponseBody
    public Object notifyUrlForLLPay(HttpServletRequest request){

        InputStream is = null;
        try {
            is = request.getInputStream();
            DataInputStream input = new DataInputStream(is);
            String str =input.readUTF();

            JSONObject resDataJson = JSONObject.parseObject(str);

            String result_pay = resDataJson.getString("result_pay"); //支付结果 SUCCESS, PROCESSING

            String money_order = resDataJson.getString("money_order"); //交易额
            String no_order = resDataJson.getString("no_order"); //订单号
            String oid_paybill = resDataJson.getString("oid_paybill");//支付单号

            // 正在支付中，未成功
            if(!"SUCCESS".equals(result_pay)){
                logger.error("正在支付中");
                return "";
            }

            OrderExample orderExample =  new OrderExample();
            OrderExample.Criteria criteria  = orderExample.createCriteria();
            criteria.andOrderNumEqualTo(no_order);

            List<OrderBean> orderList = orderMapper.selectByExample(orderExample);

            if(orderList.size()>0){
                Order order = orderList.get(0);

                if(!String.valueOf(order.getAmount()).equals(money_order)){
                    logger.error("支付失败，金额不符{}",money_order);
                    return "";
                }

                // 查找订单状态，未支付进行更新
                if (STATUS_TOPAY.equals(order.getOrderstatus())){
                    //判断订单金额是否等于支付金额

                    //记录支付日志
                    OrderPay orderPay = new OrderPay();
                    orderPay.setOrderNum(no_order);
                    orderPay.setPaymentType(PAYTYPE_LL);
                    orderPay.setOrderId(order.getId());
                    orderPay.setPaymentType(order.getPaytype());
                    orderPay.setAmount(order.getAmount());
                    orderPay.setCreateTime(new Date());
                    orderPay.setPayStatus(STATUS_TODO); // 支付成功
                    orderPay.setRequestNum(oid_paybill);

                    orderPayMapper.insert(orderPay);

                    order.setOrderstatus(STATUS_TODO); //已支付
                    orderMapper.updateByPrimaryKeySelective(order);
                }
            }



        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

        JSONObject retJson = new JSONObject();
        retJson.put("ret_code","0000");
        retJson.put("ret_msg", "交易成功");

        return retJson;
    }

    /**
     * 实时查询订单状态
     * @return
     */
    public String orderQuery(HashMap map){

        return "";
    }


    /**
     * 连连支付
     * @return
     * @throws Exception
     */
    @RequestMapping("llpayapi")
    @ResponseBody
    public String llpayapi(@ModelAttribute("payInfo") HashMap<String,String> payInfo)
            throws Exception {
        logger.debug("连连支付：{}",payInfo.toString());
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("user_id",payInfo.get("adslaccount")); // userid
//        sParaTemp.put("seller_id",PayConfig.seller_id);
        sParaTemp.put("_input_charset", PayConfig.input_charset);
//		sParaTemp.put("payment_type", payment_type);
        sParaTemp.put("notify_url", payInfo.get("notify_url"));
        sParaTemp.put("return_url", payInfo.get("return_url"));
        sParaTemp.put("no_order", payInfo.get("trade_no")); //order
        sParaTemp.put("money_order", payInfo.get("total_fee"));
        sParaTemp.put("name_goods", "测试商品");
        sParaTemp.put("pay_type","ll");

        String sHtmlText = PaySubmit.buildRequest(sParaTemp, "post", "确认");

        return sHtmlText;

    }

    /**
     * 阿里支付
     * @return
     * @throws Exception
     */
    @RequestMapping("alipayapi")
    public String alipayapi(@ModelAttribute HashMap<String,String> payInfo)
            throws Exception {

        //把请求参数打包成数组
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", PayConfig.service);
        sParaTemp.put("partner", PayConfig.partner);
        sParaTemp.put("seller_id", PayConfig.seller_id);
        sParaTemp.put("_input_charset", PayConfig.input_charset);
        sParaTemp.put("payment_type", PayConfig.payment_type);
        sParaTemp.put("notify_url", payInfo.get("notify_url"));
        sParaTemp.put("return_url", payInfo.get("return_url"));
        sParaTemp.put("out_trade_no", payInfo.get("trade_no"));
        sParaTemp.put("subject", "测试商品");
        sParaTemp.put("total_fee", payInfo.get("total_fee"));
        //收银台页面的商品展示连接
        sParaTemp.put("show_url", "");
        //sParaTemp.put("app_pay","Y");//启用此参数可唤起钱包APP支付。
//        sParaTemp.put("body", body);
        String sHtmlText = PaySubmit.buildRequest(sParaTemp, "post", "确认");

        return sHtmlText;
    }


    /**
     * 保存跳转正常页面
     */
    @RequestMapping("submitSucc")
    public String submitSucc(@ModelAttribute("orderno")String id,Model model){
        if(StringUtils.isBlank(id)){
            return "redirect:/v1/goods/"+CookieUtil.getCookieByName(request,"productID");
        }
        OrderExample orderExample = new OrderExample();
        orderExample.createCriteria().andOrderNumEqualTo(id);
        OrderBean order =  orderMapper.selectByExample(orderExample).get(0);

        model.addAttribute("orderid",id);
        model.addAttribute("amount",order.getProductAmount());
        model.addAttribute("khid",order.getKhid());
        String payType = "货到付款";
        if(PAYTYPE_ALI.equals(order.getPaytype())){
            payType="支付宝支付";
        }else if(PAYTYPE_LL.equals(order.getPaytype())){
            payType="连连支付";
        }
        model.addAttribute("payType", payType);

        return "front/product/orderSucc";
    }

    /**
     * 预支付
     * @return
     */
    @RequestMapping("prePay")
    public String prePay(){
        return "front/product/goodsPrePayOrder";
    }

    /**
     * 添加临时地址，不保存
     * @return
     */
    @RequestMapping("toAddTempAddr")
    public String toAddTempAddr(){
        return "front/product/addAddr";
    }


    /**
     * 选择添加发票信息
     * @return
     */
    @RequestMapping("toAddTicketInfo")
    public String toAddTicketInfo(){
        return "front/product/ticketInfo";
    }

    /**
     * 根据产品id查询信息
     * @param productID
     * @return
     */
    @RequestMapping("queryProductByID")
    @ResponseBody
    public Object queryProductByID(long productID){

        ProductInfoExample productInfoExample = new ProductInfoExample();
        ProductInfoExample.Criteria criteria =  productInfoExample.createCriteria();
        criteria.andIdEqualTo(productID);
        List<ProductInfoBean> infoList = productInfoService.selectByProductId(productInfoExample);
        ProductInfoBean productInfo = infoList.get(0);
        return productInfo;
    }

    /**
     * 实时获取库存
     * @param id
     * @return
     */
    @RequestMapping("queryGoodsStock")
    @ResponseBody
    public Integer queryGoodsStock(long id){
        GoodsInfo goodsInfo  = new GoodsInfo();
        goodsInfo = goodsService.selectById(id);
        return goodsInfo.getStoreNums();
    }

    /**
     * 根据商品id获取规格等信息
     * @param goodsID
     * @return
     */
    @RequestMapping("queryGoodsByID")
    @ResponseBody
    public Object queryGoodsByID(long goodsID){
        GoodsInfo goodsInfo = goodsService.selectById(goodsID);

        return goodsInfo;
    }

    /**
     * 校验号码是否在号码库
     * @param phone
     * @param numID
     * @return
     */
    @RequestMapping("checkNumByID")
    @ResponseBody
    public Object checkNumByID(String phone,int numID){
//        NuberInfoExample nuberInfoExample = new NuberInfoExample();
//        NuberInfoExample.Criteria criteria = nuberInfoExample.createCriteria();
//        criteria.andNumberidEqualTo(numID);
//        criteria.andPhonenumEqualTo(phone);
//
//        NuberInfo nuberInfo = numSerivce.selectUniqueByExample(nuberInfoExample);
//        if(nuberInfo!=null){
//            return "ok";
//        }else{
//            return "error";
//        }

        /**
         * 测试
         */
        return "ok";
    }


    /**
     * 随机取10个号码（此处为连续）
     * @param numid
     * @return
     */
    @RequestMapping("refreshPhonenum")
    @ResponseBody
    public Object refreshPhonenum(Integer numid){

        return numSerivce.randTenPhoneNum(numid);
    }

    /**
     * 校验号码是否被占用
     * @param numArr
     * @return
     */
    @RequestMapping("checkIsOccupy")
    @ResponseBody
    public Object checkIsOccupy(@RequestParam("numArr[]") List<String> numArr,Integer numid){

        List<Long> idList = new ArrayList<Long>();
        for (int i = 0; i < numArr.size(); i++) {
            NuberInfoExample example =  new NuberInfoExample();
            NuberInfoExample.Criteria criteria = example.createCriteria();
            criteria.andPhonenumEqualTo(numArr.get(i)).andNumberidEqualTo(numid);
            NuberInfo nuberInfo = numSerivce.selectUniqueByExample(example);
            if(!"0".equals(nuberInfo.getState())){
                return numArr.get(i);
            }
            idList.add(nuberInfo.getId());
        }

        NuberInfo nuberIfo = new NuberInfo();
        nuberIfo.setNumberid(numid);
        nuberIfo.setState("2");//占用
        for(int j = 0 ;j<numArr.size();j++ ){
//            nuberIfo.setPhonenum(numArr.get(j));
            nuberIfo.setId(idList.get(j));
            numSerivce.update(nuberIfo);
        }

        return "no";
    }
}

