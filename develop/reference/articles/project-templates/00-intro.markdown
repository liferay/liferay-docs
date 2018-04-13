# Project Templates [](id=project-templates)

Liferay provides project templates that you can use to generate starter projects
formatted in an opinionated way. These templates can be used by most build tools
(e.g., Gradle, Maven, Liferay @ide@) to generate your desired project structure.

Some popular project templates include

- API project
- Fragment project
- MVC Portlet project
- Service Builder project
- Template Context Contributor project
- Theme project (WAR)
- etc.

If you're using [Blade CLI](/develop/tutorials/-/knowledge_base/7-1/blade-cli),
execute the following command to display a full list of project templates:

    blade create -l

If you're using [Maven](/develop/tutorials/-/knowledge_base/7-1/maven), you can
view and use the project templates as Maven archetypes. Execute the following
command to list them:

    mvn archetype:generate -Dfilter=liferay

Archetypes with the `com.liferay.project.templates` prefix are the latest
templates offered by Liferay.

If you're using
[Liferay @ide@](/develop/tutorials/-/knowledge_base/7-1/liferay-ide), navigate
to *File* &rarr; *New* &rarr; *Liferay Module Project* and view the project
templates from the *Project Template Name* drop-down menu.

In this section of reference articles, each project template is outlined with
the appropriate generation command and folder structure. Visit the project
template article you're most interested in to start building your own project!
