package net.jeeshop.core.servlet;

import net.jeeshop.core.ManageContainer;
import org.slf4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: zhaodh
 * Date: 16/7/26
 * Time: 17:19
 */
public class AuthenticationFilter  implements Filter{

    protected Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String url = httpRequest.getServletPath();
        logger.debug("过滤{}",url);
        if(url.indexOf("manage")>-1 && url.indexOf("/login")<0){
            HttpSession session = httpRequest.getSession(true);

            Object obj =  session.getAttribute(ManageContainer.manage_session_user_info);

            logger.debug("用户信息：{}",obj);
            if(obj==null){
                logger.debug("回到登陆页面");
                ((HttpServletResponse)servletResponse).sendRedirect(httpRequest.getContextPath() +"/manage/user/login");
                return ;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
