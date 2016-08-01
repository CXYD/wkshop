package net.jeeshop.biz.member.service;

import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.member.client.MemberMapper;
import net.jeeshop.biz.member.model.Member;
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

@Service
public class MemberService extends BaseService<Member, MemberExample>{

	@Autowired
	private MemberMapper memberMapper;

	@Autowired
	private SystemLogService systemLogService;
	
	@Override
	protected BaseMapper<Member, MemberExample> getMapper() {
		return memberMapper;
	}

	/**
	 * 根据用户名密码检索
	 * @return
     */
	public Member selectByUsernamePassword(String username, String plainPassword) {
		String encPassword = MD5.md5(plainPassword);

		MemberExample example = new MemberExample();
		MemberExample.Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		criteria.andPasswordEqualTo(encPassword);
		return super.selectUniqueByExample(example);
	}

	public Member selectByUsername(String username) {
		MemberExample example = new MemberExample();
		MemberExample.Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		return super.selectUniqueByExample(example);

	}

	public Member selectByNickname(String nickname) {
		MemberExample example = new MemberExample();
		MemberExample.Criteria criteria = example.createCriteria();
		criteria.andNickNameEqualTo(nickname);
		return super.selectUniqueByExample(example);
	}

	public Member selectByEmail(String email) {
		MemberExample example = new MemberExample();
		MemberExample.Criteria criteria = example.createCriteria();
		criteria.andEmailEqualTo(email);
		return super.selectUniqueByExample(example);
	}
	/**
	 * 记录登录日志
	 */
	@Transactional
	public void logLogin(Member member) {
		Member logMember = new Member();
		//更新用户最后登录时间
		logMember.setId(member.getId());
		logMember.setLastLoginTime(new Date());
		logMember.setLastLoginIp(AddressUtils.getIp(RequestHolder.getRequest()));
		String address = null;
		try {
			address = AddressUtils.getAddresses("ip=" + member.getLastLoginIp(), "utf-8");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		logMember.setLastLoginArea(address);
		this.update(logMember);
		systemLogService.newFrontLog("用户登录", "用户["+member.getUsername()+"]", LogType.login);
	}

	@Transactional
	public ResultBean updatePassword(Member member, String oldPassword, String newPassword) {
		Member persistMember = this.selectById(member.getId());
		if(persistMember == null) {
			return new ResultBean("01", "用户不存在");
		}
		String encOldPassword = MD5.md5(oldPassword);
		if(!persistMember.getPassword().equals(encOldPassword)) {
			return  new ResultBean("01", "原密码不正确");
		}
		String encNewPassword = MD5.md5(newPassword);
		Member record = new Member();
		//更新用户最后登录时间
		record.setId(member.getId());
		record.setPassword(encNewPassword);

		this.update(record);
		systemLogService.newFrontLog("修改密码", "用户["+member.getUsername()+"]修改密码", LogType.changePassword);

		return new ResultBean();
	}

	public void regMember(Member member) {
		memberMapper.regMember(member);
	}

	public void upNickName(Member member) {
		memberMapper.updateByPrimaryKeySelective(member);
	}

	public void upGender(Member member) {
		memberMapper.updateByPrimaryKeySelective(member);
	}
	/**
	 * 更改用户密码
	 *
	 * @param id
	 * @param newPassword
	 */
	@Transactional
	public void updateMemberPwd(Long id, String newPassword) {
		Member member = new Member();
		member.setId(id);
		member.setPassword(MD5.md5(newPassword));
		memberMapper.updateByPrimaryKeySelective(member);
	}
	@Transactional
	public void updateBirthDate(Long id, Date birthDate) {
		Member member = new Member();
		member.setId(id);
		member.setBirthDate(birthDate);
		memberMapper.updateByPrimaryKeySelective(member);
	}
}
