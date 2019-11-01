package com.biz.iolist.service;

import java.util.List;
import java.util.Scanner;

import com.biz.iolist.config.DBConnection;
import com.biz.iolist.dao.IolistDao;
import com.biz.iolist.persistence.IolistDTO;

public class IolistServiceV2 {
	
	protected IolistDao iolistDao;
	Scanner scanner;
	
	public IolistServiceV2() {
		this.iolistDao = DBConnection.getSqlSessionFactory().openSession(true).getMapper(IolistDao.class);
		scanner = new Scanner(System.in);
	}
	
	public void iolistInsert() {
		System.out.println("======================================================");
		System.out.println("상품이름");
		
		
		
	}
	
	
	// public List<IolistDTO> selectAll();
	// public IolistDTO findById(long io_seq);
	// public int insert(IolistDTO iolistDTO);
	// public int update(IolistDTO iolistDTO);
	// public int delete(long io_seq);
	

}