<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>产品规格配料</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">


		function openMaterialsWin(){
			alert(1);
			top.$.jBox.open("iframe:${ctx}/qspecification/qSpecification/burdenmaterialslist", "原材料表",$(top.document).width()-640,$(top.document).height()-240,{
				buttons:{"关闭":true,"关闭":true}, loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
				}
			});
		}

		function inUsernum(){
			var price=document.getElementById('mPrice').innerHTML
			var usernum=document.getElementById('mUsernum').value;
			document.getElementById('mTallPrice').innerHTML=price*usernum;
		}



	</script>
</head>
<body>

<table id="contentTable" class="table table-striped table-bordered table-condensed">
	<form:form id="inputForm" modelAttribute="qBurden" action="${ctx}/qspecification/qSpecification/burdensave" method="post" class="form-horizontal">
		<thead>
			<tr >
				<th  style="text-align: center;width: 50px">序号</th>
				<th  style="text-align: center">材料名称</th>
				<th style="text-align: center">材料品质</th>
				<th style="text-align: center">材料价格(元/千克)</th>
				<th style="text-align: center">使用数量（千克）</th>
				<th style="text-align: center">成本价格</th>
				<th style="text-align: center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="qBurden" varStatus="j">
				<tr>
					<td style="text-align: center">
							${j.index+1}
					</td>
				<td style="text-align: center">
					${qBurden.materialsName}<a style="visibility:hidden">${qBurden.id}</a>
				</td style="text-align: center">
				<td style="text-align: center">
					${qBurden.materialsQuality}
				</td>
				<td id="mPrice" style="text-align: center">
					${qBurden.materialsPrice}
				</td>
				<td  style="text-align: center;background-color: white;width: 140px">
					<input id="mUsernum" style="text-align: center;border: 0px;width: auto" onkeyup="inUsernum()" value="${qBurden.materialsUsenum}"> </input>
				</td>

				<td id="mTallPrice" style="text-align: center">
						${qBurden.materialsPrice*qBurden.materialsUsenum}
				</td>

				<td style="text-align: center">
				<a href="#" onclick="return confirmx('确认要删除该原材料吗？', this.href)">删除</a>
				</td>

				</tr>
			</c:forEach>

		</tbody>


		<div class="form-actions" >
			<input id="openMaterials" style="margin-left: 5px" class="btn btn-primary" type="button" value="添加原材料" onclick="openMaterialsWin()"/>
			<shiro:hasPermission name="qspecification:qSpecification:edit">
				<input id="btnSubmit"style="margin-left: 800px" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" style="margin-left: 5px" class="btn" type="button"  value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</table>
</body>
</html>