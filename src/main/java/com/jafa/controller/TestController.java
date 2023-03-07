package com.jafa.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jafa.domain.PeopleVO;
import com.jafa.domain.TestDTO;
import com.jafa.domain.TestVO;
import com.jafa.service.BoardService;
import com.jafa.service.PeopleService;
import com.jafa.service.TestService;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	TestService testService;
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	PeopleService peopleService;
	
	@PostMapping("/write")
	public String expendWrite(@Valid @ModelAttribute("testDTO") TestDTO testDTO, Errors errors,
			RedirectAttributes rttr,Model model,
			String kind,String pageName) {
		
		if(errors.hasErrors()) {
			model.addAttribute("cateList",boardService.list(kind));
			List<PeopleVO> StudentList = peopleService.studentList();
			List<TestVO> testList = testService.getList();
			model.addAttribute("test",testList);
			model.addAttribute("StudentList",StudentList);
			model.addAttribute("kind",kind);
			model.addAttribute("pageName",pageName);
			return "board/schoolInfo";
		}
		
		testService.add(testDTO);
		return "redirect:/board/"+kind+"/"+kind+"/"+pageName;
	}
	
	


}