---
header-id: implementing-portlet-actions
---

# Implementing Portlet Actions

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Building the Web Front-End</p><p>Step 9 of 11</p>
</div>

When users submit the form, your application stores the form data for display in 
the guestbook. This is where you call the back-end you generated to store the
data for later retrieval in the database. 

To make your portlet do anything other than re-render itself, you must implement 
portlet actions. An action defines some processing, usually based on user input, 
that the portlet must perform before it renders itself. In the case of the 
guestbook portlet, the action you'll implement next saves a guestbook entry that 
a user typed into the form. Saved guestbook entries can be retrieved and 
displayed later. 

Since you're using Liferay's MVC Portlet framework, you have an easy way to 
implement actions. Portlet actions are implemented in the portlet class, which 
is the controller. In the form you just created, you made an action URL, and you
called it `addEntry`. To create a portlet action, you create a method in the
portlet class with the same name. `MVCPortlet` calls that method when a user
triggers its matching URL. 

## Creating an Add Entry Action

1.  Open `GuestbookPortlet`. 

2.  Create a method with the following signature:

    ```java
    public void addEntry(ActionRequest request, ActionResponse response) {

    }
    ```

3.  Press [CTRL]+[SHIFT]+O to organize imports and import the required
    `javax.portlet.ActionRequest` and `javax.portlet.ActionResponse` classes.

You've now created a portlet action. It doesn't do anything, but at least you
won't get an error now if you submit your form. Next, you should make the
action save the form data. 

The following method implements adding a guestbook entry using the back-end you
generated with Service Builder: 

```java
public void addEntry(ActionRequest request, ActionResponse response)
            throws PortalException {

        ServiceContext serviceContext = ServiceContextFactory.getInstance(
            GuestbookEntry.class.getName(), request);

        String userName = ParamUtil.getString(request, "name");
        String email = ParamUtil.getString(request, "email");
        String message = ParamUtil.getString(request, "message");
        long guestbookId = ParamUtil.getLong(request, "guestbookId");
        long entryId = ParamUtil.getLong(request, "entryId");

    if (entryId > 0) {

        try {

            _guestbookEntryLocalService.updateGuestbookEntry(
                serviceContext.getUserId(), guestbookId, entryId, userName,
                email, message, serviceContext);

            response.setRenderParameter(
                "guestbookId", Long.toString(guestbookId));

        }
        catch (Exception e) {
            System.out.println(e);

            PortalUtil.copyRequestParameters(request, response);

            response.setRenderParameter(
                "mvcPath", "/guestbook/edit_entry.jsp");
        }

    }
    else {

        try {
            _guestbookEntryLocalService.addGuestbookEntry(
                serviceContext.getUserId(), guestbookId, userName, email,
                message, serviceContext);

            response.setRenderParameter(
                "guestbookId", Long.toString(guestbookId));

        }
        catch (Exception e) {
            System.out.println(e);

            PortalUtil.copyRequestParameters(request, response);

            response.setRenderParameter(
                "mvcPath", "/guestbook/edit_entry.jsp");
        }
    }
}
```

1.  Replace your existing `addEntry` method with the above method.

2.  Press [CTRL]+[SHIFT]+O to organize imports.

This `addEntry` method gets the name, message, and email fields that the
user submits in the JSP and passes them to the service to be stored as entry
data. It also gets a 
[`ServiceContext`](/docs/7-2/frameworks/-/knowledge_base/f/understanding-servicecontext) 
so information about the request's current context can be retrieved, such as the
ID of the current user. The `if-else` logic checks whether there's an existing
`entryId`. If there is, the `update` service method is called, and if not, the
`add` service method is called. In both cases, it sets a render parameter with
the Guestbook ID so the application can display the guestbook's entries after
this one has been added. This is all done in `try...catch` statements. Note the
setting of the `mvcPath` render parameter to direct processing to the proper JSP
based on what happens. 

## Creating a Delete Entry Action

Next, create an action that deletes an entry: 

```java
public void deleteEntry(ActionRequest request, ActionResponse response) throws PortalException {
        long entryId = ParamUtil.getLong(request, "entryId");
        long guestbookId = ParamUtil.getLong(request, "guestbookId");

        ServiceContext serviceContext = ServiceContextFactory.getInstance(
            GuestbookEntry.class.getName(), request);

        try {

            response.setRenderParameter(
                "guestbookId", Long.toString(guestbookId));

            _guestbookEntryLocalService.deleteGuestbookEntry(entryId);
        }

        catch (Exception e) {
            Logger.getLogger(GuestbookPortlet.class.getName()).log(
                Level.SEVERE, null, e);
        }
}
```

This action accepts an `entryId` from the request and calls the service to
delete it. 

But there's something missing, isn't there? These methods expect a `guestbookId`
to be in the request, so the `GuestbookEntry` can be connected to its
`Guestbook`. You'll create that next, as well as a mechanism for viewing
guestbook entries. 
