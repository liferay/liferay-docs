# Updating the View

Since you updated more than just the basic mechanism behind creating the Entry,
and actually completely changed the method and structure, you'll want to make
some updates to the UI as well. To do that, you'll need to create a new JSP for
managing Guestbooks, and update the existing JSPs.

1. First you should update the dependencies that you'll need.
2. Open `init.jsp` from `/src/main/resources/META-INF/resources/`
3. Add the following additional dependencies:
    
	<%@ taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %>

	<%@ page import="java.util.List" %>
	<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
	<%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %>
	<%@ page import="com.liferay.portal.kernel.util.StringPool" %>
	<%@ page import="com.liferay.portal.kernel.model.PersistedModel" %>
	<%@ page import="com.liferay.portal.kernel.dao.search.SearchEntry" %>
	<%@ page import="com.liferay.portal.kernel.dao.search.ResultRow" %>
	<%@ page import="com.liferay.docs.guestbook.model.Guestbook" %>
	<%@ page import="com.liferay.docs.guestbook.service.EntryLocalServiceUtil" %>
	<%@ page import="com.liferay.docs.guestbook.service.GuestbookLocalServiceUtil" %>
	<%@ page import="com.liferay.docs.guestbook.model.Entry" %>
	

4. Open the `view.jsp` file found in `/resources/META-INF/resources/`
5. Replace the contents with the following code:

	<%@include file="init.jsp"%>

	<%
		long guestbookId = Long.valueOf((Long) renderRequest
				.getAttribute("guestbookId"));
	%>

	<aui:nav cssClass="nav-tabs">

		<%
			List<Guestbook> guestbooks = GuestbookLocalServiceUtil
						.getGuestbooks(scopeGroupId);
				for (int i = 0; i < guestbooks.size(); i++) {
					Guestbook curGuestbook = (Guestbook) guestbooks.get(i);
					String cssClass = StringPool.BLANK;
					if (curGuestbook.getGuestbookId() == guestbookId) {
						cssClass = "active";
					}
				
					{
				
		%>

		<portlet:renderURL var="viewPageURL">
			<portlet:param name="mvcPath" value="/view.jsp" />
			<portlet:param name="guestbookId"
				value="<%=String.valueOf(curGuestbook.getGuestbookId())%>" />
		</portlet:renderURL>

		<aui:nav-item cssClass="<%=cssClass%>" href="<%=viewPageURL%>"
			label="<%=HtmlUtil.escape(curGuestbook.getName())%>" />

		<%
				}
			}
		%>

	</aui:nav>

	<aui:button-row cssClass="guestbook-buttons">

			<portlet:renderURL var="addGuestbookURL">
				<portlet:param name="mvcPath"
					value="/edit_guestbook.jsp" />
			</portlet:renderURL>
		
			<aui:button onClick="<%=addGuestbookURL.toString()%>" 
				value="Add Guestbook" />
		
			<portlet:renderURL var="addEntryURL">
				<portlet:param name="mvcPath" value="/edit_entry.jsp" />
				<portlet:param name="guestbookId"
					value="<%=String.valueOf(guestbookId)%>" />
			</portlet:renderURL>
		
			<aui:button onClick="<%=addEntryURL.toString()%>" value="Add Entry"></aui:button>
	
	</aui:button-row>

	<liferay-ui:search-container total="<%=EntryLocalServiceUtil.getEntriesCount()%>">
		<liferay-ui:search-container-results
			results="<%=EntryLocalServiceUtil.getEntries(scopeGroupId.longValue(),
							guestbookId, searchContainer.getStart(),
							searchContainer.getEnd())%>" />

		<liferay-ui:search-container-row
			className="com.liferay.docs.guestbook.model.Entry" modelVar="entry">

			<liferay-ui:search-container-column-text property="message" />

			<liferay-ui:search-container-column-text property="name" />

		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator />
	

You've significantly expanded the `view.jsp` now. There are now buttons for both
adding an Entry and adding a new Guestbook. You've also added the necessary 
backend information to connect to this. Right now you're doing all of this with
fairly generic styles to accomplish this, but later you'll learn to leverage 
the full power of Liferay's UI tools to enhance your JSPs.

Next you need to create a new `edit_guestbook.jsp` and edit the `edit_entry.jsp`
to match our new system.

1. Open `edit_entry.jsp.`
2. Replace the existing code with this:

	<%@include file="init.jsp" %>

	<% 

	long entryId = ParamUtil.getLong(renderRequest, "entryId");

	Entry entry = null;
	if (entryId > 0) {
		entry = EntryLocalServiceUtil.getEntry(entryId);
	}

	long guestbookId = ParamUtil.getLong(renderRequest, "guestbookId");

	%>

	<portlet:renderURL var="viewURL">

		<portlet:param name="mvcPath" value="/view.jsp"></portlet:param>

	</portlet:renderURL>

	<portlet:actionURL name="addEntry" var="addEntryURL"></portlet:actionURL>


	<aui:form action="<%= addEntryURL %>" name="<portlet:namespace />fm">

	    <aui:model-context bean="<%= entry %>" model="<%= Entry.class %>" />

			<aui:fieldset>

			    <aui:input name="name" />
			    <aui:input name="email" />
			    <aui:input name="message" />
			    <aui:input name="entryId" type="hidden" />
				<aui:input name="guestbookId" type="hidden" value='<%= entry == null ? guestbookId : entry.getGuestbookId() %>'/>

			</aui:fieldset>
		
			<aui:button-row>

	            <aui:button type="submit"></aui:button>
	            <aui:button type="cancel" onClick="<%= viewURL.toString() %>"></aui:button>
            
	        </aui:button-row>
	</aui:form>
	
3. Now right-click on the `resources` folder and select *New* &rarr; *File*.
4. Name the file `edit_guestbook.jsp` and click *Finish*.
5. Open the new file and paste in this code:

	<%@include file = "init.jsp" %>

	<portlet:renderURL var="viewURL">
	    <portlet:param name="mvcPath" value="/view.jsp"></portlet:param>
	</portlet:renderURL>

	<portlet:actionURL name="addGuestbook" var="addGuestbookURL"></portlet:actionURL>

	<aui:form action="<%= addGuestbookURL %>" name="<portlet:namespace />fm">

	        <aui:fieldset>

	            <aui:input name="name" />

	        </aui:fieldset>

	        <aui:button-row>

	            <aui:button type="submit"></aui:button>
	            <aui:button type="cancel" onClick="<%= viewURL %>"></aui:button>

	        </aui:button-row>
	</aui:form>

	
These updates are a bit more simple. You created additional fields to be added 
for each entry creation to match up to the fields you created on the backend,
and you created a new form for creating a guestbook with one field.
