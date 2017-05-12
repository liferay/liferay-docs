# Service Builder
Service Builder is Liferay's code generation tool for defining custom object
models called entities. Service Builder generates your application's service
layer and provides a clean separation between your object model and code for
the underlying database.

Now you will use Service Builder to generate create, read, update, delete, and
find operations for your application. You'll also use Service Builder to
generate the necessary model, persistence, and service layers for your
application all using one XML file. Then you can add the necessary business
logic for your application.

## Service Layer
This application is a data-driven applications, uses services for storing and 
retrieving data. The application asks for data, and the service fetches it. The 
application can then display this data to the user, who reads it or modifies 
it. If the data is modified, the application passes it back to the service, and 
the service stores it. The application doesn’t need to know anything about how 
the service does what it does.

You'll create a Service Builder project and use the `service.xml` file with all 
of the necessary entities to generate this code.

1. In Eclipse, right-click on your package and select *New* &rarr; *Liferay Module Project*.
2. Name the project `guestbook`.
3. Select `service-builder` for the Project Template Name.
4. Click *Next*.
5. Enter `com.liferay.docs.guestbook` for the *Package Name*.
6. Click Finish.

This will create two modules that contain the basic workings and structure of 
your Service Layer, but you still need to generate the persistence code, create 
the correct business logic, and connect it back to the portlet application.

## Persistence
The persistence layer is responsible for saving and retrieving your model data. 
The service layer is like a buffer zone between your application and your 
persistence layer: having it gives you the freedom in the future to swap out 
your persistence layer for a different implementation without modifying 
anything but the calls in the service layer.

1. Open 'service.xml' in your `guestbook-service` project.
2. Replace the contents of the file with the following:

    <service-builder auto-namespace-tables="true"    
	package-path="com.liferay.docs.guestbook">
	<author>liferay</author>
	<namespace>GB</namespace>
	<entity local-service="true" name="Entry" remote-service="true" uuid="true">

		<!-- PK fields -->

		<column name="entryId" primary="true" type="long" />

		<!-- Group instance -->

		<column name="groupId" type="long" />

		<!-- Audit fields -->

		<column name="companyId" type="long" />
		<column name="userId" type="long" />
		<column name="userName" type="String" />
		<column name="createDate" type="Date" />
		<column name="modifiedDate" type="Date" />
		<column name="name" type="String" />
		<column name="email" type="String" />
		<column name="message" type="String" />
		<column name="guestbookId" type="long" />

		<finder name="G_G" return-type="Collection">
			<finder-column name="groupId" />
			<finder-column name="guestbookId" />
		</finder>
	</entity>
	<entity local-service="true" name="Guestbook" remote-service="true" 
	uuid="true">

		<!-- PK fields -->

		<column name="guestbookId" primary="true" type="long" />

		<!-- Group instance -->

		<column name="groupId" type="long" />

		<!-- Audit fields -->

		<column name="companyId" type="long" />
		<column name="userId" type="long" />
		<column name="userName" type="String" />
		<column name="createDate" type="Date" />
		<column name="modifiedDate" type="Date" />
		<column name="name" type="String" />

		<finder name="GroupId" return-type="Collection">
			<finder-column name="groupId" />
		</finder>
	</entity>
	<exceptions>
		<exception>EntryEmail</exception>
		<exception>EntryMessage</exception>
		<exception>EntryName</exception>
		<exception>GuestbookName</exception>
	</exceptions>
    </service-builder>
	
This creates two entities, the Guestbook and the Entry. The Guestbooks fields 
are defined here:

		<column name="companyId" type="long" />
		<column name="userId" type="long" />
		<column name="userName" type="String" />
		<column name="createDate" type="Date" />
		<column name="modifiedDate" type="Date" />
		<column name="name" type="String" />
		<column name="email" type="String" />
		<column name="message" type="String" />
		<column name="guestbookId" type="long" />

You also defined finders which will generate methods to locate a Guestbook 
object based on the provided key.

		<finder name="G_G" return-type="Collection">
			<finder-column name="groupId" />
			<finder-column name="guestbookId" />
		</finder>
		
The Entry fields are defined as:

		<column name="companyId" type="long" />
		<column name="userId" type="long" />
		<column name="userName" type="String" />
		<column name="createDate" type="Date" />
		<column name="modifiedDate" type="Date" />
		<column name="name" type="String" />

With only a finder for `groupId` being generated. 

		<finder name="GroupId" return-type="Collection">
			<finder-column name="groupId" />
		</finder>

1. Go to the Gradle Tasks pane on the right side of @ide@.
2. Open `guestbook-service` &rarr; `build`
3. Run `buildService`

## Implementing Service Methods

All of your persistence methods have now been generated by Service Builder, but
you still need to create the implementations of those methods that describe 
how you translate the user input into the data being stored in the database. 
Using Liferay and Service Builder, the implementations are typically defined in
'*ServiceImpl' classes in the service module.

Open `GuestbookLocalServiceImpl` and replace the `addGuestbook` method with this:

		public Guestbook addGuestbook(
				long userId, String name, ServiceContext serviceContext)
			throws PortalException {

			long groupId = serviceContext.getScopeGroupId();

			User user = userLocalService.getUserById(userId);

			Date now = new Date();

			validate(name);

			long guestbookId = counterLocalService.increment();

			Guestbook guestbook = guestbookPersistence.create(guestbookId);

			guestbook.setUuid(serviceContext.getUuid());
			guestbook.setUserId(userId);
			guestbook.setGroupId(groupId);
			guestbook.setCompanyId(user.getCompanyId());
			guestbook.setUserName(user.getFullName());
			guestbook.setCreateDate(serviceContext.getCreateDate(now));
			guestbook.setModifiedDate(serviceContext.getModifiedDate(now));
			guestbook.setName(name);
			guestbook.setExpandoBridgeAttributes(serviceContext);

			guestbookPersistence.update(guestbook);

			resourceLocalService.addResources(
				user.getCompanyId(), groupId, userId, Guestbook.class.getName(),
				guestbookId, false, true, true);

			return guestbook;
		}

