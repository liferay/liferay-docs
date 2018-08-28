# Theme Builder Plugin [](id=theme-builder-plugin)

The Theme Builder plugin lets you build Liferay theme files in your project.
Visit the
[Building Themes in a Maven Project](/develop/tutorials/-/knowledge_base/7-0/building-themes-in-a-maven-project)
tutorial to learn more about applying Theme Builder to your Maven project.

## Usage [](id=usage)

To use the plugin, include it in your project's root `pom.xml` file:

    <build>
        <plugins>
        ...
            <plugin>
                <groupId>com.liferay</groupId>
                <artifactId>com.liferay.portal.tools.theme.builder</artifactId>
                <version>1.1.6</version>
                <executions>
                    <execution>
                        <phase>generate-resources</phase>
                        <goals>
                            <goal>build</goal>
                        </goals>
                        <configuration>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            ...
        </plugins>
    </build>

You can view an example POM containing the Theme Builder configuration
[here](https://github.com/liferay/liferay-portal/blob/master/modules/util/portal-tools-theme-builder/samples/pom.xml).

## Goals [](id=goals)

The plugin adds one Maven goal to your project:

Name | Description
---- | -----------
`theme-builder:build` |  Builds the theme files.

## Available Parameters [](id=available-parameters)

You can set the following parameters in the `<configuration>` section of the
POM:

Parameter Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`diffsDir` | `File` | `${maven.war.src}` | The directory that contains the files to copy over the parent theme.
`name` | `String` | `${project.artifactId}` | The name of the new theme.
`outputDir` | `File` | `${project.build.directory}/${project.build.finalName}` | The directory where to build the theme.
`parentDir` | `File` | `null` | The directory of the parent theme.
`parentName` | `String` | `null` | The name of the parent theme.
`templateExtension` | `String` | `"ftl"` |  The extension of the template files, usually `"ftl"` or `"vm"`.
`unstyledDir` | `File` | `null` | The directory of [Liferay Frontend Theme Unstyled](https://github.com/liferay/liferay-portal/tree/master/modules/apps/foundation/frontend-theme/frontend-theme-unstyled).

You can also manage the `com.liferay.frontend.theme.styled` and
`com.liferay.frontend.theme.unstyled` default theme dependencies provided by the
Theme Builder in your `pom.xml`. They can be modified by adding them as project
dependencies:

    <project>
        ...
        <dependencies>
            ...
            <dependency>
                <groupId>com.liferay</groupId>
                <artifactId>com.liferay.frontend.theme.styled</artifactId>
                <version>3.0.4</version>
                <scope>provided</scope>
            </dependency>
            <dependency>
                <groupId>com.liferay</groupId>
                <artifactId>com.liferay.frontend.theme.unstyled</artifactId>
                <version>3.0.4</version>
                <scope>provided</scope>
            </dependency>
        </dependencies>
    </project>

There is an additional Liferay theme-related dependency you can manage this
way that's provided by the CSS Builder. See
[this section](/develop/reference/-/knowledge_base/7-1/css-builder-plugin) for
more information.
