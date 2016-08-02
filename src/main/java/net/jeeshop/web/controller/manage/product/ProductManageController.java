package net.jeeshop.web.controller.manage.product;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.jeeshop.biz.base.bean.PageBean;
import net.jeeshop.biz.base.bean.PageQueryBean;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.product.bean.ProductInfoBean;
import net.jeeshop.biz.product.model.GoodsInfo;
import net.jeeshop.biz.product.model.GoodsInfoExample;
import net.jeeshop.biz.product.model.ProductInfo;
import net.jeeshop.biz.product.model.ProductInfoExample;
import net.jeeshop.biz.product.service.GoodsService;
import net.jeeshop.biz.product.service.ProcessInfoService;
import net.jeeshop.biz.product.service.ProductInfoService;
import net.jeeshop.web.controller.manage.ManageBaseController;
import net.jeeshop.web.util.LoginUserHolder;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: zhaodh
 * Date: 16/5/31
 * Time: 16:04
 */
@Controller
@RequestMapping("manage/product/productManage")
public class ProductManageController extends ManageBaseController{

    @Autowired
    ProductInfoService productInfoService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    private ProcessInfoService processInfoService;


    private final String STATUS_UP="1";
    private final String STATUS_DOWN="0";
    private final String STATUS_DEL="2";



    ProductManageController(){
        super.page_toList = "/manage/product/productManage/toProductList";
        super.page_toEdit = "/manage/product/productManage/toProductAdd";
        super.page_toAdd = "/manage/product/productManage/toProductAdd";
    }

    @Override
    public BaseService getService() {
        return productInfoService;
    }

    @RequestMapping("toAddProduct")
    public String toAddProduct(Model model){
        model.addAttribute("systime",System.currentTimeMillis()/1000);
        model.addAttribute("productid","");
        return page_toAdd;
    }

    @RequestMapping("toProductList")
    public String toListProduct(){
        return page_toList;
    }

    /**
     * 编辑页面
     * @param productid
     * @param model
     * @return
     */
    @RequestMapping("toEditInfo")
    public String toEditInfo(long productid,Model model){

        logger.debug("查询商品详情={}",productid);
        model.addAttribute("systime", System.currentTimeMillis() / 1000);
        model.addAttribute("productid",productid);
        return page_toEdit;
    }

    @RequestMapping("queryDetail")
    @ResponseBody
    public Object queryDetail(long productid){
        ProductInfoExample productInfoExample = new ProductInfoExample();
        ProductInfoExample.Criteria criteria =  productInfoExample.createCriteria();
        criteria.andIdEqualTo(productid);
        List<ProductInfoBean> infoList = productInfoService.selectByProductId(productInfoExample);
        ProductInfoBean productInfo = infoList.get(0);

        return productInfo;
    }

