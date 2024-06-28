package com.min.edu.ctrl;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.edu.model.EduBoardImpl;
import com.min.edu.model.IEduBoard;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MultiDeleteServlet extends HttpServlet {

	private static final long serialVersionUID = -1596072258444762109L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("MultiDeleteServlet POST");
		String[] chs = req.getParameterValues("ch");
		log.info("전달받은 요청값 {}", Arrays.toString(chs));
		
		IEduBoard dao = new EduBoardImpl();
		
		boolean isc = dao.mutiDeleteBoard(Arrays.asList(chs));
		if(isc) {
			resp.sendRedirect("./boardList.do");
		}else {
			resp.sendRedirect("./boardList.do");
		}
	}
}
