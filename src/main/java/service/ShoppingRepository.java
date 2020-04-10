package service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import model.Cart;
import model.Ingredient;
import model.Jjim;
import model.MemAddress;
import model.Member;
import model.OrderInfo;
import model.OrderProduct;
import model.Rcp;
import model.Sale;
import mybatis.AbstractRepository;

@Component
public class ShoppingRepository{
	private final String namespace = "mybatis.dao.ShoppingMapper";
	private static ShoppingRepository instance = new ShoppingRepository();
	
	@Autowired
	public AbstractRepository opendb;
	
	
	public List<Ingredient> getIngredient(int rcpNum){
		SqlSession sqlSession = opendb.getSqlSessionFactory().openSession();
		try{
			String statement = namespace + ".getIngredient";
			return sqlSession.selectList(statement, rcpNum);
		}finally{
			sqlSession.close();
		}
	}
	public Rcp getRcp(int rcpnum) {
		SqlSession sqlSession = opendb.getSqlSessionFactory().openSession();
		try{
			String statement = namespace + ".getRcp";
			
			return (Rcp) sqlSession.selectOne(statement, rcpnum);
		}finally{
			sqlSession.close();
		}
	}

	
	public int insertCart(Cart cart){
		SqlSession sqlSession = opendb.getSqlSessionFactory().openSession();
		
		int cartnum = 0;
		try{
			cartnum = sqlSession.selectOne(namespace+".insert_maxCart");
			cart.setCartNum(cartnum);
			System.out.println(cart);
			String statement = namespace + ".insertCart";
			int result = sqlSession.insert(statement, cart);
			if(result > 0){
				sqlSession.commit();
				System.out.println("commit");
			}else{
				sqlSession.rollback();
				System.out.println("rollback");
			}
			return result;
		}finally{
			sqlSession.close();
		}
	}

	public List<Cart> getCart(int memNum) {
		// TODO Auto-generated method stub
		SqlSession sqlSession = opendb.getSqlSessionFactory().openSession();
		try{
			String statement = namespace + ".getCart";
			return sqlSession.selectList(statement, memNum);
		}finally{
			sqlSession.close();
		}
	}
	
	public int deleteCartvalue(int cartNum) throws Exception{
		SqlSession sqlSession = opendb.getSqlSessionFactory().openSession();
		try{
			
			String statement = namespace + ".deleteCartvalue";
			sqlSession.delete(statement, cartNum);
			System.out.println("repository : "+cartNum);
			sqlSession.commit();
			return 1;
		}finally{
			sqlSession.close();
		}
		
	}
	public int registjjim(int cartNum, int memNum){
		SqlSession sqlSession = opendb.getSqlSessionFactory().openSession();
		Cart cart = new Cart(); 
		Jjim jjim = new Jjim();
		int jnum = 0;
		int cknum = 0;
		int result = 0;
		try{
			cart = sqlSession.selectOne(namespace+".getCartintoJjim", cartNum);
			System.out.println("dao_c : " + cart.toString());
			
			Map map = new HashMap();
			map.put("memNum", memNum);
			map.put("productName", cart.getProductName());
			System.out.println("map : "+map);
			cknum = sqlSession.selectOne(namespace+".checkjjim", map);
			System.out.println("cknum : "+cknum);
			if(cknum>0){
				System.out.println(cknum);
				return 0; 
			}else{
				jnum = sqlSession.selectOne(namespace+".insert_maxJjim");
				
				jjim.setJjimNum(jnum);
				jjim.setMemNum(memNum);
				jjim.setPrice(cart.getPrice());
				jjim.setProductName(cart.getProductName());
				System.out.println("dao_j : " + jjim.toString());
				String statement = namespace + ".insertjjim";
				result = sqlSession.insert(statement, jjim);
				if(result > 0){
					sqlSession.commit();
					System.out.println("commit");
				}else{
					sqlSession.rollback();
					System.out.println("rollback");
				}	
				return 1;
			}

		}finally{
			sqlSession.close();
		}
	}
	
