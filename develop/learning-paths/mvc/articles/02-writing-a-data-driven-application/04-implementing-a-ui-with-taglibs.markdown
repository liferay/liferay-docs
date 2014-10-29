# Implementing a UI with Liferay Taglibs [](id=implementing-a-ui-with-liferay-taglibs)

Clearly, now that you've implemented all the other layers of your application,
you need a user interface that's a bit more organized and extensible than the
one you created for the preferences-based version of this application. Because
of this, you'll implement a UI pattern that Liferay uses for its own
applications. You'll find that this makes it easy for you to organize your code
and share repeated code elements in one place. 

## Using init.jsp For All Declarations [](id=using-init-jsp-for-all-declarations)

So far, you've had to repeat certain elements in all your JSP files. These
include declarations of any tag libraries you plan to use in your JSP, any
import statements you'll use in JSP scriptlets, and any initialization tags,
such as `<portlet:defineObjects />`. Since it is highly likely you'll want to do
this on all JSPs that you create, why not put all this code in one file and then
include that file in every JSP? Then you don't have to waste time pasting in the
same declarations on every page. 

This is a pattern you'll see in all of Liferay's portlets. The idea is to create
a JSP called `init.jsp` that contains all these declarations, and then include
it in the rest of the application's JSPs. That way, if you introduce a new tag
library or import, you add it once to `init.jsp` and the rest of your JSPs can
use it. Normally, you'd build up your `init.jsp` as you go, adding declarations
and imports as you build your application. Since this is a Liferay learning
path, obviously this application has already been written ahead of time so you
can use it to learn how to do it yourself. For this reason, we'll provide the
complete contents of `init.jsp` for you. 

1.  Create a file called `init.jsp` in the `docroot/html` folder of your
    application. This can be done by right-clicking on the folder, selecting *New*
    &rarr; *File*, supplying `init.jsp` as the name, and then clicking *OK*. 

2.  Paste the contents of the below snippet into this file: 

        <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
        <%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>
        <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
        <%@ taglib uri="http://liferay.com/tld/theme" prefix="theme" %>

        <%@ page import="java.util.List" %>
        <%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
        <%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %>
        <%@ page import="com.liferay.portal.kernel.util.StringPool" %>
        <%@ page import="com.liferay.docs.guestbook.model.Guestbook" %>
        <%@ page import="com.liferay.docs.guestbook.service.EntryLocalServiceUtil" %>
        <%@ page import="com.liferay.docs.guestbook.service.GuestbookLocalServiceUtil" %>

        <portlet:defineObjects />
        <theme:defineObjects />

As you can see, this file contains only what you'd need to use in the logic of
your other JSPs. Since there's not much interesting going on here, it's best to
move on to where things are really happening. 

## Viewing Guestbooks and Their Entries [](id=viewing-guestbooks-and-their-entries)

Since you already have logic in your controller class that creates a default
guestbook when the portlet is added to a page, you don't need to create the JSP
for adding `Guestbook` entities right away. This frees you to work on the
application's default view, which is implemented in `view.jsp`. Open that file
now. 

Since this is a longer file, instead of pasting in the whole thing, you'll work
on it step by step. 

1.  Clear out the contents of the file. You won't need anything that's in there. 

2.  Add the following first line of code: 

        <%@include file="/html/init.jsp"%>

    This includes the `init.jsp` file you just created. 

3.  Next, add the following scriptlet: 

        <%
            long guestbookId = Long.valueOf((Long) renderRequest
                    .getAttribute("guestbookId"));
        %>

    This retrieves the `guestbookId` that was added to the `RenderRequest` object
    in the `render()` method of the portlet class. 

4.  Now you'll use Liferay's AlloyUI tag libraries for something other than a
    form. You'll implement a tab bar across the top of your application that lets
    users select the guestbook to which they want to add and/or read entries. 

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
            %>

            <portlet:renderURL var="viewPageURL">
                <portlet:param name="mvcPath" value="/html/guestbook/view.jsp" />
                <portlet:param name="guestbookId"
                    value="<%=String.valueOf(curGuestbook.getGuestbookId())%>" />
            </portlet:renderURL>

            <aui:nav-item cssClass="<%=cssClass%>" href="<%=viewPageURL%>"
                label="<%=HtmlUtil.escape(curGuestbook.getName())%>" />

            <%
                }
            %>

        </aui:nav>

    The first thing this does is declare that you're using a navigation element,
    and that you want it to use tabs. These are implemented as a CSS class in
    Liferay's default theme. Next is a scriptlet that gets all the `Guestbook`
    entities out of the database and loops through them. During each loop, it checks
    to see if the current guestbook in the loop has the same ID as the one the user
    has selected. If it is, it sets a `String` variable for the CSS class to
    *active*. A render URL is then created for the guestbook, and then a navigation
    item is created that uses the CSS class as a parameter. In this way, the loop
    builds a list of navigation items on the page that are styled as tabs, and the
    currently selected tab gets an `active` class so it shows as selected. 

