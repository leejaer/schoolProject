package com.jafa.repository;

import java.util.List;

import com.jafa.domain.MemberDTO;
import com.jafa.domain.MemberVO;

public interface MemberRepository {

	MemberVO read(String memberId);

	void save(MemberDTO dto);

	List<MemberVO> memberList();

	MemberVO getList(String id);

	void updateDetail(MemberDTO dto);

	

}
