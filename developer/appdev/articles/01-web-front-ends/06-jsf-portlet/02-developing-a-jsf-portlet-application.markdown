# Developing a JSF Portlet Application

To run an existing JSF webapp on @product@, you must leverage the Liferay Faces
project. The Liferay Faces Bridge enables you to deploy JSF web apps as portlets
without writing portlet-specific code. You must also provide portlet-specific
descriptor files to make it compatible with the @product@ platform. The easiest
way to do this is by generating a Liferay JSF Portlet project and migrating your
code to it. Then you can deploy your new JSF portlet project to @product@. 

Follow these steps:

1.  Create a JSF project. The following Maven archetypes are available:

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

    Choose the archetype that matches your webapp's JSF component suite. The
    above archetypes support both Gradle and Maven development by providing a
    `build.gradle` and `pom.xml`, respectively. See the
    [Creating a Project](/docs/7-2/reference/-/knowledge_base/r/creating-a-project#maven)
    article for information on how to generate a project.

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

2.  
























