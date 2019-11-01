package com.biz.iolist.service;

import java.util.List;
import java.util.Scanner;

import com.biz.iolist.config.DBConnection;
import com.biz.iolist.dao.ProductDao;
import com.biz.iolist.persistence.ProductDTO;

public class ProductServiceV1 {

	protected ProductDao proDao;
	protected Scanner scanner;
	
	public ProductServiceV1() {
		
		this.proDao = DBConnection.getSqlSessionFactory().openSession(true).getMapper(ProductDao.class);
		scanner = new Scanner(System.in);
	}
	
	public void proUpdate() {
//		
//		List<ProductDTO> proList = proDao.selectAll();
//		for(ProductDTO dto : proList) {
//			System.out.println(dto.toString());
//		}
		System.out.println("==================================");
		System.out.print("수정할 상품코드");
		String strPCode = scanner.nextLine();
		strPCode = strPCode.toUpperCase();
		
		ProductDTO proDTO = proDao.findById(strPCode);
		System.out.println("=================================");
		System.out.printf("상품코드 : %s\n", proDTO.getP_code());
		System.out.printf("상품이름 : %s\n", proDTO.getP_name());
		System.out.printf("매입단가 : %d\n", proDTO.getP_iprice());
		System.out.printf("매출단가 : %d\n", proDTO.getP_oprice());
		
		String strVAT = proDTO.getP_vat().equals("1") ? "과세" : "면세";
		System.out.printf("과세여부(1:과세,0:면세 : %s)\n", strVAT);
		System.out.println("=================================");
		
		System.out.printf("변경할 상품명(%s) >> ", proDTO.getP_name());
		String strName = scanner.nextLine();
		
		if(!strName.trim().isEmpty()) {
			proDTO.setP_name(strName);
		}
		
		System.out.printf("변경할 매입단가(%d) >> ", proDTO.getP_iprice());
		String strIPrice = scanner.nextLine();
		
		/*
		 * 만약 매입단가를 입력하지 않고 그냥 enter만 눌렀다면
		 * Integer.valueOF(strIprice)에서 Exerption이 발생할 것이다.
		 * try{} 감싸진 코드에서 Exceprion이 발생하면
		 * 나머지 코드는 무시되고 cathchh() jump 한다.
		 * 
		 * 이런 기능ㅇ르 역 이용하여
		 * 값을 입력하지 않거나 , 문자열등ㅇ르 포함하면
		 */
		try {
			proDTO.setP_iprice(Integer.valueOf(strIPrice));
		} catch (Exception e) {
			// TODO: handle exception
		}

		System.out.printf("변경할 매출단가(%d) >> ", proDTO.getP_oprice());
		String strOPrice = scanner.nextLine();
		
		try {
			proDTO.setP_iprice(Integer.valueOf(strOPrice));
		} catch (Exception e) {
			// TODO: handle exception
		}

		/*
		 * proDTO의 필드변수들은
		 * 각 항목을 입력받을때 Enter만 입력했다면
		 * 원래 DB tavle에서 가져온 값이 그대로 유지 될 ㅓㄳ이고
		 * 
		 * ]값을 입력했다면
		 * 새로운 값으로 변경되어 있을 것이다.
		 */
		
		int ret = proDao.update(proDTO);
		if(ret > 0) {
			System.out.println("데이터변경완료");
		} else {
			System.out.println("데이터실패완료");
		}
		
		System.out.println(proDao.findById(strPCode).toString());
		
		
		
		
		
	}
	
	
	
}