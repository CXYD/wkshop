package net.jeeshop.web.controller.common;

import net.jeeshop.core.util.PinYinUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author dylan
 * @date 16/4/19 22:20
 * Email: dinguangx@163.com
 */
@Controller("common.commonController")
@RequestMapping("common")
public class CommonController {
    @RequestMapping("generatePinyinCode")
    @ResponseBody
    public String generatePinyinCode(@RequestParam("name") String name) {

        if (StringUtils.isBlank(name)) {
            return "";
        }
        String pinyin = PinYinUtil.getPingYin(name.trim());
        return pinyin;
    }
}
