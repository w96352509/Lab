package com.study.springmvc.lab.repository;

import java.util.List;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.study.springmvc.lab.entity.Fund;

@Repository
public class FundDaoImpl implements FundDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Fund> queryAll() {
		String sql = "select f.fid , f.fname , f.createtime , "
				   + "s.sid as fundstocks_sid , s.fid as fundstocks_fid , s.symbol as fundstocks_symbol , s.share as fundstocks_share  "
				   + "from fund f left join fundstock s "
				   + "on f.fid = s.fid";
		ResultSetExtractor<List<Fund>> rs = JdbcTemplateMapperFactory.newInstance().addKeys("fid").newResultSetExtractor(Fund.class);
		return jdbcTemplate.query(sql, rs);
	}

	@Override
	public List<Fund> queryAllPage(int offset) {
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
	public int delete(int fid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Fund fund) {
		// TODO Auto-generated method stub
		return 0;
	}

}
