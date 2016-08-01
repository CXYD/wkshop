package net.jeeshop.biz.member.service;

import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.member.client.MemberAddressMapper;
import net.jeeshop.biz.member.client.MemberMapper;
import net.jeeshop.biz.member.model.Member;
import net.jeeshop.biz.member.model.MemberAddress;
import net.jeeshop.biz.member.model.MemberAddressExample;
import net.jeeshop.biz.member.model.MemberExample;
import net.jeeshop.biz.system.enums.LogType;
import net.jeeshop.biz.system.service.SystemLogService;
import net.jeeshop.core.util.AddressUtils;
import net.jeeshop.core.util.MD5;
import net.jeeshop.web.bean.ResultBean;
import net.jeeshop.web.util.RequestHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class MemberAddressService extends BaseService<MemberAddress, MemberAddressExample>{

	@Autowired
	private MemberAddressMapper memberAddressMapper;

	@Override
	protected BaseMapper<MemberAddress, MemberAddressExample> getMapper() {
		return memberAddressMapper;
	}

	/**
	 * 检索用户的收货地址
	 * @param memberId
	 * @return
	 */
	public List<MemberAddress> selectMemberAddress(long memberId) {
		MemberAddressExample example = new MemberAddressExample();
		MemberAddressExample.Criteria criteria = example.createCriteria();
		criteria.andMemberIdEqualTo(memberId);

		return memberAddressMapper.selectByExample(example);
	}

	@Override
	public long insert(MemberAddress entity) {
		//TODO 提取省市区信息
		return super.insert(entity);
	}

	@Override
	public int update(MemberAddress entity) {
		//TODO 提取省市区信息
		return super.update(entity);
	}
}
