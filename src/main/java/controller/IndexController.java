package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import model.Brand;
import model.Page;
import model.Sale;
import model.Salecategory;
import service.BrandRepository;
import service.ShoppingRepository;

@Controller
public class IndexController {
	@Autowired
	BrandRepository service;
	@Autowired
	ShoppingRepository shopService;
	
	@RequestMapping(value="/main")
	public String index(){
		return "main";
	}
	@RequestMapping(value="/shop")
	public String shop(HttpSession session, @RequestParam(value="pageNum", required=false, defaultValue="1")int pageNum, Model model){
		List<Salecategory> cate = shopService.getSaleCategory();
		List<Sale> sales = shopService.getSale();
		Page page = new Page();
		
		page.setPageSize(3);
		
		session.setAttribute("pageNum", pageNum);
		System.out.println("1 "+page.getCurrentPage());
		
		int sess_page = (int)session.getAttribute("pageNum");
		page.setCurrentPage(sess_page);
		
		int count = shopService.getCountSale(); 
		System.out.println(count);
		int pageCount = count/pageSize + (count % pageSize == 0 ? 0 :1 );
		
		
		
		model.addAttribute("cate", cate);
		model.addAttribute("sales", sales);
		return "/shopping/shop";
	}
	@RequestMapping(value="/brand")
	public String brand(){
		return "/brand/brand";
	}
	@RequestMapping(value="/brand" , method=RequestMethod.POST)
	public String addbrand(Brand brand,Model m,String[] inquiry){
		 
		 String categories = "";
			
			for(int i=0;i<inquiry.length;i++){			
				categories+="/"+inquiry[i];
			}
			brand.setInquiry(categories);
			service.insertBrand(brand);
		return "/brand/brand_view";
	}
}
