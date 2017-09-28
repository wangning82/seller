<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>产品规格配料</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">


		function openMaterialsWin(specificationId){
			isFreshFlag="1";
			jBox.open("iframe:${ctx}/qspecification/qSpecification/burdenmaterialslist?specificationId="+specificationId,
					"原材料表",
					$(top.document).width()-640,
					$(top.document).height()-240,
					{
						buttons: {"关闭": true, "关闭": true},
						iframeScrolling: 'yes',
						showClose: true,
						closed:function (){
							//在弹出窗口页面，如果我们保存了数据，就将父页面里的变量isFreshFlag 值设置为2
							if(isFreshFlag==2){
								location.reload();
							}
						}
					}
			);
		}

		function inUsernum(){
			var price=document.getElementById('mPrice').innerHTML
			var usernum=document.getElementById('mUsernum').value;
			document.getElementById('mTallPrice').innerHTML=price*usernum;
		}


	</script>
</head>
<body>
<input id="specificationId" style="display: none" value=st>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
	<form:form id="inputForm" modelAttribute="qBurden" action="${ctx}/qspecification/qSpecification/burdensave" method="post" class="form-horizontal">
		<thead>
			<tr >
				<th  style="text-align: center;width: 50px">序号</th>
				<th  style="text-align: center">材料配件名称</th>
				<th style="text-align: center">材料配件品质</th>
				<th style="text-align: center">材料价格(元/单位)</th>
				<th style="text-align: center">使用数量（单位）</th>
				<th style="text-align: center">成本价格(元)</th>
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
					<a href="${ctx}/qspecification/qSpecification/burdendelete?materialsId=${qBurden.materialsId}&specificationId=${qBurden.specificationId}&id=${qBurden.id}" onclick="return confirmx('确认要删除该规格的材料吗？', this.href)">删除</a>
				</td>

				</tr>
			</c:forEach>

		</tbody>


		<div class="form-actions" >
			<input id="openMaterials" style="margin-left: 5px" class="btn btn-primary" type="button" value="添加原材料" onclick="openMaterialsWin('${QSpecification.id}')"/>
			<shiro:hasPermission name="qspecification:qSpecification:edit">
				<input id="btnSubmit"style="margin-left: 800px" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" style="margin-left: 5px" class="btn" type="button"  value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</table>
</body>
</html>