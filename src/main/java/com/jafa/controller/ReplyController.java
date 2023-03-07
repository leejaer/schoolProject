package com.jafa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jafa.domain.Reply_boardVO;
import com.jafa.domain.SchoolVO;
import com.jafa.repository.SchoolRepository;
import com.jafa.service.BoardService;
import com.jafa.service.ReplyService;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;

@RestController  
@RequestMapping("/reply")
@Log4j
public class ReplyController {
	
	@Autowired
	ReplyService replyService;
	
	@Autowired
	BoardService boardService;
	
	@PostMapping("/write")//@RequestBody 붙여야 JSON으로 들어온 값을 바인딩 할 수 있음
	public String write(@RequestBody Reply_boardVO  reply) {
		replyService.write(reply);
		
		return "댓글 성공";
	}

	
	
	@PostMapping("/commentWrite")
	public String commentWrite(@RequestBody Reply_boardVO  comment) {
		replyService.write(comment);
		return "댓글 성공";
	}

	
	@PostMapping("/answerdel")//
	public String answerdel(@RequestBody Reply_boardVO  comment) {
		comment.setWriter("미상");
		comment.setReply("지워진 글입니다");
		replyService.answerdel(comment);
		return "댓글 성공";
	}

	@GetMapping("/list")
	public List<Reply_boardVO> list(Long sno) {
		List<Reply_boardVO> list = new ArrayList<>();
		
		boardService.setClickcnt(sno);
		
		list = replyService.list(sno);
		System.out.println(list);
		return list;
	}
}
