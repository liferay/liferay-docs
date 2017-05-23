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

In order to properly model the Guestbook and Entries going forward, you're going
to need to introduce some new concepts into the application. Rather than simply
create entries in place, you will create a guestbook object which will contain 
the entries. You'll need to also create the various methods associated with
guestbook management. In addition, you'll add a field for users to enter an
email address in the guestbook.

When you create separate entity for guestbooks, you will be able to create
multiple guestbooks with different sets of entries. You will also separate the 
entity data from being simply the portlet data, and instead you will have
guestbooks and entities within those guestbooks.

1. Open 'service.xml' in your `guestbook-service` project.
2. Replace the contents of the file with the following:

	<?xml version="1.0"?>
	<!DOCTYPE service-builder PUBLIC "-//Liferay//DTD Service Builder 7.0.0//EN" "http://www.liferay.com/dtd/liferay-service-builder_7_0_0.dtd">

	    <service-builder auto-namespace-tables="true" package-path="com.liferay.docs.guestbook">
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
		<entity local-service="true" name="Guestbook" remote-service="true" uuid="true">

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
	
This creates two entities the Entry and the Guestbook.

The Entry fields are defined as:

		<column name="companyId" type="long" />
		<column name="userId" type="long" />
		<column name="userName" type="String" />
		<column name="createDate" type="Date" />
		<column name="modifiedDate" type="Date" />
		<column name="name" type="String" />
		<column name="email" type="String" />
		<column name="message" type="String" />
		<column name="guestbookId" type="long" />

With a finder for `groupId` and`guestbookId` being generated. 

			<finder name="G_G" return-type="Collection">
				<finder-column name="groupId" />
				<finder-column name="guestbookId" />
			</finder>

The Guestbook fields are defined as:

	<column name="companyId" type="long" />
	<column name="userId" type="long" />
	<column name="userName" type="String" />
	<column name="createDate" type="Date" />
	<column name="modifiedDate" type="Date" />
	<column name="name" type="String" />

With a finder for `groupId.` 

	<finder name="GroupId" return-type="Collection">
		<finder-column name="groupId" />
	</finder>

You've also identified some associated exception types   

		<exceptions>
			<exception>EntryEmail</exception>
			<exception>EntryMessage</exception>
			<exception>EntryName</exception>
			<exception>GuestbookName</exception>
		</exceptions>
			
1. Go to the Gradle Tasks pane on the right side of @ide@.
2. Open `guestbook-service` &rarr; `build`
3. Run `buildService`

## Implementing Service Methods

Your persistence model has now been generated by Service Builder, but
you still need to create the implementations which will then enable Service
Builder to properly generate the persistence methods. Using Liferay and Service 
Builder, the implementations are defined in '*ServiceImpl' classes in 
the service module.

1. Right-click on your project and select *Gradle* &rarr; *Refresh Gradle Project* to display all of the generated class.

2. Open `com.liferay.docs.guestbook.service.imply.EntryLocalServiceImpl` and add these methods:

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

		return entry;
	}

	public Entry deleteEntry(long entryId, ServiceContext serviceContext)
		throws PortalException {

		Entry entry = getEntry(entryId);

		resourceLocalService.deleteResource(
			serviceContext.getCompanyId(), Entry.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL, entryId);

		entry = deleteEntry(entryId);

		return entry;
	}

	public List<Entry> getEntries(long groupId, long guestbookId) {
		return entryPersistence.findByG_G(groupId, guestbookId);
	}

	public List<Entry> getEntries(
		long groupId, long guestbookId, int start, int end, OrderByComparator<Entry> obc) {

		return entryPersistence.findByG_G(groupId, guestbookId, start, end, obc);
	}

	public int getEntriesCount(long groupId, long guestbookId) {
		return entryPersistence.countByG_G(groupId, guestbookId);
	}
	
	public List<Entry> getEntries(long groupId, long guestbookId, int start, int end)
			     throws SystemException {

			    return entryPersistence.findByG_G(groupId, guestbookId, start, end);
			}
	
	
		protected void validate(String name, String email, String entry)
				throws PortalException {

				if (Validator.isNull(name)) {
					throw new EntryNameException();
				}

				if (!Validator.isEmailAddress(email)) {
					throw new EntryEmailException();
				}

				if (Validator.isNull(entry)) {
					throw new EntryMessageException();
				}
	}
	
	
	
3. Press [CTRL]+[SHIFT]+O to organize imports and select `java.util.Date` and
    `com.liferay.portal.kernel.service.ServiceContext,`
	`com.liferay.docs.guestbook.model.Entry,` and 
	`com.liferay.portal.kernel.util.Validator.` when prompted.