5.  Next, you'll add the button row, which is almost the same as the one you had
    in the previous version of this portlet: 

        <aui:button-row cssClass="guestbook-buttons">

            <portlet:renderURL var="addGuestbookURL">
                <portlet:param name="mvcPath"
                    value="/html/guestbook/edit_guestbook.jsp" />
            </portlet:renderURL>

            <portlet:renderURL var="addEntryURL">
                <portlet:param name="mvcPath" value="/html/guestbook/edit_entry.jsp" />
                <portlet:param name="guestbookId"
                    value="<%=String.valueOf(guestbookId)%>" />
            </portlet:renderURL>

            <aui:button onClick="<%=addGuestbookURL.toString()%>"
                value="Add Guestbook" />
            <aui:button onClick="<%=addEntryURL.toString()%>" value="Add Entry"></aui:button>

        </aui:button-row>

    This is the same as the previous version of the button row, except for the
    additional *Add Guestbook* button. This points to a new JSP which you'll create
    next. 

6.  The last thing to be added is the new Search Container that uses your service
    layer to retrieve guestbook entries: 

        <liferay-ui:search-container>
            <liferay-ui:search-container-results
                results="<%=EntryLocalServiceUtil.getEntries(scopeGroupId,
                                guestbookId, searchContainer.getStart(),
                                searchContainer.getEnd())%>"
                total="<%=EntryLocalServiceUtil.getEntriesCount(scopeGroupId,
                                guestbookId)%>" />

            <liferay-ui:search-container-row
                className="com.liferay.docs.guestbook.model.Entry" modelVar="entry">

                <liferay-ui:search-container-column-text property="message" />

                <liferay-ui:search-container-column-text property="name" />

            </liferay-ui:search-container-row>

            <liferay-ui:search-iterator />
        </liferay-ui:search-container>

    Again, this Search Container is almost the same as the previous version, except
    for the parameters in the `<liferay-ui:search-container-results />` tag. The
    `results` parameter now calls your service layer to retrieve guestbook entries
    and the total number of entries. The same rows are displayed even though your
    entity also collects users' email addresses, because obviously you don't want to
    publish email addresses on a web page: they'll get harvested by spammers. 

Excellent! You've now updated the default view of your application. Next, you'll
update the form for adding entries. 

## Adding Guestbook Entries [](id=adding-guestbook-entries)

Your `edit_entry.jsp` for services will be much the same as the one you already
wrote for the preference-based version of this portlet. For this reason, instead
of replacing its contents, just edit what you have; it'll be easier. 

1.  Remove the tag library declarations and the `<portlet:defineObjects />` tag
    from the top of the file. Replace it with the `init.jsp` include: 

        <%@include file="/html/init.jsp" />

2.  The only other things you need to add are the missing fields. Make your
    `<aui:fieldset>` look like this: 

        <aui:fieldset>

            <aui:input name="name" />
            <aui:input name="email" />
            <aui:input name="message" />
            <aui:input name='guestbookId' type='hidden' 
                value='<%= ParamUtil.getString(renderRequest, "guestbookId") %>'/>

        </aui:fieldset>

    The only thing of note here is the last field: remember that the action URL
    that gets the user here has a parameter that carries the `guestbookId`. This
    must be submitted along with the form, so that when you make the call to your
    service layer to add the entry, you know which `Guestbook` the entry should be
    added to. Since this is information the user doesn't need to see, you can add it
    to the form as a hidden field. 

Your guestbook entry form is now complete. All that's left is to create your
guestbook form. 

## Adding Guestbooks [](id=adding-guestbooks)

This JSP will look very much like the guestbook entry form, except since it
contains only one field, it's much simpler. For that reason, the entire contents
of the JSP are supplied here: 

    <%@include file = "/html/init.jsp" %>

    <portlet:renderURL var="viewURL">
        <portlet:param name="mvcPath" value="/html/guestbook/view.jsp"></portlet:param>
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

As you can see, there's no real difference between this JSP and the one for
adding entries, except of course that the URL names are different and the field
is different. Since each action URL maps to an action method in your portlet
class, when this form is submitted, the `addGuestbook()` method is called. 

![Figure 1: The completed guestbook application shows guestbooks in different tabs. ](../../images/data-driven-guestbook-2.png)

Congratulations! You've used Liferay's Service Builder persistence framework to
create a data-driven application! Of course, there are many more things you'll
want to add, some of which are listed below. 

## Next Steps [](id=next-steps)

[Setting Permissions](/develop/learning-paths/-/knowledge_base/6-2/setting-permissions)

