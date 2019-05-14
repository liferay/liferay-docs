# Upgrading a Liferay MVC Portlet

Liferay's MVC Portlet framework is used extensively in @product@'s portlets and
is a popular choice for portlet developers. The
[`MVCPortlet`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.html)
class is a lightweight extension of `javax.portlet.GenericPortlet`. Its `init`
method saves you from writing a lot of boilerplate code. MVC portlets can be
upgraded to @product-ver@ without a hitch. 

Upgrading a Liferay MVC Portlet involves these steps:

1.  Adapt the portlet to @product-ver@'s API using the Code Upgrade Planner.
    When running the planner's *Fix Upgrade Problems* step, many of the existing
    issues are autocorrected. For remaining issues, the planner identifies code
    affected by the new API and ways to adapt it.

2.  [Resolve its dependencies](/docs/tutorials/7-2/-/knowledge_base/t/resolving-a-projects-dependencies)

3.  [Deploy it](/docs/reference/7-2/-/knowledge_base/r/deploying-a-project)

After deploying the upgraded portlet, the server prints messages that indicate
the following portlet status:

-   WAR processing
-   WAB startup
-   Availability to users

You've upgraded and deployed your Liferay MVC Portlet on your @product-ver@
instance. Have fun showing off your upgraded portlet!
