<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>

<p>Hello from view.jsp!</p>

<c:if test="<%= true %>">
    <p>Hello from JSTL!</p>
</c:if>

<p>Hello from <aui:a href="http://alloyui.com">AlloyUI</aui:a>!</p>