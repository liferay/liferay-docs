<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<portlet:defineObjects />

This is the <b>Russ Adt 2</b> portlet in View mode.
        <%
        List<com.liferay.portlet.template.RussAdtPortletDisplayTemplateHandler> russadt = getRussAdtPortletDisplayTemplateHandler();
    
        long ddmTemplateId =
            PortletDisplayTemplateUtil.getPortletDisplayTemplateDDMTemplateId(
                displayStyleGroupId, displayStyle);
        Map<String, Object> contextObjects = new HashMap<String, Object>();
        contextObjects.put("myExtraObject", someExtraObject);
        %>
    
        <c:choose>
            <c:when test="<%= portletDisplayDDMTemplateId > 0 %>">
                 <%= PortletDisplayTemplateUtil.renderDDMTemplate(pageContext, ddmTemplateId, myList, contextObjects) %>
            </c:when>
            <c:otherwise>
                <%= //Default view code %>
            </c:otherwise>
        </c:choose>
