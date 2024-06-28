package com.min.edu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EduBoardDto {
	private String seq;
	private String id;
	private String title;
	private String content;
	private String delflag;
	private String createat;
	private int goodcount;
	private int readcount;
	private int reply_count;

}
