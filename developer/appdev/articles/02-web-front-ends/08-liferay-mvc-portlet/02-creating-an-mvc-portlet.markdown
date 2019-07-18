---
header-id: creating-an-mvc-portlet
---

# Creating an MVC Portlet

[TOC levels=1-4]

Generating MVC portlet projects is a snap using Liferay's project templates.
Here you'll generate an MVC Portlet project and deploy the portlet to @product@. 

1.  Generate an [MVC Portlet project](/docs/7-2/reference/-/knowledge_base/r/using-the-mvc-portlet-template)
    using a Gradle or Maven.

    Here's the resulting folder structure for an MVC Portlet class named
    `MyMvcPortlet` in a base package `com.liferay.docs.mvcportlet`: 

    - `my-mvc-portlet-project` &rarr; Arbitrary project name.
        - `gradle`
            - `wrapper`
                - `gradle-wrapper.jar`
                - `gradle-wrapper.properties`
        - `src`
            - `main`
                - `java`
                    - `com/liferay/docs/mvcportlet`
                        - `constants`
                            - `MyMvcPortletKeys.java` &rarr; Declares portlet constants.
                        -  `portlet`
                            - `MyMvcPortlet.java` &rarr; MVC Portlet class.
                - `resources`
                    - `content`
                        - `Language.properties` &rarr; Resource bundle
                    - `META-INF`
                        - `resources`
                            - `init.jsp` &rarr; Imports classes and taglibs and defines commonly used objects from the theme and the portlet.
                            - `view.jsp` &rarr; Default view template.
        - `bnd.bnd` &rarr; OSGi bundle metadata.
        - `build.gradle`
        - `gradlew`

    The Maven-generated project includes a `pom.xml` file and does not include
    the Gradle-specific files, but otherwise is exactly the same.

    Here's the resulting MVC Portlet class: 

```java 
package com.liferay.docs.mvcportlet.portlet;

import com.liferay.docs.mvcportlet.constants.MyMvcPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import javax.portlet.Portlet;
import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=my-mvc-portlet-project Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + MyMvcPortletKeys.MyMvc,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class MyMvcPortlet extends MVCPortlet {
}
```

The class extends
[`MVCPortlet`](@@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.html).
The
[`@Component`](https://osgi.org/javadoc/r6/residential/org/osgi/service/component/annotations/Component.html)
annotation and `service = Portlet.class` attribute makes the class an OSGi
Declarative Services component that provides the
[`javax.portlet.Portlet`](https://docs.liferay.com/portlet-api/3.0/javadocs/javax/portlet/Portlet.html)
service type. The `immediate = true` attribute activates the service immediately
on the portlet's deployment. 

2.  Set any portlet configuration or Liferay portlet configuration values
    using `javax.portlet.*` and `com.liferay.portlet.*` `@Component` annotation properties 
    [`javax.portlet.*` and `com.liferay.portlet.*` `@Component` annotation properties](/docs/7-2/reference/-/knowledge_base/r/portlet-descriptor-to-osgi-service-property-map)
    respectively. 

    Here are the example component's properties: 

    -   `"com.liferay.portlet.display-category=category.sample"`: Sets the 
        Widget's category to "Sample". 

    -   `"com.liferay.portlet.instanceable=true"`: Activates the component 
        immediately when its bundle installs. 

    -   `"javax.portlet.display-name=my-mvc-portlet-project Portlet"`: Sets the 
        portlet's Widget name. 

    -   `"javax.portlet.init-param.template-path=/"`: The path under
        `src/main/resources/META-INF/resources/` where the templates reside. 

    -   `"javax.portlet.init-param.view-template=/view.jsp"`: Default view 
        template. 

    -   `"javax.portlet.name=" + MyMvcPortletKeys.MyMvc`: The portlet's unique 
        identity. 

    -   `"javax.portlet.resource-bundle=content.Language"`: Sets the portlet's 
        [resource bundle](/docs/7-2/frameworks/-/knowledge_base/f/localization)
        to the `content/Language*.properties` file(s) in the
        `src/main/resources/` folder. 

    -   `"javax.portlet.security-role-ref=power-user,user"`: Makes the @product@
        virtual instance's power user and user Roles available for defining the
        portlet's permissions. 

3.  The portlet renders content via the view template 
    `src/main/resources/META-INF/resources/view.jsp` by default. 

4.  Build your project. 

    *Gradle:* 

    ```bash
    gradlew jar
    ```

    *Maven:* 

    ```bash
    mvn clean package
    ```

4.  Deploy the project [using your build
    environment](/docs/7-2/reference/-/knowledge_base/r/deploying-a-project) or
    by building the project JAR and copying it to the `deploy/` folder in your
    [Liferay Home](/docs/7-2/deploy/-/knowledge_base/d/liferay-home). 

The MVC Portlet is now available in the @product@ UI, in the Widget category you
assigned it. 

![Figure 1: The example portlet shows a message defined by the language property `yourmvc.caption=Hello from YourMVC!` in the Language.properties file.](../../../images/default-mvc-portlet-on-page.png)

Congratulations on creating and deploying an MVC Portlet! 

## Related Topics 

[Writing MVC Portlet Controller Code](/docs/7-2/appdev/-/knowledge_base/a/writing-mvc-portlet-controller-code)

[Configuring the View Layer](/docs/7-2/appdev/-/knowledge_base/a/configuring-the-view-layer)

[MVC Action Command](/docs/7-2/appdev/-/knowledge_base/a/mvc-action-command)

[MVC Render Command](/docs/7-2/appdev/-/knowledge_base/a/mvc-render-command)

[MVC Resource Command](/docs/7-2/appdev/-/knowledge_base/a/mvc-resource-command)