    /**
     * 保存
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public Object save(ProductInfoBean productInfo){

        logger.debug("修改和保存产品信息");

        logger.debug(request.getParameterMap().toString());



        logger.debug(productInfo.toString());
        Map<String,List> goodsMap = new HashMap<String, List>();
        List<Double> old_price = new ArrayList<Double>();
        List<Double> month_price= new ArrayList<Double>();
        List<String> goods_no= new ArrayList<String>();
        List<Double> sell_price= new ArrayList<Double>();
        List<Double> commission= new ArrayList<Double>();
        List<Integer> store_nums= new ArrayList<Integer>();
        List<JSONArray>spec_array = new ArrayList<JSONArray>();




        goodsMap = getGoodParas();

        old_price = (List) goodsMap.get("old_price");
        month_price = (List) goodsMap.get("month_price");
        goods_no = (List) goodsMap.get("goods_no");
        sell_price = (List) goodsMap.get("sell_price");
        commission = (List) goodsMap.get("commission");
        store_nums = (List) goodsMap.get("store_nums");
        spec_array = (List) goodsMap.get("spec_array");

        String khid =  LoginUserHolder.getLoginUser().getKhid();

        if(spec_array.size()>0){
            productInfo.setSpecArray(spec_array.get(0).toString());
        }
        productInfo.setProductStatus(STATUS_UP);
        productInfo.setUpTime(new Date());
        productInfo.setKhid(khid);

        productInfo.setStoreNums(store_nums.get(0));
        productInfo.setSellPrice(sell_price.get(0));
        productInfo.setCommission(commission.get(0));
        productInfo.setMonthPrice(month_price.get(0));



        logger.info("商品id={}",productInfo.getId());

        if(StringUtils.isNotBlank(request.getParameter("id"))){

            productInfoService.updateByPrimaryKeySelective(productInfo);
            goodsService.deleteByProductId(productInfo.getId());

        }else {
            productInfoService.insert(productInfo);
        }

        JSONArray spec_json = new JSONArray();
        JSONObject specObj = new JSONObject();


        logger.debug(goodsMap.toString());
        logger.debug("查询数组");

        for(int i=0;i<goods_no.size();i++){

            GoodsInfo goodsInfo = new GoodsInfo();
            goodsInfo.setCommission(commission.get(i));
            goodsInfo.setGoodsNo(goods_no.get(i));
            goodsInfo.setMonthPrice(month_price.get(i));
            goodsInfo.setOldPrice(old_price.get(i));
            goodsInfo.setSellPrice(sell_price.get(i));
            goodsInfo.setStoreNums(store_nums.get(i));
            if(spec_array.size()>0)
                goodsInfo.setSpecArray(spec_array.get(i).toString());
            goodsInfo.setProductId(productInfo.getId());
            goodsService.insert(goodsInfo);
        }



//        productInfoService.update(spec_array);

//        for(){
//
//        }

//        if(StringUtils.isBlank((String) getRequestValue("_goods_no[0]"))){
//            //单一商品，无规格
//            logger.debug("单一产品");
//        }else{
//            logger.debug("多个规格");
//        }


        return productInfo.getId();
    }



    private Map getGoodParas(){
        Map<String,String[]> paraMap =  new TreeMap<String, String[]>();
        paraMap.putAll(request.getParameterMap());
        Map<String,List> goodsMap = new HashMap<String, List>();
        List<Double> old_price = new ArrayList<Double>();
        List<Double> month_price= new ArrayList<Double>();
        List<String> goods_no= new ArrayList<String>();
        List<Double> sell_price= new ArrayList<Double>();
        List<Double> commission= new ArrayList<Double>();
        List<Integer> store_nums= new ArrayList<Integer>();
        List<JSONArray>spec_array = new ArrayList<JSONArray>();

        for (Map.Entry<String,String[]> entry: paraMap.entrySet()) {
           String key = entry.getKey();
           String[] values = entry.getValue();
            String value = null;
            if(values.length>0){
                value = values[0];
            }
            logger.debug("参数key={},value={}",key,value);
            if(key.startsWith("_oldPrice")){
                if(StringUtils.isNotBlank(value))
                    old_price.add(Double.valueOf(value));
                else
                    old_price.add(0.00);
            }else if(key.startsWith("_monthPrice")){
                if(StringUtils.isNotBlank(value))
                month_price.add(Double.valueOf(value));
                else
                    month_price.add(0.00);
            }else if(key.startsWith("_goods_no")){
                if(StringUtils.isNotBlank(value))
                goods_no.add(value);
                else
                    goods_no.add("");
            }else if(key.startsWith("_sellPrice")){
                if(StringUtils.isNotBlank(value))
                sell_price.add(Double.valueOf(value));
                else
                    sell_price.add(0.00);
            }else if(key.startsWith("_commission")){
                if(StringUtils.isNotBlank(value))
                commission.add(Double.valueOf(value));
                else
                    commission.add(0.00);
            }else if(key.startsWith("_storeNums")){
                if(StringUtils.isNotBlank(value))
                store_nums.add(Integer.valueOf(value));
                else
                    store_nums.add(0);
            }else if(key.startsWith("_spec_array")){
                JSONArray arr = new JSONArray();
                for(String varStr : values){
                    JSONObject obj = JSONObject.parseObject(varStr);
                    arr.add(obj);
                }
                spec_array.add(arr);
//                JSONObject obj = JSONObject.parseObject(values[0]);

//                spec_array.add((JSONArray) JSONArray.toJSON(values));
            }
        }
        goodsMap.put("old_price",old_price);
        goodsMap.put("month_price", month_price);
        goodsMap.put("goods_no", goods_no);
        goodsMap.put("sell_price", sell_price);
        goodsMap.put("commission", commission);
        goodsMap.put("store_nums", store_nums);
        goodsMap.put("spec_array", spec_array);

        return goodsMap;
    }




    /**
     * 初始化表格数据
     * @param queryParams
     * @param pageQueryBean
     * @return
     */
    @RequestMapping("loadData")
    @ResponseBody
    public PageBean<ProductInfoBean> loadData(ProductInfoBean queryParams, PageQueryBean pageQueryBean,String status,String type,String name) {
        logger.debug("加载数据....");
        logger.debug("状态：{}", status);

        if (LoginUserHolder.getLoginUser() == null) {
            return null;
        }

        String khid =  LoginUserHolder.getLoginUser().getKhid();
        ProductInfoExample productInfoExample = new ProductInfoExample();
        ProductInfoExample.Criteria criteria  = productInfoExample.createCriteria();

        criteria.andKhidEqualTo(khid);

        if(StringUtils.isNotBlank(status)){
            criteria.andProductStatusEqualTo(status);
        }
        if(StringUtils.isNotBlank(type)){
            criteria.andTypeEqualTo(type);
        }
        if(StringUtils.isNotBlank(name)){
            criteria.andProductNameLike("%".concat(name).concat("%"));
        }
        criteria.andProductStatusIn(new ArrayList<String>() {{
            add("1");
            add("0");
        }});
//        productInfoExample.setOrderByClause(getOrderColumnName(request));
        productInfoExample.setOrderByClause("create_time desc");
        PageBean pager = productInfoService.selectPageList(productInfoExample, pageQueryBean);
        return pager;
    }

