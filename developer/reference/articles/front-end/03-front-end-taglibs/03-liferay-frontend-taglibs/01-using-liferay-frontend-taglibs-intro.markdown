---
header-id: using-liferay-front-end-taglibs-in-your-portlet
---

# Using Liferay Front-end Taglibs in Your Portlet

[TOC levels=1-4]

The Liferay Front-end tag library provides a set of tags for creating common
front-end UI components in your app. 

To use the Front-end taglib in you apps, add the following declaration to your 
JSP:

```markup
<%@ taglib prefix="liferay-frontend" uri="http://liferay.com/tld/frontend" %>
```

The Liferay Front-end taglib is also available via a macro for your FreeMarker 
theme templates and web content templates. Follow this syntax:

```markup
<@liferay_frontend["tag-name"] attribute="string value" attribute=10 />
```

The following Front-end UI components are covered in this section:

- [Add Menu](/docs/7-2/reference/-/knowledge_base/r/liferay-frontend-add-menu)
- [Cards](/docs/7-2/reference/-/knowledge_base/r/liferay-frontend-cards)
- [Info Bar](/docs/7-2/reference/-/knowledge_base/r/liferay-frontend-info-bar)
- [Management Bar](/docs/7-2/reference/-/knowledge_base/r/liferay-frontend-management-bar)

Each article contains a set of examples along with a screenshot of the resulting 
UI. 
