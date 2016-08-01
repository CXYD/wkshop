package net.jeeshop.web.controller.manage.product;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.jeeshop.biz.base.bean.PageBean;
import net.jeeshop.biz.base.bean.PageQueryBean;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.product.client.PackageSpecInfoMapper;
import net.jeeshop.biz.product.model.PackageInfo;
import net.jeeshop.biz.product.model.PackageInfoExample;
import net.jeeshop.biz.product.model.PackageSpecInfo;
import net.jeeshop.biz.product.model.PackageSpecInfoExample;
import net.jeeshop.biz.product.service.PackageService;
import net.jeeshop.web.controller.manage.ManageBaseController;
import net.jeeshop.web.util.LoginUserHolder;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: zhaodh
 * Date: 16/5/31
 * Time: 16:04
 */
@Controller
@RequestMapping("manage/product/package")
public class PackageController extends ManageBaseController {

    @Autowired
    private PackageService packageService;


    @Autowired
    private PackageSpecInfoMapper specMapper;

    @Override
    public BaseService getService() {
        return packageService;
    }

    PackageController(){
        super.page_toList = "/manage/product/package/packageList";
        super.page_toAdd = "/manage/product/package/toAddPackage";
        super.page_toEdit = super.page_toAdd;
    }





    /**
     * 初始化新增页面
     *
     * @param modelMap
     * @return
     * @throws Exception
     */
    @RequestMapping("toAddInfo")
    public String toAddInfo( Model modelMap) {
       modelMap.addAttribute("packageId", "");
        modelMap.addAttribute("systime", System.currentTimeMillis() / 1000);
        return page_toAdd;
    }


    /**
     * 初始化表格数据
     * @param queryParams
     * @param pageQueryBean
     * @return
     */
    @RequestMapping("loadData")
    @ResponseBody
    public PageBean<PackageInfo> loadData(PackageInfo queryParams, PageQueryBean pageQueryBean) {
        logger.debug("加载数据....");
        if (LoginUserHolder.getLoginUser() == null) {
            return null;
        }
        String khid =  LoginUserHolder.getLoginUser().getKhid();
        PackageInfoExample packageInfoExample = new PackageInfoExample();
        packageInfoExample.createCriteria().andKhidEqualTo(khid);
//        packageInfoExample.setOrderByClause(getOrderColumnData(request));
        packageInfoExample.setOrderByClause("create_time desc");
        PageBean pager = packageService.selectPageList(packageInfoExample, pageQueryBean);
        return pager;
    }


    /**
     * 根据id查询详情
     * @param id
     * @return
     */
    @RequestMapping("queryDetail")
    @ResponseBody
    public Object queryDetail(long id){
        PackageInfoExample packageInfoExample = new PackageInfoExample();
        PackageInfoExample.Criteria criteria =  packageInfoExample.createCriteria();
        criteria.andIdEqualTo(id);
        List<PackageInfo> infoList = packageService.selectByExampleWithBLOBs(packageInfoExample);
        PackageInfo packageInfo = infoList.get(0);

        return packageInfo;
    }


