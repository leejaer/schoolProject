package com.jafa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jafa.domain.ApprovalVO;
import com.jafa.domain.ExpendVO;
import com.jafa.service.ApprovalService;

@Controller
@RequestMapping("/approval")
public class ApprovalController {
	
	@Autowired
	ApprovalService approvlaSercive;
	
	
	@PostMapping("/write")
	public String write(ApprovalVO vo,RedirectAttributes rttr,String k,String p) {
		approvlaSercive.write(vo);
		return "redirect:/board/"+k+"/"+k+"/"+p;
	}
	
	
	@PostMapping("/ok")
	public String ok(Long ano,Long no,RedirectAttributes rttr,String k,String p) {
		approvlaSercive.del(ano,no);
		return "redirect:/board/"+k+"/"+k+"/"+p;
	}
	
	
	@PostMapping("/deny")
	public String deny(String state,Long ano,RedirectAttributes rttr,String k,String p) {
		approvlaSercive.upadate(state,ano);
		return "redirect:/board/"+k+"/"+k+"/"+p;
	}
	
	
	
	@PostMapping("/eWrite")
	public String eWrite(ApprovalVO vo,RedirectAttributes rttr,String progress,String p) {
		approvlaSercive.eventWrite(vo);
		return "redirect:/board/"+progress+"/"+progress+"/"+p;
	}
	@PostMapping("/eventDel")
	public String eventDel(String kind,String pageName ,RedirectAttributes rttr,Long ano) {
		approvlaSercive.delEvent(ano);
		return "redirect:/board/"+kind+"/"+kind+"/"+pageName;
	}

	@PostMapping("/enventUpdate")
	public String enventUpdate(String kind,String pageName ,RedirectAttributes rttr,ApprovalVO vo) {
		approvlaSercive.updateEvent(vo);
		return "redirect:/board/"+kind+"/"+kind+"/"+pageName;
	}
	
	@PostMapping("/expendDel")
	public String expendDel(String kind,String pageName ,RedirectAttributes rttr,Long eno) {
		approvlaSercive.delExpend(eno);
		return "redirect:/board/"+kind+"/"+kind+"/"+pageName;
	}

	@PostMapping("/updateSign")
	public String updateSign(String kind,String pageName ,RedirectAttributes rttr,ExpendVO vo) {
		approvlaSercive.updateApprovalVO(vo);
		return "redirect:/board/"+kind+"/"+kind+"/"+pageName;
	}
}

