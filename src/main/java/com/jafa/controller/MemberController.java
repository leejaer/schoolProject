package com.jafa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jafa.domain.MemberDTO;
import com.jafa.domain.MemberDetail;
import com.jafa.domain.MemberVO;
import com.jafa.domain.PeopleVO;
import com.jafa.repository.PeopleRepository;
import com.jafa.service.MemberService;

import oracle.jdbc.proxy.annotation.Post;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	PeopleRepository peopleRepository;
	
	@GetMapping("/accessError")
	public void accessError() {
		
	}
	
	//회원가입 폼
	
	@GetMapping("/join")
	public void joinForm() {

	}
	
	@RequestMapping("/login")
	public String login(Authentication authentication, RedirectAttributes rttr) {
		if(authentication!=null && authentication.isAuthenticated()) { // 인증된 사용자가 있을 때 
			rttr.addFlashAttribute("loginOn", "이미 로그인한 상태입니다.");
			return "redirect:/";
		}
		return "/member/login";
	}

	
	//회원가입 처리
	@PostMapping("/join")
	public String join(@Valid MemberDTO dto,Errors errors ,RedirectAttributes rttr,Model model) {
		
		if(!dto.getPwd().matches(dto.getPwdConfirm())) errors.rejectValue("pwd", "equal.password");
		
		model.addAttribute("dto",dto);
		
		if(errors.hasErrors()) {
			return "/member/join";
		}
		
		String tel = dto.getTel();
		
		PeopleVO pepleVO = peopleRepository.getTel(tel);	
		MemberVO vo = memberService.getMemberIdCheck(dto.getId());
		
		if(vo != null) {
			model.addAttribute("message", "아이디가 중복되었습니다.");;
			return "/member/join";
		}
		
		
		if(pepleVO==null || pepleVO.getJoinCheck()) {
			if(pepleVO==null) rttr.addFlashAttribute("message", "학교에 등록된 사용자가 아닙니다");
			else rttr.addFlashAttribute("message", "이미 가입한 사용자입니다.");
		
			return "redirect:/member/join";
		};
		
		memberService.join(pepleVO,dto,rttr);
		rttr.addFlashAttribute("message", "회원가입 성공");
		return "redirect:/";
	}
	
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/info")
	public void info(Authentication auth, Model model) {
		MemberDetail principal = (MemberDetail) auth.getPrincipal();
		model.addAttribute("info",principal.getMemberVO());
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/infoModify")
	public void infoModify(Authentication auth, Model model) {
		MemberDetail principal = (MemberDetail) auth.getPrincipal();
		model.addAttribute("info",principal.getMemberVO());
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/update")
	public String update(@Valid MemberDTO dto,Errors errors ,RedirectAttributes rttr) {
		
		if(!dto.getPwd().matches(dto.getPwdConfirm())) errors.rejectValue("pwd", "equal.password");
		
		if(errors.hasErrors()) {
			return "/member/infoModify";
		}
		
		memberService.update(dto);
		
		rttr.addFlashAttribute("message", "수정성공");
		return "redirect:/member/info";
	}
	

}
