---
header-id: working-with-urls-in-javascript
---

# Working with URLs in JavaScript

[TOC levels=1-4]

The `Liferay` global JavaScript Object exposes methods, objects, and properties 
that access the portal context. Four of these are helpful when working with 
URLS: `authToken`, `currentURL`, `currentURLEncoded`, and `PortletURL`. If you 
have experience with Java development in @product@, you may have worked with 
some of these before. The `Liferay` global object is automatically available at 
runtime, so no additional dependencies are required. 

| **Note:** Since Liferay DXP SP1 and Liferay Portal CE 7.2 GA2, the 
| `Liferay.PortletURL` utilities are deprecated and have been replaced with 
| `Liferay.Util.PortletURL` utilities. We recommend that you use the updated 
| versions to ensure future compatibility. The examples below use the updated 
| utilities.

This covers how to use the `Liferay` global JavaScript object to manipulate 
URLs. A list of the available methods and properties appears in the tables shown 
below. Example configurations are shown below the tables. 

## Portlet URL Methods and Properties

`Liferay.Util.PortletURL` Methods:

| Method | Parameters | Returns |
| --- | --- | --- |
| `createPortletURL` | `basePortletURL`, `parameters` | A portlet URL as a [URL](https://url.spec.whatwg.org/#api) object |
| `createActionURL` | `basePortletURL`, `parameters` | A portlet URL as a [URL](https://url.spec.whatwg.org/#api) object |
| `createRenderURL` | `basePortletURL`, `parameters` | A portlet URL as a [URL](https://url.spec.whatwg.org/#api) object |
| `createResourceURL` | `basePortletURL`, `parameters` | A portlet URL as a [URL](https://url.spec.whatwg.org/#api) object |

You have access to several util argument properties by default that you can pass 
as parameters in the configuration object for the Portlet URL. These properties 
are listed in the table below:

| Parameters | Description |
| --- | --- |
| `doAsGroupId` | Sets the ID of the site, organization, or user group for the URL |
| `doAsUserId` | Sets the ID of the user to impersonate |
| `doAsUserLanguageId` | Sets the ID of the user to impersonate |
| `p_auth` | Sets the URL's authorization token  |
| `p_auth_secret` |  |
| `p_f_id` |  |
| `p_j_a_id` |  |
| `p_l_id` | Sets the URL's portlet layout ID |
| `p_l_reset` |  |
| `p_p_auth` |  |
| `p_p_cacheability` |  |
| `p_p_i_id` |  |
| `p_p_id` | Sets the URL's portlet ID |
| `p_p_isolated` |  |
| `p_p_lifecycle` |  |
| `p_p_mode` | Sets the portlet mode, if the URL triggers a request |
| `p_p_resource_id` |  |
| `p_p_state` |  |
| `p_p_state_rcv` |  |
| `p_p_static` |  |
| `p_p_url_type` |  |
| `p_p_width` |  |
| `p_t_lifecycle` |  |
| `p_v_l_s_g_id` |  |

## Liferay Util PortletURL

`Liferay.Util.PortletURL` provides APIs for creating portlet URLs 
(`actionURL`, `renderURL`, and `resourceURL`) with JavaScript in your JSPs. 
Below is an example configuration for a JSP:

```markup
var basePortletURL = 'https://localhost:8080/group/control_panel/manage?p_p_id=com_liferay_roles_admin_web_portlet_RolesAdminPortlet';

var actionURL = Liferay.Util.PortletURL.createActionURL(
  basePortletURL,
  {
    'javax.portlet.action': 'addUser',
    foo: 'bar'
  }  
);

console.log(actionURL.toString());
// https://localhost:8080/group/control_panel/manage?p_p_id=com_liferay_roles_admin_web_portlet_RolesAdminPortlet&javax.portlet.action=addUser&com_liferay_roles_admin_web_portlet_RolesAdminPortlet_foo=bar&p_p_lifecycle=1
```
The same API is available as a module for use in your JavaScript files. The ES6 
example below uses the `createActionURL` module:

```javascript
import {createActionURL} from 'frontend-js-web';

var basePortletURL = 'https://localhost:8080/group/control_panel/manage?p_p_id=com_liferay_roles_admin_web_portlet_RolesAdminPortlet';

var actionURL = createActionURL(
  basePortletURL,
  {
    'p_p_id': Liferay.PortletKeys.DOCUMENT_LIBRARY,
    foo: 'bar'
  }  
);
```

See the [Portlet URL Methods and Properties](#portlet-url-methods-and-properties) 
section for more information about the methods and properties used in the 
example above. 

## Liferay AuthToken

The `Liferay.authToken` property holds the current authentication token value as 
a String. The `authToken` is used to validate permissions when you make calls to 
services. To use the `authToken` in a URL, pass `Liferay.authToken` as the URL's 
`p_auth` parameter, as shown in the example below:

```javascript
import {createActionURL} from 'frontend-js-web';

var basePortletURL = 'https://localhost:8080/group/control_panel/manage?p_p_id=com_liferay_roles_admin_web_portlet_RolesAdminPortlet';

var actionURL = createActionURL(
  basePortletURL,
  {
    'p_auth': Liferay.authToken
  }  
);
```

## Liferay CurrentURL

The `Liferay.currentURL` property holds the path of the current URL from the 
server root.

For example, if checked from `my.domain.com/es/web/guest/home`, the value is 
`/es/web/guest/home`, as shown below:

```javascript
// Inside my.domain.com/es/web/guest/home
console.log(Liferay.currentURL); // "/es/web/guest/home"
```

## Liferay CurrentURLEncoded

The `Liferay.currentURLEncoded` property holds the path of the current URL, 
encoded in ASCII for safe transmission over the Internet, from the server root. 

For example, if checked from `my.domain.com/es/web/guest/home`, the value is 
`%2Fes%2Fweb%2Fguest%2Fhome`, as shown below:

```javascript
// Inside my.domain.com/es/web/guest/home
console.log(Liferay.currentURLEncoded); // "%2Fes%2Fweb%2Fguest%2Fhome"
```

Now you know how to manipulate URLs using methods within the `Liferay` global 
JavaScript object. 
