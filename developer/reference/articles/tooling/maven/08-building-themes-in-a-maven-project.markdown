---
header-id: building-a-theme-with-maven
---

# Building a Theme with Maven

[TOC levels=1-4]

Liferay's Theme Builder is used to build @product@ theme files in your project.
You can incorporate the Theme Builder into your Maven project to generate
WAR-style
[themes](/docs/7-2/frameworks/-/knowledge_base/frameworks/themes-and-layout-templates)
deployable to @product@.

The easiest way to create a Liferay theme with Maven is to create a new Maven
project using Liferay's provided
[Theme archetype](/docs/7-2/reference/-/knowledge_base/reference/theme-template);
Theme Builder is configured in the new project by default. In some cases,
however, this may not be convenient. For instance, if you have a legacy theme
project and don't want to start over, generating a new project is not ideal. 

For cases like this, you should manually configure your Maven project to
leverage Theme Builder. You'll learn how to do this next.

1.  Configure Liferay's
    [Theme Builder](/docs/7-2/reference/-/knowledge_base/reference/theme-builder-plugin)
    plugin in your project's `pom.xml` file:

    ```xml
    <build>
        <plugins>
            <plugin>
                <groupId>com.liferay</groupId>
                <artifactId>com.liferay.portal.tools.theme.builder</artifactId>
                <version>1.1.7</version>
                <executions>
                    <execution>
                        <phase>generate-resources</phase>
                        <goals>
                            <goal>build</goal>
                        </goals>
                        <configuration>
                            <diffsDir>${maven.war.src}</diffsDir>
                            <name>${project.artifactId}</name>
                            <outputDir>${project.build.directory}/${project.build.finalName}</outputDir>
                            <parentDir>${project.build.directory}/deps/com.liferay.frontend.theme.styled.jar</parentDir>
                            <parentName>_styled</parentName>
                            <templateExtension>ftl</templateExtension>
                            <unstyledDir>${project.build.directory}/deps/com.liferay.frontend.theme.unstyled.jar</unstyledDir>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
    ```

    The above configuration applies the Theme Builder plugin and then defines
    the Theme Builder's execution and configuration.

    - The
      [executions](https://maven.apache.org/guides/mini/guide-configuring-plugins.html#Using_the_executions_Tag)
      tag configures the Theme Builder to run during the `generate-resources`
      phase of your Maven project's build lifecycle. The `build`
      [goal](http://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html#A_Build_Phase_is_Made_Up_of_Plugin_Goals)
      is defined for that lifecycle phase.
    - The
      [configuration](https://maven.apache.org/pom.html#Plugins) defines tag
      several important properties. For more info on these properties, see the
      [Theme Builder Plugin](/docs/7-2/reference/-/knowledge_base/reference/theme-builder-plugin)
      article.

2.  Apply the CSS Builder plugin, which is required to use Theme Builder:

    ```xml
    <plugin>
        <groupId>com.liferay</groupId>
        <artifactId>com.liferay.css.builder</artifactId>
        <version>2.1.3</version>
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
            <docrootDirName>target/${project.build.finalName}</docrootDirName>
            <outputDirName>/</outputDirName>
            <portalCommonPath>target/deps/com.liferay.frontend.css.common.jar</portalCommonPath>
        </configuration>
    </plugin>
    ```

    You can learn more about the CSS Builder's Maven configuration by visiting
    the
    [Compiling Sass Files in a Maven Project](/docs/7-2/reference/-/knowledge_base/reference/compiling-sass-files-in-a-maven-project)
    tutorial.

3.  You can configure your project to exclude Sass files from being packaged in
    your theme. This is optional, but is a nice convenience to keep any
    unnecessary source code out of your WAR. Since the Theme Builder creates
    a WAR-style theme, you should apply the
    [maven-war-plugin](https://maven.apache.org/plugins/maven-war-plugin/) so it
    instructs the WAR file packaging process to exclude Sass files:

    ```xml
    <plugin>
        <artifactId>maven-war-plugin</artifactId>
        <version>3.0.0</version>
        <configuration>
            <packagingExcludes>**/*.scss</packagingExcludes>
        </configuration>
    </plugin>
    ```

4.  Insert the `<packaging>` tag in your project's POM so your project is
    correctly packaged as a WAR file. This tag can be placed with your project's
    `groupId`, `artifactId`, and `version` specifications like this:

    ```xml
    <groupId>com.liferay</groupId>
    <artifactId>com.liferay.project.templates.theme</artifactId>
    <version>1.0.0</version>
    <packaging>war</packaging>
    ```

5.  Building themes requires certain dependencies. You can 
    [configure these dependenices](/docs/7-2/customization/-/knowledge_base/customization/configuring-dependencies) 
    in your project's `pom.xml` as directories or JAR files. If you choose to 
    use JARs, you must apply the 
    [maven-dependency-plugin](http://maven.apache.org/plugins/maven-dependency-plugin/) 
    and have it copy JAR dependencies into your project from Maven Central: 

    ```xml
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
                        <artifactItem>
                            <groupId>com.liferay</groupId>
                            <artifactId>com.liferay.frontend.theme.styled</artifactId>
                            <version>${com.liferay.frontend.theme.styled.version}</version>
                        </artifactItem>
                        <artifactItem>
                            <groupId>com.liferay</groupId>
                            <artifactId>com.liferay.frontend.theme.unstyled</artifactId>
                            <version>${com.liferay.frontend.theme.unstyled.version}</version>
                        </artifactItem>
                    </artifactItems>
                    <outputDirectory>${project.build.directory}/deps</outputDirectory>
                    <stripVersion>true</stripVersion>
                </configuration>
            </execution>
        </executions>
    </plugin>
    ```

    This configuration copies the `com.liferay.frontend.css.common`,
    `com.liferay.frontend.theme.styled`, and
    `com.liferay.frontend.theme.unstyled` dependencies into your Maven project.
    Notice that you've set the `stripVersion` tag to `true` and you're setting
    the artifact versions within each `artifactItem` tag. You'll set these
    versions and a few other properties for your Maven project next.

6.  Configure the properties for your project in its `pom.xml` file:

    ```xml
    <properties>
        <com.liferay.css.builder.version>2.1.3</com.liferay.css.builder.version>
        <com.liferay.frontend.css.common.version>2.0.4</com.liferay.frontend.css.common.version>
        <com.liferay.frontend.theme.styled.version>2.0.28</com.liferay.frontend.theme.styled.version>
        <com.liferay.frontend.theme.unstyled.version>2.2.5</com.liferay.frontend.theme.unstyled.version>
        <com.liferay.portal.tools.theme.builder.version>1.1.7</com.liferay.portal.tools.theme.builder.version>
    </properties>
    ```

    The properties above set the versions for the CSS and Theme Builder plugins
    and their dependencies.

You've successfully configured your Maven project to build a Liferay theme with
Theme Builder!
