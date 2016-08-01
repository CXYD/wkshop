package net.jeeshop.web.controller.manage.order;

import net.jeeshop.biz.base.bean.PageBean;
import net.jeeshop.biz.base.bean.PageQueryBean;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.order.bean.OrderBean;
import net.jeeshop.biz.order.model.OrderExample;
import net.jeeshop.biz.order.service.OrderService;
import net.jeeshop.biz.system.model.KeyValue;
import net.jeeshop.biz.system.model.KeyValueExample;
import net.jeeshop.biz.system.model.SysUser;
import net.jeeshop.biz.system.service.KeyValueService;
import net.jeeshop.web.controller.common.Const;
import net.jeeshop.web.controller.common.ExcelUtil;
import net.jeeshop.web.controller.manage.ManageBaseController;
import net.jeeshop.web.util.LoginUserHolder;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 管理订单
 * Created by pengdong on 2016/6/2.
 */

@Controller
@RequestMapping("manage/order/orderManage")
public class OrderManageController  extends ManageBaseController<OrderBean,OrderExample> {

    private static final long serialVersionUID = 1L;
    @Autowired
    OrderService orderService;
    @Autowired
    KeyValueService keyValueService;

    private static final String page_toList = "manage/order/orderList";

    private OrderManageController() {
        super.page_toList = page_toList;
    }

    @RequestMapping("toOrderList")
    public String toOrderList(HttpServletRequest request){
        KeyValueExample keyValueExample = new KeyValueExample();
        List<KeyValue> keyValueList = keyValueService.selectByExample(keyValueExample);
        request.setAttribute("KVlist",keyValueList);
        return page_toList;
    }

