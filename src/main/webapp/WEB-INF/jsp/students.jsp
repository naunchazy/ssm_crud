<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<script type="text/javascript">
	window.onload=function(){
		 var tbl = document.getElementById("tbody");
		 var trs = tbl.getElementsByTagName("tr");
		 for (var i = 0; i < trs.length; i++){
			  var j = i + 1;
			  if (j % 2 != 0) { //偶数行
				  trs[i].style.background = "#ccc";
			  }
		}
	}
	function deleteStudent(id){
		var result=confirm("确实删除该用户？");
		if(result){
			location.href="${pageContext.request.contextPath}/deleteStudent?id="+id;
		}
	}
	function updateStudent(id){
		location.href="${pageContext.request.contextPath}/toUpdateStudent?id="+id;
	}
</script>
</head>
<body>
	<h1>学生列表</h1>
	<hr>
	<form action="${pageContext.request.contextPath}/listByPage">
		<table align="center" width="250px" border="0" cellspacing="0" cellpadding="0">
		<tr align="center">
			<td colspan="4">
				<input type="text" name="condition" value="${condition}"/>
				<input type="submit" value="搜索"/>
				<input type="reset" value="重置" onclick="location.href='${pageContext.request.contextPath}/resetSearch'"/>
			</td>
		</tr>
	</table>
	</form>
	<br>
	<table align="center" width="300px" border="1px" cellspacing="0" cellpadding="0">
		<thead>
			<tr>
				<th>编&nbsp;号</th>
				<th>用户名</th>
				<th>别&nbsp;名</th>
				<th>操&nbsp;作</th>
			</tr>
		</thead>
		<tbody id="tbody">
			<c:forEach items="${students}" var="student">
				<tr align="center">
					<td>${student.id}</td>
					<td>${student.name}</td>
					<td>${student.alias}</td>
					<td>
						<a href="javascript:void(0)" onclick="updateStudent(${student.id})">修改</a>
						<br>
						<%-- <form action="updateStudent/${student.id}" method="post" id="updateStudent(${student.id})">
							<input type="hidden" name="_method" value="PUT">
						</form> --%>
						<a href="javascript:void(0)" onclick="deleteStudent(${student.id})">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="4" align="center">
					<c:if test="${empty page || page==1 }">
						<font>首页</font>&nbsp;&nbsp;
					</c:if>
					<c:if test="${!empty page && page!=1 }">
						<a href="${pageContext.request.contextPath }/listByPage?page=${page-1}">上一页</a>&nbsp;&nbsp;
					</c:if>
					<c:forEach begin="1" end="${totalPage }" var="pages">
						<c:if test="${page!=pages }">
							<a href="${pageContext.request.contextPath }/listByPage?page=${pages}">${pages }</a>&nbsp;&nbsp;
						</c:if>
						<c:if test="${page==pages }">
							<font color="#999">${pages }</font>&nbsp;&nbsp;
						</c:if>
					</c:forEach>
					<c:if test="${page==totalPage }">
						<font>尾页</font>
					</c:if>
					<c:if test="${page!=totalPage }">
						<a href="${pageContext.request.contextPath }/listByPage?page=${page+1}">下一页</a>
					</c:if>
				</td>
			</tr>
		</tfoot>
	</table>

</body>
</html>