The `addGuestbook` method gets the `groupId` and the `userId` from the portal, 
and gets the date from the Java service It then instantiates a `Guestbook` 
object, adds the necessary fields to the object, creates a new resource in the 
database, and returns the new `guestbook`

Now, open `EntryLocalServiceImpl` and replace the `addEntry` method with this:

		public Entry addEntry(
				long userId, long guestbookId, String name, String email,
				String message, ServiceContext serviceContext)
			throws PortalException {

			long groupId = serviceContext.getScopeGroupId();

			User user = userLocalService.getUserById(userId);

			Date now = new Date();

			validate(name, email, message);

			long entryId = counterLocalService.increment();

			Entry entry = entryPersistence.create(entryId);

			entry.setUuid(serviceContext.getUuid());
			entry.setUserId(userId);
			entry.setGroupId(groupId);
			entry.setCompanyId(user.getCompanyId());
			entry.setUserName(user.getFullName());
			entry.setCreateDate(serviceContext.getCreateDate(now));
			entry.setModifiedDate(serviceContext.getModifiedDate(now));
			entry.setExpandoBridgeAttributes(serviceContext);
			entry.setGuestbookId(guestbookId);
			entry.setName(name);
			entry.setEmail(email);
			entry.setMessage(message);

			entryPersistence.update(entry);

			resourceLocalService.addResources(
				user.getCompanyId(), groupId, userId, Entry.class.getName(),
				entryId, false, true, true);

			return entry;
		}

	}

The `addEntry` method gets the `groupId` and the `userId` from the portal, 
gets the date from the Java service, and validates the users text entry. It then
instantiates an `Entry` object, adds the necessary fields to the object, 
creates a new resource in the database, and returns the new `entry`.

You've created the implementation methods, but these are not the classes that 
you access if you want to actually run any of these methods. In order to use 
these methods, you would actually call them through the service class, and in 
order for the service class to know about these methods, you need to run 
`buildService` again.

1. Run `buildService`
2. Your services will now be aware of the implementation that you created.

## Integrating generated code with the rest of the app

Now you have a little problem. You've started building a wall, and you built a 
little bit on one end, then went to the other end and another section of the 
wall, but you haven't made them meet in the middle yet (that's a very big 
problem for a wall). Now it's time to meet in the middle.

You need to make your web modules aware of your service modules so that 
you can access the services from your web module. Then you need to update your `addEntry` method to use the

1. Add this code to the `build.gradle` file for your guestbook-module project.

    compileOnly project("guestbook:guestbook-api")
	compileOnly project("guestbook:guestbook-service")


2. Replace all of the methods in GuestbookPortlet with their new versions using
    the services and removing the portlet preferences code.

    public void addEntry(ActionRequest request, ActionResponse response)
			throws PortalException {

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				Entry.class.getName(), request);

			String userName = ParamUtil.getString(request, "name");
			String email = ParamUtil.getString(request, "email");
			String message = ParamUtil.getString(request, "message");
			long guestbookId = ParamUtil.getLong(request, "guestbookId");
			long entryId = ParamUtil.getLong(request, "entryId");

					_entryService.addEntry(
						serviceContext.getUserId(), guestbookId, userName, email,
						message, serviceContext);

					SessionMessages.add(request, "entryAdded");

					response.setRenderParameter(
						"guestbookId", Long.toString(guestbookId));
			
		}

		public void addGuestbook(ActionRequest request, ActionResponse response)
			throws PortalException {

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				Guestbook.class.getName(), request);

			String name = ParamUtil.getString(request, "name");

			try {
				_guestbookService.addGuestbook(
					serviceContext.getUserId(), name, serviceContext);

				SessionMessages.add(request, "guestbookAdded");
			}
			catch (Exception e) {
				Class<?> clazz = e.getClass();

				SessionErrors.add(request, clazz.getName());

				response.setRenderParameter(
					"mvcPath", "/html/guestbookmvcportlet/edit_guestbook.jsp");
			}
		}


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

		@Reference(unbind = "-")
		protected void setEntryService(EntryService entryService) {
			_entryService = entryService;
		}

		@Reference(unbind = "-")
		protected void setGuestbookService(GuestbookService guestbookService) {
			_guestbookService = guestbookService;
		}

		private EntryService _entryService;
		private GuestbookService _guestbookService;

At the bottom, you create create the `@Reference` to the service that you need 
to call, in this case, `EntryService` and `GuestbookService.` Then you use the
methods provided by those services (whose implementations you created earlier) 
to connect the user action of creating a new Entry with the backend. In this way
the portlet class does not have to be at all concerned with how the service is
implemented.

# Wrapping up the application

You've done everything you need to create a working web application with 
Liferay. In some cases, you could deploy it now and get working, but there are
a lot of other important pieces to creating a full-featured application integrated with Liferay. Stay tuned to learn how to get your application
properly organized in Liferay's application hierarchy, and how to create
administrative applications for the Control Panel.