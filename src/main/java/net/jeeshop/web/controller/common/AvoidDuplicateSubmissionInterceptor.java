package net.jeeshop.web.controller.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * 防止重复提交过滤器

 * Created with IntelliJ IDEA.
 * User: zhaodh
 * Date: 16/7/12
 * Time: 16:17
 */
public class AvoidDuplicateSubmissionInterceptor  extends HandlerInterceptorAdapter {
    protected Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();

            AvoidDuplicateSubmission annotation = method.getAnnotation(AvoidDuplicateSubmission.class);
            if (annotation != null) {
                boolean needSaveSession = annotation.needSaveToken();
                if (needSaveSession) {
                    logger.debug("生成token");
                    request.getSession(true).setAttribute("token", UUID.randomUUID().toString().replace("-",""));
                }

                boolean needRemoveSession = annotation.needRemoveToken();
                if (needRemoveSession) {
                    if (isRepeatSubmit(request)) {
                        logger.warn("不能重复提交表单,[user:" + ",url:"
                                + request.getServletPath() + "]");
                        return false;
                    }
                    request.getSession(true).removeAttribute("token");
                }
            }
            return true;
         }else{
            return super.preHandle(request, response, handler);
         }

    }

    private boolean isRepeatSubmit(HttpServletRequest request) {
        String serverToken = (String) request.getSession(true).getAttribute("token");
        if (serverToken == null) {
            return true;
        }
        String clinetToken = request.getParameter("token");
        if (clinetToken == null) {
            return true;
        }
        if (!serverToken.equals(clinetToken)) {
            return true;
        }
        return false;
    }


}
