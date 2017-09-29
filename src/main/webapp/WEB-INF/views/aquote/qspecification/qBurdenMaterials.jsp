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

		function addMaterials(qspecificationId,qMaterialsId) {

			var submit = function (v, h, f) {
				if (v == 'ok') {
					$.ajax({
						type: "POST",
						url: ctx+"/qspecification/qSpecification/materialsadd",
						data: {"qspecificationId":qspecificationId,"qMaterialsId":qMaterialsId},
						dataType:"JSON",
						success:function(data){
							if(data.msg=="保存成功"){
								window.parent.window.isFreshFlag="2";//回写父页面的值
								//关闭当前子窗体
								parent.$.jBox.close(true);
							}else if(data.msg=="已经存在"){
								jBox.tip("原料配件已经存在，请重新选择！");
							}
							else{
								jBox.tip("原料配件添加失败，请联系管理员");
							}
						}
					});
				}
				else if (v == 'cancel') {
					// 取消
				}
				return true; //close
			};

			jBox.confirm("确定要添加材料配件吗？", "添加提示", submit);
		}


	</script>
</head>
<body>
	<input name="qspecification" style="display: none" id="qspecificationId" value="${specificationId}">
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
				<th style="text-align: center">材料配件</th>
				<th style="text-align: center">材料配件品质</th>
				<th style="text-align: center">价格（元/单位）</th>
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

				<td style="text-align: center">
						${j.index+1}
				</td>
				<td>
					${qMaterials.qModel.name}
				</td>
			<td id="qMaterialsId" style="display: none">
					${qMaterials.id}
			</td>
				<td id="qMaterialsName" ><a href="${ctx}/qmaterials/qMaterials/form?id=${qMaterials.id}"></a>
					${qMaterials.name}
				</td>

				<td style="text-align: center"  id="qMaterialsQuality">
					${qMaterials.quality}
				</td>
				<td id="qMaterialsPrice">
					${qMaterials.price}
				</td>

				<td>
					${qMaterials.remarks}
				</td>
				<shiro:hasPermission name="qmaterials:qMaterials:edit">
					<td  style="text-align: center">
					<input id="addSubmit" class="btn btn-primary" type="button" onclick="addMaterials('${specificationId}','${qMaterials.id}')" value="添加"/>&nbsp;
					</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>


		</tbody>
	</table>
	<div class="pagination">${page}</div>

</body>
</html>