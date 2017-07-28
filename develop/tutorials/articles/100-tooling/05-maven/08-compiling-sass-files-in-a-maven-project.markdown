# Compiling Sass Files in a Maven Project [](id=compiling-sass-files-in-a-maven-project)

If your Liferay Maven project uses Sass files to style its UI, you must
configure the project to convert its Sass files into CSS files so they are
recognizable for Maven's build lifecycle. It would be a real pain to convert your
Sass files into CSS files manually before building your Maven project!

Liferay provides the `com.liferay.css.builder` plugin. The CSS Builder converts
Sass files into CSS files so the Maven build can parse your style sheets.

Here's how to apply Liferay's CSS builder to your Maven project.

1.  Open your project's `pom.xml` file and apply Liferay's CSS Builder:

        <plugin>
            <groupId>com.liferay</groupId>
            <artifactId>com.liferay.css.builder</artifactId>
            <version>${com.liferay.css.builder.version}</version>
            <executions>
                <execution>
                    <id>default-build</id>
                    <phase>compile</phase>
                    <goals>
                        <goal>build</goal>
                    </goals>
                </execution>
            </executions>
            <configuration>
                <docrootDirName>${com.liferay.portal.tools.theme.builder.outputDir}</docrootDirName>
                <outputDirName>/</outputDirName>
                <portalCommonPath>target/deps/com.liferay.frontend.css.common.jar</portalCommonPath>
            </configuration>
        </plugin>

    The above configuration applies the CSS Builder by specifying its `groupId`,
    `artifactId`, and `version`. It then defines the CSS Builder's execution and
    configuration.

    - The
      [`executions` tag](https://maven.apache.org/guides/mini/guide-configuring-plugins.html#Using_the_executions_Tag)
      configures the CSS Builder to run during the `compile` phase of your Maven
      project's build lifecycle. The `build`
      [goal](http://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html#A_Build_Phase_is_Made_Up_of_Plugin_Goals)
      is defined for that lifecycle phase.
    - The
      [`configuration` tag](https://maven.apache.org/pom.html#Plugins) defines
      two important properties:
        - `docrootDirName`: The base `resources` folder containing the Sass
          files to compile.
        - `outputDirName`: The name of the sub-directories where the SCSS files
          are compiled to.
        - `portalCommonPath`: The file path for the
          [Liferay Frontend Common CSS JAR](https://mvnrepository.com/artifact/com.liferay/com.liferay.frontend.css.common)
          file.

2.  If you're using 
    [Bourbon](http://bourbon.io/) in your Sass files, you'll need to 
    [add an additional plugin dependency](/develop/tutorials/-/knowledge_base/7-0/configuring-dependencies) 
    to your project's POM. If you're not using Bourbon, skip this step. Add the 
    following plugin dependency: 

        <plugin>
            <artifactId>maven-dependency-plugin</artifactId>
            <executions>
                <execution>
                    <phase>generate-sources</phase>
                    <goals>
                        <goal>copy</goal>
                    </goals>
                    <configuration>
                        <artifactItems>
                            <artifactItem>
                                <groupId>com.liferay</groupId>
                                <artifactId>com.liferay.frontend.css.common</artifactId>
                                <version>${com.liferay.frontend.css.common.version}</version>
                            </artifactItem>
                        </artifactItems>
                        <outputDirectory>${project.build.directory}/deps</outputDirectory>
                        <stripVersion>true</stripVersion>
                    </configuration>
                </execution>
            </executions>
        </plugin>

    The [maven-dependency-plugin](http://maven.apache.org/plugins/maven-dependency-plugin/)
    copies the `com.liferay.frontend.css.common` dependency from Maven Central
    to your project's build folder so the CSS Builder can leverage it.

3.  Use this command to compile your Maven project's Sass files:

        mvn compile

+$$$

**Note:** Liferay's CSS Builder is supported for Oracle's JDK and uses a native
compiler for increased speed. If you're using an IBM JDK, you may experience
issues when building your SASS files (e.g., when building a theme). It's
recommended to switch to using the Oracle JDK, but if you prefer using the IBM
JDK, you must use the fallback Ruby compiler. To do this, add the following
tag to your CSS Builder configuration in your POM:

    <sassCompilerName>ruby</sasscompilerName>

Be aware that the Ruby-based compiler doesn't perform as well as the native
compiler, so expect longer compile times.

$$$

Awesome! You can now compile Sass files in your Liferay Maven project.
