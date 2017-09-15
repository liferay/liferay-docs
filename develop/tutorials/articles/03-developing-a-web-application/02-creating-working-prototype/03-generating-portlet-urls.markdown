# Generating Portlet URLs [](id=generating-portlet-urls)

<div class="learn-path-step">
    <p>Developing Your First Portlet<br>Step 3 of 8</p>
</div>

Recall that users can place multiple portlets on a single page. As a developer, 
you have no idea what other portlets will share a page with yours. This means 
that you can't define URLs for various functions in your application like you 
may be used to.

For example, consider a Calendar application that a user puts on the same page 
as a Blog application. To implement the functionality for deleting calendar 
events and blog entries in the respective application, both application 
developers append the `del` parameter to the URL, and give it a primary key 
value so the application can look up and delete the calendar event or blog 
entry. Since both applications read this parameter, their delete functionality 
clashes. 

System-generated URLs are @product@'s solution to this. By generating a unique 
URL parameter for each piece of functionality, @product@ lets multiple 
applications with the same or similar functionality coexist in perfect harmony. 
Unfortunately, for this to work in your portlet you must manually add support 
for it. Fortunately, doing so is very straightforward. 

In `view.jsp`, follow these steps to enable system-generated URLs in your 
portlet: 

1.  Add these tags below `<%@ include file="/init.jsp" %>`, but above the 
    `<aui:button-row>` tag: 

        <portlet:renderURL var="addEntryURL">
            <portlet:param name="mvcPath" value="/edit_entry.jsp"></portlet:param>
        </portlet:renderURL>

2.  Add this attribute to the `<aui:button>` tag, before `value="Add Entry"`:

        onClick="<%= addEntryURL.toString() %>"

    Your `view.jsp` page should now look like this: 

        <%@ include file="/init.jsp" %>

        <portlet:renderURL var="addEntryURL">
            <portlet:param name="mvcPath" value="/edit_entry.jsp"></portlet:param>
        </portlet:renderURL>

        <aui:button-row>
            <aui:button onClick="<%= addEntryURL.toString() %>" value="Add Entry"></aui:button>
        </aui:button-row>

The `<portlet:renderURL>` tag's `var` attribute creates the `addEntryURL` 
variable to hold the system-generated URL. The `<portlet:param>` tag defines a 
URL parameter to append to the URL. In this example, a URL parameter named 
`mvcPath` with a value of `/edit_entry` is appended to the URL. 

Note that your `GuestbookPortlet` class (located in your `guestbook-web` 
module's `com.liferay.docs.guestbook.portlet` package) extends Liferay's 
`MVCPortlet` class. In a 
[Liferay MVC portlet](/develop/tutorials/-/knowledge_base/7-0/liferay-mvc-portlet), 
the `mvcPath` URL parameter indicates a page within your portlet. To navigate to 
another page in your portlet, use a portal URL with the parameter `mvcPath` to 
link to the specific page. 

In the example above, you created a `renderURL` that points to your 
application's `edit_entry.jsp` page, which you haven't yet created. Note that
using an AlloyUI button to follow the generated URL isn't required. You can use 
any HTML construct that contains a link. Users can click your button to access 
your application's `edit_entry.jsp` page. This currently produces an error since 
no `edit_entry.jsp` exists yet. Creating `edit_entry.jsp` is your next step. 
