<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
  <title>登录</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link charset='utf-8' type='text/css' rel='stylesheet' href='<%=path%>/public/css/login-2.css'/>
  <link charset='utf-8' type='text/css' rel='stylesheet' href='<%=path%>/public/css/font-css3-pc.css'/>

</head>
<body id="login">
<div class='jumm'>
  <div class='denglu'>
    <div class='logo'>
      <img src='<%=path%>/public/img/logo.jpg'/>
    </div>
    <div class='denglu-main'>
      <div class='com-name'>
        乔远物流管理系统
      </div>
      <div class='form'>
        <form class="form-signin" action="${path}/login" method="post">
          <%
            String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
            if(error != null){
          %>
          <div class="alert alert-error input-medium controls">
            <button class="close" data-dismiss="alert">×</button>登录失败，请重试.
          </div>
          <%}%>

          <div class='form-div'>
            <div class='icon'>
              <img src='<%=path%>/public/img/icon1.png'/>
            </div>
            <div class='input-div'>
              <input class='myinput' placeholder='账号' id="username" name="username" type="text"/>
            </div>
            <div class='clear'>
            </div>
          </div>
          <div class='form-div'>
            <div class='icon'>
              <img src='<%=path%>/public/img/icon2.png'/>
            </div>
            <div class='input-div'>
              <input class='myinput' placeholder='密码' id="password" name="password" type="password"/>
            </div>
            <div class='clear'></div>
          </div>
          <div class='btn'>
            <input class='singnin' type='submit' value='登录'/>
          </div>
        </form>
      </div>
    </div>
  </div>

  <div class='foot'>
    &copy; 2016 乔远物流版权所有
  </div>
</div>

</body>
</html>
