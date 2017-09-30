package org.zhaobi.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.jws.WebParam;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zhaobi.web.entity.Category;
import org.zhaobi.web.entity.Question;
import org.zhaobi.web.entity.Users;
import org.zhaobi.web.service.CateService;
import org.zhaobi.web.service.QueService;
import org.zhaobi.web.service.UserService;


@Controller
@RequestMapping("/homepage")
public class IndexController {
	
	@Autowired  
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired  
	@Qualifier("queService")
	private QueService queService;
	
	@Autowired  
	@Qualifier("cateService")
	private CateService cateService;
	
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	@RequestMapping("/home")
	public String receive(HttpServletRequest request, HttpServletResponse response,Model model){
		System.out.println( "1111" );
		model.addAttribute("userCount", new Integer(this.userService.countUser(false).intValue()));
		model.addAttribute("adminCount", new Integer(this.userService.countUser(true).intValue()));
		model.addAttribute("queCount", new Integer(this.queService.countQue().intValue()));
		model.addAttribute("cateCount", new Integer(this.cateService.countCate().intValue()));
		return "/index";
	}
	
	@RequestMapping("/adduser")
	public String addUser(HttpServletRequest request, HttpServletResponse response){
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String a = request.getParameter("authority");
		boolean authority;
		if(a.equals("0")) {
			authority = false;
		}else {
			authority = true;
		}
		
		Users user = new Users();
		user.setName(name);
		user.setPassword(password);
		user.setEmail(email);
		user.setAuthority(authority);
		this.userService.addUser(user);
		return "redirect:/homepage/usermanagement";
	}
/*	
	@RequestMapping("/usermanagement")
	public String showUsers(HttpServletRequest request, HttpServletResponse response,Model model){
		List<Users> ulist = this.userService.getUser();
		model.addAttribute("userlist", ulist);
		return "/userManagement";
	}
*/	
	@RequestMapping("/deleteuser")
	public String deleteUser(HttpServletRequest request, HttpServletResponse response){
		String id =request.getParameter("id");
		this.userService.deleteUser(Integer.parseInt(id)); 
		return "redirect:/homepage/usermanagement";
	}
	
	@RequestMapping("/modifyuser")
	public String modifyUser(HttpServletRequest request, HttpServletResponse response){
		
		String id =request.getParameter("id");
		
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String a = request.getParameter("authority");
		System.out.println("mmmm"+name);
		boolean authority;
		if(a.equals("0")) {
			authority = false;
		}else {
			authority = true;
		}
		
		this.userService.update(Integer.parseInt(id), name, password, email, authority);
		return "redirect:/homepage/usermanagement";

	}
	
	@RequestMapping("/searchuser")
	public String searchUsers(HttpServletRequest request, HttpServletResponse response,Model model){
		String pg = request.getParameter("page");
		int page = 1;
		if (pg != null)
			page = Integer.parseInt(pg);
		
		model.addAttribute("page", new Integer(page));
		
		String name = request.getParameter("sname");
		List<Users> slist = this.userService.search(name, page);
		model.addAttribute("userlist", slist);
		return "/userManagement";
	}


	@RequestMapping("/usermanagement")
	public String showUsers(HttpServletRequest request, HttpServletResponse response,Model model){	
		String pg = request.getParameter("page");
		int page = 1;
		if (pg != null)
			page = Integer.parseInt(pg);
		
		model.addAttribute("page", new Integer(page));
		
		List<Users> ulist = this.userService.getUser(page);
		model.addAttribute("userlist", ulist);
		return "/userManagement";
	}
	
	@RequestMapping("/questionmanagement")
	public String showQuestions(HttpServletRequest request, HttpServletResponse response,Model model){	
		String pg = request.getParameter("page");
		int page = 1;
		if (pg != null)
			page = Integer.parseInt(pg);
		
		model.addAttribute("page", new Integer(page));
		
		List<Question> qlist = this.queService.getQuestion(page);
		model.addAttribute("queslist", qlist);

		List<Category> clist = this.cateService.getCategory();
		model.addAttribute("catelist", clist);
		
		List<Category> slist = this.cateService.showCategory(page);
		model.addAttribute("showcatelist", slist);
		
		return "/questionManagement";
	}

	@RequestMapping("/modifyques")
	public String modifyQues(HttpServletRequest request, HttpServletResponse response){
		
		String id =request.getParameter("qid");	
		String content = request.getParameter("content");
		String a = request.getParameter("answera");
		String b = request.getParameter("answerb");
		String c = request.getParameter("correct");
		String cid = request.getParameter("cid");

		this.queService.update(Integer.parseInt(id), content, a, b, c, Integer.parseInt(cid));
		return "redirect:/homepage/questionmanagement";

	}
	
	@RequestMapping("/deleteques")
	public String deleteQues(HttpServletRequest request, HttpServletResponse response){
		String qid =request.getParameter("qid");
		this.queService.deleteQues(Integer.parseInt(qid));
		return "redirect:/homepage/questionmanagement";
	}
	
