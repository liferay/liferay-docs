---
header-id: creating-a-service-builder-project
---

# Creating a Service Builder Project

[TOC levels=1-4]

Adding Service Builder services, and all that [Service
Builder](/docs/7-2/appdev/-/knowledge_base/a/service-builder) offers, to your
application requires generating an API and service project to your application
parent project. 

-   `[project]/[project]-api/` &rarr; Service interfaces.  

-   `[project]/[project]-service/` &rarr; Service implementations and 
    supporting files. 

You do all the work in the `-service` module and then [run Service
Builder](/docs/7-2/appdev/-/knowledge_base/a/running-service-builder) to
generate (or re-generate) the service API based on your changes. Here's how to
create a Service Builder project. 

1.  Decide on a project name. If the project is part of an application, name the
    project after the application. 

2.  Create the Service Builder project using [Blade
    CLI](/docs/7-2/reference/-/knowledge_base/r/blade-cli) and the
    `service-builder` project template, passing your project name as a
    parameter. For example, here are Gradle and Maven commands for creating a
    Service Builder project called `bookmarks`. 

    Gradle: 
    
    ```bash
    blade create -t service-builder -p com.liferay.docs.bookmarks bookmarks
    ```
    
    Maven:
    
    ```bash
    mvn archetype:generate \
    -DarchetypeGroupId=com.liferay \
    -DarchetypeArtifactId=com.liferay.project.templates.service.builder \
    -DgroupId=com.liferay \
    -DartifactId=bookmarks \
    -Dpackage=com.liferay.docs.bookmarks \
    -Dversion=1.0 \
    -DapiPath=com.liferay.api.path \
    -DliferayVersion=7.2
    ```

A message like this one reports project creation success:

```
Successfully created project bookmarks in C:\workspaces_liferay\72-ws\modules
```

Blade CLI generates the parent project folder and sub-folders for the `*-api`
and `*-service` module projects. 

-   `bookmarks/`
    -   `bookmarks-api/`
        -   `bnd.bnd`
        -   `build.gradle`
    -   `bookmarks-service/`
        -   `bnd.bnd`
        -   `build.gradle`
        -   `service.xml` &rarr; Service definition file.
    -   `build.gradle`

Congratulations! You've created your Service Builder project. The `service.xml`
file is where you'll define your model objects (entities) and services. 

## Related Topics 

[Service Builder Samples](/docs/7-2/reference/-/knowledge_base/r/blade-cli)

[Service Builder Gradle Plugin](/docs/7-2/reference/-/knowledge_base/r/service-builder-gradle-plugin)

[Service Builder Maven Plugin](/docs/7-2/reference/-/knowledge_base/r/service-builder-plugin)
