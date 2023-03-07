package com.jafa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jafa.common.FileUploadUtils;
import com.jafa.domain.AttachVO;
import com.jafa.domain.PeopleVO;
import com.jafa.repository.AttachRepository;
import com.jafa.repository.PeopleRepository;

@Service
public class PeopleService {
	
	@Autowired
	PeopleRepository peopleRepostory;
	
	@Autowired
	FileUploadUtils fileUploadUtil;
	
	@Autowired
	AttachRepository attachRepository;
	
	public List<PeopleVO> list() {
		return	peopleRepostory.getlist();
	}

	
	@Transactional
	public void write(PeopleVO vo, List<MultipartFile> multipartFiles) {
		peopleRepostory.write(vo);
		List<AttachVO> attachList = fileUploadUtil.getAttachVOAndUpload(vo.getPno(),multipartFiles);
		
		if(!attachList.isEmpty()) {
			for(AttachVO attachvo :attachList) {
				attachvo.setSno(vo.getPno());
			}
			attachRepository.pictureSave(attachList);//첨부파일 정보 저장
		}
	}


	public List<PeopleVO> studentList() {
		
		return peopleRepostory.ListStudent("학생");
	}


	public PeopleVO getOne(String tel) {
		return peopleRepostory.getTel(tel);
	}

}
