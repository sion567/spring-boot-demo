<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
  <title>Login</title>
  <!-- Bootstrap -->
  <link href="<%=path%>/public/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
  <link href="<%=path%>/public/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
  <link href="<%=path%>/public/assets/styles.css" rel="stylesheet" media="screen">
  <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
  <!--[if lt IE 9]>
  <script src="<%=path%>/public/vendors/html5.js"></script>
  <![endif]-->
  <script src="<%=path%>/public/vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>
</head>
<body id="login">
<div class="container">

  <form class="form-signin" action="${path}/login" method="post">
    <%
      String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
      if(error != null){
    %>
    <div class="alert alert-error input-medium controls">
      <button class="close" data-dismiss="alert">×</button>登录失败，请重试.
    </div>
    <%
      }
    %>
    <h2 class="form-signin-heading">Please sign in</h2>
    <input id="username" name="username" type="text" class="input-block-level" placeholder="admin">
    <input id="password" name="password" type="password" class="input-block-level" placeholder="aa">
    <label class="checkbox">
      <input type="checkbox" id="rememberMe" name="rememberMe"> Remember me
    </label>
    <button class="btn btn-large btn-primary" type="submit">Sign in</button>
  </form>

</div> <!-- /container -->
<script src="<%=path%>/public/vendors/jquery-1.9.1.min.js"></script>
<script src="<%=path%>/public/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
