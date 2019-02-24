<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>款号管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/modelnum/talModelnum/">款号列表</a></li>
		<shiro:hasPermission name="modelnum:talModelnum:edit"><li><a href="${ctx}/modelnum/talModelnum/form">款号添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="talModelnum" action="${ctx}/modelnum/talModelnum/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>款号：</label>
				<form:input path="modelnum" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center">款号</th>
				<th style="text-align: center">创建者</th>
				<th style="text-align: center">创建时间</th>
				<th style="text-align: center">备注信息</th>
				<shiro:hasPermission name="modelnum:talModelnum:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="talModelnum">
			<tr>
				<td><a href="${ctx}/modelnum/talModelnum/form?id=${talModelnum.id}">
					${talModelnum.modelnum}
				</a></td>
				<td>
					${talModelnum.user.name}
				</td>
				<td>
					<fmt:formatDate value="${talModelnum.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${talModelnum.remarks}
				</td>
				<shiro:hasPermission name="modelnum:talModelnum:edit"><td>
    				<a href="${ctx}/modelnum/talModelnum/form?id=${talModelnum.id}">修改</a>
					<a href="${ctx}/modelnum/talModelnum/delete?id=${talModelnum.id}" onclick="return confirmx('确认要删除该款号吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>