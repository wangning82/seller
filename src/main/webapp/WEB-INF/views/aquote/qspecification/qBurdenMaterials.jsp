<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>原材料管理</title>
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
	<form:form id="searchForm" modelAttribute="qMaterials" action="" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>材料名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>材料品质：</label>
				<form:select path="quality" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('quality_grade')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th style="text-align: center;width: 50px">序号</th>
				<th style="text-align: center">产品类型</th>
				<th style="text-align: center">材料名称</th>
				<th style="text-align: center">材料品质</th>
				<th style="text-align: center">价格（元/千克）</th>
				<%--<th>排序</th>--%>
				<%--<th>创建者</th>--%>
				<%--<th>创建时间</th>--%>
				<%--<th>更新时间</th>--%>
				<th style="text-align: center">备注信息</th>
				<shiro:hasPermission name="qmaterials:qMaterials:edit"><th style="text-align: center">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="qMaterials" varStatus="j">
			<tr>
				<td style="text-align: center">
						${j.index+1}
				</td>
				<td>
					${qMaterials.qModel.name}
				</td>
				<td><a href="${ctx}/qmaterials/qMaterials/form?id=${qMaterials.id}"></a>
					${qMaterials.name}
				</td>

				<td style="text-align: center">
					${qMaterials.quality}
				</td>
				<td>
					${qMaterials.price}
				</td>
				<%--<td>--%>
					<%--${qMaterials.sort}--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--${qMaterials.createBy.name}--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--<fmt:formatDate value="${qMaterials.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--<fmt:formatDate value="${qMaterials.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>--%>
				<%--</td>--%>
				<td>
					${qMaterials.remarks}
				</td>
				<shiro:hasPermission name="qmaterials:qMaterials:edit">
					<td  style="text-align: center">
					<input id="addSubmit" class="btn btn-primary" type="submit" value="添加"/>&nbsp;
    				<%--<a href="${ctx}/qmaterials/qMaterials/form?id=${qMaterials.id}">添加</a>--%>
					<%--<a href="${ctx}/qmaterials/qMaterials/delete?id=${qMaterials.id}" onclick="return confirmx('确认要删除该原材料吗？', this.href)">删除</a>--%>
					</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>