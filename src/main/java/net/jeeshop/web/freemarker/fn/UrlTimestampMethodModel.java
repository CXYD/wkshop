package net.jeeshop.web.freemarker.fn;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: zhaodh
 * Date: 16/7/8
 * Time: 14:10
 */
@Component("fn.UrlTimestampMethodModel")
public class UrlTimestampMethodModel  implements TemplateDirectiveModel {

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
            throws TemplateException, IOException {
        // 当前时间毫秒数 + 四位随机
        String strTimestamp = "";
        try {
            strTimestamp = String.valueOf(System.currentTimeMillis()) + getRandom(1000, 9999);
        } catch (Exception ex) {
        }
        env.getOut().write(strTimestamp);
    }

    private int getRandom(int min, int max) {
        Random random = new Random();
        return Integer.parseInt(String.valueOf(random.nextInt(max) % (max - min + 1) + min));
    }
}