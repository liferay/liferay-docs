# Using Maven in LiferayDev Studio [](id=using-maven-in-liferay-ide)

You can take full advantage of Maven in Liferay Dev Studio with its built-in
Maven support. In this tutorial, you'll learn about the following topics:

- [Installing Maven Plugins for Liferay Dev Studio](#installing-maven-plugins-for-liferay-ide)
- [Creating Maven Projects](#creating-maven-projects)
- [Importing Maven Projects](#importing-maven-projects)
- [Using the POM Graphic Editor](#using-the-pom-graphic-editor)

First you'll install the necessary Maven plugins for Dev Studio.

## Installing Maven Plugins for Liferay Dev Studio [](id=installing-maven-plugins-for-liferay-ide)

In order to support Maven projects in Dev Studio properly, you first need a
mechanism to recognize Maven projects as Dev Studio projects. Dev Studio
projects are recognized in Eclipse as faceted web projects that include the
appropriate Liferay plugin facet. Therefore, all Dev Studio projects are also
Eclipse web projects (faceted projects with the web facet installed). For Dev
Studio to recognize the Maven project and for it to be able to leverage Java EE
tooling features (e.g., the Servers view) with the project, the project must be
a flexible web project. Dev Studio relies on the following Eclipse plugins to
provide this capability:

- `m2e` (Maven integration for Eclipse)
- `m2e-wtp` (Maven integration for WTP)

All you have to do is install them so you can begin developing Maven projects
for @product@.

When first installing Liferay Dev Studio, the installation startup screen lets
you select whether you'd like to install the Maven plugins automatically. Don't
worry if you missed this during setup. You'll learn how to install the required
Maven plugins for Dev Studio manually below.

1.  Navigate to *Help* &rarr; *Install New Software*. In the *Work with* field,
    insert the following value:

        Liferay IDE repository - http://releases.liferay.com/tools/ide/latest/stable/

2.  Check the *Liferay IDE Maven Support* option. This bundles all the required
    Maven plugins you need to begin developing Maven projects for @product@.

    ![Figure 1: You can install all the necessary Maven plugins for Dev Studio by installing the *Liferay IDE Maven Support* option.](../../../images/maven-install-ide-plugins.png)

    If the *Liferay IDE Maven Support* option does not appear, then it's already
    installed. To verify that it's installed, uncheck the *Hide items that are
    already installed* checkbox and look for *Liferay IDE Maven Support* in the
    list of installed plugins. Also, if you'd like to view everything that is
    bundled with the *Liferay IDE Maven Support* option, uncheck the *Group
    items by category* checkbox.

3.  Click *Next*, review the install details, accept the term and license
    agreements, and select *Finish*.

<!--
Both Maven and Eclipse have their own standard build project lifecycles that are
independent from each other. For both to work together and run seamlessly within
Liferay IDE, a lifecycle mapping is required to link both lifecycles into one
combined lifecycle. Normally, this would have to be done manually by the user.
Fortunately, the m2e-liferay plugin combines the lifecycle metadata mapping and
Eclipse build lifecycles, to provide a seamless user experience. The lifecycle
mappings for your project can be viewed by right-clicking your project and
selecting Properties &rarr; Maven &rarr; Lifecycle Mapping.
-->

<!-- Find out if Maven Lifecycle Mapping still works properly in IDE. -Cody -->

Awesome! Your Dev Studio is ready to develop Maven projects for @product@!

You'll learn about creating Maven projects in Dev Studio next.

## Creating Maven Projects [](id=creating-maven-projects)

You can create a Maven project based on Liferay's provided Maven archetypes.

1.  Navigate to *File* &rarr; *New* &rarr; *Liferay Module Project*.

2.  Give your project a name, select the `Maven` build type, and choose the
    project template (archetype) to use.

    ![Figure 2: The New Liferay Module Project wizard lets you generate a Maven project.](../../../images/create-maven-ide-project.png)

3.  (Optional) Click *Next* and name your component class name and package. You
    can also specify your component class's properties in the Properties menu.

4.  Click *Finish*.

That's it! You've created a Liferay module project using Maven!

If you created your Maven project outside of Dev Studio with another tool, you
can still manage that project in Dev Studio, but you must first import it.
You'll learn how to do this next.

## Importing Maven Projects [](id=importing-maven-projects)

To import a pre-existing Maven project into Dev Studio, follow the steps
outlined below:

1.  Navigate to *File* &rarr; *Import* &rarr; *Maven* &rarr; *Existing Maven
    Projects* and click *Next*.

    ![Figure 3: Dev Studio offers the Maven folder in the Import wizard.](../../../images/import-maven-project.png)

2.  Click *Browse...* and select the root folder for your Maven project. Once
    you've selected it, the `pom.xml` for that project should be visible in the
    Projects menu.

    ![Figure 4: Use the Import Maven Projects wizard to import your pre-existing project.](../../../images/select-maven-import.png)

3.  Click *Finish*.

Now your Maven project is available from the Package Explorer. Next you'll learn
about Dev Studio's POM graphical editor.

### Using the POM Graphic Editor [](id=using-the-pom-graphic-editor)

You're provided a nifty POM graphic editor when opening your Maven project's
`pom.xml` in Dev Studio. This gives you several different ways to leverage
the power of Maven in your project:

- **Overview:** provides a graphical interface where you can add to and edit the
  `pom.xml` file.

- **Dependencies:** provides a graphical interface for adding and editing
  dependencies in your project, as well as modifying the `dependencyManagement`
  section of the `pom.xml` file.

- **Effective POM:** provides a read-only version of your project POM merged
  with its parent POM(s), `settings.xml`, and the settings in Eclipse for Maven.

- **Dependency Hierarchy:** provides a hierarchical view of project dependencies
  and an interactive listing of resolved dependencies.

- **pom.xml:** provides an editor for your POM's source XML.

The figure below shows the `pom.xml` file editor and its modes.

![Figure 5: Liferay Dev Studio provides five interactive modes to help you edit and organize your POM..](../../../images/pom-editor-features.png)

By taking advantage of these interactive modes, Dev Studio makes modifying and
organizing your POM and its dependencies a snap! 
