package member.controller;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ConfirmMailServlet
 */
@WebServlet("/confirmMail.me")
public class ConfirmMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmMailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String receiver = request.getParameter("email"); //  받는 사람
		String title = "[JSP/Servlet] 유효 메일 확인";
		String content = "본 메일은 현재 고객님의 메일이 유효한지 확인하기 위한 메일입니다."; // 태그를 추가하여 보낼 수 있다. 꾸미기 가능
		// 이메일 인증 코드같은 건 추가하고 싶으면 추가
		String host="smtp.naver.com"; // 사용하는 메일을 적음 (gmail 등)
		String sender = "____@naver.com"; // 실제 보내는 사람의 유효한 메일
		String senderPwd = "____"; // 그 메일의 실제 비밀번호
		
		//Properties prop = new Properties();
//		prop.setProperty("mail.stmp.host", host);
//		prop.setProperty("mail.smtp.auth", "true");
		// 위의 코드는 오류 코드
		// com.sun.mail.util.MailConnectException: Couldn't connect to host, port: localhost, 25; timeout -1;
		// 포트번호 25를 찾지 못한다는 오류인듯 -> 이래서 자꾸 인증 보내는거 fail됨 failed콘솔에 출력
		
		// SMTP 서버 정보 설정
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 587);
		props.put("mail.smtp.auth", "true");
		// 이렇게 포트번호를 587로 직접 설정해주니 된다. 네이버는 587이고 구글은 465로 설정하면 된다
		
		Session session = Session.getDefaultInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender, senderPwd);
			}
		}); 
		// javax.mail이라는 패키지의 Session을 사용 -> jar파일 필요
		// javax.mail.Authenticator : 
		// Authenticator는 객체를 만들 수 없음, new Authenticator()라고만 하면 에러 -> 익명클래스로 다른데서 상속받아서 그 클래스로 객체 만들어야 하는데 간단하게 하기 위해 여기서
		// javax.mail.Session		
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(sender));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
			message.setSubject(title);
			message.setText(content, "UTF-8", "html"); // 태그를 추가한다면 html이라고 적어줘야함
			
			Transport.send(message);
			
			response.getWriter().println("success");
			
		} catch (AddressException e) {
			e.printStackTrace();
			response.getWriter().println("fail");
		} catch (MessagingException e) {
			e.printStackTrace();
			response.getWriter().println("fail");
		}
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
