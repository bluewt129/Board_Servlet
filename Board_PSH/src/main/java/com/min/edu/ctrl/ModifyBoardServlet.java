package com.min.edu.ctrl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.min.edu.dto.EduBoardDto;
import com.min.edu.dto.EmpInfoDto;
import com.min.edu.model.EduBoardImpl;
import com.min.edu.model.IEduBoard;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ModifyBoardServlet extends HttpServlet {

	private static final long serialVersionUID = -5124008129292025705L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("ModifyBoardServlet 글수정 화면이동 GET");
		String seq = req.getParameter("seq");
		log.info("전달된 요청값 : {}", seq);
		
		HttpSession session = req.getSession();
		EmpInfoDto loginDto = (EmpInfoDto)session.getAttribute("loginDto");
		IEduBoard dao = new EduBoardImpl();
		EduBoardDto dto = dao.selectDetailBoard(seq);
		if(loginDto.getId().equals(dto.getId())) {
			req.setAttribute("dto", dto);
			req.getRequestDispatcher("/WEB-INF/views/modifyBoard.jsp").forward(req, resp);
		}else {
			resp.sendRedirect("./login.do");
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("ModifyBoardServlet 글수정 입력 POST");
		req.setCharacterEncoding("UTF-8");
		
		String seq = req.getParameter("seq");
		String content = req.getParameter("content");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("seq", seq);
		map.put("content", content);
		
		log.info("전달받은 요청 값 {}", map);
		
		IEduBoard dao = new EduBoardImpl();
 		boolean isc =  dao.modifyBoard(map);
 		if(isc) {
 			resp.sendRedirect("./detailBoard.do?seq="+seq);
 		}else {
 			resp.sendRedirect("./modifyBoard.do?seq="+seq);
 		}
		
	}
}
