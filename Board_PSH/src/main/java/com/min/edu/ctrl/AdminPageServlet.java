package com.min.edu.ctrl;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.edu.dto.EmpInfoDto;
import com.min.edu.model.EmpInfoImpl;
import com.min.edu.model.IEmpInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdminPageServlet extends HttpServlet {

	private static final long serialVersionUID = 1980166729513847257L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("AdminPageServlet GET");
		IEmpInfo dao = new EmpInfoImpl();
		
		String enabletype = req.getParameter("enabletype");
		
		List<EmpInfoDto> lists = dao.waitJoin();
		List<EmpInfoDto> alluser = dao.selectAllMember();
		
		 if (enabletype == null || enabletype.isEmpty()) {
			 enabletype = "A";
	        }

	        if ("A".equals(enabletype)) {
	            alluser = dao.selectAllMember(); 
	        } else {
	            alluser = dao.enableMember(enabletype); 
	        }
		
		req.setAttribute("lists", lists);
		req.setAttribute("alluser", alluser);
		req.getRequestDispatcher("/WEB-INF/views/adminPage.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("AdminPageServlet POST");
		
		String[] chs = req.getParameterValues("ch");
		String access = req.getParameter("access");
		log.info("전달받은 요청값 {}", Arrays.toString(chs));
		
		IEmpInfo dao = new EmpInfoImpl();
		
        
		boolean isc = false;
		if(access.equals("D")) {
			isc = dao.accessJoin(Arrays.asList(chs));		
		}else {
			isc = dao.rejectJoin(Arrays.asList(chs));
		}
		
		if(isc) {
			resp.sendRedirect("./adminPage.do");
		}else {
			resp.sendRedirect("./adminPage.do");
		}
	}
}
