package com.study.springmvc.lab.service;

import java.util.List;

import com.study.springmvc.lab.entity.Fundstock;

public interface FundstockService {

	int Limit();
	
	List<Fundstock> queryAll();
	
	List<Fundstock> queryPage(int offset);
	
	Fundstock get(int sid);
	
	//add
	  int add(Fundstock fundstock);
	//delete
	  int delete(int sid);
	//update
	  int update(Fundstock fundstock);
	//
	  int count();
	
}
