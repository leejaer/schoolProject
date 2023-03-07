package com.jafa.domain;

import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SchoolVO {

	private Long sno;
	private String kind;
	private String cate_id;
	
	@NotBlank(message = "제목을 입력하세요")
	private String title;
	
	@NotBlank(message = "내용을 입력하세요")
	private String content;
	
	@NotBlank(message = "작성자를 입력하세요")
	private String writer;
	private String writeDate;	
	private Long attachFileCnt;
	private Long replycount;
	private Long clickcnt;
	
	Category schoolCate;
	
	List<AttachVO> attachList;
}
