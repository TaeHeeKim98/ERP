package kr.happyjob.study.accounting.dao;

import java.util.List;
import java.util.Map;

import kr.happyjob.study.accounting.model.AccTitleModel;
import kr.happyjob.study.system.model.DeptMgmtModel;

public interface AccTitleDao {
	
	// 리스트 조회
	public List<AccTitleModel> accTitleList (Map<String, Object> paramMap)throws Exception ;
	// 목록 카운트 조회
	public int totalCnt(Map<String, Object> paramMap)throws Exception ;
	/** 상세 계정 목록 조회 */
	public List<AccTitleModel> selectacclargelist(Map<String, Object> paramMap) throws Exception;
	
	/** 상세 계정 목록 조회 */
	public List<AccTitleModel> selectaccsamalllist(Map<String, Object> paramMap) throws Exception;
	
	//단건조회
	public AccTitleModel selectAccTitle(Map<String, Object> paramMap)throws Exception ;
	
	public int updateAccTitlereadcnt(Map<String, Object> paramMap) throws Exception;
	
	public int insertdtlAccTitle(Map<String, Object> paramMap) throws Exception;
	
	public int updateAccTitle(Map<String, Object> paramMap) throws Exception;

	public int changeB(Map<String, Object> paramMap) throws Exception;
	
}
