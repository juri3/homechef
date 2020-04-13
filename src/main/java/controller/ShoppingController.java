package controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Cart;
import model.Ingredient;
import model.MemAddress;
import model.Member;
import model.OrderInfo;
import model.OrderProduct;
import model.Rcp;
import model.Sale;
import service.MybatisRcpDaoMysql;
import service.ShoppingRepository;

//서블릿 그자체, 왜냐하면 서블릿을 상속받았기 때문에
@Controller
@RequestMapping("/shopping/")
public class ShoppingController {
   
   @Autowired
   ShoppingRepository service;
   @Autowired
	MybatisRcpDaoMysql dbPro;
   
   @ModelAttribute
   public void initProcess(HttpSession session, Model m) {
      System.out.println("====================");
      //회원기능 머지 전이라 초기화
      //
      System.out.println("memNum : "+session.getAttribute("memNum"));
      
	  System.out.println("====================");
	  List<Rcp> foodnames =dbPro.rcpAllList();
		List<Ingredient> ingredients =dbPro.getIngredient();
		
		HashSet<String> keywords = new HashSet<String>();
		for(int i=0;i<foodnames.size();i++){
			Rcp foodname=foodnames.get(i);
			keywords.add(foodname.getFoodname());
		}
		for(int i=0;i<ingredients.size();i++){
			Ingredient ingredient=ingredients.get(i);
			keywords.add(ingredient.getIngredient());
		}
		
		m.addAttribute("keywords", keywords);
      
   }

   @RequestMapping(value = "list") // 맨끝단의 url만 가지고 옴, get방식으로 한다.
   public String reciptview(Model model) {
      // TODO Auto-generated method stub
      
      int rcpNum = 1;// 나중에 get값으로 받음
      
      List<Ingredient> ingredients = service.getIngredient(rcpNum);
      Sale sale = service.getSale(rcpNum);
      System.out.println("price : "+sale);
      
      Rcp rcp = service.getRcp(rcpNum);
      model.addAttribute("recipt", rcp);
      model.addAttribute("ingredients", ingredients);
      model.addAttribute("sale", sale);

      return "shopping/goodsview";
   }
   
   private String cartNum_split(String value){
		String str = value;
		String[] array = str.split(",");
		return array[0];
	}
   
   @RequestMapping(value = "addcart", method=RequestMethod.POST) // 맨끝단의 url만 가지고 옴, get방식으로 한다.
   public String addcart(Cart cart, HttpSession session, Model m) {
      // TODO Auto-generated method stub
      
      int memNo = (int) session.getAttribute("memNum");
      cart.setMemNum(memNo);
      
      service.insertCart(cart);
      List<Cart> cartlist = service.getCart(memNo);
      System.out.println("getcart : "+cartlist);
      
      return "redirect:/shopping/cartview";
      
   }
  
   @RequestMapping(value = "cartview", method=RequestMethod.GET) // 맨끝단의 url만 가지고 옴, get방식으로 한다.
   public String cartview(HttpSession session, Model m){
      // TODO Auto-generated method stub
      
      int memNo = (int) session.getAttribute("memNum");
      List<Cart> cartlist = service.getCart(memNo);
      System.out.println(memNo);
      System.out.println("getcart : "+cartlist);
     
      m.addAttribute("cartlist",cartlist);
      
      return "shopping/shoppingcartForm";
   }
   
   @RequestMapping(value = "cart_delete", method=RequestMethod.POST)
   public String cart_delete(HttpServletRequest request) throws Exception {
      // TODO Auto-generated method stub
      
      String[] values = request.getParameterValues("cart");
      for(String str : values){
         
         int check = service.deleteCartvalue(Integer.parseInt(cartNum_split(str)));
         System.out.println(str+" : "+check);
      }
      
      return "redirect:/shopping/cartview";
   }
   
