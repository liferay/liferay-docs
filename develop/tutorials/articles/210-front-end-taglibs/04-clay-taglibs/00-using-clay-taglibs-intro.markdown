# Using the Clay Taglib in Your portlets [](id=using-the-clay-taglib-in-your-portlets)

The Liferay Clay tag library provides a set of tags for creating 
[Clay](https://claycss.com/docs/clay/) 
UI components in your app. 

+$$$

**Note:** AUI taglibs are deprecated as of @product-ver@. We recommend that you 
use Clay taglibs to avoid future compatibility issues. 

$$$

To use the Clay taglib, add the following declaration to your JSP:

    <%@ taglib prefix="clay" uri="http://liferay.com/tld/clay" %>

The 
[`com.liferay.frontend.taglib.clay.sample.web` portlet](https://github.com/liferay/liferay-portal/tree/7.1.x/modules/apps/frontend-taglib/frontend-taglib-clay-sample-web) 
is referenced throughout this tutorial as an example. It's included 
out-of-the-box. Open the Control Menu and add the *Clay Sample* portlet from the 
*Applications* &rarr; *Sample* menu to see it in action. 

![Figure 1: The Clay Sample portlet demonstrates the various types of clay components you can create with the clay taglbs.](../../../images/clay-taglib-sample-portlet.png)

Clay taglibs provide the following UI components for your apps:

- [Alerts](/develop/tutorials/-/knowledge_base/7-1/clay-alerts)
- [Badges](/develop/tutorials/-/knowledge_base/7-1/clay-badges)
- [Buttons](/develop/tutorials/-/knowledge_base/7-1/clay-buttons)
- [Cards](/develop/tutorials/-/knowledge_base/7-1/clay-cards)
- [Dropdown Menus and Action Menus](/develop/tutorials/-/knowledge_base/7-1/clay-dropdown-menus-and-action-menus)
- [Form Elements](/develop/tutorials/-/knowledge_base/7-1/clay-form-elements)
- [Icons](/develop/tutorials/-/knowledge_base/7-1/clay-icons)
- [Labels and links](/develop/tutorials/-/knowledge_base/7-1/clay-labels-and-links)
- [Management Toolbar](/develop/tutorials/-/knowledge_base/7-1/clay-management-toolbar)
- [Navigation Bars](/develop/tutorials/-/knowledge_base/7-1/clay-navigation-bars)
- [Progress Bars](/develop/tutorials/-/knowledge_base/7-1/clay-progress-bars)
- [Stickers](/develop/tutorials/-/knowledge_base/7-1/clay-stickers)

The tutorials in this section cover how to create these components with the Clay 
taglibs. Each tutorial contains a set of clay component examples along with a 
screenshot of the resulting UI.
