---
header-id: creating-a-bean-portlet
---

# Creating a Bean Portlet

[TOC levels=1-4]

Your first step in developing a Bean Portlet is to create one. Here you'll
generate a Bean Portlet project and deploy your Bean Portlet to @product@. 

1.  Generate a Bean Portlet project using a Maven command like this: 

        mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.cdi.bean.portlet \
        -DarchetypeVersion=1.0.0 \
        -DgroupId=com.mycompany \
        -DartifactId=com.mycompany.demo.bean.portlet

    Here's the resulting folder structure for a Bean Portlet class named `Foo`:

    -   `com.mycompany.demo.bean.portlet` &rarr; Arbitrary project name.
        -   `src/main/java/`
            -   `com.mycompany.constants.FooPortletKeys` &rarr; Declares portlet 
                constants.
            -   `com.mycompany.portlet.FooPortlet` &rarr; Bean Portlet class.
        -   `src/main/webapp/WEB-INF/`
            -   `jsp/view.jsp` &rarr; Default view template.
            -   `beans.xml` &rarr; Signals CDI to scan the portlet for 
                annotations.
            
        - `pom.xml` &rarr; Specifies the project's dependencies and packaging.

    Here's the example Bean Portlet class:

    ```java
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
    ```

2.  Set any portlet configuration or Liferay portlet configuration values
    using
    [`@PortletConfiguration` and `Liferay@PortletConfiguration` attributes](/docs/7-2/reference/-/knowledge_base/r/portlet-descriptor-to-osgi-service-property-map). 

3.  Inject any CDI beans using the `@Inject` annotation. 

4.  Update your render method `doView` (it's annotated with
    `@RenderMethod`). It displays the template `WEB-INF/jsp/view.jsp` by
    default. 

5.  Add any other logic you like to your portlet class. 

6.  Build your portlet: 

        mvn clean package

7.  Deploy your portlet by copying the portlet WAR to your `[Liferay
    Home]/deploy` folder. The
    [WAB Generator](/docs/7-2/frameworks/-/knowledge_base/f/using-the-wab-generator)
    converts the WAR to an OSGi Web Application Bundle (WAB) and installs it to
    Liferay's OSGi container. 
 
@product@ logs the deployment. 

    INFO  [main][HotDeployImpl:226] Deploying com.mycompany.demo.bean.portlet from queue
    INFO  [main][PluginPackageUtil:1001] Reading plugin package for com.mycompany.demo.bean.portlet
    ...
    INFO  [main][PortletHotDeployListener:181] 1 bean portlets for com.mycompany.demo.bean.portlet are available for use

The Bean Portlet is now available in the @product@ UI. The example portlet is in
the Widget category you assigned it. 

![Figure 1: The Foo portlet prints the message returned from `doView` method and shows the included JSP's contents.](../../../images/portlet-3-portlet.png)

Congratulations on creating and deploying a Bean Portlet! 

## Related Topics 

[OSGi CDI Integration](/docs/7-2/frameworks/-/knowledge_base/f/osgi-cdi-integration) 
