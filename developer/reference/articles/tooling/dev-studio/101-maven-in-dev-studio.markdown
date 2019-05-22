---
header-id: maven-in-dev-studio
---

# Maven in Dev Studio

[TOC levels=1-4]

You can take full advantage of [Maven](https://maven.apache.org/) in Liferay Dev
Studio with its built-in Maven support. In this article, you'll learn about the
following topics:

- [Installing Maven Plugins for Liferay Dev Studio](#installing-maven-plugins-for-dev-studio)
- [Importing Maven Projects](#importing-maven-projects)
- [Using the POM Graphic Editor](#using-the-pom-graphic-editor)

Note that
[creating](/docs/7-2/reference/-/knowledge_base/r/creating-a-project#liferay-dev-studio)
and
[deploying](/docs/7-2/reference/-/knowledge_base/r/deploying-a-project#liferay-dev-studio)
Maven projects with Dev Studio are covered in their respective articles.

First you'll install the necessary Maven plugins for Dev Studio.

## Installing Maven Plugins for Dev Studio

To support Maven projects in Dev Studio properly, you first need a mechanism to
recognize Maven projects as Dev Studio projects. For Dev Studio to recognize the
Maven project and for it to be able to leverage Java EE tooling features (e.g.,
the Servers view) with the project, the project must be a flexible web project.
Dev Studio relies on the following Eclipse plugins to provide this capability:

- `m2e` (Maven integration for Eclipse)
- `m2e-wtp` (Maven integration for WTP)

All you have to do is install them so you can begin developing Maven projects
for @product@.

When first installing Dev Studio, the installation startup screen asks if you
want to install the Maven plugins automatically. Don't worry if you missed this
during setup. You'll learn how to install the required Maven plugins for Dev
Studio manually below.

1.  Navigate to *Help* &rarr; *Install New Software*. In the *Work with* field,
    insert the following value:

        http://releases.liferay.com/tools/ide/latest/stable/

2.  Check the *Liferay IDE Maven Support* option. This bundles all the required
    Maven plugins you need to begin developing Maven projects for @product@.

    ![Figure 1: You can install all the necessary Maven plugins for Dev Studio by installing the *Liferay IDE Maven Support* option.](../../../images/maven-install-ide-plugins.png)

    If the *Liferay IDE Maven Support* option does not appear, then it's already
    installed. To verify that it's installed, uncheck the *Hide items that are
    already installed* checkbox and look for *Liferay IDE Maven Support* in the
    list of installed plugins. Also, if you want to view everything that is
    bundled with the *Liferay IDE Maven Support* option, uncheck the *Group
    items by category* checkbox.

3.  Click *Next*, review the install details, accept the term and license
    agreements, and select *Finish*.

| **Note:** Both Maven and Eclipse have their own standard build project
| lifecycles that are independent from each other. For both to work together and
| run seamlessly within Dev Studio, a lifecycle mapping is required to link both
| lifecycles into one combined lifecycle. Normally, this would have to be done
| manually by the user. Fortunately, the m2e-liferay plugin combines the
| lifecycle metadata mapping and Eclipse build lifecycles, to provide a seamless
| user experience. The lifecycle mappings for your project can be viewed by
| right-clicking your project and selecting *Properties* &rarr; *Maven* &rarr;
| *Lifecycle Mapping*.

Awesome! Your Dev Studio is ready to develop Maven projects for @product@!

You'll learn about importing Maven projects in Dev Studio next.

## Importing Maven Projects

To import a pre-existing, non-Liferay Maven project into Dev Studio, follow the
steps outlined below:

1.  Navigate to *File* &rarr; *Import* &rarr; *Maven* &rarr; *Existing Maven
    Projects* and click *Next*.

    ![Figure 2: Dev Studio offers the Maven folder in the Import wizard.](../../../images/import-maven-project.png)

2.  Click *Browse...* and select the root folder for your Maven project. Once
    you've selected it, the `pom.xml` for that project should be visible in the
    Projects menu.

    ![Figure 3: Use the Import Maven Projects wizard to import your pre-existing project.](../../../images/select-maven-import.png)

3.  Click *Finish*.

Now your Maven project is available from the Package Explorer. To import a
Liferay project built with Maven, see the
[Importing Projects in Dev Studio](/docs/7-2/reference/-/knowledge_base/r/importing-projects-in-dev-studio)
Next you'll learn about Dev Studio's POM graphical editor.

### Using the POM Graphic Editor

You're provided a graphical POM editor when opening your Maven project's
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

![Figure 4: Liferay Dev Studio provides five interactive modes to help you edit and organize your POM..](../../../images/pom-editor-features.png)

By taking advantage of these interactive modes, Dev Studio makes modifying and
organizing your POM and its dependencies a snap! 
