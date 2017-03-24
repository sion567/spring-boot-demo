<%@tag pageEncoding="UTF-8"%>
<%@ attribute name="page" type="org.springframework.data.domain.Page" required="true"%>
<%@ attribute name="paginationSize" type="java.lang.Integer" required="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
    int current =  page.getNumber() + 1;
    int begin = Math.max(1, current - paginationSize/2);
    int end = Math.min(begin + (paginationSize - 1), page.getTotalPages());

    request.setAttribute("current", current);
    request.setAttribute("begin", begin);
    request.setAttribute("end", end);
%>
<% if (end > 0){%>
<div class="row">
    <div class="span6">
        <div class="dataTables_info" id="example2_info"></div>
    </div>
    <div class="span6">
        <div class="dataTables_paginate paging_bootstrap pagination">
            <ul>
                <% if (current == begin){%>
                <li class="prev disabled"><a href="#">← Previous</a></li>
                <%}else{%>
                <li class="prev"><a href="?page=${current-1}">← Previous</a></li>
                <%} %>
                <c:forEach var="i" begin="${begin}" end="${end}">
                    <c:choose>
                        <c:when test="${i == current}">
                            <li class="active"><a href="?page=${i}">${i}</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="?page=${i}">${i}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <% if (current == end){%>
                <li class="next disabled"><a href="#">Next → </a></li>
                <%}else{%>
                <li class="next"><a href="?page=${current+1}">Next → </a></li>
                <%} %>
            </ul>
        </div>
    </div>
</div>
<% }%>
