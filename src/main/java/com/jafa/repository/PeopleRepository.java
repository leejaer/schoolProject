package com.jafa.repository;

import java.util.List;

import com.jafa.domain.PeopleVO;

public interface PeopleRepository {

	List<PeopleVO> getlist();

	void write(PeopleVO vo);

	void del(Long pno);

	PeopleVO getTel(String tel);

	List<PeopleVO> ListStudent(String kind);

}
