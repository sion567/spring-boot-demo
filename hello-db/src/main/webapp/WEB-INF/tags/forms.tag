<%@tag pageEncoding="UTF-8"%>
<%@ attribute name="__type" type="java.lang.String" required="true"%>
<%@ attribute name="__title" type="java.lang.String"%>
<%@ attribute name="__id" type="java.lang.String"%>
<%@ attribute name="__name" type="java.lang.String"%>
<%@ attribute name="__class" type="java.lang.String"%>
<%@ attribute name="__value" type="java.lang.String" required="true"%>
<%@ attribute name="__map" type="java.util.Map"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%if("hidden".equals(__type)){%>
    <input type="hidden" id="${__id}" name="${__name}" value="<c:out value="${__value}"/>">
<%}else if("text".equals(__type)){%>
<div class="control-group">
    <label class="control-label" for="${__id}">${__title}</label>
    <div class="controls">
        <input class="${__class} input-xlarge focused" id="${__id}" name="${__name}" type="text" value="<c:out value="${__value}"/>">
    </div>
</div>
<%}else if("select".equals(__type)){%>
<div class="control-group success">
    <label class="control-label" for="${__id}"><c:out value="${__title}"/></label>
    <div class="controls">
        <select id="${__id}" name="${__name}">
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
</div>
<%}else if("uneditableInput".equals(__type)){%>
<div class="control-group">
    <label class="control-label">${__title}</label>
    <div class="controls">
        <span class="input-xlarge uneditable-input"><c:out value="${__value}"/></span>
    </div>
</div>
<%}else if("disabledInput".equals(__type)){%>
<div class="control-group">
    <label class="control-label" for="${__id}">${__title}</label>
    <div class="controls">
        <input class="input-xlarge disabled" id="${__id}" type="text" placeholder="<c:out value="${__value}"/>" disabled="disabled">
    </div>
</div>
<%}else if("other".equals(__type)){%>
--=other=--
<%}else{%>
    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Save changes</button>
        <button type="reset" class="btn">Cancel</button>
    </div>
<%}%>