The `addEntry` method gets the `groupId` and the `userId` from the portal, 
gets the date from the Java service, and validates the users text entry. It then
instantiates an `Entry` object, adds the necessary fields to the object, 
creates a new resource in the database, and returns the new `entry`. The 
`deleteEntry` method removes an item based on the primary key (`entryId`.) You 
also have the "get" methods defined as well as a utility method, `validate`, 
which verifies that field data was properly entered for the fields before storing them in the database.

1. Open `com.liferay.docs.guestbook.service.imply.GuestbookLocalServiceImpl`.
2. Add these methods:

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

			return guestbook;
		}

		public List<Guestbook> getGuestbooks(long groupId) {
			return guestbookPersistence.findByGroupId(groupId);
		}

		public List<Guestbook> getGuestbooks(long groupId, int start, int end, OrderByComparator<Guestbook> obc) {
			return guestbookPersistence.findByGroupId(groupId, start, end, obc);
		}

		public int getGuestbooksCount(long groupId) {
			return guestbookPersistence.countByGroupId(groupId);
		}


		protected void validate(String name) throws PortalException {
			if (Validator.isNull(name)) {
				throw new GuestbookNameException();
			}
		}
	
	
3. Press [CTRL]+[SHIFT]+O to organize imports and select `java.util.Date` and
    `com.liferay.portal.kernel.service.ServiceContext,`
	`com.liferay.docs.guestbook.model.Entry,` and 
	`com.liferay.portal.kernel.util.Validator.` when prompted.

Again, you've added the basic operations that you'll need to manage the 
Guestbook. The `addGuestbook` method gets the necessary information from the portal and the Java services, and validates the users text entry. It then instantiates an `Entry` object, adds the necessary fields to the object, 
creates a new resource in the database, and returns the new `entry`. The 
`deleteEntry` method removes an item based on the primary key (`entryId`.) You 
also have the "get" methods defined as well as a utility method, `validate`, 
that your class needs.

These are the specific "Local" implementations of the Guestbook and Entries, but
you'll also need to create the root level implementations. These are stored in
`GuestbookServiceImpl` and `EntryServiceImpl` right next to the 
*LocalServiceImpl classes. The root *ServiceImpl classes are what Service 
Builder will use to generate the backend methods, and when you go a little 
deeper, they will be where thing like Permissions code ends up.

1. Open `EntryServiceImpl`
2. Add the following methods:

	public Entry addEntry(
				long userId, long guestbookId, String name, String email,
				String message, ServiceContext serviceContext)
			throws PortalException {

			return EntryLocalServiceUtil.addEntry(
				userId, guestbookId, name, email, message, serviceContext);
		}

		public Entry deleteEntry(long entryId, ServiceContext serviceContext)
			throws PortalException {
		return EntryLocalServiceUtil.deleteEntry(entryId, serviceContext);
		}

		public List<Entry> getEntries(long groupId, long guestbookId) {
			return entryPersistence.findByG_G(groupId, guestbookId);
		}

		public List<Entry> getEntries(
			long groupId, long guestbookId, int start, int end, OrderByComparator<Entry> obc) {

			return entryPersistence.findByG_G(groupId, guestbookId, start, end, obc);
		}

		public int getEntriesCount(long groupId, long guestbookId) {
			return entryPersistence.countByG_G(groupId, guestbookId);
		}		

3. Open `GuestbookServiceImpl`
4. Add the following methods:

	 public Guestbook addGuestbook(
	 			long userId, String name, ServiceContext serviceContext)
	 		throws PortalException {

	 		return GuestbookLocalServiceUtil.addGuestbook(
	 			userId, name, serviceContext);
	
	 	}	

	 	public List<Guestbook> getGuestbooks(long groupId) {
	 		return guestbookPersistence.findByGroupId(groupId);
	 	}

	 	public List<Guestbook> getGuestbooks(long groupId, int start, int end, OrderByComparator<Guestbook> obc) {
	 		return guestbookPersistence.findByGroupId(groupId, start, end, obc);
	 	}

	 	public int getGuestbooksCount(long groupId) {
	 		return guestbookPersistence.countByGroupId(groupId);
	 	}  

You've created the implementation methods, but these are not the classes that 
you access if you want to actually run any of these methods. In order to use 
these methods, you would actually call them through the service class, and the
correct service methods are generated by Service Builder when you run 
`buildService` again.

1. Run `buildService`
2. Your services methods will now be generated.

+$$$

**Tip:** When working with Service Builder, if something isn't behaving as
expected, before you get too far into troubleshooting:

1. Run 'buildService' in your service module.
2. Run *Gradle* &rarr; *Refresh Gradle Projects*

If you've made any change to your services or any change that needs to be picked
up by Service Builder or Gradle's dependency management, that will catch it.

$$$

## Integrating generated code with the rest of the app

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

