# Upgrading a Liferay MVC Portlet [](id=upgrading-a-liferay-mvc-portlet)

Liferay's MVC Portlet framework is used extensively in Liferay's portlets and is
a popular choice for Liferay Portal 6.2 portlet developers. The
[`MVCPortlet` class](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.html)
is a lightweight extension of `javax.portlet.GenericPortlet`. Its `init` method
saves you from writing a lot of boilerplate code. MVC portlets can upgraded
to @product-ver@ without a hitch. 

This tutorial explains how to upgrade a Liferay MVC Portlet and refers to
Liferay's Stocks portlet as an example.  The Stocks portlet calls on Yahoo
Finance to retrieve stock quotes. When a user enters a stock symbol and clicks
the *Get Quote* button, the portlet shows the stock's statistics and
performance graph. 

![Figure 1: Users enter a stock's symbol and click the *Get Quote* button to see the stock's performance details.](../../../../images/upgrading-portlets-stocks-portlet-view-6-2.png)

You can follow this tutorial's steps to upgrade your Liferay MVC Portlet. Along
the way, you can examine the Stocks portlet source code from before and after
its upgrade.

-   [Liferay Portal 6.2 Stocks portlet code](https://dev.liferay.com/documents/10184/656312/stocks-portlet-pre-7-0-upgrade.zip)

-   [@product-ver@ Stocks portlet code](https://dev.liferay.com/documents/10184/656312/stocks-portlet-post-7-0-upgrade.zip)

Here are the Stock portlet's characteristics: 

-   Extends Liferay's [`MVCPortlet` class](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.html),
    overriding its `processAction` method 
    to retrieve stock quotes from Yahoo Finance
-   View layer implemented by JSPs
-   Relies on manual dependency management
-   Depends on third-party libraries Liferay Portal 6.2 provides 

The Stocks portlet uses as traditional Liferay Plugins SDK folder structure, as shown in the figure below. 

![Figure 2: The Stocks portlet follows the portlet plugin folder structure the Plugins SDK generated.](../../../../images/upgrading-portlets-stocks-portlet-anatomy.png)

Upgrading a Liferay MVC Portlet involves these steps:

1.  [Adapt the code to @product-ver@'s API](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)

2.  [Resolve dependencies](/develop/tutorials/-/knowledge_base/7-0/resolving-a-plugins-dependencies)

Liferay's 
[Code Upgrade Tool](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)
identifies code affected by the new API, explains the API changes and how to
adapt to them, and in many cases, provides options for adapting the code
automatically. 

As an example code upgrade, the Stocks portlet was adapted to changes in
@product-ver@'s 
[`LanguageUtil` class](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/language/LanguageUtil.html).
Its `get` method returns a localized version of the given `String`. In 6.2, the
method derives the current language from the page context parameter; in 7.0, it
derives it from the HTTP servlet request.

Old:

    get(javax.servlet.jsp.PageContext, java.lang.String)
 
New:

    get(javax.servlet.http.HttpServletRequest, java.lang.String)
 
The upgraded Stocks portlet's `view.jsp` now passes the request to new method.

Regarding dependencies, the original Stocks portlet relied on Liferay Portal to
provide them.  Here's the `portal-dependency-jars` property from the portlet's
`liferay-plugin-package.properties` file:

    portal-dependency-jars=\
        jstl-api.jar,\
        jstl-impl.jar

Since @product-ver@ application provides these standard JARs in its
`WEB-INF/lib` folder, you can continue to use them via the
`portal-dependency-jars` property. 

[Resolve your portlet's dependencies](/develop/tutorials/-/knowledge_base/7-0/resolving-a-plugins-dependencies)
and deploy it the same way you always do. 

The server prints messages that indicate the following portlet status:

-   WAR processing
-   WAB startup
-   Availability to users

+$$$

Note: On deploying the upgraded sample portlet, the
[WAB processor](/develop/tutorials/-/knowledge_base/7-0/using-the-wab-generator)
warns that the `portal-dependency-jars` property is deprecated. 

    21:40:25,347 WARN  [fileinstall-...][WabProcessor:564] The property "portal-dependency-jars" is deprecated. Specified JARs may not be included in the class path.

For running on @product-ver@, it's fine to specify the`portal-dependency-jars`
property per the
[instructions](resolving-a-plugins-dependencies#using-packages-liferay-portal-exports)
for using packages that @portal@ exports. After upgrading, consider
[using a dependency management tool](/develop/tutorials/-/knowledge_base/7-0/resolving-a-plugins-dependencies#managing-dependencies-with-ivy)
in your project. This helps prepare it for future @product@ versions and
facilitates managing dependencies.

$$$ 

The following figure shows the upgraded Stocks portlet deployed on
@product-ver@. 

![Figure 3: The upgrade Stocks portlet runs just as well on @product-ver@ as it did on Liferay Portal 6.2.](../../../../images/upgrading-portlets-stocks-portlet-upgraded.png)

You've upgraded and deployed your Liferay MVC Portlet on your @product-ver@
instance. Have fun showing off your upgraded portlet!

**Related Topics**

[Migrating Plugins SDK Projects to Workspace and Gradle](/develop/tutorials/-/knowledge_base/7-0/migrating-traditional-plugins-to-workspace-web-applications)

[Using Dependency Management Tools](/develop/tutorials/-/knowledge_base/7-0/resolving-a-plugins-dependencies#managing-dependencies-with-ivy)

[Using the WAB Generator](/develop/tutorials/-/knowledge_base/7-0/using-the-wab-generator)

[Migrating Data Upgrade Processes](/develop/tutorials/-/knowledge_base/7-0/optimizing-app-upgrade-processes)
