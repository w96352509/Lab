package com.study.springmvc.lab.repository;

import java.sql.ResultSet;
import java.util.List;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.study.springmvc.lab.entity.Fund;
import com.study.springmvc.lab.entity.Fundstock;

@Repository
public class FundstockDaoImpl implements FundstockDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Fundstock> queryAll() {
		return queryAllcase03();
	}
	
	private List<Fundstock> queryAllcase01() {
		String sql = "select s.sid , s.fid , s.symbol , s.share from fundstock s";
		return jdbcTemplate.query(sql,  new BeanPropertyRowMapper<Fundstock>(Fundstock.class));
	}
	
	private List<Fundstock> queryAllcase02(){
		String sql = "select s.sid , s.fid , s.symbol , s.share from fundstock s";
		RowMapper<Fundstock> rm  = (ResultSet rs, int rowNum)->{
			Fundstock fundstock = new Fundstock();
			fundstock.setFid(rs.getInt("fid"));
			fundstock.setSid(rs.getInt("sid"));
			fundstock.setSymbol(rs.getString("symbol"));
			fundstock.setShare(rs.getInt("share"));
			String sql2  = "select f.fid , f.fname , f.createtime from fund f";
			List<Fund> funds = jdbcTemplate.query(sql2, new BeanPropertyRowMapper<Fund>(Fund.class));
			fundstock.setFund(funds.get(0));
			return fundstock;
		};
		    return jdbcTemplate.query(sql, rm);
	}

	private List<Fundstock> queryAllcase03(){
		String sql = "select s.sid, s.fid, s.symbol, s.share , "
				   + "f.fid as fund_fid , f.fname as fund_fname , f.createtime as fund_createtime  "
				   + "from fundstock s left join fund f " + "on f.fid = s.fid";
		ResultSetExtractor<List<Fundstock>> rs = JdbcTemplateMapperFactory
				                           .newInstance()
				                           .addKeys("sid")
				                           .newResultSetExtractor(Fundstock.class);
		return jdbcTemplate.query(sql, rs);
	}
	
	@Override
	public List<Fundstock> querypage(int offset) {
		String sql = "select s.sid, s.fid, s.symbol, s.share , "
				   + "f.fid as fund_fid , f.fname as fund_fname , f.createtime as fund_createtime  "
				   + "from fundstock s left join fund f " + "on f.fid = s.fid";
		sql += String.format(" limit %d offset %d ", FundstockDao.LIMIT, offset);
		ResultSetExtractor<List<Fundstock>> rs = 
				JdbcTemplateMapperFactory
                .newInstance()
                .addKeys("sid")
                .newResultSetExtractor(Fundstock.class);
		return jdbcTemplate.query(sql, rs);
	}

	@Override
	public Fundstock get(int sid) {
		String sql = "select s.sid , s.fid , s.symbol , s.share from fundstock s where s.sid=? ";
		Fundstock fundstock = jdbcTemplate.queryForObject(
				sql, 
				new BeanPropertyRowMapper<Fundstock>(Fundstock.class),
				sid);
		String sql2 = "select f.fid , f.fname , f.createtime from fund f where f.fid=?";
		Fund fund =jdbcTemplate.queryForObject(sql2, new BeanPropertyRowMapper<Fund>(Fund.class),fundstock.getFid());
		fundstock.setFund(fund);
		return fundstock ;
	}

	@Override
	public int add(Fundstock fundstock) {
		String sql = "insert into fundstock(fid,symbol,share) values(?,?,?)";
		int ronumber = jdbcTemplate.update(sql,fundstock.getFid() , fundstock.getSymbol() , fundstock.getShare());
		return ronumber;
	}

	@Override
	public int delete(int sid) {
		String sql = "delete from fundstock where sid=?";
		int rownumber = jdbcTemplate.update(sql , sid);
		return rownumber;
	}

	@Override
	public int update(Fundstock fundstock) {
		String sql = "update fundstock set fid=?, symbol=?, share=? where sid=?";
		int rowcount = jdbcTemplate.update(sql, fundstock.getFid(), fundstock.getSymbol(), fundstock.getShare(), fundstock.getSid());
		return rowcount;
	}

	@Override
	public int count() {
		String sql = "select count(*) from fundstock s;";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

}
