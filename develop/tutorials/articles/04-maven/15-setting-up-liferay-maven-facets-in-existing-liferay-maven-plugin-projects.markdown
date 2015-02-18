# Setting Up Liferay Maven Facets in Existing Liferay Maven Plugin Projects [](id=setting-up-liferay-maven-facets-in-existing-liferay-maven-plugin-projects)

Liferay Maven plugin projects created with Liferay IDE (2.0 Milestone 1 and
newer) have all the required Liferay facets, including the new m2e-liferay
plugin. The m2e-liferay plugin provides a project life cycle map for building
and deploying your Liferay project. If your Liferay Maven plugin project was
created outside of Liferay IDE or created in a Liferay IDE earlier than version
2.0 Milestone 1, you must update the project with the latest required
facets, including the m2e-liferay plugin. This tutorial helps you create 
projects with facets and shows you how to import your standalone Maven projects
into Liferay IDE. You'll also learn how to determine whether your project is
missing any required facets, in case you're unsure. 

If you're interested in learning more about the Liferay Maven project facets,
then you'll enjoy the next section. You can otherwise skip ahead to the section
after it to learn how to set up your older Liferay Maven projects to use the
Liferay Maven facets. 

## Understanding Facets for Liferay Maven Plugin Projects [](id=understanding-facets-for-liferay-maven-plugin-projects)

Beginning with version 2.0 Milestone 1, Liferay IDE supports Liferay Maven
projects that use the m2e-liferay Eclipse plugin. Specifically, Liferay IDE
contains a Maven project configurator that enables Liferay Maven plugin
projects to use Liferay facets. Liferay Maven projects with Liferay facets are
recognized as full Liferay IDE projects. 

While it's possible to use a variety of Maven-related mojo plugins to build
Liferay plugin projects, you should use liferay-maven-plugin to build them. Each
Liferay plugin archetype available in Maven Central adds the
liferay-maven-plugin to the build plugin configuration section of the
project's `pom.xml`. Note that most of the Liferay Maven plugins are just `.war`
packages with the liferay-maven-plugin added to their configuration sections. 

To support these projects in Liferay IDE, a mechanism is needed for the IDE to
recognize them as Liferay IDE projects. Non-Maven Liferay IDE projects are
recognized in Eclipse as faceted web projects with the appropriate Liferay
plugin facet. These projects are also Eclipse web projects, which are simply
faceted projects with the web facet installed. To get Liferay Maven plugin
projects recognized by Eclipse and working with the rest of its JEE tooling, the
projects must be faceted as flexible web projects. The m2e-liferay plugin in
concert with the following Eclipse plugins meet these requirements. 

- m2e-core: Maven integration for Eclipse
- m2e-wtp: Maven integration for WTP

The m2e-core plugin contains the standard Maven tooling support for Eclipse. The
main benefits that m2e-core provides are dependency resolution classpath
management and an abstract project configuration framework for adapters. The
m2e-wtp plugin enables projects to be recognized as flexible web projects. This
m2e-wtp plugin provides project configuration mapping between the `.war` package
type the POMs in the Maven model and the flexible web project support in
Eclipse. 

With these Eclipse plugins in place, there's just one piece remaining to make
Liferay Maven projects first-class citizens in Eclipse. The m2e-core plugin
must be explicitly told how to integrate the liferay-maven-plugin, so it can 
add the liferay-maven-plugin goals (prefixed with `liferay:`) to the standard
build life cycle. Normally without the m2e-liferay plugin, m2e-core users have to
specify manually the liferay-metadata-execution mapping themselves. The
m2e-liferay plugin automatically provides this mapping. The m2e-liferay plugin
also provides m2e-core with additional build participation integration to make
sure that Liferay Maven projects are handled properly in the Eclipse build
life cycle. For example, m2e-liferay makes sure that the Liferay Maven goal
`liferay:build-css` is called when Eclipse deploys a Liferay theme plugin. Other
Liferay Maven goals are similarly enabled by m2e-liferay. 

Now that you know the details of what the m2e-liferay plugin provides, you can
appreciate how it facilitates developing Liferay Maven plugin projects in
Liferay IDE. In the next section, you'll learn how to set up your existing
Liferay Maven plugin projects to use the m2e-liferay plugin for Maven life cycle
mapping. 

## Setting Up Liferay Maven Facets in Existing Projects [](id=setting-up-liferay-maven-facets-in-existing-projects)

Making sure that your project has the correct facets and Liferay Maven life cycle
mapping is simple. Just right-click your project and select *Properties*. In the
window that appears, open the *Maven* section on the left and then click
*Lifecycle Mapping*. 

![Figure 1: The life cycle mapping for this Liferay Maven project is correct.](../../images/maven-lifecycle-mapping-correct.png)

If the life cycle mapping is correct, the `liferay:build-css` item has a green
check mark next to it. The Mapping and Source fields for `liferay:build-css` should
also contain configurator and extension, respectively. If the life cycle mapping
isn't correct, then you can install the m2e-liferay plugin by following the
instructions contained in the tutorial
[Using Maven from Liferay IDE](/tutorials/-/knowledge_base/using-maven-from-liferay-ide). 
If you have an existing Liferay Maven plugin project that you'd like to import
into Liferay IDE, the next section explains how to install the m2e-liferay
plugin during the import process. 

### Installing the m2e-liferay Plugin on Importing a Liferay Maven Plugin Project [](id=installing-the-m2e-liferay-plugin-on-importing-a-liferay-maven-plugin-proje)

