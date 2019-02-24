<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


<html>
<head>
	<title>库存管理</title>
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

		});
		//产品名称name
		var productname="";
		//产品名称input
		var nameid='nameC';
		//修改款号触发事件
		var modelbtn ="其他";
		//修改颜色费触发事件
		var colorbtn = "其他";
		//修改材料触发事件
		var materialbtn = "其他";
		//修改规格触发事件
		var specbtn = "其他";
		//款号
		function modelnumfunction(id)
		{
			modelbtn = document.getElementById(id);
			document.getElementById(nameid).value=modelbtn.value+"+"+colorbtn.value+"+"+materialbtn.value+"+"+specbtn.value;
		}
		//颜色
		function colorfunction(id)
		{
			colorbtn = document.getElementById(id);
			document.getElementById(nameid).value=modelbtn.value+"+"+colorbtn.value+"+"+materialbtn.value+"+"+specbtn.value;
		}
		//规格
		function specfunction(id)
		{
			specbtn = document.getElementById(id);
			document.getElementById(nameid).value=modelbtn.value+"+"+colorbtn.value+"+"+materialbtn.value+"+"+specbtn.value;
		}
		//材料
		function materialfunction(id)
		{
			materialbtn = document.getElementById(id);
			document.getElementById(nameid).value=modelbtn.value+"+"+colorbtn.value+"+"+materialbtn.value+"+"+specbtn.value;
		}


	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/productinven/talProduct/">产品列表</a></li>
		<li class="active"><a href="${ctx}/productinven/talProduct/form?id=${talProduct.id}">库存<shiro:hasPermission name="productinven:talProduct:edit">${not empty talProduct.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="productinven:talProduct:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="talProduct" action="${ctx}/productinven/talProduct/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">产品名称：</label>
			<div class="controls">
				<form:input id ="nameC" readonly="true" path="productname" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">款号：</label>
			<div class="controls">
				<form:select id="modelnumid" path="modelnum" class="required input-xlarge " onchange="modelnumfunction(this.id)">
					<form:option value="" label="请选择"/>
					<form:options   items="${modelnumNameList}"  htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">颜色：</label>
			<div class="controls">
				<form:select id="colorid" path="colorname" class="required input-xlarge " onchange="colorfunction(this.id)">
					<form:option value="" label="请选择"/>
					<form:options   items="${colorNameList}"  htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">规格：</label>
			<div class="controls">
				<form:select id="specid" path="specification" class="required input-xlarge " onchange="specfunction(this.id)">
					<form:option value="" label="请选择"/>
					<form:options  items="${specNameList}" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">材料：</label>
			<div class="controls">
				<form:select id="materialsid" path="texture" class="required input-xlarge " onchange="materialfunction(this.id)">
					<form:option value="" label="请选择"/>
					<form:options  items="${materialNameList}"  htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">总库存：</label>
			<div class="controls">
				<form:input placeholder="请填写大于0的数字" path="tallinventory" htmlEscape="false" maxlength="30" class="required input-xlarge " oninput="value=value.replace(/[^\d]/g,'')"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>

		</div>
		<div class="control-group">
			<label class="control-label">当前库存：</label>
			<div class="controls">
				<form:input placeholder="请填写大于0的数字" path="nowinventory" htmlEscape="false" maxlength="30" class="required input-xlarge " oninput="value=value.replace(/[^\d]/g,'')"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>

		</div>
		<div class="control-group">
			<label class="control-label">成本价：</label>
			<div class="controls">
				<form:input placeholder="请填写大于0的数字" path="cost" htmlEscape="false" maxlength="30" class="input-xlarge " oninput="value=value.replace(/[^\d]/g,'')"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">销售价：</label>
			<div class="controls">
				<form:input placeholder="请填写大于0的数字" path="price" htmlEscape="false" maxlength="30" class="input-xlarge " oninput="value=value.replace(/[^\d]/g,'')"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">上传图片：</label>
			<div class="controls">
					<form:hidden id="nameImage" multiple="multiple" path="picture" htmlEscape="false" maxlength="255" class="input-xlarge"/>
					<sys:ckfinder input="nameImage" type="images" uploadPath="/photo" selectMultiple="ture" maxWidth="100" maxHeight="100"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="productinven:talProduct:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>