	public Cart getCartByNum(int cartNum){
		SqlSession sqlSession = opendb.getSqlSessionFactory().openSession();
		Cart cart = new Cart(); 
		try{
			cart = sqlSession.selectOne(namespace+".getCartintoJjim", cartNum);
			//System.out.println("dao_c : " + cart.toString());
			return cart;
		}finally{
			sqlSession.close();
		}
	}
	
	public Sale getSale(int rcpNum) {
		// TODO Auto-generated method stub
		SqlSession sqlSession = opendb.getSqlSessionFactory().openSession();
		try{
			String statement = namespace + ".getSale";
			return (Sale)sqlSession.selectOne(statement, rcpNum);
		}finally{
			sqlSession.close();
		}
	}
	public Member getMember(int memNum){
		SqlSession sqlSession=opendb.getSqlSessionFactory().openSession();
		Member member=null;
		
		try{
			String statement=namespace+".getMember";         
			member=sqlSession.selectOne(statement, memNum);
		}finally{
			sqlSession.close();
		}

		return member;
	}
	
	public List<MemAddress> getAddress(int memNum){
		SqlSession sqlSession=opendb.getSqlSessionFactory().openSession();
		
		try{
			String statement=namespace+".getAddress";         
			return sqlSession.selectList(statement, memNum);
		}finally{
			sqlSession.close();
		}
	}
	public MemAddress getAddress1(int addressNum){
		SqlSession sqlSession=opendb.getSqlSessionFactory().openSession();
		
		try{
			String statement=namespace+".getAddress1";         
			return sqlSession.selectOne(statement, addressNum);
		}finally{
			sqlSession.close();
		}
	}

	public void insertMemAddr(MemAddress memaddr) {
		// TODO Auto-generated method stub
		SqlSession sqlSession = opendb.getSqlSessionFactory().openSession();
		
		int addrnum = 0;
		try{
			addrnum = sqlSession.selectOne(namespace+".insert_maxMA");
			memaddr.setAddressNum(addrnum);
			System.out.println(memaddr);
			String statement = namespace + ".insertMemaddr";
			int result = sqlSession.insert(statement, memaddr);
			if(result > 0){
				sqlSession.commit();
				System.out.println("commit");
			}else{
				sqlSession.rollback();
				System.out.println("rollback");
			}
		}finally{
			sqlSession.close();
		}
	}
	
	public void insertOrderForm(OrderInfo orderinfo, OrderProduct ordpro, int[] nums) {
		// TODO Auto-generated method stub
		SqlSession sqlSession = opendb.getSqlSessionFactory().openSession();
		Cart cart = new Cart(); 
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String strDate = sdf.format(new Date());
		Long ordernum = Long.parseLong((String)strDate+orderinfo.getMemNum());
		orderinfo.setOrderNum(ordernum);
		ordpro.setOrderNum(ordernum);
		
		int ordpronum = 0;
		try{
			String statement = namespace + ".insertOrderInfo";
			int result = sqlSession.insert(statement, orderinfo);
			if(result > 0){
				sqlSession.commit();
				System.out.println("commit_orderinfo");
			}else{
				sqlSession.rollback();
				System.out.println("rollback_orderinfo");
			}
			
			for(int i : nums){
				cart = getCartByNum(i);
				ordpro.setProductName(cart.getProductName());
				ordpro.setQty(cart.getQty());
				ordpro.setPrice(cart.getQty()*cart.getPrice());
				ordpronum = sqlSession.selectOne(namespace+".insert_maxOP");
				ordpro.setProductNum(ordpronum);
				System.out.println(ordpro);
				statement = namespace + ".insertOrderProduct";
				result = sqlSession.insert(statement, ordpro);
				if(result > 0){
					sqlSession.commit();
					System.out.println("commit_ordpro");
				}else{
					sqlSession.rollback();
					System.out.println("rollback_ordpro");
				}
				try {
					//주문완료된 카트 종료
					deleteCartvalue(i);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
			
		}finally{
			sqlSession.close();
		}
	}
	
	public int getCountCart(int memNum) {
		// TODO Auto-generated method stub
		SqlSession sqlSession = opendb.getSqlSessionFactory().openSession();
		try{
			String statement = namespace + ".getCountCart";
			return sqlSession.selectOne(statement, memNum);
		}finally{
			sqlSession.close();
		}
	}
	

}
