---
header-id: creating-an-add-entry-button
---

# Creating an Add Entry Button

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Building the Web Front-End</p><p>Step 5 of 11</p>
</div>

A guestbook application is pretty simple, right? People come to your site and 
post their names and brief messages. Others can read these entries and post
their own. 

When you created your project, it generated a file named `view.jsp`, which
you've already moved to `src/main/resources/META-INF/resources/guestbook`
folder. This file contains the default view for users when the portlet is added
to the page. Right now it contains sample content: 

```markup
<%@ include file="../init.jsp" %>

<p>
    <b><liferay-ui:message key="guestbook-web.caption"/></b>
</p>
```

First, `view.jsp` imports `init.jsp`. By Liferay convention, we declare imports
and tag library declarations in an `init.jsp` file. The other JSP files in
the application import `init.jsp`. This reserves JSP dependency management to
a single file. 

Besides importing `init.jsp`, `view.jsp` displays a message defined by a
language key. This key and its value are declared in your project's
`src/main/resources/content/Language.properties` file. 

It's time to start developing the Guestbook application. First, users need a way 
to add a Guestbook entry. In `view.jsp`, follow these steps to add this button: 

1.  Remove everything under the include for `init.jsp`. 

2.  Below the include, add the following 
    [AlloyUI](http://alloyui.com/) tags to display an Add Entry button inside of 
    a button row: 

    ```markup
    <aui:button-row>
        <aui:button value="Add Entry"></aui:button>
    </aui:button-row>
    ```

You can use `aui` tags in `view.jsp` since `init.jsp` declares the AlloyUI tag 
library by default (as well as other important imports and tags): 

```markup
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<portlet:defineObjects />

<liferay-theme:defineObjects />
```

Your application now displays a button instead of a message, but the button 
doesn't do anything. Next, you'll create a URL for your button. 

![Figure 1: Your new button is awesome, but it doesn't work yet.](../../../images/guestbook-new-button.png)
