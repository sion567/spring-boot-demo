<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title><spring:message code="hotel"/></title>
    <link href="${ctx}/public/assets/DT_bootstrap.css" rel="stylesheet" media="screen">
</head>

<body>
<!-- BreadcrumbNavigation begin-->
<div class="row-fluid">
    <div class="navbar">
        <div class="navbar-inner">
            <ul class="breadcrumb">
                <i class="icon-chevron-left hide-sidebar"><a href='#' title="Hide Sidebar" rel='tooltip'>&nbsp;</a></i>
                <i class="icon-chevron-right show-sidebar" style="display:none;"><a href='#' title="Show Sidebar" rel='tooltip'>&nbsp;</a></i>
                <li class="active">hotel list<span class="divider">/</span></li>
            </ul>
        </div>
    </div>
</div>
<!-- BreadcrumbNavigation end-->

<c:if test="${not empty message}">
    <div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
</c:if>

<div class="row-fluid">
    <!-- block -->
    <div class="block">
        <div class="navbar navbar-inner block-header">
            <div class="muted pull-left">hotel list</div>
        </div>
        <div class="block-content collapse in">
            <div class="span12">
                <div class="table-toolbar">
                    <div class="btn-group">
                        <shiro:hasPermission name="hotel:create">
                            <a href="${ctx}/admin/hotel/input/0"><button class="btn btn-success">Add New <i class="icon-plus icon-white"></i></button></a>
                        </shiro:hasPermission>
                    </div>
                    <div class="btn-group pull-right">
                        <button data-toggle="dropdown" class="btn dropdown-toggle">Tools <span class="caret"></span></button>
                        <ul class="dropdown-menu">
                            <li><a href="#">Export to Excel</a></li>
                        </ul>
                    </div>
                </div>
                <div class="dataTables_wrapper form-inline" role="grid">
                    <div class="row">
                        <div class="span6">
                            <div class="dataTables_length">
                                <label>
                                    <form action="#">
                                        <input id="search_name" name="search_EQ_name" type="text" value="${param.search_EQ_name}" />
                                        <input type="submit" value="Search" id="search_btn">
                                    </form>
                                </label>
                            </div>
                        </div>
                        <div class="span6">
                            <div class="dataTables_filter"></div>
                        </div>
                    </div>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>hotel name</th>
                            <th>hotel address</th>
                            <th>hotel zip</th>
                            <th>状态</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pagebean.content}" var="h" varStatus="index">
                            <tr>
                                <td title="${h.id}">${index.count}</td>
                                <td>${h.name}</td>
                                <td>${h.address}</td>
                                <td>${h.zip}</td>
                                <td>${s[h.state]}</td>
                                <th>
                                    <a href="${ctx}/admin/hotel/input/${h.id}">编辑</a>
                                </th>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <tags:pagination page="${pagebean}" paginationSize="10"/>
                </div>
            </div>
        </div>
    </div>
    <!-- /block -->
</div>

</body>
</html>