1. Add these declarations to the `build.gradle` file for your guestbook-module 
    project, to add the service and api modules to the classpath.

    compileOnly project(":modules:guestbook:guestbook-api")
	compileOnly project(":modules:guestbook:guestbook-service")


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

					try {
						_entryService.addEntry(
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
						"mvcPath", "edit_guestbook.jsp");
				}
			}

			public void deleteEntry(ActionRequest request, ActionResponse response) {
				long entryId = ParamUtil.getLong(request, "entryId");
				long guestbookId = ParamUtil.getLong(request, "guestbookId");

				try {
					ServiceContext serviceContext = ServiceContextFactory.getInstance(
						Entry.class.getName(), request);

					response.setRenderParameter(
						"guestbookId", Long.toString(guestbookId));

					_entryService.deleteEntry(entryId, serviceContext);
				}
				catch (Exception e) {
					System.out.println(e);

					Class<?> clazz = e.getClass();

					SessionErrors.add(request, clazz.getName());
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
	
		
The `addEntry` method gets the name, message, and email fields that the user 
submits through the JSP and passes them on to the service to be stored as entry
data. The `addGuestbook` method does the same for a guestbook.

The new `render` method checks if any guestbooks currently exist for the 
current site, and if none do, it creates a new one. Once some number of
guestbooks have either been found or one has been created, they are served up 
to be displayed by the JSP.

At the bottom, you create create the `@Reference` to the service that you need 
to call, in this case, `EntryService`. Then you use the methods provided by 
those services (whose implementations you created earlier) to connect the user 
action of creating a new Entry with the backend. In this way the portlet class 
does not have to be at all concerned with how the service is implemented.

## Updating the View

Since you updated more than just the basic mechanism behind creating the Entry,
and actually completely changed the method and structure, you'll want to make
some updates to the UI as well. To do that, you'll need to create a new JSP for
managing Guestbooks, and update the existing JSPs.

1. First you should update the dependencies that you'll need.
2. Open `init.jsp` from `/src/main/resources/META-INF/resources/`
3. Add the following additional dependencies:
    
	<%@ taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %>

	<%@ page import="java.util.List" %>
	<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
	<%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %>
	<%@ page import="com.liferay.portal.kernel.util.StringPool" %>
	<%@ page import="com.liferay.portal.kernel.model.PersistedModel" %>
	<%@ page import="com.liferay.portal.kernel.dao.search.SearchEntry" %>
	<%@ page import="com.liferay.portal.kernel.dao.search.ResultRow" %>
	<%@ page import="com.liferay.docs.guestbook.model.Guestbook" %>
	<%@ page import="com.liferay.docs.guestbook.service.EntryLocalServiceUtil" %>
	<%@ page import="com.liferay.docs.guestbook.service.GuestbookLocalServiceUtil" %>
	<%@ page import="com.liferay.docs.guestbook.model.Entry" %>
	

4. Open the `view.jsp` file found in `/resources/META-INF/resources/`
5. Replace the contents with the following code:

	<%@ include file="init.jsp" %>

	<%
		long guestbookId = Long.valueOf((Long) renderRequest
				.getAttribute("guestbookId"));
	%>

	<aui:nav cssClass="nav-tabs">

		<%
			List<Guestbook> guestbooks = GuestbookLocalServiceUtil
						.getGuestbooks(scopeGroupId);
				for (int i = 0; i < guestbooks.size(); i++) {
					Guestbook curGuestbook = (Guestbook) guestbooks.get(i);

					String cssClass = StringPool.BLANK;

					if (curGuestbook.getGuestbookId() == guestbookId) {
						cssClass = "active";
					}
					else	
				 {
				
		%>

		<portlet:renderURL var="viewPageURL">
			<portlet:param name="mvcPath" value="/view.jsp" />
			<portlet:param name="guestbookId"
				value="<%=String.valueOf(curGuestbook.getGuestbookId())%>" />
		</portlet:renderURL>

		<aui:nav-item cssClass="<%=cssClass%>" href="<%=viewPageURL%>"
			label="<%=HtmlUtil.escape(curGuestbook.getName())%>" />

		<%
				}
			}
		%>

	</aui:nav>

	<aui:button-row cssClass="guestbook-buttons">

			<portlet:renderURL var="addGuestbookURL">
				<portlet:param name="mvcPath"
					value="/edit_guestbook.jsp" />
			</portlet:renderURL>
		
			<aui:button onClick="<%=addGuestbookURL.toString()%>" 
				value="Add Guestbook" />
	
			<portlet:renderURL var="addEntryURL">
				<portlet:param name="mvcPath" value="/edit_entry.jsp" />
				<portlet:param name="guestbookId"
					value="<%=String.valueOf(guestbookId)%>" />
			</portlet:renderURL>
		
			<aui:button onClick="<%=addEntryURL.toString()%>" value="Add Entry"></aui:button>

	</aui:button-row>

	<liferay-ui:search-container total="<%=EntryLocalServiceUtil.getEntriesCount()%>"
	>
		<liferay-ui:search-container-results
			results="<%=EntryLocalServiceUtil.getEntries(scopeGroupId.longValue(),
							guestbookId, searchContainer.getStart(),
							searchContainer.getEnd())%>" />

		<liferay-ui:search-container-row
			className="com.liferay.docs.guestbook.model.Entry" modelVar="entry">

			<liferay-ui:search-container-column-text property="message" />

			<liferay-ui:search-container-column-text property="name" />
		
			<liferay-ui:search-container-column-jsp path="guestbook_actions.jsp" align="right" />

		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>

You've significantly expanded the `view.jsp` now. There are now buttons for both
adding an Entry and adding a new Guestbook. You've also added the necessary 
backend information to connect to this. Right now you're doing all of this with
fairly generic styles to accomplish this, but later you'll learn to leverage 
the full power of Liferay's UI tools to enhance your JSPs.

Next you need to create a new `edit_guestbook.jsp` and edit the `edit_entry.jsp`
to match our new system.

1. Open `edit_entry.jsp.`
2. Replace the existing code with this:

	<%@include file="init.jsp" %>

	<% 

	long entryId = ParamUtil.getLong(renderRequest, "entryId");

	Entry entry = null;
	if (entryId > 0) {
		entry = EntryLocalServiceUtil.getEntry(entryId);
	}

	long guestbookId = ParamUtil.getLong(renderRequest, "guestbookId");

	%>

	<portlet:renderURL var="viewURL">

		<portlet:param name="mvcPath" value="/view.jsp"></portlet:param>

	</portlet:renderURL>

	<portlet:actionURL name="addEntry" var="addEntryURL"></portlet:actionURL>


	<aui:form action="<%= addEntryURL %>" name="<portlet:namespace />fm">

	    <aui:model-context bean="<%= entry %>" model="<%= Entry.class %>" />

			<aui:fieldset>

			    <aui:input name="name" />
			    <aui:input name="email" />
			    <aui:input name="message" />
			    <aui:input name="entryId" type="hidden" />
				<aui:input name="guestbookId" type="hidden" value='<%= entry == null ? guestbookId : entry.getGuestbookId() %>'/>

			</aui:fieldset>
		
			<aui:button-row>

	            <aui:button type="submit"></aui:button>
	            <aui:button type="cancel" onClick="<%= viewURL.toString() %>"></aui:button>
            
	        </aui:button-row>
	</aui:form>
	
3. Now right-click on the `resources` folder and select *New* &rarr; *File*.
4. Name the file `edit_guestbook.jsp` and click *Finish*.
5. Open the new file and paste in this code:

	<%@include file = "init.jsp" %>

	<portlet:renderURL var="viewURL">
	    <portlet:param name="mvcPath" value="/view.jsp"></portlet:param>
	</portlet:renderURL>

	<portlet:actionURL name="addGuestbook" var="addGuestbookURL"></portlet:actionURL>

	<aui:form action="<%= addGuestbookURL %>" name="<portlet:namespace />fm">

	        <aui:fieldset>

	            <aui:input name="name" />

	        </aui:fieldset>

	        <aui:button-row>

	            <aui:button type="submit"></aui:button>
	            <aui:button type="cancel" onClick="<%= viewURL %>"></aui:button>

	        </aui:button-row>
	</aui:form>

	
These updates are a bit more simple. You created additional fields to be added 
for each entry creation to match up to the fields you created on the backend,
and you created a new form for creating a guestbook with one field.

## Deploying and testing the application

Now that you created your services, and set them all up, you'll need to deploy
your `guestbook-service` and `guestbook-api` modules to get your complete 
integrated application working.

1. Drag and drop the `guestbook-api` module onto the server.
2. Drag and drop the `guestbook-service` module onto the server.
3. Wait for the everything to finish deploying.
4. Head over to your Liferay instance at `localhost:8080` in your browser to
    test your updated application.

Now you'll want to test your finished application.

1. In your application, click on *Add Guestbook*.
2. Enter a name for your Guestbook and click *Submit*.
3. Verify that a guestbook appears.
4. Now click *Add Entry*.
5. Enter a *Name*, *Message*, and *Email Address*.
6. Click *Submit*.
7. Verify that your entry now appears.

![Figure x: A new Guestbook and Entry created.](../../../images/guestbook-entry-test.png)

# Wrapping up the application

You've done everything you need to create a working web application with 
Liferay. In some cases, you could deploy it now and get working, but there are
a lot of other important pieces to creating a full-featured application integrated with Liferay. Stay tuned to learn how to get your application
properly organized in Liferay's application hierarchy, and how to create
administrative applications for the Control Panel.