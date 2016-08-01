package net.jeeshop.web.freemarker.fn;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import net.jeeshop.web.util.LoginUserHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 获取当前登录的用户
 * Created by dylan on 15-1-15.
 */
@Component("fn.currentMember")
public class CurrentMemberGetter implements TemplateMethodModelEx {
    @Override
    public Object exec(List arguments) throws TemplateModelException {
        return LoginUserHolder.getLoginMember();
    }
}
