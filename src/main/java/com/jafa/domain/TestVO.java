package com.jafa.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
public class TestVO {
	private long pno;
	private String testKind;
	
	@NotBlank(message = "입력해주세요")
	private String sname;
	
	
	@NotNull(message = "숫자를 입력하세요")
	@Min(value = 0, message = "0이상 100이하로 입력하세요")
	@Max(value = 100, message = "0이상 100이하로 입력하세요")
	private Long korea;
	
	@NotNull(message = "숫자를 입력하세요")
	@Min(value = 0, message = "0이상 100이하로 입력하세요")
	@Max(value = 100, message = "0이상 100이하로 입력하세요")
	private Long math;
	
	@NotNull(message = "숫자를 입력하세요")
	@Min(value = 0, message = "0이상 100이하로 입력하세요")
	@Max(value = 100, message = "0이상 100이하로 입력하세요")
	private Long english;
	
	@NotNull(message = "숫자를 입력하세요")
	@Min(value = 0, message = "0이상 100이하로 입력하세요")
	@Max(value = 100, message = "0이상 100이하로 입력하세요")
	private Long history;
}
