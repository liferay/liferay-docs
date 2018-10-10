# Packaging a JSF Application [](id=packaging-a-jsf-application)

Developers creating portlets for @product-ver@ can package their portlets as
Java EE style Web Application ARchive (WAR) artifacts or as Java ARchive (JAR)
OSGi bundle artifacts. JSF portlet developers, however, must package their
portlets as WAR artifacts because the JSF framework expects a WAR layout and
often requires the `WEB-INF/faces-config.xml` descriptor and other Java EE
resources such as the `WEB-INF/web.xml` descriptor.

Liferay provides a way for these WAR-styled portlets to be deployed and treated
like OSGi modules by Liferay's OSGi runtime. The WAB Generator does this
automatically by converting your WAR artifact to a WAB at deployment time. You
can learn more about WABs and the WAB Generator in the
[Using the WAB Generator](/develop/tutorials/-/knowledge_base/7-1/using-the-wab-generator)
tutorial.

This is how a JSF WAR artifact is structured: 

- `jsf-portlet`
    - `src`
        - `main`
            - `java`
                - Java Classes
            - `resources`
                - Properties files
            - `webapp`
                - `WEB-INF/`
                    - `classes/`
                        - Class files and related properties
                    - `lib/`
                        - JAR dependencies
                    - `resources/`
                        - CSS, XHTML, PNG or other frontend files
                    - `views/`
                        - XHTML views
                    - `faces-config.xml`
                    - `liferay-display.xml`
                    - `liferay-plugin-package.properties`
                    - `liferay-portlet.xml`
                    - `portlet.xml`
                    - `web.xml`

Next, you'll begin creating a simple JSF application that is deployable to
@product@.
