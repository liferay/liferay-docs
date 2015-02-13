# Writing Local Service Classes

The heart of your service is its `*LocalServiceImpl` class. This class is your
entity's local service extension point. Local services can be invoked within
your application or by other Liferay applications running on the same Liferay
instance as your application. Remote services differ from local services in that
remote services can be invoked from any application that can access your Liferay
instance (e.g., over the internet) and has permission to do so. All of your
application's core business logic for working with your entity model (or models)
should be added as methods of your `*LocalServiceImpl` class. However, before
adding any custom service methods, you should review the initial service classes
that Service Builder generated during its initial run. 

**Best Practice:** If your application needs both local and remote services,
determine the service methods that your application needs for working with your
entity model. Add these service methods to `*LocalServiceImpl`. Then create
corresponding remote services methods to `*ServiceImpl`. Add permission checks
to the remote services methods and make them call the local service methods. The
remote service methods can have the same names as the local service methods that
they call. Within your application, only call the remote services. This ensures
that your services methods are secured and you don't have to duplicate
permissions code.

Note that Service Builder creates a `*LocalService` class which is the interface
for the local service. It contains the signatures of every method in
`*LocalServiceBaseImpl` and `*LocalServiceImpl`. `*LocalServiceBaseImpl`
contains a few automatically generated methods providing common functionality.
Since the `*LocalService` class is generated, you should never modify it. If you
do, your changes will be overwritten the next time you run Service Builder. All
custom code should be placed in `EventLocalServiceImpl`, where it will not be
overwritten.

The Event Listing project's `EventLocalServiceImpl` class demonstrates the kinds
of service methods that applications commonly need for working with an entity
model. This class includes the following local service methods:

    public Event addEvent(
            long userId, long groupId, String name, String description,
            int month, int day, int year, int hour, int minute, long locationId,
            ServiceContext serviceContext)
        throws PortalException, SystemException {

        User user = userPersistence.findByPrimaryKey(userId);

        Date now = new Date();

        long eventId = counterLocalService.increment(Event.class.getName());

        Event event = eventPersistence.create(eventId);

        event.setName(name);
        event.setDescription(description);

        Calendar dateCal = CalendarFactoryUtil.getCalendar(
            user.getTimeZone());
        dateCal.set(year, month, day, hour, minute);
        Date date = dateCal.getTime();
        event.setDate(date);

        event.setLocationId(locationId);

        event.setGroupId(groupId);
        event.setCompanyId(user.getCompanyId());
        event.setUserId(user.getUserId());
        event.setCreateDate(serviceContext.getCreateDate(now));
        event.setModifiedDate(serviceContext.getModifiedDate(now));

        super.addEvent(event);

        // Resources

        resourceLocalService.addResources(
            event.getCompanyId(), event.getGroupId(), event.getUserId(),
            Event.class.getName(), event.getEventId(), false,
            true, true);

        return event;
    }

    public Event deleteEvent(Event event) throws SystemException {

        return eventPersistence.remove(event);
    }

    public Event deleteEvent(long eventId)
        throws PortalException, SystemException {

        Event event = eventPersistence.findByPrimaryKey(eventId);

        return deleteEvent(event);
    }

    public Event getEvent(long eventId)
        throws SystemException, PortalException {

        return eventPersistence.findByPrimaryKey(eventId);
    }

    public List<Event> getEventsByGroupId(long groupId) throws SystemException {

        return eventPersistence.findByGroupId(groupId);
    }

    public List<Event> getEventsByGroupId(long groupId, int start, int end)
        throws SystemException {

        return eventPersistence.findByGroupId(groupId, start, end);
    }

    public int getEventsCountByGroupId(long groupId) throws SystemException {

        return eventPersistence.countByGroupId(groupId);
    }

    public Event updateEvent(
            long userId, long eventId, String name, String description,
            int month, int day, int year, int hour, int minute,
            long locationId, ServiceContext serviceContext)
        throws PortalException, SystemException {

        User user = userPersistence.findByPrimaryKey(userId);

        Date now = new Date();

        Event event = EventLocalServiceUtil.fetchEvent(eventId);

        event.setModifiedDate(serviceContext.getModifiedDate(now));
        event.setName(name);
        event.setDescription(description);

        Calendar dateCal = CalendarFactoryUtil.getCalendar(
            user.getTimeZone());
        dateCal.set(year, month, day, hour, minute);
        Date date = dateCal.getTime();
        event.setDate(date);

        event.setLocationId(locationId);

        super.updateEvent(event);

        return event;
    }	

