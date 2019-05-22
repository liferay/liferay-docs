---
header-id: using-the-clay-taglib-in-your-portlets
---

# Using the Clay Taglib in Your portlets

[TOC levels=1-4]

The Liferay Clay tag library provides a set of tags for creating 
[Clay](https://claycss.com/docs/clay/) 
UI components in your app. 

| **Note:** AUI taglibs are deprecated as of @product-ver@. We recommend that you
| use Clay taglibs to avoid future compatibility issues.

To use the Clay taglib in your apps, add the following declaration to your JSP:

```markup
<%@ taglib prefix="clay" uri="http://liferay.com/tld/clay" %>
```
The Liferay Clay taglib is also available via a macro for your FreeMarker theme 
templates and web content templates. Follow this syntax:

```markup
<@clay["tag-name"] attribute="string value" attribute=10 />
```

Clay taglibs provide the following UI components for your apps:

- [Alerts](/docs/7-2/reference/-/knowledge_base/r/clay-alerts)
- [Badges](/docs/7-2/reference/-/knowledge_base/r/clay-badges)
- [Buttons](/docs/7-2/reference/-/knowledge_base/r/clay-buttons)
- [Cards](/docs/7-2/reference/-/knowledge_base/r/clay-cards)
- [Dropdown Menus and Action Menus](/docs/7-2/reference/-/knowledge_base/r/clay-dropdown-menus-and-action-menus)
- [Form Elements](/docs/7-2/reference/-/knowledge_base/r/clay-form-elements)
- [Icons](/docs/7-2/reference/-/knowledge_base/r/clay-icons)
- [Labels and links](/docs/7-2/reference/-/knowledge_base/r/clay-labels-and-links)
- [Management Toolbar](/docs/7-2/reference/-/knowledge_base/r/clay-management-toolbar)
- [Navigation Bars](/docs/7-2/reference/-/knowledge_base/r/clay-navigation-bars)
- [Progress Bars](/docs/7-2/reference/-/knowledge_base/r/clay-progress-bars)
- [Stickers](/docs/7-2/reference/-/knowledge_base/r/clay-stickers)

This section covers how to create these components with the Clay taglibs. Each 
article contains a set of clay component examples along with a screenshot of the 
resulting UI. 
