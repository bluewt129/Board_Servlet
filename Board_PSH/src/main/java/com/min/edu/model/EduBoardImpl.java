package com.min.edu.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.min.edu.database.SqlSessionFactoryManager;
import com.min.edu.dto.EduBoardDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EduBoardImpl implements IEduBoard {
	
	private SqlSessionFactory factory = SqlSessionFactoryManager.getFactory();
	private final String NS ="com.min.edu.model.EduBoardImpl.";
	
	@Override
	public List<EduBoardDto> selectAllBoard(Map<String, Object> map) {
		log.info("EduBoardImpl selectAllBoard");
		SqlSession session = factory.openSession();
		return session.selectList(NS+"selectAllBoard", map);
	}

	@Override
	public EduBoardDto selectDetailBoard(String seq) {
		log.info("EduBoardImpl selectDetailBoard");
		SqlSession session = factory.openSession();
		return session.selectOne(NS+"selectDetailBoard", seq);
	}

	@Override
	public boolean insertBoard(EduBoardDto dto) {
		log.info("EduBoardImpl insertBoard");
		SqlSession session = factory.openSession(true);
		int n = session.insert(NS+"insertBoard", dto);
		return (n==1)?true:false;
	}

	@Override
	public boolean modifyBoard(Map<String, Object> map) {
		log.info("EduBoardImpl modifyBoard");
		SqlSession session = factory.openSession(true);
		int n =session.update(NS+"modifyBoard", map);
		return (n==1)?true:false;
	}

	@Override
	public boolean mutiDeleteBoard(List<String> list) {
		log.info("EduBoardImpl mutiDeleteBoard");
		SqlSession session = factory.openSession(true);
		int n = session.delete(NS+"mutiDeleteBoard", list);
		return (n>0)?true:false;
	}

	@Override
	public boolean deleteBoard(String seq) {
		log.info("EduBoardImpl deleteBoard");
		SqlSession session = factory.openSession(true);
		int n = session.delete(NS+"deleteBoard", seq);
		return (n==1)?true:false;
	}

	@Override
	public int countBoard() {
		log.info("EduBoardImpl countBoard");
		SqlSession session = factory.openSession();
		return session.selectOne(NS+"countBoard");
	}

	@Override
	public List<EduBoardDto> selectMyBoard(Map<String, Object> map) {
		log.info("EduBoardImpl selectMyBoard");
		SqlSession session = factory.openSession();
		return session.selectList(NS+"selectMyBoard", map);
	}

	@Override
	public int countMyBoard(String id) {
		log.info("EduBoardImpl countMyBoard");
		SqlSession session = factory.openSession();
		return session.selectOne(NS+"countMyBoard", id);
	}

	@Override
	public boolean updateGoodCount(String seq) {
		log.info("EduBoardImpl updateGoodCount");
		SqlSession session = factory.openSession(true);
		int n = session.delete(NS+"updateGoodCount", seq);
		return (n==1)?true:false;
	}

	@Override
	public List<EduBoardDto> selectBestBoard() {
		log.info("EduBoardImpl selectBestBoard");
		SqlSession session = factory.openSession();
		return session.selectList(NS+"selectBestBoard");
	}

	@Override
	public boolean updateReadCount(String seq) {
		log.info("EduBoardImpl updateReadCount");
		SqlSession session = factory.openSession(true);
		int n = session.update(NS+"updateReadCount", seq);
		return (n==1)?true:false;
	}

}
