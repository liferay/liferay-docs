# Deploying Liferay Plugins with Maven [](id=deploying-liferay-plugins-with-maven)

With Maven it's easy to deploy plugins to a Liferay Portal instance. Just follow
these steps: 

1.  Make sure your parent POM's Liferay-specific properties specify the correct
    Liferay version and your Liferay Portal's deploy directory path. 

    Here's an example of these properties using Liferay 6.1.30 and a deploy
    directory path of `E:\liferay-portal-6.1.30-ee-ga3\deploy`: 

        <properties>
            <liferay.auto.deploy.dir>E:\liferay-portal-6.1.30-ee-ga3\deploy</liferay.auto.deploy.dir>
            <liferay.version>6.1.30</liferay.version>
        </properties>

    If you haven't already created a parent plugin project, see *Using a Parent
    Plugin Project* for details. 

2.  In your command prompt, navigate to your Liferay plugin project's directory. 

3.  Package your plugin by entering 

        mvn package

    Your command output should be similar to the following output: 

        [INFO] Building war:
        E:\ce-plugins-maven\sample-parent-project\sample-portlet\target\sample-portlet-1.0-SNAPSHOT.war
        ...
        [INFO] ------------------------------------------------------------------------
        [INFO] BUILD SUCCESS
        [INFO] ------------------------------------------------------------------------

4.  Deploy the plugin into your Liferay bundle by entering

        mvn liferay:deploy

    The command output should look similar to the following output: 

        [INFO] Deploying sample-portlet-1.0-SNAPSHOT.war to [liferay version]\deploy
        [INFO] ------------------------------------------------------------------------
        [INFO] BUILD SUCCESS
        [INFO] ------------------------------------------------------------------------

    Your Liferay console output shows your plugin deploying. It looks
    like this: 

        INFO: Deploying web application directory [liferay version]\[tomcat version]\webapps\sample-portlet
        INFO  [pool-2-thread-2][HotDeployImpl:178] Deploying sample-portlet from queue
        INFO  [pool-2-thread-2][PluginPackageUtil:1033] Reading plugin package for sample-portlet

    +$$$
 
    **Note:** If you get the following error after executing `mvn
    liferay:deploy`, make sure you're executing the command from your plugin's
    directory (e.g., `sample-portlet`). 
 
     `[ERROR] No plugin found for prefix 'liferay' in the current project and in
     the plugin groups [org.apache.maven.plugins, org.codehaus.mojo] available
     from the repositories [local (C:\Users\cdhoag\.m2\repository), central
     (http://repo.maven .apache.org/maven2)] -> [Help 1]`
    
    $$$

5.  If you're deploying the plugin to a release or snapshot repository,
    specify the repository by adding a distribution management section to your
    plugin's `pom.xml`. 

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

    ![Figure 9.13: Select the *Summary* tab of your repository to see how to specify it for distribution management in your plugin's POM.](../../images/maven-repository-summary.png) 

    Since you created the plugin as a snapshot, you'll have to deploy it to a
    snapshot repository. You can deploy a plugin as a release, but the plugin's
    POM must specify a valid release version (e.g., `<version>1.0</version>`),
    not a snapshot version (e.g., `<version>1.0-SNAPSHOT</version>`). 

6.  Deploy your plugin into your specified Nexus repository:

		mvn deploy

+$$$

**Note:** There are three build phases you'll use when developing plugins with
Maven: 

- In Maven's *compile* phase, explicit dependencies are downloaded to your
  local repository (i.e., `.m2/repository/com/liferay/portal`).
- In Maven's *package* phase, the plugin's inferred dependencies are downloaded
  to your local repository (i.e., `.m2/repository`). 
- In Maven's *install* phase, your plugin is installed to your local
  repository. 

$$$

Now that you've deployed a plugin using Maven, let's consider the types of
Liferay plugins you can develop with Liferay Maven archetypes. 
