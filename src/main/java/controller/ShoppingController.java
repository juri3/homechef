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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.Cart;
import model.Ingredient;
import model.MemAddress;
import model.Member;
import model.OrderInfo;
import model.OrderProduct;
import model.Rcp;
import model.Sale;
import service.MybatisRcpDao;
import service.ShoppingRepository;

//서블릿 그자체, 왜냐하면 서블릿을 상속받았기 때문에
@Controller
@RequestMapping("/shopping/")
public class ShoppingController {
   
   @Autowired
   ShoppingRepository service;
   
   @ModelAttribute
   public void initProcess(HttpSession session) {
      System.out.println("====================");
      //회원기능 머지 전이라 초기화
      //
      String memNum =  (String) session.getAttribute("memNum");
      if (memNum==null) {
    	  memNum = "2";
    	  session.setAttribute("memNum", "2");
		}
      System.out.println(memNum);
     
      
   }


   @RequestMapping(value = "list") // 맨끝단의 url만 가지고 옴, get방식으로 한다.
   public String reciptview(Model model) {
      // TODO Auto-generated method stub
      
      int rcpNum = 1;// 나중에 get값으로 받음
      
      List<Ingredient> ingredients = service.getIngredient(rcpNum);
      Sale sale = service.getSale(rcpNum);
      System.out.println("price : "+sale);
      
      Rcp rcp = service.getRcp(rcpNum);
      System.out.println("ingredient : " + rcp);
      model.addAttribute("ingredients", ingredients);
      model.addAttribute("sale", sale);

      return "shopping/goodsview";
   }

   @RequestMapping(value = "addcart", method=RequestMethod.POST) // 맨끝단의 url만 가지고 옴, get방식으로 한다.
   public String addcart(Cart cart, HttpSession session, Model m) {
      // TODO Auto-generated method stub
      
      int memNo = Integer.parseInt((String)session.getAttribute("memNum"));
      cart.setMemNum(memNo);
      service.insertCart(cart);
      List<Cart> cartlist = service.getCart(memNo);
      System.out.println("getcart : "+cartlist);
      
      return "redirect:/shopping/cartview";
      
   }

   @RequestMapping(value = "cartview", method=RequestMethod.GET) // 맨끝단의 url만 가지고 옴, get방식으로 한다.
   public String cartview(HttpSession session, Model m){
      // TODO Auto-generated method stub
      
      int memNo = Integer.parseInt((String)session.getAttribute("memNum"));
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
         
         int check = service.deleteCartvalue(Integer.parseInt(str));
         System.out.println(str+" : "+check);
      }
      
      return "redirect:/shopping/cartview";
   }
   
   @RequestMapping(value = "regist_jjim", method=RequestMethod.POST)
   public String regist_jjim(HttpServletRequest request, Model m) throws Exception {
      // TODO Auto-generated method stub
      HttpSession session = request.getSession();
      boolean flag = false;
      String[] values = request.getParameterValues("cart");
      for(String str : values){
         int check = service.registjjim(Integer.parseInt(str),(String)session.getAttribute("memNum"));
         if(check == 1){
            System.out.println( str+"-- 성공");
            flag = true;
         }else{
            System.out.println( str+"-- 실패");
         }
      }
      System.out.println(flag);
      m.addAttribute("flag", flag);

      return "redirect:/shopping/cartview";
   }
   
   @RequestMapping(value = "order", method=RequestMethod.POST) // �ǳ����� url�� ������ ��, get������� �Ѵ�.
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
			cartlist.add(service.getCartByNum(Integer.parseInt(str)));
		}
		int memNum = Integer.parseInt((String)session.getAttribute("memNum"));
		mem = service.getMember(memNum);

		
		address = service.getAddress(memNum);
		
		System.out.println(cartlist);
		System.out.println(address);
		
		m.addAttribute("memName", mem.getName());
		m.addAttribute("cartlist", cartlist);
		m.addAttribute("address", address);
		
		return "shopping/orderForm";
	}
	
	@RequestMapping(value = "complete_order") 
	public String payment(HttpServletRequest request, int[] nums, MemAddress memaddr, OrderInfo orderinfo, OrderProduct ordpro, Model m){
		// TODO Auto-generated method stub
		String tot_addr = memaddr.getAddress1() +" "+ memaddr.getAddress2() + memaddr.getAddress3();
		orderinfo.setAddress(tot_addr);
		service.insertMemAddr(memaddr);
		service.insertOrderForm(orderinfo, ordpro, nums);
		
		System.out.println(nums[1]);
		System.out.println(memaddr);
		System.out.println(orderinfo);
		System.out.println(ordpro);
		//orderinfo, orderproduct, 
		
		
		
		
		return "shopping/orderSuccess";
	}
}