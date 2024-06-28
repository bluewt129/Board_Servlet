package com.min.edu.ctrl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.min.edu.dto.EmpInfoDto;
import com.min.edu.model.EmpInfoImpl;
import com.min.edu.model.IEmpInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyPageServlet extends HttpServlet {

	private static final long serialVersionUID = -5114206428413845744L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("MyPageServlet GET");
		
		HttpSession session = req.getSession();
		EmpInfoDto loginDto = (EmpInfoDto)session.getAttribute("loginDto");
		
		IEmpInfo dao = new EmpInfoImpl();
		EmpInfoDto dto = dao.selectMyInfo(String.valueOf(loginDto.getId()));
		
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/WEB-INF/views/myPage.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("MyPageServlet POST");
	}
	
}
