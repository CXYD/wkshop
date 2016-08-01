package net.jeeshop.biz.member.service;

import java.util.List;

import net.jeeshop.biz.member.client.MemberRankMapper;
import net.jeeshop.biz.member.model.MemberRank;
import net.jeeshop.biz.member.model.MemberRankExample;
import net.jeeshop.biz.base.bean.PageBean;
import net.jeeshop.biz.base.bean.PageQueryBean;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.base.service.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberRankService extends BaseService<MemberRank, MemberRankExample>{

	@Autowired
	private MemberRankMapper accountRankMapper;

	@Override
	protected BaseMapper<MemberRank, MemberRankExample> getMapper() {
		return accountRankMapper;
	}

	public PageBean<MemberRank> selectPageBean(final MemberRankExample params,PageQueryBean pageQueryBean)
	{
		return executePageQuery(new PageQueryExecutor<MemberRank>() 
		{
			@Override
			public List<MemberRank> executeQuery() {
				return accountRankMapper.selectByExample(params);
			}
		}, pageQueryBean);
	}

}
