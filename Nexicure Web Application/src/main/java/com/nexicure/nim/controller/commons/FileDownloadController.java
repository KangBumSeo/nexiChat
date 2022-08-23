package com.nexicure.nim.controller.commons;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nexicure.nim.ConfigConstants;

public class FileDownloadController {

	private Logger logger = LogManager.getLogger(FileDownloadController.class);

	
	@RequestMapping(value = "/download/file", method= RequestMethod.GET)
	public ResponseEntity<Resource> downloadFile(
			@RequestParam(value = "filename", required = true) String filename) throws Exception{
		
		File file = new File(ConfigConstants.TEMP_UPLOAD_DIR + File.separatorChar + filename);
		if(filename!=null && file.exists()
				&& (filename.toLowerCase().endsWith(".xls") || filename.toLowerCase().endsWith(".xlsx")
				 || filename.toLowerCase().endsWith(".txt") 
				 )
			) {
			
			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
			headers.add("Content-Disposition", "attachment; filename="+ URLEncoder.encode(filename, "UTF-8") +";");
			headers.add("Content-Transfer-Encoding", "binary;");
			headers.add("Pragma", "no-cache");
			headers.add("Expires", "0");
			return ResponseEntity.ok()
					.headers(headers)
					.contentLength(file.length())
					.contentType(new MediaType(MediaType.APPLICATION_OCTET_STREAM, StandardCharsets.UTF_8))
					.body(resource);
		} else {
			return null;
		}
		
	}
	
	/*
	RequestMapping(value = "/download/boardFile", method= RequestMethod.GET)
	public ResponseEntity<Resource> downloadBoardFile(
			@RequestParam(value = "code", required = true) String code,
			@RequestParam(value = "filename", required = true) String filename) throws Exception{
		
		TbBoradAttchVO inqueryVO = new TbBoradAttchVO();
		inqueryVO.setBoardId(code);
		inqueryVO.setFileNm(filename);
		ArrayList fileInfo = (ArrayList) tbBoard.selectSpecificFile(inqueryVO);
		if(fileInfo.size() == 1) {
			TbBoradAttchVO tbBoradAttchVO = (TbBoradAttchVO) fileInfo.get(0);
			String sFullPath = ConfigConstants.TEMP_UPLOAD_DIR + tbBoradAttchVO.getFolderNm() + File.separatorChar + tbBoradAttchVO.getFileNm();
			File file = new File(sFullPath);
			
			String filename_orgin = tbBoradAttchVO.getFileNmOrg();
			if(filename_orgin!=null && file.exists() 
				&& (filename_orgin.toLowerCase().lastIndexOf(".xls")>0 || filename_orgin.toLowerCase().lastIndexOf(".doc")>0 || filename_orgin.toLowerCase().lastIndexOf(".txt")>0 )) {
				InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
				
				HttpHeaders headers = new HttpHeaders();
				headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
				headers.add("Content-Disposition", "attachment; filename="+ URLEncoder.encode(filename_orgin, "UTF-8") +";");
				headers.add("Content-Transfer-Encoding", "binary;");
				headers.add("Pragma", "no-cache");
				headers.add("Expires", "0");
				return ResponseEntity.ok()
						.headers(headers)
						.contentLength(file.length())
						.contentType(new MediaType(MediaType.APPLICATION_OCTET_STREAM, StandardCharsets.UTF_8))
						.body(resource);
			} else {
				return null;
			}
		} else {
			return null;
		}
		
	}
	*/
	
}
