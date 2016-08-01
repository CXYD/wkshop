package net.jeeshop.web.controller.front.xufei;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: zhaodh
 * Date: 16/7/25
 * Time: 14:11
 */

@Controller
public class XFController {


    /**
     * 跳转到身份校验
     * @return
     */
    @RequestMapping("checkADInfo")
    public String checkADUserInfo(){

        //判断是否有维挽数据

        boolean flag = false;

        if(flag){
            //1 有数据
                //直通车认证
            //认证通过

            //认证不通过
            return "front/xufei/checkADInfo";
        }else {
            //2 没有数据
            return "front/xufei/checkADInfo";
        }

    }
}
