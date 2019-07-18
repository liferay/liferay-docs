---
header-id: liferay-mvc-portlet
---

# Liferay MVC Portlet

[TOC levels=1-4]

If you're an experienced developer, this is not the first time you've heard
about Model View Controller. If there are so many implementations of MVC
frameworks in Java, why did Liferay create yet another one? Stay with us and
you'll see that Liferay MVC Portlet provides these benefits:

-   It's lightweight, as opposed to many other Java MVC frameworks. 
-   There are no special configuration files that need to be kept in sync with
    your code. 
-   It's a simple extension of
    [`GenericPortlet`](https://docs.liferay.com/portlet-api/3.0/javadocs/javax/portlet/GenericPortlet.html). 
-   You avoid writing a bunch of boilerplate code, since Liferay's MVC Portlet 
    framework only looks for some pre-defined parameters when the `init()`
    method is called. 
-   The controller can be broken down into MVC command classes, each of which
    handles the controller code for a particular 
    [portlet phase](/docs/7-2/frameworks/-/knowledge_base/f/portlets) (render, action,
    and resource serving phases). 
-   An MVC command class can serve multiple portlets. 
-   Liferay's portlets use it. That means there are plenty of robust
    implementations to reference when you need to design or troubleshoot your
    Liferay applications.

The Liferay MVC Portlet framework is light and easy to use. The default
[`MVCPortlet` project](/docs/7-2/reference/-/knowledge_base/r/using-the-mvc-portlet-template)
template generates a fully configured and working project. 

Here, you'll learn how MVCPortlet works by covering these topics: 

- [MVC layers and modularity](#mvc-layers-and-modularity)
- [Liferay MVC command classes](#liferay-mvc-command-classes)
- [Liferay MVC portlet component](#liferay-mvc-portlet-component)
- [Simple MVC portlets](#a-simpler-mvc-portlet)

Review how each layer of the Liferay MVC portlet framework helps you separate
the concerns of your application. 

## MVC Layers and Modularity

In MVC, there are three layers, and you can probably guess what they are.

**Model:** The model layer holds the application data and logic for manipulating
it.

**View:** The view layer contains logic for displaying data.

**Controller:** The middle man in the MVC pattern, the Controller contains logic
for passing the data back and forth between the view and the model layers.

@product@'s applications are divided into multiple discrete modules. With
[Service Builder](/docs/7-2/appdev/-/knowledge_base/a/service-builder), the
model layer is generated into a `service` and an `api` module. That accounts for
the model in the MVC pattern. The view and the controller layers share a module,
the `web` module. 

Generating the skeleton for a [multi-module Service Builder-driven MVC
application](/docs/7-2/reference/-/knowledge_base/r/using-the-service-builder-template)
saves you lots of time and gets you started on the more important (and
interesting, if we're being honest) development work. 

## Liferay MVC Command Classes

In a larger application, your `-Portlet` class can become monstrous and unwieldy
if it holds all of the controller logic. Liferay provides MVC command classes to
break up your controller functionality.

-   **[`MVCActionCommand`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCActionCommand.html):**
    Use `-ActionCommand` classes to hold each of your portlet actions, which are
    invoked by action URLs.
-   **[`MVCRenderCommand`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCRenderCommand.html):**
    Use `-RenderCommand` classes to hold a `render` method that dispatches to
    the appropriate JSP, by responding to render URLs.
-   **[`MVCResourceCommand`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCResourceCommand.html):**
    Use `-ResourceCommand` classes to serve resources based on resource URLs.

There must be some confusing configuration files to keep everything wired
together and working properly, right? Wrong: it's all easily managed in the
`-Portlet` class's
[`@Component`](https://osgi.org/javadoc/r6/residential/org/osgi/service/component/annotations/Component.html)
annotation. 

## Liferay MVC Portlet Component

Whether or not you plan to split up the controller into MVC command classes, the
portlet `@Component` annotation configures the portlet. Here's a simple portlet
component as an example:

```java
@Component(
    property = {
        "com.liferay.portlet.css-class-wrapper=portlet-hello-world",
        "com.liferay.portlet.display-category=category.sample",
        "com.liferay.portlet.icon=/icons/hello_world.png",
        "com.liferay.portlet.preferences-owned-by-group=true",
        "com.liferay.portlet.private-request-attributes=false",
        "com.liferay.portlet.private-session-attributes=false",
        "com.liferay.portlet.remoteable=true",
        "com.liferay.portlet.render-weight=50",
        "com.liferay.portlet.use-default-template=true",
        "javax.portlet.display-name=Hello World",
        "javax.portlet.expiration-cache=0",
        "javax.portlet.init-param.always-display-default-configuration-icons=true",
        "javax.portlet.name=" + HelloWorldPortletKeys.HELLO_WORLD,
        "javax.portlet.resource-bundle=content.Language",
        "javax.portlet.security-role-ref=guest,power-user,user",
        "javax.portlet.supports.mime-type=text/html"
    },
    service = Portlet.class
)
public class HelloWorldPortlet extends MVCPortlet {
}
```

The `javax.portlet.name` property is required.  When using MVC commands, the
`javax.portlet.name` property value links particular portlet URL/command
combinations to the correct portlet. 

| **Important:** Make your portlet name unique, considering how
| [@product@ uses the name to create the portlet's ID](/docs/7-2/reference/-/knowledge_base/r/portlet-descriptor-to-osgi-service-property-map#ten).

There can be some confusion over exactly what kind of `Portlet.class`
implementation you're publishing with a component. The service registry expects
this to be the
[`javax.portlet.Portlet`](https://docs.liferay.com/portlet-api/3.0/javadocs/javax/portlet/Portlet.html)
interface. Import that, and not, for example,
`com.liferay.portal.kernel.model.Portlet`. 

| **Note:** The DTD [liferay-portlet-app_7_2_0.dtd](@platform-ref@/7.2-latest/definitions/liferay-portlet-app_7_2_0.dtd.html)
| defines all the Liferay-specific attributes you can specify as properties in
| your portlet components.
| 
| Consider the `<css-class-wrapper>` element from the above link as an example. 
| To specify that property in your component, use this syntax in your property 
| list:
| 
| `"com.liferay.portlet.css-class-wrapper=portlet-hello-world",`
| 
| The properties namespaced with `javax.portlet.` are elements of the
| [`portlet.xml` descriptor](https://docs.liferay.com/portlet-api/3.0/portlet-app_3_0.xsd).

## A Simpler MVC Portlet

In simpler applications, you don't use MVC commands. Your
portlet render URLs specify JSP paths in `mvcPath` parameters.

```javascript
<portlet:renderURL var="addEntryURL">
	<portlet:param name="mvcPath" value="/entry/edit_entry.jsp" />
	<portlet:param name="redirect" value="<%= redirect %>" />
</portlet:renderURL>
```

As you've seen, Liferay's MVC Portlet framework gives you a well-structured
controller layer that takes very little time to implement. With all your free
time, you could

-   Learn a new language
-   Take pottery classes
-   Lift weights
-   Work on your application's business logic

It's entirely up to you. 

To get into the details of creating an MVC Portlet application, continue with
[Creating an MVC
Portlet](/docs/7-2/appdev/-/knowledge_base/a/creating-an-mvc-portlet). 

## Related Topics 

[Bean Portlet](/docs/7-2/appdev/-/knowledge_base/a/bean-portlet)
