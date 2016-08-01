package net.jeeshop.web.controller.manage.data;

import com.alibaba.fastjson.JSONObject;
import net.jeeshop.biz.base.bean.PageBean;
import net.jeeshop.biz.base.bean.PageQueryBean;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.data.model.NuberInfo;
import net.jeeshop.biz.data.model.NuberInfoExample;
import net.jeeshop.biz.data.service.NumSerivce;
import net.jeeshop.core.exception.JShopException;
import net.jeeshop.core.util.CommUtils;
import net.jeeshop.core.util.ExcelUtil;
import net.jeeshop.web.controller.manage.ManageBaseController;
import net.jeeshop.web.util.LoginUserHolder;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: zhaodh
 * Date: 16/6/13
 * Time: 18:10
 */

@Controller
@RequestMapping("manage/data/number")
public class NumberController extends ManageBaseController {

    public static final String STATUS_PARM_IS_EMPTY = "-1";
    public static final String STATUS_OK = "1";
    public static final String STATUS_EXECPTION = "0";

    NumberController(){
        super.page_toList="/manage/data/numList";
    }

    @Autowired
    private NumSerivce numSerivce;

    @Override
    public BaseService getService() {
        return numSerivce;
    }


    @RequestMapping("loadData")
    @ResponseBody
    public PageBean<NuberInfo> loadData(NuberInfo nuberInfo, PageQueryBean pageQueryBean,String status,String pici){
        if (LoginUserHolder.getLoginUser() == null) {
            return null;
        }
        String khid =  LoginUserHolder.getLoginUser().getKhid();
       NuberInfoExample nuberInfoExample = new NuberInfoExample();
        NuberInfoExample.Criteria criteria = nuberInfoExample.createCriteria();
        if(StringUtils.isNotBlank(status)){
            criteria.andStateEqualTo(status);
        }

        if(StringUtils.isNotBlank(pici)){
            criteria.andNumberidEqualTo(Integer.parseInt(pici));
        }

        criteria.andKhidEqualTo(khid);
       return numSerivce.selectPageList(nuberInfoExample,pageQueryBean);
    }



    /**
     * ajax验证输入的字符的唯一性
     *
     * @return
     * @throws IOException
     */
    @RequestMapping("unique")
    @ResponseBody
    public String unique( String name, HttpServletResponse response) throws IOException {
        logger.debug("验证输入的字符的唯一性:" + name);
        String khid =  LoginUserHolder.getLoginUser().getKhid();
        response.setCharacterEncoding("utf-8");
        if (StringUtils.isNotBlank(name)) {//验证昵称是否被占用
            logger.debug("验证昵称是否被占用:" + name);
            NuberInfoExample example = new NuberInfoExample();
            example.createCriteria().andBatchnameEqualTo(name).andKhidEqualTo(khid);

            try {
                NuberInfo nuberInfo = numSerivce.selectUniqueByExample(example);

                if (nuberInfo == null) {
                    //数据库中不存在此编码
                   return "ok";
                } else {
                   return "error";
                }
            }catch (JShopException e){
               return "error";
            }
        }
        return "";
    }


    @RequestMapping("selectKeyValue")
    @ResponseBody
    public Object selectKeyValue(String khid){
        if (LoginUserHolder.getLoginUser() == null) {
            return null;
        }
         khid =  LoginUserHolder.getLoginUser().getKhid();
        return  numSerivce.selectKeyValue(khid);
    }

    @RequestMapping("deletePici")
    public @ResponseBody Object deletePici(Integer pici){
        return numSerivce.delByPici(pici);
    }


    /**
     * 导入号码
     * @param filePath
     * @param request
     * @param name
     * @param type
     */
    @RequestMapping("importFile")
    @ResponseBody
    public void importFile(@RequestParam(value = "filePath",required = false)MultipartFile filePath,
                           HttpServletRequest request,
                           String name,String type){


        JSONObject resMap = new JSONObject();
        logger.debug("接收上传文件{}", filePath);
        logger.debug("导入号码批次名={},type={}",name,type);
        String originFileName = UUID.randomUUID().toString().replace("-","")+".xls";
        Long pici = System.currentTimeMillis()/1000;

        String khid =  LoginUserHolder.getLoginUser().getKhid();

        InputStream in;

        if (filePath != null) {
            //获取保存的路径，
            String realPath = request.getSession().getServletContext()
                    .getRealPath("/static/upload");
            if (filePath.isEmpty()) {
                // 未选择文件
                resMap.put("status", STATUS_PARM_IS_EMPTY);
            } else{
                // 文件原名称
//                String originFileName = imgFile.getOriginalFilename();
                try {
                    //这里使用Apache的FileUtils方法来进行保存
                    FileUtils.copyInputStreamToFile(filePath.getInputStream(), new File(realPath, originFileName));

                    // 获取前台exce的输入流
                    List<Row> list =   new ExcelUtil().readExcel(new HSSFWorkbook(filePath.getInputStream()));
                    logger.info("导入文件excel行数："+list.size());
//                    Set<String> numberSet=new HashSet();
                    List<NuberInfo> nuberInfoList = new ArrayList<NuberInfo>();
                    for (Row rowObje:list){

                        NuberInfo nuberInfo = new NuberInfo();
                        String phone  = "";
                        try {
                            phone = new DecimalFormat("#").format(rowObje.getCell(0).getNumericCellValue());
                        }catch (Exception e){
                            logger.error("导入电话号码不正确");
                            phone = rowObje.getCell(0).getStringCellValue();
                        }
                        boolean flag = CommUtils.isMobile(phone);

                        if(!flag){
                            logger.info("此号码不是手机号：" + phone);
                        }else{
//                            numberSet.add(phone);
                            nuberInfo.setCreateTime(new Date());
                            nuberInfo.setBatchname(name);
                            nuberInfo.setNumtype(type);
                            nuberInfo.setPhonenum(phone);
                            nuberInfo.setNumberid(pici.intValue());
                            nuberInfo.setState("0");
                            nuberInfo.setKhid(khid);
                            nuberInfoList.add(nuberInfo);
                        }
                        logger.info("导入excel正确号码条数："+nuberInfoList.size());

                    }

                    int i = numSerivce.batchInsertNumber(nuberInfoList);
                    if(i>0){
                        resMap.put("status", STATUS_OK);
                        logger.info("导入成功");
                    }else{
                        resMap.put("status", STATUS_EXECPTION);
                        logger.info("导入失败");
                    }
                } catch (IOException e) {
                    System.out.println("文件上传失败");
                    resMap.put("status", STATUS_EXECPTION);
                    logger.error("保存上传的文件出错");
                }
            }

        }


    }
}
