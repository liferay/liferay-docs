# Integrating the New Back-end 

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

Now you get to do some dependency management. You must make the web module aware
of the service modules so the web can access their services. Then you can update
the `addEntry` method to use the new services.

1.  First, open `guestbook-web`'s `build.gradle` file and add these dependencies:

        compileOnly project(":modules:guestbook:guestbook-api")
        compileOnly project(":modules:guestbook:guestbook-service")

2.  Right-click on the `guestbook-web` project and select *Gradle* &rarr;
    *Refresh Gradle Project*. 

3.  The first thing you'll do is add a *reference* to the Service Builder
    services you need. To do this, you need only add them as class variables
    with an `@Reference` annotation. Open `GuestbookPortlet.java` and add these
    references to the bottom of the file: 

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

4.  Now you can modify the `addEntry` method: 

        public void addEntry(ActionRequest request, ActionResponse response)
                    throws PortalException {

                ServiceContext serviceContext = ServiceContextFactory.getInstance(
                    Entry.class.getName(), request);

                String userName = ParamUtil.getString(request, "name");
                String email = ParamUtil.getString(request, "email");
                String message = ParamUtil.getString(request, "message");
                long guestbookId = ParamUtil.getLong(request, "guestbookId");
                long entryId = ParamUtil.getLong(request, "entryId");

                    try {
                        _entryLocalService.addEntry(
                            serviceContext.getUserId(), guestbookId, userName, email,
                            message, serviceContext);

                        response.setRenderParameter(
                            "guestbookId", Long.toString(guestbookId));
                    }
                    catch (Exception e) {
                        Logger.getLogger(GuestbookPortlet.class.getName()).log(
                        Level.SEVERE, null, e);

                        PortalUtil.copyRequestParameters(request, response);
                        response.setRenderParameter("mvcPath", "/guestbookwebportlet/edit_entry.jsp");
                        }
            }

    The `addEntry` method gets the name, message, and email fields that the 
    user submits through the JSP and passes them on to the service to be stored 
    as entry data. It also sets a render parameter with the Guestbook ID so the
    application can display the guestbook's entries after this one has been
    added. This is all done in a `try...catch` statement.
 
5. Now add `deleteEntry`, which you didn't have before: 

            public void deleteEntry(ActionRequest request, ActionResponse response) {
                long entryId = ParamUtil.getLong(request, "entryId");
                long guestbookId = ParamUtil.getLong(request, "guestbookId");

                try {

                    response.setRenderParameter(
                        "guestbookId", Long.toString(guestbookId));

                    _entryLocalService.deleteEntry(entryId);
                }

                catch (Exception e) {
                    Logger.getLogger(GuestbookPortlet.class.getName()).log(
                        Level.SEVERE, null, e);
   
                }
            }

    This method retrieves the entry object (using its ID from the request) and
    calls the service to delete it.

6. Next you must replace `render`.

            @Override
            public void render(
                    RenderRequest renderRequest, RenderResponse renderResponse)
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

    The new `render` method checks if any guestbooks currently exist for the 
    current site. If there aren't any, it creates one. Either way, it grabs the
    first one so its entries can be displayed by your view layer. 

7.  Remove the `parseEntries` method. 

8.  Hit Ctrl-Shift-O to fix your imports. 

Awesome! You've updated your controller. Next, you'll tackle the view. 

