package net.jeeshop.web.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import net.jeeshop.core.util.AddressUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: zhaodh
 * Date: 16/5/18
 * Time: 16:18
 */
@Controller
public class QRCodeController {

    private final static String HOSTURL = "http://wx.wokuan.cn:8181/wkshop/";

    static final Logger logger = org.slf4j.LoggerFactory.getLogger(QRCodeController.class);

    private final static String TYPE_PRODUCT = "1";
    private final static String TYPE_CLASS = "2";
    private final static String TYPE_COSTOM = "3";

    @RequestMapping("getQrCode")
    public void getRqCode(HttpServletResponse resp, String id,String type) throws IOException {

        logger.info("获取商城二维码：id={},type={}",id,type);

//        logger.info("当前服务器ip:{}", AddressUtils.getLocalIP());
//        logger.info("当前服务器ip:{}", AddressUtils.getServerIp());
        String link = HOSTURL;

        if(TYPE_PRODUCT.equals(type)){
            link = link + "v1/goods/"+id;
        }else if(TYPE_CLASS.equals(type)){
            link = link + "tag/"+id;
        }else if(TYPE_COSTOM.equals(type)){
            link = link + "webapp/"+id;
        }

        ServletOutputStream stream = null;
        try {

            int width = 225;//图片的宽度
            int height = 225;//高度
            stream = resp.getOutputStream();
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix m = writer.encode(link, BarcodeFormat.QR_CODE, height, width);
            MatrixToImageWriter.writeToStream(m, "png", stream);
        } catch (WriterException e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }
    }

    @RequestMapping("getPageQrCode")
    public void getPageRqCode(HttpServletResponse resp, String productid) throws IOException {

        logger.info("获取商品页二维码："+productid);
        logger.info("当前服务器ip:{}", AddressUtils.getLocalIP());

        if (HOSTURL != null && !"".equals(HOSTURL)) {
            ServletOutputStream stream = null;
            try {

                int width = 225;//图片的宽度
                int height = 225;//高度
                stream = resp.getOutputStream();
                QRCodeWriter writer = new QRCodeWriter();
                BitMatrix m = writer.encode(HOSTURL, BarcodeFormat.QR_CODE, height, width);
                MatrixToImageWriter.writeToStream(m, "png", stream);
            } catch (WriterException e) {
                e.printStackTrace();
            } finally {
                if (stream != null) {
                    stream.flush();
                    stream.close();
                }
            }
        }
    }

}
