package com.jafa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jafa.domain.ApprovalVO;
import com.jafa.domain.Criteria;
import com.jafa.domain.ExpendDTO;
import com.jafa.domain.ExpendVO;
import com.jafa.repository.ApprovalRepository;
import com.jafa.repository.PeopleRepository;

import oracle.jdbc.proxy.annotation.Pre;
import oracle.net.aso.e;

@Service
public class ApprovalService {
	
	@Autowired
	ApprovalRepository approvalRepository;
	
	@Autowired
	PeopleRepository peopleRepository;
	
	public void write(ApprovalVO vo) {
		approvalRepository.write(vo);
	}

	public List<ApprovalVO> list() {
		return approvalRepository.getList();
	}
	
	@Transactional
	public void del(Long ano, Long no) {
		approvalRepository.del(ano);
		peopleRepository.del(no);
	}

	public void upadate(String state, Long ano) {
		approvalRepository.denyUpdate(state,ano);
		
	}
	
	
	public void eventWrite(ApprovalVO vo) {
		approvalRepository.addEvent(vo);
	}

	public List<ApprovalVO> eventList() {
		return approvalRepository.eventList();
	}

	public void delEvent(Long ano) {
		approvalRepository.del(ano);
	}

	public void updateEvent(ApprovalVO vo) {
		approvalRepository.eventUpdate(vo);
		
	}

	public List<ApprovalVO> eventListP() {
		
		return approvalRepository.eventListP();
	}

	@Transactional
	public void expendAdd(ExpendDTO expendDTO) {
		List<ExpendVO> expendVOList = expendDTO.getExpendDTO();
		
		approvalRepository.addExpend(expendVOList.get(0));
		Long eno = expendVOList.get(0).getEno();
		for(ExpendVO vo : expendVOList) {
			vo.setEno(eno);
			approvalRepository.ExpnedApproval(vo);
		}
	}

	public List<ExpendVO> getExpend(String pageName) {
		List<ExpendVO> expendVOList = approvalRepository.getExpendList(pageName);
		Long total = 0L;
		String name = "";
		if(pageName.equals("expend")) {
			for(ExpendVO vo : expendVOList) {
				List<ExpendVO> expend = approvalRepository.getExpendApproval(vo.getEno());
				total = 0L;
				name = "";
				for(ExpendVO VOList : expend) {
					total += VOList.getExpend()*VOList.getCount();
					name += VOList.getName()+" ";
				}
				vo.setExpend(total);
				vo.setName(name);
			}
		}
			
		return expendVOList;
	}

	public void delExpend(Long eno) {
		approvalRepository.ExpendDelete(eno);
		
	}

	public void constructionAdd(ExpendVO vo) {
		approvalRepository.setContstruction(vo);
	}

	public List<ExpendVO> getSignExpend() {
		
		return approvalRepository.getApproval();
	}

	public void updateApprovalVO(ExpendVO vo) {
		approvalRepository.updateApproval(vo);
		
	}




}
