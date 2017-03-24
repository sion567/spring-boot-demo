<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
  <title>欢迎页</title>
</head>

<body>

<div class="row-fluid">
  <!-- BreadcrumbNavigation begin-->
  <div class="navbar">
    <div class="navbar-inner">
      <ul class="breadcrumb">
        <i class="icon-chevron-left hide-sidebar"><a href='#' title="Hide Sidebar" rel='tooltip'>&nbsp;</a></i>
        <i class="icon-chevron-right show-sidebar" style="display:none;"><a href='#' title="Show Sidebar" rel='tooltip'>&nbsp;</a></i>
        <li>
          <a href="#">Dashboard</a> <span class="divider">/</span>
        </li>
        <li class="active">
          Welcome
        </li>
      </ul>
    </div>
  </div>
  <!-- BreadcrumbNavigation end-->

  <div class="alert alert-success">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <h4>Welcome</h4>
    The operation completed successfully</div>

</div>

</body>
</html>