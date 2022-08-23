package com.nexicure.nim.controller.base;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nexicure.es.services.crypt.seed.Seed;
import com.nexicure.nim.ConfigConstants;
import com.nexicure.nim.entities.Json4jqGrid;
import com.nexicure.nim.entities.UserBean;
import com.nexicure.nim.entities.vo.ErServiceItemVO;
import com.nexicure.nim.entities.vo.InquiryVO;
import com.nexicure.nim.entities.vo.MuserVO;
import com.nexicure.nim.services.utility.CommUtil;
import com.nexicure.nim.services.utility.SampleData;

@Controller
@RequestMapping("/base/view")
public class ViewController {

	private Logger logger = LogManager.getLogger(ViewController.class);

	 
	@Autowired//(required = false)
	private SampleData sampleData;
	
	@GetMapping()
	public String main(Model model,@RequestParam(value = "type", required = false) String type) {
		
		// menu에서 전달 예정인 데이타 테스트
		model.addAttribute("v_submenu", "기본화면");
		model.addAttribute("v_title", "jqGrid활용");
		
		// 코드를 활용한 화면 구성
		ObjectNode rootNode = new ObjectMapper().createObjectNode();
		List dept = sampleData.getDeptCode();
		List position = sampleData.getPositionCode();
		
		model.addAttribute("dept", dept);
		model.addAttribute("position", position);
		
		CommUtil.setCodeMap(rootNode, dept, "dept");
		CommUtil.setCodeMap(rootNode, position, "position");
		model.addAttribute("v_data", rootNode.toString());
		//
		
		return "base/list";
	}
	
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces="application/json")
	public Json4jqGrid list(@RequestParam(value = "page", required = false) Integer page,
					    @RequestParam(value = "rows", required = false) Integer rows,
					    @ModelAttribute MuserVO inquiryVO,
					    @RequestParam Map<String, String> map) {
		
		 // Constructs page request for current page
        // Note: page number for Spring Data JPA starts with 0, while jqGrid starts with 1
        PageRequest pageRequest = new PageRequest(page - 1, rows);	// 페이징 객체

        //logger.debug("getSearchValue : "+ inquiryVO.getSearchValue());
        //logger.debug("getDept : "+ inquiryVO.getDept());
        //logger.debug("getPosition : "+ inquiryVO.getPosition());
        //logger.debug("map : "+ map);
        //List dump = null;
        Json4jqGrid json4jqGrid = null;
        try {
        	//dump = iTIMServiceManager.getServiceList(inquiryVO);
        	
	        // Construct the grid data that will return as JSON data
	        json4jqGrid = new Json4jqGrid();
	        json4jqGrid.setPage(page);
	        json4jqGrid.setTotal(pageRequest.getPageSize());
	        //json4jqGrid.setRecords(dump.size());					// 페이징 처리 > 전체 데이타 갯수 전달시 사후처리
	        json4jqGrid.setRecords(52);

	        //logger.debug("pageRequest.getPageSize() : "+ pageRequest.getPageSize());
	        
	        List results = new ArrayList<ErServiceItemVO>();
	        
	        //for(int i=pageRequest.getOffset(),j=0; i<dump.size() && j<pageRequest.getPageSize(); i++,j++) {
	        for(int i=pageRequest.getOffset(),j=0; i<52 && j<pageRequest.getPageSize(); i++,j++) {
	        //	ErServiceItemVO erServiceItemVO = (ErServiceItemVO)dump.get(i);
	        	MuserVO muserVO = new MuserVO(i);

		    //	logger.debug("MuserVO : "+ muserVO.getUid());
	        	results.add(muserVO);
	        }
	        
			json4jqGrid.setRows(results);
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e);
		}

        return json4jqGrid;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute MuserVO inquiryVO,
					    @RequestParam Map<String, String> map) {
		
		boolean rslt = true;
        logger.debug("getUid : "+ inquiryVO.getUid());
        logger.debug("getDept : "+ inquiryVO.getDept());
        logger.debug("getPosition : "+ inquiryVO.getPosition());
        logger.debug("map : "+ map);

		try {
			
			// to-do
		} catch(Exception e) {
			rslt = false;
			e.printStackTrace();
			logger.error(e);
		}
		if(rslt) {
			return "T";
		} else {
			return "F";
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/mod", method = RequestMethod.POST)
	public String mod(@ModelAttribute MuserVO inquiryVO,
					    @RequestParam Map<String, String> map) {
		
		boolean rslt = true;
        logger.debug("getUid : "+ inquiryVO.getUid());
        logger.debug("getDept : "+ inquiryVO.getDept());
        logger.debug("getPosition : "+ inquiryVO.getPosition());
        logger.debug("map : "+ map);

		try {
			
			// to-do
		} catch(Exception e) {
			rslt = false;
			e.printStackTrace();
			logger.error(e);
		}
		if(rslt) {
			return "T";
		} else {
			return "F";
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public String del(SecurityContextHolderAwareRequestWrapper secRequest,
						@ModelAttribute MuserVO inquiryVO,
					    @RequestParam Map<String, String> map) {
		 
		boolean auth = secRequest.isUserInRole("ROLE_ADMIN");
		//if (auth) {
		
		boolean rslt = true;
        logger.debug("getUid : "+ inquiryVO.getUid());
        logger.debug("getDept : "+ inquiryVO.getDept());
        logger.debug("getPosition : "+ inquiryVO.getPosition());
        logger.debug("map : "+ map);

		try {
			
			// to-do
		} catch(Exception e) {
			rslt = false;
			e.printStackTrace();
			logger.error(e);
		}
		if(rslt) {
			return "T";
		} else {
			return "F";
		}
	}
	
	
	
	
	/*
	ResponseBody
	RequestMapping(value="/importSvc", method = RequestMethod.POST, produces="multipart/form-data")
	public String importSvc(@RequestParam(value="excelFile") MultipartFile excelFile,
		    		@RequestParam(value = "title", required = false) String title) {

		AttributeValues serviceAttrs = new AttributeValues();
		Seed seed = new Seed();
		
		String filename = excelFile.getOriginalFilename();
		logger.info(excelFile.getOriginalFilename());
		try {
			UserBean userBean = iTIMAuthManager.login(seed.decrypt(ConfigConstants.CONFIG.getString("sys.itim.admin.id")), seed.decrypt(ConfigConstants.CONFIG.getString("sys.itim.admin.pwd")));
			
		
        	if(!excelFile.isEmpty() && !"".equals(excelFile.getOriginalFilename())) {
        		
	            File file = multipartFileManager.saveFile(excelFile, "ImportSvc");
	 

	    		logger.info(file.exists());
	    		logger.info(file.isFile());
	    		logger.info(file.canRead());
	            if (!file.exists() || !file.isFile() || !file.canRead()) {
	                throw new IOException("File not exist!!");
	            }
	 
	            String extName = filename.substring(filename.lastIndexOf("."), filename.length());
	            
	            importExcelManager.init();
	            if(extName.indexOf("xlsx") > -1) {
		            importExcelManager.readExcelXLSX(file);
	            } else {
	            	importExcelManager.readExcelXLS(file);
	            }
	            ArrayList<HashMap> excelData = importExcelManager.getDataList();
	            
	            for(HashMap map : excelData) {
		            for( Object key : map.keySet() ){
		            	logger.info( String.format("key : %s, value : %s", key, map.get(key)) );
		                
		                serviceAttrs.put(new AttributeValue((String)key, (String)map.get(key)));
						serviceAttrs.put(new AttributeValue("eritdiurl", ConfigConstants.CONFIG.getString("itim.service.posix.eritdiurl")));
						serviceAttrs.put(new AttributeValue("erpassword", seed.decrypt(ConfigConstants.CONFIG.getString("sys.itim.service.os.pwd"))));	// PASSWORD
		            
		            }
					
		            AndFilter searchFilter = new AndFilter();
		            searchFilter.and(new EqualsFilter("erservicename", (String)map.get("erservicename")));
		            logger.info(searchFilter.toString());
		            if(iTIMServiceManager.getService(searchFilter) == null) {
		            	hHRSyncService.regLinuxService((String)map.get("erservicename"), serviceAttrs, userBean);
		            }
	            }
	            


        	}
        } catch (Exception e) {
            e.printStackTrace();
    		return "FAIL";
        }


		return "SUCCESS";
	}
	
	RequestMapping(value="/proc/registry", method = RequestMethod.POST)
	public String registry(HttpServletRequest request, Model model, TbBoardVO tbBoardVO,
		    		@RequestParam(value = "file", required = false) List<MultipartFile> files) throws Exception {
	
		boolean modify = false;
		if(tbBoardVO.getBoardId() != null && !"".equals(tbBoardVO.getBoardId())) modify = true;
		if(tbBoardVO.getTitle() != null && !"".equals(tbBoardVO.getTitle())) {
			MultipartFileManager multipartFileManager = new MultipartFileManager();
			
			if(!modify) {
				int seq = tbBoard.getNextSeq();
				tbBoardVO.setBoardId("NTC"+ StringUtils.leftPad(seq+"", 6, "0") );
				tbBoardVO.setSeq(seq);
			}
			
			for(MutipartFile file : files) {
				if(!file.isEmpty()) {
					TbBoardAttchVO tbBoardAttchVO = multipartFileManager.restoreBoardFile(file, tbBOardVO);
					if(tbBoardAttchVO != null) {
						tbBoard.insertTBBoardAttch(tbBoardAttchVO, request, this.getClass().getName());
					}
				}
			}
			
			if(tbBoardVO.getAttachFileNm() != null) {
				for(String fileNm : tbBoardVO.getAttachFileNm() ) {
					TbBoardAttchVO tbBoardAttchVO = new TbBoardAttchVO();
					tbBoardAttchVO.setBoardId(tbBoardVO.getBoardId());
					tbBoardAttchVO.setFileNm(fileNm);
					
					ArrayList<TbBoardAttchVO> attchVO = (ArrayList) tbBoard.selectSpecificFile(tbBoardAttchVO);
					for(TbBoardAttchVO tempVO : attchVO) {
						multipartFileManager.deleteBoardFile(tempVO.getFileNm(), tempVO.getFolderNm());
					}
					tbBoard.deleteTBBoardAttch(tbBoardAttchVO, request, this.getClass().getName());
				}
			}
			
			if(!modify) {
				tbBOardVO.setBoardType("01");	// 게시판 구분 > 01 : 공지사항, 02 : FAQ
				tbBoard.insertTBBoard(tbBOardVO, request, this.getClass().getName());
			} else {
				tbBoard.updateTBBoard(tbBOardVO, request, this.getClass().getName());
			}
		}
		
		return "redirect:/support/notice";
	}
		
	 */
}
