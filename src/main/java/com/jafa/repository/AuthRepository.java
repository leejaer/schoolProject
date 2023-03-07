package com.jafa.repository;

import com.jafa.domain.AuthVO;

public interface AuthRepository {

	void remove(String memberId);

	void save(AuthVO vo);

	

}
