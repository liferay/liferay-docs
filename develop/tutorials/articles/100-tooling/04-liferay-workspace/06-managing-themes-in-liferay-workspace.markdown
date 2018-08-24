# Managing Themes in Liferay Workspace

Creating a @product@ theme can be accomplished using several different tools:

- [Theme Generator](/develop/tutorials/-/knowledge_base/7-1/creating-themes)
  (Node.js-based)
- [Project template/archetype](/develop/reference/-/knowledge_base/7-1/theme-template)
  (Gradle/Maven-based)
- etc.

Liferay Workspace offers an environment where developers with differing theme
development expertise can collaborate and work together in one place. For
example, suppose Tony theme developer is a guru at Gradle-based themes and Tracy
theme developer is an expert with Node.js-based themes. Both developers can
create a theme with their preferred tooling and have them both seamlessly
integrated into workspace.

On a grander scale, workspace provides the environment necessary for developers
to stick to what they're comfortable with. It creates a shared space where
frontend developers can use frontend tools, backend developers can use backend
tools, etc. Then they can leverage workspace to tie everything together with its
underlying CI build (e.g., Gradle or Maven).

So how does this work with themes? You'll find how Node.js-based themes and
Gradle/Maven-based themes are managed next.

## Node.js Themes in Workspace

Liferay Workspace reserves the `themes` folder only for Node.js-based themes.
There are no Blade CLI-provided commands or Maven archetypes to create a Liferay
Node.js theme. You should leverage Liferay's
[Theme Generator](/develop/tutorials/-/knowledge_base/7-1/creating-themes) to
create them. You can run the Theme Generator from within the `themes` folder or
the generated theme can be copied into the folder.

You'll demo workspace's Node.js theme management capability next. Be sure the
Theme Generator's required tooling is installed.

1.  Navigate to your workspace's `themes` folder and run the following Theme
    Generator command:

        yo liferay-theme

    Follow the prompts to create your theme.

2.  Navigate into your new theme and run `../gradlew build`. Liferay Workspace
    builds the frontend theme using Gradle. Under the hood, Liferay's
    [Node Gradle Plugin](/develop/reference/-/knowledge_base/7-1/node-gradle-plugin)
    is applied and used to build your theme.

3.  Workspace is smart enough to differentiate between differing theme types.
    For instance, you can't copy a Node.js-based theme into the `wars` folder
    and expect it to build. You can test if your project is recognized by
    workspace by running this command from workspace's root folder:

        ../gradlew projects

    Your CLI should display your new theme under the `themes` project.

        Root project 'liferay-workspace'
        +--- Project ':themes'
        |    \--- Project ':themes:my-nodejs-theme'

    If you moved a non-Node.js theme (e.g., WAR-style theme) into the `themes`
    folder, it would not be recognized by the Gradle `projects` command.

    **Note:** Workspace identifies a Node.js theme by checking whether it has a
    `package.json` file. Any theme without this file is not compatible in the
    `themes` folder.

Excellent! You learned how Node.js themes are recognized in workspace and where
they should reside. Next you'll learn how workspace manages WAR-style themes.

## Gradle/Maven Themes in Workspace

Liferay Workspace provides the `wars` folder for any WAR-style project. Themes
created with [Blade CLI](/develop/tutorials/-/knowledge_base/7-1/blade-cli) or
Maven using the [`theme`](/develop/reference/-/knowledge_base/7-1/theme-template)
project template or archetype are automatically generated here when creating the
project within workspace.

Themes built using Liferay's `theme` project template are always WARs and should
always reside in workspace's `wars` folder. They should never be moved to the
`themes` folder; that folder is reserved for Node.js-based themes only.

To build a WAR-style theme in workspace, run the `../gradlew build` command.
Liferay Workspace builds the theme using Gradle. Under the hood, Liferay's
[Theme Builder Gradle Plugin](/develop/reference/-/knowledge_base/7-1/theme-builder-gradle-plugin)
is applied and used to build your theme. You can complete a similar workflow in
a Maven workspace. See the
[Building Themes in a Maven Project](/develop/tutorials/-/knowledge_base/7-1/building-themes-in-a-maven-project)
tutorial for more information.

Awesome! You know how WAR-style themes are built in workspace and where they
should reside.
