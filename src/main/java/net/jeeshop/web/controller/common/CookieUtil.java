package net.jeeshop.web.controller.common;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import net.jeeshop.biz.member.model.Member;
import net.jeeshop.core.util.MD5;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * Created by  pengdong on 2016/7/11.
 */
public class CookieUtil {
    //保存cookie时的cookieName
    private final static String cookieDomainName = "shop";
    //加密cookie时的网站自定码
    public final static String webKey = "!@#$%";
    //设置cookie有效期是两个星期，根据需要自定义
    private final static long cookieMaxAge = 60 * 60 * 24 * 7 * 2;

    //保存Cookie到客户端-------------------------------------------------------------------------
    //在CheckLogonServlet.java中被调用
    //传递进来的user对象中封装了在登陆时填写的用户名与密码
    public static void saveCookie(Member member, HttpServletResponse response) {
        //cookie的有效期
        long validTime = System.currentTimeMillis() + (cookieMaxAge * 5000);
        //MD5加密用户详细信息
        String cookieValueWithMd5 = MD5.md5(member.getUsername() + ":" + member.getPassword()
                + ":" + validTime + ":" + webKey);
        //将要被保存的完整的Cookie值
        String cookieValue = member.getUsername() + ":" + validTime + ":" + cookieValueWithMd5;
        //再一次对Cookie的值进行BASE64编码
        String cookieValueBase64 = new String(Base64.encode(cookieValue.getBytes()));
        //开始保存Cookie
        Cookie cookie = new Cookie(cookieDomainName, cookieValueBase64);
        //存两年(这个值应该大于或等于validTime)
        cookie.setMaxAge(60 * 60 * 24 * 365 * 2);
        //cookie有效路径是网站根目录
        cookie.setPath("/");
        //向客户端写入
        response.addCookie(cookie);
    }

    public static String readCookie(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        //根据cookieName取cookieValue
        Cookie cookies[] = request.getCookies();
        String cookieValue = null;
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++)
                if (cookieDomainName.equals(cookies[i].getName())) {
                    cookieValue = cookies[i].getValue();
                    break;
                }
        }
        //如果cookieValue为空,返回,
        if (cookieValue == null) {
            return null;
        }
        //如果cookieValue不为空,才执行下面的代码
        //先得到的CookieValue进行Base64解码
        String cookieValueAfterDecode = new String(Base64.decode(cookieValue), "utf-8");
        //对解码后的值进行分拆,得到一个数组,如果数组长度不为3,就是非法登陆

        return cookieValueAfterDecode;
    }
    public static void clearCookie(HttpServletResponse response,HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for(int i=0;i<cookies.length;i++)
        {
            Cookie cookie = new Cookie(cookies[i].getName(), null);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
    }
}
