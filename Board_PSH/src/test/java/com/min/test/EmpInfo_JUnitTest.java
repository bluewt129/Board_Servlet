package com.min.test;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import com.min.edu.database.SqlSessionFactoryManager;
import com.min.edu.dto.EmpInfoDto;

public class EmpInfo_JUnitTest {
	
	//@Before
	public void test() {
		SqlSessionFactory factory = SqlSessionFactoryManager.getFactory();
		SqlSession session = factory.openSession();
		assertNotNull(session);
	}
	
	@SuppressWarnings("serial")
	@Test
	public void loginTest() {
		SqlSessionFactory factory = SqlSessionFactoryManager.getFactory();
		SqlSession session = factory.openSession();
		EmpInfoDto loginDto = session.selectOne("com.min.edu.model.EmpInfoImpl.login",
				new HashMap<String, Object>(){{
					put("id", "USER");
					put("password", "USER");
				}});
		System.out.println(loginDto);
		assertNotNull(loginDto);
	}


}
