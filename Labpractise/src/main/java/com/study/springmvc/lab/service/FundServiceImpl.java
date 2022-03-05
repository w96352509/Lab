package com.study.springmvc.lab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springmvc.lab.entity.Fund;
import com.study.springmvc.lab.repository.FundDao;

@Service
public class FundServiceImpl implements FundService {

	@Autowired
	private FundDao fundDao;
	
	@Override
	public List<Fund> queryAll() {
		
		return  fundDao.queryAll();
	}

	@Override
	public List<Fund> queryFunds(int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Fund get(int fid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(Fund fund) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int upadate(Fund fund) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int fid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int Limit() {
		
		return fundDao.LIMIT;
	}

	

}
