package service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import model.Category;
import model.Ingredient;
import model.Member;
import model.Rcp;
import model.RcpContent;
import mybatis.AbstractRepository;

@Component
public class MybatisRcpDaoMysql{
	private final String namespace = "mybatis.RcpMapper";
	
	@Autowired
	public AbstractRepository opendb;

	public void insertRcp(Rcp rcp) {

		SqlSession sqlSession = opendb.getSqlSessionFactory().openSession();
		int rcpNum;
		
		try {
			String statement = namespace + ".insert_max";
			rcpNum = sqlSession.selectOne(statement);
			rcp.setRcpnum(rcpNum); // rcpNum max값을 여기에 저장
			
			String statement2 = namespace + ".insert1";
			
			sqlSession.insert(statement2, rcp);
			sqlSession.commit();
			
		} finally {
			sqlSession.close();
		}
	}
	
	public void insertIngredient(Ingredient ingre) {
		SqlSession sqlSession = opendb.getSqlSessionFactory().openSession();
		int rcpNum;
		
		try {
			String statement = namespace + ".insert_max";
			rcpNum = sqlSession.selectOne(statement);
			ingre.setRcpNum(rcpNum); 
			
			String statement2 = namespace + ".insertIngredient";
			
			sqlSession.insert(statement2, ingre);
			sqlSession.commit();
			
		} finally {
			sqlSession.close();
		}
	}
	
	public void insertRcpContent(RcpContent rcpContent) {
		SqlSession sqlSession = opendb.getSqlSessionFactory().openSession();
		int rcpNum;
		
		try {
			String statement = namespace + ".insert_max";
			rcpNum = sqlSession.selectOne(statement);
			rcpContent.setRcpNum(rcpNum);
			
			String statement2 = namespace + ".insertRcpContent";
			
			sqlSession.insert(statement2, rcpContent);
			sqlSession.commit();
			
		} finally {
			sqlSession.close();
		}
	}
	
	public List<Category> getCategory() {
		SqlSession sqlSession=opendb.getSqlSessionFactory().openSession();
		List<Category> category=null;
		
		try{
			String statement=namespace+".getCategory";         
			category=sqlSession.selectList(statement);
		}finally{
			sqlSession.close();
		}

		return category;
	}
	
	public int rcpAllCount(){
		SqlSession sqlSession=opendb.getSqlSessionFactory().openSession();
		int count;
		
		try{
			String statement=namespace+".rcpAllCount"; 
			count=sqlSession.selectOne(statement);
		}finally{
			sqlSession.close();
		}

		return count;
	}
	
	public List<Rcp> rcpAllList(){
		SqlSession sqlSession=opendb.getSqlSessionFactory().openSession();
		List<Rcp> rcpAllList=null;
		String statement;
		
		try{
			statement=namespace+".rcpAllList";         
			rcpAllList=sqlSession.selectList(statement);
		}finally{
			sqlSession.close();
		}

		return rcpAllList;
	}
	
	public Rcp rcpContent(int rcpnum){
		SqlSession sqlSession=opendb.getSqlSessionFactory().openSession();
		Rcp rcpContent=null;
		String statement;
		
		try{
			statement=namespace+".rcpContent";         
			rcpContent=sqlSession.selectOne(statement, rcpnum);
		}finally{
			sqlSession.close();
		}

		return rcpContent;
	}
}
