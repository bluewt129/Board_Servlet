package com.min.edu.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.min.edu.database.SqlSessionFactoryManager;
import com.min.edu.dto.EmpInfoDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmpInfoImpl implements IEmpInfo {

	private SqlSessionFactory factory = SqlSessionFactoryManager.getFactory();
	private final String NS = "com.min.edu.model.EmpInfoImpl.";

	@Override
	public EmpInfoDto login(Map<String, Object> map) {
		log.info("EmpInfoImpl login : " + map);
		SqlSession session = factory.openSession();
		return session.selectOne(NS + "login", map);
	}

	@Override
	public int joinMember(EmpInfoDto dto) {
		log.info("EmpInfoImpl joinMember : " + dto);
		SqlSession session = factory.openSession(true);
		return session.insert(NS + "joinMember", dto);
	}

	@Override
	public List<EmpInfoDto> waitJoin() {
		log.info("EmpInfoImpl waitJoin");
		SqlSession session = factory.openSession();
		return session.selectList(NS + "waitJoin");
	}

	@Override
	public boolean accessJoin(List<String> list) {
		log.info("EmpInfoImpl accessJoin"+list);
		SqlSession session = factory.openSession(true);
		int n = session.update(NS + "accessJoin", list);
		return (n == 1) ? true : false;
	}

	@Override
	public boolean rejectJoin(List<String> list) {
		log.info("EmpInfoImpl rejectJoin"+list);
		SqlSession session = factory.openSession(true);
		int n = session.update(NS + "rejectJoin", list);
		return (n == 1) ? true : false;
	}

	@Override
	public List<EmpInfoDto> selectAllMember() {
		log.info("EmpInfoImpl selectAllMember");
		SqlSession session = factory.openSession();
		return session.selectList(NS + "selectAllMember");
	}
	
	@Override
	public List<EmpInfoDto> enableMember(String enable) {
		log.info("EmpInfoImpl enableMember");
		SqlSession session = factory.openSession();
		return session.selectList(NS + "enableMember", enable);
	}

	@Override
	public EmpInfoDto selectMyInfo(String id) {
		log.info("EmpInfoImpl selectMyInfo");
		SqlSession session = factory.openSession();
		return session.selectOne(NS+"selectMyInfo", id);
	}

	@Override
	public boolean updateProfileImg(EmpInfoDto dto) {
		log.info("EmpInfoImpl updateProfileImg"+dto);
		SqlSession session = factory.openSession(true);
		int n = session.update(NS + "updateProfileImg", dto);
		return (n == 1) ? true : false;
	}

	@Override
	public boolean deleteMember(String id) {
		log.info("EmpInfoImpl deleteMember"+id);
		SqlSession session = factory.openSession(true);
		int n = session.update(NS + "deleteMember", id);
		return (n == 1) ? true : false;
	}

}
