package com.min.edu.model;

import java.util.List;
import java.util.Map;

import com.min.edu.dto.EmpInfoDto;

public interface IEmpInfo {
	public EmpInfoDto login(Map<String, Object> map);
	
	public int joinMember(EmpInfoDto dto);
	
	public List<EmpInfoDto> waitJoin();
	
	public boolean accessJoin(List<String> list);
	
	public boolean rejectJoin(List<String> list);
	
	public List<EmpInfoDto> selectAllMember();
	
	public List<EmpInfoDto> enableMember(String enable);
	
	public EmpInfoDto selectMyInfo(String id);
	
	public boolean updateProfileImg(EmpInfoDto dto);
	
	public boolean deleteMember(String id);
}
