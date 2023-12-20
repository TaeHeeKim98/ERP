package kr.happyjob.study.employee.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.happyjob.study.employee.model.EmpTaApplyModel;
import kr.happyjob.study.employee.model.EmpTaApplyRemainVacModel;
import kr.happyjob.study.employee.service.EmpTaApplyService;

@Controller
@RequestMapping("/employee/")
public class EmpTaApplyController {
	
	@Autowired
	EmpTaApplyService empTaApplyService;
	
	// Set logger
	private final Logger logger = LogManager.getLogger(this.getClass());

	// Get class name for logger
	private final String className = this.getClass().toString();
	
	
	
	/**
	 * 휴가리스트 관리 초기화면
	 */
	@RequestMapping("empTaApply.do")
	public String initEmpTaApply(Model model, @RequestParam Map<String, Object> paramMap, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		
		String loginID = (String) session.getAttribute("loginId");
		paramMap.put("loginID", loginID);
		
		String userType = (String) session.getAttribute("userType"); 
		paramMap.put("userType", userType);
		model.addAttribute("userType",userType);
		logger.info("+ Start " + className + ".initEmpTaApply");
		logger.info("   - paramMap : " + paramMap);
		
		logger.info("+ End " + className + ".initEmpTaApply");

		return "employee/empTaApply";
	}
	
	
	/**
	 * 휴가리스트 목록 조회
	 */
	@RequestMapping("listEmpTaApply.do")
	public String listEmpTaApply(Model model, @RequestParam Map<String, Object> paramMap, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		
		logger.info("+ Start " + className + ".listEmpTaApply");
		
		String loginID = (String) session.getAttribute("loginId");
		paramMap.put("loginID", loginID);
		
		int currentPage = Integer.parseInt((String)paramMap.get("currentPage"));	// 현재 페이지 번호
		int pageSize = Integer.parseInt((String)paramMap.get("pageSize"));			// 페이지 사이즈
		int pageIndex = (currentPage-1)*pageSize;									// 페이지 시작 row 번호
		
		paramMap.put("pageIndex", pageIndex);
		paramMap.put("pageSize", pageSize);
		
		logger.info("   - paramMap : " + paramMap);
		
		// empTaAppliyList 목록 조회
		List<EmpTaApplyModel> listEmpTaApplyModel = empTaApplyService.listEmpTaApply(paramMap);
		
		logger.info("   - listEmpTaApplyModel : " + listEmpTaApplyModel);
		
		model.addAttribute("listEmpTaApply", listEmpTaApplyModel);
		
		model.addAttribute("loginID", loginID);
		
		// empTaAppliyList 총 카운트 조회
		int totalCount = empTaApplyService.countListEmpTaApply(paramMap);	
		
		model.addAttribute("totalCntEmpTaApply", totalCount);
		
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("currentPageEmpTaApply",currentPage);
		
		logger.info("+ End " + className + ".listEmpTaApply");
		

		return "/employee/empTaApplyList";
	}	
	
	@RequestMapping("listLoginVac.do")
	public String listLoginVac(Model model, @RequestParam Map<String, Object> paramMap, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		
		logger.info("+ Start " + className + ".listLoginVac");
		
		String loginID = (String) session.getAttribute("loginId");
		paramMap.put("loginID", loginID);
		
		logger.info("   - paramMap : " + paramMap);
		
		// 로그인한 사람의 휴가 일수 조회 
		List<EmpTaApplyRemainVacModel> listLoginVac = empTaApplyService.listLoginVac(paramMap);

		model.addAttribute("listLoginVac", listLoginVac);

		logger.info("+ End " + className + ".listLoginVac");
		
		return "/employee/empTaApplyRemain";
	}	
	
	@RequestMapping("empDtlTaApply.do")
	@ResponseBody
	public Map<String, Object> empDtlTaApply(Model model, @RequestParam Map<String, Object> paramMap, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		
		logger.info("+ Start " + className + ".empDtlTaApply");
		logger.info("   - paramMap : " + paramMap);
		
		String result = "SUCCESS";
		String resultMsg = "조회 되었습니다.";
		
		
		EmpTaApplyModel empDtlTaApplyModel = empTaApplyService.selectEmpDtlApply(paramMap);
		
		logger.info("   - empDtlTaApplyModel : " + empDtlTaApplyModel);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("result", result);
		resultMap.put("resultMsg", resultMsg);
		resultMap.put("empDtlTaApplyModel", empDtlTaApplyModel);

		logger.info("+ End " + className + ".empDtlTaApply");
		
		return resultMap;
	}	
	
