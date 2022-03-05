package com.study.springmvc.lab.service;

import java.util.List;

import com.study.springmvc.lab.entity.Fund;

public interface FundService {
   
	int Limit();
	
	List<Fund> queryAll();
   
    List<Fund> queryFunds(int offset);

    Fund get(int fid);
    
    int add(Fund fund);
    
    int upadate(Fund fund);
    
    int delete(int fid);
}