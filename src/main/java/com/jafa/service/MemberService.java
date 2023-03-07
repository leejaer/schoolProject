package com.jafa.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jafa.domain.AuthVO;
import com.jafa.domain.MemberDTO;
import com.jafa.domain.MemberType;
import com.jafa.domain.MemberVO;
import com.jafa.domain.PeopleVO;
import com.jafa.repository.AuthRepository;
import com.jafa.repository.MemberRepository;

@Service
public class MemberService {
	
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	AuthRepository authRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	

	
	//회원가입
	@Transactional
	public void join(PeopleVO pepleVO, MemberDTO dto, RedirectAttributes rttr) {
		dto.setPwd(passwordEncoder.encode(dto.getPwd()));
		memberRepository.save(dto);
		
		if(pepleVO.getKind().equals("학생")) {
			AuthVO authVO = AuthVO.builder()
					.id(dto.getId())
					.memberType(MemberType.ROLE_STUDENT)
					.ordinal(MemberType.ROLE_STUDENT.ordinal())
					.build();
			authRepository.save(authVO);
		}else if(pepleVO.getKind().equals("교사")) {
			AuthVO authVO = AuthVO.builder()
					.id(dto.getId())
					.memberType(MemberType.ROLE_TEACHER)
					.ordinal(MemberType.ROLE_TEACHER.ordinal())
					.build();
			authRepository.save(authVO);			
		}else if(pepleVO.getKind().equals("영양사")) {
			AuthVO authVO = AuthVO.builder()
					.id(dto.getId())
					.memberType(MemberType.ROLE_NUTRI)
					.ordinal(MemberType.ROLE_NUTRI.ordinal())
					.build();
			authRepository.save(authVO);			
		}else if(pepleVO.getKind().equals("경비")) {
			AuthVO authVO = AuthVO.builder()
					.id(dto.getId())
					.memberType(MemberType.ROLE_SAFE)
					.ordinal(MemberType.ROLE_SAFE.ordinal())
					.build();
			authRepository.save(authVO);			
		}else if(pepleVO.getKind().equals("교감")) {
			AuthVO authVO = AuthVO.builder()
					.id(dto.getId())
					.memberType(MemberType.ROLE_VICEP)
					.ordinal(MemberType.ROLE_VICEP.ordinal())
					.build();
			authRepository.save(authVO);			
		}else if(pepleVO.getKind().equals("교장")) {
			AuthVO authVO = AuthVO.builder()
					.id(dto.getId())
					.memberType(MemberType.ROLE_PRINCIPAL)
					.ordinal(MemberType.ROLE_PRINCIPAL.ordinal())
					.build();
			authRepository.save(authVO);			
		}else if(pepleVO.getKind().equals("행정")) {
			AuthVO authVO = AuthVO.builder()
					.id(dto.getId())
					.memberType(MemberType.ROLE_ADMINIST)
					.ordinal(MemberType.ROLE_ADMINIST.ordinal())
					.build();
			authRepository.save(authVO);			
		}
	}
	
	@Transactional
	public void upateMemberType(AuthVO authVO) {
		authRepository.remove(authVO.getId());
		MemberType memberType = authVO.getMemberType();//변경할 회원등급
		MemberType[] types = MemberType.values();
		
		for(int i= memberType.ordinal(); i<types.length;i++) {
			AuthVO vo = AuthVO.builder()
					.id(authVO.getId())
					.memberType(types[i])
					.ordinal(types[i].ordinal())
					.build();
			
			authRepository.save(vo);
		}
	}
	//회원목록 조회
	public List<MemberVO> memberList() {
		
		return memberRepository.memberList();
	}

	public MemberVO getPno(String username) {
		
		return memberRepository.getList(username);
	}

	public void update(MemberDTO dto) {
		dto.setPwd(passwordEncoder.encode(dto.getPwd()));
		memberRepository.updateDetail(dto);
		
	}

	public MemberVO getMemberIdCheck(String id) {
		
		return memberRepository.getList(id);
	}
	
	
}
