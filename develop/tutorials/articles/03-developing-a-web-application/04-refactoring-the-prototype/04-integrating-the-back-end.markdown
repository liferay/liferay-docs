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

3.  Open `GuestbookPortlet.java` and replace the `addEntry` method with the new version:

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

                        SessionMessages.add(request, "entryAdded");

                        response.setRenderParameter(
                            "guestbookId", Long.toString(guestbookId));
                    }
                    catch (Exception e) {
                        Class<?> clazz = e.getClass();

                        SessionErrors.add(request, clazz.getName());

                        PortalUtil.copyRequestParameters(request, response);

                        response.setRenderParameter(
                            "mvcPath", "edit_entry.jsp");
                    }
            
            }

    The `addEntry` method gets the name, message, and email fields that the 
    user submits through the JSP and passes them on to the service to be stored 
    as entry data. This is all done in a `try...catch` statement.
    
4. Next do `addGuestbook`:

            public void addGuestbook(ActionRequest request, ActionResponse response)
                throws PortalException {

                ServiceContext serviceContext = ServiceContextFactory.getInstance(
                    Guestbook.class.getName(), request);

                String name = ParamUtil.getString(request, "name");

                try {
                    _guestbookLocalService.addGuestbook(
                        serviceContext.getUserId(), name, serviceContext);

                    SessionMessages.add(request, "guestbookAdded");
                }
                catch (Exception e) {
                    Class<?> clazz = e.getClass();

                    SessionErrors.add(request, clazz.getName());

                    response.setRenderParameter(
                        "mvcPath", "edit_guestbook.jsp");
                }
            }

    Just like the `addEntry` method, you're retrieving user entered data--in this case the `name`--and adding it to the Guestbook via the service call.
        
5. Then add `deleteEntry`

            public void deleteEntry(ActionRequest request, ActionResponse response) {
                long entryId = ParamUtil.getLong(request, "entryId");
                long guestbookId = ParamUtil.getLong(request, "guestbookId");

                try {
                    ServiceContext serviceContext = ServiceContextFactory.getInstance(
                        Entry.class.getName(), request);

                    response.setRenderParameter(
                        "guestbookId", Long.toString(guestbookId));

                    _entryLocalService.deleteEntry(entryId, serviceContext);
                }
                catch (Exception e) {
                    System.out.println(e);

                    Class<?> clazz = e.getClass();

                    SessionErrors.add(request, clazz.getName());
                }
            }

    This method retrieves the entry object and calls the service to delete it.

6. Next you need to replace `render`.

            @Override
            public void render(
                    RenderRequest renderRequest, RenderResponse renderResponse)
                throws IOException, PortletException {

                try {
                    ServiceContext serviceContext = ServiceContextFactory.getInstance(
                        Guestbook.class.getName(), renderRequest);

                    long groupId = serviceContext.getScopeGroupId();

                    long guestbookId = ParamUtil.getLong(renderRequest, "guestbookId");

                    List<Guestbook> guestbooks = _guestbookService.getGuestbooks(
                        groupId);

                    if (guestbooks.isEmpty()) {
                        Guestbook guestbook = _guestbookService.addGuestbook(
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
    current site, and if none do, it creates a new one. Once some number of
    guestbooks have either been found or one has been created, they are served 
    up to be displayed by the JSP.

7. Finally you add your service references at the bottom of the file.

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
    
        

    At the bottom, you create create the `@Reference` to the service that you 
    need to call, in this case, `EntryLocalService`. Then you use the methods 
    provided by those services (whose implementations you created earlier) to 
    connect the user action of creating a new Entry with the backend. In this 
    way the portlet class does not have to be at all concerned with how the 
    service is implemented.
    
