<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@taglib uri="/WEB-INF/tld/spring.tld" prefix="spring" %>
<%@taglib uri="/WEB-INF/tld/spring-form.tld" prefix="form" %>

Time is ${var1}

<c:if test="${var1 != null }"><div>Test JSTL</div></c:if>

<form:form modelAttribute="item" method="POST">
	<form:input path="name" /><br />
	<input type="submit" value="send" />
</form:form>