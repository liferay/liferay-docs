# Understanding Liferay Faces Bridge [](id=understanding-liferay-faces-bridge)

The Liferay Faces Bridge enables you to deploy JSF web apps as portlets without
writing portlet-specific code. It also contains innovative features that make it
possible to leverage the power of JSF 2.x inside a portlet application.

Liferay Faces Bridge is a JAR that you can add as a dependency to your portlet
WAR projects, in order to deploy JSF web applications as portlets within JSR 286
(Portlet 2.0) compliant portlet containers, like Liferay Portal 5.2, 6.0, 6.1,
and 6.2. 

The Liferay Faces Bridge project home page can be found
[here](http://www.liferay.com/community/liferay-projects/liferay-faces/bridge). 

To fully understand Liferay Faces Bridge, you must first understand the portlet
bridge standard. Because the Portlet 1.0 and JSF 1.0 specs were being created at
essentially the same time, the Expert Group (EG) for the JSF specification
constructed the JSF framework to be compliant with portlets. For example, the
[ExternalContext.getRequest()](http://docs.oracle.com/javaee/6/api/javax/faces/context/ExternalContext.html#getRequest%28%29)
method returns an `Object` instead of an
[javax.servlet.http.HttpServletRequest](http://download.oracle.com/javaee/6/api/javax/servlet/http/HttpServletRequest.html).
When this method is used in a portal, the `Object` can be cast to a
[javax.portlet.PortletRequest](http://portals.apache.org/pluto/portlet-2.0-apidocs/javax/portlet/PortletRequest.html).
Despite the EG's consciousness of portlet compatibility within the design of
JSF, the gap between the portlet and JSF lifecycles had to be bridged. 

Portlet bridge standards and implementations evolved over time. 

Starting in 2004, several different JSF portlet bridge implementations were
developed in order to provide JSF developers with the ability to deploy their
JSF web apps as portlets. In 2006, the JCP formed the Portlet Bridge 1.0 ([JSR
301](http://www.jcp.org/en/jsr/detail?id=301)) EG in order to define a standard
bridge API, as well as detailed requirements for bridge implementations. JSR 301
was released in 2010, targeting Portlet 1.0 and JSF 1.2. 

When the Portlet 2.0 ([JSR 286](http://www.jcp.org/en/jsr/detail?id=286))
standard was released in 2008, it became necessary for the JCP to form the
Portlet Bridge 2.0 ([JSR 329](http://www.jcp.org/en/jsr/detail?id=329)) EG. JSR
329 was also released in 2010, targeting Portlet 2.0 and JSF 1.2. 

After the [JSR 314](http://www.jcp.org/en/jsr/detail?id=314) EG released JSF 2.0
in 2009 and JSF 2.1 in 2010, it became evident that a Portlet Bridge 3.0
standard would be beneficial. Currently, the JCP has not formed such an EG. In
the meantime, Liferay developed *Liferay Faces Bridge*, which targets Portlet
2.0 and JSF 1.2/2.1/2.2. 

Liferay Faces Bridge is an implementation of the JSR 329 Portlet Bridge
Standard. It also contains innovative features that make it possible to leverage
the power of JSF 2.x inside a portlet application. 

Now that you're familiar with some of the history of the Portlet Bridge
standards, you'll learn about the responsibilities required of the portlet
bridge. 

A JSF portlet bridge aligns the correct phases of the JSF lifecycle with each
phase of the portlet lifecycle. For instance, if a browser sends an HTTP GET
request to a portal page with a JSF portlet in it, the `RENDER_PHASE` is
perfomed in the portlet's lifecycle. The JSF portlet bridge then initiates the
`RESTORE_VIEW` and `RENDER_RESPONSE` phases in the JSF lifecycle. Likewise, when
an HTTP POST is executed on a portlet and the portlet enters the `ACTION_PHASE`,
then the full JSF lifecycle is initiated by the bridge. 

![Figure 1: The different phases of the JSF Lifecycle are executed depending on which phase of the Portlet lifecycle is being executed.](../../images/lifecycle-bridge.png)
 
Besides ensuring that the two lifecycles connect correctly, the JSF portlet
bridge also acts as a mediator between the portal URL generator and JSF
navigation rules. JSF portlet bridges ensure that URLs created by the portal
comply with JSF navigation rules, so that a JSF portlet is able to switch to
different views. 

The JSR 329 standard defines several configuration options prefixed with the
`javax.portlet.faces` namespace. Liferay Faces Bridge defines additional
implementation-specific options prefixed with the `com.liferay.faces.bridge`
namespace. If you're curious of ways to configure Liferay Faces Bridge using
these configuration options, you can run through provided in-depth
[tutorials](/develop/tutorials/-/knowledge_base/6-2/liferay-faces-jsf-portlets). 

With the main aspects of JSF portlet bridges described, you can learn how
a JSF application uses the bridge in the
[Creating a JSF Portlet](/develop/learning-paths/jsf/-/knowledge_base/6-2/creating-a-jsf-portlet)
learning path. 

## Related Topics [](id=related-topics)

[Understanding Liferay Faces Alloy](/develop/tutorials/-/knowledge_base/6-2/understanding-liferay-faces-alloy)

[Understanding Liferay Faces Portal](/develop/tutorials/-/knowledge_base/6-2/understanding-liferay-faces-portal)

[Specifying and Using Portlet Preferences with JSF](/develop/tutorials/-/knowledge_base/6-2/specifying-and-using-portlet-preferences-with-jsf)
