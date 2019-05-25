---
header-id: improved-developer-tooling-liferay-workspace-maven-plugins-and-more
---

# Improved Developer Tooling: Liferay Workspace, Maven Plugins and More

[TOC levels=1-4]

Creating applications is fun when you have the right tools. Here are some key
ingredients:

- Rich templates for stubbing out projects
- Extensible build environments that offer state-of-the-art plugins
- Deployment and runtime management tools
- Application upgrade automation

[Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/liferay-workspace)
(Workspace) has all these things. It's a Gradle-based development
environment that integrates with
[Liferay @ide@](/docs/7-1/tutorials/-/knowledge_base/t/liferay-ide) and can be
used in conjunction with other IDEs, such as a "vanilla" Eclipse, IntelliJ, or
NetBeans. You can extend Workspace's Gradle environment with additional
[Gradle plugins](/docs/7-1/reference/-/knowledge_base/r/gradle) for testing,
code coverage analysis, and more.

If you prefer [Maven](/docs/7-1/tutorials/-/knowledge_base/t/maven) over
Gradle, you can use the
[Maven-based Workspace](/docs/7-1/tutorials/-/knowledge_base/t/maven-workspace)
instead. @product-ver@'s' lean artifacts and new project
[archetypes](/docs/7-1/tutorials/-/knowledge_base/t/generating-new-projects-using-archetypes)
and [Maven plugins](/docs/7-1/reference/-/knowledge_base/r/maven) make
@product@ development with Maven easier than ever.

Workspace comes with
[Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/blade-cli): a command line
tool for creating and deploying projects, managing the runtime environment, and
more. It provides all kinds of 
[project templates](/docs/7-1/tutorials/-/knowledge_base/t/creating-projects-with-blade-cli#project-templates),
to create modules for developing in any Gradle or Maven environment. 

Liferay's tools also streamline the application upgrade process. Liferay @ide@'s
[Code Upgrade Tool](/docs/7-1/tutorials/-/knowledge_base/t/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)
adapts traditional plugins to @product-ver@ APIs. The
[Liferay Theme Generator](/docs/7-1/tutorials/-/knowledge_base/t/creating-themes) 
[migrates](/docs/7-0/tutorials/-/knowledge_base/t/migrating-a-6-2-theme-to-liferay-7)
themes and layout templates to use the new Liferay JS Theme Toolkit and 
[adapts](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-6-2-themes-intro) 
them to @product-ver@.

Here are the tooling improvement topics:

- [Moving from the Plugins SDK to Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/from-the-plugins-sdk-to-liferay-workspace)
- [Developing projects with Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/developing-modules-with-liferay-workspace)
- [What's new for Maven Users](/docs/7-1/tutorials/-/knowledge_base/t/whats-new-for-maven-users)
- [Using other build systems and IDEs](/docs/7-1/tutorials/-/knowledge_base/t/using-other-build-systems-and-ides)
