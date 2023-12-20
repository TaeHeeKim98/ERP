package kr.happyjob.study.employee.service;

import java.util.List;
import java.util.Map;

import kr.happyjob.study.employee.model.EmpTaCalendarModel;

public interface EmpTaCalendarService {
	public List<EmpTaCalendarModel> empTaCalendarList(Map<String, Object> paramMap);
	public List<EmpTaCalendarModel> empTaCalendarDetailList(Map<String, Object> paramMap);

}
