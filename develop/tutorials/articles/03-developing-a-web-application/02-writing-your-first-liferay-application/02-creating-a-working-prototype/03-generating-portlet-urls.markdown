# Generating Portlet URLs 

Since portlets are designed to be placed on pages by end users, and end users
can place multiple portlets on a page, and you as the developer have no idea
what other portlets will share a page with yours, guess what? You can't define
URLs for various functions in your application like you may be used to. Why? 

Say you've written a Calendar application that a user puts on the same
page as a Blog application. The Calendar allows the user to add and delete
events. The Blog allows the user to add and delete blog entries. To implement
the functionality for deleting, both application developers elected to append
the *del* parameter to the URL, and give it a primary key value so the
application can go look up the calendar event or the blog entry and delete it.

Since both applications read this parameter, when a user uses the delete
function in either one of them, both applications attempt to delete either a
calendar event or a blog entry that matches the given ID. In other words, the
applications are clashing because they use the same URL parameter.
System-generated URLs prevent this from happening. Creating a system-generated
URL is easy:

1. Add these tags below the `<%@ include file="/init.jsp" %>` line but above
   the `<aui:button-row>` tag:

        <portlet:renderURL var="addEntryURL">
            <portlet:param name="mvcPath" value="/edit_entry.jsp"></portlet:param>
        </portlet:renderURL>

2. Add this attribute to the `<aui:button>` tag after `value="Add Entry"`:

        onClick="<%= addEntryURL.toString() %>"

    Your `view.jsp` page should now look like this:

        <%@ include file="/init.jsp" %>

        <portlet:renderURL var="addEntryURL">
            <portlet:param name="mvcPath" value="/edit_entry.jsp"></portlet:param>
        </portlet:renderURL>

        <aui:button-row>
            <aui:button onClick="<%= addEntryURL.toString() %>" value="Add Entry"></aui:button>
        </aui:button-row>

The `<portlet:renderURL>` tag's `var` attribute creates a variable to hold the
system-generated URL. The generated URL is stored in the `addEntryURL` variable.
The `<portlet:param>` tag defines a URL parameter that's appended to the
generated URL. In this example, a URL parameter named `mvcPath` with a value of
`/edit_entry` is appended to the URL.

Notice that your `GuestbookMVCPortlet` class extends Liferay's `MVCPortlet`
class. In a Liferay MVC portlet, the `mvcPath` URL parameter indicates a page
within your portlet application. To navigate to another page in your portlet
application, use a portal URL with the parameter `mvcPath` to link to the
specific page.

In the example above, you created a `renderURL` that points to your
application's `edit_entry.jsp` page, which you haven't yet created. Note that
using an AlloyUI button to follow the generated URL is not required. You could
have used any HTML construct that contains a link. Users can click on your
button to access your application's `edit_entry.jsp` page. This currently
produces an error since no `edit_entry.jsp` exists yet. Creating the
`edit_entry.jsp` page is your next step.
