# Integrating generated code with the rest of the app

Now you have a little problem. You've started building a wall, and you built a 
little bit on one end, then went to the other end and another section of the 
wall, but you haven't made them meet in the middle yet (that's a very big 
problem for a wall). Now it's time to meet in the middle.

First you need to do a little housekeeping. You manually created your own model 
earlier now that you have a generated model being managed by Service Builder, 
you need to remove the old one to prevent any conflicts:

1. Find the 'com.liferay.docs.guestbook.model' package in the `guestbook-mvc` 
    module.
2. Delete it - this will cause some exceptions now, but they'll all be fixed 
    soon.

Now you need to make your web modules aware of your service modules so that 
you can access the services from your web module. Then you need to update your `addEntry` method to use the new services.

1. Add these declarations to the `build.gradle` file for your guestbook-web 
    project, to add the service and api modules to the classpath.

    compileOnly project(":modules:guestbook:guestbook-api")
	compileOnly project(":modules:guestbook:guestbook-service")


2. First replace the `addEntry` method with the new version:

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
	as entry data. This is all done in a `try...catch` statement to provide
	appropriate feedback if the entry cannot be added.
	
3. Next do `addGuestbook`:

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
		
4. Then replace `deleteEntry`

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

5. Next you need to replace `render`.

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

6. Finally you add your service references at the bottom of the file.

			@Reference(unbind = "-")
			protected void setEntryService(EntryLocalService entryService) {
				_entryService = entryLocalService;
			}

			@Reference(unbind = "-")
			protected void setGuestbookService(GuestbookLocalService guestbookService) {
				_guestbookService = guestbookService;
			}

			private EntryLocalService _entryLocalService;
			private GuestbookLocalService _guestbookLocalService;
	
		

    At the bottom, you create create the `@Reference` to the service that you 
	need to call, in this case, `EntryLocalService`. Then you use the methods 
	provided by those services (whose implementations you created earlier) to 
	connect the user action of creating a new Entry with the backend. In this 
	way the portlet class does not have to be at all concerned with how the 
	service is implemented.
	