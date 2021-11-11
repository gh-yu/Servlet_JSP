package common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy{

	@Override
	public File rename(File originFile) { // 원본 파일을 매개변수로 받음

		// 이름이 겹치지 않게 하기 위해 시간을 이용 + 확실히 하기 위해 random값까지 같이
		long currentTime = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		int ranNum = (int)Math.random();
		
		String name = originFile.getName();
		String ext = null;
		
		int dot = name.lastIndexOf("."); // 가장 마지막의 .은 -> 확장자 앞의 . 일 것
		// this.is.my.dog.image.png -> 가장 마지막에 있는 . 위치 추출
		if (dot != -1) {
			ext = name.substring(dot); // 확장자 추출
		} else { // 위치를 찾을 수 없을때 -1을 반환함 (확장자가 없는 파일도 있음)
			ext = ""; // 확장자 없는 파일은 비워두기
		}
		
		String fileName = sdf.format(new Date(currentTime)) + ranNum + ext; 
		// 여기서의 Date는 java.util의 Date
		
		File newFile = new File(originFile.getParent(), fileName); // File의 매개변수 -> parent pathname string and a child pathname string. 
		// 이 객체 생성으로 인해 파일 컴퓨터에 저장됨
		
		return newFile;
	}
}
