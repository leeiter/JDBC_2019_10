package com.biz.oracle.exec;

import com.biz.oracle.service.BookServiceV1;

public class BookPriceEx_01 {

	/*
	 * 두개의 숫자(도서금액)을
	 */
	public static void main(String[] args) {
		
		BookServiceV1 bs = new BookServiceV1();
		bs.searchBookPrice();

		
	}

}