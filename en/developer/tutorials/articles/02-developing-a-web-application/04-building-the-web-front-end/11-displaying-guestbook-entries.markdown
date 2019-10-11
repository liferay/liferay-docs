---
header-id: viewing-guestbook-entries
---

# Displaying Guestbook Entries

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Building the Web Front-End</p><p>Step 10 of 11</p>
</div>

To display guestbook entries, you must do the reverse of what you did to store
them: retrieve them the database, loop through them, and present them on the
page. To do this, you must override the default MVC Portlet `render` method so
you can tell your portlet how to render itself. 

## Rendering the Portlet

1.  Add the following `render` method to `GuestbookPortlet`: 

    ```java
    @Override
    public void render(RenderRequest renderRequest, RenderResponse renderResponse)
        throws IOException, PortletException {

        try {
            ServiceContext serviceContext = ServiceContextFactory.getInstance(
                Guestbook.class.getName(), renderRequest);

            long groupId = serviceContext.getScopeGroupId();

            long guestbookId = ParamUtil.getLong(renderRequest, "guestbookId");

            List<Guestbook> guestbooks = _guestbookLocalService.getGuestbooks(
                groupId);

            if (guestbooks.isEmpty()) {
                Guestbook guestbook = _guestbookLocalService.addGuestbook(
                    serviceContext.getUserId(), "Main", serviceContext);

                guestbookId = guestbook.getGuestbookId();
            }

            if (guestbookId == 0) {
                guestbookId = guestbooks.get(0).getGuestbookId();
            }

            renderRequest.setAttribute("guestbookId", guestbookId);
        }
        catch (Exception e) {
            throw new PortletException(e);
        }

        super.render(renderRequest, renderResponse);
    }
    ```

    This `render` method checks for guestbooks in the current Site. If there aren't
    any, it creates one. The `guestbookId` that it has (either the first one or one
    that has been selected in functionality you haven't written yet) is set in the
    request object so that it becomes the current guestbook. 

2.  Press [CTRL]+[SHIFT]+O to organize imports and then save the file. 

| Note: When you are prompted to choose imports, here are some guidelines:
| 
| * Always use `org.osgi...` packages instead of `aQute.bnd...`
| 
| * Generally use `java.util...` or `javax.portlet...` packages.
| 
| * You never use `java.awt...` in this project.
| 
| * Only use `com.liferay...` when it is for a Liferay specific implementation or
|   your custom implementation of a concept.
| 
| For example:
| 
| * If you are given the choice between `javax.portlet.Portlet` and
|     `com.liferay.portlet.Portlet` choose `javax.portlet.Portlet`.
| 
| * If you are given the choice between `org.osgi.component` and
|   `aQute.bnd.annotation.component` choose `org.osgi.component`
| 
| If at some point you think you chose an incorrect import, but you're not sure
| what it might be, you can erase all of the imports from the file and press
| [CTRL]+[SHIFT]+O again and see if you can identify where you went wrong.

Now that you have your controller preparing your data for display, your next
step is to implement the view so users can see guestbook entries. 

## Displaying Guestbook Entries

Liferay's development framework makes it easy to loop through data and display 
it nicely to the end user. You'll use a Liferay UI construct called *Search 
Container* to make this happen. 

1.  Replace the contents of `view.jsp` with this code: 

    ```markup
    <%@include file="../init.jsp"%>

    <%
    long guestbookId = Long.valueOf((Long) renderRequest
            .getAttribute("guestbookId"));
    %>

    <aui:button-row cssClass="guestbook-buttons">

        <portlet:renderURL var="addEntryURL">
            <portlet:param name="mvcPath" value="/guestbook/edit_entry.jsp" />
            <portlet:param name="guestbookId"
                value="<%=String.valueOf(guestbookId)%>" />
        </portlet:renderURL>

        <aui:button onClick="<%=addEntryURL.toString()%>" value="Add Entry"></aui:button>

    </aui:button-row>

    <liferay-ui:search-container total="<%=GuestbookEntryLocalServiceUtil.getGuestbookEntriesCount()%>">
    <liferay-ui:search-container-results
        results="<%=GuestbookEntryLocalServiceUtil.getGuestbookEntries(scopeGroupId.longValue(),
                        guestbookId, searchContainer.getStart(),
                        searchContainer.getEnd())%>" />

    <liferay-ui:search-container-row
        className="com.liferay.docs.guestbook.model.GuestbookEntry" modelVar="entry">

        <liferay-ui:search-container-column-text property="message" />

        <liferay-ui:search-container-column-text property="name" />

        <liferay-ui:search-container-column-jsp
            align="right" 
            path="/guestbook/entry_actions.jsp" />

    </liferay-ui:search-container-row>

    <liferay-ui:search-iterator />

    </liferay-ui:search-container>
    ```

2.  You've used a lot of new objects in this JSP, so you must declare them in
    `init.jsp`. Replace the contents of `init.jsp` with this: 

    ```markup
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
    <%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%>
    <%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet"%>
    <%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme"%>
    <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
    <%@ taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %>
    <%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %>

    <%@ page import="java.util.List" %>
    <%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
    <%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %>
    <%@ page import="com.liferay.portal.kernel.util.WebKeys" %>
    <%@ page import="com.liferay.petra.string.StringPool" %>
    <%@ page import="com.liferay.portal.kernel.model.PersistedModel" %>
    <%@ page import="com.liferay.portal.kernel.dao.search.SearchEntry" %>
    <%@ page import="com.liferay.portal.kernel.dao.search.ResultRow" %>
    <%@ page import="com.liferay.docs.guestbook.model.Guestbook" %>
    <%@ page import="com.liferay.docs.guestbook.service.GuestbookEntryLocalServiceUtil" %>
    <%@ page import="com.liferay.docs.guestbook.service.GuestbookLocalServiceUtil" %>
    <%@ page import="com.liferay.docs.guestbook.model.GuestbookEntry" %> 

    <liferay-theme:defineObjects />

    <portlet:defineObjects />
    ```

Many of these objects, such as `HtmlUtil`, `ParamUtil`, and `StringPool`, are
Liferay helper utilities that enable you with a single line of code do things
like extract parameters, escape data, or provide `String`s that otherwise have
to be escaped. 

Save your work. 

## Creating an Actions JSP

Actions can be performed on your entities once they're stored. Users who enter
Guestbook entries may wish to edit them or delete them. Now you'll provide that
functionality. 

1.  Right-click on the `src/main/resources/META-INF/resources/guestbook` folder
    and select *New* &rarr; *File*. 

2.  Name the file `entry_actions.jsp`. 

3.  Paste the following code into the file: 

    ```markup
<%@include file="../init.jsp"%>

    <%
    String mvcPath = ParamUtil.getString(request, "mvcPath");

    ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

    GuestbookEntry entry = (GuestbookEntry)row.getObject(); 
    %>

    <liferay-ui:icon-menu>

            <portlet:renderURL var="editURL">
                <portlet:param name="entryId"
                    value="<%= String.valueOf(entry.getEntryId()) %>" />
                <portlet:param name="mvcPath" value="/guestbook/edit_entry.jsp" />
            </portlet:renderURL>

            <liferay-ui:icon image="edit" message="Edit"
                url="<%=editURL.toString() %>" />

            <portlet:actionURL name="deleteEntry" var="deleteURL">
                <portlet:param name="entryId"
                    value="<%= String.valueOf(entry.getEntryId()) %>" />
                <portlet:param name="guestbookId"
                    value="<%= String.valueOf(entry.getGuestbookId()) %>" />
            </portlet:actionURL>

            <liferay-ui:icon-delete url="<%=deleteURL.toString() %>" />

    </liferay-ui:icon-menu>
    ```

You may have noticed this JSP was included in the Search Container rows in your
`view.jsp`. As the Search Container loops through Guestbook entries, this JSP
generates an Actions button for each of them containing two functions: a call to
your `addEntry` method (which both adds and edits) and a call to your
`deleteEntry` method. Both calls supply the current `guestbookId` and `entryId`
parameters so the Action method has everything it needs to call the service
method that does the work. 

Awesome! You've now completed the first iteration of your Guestbook application. 

![Figure 1: You have a form to enter information.](../../../images/guestbook-prototype-form.png)

![Figure 2: Submitted entries are displayed here.](../../../images/guestbook-prototype-container.png)


Next you'll review what's been done so far, and you'll deploy and test your
application. 

