package net.jeeshop.web.controller.front.cms;

import net.jeeshop.biz.base.bean.PageBean;
import net.jeeshop.biz.base.bean.PageQueryBean;
import net.jeeshop.biz.cms.model.Article;
import net.jeeshop.biz.cms.model.Notice;
import net.jeeshop.biz.cms.model.NoticeExample;
import net.jeeshop.biz.cms.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Dylan.Ding
 * @date 2016-04-04 17-57
 */
@Controller("front.noticeController")
@RequestMapping("cms/notice")
public class NoticeController {

    @Autowired
    NoticeService noticeService;
    @RequestMapping({"index", "index.html"})
    public String index(ModelMap modelMap) {
        PageBean<Notice> pager = noticeService.selectPageList(new NoticeExample(), new PageQueryBean());
        modelMap.addAttribute("pager", pager);
        return "cms/noticeList";
    }


    @RequestMapping({"{noticeId}","{noticeId}.html"})
    public String noticePage(ModelMap modelMap, @PathVariable("noticeId")Long noticeId) {
        Notice notice = noticeService.selectById(noticeId);
        if(notice == null) {
            return "redirect:index";
        }
        //阅读数加1,目前使用即时更新
        noticeService.updateReadCount(notice);
        modelMap.put("notice", notice);
        return "cms/notice";
    }
}
