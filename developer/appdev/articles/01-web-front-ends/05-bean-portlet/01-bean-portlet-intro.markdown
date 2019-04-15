---
header-id: bean-portlet
---

# Bean Portlet

[TOC levels=1-4]

@product@ supports the
[JSR 362](https://jcp.org/en/jsr/detail?id=362)
Portlet 3.0 standard from the Java Community Process (JCP). Portlet 3.0 features
a new style of portlet development called "bean portlets" that use Contexts and
Dependency Injection (CDI). Bean Portlets fully leverage
[all the new Portlet 3.0 features](https://portals.apache.org/pluto/v301/v3Features.html)
in compliant portals, such as Liferay. 

Bean Portlets are plain old Java objects (POJOs)---they don't need to extend
anything. Your portlet descriptors declare them to be portlets. This gives you
flexibility to develop portlets the way you like. 

Configuration annotations, phase method annotations, and CDI are some of the
Portlet 3.0 features these Bean Portlet articles demonstrate. They're described
here. 

## Portlet Configuration Annotations

The
[`@PortletConfiguration`](https://docs.liferay.com/portlet-api/3.0/javadocs/javax/portlet/annotations/PortletConfiguration.html)
annotation lets you describe your portlet to the portlet container. You can use
the annotation instead of or in addition to the standard `portlet.xml`
descriptor file. The `@PortletConfiguration` annotation allows you to stay in
the portlet code instead of bouncing to a separate file to describe your
portlet. 

| **Note:** You can configure Bean Portlets using configuration annotations, 
| descriptors, or both. If using annotations and descriptors, the descriptors 
| take precedence. Annotations, however, configure the portlet in its source
| code. 

This example portlet was generated using the
`com.liferay.project.templates.cdi.bean.portlet` archetype and it uses
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

The `@PortletConfiguration` annotation's `portletName` attribute is where you
name the portlet. It's the only required attribute. The `title` attribute is
assigned the key constant `FooPortletKeys.Foo`. You can also specify a title in
one or more languages using an array of
[`@LocaleString`](https://docs.liferay.com/portlet-api/3.0/javadocs/javax/portlet/annotations/LocaleString.html)
annotations, each specifying a different value for the `locale` element. 

The `@LiferayPortletConfiguration` annotation specifies Liferay-specific
configurable properties. For example, the `portletName` attribute lets you name
the portlet and the `com.liferay.portlet.display-category` property lets you
assign the Widget category in which to make the portlet available to users.
Setting the `com.liferay.portlet.instanceable=true` enables adding multiple
instances of the portlet to a page. 

| **Note:** The `@PortletConfiguration` and `@LiferayPortletConfiguration` 
| annotations are synonymous with the `javax.portlet.*` and
| `com.liferay.portlet.*` properties in the OSGi `@Component` annotation (used 
| in
| [Liferay MVC Portlets](/docs/7-2/appdev/-/knowledge_base/appdev/creating-an-mvc-portlet),
| for example).
| If you're familiar with the `portlet.xml` and `liferay-portlet.xml` 
| descriptors, the
| [Portlet Descriptor to OSGi Service Property Map](/docs/7-2/reference/-/knowledge_base/reference/portlet-descriptor-to-osgi-service-property-map) 
| shows you the OSGi `@Component` property equivalent---there's an
| `@PortletConfiguration` or `@LiferayPortletConfiguration` equivalent setting 
| for each `@Component` property. 

## Dependency Injection

Bean Portlets use the `@Inject` CDI annotation to inject dependencies. Just
apply the annotation to a field you want injected with an object of the
specified type. This example portlet injects the portlet's `PortletConfig`
object. 

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

Portlet 3.0 defines annotations for setting portlet phase methods too. 

## Portlet Phase Methods

Phase method annotations can be applied to methods in any class anywhere in the
portlet WAR. There's no mandatory method naming convention---just apply phase
annotations to the method you want to invoke. Here are the annotations:

| Phase    | Annotation |
| ---------------- | ---------- |
| Header (new)     | [`@HeaderMethod`](https://portals.apache.org/pluto/portlet-3.0-apidocs/javax/portlet/annotations/HeaderMethod.html) |
| Render           | [`@RenderMethod`](https://portals.apache.org/pluto/portlet-3.0-apidocs/javax/portlet/annotations/RenderMethod.html) |
| Action           | [`@ActionMethod`](https://portals.apache.org/pluto/portlet-3.0-apidocs/javax/portlet/annotations/ActionMethod.html) |
| Event            | [`@EventMethod`](https://portals.apache.org/pluto/portlet-3.0-apidocs/javax/portlet/annotations/EventMethod.html)  |
| Resource-serving | [`@ServeResourceMethod`](https://portals.apache.org/pluto/portlet-3.0-apidocs/javax/portlet/annotations/ServeResourceMethod.html) |

The Header phase lets you specify resource dependencies, such as CSS, prior to
the Render phase. This phase helps you avoid loading the same resources multiple
times. 

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
phase of portlets matching any of the names listed for the `portletNames`
attribute.  

The example render method produces this content:

1.  A string greeting `"Hello from " + portletConfig.getPortletName()`

2.  The JSP template `/WEB-INF/jsp/view.jsp`---the `@RenderMethod` annotation's 
    `include` attribute references it. 

These are just a few of the Portlet 3.0 features that facilitate developing
applications. This section covers more Portlet 3.0 features. Creating and
deploying your own Bean Portlet is next. 