	@RequestMapping("empTaLoginInfo.do")
	@ResponseBody
	public Map<String,Object> empTaLoginInfo(Model model, @RequestParam Map<String, Object> paramMap, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception{ 
		
		logger.info("+ Start " + className + ".empTaLoginInfo");
		logger.info("   - paramMap : " + paramMap);
		
		String loginID = (String) session.getAttribute("loginId");
		paramMap.put("loginID", loginID);
		
		EmpTaApplyModel empTaLoginInfoModel = empTaApplyService.empTaLoginInfo(paramMap);
		
		System.out.println("empTaLoginInfoModel:"+empTaLoginInfoModel);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("empTaLoginInfoModel", empTaLoginInfoModel);
		
		logger.info("+ End " + className + ".empTaLoginInfo");
		
		return resultMap;
		
	}
	
	@RequestMapping("saveEmpTaApply.do")
	@ResponseBody
	public Map<String, Object> saveEmpTaApply(Model model, @RequestParam Map<String, Object> paramMap, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
	
		logger.info("+ Start " + className + ".saveEmpTaApply");
		logger.info("   - paramMap : " + paramMap);
		
		String loginID = (String) session.getAttribute("loginId");
		paramMap.put("loginID", loginID);
		
		String action = (String)paramMap.get("action");
		
		System.out.println("saveEmpTaApply paramMap:"+paramMap);
		System.out.println("saveEmpTaApply action:"+action);
		
		
		String result = "SUCCESS";
		String resultMsg = "저장 되었습니다.";
		
		if ("I".equals(action)) {
			// 휴가신청 정보 신규 저장
			empTaApplyService.insertEmpTaApply(paramMap);
		} else if("U".equals(action)) {
			// 휴가신청정보 수정 저장
			empTaApplyService.updateEmpTaApply(paramMap);
		} else {
			result = "FALSE";
			resultMsg = "알수 없는 요청 입니다.";
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", result);
		resultMap.put("resultMsg", resultMsg);
		
		logger.info("+ End " + className + ".saveEmpTaApply");
		
		return resultMap;
		
	}
	
	/*승인대기 휴가정보 조회 */
	@RequestMapping("empSelectWaitingTaApply.do")
	@ResponseBody
	public Map<String, Object> empDtlUpTaApply(Model model, @RequestParam Map<String, Object> paramMap, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
	
		logger.info("+ Start " + className + ".empSelectDtlTaApply");
		logger.info("   - paramMap : " + paramMap);
		
		String loginID = (String) session.getAttribute("loginId");
		paramMap.put("loginID", loginID);
		
		//휴가 단건 조회 
		EmpTaApplyModel empSelectWaitingApplyModel = empTaApplyService.selectWaitingEmpApply(paramMap);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("empSelectWaitingApplyModel", empSelectWaitingApplyModel); 
		
		logger.info("+ End " + className + ".empSelectDtlTaApply");
		
		return resultMap;
		
	}
	
	@RequestMapping("empUpDeldateDtlTaApply.do")
	@ResponseBody
	public Map<String, Object> empVacUpDelTaApply(Model model, @RequestParam Map<String, Object> paramMap, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		

		logger.info("+ Start " + className + ".empVacUpdateTaApply");
		logger.info("   - paramMap : " + paramMap);
		
		String loginID = (String) session.getAttribute("loginId");
		paramMap.put("loginID", loginID);
		
		String action = (String)paramMap.get("action");
		
		System.out.println("empSelectDtlTaApply paramMap:"+paramMap);
		System.out.println("empSelectDtlTaApply action:"+action);
		
		//휴가 단건 조회 
		EmpTaApplyModel empSelectWaitingApplyModel = empTaApplyService.selectWaitingEmpApply(paramMap);
		
		String result = "SUCCESS";
		String resultMsg = "성공적으로 작업이 완료되었습니다.";
		
		if("U".equals(action)) {
			// 휴가신청정보 수정 저장
			empTaApplyService.updateEmpTaApply(paramMap);
		} else if("D".equals(action)){
			// 휴가신청정보 삭제 저장 
			empTaApplyService.deleteEmpTaApply(paramMap);
		}else{
			result = "FALSE";
			resultMsg = "알수 없는 요청 입니다.";
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("empSelectWaitingApplyModel", empSelectWaitingApplyModel); 
		resultMap.put("result", result);
		resultMap.put("resultMsg", resultMsg);
		
		logger.info("+ End " + className + ".empVacUpdateTaApply");
		
		return resultMap;
		
	}
	
	//승인
	@RequestMapping("approve.do")
	@ResponseBody
	public Map<String, Object> approve(Model model, @RequestParam Map<String, Object> paramMap, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		
		logger.info("승인 출발! " + className + ".empVacUpdateTaApply"+ paramMap);
		
		// 로그인한 사람의 휴가 일수 조회 
		EmpTaApplyRemainVacModel vacCnt = empTaApplyService.vacCnt(paramMap);
		
		//사용한 연차
		int vacAnllv = vacCnt.getDva_rest();
		
		//반차 사용 이력
		int bancha = vacCnt.getDva_bancha();
		
		//차감 연차 가져오기
		int annualUse = empTaApplyService.annualUse(paramMap);
		
		//Update 할 연차 사용횟수
		int yearsUse = vacAnllv + annualUse;
		
		//반차 일때
		if(Integer.parseInt((String) paramMap.get("userVacType")) == 1){
			//사용한 반차가 있을시
			if(bancha == 1){
				
				paramMap.put("yearsUse", yearsUse);
				
				empTaApplyService.updateVacbancha(paramMap);
			//사용한 반차가 없을시	
			}else{
				empTaApplyService.updateVacbancha2(paramMap);
			}
		//반차 이외
		}else{
			//승인시 남은연차 차감
			if(vacCnt.getDva_use() != 0){
				
				paramMap.put("yearsUse", yearsUse);
				
				empTaApplyService.updateVac(paramMap);
			}
		}
		
		empTaApplyService.approve(paramMap);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		return resultMap;
		
	}
	
	//반려
	@RequestMapping("reject.do")
	@ResponseBody
	public Map<String, Object> reject(Model model, @RequestParam Map<String, Object> paramMap, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		logger.info("반려 출발! " + className + ".empVacUpdateTaApply"+ paramMap);
		

		empTaApplyService.reject(paramMap);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		return resultMap;
		
	}
}

