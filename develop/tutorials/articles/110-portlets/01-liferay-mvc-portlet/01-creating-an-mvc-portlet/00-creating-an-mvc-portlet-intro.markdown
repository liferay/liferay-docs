# Creating an MVC Portlet [](id=creating-an-mvc-portlet)

MVC Portlet applications are web modules containing at least one portlet class
that's registered in Liferay's runtime environment as a component. Web modules
describe themselves using standard OSGi metadata and can use any build
environment.

Here are the general steps for implementing a Liferay MVC Portlet component
module: 

1. [Configuring a Web module](#configuring-a-web-module)

2. [Specifying OSGi metadata](#specifying-osgi-metadata)

3. [Creating a portlet Component](#creating-a-portlet-component)

Start by creating a web module for your portlet. 

## Step 1: Configuring a Web Module [](id=configuring-a-web-module)

The folder structure for a web module generally follows this pattern: 

-   docs.liferaymvc.web/
    -   src/main/java/
        -   com/liferay/docs/liferaymvc/web/portlet/LiferayMVCPortlet.java
    -   src/main/resources/
        -   content/
            -   Language.properties
        -   META/-INF/resources/
            -   init.jsp
            -   view.jsp
    -   build.gradle
    -   bnd.bnd

The [MVC portlet template](/develop/reference/-/knowledge_base/7-1/using-the-mvc-portlet-template),
available for both Maven and Gradle in [Liferay @ide@](/develop/tutorials/-/knowledge_base/7-1/liferay-ide)
and [Blade CLI](/develop/tutorials/-/knowledge_base/7-1/blade-cli),
makes creating such Web modules a snap. Of course you're not tied to using
Gradle or bnd to build your project. However, you must build your module as
a JAR and define your module with proper OSGi headers. 

## Step 2: Specifying OSGi Metadata [](id=specifying-osgi-metadata)

OSGi metadata describes your module to the OSGi runtime environment. At
a minimum, you should specify the bundle symbolic name and the bundle version.
We recommend a human-readable bundle name.

    Bundle-Name: Example Liferay MVC Web
    Bundle-SymbolicName: com.liferay.docs.liferaymvc.web
    Bundle-Version: 1.0.0

If you don't specify a `Bundle-SymbolicName`, one is generated from the
project's folder path, which is suitable for many cases. Liferay's convention is
to specify the root package name as your bundle symbolic name.

## Step 3: Creating a Portlet Component [](id=creating-a-portlet-component)

The OSGi Declarative Services component model makes it easy to publish service
implementations to the OSGi runtime. For example, publishing your portlet class
as a `javax.portlet.Portlet` requires an `@Component` annotation like this one:

    @Component(
        immediate = true,
        service = Portlet.class
    )
    public class LiferayMVCPortlet extends MVCPortlet {
    }

The `immediate = true` attribute tells the runtime to publish the portlet as
soon as its dependencies resolve. The attribute `service = Portlet.class`
specifies that the portlet provides the `javax.portlet.Portlet` service. 

Since Liferay's
[`MVCPortlet` class](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.html)
is itself an extension of `javax.portlet.Portlet`, you've provided the right
implementation. That's good in itself, but the Component must be configured:

    @Component(
        immediate = true,
        property = {
            "com.liferay.portlet.display-category=category.sample",
            "com.liferay.portlet.instanceable=true",
            "javax.portlet.display-name=Liferay MVC Portlet",
            "javax.portlet.init-param.template-path=/",
            "javax.portlet.init-param.view-template=/view.jsp",
            "javax.portlet.name=MyMVCPortlet",
            "javax.portlet.resource-bundle=content.Language",
            "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
    )
    public class LiferayMVCPortlet extends MVCPortlet {
    }

Liferay's [MVC portlet template](/develop/reference/-/knowledge_base/7-1/using-the-mvc-portlet-template)
includes these component properties in the portlet class it generates. 

Some of the properties might look familiar to you if you've developed Liferay
MVC portlets for Liferay Portal 6.2. That's because they're the same as the XML
attributes you used to specify in `liferay-portlet.xml`, `liferay-display.xml`,
and `portlet.xml`. The 
[mapping of portlet descriptors to OSGi properties](/develop/reference/-/knowledge_base/7-1/portlet-descriptor-to-osgi-service-property-map)
can you help find OSGi properties for descriptors you already know. 

To keep compatibility with the JSR-168 and JSR-286 portlet specs, these DTDs
define the Liferay-specific portlet attributes:

- [liferay-portlet-app_7_1_0.dtd](@platform-ref@/7.1-latest/definitions/liferay-portlet-app_7_1_0.dtd.html). 
- [liferay-display_7_1_0.dtd](@platform-ref@/7.1-latest/definitions/liferay-display_7_1_0.dtd.html)

For example, consider the `<instanceable>` element from
`liferay-portlet-app_7_1_0.dtd`. To specify that property in your Component, use
this syntax in your `@Component` property list:

    "com.liferay.portlet.instanceable=true",

The properties namespaced with `javax.portlet....` are elements of the
[portlet.xml descriptor](http://java.sun.com/xml/ns/portlet/portlet-app_2_0.xsd).

Also note that you can use the `com.liferay.portlet.display-category` property
to create nested categories. Use `//` to separate the category root and all
categories and sub-categories that comprise your portlet's category path. Here's
an example:

    com.liferay.portlet.display-category=root//category.category1//category.category2
 
You now know how to extend Liferay's `MVCPortlet` and register it as a Component
in the OSGi runtime. It's time to write your controller code. 
