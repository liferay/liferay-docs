---
header-id: working-with-urls-in-javascript
---

# Working with URLs in JavaScript

In Java, developers are able to create and work with URLs using their APIs. The 
`Liferay` global object offers some features to help you create and work with 
URLs.

This tutorial covers some of the most commonly used methods inside the 
`Liferay` global JavaScript object to manipulate URLs.

## Liferay PortletURL

The `Liferay.PortletURL` class provides a way to create Liferay PortletURL's 
such as the `actionURL`, `renderURL`, and `resourceURL` through JavaScript.
Below is an example configuration:

    var portletURL = Liferay.PortletURL.createURL(themeDisplay.getURLControlPanel());
    
    portletURL.setDoAsGroupId('true');
    portletURL.setLifecycle(Liferay.PortletURL.ACTION_PHASE);
    portletURL.setParameter('cmd', 'add_temp');
    portletURL.setParameter('javax.portlet.action', '/document_library/upload_file_entry');
    portletURL.setParameter('p_auth', Liferay.authToken);
    portletURL.setPortletId(Liferay.PortletKeys.DOCUMENT_LIBRARY);

## Liferay AuthToken

Below is an example configuration for the `Liferay.authToken`:

    Liferay.authToken = '<%= AuthTokenUtil.getToken(request) %>';

## Liferay CurrentURL

The `Liferay.currentURL` variable holds the path of the current URL from the 
server root.

For example, if checked from `my.domain.com/es/web/guest/home`, the value is
`/es/web/guest/home`, as shown below:

    // Inside my.domain.com/es/web/guest/home
    console.log(Liferay.currentURL); // "/es/web/guest/home"

## Liferay CurrentURLEncoded

The `Liferay.currentURLEncoded` variable holds the path of the current URL from 
the server root encoded in ASCII for safe transmission over the Internet.

For example, if checked from `my.domain.com/es/web/guest/home`, the value is
`%2Fes%2Fweb%2Fguest%2Fhome`, as shown below:

    // Inside my.domain.com/es/web/guest/home
    console.log(Liferay.currentURLEncoded); // "%2Fes%2Fweb%2Fguest%2Fhome"
 
Now you know how to manipulate URLs using methods within the `Liferay` global 
JavaScript object.

## Related Topics

[@product@ JavaScript Utilities](/docs/7-0/tutorials/-/knowledge_base/t/javascript-utilities)

[Liferay Theme Display](/docs/7-0/tutorials/-/knowledge_base/t/liferay-themedisplay)
