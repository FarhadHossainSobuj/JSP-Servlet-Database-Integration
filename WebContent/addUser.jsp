<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/include/header.jsp">
<c:param name="title" value="Add User"/>
</c:import>

<div class="container">
	<div class="row">
		<div class="col-lg-6">
			<form class="form-group" action="${pageContext.request.contextPath }/operation" method="post">
				Username : <input class="form-control" type="text" name="username" required="required"><br>
				Email : <input class="form-control"  type="email" name="email" required="required"><br>
				<input type="hidden" name="form" value="addUserOperation">
				<input  class="btn btn-info" type="submit" value="Add User">
			</form>
		</div>
	</div>
	
</div>

<c:import url="/include/footer.jsp"/>