package controller;

import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import model.Category;
import model.Division;
import model.Ingredient;
import model.Rcp;
import model.RcpContent;
import service.MybatisRcpDaoMysql;

@Controller
@RequestMapping("/rcp/")
public class RcpController {
	
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

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String rcp_list(int cateNum, String keyword, String sorting, Model m) throws Exception {
		int rcpAllCount;
		List<Rcp> rcpAllList;
		List<Category> category;
		List<Division> division;
		
		if(sorting.equals("latest")){
			if(cateNum==0){
				if(keyword!=null){
					rcpAllCount = dbPro.searchCount(keyword);
					rcpAllList=dbPro.searchList(keyword);
					
				}else{
					rcpAllCount = dbPro.rcpAllCount();
					rcpAllList=dbPro.rcpAllList();
				}			
				category=dbPro.getCategory();
				division=dbPro.getDivision();
			}else{
				if(cateNum<100){
					rcpAllCount=dbPro.rcpAllCount2(cateNum);
					rcpAllList=dbPro.rcpAllList2(cateNum);
					category=dbPro.getCategory2(cateNum);
					division=dbPro.getDivision2(cateNum);
				}else{
					rcpAllCount=dbPro.rcpAllCount3(cateNum);
					rcpAllList=dbPro.rcpAllList3(cateNum);
					category=dbPro.getCategory3(cateNum);
					division=dbPro.getDivision3(cateNum);					
				}				
			}
		}else{
			if(cateNum==0){
				if(keyword!=null){
					rcpAllCount = dbPro.searchCount(keyword);
					rcpAllList=dbPro.searchList(keyword);					
				}else{
					rcpAllCount = dbPro.rcpAllCount();
					rcpAllList=dbPro.readCountList();
				}			
				category=dbPro.getCategory();
				division=dbPro.getDivision();
			}else{
				if(cateNum<100){
					rcpAllCount=dbPro.rcpAllCount2(cateNum);
					rcpAllList=dbPro.readCountList2(cateNum);
					category=dbPro.getCategory2(cateNum);
					division=dbPro.getDivision2(cateNum);
				}else{
					rcpAllCount=dbPro.rcpAllCount3(cateNum);
					rcpAllList=dbPro.readCountList3(cateNum);
					category=dbPro.getCategory3(cateNum);
					division=dbPro.getDivision3(cateNum);					
				}				
			}
		}				
		
		m.addAttribute("rcpAllCount", rcpAllCount);
		m.addAttribute("rcpAllList", rcpAllList);
		m.addAttribute("category", category);
		m.addAttribute("division", division);
		m.addAttribute("keyword", keyword);
		m.addAttribute("cateNum", cateNum);

		return "rcp/list";
	}
	
	@RequestMapping(value = "content", method = RequestMethod.GET)
	public String rcp_content(HttpServletRequest request, int rcpnum, Model m) throws Exception {
		HttpSession session = request.getSession();
		
		int loginNum = 0;

		if (session.getAttribute("memNum") == null) {
			session.setAttribute("memNum", 0);
			loginNum = (int) session.getAttribute("memNum");
		} else {
			loginNum = (int) session.getAttribute("memNum");
		}
		
		Rcp rcpContent=dbPro.rcpContent(rcpnum);
		List<RcpContent> rcpContent2=dbPro.rcpContent2(rcpnum);
		List<Ingredient> rcpContent3=dbPro.rcpContent3(rcpnum);
		
		int checkScrap = dbPro.checkScrap(loginNum, rcpnum);
		int scrapCount = dbPro.scrapCount(rcpnum);
		
		
		m.addAttribute("rcpContent", rcpContent);
		m.addAttribute("rcpContent2", rcpContent2);
		m.addAttribute("rcpContent3", rcpContent3);
		m.addAttribute("checkScrap", checkScrap);
		m.addAttribute("scrapCount", scrapCount);
		m.addAttribute("loginNum", loginNum);
		
		return "rcp/content";
	}

	@RequestMapping(value = "writeForm", method = RequestMethod.GET)
	public String rcp_writeForm(Model m) throws Exception {
		List<Category> category = null;
		
		category = dbPro.getCategory();
		
		m.addAttribute("category", category);
		
		return "rcp/writeForm";
	}

	@RequestMapping(value = "writePro", method = RequestMethod.POST)
	public String rcp_writePro(MultipartHttpServletRequest multipart,Rcp rcp, RcpContent rcpContent,String[] cateNum) throws Exception {
		HttpSession session = multipart.getSession();	
	    int memNum=(int) session.getAttribute("memNum");
	    rcp.setMemnum(memNum);	   
	   
	    MultipartFile multi=multipart.getFile("thumbNail");		
	    String filename=multi.getOriginalFilename();
	    if(filename!=null && !filename.equals("")){
		   String uploadPath=multipart.getRealPath("/")+"/uploadRcpFile";			
		   FileCopyUtils.copy(multi.getInputStream(), new FileOutputStream(uploadPath+"/"+multi.getOriginalFilename()));
		   rcp.setThumbnail(filename);
	    }else{
		   rcp.setThumbnail("");	
	    }
	    
	    Ingredient ingre=new Ingredient();
	    int x = 1;
		while (multipart.getParameter("ingredient" + x) != null) {
			x++;
		}
		for(int i=1;i<x;i++){
			ingre.setIngredient(multipart.getParameter("ingredient" + i));
			ingre.setQuantity(multipart.getParameter("quantity" + i));
			dbPro.insertIngredient(ingre);
		}
		
		int y = 1;
		while (multipart.getParameter("step" + y) != null) {
			y++;
		}
		for(int i=1;i<y;i++){
			rcpContent.setStep(Integer.parseInt(multipart.getParameter("step"+i)));
			
			MultipartFile multi2=multipart.getFile("rcpFile"+i);		
		    String filename2=multi2.getOriginalFilename();
		    if(filename2!=null && !filename2.equals("")){
			   String uploadPath=multipart.getRealPath("/")+"/uploadRcpContentFile";			
			   FileCopyUtils.copy(multi2.getInputStream(), new FileOutputStream(uploadPath+"/"+multi2.getOriginalFilename()));
			   rcpContent.setFileName(filename2);
		    }else{
			   rcpContent.setFileName("");	
		    }
		    
			rcpContent.setContent(multipart.getParameter("content" + i));
			dbPro.insertRcpContent(rcpContent);
		}
		
		String categories = "";
	
		for(int i=0;i<cateNum.length;i++){			
			categories+="/"+cateNum[i];
		}
		
		rcp.setCategory(categories);
		
	    dbPro.insertRcp(rcp);
		
	    return "redirect:/member/mypage?memNum="+memNum;
	    /*return "redirect:/main";*/
	}

}
