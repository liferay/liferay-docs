# Creating Remote Services [](id=creating-remote-services)

Many default Liferay services are available as web services. Liferay exposes its
web services via JSON and SOAP web services. If you're running Liferay locally
on port 8080, visit the following URL to browse Liferay's default JSON web
services:

    http://localhost:8080/api/jsonws/

To browse Liferay's default SOAP web services, visit this URL:

    http://localhost:8080/api/axis

These web services APIs can be accessed by many different kinds of clients,
including non-portlet and even non-Java clients. You can use Service Builder to
generate similar remote services for your projects' custom entities. When you
run Service Builder with the `remote-service` attribute set to `true` for an
entity, all the classes, interfaces, and files required to support both SOAP and
JSON web services are generated for that entity. Service Builder generates
methods that call existing services, but it's up to you to implement the methods
that are exposed remotely. In this tutorial, you'll learn how to generate remote
services for your application. When you're done, your application's remote
service methods can be called remotely via JSON and SOAP web services. 

Remember that your application's local service methods should be implemented in
`*LocalServiceImpl`. Similarly, your applications remote service methods should
be implemented in `*ServiceImpl`.

<!--
This best practice appears in both 05-creating-local-service.markdown and
in 07-creating-remote-services.markdown. If you edit it, please update both
locations.
-->

**Best Practice:** If your application needs both local and remote services,
determine the service methods that your application needs for working with your
entity model. Add these service methods to `*LocalServiceImpl`. Then create
corresponding remote services methods in `*ServiceImpl`. Add permission checks
to the remote service methods and make the remote service methods invoke the
local service methods. The remote service methods can have the same names as the
local service methods that they call. Within your application, only call the
remote services. This ensures that your service methods are secured and that you
don't have to duplicate permissions code.

Here are the remote service methods of the `EventServiceImpl`
class that belongs to the
[Event Listing](https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/tutorials-sdk-6.2-ga3/portlets/event-listing-portlet)
project:

	public Event addEvent(long groupId, String name, String description,
			int month, int day, int year, int hour, int minute,
			long locationId, ServiceContext serviceContext)
			throws PortalException, SystemException {

		EventListingPermission.check(getPermissionChecker(), groupId,
				EventListingActionKeys.ADD_EVENT);

		return EventLocalServiceUtil.addEvent(getUserId(), groupId, name,
				description, month, day, year, hour, minute, locationId,
				serviceContext);
	}

	public Event deleteEvent(long eventId) throws PortalException,
			SystemException {

		EventPermission.check(getPermissionChecker(), eventId,
				EventListingActionKeys.DELETE_EVENT);

		return eventLocalService.deleteEvent(eventId);
	}

	public Event getEvent(long eventId) throws PortalException, SystemException {

		EventPermission.check(getPermissionChecker(), eventId,
				EventListingActionKeys.VIEW);

		return EventLocalServiceUtil.getEvent(eventId);
	}

	public Event updateEvent(long userId, long eventId, String name,
			String description, int month, int day, int year, int hour,
			int minute, long locationId, ServiceContext serviceContext)
			throws PortalException, SystemException {

		EventPermission.check(getPermissionChecker(), eventId,
				EventListingActionKeys.UPDATE_EVENT);

		return EventLocalServiceUtil.updateEvent(userId, eventId, name,
				description, month, day, year, hour, minute, locationId,
				serviceContext);
	}

As demonstrated above, each of your remote service methods should perform
security checks to determine whether the user invoking your service has
permission to perform the action in question. In the example above, the
permission checks determine whether the user has permission to add, view,
update, or delete events. Notice that the methods use three new classes:

- [`EventListingActionKeys`](https://github.com/liferay/liferay-docs/blob/master/develop/tutorials/tutorials-sdk-6.2-ga3/portlets/event-listing-portlet/docroot/WEB-INF/src/com/liferay/docs/eventlisting/util/EventListingActionKeys.java)
  is an extension of the [`ActionKeys`](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/security/permission/ActionKeys.html)
  class. You can create similar classes to provide constants that specify the
  types of actions that be performed in your application. 

- [`EventPermission`](https://github.com/liferay/liferay-docs/blob/master/develop/tutorials/tutorials-sdk-6.2-ga3/portlets/event-listing-portlet/docroot/WEB-INF/src/com/liferay/docs/eventlisting/service/permission/EventPermission.java)
  is a helper class for checking whether the user is authorized to perform
  specific actions on the Event entity. 

- [`EventListingPermission`](https://github.com/liferay/liferay-docs/blob/master/develop/tutorials/tutorials-sdk-6.2-ga3/portlets/event-listing-portlet/docroot/WEB-INF/src/com/liferay/docs/eventlisting/service/permission/EventListingPermission.java)
  is a helper class for checking whether the user is authorized to add the
  instances of the plugin's specific entity types. 

You can create analogues of the above classes for your application. The
`*ActionKeys` and `*Permission` classes are not generated by Service Builder. If
you want to use such classes in your application, you must manually create them.

<!--
To learn about Liferay's permissions framework, please refer to the
[Security and Permissions](http://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/security-and-permissions-liferay-portal-6-2-dev-guide-06-en)
tutorial.
-->

To see how the Event Listing Portlet is integrated with Liferay's permissions
system, you can browse the Event Listing example project available on Github
here:
[Event Listing](https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/tutorials-sdk-6.2-ga3/portlets/event-listing-portlet).

Notice the calls to the `eventLocalService` field's `addEvent`, `updateEvent`,
and `deleteEvent` methods. The `eventLocalService` field holds a Spring bean of
type `EventLocalServiceImpl` that's injected into `EventServiceBaseImpl` by
Service Builder. See `EventServiceBaseImpl` for a complete list of the Spring
beans available in `EventServiceImpl`. These include the following:

- `counterLocalService`
- `eventLocalService`
- `eventService`
- `eventPersistence`
- `locationLocalService`
- `locationPersistence`
- `locationService`
- `resourceLocalService`
- `resourcePersistence`
- `resourceService`
- `userLocalService`
- `userPersistence`
- `userService`

Notice also that the `deleteEvent` method of the `EventServiceImpl` class takes
an event ID as a parameter instead of an entire event object. A `delete*` method
that takes and entity ID as a parameter instead of an entire event object is
much easier to implement as a remote service.

After you've finished adding remote service methods to your `*ServiceImpl`
class, save it and run Service Builder again. After running Service Builder,
check Liferay JSON web services URL
[http://localhost:8080/api/jsonws/](http://localhost:8080/api/jsonws/)
and make sure that your remote services appear when you select your
application's context path.

Liferay uses Apache Axis to make SOAP web services available. Axis requires a
Web Service Deployment Descriptor (WSDD) to be generated in order to make the
SOAP web services available. Liferay provides a `build-wsdd` Ant target that
generates the WSDD. In Liferay IDE or Developer Studio, when viewing your
`service.xml` file in Overview mode, there's a button in the top-right
corner of the screen for calling the Build WSDD target. Liferay Portal makes
your service's Web Services Definition Language (WSDL) available after you've
built its WSDD and deployed your portlet project. Once you've created remote web
services for your application, you'll want to know how to invoke them. To learn
how, please see the [Invoking Remote Services](https://dev.liferay.com/develop/tutorials/-/knowledge_base/6-2/what-is-service-builder) tutorial.
