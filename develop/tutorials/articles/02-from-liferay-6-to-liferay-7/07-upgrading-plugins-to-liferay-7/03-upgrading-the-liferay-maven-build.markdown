# Upgrading the Liferay Maven Build [](id=upgrading-the-liferay-maven-build)

If you're an avid Maven user and have been using it for Liferay Portal 6.2
project development, you must upgrade your Maven build to be compatible with
@product-ver@ development. There are two main parts of the Maven environment
upgrade process that you must address:

- Upgrading to new @product-ver@ Maven plugins
- Updating Liferay Maven artifact dependencies

For more information on using Maven with @product-ver@, see the
[Maven tutorial section](/develop/tutorials/-/knowledge_base/7-0/maven). For a
guided and expedited upgrade process for your Maven build, try the
[Code Upgrade Tool](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool).

<!-- Mention Maven Workspace here when documentation is available. -Cody -->

You'll start off by upgrading your Maven environment's Liferay Maven plugins.

## Upgrading to New @product-ver@ Maven Plugins [](id=upgrading-to-new-product-ver-maven-plugins)

The biggest change for your project's build plugins is the removal of the
`liferay-maven-plugin`. Liferay now provides several individual Maven plugins
that accomplish specific tasks. For example, you can configure Maven plugins for
Liferay's CSS Builder, Service Builder, Theme Builder, etc. With smaller plugins
available to accomplish specific tasks in your project, you no longer have to
rely on one large plugin that provides many things you may not want.

For example, suppose your Liferay Portal 6.2 project was using the
`liferay-maven-plugin` for Liferay CSS Builder only. First, you should remove
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
                <id>default-build</id>
                <phase>generate-sources</phase>
                <goals>
                    <goal>build</goal>
                </goals>
            </execution>
        </executions>
            <configuration>
                <docrootDirName>src/main/webapp</docrootDirName>
            </configuration>
    </plugin>

Some common Liferay Maven plugins are listed below, with their corresponding
artifact IDs and tutorials explaining how to configure them:

**Common Liferay Maven Plugins**

Name | Artifact ID | Tutorial |
:----| :---------- | :------- |
CSS Builder | [com.liferay.css.builder](https://search.maven.org/#search%7Cga%7C1%7Ccom.liferay.css.builder) | [Compiling SASS Files in a Maven Project](/develop/tutorials/-/knowledge_base/7-0/compiling-sass-files-in-a-maven-project) |
Lang Builder | [com.liferay.lang.builder](https://search.maven.org/#search%7Cga%7C1%7Ccom.liferay.lang.builder) | Coming Soon |
Service Builder | [com.liferay.portal.tools.service.builder](https://search.maven.org/#search%7Cga%7C1%7Ccom.liferay.portal.tools.service.builder) | [Using Service Builder in a Maven Project](/develop/tutorials/-/knowledge_base/7-0/using-service-builder-in-a-maven-project) |
Theme Builder | [com.liferay.portal.tools.theme.builder](https://search.maven.org/#search%7Cga%7C1%7Ccom.liferay.portal.tools.theme.builder) | [Building Themes in a Maven Project](/develop/tutorials/-/knowledge_base/7-0/building-themes-in-a-maven-project) |

In Liferay Portal 6.2, you were also required to specify all your app server
configuration settings. For example, your parent POM probably contained settings
similar to these:

    <properties>
        <liferay.app.server.deploy.dir>
            E:\liferay-portal-6.2.0-ce-ga1\tomcat-7.0.42\webapps
        </liferay.app.server.deploy.dir>

        <liferay.app.server.lib.global.dir>
            E:\liferay-portal-6.2.0-ce-ga1\tomcat-7.0.42\lib\ext
        </liferay.app.server.lib.global.dir>

        <liferay.app.server.portal.dir>
            E:\liferay-portal-6.2.0-ce-ga1\tomcat-7.0.42\webapps\root
        </liferay.app.server.portal.dir> 

        <liferay.auto.deploy.dir>
            E:\liferay-portal-6.2.0-ce-ga1\deploy
        </liferay.auto.deploy.dir>

        <liferay.version>
            6.2.0
        </liferay.version>

        <liferay.maven.plugin.version>
            6.2.0
        </liferay.maven.plugin.version
 
    </properties>

This is no longer required in @product-ver@ because Liferay's Maven tools no
longer rely on your @product@ installation's specific versions. You should
remove them from your POM file.

Awesome! You've learned about the new Maven plugins available to you for
@product-ver@ development. Next, you'll learn about updating your Liferay Maven
artifacts.

## Updating Liferay Maven Artifact Dependencies [](id=updating-liferay-maven-artifact-dependencies)

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
projects. If you're using the Central Repository to install Liferay Maven
artifacts, you won't need to do anything more than update the artifacts in
your POMs. If, however, you're working behind a proxy or don't have Internet
access, you must update your company-shared or local repository with the latest
@product-ver@ Maven artifacts. See the
[Installing Liferay Maven Artifacts](/develop/tutorials/-/knowledge_base/7-0/installing-liferay-maven-artifacts)
tutorial for instructions.

With these updates, you can easily upgrade your Liferay Maven build so you can
begin developing traditional plugin projects for @product-ver@.
