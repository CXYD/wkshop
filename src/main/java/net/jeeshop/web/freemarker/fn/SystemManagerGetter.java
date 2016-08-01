package net.jeeshop.web.freemarker.fn;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import net.jeeshop.core.SystemManager;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 获取系统管理
 * Created by dylan on 15-1-15.
 */
@Component("fn.systemManager")
public class SystemManagerGetter implements TemplateMethodModelEx {
    @Override
    public Object exec(List arguments) throws TemplateModelException {
        return SystemManager.getInstance();
    }
}
