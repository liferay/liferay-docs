---
header-id: liferay-theme-objects-available-in-jsps
---

# Liferay Theme Objects Available in JSPs

[TOC levels=1-4]

When you include the `<liferay-theme:defineObjects>` tag in your JSP, you gain 
access to several Liferay theme objects via variables. These objects are 
described in the table below:

| Object | Description |
| --- | --- |
| `account` | The user's Account object. This object maps to the Account table in the Liferay database. |
| `colorScheme` | An object representing the current color scheme in the theme that is being rendered by the portal |
| `company` | The current Company object. This represents the portal instance on which the user is currently navigating. |
| `contact` | The user's Contact object. This object maps to the Contact table in the Liferay database. |
| `layout` | The page to which the user has currently navigated |
| `layoutTypePortlet` | This object can be used to programmatically add or remove portlets from a page. |
| `locale` | The current user’s locale, as defined by Java |
| `permissionChecker` | An object that can determine---given a particular resource---whether the current user has a particular permission for that resource |
| `plid` | A portal layout ID. This is a unique identifier for any  page that exists in the portal, across all portal instances. |
| `portletDisplay` | An object that gives the programmer access to many attributes of the current portlet, including the portlet name, the portlet mode, the ID of the column on the layout in which it resides, and more |
| `realUser` | When an administrator is impersonating a user, this variable tracks the administrator’s User object. |
| `scopeGroupId` | By default, contains the groupId for the community or organization in which this portlet resides. If the scopeable attribute is set to true, this may contain a unique scope identifier for custom scopes, such as the page scope, if the portlet has been configured to use a custom scope. |
| `theme` | An object representing the current theme that is being rendered by the portal |
| `themeDisplay` | A runtime object that contains many useful items, such as the logged-in user, the layout, logo information, paths, and much more |
| `timeZone` | The current user’s time zone, as defined by Java |
| `user` | The User object representing the current user |
