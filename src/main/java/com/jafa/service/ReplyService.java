package com.jafa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jafa.domain.Reply_boardVO;
import com.jafa.domain.SchoolVO;
import com.jafa.repository.ReplyRepository;
import com.jafa.repository.SchoolRepository;

@Service
public class ReplyService {

	@Autowired
	ReplyRepository replyRepository;
	
	@Autowired
	SchoolRepository schoolRepository;
	
	@Transactional
	public void write(Reply_boardVO reply) {
		replyRepository.write(reply);
		
		replyRepository.updateAnswer(reply);
		
		SchoolVO getdetail = schoolRepository.getdetail(reply.getSno());
		
		schoolRepository.setReplycnt(getdetail.getReplycount()+1L,getdetail.getSno());
	}

	public void answerdel(Reply_boardVO comment) {
		
		
		if(comment.getAnswer()!=null) {
			replyRepository.delanswer(comment);
		}else {
			replyRepository.del(comment);
		}
		
	}

	public List<Reply_boardVO> list(Long sno) {
		return replyRepository.reply(sno);
	}
	
	

}
