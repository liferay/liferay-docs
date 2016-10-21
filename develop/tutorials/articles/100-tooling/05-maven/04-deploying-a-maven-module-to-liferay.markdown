# Deploying a Maven Module to @product@

Deploying a Liferay Maven module can be completed two ways:

- Manually copy your generated Maven module JAR to your @product@ instance's
  `/deploy` folder.
- Configure your Maven project to deploy to the @product@ instance automatically
  by running a Maven command via the command prompt.

Although manually copying your module JAR for deployment is a viable option,
this is an inefficient way to deploy your projects. With just a few simple
configurations in your Maven POMs, you can deploy a module to @product@ with one
command execution.

In this tutorial, you'll learn how to deploy your Liferay Maven module
automatically. Before you can deploy a your Maven project, you must have it
configured to generate an OSGi module JAR; visit the
[Creating a Module JAR Using Maven](/develop/tutorials/-/knowledge_base/7-0/creating-a-module-jar-using-maven)
tutorial to for more information.

You'll learn how to deploy your Liferay Maven module automatically next.

1.  Add the following `maven-antrun-plugin` configuration to your Liferay Maven
    project's parent `pom.xml` file.

        <build>
            <plugins>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-antrun-plugin</artifactId>
                    <version>1.7</version>
                    <executions>
                        <execution>
                            <id>deploy-liferay-artifacts</id>
                            <phase>pre-integration-test</phase>
                            <goals>
                                <goal>run</goal>
                            </goals>
                            <configuration>
                                <target name="maven-deploy">
                                    <copy failonerror="false" file="${project.build.directory}/${project.artifactId}-${project.version}.war" tofile="${deploy.dir}/${project.artifactId}.war"/>
                                    <copy failonerror="false" file="${project.build.directory}/${project.artifactId}-${project.version}.jar" tofile="${deploy.dir}/${project.artifactId}.jar"/>
                                </target>
                            </configuration>
                        </execution>
                    </executions>
                </plugin>
            </plugins>
        </build>

    This POM configuration applies the
    [Maven AntRun Plugin](http://maven.apache.org/plugins/maven-antrun-plugin/)
    by defining its `groupId`, `artifactId`, and `version`. The logic also
    defines

    - The
      [`executions` tag](https://maven.apache.org/guides/mini/guide-configuring-plugins.html#Using_the_executions_Tag),
      which configures the Maven AntRun plugin to run during the
      `pre-integration-test` phase of your Maven project's build lifecycle. The
      `run`
      [goal](http://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html#A_Build_Phase_is_Made_Up_of_Plugin_Goals)
      is defined for that lifecycle phase.
    - The
      [`configuration` tag](https://maven.apache.org/pom.html#Plugins), which
      configures and runs an Ant target from the POM. The logic above creates a
      `build-maven-deploy.xml` file in your project's build folder (e.g.,
      `/target`), containing the Ant target specified. That file is then
      executed, which copies any JAR or WAR file from your Maven project's build
      folder to your @product@ instance's deployment folder. You'll configure
      the deployment folder next.

2.  Add the following `properties` tag outside the `build` tag in your project's
    parent `pom.xml` file:

        <properties>
            <deploy.dir>${liferay-bundle}/deploy</deploy.dir>
        </properties>

    This logic configures your deployment directory. Make sure to replace the
    `${liferay-bundle}` variable with your @product@ instance's file path.

3.  To invoke the Maven AntRun plugin from your project, configure it in your
    project's `pom.xml`:

        <build>
            <plugins>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-antrun-plugin</artifactId>
               </plugin>
            </plugins>
        </build>

4.  Navigate to your Maven project in a command prompt and deploy your project
    to the configured deployment folder:

        mvn verify

That's it! Your Liferay Maven project is automatically copied to your @product@
instance's deployment folder and ready for use.
