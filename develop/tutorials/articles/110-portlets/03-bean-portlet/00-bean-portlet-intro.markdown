# Portlet 3: Bean Portlet [](id=bean-portlet-portlet-3)

@product-ver@ GA2 onward supports the
[JSR 362](https://jcp.org/en/jsr/detail?id=362)
Portlet 3.0 standard from the Java Community Process (JCP). Portlet 3.0 features
a new style of portlet development called "bean portlets" that use Contexts and
Dependency Injection (CDI). Bean portlets fully leverage
[all the new Portlet 3.0 features](https://portals.apache.org/pluto/v301/v3Features.html)
in compliant portals, such as Liferay.

Here you'll learn how to create and deploy a bean portlet. And if you're
familiar with developing OSGi portlets, we'll help you relate what you already
know to bean portlet development!

## Creating a bean portlet project [](id=creating-a-bean-portlet-project)

Bean portlets have a Maven archetype.

- Group: `com.liferay`
- Artifact: `com.liferay.project.templates.cdi.bean.portlet`
- Version: `1.0.0`

To create a bean portlet project, execute a Maven command like this one: 

    mvn archetype:generate \
    -DarchetypeGroupId=com.liferay \
    -DarchetypeArtifactId=com.liferay.project.templates.cdi.bean.portlet \
    -DarchetypeVersion=1.0.0 \
    -DgroupId=com.mycompany \
    -DartifactId=com.mycompany.demo.bean.portlet

Maven prompts you for a project author name, version, and portlet class
name. For this example, the defaults are fine for everything else. 

If you entered `Foo` as the portlet class name, it generates this project
layout: 

- `com.mycompany.demo.bean.portlet` &rarr; Arbitrary project name.
    - `src/main/java`
        - `com.mycompany.constants.FooPortletKeys` &rarr; Declares portlet 
        constants.
        - `com.mycompany.portlet.FooPortlet` &rarr; CDI bean portlet class.
    - `src/main/webapp/WEB-INF/beans.xml` &rarr; Signals CDI to scan the 
    portlet for annotations. 
    - `pom.xml` &rarr; Specifies the project's dependencies and packaging.

When Maven generates the project based on the archetype, it uses the class name
that you entered for the CDI bean portlet class file name. It also uses it as
the value of a portlet key constant. 

The generated bean portlet project depends on the Portlet 3.0 API and the "Weld"
implementation of CDI. The project also depends on Liferay's CDI portable
extension, which facilitates bean portlet registration. The `pom.xml` file lists
all the dependencies.

+$$$

**Note:** A bean portlet is packaged as a WAR. The bean portlet project
generated using the above archetype differs from a traditional  portlet and
Liferay portlet project in that it doesn't have a `portlet.xml` and
`liferay-portlet.xml` descriptor. You can configure bean portlets using
configuration annotations (described next), descriptors, or both. If using
annotations and descriptors, the descriptors take precedence. Annotations,
however, configure the portlet in its source code. 

$$$ 

## Developing a Bean Portlet [](id=developing-a-bean-portlet)

Bean portlets, like `FooPortlet`, are plain old Java objects (POJOs)---they
don't extend anything. Here's the example bean portlet class:

    package com.mycompany.portlet;

    import com.mycompany.constants.FooPortletKeys;

    import com.liferay.bean.portlet.LiferayPortletConfiguration;

    import javax.inject.Inject;

    import javax.portlet.PortletConfig;
    import javax.portlet.annotations.LocaleString;
    import javax.portlet.annotations.PortletConfiguration;
    import javax.portlet.annotations.RenderMethod;

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

    	@Inject
    	PortletConfig portletConfig;

    	@RenderMethod(
    		include = "/WEB-INF/jsp/view.jsp",
    		portletNames = {FooPortletKeys.Foo})
    	public String doView() {
    		return "Hello from " + portletConfig.getPortletName();
    	}

    }

The
[`@PortletConfiguration`](https://docs.liferay.com/portlet-api/3.0/javadocs/javax/portlet/annotations/PortletConfiguration.html)
annotation configures the bean portlet. It requires only the `portletName`
element, but has several optional configuration elements as well. In the
generated project, the `title` element is assigned the key constant
`FooPortletKeys.Foo`. You can also specify a title in one or more languages
using an array of
[`@LocaleString`](https://docs.liferay.com/portlet-api/3.0/javadocs/javax/portlet/annotations/LocaleString.html)
annotations, each specifying a different value for the `locale` element.

The `@LiferayPortletConfiguration` annotation specifies Liferay-specific
configurable properties. Assign the portlet's name via the `portletName`
property. Use the `com.liferay.portlet.display-category` property to assign
the Widget category in which to make the portlet available to users. Setting the
`com.liferay.portlet.instanceable=true` enables adding multiple instances of the
portlet to a page.

+$$$

**Note:** The `@PortletConfiguration` and `@LiferayPortletConfiguration` 
annotations are synonymous with the `javax.portlet.*` and
`com.liferay.portlet.*` properties in the OSGi `@Component` annotation (used in
[Liferay MVC Portlets](/develop/tutorials/-/knowledge_base/7-1/creating-an-mvc-portlet#creating-a-portlet-component), for example).
If you're familiar with the `portlet.xml` and `liferay-portlet.xml` descriptors,
the
[Portlet Descriptor to OSGi Service Property Map](https://dev.liferay.com/develop/reference/-/knowledge_base/7-1/portlet-descriptor-to-osgi-service-property-map) 
shows you the OSGi `@Component` property equivalent---there's an
`@PortletConfiguration` or `@LiferayPortletConfiguration` equivalent setting for
each `@Component` property. 

$$$

The `portletConfig` field's `@Inject` annotation instructs CDI to inject the
bean portlet with a
[`PortletConfig`](https://docs.liferay.com/portlet-api/3.0/javadocs/javax/portlet/PortletConfig.html)
instance specified by the aforementioned annotations.

The `doView` method prints a greeting to the portlet. The
[`@RenderMethod`](https://docs.liferay.com/portlet-api/3.0/javadocs/javax/portlet/annotations/RenderMethod.html)
annotation assures that the `doView` method is executed during the
`RENDER_PHASE` of the portlet lifecycle. The annotation's `include
= "/WEB-INF/jsp/view.jsp"` element specifies a JSP to dispatch to during the
`RENDER_PHASE`. The `portletNames = {FooPortletKeys.Foo}` element associates the
`doView` method with the portlet named `Foo`. 

## Deploying the Bean Portlet [](id=deploying-the-bean-portlet)

Building the portlet is easy. 

    mvn clean package

Then deploy the portlet by copying the portlet WAR to the `[Liferay
Home]/deploy` folder. The
[WAB Generator](/develop/tutorials/-/knowledge_base/7-1/using-the-wab-generator)
converts the WAR to an OSGi Web Application Bundle (WAB) and installs it to
Liferay's OSGi container. @product@ logs the deployment. 

    INFO  [main][HotDeployImpl:226] Deploying com.mycompany.demo.bean.portlet from queue
    INFO  [main][PluginPackageUtil:1001] Reading plugin package for com.mycompany.demo.bean.portlet
    ...
    INFO  [main][PortletHotDeployListener:181] 1 bean portlets for com.mycompany.demo.bean.portlet are available for use

The bean portlet is now available in the @product@ UI. The example portlet is in
the Widget category you assigned it.

![Figure 1: The Foo portlet prints the message returned from `doView` method and shows the included JSP's contents.](../../../images/portlet-3-portlet.png)

Congratulations on creating and deploying a CDI bean portlet!

## Related Topics [](id=related-topics)

[Liferay MVC Portlet](/develop/tutorials/-/knowledge_base/7-1/liferay-mvc-portlet)

[Liferay Soy Portlet](/develop/tutorials/-/knowledge_base/7-1/liferay-soy-portlet)

[Tooling](/develop/tutorials/-/knowledge_base/7-1/tooling)

[Using the WAB Generator](/develop/tutorials/-/knowledge_base/7-1/using-the-wab-generator)

[Resolving Third Party Library Package Dependencies](/develop/tutorials/-/knowledge_base/7-1/adding-third-party-libraries-to-a-module)

[Troubleshooting](/develop/tutorials/-/knowledge_base/7-1/troubleshooting)