	@RequestMapping("/addquestion")
	public String addQues(HttpServletRequest request, HttpServletResponse response){
		String content = request.getParameter("content");
		String a = request.getParameter("answera");
		String b = request.getParameter("answerb");
		String c = request.getParameter("correct");
		String cid = request.getParameter("cid");
		
		Question ques = new Question();
		ques.setContent(content);
		ques.setAnswera(a);
		ques.setAnswerb(b);
		ques.setCorrect(c);
		ques.setCid(Integer.parseInt(cid));
		this.queService.addQues(ques);

		return "redirect:/homepage/questionmanagement";
	}
	
	@RequestMapping("/modifycate")
	public String modifyCate(HttpServletRequest request, HttpServletResponse response){
		
		String cid =request.getParameter("cid");	
		String name = request.getParameter("cate");
		this.cateService.update(Integer.parseInt(cid), name);
		return "redirect:/homepage/questionmanagement";

	}
	
	@RequestMapping("/searchques")
	public String searchQues(HttpServletRequest request, HttpServletResponse response,Model model){
		String pg = request.getParameter("page");
		int page = 1;
		if (pg != null)
			page = Integer.parseInt(pg);
		
		model.addAttribute("page", new Integer(page));
		String cid = request.getParameter("cid");
		
		List<Question> qlist = this.queService.search(Integer.parseInt(cid), page);
		model.addAttribute("queslist", qlist);

		List<Category> clist = this.cateService.search(Integer.parseInt(cid));
		model.addAttribute("catelist", clist);
		

		List<Category> slist = this.cateService.showSearch(Integer.parseInt(cid), page);
		model.addAttribute("showcatelist", slist);
		return "/questionManagement";
	}

	@RequestMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response,Model model) throws IOException, ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username+"+"+password);
		List<Users> result = userService.getOneUser(username,password);
		System.out.println(result.size());
		if(result.size()==0){
			request.setAttribute("message", "Log in failed!");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			System.out.println("NOT FOUND");
			return "redirect:/login.jsp";
		}else {
			Users u = result.get(0);
			System.out.println("SEARCH USERNAME FROM DB:"+u.getName()+"+"+u.getPassword()+"+"+u.getId()+"+"+u.isAuthority());
//			Cookie c = new Cookie(u.getName(),u.getId()+"");

			Cookie c = new Cookie("username",u.getName()+"|"+u.getId());
			c.setMaxAge(60*60);
			c.setPath("/");
			response.addCookie(c);
			response.getWriter().println("setCookie Succeed!");
			System.out.println("COOKIE SET");
			if(u.isAuthority()){
				return "redirect:/homepage/home";
			}else {
				return "redirect:/homepage/userHomepage";
			}
		}
	}

	@RequestMapping("/userHomepage")
	public String userhomepage(HttpServletRequest request, HttpServletResponse response,Model model){
		String pg = request.getParameter("page");
		int page = 1;
		if (pg != null)
			page = Integer.parseInt(pg);

		model.addAttribute("page", new Integer(page));

		List<Question> qlist = this.queService.getQuestion(page);
		model.addAttribute("queslist", qlist);

		List<Category> clist = this.cateService.getCategory();
//		System.out.println(clist);
		model.addAttribute("catelist", clist);

		List<Category> slist = this.cateService.showCategory(page);
		model.addAttribute("showcatelist", slist);
//		System.out.println(slist);

		return "/userHomepage";
	}

	@RequestMapping("/showquestion")
	public String showquestion(HttpServletRequest request, HttpServletResponse response,Model model){
		String pg = request.getParameter("page");
		int page = 1;
		if (pg != null)
			page = Integer.parseInt(pg);

		model.addAttribute("page", new Integer(page));
		String cid = request.getParameter("cid");

		List<Question> qlist = this.queService.search(Integer.parseInt(cid), page);
		model.addAttribute("queslist", qlist);

		List<Category> clist = this.cateService.search(Integer.parseInt(cid));
		model.addAttribute("catelist", clist);


		List<Category> slist = this.cateService.showSearch(Integer.parseInt(cid), page);
		model.addAttribute("showcatelist", slist);
		return "/userHomepage";
	}

	public String  getUsername(HttpServletRequest request){
		Cookie[] cookie = request.getCookies();
		Cookie c = null;
//		System.out.println(cookie.length);
		for(int i=0;i<cookie.length;i++){
			c = cookie[i];
			if (c.getName().equals("username")) {
				System.out.println("FOUND USERNAME "+c.getValue());
				String username = c.getValue().split("\\|")[0];
				return username;
			}
		}
		return "Not found";
	}

	public int getUserid(HttpServletRequest request){
		Cookie[] cookie = request.getCookies();
		Cookie c = null;
//		System.out.println(cookie.length);
		for(int i=0;i<cookie.length;i++){
			c = cookie[i];
			if (c.getName().equals("username")) {
				int userID = Integer.parseInt(c.getValue().split("\\|")[1]);
				return userID;
			}
		}
		return -1;
	}

	@RequestMapping("/addquestiontoList")
	public String addquestiontoList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int qid = Integer.parseInt(request.getParameter("qid"));
