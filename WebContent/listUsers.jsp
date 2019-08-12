<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.farhad.entity.User" %>
<c:import url="/include/header.jsp">
<c:param name="title" value="Home Page"/>
</c:import>

<div class="container">
	<div class="row">
		<div class="col-lg-6">
			<strong>Listing Users</strong>
			<hr>
			<table width="70%" border="1">
				<thead>
					<th>User ID</th>
					<th>Username</th>
					<th>Email</th>
					<th>Operation</th>
				</thead>
				<%
				List<User> listUsers = (List)request.getAttribute("listUsers");
				String tempURL, deleteURL;
				for(int i = 0; i <listUsers.size(); i++ ){
					out.print("<tr>");
					out.print("<td>"+listUsers.get(i).getUsers_id()+"</td>");
					out.print("<td>"+listUsers.get(i).getUsername()+"</td>");
					out.print("<td>"+listUsers.get(i).getEmail()+"</td>");
					deleteURL = request.getContextPath()+"/operation?page=deleteUser&usersId="
							+listUsers.get(i).getUsers_id();
					tempURL = request.getContextPath()+"/operation?page=updateUser&usersId="
					+listUsers.get(i).getUsers_id()+"&username="+listUsers.get(i).getUsername()+"&email="+listUsers.get(i).getEmail();
					out.print("<td><a href="+tempURL+">Update</a> | <a onclick='confirm()' href="+deleteURL+">Delete</a></td>");
					out.print("</tr>");
				}
				
				%>
			</table>
		</div>
	</div>
	
</div>

<c:import url="/include/footer.jsp"/>