<%@include file="/html/init.jsp"%>

<aui:button-row cssClass="guestbook-admin-buttons">
	<c:if test='<%= GuestbookModelPermission.contains(permissionChecker, scopeGroupId, "ADD_GUESTBOOK") %>'>
		<portlet:renderURL var="addGuestbookURL">
			<portlet:param name="mvcPath"
				value="/html/guestbookadmin/edit_guestbook.jsp" />
		</portlet:renderURL>
	
		<aui:button onClick="<%= addGuestbookURL.toString() %>"
			value="Add Guestbook" />
	</c:if>
</aui:button-row>

<liferay-ui:search-container>
	<liferay-ui:search-container-results
		results="<%= GuestbookLocalServiceUtil.getGuestbooks(scopeGroupId,
						searchContainer.getStart(),
						searchContainer.getEnd()) %>"
		total="<%= GuestbookLocalServiceUtil.getGuestbooksCount(scopeGroupId) %>" />

	<liferay-ui:search-container-row
		className="com.liferay.docs.guestbook.model.Guestbook" modelVar="guestbook">
		
		<portlet:renderURL var="viewGuestbook">
			<portlet:param name="mvcPath" value="/html/guestbookadmin/view_guestbook.jsp" />
			<portlet:param name="guestbookId" value="<%= String.valueOf(guestbook.getGuestbookId()) %>" />
		</portlet:renderURL>
		
		<liferay-ui:search-container-column-text property="name" href="<%= viewGuestbook %>" />

		<liferay-ui:search-container-column-text name="status" >
            <aui:workflow-status showIcon="<%= false %>" showLabel="<%= false %>"
                status="<%= guestbook.getStatus() %>" />
        </liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-jsp
	            path="/html/guestbookadmin/guestbook_actions.jsp"
	            align="right" />
	
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>