---
header-id: liferay-portlet-objects-available-in-jsps
---

# Liferay Portlet Objects Available in JSPs

[TOC levels=1-4]

You may have noticed the `<liferay-portlet:defineObjects>` tag in your JSPs. 
Similar to the [theme:defineObjects](/docs/7-2/reference/-/knowledge_base/r/liferay-theme-objects-available-in-jsps) 
tag, when you include this tag in your JSP, you gain access to several variables 
that, in this case, return useful information about your portlet. Note that the 
JSR-286 specification defines four lifecycle methods for a portlet: 
processAction, processEvent, render, and serveResource. Some of the variables 
defined by the `<portlet:defineObjects/>` tag are only available to a JSP if the 
JSP was included during the appropriate phase of the portlet lifecycle. These 
objects are described in the table below:

| Object | Description |
| ------ | ----------- |
| `ActionRequest actionRequest` | Represents the request sent to the portlet to handle an action. `actionRequest` is only available to a JSP if the JSP was included during the action-processing phase. |
| `ActionResponse actionResponse` | Represents the portlet response to an action request. `actionResponse` is only available to a JSP if the JSP was included in the action-processing phase. |
| `EventRequest eventRequest` | Represents the request sent to the portlet to handle an event. `eventRequest` is only available to a JSP if the JSP was included during the event-processing phase. |
| `EventResponse eventResponse` | Represents the portlet response to an event request. `eventResponse` is only available to a JSP if the JSP was included in the event-processing phase. |
| `HeaderRequest headerRequest` | Represents the request sent to the portlet to handle its HTML header or HEAD section. `headerRequest` is only available to a JSP if the JSP was included during the header-processing phase. |
| `HeaderResponse headerResponse` | Represents the portlet response to a header request. `headerResponse` is only available to a JSP if the JSP was included in the header-processing phase. |
| [`LiferayPortletRequest liferayPortletRequest`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/LiferayPortletRequest.html) | Provides access to the `HttpServletRequest`, the `Portlet`, and the portlet name and lifecycle value. `liferayPortletRequest` is available in all portlet phases. |
| [`LiferayPortletResponse liferayPortletResponse`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/LiferayPortletResponse.html) | Includes the properties returned to the portal and provides a means to add or change properties. `liferayPortletResponse` is available in all portlet phases. |
| `RenderRequest renderRequest` | Represents the request sent to the portlet to render the portlet. `renderRequest` is only available to a JSP if the JSP was included during the render request phase. |
| `RenderResponse renderResponse` | Represents an object that assists the portlet in sending a response to the portal. `renderResponse` is only available to a JSP if the JSP was included during the render request phase. |
| `ResourceRequest resourceRequest` | Represents the request sent to the portlet for rendering resources. `resourceRequest` is only available to a JSP if the JSP was included during the resource-serving phase. |
| `ResourceResponse resourceResponse` | Represents an object that assists the portlet in rendering a resource. `resourceResponse` is only available to a JSP if the JSP was included in the resource-serving phase. |
| `PortletConfig portletConfig` | Represents the portlet's configuration including, the portlet's name, initialization parameters, resource bundle, and application context. `portletConfig` is always available to a portlet JSP, regardless of the request-processing phase in which it was included. |
| `PortletPreferences portletPreferences` | Provides access to a portlet's preferences. `portletPreferences` is always available to a portlet JSP, regardless of the request-processing phase in which it was included. |
| `Map<String, String[]> portletPreferencesValues` | Provides a Map equivalent to the `portletPreferences.getMap()` call or an empty Map if no portlet preferences exist. |
| `PortletSession portletSession` | Provides a way to identify a user across more than one request and to store transient information about a user. A `portletSession` is created for each user client. `portletSession` is always available to a portlet JSP, regardless of the request-processing phase in which it was included. `portletSession` is `null` if no session exists. |
| `Map<String, Object> portletSessionScope` | Provides a Map equivalent to the `PortletSession.getAtrributeMap()` call or an empty Map if no session attributes exist. |

For more details, visit the
[Portlet 3.0 API Javadoc](https://docs.liferay.com/portlet-api/3.0/javadocs/). 
