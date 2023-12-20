package kr.happyjob.study.system.controller;

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

import kr.happyjob.study.system.model.DeptMgmtModel;
import kr.happyjob.study.system.service.DeptMgmtService;

@Controller
@RequestMapping("/system/")
public class DeptMgmtController {
	
	@Autowired
	DeptMgmtService deptMgmtService;

	// Set logger
	private final Logger logger = LogManager.getLogger(this.getClass());

	// Get class name for logger
	private final String className = this.getClass().toString();
	
	
	/**
	 *  초기화면
	 */
	@RequestMapping("deptManagement.do")
	public String deptManagement(Model model, @RequestParam Map<String, Object> paramMap, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		
		logger.info("+ Start " + className + ".deptManagement");
		logger.info("   - paramMap : " + paramMap);
		
		logger.info("+ End " + className + ".deptManagement");

		return "system/deptManagement";
	}
	
	    //리스트 출력
		@RequestMapping("deptManagementList.do")		
		public String deptManagementList(Model model, @RequestParam Map<String, Object> paramMap, 
				HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
			
			logger.info("   - paramMap : " + paramMap);
			String deptname = (String) paramMap.get("deptname");
			
			int currentPage = Integer.parseInt((String) paramMap.get("currentPage")); // 현재페이지
		    int pageSize = Integer.parseInt((String) paramMap.get("pageSize"));
		    int pageIndex = (currentPage - 1) * pageSize;
			
			paramMap.put("pageIndex", pageIndex);
			paramMap.put("pageSize", pageSize);
			paramMap.put("deptname", deptname);
			
			// 공지사항 목록 조회
			List<DeptMgmtModel> deptManagementList = deptMgmtService.deptManagementList(paramMap);	
			model.addAttribute("deptManagementList", deptManagementList);
			
			// 목록 수 추출해서 보내기
			int totalCnt = deptMgmtService.totalCnt(paramMap);
			
			model.addAttribute("totalCnt", totalCnt);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("currentPage", currentPage);
			
		    return "system/deptManagementList";
		}	
		
		//단건조회
		@RequestMapping("selectDept.do")
		@ResponseBody
		public Map<String, Object> selectDept(Model model, @RequestParam Map<String, Object> paramMap, HttpServletRequest request,
				HttpServletResponse response, HttpSession session) throws Exception {
			
			logger.info("+ Start " + className + ".selectDept");
			logger.info("   - paramMap : " + paramMap);
			
			String result = "SUCCESS";
			String resultMsg = "조회 되었습니다.";
			
			DeptMgmtModel DeptMgmtModel = deptMgmtService.selectDept(paramMap);
					
			Map<String, Object> resultMap = new HashMap<String, Object>();
			
			resultMap.put("result", result);
			resultMap.put("resultMsg", resultMsg);		
			resultMap.put("DeptMgmtModel", DeptMgmtModel);
			
			logger.info("+ Stop " + className + ".selectDept");
			
			return resultMap;
		}	
		
		//등록, 수정
		@RequestMapping("saveDept.do")
		@ResponseBody
		public Map<String, Object> saveDept(Model model, @RequestParam Map<String, Object> paramMap, HttpServletRequest request,
				HttpServletResponse response, HttpSession session) throws Exception {
			
			logger.info("+ Start " + className + ".saveDept");
			logger.info("   - paramMap : " + paramMap);
			
			String action = (String)paramMap.get("action");

			String result = "SUCCESS";
			String resultMsg = "저장 되었습니다.";
			
			if("I".equals(action)){
				deptMgmtService.insertDept(paramMap);
			}else if("U".equals(action)){
				deptMgmtService.updateDept(paramMap);
			}else {
				result = "FALSE";
				resultMsg = "알수 없는 요청 입니다.";
			}
			
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("result", result);
			resultMap.put("resultMsg", resultMsg);
			
			logger.info("+ Stop " + className + ".saveDept");
			
			return resultMap;
		}	
		
		//삭제
		@RequestMapping("deleteDept.do")
		@ResponseBody
		public Map<String, Object> deleteDept(Model model, @RequestParam Map<String, Object> paramMap, HttpServletRequest request,
				HttpServletResponse response, HttpSession session) throws Exception {
			
			logger.info("+ Start " + className + ".deleteDept");
			logger.info("   - paramMap : " + paramMap);

			String result = "SUCCESS";
			String resultMsg = "삭제 되었습니다.";
			
			// 그룹코드 삭제
			deptMgmtService.deleteDept(paramMap);
			
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("result", result);
			resultMap.put("resultMsg", resultMsg);
			
			logger.info("+ End " + className + ".deleteDept");
			
			return resultMap;
		}
		
}
