<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html class="no-js">

<head>
  <title>Admin - <sitemesh:write property='title'/></title>
  <!-- Bootstrap -->
  <link href="${ctx}/public/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
  <link href="${ctx}/public/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
  <link href="${ctx}/public/vendors/easypiechart/jquery.easy-pie-chart.css" rel="stylesheet" media="screen">
  <link href="${ctx}/public/assets/styles.css" rel="stylesheet" media="screen">
  <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
  <!--[if lt IE 9]>
  <script src="${ctx}/public/vendors/html5.js"></script>
  <![endif]-->
  <script src="${ctx}/public/vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>

  <sitemesh:write property='head'/>
</head>

<body>
<%@ include file="/WEB-INF/layouts/header.jsp" %>
<div class="container-fluid">
  <div class="row-fluid">
    <%@ include file="/WEB-INF/layouts/side.jsp" %>
    <!--/span-->
    <div class="span9" id="content">
      <sitemesh:write property='body'/>
    </div>
  </div>
  <hr>
  <%@ include file="/WEB-INF/layouts/footer.jsp" %>
</div>
<!--/.fluid-container-->
<script src="${ctx}/public/vendors/jquery-1.9.1.min.js"></script>
<script src="${ctx}/public/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx}/public/assets/scripts.js"></script>
<script>
  $(function() {
  });
</script>
</body>

</html>