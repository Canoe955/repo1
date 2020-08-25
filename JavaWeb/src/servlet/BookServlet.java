package servlet;

import dao.BookDao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BookServlet extends BaseServlet{
	
	private BookDao bookDao = new BookDao();
	
	public String findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("bookList", bookDao.findAll());
		return "/show.jsp";
	}
	
	public String findByCategroy(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String value = req.getParameter("category");
		int category = Integer.parseInt(value);
		req.setAttribute("bookList", bookDao.findByCategory(category));
		return "/show.jsp";
	}
}
