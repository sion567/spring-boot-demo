<%@tag pageEncoding="UTF-8"%>
<%@ attribute name="pb" type="com.jst.dto.PageBean" required="true"%>
<%@ attribute name="paginationSize" type="java.lang.Integer" required="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
    long current = pb.getCurPage() ;
    long end = pb.getTotalPage();
    long total = pb.getRows();

%>

<% if (total > 0){%>
<div class="row">
    <%--
    <div class="col-sm-5">
        <div class="dataTables_info" role="status" aria-live="polite">Showing ${begin} to ${end} of ${total} entries</div>
    </div>
    --%>
    <div class="col-sm-7">
        <div class="dataTables_paginate paging_simple_numbers">
            <ul class="pagination">

                <% if(pb.isFirst()){%>
                    <li class="paginate_button previous disabled"><a href="#">上一页</a></li>
                <%}else{%>
                    <li class="paginate_button previous"><a href="?page=<%=current-1 %>">上一页</a></li>
                <%} %>

<% for(long i = 1; i <= end; i++){%>
    <% if (current == i){%>
        <li class="paginate_button active"><a href="?page=<%=i %>"><%=i %></a></li>
    <%}else{%>
        <% if (end <= 10){%>
            <li class="paginate_button"><a href="?page=<%=i %>"><%=i %></a></li>
        <%}else{%>

    <% if (current < 5){%>
        <li class="paginate_button"><a href="?page=<%=i %>"><%=i %></a></li>
        <% if (i == 5){%><li><span>......</span></li> <% break; } %>
    <%}else if(current > 5){%>
        <% if (i <= 2){%>
            <li class="paginate_button"><a href="?page=<%=i %>"><%=i %></a></li>
            <% if (i == 2)%> <li><span>......</span></li>
        <%}else if (i >= (current - 2) && i <= (current + 2)) { %>
             <li class="paginate_button"><a href="?page=<%=i %>"><%=i %></a></li>
             <% if (i == (current + 2) && i < end)%><li><span>......</span></li>
        <%}else if (i>end-2){%>
            <li class="paginate_button"><a href="?page=<%=i %>"><%=i %></a></li>
        <%}else{ continue; } %>
    <%}else{%>
        <% if (i <= 7){%>
            <li class="paginate_button"><a href="?page=<%=i %>"><%=i %></a></li>
            <% if (i == 7){%><li><span>......</span></li><% break;} %>
    <%} }  %>
    <% } } %>
<%} %>

                <% if (pb.isLast()){%>
                    <li class="paginate_button next disabled"><a href="#">下一页</a></li>
                <%}else{%>
                    <li class="paginate_button next"><a href="?page=<%=current+1 %>">下一页</a></li>
                <%} %>

            </ul>
        </div>
    </div>
</div>

<% }%>
