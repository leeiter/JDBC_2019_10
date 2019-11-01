package com.biz.rent.dao;

import java.util.List;

import com.biz.rent.persistence.RentBookDTO;

public interface RentBookDao {
	
	public List<RentBookDTO> selectAll();
	
	public RentBookDTO findById(long rent_seq);
	
	public int insert(RentBookDTO rentBookDTO);
	public int update(RentBookDTO rentBookDTO);
	public int delete(long rent_seq);

}