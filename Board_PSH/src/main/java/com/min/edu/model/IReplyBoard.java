package com.min.edu.model;

import java.util.List;

import com.min.edu.dto.ReplyDto;

public interface IReplyBoard {
	public List<ReplyDto> selectAllReply(String post_id);

	public boolean insertFirstReply(ReplyDto dto);

	public boolean insertReReply(ReplyDto dto);

	public boolean updateReGoodCount(String seq);

	public boolean deleteReply(String seq);

	public List<ReplyDto> selectBestReply(String post_id);
	
	public int countReply(String post_id);
}
