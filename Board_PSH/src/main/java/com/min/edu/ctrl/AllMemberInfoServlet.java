package com.min.edu.ctrl;

import java.io.IOException;
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
public class AllMemberInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 1825573887791780958L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("AdminPageServlet GET");
		IEmpInfo dao = new EmpInfoImpl();

		String enabletype = req.getParameter("enabletype");

		List<EmpInfoDto> alluser = dao.selectAllMember();

		if (enabletype == null || enabletype.isEmpty()) {
			enabletype = "A";
		}

		if ("A".equals(enabletype)) {
			alluser = dao.selectAllMember();
		} else {
			alluser = dao.enableMember(enabletype);
		}

		req.setAttribute("alluser", alluser);
		req.getRequestDispatcher("/WEB-INF/views/allMemberInfo.jsp").forward(req, resp);
	}

	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("AllMemberInfoServlet POST");

        String enabletype = req.getParameter("enabletype");

        IEmpInfo dao = new EmpInfoImpl();
        List<EmpInfoDto> alluser;

        if ("A".equals(enabletype)) {
            alluser = dao.selectAllMember();
        } else {
            alluser = dao.enableMember(enabletype);
        }

        req.setAttribute("alluser", alluser);
        req.getRequestDispatcher("/WEB-INF/views/allMemberInfo.jsp").forward(req, resp);
	}
}
