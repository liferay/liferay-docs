# Upgrading the Liferay Maven Environment

If you're an avid Maven user, and have been using it for Liferay Portal 6.2
project development, you must upgrade your Maven environment to be compatible
with @product-ver@ development. There are two main parts of the Maven
environment upgrade process that you must address:

- Upgrading Liferay Maven Plugins
- Upgrading Liferay Maven Artifacts

The changes for both of these topics only occur in your parent POM and any
relevant child project POMs. For more information on using Maven with
@product-ver@, see the
[Maven tutorial section](/develop/tutorials/-/knowledge_base/7-0/maven).

You'll start off by upgrading your Maven environment's Liferay Maven plugins.

## Upgrading Liferay Maven Plugins

The biggest change for your project's build plugins is the removal of the
`liferay-maven-plugin`. Liferay now provides several individual Maven plugins
that accomplish specific tasks. For example, you can configure Maven plugins for
Liferay's CSS Builder, Service Builder, Theme Builder, etc. With smaller plugins
available to accomplish specific tasks in your project, you no longer have to
rely on one bulky plugin that provides many things you may not want.

As an example, suppose your Liferay Portal 6.2 project was using the
`liferay-maven-plugin` to only use Liferay CSS Builder. First, you should remove
the legacy `liferay-maven-plugin` plugin dependency from your project's
`pom.xml` file:

    <plugin>
        <groupId>com.liferay.maven.plugins</groupId>
        <artifactId>liferay-maven-plugin</artifactId>
        <version>${liferay.version}</version>
        <configuration>
            <autoDeployDir>${liferay.auto.deploy.dir}</autoDeployDir>
            <liferayVersion>${liferay.version}</liferayVersion>
            <pluginType>portlet</pluginType>
        </configuration>
    </plugin>

Then, add the CSS Builder plugin dependency to your project's `pom.xml` file:

    <plugin>
        <groupId>com.liferay</groupId>
        <artifactId>com.liferay.css.builder</artifactId>
        <version>1.0.21</version>
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
                <docrootDirName>src/main/webapp</docrootDirName>
            </configuration>
    </plugin>

Some popular Liferay Maven plugins are listed below, with their corresponding
artifact IDs and tutorials explaining how to configure them:

**Popular Liferay Maven Plugins**

Name | Artifact ID | Tutorial |
:----| :---------- |
CSS Builder | [com.liferay.css.builder](https://search.maven.org/#search%7Cga%7C1%7Ccom.liferay.css.builder) | [Compiling SASS Files in a Maven Project](/develop/tutorials/-/knowledge_base/7-0/compiling-sass-files-in-a-maven-project) |
Lang Builder | [com.liferay.lang.builder](https://search.maven.org/#search%7Cga%7C1%7Ccom.liferay.lang.builder) | Coming Soon |
Service Builder | [com.liferay.portal.tools.service.builder](https://search.maven.org/#search%7Cga%7C1%7Ccom.liferay.portal.tools.service.builder) | [Using Service Builder in a Maven Project](/develop/tutorials/-/knowledge_base/7-0/using-service-builder-in-a-maven-project) |
Theme Builder | [com.liferay.portal.tools.theme.builder](https://search.maven.org/#search%7Cga%7C1%7Ccom.liferay.portal.tools.theme.builder) | [Building Themes in a Maven Project](/develop/tutorials/-/knowledge_base/7-0/building-themes-in-a-maven-project) |

Awesome! You've learned about the new Maven plugins available to you for
@product-ver@ development. Next, you'll learn about upgrading your Liferay Maven
artifacts.

## Upgrading Liferay Maven Artifacts

Many Liferay Portal 6.2 artifact dependencies you were using have changed in
@product-ver@. See the table below for popular Liferay Maven artifacts that have
changed:

Liferay Portal 6.2 Artifact ID | @product-ver@ Artifact ID |
:----------------------------- | :------------------------ |
`portal-service` | `com.liferay.portal.kernel` |
`util-bridges` | `com.liferay.util.bridges` |
`util-java` | `com.liferay.util.java` |
`util-slf4j` | `com.liferay.util.slf4j` |
`util-taglib` | `com.liferay.util.taglib` |

For more information on resolving dependencies in @product-ver@, see the
[Resolving a Plugin's Dependencies](/develop/tutorials/-/knowledge_base/7-0/resolving-a-plugins-dependencies)
tutorial.

Of course, you must also update the artifacts you're referencing for your
projects. If you're using the Central Repository to to install Liferay Maven
artifacts, you won't need to do anything further than updating the artifacts in
your POMs. If you're, however, working behind a proxy or do not have internet
access, you must update your company-shared or local repository with the latest
@product-ver@ Maven artifacts. See the
[Installing Liferay Maven Artifacts](/develop/tutorials/-/knowledge_base/7-0/installing-liferay-maven-artifacts)
tutorial for instructions.

With these updates, you can easily upgrade your Liferay Maven Environment so you
can begin developing traditional plugin projects for @product-ver@.