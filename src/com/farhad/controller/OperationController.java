package com.farhad.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.farhad.entity.User;
import com.farhad.model.UsersModel;


@WebServlet("/operation")
public class OperationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/project")
	private DataSource dataSource;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		page = page.toLowerCase();
		
		switch(page) {
		case "listusers":
			listUsers(request, response);
			break;
		case "adduser":
			addUser(request, response);
			break;
		case "updateuser":
			updateUserFormLoader(request, response);
			break;
		case "deleteuser":
			deleteUserFormLoader(request, response);
			listUsers(request, response);
			break;
		default:
			errorPage(request, response);
			break;
		}
	}
	
	private void deleteUserFormLoader(HttpServletRequest request, HttpServletResponse response) {
		User deleteUser = new User(Integer.parseInt(request.getParameter("usersId")));
				
		new UsersModel().deleteUser(dataSource, deleteUser);
		return;
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("form");
		operation = operation.toLowerCase();
		
		switch(operation) {
		case "adduseroperation":
			User newUser = new User(request.getParameter("username"), request.getParameter("email"));
			addUserFormLoader(newUser);
			listUsers(request, response);
			break;
		case "updateuseroperation":
			User updateUser = new User(Integer.parseInt(request.getParameter("usersId")), request.getParameter("username"), request.getParameter("email"));
			updateUserOperation(dataSource, updateUser);
			listUsers(request, response);
			break;
		default:
			errorPage(request, response);
			break;
		}
	}

	private void updateUserOperation(DataSource dataSource2, User updateUser) {
		new UsersModel().updateUser(dataSource, updateUser);
		return;
		
	}

	public void updateUserFormLoader(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("updateUser.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void addUserFormLoader(User newUser) {
		new UsersModel().addUser(dataSource, newUser);
		return;
		
	}

	public void listUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> listUsers = new ArrayList<>();
		listUsers = new UsersModel().listUsers(dataSource);
		request.setAttribute("listUsers", listUsers);
		request.getRequestDispatcher("listUsers.jsp").forward(request, response);
	}
	
	public void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("addUser.jsp").forward(request, response);
	}

	public void errorPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("error.jsp").forward(request, response);
	}

}
