package ua.nure.mezentseva.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.el.util.Validation;

import ua.nure.mezentseva.User;
import ua.nure.mezentseva.db.DatabaseException;

public class EditServlet extends HttpServlet {

	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getParameter("okButton") != null){
			doOk(req,resp);
		} else if(req.getParameter("cancelButton") != null){
			doCancel(req,resp);
		} else {
			showPage(req,resp);
		}
		
	
	}

	private void doOk(HttpServletRequest req, HttpServletResponse resp) {
		User user = null;
		
		try{
			user = getUser(req);
		}
		catch(ValidationException e){
			req.setAttribute("error", e.getMessage());
			showPage(req,resp);
		}
		try{
			processUser(user);
		} catch(DatabaseException e){
			new ServletException(e);
			
		}
		req.getRequestDispatcher("/browse").forward(req, resp);
	}

	private void doCancel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/browse").forward(req, resp);
		
	}

	private void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/edit.jsp").forward(req, resp);
		
	}
	

}
