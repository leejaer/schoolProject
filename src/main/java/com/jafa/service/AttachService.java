package com.jafa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jafa.domain.AttachVO;
import com.jafa.repository.AttachRepository;

@Service
public class AttachService {

	@Autowired
	AttachRepository attachRepository;
	
	public List<AttachVO> list(Long l) {
		return attachRepository.getList(l);
	}

}
