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
import com.min.edu.dto.PagingDto;
import com.min.edu.model.EduBoardImpl;
import com.min.edu.model.IEduBoard;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BoardListServlet extends HttpServlet {

	private static final long serialVersionUID = -6209102480098499921L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("BoardListServlet GET : 모든 게시글 조회");
		IEduBoard dao = new EduBoardImpl();
		
		String page = req.getParameter("page");
		if (page == null) {
			page = "1";
		}

		int selectPage = Integer.parseInt(page);

		PagingDto p = new PagingDto();
		p.setTotalCount(dao.countBoard());
		p.setCountList(10);
		p.setCountPage(5);
		p.setTotalPage(p.getTotalCount());
		p.setPage(selectPage);
		p.setStagePage(selectPage);
		p.setEndPage(p.getCountPage());

		List<EduBoardDto> lists = null;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("first", p.getPage() * p.getCountList() - (p.getCountList() - 1));
		map.put("last", p.getPage() * p.getCountList());
		lists = dao.selectAllBoard(map);
		log.info("전달받은값 (모든 게시글) {}", map);
		

		req.setAttribute("page", p);
		req.setAttribute("lists", lists);
		req.getRequestDispatcher("/WEB-INF/views/boardList.jsp").forward(req, resp);

	}

}
