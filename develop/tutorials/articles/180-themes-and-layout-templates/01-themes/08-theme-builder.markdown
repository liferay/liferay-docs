# Theme Builder [](id=theme-builder)

Liferay's Theme Builder gives developers who aren't using
[Liferay's Theme Generator](/develop/tutorials/-/knowledge_base/7-1/creating-themes)
(e.g., Gradle or Maven) a way to compile and build a theme WAR file. To use the
Theme Builder, you must apply it to your project. If you're unsure how to
structure themes for @product@, see the
[Introduction to Themes](/develop/tutorials/-/knowledge_base/7-1/introduction-to-themes)
tutorial.

Follow the instructions below to apply the Theme Builder plugin and build your
theme WAR.

##  Step 1: Apply the Theme Builder Plugin to Your Theme Project [](id=step-1-apply-the-theme-builder-plugin-to-your-theme-project)

Liferay provides two Theme Builder plugins depending on your build tool:

- [com.liferay.portal.tools.theme.builder](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.portal.tools.theme.builder/)
  (Ant, Maven, etc.)
- [com.liferay.gradle.plugins.theme.builder](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.gradle.plugins.theme.builder/)
  (Gradle)

If you want to apply the Theme Builder plugin to an Ant project, examine the
`build.xml` file as an example below:

    <?xml version="1.0"?>
    <!DOCTYPE project>

    <project>
        <path id="theme.builder.classpath">
            <fileset dir="[PATH_TO_THEME_BUILDER_JAR]" includes="com.liferay.portal.tools.theme.builder-*.jar" />
        </path>

        <taskdef classpathref="theme.builder.classpath" resource="com/liferay/portal/tools/theme/builder/ant/taskdefs.properties" />

        <target name="build-theme">
            <build-theme
                diffsDir="diffs"
                outputDir="../dist"
                parentDir="[PATH_TO_STYLED_THEME]/classes/META-INF/resources/_styled"
                parentName="_styled"
                unstyledDir="[PATH_TO_UNSTYLED_THEME]/classes/META-INF/resources/_unstyled"
            />
        </target>
    </project>

You should first supply the path to the Theme Builder JAR. The above code
configures the literal path to the JAR on your local machine. As an alternative,
you could configure [Ivy](http://ant.apache.org/ivy/) to do this for you behind
the scenes. Then create an Ant target (e.g., `build-theme`) that configures the
required parameters to build your theme.

For assistance applying the Theme Builder plugin for a Gradle or Maven project,
see the
[Theme Builder Gradle Plugin](/develop/reference/-/knowledge_base/7-1/theme-builder-gradle-plugin)
or
[Building Themes in a Maven Project](/develop/tutorials/-/knowledge_base/7-1/building-themes-in-a-maven-project)
articles, respectively.

## Step 2: Build Your Theme [](id=step-2-build-your-theme)

Execute the appropriate command based on your build tool: 

- *Ant:* `ant build-theme`
- *Gradle:* `gradlew buildTheme`
- *Maven:* `mvn verify`

The WAR is generated in the following folder, depending on the build tool you
used:

- *Ant:* `/dist`
- *Gradle:* `/build`
- *Maven:* `/target`

That's it! You've successfully configured and leveraged the Theme Builder in
your project. You can also use the Theme Builder to migrate a Plugins SDK
theme to Liferay Workspace. See the
[Migrating a Theme from the Plugins SDK to Workspace](/develop/tutorials/-/knowledge_base/7-1/migrating-a-theme-from-the-plugins-sdk-to-workspace)
tutorial for details.

## Related Topics [](id=related-topics)

[Creating Themes](/develop/tutorials/-/knowledge_base/7-1/creating-themes)

[Theme Template](/develop/reference/-/knowledge_base/7-0/theme-template)
