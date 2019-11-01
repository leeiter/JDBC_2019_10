package com.biz.iolist.service;

import java.util.List;

import com.biz.iolist.persistence.ProductDTO;

public class ProductServiceV3 extends ProductServiceV2 {

	public void menuProduct() {
		System.out.println("==================================================");
		System.out.println("대한 쇼핑몰 상품관리 시스템 v3");
		System.out.println("==================================================");
		System.out.println("1.등록 2.수정 3. 삭제 ");
	}
	public void insertProduct( ) {
		
		System.out.print("입력할 상품코드 >> ");
		String strPCode = scanner.nextLine();
		
		// if()
		
		List<ProductDTO> proList = proDao.selectAll();
		for(ProductDTO dto : proList) {
			System.out.println(dto.toString());
		}
		

		
	}
	
	public void deleteProduct() {
		
		
	}
	
	
	
}