    /**
     * 订单详情
     * @param orderNum
     * @param request
     * @return
     */
    @RequestMapping("toOrderDetail")
    public String toOrderDetail(String orderNum,HttpServletRequest request){
        try {
            OrderExample orderExample = new OrderExample();
            OrderExample.Criteria criteria = orderExample.createCriteria();
            criteria.andOrderNumEqualTo(orderNum);
            List<OrderBean> list = orderService.queryOrderDetail(orderExample);
            if(list.size()>0){
                OrderBean orderBean = list.get(0);
                request.setAttribute("odBean",orderBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/manage/order/orderDetail";
    }

    @Override
    public BaseService<OrderBean, OrderExample> getService() {
        return orderService;
    }

    @RequestMapping("loadData")
    @ResponseBody
    public PageBean<OrderBean> loadData(@ModelAttribute("queryParams")OrderBean queryParams, PageQueryBean pageQueryBean) {
        logger.debug("订单号："+queryParams.getOrderNum());
        PageBean  pager  = null;
        try {
            OrderExample orderExample = new OrderExample();
            OrderExample.Criteria criteria = orderExample.createCriteria();
            if(LoginUserHolder.getLoginUser()!=null && StringUtils.isNotBlank(LoginUserHolder.getLoginUser().getKhid())){
                criteria.andKhidEqualTo(LoginUserHolder.getLoginUser().getKhid());
                if(StringUtils.isNotBlank(queryParams.getOrderNum())){
                    criteria.andOrderNumLike("%"+queryParams.getOrderNum()+"%");
                }
                if(StringUtils.isNotBlank(queryParams.getLinkman())){
                    criteria.andLinkmanLike("%"+queryParams.getLinkman()+"%");
                }
                logger.debug("手机号："+queryParams.getContractmobile());
                if(StringUtils.isNotBlank(queryParams.getContractmobile()) ){
                    criteria.andContractmobileEqualTo(queryParams.getContractmobile());
                }
                if(StringUtils.isNotBlank(queryParams.getPaytype())){
                    criteria.andPaytypeEqualTo(queryParams.getPaytype());
                }
                if(StringUtils.isNotBlank(queryParams.getOrderstatus())){
                    criteria.andOrderstatusEqualTo(queryParams.getOrderstatus());
                }
                Date startTime = queryParams.getCreateTime();
                Date endTime = queryParams.getEndTime();
                if(startTime!=null && endTime!=null){
                    try {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String startd = simpleDateFormat.format(startTime);
                        String endd = simpleDateFormat.format(endTime);
                        criteria.andCreateTimeGreaterThanOrEqualTo(simpleDateFormat.parse(startd));
                        criteria.andCreateTimeLessThanOrEqualTo(simpleDateFormat.parse(endd));            } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                orderExample.setOrderByClause(getOrderColumnName(request));
                pager  = orderService.queryOrder(orderExample, pageQueryBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pager;
    }
   @RequestMapping("updateOrderStatus")
   @ResponseBody
    public String updateOrderStatus(Long id,String status){
       if(id>0){
           OrderBean    order = orderService.selectById(id);
           SysUser loginUser = LoginUserHolder.getLoginUser();
           if(StringUtils.isNotBlank(loginUser.getNickname())){
               order.setUpdateAccount(loginUser.getNickname());
           }
           order.setOrderstatus(status);
           if(Const.HANDLE_SUCCESS.equals(status)){
               order.setSettlestatus(Const.PRE_SETTLE);
               order.setFinishtime(new Date());
           }
           int i = orderService.update(order);
           if(i>0){
               return "success";
           }
       }
       return "fail";
    }

    /**
     * 办理失败 添加备注
     * @param id
     * @param remark
     * @return
     */
    @RequestMapping("addRemark")
    @ResponseBody
    public String addRemark(Long id,String remark,String orderstatus){
        if(id>0){
            OrderBean  order = orderService.selectById(id);
             SysUser loginUser = LoginUserHolder.getLoginUser();
            if(StringUtils.isNotBlank(loginUser.getNickname())){
                order.setUpdateAccount(loginUser.getNickname());
            }
            if(StringUtils.isNotBlank(remark)){
                order.setRemark(remark);
            }
            order.setOrderstatus(orderstatus);
            int i = orderService.update(order);
            if(i>0){
                return "success";
            }
        }
        return "fail";
    }

    /**
     *日期转换
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值
    }
    @RequestMapping("orderDataExport")
    public String orderDataExport(HttpServletRequest request, HttpServletResponse response,PageQueryBean pageQueryBean,String filename,OrderBean queryParams) {
        OrderExample example = new OrderExample();
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        if(LoginUserHolder.getLoginUser()!=null && StringUtils.isNotBlank(LoginUserHolder.getLoginUser().getKhid())){
            criteria.andKhidEqualTo(LoginUserHolder.getLoginUser().getKhid());
            if(StringUtils.isNotBlank(queryParams.getOrderNum())){
                criteria.andOrderNumLike("%"+queryParams.getOrderNum()+"%");
            }
            if(StringUtils.isNotBlank(queryParams.getLinkman())){
                criteria.andLinkmanLike("%"+queryParams.getLinkman()+"%");
            }
            logger.debug("手机号："+String.valueOf(queryParams.getContractmobile()));
            if(StringUtils.isNotBlank(queryParams.getContractmobile())){
                criteria.andContractmobileEqualTo(queryParams.getContractmobile());
            }
            if(StringUtils.isNotBlank(queryParams.getPaytype())){
                criteria.andPaytypeEqualTo(queryParams.getPaytype());
            }
            if(StringUtils.isNotBlank(queryParams.getOrderstatus())){
                criteria.andOrderstatusEqualTo(queryParams.getOrderstatus());
            }
            Date startTime = queryParams.getCreateTime();
            Date endTime = queryParams.getEndTime();
            if(startTime!=null && endTime!=null){
                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String startd = simpleDateFormat.format(startTime);
                    String endd = simpleDateFormat.format(endTime);
                    criteria.andCreateTimeGreaterThanOrEqualTo(simpleDateFormat.parse(startd));
                    criteria.andCreateTimeLessThanOrEqualTo(simpleDateFormat.parse(endd));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            orderExample.setOrderByClause("o.order_num DESC");

            PageBean  pager  = orderService.queryOrder(orderExample, pageQueryBean);

            List<Map<String, Object>> list = createExcelRecord(pager.getList());

            String columnNames[] = {"订单号", "下单时间", "商品名称","单价","数量","联系人","联系电话","支付方式","实付金额","订单状态"};//列名
            String keys[] = {"order_num", "create_time", "productName","price","quantity","linkman","contractmobile","paytype","amount","orderstatus"};//map中的key
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            try {
                ExcelUtil.createWorkBook(list, keys, columnNames).write(os);
            } catch (IOException e) {
                e.printStackTrace();
                request.setAttribute("message", "导出失败!");
                return page_toList;
            }
            byte[] content = os.toByteArray();
            InputStream is = new ByteArrayInputStream(content);
            // 设置response参数，可以打开下载页面
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            ServletOutputStream out = null;
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            try {
                response.setHeader("Content-Disposition", "attachment;filename=" + new String((filename + ".xls").getBytes(), "iso-8859-1"));
                out = response.getOutputStream();
                bis = new BufferedInputStream(is);
                bos = new BufferedOutputStream(out);
                byte[] buff = new byte[2048];
                int bytesRead;
                // Simple read/write loop.
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
            } catch (final IOException e) {
                e.printStackTrace();
                request.setAttribute("message", "导出失败!");
            } finally {
                try {

                    if (bis != null)
                        bis.close();
                    if (bos != null)
                        bos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    private List<Map<String, Object>> createExcelRecord(List<OrderBean> projects) {
        // TODO Auto-generated method stub
        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sheetName", "sheet1");
        listmap.add(map);
        OrderBean project=null;
        for (int j = 0; j < projects.size(); j++) {
            project=projects.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
            mapValue.put("order_num", project.getOrderNum());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            mapValue.put("create_time",simpleDateFormat.format(project.getCreateTime()));
            mapValue.put("productName",project.getProductName());
            mapValue.put("price",project.getPrice());
            mapValue.put("quantity",project.getQuantity());
            mapValue.put("linkman", project.getLinkman());
            mapValue.put("contractmobile",project.getContractmobile());
            mapValue.put("paytype", project.getPaytype());
            mapValue.put("amount",project.getAmount());
            mapValue.put("orderstatus",project.getOrderstatus());
            listmap.add(mapValue);
        }
        return listmap;
    }
}
