package com.min.edu.ctrl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import com.min.edu.dto.EmpInfoDto;
import com.min.edu.model.EmpInfoImpl;
import com.min.edu.model.IEmpInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProfileImgServlet extends HttpServlet {

	private static final long serialVersionUID = 8235762560851855815L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("ProfileImgServlet POST");
		req.setCharacterEncoding("UTF-8");

		EmpInfoDto loginDto = (EmpInfoDto) req.getSession().getAttribute("loginDto");

		if(loginDto == null) {
			resp.sendRedirect("./login.do");	
		}
		
		//String saveDirectory = "C:\\Programming_IDE\\eclipse_web\\workspace_web\\Board_PSH\\src\\main\\webapp\\upload";
		
		String path = "C:\\Programming_IDE\\eclipse_web\\workspace_web\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Board_PSH\\upload";
		ServletContext context = req.getServletContext();
		String saveDirectory = context.getRealPath("upload");
		
		log.info("절대 경로 {}", saveDirectory);

		//File directory = new File(saveDirectory);
		File directory = new File(path);
		if (!directory.exists()) {
			boolean created = directory.mkdirs();
			if (created) {
				log.info("디렉토리 생성 {}", saveDirectory);
			} else {
				log.info("디렉토리 생성 실패");
			}
		}
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(5 * 1024 * 1024); // 5MB

		try {
			FileItemIterator iter = upload.getItemIterator(req);

			EmpInfoDto empInfoDto = new EmpInfoDto();
			empInfoDto.setId(loginDto.getId());
			while (iter.hasNext()) {
				FileItemStream item = iter.next();

				if (item.isFormField()) {
					String fileName = item.getFieldName();
					String fileValue = Streams.asString(item.openStream());

					log.info("파일이 아닌 경우 처리 - fileName: {}, fileValue: {}", fileName, fileValue);

					if ("profile_img".equals(fileName)) {
						empInfoDto.setProfile_img(fileValue);
					}
					log.info("최종 DB에 입력되는 From 필드 값: {}", empInfoDto);
				} else if (!item.isFormField()) {
					String origin_fname = item.getName();
					if (origin_fname != null && !origin_fname.isEmpty()) {
						String stored_fname = UUID.randomUUID().toString().replace("-", "")
								+ origin_fname.substring(origin_fname.lastIndexOf("."));

						File uploadedFile = new File(saveDirectory, stored_fname);
						InputStream inputStream = null;
						OutputStream outputStream = null;
						inputStream = item.openStream();
						outputStream = new FileOutputStream(uploadedFile);

						inputStream.transferTo(outputStream);

						empInfoDto.setProfile_img(stored_fname);

					}
				}
			}

			IEmpInfo dao = new EmpInfoImpl();

			boolean isc = dao.updateProfileImg(empInfoDto);
			if (isc) {
				resp.sendRedirect("./myPage.do");
			}

		} catch (FileUploadException | IOException e) {
			e.printStackTrace();
		}

	}
}
