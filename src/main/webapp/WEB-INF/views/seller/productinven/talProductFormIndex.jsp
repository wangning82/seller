<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">

	</script>
	<style type="text/css">

		#bj{
			width:1200px;
			height:550px;
			border:3px solid #1421ff;
			text-align:center;
			vertical-align:middle;
			display:table-cell; //将对象作为表格单元格显示
		background:pink;
		}

		img{
			width:1197px;
			height:547px;
			margin:0 auto;
			vertical-align:middle;
			border:2px solid green;
		}

	</style>
</head>
<body>
	<form:form id="inputForm" modelAttribute="talProduct" action="" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div id="bj">
			<img src="/seller/static/seller/image/welcome.jpg"/>
		</div>
	</form:form>
</body>
</html>