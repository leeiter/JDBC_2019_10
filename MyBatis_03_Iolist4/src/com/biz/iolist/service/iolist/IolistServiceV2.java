package com.biz.iolist.service.iolist;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.biz.iolist.config.DBConnection;
import com.biz.iolist.dao.IolistDao;
import com.biz.iolist.persistence.DeptDTO;
import com.biz.iolist.persistence.IolistDTO;
import com.biz.iolist.persistence.ProductDTO;
import com.biz.iolist.service.dept.DeptServiceV3;
import com.biz.iolist.service.pro.ProductServiceV4;

public class IolistServiceV2 extends IolistServiceV1 {
	
	protected ProductServiceV4 proService = new ProductServiceV4();
	protected DeptServiceV3 deptService = new DeptServiceV3();
	
	@Override
	protected void insert() {
		System.out.println("=============================================================");
		System.out.println("매입매출등록");
		System.out.println("=============================================================");
		
		// 값이 없는 new IolistDTO 생성
		IolistDTO iolistDTO = new IolistDTO();
		
		while(true) {
			System.out.print("거래구분 입력(1:매입, 2.매출, -1:종료) >> ");
			String strInout = scanner.nextLine();
			try {
				int intInout = Integer.valueOf(strInout);
				if(intInout < 0) break;
				
				if(intInout == 1) {
					iolistDTO.setIo_inout("매입");
				} else if(intInout == 2){
					iolistDTO.setIo_inout("매출");
				} else {
					System.out.println("매입, 매출 구분을 다시 선택하세요.");
					continue;
				}
				iolistDTO.setIo_inout(strInout);
				
			} catch (Exception e) {
				System.out.println("매입 매출구분을 다시 입력해 주세요.");
				continue;
			}
			break;
		}
		
		if(iolistDTO.getIo_inout().isEmpty()) return;
		
		while(true) {
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String curDate = sd.format(date);
			
			System.out.printf("거래일자(%s)", curDate);
			String strDate = scanner.nextLine();
			
			if(strDate.trim().isEmpty()) {
				iolistDTO.setIo_date(curDate);
			} else {
				try {
					sd.parse(strDate);
				} catch (ParseException e) {
					// TODO: handle exception
					System.out.println("날짜형식이 잘못되었습니다.");
					continue;
				}
				iolistDTO.setIo_date(strDate);
			}
			break;
		}
		
		while(true) {
			System.out.print("거래처명 입력(-Q:quit) >> ");
			String strDName = scanner.nextLine();
			if(strDName.equals("-Q")) break;
			List<DeptDTO> deptList = deptDao.findByName(strDName);
			if(deptList != null && deptList.size() > 0) {
				
				for(DeptDTO dto : deptList) {
					System.out.println(dto.toString());
				}
				System.out.println("----------------------------------------");
				System.out.print("거래처코드 입력 >> ");
				String strDCode = scanner.nextLine();
				
				DeptDTO deptDTO = deptDao.findById(strDCode);
				if(deptDTO == null) {
					System.out.println("거래처 코드 없음");
					continue;
				} else {
					iolistDTO.setIo_dcode(strDCode);
				}
			} else {
				continue;
			}
			break;
		}
		if(iolistDTO.getIo_dcode().isEmpty()) return;
		
		while(true) {
			System.out.print("상품명 입력(-Q:quit) >> ");
			String strPName = scanner.nextLine();
			if(strPName.equals("-Q")) break;
			
			List<ProductDTO> proList = proDao.findByName(strPName);
			
			if(proList == null && proList.size() < 1) {
				System.out.println("찾는 상품이 없음");
				continue;
			} else {
				for(ProductDTO dto : proList) {
					System.out.println(dto.toString());
				}
				System.out.print("상품코드 >> ");
				String strPCode = scanner.nextLine();
				
				ProductDTO proDTO = proDao.findById(strPCode);
				if(proDTO == null) {
					System.out.println("상품코드를 확인하세요");
					continue;
				} else {
					iolistDTO.setIo_pcode(strPCode);
					int intPrice = iolistDTO.getIo_inout().equals("매입") ? proDTO.getP_iprice() : proDTO.getP_oprice();
					iolistDTO.setIo_price(intPrice);
				}
			}
			break;
		}
		if(iolistDTO.getIo_pcode().isEmpty()) return;
		
		
		while(true) {
			System.out.printf("단가 입력(%d) >> ", iolistDTO.getIo_price());
			String strPrice = scanner.nextLine();
			try {
				int price = Integer.valueOf(strPrice);
				iolistDTO.setIo_price(price);
			} catch (Exception e) {
				// TODO: handle exception
			}
			break;
			
		}
		
		while(true) {
			System.out.print("수량 입력 >> ");
			String strQty = scanner.nextLine();
			
			try {
				int intQty = Integer.valueOf(strQty);
				iolistDTO.setIo_qty(intQty);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("수량은 숫자로만 입력!!");
				continue;
			}
			break;
		}
		
		int inttotal = iolistDTO.getIo_price() * iolistDTO.getIo_qty();
		iolistDTO.setIo_total(inttotal);
		
		int ret = iolistDao.insert(iolistDTO);
		if(ret > 0) System.out.println("데이터 추가 완료");
		else System.out.println("데이터 추가 실패");
	}
	
}