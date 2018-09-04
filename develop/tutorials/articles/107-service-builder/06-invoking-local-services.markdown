# Invoking Local Services [](id=invoking-local-services)

Once Service Builder has generated your portlet project's services, you can call
them from anywhere in your application. For example, you can invoke services
from your application's `*Portlet` classes or from your application's view layer
(JSPs, Freemarker templates, or Velocity templates). Remember that when invoking
a service, you should always call one of the static service methods of a `*Util`
class. You should never call the service methods of an `*Impl` class directly.

In the Event Listing project, for example, the Event Listing portlet needs to
perform create, read, update, and delete (CRUD) operations on Events. The
Location Listing portlet needs to perform CRUD operations on Locations. To
facilitate these operations, the following methods were added to the
`EventListingPortlet` class:

- `addEvent`
- `updateEvent`
- `deleteEvent`

These portlet methods invoke the location service methods responsible for
adding, updating, and deleting Event entities. Similar portlet methods were
added to the `LocationListingPortlet` class.

Here's a version of the `EventListingPortlet` class containing these portlet
methods and their service invocations. Note that this version of the
`EventListingPortlet` differs from the
[final version](https://github.com/liferay/liferay-docs/tree/6.2.x/develop/tutorials/code/tutorials-sdk/portlets/event-listing-portlet/docroot/WEB-INF/src/com/liferay/docs/eventlisting/portlet/EventListingPortlet.java)
since this portlet invokes local services and the final version of the portlet
invokes remote services.

    package com.liferay.docs.eventlisting.portlet;
    
    import java.util.Calendar;
    
    import javax.portlet.ActionRequest;
    import javax.portlet.ActionResponse;
    
    import com.liferay.docs.eventlisting.model.Event;
    import com.liferay.docs.eventlisting.service.EventLocalServiceUtil;
    import com.liferay.portal.kernel.exception.PortalException;
    import com.liferay.portal.kernel.exception.SystemException;
    import com.liferay.portal.kernel.log.Log;
    import com.liferay.portal.kernel.log.LogFactoryUtil;
    import com.liferay.portal.kernel.util.ParamUtil;
    import com.liferay.portal.service.ServiceContext;
    import com.liferay.portal.service.ServiceContextFactory;
    import com.liferay.util.bridges.mvc.MVCPortlet;
    
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

The Event Listing Portlet's `addEvent`, `updateEvent`, and `deleteEvent`
methods call the appropriate methods from `EventLocalServiceUtil`. Liferay's
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

![Figure 1: The `EventListingPortlet` class can access these methods of `EventLocalServiceUtil`, many of which are CRUD operations.](../../images/local-service-util-outline.png)

Portlet classes should have access only to the `*LocalServiceUtil` (and
`*ServiceUtil`, in the case of remote services) classes. The `*LocalServiceUtil`
classes, in turn, call their injected `*LocalServiceImpl` classes. Notice in the
figure above that the `EventLocalServiceUtil` utility class has a private
instance variable called `_service`. The `_service` instance variable of type
`EventLocalService` gets an instance of `EventLocalServiceImpl` at runtime via
dependency injection. So all the methods of the `EventLocalServiceUtil` utility
class internally call corresponding methods of the `EventLocalServiceImpl` class
at runtime. 

The `LocationListingPortlet` class contains methods that are very similar to the
ones in the `EventListingPortlet` class:

    package com.liferay.docs.eventlisting.portlet;

    import javax.portlet.ActionRequest;
    import javax.portlet.ActionResponse;

    import com.liferay.docs.eventlisting.model.Location;
    import com.liferay.docs.eventlisting.service.LocationLocalServiceUtil;
    import com.liferay.portal.kernel.exception.PortalException;
    import com.liferay.portal.kernel.exception.SystemException;
    import com.liferay.portal.kernel.log.Log;
    import com.liferay.portal.kernel.log.LogFactoryUtil;
    import com.liferay.portal.kernel.util.ParamUtil;
    import com.liferay.portal.service.ServiceContext;
    import com.liferay.portal.service.ServiceContextFactory;
    import com.liferay.util.bridges.mvc.MVCPortlet;

    public class LocationListingPortlet extends MVCPortlet {

        public void addLocation(ActionRequest request, ActionResponse response)
                throws Exception {

            _updateLocation(request);

            sendRedirect(request, response);
        }

        public void deleteLocation(ActionRequest request, ActionResponse response)
            throws Exception {

            long locationId = ParamUtil.getLong(request, "locationId");

            LocationLocalServiceUtil.deleteLocation(locationId);

            sendRedirect(request, response);
        }

        public void updateLocation(ActionRequest request, ActionResponse response)
            throws Exception {

            _updateLocation(request);

            sendRedirect(request, response);
        }

        private Location _updateLocation(ActionRequest request)
                throws PortalException, SystemException {

            long locationId = (ParamUtil.getLong(request, "locationId"));
            String name = (ParamUtil.getString(request, "name"));
            String description = (ParamUtil.getString(request, "description"));
            String streetAddress = (ParamUtil.getString(request, "streetAddress"));
            String city = (ParamUtil.getString(request, "city"));
            String stateOrProvince = (ParamUtil.getString(request, "stateOrProvince"));
            String country = (ParamUtil.getString(request, "country"));

            ServiceContext serviceContext = ServiceContextFactory.getInstance(
                    Location.class.getName(), request);

            Location location = null;

            if (locationId <= 0) {

                location = LocationLocalServiceUtil.addLocation(
                    serviceContext.getUserId(), serviceContext.getScopeGroupId(), name, description,
                    streetAddress, city, stateOrProvince, country, serviceContext);
            }
            else {
                location = LocationLocalServiceUtil.getLocation(locationId);
    
                location = LocationLocalServiceUtil.updateLocation(
                        serviceContext.getUserId(), locationId, name,
                        description, streetAddress, city, stateOrProvince, country,
                        serviceContext);
            }

            return location;
        }

        private static Log _log = LogFactoryUtil.getLog(LocationListingPortlet.class);

    }

These examples demonstrate how you can call the local services generated by
Service Builder in your project's `*Portlet` classes. To learn how to call
Liferay services, please see the
[Service Security Layers](/develop/tutorials/-/knowledge_base/6-2/service-security-layers)
and
[Finding and Invoking Liferay Services](/develop/tutorials/-/knowledge_base/6-2/finding-and-invoking-liferay-services)
tutorials.

## Related Topics [](id=related-topics)

[Creating Local Services](/develop/tutorials/-/knowledge_base/6-2/writing-local-service-classes)

[Creating Remote Services](/develop/tutorials/-/knowledge_base/6-2/creating-remote-services)

[Invoking Remote Services](/develop/tutorials/-/knowledge_base/6-2/invoking-remote-services)

[Invoking Liferay Services in your Android App](/develop/tutorials/-/knowledge_base/6-2/invoking-liferay-services-in-your-android-app)

[Invoking Liferay Services in your iOS App](/develop/tutorials/-/knowledge_base/6-2/invoking-liferay-services-in-your-ios-app)

