package com.min.edu.ctrl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.edu.dto.EduBoardDto;
import com.min.edu.model.EduBoardImpl;
import com.min.edu.model.IEduBoard;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BestBoardServlet extends HttpServlet {

	private static final long serialVersionUID = 4335914151279115547L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("BestBoardServlet GET");
		
		IEduBoard dao = new EduBoardImpl();
		
		List<EduBoardDto> lists = dao.selectBestBoard();
		req.setAttribute("lists", lists);
		req.getRequestDispatcher("/WEB-INF/views/bestBoard.jsp").forward(req, resp);

		
	}
}
