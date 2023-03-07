package com.jafa.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jafa.domain.ApprovalVO;
import com.jafa.domain.Criteria;
import com.jafa.domain.ExpendDTO;
import com.jafa.domain.ExpendVO;

public interface ApprovalRepository {

	void write(ApprovalVO vo);

	List<ApprovalVO> getList();

	void del(Long ano);

	void denyUpdate(@Param("state") String state,@Param("ano") Long ano);

	void addEvent(ApprovalVO vo);

	List<ApprovalVO> eventList();

	void eventUpdate(ApprovalVO vo);

	List<ApprovalVO> eventListP();

	void addExpend(ExpendVO vo);

	void ExpnedApproval( ExpendVO vo);

	List<ExpendVO> getExpendList(String pageName);

	List<ExpendVO> getExpendApproval(Long eno);

	void ExpendDelete(Long eno);

	void setContstruction(ExpendVO vo);

	List<ExpendVO> getApproval();

	void updateApproval(ExpendVO vo);


}
