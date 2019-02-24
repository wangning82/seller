<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>材料管理管理</title>
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
		<li class="active"><a href="${ctx}/material/talMaterial/">材料管理列表</a></li>
		<shiro:hasPermission name="material:talMaterial:edit"><li><a href="${ctx}/material/talMaterial/form">材料管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="talMaterial" action="${ctx}/material/talMaterial/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>材料：</label>
				<form:input path="material" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center">材料</th>
				<th style="text-align: center">创建者</th>
				<th style="text-align: center">创建时间</th>
				<th style="text-align: center">备注信息</th>
				<shiro:hasPermission name="material:talMaterial:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="talMaterial">
			<tr>
				<td><a href="${ctx}/material/talMaterial/form?id=${talMaterial.id}">
					${talMaterial.material}
				</a></td>
				<td>
					${talMaterial.user.name}
				</td>
				<td>
					<fmt:formatDate value="${talMaterial.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${talMaterial.remarks}
				</td>
				<shiro:hasPermission name="material:talMaterial:edit"><td>
    				<a href="${ctx}/material/talMaterial/form?id=${talMaterial.id}">修改</a>
					<a href="${ctx}/material/talMaterial/delete?id=${talMaterial.id}" onclick="return confirmx('确认要删除该材料管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>