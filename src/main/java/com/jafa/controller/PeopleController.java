package com.jafa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jafa.domain.PeopleVO;
import com.jafa.service.BoardService;
import com.jafa.service.PeopleService;

@Controller 
@RequestMapping("/people")
public class PeopleController {
	
	@Autowired
	PeopleService peopleService;
	
	@Autowired
	BoardService boardService;
	
	@GetMapping(value = {"/write/{kind}/{pageName}"})
	public String writeForm(Model model,@PathVariable String kind, @PathVariable String pageName) {
		model.addAttribute("cateList",boardService.list(kind));
		
		return "/people/writeForm";
	}
		
	//MultipartFile Security에서 어떻게 받을까 csrf 액세스거부 1.@ModelAttribute, @RequestPart 로 나눠서 보내기 2.action경로 뒤에 csrf토큰을 입력.
	//MultipartHttpServletRequest 와 @RequestParam 둘다 받을 수 있음
	@PostMapping("/write")
	public String school(Model model,PeopleVO  vo,RedirectAttributes rttr,
			@RequestParam("attachFile") List<MultipartFile> multipartFiles,String cate_id,String kind) {
		
		if(multipartFiles !=null) {
			for(MultipartFile MF : multipartFiles) {
				if(MF.getContentType().startsWith("image")){
					model.addAttribute("cateList",boardService.list(kind));
					model.addAttribute("pageName",cate_id);
					model.addAttribute("kind",kind);
					model.addAttribute("message","이미지 파일을 업로드해주세요");
					return "/people/writeForm";
				}
			}
		}
		
		
		peopleService.write(vo,multipartFiles);
		
		return "redirect:/board/vicep/vicep/"+cate_id;
	}
	

}
