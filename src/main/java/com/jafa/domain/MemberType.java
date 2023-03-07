package com.jafa.domain;

public enum MemberType {
	
	ROLE_ADMIN("관리자"),
	ROLE_PRINCIPAL("교장"),
	ROLE_VICEP("교감"),
	ROLE_NUTRI("영양사"),
	ROLE_ADMINIST("행정"),
	ROLE_TEACHER("교사"),
	ROLE_SAFE("경비"),
	ROLE_STUDENT("학생");
	
	private final String name; 

	MemberType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}	
}
