package controller;

import java.text.SimpleDateFormat;
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
	public String shop(HttpSession session, @RequestParam(value="pageNum", required=false, defaultValue="1")int pageNum, @RequestParam(value="category", required=false, defaultValue="0")int category,
			Model model){
		List<Salecategory> cate = shopService.getSaleCategory();
		
		Page page = new Page();
		
		page.setPageSize(3);
		
		session.setAttribute("pageNum", pageNum);
		session.setAttribute("category", category);
		
		System.out.println("session : "+session.getAttribute("pageNum"));
		int sess_page = (int)session.getAttribute("pageNum");
		int cateNum = (int)session.getAttribute("category");
		
		page.setCurrentPage(sess_page);
		System.out.println("Current : "+page.getCurrentPage());
		if(cateNum == 0){
			page.setCount(shopService.getCountSale()); 
	   	}else{
	   		page.setCount(shopService.getCountSale_cate(category));
	   	}
		System.out.println(page.getCount());
		
		page.setPageCount(page.getCount() / page.getPageSize() + ( page.getCount()%page.getPageSize()==0 ? 0 : 1) );
		
		if (page.getCurrentPage() > page.getPageCount()) { 
			page.setCurrentPage(page.getPageCount());
			session.setAttribute("pageNum", page.getCurrentPage());
		}
		page.setStartRow(( page.getCurrentPage()-1 ) * page.getPageSize() + 1 );
		page.setEndRow(page.getStartRow() + page.getPageSize() - 1);
		
		page.setNumber(page.getCount() - (page.getCurrentPage() - 1) * page.getPageSize());

	   	page.setBottomLine(3);
	   	page.setStartPage( 1 + (page.getCurrentPage() - 1) / page.getBottomLine() * page.getBottomLine());
	   	page.setEndPage(page.getStartPage()+page.getBottomLine() - 1);
   	
	   	if (page.getEndPage() > page.getPageCount())
	   		page.setEndPage(page.getPageCount());
		
	   	List<Sale> sales = null;
	   	if(cateNum == 0){
			sales = shopService.getSale(page.getStartRow(), page.getEndRow());
	   	}else{
	   		sales = shopService.getSale_cate(page.getStartRow(), page.getEndRow(), cateNum);
	   	}
	   	
		System.out.println(page);
		System.out.println(sales);
		
		model.addAttribute("page", page);
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