//		Cookie[] cookie = request.getCookies();
//		Cookie c = null;
////		System.out.println(cookie.length);
//		for(int i=0;i<cookie.length;i++){
//			c = cookie[i];
////			System.out.println(c.getName());
//			if (c.getName().equals("username")) {
//				System.out.println("FOUND USERNAME "+c.getValue());
//				String username = c.getValue().split("\\|")[0];
//				int userID = Integer.parseInt(c.getValue().split("\\|")[1]);
//				System.out.println("ADD QUESTION TO LIST:"+qid+"+"+userID);
//				this.queService.addQuesToList(qid,userID);
//			}
//		}
		int userID = getUserid(request);
		System.out.println("ADD QUESTION TO LIST:"+qid+"+"+userID);
		this.queService.addQuesToList(qid,userID);
		return "redirect:/homepage/userHomepage";
	}

	@RequestMapping("/listHomepage")
	public String showListHomepage(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		String pg = request.getParameter("page");
		int page = 1;
		if (pg != null)
			page = Integer.parseInt(pg);

		model.addAttribute("page", new Integer(page));

		List<Question> qlist = this.queService.getQuestion(page);
		model.addAttribute("queslist", qlist);

		List<Category> clist = this.cateService.getCategory();
		model.addAttribute("catelist", clist);

		List<Category> slist = this.cateService.showCategory(page);
		model.addAttribute("showcatelist", slist);

		return "/listHomepage";
	}

	@RequestMapping("/showquestionlist")
	public String showquestionlist(HttpServletRequest request, HttpServletResponse response,Model model){
		String pg = request.getParameter("page");
		int page = 1;
		if (pg != null)
			page = Integer.parseInt(pg);

		model.addAttribute("page", new Integer(page));
		String cid = request.getParameter("cid");

		List<Question> qlist = this.queService.search(Integer.parseInt(cid), page);
		model.addAttribute("queslist", qlist);

		List<Category> clist = this.cateService.search(Integer.parseInt(cid));
		model.addAttribute("catelist", clist);


		List<Category> slist = this.cateService.showSearch(Integer.parseInt(cid), page);
		model.addAttribute("showcatelist", slist);
		System.out.println("SHOW PERSONAL LIST ");
		return "/personalList";
	}

	@RequestMapping("/personalList")
	public String PersonalList(HttpServletRequest request, HttpServletResponse response,Model model){
		String pg = request.getParameter("page");
		int page = 1;
		if (pg != null)
			page = Integer.parseInt(pg);

		model.addAttribute("page", new Integer(page));
		int userid = getUserid(request);
		List<Question> qlist = this.queService.getPersonalList(page,userid);
		model.addAttribute("queslist", qlist);

		List<Category> clist = this.cateService.getCategory();
		model.addAttribute("catelist", clist);
		System.out.println("catelist"+clist.size());
		for(int i=0;i<clist.size();i++){
			System.out.println(clist.get(i).getCate());
		}

//		List<Category> slist = this.cateService.showCategory(page);
		List<Category> slist = this.cateService.getCategory();
		model.addAttribute("showcatelist", slist);
		System.out.println("SHOWCATEGORYLIST:SLIST"+slist.size());
		if(slist.size()>0){
			for (int i=0;i<slist.size();i++){
				System.out.println(slist.get(i).getCate());
			}
		}
		System.out.println("PERSONAL LIST");
		return "/personalList";
	}

	@RequestMapping("/showPersonalListCate")
	public String showPersonalListCate(HttpServletRequest request, HttpServletResponse response,Model model){
		String pg = request.getParameter("page");
		int page = 1;
		if (pg != null)
			page = Integer.parseInt(pg);
		model.addAttribute("page", new Integer(page));
		String cid = request.getParameter("cid");

		List<Question> qlist = this.queService.searchQuestionInList(Integer.parseInt(cid), page,getUserid(request));
		model.addAttribute("queslist", qlist);

		List<Category> clist = this.cateService.search(Integer.parseInt(cid));
		model.addAttribute("catelist", clist);
		System.out.println("CATEGORY:CATELIST"+clist.size());
		if(clist.size()>0){
			for (int i=0;i<clist.size();i++){
				System.out.println(clist.get(i).getCate());
			}
		}

//		List<Category> slist = this.cateService.showSearch(Integer.parseInt(cid), page);
		List<Category> slist = this.cateService.getCategory();
		model.addAttribute("showcatelist", slist);
		System.out.println("SHOWCATEGORYLIST:SLIST"+slist.size());
		if(slist.size()>0){
			for (int i=0;i<slist.size();i++){
				System.out.println(slist.get(i).getCate());
			}
		}
		return "/personalList";
	}
}
