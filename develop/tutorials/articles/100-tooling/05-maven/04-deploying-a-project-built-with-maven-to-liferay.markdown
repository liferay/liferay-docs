# Deploying a Project Built with Maven to @product@ [](id=deploying-a-project-built-with-maven-to-product)

There are two ways to deploy a Maven-built Liferay project:

1.  Copy your generated Maven JAR/WAR to your @product@ instance's `/deploy`
    folder.
2.  Configure your Maven project to deploy to the @product@ instance
    automatically by running a Maven command via the command line.

Although manually copying your JAR/WAR for deployment is a viable option, this
is an inefficient way to deploy your projects. With a small configuration in
your Maven POMs, you can deploy a project to @product@ with one command
execution.

+$$$

**Note:** In previous versions of Liferay Portal, you were able to execute the
`liferay:deploy` command to deploy your configured Maven project to a Liferay
server. This is no longer possible since the `liferay-maven-plugin` is not
applied to Maven projects built from Liferay archetypes.

$$$

If you're deploying a module JAR, a prerequisite for this tutorial is to have
your project configured to generate an OSGi module JAR; if you haven't done
this, visit the
[Creating a Module JAR Using Maven](/develop/tutorials/-/knowledge_base/7-1/creating-a-module-jar-using-maven)
tutorial for more information.

1.  Add the following plugin configuration to your Liferay Maven project's
    parent `pom.xml` file.

        <build>
            <plugins>
                <plugin>
                    <groupId>com.liferay</groupId>
                    <artifactId>com.liferay.portal.tools.bundle.support</artifactId>
                    <version>3.2.1</version>
                    <executions>
                        <execution>
                            <id>deploy</id>
                            <goals>
                                <goal>deploy</goal>
                            </goals>
                            <phase>pre-integration-test</phase>
                        </execution>
                    </executions>
                </plugin>
            </plugins>
        </build>

    This POM configuration applies Liferay's
    [Bundle Support plugin](/develop/reference/-/knowledge_base/7-1/bundle-support-plugin)
    by defining its `groupId`, `artifactId`, and `version`. You can learn more
    about this plugin in the
    [Maven Workspace](/develop/tutorials/-/knowledge_base/7-1/maven-workspace)
    tutorial. The logic also defines the
    [executions](https://maven.apache.org/guides/mini/guide-configuring-plugins.html#Using_the_executions_Tag)
    tag, which configures the Bundle Support plugin to run during the
    `pre-integration-test` phase of your Maven project's build lifecycle. The
    `deploy`
    [goal](http://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html#A_Build_Phase_is_Made_Up_of_Plugin_Goals)
    is defined for that lifecycle phase.

2.  By default, the Bundle Support plugin deploys to the Liferay installation
    in the `bundles` folder, located in your plugin's parent folder. If you do
    not have your project set up this way, you must define your Liferay home
    folder in your POM. You can do this by adding the following logic within the
    `plugin` tags, but outside of the `execution` tags:

        <configuration>
            <liferayHome>LIFERAY_HOME_PATH</liferayHome>
        </configuration>

    An example configuration would look like this:

        <configuration>
            <liferayHome>C:/liferay/liferay-ce-portal-7.0-ga3</liferayHome>
        </configuration>

    +$$$

    **Note:** Maven applications built for previous Liferay Portal versions
    required the `<liferay.maven.plugin.version>` tag to do various tasks (e.g.,
    deploying to a Liferay server). This tag is not needed since the old
    `liferay-maven-plugin` is no longer used.

    $$$

3.  Run this command to deploy your project:

        mvn verify

That's it! Your Liferay Maven project is built and deployed automatically to
your @product@ instance.
