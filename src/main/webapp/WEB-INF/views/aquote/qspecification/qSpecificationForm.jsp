<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>产品规格管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			//成本input
			var costCid='costC';
			//利润率input
			var profitRid='profitR';
			//利润input
			var profitPid='profitP';
			//加工费率input
			var chargeRid='chargeR';
			//加工费input
			var chargePid='chargeP';


			//产品的价格
			var pricePid='priceP';
			//成本
			var cost = document.getElementById(costCid).value;
			//利润
			var profitPNum = '';
			//加工费
			var chargePNum = '';

			//价格
			var pricePNum ='';


			//修改利润触发事件
			var profitbtn = document.getElementById(profitRid);
			//修改加工费触发事件
			var chargebtn = document.getElementById(chargeRid);


			profitbtn.onchange = function(){
				//获取利润变化值
				var profit=document.getElementById(profitRid).value;
				profitPNum =cost*profit/100;
				document.getElementById(profitPid).value =profitPNum;
				//加工费
				var charge=document.getElementById(chargeRid).value;
				chargePNum = cost*charge/100;
				document.getElementById(chargePid).value =chargePNum;
				//价格
				document.getElementById(pricePid).value =parseFloat(cost)+ parseFloat(profitPNum)+parseFloat(chargePNum);
				pricePNum=parseFloat(cost)+ parseFloat(profitPNum)+parseFloat(chargePNum);
			}


			chargebtn.onchange = function(){
				//获取利润变化值
				var profit=document.getElementById(profitRid).value;
				profitPNum =cost*profit/100;
				document.getElementById(profitPid).value =profitPNum;
				//加工费
				var charge=document.getElementById(chargeRid).value;
				chargePNum = cost*charge/100;
				document.getElementById(chargePid).value =chargePNum;

				//价格
				document.getElementById(pricePid).value =parseFloat(cost)+ parseFloat(profitPNum)+parseFloat(chargePNum);
				pricePNum=parseFloat(cost)+ parseFloat(profitPNum)+parseFloat(chargePNum);
			}

		});



	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/qspecification/qSpecification/">产品规格列表</a></li>
		<li class="active"><a href="${ctx}/qspecification/qSpecification/form?id=${qSpecification.id}">产品规格<shiro:hasPermission name="qspecification:qSpecification:edit">${not empty qSpecification.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="qspecification:qSpecification:edit">查看</shiro:lacksPermission></a></li>

	</ul><br/>
	<form:form id="inputForm" modelAttribute="qSpecification" action="${ctx}/qspecification/qSpecification/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">产品型号:</label>
			<div class="controls">
				<sys:treeselect id="qModel" name="qModel.id" value="${QSpecification.qModel.id}" labelName="qModel.name" labelValue="${QSpecification.qModel.name}"
								title="型号" url="/model/qModel/treeData" cssClass="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>

		</div>

		<div class="control-group">
			<label class="control-label">产品规格：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">规格等级：</label>
			<div class="controls">
				<form:select path="type" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('qspecification_grade')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">成本：</label>
			<div class="controls">
				<form:input id="costC"  path="cost" readonly="true" htmlEscape="false" class="input-xlarge" />
					<%--<span class="help-inline"><font color="red">*</font> </span>--%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">利润率：</label>
			<div class="controls">
				<form:input id="profitR" path="profitratio" htmlEscape="true" class="input-xlarge"/>%

				<%--<span class="help-inline"><font color="red">*</font> </span>--%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">利润：</label>
			<div class="controls">
				<form:input id="profitP"  path="profit" readonly="true" htmlEscape="false" class="input-xlarge"/>
					<%--<span class="help-inline"><font color="red">*</font> </span>--%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">加工费率：</label>
			<div class="controls">
				<form:input id="chargeR" path="chargeratio" htmlEscape="true" class="input-xlarge"/>%
				<%--<span class="help-inline"><font color="red">*</font> </span>--%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">加工费：</label>
			<div class="controls">
				<form:input id="chargeP" path="charge" readonly="true" htmlEscape="true" class="input-xlarge"/>
					<%--<span class="help-inline"><font color="red">*</font> </span>--%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">价格：</label>
			<div class="controls">
				<form:input id="priceP" path="price"  readonly="true" htmlEscape="false" class="input-xlarge"/>
					<%--<span class="help-inline"><font color="red">*</font> </span>--%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否显示：</label>
			<div class="controls">
				<form:input path="isShow" htmlEscape="false"  maxlength="5"  class="input-xlarge"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group" style="display: none">
			<label class="control-label">权限标识：</label>
			<div class="controls">
				<form:input path="permission" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="qspecification:qSpecification:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>