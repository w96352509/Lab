package com.study.springmvc.lab.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springmvc.lab.entity.Fundstock;
import com.study.springmvc.lab.repository.FundstockDao;

@Service
public class FundstockServiceImpl implements FundstockService {

	@Autowired
	private FundstockDao fundstockDao;

	@Override
	public List<Fundstock> queryAll() {
		
		return fundstockDao.queryAll();
	}

	@Override
	public List<Fundstock> queryPage(int offset) {
		if (offset < 0) {
			return fundstockDao.queryAll();
		}
		return fundstockDao.querypage(offset);
	}

	@Override
	public Fundstock get(int sid) {
		
		return fundstockDao.get(sid);
	}

	@Override
	public int add(Fundstock fundstock) {
		
		return fundstockDao.add(fundstock);
	}

	@Override
	public int delete(int sid) {
		
		return fundstockDao.delete(sid);
	}

	@Override
	public int update(Fundstock fundstock) {
		
		return fundstockDao.update(fundstock);
	}

	@Override
	public int count() {
		
		return fundstockDao.count();
	}

	@Override
	public int Limit() {
		return fundstockDao.LIMIT;
	}

	@Override
	public Map<String, Integer> getMapgroup() {
		
		return fundstockDao.getGroupMap();
	}
	
	

}
