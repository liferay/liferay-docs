# What's New for Maven Users [](id=whats-new-for-maven-users)

Liferay Portal 7.0+ and Liferay DXP fully support Maven development and offers
several new and improved features: 

- [Liferay Workspace for Maven](/develop/tutorials/-/knowledge_base/7-1/maven-workspace)
- New archetypes
- New Maven plugins
- More granular dependency management

The new archetype `com.liferay.project.templates.workspace` generates a Liferay
Workspace that includes a POM file for developing in Workspace using Maven. You
can develop modules and themes in the Workspace subfolders. 

@product-ver@ provides many new Maven archetypes for various Liferay module
projects. There are over thirty
[Maven archetypes](/develop/reference/-/knowledge_base/7-1/project-templates)
for @product-ver@, and more are in development. Here are some popular ones:

- Configuration Icons
- Fragments
- Menu Buttons
- Portlets
    - MVC
    - npm
    - Soy
    - Spring MVC
- Service Builder
- Themes
- etc...

Liferay's Maven archetypes cover many different Liferay frameworks and
service types. These make Maven a first-class tool for creating Liferay modules
and themes. Visit the 
[Generating New Projects Using Archetypes](/develop/tutorials/-/knowledge_base/7-1/generating-new-projects-using-archetypes)
tutorial to learn more about Liferay's Maven archetypes and how to use them.

Liferay also provides several new and updated 
[Maven plugins](/develop/reference/-/knowledge_base/7-1/maven) that simplify the
build process. For example, the following plugins build style sheets, services,
and themes respectively:

- [CSS Builder](/develop/tutorials/-/knowledge_base/7-1/compiling-sass-files-in-a-maven-project)
- [Service Builder](/develop/tutorials/-/knowledge_base/7-1/using-service-builder-in-a-maven-project)
- [Theme Builder](/develop/tutorials/-/knowledge_base/7-1/building-themes-in-a-maven-project)

@product-ver@'s modularity provides a more granular dependency management
experience. You no longer need to depend on `portal-impl` or `portal-service`
(now `portal-kernel`) for everything. For example, to use @product@'s Wiki
framework, you need only depend on the Wiki module. You set dependencies on
concise modules that provide the functionality you want without inheriting extra
baggage.

Liferay's new Workspace environment, Maven archetypes, Maven plugins, and
streamlined modules make developing on @product@ easier than ever. To learn
more, see the 
[Maven tutorials](/develop/tutorials/-/knowledge_base/7-1/maven).
