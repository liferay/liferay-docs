# Working with URLs in JavaScript [](id=working-with-urls-in-javascript)

In Java, developers are able to create and work with URLs using their APIs. The `Liferay` global object offers some features to help you create and work with URLs.

This tutorial covers some of the most commonly used methods inside the `Liferay` global JavaScript object to manipulate URLs.

## Liferay.PortletURL [](id=liferay-portlet-url)

The `Liferay.PortletURL` class provides a way to create Liferay PortletURL's such as `actionURL`, `renderURL`, and `resourceURL` through JavaScript. Here is an example:

```javascript
var portletURL = Liferay.PortletURL.createURL(themeDisplay.getURLControlPanel());

portletURL.setDoAsGroupId('true');
portletURL.setLifecycle(Liferay.PortletURL.ACTION_PHASE);
portletURL.setParameter('cmd', 'add_temp');
portletURL.setParameter('javax.portlet.action', '/document_library/upload_file_entry');
portletURL.setParameter('p_auth', Liferay.authToken);
portletURL.setPortletId(Liferay.PortletKeys.DOCUMENT_LIBRARY);
```

## Liferay.authToken [](id=liferay-auth-token)

```javascript
Liferay.authToken = '<%= AuthTokenUtil.getToken(request) %>';
```

## Liferay.currentURL [](id=liferay-current-url)

The `Liferay.currentURL` variable holds the path of the current URL from the server root.

For example, if checked from `my.domain.com/es/web/guest/home`, the value will be `/es/web/guest/home`

```javascript
// Inside my.domain.com/es/web/guest/home
console.log(Liferay.currentURL); // "/es/web/guest/home"
```

## Liferay.currentURLEncoded [](id=liferay-current-url-encoded)


The `Liferay.currentURLEncoded` variable holds the path of the current URL from the server root encoded in ACII for safe transmission over the Internet.

For example, if checked from `my.domain.com/es/web/guest/home`, the value will be `%2Fes%2Fweb%2Fguest%2Fhome`

```javascript
// Inside my.domain.com/es/web/guest/home
console.log(Liferay.currentURLEncoded); // "%2Fes%2Fweb%2Fguest%2Fhome"
```