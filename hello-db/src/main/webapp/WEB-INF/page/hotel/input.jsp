<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title><spring:message code="hotel"/> </title>
</head>

<body>

<!-- BreadcrumbNavigation begin-->
<div class="row-fluid">
    <div class="navbar">
        <div class="navbar-inner">
            <ul class="breadcrumb">
                <i class="icon-chevron-left hide-sidebar"><a href='#' title="Hide Sidebar" rel='tooltip'>&nbsp;</a></i>
                <i class="icon-chevron-right show-sidebar" style="display:none;"><a href='#' title="Show Sidebar" rel='tooltip'>&nbsp;</a></i>
                <li><a href="${ctx}/admin/hotel">hote list</a><span class="divider">/</span></li>
            </ul>
        </div>
    </div>
</div>
<!-- BreadcrumbNavigation end-->

<div class="row-fluid">
    <!-- block -->
    <div class="block">
        <div class="navbar navbar-inner block-header">
            <div class="muted pull-left">Form</div>
        </div>
        <div class="block-content collapse in">
            <div class="span12">
                <form class="form-horizontal" id="inputForm" action="${ctx}/admin/hotel/save" method="post">
                    <tags:forms __type="hidden" __id="id" __name="id" __value="${obj.id}" />
                    <tags:forms __type="text" __id="name" __name="name" __value="${obj.name}" __title="hotel name" />
                    <tags:forms __type="text" __id="address" __name="address" __value="${obj.address}" __title="hotel address" />
                    <tags:forms __type="text" __id="zip" __name="zip" __value="${obj.zip}" __title="hotel zip" />
                    <tags:forms __type="select" __id="state" __name="state" __value="${obj.state}" __map="${s}" __title="状态" />
                    <tags:forms __type="save" __value="save"/>
                </form>
                <script>
                    $(document).ready(function() {
                    });
                </script>
            </div>
        </div>
    </div>
    <!-- /block -->
</div>

</body>
</html>
