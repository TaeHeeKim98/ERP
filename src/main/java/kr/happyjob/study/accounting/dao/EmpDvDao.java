package kr.happyjob.study.accounting.dao;

import java.util.List;
import java.util.Map;

import kr.happyjob.study.accounting.model.EmpDvLoginInfoModel;
import kr.happyjob.study.accounting.model.EmpDvModel;


public interface EmpDvDao {

	public List<EmpDvModel> listEmpDv(Map<String, Object> paramMap) throws Exception;

	public int countListEmpDv(Map<String, Object> paramMap) throws Exception;

	EmpDvLoginInfoModel empDvLoginInfo(Map<String, Object> paramMap);
	
	public void insertempdv(Map<String, Object> paramMap) throws Exception;
	
	public void insertempdvfile(Map<String, Object> paramMap) throws Exception;
	
	public void upinnerinsertfile(Map<String, Object> paramMap) throws Exception;
	
	EmpDvModel selectOnenotice(Map<String, Object> paramMap) throws Exception;
	
	/*수정,삭제  파일 첨부 및 expend정보 관련 */
	
	//expend 테이블 update
	public int updateexpend(Map<String, Object> paramMap) throws Exception;
	
	//attachment 테이블 update
	public int updatefileempdv(Map<String, Object> paramMap) throws Exception;
	
	//attachment 테이블 delete
	public int expendfiledelete(Map<String, Object> paramMap) throws Exception;
	
	//expend 테이블 delete
	public int expenddelete(Map<String, Object> paramMap) throws Exception;
	
	
	
	
	
	
	
}
