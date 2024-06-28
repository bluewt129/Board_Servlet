package com.min.edu.ctrl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.edu.model.IReplyBoard;
import com.min.edu.model.ReplyBoardImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UpdateReGoodServlet extends HttpServlet {

	private static final long serialVersionUID = 1186047563566386933L;
		
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("UpdateReGoodServlet POST");
		
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String seq = req.getParameter("rseq");
        log.info("전달된 rseq {}", seq);
        
        IReplyBoard dao = new ReplyBoardImpl();
        
        try {
			boolean success = dao.updateReGoodCount(seq);
			if(success) {
				resp.getWriter().write("{\"result\":\"success\"}");
			}else {
				resp.getWriter().write("{\"result\":\"failure\"}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.getWriter().write("{\"result\":\"error\"}");
		}
	}
	
	
}
