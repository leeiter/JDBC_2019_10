package com.biz.oracle.exec;

import com.biz.oracle.service.BookServiceV1;

public class BookNameEx_01 {

	public static void main(String[] args) {
		
		BookServiceV1 bs = new BookServiceV1();
		
		// searchBookName() method에
		// true 값을 파라메터로 전달을 하면
		// 반복을 하면
		bs.searchBookName(true);
		System.out.println("도서검색종료");
		
		
		
		// TODO Auto-generated method stub

	}

}
