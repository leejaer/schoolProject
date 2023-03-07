package com.jafa.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.jafa.domain.Category;
import com.jafa.domain.Criteria;
import com.jafa.domain.SchoolVO;

public interface SchoolRepository {
	
	List<Category> list(String kind);

	SchoolVO getdetail(Long sno);

	List<SchoolVO> getBoard(@Param("kind") String kind,@Param("pageName") String pageName,@Param("criteria") Criteria criteria);

	void write(SchoolVO vo);

	void updateAttachFileCnt(Long sNo);


	void pictureUpdate(SchoolVO vo);

	void remove(Long sno);

	SchoolVO getCafeDetail(@Param("cate_id") String cate_id,@Param("title") String title);

	void updateCafe(SchoolVO vo);

	int getTotalCount(@Param("kind") String kind,@Param("pageName") String pageName,@Param("criteria") Criteria criteria);

	void setClickcnt(@Param("clickcnt") long l,@Param("no") Long no);

	void setReplycnt(@Param("l") Long l,@Param("sno") Long sno);


}
