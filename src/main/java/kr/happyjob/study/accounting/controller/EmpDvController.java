package kr.happyjob.study.accounting.controller;


import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.happyjob.study.accounting.model.EmpDvLoginInfoModel;
import kr.happyjob.study.accounting.model.EmpDvModel;
import kr.happyjob.study.accounting.service.EmpDvService;


@Controller
@RequestMapping("/accounting/")
public class EmpDvController {
	
	@Autowired
	EmpDvService empDvService;
	
	// Set logger
	private final Logger logger = LogManager.getLogger(this.getClass());

	// Get class name for logger
	private final String className = this.getClass().toString();
	
	
	
	/**
	 * 공통코드 관리 초기화면
	 */
	@RequestMapping("empDv.do")
	public String initEmpDv(Model model, @RequestParam Map<String, Object> paramMap, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		
		logger.info("+ Start " + className + ".initEmpDv");
		logger.info("   - paramMap : " + paramMap);
		
		logger.info("+ End " + className + ".initEmpDv");

		return "accounting/empDv";
	}
	
	
	 /* 지출결의서 목록 조회*/
	@RequestMapping("listEmpDv.do")
	public String listEmpDv(Model model, @RequestParam Map<String, Object> paramMap, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		
		logger.info("+ Start " + className + ".listEmpDv");
		logger.info("   - paramMap : " + paramMap);
		
		String loginID = (String) session.getAttribute("loginId");
		paramMap.put("loginID", loginID);
		
		System.out.println("listEmpDv paramMap"+paramMap);
		
		int currentPage = Integer.parseInt((String)paramMap.get("currentPage"));	// 현재 페이지 번호
		int pageSize = Integer.parseInt((String)paramMap.get("pageSize"));			// 페이지 사이즈
		int pageIndex = (currentPage-1)*pageSize;												// 페이지 시작 row 번호
				
		paramMap.put("pageIndex", pageIndex);
		paramMap.put("pageSize", pageSize);
		
		// 공통 그룹코드 목록 조회
		List<EmpDvModel> listEmpDvModel = empDvService.listEmpDv(paramMap);
		model.addAttribute("listEmpDv", listEmpDvModel);
		
		logger.info("   - listEmpDvModel : " + listEmpDvModel);
		
		// 공통 그룹코드 목록 카운트 조회
		int totalCount = empDvService.countListEmpDv(paramMap);
		model.addAttribute("totalCntEmpDv", totalCount);
		
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("currentPageEmpDv",currentPage);
		
		logger.info("+ End " + className + ".listEmpDv");
		

		return "accounting/empDvList";
	}	

	@RequestMapping("selectEmpDvLoginInfo.do")
	@ResponseBody
	public  Map<String, Object> selectEmpDvLoginInfo(Model model, @RequestParam Map<String, Object> paramMap, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
	
		logger.info("+ Start " + className + ".selectEmpDvLoginInfo");
		logger.info("   - paramMap : " + paramMap);
		
		String loginID = (String) session.getAttribute("loginId");
		paramMap.put("loginID", loginID);
		
		EmpDvLoginInfoModel empDvLoginInfoModel = empDvService.empDvLoginInfo(paramMap);
		
		System.out.println("empDvLoginInfoModel:"+empDvLoginInfoModel);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("empDvLoginInfoModel", empDvLoginInfoModel);
		
		logger.info("+ End " + className + ".selectEmpDvLoginInfo");
		
		return resultMap;
		
	}
	
	@RequestMapping("saveempdvfile.do")
	@ResponseBody
	public Map<String, Object> saveempdvfile(Model model, @RequestParam Map<String, Object> paramMap, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		
		logger.info("+ Start " + className + ".saveempdvfile");
		logger.info("   - paramMap : " + paramMap);
		
		String sucessresult = "SUCCESS";
		String failresult = "FAIL";
		
		String action = (String) paramMap.get("action");
        
		int sqlresult = 0;
		
		empDvService.insertempdvfile(paramMap,request);
		
		Map<String, Object> returnvalue = new HashMap<String, Object>();
		
		if(sqlresult == -1) {
			returnvalue.put("result", failresult);
		} else {
			returnvalue.put("result", sucessresult);
		}
		
		
		logger.info("+ Stop " + className + ".saveempdvfile");
		
		return returnvalue;
	}	
	

