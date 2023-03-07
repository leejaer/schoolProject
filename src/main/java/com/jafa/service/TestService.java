package com.jafa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jafa.domain.TestDTO;
import com.jafa.domain.TestVO;
import com.jafa.repository.TestRepository;

@Service
public class TestService {

	@Autowired
	TestRepository testRepository;

	@Transactional
	public void add(TestDTO testDTO) {
		for(TestVO vo : testDTO.getTestDTO()) {
			if(testRepository.lookExist(vo.getPno(),vo.getTestKind()).isEmpty()) {
				testRepository.addScore(vo);
			}
		}
	}

	public List<TestVO> getList() {
		
		return testRepository.testList();
	}

	public List<TestVO> getOne(Long pno) {
		
		return testRepository.getOnScore(pno);
	}


}
