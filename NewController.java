package Homework2;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

@WebServlet(urlPatterns = "/new.nhn")
@MultipartConfig(maxFileSize=1024*1024*2, location="C:\\Temp\\img")

public class NewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private NewsDAO dao;
	private ServletContext ctx;
	
	private final String START_PAGE = "Homework2/newsList.jsp";
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = new NewsDAO();
		ctx = getServletContext();
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		dao = new NewsDAO();
		
		Method m;
		String view = null;
		
		if (action == null) {
			action = "listNews";
		}
		try {
			m = this.getClass().getMethod(action, HttpServletRequest.class);
			view = (String)m.invoke(this, request);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			ctx.log("요청 action 없음!!");
			request.setAttribute("error", "action 파라미터가 잘못되었습니다!!");
			view = START_PAGE;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (view.startsWith("redirect:/")) {
			String rview = view.substring("redirect:/".length());
			response.sendRedirect(rview);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}
	}
	public String addNews(HttpServletRequest request) {
		News n = new News();
		
		try {
			BeanUtils.populate(n, request.getParameterMap());
			
			
			dao.addNews(n);
			
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("뉴스 추가 과정에서 문제 발생!!");
			request.setAttribute("error",  "뉴스가 정상적으로 등록되지 않았습니다!!");
			return listNews(request);
		}
		return "redirect:/new.nhn?action=listNews";
	}
	

	
	public String updateNews(HttpServletRequest request) {
		String aidParam = request.getParameter("aid");
		int aid = (aidParam != null) ? Integer.parseInt(aidParam) : 4;
		News n = new News();
		
		try {
			BeanUtils.populate(n, request.getParameterMap());
			
			
//			int aid = Integer.parseInt(request.getParameter("aid"));
//			String aidParam = request.getParameter("aid");
//	        int aid = 2;
//	        if (aidParam != null) {
//	            try {
//	                aid = Integer.parseInt(aidParam);
//	            } catch (NumberFormatException e) {
//	                e.printStackTrace();
//	            }
//	        }
//			String aidParam = request.getParameter("aid");
//			int aid = (aidParam != null) ? Integer.parseInt(aidParam) : 0;
			
			dao.updateNews(n, aid);
			System.out.println("NewController updateNews: "+aid);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("뉴스 수정 과정에서 문제 발생!!");
			request.setAttribute("error",  "뉴스가 정상적으로 수정되지 않았습니다!!");
			return listNews(request);
		}
		return "redirect:/new.nhn?action=listNews";
	}
	
	
//	public String updateNews(HttpServletRequest request) {
//	    String title = request.getParameter("title");
//	    int aid = 0;
//	    
//	    try {
//	        aid = dao.getAidByTitle(title);
//	        
//	        if (aid == 0) {
//	            // 해당 제목의 뉴스가 없는 경우 에러 처리
//	            request.setAttribute("error", "해당 제목의 뉴스가 존재하지 않습니다!!");
//	            return listNews(request);
//	        }
//	        
//	        News n = new News();
//	        BeanUtils.populate(n, request.getParameterMap());
//	        dao.updateNews(n, aid);
//	        System.out.println("NewController updateNews: " + aid);
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	        ctx.log("뉴스 수정 과정에서 문제 발생!!");
//	        request.setAttribute("error", "뉴스가 정상적으로 수정되지 않았습니다!!");
//	        return listNews(request);
//	    }
//	    
//	    return "redirect:/new.nhn?action=listNews";
//	}

	
	
	public String deleteNews(HttpServletRequest request) {
		int aid = Integer.parseInt(request.getParameter("aid"));
		try {
			dao.delNews(aid);
		} catch (SQLException e) {
			e.printStackTrace();
			ctx.log("뉴스 삭제 과정에서 문제 발생!!");
			request.setAttribute("error", "뉴스가 정상적으로 삭제안됨!!");
			return listNews(request);
		}
		return "redirect:/new.nhn?action=listNews";
	}
	
	public String listNews(HttpServletRequest request) {
		List<News> list;
		try {
			list = dao.getAll();
			request.setAttribute("newslist", list);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("뉴스 목록 생성 과정에서 문제 발생!!");
			request.setAttribute("error", "뉴스목록 정상적 처리되지 않았습니다!!");
		}
		return "Homework2/newsList.jsp";
	}
	
	public String getNews(HttpServletRequest request) {
		int aid = Integer.parseInt(request.getParameter("aid"));
		try {
			News n = dao.getNews(aid);
			request.setAttribute("news", n);
			System.out.println("NewController getNews"+aid);
		} catch (SQLException e) {
			e.printStackTrace();
			ctx.log("뉴스를 가져오는 과정에서 문제 발생!!");
			request.setAttribute("error", "뉴스를 정상적으로 가져오지 못했습니다!!");
//			return listNews(request);
		}
		return "Homework2/newsView.jsp";
	}
	
	public String getNews2(HttpServletRequest request) {
		return "Homework2/NewsInput.jsp";
	}
	
//	public String updateNews(HttpServletRequest request) {
//	    int aid = Integer.parseInt(request.getParameter("aid"));
//	    
//	    try {
//	        News updatedNews = new News();
//	        BeanUtils.populate(updatedNews, request.getParameterMap());
//	        updatedNews.setAid(aid);
//	        
//	        dao.updateNews(updatedNews);
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	        ctx.log("뉴스 수정 과정에서 문제 발생!!");
//	        request.setAttribute("error", "뉴스가 정상적으로 수정되지 않았습니다!!");
//	        return getNews(request);
//	    }
//	    
//	    return "redirect:/new.nhn?action=getNews&aid=" + aid;
//	}
//	public String updateNews(HttpServletRequest request) throws ServletException, IOException {
//	    // 폼에서 전달된 데이터 가져오기
//	    int aid = Integer.parseInt(request.getParameter("aid"));
//	    String title = request.getParameter("title");
//	    String content = request.getParameter("content");
//	    String img = request.getParameter("img");
//
//	    // 수정할 방명록 생성
//	    News updatedNews = new News(aid, title, content, img);
//
//	    try {
//	        // NewsDAO를 사용하여 방명록 수정
//	        NewsDAO newsDAO = new NewsDAO();
//	        newsDAO.updateNews(updatedNews);
//
//	        // 방명록 목록으로 리다이렉트
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	        // 에러 처리
//	    }
//	    return "Homework2/newsList.jsp";
//	}
	
//	private String getFilename(Part part) {
//		String fileName = null;
//		String header = part.getHeader("content-disposition");
//		System.out.println("Header = > " + header);
//		
//		int start = header.indexOf("filename=");
//		fileName = header.substring(start+10, header.length()-1);
//		ctx.log("파일명:"+fileName);
//		return fileName;
//	}
//	
}