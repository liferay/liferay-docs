# Writing Local Service Classes

The heart of your service is its `*LocalServiceImpl` class. This class is your
entity's local service extension point. Local services can be invoked by other
Liferay applications running on the same Liferay instance as your application.
Don't confuse local services with remote services. Remote services can be
invoked from any application that can access your Liferay instance (e.g., over
the internet) and has permission to do so. All of your application's core
business logic for working with your entity model (or models) should be added as
methods of your `*LocalServiceImpl` class. However, before adding any custom
service methods, you should review the initial service classes that Service
Builder generated during its initial run. 

**Best Practice:** If your application needs both local and remote services, add
whatever local service methods you need to implement your business logic for
working with your entity model to `*LocalServiceImpl`. Then create corresponding
remote services methods to `*ServiceImpl`. Add permission checks to the remote
services methods and make them call the local service methods. The remote
service methods can have the same names as the local service methods that they
call.

Note that Service Builder created an `EventLocalService` class which is the
interface for the local service. It contains the signatures of every method in
`EventLocalServiceBaseImpl` and `EventLocalServiceImpl`.
`EventLocalServiceBaseImpl` contains a few automatically generated methods
providing common functionality. Since the `EventLocalService` class is
generated, you should never modify it. If you do, your changes will be
overwritten the next time you run Service Builder. Instead, all custom code
should be placed in `EventLocalServiceImpl`.

Open the `EventLocalServiceImpl.java` file in your
`/docroot/WEB-INF/src/com/nosester/portlet/eventlisting/service/impl/` folder.

Add the following database interaction methods to the `EventLocalServiceImpl`
class:

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

Remember to import the required classes. For your convenience, you can copy the
following imports into your class: 

    import java.util.Calendar;
    import java.util.Date;
    import java.util.List;

    import com.liferay.portal.kernel.exception.PortalException;
    import com.liferay.portal.kernel.exception.SystemException;
    import com.liferay.portal.kernel.util.CalendarFactoryUtil;
    import com.liferay.portal.model.User;
    import com.liferay.portal.service.ServiceContext;

    import com.nosester.portlet.eventlisting.model.Event;
    import com.nosester.portlet.eventlisting.service.EventLocalServiceUtil;

In order to add an Event to the database, you need an ID for the Event. Liferay
provides a counter service which you call to obtain a unique ID for each new
Event entity. It's possible to use the `increment` method of Liferay's
`CounterLocalServiceUtil` class but Service Builder already makes a
`CounterLocalService` instance available to `EventLocalServiceBaseImpl` via
Spring by dependency injection. Since your `EventLocalServiceImpl` class extends
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

We use the generated `eventId` as the ID for the new Event:

    Event event = eventPersistence.create(eventId);

`eventPersistence` is one of the Spring beans injected into
`EventLocalServiceBaseImpl` by Service Builder. 

Next, we set the attribute fields that we specified for the Event. First, we set
the name and description of the Event. Then we use the date and time values to
construct the Event's date. Lastly, we associate a location with the Event. 

Then we assign values to the audit fields. First, we set the group, or scope, of
the entity. In this case the group is the site. Then we set the company and
user. The company represents the portal instance. We set the `createDate` and
`modifiedDate` of our Event to the current time. After that, we call the
generated `addEvent` method of `EventLocalServiceBaseImpl` with our Event.
Lastly, we add the Event as a resource so that we can apply permissions to it
later. We'll cover the details of adding resources in the [Asset
Framework](http://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/asset-framework-liferay-portal-6-2-dev-guide-06-en)
section. 

Since events require event locations, let's implement the local services for the
Location entity, too. Open your `LocationLocalServiceImpl.java` file in your
`/docroot/WEB-INF/src/com/nosester/portlet/eventlisting/service/impl/` folder
and add the following methods:  

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

Make sure to add the following imports:

    import java.util.Date;
    import java.util.List;

    import com.liferay.portal.kernel.exception.PortalException;
    import com.liferay.portal.kernel.exception.SystemException;
    import com.liferay.portal.model.User;
    import com.liferay.portal.service.ServiceContext;

    import com.nosester.portlet.eventlisting.model.Location;
    import com.nosester.portlet.eventlisting.service.base.LocationLocalServiceBaseImpl;

Your local service implementations for events and locations are ready
for action. 

Before you can use any custom methods that you added to the
`EventLocalServiceImpl` and `LocationLocalServiceImpl` classes, you must add
their signatures to the `EventLocalService` and `LocationLocalService`
interfaces by running Service Builder again.

***Using Developer Studio:*** As we did before, open your `service.xml` file and
make sure you are in the *Overview* mode. Then, select *Build Services*.

***Using the terminal:*** Navigate to the root directory of your portlet in the
terminal and run:

    ant build-service

Service Builder looks through `EventLocalServiceImpl` and automatically copies
the signatures of each method into the interface. You can now add a new Event to
the database by making the following call:

    EventLocalServiceUtil.addEvent(event);

Service Builder generates the `addEvent` method in the `EventLocalServiceUtil`
utility class. In addition to all the Java classes, Service Builder also
generates a `service.properties` file which we'll discuss later. Next, let's
call our newly implemented local service. 

