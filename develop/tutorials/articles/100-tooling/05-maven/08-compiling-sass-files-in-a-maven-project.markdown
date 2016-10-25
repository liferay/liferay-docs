# Compiling Sass Files in a Maven Project [](id=compiling-sass-files-in-a-maven-project)

If your Liferay Maven project uses Sass files to style its provided UI, you'll
need to configure the project to compile those files. The Maven build system
cannot parse Sass files on its own, so you'll need to configure your Maven
project to convert its Sass files into CSS files so they are recognizable for
Maven's build lifecyle. It would be a real pain to convert your Sass files into
CSS files manually before building your Maven project!

Liferay provides the `com.liferay.css.builder` plugin, which can be easily
configured in your project's `pom.xml` file. The CSS Builder converts Sass files
into CSS files so the Maven build can parse your style sheets.

You'll learn how to apply Liferay's CSS builder to your Maven project next.

1.  Open your project's `pom.xml` file and apply Liferay's CSS Builder:

        <build>
            <plugins>
                <plugin>
                    <groupId>com.liferay</groupId>
                    <artifactId>com.liferay.css.builder</artifactId>
                    <version>1.0.20</version>
                    <executions>
                        <execution>
                            <id>default-build-css</id>
                            <phase>generate-sources</phase>
                            <goals>
                                <goal>build-css</goal>
                            </goals>
                        </execution>
                    </executions>
                    <configuration>
                        <portalCommonPath>/</portalCommonPath>
                        <docrootDirName>src/main/resources</docrootDirName>
                    </configuration>
                </plugin>
            </plugins>
        </build>

    The above configuration applies the CSS Builder by specifying its `groupId`,
    `artifactId`, and `version`. Then the CSS Builder's execution and
    configuration are defined.

    - The
      [`executions` tag](https://maven.apache.org/guides/mini/guide-configuring-plugins.html#Using_the_executions_Tag)
      configures the CSS Builder to run during the `generate-sources` phase of
      your Maven project's build lifecycle. The `build-css`
      [goal](http://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html#A_Build_Phase_is_Made_Up_of_Plugin_Goals)
      is defined for that lifecycle phase.
    - The
      [`configuration` tag](https://maven.apache.org/pom.html#Plugins) defines
      two important properties:
        - `portalCommonPath`: The file path for the
          [Liferay Frontend Common CSS JAR](https://mvnrepository.com/artifact/com.liferay/com.liferay.frontend.css.common)
          file.
        - `docrootDirName`: The base `resources` folder containing the Sass
          files to compile.

2.  Initiate the appropriate Maven command to compile your Maven project's Sass
    files:

        mvn liferay:build-css

    Since the `build-css` goal is configured as a part of the `generate-sources`
    phase, this is also invoked by running `mvn generate-sources` or a parent
    phase of `generate-sources` (e.g., `mvn verify`, `mvn compile`, etc.).

Awesome! You can now compile Sass files in your Liferay Maven project.
