# Writing Remote Service Classes [](id=writing-remote-service-classes)

Many default Liferay services are available as web services. Liferay exposes its
web services via SOAP and JSON web services. If you're running Liferay locally
on port 8080, visit the following URL to browse Liferay's default SOAP web
services:

    http://localhost:8080/api/axis

To browse Liferay's default JSON web services, visit this URL:

    http://localhost:8080/api/jsonws/

These web services APIs can be accessed by many different kinds of clients,
including non-portlet and even non-Java clients. You can use Service Builder to
generate similar remote services for your projects' custom entities. When you
run Service Builder with the `remote-service` attribute set to `true` for an
entity, all the classes, interfaces, and files required to support both SOAP and
JSON web services are generated for that entity. Service Builder generates
methods that call existing services, but it's up to you to implement the
methods that are exposed remotely. Let's use Service Builder to generate remote
services for the Nose-ster Event Listing portlet example project. You'll
implement a few methods for the Event Listing portlet that can be called
remotely via SOAP and JSON web services. 

Remember: local service methods are implemented in `EventLocalServiceImpl`.
Similarly, you'll implement remote service methods in `EventServiceImpl`. Add
the following methods to the `EventServiceImpl` class:

	public Event addEvent(
			long groupId, String name, String description,
			int month, int day, int year, int hour, int minute, long locationId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		EventListingPermission.check(
			getPermissionChecker(), groupId, EventListingActionKeys.ADD_EVENT);

		return EventLocalServiceUtil.addEvent(
			getUserId(), groupId, name, description, month, day, year, hour,
			minute, locationId, serviceContext);
	}

	public Event deleteEvent(long eventId)
		throws PortalException, SystemException {

		EventPermission.check(getPermissionChecker(), eventId,
			EventListingActionKeys.DELETE_EVENT);

		return eventLocalService.deleteEvent(eventId);
	}

	public Event getEvent(long eventId)
		throws PortalException, SystemException {

		EventPermission.check(getPermissionChecker(), eventId,
			EventListingActionKeys.VIEW);

		return EventLocalServiceUtil.getEvent(eventId);
	}

	public Event updateEvent(
			long userId, long eventId, String name, String description,
			int month, int day, int year, int hour, int minute, long locationId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		EventPermission.check(getPermissionChecker(), eventId,
			EventListingActionKeys.UPDATE_EVENT);

		return EventLocalServiceUtil.updateEvent(
			userId, eventId, name, description, month, day, year, hour, minute,
			locationId, serviceContext);
	}

Each remote service method performs security checks to determine whether the
caller has permission to add/update/delete events. We cover Liferay's security
and permissions system in Chapter 12. To see how the Event Listing portlet is
integrated with Liferay's permissions system, browse the Event Listing portlet
project available in the *Dev Guide SDK* at
[https://github.com/liferay/liferay-docs/tree/6.1.x/devGuide/code/devGuide-sdk](https://github.com/liferay/liferay-docs/tree/6.1.x/devGuide/code/devGuide-sdk). 
The project is in the SDK's
[portlets/event-listing-portlet](https://github.com/liferay/liferay-docs/tree/6.1.x/devGuide/code/devGuide-sdk/portlets/event-listing-portlet).
folder. 

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

Notice also that we modified the `deleteEvent` method of the
`EventServiceImpl` class passing it an event ID as a parameter instead of an
entire event object. The method is now ready to call as a remote web service. 

After you finish adding imports to `EventServiceImpl`, save the class and run
Service Builder again. 

Liferay uses Apache Axis to make SOAP web services available. Axis requires a
Web Service Deployment Descriptor (WSDD) to be generated in order to make the
SOAP web services available. Liferay provides a `build-wsdd` Ant target that
generates the WSDD. In Liferay IDE or Developer Studio, when viewing your
`service.xml` file in Overview mode, there's a button in the top-right
corner of the screen for calling the Build WSDD target. Liferay Portal makes
your service's Web Services Definition Language (WSDL) available after you've
built its WSDD and deployed your portlet project. Let's learn how to call your
remote services next. 

## Calling Remote Services [](id=calling-remote-services)

Service Builder can expose your project's remote web services both via a JSON
API and via SOAP. By default, running Service Builder with `remote-service` set
to `true` for your entities generates a JSON web services API for your project.
You can access your project's JSON-based RESTful services via a convenient web
interface. To view the JSON web services available for the Event Listing plugin,
visit the following URL:

    http://localhost:8080/event-listing-portlet/api/jsonws

Each entity's available operations are listed on the plugin's JSON web services
API page. If you've been implementing the Nose-ster Event Listing example
portlet used throughout this chapter, you'll be anxious to try out its remote
web services. You can invoke JSON web services directly from your browser. For
example, to bring up a test form for your Event entity's *delete-event*
operation, visit the above URL and click on its *delete-event* link. 

![Figure 4.10: You'll see a page displaying the name of the service method, its required parameters, its return type, possible exceptions it can throw, and a form for entering its parameters.](../../images/invoking-sb-generated-json-ws.png)

The only parameter required for the `delete-event` operation is an event ID.
Since there's no UI yet for the Event Listing application, you probably don't
have any events in your database. But if you did, you could check for an event's
ID in your `Event_Event` database table. Then you could enter the value into the
eventId field in the test page's Execute section and click *Invoke* to delete
that event. Liferay returns feedback from each invocation.

Finding a portlet's web services is easy with Liferay's JSON web service
interface. Invoking a portlet's web services via Liferay's JSON web service
interface is a great way to test them. You can also examine alternate equivalent
methods of calling the SOAP and JSON web services via JavaScript, Curl, and
URLs. Next, we'll consider how to implement custom SQL queries in your portlet,
so you can easily leverage information from multiple entity types. 

Service Builder can also make your project's web services available via SOAP
using Apache Axis. After you've built your portlet project's WSDDs and deployed
the project as a plugin, its services are available on the portal server. It's
easy to list your Nose-ster Event Listing plugin's SOAP web services. You have
two options: you can access them in Liferay IDE or you can open your browser to
the URL for its web services.

To access your SOAP web services in Liferay IDE, right-select your portlet from
under it's Liferay server in the *Servers* view. Then select *Test Liferay Web
Services...*.

If you'd rather view your SOAP web services from a browser, go to the following
URL: 

    http://localhost:8080/event-listing-portlet/api/axis

Liferay Portal lists the services available for all your entities and provides
links to their WSDL documents. Clicking on the WSDL link for the Event service
takes you to the following URL: 

    http://localhost:8080/event-listing-portlet/api/axis/Plugin_Event_EventService?wsdl 

This WSDL document lists the Event entity's SOAP web services. Once the web
service's WSDL is available, any SOAP web service client can access it. 
