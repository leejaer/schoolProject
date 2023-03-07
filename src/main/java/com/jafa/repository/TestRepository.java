package com.jafa.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jafa.domain.TestVO;

public interface TestRepository {

	void addScore(TestVO vo);

	List<TestVO> testList();

	List<TestVO> lookExist(@Param("pno") long pno,@Param("testKind") String testKind);

	List<TestVO> getOnScore(Long pno);
}
