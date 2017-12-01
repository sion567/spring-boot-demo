<%@tag pageEncoding="UTF-8"%>
<%@ attribute name="__type" type="java.lang.String" required="true"%>
<%@ attribute name="__val" type="java.lang.Object" required="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<%if("date".equals(__type)){%>
<fmt:formatDate value="${__val}" type="date"/>
<%}else if("datetime".equals(__type)){%>
<fmt:formatDate value="${__val}" type="both"/>
<%}else if("time".equals(__type)){%>
<fmt:formatDate value="${__val}" type="time"/>
<%}else if("text".equals(__type)){%>
<c:out value="${fn:substring(__val, 0, 20)}......" />
<%}else if("money".equals(__type)){%>
<fmt:formatNumber value="${__val}" type="number" pattern="￥0.00"/>
<%}else{%>
--=other=--
<%}%>