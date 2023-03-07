package com.jafa.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jafa.domain.AttendVO;

public interface AttendRepository {

	void setAttend(@Param("attendVO") AttendVO attendVO,@Param("attendDate") String attendDate);

	List<AttendVO> getAttendList();

	Long gerOneCheck(@Param("pno") Long pno,@Param("attendDate") String attendDate);

	AttendVO getAttendOne(Long pno);

}
