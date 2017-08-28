<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>library</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="/resources/css/style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="/resources/js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/resources/js/script.js"></script>
	<script src="/resources/ckeditor/ckeditor.js"></script>
</head>
<body>
<div class="container">
	<header>
		<tiles:insertAttribute name="header" />
	</header>
	<div id="menu">
		<tiles:insertAttribute name="menu" />
	</div>
	<div class="container-fluid">
	<tiles:insertAttribute name="body" />
	</div>
	<footer>
		<tiles:insertAttribute name="footer" />
	</footer>
</div>
</body>
</html>