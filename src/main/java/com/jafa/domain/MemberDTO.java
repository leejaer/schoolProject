package com.jafa.domain;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDTO {

	private Long mno;
	
	@NotBlank(message = "입력해주세요")
	@Length(min= 4, max = 16, message = "아이디 길이를 확인해주세요")
	private String id;
	@NotBlank(message = "입력해주세요")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&=]{8,}$",message = "8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
	private String pwd;
	@NotBlank(message = "입력해주세요")
	private String pwdConfirm;
	@NotBlank(message = "입력해주세요")
	private String pname;
	@Email(message = "이메일 형식으로 입력해주세요")
	@NotBlank(message = "입력해주세요")
	private String email;
	@NotBlank(message = "입력해주세요")
	@Pattern(regexp = "^01([0|1|6|7|8|9])?([0-9]{3,4})?([0-9]{4})$",message = "-를 제외한 전화번호를 입력하세요")
	private String tel;
	private boolean enabled;
	private List<AuthVO> authList;
}
