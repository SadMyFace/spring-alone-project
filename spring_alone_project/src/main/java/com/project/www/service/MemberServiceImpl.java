package com.project.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.www.repository.MemberDAO;
import com.project.www.security.MemberVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
	
	private final MemberDAO mdao;
	
	@Transactional
	@Override
	public int memberRegister(MemberVO mvo) {
		
		int isOk = mdao.memberRegister(mvo);
		return mdao.insertAuthInit(mvo.getEmail());
//		MemberVO tempMvo = mdao.findUser(mvo.getEmail());
//		
//		if(tempMvo != null) {
//			return 0;
//		}
//		
//		if(mvo.getEmail() == null || mvo.getEmail().length() == 0) {
//			return 0;
//		}
//		if(mvo.getPwd() == null || mvo.getPwd().length() == 0) {
//			return 0;
//		}
//		
//		String pwd = mvo.getPwd();
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String encodePw = passwordEncoder.encode(pwd);
//		mvo.setPwd(encodePw);
//		
//		int isOk = mdao.memberRegister(mvo);
//		
//		return isOk;
	}

	@Override
	public boolean updateLastLogin(String authEmail) {
		return mdao.updateLastLogin(authEmail) > 0 ? true : false;
	}

	@Override
	public MemberVO detail(String email) {
		// TODO Auto-generated method stub
		MemberVO mvo =  mdao.selectEmail(email);
		mvo.setAuthList(mdao.selectAuths(email));
		
		return mvo;
	}

	@Override
	public int updateMember(MemberVO mvo) {
		// TODO Auto-generated method stub
		return mdao.updateMember(mvo);
	}

	@Override
	public String findPassword(String email) {
		// TODO Auto-generated method stub
		return mdao.findPassword(email);
	}
	
	@Transactional
	@Override
	public List<MemberVO> getMemberList() {
		// TODO Auto-generated method stub
		List<MemberVO> list = mdao.getMemberList();
		for(MemberVO mvo : list) {
			mvo.setAuthList(mdao.selectAuths(mvo.getEmail()));
		}
		return list;
	}
	
	@Transactional
	@Override
	public int deleteMember(String email) {
		// TODO Auto-generated method stub
		int isOk = mdao.deleteAuthMember(email);
		
		log.info(">>> delete Auth Member >>>" + ((isOk > 0) ? "OK" : "Fail"));
		
		return mdao.deleteMember(email);
	}

	@Override
	public MemberVO checkEmail(String email) {
		// TODO Auto-generated method stub
		return mdao.checkEmail(email);
	}
}
