# Deploying Liferay Plugins with Maven [](id=deploying-liferay-plugins-with-maven-lp-6-2-develop-tutorial)

With Maven, it's easy to deploy plugins to a Liferay Portal instance. This
tutorial explain the process. Just follow these steps: 

1.  Make sure you've specified the Liferay specific properties (the properties
    starting with `liferay.`) in your plugin's (or your parent plugin's)
    `pom.xml`. See the 
    [Using Maven Parent Plugin Projects](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/using-maven-parent-plugin-projects-lp-6-2-develop-tutorial)
    tutorial for descriptions of these Liferay properties. 

    Here's an example where these *properties* are specified for a Liferay
    instance bundled with Apache Tomcat in the directory
    `C:\liferay-portal-6.2`:

		<properties>
			<liferay.app.server.deploy.dir>
				C:\liferay-portal-6.2\tomcat-7.0.42\webapps
			</liferay.app.server.deploy.dir>

			<liferay.app.server.lib.global.dir>
				C:\liferay-portal-6.2\tomcat-7.0.42\lib\ext
			</liferay.app.server.lib.global.dir>

			<liferay.app.server.portal.dir>
				C:\liferay-portal-6.2\tomcat-7.0.42\webapps\root
			</liferay.app.server.portal.dir>

			<liferay.auto.deploy.dir>
				C:\liferay-portal-6.2\deploy
			</liferay.auto.deploy.dir>

			<liferay.maven.plugin.version>
				6.2.0-GA1
			</liferay.maven.plugin.version>

			<liferay.version>
				6.2.0-GA1
			</liferay.version>
		</properties>

2.  In your command prompt, navigate to your Liferay plugin project's directory. 

3.  Package your plugin by entering 

        mvn package

    Your command output should be similar to the following output: 

        [INFO] Building war:
        E:\liferay-plugins-maven\sample-parent-project\sample-portlet\target\sample-
        portlet-1.0-SNAPSHOT.war
        ...
        [INFO] --------------------------------------------------------------------
        [INFO] BUILD SUCCESS
        [INFO] --------------------------------------------------------------------

4.  Deploy the plugin into your Liferay bundle by entering

        mvn liferay:deploy

    The command output should look similar to the following output: 

        [INFO] Deploying sample-portlet-1.0-SNAPSHOT.war to [liferay version]\deploy
        [INFO] ---------------------------------------------------------------------
        [INFO] BUILD SUCCESS
        [INFO] ---------------------------------------------------------------------

    Your Liferay console output shows your plugin deploying. It looks
    like this: 

        INFO: Deploying web application directory [liferay version]\[tomcat version]
        \webapps\sample-portlet
        INFO  [pool-2-thread-2][HotDeployImpl:178] Deploying sample-portlet from que
        ue
        INFO  [pool-2-thread-2][PluginPackageUtil:1033] Reading plugin package for s
        ample-portlet

    ---
 
    ![note](../../images/tip-pen-paper.png) **Note:** If you get the following
    error after executing `mvn liferay:deploy`, make sure you're executing the
    command from your plugin's directory (e.g., `sample-portlet`)--not your
    parent project's directory. 
 
     `[ERROR] No plugin found for prefix 'liferay' in the current project and in
     the plugin groups [org.apache.maven.plugins, org.codehaus.mojo] available
     from the repositories [local (C:\Users\cdhoag\.m2\repository), central
     (http://repo.maven .apache.org/maven2)] -> [Help 1]`
    
    ---

5.  If you're deploying the plugin to a release or snapshot repository,
    specify the repository by adding a distribution management section to your
    plugin's `pom.xml` or your parent project's `pom.xml`. 

    Here's an example distribution management section for a snapshot repository: 

        <distributionManagement>
            <repository>
                <id>liferay-releases</id>
                <url>http://localhost:8081/nexus/content/repositories/liferay-releases</url>
            </repository>
            <snapshotRepository>
                <id>liferay-snapshots</id>
                <name>Liferay Snapshots Repository</name>
                <url>http://localhost:8081/nexus/content/repositories/liferay-snapshots</url>
            </snapshotRepository>
        </distributionManagement>

    The proper contents for your `<distributionManagement>` element can be found
    in the *Summary* tab for each of your repositories. 

    ![Figure 2.29: Select the *Summary* tab of your repository to see how to specify it for distribution management in your plugin's POM.](../../images/maven-repository-summary.png) 

    If you created the plugin as a snapshot, you'll have to deploy it to a
    snapshot repository. You can deploy a plugin as a release, but the plugin's
    POM must specify a valid release version (e.g., `<version>1.0</version>`),
    not a snapshot version (e.g., `<version>1.0-SNAPSHOT</version>`). 

6.  Deploy your plugin into your specified Nexus repository:

		mvn deploy

Your plugin is now available in your Nexus repository!

---

![note](../../images/tip-pen-paper.png) **Note:** There are three build phases
you'll use when developing plugins with Maven: 

- In Maven's *compile* phase, explicit dependencies are downloaded to your
  local repository (i.e., `.m2/repository/com/liferay/portal`).
- In Maven's *package* phase, the plugin's inferred dependencies are downloaded
  to your local repository (i.e., `.m2/repository`). 
- In Maven's *install* phase, your plugin is installed to your local
  repository. 

---

Congratulations on deployin your plugin to Liferay and to your repository! 

## Next Steps

[Developing Liferay Portlets with Maven](/tutorials/-/knowledge_base/creating-liferay-portlets-with-maven-lp-6-2-develop-tutorial)

[Developing Liferay Hook Plugins with Maven](/tutorials/-/knowledge_base/developing-liferay-hook-plugins-with-maven-lp-6-2-develop-tutorial)

[Developing Liferay Theme Plugins with Maven](/tutorials/-/knowledge_base/developing-liferay-theme-plugins-with-maven-lp-6-2-develop-tutorial)

[Developing Liferay Layout Template Plugins with Maven](/tutorials/-/knowledge_base/developing-liferay-layout-template-plugins-with-mav-lp-6-2-develop-tutorial)


