# Updating the View [](id=updating-the-view)

<div class="learn-path-step">
    <p>Refactoring the Prototype<br>Step 5 of 6</p>
</div>

You updated more than just the mechanism behind creating entries: you completely
changed the method and structure. You must, therefore, update the UI as well. To
do that, you must create a new JSP for managing guestbooks and update the
existing JSPs. 

1.  First, you must update your dependencies. In your `guestbook-web` module, 
    open `init.jsp` from `/src/main/resources/META-INF/resources/`. In this 
    file, add the following additional dependencies: 

        <%@ taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %>
        <%@ page import="java.util.List" %>
        <%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
        <%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %>
        <%@ page import="com.liferay.petra.string.StringPool" %>
        <%@ page import="com.liferay.portal.kernel.model.PersistedModel" %>
        <%@ page import="com.liferay.portal.kernel.dao.search.SearchEntry" %>
        <%@ page import="com.liferay.portal.kernel.dao.search.ResultRow" %>
        <%@ page import="com.liferay.docs.guestbook.model.Guestbook" %>
        <%@ page import="com.liferay.docs.guestbook.service.EntryLocalServiceUtil" %>
        <%@ page import="com.liferay.docs.guestbook.service.GuestbookLocalServiceUtil" %>
        <%@ page import="com.liferay.docs.guestbook.model.Entry" %> 

2.  Open the `view.jsp` file found in 
    `/resources/META-INF/resources/guestbookwebportlet`. Replace this file's 
    contents with the following code: 

        <%@include file="../init.jsp"%>

        <%
        long guestbookId = Long.valueOf((Long) renderRequest
                .getAttribute("guestbookId"));
        %>

        <aui:button-row cssClass="guestbook-buttons">

            <portlet:renderURL var="addEntryURL">
                <portlet:param name="mvcPath" value="/guestbookwebportlet/edit_entry.jsp" />
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

        </liferay-ui:search-container>

This `view.jsp` now retrieves the entries from the guestbook it gets from the 
`render` method. It does this inside a @product@ construct called a *Search 
Container*. This is a front-end component that makes it easy to display data in 
rows and columns. The `EntryLocalServiceUtil` call retrieves the data from your 
new Service Builder-based back-end. Otherwise, this JSP is much the same: you 
still have an *Add Entry* button with its corresponding URL. 

Next, you need to edit the `edit_entry.jsp`:

1.  Open `edit_entry.jsp` and replace the existing code with this: 

        <%@include file="../init.jsp" %>

        <% 

        long entryId = ParamUtil.getLong(renderRequest, "entryId");

        Entry entry = null;
        if (entryId > 0) {
          entry = EntryLocalServiceUtil.getEntry(entryId);
        }

        long guestbookId = ParamUtil.getLong(renderRequest, "guestbookId");

        %>

        <portlet:renderURL var="viewURL">

        <portlet:param name="mvcPath" value="/guestbookwebportlet/view.jsp"></portlet:param>

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

    This is much the same form, though there are more fields now. Using some 
    AlloyUI tags, the form is linked to your `Entry` entity. The two hidden 
    fields contain the new `entryId` and the `guestbookId` for the guestbook the 
    new entry belongs to. The submit button is an `ActionURL` that executes the 
    `addEntry` method in the controller (your portlet class). 

Congratulations! You've now successfully replaced your prototype back-end with a
real, database-driven back-end. Next, you'll do a quick review and deploy your
application. 
