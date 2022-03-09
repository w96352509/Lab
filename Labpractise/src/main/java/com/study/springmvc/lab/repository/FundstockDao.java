package com.study.springmvc.lab.repository;

import java.util.List;
import java.util.Map;

import com.study.springmvc.lab.entity.Fundstock;

public interface FundstockDao {

	int LIMIT = 5;
	
	//queryAll
	  List<Fundstock> queryAll();
	//queryPage
	  List<Fundstock> querypage(int offset);
	//get
	  Fundstock get(int sid);
	//add
	  int add(Fundstock fundstock);
	//delete
	  int delete(int sid);
	//update
	  int update(Fundstock fundstock);
	//總比數
	  int count();
	//getGroupMap
	  Map<String, Integer> getGroupMap();
}