Remember to import the required classes. `EventLocalServiceImpl` uses the
following imports:

    import java.util.Calendar;
    import java.util.Date;
    import java.util.List;

    import com.liferay.portal.kernel.exception.PortalException;
    import com.liferay.portal.kernel.exception.SystemException;
    import com.liferay.portal.kernel.util.CalendarFactoryUtil;
    import com.liferay.portal.model.User;
    import com.liferay.portal.service.ServiceContext;

    import com.liferay.docs.eventlisting.model.Event;
    import com.liferay.docs.eventlisting.service.EventLocalServiceUtil;

In order to add an Event to the database, you need an ID for the Event. Liferay
provides a counter service which you call to obtain a unique ID for each new
Event entity. It's possible to use the `increment` method of Liferay's
`CounterLocalServiceUtil` class but Service Builder already makes a
`CounterLocalService` instance available to `EventLocalServiceBaseImpl` via
Spring dependency injection. Since your `EventLocalServiceImpl` class extends
`EventLocalServiceBaseImpl`, you can access this `CounterLocalService` instance.
See `EventLocalServiceBaseImpl` for a list of all the beans that Spring makes
available for use. These include the following beans:

- `eventLocalService`
- `eventPersistence`
- `locationLocalService`
- `locationPersistence`
- `counterLocalService`
- `resourceLocalService`
- `resourceService`
- `resourcePersistence`
- `userLocalService`
- `userService`
- `userPersistence`

You can use either the injected class's `increment` method or you can call
Liferay's `CounterLocalService`'s `increment` method directly.

    long eventId = counterLocalService.increment(Event.class.getName());

The Event Listing project uses the generated `eventId` as the ID for the new
Event:

    Event event = eventPersistence.create(eventId);

`eventPersistence` is one of the Spring beans injected into
`EventLocalServiceBaseImpl` by Service Builder. 

Next, the Event Listing project sets the attribute fields that were specified
for the Event entity. First, the name and description of the Event need to be
specified. Then the Event's date needs to be specified. Lastly, a Location must
be associated to the Event. 

