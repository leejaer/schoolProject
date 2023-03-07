package com.jafa.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
public class AttendVO {
	private Long pno;
	private String aname;
	private String condition;
	private String month;
	private String year;
	private String date;
	private Date attendDate;

	
}
