 package com.min.edu.ctrl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.edu.dto.EduBoardDto;
import com.min.edu.dto.ReplyDto;
import com.min.edu.model.EduBoardImpl;
import com.min.edu.model.IEduBoard;
import com.min.edu.model.IReplyBoard;
import com.min.edu.model.ReplyBoardImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DetailBoardServlet extends HttpServlet {

	private static final long serialVersionUID = -3818823526637674461L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("DetailBoardServlet GET");
		String seq = req.getParameter("seq");
		log.info("전송된 요청값 {}" ,seq);
	
		IEduBoard dao = new EduBoardImpl();
		EduBoardDto dto = dao.selectDetailBoard(seq);
		
		IReplyBoard replyDao = new ReplyBoardImpl();
		List<ReplyDto> rdto = replyDao.selectAllReply(seq);
		int replyCount = replyDao.countReply(seq);
		
		IReplyBoard bestRe = new ReplyBoardImpl();
		List<ReplyDto> bestrdto = bestRe.selectBestReply(seq);
		
		dao.updateReadCount(seq);
		
		
		req.setAttribute("dto", dto);
		req.setAttribute("rdto", rdto);
		req.setAttribute("replyB", bestrdto);
		req.setAttribute("replyCount", replyCount);
		req.getRequestDispatcher("/WEB-INF/views/detailBoard.jsp").forward(req, resp);
		
	}
}
