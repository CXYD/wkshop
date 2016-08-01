package net.jeeshop.biz.cms.service;

import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.cms.client.NoticeMapper;
import net.jeeshop.biz.cms.client.NoticeMapperExt;
import net.jeeshop.biz.cms.model.Notice;
import net.jeeshop.biz.cms.model.NoticeExample;
import net.jeeshop.biz.system.model.SysUser;
import net.jeeshop.web.util.LoginUserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 公告相关的service
 *
 * @author zuowen
 */
@Service
public class NoticeService extends BaseService<Notice, NoticeExample> {

    @Autowired
    NoticeMapper noticeMapper;

    @Autowired
    NoticeMapperExt noticeMapperExt;

    @Override
    protected BaseMapper<Notice, NoticeExample> getMapper() {
        return noticeMapper;
    }

    @Transactional
    public void updateStatus(Long[] ids, boolean isValid) {
        if (ids == null || ids.length == 0) {
            return;
        }

        SysUser sysUser = LoginUserHolder.getLoginUser();
        Date nowDate = Calendar.getInstance().getTime();
        List<Notice> noticeList = noticeMapperExt.selectNoticeByIds(ids);
        if (noticeList == null || noticeList.isEmpty()) {
            return;
        }
        List<Notice> updateNoticeList = new ArrayList<Notice>(noticeList.size());
        for (Notice notice : noticeList) {
            if (notice.getIsValid() == isValid) {//不需要更新
                continue;
            }
            notice.setIsValid(isValid);
            notice.setUpdateAccount(sysUser.getUsername());
            notice.setUpdateTime(nowDate);
            updateNoticeList.add(notice);

        }
        if (updateNoticeList != null && !updateNoticeList.isEmpty()) {
            noticeMapperExt.updateNoticeByNoticeList(updateNoticeList);
        }
    }

	/**
	 * 更新阅读次数
	 * @param notice
	 */
	public void updateReadCount(Notice notice) {
		Long count = notice.getReadCount();
		if(count == null)
			count = 0L;
		count +=1;
		notice.setReadCount(count);
		noticeMapper.updateByPrimaryKey(notice);
	}

}
