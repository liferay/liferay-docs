# Integrating the New Back-end [](id=integrating-the-new-back-end)

<div class="learn-path-step">
    <p>Refactoring the Prototype<br>Step 4 of 6</p>
</div>

It's a good practice to start with a working prototype as a proof of concept, 
but eventually that prototype must transform into a real application. Up to this
point, you've made all the preparations to do that, and now it's time to replace
the prototype back-end with the real, database-driven back-end you created with
Service Builder. 

For the prototype, you manually created the application's model. The first thing
you want to do is remove it, because Service Builder generated a new one:

1.  Find the `com.liferay.docs.guestbook.model` package in the `guestbook-web` 
    module.

2.  Delete it. You'll see errors in your project, but that's because you haven't
    replaced the model yet. 

Now you get to do some dependency management. For the web module to access the 
generated services, you must make it aware of the API and service modules. Then 
you can update the `addEntry` method in `GuestbookPortlet` to use the new 
services: 

1.  First, open `guestbook-web`'s `build.gradle` file and add these 
    dependencies:

        compileOnly project(":modules:guestbook:guestbook-api")
        compileOnly project(":modules:guestbook:guestbook-service")

2.  Right-click on the `guestbook-web` project and select *Gradle* &rarr;
    *Refresh Gradle Project*. 

3.  Now you must add *references* to the Service Builder services you need. To 
    do this, add them as class variables with `@Reference` annotations on their 
    setter methods. Open `GuestbookPortlet` and add these references to the 
    bottom of the file: 

            @Reference(unbind = "-")
            protected void setEntryService(EntryLocalService entryLocalService) {
                _entryLocalService = entryLocalService;
            }

            @Reference(unbind = "-")
            protected void setGuestbookService(GuestbookLocalService guestbookLocalService) {
                _guestbookLocalService = guestbookLocalService;
            }

            private EntryLocalService _entryLocalService;
            private GuestbookLocalService _guestbookLocalService;

    Note that it's Liferay's code style to add class variables this way. The
    `@Reference` annotation on the setters allows Liferay's OSGi container to
    inject references to your generated services so you can use them. The
    `unbind` parameter tells the container there's no method for unbinding these
    services: the references can die with the class during garbage collection
    when they're no longer needed. 

4.  Now you can modify the `addEntry` method to use these service references: 

        public void addEntry(ActionRequest request, ActionResponse response)
                    throws PortalException {

                ServiceContext serviceContext = ServiceContextFactory.getInstance(
                    Entry.class.getName(), request);

                String userName = ParamUtil.getString(request, "name");
                String email = ParamUtil.getString(request, "email");
                String message = ParamUtil.getString(request, "message");
                long guestbookId = ParamUtil.getLong(request, "guestbookId");
                long entryId = ParamUtil.getLong(request, "entryId");

            if (entryId > 0) {

                try {

                    _entryLocalService.updateEntry(
                        serviceContext.getUserId(), guestbookId, entryId, userName,
                        email, message, serviceContext);

                    response.setRenderParameter(
                        "guestbookId", Long.toString(guestbookId));

                }
                catch (Exception e) {
                    System.out.println(e);

                    PortalUtil.copyRequestParameters(request, response);

                    response.setRenderParameter(
                        "mvcPath", "/guestbookwebportlet/edit_entry.jsp");
                }

            }
            else {

                try {
                    _entryLocalService.addEntry(
                        serviceContext.getUserId(), guestbookId, userName, email,
                        message, serviceContext);

                    SessionMessages.add(request, "entryAdded");

                    response.setRenderParameter(
                        "guestbookId", Long.toString(guestbookId));

                }
                catch (Exception e) {
                    SessionErrors.add(request, e.getClass().getName());

                    PortalUtil.copyRequestParameters(request, response);

                    response.setRenderParameter(
                        "mvcPath", "/guestbookwebportlet/edit_entry.jsp");
                }
            }
        }

    This `addEntry` method gets the name, message, and email fields that the
    user submits in the JSP and passes them to the service to be stored as entry
    data. The `if-else` logic checks whether there's an existing `entryId`. If
    there is, the `update` service method is called, and if not, the `add` service
    method is called. In both cases, it sets a render parameter with the
    Guestbook ID so the application can display the guestbook's entries after
    this one has been added. This is all done in `try...catch` statements.
 
5.  Now add `deleteEntry`, which you didn't have before: 

        public void deleteEntry(ActionRequest request, ActionResponse response) throws PortalException {
                long entryId = ParamUtil.getLong(request, "entryId");
                long guestbookId = ParamUtil.getLong(request, "guestbookId");

                ServiceContext serviceContext = ServiceContextFactory.getInstance(
                    Entry.class.getName(), request);

                try {

                    response.setRenderParameter(
                        "guestbookId", Long.toString(guestbookId));

                    _entryLocalService.deleteEntry(entryId, serviceContext);
                }

                catch (Exception e) {
                    Logger.getLogger(GuestbookPortlet.class.getName()).log(
                        Level.SEVERE, null, e);
                }
        }

    This method retrieves the entry object (using its ID from the request) and
    calls the service to delete it.

6.  Next you must replace the `render` method: 

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

    This new `render` method checks for any guestbooks in the current Site. If 
    there aren't any, it creates one. Either way, it grabs the first guestbook 
    so its entries can be displayed by your view layer. 

7.  Remove the `parseEntries` method. It's a remnant of the prototype 
    application. 

8.  Hit Ctrl-Shift-O to organize your imports. 

Awesome! You've updated your controller to use services. Next, you'll tackle 
the view. 
