package kr.happyjob.study.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.happyjob.study.system.dao.DeptMgmtDao;
import kr.happyjob.study.system.model.DeptMgmtModel;

@Service
public class DeptMgmtServiceImpl implements DeptMgmtService{
	
	@Autowired
	DeptMgmtDao deptMgmtDao;

	@Override
	public List<DeptMgmtModel> deptManagementList(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		List<DeptMgmtModel> deptManagementList = deptMgmtDao.deptManagementList(paramMap);
		return deptManagementList;
	}

	@Override
	public int totalCnt(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		int totalCnt = deptMgmtDao.totalCnt(paramMap);
		return totalCnt;
	}

	@Override
	public DeptMgmtModel selectDept(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		DeptMgmtModel DeptMgmtModel = deptMgmtDao.selectDept(paramMap);
		return DeptMgmtModel;
	}

	@Override
	public int insertDept(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return deptMgmtDao.insertDept(paramMap);
	}

	@Override
	public int updateDept(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return deptMgmtDao.updateDept(paramMap);
	}

	@Override
	public int deleteDept(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return deptMgmtDao.deleteDept(paramMap);
	}

}
