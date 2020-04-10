package controller;

import java.sql.Connection;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import exception.DuplicateldException;
import model.Brand;
import service.BrandRepository;
import service.ShoppingRepository;
import util.JdbcUtil;

@Controller
public class IndexController {
	@Autowired
	BrandRepository service;
	@Autowired
	ShoppingRepository shop;

	@ModelAttribute
	public void initProcess(HttpSession session, Model model) {
		if (session.getAttribute("memNum") == null) {
			session.setAttribute("memNum", 0);
		}

		int cartcount = 0;
		// index
		cartcount = shop.getCountCart((int) session.getAttribute("memNum"));
		System.out.println(cartcount);

		session.setAttribute("cartcount", cartcount);
		System.out.println("====================");

	}

	@RequestMapping(value = "/main")
	public String index() {
		return "main";
	}

	@RequestMapping(value = "/shop")
	public String shop() {
		return "/shopping/shop";
	}

	@RequestMapping(value = "/brand")
	public String brand() {
		return "/brand/brand";
	}

	@RequestMapping(value = "/brand", method = RequestMethod.POST)
	public String addbrand(Brand newbrand, Model m,
			@RequestParam(value = "inquiry", required = false, defaultValue = "선택안함") String[] inquiry) {

		String categories = "";
		/*
		 * if(inquiry==null){ String[] inquiry=""; }
		 */
		for (int i = 0; i < inquiry.length; i++) {
			categories += "/" + inquiry[i];
		}
		newbrand.setInquiry(categories);

		Connection conn = null;
		int result = 0;
		try {
			String brand = service.selectById(newbrand.getId());
			if (brand != null) {
				result = 1;
				JdbcUtil.rollback(conn);
				throw new DuplicateldException();

			}

			service.insertBrand(newbrand);
			System.out.println(result);

		} catch (DuplicateldException e) {
			System.out.println(result);
			return "/brand/brand";
		}

		return "/brand/brandSuccess";
	}

	// 아이디 중복 체크
	@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
	public @ResponseBody String AjaxView(@RequestParam("id") String id) {
		String str = "";
		String idcheck = service.selectById(id);
		if (idcheck != null) { // 이미 존재하는 계정
			str = "NO";
		} else { // 사용 가능한 계정
			str = "YES";
		}
		return str;
	}

	@RequestMapping(value = "/brandInfo") // 맨끝단의 url만 가지고 옴, get방식으로 한다.
	public String brandview(HttpSession session, String id, Model m) {
		int loginNum = 0;

		if (session.getAttribute("id") == null) {
			session.setAttribute("id", 0);
			loginNum = (int) session.getAttribute("id");
		} else {
			loginNum = (int) session.getAttribute("id");
		}
		Brand brand = service.getBrand(id);

		return "/brand/brandSuccess";
	}

	@RequestMapping(value = "/chef")
	public String cheflist() {
		return "/chef/cheflist";
	}

	@RequestMapping("/navermailtest") public void navermailtest(HttpServletRequest request, ModelMap mo) throws Exception{ 
		// 메일 관련 정보 
		String host = "smtp.naver.com"; 
		final String username = "dbsdlfkdbnm"; //네이버 이메일 주소중 @ naver.com앞주소만 기재합니다.
		final String password = "dudalsdlfkd8693"; //네이버 이메일 비밀번호를 기재합니다.
		int port=465; // 메일 내용
		String recipient = "yooneirang@gmail.com"; //메일을 발송할 이메일 주소를 기재해 줍니다. 
		String subject = "네이버를 사용한 발송 테스트입니다."; 
		String body = "내용 무"; 
		Properties props = System.getProperties(); props.put("mail.smtp.host", host); 
		props.put("mail.smtp.port", port); 
		props.put("mail.smtp.auth", "true"); 
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", host);
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() { 
			String un=username; 
			String pw=password; 
			protected PasswordAuthentication getPasswordAuthentication() { 
				return new PasswordAuthentication(un, pw); 
				} 
			}); 
		session.setDebug(true); //for debug 
		Message mimeMessage = new MimeMessage(session); 
		mimeMessage.setFrom(new InternetAddress("sosohanworkroom@naver.com")); 
		mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient)); 
		mimeMessage.setSubject(subject); mimeMessage.setText(body); Transport.send(mimeMessage); 
		}
	
	}

	
