package filter.wrapper;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncryptWrapper extends HttpServletRequestWrapper { // wrapper는 filter와 연결되어야 사용 가능
	// 들어온 비밀번호를 가지고 암호화 -> 요청에서부터 암호화 필요 : HttpServletRequestWrapper 상속
	
	public EncryptWrapper(HttpServletRequest request) { // HttpServletRequestWrapper상속 받을시 생성자 필수
		super(request);
	}
	
	// url 쿼리스트링에 있는 매개변수의 데이터를 가져오는 메소드
	@Override
	public String getParameter(String name) { // getParameter(String name):String -> 매개변수 String으로 들어올 값들(쿼리스트링의 변수 name) (ex. "title", "userEmail", "userPwd" ..)
		// 암호화시킬 것은 비밀번호기 때문에 name으로 들어오는 것이 비밀번호일 때만으로 조건 걸어야함
		// 로그인, 회원가입, 비밀번호 수정 요청 url에서 들어오는 파라미터 이름은 현재 userPwd, joinUserPwd, newPwd (usePwd2같이 비밀번호 확인하는 건 뷰에서 처리하고, 만약 백에서 처리한다면 여기서도 조건 걸어줘야 함
		// if (name.contains("Pwd")) { // 간단하게 한다면 이렇게
		
		String value = null;
		if (name != null && (name.equals("userPwd") || name.equals("joinUserPwd") || name.equals("newPwd"))) {
			try {
				MessageDigest md = MessageDigest.getInstance("SHA-512"); // java.security.MessageDigest -> 암호화를 위해 필요한 객체 생성
				// SHA-512 라는 암호화 방식(로직)을 사용하겠다
				byte[] bytes = super.getParameter(name).getBytes(Charset.forName("UTF-8")); // 암호화 위해 byte배열 필요
				md.update(bytes); // 암호화시킴
				value = Base64.getEncoder().encodeToString(md.digest()); // 임호화한 것을 String으로 인코딩해서 value(반환할 변수)에 답음 
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		} else {
			value = super.getParameter(name); // 이걸 받아줘야지 됨, 저 if문 내용과 다른 이름의 파라미터들은 여기에서 받아줌
		}
		
		return value;
	}
}
