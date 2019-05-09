# Upgrading Your Maven Build Environment

<div class="learn-path-step">
    <p>Upgrading Your Maven Build Environment<br>Step 1 of 1</p>
</div>

| **Note:** This step only applies to projects residing in a pre @product@ 7.0
| Maven environment that are not upgrading to Liferay Workspace.

If you're an avid Maven user and have been using it for Liferay Portal 6.2
project development or older, you must upgrade your Maven build to be compatible
with @product-ver@ development. There are two main parts of the Maven
environment upgrade process that you must address:

- [Upgrading to new @product-ver@ Maven plugins](#upgrading-to-new-product-ver-maven-plugins)
- [Updating Liferay Maven artifact dependencies](updating-liferay-maven-artifact-dependencies)

For more information on using Maven with @product-ver@, see the
[Maven](/docs/reference/7-2/-/knowledge_base/r/maven) section.

Liferay also offers a Maven development environment tailored specifically for
@product-ver@ development. Learn more about this in the
[Liferay Workspace](/docs/reference/7-2/-/knowledge_base/r/liferay-workspace)
section.

You'll start off by upgrading your Maven environment's Liferay Maven plugins.

## Upgrading to New @product-ver@ Maven Plugins

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
        <version>2.1.3</version>
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
artifact IDs and articles explaining how to configure them:

**Common Liferay Maven Plugins**

Name | Artifact ID | Tutorial |
:----| :---------- | :------- |
Bundle Support | [com.liferay.portal.tools.bundle.support](https://search.maven.org/search?q=com.liferay.portal.tools.bundle.support) | [Bundle Support Plugin](/docs/7-2/reference/-/knowledge_base/r/bundle-support-plugin) |
CSS Builder | [com.liferay.css.builder](https://search.maven.org/search?q=com.liferay.css.builder) | [CSS Builder Plugin](/docs/7-2/reference/-/knowledge_base/r/css-builder-plugin) |
DB Support | [com.liferay.portal.tools.db.support](https://search.maven.org/search?q=com.liferay.portal.tools.db.support) | [DB Support Plugin](/docs/7-2/reference/-/knowledge_base/r/db-support-plugin)
Deployment Helper | [com.liferay.deployment.helper](https://search.maven.org/search?q=com.liferay.deployment.helper) | [Deployment Helper Plugin](/docs/7-2/reference/-/knowledge_base/r/deployment-helper-plugin) |
Javadoc Formatter | [com.liferay.javadoc.formatter](https://search.maven.org/search?q=com.liferay.javadoc.formatter) | [Javadoc Formatter Plugin](/docs/7-2/reference/-/knowledge_base/r/javadoc-formatter-plugin) |
Lang Builder | [com.liferay.lang.builder](https://search.maven.org/search?q=com.liferay.lang.builder) | [Lang Builder Plugin](/docs/7-2/reference/-/knowledge_base/r/lang-builder-plugin) |
REST Builder | [com.liferay.portal.tools.rest.builder](https://search.maven.org/search?q=com.liferay.portal.tools.rest.builder) | [REST Builder Plugin](/docs/7-2/reference/-/knowledge_base/r/rest-builder-plugin) |
Service Builder | [com.liferay.portal.tools.service.builder](https://search.maven.org/search?q=com.liferay.portal.tools.service.builder) | [Service Builder Plugin](/docs/7-2/reference/-/knowledge_base/r/service-builder-plugin) |
Source Formatter | [com.liferay.source.formatter](https://search.maven.org/search?q=com.liferay.source.formatter) | [Source Formatter Plugin](/docs/7-2/reference/-/knowledge_base/r/source-formatter-plugin) |
Theme Builder | [com.liferay.portal.tools.theme.builder](https://search.maven.org/search?q=com.liferay.portal.tools.theme.builder) | [Theme Builder Plugin](/docs/7-2/reference/-/knowledge_base/r/theme-builder-plugin) |
TLD Formatter | [com.liferay.tld.formatter](https://search.maven.org/search?q=com.liferay.tld.formatter) |[TLD Formatter Plugin](/docs/7-2/reference/-/knowledge_base/r/tld-formatter-plugin) |
WSDD Builder | [com.liferay.portal.tools.wsdd.builder](https://search.maven.org/search?q=com.liferay.portal.tools.wsdd.builder) | [WSDD Builder Plugin](/docs/7-2/reference/-/knowledge_base/r/wsdd-builder-plugin) |
XML Formatter | [com.liferay.xml.formatter](https://search.maven.org/search?q=com.liferay.xml.formatter) | [XML Formatter Plugin](/docs/7-2/reference/-/knowledge_base/r/xml-formatter-plugin) |

| **Note:** When upgrading to a Liferay Workspace built with Maven, many of
| these plugins are applied to the workspace by default.

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

## Updating Liferay Maven Artifact Dependencies

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
[Resolving a Plugin's Dependencies](/docs/7-2/customization/-/knowledge_base/c/resolving-a-plugins-dependencies)
article.

Of course, you must also update the artifacts you're referencing for your
projects. If you're using the Central Repository to install Liferay Maven
artifacts, you won't need to do anything more than update the artifacts in
your POMs. If, however, you're working behind a proxy or don't have Internet
access, you must update your company-shared or local repository with the latest
@product-ver@ Maven artifacts. See the
[Installing Local Liferay Maven Artifacts](/docs/7-2/reference/-/knowledge_base/r/installing-local-liferay-maven-artifacts)
article for instructions.

With these updates, you can easily upgrade your Liferay Maven build so you can
begin developing traditional plugin projects for @product-ver@.
