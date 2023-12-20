package kr.happyjob.study.accounting.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.happyjob.study.accounting.dao.EmpDvDao;
import kr.happyjob.study.accounting.model.EmpDvLoginInfoModel;
import kr.happyjob.study.accounting.model.EmpDvModel;
import kr.happyjob.study.common.comnUtils.FileUtilCho;


@Service
public class EmpDvServiceImpl implements EmpDvService {
	
	@Autowired
	EmpDvDao empDvDao;
	
	// Root path for file upload 
	@Value("${fileUpload.rootPath}")
	private String rootPath;
	
	@Value("${fileUpload.virtualRootPath}")
	private String virtualRootPath;

	@Value("${fileUpload.noticePath}")
	private String noticePath;
	
	
	@Override
	public List<EmpDvModel> listEmpDv(Map<String, Object> paramMap) throws Exception {
		
		List<EmpDvModel> listEmpDv = empDvDao.listEmpDv(paramMap);
		
		return listEmpDv;
	}

	@Override
	public int countListEmpDv(Map<String, Object> paramMap) throws Exception {
		
		int totalCount = empDvDao.countListEmpDv(paramMap);
		
		return totalCount;
		
	}
	
	/*로그인한 사람 기본 정보 조회 */
	public EmpDvLoginInfoModel empDvLoginInfo(Map<String, Object> paramMap) throws Exception{
		
		EmpDvLoginInfoModel empDvLoginInfoModel = empDvDao.empDvLoginInfo(paramMap);
		
		return empDvLoginInfoModel; 
		
	}

	public void insertempdvfile(Map<String, Object> paramMap, HttpServletRequest request) throws Exception {
	 	
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
				
		//파일저장
		String itemFilePath = noticePath + File.separator; // 업로드 실제 경로 조립 (무나열생성)
		FileUtilCho fileUtil = new FileUtilCho(multipartHttpServletRequest, rootPath, itemFilePath);
		Map<String, Object> fileInfo = fileUtil.uploadFiles(); // 실제 업로드 처리
		
		System.out.println("fileInfo @@@@@@@@@@@@@@@@@@@" +fileInfo);
		
		String logicalpath = "/serverfile" + File.separator + noticePath + File.separator + fileInfo.get("file_nm");
		
		fileInfo.put("loc_file_loc", logicalpath);		
		
		//지출 결의 테이블에 insert
		empDvDao.insertempdv(paramMap); 
		
		paramMap.put("fileinf", fileInfo);
		
		if(fileInfo.get("file_nm") == null) {
			paramMap.put("fileyn", "N");
		} else {
			paramMap.put("fileyn", "Y");
			
			//파일 테이블에 insert
			empDvDao.insertempdvfile(paramMap);
		}
		
		return;
	}

	@Override
	public EmpDvModel selectOneEmpDv(Map<String, Object> paramMap) throws Exception {
		
		EmpDvModel empDvOneModel = empDvDao.selectOnenotice(paramMap);
		
		return empDvOneModel;
		
	}

 	public void updateEmpDvModal(Map<String, Object> paramMap, HttpServletRequest request) throws Exception {
		
 		System.out.println("EmpDvSericeImpl updateEmpDvModal @@@@@@@@@@@@@@@@@@@@@@@@@@@");
 		
 		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		
 		System.out.println("multipartHttpServletRequest ::::::::::::::"+ multipartHttpServletRequest);
 		System.out.println("EmpDvServiceImpl paramMap::::::::::::::"+ paramMap);
		
 		//1. 정보 상세조회 
		EmpDvModel empDvOneModel = empDvDao.selectOnenotice(paramMap);
		
		//2. 상세 조회 한 부분에 파일명이 없으면(파일이 없으면) 
		if(empDvOneModel.getAtt_ori()==null || empDvOneModel.getAtt_ori()==""){
			System.out.println("update 파일의 if 부분::::::::::::::");
			
		}else{ //2. 파일이 존재하면 
			
			System.out.println("update의 파일 삭제 else 부분:::::::::::");
			
			// 3. 파일 물리 경로에서 파일 삭제
			String Fullpath = empDvOneModel.getAtt_mul();
			File orgfile = new File(Fullpath);
			
			orgfile.delete();
			
			// tb_attachment 테이블에서 파일 att_no 삭제
			empDvDao.expendfiledelete(paramMap);
			
		}
		
		//tb_expend 테이블 업데이트(성공) 
		empDvDao.updateexpend(paramMap);
		
		//파일저장
		String itemFilePath = noticePath + File.separator; // 업로드 실제 경로 조립 (무나열생성)
		FileUtilCho fileUtil = new FileUtilCho(multipartHttpServletRequest, rootPath, itemFilePath);
		Map<String, Object> fileInfo = fileUtil.uploadFiles(); // 실제 업로드 처리
		
		String logicalpath = "/serverfile" + File.separator + noticePath + File.separator + fileInfo.get("file_nm");
		
		fileInfo.put("loc_file_loc", logicalpath);		
		
		paramMap.put("fileinf", fileInfo);
		
		System.out.println("update 부분의 fileInfo fileInfo.get(file_nm):::::::::"+fileInfo.get("file_nm"));
		
		if(fileInfo.get("file_nm") == null) {
			System.out.println("update if 부분의 fileInfo file_nm:::::::::");
			
			paramMap.put("fileyn", "N");
		} else {
			System.out.println("update else 부분의 fileInfo file_nm:::::::::");
			paramMap.put("fileyn", "Y");
			empDvDao.upinnerinsertfile(paramMap);
		}

		return;
	}
    
    public void deleteEmpDvModal(Map<String, Object> paramMap) throws Exception {
		
    	System.out.println("EmpDvSericeImpl deleteEmpDvModal @@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
    	// 첨부된 파일이 있으면
    	// 실제 파일 삭제
    	// tb_attachment att_no 삭제
        
        EmpDvModel empDvOneModel = empDvDao.selectOnenotice(paramMap);
        
		if(empDvOneModel.getAtt_ori()!= null) {
			// 파일 삭제
			String Fullpath = empDvOneModel.getAtt_mul();
			File orgfile = new File(Fullpath);
			orgfile.delete();
			
			// tb_attachment att_no 삭제
			empDvDao.expendfiledelete(paramMap);						
		}
		
		empDvDao.expenddelete(paramMap);	
    	
    	// 공통
    	// tb_announce  att_no 삭제
		//empDvDao.deleteexpend(paramMap);
    	
		return;
	}    

}
