# Upgrading Portlet Plugins [](id=upgrading-portlet-plugins)

All portlet plugin types developed for Liferay Portal 6 can be upgraded and
deployed to @product-ver@.

Upgrading most portlets involves these steps:

1.  Adapt the code to @product-ver@'s API
2.  Resolve dependencies

Liferay's Code Upgrade Tool helps you adapt your code to @product-ver@'s API.
And resolving a portlet's dependencies is straightforward. In most cases, after
you finish the above steps, you can deploy your portlet to @product@.

The portlet upgrade tutorials show you how to upgrade the following common
portlets: 

-   [GenericPortlet](upgrading-a-genericportlet)
-   [Servlet-based portlet](upgrading-a-servlet-based-portlet)
-   [Liferay MVC Portlet](upgrading-a-liferay-mvc-portlet)
-   [Portlet that uses Service Builder](upgrading-portlets-that-use-service-builder)
-   [Liferay JSF Portlet](upgrading-a-liferay-jsf-portlet)
-   [Struts Portlet](upgrading-a-struts-portlet)
-   [Spring MVC Portlet](upgrading-a-spring-mvc-portlet)

The tutorials provide example portlet source code from before and after
upgrading the example portlet. Each tutorial's steps were applied to the example
portlet. You can refer to example code as you upgrade your portlet. 

Let's get your portlet running on @product-ver@!
