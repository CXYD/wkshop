package net.jeeshop.web.controller.manage.order;

import net.jeeshop.biz.base.bean.PageBean;
import net.jeeshop.biz.base.bean.PageQueryBean;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.order.bean.OrderBean;
import net.jeeshop.biz.order.model.OrderExample;
import net.jeeshop.biz.order.service.OrderService;
import net.jeeshop.biz.system.model.KeyValue;
import net.jeeshop.biz.system.model.KeyValueExample;
import net.jeeshop.biz.system.service.KeyValueService;
import net.jeeshop.web.controller.common.ExcelUtil;
import net.jeeshop.web.controller.manage.ManageBaseController;
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
 * Created by pengdong on 2016/6/8.
 * 收支明细
 */
@Controller
@RequestMapping("manage/order/banlancePay")
public class BanlancePayController extends ManageBaseController<OrderBean,OrderExample> {
    private static final long serialVersionUID = 1L;
    @Autowired
    OrderService orderService;
    @Autowired
    KeyValueService keyValueService;
    private static final String page_toList = "manage/order/banlPayList";
    @Override
    public BaseService<OrderBean, OrderExample> getService() {
        return orderService;
    }
    @RequestMapping("toBanlPayList")
    public String toOrderList(HttpServletRequest request){
        KeyValueExample keyValueExample = new KeyValueExample();
        List<KeyValue> keyValueList = keyValueService.selectByExample(keyValueExample);
        request.setAttribute("KVlist",keyValueList);
        return page_toList;
    }

    @RequestMapping("loadData")
    @ResponseBody
    public PageBean<OrderBean> loadData(@ModelAttribute("queryParams")OrderBean queryParams, PageQueryBean pageQueryBean) {
        logger.debug("订单号："+queryParams.getOrderNum());
        OrderExample example = new OrderExample();
        /*example.setOrderByClause("id asc");*/
//        PageBean  pager = orderService.selectPageBean(queryParams, pageQueryBean);
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        if(StringUtils.isNotBlank(queryParams.getOrderNum())){
            criteria.andOrderNumLike("%"+queryParams.getOrderNum()+"%");
        }
        if(StringUtils.isNotBlank(queryParams.getPaytype())){
            criteria.andPaytypeEqualTo(queryParams.getPaytype());
        }
        if(StringUtils.isNotBlank(queryParams.getSettlestatus())){
            criteria.andSettlestatusEqualTo(queryParams.getSettlestatus());
        }
        Date finishTime = queryParams.getFinishtime();
        Date endfinishTime = queryParams.getEndTime();
        if(finishTime!=null && endfinishTime!=null){
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String startd = simpleDateFormat.format(finishTime);
                String endd = simpleDateFormat.format(endfinishTime);
                criteria.andFinishtimeBetween(simpleDateFormat.parse(startd), simpleDateFormat.parse(endd));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
            List<String> list = new ArrayList<String>();
            list.add("0");
            list.add("1");
            criteria.andSettlestatusIn(list);

        orderExample.setOrderByClause(getOrderColumnName(request));

        PageBean  pager  = orderService.selectBalPayList(orderExample, pageQueryBean);
        return pager;
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

    /**
     * 导出数据
     * @param request
     * @param response
     * @param pageQueryBean
     * @param filename
     * @param queryParams
     * @return
     */
    @RequestMapping("balPayExport")
    public String balPayExport(HttpServletRequest request, HttpServletResponse response,PageQueryBean pageQueryBean,String filename,OrderBean queryParams) {
        OrderExample example = new OrderExample();
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        if(StringUtils.isNotBlank(queryParams.getOrderNum())){
            criteria.andOrderNumLike("%"+queryParams.getOrderNum()+"%");
        }
        if(StringUtils.isNotBlank(queryParams.getPaytype())){
            criteria.andPaytypeEqualTo(queryParams.getPaytype());
        }
        if(StringUtils.isNotBlank(queryParams.getSettlestatus())){
            criteria.andSettlestatusEqualTo(queryParams.getSettlestatus());
        }
        Date finishTime = queryParams.getFinishtime();
        Date endfinishTime = queryParams.getEndTime();
        if(finishTime!=null && endfinishTime!=null){
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String startd = simpleDateFormat.format(finishTime);
                String endd = simpleDateFormat.format(endfinishTime);
                criteria.andFinishtimeBetween(simpleDateFormat.parse(startd), simpleDateFormat.parse(endd));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        List<String> statusList = new ArrayList<String>();
        statusList.add("0");
        statusList.add("1");
        criteria.andSettlestatusIn(statusList);

        orderExample.setOrderByClause("o.finishtime DESC");

        PageBean  pager  = orderService.selectBalPayList(orderExample, pageQueryBean);

        List<Map<String, Object>> list = createExcelRecord(pager.getList());

        String columnNames[] = {"完成时间", "订单号", "下单时间","商品名称","支付方式","实付金额","佣金","结算金额","结算状态"};//列名
        String keys[] = {"finishtime", "order_num", "create_time","productName","paytype","amount","commision","balamount","settlestatus"};//map中的key
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ExcelUtil.createWorkBook(list, keys, columnNames).write(os);
        } catch (IOException e) {
            e.printStackTrace();
            request.setAttribute("message", "导出失败!");
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
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            mapValue.put("finishtime", simpleDateFormat.format(project.getFinishtime()));
            mapValue.put("order_num",project.getOrderNum());
            mapValue.put("create_time",simpleDateFormat.format(project.getCreateTime()));
            mapValue.put("productName",project.getProductName());
            mapValue.put("paytype",project.getPaytype());
            mapValue.put("amount", project.getAmount());
            mapValue.put("commision",project.getCommision());
            mapValue.put("balamount", project.getBalamount());
            String settlestatus = project.getSettlestatus();
            mapValue.put("settlestatus",settlestatus);
            listmap.add(mapValue);
        }
        return listmap;
    }

}
