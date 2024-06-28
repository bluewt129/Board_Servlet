package com.min.edu.ctrl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 3828164570372890792L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("LoginServlet GET");
		HttpSession session = req.getSession();
		session.invalidate();
		req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
		;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("LoginServlet POST");
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8;");

		IEmpInfo dao = new EmpInfoImpl();

		String id = req.getParameter("id");
		String password = req.getParameter("password");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("password", password);
		log.info("전달받은 요청값 : " + map);

		EmpInfoDto loginDto = dao.login(map);
		
		if (loginDto != null) {
			HttpSession session = req.getSession();
			session.setAttribute("loginDto", loginDto);
			resp.sendRedirect("./boardList.do");
		} else {
			resp.getWriter().print("<script>alert('회원이 존재하지 않습니다');" + "location.href='./';</script>");
		}

	}
}
