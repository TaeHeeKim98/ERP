package kr.happyjob.study.employee.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.happyjob.study.employee.model.EmpTaCalendarModel;

@Service
public class EmpTaCalendarServiceImpl implements EmpTaCalendarService{

	@Autowired
	kr.happyjob.study.employee.dao.EmpTaCalendarDao EmpTaCalendarDao;
	
	@Override
	public List<EmpTaCalendarModel> empTaCalendarList(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return EmpTaCalendarDao.empTaCalendarList(paramMap);
	}

	@Override
	public List<EmpTaCalendarModel> empTaCalendarDetailList(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return EmpTaCalendarDao.empTaCalendarDetailList(paramMap);
	}

	

}
