<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>产品规格配料</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">

		//打开材料配件的添加窗体
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
								//刷新页面
								location.reload();
							}
						}
					}
			);
		}
//
//		$(function () {
//			(function () {
//				var   mUsernumBtn =$('mUsernum');
//				mUsernumBtn.onchange(function () {
//
//
//				});
//
//			});
//
//		})
		//动态计算价钱
		function inUsernum(j){
			var mPriceid='mPrice'+j;
			var mUsernumid='mUsernum'+j;
			var mTallPriceid='mTallPrice'+j;

			var price=document.getElementById(mPriceid).innerHTML;
			var usernum=document.getElementById(mUsernumid).value;
			document.getElementById(mTallPriceid).innerHTML=price*usernum;
		}
		//删除材料配件
		function deleteMaterials(qspecificationId,qMaterialsId) {
			//jbox的提示框
			var submit = function (v, h, f) {
				if (v == 'ok'){
					$.ajax({
						type: "POST",//post请求方式
						url: ctx+"/qspecification/qSpecification/burdendelete",//请求的controller
						data: {"qspecificationId":qspecificationId,"qMaterialsId":qMaterialsId},//请求的参数
						dataType:"JSON",//返回的数据格式
						success:function(data){
							if(data.msg=="删除成功"){
								jBox.tip("材料配件删除成功");
								//刷新页面
								location.reload();
							}else{
								jBox.tip("原料配件删除失败，请联系管理员");
							}
						}
					});
				}
				else if (v == 'cancel'){
					jBox.tip(v, 'info');
				}
				return true; //close
			};

			$.jBox.confirm("确定删除材料配件吗？", "删除提示", submit);
		}

		function updateMaterials(qBurdenId,materialsId,materialsName,materialsPrice,materialsQuality,materialsUsenum) {
			//jbox的保存提示框
			var submit = function (v, h, f) {
				if (v == 'ok'){
					$.ajax({
						type: "POST",//post请求方式
						url: ctx+"/qspecification/qSpecification/burdenupdate",//请求的controller
						data: {"qBurdenId":qBurdenId,"materialsId":materialsId,"materialsName":materialsName,"materialsPrice":materialsPrice,"materialsQuality":materialsQuality,"materialsUsenum":materialsUsenum},//请求的参数
						dataType:"JSON",//返回的数据格式
						success:function(data){
							if(data.msg=="设置成功"){
								jBox.tip("设置成功");
								//刷新页面
								location.reload();
							}else{
								jBox.tip("设置保存失败，请联系管理员");
							}
						}
					});
				}
				else if (v == 'cancel'){
					jBox.tip(v, 'info');
				}
				return true; //close
			};

			$.jBox.confirm("对材料配件设置保存吗？", "设置保存提示", submit);
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
				<td id="mPrice${j.index}" style="text-align: center">
					${qBurden.materialsPrice}
				</td>
				<td  style="text-align: center;background-color: white;width: 140px">
					<input id="mUsernum${j.index}" style="text-align: center;border: 0px;width: auto" onchange="inUsernum(${j.index})" value="${qBurden.materialsUsenum}"> </input>
				</td>

				<td id="mTallPrice${j.index}" style="text-align: center">
						${qBurden.materialsPrice*qBurden.materialsUsenum}
				</td>

				<td style="text-align: center">
					<input id="updateSubmit" class="btn btn-primary" type="button" onclick="updateMaterials('${qBurden.id}','${qBurden.materialsId}','${qBurden.materialsName}','${qBurden.materialsPrice}','${qBurden.materialsQuality}',$('#mUsernum').val())" value="保存"/>&nbsp;
					<input id="addSubmit"${j.index} class="btn btn-primary" type="button" onclick="deleteMaterials('${qBurden.specificationId}','${qBurden.materialsId}')" value="删除"/>&nbsp;
				</td>

				</tr>
			</c:forEach>

		</tbody>


		<div class="form-actions" >
			<input id="openMaterials" style="margin-left: 5px" class="btn btn-primary" type="button" value="添加原材料" onclick="openMaterialsWin('${QSpecification.id}')"/>

			<input id="btnCancel" style="margin-left: 5px" class="btn" type="button"  value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</table>
</body>
</html>