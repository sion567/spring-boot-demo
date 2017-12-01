<%@tag pageEncoding="UTF-8"%>
<%@ attribute name="__type" type="java.lang.String" required="true"%>
<%@ attribute name="__title" type="java.lang.String"%>
<%@ attribute name="__id" type="java.lang.String"%>
<%@ attribute name="__name" type="java.lang.String"%>
<%@ attribute name="__class" type="java.lang.String"%>
<%@ attribute name="__value" type="java.lang.Object" required="true"%>
<%@ attribute name="__map" type="java.util.Map"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
<spring:message code="welcome" />
--%>

<%if("hidden".equals(__type)){%>
    <input type="hidden" id="${__id}" name="${__name}" value="<c:out value="${__value}"/>">
<%}else if("text".equals(__type)){%>
<div class="form-group <c:if test="${not empty err[__name]}">has-error</c:if>">
    <label for="${__id}"><c:out value="${__title}"/></label>
    <input type="text" id="${__id}" name="${__name}" class="${__class}form-control" placeholder="Enter..." value="<c:out value="${__value}"/>">
    <span class="help-block">${err[__name]}</span>
</div>
<%}else if("select".equals(__type)){%>
<div class="form-group">
    <label for="${__id}"><c:out value="${__title}"/></label>
    <select id="${__id}" name="${__name}" class="form-control select2" style="width: 100%;">
        <c:forEach var="type" items="${__map}">
            <c:choose>
                <c:when test="${type.key eq __value}">
                    <option selected="selected" value="${type.key}">${type.value}</option>
                </c:when>
                <c:otherwise>
                    <option value="${type.key}">${type.value}</option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </select>
</div>
<%}else if("disabledInput".equals(__type)){%>
<div class="form-group">
    <label for="${__id}"><c:out value="${__title}"/></label>
    <input id="${__id}" type="text" class="form-control" placeholder="<c:out value="${__value}"/>" disabled="">
</div>
<%}else if("date".equals(__type)){%>
<div class="form-group">
    <label for="${__id}"><c:out value="${__title}"/></label>
    <div class="input-group date">
        <div class="input-group-addon">
            <i class="fa fa-calendar"></i>
        </div>
        <%--
        <input type="text" class="form-control pull-right datepicker" data-date-format="yyyy-mm-dd" id="${__id}" name="${__name}" value="${__value}">
        --%>
        <input type="text" class="form-control" id="${__id}" name="${__name}" value="<fmt:formatDate value="${__value}" />">

    </div>
    <!-- /.input group -->
</div>
<%}else if("other".equals(__type)){%>
--=other=--
<%}else{%>
<div class="box-footer">
    <button type="submit" class="btn btn-primary">保存</button>
</div>
<%}%>
