package com.min.edu.ctrl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.edu.model.EmpInfoImpl;
import com.min.edu.model.IEmpInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeleteMemberServlet extends HttpServlet {

	private static final long serialVersionUID = 8527353172441708221L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("DeleteMemberServlet POST");
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8;");

		String id = req.getParameter("id");
		log.info("전달받은 요청값{}", id);
		
		IEmpInfo dao = new EmpInfoImpl();
		Boolean isc = dao.deleteMember(id);
		if (isc) {
			resp.getWriter().print("<script>alert('탈퇴완료');" + "location.href='./login.do';</script>");
		} else {
			resp.getWriter().print("<script>alert('탈퇴 실패');</script>");
			resp.sendRedirect("./myPage.do");
		}
	}
}
