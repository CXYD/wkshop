package net.jeeshop.biz.cms.client;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.jeeshop.biz.cms.model.Notice;



public interface NoticeMapperExt extends NoticeMapper {
   
	List<Notice> selectNoticeByIds(@Param("ids") Long[] ids);
	
	Integer updateNoticeByNoticeList( List<Notice> noticeList);
}