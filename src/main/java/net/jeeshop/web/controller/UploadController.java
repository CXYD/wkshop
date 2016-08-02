package net.jeeshop.web.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: zhaodh
 * Date: 16/6/1
 * Time: 19:13
 */
@Controller
public class UploadController {

    public static final String STATUS_PARM_IS_EMPTY = "-1";
    public static final String STATUS_OK = "1";
    public static final String STATUS_EXECPTION = "0";
    static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @RequestMapping("upload")
    @ResponseBody
    public Object uploadImg(@RequestParam(value = "head_photo",required = false)MultipartFile imgFile, HttpServletRequest request){
        JSONObject resMap = new JSONObject();
        logger.debug("接收上传文件{}",imgFile);
        String originFileName = UUID.randomUUID().toString().replace("-","")+".png";
        if (imgFile != null) {
            //获取保存的路径，
            String realPath = request.getSession().getServletContext().getRealPath("/static/upload");
            if (imgFile.isEmpty()) {
                // 未选择文件
                resMap.put("status", STATUS_PARM_IS_EMPTY);
            } else{
                // 文件原名称
//                String originFileName = imgFile.getOriginalFilename();
                try {
                    //这里使用Apache的FileUtils方法来进行保存
                    FileUtils.copyInputStreamToFile(imgFile.getInputStream(), new File(realPath, originFileName));
                    resMap.put("status", STATUS_OK);
                    resMap.put("imgurl","/static/upload/"+File.separator+originFileName);
                } catch (IOException e) {
                    System.out.println("文件上传失败");
                    resMap.put("status", STATUS_EXECPTION);
                    logger.error("保存上传的文件出错");
                }
            }

        }
        return "/wkshop/static/upload"+File.separator+originFileName;


    }

    @RequestMapping(value = "imgUpload", method = RequestMethod.POST)
    @ResponseBody
    public Object imgUpload(HttpServletRequest request, MultipartFile imgFile){
        logger.debug("接收上传文件{}", imgFile);
        boolean flag = false;
        //errorMessage：上传失败，则是错误信息；上传成功，则提示成功以及显示文件上传后的地址
        String errorMessage = "";

            String originFileName = UUID.randomUUID().toString().replace("-","")+".png";
            if(imgFile != null){
                String realPath = request.getSession().getServletContext().getRealPath("/static/upload");
                if (imgFile.isEmpty()) {
                    errorMessage = "新增文件失败!";
                    // 未选择文件
                } else{
                    // 文件原名称
//                String originFileName = imgFile.getOriginalFilename();
                    try {
                        //这里使用Apache的FileUtils方法来进行保存
                        FileUtils.copyInputStreamToFile(imgFile.getInputStream(), new File(realPath, originFileName));

                        errorMessage ="/static/upload/"+File.separator+originFileName;
                        flag = true;
                    } catch (IOException e) {
                        System.out.println("文件上传失败");
                        errorMessage = "新增文件失败!";
                        logger.error("保存上传的文件出错");
                    }
                }
            }


        if(flag){
            //上传成功，返回到前台，调用uploadSucced()这个方法
            return "<script>window.parent.uploadSucced('" + errorMessage + "');</script>";
        }
        //上传失败，返回到前台，调用uploadFailed()这个方法
        return "<script>window.parent.uploadFailed('" + errorMessage + "');</script>";
    }


    @RequestMapping(value = "/uploadProductImg")
    @ResponseBody
    public Map upload(MultipartFile file, HttpServletRequest request) {

        System.out.println("开始");
        String path = request.getSession().getServletContext().getRealPath("/static/productImg");//项目中的img文件夹下
//        String fileName = file.getOriginalFilename();
//        String fileName = new Date().getTime()+".jpg";
        String fileName = UUID.randomUUID().toString().replace("-","")+".jpg";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("fileName",fileName);

        System.out.println(fileName);
        System.out.println(path);
        File targetFile = new File(path, fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }

        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(fileName);
        return map;
    }

    /**
     * 上传base64格式图片
     * @param base64_string
     * @param request
     * @return
     */
    @RequestMapping("uploadBASE64IMG")
    @ResponseBody
    public Object uploadIMG(String base64_string,HttpServletRequest request){
        String path = request.getSession().getServletContext().getRealPath("/static/upload/");
//        String fileName = file.getOriginalFilename();
//        String fileName = new Date().getTime()+".jpg";
        JSONObject data = new JSONObject();


        String fileName ="userid_" + UUID.randomUUID().toString().replace("-","")+".jpg";

        String imgFilePath = path +File.separator+ fileName;

        if (base64_string == null) // 图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(base64_string);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            if(b.length/1024>2048){
                // 图片不能大于2M
                data.put("status", 2);
            }else {
                // 生成jpeg图片
                OutputStream out = new FileOutputStream(imgFilePath);
                out.write(b);
                out.flush();
                out.close();

                data.put("status", 1);
                data.put("content", "/static/upload/"+fileName);
                logger.info("上传照片成功");
            }


        } catch (Exception e) {
            data.put("status",0);
            logger.error("转换保存图片错误");
        }

        return data;
    }



}
