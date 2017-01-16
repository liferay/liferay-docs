# Using Maven in Liferay @ide@

You can take full advantage of Maven in Liferay @ide@ with its built-in Maven
support. You can use Liferay @ide@ to

- [Create Maven Projects](#creating-maven-projects)
- [Import Maven Projects](#importing-maven-projects)
- [Manage POM Files](#using-the-pom-graphic-editor)

The first thing you’ll learn about in this tutorial is creating Maven projects
in IDE.

## Creating Maven Projects

You can create a Maven project based on Liferay's provided Maven archetypes.

1.  Navigate to *File* &rarr; *New* &rarr; *Liferay Module Project*.

2.  Give your project a name, select the `maven-module` build type, and choose
    the project template (archetype) you'd like to use.

    ![Figure 1: The New Liferay Module Project wizard lets you generate a Maven module project.](../../../images/create-maven-ide-project.png)

3.  (Optional) Click *Next* and name your component class name and package. You
    can also specify your component class's properties in the Properties menu.

4.  Click *Finish*.

That's it! You've created a Liferay module project using Maven!

You may have created your Maven project outside of @ide@ with another tool. You
can still manage that project in @ide@, but you must first import it. You'll
learn how to do this next.

## Importing Maven Projects

To import a pre-existing Maven project into Liferay @ide@, follow the steps
outlined below:

1.  Navigate to *File* &rarr; *Import* &rarr; *Maven* &rarr; *Existing Maven
    Projects* and click *Next*.

    ![Figure 2: @ide@ offers the Maven folder in the Import wizard.](../../../images/import-maven-project.png)

2.  Click *Browse...* and select the root directory for your Maven project. Once
    you've selected it, the `pom.xml` for that project should be visible in the
    Projects menu.

    ![Figure 3: Use the Import Maven Projects wizard to import your pre-existing project.](../../../images/select-maven-import.png)

3.  Click *Finish*.

Now your Maven project is available from the Package Explorer. Next you'll learn
about Liferay @ide@'s POM graphical editor.

### Using the POM Graphic Editor

You're provided a nifty POM graphic editor when opening your Maven project's
`pom.xml` in Liferay @ide@. This gives you several different ways to leverage
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

The figure below show the `pom.xml` file editor and its modes.

![Figure 4: Liferay @ide@ provides five interactive modes to help you edit and organize your POM..](../../../images/pom-editor-features.png)

By taking advantage of these interactive modes, modifying and organizing your
POM and its dependencies has never been easier!
