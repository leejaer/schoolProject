package com.jafa.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
public class ExpendVO {
	private Long eno;
    private String kind;
    
    
    @NotBlank(message = "입력하세요")
    private String name;
    
    @NotNull(message = "입력하세요")
    private Long count;
    private String state;
    
    @NotNull(message = "입혁하세요")
    private Long expend; 
    
	
}
