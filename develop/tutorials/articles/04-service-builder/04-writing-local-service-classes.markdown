# Writing Local Service Classes [](id=writing-local-service-classes)

The heart of your service is its `-LocalServiceImpl` class, where you put core
business logic for working with your model. Throughout this chapter, you've been
constructing services for the Nose-ster Event Listing example portlet project.
Start with your services by examining the initial service classes Service
Builder generated for it. 

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

Remember to import the required classes. These include the following:

    com.nosester.portlet.eventlisting.model.Event
    com.liferay.portal.kernel.exception.PortalException
    com.liferay.portal.kernel.exception.SystemException
    com.liferay.portal.model.User
    com.liferay.portal.service.ServiceContext
    java.util.Date
    java.util.List

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

It's a best practice to use the injected class's `increment` method rather than
calling Liferay's `CounterLocalService`'s `increment` method since using the
injected class does not require an extra database transaction. Calling Liferay's
`CounterLocalServiceUtil` class, on the other hand, does require an extra
database transaction. 

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
later. We'll cover the details of adding resources in Chapter 12. 

Before you can use any custom methods that you added to `EventLocalServiceImpl`
class, you must add their signatures to the `EventLocalService` interface by
running Service Builder again.

***Using Developer Studio:*** As we did before, open your `service.xml` file and
make sure you are in the *Overview* mode. Then, select *Build Services*.

***Using the terminal:*** Navigate to the root directory of your portlet in the
terminal and run:

    ant build-service

Service Builder looks through `EventLocalServiceImpl` and automatically copies
the signatures of each method into the interface. You can now add a new Event to
the database by invoking the static `addEvent` method that Service Builder
generated in the `EventLocalServiceUtil` utility class. In addition to all the
Java classes, Service Builder also generates a `service.properties` file which
will be covered later. Next, let's call our newly implemented local service. 
