<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html>
<html>

<head>
	<tiles:insertAttribute name="header"/>
</head>

<body>

<div id="wrapper">

	<tiles:insertAttribute name="left"/>

<div id="page-wrapper" class="gray-bg">
	<tiles:insertAttribute name="header"/>
	<tiles:insertAttribute name="nav"/>	
	<tiles:insertAttribute name="pageHead"/>

	<div class="wrapper wrapper-content animated fadeInRight">
	   <tiles:insertAttribute name="middle"/>
	   <tiles:insertAttribute name="footer"/>
	</div>
</div>
</div>
	<tiles:insertAttribute name="script"/>
</body>

</html>
