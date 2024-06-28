package com.min.edu.ctrl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.edu.dto.EduBoardDto;
import com.min.edu.dto.EmpInfoDto;
import com.min.edu.dto.PagingDto;
import com.min.edu.model.EduBoardImpl;
import com.min.edu.model.IEduBoard;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyBoardServlet extends HttpServlet {

	private static final long serialVersionUID = 9052443115800461202L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("MyBoardServlet GET");
		IEduBoard dao = new EduBoardImpl();
		
		String page = req.getParameter("page");
		if(page == null) {
			page = "1";
		}
		
		EmpInfoDto dto = (EmpInfoDto)req.getSession().getAttribute("loginDto");
		int selectPage = Integer.parseInt(page);
		
		PagingDto p = new PagingDto();
		p.setTotalCount(dao.countMyBoard(dto.getId()));
		p.setCountList(10); 
		p.setCountPage(5); 
		p.setTotalPage(p.getTotalCount());
		p.setPage(selectPage);
		p.setStagePage(selectPage);
		p.setEndPage(p.getCountPage());
		
		String myboard = req.getParameter("myboard");
		List<EduBoardDto> lists = null;
		
		if ("true".equals(myboard)) {
			Map<String, Object> mymap = new HashMap<String, Object>();
			mymap.put("id", dto.getId());
			mymap.put("first", p.getPage() * p.getCountList() - (p.getCountList() - 1));
			mymap.put("last", p.getPage() * p.getCountList());
			lists = dao.selectMyBoard(mymap);
			log.info("전달받은값 (내글 보기) {}", mymap);
		}
		
		req.setAttribute("page", p);
		req.setAttribute("lists", lists);
		req.getRequestDispatcher("/WEB-INF/views/myBoardList.jsp").forward(req, resp);
	}
}
