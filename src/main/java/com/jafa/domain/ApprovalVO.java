package com.jafa.domain;

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
public class ApprovalVO {

	private Long ano;
	private Long no;
	private String kind;
	private String title;
	private String state;
	private String content;
	private String progress;
}
