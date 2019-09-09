---
header-id: generating-new-projects-using-archetypes
---

# Generating New Projects Using Archetypes

[TOC levels=1-4]

Creating Maven projects from scratch can be a lot of work. What dependencies
does my Liferay portlet project need? What does a Liferay Maven Service Builder
project look like? How do I create a Liferay Maven-based context contributor?
These questions can be answered with three words: Liferay Maven Archetypes.

Liferay provides a slew of Maven archetypes to easily create Liferay projects.
In this tutorial, you'll learn how to use Liferay's Maven archetypes to generate
a project.

At the time of this writing, Liferay provides approximately 60 Maven archetypes
for you to use; expect this number to continue growing! These archetypes are
generated from the Central Repository, unless you've configured for them to be
generated from another remote repository (e.g., 
[Liferay Repository](https://repository.liferay.com). You can view the
Liferay-provided Maven archetypes by running the following command:

    mvn archetype:generate -Dfilter=liferay

The generated archetypes are not all intended for the latest @product@ release.
Some are intended for earlier versions of Liferay Portal (e.g., 7.0, 6.2). For
example, archetypes with the `com.liferay.maven.archetypes` prefix are legacy
archetypes targeted for Liferay Portal 6.2. Those prefixed with
`com.liferay.project.templates.[TYPE]` or `com.liferay.faces.archetype:[TYPE]`
are compatible with @product-ver@.

<!-- TODO: Monitor the archetypes; updates may be required for the above text
for upcoming 7.1 Maven archetypes. -Cody -->

Here's a brief list of some popular Maven archetypes provided by Liferay:

- [Activator](/docs/7-1/reference/-/knowledge_base/r/activator-template)
- [Fragment](/docs/7-1/reference/-/knowledge_base/r/using-the-fragment-template)
- [MVC Portlet](/docs/7-1/reference/-/knowledge_base/r/using-the-mvc-portlet-template)
- [npm Angular Portlet](/docs/7-1/reference/-/knowledge_base/r/npm-angular-portlet-template)
- [npm React Portlet](/docs/7-1/reference/-/knowledge_base/r/npm-react-portlet-template)
- [Panel App](/docs/7-1/reference/-/knowledge_base/r/panel-app-template)
- [Portlet Provider](/docs/7-1/reference/-/knowledge_base/r/portlet-provider-template)
- [Service Builder](/docs/7-1/reference/-/knowledge_base/r/using-the-service-builder-template)
- [Soy Portlet](/docs/7-1/reference/-/knowledge_base/r/soy-portlet-template)
- [Theme](/docs/7-1/reference/-/knowledge_base/r/theme-template)
- and many more...

<!-- TODO: readd JSF archetype, when available -Cody.

- [Liferay Faces](develop/tutorials/-/knowledge_base/7-1/jsf-portlets-with-liferay-faces)
  portlets
  
-->

For documentation on the archetypes (project templates) compatible with
@product-ver@, see the
[Project Templates](/docs/7-1/reference/-/knowledge_base/r/project-templates)
reference section. Visit Maven's
[Archetype Generation](http://maven.apache.org/archetype/maven-archetype-plugin/generate-mojo.html)
documentation for further details on how to modify the Maven archetype
generation process.

| **Note:** If you're creating a JSF portlet using Liferay Faces, you can find
| example archetype declarations for JSF component suites at
| [http://www.liferayfaces.org](http://www.liferayfaces.org/).

Here's an example that creates a Liferay MVC portlet using its Liferay Maven
archetype.

1.  On the command line, navigate to where you want your Maven project. Run
    the Maven archetype generation command filtered for Liferay archetypes only:

        mvn archetype:generate -Dfilter=liferay

2.  Select the `com.liferay.project.templates.mvc.portlet` archetype by
    choosing its corresponding number (e.g., `11`).

    In most cases, you should choose the latest archetype version. The archetype
    versions provided are compatible with all 7.x versions of @product@.

3.  Depending on the Maven archetype you selected, you're given a set of
    archetype options to fill out for your Maven project. For the MVC portlet
    archetype, you could use these properties: 

    - `groupId`: `com.liferay`
    - `artifactId`: `com.liferay.project.templates.mvc.portlet`
    - `version`: `1.0.6`
    - `package`: `com.liferay.docs`
    - `className`: `SampleMVC`

    Once you've filled out the required property values, you're given a summary
    of the properties configuration you defined. Enter `Y` to confirm your
    project's configuration.

Your Maven project is generated and available from the folder you ran the
archetype generation command from. If you have an existing parent `pom.xml` file
in that folder, your module project is automatically accounted for there:

    <modules>
        ...
        <module>com.liferay.project.templates.mvc.portlet</module>
    </modules>

The Liferay Maven archetypes generate deployable Liferay projects, but they're
bare bones and likely require further customizations.

If you want to generate a quick foundation for a Liferay project built with
Maven, using Liferay Maven archetypes is your best option.