   @RequestMapping(value = "regist_jjim", method=RequestMethod.POST)
   public String regist_jjim(HttpServletRequest request, Model m) throws Exception {
      // TODO Auto-generated method stub
      HttpSession session = request.getSession();
      
      String values = request.getParameter("rcpNum");
     int check = service.registjjim(Integer.parseInt(values),(int)session.getAttribute("memNum"));
     System.out.println("check " + check);
     System.out.println("dddd : "+(int)session.getAttribute("memNum"));
     if(check >= 1){
        System.out.println( values+"-- 성공");
     }else{
        System.out.println( values+"-- 중복");
     }
      

      return "redirect:/shopping/list";
   }
   
   @RequestMapping(value = "order", method=RequestMethod.POST) // 
	public String order(HttpServletRequest request, Model m){
		// TODO Auto-generated method stub
		List<Cart> cartlist = new ArrayList<>();
		List<MemAddress> address = new ArrayList<>();
		Member mem = new Member();
		
		HttpSession session = request.getSession();
		
		//카트에서 선택된 항목만 가져오기
		String[] values = request.getParameterValues("cart");
		for(String str : values){
			System.out.println("str : " +str);
			cartlist.add(service.getCartByNum(Integer.parseInt(cartNum_split(str))));
		}
		int memNum = (int) session.getAttribute("memNum");
		mem = service.getMember(memNum);

		
		address = service.getAddress(memNum);
		
		System.out.println(cartlist);
		System.out.println(address);
		
		m.addAttribute("member", mem);
		m.addAttribute("cartlist", cartlist);
		m.addAttribute("reg_address", address);
		
		return "shopping/orderForm";
	}
   
   private String address_split(String value, int index){
		String str = value;
		String[] array = str.split(",");
		return array[index];
	}
	
	@RequestMapping(value = "complete_order") 
	public String payment(HttpServletRequest request, @RequestParam("select")int select, @RequestParam(value="sel_address", required=false, defaultValue="-1")int sel_address, @RequestParam(value="addradd", required=false, defaultValue="0")int addradd,
			int[] nums, MemAddress memaddr, OrderInfo orderinfo, OrderProduct ordpro, Model m){
		// TODO Auto-generated method stub
		MemAddress getmemA = new MemAddress();
		System.out.println("select : " + select);
		System.out.println("address : " + sel_address);
		System.out.println("address : " + memaddr.getAddress());
		System.out.println("addr_add : "+addradd);
		String addr = "";
		if(select==0){
			String[] array = memaddr.getAddress().split(",");
			for(int i = 0; i < 2 ; i++){
				addr+=array[i]+" ";
			}
			memaddr.setAddress(addr);
			memaddr.setRecipient(address_split(memaddr.getRecipient(),select));
			memaddr.setZipcode(address_split(memaddr.getZipcode(),select));
			orderinfo.setAddress(addr);
			orderinfo.setRecipient(address_split(memaddr.getRecipient(),select));
			orderinfo.setZipcode(address_split(memaddr.getZipcode(),select));
			
			if(addradd==1){
				//service.insertMemAddr(memaddr);
			}
		}else{
			// 기존 배송지 일경우, 주문완료된 카트들 삭제..
			//getmemA = service.getAddress1(sel_address);
			orderinfo.setAddress(getmemA.getAddress());
			orderinfo.setRecipient(getmemA.getRecipient());
			orderinfo.setZipcode(getmemA.getZipcode());

		}
		System.out.println(addr);
		
		//service.insertOrderForm(orderinfo, ordpro, nums);
		
		
		System.out.println(memaddr);
		System.out.println(orderinfo);
		System.out.println(ordpro);
		
		
		return "shopping/orderSuccess";
	}
	
	@RequestMapping("pay") // 맨끝단의 url만 가지고 옴, get방식으로 한다.
	public String paying(@ModelAttribute("name")String name, @ModelAttribute("email")String email, @ModelAttribute("phone")String phone, @ModelAttribute("address")String address, @ModelAttribute("price")int price) {
	      // TODO Auto-generated method stub
			System.out.println(name+", "+email+", "+phone+", "+address+", "+price);
	      return "shopping/pay";
	      
	}
}