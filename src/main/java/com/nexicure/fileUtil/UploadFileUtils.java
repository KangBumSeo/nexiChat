package com.nexicure.fileUtil;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

public class UploadFileUtils {
	
	public static String makeThumbnail(String filePath, String fileName, File uploadFile) throws IOException {
				 
		// 이미지 읽기 위한 버퍼
		BufferedImage bufferImg = ImageIO.read(uploadFile);
		
		// 이미지 리사이징
		BufferedImage thumbImg = new BufferedImage(100, 100, bufferImg.getType());
		
		Graphics2D graphics2D = thumbImg.createGraphics();
		graphics2D.drawImage(bufferImg, 0, 0, 100, 100, null);
		graphics2D.dispose();
		
		// 썸네일 이름 생성
		String thumbName = "img_"+fileName;
		File imgFile = new File(filePath, thumbName);
		
		// 이미지 파일 
		ImageIO.write(thumbImg, "png", imgFile);
		// 파일 확장자 확인
	
		return thumbName;
	}
	// 썸네일 생성

}