Importing and installing the m2e-liferay plugin is straightforward when
importing a Liferay Maven project that contains all of the required Liferay
Maven properties. The plugin installer launches immediately after you specify
the project to import, effectively combining these two processes. To begin,
select *File* &rarr; *Import* in Liferay IDE. Then open the *Maven* section in
the window that appears, select *Existing Maven Projects*, and click *Next*. 

![Figure 2: The first step in importing an existing Liferay Maven project.](../../images/maven-ide-import-01.png)

The next step is to specify the project to import. First, select the project's
location in the *Root Directory* field. Once you select a project, its `pom.xml`
appears in the Projects field. Make sure that the checkbox next to the `pom.xml`
is checked. Note that this example imports the project `maven-test`. Since
this project's Liferay properties are contained in a profile in the
`pom.xml`, the profile must be specified in the Profiles field of the Advanced
section. You don't need to specify a profile here if the Liferay properties are
contained elsewhere in the project. For example, if the Liferay properties are
specified for the whole project in a `<properties>` tag in the `pom.xml`, then
you don't need to put anything in the Advanced section. Click *Finish* when
you're done here. 

![Figure 3: Specify the Liferay Maven project that you want to import and click *Finish*.](../../images/maven-ide-import-02.png)

Now comes the magical part! Liferay IDE detects that you don't have the correct 
life cycle mappings for the project you're importing and suggests an action; 
install Maven integration for Liferay IDE (the `m2e-liferay` plugin).

If at this point, the Setup Maven plugin connectors dialog displays a green
check mark before each of the Maven build goals listed in the table, you can
proceed to setup the Maven plugin connectors by clicking *Finish* and continuing
to the next step. If a red check mark precedes any of the Maven build goals
however, then you can resolve them by continuing to the next section. 

![Figure 4: Click *Finish* to launch the installer for Maven integration in Liferay IDE.](../../images/maven-ide-import-03.png)

The next set of steps takes you through the installation process for the
`m2e-liferay` plugin. First, make sure that the checkbox for the `m2e-liferay`
plugin is checked and then click *Next*. This takes you to the window that lets
you review the items you've marked for installation. Verify that the
`m2e-liferay` plugin is listed and click *Next*. The final window presents you
with the license agreements. Select the radio button to accept the license
agreements and click *Finish*. The following sequence of screenshots shows these
steps: 

![Figure 5: The first step of the installer lets you select the `m2e-liferay` plugin for installation.](../../images/maven-ide-m2e-install-01.png)

![Figure 6: Review the item that you selected for installation in the previous step of the installer.](../../images/maven-ide-m2e-install-02.png)

![Figure 7: Agree to the licenses and click *Finish* to complete the installation.](../../images/maven-ide-m2e-install-03.png)

Upon clicking Finish, the `m2e-liferay` plugin downloads and installs. Note that 
this may take a few minutes to complete. When the installation is complete, 
you're prompted to restart Eclipse. Restart Eclipse and you're all set! 

As mentioned during the Maven plugin connector installation discussion, if the
wizard detected missing Liferay properties, you can follow the instructions in
the next section to resolve those missing properties on importing your Liferay
Maven project. 

### Importing a Liferay Maven Project that is Missing Liferay Properties [](id=importing-a-liferay-maven-project-that-is-missing-liferay-properties)

What if the project that you want to import doesn't contain Liferay properties? 
No sweat! Liferay IDE still has you covered. The initial import steps are the 
same as in the section above. The process diverges, though, after you specify
the project to import. As before, Liferay IDE detects that you don't have the
correct life cycle mappings for the project you're importing and suggests an
action. However, since there are no Liferay properties in the project, it shows
an error. When you click *Finish*, you're presented with a warning that tells 
you you're about to import a project that contains errors. Don't worry! You'll 
resolve this in the following steps. Click *OK* to move on. 

![Figure 8: The error occurs because Liferay IDE can't find the correct plugin connectors.](../../images/maven-ide-no-props-01.png)

As indicated in the previous dialog box, the project imports, but it contains
errors. To resolve this, you need to download and install the `m2e-liferay`
plugin. First, open the Liferay IDE preferences by selecting *Window* &rarr;
*Preferences*. Then open the *Maven* section on the left, select *Discovery*,
and click the *Open Catalog* button. This opens the catalog of plugins that can
be installed for Maven. 

![Figure 9: In Preferences, open the plugin catalog for Maven.](../../images/maven-ide-no-props-02.png)

Now just scroll down in the catalog, check the checkbox for *m2e connector for
liferay-maven-plugin*, and click *Finish*. This launches the installer for the
`m2e-liferay` plugin. The steps in the installer are identical to those in the
previous section. Once the installation completes and Liferay IDE restarts, the
error in your imported project is gone!

![Figure 10: Select the `m2e-liferay` plugin for installation and click *Finish* to launch the installer.](../../images/maven-ide-no-props-03.png)

Great! Now you know how to resolve life cycle mapping errors for Liferay Maven 
projects in Liferay IDE. Just remember that it's the `m2e-liferay` plugin that 
takes care of the Maven life cycle mapping. 

## Related Topics [](id=related-topics)

[Developing Plugins with Liferay IDE](/tutorials/-/knowledge_base/liferay-ide)

[Developing with the Plugins SDK](/tutorials/-/knowledge_base/plugins-sdk)

[MVC Portlets](/tutorials/-/knowledge_base/developing-jsp-portlets-using-liferay-mvc)

[Service Builder and Services](/tutorials/-/knowledge_base/service-builder)

[Deploying Plugins](/tutorials/-/knowledge_base/deploying-plugins)
