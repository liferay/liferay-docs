---
header-id: adding-tabs-to-the-guestbook-portlet
---

# Adding Tabs to the Guestbook Portlet

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Writing the Guestbook Admin App</p><p>Step 5 of 6</p>
</div>

Before you finish the Guestbook Admin portlet, you must prepare the Guestbook
portlet's UI to display multiple Guestbooks. As administrators add Guestbooks
using the Guestbook Admin portlet, users must be able to choose which Guestbook
they want to sign. They'll do this using a series of tabs across the top: 

![Figure 1: Users can click a tab to choose which Guestbook to sign.](../../../images/guestbook-tabs.png)

This is surprisingly easy to do using Liferay's Alloy UI tag library: 

1.  Open `view.jsp` from the `src/main/resources/META-INF/resources/guestbook`
    folder. 

2.  After the first Java snippet (the one that gets the `guestbookId` out of the
    request), add this code: 

    ```markup

    <aui:nav cssClass="nav-tabs">

        <%
            List<Guestbook> guestbooks = GuestbookLocalServiceUtil.getGuestbooks(scopeGroupId);

                for (int i = 0; i < guestbooks.size(); i++) {

                    Guestbook curGuestbook = (Guestbook) guestbooks.get(i);
                    String cssClass = StringPool.BLANK;

                    if (curGuestbook.getGuestbookId() == guestbookId) {
                        cssClass = "active";
                    }

        %>

        <portlet:renderURL var="viewPageURL">
            <portlet:param name="mvcPath" value="/guestbook/view.jsp" />
            <portlet:param name="guestbookId"
                value="<%=String.valueOf(curGuestbook.getGuestbookId())%>" />
        </portlet:renderURL>

            
        <aui:nav-item cssClass="<%=cssClass%>" href="<%=viewPageURL%>"
            label="<%=HtmlUtil.escape(curGuestbook.getName())%>" />

        <%  
                    }
                
        %>

    </aui:nav>
    ```

3.  Save the file. 

This code declares the AUI navigation tabs. Then a code scriptlet gets all the
Guestbooks in this scope and loops through each one. As it examines them, it
checks to see if the one it's examining is the current Guestbook. If so, a CSS
style called `active` is applied. 

After this, a new URL called `viewPageURL` is created that points to `view.jsp`
with a `guestbookId` parameter containing the current Guestbook in the loop.
Finally, an `<aui:nav-item>` tag declares the markup for the tab, using the CSS
class, the URL containing the parameters to navigate to the new Guestbook, and
the name to label it. 

The loop continues until all the retrieved Guestbooks have tabs. 

Awesome! You've updated the Guestbook portlet so it can display all the
Guestbooks administrators add. Now it's time to provide a UI for your Guestbook
Admin portlet so they can do just that. 

