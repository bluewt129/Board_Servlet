package com.min.edu.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.min.edu.database.SqlSessionFactoryManager;
import com.min.edu.dto.ReplyDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReplyBoardImpl implements IReplyBoard {
	
	private SqlSessionFactory factory = SqlSessionFactoryManager.getFactory();
	private final String NS = "com.min.edu.model.ReplyBoardImpl.";

	@Override
	public List<ReplyDto> selectAllReply(String post_id) {
		log.info("ReplyBoardImpl selectAllReply");
		SqlSession session = factory.openSession();
		return session.selectList(NS+"selectAllReply", post_id);
	}

	@Override
	public boolean insertFirstReply(ReplyDto dto) {
		log.info("ReplyBoardImpl insertFirstReply");
		SqlSession session = factory.openSession(true);
		int n = session.insert(NS+"insertFirstReply", dto);
		return (n==1)?true:false;
	}

	@Override
	public boolean insertReReply(ReplyDto dto) {
		log.info("ReplyBoardImpl insertReReply");
		SqlSession session = factory.openSession(true);
		int n = session.insert(NS+"insertReReply", dto);
		return (n==1)?true:false;
	}

	@Override
	public boolean updateReGoodCount(String seq) {
		log.info("ReplyBoardImpl updateReGoodCount");
		SqlSession session = factory.openSession(true);
		int n = session.update(NS+"updateReGoodCount", seq);
		return (n==1)?true:false;
	}

	@Override
	public boolean deleteReply(String seq) {
		log.info("ReplyBoardImpl deleteReply");
		SqlSession session = factory.openSession(true);
		int n = session.delete(NS+"deleteReply", seq);
		return (n==1)?true:false;
	}

	@Override
	public List<ReplyDto> selectBestReply(String post_id) {
		log.info("ReplyBoardImpl selectBestReply");
		SqlSession session = factory.openSession();
		return session.selectList(NS+"selectBestReply", post_id);
	}

	@Override
	public int countReply(String post_id) {
		log.info("ReplyBoardImpl countReply");
		SqlSession session = factory.openSession();
		int n = session.selectOne(NS+"countReply", post_id);
		return n;
	}

}
