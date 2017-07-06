# Creating an Add Guestbook Button [](id=creating-an-add-guestbook-button)

A guestbook application is pretty simple, right? People come to your site,
enter their names and brief messages, and then post them. Users can read the
entries that others posted, and they can post entries themselves.

When you created your project, it generated a file named `view.jsp` in your
project's `src/main/resources/META-INF/resources`. This file creates the 
default view for users when the portlet is added to the page. Right now it
only contains some sample content:

    <%@ include file="/init.jsp" %>

    <p>
        <b><liferay-ui:message key="com_liferay_docs_guestbook_GuestbookMVCPortlet.caption"/></b>
    </p>

First of all, `view.jsp` imports `init.jsp`. By convention, imports and 
tag library declarations in Liferay portlet applications are made in an 
`init.jsp` file. Every other JSP file in the application imports `init.jsp`. 
This convention ensures that JSP dependency management can always be handled 
from a single fie.

Besides importing `init.jsp`, `view.jsp` displays a message defined by a
language key. This key and its value are declared in your project's
`src/main/resources/content/Language.properties` file. 

It's time to start turning this into the Guestbook application. Add the first
button, labeled *Add Entry*.

1.  First, remove everything under the include for `init.jsp`. 

2.  Next, add the following AlloyUI tags to display an Add Entry button inside
    of a button row:

        <aui:button-row>
            <aui:button value="Add Entry"></aui:button>
        </aui:button-row>

Notice that you can use `aui` tags in `view.jsp` since `init.jsp` declares the
AlloyUI tag library by default (as well as other important imports and two
useful tags):

        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

        <%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
        <%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
        <%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
        <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

        <portlet:defineObjects />

        <liferay-theme:defineObjects />

Your application now displays a button instead of a message but the button
currently doesn't do anything. Next, you'll create a URL for your button.
