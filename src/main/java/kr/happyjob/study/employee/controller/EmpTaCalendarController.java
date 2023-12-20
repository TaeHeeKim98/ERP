package kr.happyjob.study.employee.controller;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.happyjob.study.employee.model.EmpTaCalendarModel;
import kr.happyjob.study.employee.service.EmpTaCalendarService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/employee/")
public class EmpTaCalendarController {
		// Set logger
		private final Logger logger = LogManager.getLogger(this.getClass());
		// Get class name for logger
		private final String className = this.getClass().toString();
		
		@Autowired
		EmpTaCalendarService EmpTaCalendarService;
		
		/* 근태현황조회 초기화면 */
		@RequestMapping("empTaCalendar.do")
		public String EmpTaCalendar(Model model, @RequestParam Map<String, Object> paramMap, HttpServletRequest request,
				HttpServletResponse response, HttpSession session) throws Exception {
			logger.info("+ Start " + className + ".EmpTaCalendar");
			
			return "employee/empTaCalendar";
		}//근태현황조회 초기화면 끝
		
		/* 근태현황조회*/
		@RequestMapping(value = "empTaCalendarList.do", method = RequestMethod.POST, produces = "application/text; charset=UTF-8")
		@ResponseBody
		public String EmpTaCalendarList(Model model, @RequestParam Map<String, Object> paramMap, HttpServletRequest request,
				HttpServletResponse response, HttpSession session){

			logger.info("+ Start " + className + ".empTaList");
			logger.info("   - paramMap : " + paramMap);
			JSONObject object = new JSONObject();
			JSONArray array = new JSONArray();
			
			List<EmpTaCalendarModel> empTaCalendarList = EmpTaCalendarService.empTaCalendarList(paramMap);
			logger.info("   - empTaList : " + empTaCalendarList);
			
			for (EmpTaCalendarModel dto : empTaCalendarList) {
				JSONObject EmpTaCalendarModel = new JSONObject();
				EmpTaCalendarModel.put("vac_stat", dto.getVac_stat());
				EmpTaCalendarModel.put("vac_cnt", dto.getVac_cnt());
				EmpTaCalendarModel.put("vac_rdate", dto.getVac_rdate());
				EmpTaCalendarModel.put("vac_sdate", dto.getVac_sdate());
				EmpTaCalendarModel.put("detail_name", dto.getDetail_name());
				EmpTaCalendarModel.put("name", dto.getName());
				EmpTaCalendarModel.put("vac_type", dto.getVac_type());
				
				
				/* 원래는 add가 아닌 put이었다*/
				array.add(EmpTaCalendarModel);
			}
			
			object.put("empTaCalendarList", array);
			
			logger.info("+ End " + className + ".empTaList");
			
			return object.toString();
		}
		
		/* 근태현황조회*/
		@RequestMapping(value = "empTaCalendarDetailList.do")
		@ResponseBody
		public Map<String, Object>  EmpTaCalendarDetailList(Model model, @RequestParam Map<String, Object> paramMap, HttpServletRequest request,
				HttpServletResponse response, HttpSession session){
			logger.info("+ Start " + className + ".empTaList");
			logger.info("   - paramMap : " + paramMap);
			
			List<EmpTaCalendarModel> empTaCalendarDetailList = EmpTaCalendarService.empTaCalendarDetailList(paramMap);
			
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("empTaCalendarDetailList", empTaCalendarDetailList);
			
			return resultMap;
		}
}
