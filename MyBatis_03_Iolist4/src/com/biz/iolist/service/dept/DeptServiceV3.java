package com.biz.iolist.service.dept;

import java.util.List;

import com.biz.iolist.persistence.DeptDTO;


public class DeptServiceV3 extends DeptServiceV2{

	/*
	 * 키보드에서 정보를 입력받아 DB에 추가하기
	 * 1. 거래처코드 : 자동생성
	 * 	기존 코드가 있으면 추가 금지
	 * 2. 상호는 같은 상호가 있더라도
	 * 	대표자명이 다르면 입력 가능하도록
	 */
	public void deptInsert() {
		
		System.out.println("============================================");
		
		String strDCode;
		while(true) {
			System.out.print("상품 코드(Enter:자동생성, -Q:quit) >> ");
			strDCode = scanner.nextLine();
			if(strDCode.equals("-Q")) break;
			
			if(strDCode.trim().isEmpty()) {
				String strTMPDCode = deptDao.getMaxDCode();
				
				int intDCode = Integer.valueOf(strTMPDCode.substring(1));
				intDCode++;
				strDCode = strTMPDCode.substring(0,1);
				strDCode += String.format("%04d", intDCode);
				
				System.out.println("생성된 코드 : " + strDCode);
				System.out.print("사용하시겠습니까?(Enter:Yes, NO:No) >> ");
				String strYesNo = scanner.nextLine();
				if(strYesNo.equals("NO")) break;
				if(strYesNo.trim().isEmpty()) break;
				else continue;
			}
			
			strDCode = strDCode.toUpperCase();
			if(deptDao.findById(strDCode) != null) {
				System.out.println("이미 등록된 코드입니다.");
				continue;
			}
			break;
		}

		String strDName;
		while(true) {
			System.out.println("-------------------------------------------");
			System.out.print("상호명(-Q:quit) >> ");
			strDName = scanner.nextLine();
			if(strDName.equals("-Q")) break;
			
			if(strDName.trim().isEmpty()) {
				System.out.println("상호명은 반드시 입력을 해야함");
				continue;
			}
			
			List<DeptDTO> deptList = deptDao.findByName(strDName);
			if(strDName != null) {
				System.out.println("===================================");
				System.out.println("같은 상호명이 있음");
				System.out.println("-----------------------------------");
				this.viewList(deptList);
				System.out.println("===================================");
				System.out.print("사용하시겠습니까?(Enter:Yes, NO:다시 입력) >> ");
				String YesNo = scanner.nextLine();
				if(YesNo.trim().isEmpty()) break;
				continue;
			}
			break;
		}

		System.out.print("대표자명(-Q:quit) >> ");
		String strDCEO = scanner.nextLine();
		if(strDCEO.equals("-Q")) return;
			
		System.out.print("전화번호(-Q:quit) >> ");
		String strDTel = scanner.nextLine();
		if(strDTel.equals("-Q")) return;
			
		System.out.print("주소(-Q:quit) >> ");
		String strDAddr = scanner.nextLine();
		if(strDAddr.equals("-Q")) return;
		
		DeptDTO deptDTO = DeptDTO.builder()
				.d_code(strDCode)
				.d_name(strDName)
				.d_ceo(strDCEO)
				.d_tel(strDTel)
				.d_addr(strDAddr)
				.build();
		
		int ret = deptDao.insert(deptDTO);
		if(ret > 0) System.out.println("상품등록성공");
		else System.out.println("상품등록 실패");

	}

}