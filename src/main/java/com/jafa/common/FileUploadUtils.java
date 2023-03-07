package com.jafa.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.jafa.domain.AttachVO;
import com.jafa.domain.FileType;

@Component
public class FileUploadUtils {

	
	private final static String BOARD_UPLOAD_FOLDER = "c:/school/board";
	
	public List<AttachVO> getAttachVOAndUpload(Long no, List<MultipartFile> multipartFiles){
		List<AttachVO> attachList = new ArrayList<>();

		for(MultipartFile mf: multipartFiles) {
			String uuid = getUuid(mf.getOriginalFilename());
			
			if(!mf.isEmpty()) {//multipartFile객체에 파일정보가 있을 떄
				AttachVO attachVO = AttachVO.builder().sno(no)
							.uuidName(uuid)
							.fileName(mf.getOriginalFilename())
							.filePath(getFilePath(no, uuid))
							.fileType(getFileType(mf.getContentType()))
							.build();
				attachList.add(attachVO);
				
				File folder = new File(BOARD_UPLOAD_FOLDER+"/"+no);
				File file = new File(attachVO.getFilePath());
				if(!folder.exists()) {
					folder.mkdirs();
				}
				try {
					mf.transferTo(file);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return attachList;
	}
	
	//업로드 파일 경로
	private String getFilePath(Long no, String uuid) {
		return BOARD_UPLOAD_FOLDER+"/"+no+"/"+uuid;
	}
	
	private String getUuid(String originalFilename) {
		String uuid = UUID.randomUUID().toString();
		return uuid+"_"+originalFilename;
	}

	//파일 타입 결정
	private FileType getFileType(String contentType) {

		if(contentType.startsWith("image")) return FileType.IMAGE;
		else return FileType.OTHER;
	}
	
	public void deleteAllFile(Long bno) {
		File delFolder = new File(BOARD_UPLOAD_FOLDER+"/"+bno);
		
		if(delFolder.exists()) {
			File[] listFiles = delFolder.listFiles();
			for(File f : listFiles) {
				f.delete();
			}
		}
		delFolder.delete();//폴더 삭제
	}
}
