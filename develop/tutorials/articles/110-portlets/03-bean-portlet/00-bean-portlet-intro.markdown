# Bean Portlet - Portlet 3 [](id=bean-portlet-portlet-3)

@product-ver@ GA2 onward supports Portlet 3. Bean portlets are standard Portlet
3 portlets. They let you,

1. Leverage all [Portlet 3 features](https://portals.apache.org/pluto/v301/v3Features.html). 

2. Deploy to portals that support Portlet 3. 

Here you'll learn how to create and deploy a bean portlet. And if you're
familiar with developing OSGi portlets, we'll help you relate what you already
know to bean Portlet development---It's straightforward!

## Creating a bean portlet project [](id=creating-a-bean-portlet-project)

CDI bean portlets have a Maven archetype.

- Group: `com.liferay`
- Artifact: `com.liferay.project.templates.cdi.bean.portlet`
- Version: `1.0.0`

To create a bean portlet project, execute a Maven command like this one:  

`mvn archetype:generate -DarchetypeGroupId=com.liferay -DarchetypeArtifactId=com.liferay.project.templates.cdi.bean.portlet -DarchetypeVersion=1.0.0 -DgroupId=com.mycompany -DartifactId=com.mycompany.demo.bean.portlet`

Maven prompts you for a project author name, version, and portlet class
name. For this example, the defaults are fine for everything else. 

Here's the generated bean portlet project:

- `com.mycompany.demo.bean.portlet` &rarr; Arbitrary project name.
    - `src/main/java`
        - `com.mycompany.constants.FooPortletKeys` &rarr; Declares portlet constants.
        - `com.mycompany.portlet.FooPortlet` &rarr; CDI bean portlet class.
    - `src/main/webapp/WEB-INF/beans.xml` &rarr; Signals CDI to scan the portlet for CDI annotations. 
    - `pom.xml` &rarr; Specifies the project's dependencies and packaging.

The archetype uses the class name you entered in the portlet and
portlet keys class names. 

The bean portlet depends on the Portlet 3 API and  Weld's implementation of
Contexts and Dependency Injection (CDI). Liferay's CDI extension
`com.liferay.bean.portlet.cdi.extension` supports bean portlets. See the
project's `pom.xml` for the complete dependency list. 

+$$$

**Note:** A bean portlet is packaged as a WAR. It differs from a traditional 
portlet and Liferay portlet WAR in that it doesn't have a `portlet.xml` or a
`liferay-portlet.xml` file, for example. The bean portlet's annotations specify
its configuration. 

$$$ 

## Developing a bean portlet [](id=developing-a-bean-portlet)

Bean portlets, like `FooPortlet`, are plain old Java objects (POJOs)---they
don't need to extend anything. Here's the example bean portlet class:

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
[`@PortletConfiguration`](https://portals.apache.org/pluto/portlet-3.0-apidocs/javax/portlet/annotations/PortletConfiguration.html)
annotation configures standard portlets. It requires a `portletName` element,
and has other configuration elements. The `title` element is assigned the key
constant `FooPortletKeys.Foo`. To use a specific locale of the
`FooPortletKeys.Foo` key, for example, you could assign that locale name to a
`locale` element in the `@LocaleString` annotation. 

The `@LiferayPortletConfiguration` annotation specifies Liferay-specific
configuration elements. Assign its `portletName` element the portlet's name.
Assign a `com.liferay.portlet.display-category` property the Widget category in
which to make the portlet available to users. Setting the property
`com.liferay.portlet.instanceable=true` lets users add multiple instances of the
portlet to the site. 

+$$$

**Note:** The `@PortletConfiguration` and `@LiferayPortletConfiguration` 
annotations are synonymous with the `javax.portlet.*` and
`com.liferay.portlet.*` properties in the OSGi `@Component` annotation (used in
[Liferay MVC Portlets](/develop/tutorials/-/knowledge_base/7-1/creating-an-mvc-portlet#creating-a-portlet-component), for example).
If you're familiar with `portlet.xml` and `liferay-portlet.xml` portlet
descriptor files, the
[Portlet Descriptor to OSGi Service Property Map](https://dev.liferay.com/develop/reference/-/knowledge_base/7-1/portlet-descriptor-to-osgi-service-property-map) 
shows you the OSGi `@Component` property equivalent---there's either an
`@PortletConfiguration` or `@LiferayPortletConfiguration` element for each
`@Component` property. 

$$$

The `portletConfig` field's `@Inject` annotation tells the container to inject
it with a 
[`PortletConfig`](https://portals.apache.org/pluto/portlet-3.0-apidocs/javax/portlet/PortletConfig.html)
instance. `PortletConfig` is a Java bean class that makes the portlet's
configuration, including the portlet's name, accessible. 

The `doView` method prints a greeting to the portlet. Its
[`@RenderMethod`](https://portals.apache.org/pluto/portlet-3.0-apidocs/javax/portlet/annotations/RenderMethod.html)
annotation assures that it is executed with each Render phase for the portlet.
The annotation's `include = "/WEB-INF/jsp/view.jsp"` element renders the JSP.
The `portletNames = {FooPortletKeys.Foo}` element associates the `doView` method
with this portlet. 

## Deploying the bean portlet [](id=deploying-the-bean-portlet)

Building the portlet is easy. 

    mvn clean package

Then deploy the portlet, by copying the portlet WAR to the `[Liferay
Home]/deploy` folder. The
[WAB Generator](/develop/tutorials/-/knowledge_base/7-1/using-the-wab-generator)
converts the WAR to an OSGi Web Application Bundle (WAB) and installs it to
Liferay's OSGi container. @product@ logs the deployment. 

    2018-08-06 17:37:43.497 INFO  [main][HotDeployImpl:226] Deploying com.mycompany.demo.bean.portlet from queue
    2018-08-06 17:37:43.498 INFO  [main][PluginPackageUtil:1001] Reading plugin package for com.mycompany.demo.bean.portlet
    ...
    2018-08-06 17:37:43.511 INFO  [main][PortletHotDeployListener:181] 1 bean portlets for com.mycompany.demo.bean.portlet are available for use

The bean portlet is now available in the @prodcut@ UI. The example portlet is in
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
