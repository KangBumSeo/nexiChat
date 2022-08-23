package com.nexicure.nim.services.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.BeanWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nexicure.nim.ConfigConstants;


@Service
public class MultipartFileManager {
	private Logger logger = LogManager.getLogger(MultipartFileManager.class);


	public File saveFile(MultipartFile saveFile) throws IOException {
		return saveFile(saveFile, "TMP");
	}
	public File saveFile(MultipartFile saveFile, String label) throws IOException {
		
		File file = null;
		FileOutputStream fos = null;
		String filename = saveFile.getOriginalFilename();
		String extName = "";
		if(filename != null) {
			extName = filename.substring(filename.lastIndexOf("."), filename.length());
		}
		
		String saveFileName = getSaveFileName(label, extName);
		try {
			File tempDir = new File(ConfigConstants.TEMP_UPLOAD_DIR);
			if(!tempDir.exists())
				tempDir.mkdirs();
			
			byte[] data = saveFile.getBytes();
		
			logger.info(ConfigConstants.TEMP_UPLOAD_DIR + saveFileName);
			fos = new FileOutputStream(ConfigConstants.TEMP_UPLOAD_DIR + saveFileName);
			fos.write(data);
			
			file = new File(ConfigConstants.TEMP_UPLOAD_DIR + saveFileName);
		} catch(IOException e) {
			throw e;
		} finally {
			if(fos != null) try {fos.close();} catch(IOException e) {}
		}
		
		return file;
	}
		
	private String getSaveFileName(String label, String extName) {
		return label +"_"+ new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + extName;
	}
	private String getSaveFileName(String label) {
		String today = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		
		return today +"_"+ label;
	}
	private void writeFile(MultipartFile multipartFile, String saveFoler, String savefileName) throws IOException {
		File tempDir = new File(saveFoler);
		if(!tempDir.exists())
			tempDir.mkdirs();
		
		byte[] data = multipartFile.getBytes();
		FileOutputStream fos = new FileOutputStream(saveFoler + File.separatorChar + savefileName);
		try {
			fos.write(data);
		} catch(IOException e) {
			throw e;
		} finally {
			fos.close();
		}
	}
	public void deleteBoardFile(String filename, String foldername) {
		String sFullPath = "";
		try {
			sFullPath = ConfigConstants.TEMP_UPLOAD_DIR + foldername + File.separatorChar + filename;
			File file = new File(sFullPath);
			logger.info(" > Board >>> Delete file ::: "+ file.exists() +" > "+ sFullPath);
			file.delete();
		} catch(Exception e) {
			logger.info(" > Board >>> Delete file > fail!! > "+ sFullPath);
			e.printStackTrace();
		}
	}
	
	public Object restoreBoardFile(MultipartFile multipartFile, Object tbBoardVO) {
		Object tbBoardAttchVO = null;
		try {
			// 파일 정보
			String originFilename = multipartFile.getOriginalFilename();
			String extName = originFilename.substring(originFilename.lastIndexOf("."), originFilename.length());
			Long size = multipartFile.getSize();
			
			BeanWrapper boardBean = new BeanWrapperImpl(tbBoardVO);
			String boardId = "";
			try {
				boardId = (String) boardBean.getPropertyValue("boardId");
			} catch(Exception e) {
				try {
					boardId = (String) boardBean.getPropertyValue("inspectId");
				} catch(Exception e1) { }
			}
			
			// 서버에 저장할 파일 이름
			String savefileName = getSaveFileName(boardId);
			
			logger.debug("originFilename : "+ originFilename);
			logger.debug("extensionNmae : "+ extName);
			logger.debug("size : "+ size);
			logger.debug("originFilename : "+ originFilename);
			
			if(extName != null && (extName.toLowerCase().indexOf(".xls") > -1 || extName.toLowerCase().indexOf(".doc") > -1 || extName.toLowerCase().indexOf(".txt") > -1)) {
				
				// file path
				String folderNm = new SimpleDateFormat("yyyyMM").format(new Date());
				String filePath = ConfigConstants.TEMP_UPLOAD_DIR + folderNm;
				logger.debug("filePath : "+ filePath);
				
				writeFile(multipartFile, filePath, savefileName);
				
				// set tbBoardVO
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return tbBoardVO;
	}
}
