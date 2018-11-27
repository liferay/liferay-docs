# Working with URLs in JavaScript [](id=working-with-urls-in-javascript)

The `Liferay` global JavaScript Object exposes methods, objects, and properties
that access the portal context. Four of these are helpful when working with
URLS: `authToken`, `currentURL`, `currentURLEncoded`, and `PortletURL`. If you
have experience with Java development in @product@, you may have worked with
some of these before. The `Liferay` global object is automatically available at
runtime, so no additional dependencies are required. 

This tutorial covers how to use the `Liferay` global JavaScript object to
manipulate URLs. A complete list of the available methods and properties appears
in the tables at the end of this tutorial. 

## Liferay PortletURL [](id=liferay-portlet-url)

The `Liferay.PortletURL` object provides methods for creating portlet API URLs
(`actionURL`, `renderURL`, and `resourceURL`), through JavaScript. Below is an
example configuration:

    var portletURL = Liferay.PortletURL.createURL(themeDisplay.getURLControlPanel());

    portletURL.setDoAsGroupId('true');
    portletURL.setLifecycle(Liferay.PortletURL.ACTION_PHASE);
    portletURL.setParameter('cmd', 'add_temp');
    portletURL.setParameter('javax.portlet.action', '/document_library/upload_file_entry');
    portletURL.setParameter('p_auth', Liferay.authToken);
    portletURL.setPortletId(Liferay.PortletKeys.DOCUMENT_LIBRARY);

See the 
[Portlet URL Methods and Properties](#portlet-url-methods-and-properties) 
section for more information about the methods and properties used in the 
example above. 

## Liferay AuthToken [](id=liferay-auth-token)

The `Liferay.authToken` property holds the current authentication token value as 
a String. Below is an example configuration for the `Liferay.authToken` property:

    Liferay.authToken = '<%= AuthTokenUtil.getToken(request) %>';

## Liferay CurrentURL [](id=liferay-current-url)

The `Liferay.currentURL` property holds the path of the current URL from the 
server root.

For example, if checked from `my.domain.com/es/web/guest/home`, the value is 
`/es/web/guest/home`, as shown below:

    // Inside my.domain.com/es/web/guest/home
    console.log(Liferay.currentURL); // "/es/web/guest/home"

## Liferay CurrentURLEncoded [](id=liferay-current-url-encoded)

The `Liferay.currentURLEncoded` property holds the path of the current URL, 
encoded in ASCII for safe transmission over the Internet, from the server root. 

For example, if checked from `my.domain.com/es/web/guest/home`, the value is 
`%2Fes%2Fweb%2Fguest%2Fhome`, as shown below:

    // Inside my.domain.com/es/web/guest/home
    console.log(Liferay.currentURLEncoded); // "%2Fes%2Fweb%2Fguest%2Fhome"

## Portlet URL Methods and Properties [](id=portlet-url-methods-and-properties)

`Liferay.PortletURL` Methods:

| Method | Parameters | Returns |
| --- | --- | --- |
| `createURL` | `basePortletURL`, `params` | new `PortletURL(null, params, basePortletURL);` |
| `createActionURL` |  | new `PortletURL(PortletURL.ACTION_PHASE`); |
| `createRenderURL` |  | new `PortletURL(PortletURL.RENDER_PHASE`); |
| `createResourceURL` |  | new `PortletURL(PortletURL.RESOURCE_PHASE`); |

`Liferay.PortletURL` Properties:

| Property | Value |
| --- | --- |
| `ACTION_PHASE` | "1" |
| `RENDER_PHASE` | "0" |
| `RESOURCE_PHASE` | "2" |

Once the portlet URL is created, you have access to several methods that you can 
use to manipulate the URL further:

| Method | Description | Parameters | Returns |
| --- | --- | --- | --- |
| `setDoAsGroupId` | Sets the ID of the site, organization, or user group for the URL | `doAsGroupId` | The updated Portlet URL Object |
| `setDoAsUserId` | Sets the ID of the user to impersonate | `doAsUserId` | The updated Portlet URL Object |
| `setEscapeXML` | Sets whether the URL should be XML escaped | `true` or `false` | The updated Portlet URL Object |
| `setLifecycle` | Sets the portlet lifecycle of this URL's target portlet | `lifecycle` | The updated Portlet URL Object |
| `setName` | sets the portlet URL's `javax.portlet.action` name | `name` | The updated Portlet URL Object |
| `setParameter` | Creates an individual parameter or replaces an existing reserved parameter | key,value | The updated Portlet URL Object |
| `setParameters` | Creates multiple parameters and/or replaces existing reserved parameters | {key:value,...} | The updated Portlet URL Object |
| `setPlid` | Sets the portlet layout ID | `plid` | The updated Portlet URL Object |
| `setPortletId` | Sets the ID of the target portlet | `portletId` | The updated Portlet URL Object |
| `setPortletMode` | Sets the portlet mode, if the URL triggers a request | `portletMode` | The updated Portlet URL Object |
| `setResourceId` | Sets the ID of the URL's target resource | `ResourceId` | The updated Portlet URL Object |
| `setSecure` | Sets whether to make the URL secure (HTTPS). | `true` or `false` | The updated Portlet URL Object |
| `setWindowState` | Sets the portlet's window state, if the URL triggers a request | `windowState` | The updated Portlet URL Object |
| `toString` | Returns the URL as a String |  | The portlet URL as a String |
| `_isReservedParam` | Returns whether the parameter is reserved | `paramName` | `true` if the parameter is reserved |
 
Now you know how to manipulate URLs using methods within the `Liferay` global 
JavaScript object. 

## Related Topics [](id=related-topics)

[@product@ JavaScript Utilities](/develop/tutorials/-/knowledge_base/7-1/javascript-utilities)

[Liferay Theme Display](/develop/tutorials/-/knowledge_base/7-1/liferay-themedisplay)
