package com.jafa.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jafa.domain.AttendDTO;
import com.jafa.service.AttendService;

@Controller
@RequestMapping("/attend")
public class AttendController {
	
	@Autowired
	AttendService attendService;
	
	@PostMapping("/write")
	public String expendWrite(@ModelAttribute("attendDTO") AttendDTO attendDTO,
			RedirectAttributes rttr,String kind,String pageName) {
		
		attendService.writeAttend(attendDTO);

		return "redirect:/board/"+kind+"/"+kind+"/"+pageName;
	}
	
	


}