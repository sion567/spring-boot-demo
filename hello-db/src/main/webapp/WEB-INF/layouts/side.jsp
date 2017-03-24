<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div class="span3" id="sidebar">
  <ul class="nav nav-list bs-docs-sidenav nav-collapse collapse">
    <li <c:if test="${target eq 'wel'}">class="active"</c:if>>
      <a href="${ctx}/admin/wel"><i class="icon-chevron-right"></i> Dashboard</a>
    </li>
    <li>
      <a href="${ctx}/admin/hotel"><i class="icon-chevron-right"></i> 酒店维护</a>
    </li>

  </ul>
</div>