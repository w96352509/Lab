package com.study.springmvc.lab.repository;

import java.util.List;

import com.study.springmvc.lab.entity.Fund;

public interface FundDao {

	int LIMIT = 5;
	
	List<Fund> queryAll();
	
	List<Fund> queryAllPage(int offset);
	
	Fund get(int fid);
	
	int add(Fund fund);
	
	int delete(int fid);
	
	int update(Fund fund);
	
}
