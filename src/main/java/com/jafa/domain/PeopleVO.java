package com.jafa.domain;

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
public class PeopleVO {
	private Long pno;
	private String kind;
	private String cate_id;
	private String pclass;
	private String pname;
	private String year;
	private Boolean joinCheck;
	private String tel;
	private String joinDate;
}
