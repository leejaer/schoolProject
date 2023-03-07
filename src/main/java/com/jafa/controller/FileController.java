package com.jafa.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.FileNameMap;
import java.net.URLConnection;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@RestController//@ResponseBody가 포함
@Log4j
public class FileController {
	

	@GetMapping(value = "/download", produces =  MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> download(@RequestHeader("User-Agent") String userAgent,
			String filePath,String fileName) throws UnsupportedEncodingException{
		Resource resource = new FileSystemResource(filePath);
		//파일이 존재 x
		if(!resource.exists()) return new ResponseEntity<Resource>(HttpStatus.INTERNAL_SERVER_ERROR);
		
		HttpHeaders headers = new HttpHeaders();
		String downloadFileName = new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
		headers.add("Content-Disposition", "attach;fileName="+downloadFileName);
		
		return new ResponseEntity<Resource>(resource,headers,HttpStatus.OK);

	}
	


	public void deleteFile(String fileName) {
		File file = new File("c:/school/board",fileName);
		if(file.exists()) { // 파일이 있으면
			file.delete();
		}

	}


	public void folderDelete(String fileName) {
		File folder = new File("c:/school/board",fileName);
		File[] listFiles = folder.listFiles();
		
		if(!folder.exists()) return;
		if(folder.exists()) for(File f: listFiles) f.delete(); 
		if(folder.list().length==0 && folder.isDirectory() && folder.exists()) folder.delete();  
		return;
	}
	

	@GetMapping("/imgDisplay")
	public ResponseEntity<byte[]> imgDisplay(String filePath,String fileName) throws IOException {
		File file = new File(filePath);
		if(!file.exists()) {
			return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		HttpHeaders headers = new HttpHeaders();
		FileNameMap fileNameMap = URLConnection.getFileNameMap();
		headers.add("Content-Type", fileNameMap.getContentTypeFor(fileName));

		return new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file),headers,HttpStatus.OK);
	}
}
