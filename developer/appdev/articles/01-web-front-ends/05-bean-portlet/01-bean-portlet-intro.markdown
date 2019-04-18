---
header-id: bean-portlet
---

# Bean Portlet

[TOC levels=1-4]

Portlet 3.0, the 
[JSR 362](https://jcp.org/en/jsr/detail?id=362) standard, features
a new style of portlet development called Bean Portlets that use Contexts and
Dependency Injection (CDI). Bean Portlets fully leverage 
[all the new Portlet 3.0 features](https://portals.apache.org/pluto/v301/v3Features.html)
in compliant portals, and are fully supported in @product@. 

Bean Portlets are plain old Java objects (POJOs): they don't need to extend
anything. Portlet descriptors declare them to be portlets. 

Configuration annotations, phase method annotations, and CDI are some of the
features you'll use in Portlet 3.0. 

## Portlet Configuration Annotations

The
[`@PortletConfiguration`](https://docs.liferay.com/portlet-api/3.0/javadocs/javax/portlet/annotations/PortletConfiguration.html)
annotation describes your portlet to the portlet container. You can use the
annotation instead of or in addition to the traditional `portlet.xml` descriptor
file. The `@PortletConfiguration` annotation describes your portlet in the
portlet code instead of a separate file. 

| **Note:** You can configure Bean Portlets using configuration annotations, 
| descriptors, or both. If using annotations and descriptors, the descriptors 
| take precedence. 

This example portlet was generated using the
`com.liferay.project.templates.cdi.bean.portlet` archetype, and it uses
`@PortletConfiguration` and `@LiferayPortletConfiguration` annotations:

```java
import com.mycompany.constants.FooPortletKeys;

import com.liferay.bean.portlet.LiferayPortletConfiguration;

import javax.portlet.annotations.LocaleString;
import javax.portlet.annotations.PortletConfiguration;

@PortletConfiguration(
    portletName = FooPortletKeys.Foo,
    title = @LocaleString(value = FooPortletKeys.Foo))
@LiferayPortletConfiguration(
    portletName = FooPortletKeys.Foo,
    properties = {
        "com.liferay.portlet.display-category=category.sample",
        "com.liferay.portlet.instanceable=true"
    }
)
public class FooPortlet {
    ...
}
```

`@PortletConfiguration`'s `portletName` attribute names the portlet. It's the
only required attribute. The `title` attribute typically uses a nicer looking
name (e.g., uses spaces and capitalization). The `title` above is assigned
the key constant `FooPortletKeys.Foo`. You can also localize a title to one or
more languages using an array of
[`@LocaleString`](https://docs.liferay.com/portlet-api/3.0/javadocs/javax/portlet/annotations/LocaleString.html)
annotations, each specifying a different value for the `locale` element. 

The `@LiferayPortletConfiguration` annotation specifies additional
Liferay-specific configuration properties. For example, the
`com.liferay.portlet.display-category` property lets you assign the Widget
category where users will find your portlet. Setting the
`com.liferay.portlet.instanceable=true` enables adding multiple instances of the
portlet to a page. 

| **Note:** The `@PortletConfiguration` and `@LiferayPortletConfiguration` 
| annotations are respectively synonymous with the `javax.portlet.*` and
| `com.liferay.portlet.*` properties in the OSGi `@Component` annotation (used 
| in
| [Liferay MVC Portlets](/docs/7-2/appdev/-/knowledge_base/appdev/creating-an-mvc-portlet),
| for example).
| If you're familiar with the `portlet.xml` and `liferay-portlet.xml` 
| descriptors, the
| [Portlet Descriptor to OSGi Service Property Map](/docs/7-2/reference/-/knowledge_base/reference/portlet-descriptor-to-osgi-service-property-map) 
| shows you the OSGi `@Component` property equivalent. There's an
| `@PortletConfiguration` or `@LiferayPortletConfiguration` equivalent setting 
| for each `@Component` property. 

Once you've configured your portlet, you should declare the objects it uses
(depends on). 

## Dependency Injection

Bean Portlets use the `@Inject` CDI annotation (by default) to inject
dependencies. Apply the annotation to a field you want injected with an object
of the specified type. This example portlet injects the portlet's
`PortletConfig` object. 

```java
import javax.inject.Inject;

import javax.portlet.PortletConfig;

public class FooPortlet {

    @Inject
    PortletConfig portletConfig;

    // Invoke methods on portletConfig ...

}
```

| **Note:**
| [OSGi Integration](/docs/7-2/frameworks/-/knowledge_base/frameworks/osgi-cdi-integration) 
| allows you to use OSGi services (e.g., Liferay's 
| [`UserLocalService`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/service/UserLocalService.html))
| in your Bean Portlets. 

Portlet 3.0 defines annotations for declaring methods that handle portlet
phases. 

## Portlet Phase Methods

Phase method annotations apply methods for handling a portlet's phases. You can
add them to methods in any class anywhere in the portlet WAR. There's no
mandatory method naming convention: assign a phase annotation to the
methods you want to invoke to process the phase. Here are the annotations:

| Phase    | Annotation |
| ---------------- | ---------- |
| Header (new)     | [`@HeaderMethod`](https://docs.liferay.com/portlet-api/3.0/javadocs/javax/portlet/annotations/HeaderMethod.html) |
| Render           | [`@RenderMethod`](https://docs.liferay.com/portlet-api/3.0/javadocs/javax/portlet/annotations/RenderMethod.html) |
| Action           | [`@ActionMethod`](https://docs.liferay.com/portlet-api/3.0/javadocs/javax/portlet/annotations/ActionMethod.html) |
| Event            | [`@EventMethod`](https://docs.liferay.com/portlet-api/3.0/javadocs/javax/portlet/annotations/EventMethod.html)  |
| Resource-serving | [`@ServeResourceMethod`](https://docs.liferay.com/portlet-api/3.0/javadocs/javax/portlet/annotations/ServeResourceMethod.html) |

You can specify resource dependencies, such as CSS, in the Header phase prior to
the Render phase. It helps you avoid loading the same resources multiple times. 

You'll definitely want to define a Render method. For example, here's a method
invoked during the Render phase:

```java
import javax.portlet.annotations.RenderMethod;

@RenderMethod(
    include = "/WEB-INF/jsp/view.jsp",
    portletNames = {FooPortletKeys.Foo})
public String doView() {
    return "Hello from " + portletConfig.getPortletName();
}
```

The `@RenderMethod` annotation sets the method to be invoked during the Render
phase of the WAR's portlets matching any of the names listed for the
`portletNames` attribute. 

The example Render method produces this content:

1.  A string greeting `"Hello from " + portletConfig.getPortletName()`

2.  The JSP template `/WEB-INF/jsp/view.jsp`---the `@RenderMethod` annotation's 
    `include` attribute references it. 

These are just a few of the Portlet 3.0 features that facilitate developing
applications. This section covers more Portlet 3.0 features and Bean Portlet
demonstrations. Creating and deploying your own Bean Portlet is next. 
