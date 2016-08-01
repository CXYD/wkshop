package net.jeeshop.web.controller;

import com.alibaba.fastjson.JSONObject;
import net.jeeshop.web.util.pay.util.FuncUtils;
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

    public static final String WEB_FILE_URL = "/upload/";


    static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @RequestMapping("upload")
    @ResponseBody
    public Object uploadImg(@RequestParam(value = "head_photo",required = false)MultipartFile imgFile, HttpServletRequest request){
        JSONObject resMap = new JSONObject();
        logger.debug("接收上传文件{}", imgFile);
        String originFileName = UUID.randomUUID().toString().replace("-","")+".png";
        String dir=new File(request.getSession().getServletContext().getRealPath("/")).getParent();
        logger.debug("根目录：{}",dir);
        String uploadPath = dir + "/upload/";
        if (imgFile != null) {
            //获取保存的路径，
            if (imgFile.isEmpty()) {
                // 未选择文件
                resMap.put("status", STATUS_PARM_IS_EMPTY);
            } else{
                try {
                    //这里使用Apache的FileUtils方法来进行保存
                    FileUtils.copyInputStreamToFile(imgFile.getInputStream(), new File(uploadPath, originFileName));
                    resMap.put("status", STATUS_OK);
                    resMap.put("imgurl","/upload/"+originFileName);
                } catch (IOException e) {
                    resMap.put("status", STATUS_EXECPTION);
                    logger.error("保存上传的文件出错");
                }
            }

        }
        return "/upload/"+File.separator+originFileName;


    }

    /**
     * 页面配置
     * @param request
     * @param imgFile
     * @return
     */
    @RequestMapping(value = "imgUpload", method = RequestMethod.POST)
    @ResponseBody
    public Object imgUpload(HttpServletRequest request, MultipartFile imgFile){
        logger.debug("接收上传文件{}", imgFile);
        boolean flag = false;
        //errorMessage：上传失败，则是错误信息；上传成功，则提示成功以及显示文件上传后的地址
        String errorMessage = "";

            String originFileName = UUID.randomUUID().toString().replace("-","")+".jpg";
            if(imgFile != null){
                // TODO 测试
//                String realPath = request.getSession().getServletContext().getRealPath("/static");
                String realPath=new File(request.getSession().getServletContext().getRealPath("/")).getParent();
                logger.debug("根目录：{}",realPath);
                String uploadPath = realPath + "/upload/";
                if (imgFile.isEmpty()) {
                    errorMessage = "新增文件失败!";
                    // 未选择文件
                } else{
                    try {
                        //这里使用Apache的FileUtils方法来进行保存
                        FileUtils.copyInputStreamToFile(imgFile.getInputStream(), new File(uploadPath, originFileName));

                        // TODO 测试
//                        errorMessage="/static/upload/"+originFileName;
                        errorMessage ="/upload/"+originFileName;
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

    /**
     * 上传产品图片
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "/uploadProductImg")
    @ResponseBody
    public Map upload(MultipartFile file, HttpServletRequest request) {

//        String path = request.getSession().getServletContext().getRealPath("/static/productImg");//项目中的img文件夹下
        String webAppPath=new File(request.getSession().getServletContext().getRealPath("/")).getParent();
        String uploadPath = webAppPath +"/upload/productImg";
        logger.debug("webapp路径={}", uploadPath);
        String fileName = UUID.randomUUID().toString().replace("-","")+".jpg";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("fileName", "/upload/productImg/"+fileName);

        File targetFile = new File(uploadPath, fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }

        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

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
//        String path = request.getSession().getServletContext().getRealPath("/static/upload/");
        String dir=new File(request.getSession().getServletContext().getRealPath("/")).getParent();
        String uploadPath = dir + "/upload/";
        JSONObject data = new JSONObject();


        String fileName ="userid_" + UUID.randomUUID().toString().replace("-","")+".jpg";

        String imgFilePath = uploadPath +File.separator+ fileName;

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
                logger.error("文件大于2M了{}",b.length/1024);
                // 图片不能大于2M
                data.put("status", 2);
            }else {
                // 生成jpeg图片
                OutputStream out = new FileOutputStream(imgFilePath);
                out.write(b);
                out.flush();
                out.close();

                data.put("status", 1);
                data.put("content", "/upload/"+fileName);
                logger.info("上传照片成功{}",fileName);
            }


        } catch (Exception e) {
            data.put("status",0);
            logger.error("转换保存图片错误");
        }

        return data;
    }





    /**
     * 上传身份证正面
     * @param request
     * @return
     */
    @RequestMapping("/uploadIDA")
    @ResponseBody
    public Object uploadIDA( HttpServletRequest request) {

        String photoBase64 = request.getParameter("file_data");


//        String uploadPath = request.getSession().getServletContext().getRealPath("/static/upload/");
        String dir = new File(request.getSession().getServletContext().getRealPath("/")).getParent();
        String uploadPath = dir + "/upload/";
        JSONObject data = new JSONObject();


        String fileName = "userid_" + UUID.randomUUID().toString().replace("-", "") + ".jpg";

        int state =  decodeBase64ToImage(photoBase64,uploadPath,fileName);
        logger.debug("状态:{}",state);
        data.put("errorCode", state);
        data.put("img1",true);
        data.put("img1_url",WEB_FILE_URL+fileName);

        return data;

    }


    /**
     * 上传身份证背面
     * @param request
     * @return
     */
    @RequestMapping("/uploadIDB")
    @ResponseBody
    public Object uploadIDB( HttpServletRequest request) {

        String photoBase64 = request.getParameter("file_data");

//        String uploadPath = request.getSession().getServletContext().getRealPath("/static/upload/");
        String dir = new File(request.getSession().getServletContext().getRealPath("/")).getParent();
        String uploadPath = dir + "/upload/";
        JSONObject data = new JSONObject();


        String fileName = "userid_" + UUID.randomUUID().toString().replace("-", "") + ".jpg";

        int state =  decodeBase64ToImage(photoBase64,uploadPath,fileName);
        logger.debug("状态:{}",state);
        data.put("errorCode", state);
        data.put("img2",true);
        data.put("img2_url",WEB_FILE_URL+fileName);

        return data;

    }

    /**
     * 上传手持照片
     * @param request
     * @return
     */
    @RequestMapping("/uploadIDC")
    @ResponseBody
    public Object uploadIDC( HttpServletRequest request) {

        String photoBase64 = request.getParameter("file_data");

//        String uploadPath = request.getSession().getServletContext().getRealPath("/static/upload/");
        String dir = new File(request.getSession().getServletContext().getRealPath("/")).getParent();
        String uploadPath = dir + "/upload/";
        JSONObject data = new JSONObject();


        String fileName = "userid_" + UUID.randomUUID().toString().replace("-", "") + ".jpg";

        int state =  decodeBase64ToImage(photoBase64,uploadPath,fileName);
        logger.debug("状态:{}",state);
        data.put("errorCode", state);
        data.put("img3",true);
        data.put("img3_url",WEB_FILE_URL+fileName);

        return data;

    }




    public static int decodeBase64ToImage(String base64, String path,String imgName) {
        BASE64Decoder decoder = new BASE64Decoder();
        try {

            String fileExt = FuncUtils.substringBetween(base64,"data:image/", ";");
            if(!"jpg".equals(fileExt) && !"jpeg".equals(fileExt) ){
                    logger.debug("后缀：{}",fileExt);
                    return 2;
            }
            String base64ImgData  = FuncUtils.substringBetween(base64, "base64,", "");

//            logger.debug("数据：{}",base64ImgData);
            byte[] decoderBytes = decoder.decodeBuffer(base64ImgData);
            if (decoderBytes.length / 1024 > 3072) {
                logger.error("文件大于3M了{}", decoderBytes.length / 1024);
                // 图片不能大于2M
               return 1;
            } else {
                FileOutputStream write = new FileOutputStream(new File(path + File.separator + imgName));
                write.write(decoderBytes);
                write.close();
                return 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return 3;
        }
    }



}
