package net.jeeshop.web.controller.manage.order;

import net.jeeshop.biz.base.bean.PageBean;
import net.jeeshop.biz.base.bean.PageQueryBean;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.order.model.OrderComment;
import net.jeeshop.biz.order.model.OrderCommentExample;
import net.jeeshop.biz.order.service.OrderCommentService;
import net.jeeshop.web.controller.manage.ManageBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 评价管理
 * Created by pengdong on 2016/6/5.
 */
@Controller
@RequestMapping("manage/order/comment")
public class CommentController extends ManageBaseController<OrderComment,OrderCommentExample> {
    private static final long serialVersionUID = 1L;
    @Autowired
    OrderCommentService orderCommentService;
    private static final String page_toList = "/manage/order/assessList";

    private CommentController() {
        super.page_toList = page_toList;
    }
    @Override
    public BaseService<OrderComment, OrderCommentExample> getService() {
        return orderCommentService;
    }

    /**
     * 查询
     * @param queryParams
     * @param pageQueryBean
     * @return
     */
    @RequestMapping("loadData")
    @ResponseBody
    public PageBean<OrderComment> loadData(OrderComment queryParams, PageQueryBean pageQueryBean) {
        OrderCommentExample example = new OrderCommentExample();
        PageBean  pager = orderCommentService.selectPageList(example, pageQueryBean);
        return pager;
    }

    @RequestMapping("delByID")
    @ResponseBody
    public String delByID(Long id){
        int rows = orderCommentService.deleteById(id);
        if(rows>0){
            logger.debug("删除成功!");
            return "success";
        }else{
            logger.debug("删除失败!");
            return "fail";
        }

    }
    @RequestMapping("updateById")
    @ResponseBody
    public String updateById(Long id,String status){
        /*if(id>0){
            OrderComment orderComment = orderCommentService.selectById(id);
            orderComment.setBalstatus(Integer.parseInt(status));
            int i = orderCommentService.update(balance);
            if(i>0){
                return "success";
            }
        }*/
        return "fail";
    }
}
