<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>入库管理</title>
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
		<li class="active"><a href="${ctx}/addinventory/talProductIn/">出入库列表</a></li>
		<shiro:hasPermission name="addinventory:talProductIn:edit"><li><a href="${ctx}/addinventory/talProductIn/form">出库入库调整</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="talProductIn" action="${ctx}/addinventory/talProductIn/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<%--<li><label>用户：</label>--%>
				<%--<form:input path="user" htmlEscape="false" maxlength="64" class="input-medium"/>--%>
			<%--</li>--%>
			<li><label>产品：</label>
				<form:select path="product" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${talProductMap}"  htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center">用户</th>
				<th style="text-align: center">产品</th>
				<th style="text-align: center">库存增加</th>
				<th style="text-align: center">创建者</th>
				<th style="text-align: center">创建时间</th>
				<th style="text-align: center">备注信息</th>
				<%--<shiro:hasPermission name="addinventory:talProductIn:edit"><th>操作</th></shiro:hasPermission>--%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="talProductIn">
			<tr>
				<td>
					<%--<a href="${ctx}/addinventory/talProductIn/form?id=${talProductIn.id}">--%>
					${talProductIn.user.name}
					<%--</a>--%>
				</td>
				<td>
					${talProductIn.product.productname}
				</td>
				<td>
					${talProductIn.addinventory}
				</td>
				<td>
					${talProductIn.user.name}
				</td>
				<td>
					<fmt:formatDate value="${talProductIn.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${talProductIn.remarks}
				</td>
				<%--<shiro:hasPermission name="addinventory:talProductIn:edit"><td>--%>
    				<%--<a href="${ctx}/addinventory/talProductIn/form?id=${talProductIn.id}">修改</a>--%>
					<%--<a href="${ctx}/addinventory/talProductIn/delete?id=${talProductIn.id}" onclick="return confirmx('确认要删除该入库吗？', this.href)">删除</a>--%>
				<%--</td></shiro:hasPermission>--%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>