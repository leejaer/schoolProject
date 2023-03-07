package com.jafa.domain;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reply_boardVO {
	private long level;
    private Long rno;
    private Long sno;
    private String reply;
    private String writer;
    private int parent_id;
    private String answer;
    
    List<Reply_boardVO> replyList;
    
	public void setReplyList(Reply_boardVO vo) {
		if(this.replyList==null) {
			this.replyList = new ArrayList<>();
		}
		this.replyList.add(vo);
	}
}
