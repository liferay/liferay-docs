---
header-id: portletmvc4spring-project-anatomy
---

# PortletMVC4Spring Project Anatomy

[TOC levels=1-4]

PortletMVC4Spring portlets are packaged in WARs. Liferay provide Maven archetypes for creating projects configured to use JSP/JSPX and Thymeleaf templates. Their commands are listed below. The PortletMVC4Spring project structure follows the commands. 

## Maven Commands for Generating PortletMVC4Spring Projects

Here are Maven commands for 
generating PortletMVC4Spring portlet projects that use JSPX and
[Thymeleaf](https://www.thymeleaf.org)
View templates: 

### SP/JSPX Form Portlet

```bash
mvn archetype:generate \
-DarchetypeGroupId=com.liferay.portletmvc4spring.archetype \
-DarchetypeArtifactId=com.liferay.portletmvc4spring.archetype.form.jsp.portlet \
-DarchetypeVersion=5.1.0 \ 
-DgroupId=com.mycompany \ 
-DartifactId=com.mycompany.my.form.jsp.portlet
```

### Thymeleaf Form Portlet

```bash
mvn archetype:generate \
-DarchetypeGroupId=com.liferay.portletmvc4spring.archetype \
-DarchetypeArtifactId=com.liferay.portletmvc4spring.archetype.form.thymeleaf.portlet \
-DarchetypeVersion=5.1.0 \
-DgroupId=com.mycompany \
-DartifactId=com.mycompany.my.form.thymeleaf.portlet
```

## Project Structure 

The Maven commands generate a project that includes Model and Controller
classes, View templates, a resource bundle, a stylesheet, and more. The
[Spring contexts and configuration files](/docs/7-2/reference/-/knowledge_base/r/portletmvc4spring-configuration-files)
set PortletMVC4Spring development essentials. Here's the resulting project
structure: 

-   `[com.mycompany.my.form.jsp.portlet]`/ &rarr; Arbitrary project name
    -   `src/`
        -   `main/`
            -   `java/[my-package-path]/`
                -   `controller/` &rarr; Sub-package for Controller classes 
                    (optional)
                -   `dto/` &rarr; Sub-package for Model (data transfer 
                    object) classes (optional)
                -    `resources/` &rarr; Resources to include in the class 
                    path 
                    -   `content/` &rarr; Resource bundles 
                    -   `log4j.properties` &rarr; Log4J logging 
                        configuration
                -   `webapp/` 
                    -   `resources/`
                        -   `css/` &rarr; Style sheets
                        -   `images/` &rarr; Images 
                    -   `WEB-INF/`
                        -   `spring-context/` &rarr; Contexts
                            -   `portlet/` &rarr; Portlet contexts 
                                -   `portlet1-context.xml` &rarr; Portlet 
                                    context
                            -   `portlet-application-context.xml` &rarr; 
                                Application context
                        -   `views/` &rarr; View templates 
                        -   `liferay-display.xml` &rarr; Portlet display 
                            configuration 
                        -   `liferay-plugin-package.properties` &rarr; 
                            Packaging descriptor 
                        -   `liferay-portlet.xml` &rarr; Liferay-specific 
                            portlet configuration 
                        -   `portlet.xml` &rarr; Portlet configuration
                        -   `web.xml` &rarr; Web application configuration
        -   `test/java/` &rarr; Test source files
    -   `build.gradle` &rarr; Gradle build file 
    -   `pom.xml` &rarr; Maven POM
