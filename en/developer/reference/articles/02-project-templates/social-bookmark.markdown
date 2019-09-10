---
header-id: social-bookmark-template
---

# Social Bookmark Template

[TOC levels=1-4]

In this article, you'll learn how to create a Liferay social bookmark as a
Liferay module. To create a social bookmark as a module via the command line
using Blade CLI or Maven, use one of the commands with the following parameters:

```bash
blade create -t social-bookmark [-p packageName] [-c className] projectName
```

or

```bash
mvn archetype:generate \
    -DarchetypeGroupId=com.liferay \
    -DarchetypeArtifactId=com.liferay.project.templates.social.bookmark \
    -DartifactId=[projectName] \
    -Dpackage=[packageName] \
    -DclassName=[className] \
    -DliferayVersion=7.2
```

You can also insert the `-b maven` parameter in the Blade command to generate a
Maven project using Blade CLI.

The template for this kind of project is `social-bookmark`. Suppose you want to
create a social bookmark project called `my-social-bookmark-project` with a
package name of `com.liferay.docs.socialbookmark` and a class name of
`TestSocialBookmark`. You could run the following command to accomplish this:

```bash
blade create -t social-bookmark -p com.liferay.docs.socialbookmark -c Test my-social-bookmark-project
```

or

```bash
mvn archetype:generate \
    -DarchetypeGroupId=com.liferay \
    -DarchetypeArtifactId=com.liferay.project.templates.social.bookmark \
    -DgroupId=com.liferay \
    -DartifactId=my-social-bookmark-project \
    -Dpackage=com.liferay.docs.socialbookmark \
    -Dversion=1.0 \
    -DclassName=Test \
    -Dauthor=Joe Bloggs \
    -DliferayVersion=7.2
```

After running the Blade command above, your project's directory structure looks
like this:

- `my-social-bookmark-project`
    - `gradle`
        - `wrapper`
            - `gradle-wrapper.jar`
            - `gradle-wrapper.properties`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs/socialbookmark`
                    - `social/bookmark`
                        - `TestSocialBookmark`
            - `resources`
                - `content`
                    - `Language.properties`
                - `META-INF`
                    - `resources`
                        - `icons.svg`
                        - `init.jsp`
                        - `page.jsp`
    - `bnd.bnd`
    - `build.gradle`
    - `gradlew`

The Maven-generated project includes a `pom.xml` file and does not include the
Gradle-specific files, but otherwise, appears exactly the same.

The generated module is a working application and is deployable to a @product@
instance. An unmodified module generated as described above creates a social
bookmark named *Test* that searches the current URL using Google Search.

![Figure 1: Click the magnifying glass icon to search the current URL using Google Search.](../../images/social-bookmark-project-template.png)

To build upon the generated app, modify the project by adding logic and
additional files to the folders outlined above. For more information on
developing social bookmarks, see the
[Social API](/docs/7-2/frameworks/-/knowledge_base/f/social-api) section of
tutorials. For information on configuring social bookmarks for the Blogs widget,
see the [Displaying Blogs](/docs/7-2/user/-/knowledge_base/u/displaying-blogs)
article.
