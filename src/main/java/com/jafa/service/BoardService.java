package com.jafa.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jafa.common.FileUploadUtils;
import com.jafa.domain.AttachVO;
import com.jafa.domain.Category;
import com.jafa.domain.Criteria;
import com.jafa.domain.ModifySnoS;
import com.jafa.domain.Reply_boardVO;
import com.jafa.domain.SchoolVO;
import com.jafa.repository.AttachRepository;
import com.jafa.repository.ReplyRepository;
import com.jafa.repository.SchoolRepository;

@Service
public class BoardService {
	
	@Autowired
	SchoolRepository schoolRepository;
	
	@Autowired
	AttachRepository attachRepository;
	
	@Autowired
	ReplyRepository replyRepository;
	
	@Autowired
	FileUploadUtils fileUploadUtil;
	
	@Transactional
	public void write(SchoolVO vo, List<MultipartFile> multipartFiles) {
		schoolRepository.write(vo);
		
		List<AttachVO> attachList = fileUploadUtil.getAttachVOAndUpload(vo.getSno(),multipartFiles);
		
		if(!attachList.isEmpty()) {//첨부파일있으면
			attachRepository.save(attachList);//첨부파일 정보 저장
			schoolRepository.updateAttachFileCnt(vo.getSno());//첨부파일 개수 업데이트
		}
		
	}


	public List<Category> list(String kind) {
		return schoolRepository.list(kind);
	}


	public List<SchoolVO> getBoard(String kind, String pageName, Criteria criteria) {
		
		return schoolRepository.getBoard(kind, pageName,criteria);
	}

	@Transactional
	public void pictureUpdate(SchoolVO vo, List<MultipartFile> multipartFile) {
		SchoolVO oldVO = schoolRepository.getdetail(vo.getSno());
		
		if(oldVO.getAttachFileCnt()==1) {
			Long sNo = oldVO.getSno();
			AttachVO attachVO = attachRepository.get(sNo);
			new File(attachVO.getFilePath()).delete();
			attachRepository.remove(sNo);
		}
		List<AttachVO> attachList = fileUploadUtil.getAttachVOAndUpload(vo.getSno(),multipartFile);
		
		if(!attachList.isEmpty()) {
			attachRepository.save(attachList);
		}
		schoolRepository.updateAttachFileCnt(vo.getSno());
	}


	public SchoolVO detail(Long no) {
		return schoolRepository.getdetail(no);
	}

	public void setClickcnt(Long no) {
		Long clickcnt = schoolRepository.getdetail(no).getClickcnt();
		schoolRepository.setClickcnt(clickcnt+1,no);
		schoolRepository.getdetail(no);
	}


	public void remove(Long sno) {
		
		List<AttachVO> list = attachRepository.getList(sno);
		if(!list.isEmpty()) {
			fileUploadUtil.deleteAllFile(sno);
		}
		schoolRepository.remove(sno);
		
	}

	//TooManyResultsException 반환하는 갑이 많음 /받는 값과 결과 값 일치 맵퍼에서 해결함or 저장소에서 해결
	@Transactional
	public void cafeUpdate(SchoolVO vo, List<MultipartFile> multipartFile) {
		SchoolVO oldVO = schoolRepository.getCafeDetail(vo.getCate_id(),vo.getTitle());
		vo.setSno(oldVO.getSno());
		if(oldVO.getAttachFileCnt()==1) {
			Long sNo = oldVO.getSno();
			AttachVO attachVO = attachRepository.get(sNo);
			new File(attachVO.getFilePath()).delete();
			attachRepository.remove(sNo);
		}
		
		List<AttachVO> attachList = fileUploadUtil.getAttachVOAndUpload(oldVO.getSno(),multipartFile);
		
		if(!attachList.isEmpty()) {
			attachRepository.save(attachList);
		}
		
		
		schoolRepository.updateCafe(vo);
		schoolRepository.updateAttachFileCnt(oldVO.getSno());
	}


	public SchoolVO serchCafeInfo(String kind, int today) {
		
		return schoolRepository.getCafeDetail(kind, today+"");
	}

	@Transactional
	public void modifyBoard(ModifySnoS delCheck, SchoolVO vo, List<MultipartFile> multipartFiles) {
		if(delCheck.getDelCheck()!=null) {
			List<Long> delCheckList = delCheck.getDelCheck();
			for(Long d:delCheckList) {
				AttachVO attachVO = attachRepository.get(d);
				new File(attachVO.getFilePath()).delete();
				attachRepository.remove(d);
			}
		}
		List<AttachVO> attachList = fileUploadUtil.getAttachVOAndUpload(vo.getSno(),multipartFiles);
		
		if(!attachList.isEmpty()) {
			attachRepository.save(attachList);
		}
		schoolRepository.updateCafe(vo);
		schoolRepository.updateAttachFileCnt(vo.getSno());		
		
	}


	public int getToalCount(String kind, String pageName, Criteria criteria) {
		return schoolRepository.getTotalCount(kind,pageName,criteria);
	}


	public List<Reply_boardVO> findReply(Long sno) {
		List<Reply_boardVO> replyList = replyRepository.reply(sno);

		return replyList;
		
	}
	



}
