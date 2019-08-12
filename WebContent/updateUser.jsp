<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/include/header.jsp">
<c:param name="title" value="Update User"/>
</c:import>

<div class="container">
	<div class="row">
		<div class="col-lg-6">
			<form class="form-group" action="${pageContext.request.contextPath }/operation" method="post">
				Username : <input value="${param.username }" class="form-control" type="text" name="username" required="required"><br>
				Email : <input value="${param.email }" class="form-control"  type="email" name="email" required="required"><br>
				<input type="hidden" name="usersId"  value="${param.usersId }">
				<input type="hidden" name="form" value="updateUserOperation">
				<input  class="btn btn-info" type="submit" value="Update User">
			</form>
		</div>
	</div>
	
</div>

<c:import url="/include/footer.jsp"/>