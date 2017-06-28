# Theme Builder [](id=theme-builder)

Liferay's Theme Builder gives developers who generated their @product@ theme
using tools other than
[Liferay's Theme Generator](/develop/tutorials/-/knowledge_base/7-0/themes-generator)
(e.g., Gradle, or Maven) a way to compile and build a theme WAR file. To use the
Theme Builder, you must apply it to your project. Then you can leverage it to
build your theme. If you're unsure how to structure themes for @product@, see
the
[Introduction to Themes](/develop/tutorials/-/knowledge_base/7-0/introduction-to-themes)
tutorial.

You can also leverage the Theme Builder to
[migrate a Plugins SDK theme to Liferay Workspace](/develop/tutorials/-/knowledge_base/7-0/migrating-a-theme-from-the-plugins-sdk-to-workspace).
This is done using
[Blade CLI](/develop/tutorials/-/knowledge_base/7-0/blade-cli).

Follow the instructions below to apply the Theme Builder plugin and build your
theme WAR.

##  Step 1: Apply the Theme Builder Plugin to Your Theme Project

Liferay provides two Theme Builder plugins depending on your build tool:

- [com.liferay.portal.tools.theme.builder](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.portal.tools.theme.builder/)
  (Maven, Ant, etc.)
- [com.liferay.gradle.plugins.theme.builder](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.gradle.plugins.theme.builder/)
  (Gradle)

If you want to apply the Theme Builder plugin to an Ant project, examine the
`build.xml` file as an example below:

    <?xml version="1.0"?>
    <!DOCTYPE project>

    <project>
        <path id="css.builder.classpath">
            <fileset dir="[PATH_TO_THEME_BUILDER_JAR]" includes="com.liferay.portal.tools.theme.builder-*.jar" />
        </path>

        <taskdef classpathref="css.builder.classpath" resource="com/liferay/portal/tools/theme/builder/ant/taskdefs.properties" />

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

The Theme Builder requires the CSS Builder, which is automatically applied by
pointing to the Theme Builder JAR. Then an Ant target is created (`build-theme`)
that configures the parameters necessary to build your theme.

For assistance applying the Theme Builder plugin for a Gradle or Maven project,
see the
[Theme Builder Gradle Plugin](/develop/reference/-/knowledge_base/7-0/theme-builder-gradle-plugin)
or
[Building Themes in a Maven Project](/develop/tutorials/-/knowledge_base/7-0/building-themes-in-a-maven-project)
articles, respectively.

## Step 2: Build Your Theme

Execute the appropriate command based on your build tool. These commands are
listed below organized by build tool:

- *Ant:* `ant build-theme`
- *Gradle:* `gradlew buildTheme`
- *Maven:* `mvn verify`

The generated WAR file can be found in the following folder, depending on the
build tool you used:

- *Ant:* `/dist`
- *Gradle:* `/build`
- *Maven:* `/target`

That's it! You've successfully configured and leveraged the Theme Builder in
your project.
