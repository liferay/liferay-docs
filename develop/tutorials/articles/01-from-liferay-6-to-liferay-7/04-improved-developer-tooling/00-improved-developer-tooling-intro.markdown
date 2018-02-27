# Improved Developer Tooling: Liferay Workspace, Maven Plugins and More [](id=improved-developer-tooling-liferay-workspace-maven-plugins-and-more)

Creating applications is fun when you have the right tools. Here are some key
ingredients:

- Rich templates for stubbing out projects
- Extensible build environments that offer state-of-the-art plugins
- Deployment and runtime management tools
- Application upgrade automation

[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/liferay-workspace)
(Workspace) has all these things. It's a Gradle-based development
environment that integrates with
[Liferay @ide@](/develop/tutorials/-/knowledge_base/7-1/liferay-ide) and can be
used in conjunction with other IDEs, such as a "vanilla" Eclipse, IntelliJ, or
NetBeans. You can extend Workspace's Gradle environment with additional
[Gradle plugins](/develop/reference/-/knowledge_base/7-1/gradle) for testing,
code coverage analysis, and more.

If you prefer [Maven](/develop/tutorials/-/knowledge_base/7-1/maven) over
Gradle, you can use the
[Maven-based Workspace](/develop/tutorials/-/knowledge_base/7-1/maven-workspace)
instead. @product-ver@'s' lean artifacts and new project
[archetypes](/develop/tutorials/-/knowledge_base/7-1/generating-new-projects-using-archetypes)
and [Maven plugins](/develop/reference/-/knowledge_base/7-1/maven) make
@product@ development with Maven easier than ever.

Workspace comes with
[Blade CLI](/develop/tutorials/-/knowledge_base/7-1/blade-cli): a command line
tool for creating and deploying projects, managing the runtime environment, and
more. It provides all kinds of 
[project templates](/develop/tutorials/-/knowledge_base/7-1/creating-projects-with-blade-cli#project-templates),
to create modules for developing in any Gradle or Maven environment. 

Liferay's tools also streamline the application upgrade process. Liferay @ide@'s
[Code Upgrade Tool](/develop/tutorials/-/knowledge_base/7-1/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)
adapts traditional plugins to @product-ver@ APIs. The
[Liferay Theme Generator](/develop/tutorials/-/knowledge_base/7-1/themes-generator)
tool
[migrates](/develop/tutorials/-/knowledge_base/7-1/migrating-a-6-2-theme-to-liferay-7)
themes and layout templates to the new Node.js-based environment and
[adapts](/develop/tutorials/-/knowledge_base/7-1/upgrading-themes#running-the-upgrade-task-for-themes-generator-themes)
them to @product-ver@.

Here are the tooling improvement topics:

- [Moving from the Plugins SDK to Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/from-the-plugins-sdk-to-liferay-workspace)
- [Developing projects with Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/developing-modules-with-liferay-workspace)
- [What's new for Maven Users](/develop/tutorials/-/knowledge_base/7-1/whats-new-for-maven-users)
- [Using other build systems and IDEs](/develop/tutorials/-/knowledge_base/7-1/using-other-build-systems-and-ides)
