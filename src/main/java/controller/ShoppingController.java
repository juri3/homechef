package controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import model.Cart;
import model.Ingredient;
import model.Jjim;
import model.MemAddress;
import model.Member;
import model.OrderInfo;
import model.OrderProduct;
import model.Rcp;
import model.Sale;
import model.Salecategory;
import service.MybatisRcpDaoMysql;
import service.ShoppingRepository;

//서블릿 그자체, 왜냐하면 서블릿을 상속받았기 때문에
@Controller
@RequestMapping("/shopping/")
public class ShoppingController {
   
   @Autowired
   ShoppingRepository service;
   
   @ModelAttribute
   public void initProcess(HttpSession session, Model model) {
      System.out.println("====================");
      //회원기능 머지 전이라 초기화
      //
      System.out.println("memNum : "+session.getAttribute("memNum"));
      
	  System.out.println("====================");
      
   }

   @RequestMapping(value = "list") // 맨끝단의 url만 가지고 옴, get방식으로 한다.
   public String reciptview(@RequestParam("saleNum")int saleNum, Model model) {
      // TODO Auto-generated method stub
      
      Sale sale = service.getSale(saleNum);
      
      System.out.println("sale : "+sale);
      
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
      if(session.getAttribute("memNum")==null){
    	  return "redirect:/member/login";
      }
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
   public String regist_jjim(HttpSession session, @RequestParam("saleNum")int saleNum , Model m) throws Exception {
      // TODO Auto-generated method stub
	if(session.getAttribute("memNum")==null){
	    	  return "redirect:/member/login";
	   }
     int check = service.registjjim(saleNum, (int)session.getAttribute("memNum"));
     System.out.println("check " + check);
     System.out.println("dddd : "+(int)session.getAttribute("memNum"));
     if(check >= 1){
        System.out.println( saleNum+"-- 성공");
     }else{
        System.out.println( saleNum+"-- 중복");
     }
      return "redirect:/shopping/list?saleNum="+saleNum;
   }
   
   @RequestMapping(value = "regist_jjim")
   public String regist_jjim_get(HttpSession session, @RequestParam("saleNum")int saleNum , Model m) throws Exception {
      // TODO Auto-generated method stub
	if(session.getAttribute("memNum")==null){
	    	  return "redirect:/member/login";
	   }
     int check = service.registjjim(saleNum, (int)session.getAttribute("memNum"));
     if(check >= 1){
        System.out.println( saleNum+"-- 성공");
     }else{
        System.out.println( saleNum+"-- 중복");
     }
      return "redirect:/shop";
   }
   
   
   @RequestMapping(value = "jjimlist")
   public String jjimlist(HttpSession session, Model m) throws Exception {
      // TODO Auto-generated method stub
      
	 List<Jjim> jjimlist = service.getJjimlist((int)session.getAttribute("memNum"));
	 m.addAttribute("jjimlist", jjimlist);

      return "/shopping/Wishlist";
   }
   
   @RequestMapping(value = "jjim_delete", method=RequestMethod.POST)
   public String jjim_delete(HttpServletRequest request) throws Exception {
      // TODO Auto-generated method stub
      
      String[] values = request.getParameterValues("jjim");
      for(String str : values){
         
         int check = service.deletejjimvalue(Integer.parseInt(str));
         System.out.println(str+" : "+check);
      }
      
      return "redirect:/shopping/jjimlist";
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
	public String payment(@RequestParam("select")int select, @RequestParam(value="sel_address", required=false, defaultValue="-1")int sel_address, @RequestParam(value="addradd", required=false, defaultValue="0")int addradd,
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
	
	@RequestMapping(value = "complete", method=RequestMethod.POST)
	@ResponseBody
	public String pa(@RequestParam("select")int select, Model m){

		System.out.println("complete!");
		System.out.println(select);
		//System.out.println(memaddr);
		//System.out.println(orderinfo);
		//System.out.println(ordpro);
		
		
		return "shopping/orderSuccess";

	}
	
	@RequestMapping("pay") // 맨끝단의 url만 가지고 옴, get방식으로 한다.
	public String paying(@ModelAttribute("name")String name, @ModelAttribute("email")String email, @ModelAttribute("phone")String phone, @ModelAttribute("address")String address, @ModelAttribute("price")int price) {
	      // TODO Auto-generated method stub
			System.out.println(name+", "+email+", "+phone+", "+address+", "+price);
	      return "shopping/pay";
	      
	}
}