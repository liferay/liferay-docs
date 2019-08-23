---
header-id: viewing-guestbook-entries
---

# Displaying Guestbook Entries

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Developing Your First Portlet</p><p>Step 8 of 8</p>
</div>

To display guestbook entries, you must do the reverse of what you did to store
them: retrieve them the database, loop through them, and present them on the
page. 

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

3. Press [CTRL]+[SHIFT]+O to organize imports. 

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

--------Start Here-------------

## Displaying Guestbook Entries

Liferay's development framework makes it easy to loop through data and display 
it nicely to the end user. You'll use a Liferay UI construct called *Search 
Container* to make this happen. 

1.  Add these tags to your `view.jsp` in between the `</portlet:renderURL>` and 
    `<aui:button-row>` tags: 

        <jsp:useBean id="entries" class="java.util.ArrayList" scope="request"/>

        <liferay-ui:search-container>
            <liferay-ui:search-container-results results="<%= entries %>" />

            <liferay-ui:search-container-row
                className="com.liferay.docs.guestbook.model.Entry"
                modelVar="entry"
            >
                <liferay-ui:search-container-column-text property="message" />

                <liferay-ui:search-container-column-text property="name" />
            </liferay-ui:search-container-row>

            <liferay-ui:search-iterator />
        </liferay-ui:search-container>

Save your work, deploy your application, and try adding some guestbook entries. 

![Figure 1: You have a form to enter information.](../../../images/guestbook-prototype-form.png)

![Figure 2: Submitted entries are displayed here..](../../../images/guestbook-prototype-container.png)

Awesome! You've finished your prototype! You have a working application that
adds and saves guestbook entries. 

The way you're saving the entries isn't the best way to persist data in your 
application. Next, you'll use Service Builder to generate your persistence 
classes and the methods you need to store your application data in the database. 
