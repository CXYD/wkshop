package net.jeeshop.web.controller.manage.order;

import net.jeeshop.biz.base.bean.PageBean;
import net.jeeshop.biz.base.bean.PageQueryBean;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.order.model.Balance;
import net.jeeshop.biz.order.model.BalanceExample;
import net.jeeshop.biz.order.service.BalanceService;
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
 * 结算记录
 * Created by pengdong on 2016/6/2.
 */
@Controller
@RequestMapping("manage/order/balance")
public class BalanceController extends ManageBaseController<Balance,BalanceExample> {

    private static final long serialVersionUID = 1L;
    @Autowired
    BalanceService balanceService;
    private static final String page_toList = "manage/order/balanceList";
    @Autowired
    KeyValueService keyValueService;

    private BalanceController() {
        super.page_toList = page_toList;
    }
    @Override
     public BaseService<Balance, BalanceExample> getService() {
        return balanceService;
    }

        @RequestMapping("toBalancelist")
        public String toBalancelist(){
            KeyValueExample keyValueExample = new KeyValueExample();
            List<KeyValue> keyValueList = keyValueService.selectByExample(keyValueExample);
            request.setAttribute("KVlist",keyValueList);
            return page_toList;
        }

    /**
     * 查询
      * @param queryParams
     * @param pageQueryBean
     * @return
     */
    @RequestMapping("loadData")
    @ResponseBody
    public PageBean<Balance> loadData(Balance queryParams, PageQueryBean pageQueryBean) {
        PageBean  pager  = null;
        try {
            BalanceExample example = new BalanceExample();
            BalanceExample.Criteria criteria = example.createCriteria();
            if(StringUtils.isNotBlank(queryParams.getBalstatus())){
                criteria.andBalstatusEqualTo(queryParams.getBalstatus());
            }
            Date baldate = queryParams.getBaldate();
            Date endTime = queryParams.getEndTime();
            if(baldate!=null && endTime!=null){
                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String startd = simpleDateFormat.format(baldate);
                    String endd = simpleDateFormat.format(endTime);
                    criteria.andBaldateBetween(simpleDateFormat.parse(startd), simpleDateFormat.parse(endd));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            example.setOrderByClause(getOrderColumnName(request));
            pager = balanceService.selectPageList(example, pageQueryBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pager;
    }

    @RequestMapping("delByID")
    @ResponseBody
    public String delByID(Long id){
        int rows = balanceService.deleteById(id);
        if(rows>0){
            logger.debug("删除成功!");
            return "success";
        }else{
            logger.debug("删除失败!");
            return "fail";
        }

    }
    @RequestMapping("updateById")
    @ResponseBody
    public String updateById(Long id,String status){
        if(id>0){
            Balance balance = balanceService.selectById(id);
            balance.setBalstatus(status);
            int i = balanceService.update(balance);
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
    @RequestMapping("balExport")
    public String balExport(HttpServletRequest request, HttpServletResponse response,PageQueryBean pageQueryBean,String filename,Balance queryParams) {

        BalanceExample example = new BalanceExample();
        BalanceExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(queryParams.getBalstatus())){
            criteria.andBalstatusEqualTo(queryParams.getBalstatus());
        }
        Date baldate = queryParams.getBaldate();
        Date endTime = queryParams.getEndTime();
        if(baldate!=null && endTime!=null){
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String startd = simpleDateFormat.format(baldate);
                String endd = simpleDateFormat.format(endTime);
                criteria.andBaldateBetween(simpleDateFormat.parse(startd), simpleDateFormat.parse(endd));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        example.setOrderByClause("baldate DESC");

        PageBean  pager  = balanceService.selectPageList(example, pageQueryBean);

        List<Map<String, Object>> list = createExcelRecord(pager.getList());

        String columnNames[] = {"时间", "收入金额", "支出金额","结算金额","结算状态"};//列名
        String keys[] = {"baldate", "incomeamount", "payamount","balamount","balstatus"};//map中的key
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
    private List<Map<String, Object>> createExcelRecord(List<Balance> projects) {
        // TODO Auto-generated method stub
        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sheetName", "sheet1");
        listmap.add(map);
        Balance project=null;
        for (int j = 0; j < projects.size(); j++) {
            project=projects.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            mapValue.put("baldate", simpleDateFormat.format(project.getBaldate()));
            mapValue.put("incomeamount",project.getIncomeamount());
            mapValue.put("payamount",project.getPayamount());
            mapValue.put("balamount",project.getBalamount());
            mapValue.put("balstatus",project.getBalstatus());
            listmap.add(mapValue);
        }
        return listmap;
    }

}
