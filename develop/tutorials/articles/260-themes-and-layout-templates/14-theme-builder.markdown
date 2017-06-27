# Theme Builder

Liferay's Theme Builder gives developers who generated their @product@ theme
using tools other than
[Liferay's Theme Generator](/develop/tutorials/-/knowledge_base/7-0/themes-generator)
(e.g., Gradle or Maven) a way to compile and build a theme WAR file. To use the
Theme Builder, you must apply it to your project. Then you can leverage it
to build your theme's deployable WAR file.

Follow the instructions below to apply the plugin and build your theme.

1.  Apply the Theme Builder plugin to your theme project. Liferay provides two
    Theme Builder plugins for specific build tools:

    - [com.liferay.portal.tools.theme.builder](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.portal.tools.theme.builder/)
      (Maven, Ant, etc.)
    - [com.liferay.gradle.plugins.theme.builder](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.gradle.plugins.theme.builder/)
      (Gradle)

    If you must apply the Theme Builder plugin to an Ant project, view the
    `build.xml` file as an example below:

        <?xml version="1.0"?>
        <!DOCTYPE project>

        <project>
            <path id="css.builder.classpath">
                <fileset dir="[PATH_TO_JAR]" includes="com.liferay.portal.tools.theme.builder-*.jar" />
            </path>

            <taskdef classpathref="css.builder.classpath" resource="com/liferay/portal/tools/theme/builder/ant/taskdefs.properties" />

            <target name="build-theme">
                <build-theme
                    diffsDir="diffs"
                    outputDir="../tmp"
                    parentDir="[PATH_TO_STYLED_THEME]/classes/META-INF/resources/_styled"
                    parentName="_styled"
                    unstyledDir="[PATH_TO_UNSTYLED_THEME]/classes/META-INF/resources/_unstyled"
                />
            </target>
        </project>

    The Theme Builder requires the CSS Builder, which is automatically applied
    by pointing to the Theme Builder JAR. Then an Ant target is created
    (`build-theme`) that configures the parameters necessary to build your
    theme.

    For assistance applying the Theme Builder plugin for a Gradle or Maven
    project, see the
    [Theme Builder Gradle Plugin](/develop/reference/-/knowledge_base/7-0/theme-builder-gradle-plugin)
    or
    [Building Themes in a Maven Project](/develop/tutorials/-/knowledge_base/7-0/building-themes-in-a-maven-project)
    article, respectively.

2.  Build your theme by executing the appropriate command. These commands are
    listed below organized by build tool:

    - *Ant:* `ant build-theme`
    - *Gradle:* `gradlew buildTheme`
    - *Maven:* `mvn verify`

That's it! Once you execute the appropriate build command, your theme is
compiled and built into a deployable WAR file.