    /**
     * 批量改变分类（分组)
     * @param idsList
     * @param productClassId
     * @return
     */
    @ResponseBody
    @RequestMapping("change")
    public String change(@RequestParam("ids[]")List<Long>idsList,Long productClassId){
        logger.debug("ids={},class={}",idsList,productClassId);

        try {
            for (long id : idsList) {
                ProductInfo productInfo = new ProductInfo();
                productInfo.setCategoryId(productClassId);
                productInfo.setId(id);
                productInfoService.updateByPrimaryKeySelective(productInfo);
            }
        }catch (Exception e){
            logger.error("更改分类失败ids={},productClass={}", idsList.toString(), productClassId);
            return  "fail";
        }

        return "success";
    }

    /**
     * 批量删除
     * @param idsList
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteS")
    public String deletes(@RequestParam("ids[]")Long[] idsList){
        Map map = request.getParameterMap();
        logger.debug(map.toString());
        logger.debug("ids={}",idsList.toString());

//        int i = productInfoService.deletes(idsList);
        //没有真正删除，只是更改状态
        try {
            for (long id : idsList) {
                ProductInfo productInfo = new ProductInfo();
                productInfo.setProductStatus(STATUS_DEL);
                productInfo.setId(id);
                productInfoService.updateByPrimaryKeySelective(productInfo);
            }
        }catch (Exception e){

            logger.error("上架失败,ids={}",idsList);
            return "fail";
        }
       return "success";
    }

    /**
     * 批量上架
     * @param idsList
     * @return
     */
    @ResponseBody
    @RequestMapping("updateUp")
    public String updateUp(@RequestParam("ids[]")List<Long> idsList){
        Map map = request.getParameterMap();
        logger.debug(map.toString());
        logger.debug("上架ids={}", idsList.toString());
        try {
            for (long id : idsList) {
                ProductInfo productInfo = new ProductInfo();
                productInfo.setProductStatus(STATUS_UP);
                productInfo.setId(id);
                productInfoService.updateByPrimaryKeySelective(productInfo);
            }
        }catch (Exception e){
            logger.error("上架失败,ids={}",idsList);
            return "fail";
        }
        return "success";
    }

    /**
     * 批量下架
     * @param idsList
     * @return
     */
    @ResponseBody
    @RequestMapping("updateDown")
    public String updateDown(@RequestParam("ids[]")List<Long> idsList){
        logger.debug("下架ids={}",idsList);

        try {
            for (long id : idsList) {
                ProductInfo productInfo = new ProductInfo();
                productInfo.setProductStatus(String.valueOf(STATUS_DOWN));
                productInfo.setId(id);
                productInfoService.updateByPrimaryKeySelective(productInfo);
            }
        }catch (Exception e){
            logger.error("下失败,ids={}",idsList);
            return "fail";
        }
        return "success";
    }


    @RequestMapping("loadClassDetailData")
    @ResponseBody
    public PageBean<ProductInfoBean> loadClassDetailData(ProductInfoBean queryParams, PageQueryBean pageQueryBean,String labelid) {
        logger.debug("加载数据....");
        logger.debug("参数：queryParams={},labelid={}",queryParams,labelid);
        if (LoginUserHolder.getLoginUser() == null) {
            return null;
        }
        ProductInfoExample productInfoExample = new ProductInfoExample();
        ProductInfoExample.Criteria criteria  = productInfoExample.createCriteria();
        String khid =  LoginUserHolder.getLoginUser().getKhid();
        criteria.andKhidEqualTo(khid);
        if(StringUtils.isNotBlank(labelid)){
            criteria.andBrandIdEqualTo(Long.valueOf(labelid));
        }

        productInfoExample.setOrderByClause(getOrderColumnName(request));
        PageBean pager = productInfoService.selectPageBean(productInfoExample, pageQueryBean);
        return pager;
    }

    @RequestMapping("detailList")
    public String classDetailList(String id ,String name,Model model){
        logger.debug("加载类详情：id={}，name={}",id,name);
        model.addAttribute("id", id);
        model.addAttribute("name", name);
        return "manage/product/class/classDetailList";
    }

    @RequestMapping("queryGoodsByPid/{id}")
    public @ResponseBody Object queryGoodsByPid(@PathVariable long id){
        GoodsInfoExample goodsInfoExample = new GoodsInfoExample();
        goodsInfoExample.createCriteria().andProductIdEqualTo(id);
        List<GoodsInfo>goodsInfoList =  goodsService.selectByExampleWithBLOBs(goodsInfoExample);
        logger.debug("货物详情：{}", goodsInfoList);
        logger.debug("货物详情：{}", goodsInfoList.get(0).getSpecArray());
        return goodsInfoList;
    }


    protected void beforeToEdit(Model e, ModelMap modelMap) {

    }


    /*@RequestMapping("productDescUpload")
    @ResponseBody
    public String productDescUpload(){
        logger.debug("产品描述上传。。。");
        String rootPath = request.getRealPath( "/" );
        return  new ActionEnter( request, rootPath ).exec() ;
    }*/
}
