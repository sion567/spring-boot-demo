<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>


<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title><sitemesh:write property='title' /> | OOXX | 系统</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="${ctx}/webjars/adminlte/2.3.11/bootstrap/css/bootstrap.css">

  <!-- Font Awesome-->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
  <!-- Ionicons-->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${ctx}/webjars/adminlte/2.3.11/dist/css/AdminLTE.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="${ctx}/webjars/adminlte/2.3.11/dist/css/skins/_all-skins.css">
  <!-- iCheck -->
  <link rel="stylesheet" href="${ctx}/webjars/adminlte/2.3.11/plugins/iCheck/flat/blue.css">
  <!-- Morris chart -->
  <link rel="stylesheet" href="${ctx}/webjars/adminlte/2.3.11/plugins/morris/morris.css">
  <!-- jvectormap -->
  <link rel="stylesheet" href="${ctx}/webjars/adminlte/2.3.11/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
  <!-- Date Picker -->
  <link rel="stylesheet" href="${ctx}/webjars/adminlte/2.3.11/plugins/datepicker/datepicker3.css">
  <!-- Daterange picker -->
  <link rel="stylesheet" href="${ctx}/webjars/adminlte/2.3.11/plugins/daterangepicker/daterangepicker.css">
  <!-- bootstrap wysihtml5 - text editor -->
  <link rel="stylesheet" href="${ctx}/webjars/adminlte/2.3.11/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
  <!-- jQuery 2.2.3 -->
  <script src="${ctx}/webjars/adminlte/2.3.11/plugins/jQuery/jquery-2.2.3.min.js"></script>
  <!-- jQuery UI 1.11.4 -->
  <script src="${ctx}/webjars/adminlte/2.3.11/plugins/jQueryUI/jquery-ui.min.js"></script>
  <!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
  <script>
      $.widget.bridge('uibutton', $.ui.button);
  </script>
  <!-- Bootstrap 3.3.6 -->
  <script src="${ctx}/webjars/adminlte/2.3.11/bootstrap/js/bootstrap.js"></script>
  <!-- Morris.js charts
  <script src="https://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
  <script src="${ctx}/webjars/adminlte/2.3.11/plugins/morris/morris.js"></script>
  -->
  <!-- Sparkline -->
  <script src="${ctx}/webjars/adminlte/2.3.11/plugins/sparkline/jquery.sparkline.min.js"></script>
  <!-- jvectormap
  <script src="${ctx}/webjars/adminlte/2.3.11/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
  <script src="${ctx}/webjars/adminlte/2.3.11/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
  -->
  <!-- jQuery Knob Chart -->
  <script src="${ctx}/webjars/adminlte/2.3.11/plugins/knob/jquery.knob.js"></script>
  <!-- daterangepicker -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
  <script src="${ctx}/webjars/adminlte/2.3.11/plugins/daterangepicker/daterangepicker.js"></script>
  <!-- datepicker -->
  <script src="${ctx}/webjars/adminlte/2.3.11/plugins/datepicker/bootstrap-datepicker.js"></script>
  <!-- Bootstrap WYSIHTML5
  <script src="${ctx}/webjars/adminlte/2.3.11/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.js"></script>
  -->
  <!-- InputMask -->
  <script src="${ctx}/webjars/adminlte/2.3.11/plugins/input-mask/jquery.inputmask.js"></script>
  <script src="${ctx}/webjars/adminlte/2.3.11/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
  <script src="${ctx}/webjars/adminlte/2.3.11/plugins/input-mask/jquery.inputmask.extensions.js"></script>
  <!-- Slimscroll -->
  <script src="${ctx}/webjars/adminlte/2.3.11/plugins/slimScroll/jquery.slimscroll.min.js"></script>
  <!-- FastClick -->
  <script src="${ctx}/webjars/adminlte/2.3.11/plugins/fastclick/fastclick.js"></script>
  <!-- AdminLTE App -->
  <script src="${ctx}/webjars/adminlte/2.3.11/dist/js/app.min.js"></script>
  <!-- AdminLTE for demo purposes -->
  <script src="${ctx}/webjars/adminlte/2.3.11/dist/js/demo.js"></script>
  <sitemesh:write property='head'/>
</head>


<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
  <%@ include file="/WEB-INF/layouts/main-header.jsp" %>
  <!-- Left side column. contains the logo and sidebar -->
  <%@ include file="/WEB-INF/layouts/main-sidebar.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <sitemesh:write property='body'/>
  </div>
  <!-- /.content-wrapper -->

  <%@ include file="/WEB-INF/layouts/main-footer.jsp" %>
  <%-- Control Sidebar
  <%@ include file="/WEB-INF/layouts/control-sidebar.jsp" %>
  --%>
  <!-- Add the sidebar's background. This div must be placed
     immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->
</body>
</html>
