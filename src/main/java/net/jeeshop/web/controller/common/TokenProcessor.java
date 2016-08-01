package net.jeeshop.web.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created with IntelliJ IDEA.
 * User: zhaodh
 * Date: 16/7/12
 * Time: 16:22
 */
public class TokenProcessor {
    static final String TOKEN_KEY  = "cn.wokan";

    private static TokenProcessor instance = new TokenProcessor();

    public static TokenProcessor getInstance(){
        return  instance;
    }

    /**
     * 最近一次生成令牌值的时间戳
     */
    private  long previous;

    public synchronized boolean isTokenValid(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session==null){
            return false;
        }

        String saved = (String) session.getAttribute(TOKEN_KEY);

        resetToken(request);

        String token = request.getParameter(TOKEN_KEY);
        if(token == null){
            return false;
        }

        return saved.equals(token);
    }

    /**
     * 重置
     * @param request
     */
    public synchronized void resetToken(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session==null){
            return ;
        }

        session.removeAttribute(TOKEN_KEY);
    }

    public synchronized void saveToken(HttpServletRequest request){
        HttpSession session = request.getSession();
        String token = generateToken(request);
        if(token != null){
            session.setAttribute(TOKEN_KEY,token);
        }
    }

    public synchronized String generateToken(HttpServletRequest request){
        HttpSession session = request.getSession();
        try {
        byte[] id = session.getId().getBytes();
        long current = System.currentTimeMillis();
        if(current == previous){
            current ++ ;
        }
        previous = current;

        byte[] now = new Long(current).toString().getBytes();
        MessageDigest md = null;
        md = MessageDigest.getInstance("MD5");

        md.update(id);
        md.update(now);

        return toHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String toHex(byte buffer[]){
        StringBuffer sb = new StringBuffer(buffer.length*2);
        for (int i = 0; i <buffer.length; i++) {
            sb.append(Character.forDigit((buffer[i] & 0xf0)>>4,16));
            sb.append(Character.forDigit(buffer[i] & 0xf0,16));

        }
        return sb.toString();
    }


    public synchronized String getToken(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(null == session){
            return null;
        }

        String token = (String) session.getAttribute(TOKEN_KEY);
        if(null == token){
            token = generateToken(request);
            if(token != null){
                session.setAttribute(TOKEN_KEY,token);
                return token;
            }else {
                return null;
            }
        }else
            return token;
    }

}
