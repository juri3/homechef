package controller;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.Brand;
import model.Ingredient;
import model.Rcp;
import service.BrandRepository;
import service.MybatisRcpDaoMysql;

@Controller
public class IndexController {
	@Autowired
	BrandRepository service;
	@Autowired
	MybatisRcpDaoMysql dbPro;
	
	@ModelAttribute
	public void initProcess(Model m){
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
