# Upgrading a GenericPortlet

It's common to create portlets that extend `javax.portlet.GenericPortlet`. After
all, `GenericPortlet` provides a default `javax.portlet.Portlet` interface
implementation. Upgrading a `GenericPortlet` is straightforward and takes only
two steps: 

1.  Adapt the portlet to @product-ver@'s API using the Code Upgrade Planner.
    When running the planner's *Fix Upgrade Problems* step, many of the existing
    issues are autocorrected. For remaining issues, the planner identifies code
    affected by the new API and ways to adapt it.

2.  [Resolve its dependencies](/docs/tutorials/7-2/-/knowledge_base/t/resolving-a-projects-dependencies)

3.  [Deploy it](/docs/reference/7-2/-/knowledge_base/r/deploying-a-project)

When the portlet WAR file is deployed, @product@'s Plugin Compatibility Layer
converts the WAR to a Web Application Bundle (WAB) and installs the portlet as a
WAB to @product@'s OSGi runtime.

On deploying an upgraded portlet, the server prints messages that indicate the
following portlet status:

-   WAR processing
-   WAB startup
-   Availability to users

Deploying a portlet produces messages like these:

```bash
2018-03-21 17:44:59.179 INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:262] Processing sample-dao-portlet-7.1.0.1.war
...
2018-03-21 17:45:09.959 INFO  [Refresh Thread: Equinox Container: 0012cbb0-7e2c-0018-146e-95a4d71cdf95][PortletHotDeployListener:298] 1 portlet for sample-dao-portlet is available for use 
...
2018-03-21 17:45:10.151 INFO  [Refresh Thread: Equinox Container: 0012cbb0-7e2c-0018-146e-95a4d71cdf95][BundleStartStopLogger:35] STARTED sample-dao-portlet_7.1.0.1 [655]
```

The portlet is now available on @product@.

You've learned how to upgrade and deploy a portlet that extends
`GenericPortlet`. You adapt the code, resolve dependencies, and deploy the
portlet as you always have. It's just that easy!