---
header-id: applying-clay-patterns-to-navigation
---

# Applying Clay Patterns to Navigation

[TOC levels=1-4]

This article covers how to leverage Clay patterns in your app's navigation to 
make it more user-friendly. Updating your app's navigation bar to use Clay is 
easy, thanks to the `<clay:navigation-bar />` tag. Follow these steps to update 
your app:

1.  Add the required imports to your app's `init.jsp`:

    ```markup
    // Import the clay tld file to be able to use the new tag
    <%@ taglib uri="http://liferay.com/tld/clay" prefix="clay" %>

    // Import the NavigationItem utility class to create the items model
    <%@ page import="com.liferay.frontend.taglib.clay.servlet.taglib.util.JSPNavigationItemList" %>
    ```

2.  Add the `frontend-taglib-clay` and `frontend.taglib.soy` module dependencies 
    to your app's `build.gradle` file:

    ```groovy
    compileOnly group: "com.liferay", name: "com.liferay.frontend.taglib.soy", 
    version: "1.0.10"

    compileOnly group: "com.liferay", name: "com.liferay.frontend.taglib.clay", 
    version: "1.0.0"
    ```

3.  Inside your JSP view, add a java scriplet to retrieve the navigation 
    variable and portlet URL. An example configuration is shown below:

    ```java
    <%
    final String navigation = ParamUtil.getString(request, "navigation", 
    "entries");

    PortletURL portletURL = renderResponse.createRenderURL();

    portletURL.setParameter("mvcRenderCommandName", "/blogs/view");
    portletURL.setParameter("navigation", navigation);
    %>
    ```

4.  Add the `<clay:navigation-bar />` tag to your app, and use the `items` 
    attribute to specify the navigation items. The navigation bar should be dark 
    if your app is intended for Admin use. To do this, set the `inverted` 
    attribute to `true`. If your app is intended for an instance on a live site, 
    keep the navigation bar light by setting the `inverted` attribute to 
    `false`. An example configuration for an admin app is shown below:

    ```markup
    <clay:navigation-bar
    	inverted="<%= true %>"
    	navigationItems="<%=
    		new JSPNavigationItemList(pageContext) {
    			{
    				add(
    				navigationItem -> {
    					navigationItem.setActive(navigation.equals("entries"));
    					navigationItem.setHref(renderResponse.createRenderURL());
    					navigationItem.setLabel(LanguageUtil.get(request, "entries"));
    				});

    				add(
    				navigationItem -> {
    					navigationItem.setActive(navigation.equals("images"));
    					navigationItem.setHref(renderResponse.createRenderURL(), 
              "navigation", "images");
    					navigationItem.setLabel(LanguageUtil.get(request, "images"));
    				});
    			}
    		}
    	%>"
    />
    ```

5.  Add a conditional block to display the proper JSP for the selected 
    navigation item. An example configuration for the Blogs Admin portlet is 
    shown below:

    ```markup
    <c:choose>
    	<c:when test='<%= navigation.equals("entries") %>'>
    		<liferay-util:include page="/blogs_admin/view_entries.jsp" 
        servletContext="<%= application %>" />
    	</c:when>
    	<c:otherwise>
    		<liferay-util:include page="/blogs_admin/view_images.jsp" 
        servletContext="<%= application %>" />
    	</c:otherwise>
    </c:choose>
    ```

Live site navigation bar:

![Figure 1: The navigation bar should be light for apps on the live site.](../../../../images/clay-patterns-navbar.png)

Admin app navigation bar:

![Figure 2: The navigation bar should be dark (inverted) in admin apps.](../../../../images/clay-patterns-navbar-inverted.png)

Sweet! Now you know how to style a navigation bar with Clay. 

## Related topics

[Implementing the Management Toolbar](/docs/7-2/frameworks/-/knowledge_base/f/implementing-the-management-toolbar)
