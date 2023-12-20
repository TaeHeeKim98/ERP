package kr.happyjob.study.accounting.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.happyjob.study.accounting.dao.AccTitleDao;
import kr.happyjob.study.accounting.model.AccTitleModel;

@Service
public class AccTitleServiceImpl implements AccTitleService{

	@Autowired
	AccTitleDao accTitleDao;
	
	@Override
	public List<AccTitleModel> accTitleList(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		List<AccTitleModel> AccTitleList = accTitleDao.accTitleList(paramMap);
		return AccTitleList;
	}

	@Override
	public int totalCnt(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		int totalCnt = accTitleDao.totalCnt(paramMap);
		return totalCnt;
	}

	@Override
	public List<AccTitleModel> selectacclargelist(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		List<AccTitleModel> acclist =accTitleDao.selectacclargelist(paramMap);
		return acclist;
	}

	@Override
	public List<AccTitleModel> selectaccsamalllist(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		List<AccTitleModel> acclist = accTitleDao.selectaccsamalllist(paramMap);
		return acclist;
	}

	@Override
	public AccTitleModel selectAccTitle(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		AccTitleModel AccTitleModel = accTitleDao.selectAccTitle(paramMap);
		return AccTitleModel;
	}

	@Override
	public int updateAccTitlereadcnt(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return accTitleDao.updateAccTitlereadcnt(paramMap);
	}

	@Override
	public int updateAccTitle(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub

    	String acc_nm = (String)paramMap.get("acc_nm");
    	String acc_nm_ori = (String)paramMap.get("acc_nm_ori");
    	
    	if(!acc_nm.equals(acc_nm_ori)){//대분류 명이 변경되었을 시에 탄다
    		int changeB = accTitleDao.changeB(paramMap);
    	}
    	
		int updateAccTitle = accTitleDao.updateAccTitle(paramMap);//계정 소분류 업데이트
		return updateAccTitle;
	}

	@Override
	public int insertdtlAccTitle(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return accTitleDao.insertdtlAccTitle(paramMap);
	}

}
