package com.jafa.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jafa.domain.Reply_boardVO;

public interface ReplyRepository {

	void write(Reply_boardVO reply);

	List<Reply_boardVO> reply(Long sno);

	void updateAnswer(Reply_boardVO reply);

	void delanswer(Reply_boardVO comment);

	void del(Reply_boardVO comment);


}
