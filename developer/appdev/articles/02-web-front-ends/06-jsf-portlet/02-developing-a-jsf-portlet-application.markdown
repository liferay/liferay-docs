---
header-id: developing-a-jsf-portlet-application
---

# Developing a JSF Portlet Application

[TOC levels=1-4]

To run an existing JSF web app on @product@, you must leverage the Liferay Faces
project. The
[Liferay Faces Bridge](/docs/7-2/reference/-/knowledge_base/r/understanding-liferay-faces-bridge)
enables you to deploy JSF web apps as portlets without writing portlet-specific
code. You must also provide portlet-specific descriptor files to make it
compatible with the @product@ platform. The easiest way to do this is by
generating a new Liferay JSF Portlet project and migrating your code to it. Then
you can deploy your new JSF portlet project to @product@. 

Follow these steps:

1.  Create a new JSF portlet project. The following Maven archetypes are
    available:

    - `com.liferay.faces.archetype.alloy.portlet` (Liferay Faces Alloy portlet)
    - `com.liferay.faces.archetype.bootsfaces.portlet` (Liferay BootsFaces
      portlet)
    - `com.liferay.faces.archetype.butterfaces.portlet` (Liferay ButterFaces
      portlet)
    - `com.liferay.faces.archetype.icefaces.portlet` (Liferay ICEFaces portlet)
    - `com.liferay.faces.archetype.jsf.portlet` (Liferay JSF portlet)
    - `com.liferay.faces.archetype.primefaces.portlet` (Liferay PrimeFaces
      portlet)
    - `com.liferay.faces.archetype.richfaces.portlet` (Liferay RichFaces
      portlet)

    Choose the archetype that matches your web app's JSF component suite. For
    example,

    ```bash
    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay.faces.archetype \
        -DarchetypeArtifactId=com.liferay.faces.archetype.jsf.portlet \
        -DarchetypeVersion=5.0.6 \
        -DgroupId=com.mycompany \
        -DartifactId=com.mycompany.my.jsf.portlet
    ```

    The above archetypes support both Gradle and Maven development by providing
    a `build.gradle` and `pom.xml`, respectively. For more information, visit
    [faces.liferay.dev](https://faces.liferay.dev).
    
    Here's the resulting project structure for a JSF Standard portlet:

    - [liferay-jsf-portlet]/ &rarr; Arbitrary project name
        - `src/`
            - `main/`
                - `java/[my-package-path]/`
                    - `bean/` &rarr; Sub-package for managed Java beans
                      (optional)
                    - `dto/` &rarr; Sub-package for model (data transfer 
                      object) classes (optional)
                - `resources/` &rarr; Resources to include in the class path
                    - `i18n.properties` &rarr; Internationalization
                      configuration
                    - `log4j.properties` &rarr; Log4J logging configuration
                - `webapp/`
                    - `resources/`
                        - `images/` &rarr; Images
                    - `WEB-INF/`
                        - `resources/` Frontend files (e.g., CSS, JS, XHTML,
                          etc.) that shouldn't be accessed directly by the
                          browser
                            - `css/` &rarr; Stylesheets
                        - `views/` &rarr; View templates
                        - `faces-config.xml` &rarr; JSF application
                          configuration file
                        - `liferay-display.xml` &rarr; Portlet display
                          configuration
                        - `liferay-plugin-package.properties` &rarr; Packaging
                           descriptor
                        - `liferay-portlet.xml` &rarr; Liferay-specific portlet
                          configuration
                        - `portlet.xml` &rarr; Portlet configuration
                        - `web.xml` &rarr; Web application configuration
        - `test/java/` &rarr; Test source files

2.  Update your dependencies as desired. The generated portlet already includes
    the required artifacts required to deploy a simple JSF portlet to @product@.
    For example, the
    [Liferay Faces Bridge](/docs/7-2/reference/-/knowledge_base/r/understanding-liferay-faces-bridge)
    artifacts look like this:

    **Maven:**

    ```xml
    <dependencies>
        <dependency>
            <groupId>com.liferay.faces</groupId>
            <artifactId>com.liferay.faces.bridge.ext</artifactId>
            <version>5.0.4</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>com.liferay.faces</groupId>
            <artifactId>com.liferay.faces.bridge.impl</artifactId>
            <version>4.1.3</version>
            <scope>runtime</scope>
        </dependency>
    </dependencies>
    ```

    **Gradle:**

    ```groovy
    dependencies {
        runtime group: 'com.liferay.faces', name: 'com.liferay.faces.bridge.ext', version: '5.0.4'
        runtime group: 'com.liferay.faces', name: 'com.liferay.faces.bridge.impl', version: '4.1.3'
    }
    ```

3.  Copy your Java classes to the new `java/[my-package-path]/` folder.

4.  Copy your view templates to the new `src/main/webapp/WEBINF/views` folder.

5.  Add your frontend files (e.g., CSS, JS, etc.) that shouldn't be accessed
    directly by the browser to the `webapp/WEB-INF/resources/` folder. For
    example, your web app's CSS files would reside in the
    `webapp/WEB-INF/resources/css` folder.

6.  Add your image files to the `webapp/resources/images` folder.

7.  Add localized messages to the `resources/i18n.properties` file. The messages
    in the `i18n.properties` file can be accessed via the Expression Language
    using the implicit `i18n` object provided by Liferay Faces Util. The `i18n`
    object can access messages both from a resource bundle defined in the
    portlet's `portlet.xml` file, and from @product@'s `Language.properties`
    file.

8.  Configure your portlet's logging configuration as desired. The
    `log4j.properties` file in the `src/main/resources` folder sets properties
    for the Log4j logging utility defined in your JSF portlet (i.e.,
    `faces-config.xml`).

9.  Replace your new JSF portlet's `webapp/WEB-INF/faces-config.xml` with your
    web app's `faces-config.xml` file. The `faces-config.xml` file is a JSF
    portlet's application configuration file, which is used to register and
    configure objects and navigation rules.

10. Replace your new JSF portlet's `webapp/WEB-INF/web.xml` with your web app's
    `web.xml` file. The `web.xml` file serves as a deployment descriptor that
    provides necessary configurations for your JSF portlet to deploy and
    function in @product@. 

    Make sure the Faces Servlet is configured in your `web.xml`:

    ```xml
    <servlet>
        <servlet-name>Faces Servlet</servlet-name>
        <servlet-class>javax.faces.webapp.FacesServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>
    ```

    This is required to initialize JSF and should be defined in all JSF portlets
    deployed to @product@.

11. Modify your `webapp/WEB-INF/portlet.xml` as desired. The `portlet.xml`
    descriptor describes the portlet to the portlet container. For example, it
    describes portlet info, security settings, etc. Also, the
    `javax.portlet.faces.GenericFacesPortlet` is defined here, which handles
    invocations to your JSF portlet and makes your portlet, since it relies on
    Liferay Faces Bridge, easy to develop by acting as a turnkey implementation.

    The `init-param` is also defined here, which ensures your portlet is visible
    when deployed to @product@ by pointing to your default view template:

    ```xml
    <init-param>
        <name>javax.portlet.faces.defaultViewId.view</name>
        <value>/WEB-INF/views/view.xhtml</value>
    </init-param>
    ```

12. Modify your `webapp/WEB-INF/liferay-portlet.xml` as desired. It specifies 
    additional information @product@ uses to enhance your portlet: supported
    security roles, portlet icon, CSS and JavaScript locations, and more. The
    [liferay-portlet-app DTD](@platform-ref@/7.2-latest/definitions/liferay-portlet-app_7_2_0.dtd.html)
    defines the `liferay-portlet.xml` elements.

13. Modify your `webapp/WEB-INF/liferay-display.xml` as desired. It configures 
    characteristics for displaying your portlet. For example, this
    `liferay-display.xml` snippet specifies the Widget category in the Add
    Widget menu: 

    ```xml
    <?xml version="1.0"?>
    <!DOCTYPE display PUBLIC "-//Liferay//DTD Display 7.2.0//EN" "http://www.liferay.com/dtd/liferay-display_7_2_0.dtd">

    <display>
    <category name="category.sample">
        <portlet id="jsf-portlet" />
    </category>
    </display>
    ```

14. Modify your `webapp/WEB-INF/liferay-plugin-package.properties` as desired. 
    It describes the portlet application's packaging and version information
    and specifies any required OSGi metadata. For example, this
    `liferay-plugin-package.properties` snippet tells the OSGi container not to
    scan for CDI annotations in @product@.

    ```properties
    -cdiannotations:
    ```

    This is required for JSF portlets leveraging CDI deployed to @product@. They
    must reference their own included CDI implementation.

    On deploying the WAR file, the
    [WAB Generator](/docs/7-2/customization/-/knowledge_base/c/deploying-wars-wab-generator)
    adds the specified OSGi metadata to the resulting web application bundle
    (WAB) that's deployed to @product@'s runtime framework.

    The
    [liferay-plugin-package reference document](@platform-ref@/7.2-latest/propertiesdoc/liferay-plugin-package_7_2_0.properties.html)
    describes the `liferay-plugin-package.properties` file. 

15. [Build and deploy your project](/docs/7-2/reference/-/knowledge_base/r/deploying-a-project).

@product@ logs the deployment. 

```
2019-05-30 14:10:59.405 INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:261] Processing guestbook-jsf-portlet.war
...
2019-05-30 14:11:11.401 INFO  [fileinstall-C:/liferay-ce-portal-7.2.0-ga1/osgi/war][BaseDeployer:877] Deploying guestbook-jsf-portlet.war
...
2019-05-30 14:11:26.379 INFO  [fileinstall-C:/liferay-ce-portal-7.2.0-ga1/osgi/war][BundleStartStopLogger:39] STARTED guestbook-jsf-portlet_7.2.0.1 [2155]
...
2019-05-30 14:11:67.569 INFO  [fileinstall-C:/liferay-ce-portal-7.2.0-ga1/osgi/war][PortletHotDeployListener:288] 1 portlet for guestbook-jsf-portlet is available for use
```

The portlet is now available in the @product@ UI. Find your portlet by selecting
the *Add* icon (![Add](../../../images/icon-add-app.png)) and navigating to
*Widgets* and the category you specified (*Sample* is the default category).

Great! You've successfully developed a Liferay JSF portlet and migrated your
web app logic to it.