    /**
     * 编辑页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("toEditInfo")
    public String toEditInfo(long id,Model model){

        logger.debug("查询商品详情={}",id);
        model.addAttribute("systime", System.currentTimeMillis() / 1000);
        model.addAttribute("packageId", id);
        return page_toEdit;
    }


    /**
     * 保存和修改
     * @param packageInfo
     * @param packageId
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public Object save(PackageInfo packageInfo,String packageId){

        Map<String,List> packageMap = new HashMap<String, List>();
        List<String> monthList = new ArrayList<String>();
        List<String> zengList= new ArrayList<String>();
        List<String> returnsList= new ArrayList<String>();
        List<String> timesList= new ArrayList<String>();
        List<String> smsList= new ArrayList<String>();
        List<String> flowList= new ArrayList<String>();
        List<JSONArray>spec_array = new ArrayList<JSONArray>();

        packageMap = getParas();

        monthList = (List) packageMap.get("monthList");
        zengList = (List) packageMap.get("zengList");
        returnsList = (List) packageMap.get("returnsList");
        timesList = (List) packageMap.get("timesList");
        smsList = (List) packageMap.get("smsList");
        flowList = (List) packageMap.get("flowList");
        spec_array = (List) packageMap.get("spec_array");

        String khid =  LoginUserHolder.getLoginUser().getKhid();

        packageInfo.setSpecArray(spec_array.get(0).toString());
        packageInfo.setStatus("1");
        packageInfo.setCreateTime(new Date());
        packageInfo.setKhid(khid);


        if(StringUtils.isBlank(packageId)) {
            packageService.insert(packageInfo);
        }else{
            packageInfo.setId(Long.valueOf(packageId));
            packageService.update(packageInfo);
            specMapper.deleteByPackageId(packageId);
        }

        JSONArray spec_json = new JSONArray();
        JSONObject specObj = new JSONObject();


        logger.debug(packageInfo.toString());
        logger.debug("查询数组");

        for (int i = 0; i < monthList.size(); i++) {

            PackageSpecInfo packageSpecInfo = new PackageSpecInfo();
            packageSpecInfo.setMonth(monthList.get(i));
            packageSpecInfo.setFolw(flowList.get(i));
            packageSpecInfo.setReturns(returnsList.get(i));
            packageSpecInfo.setSms(smsList.get(i));
            packageSpecInfo.setTimes(timesList.get(i));
            packageSpecInfo.setZeng(zengList.get(i));
            packageSpecInfo.setPackageId(String.valueOf(packageInfo.getId()));
            packageSpecInfo.setSpecArray(spec_array.get(i).toString());

            specMapper.insert(packageSpecInfo);
        }

        return "success";
    }

    /**
     * 获取所有请求参数
     * @return
     */
    public Map<String,List> getParas() {
        Map<String, String[]> paraMap = new TreeMap<String, String[]>();
        paraMap.putAll(request.getParameterMap());
        Map<String, List> packageMap = new HashMap<String, List>();
        List<String> monthList = new ArrayList<String>();
        List<String> zengList = new ArrayList<String>();
        List<String> returnsList = new ArrayList<String>();
        List<String> timesList = new ArrayList<String>();
        List<String> smsList = new ArrayList<String>();
        List<String> flowList = new ArrayList<String>();
        List<JSONArray> spec_array = new ArrayList<JSONArray>();

        for (Map.Entry<String, String[]> entry : paraMap.entrySet()) {
            String key = entry.getKey();
            String[] values = entry.getValue();
            String value = null;
            if (values.length > 0) {
                value = values[0];
            }
            logger.debug("参数key={},value={}", key, value);
            if (key.startsWith("_month")) {
                monthList.add(value);
            } else if (key.startsWith("_zeng")) {
                zengList.add(value);
            } else if (key.startsWith("_returns")) {
                returnsList.add(value);
            } else if (key.startsWith("_times")) {
                timesList.add(value);
            } else if (key.startsWith("_sms")) {
                smsList.add(value);
            } else if (key.startsWith("_folw")) {
                flowList.add(value);
            } else if (key.startsWith("_spec_array")) {
                JSONArray arr = new JSONArray();
                for (String varStr : values) {
                    JSONObject obj = JSONObject.parseObject(varStr);
                    arr.add(obj);
                }
                spec_array.add(arr);
//                JSONObject obj = JSONObject.parseObject(values[0]);

//                spec_array.add((JSONArray) JSONArray.toJSON(values));
            }
        }
        packageMap.put("monthList", monthList);
        packageMap.put("zengList", zengList);
        packageMap.put("returnsList", returnsList);
        packageMap.put("timesList", timesList);
        packageMap.put("smsList", smsList);
        packageMap.put("flowList", flowList);
        packageMap.put("spec_array", spec_array);

        return packageMap;
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @RequestMapping("delByID")
    @ResponseBody
    public int delByID(String id){
        specMapper.deleteByPackageId(id);
        return packageService.deleteById(Long.parseLong(id));
    }

    /**
     * 更新状态
     * @param id
     * @param status
     * @param packageInfo
     * @return
     */
    @RequestMapping("updateStatus")
    @ResponseBody
    public int updateStatus(long id, String status,PackageInfo packageInfo){
        packageInfo.setStatus(status);
        packageInfo.setId(id);
        return packageService.update(packageInfo);
    }

    /**
     * 查询详情列表
     * @param id
     * @return
     */
    @RequestMapping("querySpecByPid/{id}")
    public @ResponseBody Object queryGoodsByPid(@PathVariable long id){
        PackageSpecInfoExample infoExample = new PackageSpecInfoExample();
        infoExample.createCriteria().andPackageIdEqualTo(String.valueOf(id));
        List<PackageSpecInfo>infoList =  specMapper.selectByExampleWithBLOBs(infoExample);
        logger.debug("package详情：{}", infoList);
        logger.debug("package详情：{}", infoList.get(0).getSpecArray());
        return infoList;
    }

    /**
     * 校验名称
     * @param name
     * @return
     */
    @RequestMapping("checkName")
    @ResponseBody
    public String checkName(String name){
        String khid =  LoginUserHolder.getLoginUser().getKhid();
        PackageInfoExample packageInfoExample = new PackageInfoExample();
        PackageInfoExample.Criteria c = packageInfoExample.createCriteria();
        c.andNameEqualTo(name);
        c.andKhidEqualTo(khid);
        List list = packageService.selectByExample(packageInfoExample);
        if(list.size()>0){
            return "fail";
        }else{
            return "ok";
        }
    }

    /**
     * 查询package名称和id
     * @return
     */
    @RequestMapping("selectKeyValue")
    public @ResponseBody List selectKeyValue(){
        String khid =  LoginUserHolder.getLoginUser().getKhid();
        logger.debug("商户id:{}", khid);

        return packageService.selectKeyValue(khid);
    }


}
