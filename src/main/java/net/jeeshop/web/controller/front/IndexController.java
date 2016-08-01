package net.jeeshop.web.controller.front;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author dylan
 * @date 16/3/2 21:55
 * Email: dinguangx@163.com
 */
@Controller
@RequestMapping("/")
public class IndexController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping({"/", "/index"})
    public String index(ModelMap model) {
        return "redirect:/manage/user/login";
    }




}
