package com.min.edu.ctrl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.edu.dto.ReplyDto;
import com.min.edu.model.IReplyBoard;
import com.min.edu.model.ReplyBoardImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReplyBoardServlet extends HttpServlet {

	private static final long serialVersionUID = 8138665621791332359L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("ReplyBoard POST");
		
		req.setCharacterEncoding("UTF-8");
		
		String rcontent = req.getParameter("rcontent");
		String boardSeq = req.getParameter("post_id");
		String userId = req.getParameter("user_id");
		
		ReplyDto replyDto = new ReplyDto();
		replyDto.setUser_id(userId);
		replyDto.setPost_id(boardSeq);
		replyDto.setRcontent(rcontent);
		
		log.info("저장된 값 {}:",replyDto);
		IReplyBoard reply = new ReplyBoardImpl();
		
		boolean isc = reply.insertFirstReply(replyDto);
		if(isc) {
		    resp.sendRedirect("./detailBoard.do?seq=" + boardSeq);
		} else {
		    resp.getWriter().print("<script>alert('댓글 등록 불가');" + "location.href='./detailBoard.do?seq=" + boardSeq + "';</script>");
		}

	}
	
}