	/*지출 결의 단건 조회 */
	@RequestMapping("empDvDtlModal.do")
	@ResponseBody
	public Map<String, Object> empDvDtlModal(Model model, @RequestParam Map<String, Object> paramMap, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		
		logger.info("+ Start " + className + ".empDvDtlModal");
		logger.info("   - paramMap : " + paramMap);

		System.out.println("empDvDtlModal paramMap!!!!!!!!!!!!!!!!!!!!!!"+paramMap);
		
		String result = "SUCCESS";
		String resultMsg = "조회 되었습니다.";
		
		// 지출결의 단건 조회
		EmpDvModel EmpDvOneModel = empDvService.selectOneEmpDv(paramMap);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", result);
		resultMap.put("resultMsg", resultMsg);
		resultMap.put("EmpDvOneModel", EmpDvOneModel);
		
		logger.info("+ End " + className + ".empDvDtlModal");
		
		return resultMap;
		
	} 
	
	/*지출 결의 수정  및 삭제 */
	@RequestMapping("empDvUpdateDelModal.do")
	@ResponseBody
	public Map<String, Object> empDvUpdateDelModal(Model model, @RequestParam Map<String, Object> paramMap, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		
		logger.info("+ Start " + className + ".empDvUpdateModal");
		logger.info("   - paramMap : " + paramMap);

		System.out.println("empDvUpdateDelModal Controller paramMap@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+paramMap);
		System.out.println("empDvUpdateDelModal Controller request@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+request);
		
		
		String result = "SUCCESS";
		String resultMsg = "처리 되었습니다.";
		
		String sucessresult = "SUCCESS";
		String failresult = "FAIL";
		
		String action = (String) paramMap.get("action");
		
		System.out.println("empDvUpdateDelModal action:"+action);
		
		int sqlresult = 0;
		
		if("U".equals(action)) {  // 수정
			empDvService.updateEmpDvModal(paramMap,request);
		} else if("D".equals(action)) {  // 삭제
			empDvService.deleteEmpDvModal(paramMap);
		}
		
		Map<String, Object> returnvalue = new HashMap<String, Object>();
		
		if(sqlresult == -1) {
			returnvalue.put("result", failresult);
		} else {
			returnvalue.put("result", sucessresult);
		}
		
		returnvalue.put("resultMsg", resultMsg); 
		
		logger.info("+ End " + className + ".empDvUpdateModal");
		
		return returnvalue;
		
	} 
	
	/*파일 다운로드  */
	@RequestMapping("empdvdownloadfile.do")
	public void downloadfile(Model model, @RequestParam Map<String, Object> paramMap, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
	
		logger.info("+ Start " + className + ".downloadfile");
		logger.info("   - paramMap : " + paramMap);

		System.out.println("EmpDvController downloadfile paramMap"+ paramMap);
		
		
		EmpDvModel empDvModel = empDvService.selectOneEmpDv(paramMap);

		System.out.println("EmpDvController downloadfile empDvModel"+ empDvModel);

		//물리경로 조회해서 담기 
		String file = empDvModel.getAtt_mul();
		byte fileByte[] = FileUtils.readFileToByteArray(new File(file));
		
	    response.setContentType("application/octet-stream");
	    response.setContentLength(fileByte.length);
	    response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(empDvModel.getAtt_ori(),"UTF-8")+"\";");
	    response.setHeader("Content-Transfer-Encoding", "binary");
	    response.getOutputStream().write(fileByte);

	}
	
}

