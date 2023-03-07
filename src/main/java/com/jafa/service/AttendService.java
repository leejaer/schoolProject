package com.jafa.service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jafa.domain.AttendDTO;
import com.jafa.domain.AttendVO;
import com.jafa.repository.AttendRepository;

@Service
public class AttendService {

	@Autowired
	AttendRepository attenRepository;
	
	public void writeAttend(AttendDTO attendDTO) {
		
		for(AttendVO attendVO : attendDTO.getAttendDTO()) {
			
			String attendDate = attendVO.getYear()+attendVO.getMonth()+attendVO.getDate();
			if(attenRepository.gerOneCheck(attendVO.getPno(),attendDate)==null) {
				attenRepository.setAttend(attendVO,attendDate);
			};
		}
		
	}

	public List<AttendVO> getList() {
		List<AttendVO> attendList = attenRepository.getAttendList();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일",Locale.KOREAN);
		
		for(AttendVO vo:attendList) {
			vo.setDate(sdf.format(vo.getAttendDate()));
		}
		
		return attendList;
	}

	public AttendVO getVO(Long pno) {
		AttendVO attendOne = attenRepository.getAttendOne(pno);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일",Locale.KOREAN);
		attendOne.setDate(sdf.format(attendOne.getAttendDate()));
		return attendOne;
	}




}
