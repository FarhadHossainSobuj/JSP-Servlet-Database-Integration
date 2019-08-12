package com.farhad.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.farhad.entity.User;

public class UsersModel {
	public List<User> listUsers(DataSource dataSource){
		List<User> listUsers = new ArrayList<User>();
		// Step 1 : initialize connection objects
		Connection connect = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connect = dataSource.getConnection();
			
			// Step 2 : Create a SQL statements string
			String query = "select * from users";
			stmt = connect.createStatement();
			
			// Step 3 : Execute SQL query
			rs = stmt.executeQuery(query);
			
			// Step 4 : Process the result set
			while(rs.next()) {
				listUsers.add(new User(rs.getInt("users_id"), rs.getString("username"), rs.getString("email")));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listUsers;
	}

	public boolean addUser(DataSource dataSource, User newUser) {
		Connection connect = null;
		PreparedStatement statement = null;
		try {
			connect = dataSource.getConnection();
			String username = newUser.getUsername();
			String email = newUser.getEmail();
			String query = "insert into users(username, email) values(?, ?)";
			statement = connect.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, email);
			return statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		finally {
			try {
				connect.close();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public void updateUser(DataSource dataSource, User updateUser) {
		Connection connect = null;
		PreparedStatement statement = null;
		
		try {
			
			connect = dataSource.getConnection();
			int usersId = updateUser.getUsers_id();
			String username = updateUser.getUsername();
			String email = updateUser.getEmail();
			String query = "update users set username = ?, email = ? where users_id=?";
			statement = connect.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, email);
			statement.setInt(3, usersId);
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				connect.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	public void deleteUser(DataSource dataSource, User deleteUser) {
		Connection connect = null;
		PreparedStatement statement = null;
		
		try {
			
			connect = dataSource.getConnection();
			int usersId = deleteUser.getUsers_id();
			String query = "delete from users where users_id=?";
			statement = connect.prepareStatement(query);
			statement.setInt(1, usersId);
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				connect.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
