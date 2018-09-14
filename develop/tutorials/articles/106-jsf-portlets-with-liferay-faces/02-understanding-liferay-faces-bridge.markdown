# Understanding Liferay Faces Bridge [](id=understanding-liferay-faces-bridge)

The Liferay Faces Bridge enables you to deploy JSF web apps as portlets without
writing portlet-specific code. It also contains innovative features that make it
possible to leverage the power of JSF 2.x inside a portlet application.

Liferay Faces Bridge is distributed in a `.jar` file. You can add Liferay Faces
Bridge as a dependency to your portlet projects, in order to deploy your JSF web
applications as portlets within JSR 286 (Portlet 2.0) compliant portlet
containers, like Liferay Portal 6.2. 

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
standard would be beneficial. In 2015 the JCP formed [JSR 378]
(http://www.jcp.org/en/jsr/detail?id=378)) which is defining a
bridge for Portlet 3.0 and JSF 2.2. There are also variants of *Liferay Faces
Bridge* that support Portlet 2.0 and JSF 1.2/2.1/2.2.

Liferay Faces Bridge is the Reference Implementation (RI) of the Portlet Bridge
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

The JSR 329/378 standards defines several configuration options prefixed with
the `javax.portlet.faces` namespace. Liferay Faces Bridge defines additional
implementation-specific options prefixed with the `com.liferay.faces.bridge`
namespace. 

With the main aspects of JSF portlet bridges described, you can learn how
a JSF application uses the bridge in the
[Creating a JSF Portlet](/develop/tutorials/-/knowledge_base/6-2/creating-a-jsf-portlet)
learning path. Next, you'll learn how to configure Liferay Faces Bridge,
starting with configuring its scope behavior. 

## Configuring Bridge Request Scope Behavior [](id=configuring-bridge-request-scope-behavior)

For this part of the tutorial, you'll step through requirements, drawbacks,
assumptions, and behaviors for configuring the Bridge Request Scope. 

One of the key requirements in creating a JSF portlet bridge is managing JSF
request-scoped data within the portlet lifecycle. This is normally referred to
as the *Bridge Request Scope* by JSR 329/378. The lifespan of the Bridge Request
Scope works like this: 

1. `ActionRequest`/`EventRequest`: `BridgeRequestScope` begins.

2. `RenderRequest`: `BridgeRequestScope` is preserved.

3. Subsequent `RenderRequest`: `BridgeRequestScope` is reused.

4. Subsequent `ActionRequest`/`EventRequest`: `BridgeRequestScope` ends, and a
   new `BridgeRequestScope` begins.

5. If the session expires or is invalidated, then similar to the
   `PortletSession` scope, all `BridgeRequestScope` instances associated with
   the session are made available for garbage collection by the JVM.

The main use-case for having the `BridgeRequestScope` preserved in Step 2
(above) is for *re-rendering* portlets. Consider the following example that
illustrates this use-case. 

Suppose two or more JSF portlets are placed on a portal page (Portlets X and
Y), and those portlets are *not* using `f:ajax` for form submission. In such a
case, if the user were to submit a form (via full `ActionRequest` postback) in
Portlet X, and then submit a form in Portlet Y, then Portlet X should be
re-rendered with its previously submitted form data. 

With the advent of JSF 2.x and Ajax, there were four drawbacks for continuing
to support this use-case as the default behavior: 

-   Request-scoped data is basically semi-session-scoped in nature, because the
    `BridgeRequestScope` is preserved (even though the user might *never* click
    the Submit button again). 
-   `BridgeRequestScope` can't be stored in the `PortletSession` because the
    data is request-scoped in nature, and the data stored in the scope isn't
    guaranteed to be `Serializable` for replication. Therefore, it doesn't
    really work well in a clustered deployment. 
-   The developer might have to specify the
    `javax.portlet.faces.MAX_MANAGED_REQUEST_SCOPES` `<init-param>` in the
    `WEB-INF/web.xml` descriptor in order to tune the memory settings on the
    server. 

As result, Liferay Faces Bridge was designed for JSF 2.x, and keeps Ajax in
mind. The Liferay Faces Bridge makes the following assumptions: 

-   Developers are not primarily concerned about the *re-rendering of
    portlets* use-case mentioned above. 
-   Developers don't want any of the drawbacks mentioned above. 
-   Developers are making heavy use of the `f:ajax` tag and submitting
    forms via Ajax with their modern-day portlets. 
-   Developers want to do as little configuration as possible and don't
    want to be forced to add anything to the `WEB-INF/web.xml` descriptor. 

Consequently, the default behavior of Liferay Faces Bridge is to cause the
`BridgeRequestScope` to end at the end of the `RenderRequest`. 

If you prefer the standard behavior over Liferay Faces Bridge's default
behavior, then you can place the following option in your portlet's
`WEB-INF/web.xml` descriptor: 

    <context-param>
        <param-name>com.liferay.faces.bridge.bridgeRequestScopePreserved</param-name>
        <param-value>true</param-value>
    </context-param>

    <context-param>
        <param-name>javax.portlet.faces.MAX_MANAGED_REQUEST_SCOPES</param-name>
        <param-value>2000</param-value>
    </context-param>

The default value of the `com.liferay.faces.bridge.bridgeRequestScopePreserved`
param is `false`, meaning that Liferay Faces Bridge causes the
`BridgeRequestScope` to end after the `RENDER_PHASE` of the portlet lifecycle.
Setting the value to `true` causes Liferay Faces Bridge to allow the
`BridgeRequestScope` to last until the next `ACTION_PHASE` or `EVENT_PHASE` of
the portlet lifecycle. 

The default value of the `javax.portlet.faces.MAX_MANAGED_REQUEST_SCOPES` param
is `100`. It defines the maximum number of `BridgeRequestScope` instances to
keep in memory on the server if the `bridgeRequestScopePreserved` option is
`true`. 

Alternatively, the `com.liferay.faces.bridge.bridgeRequestScopePreserved` value
can be specified on a portlet-by-portlet basis in the `WEB-INF/portlet.xml`
descriptor. 

Now you know the two options for Bridge Request Scope behavior. By considering
the outlined assumptions and drawbacks, you should be able to make an educated
decision about how you'd like to implement the Bridge Request Scope behavior. 

## Configuring the Portlet Container Abilities [](id=configuring-the-portlet-container-abilities)

Liferay Faces Bridge can be run in a variety of portlet containers (Liferay,
Pluto, etc.) and is aware of some of the abilities (or limitations) of these
containers. For this part of the tutorial, you'll see how to configure a portlet
container in Liferay Faces Bridge.

Liferay Faces Bridge enables you to configure the abilities of the portlet
container in the portlet project's `WEB-INF/web.xml` descriptor. 

An example of configuring the abilities of the portlet container can be found in
the code snippet below: 

    <context-param>
        <param-name>com.liferay.faces.bridge.containerAbleToSetHttpStatusCode</param-name>
        <param-value>true</param-value>
    </context-param>

The default value of the `context-param` depends on which portlet container the
bridge is running in. The value determines whether or not the bridge resource
handler attempts to set the status code of downloaded resources to values like
`HttpServletResponse.SC_NOT_MODIFIED`. 

By configuring portlet container capabilities, you can take advantage of your
portlet container's specific strengths while using Liferay Faces Bridge. 

## Configuring XML Entity Validation [](id=configuring-xml-entity-validation)

<!-- Explain why this is helpful. - Jim -->

Liferay Faces Bridge lets you enable or disable XML validation for all
`faces-config.xml` file entities. This part of the tutorial shows you how! 

By default, validation is disabled. To enable XML validation for all
`faces-config.xml` file entities, you can set the option to `true` in the
portlet project's `WEB-INF/web.xml` descriptor: 

    <context-param>
        <param-name>com.liferay.faces.bridge.resolveXMLEntities</param-name>
        <param-value>true</param-value>
    </context-param>

Excellent! You now know how to toggle XML entity validation using Liferay Faces
Bridge. 

## Configuring Resource Buffer Size [](id=configuring-resource-buffer-size)

Liferay Faces Bridge lets you set the size of the buffer used to load resources
into memory, as file contents are copied to the response. This part of the
tutorial demonstrates how to configure this setting. 

You can configure the resource buffer size in the portlet project's
`WEB-INF/web.xml` descriptor: 

    <context-param>
        <param-name>com.liferay.faces.bridge.resourceBufferSize</param-name>
        <param-value>4096</param-value>
    </context-param>

The default value of this `context-param` is `1024` (1KB). 

Alternatively, you can specify the `com.liferay.faces.bridge.resourceBufferSize`
value on a portlet-by-portlet basis in the portlet project's
`WEB-INF/portlet.xml` descriptor. 

## Configuring Distinct Request Scoped Managed Beans [](id=configuring-distinct-request-scoped-managed-beans)

Liferay Portal gives you the ability to specify whether or not request
attributes are shared among portlets. You can specify this using the
`<private-request-attributes>` element in the portlet project's
`WEB-INF/liferay-portlet.xml` descriptor. The default value of this option is
`true`, meaning that request attributes are *not* shared among portlets. 

    <liferay-portlet-app>
        <portlet>

            ...

            <private-request-attributes>false</private-request-attributes>
        </portlet>

        ...

    </liferay-portlet-app>

This non-shared feature, however, only works for request attributes that are
present in the request map and that have a non-null value. This can cause a
problem for JSF managed-beans in request scope. Specifically, the problem arises
when a portal page has two or more portlets that have a request scope managed
bean with the same name.

For example, suppose Portlet X and Portlet Y each have a class named
`BackingBean` annotated with `@RequestScoped` `@ManagedBean`. When the JSF
runtime is asked to resolve an EL-expression `#{backingBean}`, there is no
guarantee that the correct instance will be resolved. In order to solve this
problem, Liferay Faces Bridge provides a configuration option that can be
specified in the portlet project's `WEB-INF/web.xml`. It causes request-scoped
managed beans to be distinct for each portlet. 

    <context-param>
        <param-name>com.liferay.faces.bridge.distinctRequestScopedManagedBeans</param-name>
        <param-value>true</param-value>
    </context-param>

The default value of this `context-param` is `false`. To ensure that
`@RequestScoped` managed beans are resolved correctly for each portlet, set this
value to `true`. 

Great! You can now configure distinct request scoped managed beans with Liferay
Faces Bridge. 

## Configuring View Parameters [](id=configuring-view-parameters)

In the case of a portlet `RenderRequest`, Section 5.2.6 of the JSR 329/378 Spec
requires that the bridge only executes the `RESTORE_VIEW` and `RENDER_RESPONSE`
phases of the JSF lifecycle. In addition, Section 6.4 requires that a
`PhaseListener` be used to skip the `APPLY_REQUEST_VALUES`,
`PROCESS_VALIDATIONS`, `UPDATE_MODEL_VALUES`, and `INVOKE_APPLICATION` phases.
These requirements are valid for JSF 1.2, but for JSF 2.x *View Parameters*, the
presence of `f:metadata` and `f:viewParam` in a Facelet view, makes the entire
JSF lifecycle run. 

Liferay Faces Bridge enables support for View Parameters by default, but
provides a configuration option that you can specify in the portlet project's
`WEB-INF/web.xml` descriptor to disable the feature. 

    <context-param>
        <param-name>com.liferay.faces.bridge.viewParametersEnabled</param-name>
        <param-value>false</param-value>
    </context-param>

The param's default value is `true`. If you're using JSF 1.2, you should set
the param value to `false`. 

Great! You've learned another JSF portlet bridge standard and how to configure
several key options in Liferay Faces Bridge. 

## Related Topics [](id=related-topics)

[Understanding Liferay Faces Alloy](/develop/tutorials/-/knowledge_base/6-2/understanding-liferay-faces-alloy)

[Understanding Liferay Faces Portal](/develop/tutorials/-/knowledge_base/6-2/understanding-liferay-faces-portal)

[Creating and Deploying JSF Portlets](/develop/tutorials/-/knowledge_base/6-2/creating-and-deploying-jsf-portlets)

[Developing Liferay Faces Portlets with Maven](/develop/tutorials/-/knowledge_base/6-2/developing-liferay-faces-portlets-with-maven)

[Specifying and Using Portlet Preferences with JSF](/develop/tutorials/-/knowledge_base/6-2/using-portlet-preferences-with-jsf)