It's also important to assign values to the audit fields. In the Event Listing
project, the group of the entity is set first. An entity's group determines its
scope. In the example, the group is the site. The company and user are specified
after the group is set. The company represents the portal instance and the user
is the user who created the event. The Event Listing project sets the
`createDate` and `modifiedDate` of the Event to the current time. After that,
the generated `addEvent` method of `EventLocalServiceBaseImpl` is called to add
the Event to the database. Lastly, the Event is added as a resource so that
permissions can be applied to it later. For more information about adding
resources, please see the
[Asset Enabling Custom Entities](https://dev.liferay.com/develop/learning-paths/-/knowledge_base/6-2/asset-enabling-custom-entities)
learning path. 

The Event Listing project creates local services for Location entities as well
as for Event entities. Here are the custom service methods that were added to
the `LocationLocalServiceImpl` class in the Event Listing project:

    public Location addLocation(
            long userId, long groupId, String name, String description,
            String streetAddress, String city, String stateOrProvince,
            String country, ServiceContext serviceContext)
    throws PortalException, SystemException {

        User user = userPersistence.findByPrimaryKey(userId);

        Date now = new Date();

        long locationId =
            counterLocalService.increment(Location.class.getName());

        Location location = locationPersistence.create(locationId);

        location.setName(name);
        location.setDescription(description);
        location.setStreetAddress(streetAddress);
        location.setCity(city);
        location.setStateOrProvince(stateOrProvince);
        location.setCountry(country);

        location.setGroupId(groupId);
        location.setCompanyId(user.getCompanyId());
        location.setUserId(user.getUserId());
        location.setCreateDate(serviceContext.getCreateDate(now));
        location.setModifiedDate(serviceContext.getModifiedDate(now));

        super.addLocation(location);

        return location;
    }

    public Location deleteLocation(Location location)
        throws SystemException {

        return locationPersistence.remove(location);
    }

    public Location deleteLocation(long locationId)
        throws PortalException, SystemException {

        Location location = locationPersistence.fetchByPrimaryKey(locationId);

        return deleteLocation(location);
    }

    public List<Location> getLocationsByGroupId(long groupId)
        throws SystemException {

        return locationPersistence.findByGroupId(groupId);
    }

    public List<Location> getLocationsByGroupId(
            long groupId, int start, int end)
        throws SystemException {

        return locationPersistence.findByGroupId(groupId, start, end);
    }

    public int getLocationsCountByGroupId(long groupId) throws SystemException {

        return locationPersistence.countByGroupId(groupId);
    }

    public Location updateLocation(
            long userId, long locationId, String name, String description,
            String streetAddress, String city, String stateOrProvince,
            String country, ServiceContext serviceContext)
        throws PortalException, SystemException {

        User user = userPersistence.findByPrimaryKey(userId);

        Date now = new Date();

        Location location = locationPersistence.findByPrimaryKey(locationId);

        location.setName(name);
        location.setDescription(description);
        location.setStreetAddress(streetAddress);
        location.setCity(city);
        location.setStateOrProvince(stateOrProvince);
        location.setCountry(country);
        location.setModifiedDate(serviceContext.getModifiedDate(now));

        super.updateLocation(location);

        return location;
    }

`LocationLocalServiceImpl` uses the following imports:

    import java.util.Date;
    import java.util.List;

    import com.liferay.portal.kernel.exception.PortalException;
    import com.liferay.portal.kernel.exception.SystemException;
    import com.liferay.portal.model.User;
    import com.liferay.portal.service.ServiceContext;

    import com.liferay.docs.eventlisting.model.Location;
    import com.liferay.docs.eventlisting.service.base.LocationLocalServiceBaseImpl;

Before you can use any custom methods that you added to your `*LocalServiceImpl`
class, you must run Service Builder again. Running Service Builder again adds
the method signatures of your custom service methods to your `*LocalService`
interface and updates your `*LocalServiceUtil` class.

***Using Developer Studio:*** To run Service Builder from Liferay IDE or
Developer Studio, open your `service.xml` file and make sure you are in the
*Overview* mode. Then, select *Build Services*.

Alternatively, right-click on your project in the Package Explorer and then
select *Liferay* &rarr; *Build Services* (or, equivalently, *Liferay* &rarr;
*SDK* &rarr; *build-service*).

***Using the terminal:*** Navigate to your portlet's directory in your Plugins
SDK, and run the following command:

    ant build-service

Service Builder looks through your `*LocalServiceImpl` class and automatically
copies the signatures of each method into the corresponding `*LocalService`
interface. After running Service Builder, you can test that your services are
working as intended by invoking one the methods that Service Builder added to
your `*LocalServiceUtil` class. For example, if you were developing the Event
Listing project, you could make the following service invocation to make sure
that your service was working as intended:

    EventLocalServiceUtil.addEvent(event);

Service Builder generates methods in your `*LocalServiceUtil` class that
correspond to the methods you implemented in your `*LocalServiceImpl` class. The
utility service methods invoke the implementation service methods that you
wrote. In addition to all of the Java classes and interfaces, Service Builder
also generates a `service.properties` file. To learn about the
`service.properties` file and how to configure it, please refer to the [Configuring `service.properties`](https://dev.liferay.com/develop/tutorials/-/knowledge_base/6-2/configuring-service.properties)
tutorial. To learn how to invoke local services, please refer to the
[Invoking Local Services](https://dev.liferay.com/develop/tutorials/-/knowledge_base/6-2/invoking-local-services)
tutorial. 
