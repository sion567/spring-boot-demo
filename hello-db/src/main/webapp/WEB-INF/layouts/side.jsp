<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div class="span3" id="sidebar">
  <ul class="nav nav-list bs-docs-sidenav nav-collapse collapse">
    <li <c:if test="${target eq 'wel'}">class="active"</c:if>>
      <a href="${ctx}/admin/wel"><i class="icon-chevron-right"></i> Dashboard</a>
    </li>

    <shiro:guest>  
      <li><a href="#"><i class="icon-chevron-right"></i> >>guest</a></li>
    </shiro:guest>   
    <shiro:authenticated>
      <li><a href="#"><i class="icon-chevron-right"></i>
         [<shiro:principal/>]>>authenticated  
      </a></li>
    </shiro:authenticated>   
    <shiro:notAuthenticated>
      <li><a href="#"><i class="icon-chevron-right"></i>
         >>notAuthenticated 
      </a></li>
    </shiro:notAuthenticated>
    <shiro:hasRole name="customer">  
      <li><a href="#"><i class="icon-chevron-right"></i>
         >>has role:customer
      </a></li>
    </shiro:hasRole>   
    <shiro:hasRole name="agent">  
      <li><a href="#"><i class="icon-chevron-right"></i>
         >>has role:agent
      </a></li>
    </shiro:hasRole> 
    <shiro:hasRole name="admin">  
      <li><a href="#"><i class="icon-chevron-right"></i>
         >>has role:admin
      </a></li>
    </shiro:hasRole> 
    <shiro:hasAnyRoles name="admin,agent,customer">  
      <li><a href="#"><i class="icon-chevron-right"></i>
         >>has any role:admin,agent,customer
      </a></li> 
    </shiro:hasAnyRoles>   
    <shiro:lacksRole name="abc">  
      <li><a href="#"><i class="icon-chevron-right"></i>
         >>not role:abc
      </a></li>  
    </shiro:lacksRole>
    <shiro:hasPermission name="sys:city:create">  
      <li><a href="#"><i class="icon-chevron-right"></i>
         >>has permission:sys:city:create 
      </a></li> 
    </shiro:hasPermission>
    <shiro:hasPermission name="sys:rating:create">  
      <li><a href="#"><i class="icon-chevron-right"></i>
         >>has permission:sys:rating:create
      </a></li> 
    </shiro:hasPermission>

    <%--
    <li>
      <a href="${ctx}/admin/hotel"><i class="icon-chevron-right"></i> 酒店维护</a>
    </li>
    --%>
  </ul>
</div>