package net.jeeshop.core.util;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.util.Iterator;

import javax.imageio.*;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

import com.sun.imageio.plugins.jpeg.JPEGImageWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 等比缩放图片、裁剪图片的 图片处理工具类
 *
 * @author huangf
 */
public class ImageUtils {
    private static final Logger logger = LoggerFactory.getLogger(ImageUtils.class);
    // 压缩质量 百分比
    private static final float JPEGcompression = 0.7f;
    // ===源图片路径名称如:c:\1.jpg
    private String srcpath;
    // ===剪切图片存放路径名称.如:c:\2.jpg
    private String subpath;
    // ===剪切点x坐标
    private int x;
    private int y;
    // ===剪切点宽度
    private int width;
    private int height;

    public String getSrcpath() {
        return srcpath;
    }

    public void setSrcpath(String srcpath) {
        this.srcpath = srcpath;
    }

    public String getSubpath() {
        return subpath;
    }

    public void setSubpath(String subpath) {
        this.subpath = subpath;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public ImageUtils() {
    }

    /**
     * 对图片裁剪，并把裁剪完的新图片保存
     **/
    public void cut() throws IOException {
        FileInputStream is = null;
        ImageInputStream iis = null;
        try {
            // 读取图片文件
            is = new FileInputStream(srcpath);
            /*
             * 返回包含所有当前已注册 ImageReader 的 Iterator，这些 ImageReader 声称能够解码指定格式。
			 * 参数：formatName - 包含非正式格式名称 . （例如 "jpeg" 或 "tiff"）等 。
			 */
            Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName("jpg");
            ImageReader reader = it.next();
            // 获取图片流
            iis = ImageIO.createImageInputStream(is);
			/*
			 * <p>iis:读取源.true:只向前搜索 </p>.将它标记为 ‘只向前搜索’。
			 * 此设置意味着包含在输入源中的图像将只按顺序读取，可能允许 reader 避免缓存包含与以前已经读取的图像关联的数据的那些输入部分。
			 */
            reader.setInput(iis, true);
			/*
			 * <p>描述如何对流进行解码的类<p>.用于指定如何在输入时从 Java Image I/O
			 * 框架的上下文中的流转换一幅图像或一组图像。用于特定图像格式的插件 将从其 ImageReader 实现的
			 * getDefaultReadParam 方法中返回 ImageReadParam 的实例。
			 */
            ImageReadParam param = reader.getDefaultReadParam();
			/*
			 * 图片裁剪区域。Rectangle 指定了坐标空间中的一个区域，通过 Rectangle 对象
			 * 的左上顶点的坐标（x，y）、宽度和高度可以定义这个区域。
			 */
            logger.error("x=" + x + ",y=" + y + ",width=" + width + ",height=" + height);
            Rectangle rect = new Rectangle(x, y, width, height);
            // 提供一个 BufferedImage，将其用作解码像素数据的目标。
            param.setSourceRegion(rect);
			/*
			 * 使用所提供的 ImageReadParam 读取通过索引 imageIndex 指定的对象，并将 它作为一个完整的
			 * BufferedImage 返回。
			 */
            BufferedImage bi = reader.read(0, param);
            // 保存新图片
            ImageIO.write(bi, "jpg", new File(subpath));
        } finally {
            if (is != null)
                is.close();
            if (iis != null)
                iis.close();
        }
    }

    /*
     * Java文件操作 获取文件扩展名
     */
    private static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    public static void reduceImg(String imgsrc, String imgdist, int widthdist,
                                 int heightdist) {
        try {
            File srcfile = new File(imgsrc);
            if (!srcfile.exists()) {
                return;
            }
            BufferedImage src = ImageIO.read(srcfile);

            BufferedImage tag = new BufferedImage(widthdist, heightdist,
                    BufferedImage.TYPE_INT_RGB);

            tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist, Image.SCALE_SMOOTH), 0, 0, null);
//	        tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist,  Image.SCALE_AREA_AVERAGING), 0, 0,  null);

            saveImage(new File(imgdist), src);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 损失小一些
     * 创建图片缩略图(等比缩放)
     *
     * @param src   源图片文件完整路径
     * @param dist  目标图片文件完整路径
     * @param width 目标图片的最大宽高
     */
    public static boolean ratioZoom(File src, File dist, double width) {
        try {
            if (!src.exists() ) {
                throw new NullPointerException("文件不存在");
            } else if (src.isDirectory()) {
                throw new IllegalArgumentException("源文件不能是文件夹");
            }

            BufferedImage image = ImageIO.read(src);

            //获得缩放的比例
            double ratio = 0.0;
            if (image.getHeight() > 100 || image.getWidth() > 100) {
                if (image.getHeight() > image.getWidth()) {
                    ratio = width / image.getHeight();
                } else {
                    ratio = width / image.getWidth();
                }
            }
            //计算新的图面宽度和高度
            int newWidth = (int) (image.getWidth() * ratio);
            int newHeight = (int) (image.getHeight() * ratio);

            BufferedImage bfImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            bfImage.getGraphics().drawImage(image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);

            saveImage(dist, bfImage);
            return true;
        } catch (Exception e) {
            System.out.println("创建缩略图发生异常" + e.getMessage());
        }
        return false;
    }

    private static void saveImage(File file, RenderedImage image) throws IOException {
        FileOutputStream os = new FileOutputStream(file);
        String type = getExtensionName(file.getName());

        ImageWriter imageWriter = ImageIO.getImageWritersBySuffix(type).next();
        ImageOutputStream ios = ImageIO.createImageOutputStream(os);
        imageWriter.setOutput(ios);
        // and metadata
        IIOMetadata imageMetaData = imageWriter.getDefaultImageMetadata(
                new ImageTypeSpecifier(image), null);

        if (imageWriter instanceof JPEGImageWriter) {
            // new Compression
            ImageWriteParam imageParams = imageWriter.getDefaultWriteParam();
            imageParams.setCompressionMode(JPEGImageWriteParam.MODE_EXPLICIT);
            imageParams.setCompressionQuality(JPEGcompression);
        }
        // new Write and clean up
        imageWriter.write(imageMetaData,
                new IIOImage(image, null, null), null);
        ios.close();
        imageWriter.dispose();
        os.close();
    }

}
