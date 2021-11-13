package board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.multi.MultiOptionPaneUI;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;

import board.model.service.BoardService;
import board.model.vo.Attachment;
import board.model.vo.Board;
import common.MyFileRenamePolicy;
import member.model.vo.Member;


/**
 * Servlet implementation class ThumbnailInsertServlet
 */
@WebServlet("/insert.th")
public class ThumbnailInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThumbnailInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
		
//		String title = request.getParameter("title"); // 출력시 null
//		String content = request.getParameter("content"); // 출력시 null
		
		// form태그의 속성으로 encType="multipart/form-data" 설정하면 위와 같이 String으로 가져왔을때 인지하지 못함 -> MultipartRequest클래스 객체 생성하여 받아줘야 함
		
		// enctype(인코딩타입)이 multipart/form-date로 전송되었는지 확인 
		if (ServletFileUpload.isMultipartContent(request)) {
			
			// 파일 업로드 처리를 위한 변수 설정
			int maxSize = 1024*1024*10; // 10Mbyte
			String root = request.getSession().getServletContext().getRealPath("/"); // 웹 서버 컨테이너 경로 추출 : WebContent
			String savePath = root + "thumbnail_uploadFiles/";
			
			File f = new File(savePath); // 직접 폴더 생성하지 않고 File객체를 통해 생성
			if (!f.exists()) { // 폴더(경로)가 존재하지 않는다면
				f.mkdirs(); // 디렉토리 생성
			}
			
			// DefaultFileRenamePolicy (cos.jar 안에 존재)
			// : 같은 파일 명이 있는지 검사하고, 있을 경우 파일 명 뒤에 숫자를 붙임
			// ex. aaa.jpg, aaa1.jpg, aaa2.jpg (중복된 이름의 파일이 있으면 숫자를 붙임)
			// 인지할 수 없는 파일의 이름은 가져오기 힘들기 때문에 rename 필수 -> 우리만의 규약 만듦
			
			// MultipartRequest : 파일 형식 가져오려면 필요한 클래스 <- 이 클래스를 쓰려면 jar라이브러리 필요
			// cos.jar 라이브러리 먼저 lib폴더에 복사 후  -> MultipartRequest클래스 import 가능
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy()); // 매개변수 있는 생성자로만 생성 가능
			//매개변수 -> request, saveDirectory : 어디에 저장할 건지,  maxPostSize : 최대 크기, encoding : 인코딩타입, policy : 파일 이름 바꾸는 규약    
			
			ArrayList<String> saveFiles = new ArrayList<String>();		// 파일의 바뀐 이름을 저장할 ArrayList
			ArrayList<String> originFiles = new ArrayList<String>();	// 파일의 원래 이름을 저장할 ArryaList
			
			// multiRequest.getFileNames() : request.getParameter()처럼 form에서 넘어온  file정보 가져옴, 전송 순서의 역순으로 가져옴 thumbnailImg4, ..3, ..2, ..1
			Enumeration<String> files = multiRequest.getFileNames(); // multiRequest.getFileNames()의 반환값 -> Enumeration : iterator와 기능이 같음 
			while(files.hasMoreElements()) {
				
				String name = files.nextElement(); // files.nextElement() 출력하면 thumbnailImg4, ..3, ..2, ..1 나옴
				
				if (multiRequest.getFilesystemName(name) != null) { // rename이 된 파일명(바뀐 파일명)을 가져옴, 사진이 들어가있지 않을때 null일 수도 있음, 사진 들어가있는 것만 가져오기 (!= null)
					saveFiles.add(multiRequest.getFilesystemName(name));
					originFiles.add(multiRequest.getOriginalFileName(name));
				}
			}
			
			// [202111101623119710.png, 202111101623119700.png]
			// [it's a bond.png, deposit.png] // originFiles 출력시 역순으로 파일 들어옴을 확인 가능
			
			String title = multiRequest.getParameter("title");
			String content = multiRequest.getParameter("content");
			String writer = ((Member)request.getSession().getAttribute("loginUser")).getUserId();
			
			Board b = new Board(0, 2, "10", title, content, writer, null, 0, null, null, null);
			ArrayList<Attachment> fileList = new ArrayList<Attachment>();
			for(int i = originFiles.size() - 1; i >= 0; i--) { // 역순으로 파일 저장되어 있기 때문에 거기서 또 역순으로 저장하기
				Attachment a = new Attachment();
				a.setFilePath(savePath);
				a.setOriginName(originFiles.get(i));
				a.setChangeName(saveFiles.get(i));
				
				if (i == originFiles.size() - 1) { // 맨 끝에 저장된 사진이라면(맨 처음 입력받았던 썸네일 사진이라면)
					a.setFileLevel(0); // fileLevel이 0 이면 썸네일로 약속
				} else {
					a.setFileLevel(1);
				}
				
				fileList.add(a);
			}
			
			int result = new BoardService().inserThumbnail(b, fileList); // DB 저장을 위해 게시판과 파일리스트를 보냄 
			
//			if (result > 1) { // 파일 db저장 과정에서 저장이 안된게 있다면 result 0, 모두 저장됐다면 1을 반환하게 했음 때의 조건문임 (1+1 = 2)
//				response.sendRedirect("list.th");
//			} else {
//				request.setAttribute("msg", "사진 게시글 저장 실패");
//				request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
//			}
		
			if (result >= 1+fileList.size()) { // 게시판
				response.sendRedirect("list.th");
			} else {
				request.setAttribute("msg", "사진 게시글 저장 실패");
				request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
			
				for (int i =0; i < saveFiles.size(); i++) {
					File fail = new File(savePath + saveFiles.get(i));
					fail.delete(); // 만약 게시판이나 사진들 중 하나라도 insert가 안된게 있다면 저장된 파일들 다 삭제
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
