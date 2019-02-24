<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>库存管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出产品数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/productinven/talProduct/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
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
		<li class="active"><a href="${ctx}/productinven/talProduct/">产品列表</a></li>
		<shiro:hasPermission name="productinven:talProduct:edit"><li><a href="${ctx}/productinven/talProduct/form">产品添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="talProduct" action="${ctx}/productinven/talProduct/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>产品名称：</label>
				<form:input path="productname" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>款号：</label>
				<form:select path="modelnum" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${modelnumNameList}"  htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>颜色：</label>
				<form:select path="colorname" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${colorNameList}"  htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>规格：</label>
				<form:select path="specification" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${specNameList}"  htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>材料：</label>
				<form:select path="texture" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${materialNameList}" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>总库存：</label>
				<form:input path="tallinventory" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li><label>当前库存：</label>
				<form:input path="nowinventory" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li><label>更新时间：</label>
				<input name="beginUpdateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${talProduct.beginUpdateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endUpdateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${talProduct.endUpdateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
			<li></li><label>当前库存：</label> <input name="pageSize" type="hidden" value="${allNum}"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center">用户</th>
				<th style="text-align: center">产品名称</th>
				<th style="text-align: center">款号</th>
				<th style="text-align: center">颜色</th>
				<th style="text-align: center">规格</th>
				<th style="text-align: center">材料</th>
				<th style="text-align: center">总库存</th>
				<th style="text-align: center">当前库存</th>
				<th style="text-align: center">成本价</th>
				<th style="text-align: center">销售价</th>
				<th style="text-align: center">创建者</th>
				<th style="text-align: center">创建时间</th>
				<th style="text-align: center">更新者</th>
				<th style="text-align: center">更新时间</th>
				<th style="text-align: center">备注信息</th>
				<shiro:hasPermission name="productinven:talProduct:edit"><th style="text-align: center">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="talProduct">
			<tr>
				<td>
					<%--<a href="${ctx}/productinven/talProduct/form?id=${talProduct.id}">--%>
					${talProduct.user.name}
					<%--</a>--%>
				</td>
				<td>
					${talProduct.productname}
				</td>
				<td>
					${talProduct.modelnum}
				</td>
				<td>
					${talProduct.colorname}
				</td>
				<td>
					${talProduct.specification}
				</td>
				<td>
					${talProduct.texture}
				</td>
				<td>
					${talProduct.tallinventory}
				</td>
				<td>
					${talProduct.nowinventory}
				</td>
				<td>
						${talProduct.cost}
				</td>
				<td>
					${talProduct.price}
				</td>
				<td>
					${talProduct.user.name}
				</td>
				<td>
					<fmt:formatDate value="${talProduct.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${talProduct.user.name}
				</td>
				<td>
					<fmt:formatDate value="${talProduct.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${talProduct.remarks}
				</td>
				<shiro:hasPermission name="productinven:talProduct:edit"><td style="text-align: center">
    				<a href="${ctx}/productinven/talProduct/formamend?id=${talProduct.id}">修改</a>
					<a href="${ctx}/productinven/talProduct/delete?id=${talProduct.id}" onclick="return confirmx('确认要删除该库存吗？', this.href)">删除</a>
					<a href="${ctx}/productinven/talProduct/formpicture?id=${talProduct.id}" >查看图片</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>