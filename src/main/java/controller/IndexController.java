package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.Brand;
import service.BrandRepository;

@Controller
public class IndexController {
	@Autowired
	BrandRepository service;
	@RequestMapping(value="/main")
	public String index(){
		return "main";
	}
	@RequestMapping(value="/shop")
	public String shop(){
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
