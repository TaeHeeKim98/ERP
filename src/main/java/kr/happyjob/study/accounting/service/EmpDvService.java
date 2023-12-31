package kr.happyjob.study.accounting.service;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.happyjob.study.accounting.model.EmpDvLoginInfoModel;
import kr.happyjob.study.accounting.model.EmpDvModel;

public interface EmpDvService {

	//지출결의 목록 조회 
	public List<EmpDvModel> listEmpDv(Map<String, Object> paramMap) throws Exception;
	
	//지출결의 리스트 목록 카운트 조회 
	public int countListEmpDv(Map<String, Object> paramMap) throws Exception;
	
	//로그인한 사람 정보 조회 
	public EmpDvLoginInfoModel empDvLoginInfo(Map<String, Object> paramMap) throws Exception;
	
	//지출결의서 Insert 조회 
	public void insertempdvfile(Map<String, Object> paramMap, HttpServletRequest request) throws Exception;
	
	//지출결의 상세조회(단건조회) 
	public EmpDvModel selectOneEmpDv (Map<String, Object> paramMap) throws Exception;
	
	public void updateEmpDvModal(Map<String, Object> paramMap, HttpServletRequest request) throws Exception; 

	public void deleteEmpDvModal(Map<String, Object> paramMap) throws Exception; 
	
}
