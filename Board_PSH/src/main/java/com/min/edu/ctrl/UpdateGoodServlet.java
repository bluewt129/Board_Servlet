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
public class UpdateGoodServlet extends HttpServlet {

	private static final long serialVersionUID = 6571568338595280401L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("UpdateGoodServlet POST");
		
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String seq = req.getParameter("seq");
        
        IEduBoard dao = new EduBoardImpl();

        try {
            boolean success = dao.updateGoodCount(seq);
            if (success) {
                resp.getWriter().write("{\"result\":\"success\"}");
            } else {
                resp.getWriter().write("{\"result\":\"failure\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("{\"result\":\"error\"}");
        }
    }
}
