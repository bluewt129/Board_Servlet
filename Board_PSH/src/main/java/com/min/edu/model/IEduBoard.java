package com.min.edu.model;

import java.util.List;
import java.util.Map;

import com.min.edu.dto.EduBoardDto;

public interface IEduBoard {

	public List<EduBoardDto> selectAllBoard(Map<String, Object> map);

	public EduBoardDto selectDetailBoard(String seq);

	public boolean insertBoard(EduBoardDto dto);

	public boolean modifyBoard(Map<String, Object> map);
	
	public boolean mutiDeleteBoard (List<String> list);

	public boolean deleteBoard(String seq);

	public int countMyBoard(String id);
	
	public int countBoard();
	
	public List<EduBoardDto> selectMyBoard(Map<String, Object> map);
	
	public boolean updateGoodCount(String seq);
	
	public List<EduBoardDto> selectBestBoard();

	public boolean updateReadCount(String seq);

}
