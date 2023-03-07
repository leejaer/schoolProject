package com.jafa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jafa.domain.Criteria;
import com.jafa.repository.SchoolRepository;
import com.jafa.service.BoardService;

@Controller
public class HomeController {

	@Autowired
	BoardService boardService;

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("event",boardService.getBoard("school", "event", new Criteria()));
		model.addAttribute("notice",boardService.getBoard("school", "notice", new Criteria()));
		
		return "index";
	}
}