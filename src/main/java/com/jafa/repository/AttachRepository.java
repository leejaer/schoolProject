package com.jafa.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.jafa.domain.AttachVO;
import com.jafa.domain.Category;
import com.jafa.domain.SchoolVO;

public interface AttachRepository {

	void save(@Param("attachList") List<AttachVO> attachList);

	AttachVO get(Long sno);

	void remove(Long sno);

	List<AttachVO> getList(Long sno);

	void pictureSave(@Param("attachList") List<AttachVO> attachList);
}
