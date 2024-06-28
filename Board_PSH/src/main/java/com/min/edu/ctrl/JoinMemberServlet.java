package com.min.edu.ctrl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.edu.dto.EmpInfoDto;
import com.min.edu.model.EmpInfoImpl;
import com.min.edu.model.IEmpInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JoinMemberServlet extends HttpServlet {

	private static final long serialVersionUID = 3939257978454527832L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("JoinMemberServlet GET");
		req.getRequestDispatcher("/WEB-INF/views/joinMember.jsp").forward(req, resp);;
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("JoinMemberServlet POST");
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8;");
		
		String id = req.getParameter("id");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
		
        EmpInfoDto dto = new EmpInfoDto(id, password, name, phone);
        System.out.println("신규가입정보 : "+dto);
        
        IEmpInfo dao = new EmpInfoImpl();
        int row = dao.joinMember(dto);
        System.out.println(row);
        if(row == 1) {
        	resp.getWriter().print("<script>alert('회원 가입 완료');"
					+ "location.href='./';</script>");
            resp.sendRedirect("./"); 
        }else {
        	resp.sendRedirect("./joinMember.do");
        }
	}
}
