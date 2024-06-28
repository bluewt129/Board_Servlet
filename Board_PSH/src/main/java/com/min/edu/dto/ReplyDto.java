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
public class ReplyDto {

	private String seq;
	private String user_id;
	private String post_id;
	private String rcontent;
	private int step;
	private int dept;
	private String regdate;
	private String delflag;
	private String modifydate;
	private int regoodcount;
	private String parent_rseQ;

}
