<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息</title>
<script type="text/javascript">
	
	function updateStudent(id){
		var result=confirm("确实更新？");
		if(result){
			//location.href="${pageContext.request.contextPath}/updateStudent/"+id;
			form.submit();
		}
	}
</script>
</head>
<body>
	<h1>用户信息</h1>
	<hr>
	<form action="${pageContext.request.contextPath}/updateStudent">
		<table align="center" width="250px" border="1px" cellspacing="0" cellpadding="0">
			<tr>
				<td>编&nbsp;号:</td><td><input type="text" value="${student.id }" name="id" readonly="readonly"/></td>
			</tr>
			<tr>
				<td>用户名:</td><td><input type="text" value="${student.name }" name="name"/></td>
			</tr>
			<tr>
				<td>别&nbsp;名:</td><td><input type="text" value="${student.alias }" name="alias"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="确认修改" onclick="updateStudent(${student.id })"/></td>
			<tr>
	</table>
	</form>

</body>
</html>