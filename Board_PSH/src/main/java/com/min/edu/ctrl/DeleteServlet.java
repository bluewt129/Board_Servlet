package com.min.edu.ctrl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.edu.model.EduBoardImpl;
import com.min.edu.model.IEduBoard;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeleteServlet extends HttpServlet {

	private static final long serialVersionUID = 8346730448244748028L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("DeleteServlet POST");

		String seq = req.getParameter("seq");
		log.info("전달받은 요청값{}", seq);

		IEduBoard dao = new EduBoardImpl();
		Boolean isc = dao.deleteBoard(seq);
		if (isc) {
			resp.sendRedirect("./boardList.do");
		} else {
			resp.sendRedirect("./login.do");
		}
	}
}
