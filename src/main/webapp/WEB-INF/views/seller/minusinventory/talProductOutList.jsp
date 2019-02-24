<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>销售管理</title>
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
		<li class="active"><a href="${ctx}/minusinventory/talProductOut/">销售列表</a></li>
		<shiro:hasPermission name="minusinventory:talProductOut:edit"><li><a href="${ctx}/minusinventory/talProductOut/form">销售添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="talProductOut" action="${ctx}/minusinventory/talProductOut/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>产品：</label>
				<form:select path="product" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${talProductMap}" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${talProductOut.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${talProductOut.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th style="text-align: center">用户</th>
				<th style="text-align: center">产品</th>
				<th style="text-align: center">库存减少</th>
				<th style="text-align: center">单价</th>
				<th style="text-align: center">总价</th>
				<th style="text-align: center">创建者</th>
				<th style="text-align: center">创建时间</th>
				<th style="text-align: center">备注信息</th>
				<shiro:hasPermission name="minusinventory:talProductOut:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="talProductOut">
			<tr>
				<td><a href="${ctx}/minusinventory/talProductOut/form?id=${talProductOut.id}">
					${talProductOut.user.name}
				</a></td>
				<td>
					${talProductOut.product.productname}
				</td>
				<td>
					${talProductOut.minusinventory}
				</td>
				<td>
					${talProductOut.unitprice}
				</td>
				<td>
					${talProductOut.talprice}
				</td>
				<td>
					${talProductOut.user.name}
				</td>
				<td>
					<fmt:formatDate value="${talProductOut.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${talProductOut.remarks}
				</td>
				<shiro:hasPermission name="minusinventory:talProductOut:edit"><td>
    				<a href="${ctx}/minusinventory/talProductOut/form?id=${talProductOut.id}">修改</a>
					<a href="${ctx}/minusinventory/talProductOut/delete?id=${talProductOut.id}" onclick="return confirmx('确认要删除该销售吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>