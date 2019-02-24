<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>规格管理</title>
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
		<li class="active"><a href="${ctx}/spec/talSpec/">规格列表</a></li>
		<shiro:hasPermission name="spec:talSpec:edit"><li><a href="${ctx}/spec/talSpec/form">规格添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="talSpec" action="${ctx}/spec/talSpec/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>规格：</label>
				<form:input path="spec" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${talSpec.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${talSpec.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center">规格</th>
				<th style="text-align: center">创建者</th>
				<th style="text-align: center">创建时间</th>
				<th style="text-align: center">备注信息</th>
				<shiro:hasPermission name="spec:talSpec:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="talSpec">
			<tr>
				<td><a href="${ctx}/spec/talSpec/form?id=${talSpec.id}">
					${talSpec.spec}
				</a></td>
				<td>
					${talSpec.user.name}
				</td>
				<td>
					<fmt:formatDate value="${talSpec.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${talSpec.remarks}
				</td>
				<shiro:hasPermission name="spec:talSpec:edit"><td>
    				<a href="${ctx}/spec/talSpec/form?id=${talSpec.id}">修改</a>
					<a href="${ctx}/spec/talSpec/delete?id=${talSpec.id}" onclick="return confirmx('确认要删除该规格吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>