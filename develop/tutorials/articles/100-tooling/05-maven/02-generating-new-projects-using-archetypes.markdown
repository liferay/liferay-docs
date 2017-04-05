# Generating New Projects Using Archetypes [](id=generating-new-projects-using-archetypes)

Creating Maven projects from scratch can be a lot of work. What dependencies
does my Liferay portlet project need? What does a Liferay Maven Service Builder
project look like? How do I create a Liferay Maven-based context contributor?
These questions can be answered with three words: Liferay Maven Archetypes.

Liferay provides a slew of Maven archetypes for easy Liferay module projects. In
this tutorial, you'll learn how to use Liferay's Maven archetypes to generate
your module project.

At the time of this writing, Liferay provides just under 40 Maven archetypes for
you to use; expect this number to continue growing! These archetypes are
generated from the Central Repository, unless you've configured for them to be
generated from another remote repository (e.g., 
[Liferay Repository](/develop/tutorials/-/knowledge_base/7-0/installing-liferay-maven-artifacts#liferay-repository)).
You can view the Liferay-provided Maven archetypes by running the following
command:

    mvn archetype:generate -Dfilter=liferay

The generated archetypes are not all intended for the latest @product@ release.
Some are intended for earlier versions of Liferay Portal (e.g., 6.2, 6.1, etc.).
An easy way to tell if the archetype is compatible with @product-ver@ is by
inspecting the archetype's package name. Archetypes with the
`com.liferay.maven.archetypes` prefix are legacy archetypes. Those prefixed with
`com.liferay.project.templates.[TYPE]` or `com.liferay.faces.archetype:[TYPE]`
are compatible with @product-ver@.

Here's a brief list of some popular Maven archetypes provided by Liferay:

- Activator
- [Context Contributor](/develop/tutorials/-/knowledge_base/7-0/context-contributors)
- [Liferay Faces](/develop/tutorials/-/knowledge_base/7-0/jsf-portlets-with-liferay-faces)
  portlets
    - [Alloy](https://web.liferay.com/community/liferay-projects/liferay-faces/alloy)
    - [ICEfaces](http://www.icesoft.org/java/projects/ICEfaces/overview.jsf)
    - [JSF](https://web.liferay.com/community/liferay-projects/liferay-faces/overview)
    - [PrimeFaces](http://primefaces.org/)
    - [RichFaces](http://richfaces.jboss.org/)
- [MVC Portlet](/develop/tutorials/-/knowledge_base/7-0/liferay-mvc-portlet)
- [Panel App](/develop/tutorials/-/knowledge_base/7-0/customizing-the-product-menu#adding-custom-panel-apps)
- [Portlet Provider](/develop/tutorials/-/knowledge_base/7-0/providing-portlets-to-manage-requests)
- [Service Builder](/develop/tutorials/-/knowledge_base/7-0/what-is-service-builder)
- Service Wrapper
- Vaadin Liferay portlet

For documentation on the archetypes (project templates) compatible with @product-ver@,
see the
[Project Templates](/develop/reference/-/knowledge_base/7-0/project-templates)
reference section. Visit Maven's
[Archetype Generation](http://maven.apache.org/archetype/maven-archetype-plugin/generate-mojo.html)
documentation for further details on how to modify the Maven archetype
generation process.

+$$$

**Note:** If you're creating a JSF portlet using Liferay Faces, you can find
example archetype declarations for JSF component suites at
[http://www.liferayfaces.org](http://www.liferayfaces.org/). 

$$$

Here's an example that creates a Liferay MVC portlet using its Liferay Maven
archetype.

1.  On the command line, navigate to where you want your Maven project. Run
    the Maven archetype generation command filtered for Liferay archetypes only:

        mvn archetype:generate -Dfilter=liferay

2.  Select the `com.liferay.project.templates.mvc.portlet` archetype by
    choosing its corresponding number (e.g., `8`).

    In some cases, an archetype provides multiple versions of itself for you to
    select. Make sure to select the archetype version that corresponds with the
    @product@ instance you're using (e.g., `1.0.0`, `1.0.1`, etc.).

3.  Depending on the Maven archetype you selected, you're given a set of
    archetype options to fill out for your Maven project. For the MVC portlet
    archetype, you could use these properties: 

    - `groupId`: `com.liferay`
    - `artifactId`: `com.liferay.project.templates.mvc.portlet`
    - `version`: `1.0.0`
    - `package`: `com.liferay.docs`
    - `className`: `SampleMVC`

    Once you've filled out the required property values, you're given a summary
    of the properties configuration you defined. Enter `Y` to confirm your
    project's configuration.

Your Maven project is generated and available from the folder for which you ran
the archetype generation command. If you have an existing parent `pom.xml` file
in that folder, your module project is automatically accounted for there:

    <modules>
        ...
        <module>com.liferay.project.templates.mvc.portlet</module>
    </modules>

The Liferay Maven archetypes generate deployable Liferay module projects, but
they're bare bones and likely require further customizations.

If you want to generate a quick foundation for a Liferay module built with
Maven, using Liferay Maven archetypes is your best option.
