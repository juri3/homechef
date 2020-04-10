package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import model.Category;
import model.Division;
import model.Ingredient;
import model.Rcp;
import model.RcpContent;
import mybatis.AbstractRepository;

@Component
public class MybatisRcpDaoMysql {
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
		SqlSession sqlSession = opendb.getSqlSessionFactory().openSession();
		List<Category> category = null;

		try {
			String statement = namespace + ".getCategory";
			category = sqlSession.selectList(statement);
		} finally {
			sqlSession.close();
		}

		return category;
	}

	public List<Category> getCategory2(int cateNum) {
		SqlSession sqlSession = opendb.getSqlSessionFactory().openSession();
		List<Category> category = null;

		try {
			String statement = namespace + ".getCategory2";
			category = sqlSession.selectList(statement, cateNum);
		} finally {
			sqlSession.close();
		}

		return category;
	}

	public List<Category> getCategory3(int cateNum) {
		SqlSession sqlSession = opendb.getSqlSessionFactory().openSession();
		List<Category> category = null;

		try {
			String statement = namespace + ".getCategory3";
			category = sqlSession.selectList(statement, cateNum);
		} finally {
			sqlSession.close();
		}

		return category;
	}

	public List<Division> getDivision() {
		SqlSession sqlSession = opendb.getSqlSessionFactory().openSession();
		List<Division> division = null;

		try {
			String statement = namespace + ".getDivision";
			division = sqlSession.selectList(statement);
		} finally {
			sqlSession.close();
		}

		return division;
	}

	public List<Division> getDivision2(int cateNum) {
		SqlSession sqlSession = opendb.getSqlSessionFactory().openSession();
		List<Division> division = null;

		try {
			String statement = namespace + ".getDivision2";
			division = sqlSession.selectList(statement, cateNum);
		} finally {
			sqlSession.close();
		}

		return division;
	}

	public List<Division> getDivision3(int cateNum) {
		SqlSession sqlSession = opendb.getSqlSessionFactory().openSession();
		List<Division> division = null;

		try {
			String statement = namespace + ".getDivision3";
			division = sqlSession.selectList(statement, cateNum);
		} finally {
			sqlSession.close();
		}

		return division;
	}

	public List<Rcp> rcpAllList() {
		SqlSession sqlSession = opendb.getSqlSessionFactory().openSession();
		List<Rcp> rcpAllList = null;
		String statement;

		try {
			statement = namespace + ".rcpAllList";
			rcpAllList = sqlSession.selectList(statement);
		} finally {
			sqlSession.close();
		}

		return rcpAllList;
	}

	public int rcpAllCount() {
		SqlSession sqlSession = opendb.getSqlSessionFactory().openSession();
		int count;

		try {
			String statement = namespace + ".rcpAllCount";
			count = sqlSession.selectOne(statement);
		} finally {
			sqlSession.close();
		}

		return count;
	}

	public int rcpAllCount2(int cateNum) {
		SqlSession sqlSession = opendb.getSqlSessionFactory().openSession();
		int count;

		try {
			String statement = namespace + ".rcpAllCount2";
			count = sqlSession.selectOne(statement, cateNum);
		} finally {
			sqlSession.close();
		}

		return count;
	}

	public List<Rcp> rcpAllList2(int cateNum) {
		SqlSession sqlSession = opendb.getSqlSessionFactory().openSession();
		List<Rcp> rcpAllList = null;
		String statement;

		try {
			statement = namespace + ".rcpAllList2";
			rcpAllList = sqlSession.selectList(statement, cateNum);
		} finally {
			sqlSession.close();
		}

		return rcpAllList;
	}

	public int rcpAllCount3(int cateNum) {
		SqlSession sqlSession = opendb.getSqlSessionFactory().openSession();
		int count;

		try {
			String statement = namespace + ".rcpAllCount3";
			count = sqlSession.selectOne(statement, cateNum);
		} finally {
			sqlSession.close();
		}

		return count;
	}

	public List<Rcp> rcpAllList3(int cateNum) {
		SqlSession sqlSession = opendb.getSqlSessionFactory().openSession();
		List<Rcp> rcpAllList = null;
		String statement;

		try {
			statement = namespace + ".rcpAllList3";
			rcpAllList = sqlSession.selectList(statement, cateNum);
		} finally {
			sqlSession.close();
		}

		return rcpAllList;
	}

	public Rcp rcpContent(int rcpnum) {
		SqlSession sqlSession = opendb.getSqlSessionFactory().openSession();
		Rcp rcpContent = null;
		String statement;

		try {
			statement = namespace + ".rcpContent";
			rcpContent = sqlSession.selectOne(statement, rcpnum);

			statement = namespace + ".addReadcount";
			sqlSession.update(statement, rcpnum);
			sqlSession.commit();

		} finally {
			sqlSession.close();
		}

		return rcpContent;
	}

	public List<RcpContent> rcpContent2(int rcpnum) {
		SqlSession sqlSession = opendb.getSqlSessionFactory().openSession();
		List<RcpContent> rcpContent2 = null;
		String statement;

		try {
			statement = namespace + ".rcpContent2";
			rcpContent2 = sqlSession.selectList(statement, rcpnum);
		} finally {
			sqlSession.close();
		}

		return rcpContent2;
	}

	public List<Ingredient> rcpContent3(int rcpnum) {
		SqlSession sqlSession = opendb.getSqlSessionFactory().openSession();
		List<Ingredient> rcpContent3 = null;
		String statement;

		try {
			statement = namespace + ".rcpContent3";
			rcpContent3 = sqlSession.selectList(statement, rcpnum);
		} finally {
			sqlSession.close();
		}

		return rcpContent3;
	}

	public int checkScrap(int loginNum, int rcpnum) {
		SqlSession sqlSession = opendb.getSqlSessionFactory().openSession();
		int checkScrap = -1;

		Map map = new HashMap();
		map.put("memnum", loginNum);
		map.put("scrapnum", rcpnum);

		try {
			String statement = namespace + ".checkScrap";
			checkScrap = sqlSession.selectOne(statement, map);
		} finally {
			sqlSession.close();
		}
		return checkScrap;
	}

	public int scrapCount(int rcpnum) {
		SqlSession sqlSession = opendb.getSqlSessionFactory().openSession();
		int count;

		try {
			String statement = namespace + ".scrapCount";
			count = sqlSession.selectOne(statement, rcpnum);
		} finally {
			sqlSession.close();
		}

		return count;
	}

	public int getReadcount(int rcpnum) {
		SqlSession sqlSession = opendb.getSqlSessionFactory().openSession();
		int readcount = 0;
		try {
			String statement = namespace + ".getReadcount";
			sqlSession.selectOne(statement);
		} finally {
			sqlSession.close();
		}
		return readcount;
	}
	public int searchCount(String keyword){
		SqlSession sqlSession=opendb.getSqlSessionFactory().openSession();
		int count;
		
		try{
			String statement=namespace+".searchCount"; 
			count=sqlSession.selectOne(statement, keyword);
		}finally{
			sqlSession.close();
		}

		return count;
	}
	
	public List<Rcp> searchList(String keyword){
		SqlSession sqlSession=opendb.getSqlSessionFactory().openSession();
		List<Rcp> searchList=null;
		String statement;
		System.out.println(11111);
		try{
			statement=namespace+".searchList";         
			searchList=sqlSession.selectList(statement, keyword);
		}finally{
			sqlSession.close();
		}
		System.out.println(222222);
		return searchList;
	}
	
}
