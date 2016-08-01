package net.jeeshop.web.freemarker.fn;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import net.jeeshop.core.SystemManager;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 获取系统参数的配置
 * Created by dylan on 15-1-15.
 */
@Component("fn.systemSetting")
public class SystemSettingGetter implements TemplateMethodModelEx {
    @Override
    public Object exec(List arguments) throws TemplateModelException {
        return SystemManager.getInstance().getSystemSetting();
    }
}
