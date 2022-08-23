package com.nexicure.nim.controller.commons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nexicure.nim.entities.Json4jqGrid;
import com.nexicure.nim.entities.vo.ErManagedItemVO;
import com.nexicure.nim.entities.vo.InquiryVO;
import com.nexicure.nim.entities.vo.McodedescVO;

@Controller
@RequestMapping("/commons/codedesc")
public class CodeDescController {
	private Logger logger = LogManager.getLogger(CodeDescController.class);
	
	
	@GetMapping("/main")
	public String main(Model model) throws Exception{
		return  "sysmgmt/mcodedesc/list"  ;
	}
	
	
	  @ResponseBody
	  @RequestMapping(value = "/list", method =
	  RequestMethod.POST,produces="application/json") 
	  public Json4jqGrid list(@RequestParam(value ="page", required = false) Integer page,	  
	  @RequestParam(value = "rows", required = false) Integer rows,
			 @ModelAttribute InquiryVO inquiryVO) {
	
		  List dump = null;
		  Json4jqGrid json4jqGrid = null;
		  		
		  try {
			//  dump = iTIMCodeManager.getCodeDescList(inquiryVO);
			  
			  // Construct the grid data that will return as JSON data Json4jqGrid
			  PageRequest  pageRequest = new PageRequest(page - 1, rows);	// 페이징 객체
			  
			  json4jqGrid = new Json4jqGrid(); 			  
			  json4jqGrid.setPage(page);
			  json4jqGrid.setTotal(pageRequest.getPageSize());
			  json4jqGrid.setRecords(dump.size());					// 페이징 처리 > 전체 데이타 갯수 전달시 사후처리
			 
			//  List results = new ArrayList<SimCodeDescVO>();
		      
		      for(int i=pageRequest.getOffset(),j=0; i<dump.size() && j<pageRequest.getPageSize(); i++,j++) {
		  //  	  SimCodeDescVO simcodeDescVO = (SimCodeDescVO)dump.get(i);
		      	
				
				
		  //    	results.add(simcodeDescVO);
		      }
			  
		  //    	json4jqGrid.setRows(results);
					
		  } catch(Exception e) {
				e.printStackTrace();
				logger.error(e);
		  }
		  return json4jqGrid;
	  
	  }
	  
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = "/codename", method = {RequestMethod.GET,
	 * RequestMethod.POST}, produces="application/json") public List<SimCodeVO>
	 * getCodenameList(Model model) {
	 * 
	 * 
	 * List results = null;
	 * 
	 * 
	 * results = iTIMCodeManager.getCodenameList();
	 * 
	 * 
	 * return results;
	 * 
	 * }
	 * 
	 */
	 
	  @ResponseBody
		@RequestMapping(value = "/codeMap", method = {RequestMethod.GET, RequestMethod.POST}, produces="application/json")
		public Map<String, List> getCodeMap(@RequestParam(value = "codelist", required = true) String codeList) {

			Map<String, List> map = new HashMap();
			String codeGubun[] = codeList.split(";");

			try {
				for(int i=0; i<codeGubun.length; i++) {
		//			map.put(codeGubun[i], iTIMCodeManager.getCodeGubunList(codeGubun[i]));
				}	
				System.out.println("codeMap="+map);
			} catch(Exception e) {
				e.printStackTrace();
				logger.error(e);
			}
			return map;
		}
	  
	@ResponseBody
	@RequestMapping(value = "/codeMapT", method = {RequestMethod.GET, RequestMethod.POST}, produces="application/json")
	public Map<String, List> getCodeSampleMap(@RequestParam(value = "codelist", required = true) String codeList) {

		Map<String, List> map = new HashMap();
		String codeGubun[] = codeList.split(";");

		McodedescVO mcodedescVO = new McodedescVO();
		List list = new ArrayList();
		
		mcodedescVO.setCode("D001");
		mcodedescVO.setCodeName("ITO테스트");
		list.add(mcodedescVO);
		mcodedescVO.setCode("D002");
		mcodedescVO.setCodeName("SCM테스트");
		list.add(mcodedescVO);
		map.put("dept", list);
		
		list.clear();
		

		mcodedescVO.setCode("P001");
		mcodedescVO.setCodeName("테스트");
		list.add(mcodedescVO);
		mcodedescVO.setCode("P003");
		mcodedescVO.setCodeName("테스트2");
		list.add(mcodedescVO);
		mcodedescVO.setCode("P002");
		mcodedescVO.setCodeName("테스트3");
		list.add(mcodedescVO);
		map.put("position", list);
		
		try {
		//	for(int i=0; i<codeGubun.length; i++) {
		//		map.put(codeGubun[i], iTIMCodeManager.getCodeGubunList(codeGubun[i]));
		//	}	
			logger.debug("codeMap : "+map);
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return map;
	}
	
	

}
