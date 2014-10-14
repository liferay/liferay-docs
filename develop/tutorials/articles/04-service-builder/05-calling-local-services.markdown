# Calling Local Services [](id=calling-local-services)

Once Service Builder has generated your portlet project's services, you can call
them in our project's `-Portlet` classes. You can call any methods in your
`EventLocalServiceUtil` or `LocationLocalServiceUtil` static utility classes
from `EventListingPortlet` and `LocationListingPortlet`. For example, you
want the Event Listing portlet to perform create, read, update, and delete
(CRUD) operations on Events and the Location Listing portlet to perform CRUD
operations on Locations. To this end, you'll create the following methods for 
`EventListingPortlet` and similar ones for `LocationListingPortlet`:

- `addEvent`
- `updateEvent`
- `deleteEvent`

Replace the contents of `EventListingPortlet.java` with the following code:

    package com.nosester.portlet.eventlisting;
    
    import java.util.Calendar;
    
    import javax.portlet.ActionRequest;
    import javax.portlet.ActionResponse;
    
    import com.liferay.portal.kernel.exception.PortalException;
    import com.liferay.portal.kernel.exception.SystemException;
    import com.liferay.portal.kernel.log.Log;
    import com.liferay.portal.kernel.log.LogFactoryUtil;
    import com.liferay.portal.kernel.util.ParamUtil;
    import com.liferay.portal.service.ServiceContext;
    import com.liferay.portal.service.ServiceContextFactory;
    import com.liferay.util.bridges.mvc.MVCPortlet;
    import com.nosester.portlet.eventlisting.model.Event;
    import com.nosester.portlet.eventlisting.service.EventLocalServiceUtil;
    
    public class EventListingPortlet extends MVCPortlet {
    
        public void addEvent(ActionRequest request, ActionResponse response)
                throws Exception {
    
            _updateEvent(request);
    
            sendRedirect(request, response);
        }
    
        public void deleteEvent(ActionRequest request, ActionResponse response)
            throws Exception {
    
            long eventId = ParamUtil.getLong(request, "eventId");
    
            EventLocalServiceUtil.deleteEvent(eventId);
    
            sendRedirect(request, response);
        }
    
        public void updateEvent(ActionRequest request, ActionResponse response)
            throws Exception {
    
            _updateEvent(request);
    
            sendRedirect(request, response);
        }
    
        private Event _updateEvent(ActionRequest request)
            throws PortalException, SystemException {
    
            long eventId = ParamUtil.getLong(request, "eventId");
            String name = ParamUtil.getString(request, "name");
            String description = ParamUtil.getString(request, "description");
            long locationId = ParamUtil.getLong(request, "locationId");
    
            int year = ParamUtil.getInteger(request, "dateYear");
            int month = ParamUtil.getInteger(request, "dateMonth");
            int day = ParamUtil.getInteger(request, "dateDay");
            int hour = ParamUtil.getInteger(request, "dateHour");
            int minute = ParamUtil.getInteger(request, "dateMinute");
            int amPm = ParamUtil.getInteger(request, "dateAmPm");
    
            if (amPm == Calendar.PM) {
                hour += 12;
            }
    
            ServiceContext serviceContext = ServiceContextFactory.getInstance(
                Event.class.getName(), request);
    
            Event event = null;
    
            if (eventId <= 0) {
                event = EventLocalServiceUtil.addEvent(
                    serviceContext.getUserId(), serviceContext.getScopeGroupId(),
                    name, description, month, day, year, hour, minute, locationId,
                    serviceContext);
            }
            else {
                event = EventLocalServiceUtil.getEvent(eventId);
    
                event = EventLocalServiceUtil.updateEvent(
                    serviceContext.getUserId(), eventId, name, description, month,
                    day, year, hour, minute, locationId, serviceContext);
            }
            
            return event;
        }

        private static Log _log = LogFactoryUtil.getLog(EventListingPortlet.class);
    }

The Event Listing portlet's `addEvent`, `updateEvent`, and `deleteEvent`
methods now call the appropriate methods from `EventLocalServiceUtil`. Liferay's
`ParamUtil` getter methods such as `getLong` and `getString` return default
values like `0` or `""` if the specified request parameter is not available from
the portlet request. When adding a new event, for example, no event ID is
available so `ParamUtil.getLong("request", "eventId")` returns `0`. The Event
portlet's `addEvent` method calls `EventLocalServiceUtil`'s `addEvent` method.
The event ID for the new event is generated at the service layer in the
`addEvent` method that you added to the `EventLocalServiceImpl` class. The
`EventLocalServiceUtil` generated for us by Service Builder contains various
CRUD methods:

- `createEvent`
- `addEvent`
- `deleteEvent`
- `updateEvent`
- `fetchEvent`
- `getEvent`

The methods listed in the figure below are all generated by Service Builder
and can be called by `EventListingPortlet`.

![Figure 4.5: Our `EventListingPortlet` class can access these methods of `EventLocalServiceUtil`, many of which are CRUD operations.](../../images/local-service-util-outline.png)

Portlet classes should have access only to the `-LocalServiceUtil` classes. The
`-LocalServiceUtil` classes, in turn, call their injected `-LocalServiceImpl`
classes. Notice in the figure above that the `EventLocalServiceUtil` utility
class has a private instance variable called `_service`. The `_service` instance
variable of type `EventLocalService` gets an instance of
`EventLocalServiceImpl` at runtime via dependency injection. So all the methods
of the `EventLocalServiceUtil` utility class internally call corresponding
methods of the `EventLocalServiceImpl` class at runtime.

We've demonstrated how to call the local services generated by Service Builder
in our project's `-Portlet` classes. Next, let's learn how to how to call
Liferay's local services.

<!-- The above paragraph would be much more clear if we delineate the various
layers of the app as recommended above. -Rich -->
