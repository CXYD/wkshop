package net.jeeshop.web.controller.front;

import net.jeeshop.biz.member.model.Member;
import net.jeeshop.web.controller.BaseController;
import net.jeeshop.web.util.LoginUserHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

/**
 * Created by dylan on 15-3-17.
 */
@Controller
public abstract class FrontBaseController extends BaseController {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    protected static final String page_toLogin = "/member/login";
    protected static final String page_toLoginRedirect = "redirect:/member/login";

    /**
     * 当前登录用户
     *
     * @return
     */
    protected Member getLoginMember() {
        return LoginUserHolder.getLoginMember();
    }


}
