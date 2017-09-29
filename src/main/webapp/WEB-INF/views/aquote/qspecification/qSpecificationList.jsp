<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>产品规格管理</title>
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
		<li class="active"><a href="${ctx}/qspecification/qSpecification/">产品规格列表</a></li>
		<shiro:hasPermission name="qspecification:qSpecification:edit"><li><a href="${ctx}/qspecification/qSpecification/form">产品规格添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="qSpecification" action="${ctx}/qspecification/qSpecification/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<%--<li><label>型号id：</label>--%>
				<%--<form:input path="modelId" htmlEscape="false" maxlength="64" class="input-medium"/>--%>
			<%--</li>--%>
			<li><label>产品规格：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
				<li><label>规格等级：</label>
					<form:select path="type" class="input-medium">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('qspecification_grade')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</li>
			<%--<li><label>创建者：</label>--%>
				<%--<form:input path="createBy.id" htmlEscape="false" maxlength="64" class="input-medium"/>--%>
			<%--</li>--%>
			<%--<li><label>创建时间：</label>--%>
				<%--<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"--%>
					<%--value="<fmt:formatDate value="${qSpecification.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"--%>
					<%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - --%>
				<%--<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"--%>
					<%--value="<fmt:formatDate value="${qSpecification.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"--%>
					<%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>--%>
			<%--</li>--%>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr >
				<th  style="text-align: center">序号</th>
				<th  style="text-align: center">产品类型</th>
				<th style="text-align: center">归属型号</th>
				<th style="text-align: center">产品规格</th>
				<th style="text-align: center">规格等级</th>
				<th style="text-align: center;background-color: #00A6C7">产品成本(元)</th>
				<th style="text-align: center">利润率(%)</th>
				<th style="text-align: center;background-color: #00aa00">利润(元)</th>
				<th style="text-align: center">加工费(%)</th>
				<th style="text-align: center;background-color: #1ABC9C">加工费(元)</th>
				<th style="text-align: center;background-color: #e7a413">产品价格(元)</th>
				<%--<th style="text-align: center">创建者</th>--%>
				<%--<th style="text-align: center">创建时间</th>--%>
				<th style="text-align: center">更新时间</th>
				<th style="text-align: center">备注信息</th>
				<shiro:hasPermission name="qspecification:qSpecification:edit"><th style="text-align: center">操作</th></shiro:hasPermission>
				<th style="text-align: center">产品配料</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="qSpecification">
			<tr>
				<td style="text-align: center">
						${j.index+1}
				</td>
				<td>
					${qSpecification.qModel.parent.name}
				</td>
				<td>
					${qSpecification.qModel.name}
				</td>
				<td><a href="${ctx}/qspecification/qSpecification/form?id=${qSpecification.id}">
					${qSpecification.name}
				</a></td>
				<td>
					${qSpecification.type}
				</td>
				<td id="costid"  style="background-color: #00A6C7">
					${qSpecification.cost}
				</td>
				<td style="background-color: #00aa00">
					${qSpecification.profitratio}%
				</td>
				<td style="background-color: #00aa00">
					${qSpecification.profit}
				</td>
				<td style="background-color: #1ABC9C">
					${qSpecification.chargeratio}%
				</td>
				<td style="background-color: #1ABC9C">
					${qSpecification.charge}
				</td>
				<td style="background-color: #e7a413">
					${qSpecification.price}
				</td>
				<%--<td>--%>
					<%--${qSpecification.createBy.name}--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--<fmt:formatDate value="${qSpecification.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>--%>
				<%--</td>--%>
				<td style="text-align: center">
					<fmt:formatDate value="${qSpecification.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${qSpecification.remarks}
				</td>
				<shiro:hasPermission name="qspecification:qSpecification:edit">
					<td style="text-align: center">
    					<a href="${ctx}/qspecification/qSpecification/form?id=${qSpecification.id}">修改</a>
						<a href="${ctx}/qspecification/qSpecification/delete?id=${qSpecification.id}" onclick="return confirmx('确认要删除该产品规格吗？', this.href)">删除</a>
					</td>
				</shiro:hasPermission>

				<shiro:hasPermission name="qspecification:qSpecification:edit">
				<td  style="text-align: center">
					<%--A標籤的方式--%>
							<%--<a href="${ctx}/qspecification/qSpecification/burden?id=${qSpecification.id}">配料</a>--%>
					<%--按鈕的方式--%>
					<input id="openMaterials" class="btn btn-primary" type="button" value="配料" onclick="window.location='${ctx}/qspecification/qSpecification/burdenlist?id=${qSpecification.id}'"/>
				</td>
				</shiro:hasPermission>

			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>