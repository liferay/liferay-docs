### Creating Liferay Plugins with Maven [](id=creating-liferay-plugins-with-maven-liferay-portal-6-2-dev-guide-02-en)

Liferay offers many archetypes to help create Maven projects for multiple plugin
types, including portlet, theme, hook, and layout template plugins. We provide
archetypes for each of these plugin types for various versions of Liferay, so
you almost certainly have the archetype you need. 

*Archetype* is Maven's project templating toolkit. Let's use it to create a
Liferay portlet project. With Archetype, you can use the same steps we detail
below to generate Liferay plugin projects of any type. 

---

![note](../../images/tip-pen-paper.png) **Note:** Make sure Maven is installed
 and that its executable is in your `$PATH` environment variable. 

---

We'll demonstrate two ways of creating Liferay plugins with Maven: using Liferay
IDE and using the command line. First, let's learn how to use Maven archetypes
to generate a Liferay plugin project using Liferay IDE:

1.  Navigate to *File* &rarr; *New* &rarr; *Liferay Plugin Project*.

2.  Assign a project name and display name. For our example, we'll use
    *sample-portlet* and *Sample* for the project name and display name,
    respectively. Notice that upon entering *sample-portlet* as the project
    name, the wizard conveniently inserts *Sample* in grayed-out text as the
    portlet's default display name. The wizard derives the default display name
    from the project name, starts it in upper-case, and leaves off the plugin
    type suffix *Portlet* because the plugin type is automatically appended to
    the display name in Liferay Portal. The IDE saves the you from repetitively
    appending the plugin type to the display name; in fact, the IDE ignores any
    plugin type suffix if you happen to append it to the display name. 

3.  Select *Maven (liferay-maven-plugin)* for the build type. Notice that some
    of the options for your plugin project changed, including the *Location* 
    field, which is set to the user's workspace by default. Choose the parent 
    directory in which you want to create the plugin project. It is a best 
    practice to create a parent project for your Maven plugins, so they can all
    share common project information. See section *Using a Parent Plugin
    Project* for details. 

4.  Specify the *Artifact version* and *Group id*. For our example, we'll use
    `1.0-SNAPSHOT` and `com.liferay.sample` for the artifact version and group
    id, respectively.

5.  Specify the active profile that you'd like your Liferay plugin project to
    use. If you don't remember your active profile or haven't created one, click
    the *Select Active Profiles ...* icon immediately to the right of the text
    field. If you have any active profiles, they will be listed in the menu on
    the left. To select an existing profile, highlight its profile ID and select
    the illuminated *right arrow* button to transfer it to the menu on the
    right. Otherwise, if you don't have any existing profile, click the green
    *addition* button to create a profile and give it an ID. 

    If you're specifying a new profile, the wizard will still create your
    plugin, but it will need further attention before it is deployable. You'll
    need to specify the necessary properties within the new profile; we'll
    demonstrate specifying these properties in the *Configuring your Liferay
    Maven Project* section of this chapter. 

    You also have the option to create a profile based on a Liferay runtime. To
    do this, select the *Create New Maven Profile Based on Liferay Runtime*
    button to the far right of the *Active profiles* text field. Specify the
    *Liferay runtime*, *New profile id*, and *Liferay version*. For the new
    profile location you can choose to specify your profile in the
    `settings.xml` (recommended) or your project's `pom.xml`. When creating your
    Maven profile based on a Liferay runtime, the IDE automatically populates
    the new profile with the required properties, and no additional profile 
    configuration is needed for the plugin. 

6.  Select the *Portlet* plugin type and then click *Finish*.

	![Figure 2.27: You can build a Liferay Plugin Project using Maven by completing the setup wizard.](../../images/create-wizard-maven-ide.png) 

Great! You've successfully created a Liferay portlet project using Maven in
Liferay IDE! Next, let's run through steps for creating your Liferay Maven
plugins using the command line.

1.  Open the command prompt and navigate to the parent directory in which you
    want to create the plugin project. Archetype will create a sub-directory for
    the plugin project you create.

    ---

    ![note](../../images/tip-pen-paper.png) **Note:** If you haven't already
    created a parent project, you may want to consider creating one to share
    common project information. See section *Using a Parent Plugin Project* for
    details.

    ---

2.  Execute the command

        mvn archetype:generate -DarchetypeCatalog=https://repository.liferay.com/nexus/content/groups/liferay-ce

    ---
    
    ![note](../../images/tip-pen-paper.png) **Important:** Currently, the new
    GA1 artifacts for CE and EE are only available from
    [repository.liferay.com](repository.liferay.com). Therefore, you must use
    the `-DarchetypeCatalog=...` portion to access the Liferay Repository.
    You'll also need to configure a couple other files to ensure the generation
    command completes successfully. Reference the *Installing CE Artifacts from
    the Central Repository* and *Installing EE Artifacts from the Liferay
    Repository* sections to configure Maven to access the Liferay Repository for
    CE and EE artifacts, respectively.
    
    ---

    <!-- Edit archetype generate command and remove above note when GA1 artifacts
    are available from Maven Central. -Cody -->

    Archetype starts and lists the archetypes available to you. You're prompted
    to *choose* an archetype or *filter* archetypes by group / artifact ID. The
    output looks similar to the following text: 

        ...
        4: https://repository.liferay.com/nexus/content/groups/liferay-ce/ -> com.liferay.
        maven.archetypes:liferay-portlet-jsf-archetype
        (Provides an archetype to create Liferay JSF portlets.)
        5: https://repository.liferay.com/nexus/content/groups/liferay-ce/ -> com.liferay.
        maven.archetypes:liferay-layouttpl-archetype
        (Provides an archetype to create Liferay layout templates.)
        6: https://repository.liferay.com/nexus/content/groups/liferay-ce/ -> com.liferay.
        maven.archetypes:liferay-portlet-archetype
        (Provides an archetype to create Liferay portlets.)
        7: https://repository.liferay.com/nexus/content/groups/liferay-ce/ -> com.liferay.
        maven.archetypes:liferay-portlet-liferay-faces-alloy-archetype
        (Provides an archetype to create Liferay Faces Alloy portlets.)
        8: https://repository.liferay.com/nexus/content/groups/liferay-ce/ -> com.liferay.
        maven.archetypes:liferay-portlet-primefaces-archetype
        (Provides an archetype to create Liferay PrimeFaces portlets.)
        ...
        Choose a number or apply filter (format: [groupId:]artifactId, case sensiti
        ve contains):
        
<!--
        ...
        39: remote -> com.liferay.maven.archetypes:liferay-hook-archetype
        (Provides an archetype to create Liferay hooks.)
        40: remote -> com.liferay.maven.archetypes:liferay-layouttpl-archetype
        (Provides an archetype to create Liferay layout templates.)
        41: remote -> com.liferay.maven.archetypes:liferay-portlet-archetype
        (Provides an archetype to create Liferay portlets.)
        42: remote -> com.liferay.maven.archetypes:liferay-portlet-icefaces-archety
        pe
        (Provides an archetype to create Liferay ICEfaces portlets.)
        43: remote -> com.liferay.maven.archetypes:liferay-portlet-jsf-archetype
        (Provides an archetype to create Liferay JSF portlets.)
        ...
        Choose a number or apply filter (format: [groupId:]artifactId, case sensiti
        ve contains):
-->
<!-- Output will look similar to this once GA1 artifacts are officially
available on Maven Central/ZIP files. Add similar output back, when available
-Cody -->

3. Choose a Liferay portlet archetype by entering its number. Since we're using
the Liferay Repository, the newest archetype version is automatically selected.
(*6.2-GA1*).

<!--
3.  To find the right Liferay archetype for your project, you can either scroll
    up to find it or apply filters to narrow the set of results. Filtering on
    *liferay* as your group ID, and a plugin type (*portlet*, *hook*, *theme*,
    etc.) can help you focus on more applicable Liferay archetypes. 

    Entering `liferay:portlet` as a filter gives a listing of Liferay portlet
    archetypes: -->
<!-- Add back when filtering is relevant (using GA1 artifacts from Central -Cody
-->
<!--
        Choose a number or apply filter (format: [groupId:]artifactId, case
        sensitive contains): : liferay:portlet Choose archetype:
        1: remote -> com.liferay.maven.archetypes:liferay-portlet-archetype
        (Provides an archetype to create Liferay portlets.)
        2: remote ->
        com.liferay.maven.archetypes:liferay-portlet-icefaces-archetype (Pr
        ovides an archetype to create Liferay ICEfaces portlets.)
        3: remote -> com.liferay.maven.archetypes:liferay-portlet-jsf-archetype
        (Provide s an archetype to create Liferay JSF portlets.)
        4: remote ->
        com.liferay.maven.archetypes:liferay-portlet-liferay-faces-alloy-ar
        chetype (Provides an archetype to create Liferay Faces Alloy portlets.)
        5: remote ->
        com.liferay.maven.archetypes:liferay-portlet-primefaces-archetype (
        Provides an archetype to create Liferay PrimeFaces portlets.)
        6: remote ->
        com.liferay.maven.archetypes:liferay-portlet-richfaces-archetype (P
        rovides an archetype to create Liferay RichFaces portlets.) Choose a
        number or apply filter (format: [groupId:]artifactId, case sensitive co
        ntains): :

4.  Choose an archetype by entering its number.-->
<!-- Add back when step 3. is added back -->

<!--
5.  You're prompted to choose the archetype version. Enter the number
    corresponding to the Liferay version for the archetype. However, you're not
    required to select the archetype version that corresponds with your Liferay
    instance; older archetype versions are compatible with updated Liferay
    bundles. 

        Choose com.liferay.maven.archetypes:liferay-portlet-archetype version:
        1: 6.0.2
        2: 6.0.3
        3: 6.0.4
        4: 6.0.5
        5: 6.0.6
        6: 6.1.0
        7: 6.1.1
        8: 6.1.2
        9: 6.1.10
        10: 6.1.20
        11: 6.1.30
        12: 6.2.0-B1
        13: 6.2.0-B2
        14: 6.2.0-B3
        15: 6.2.0-M5
        16: 6.2.0-M6
        17: 6.2.0-RC1
        18: 6.2.0-RC2
        19: 6.2.0-RC3
        20: 6.2.0-RC4
        21: 6.2.0-RC5
        Choose a number: 21:
-->
<!-- Add back when GA1 artifacts are available from Central. --Cody -->

4.  Enter values for the *groupId*, *artifactId*, *version*, and *package*
    coordinates (properties) of your project. Here are some examples: 

        groupId: com.liferay.sample
        artifactId: sample-portlet
        version: 1.0-SNAPSHOT
        package: com.liferay.sample

    This process is illustrated in the snapshot below:

    ![Figure 2.28: When creating your portlet plugin, you must enter your *groupId*, *artifactId*, *version*, and *package* properties.](../../images/maven-portlet-plugin-settings.png)

    For more information on defining Maven coordinates, see
    [http://maven.apache.org/pom.html#Maven_Coordinates](http://maven.apache.org/pom.html#Maven_Coordinates).

5.  Enter the letter *Y* to confirm your coordinates.

    Maven's Archetype tool creates a Liferay plugin project directory with a new
    `pom.xml` file and source code. 

---

![note](../../images/tip-pen-paper.png) **Note:** The archetype file is
downloaded and installed automatically to your local repository (e.g.,
`.m2/repository/com/liferay/maven/archetypes/[archetype]`). If you
configured a mirror pointing to your public repository on Nexus, the
plugin is installed there. 

---

Following these steps using Liferay IDE or the command line, you can use
Archetype to generate all your Liferay plugin projects! 

Plugin projects generated from a Liferay archetype are equipped with a POM
that's ready to work with a parent project. It inherits the values for
`liferay.version` and `liferay.auto.deploy.dir` properties from the parent. 

When your plugin is created, you can package and deploy your project to a
specified Liferay instance. You can even install and deploy the individual
plugin to a remote repository. 

Next we'll go through some brief examples to demonstrate deploying your plugins
to Liferay Portal using Maven. 

### Deploying Liferay Plugins with Maven [](id=deploying-liferay-plugins-with-maven-liferay-portal-6-2-dev-guide-02-en)

With Maven it's easy to deploy plugins to a Liferay Portal instance. Just follow
these steps: 

1.  Make sure you've specified the Liferay specific properties (e.g., those
    properties starting with `liferay.`) your plugin uses. See this chapter's
    section *Using a Parent Plugin Project* for descriptions of these Liferay
    properties. 

    Here's an example where we specified these *properties* for Liferay bundled
    with Apache Tomcat in a directory `C:\liferay-portal-6.2`:

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

    Since you created the plugin as a snapshot, you'll have to deploy it to a
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

Now that you've deployed a plugin using Maven, let's consider the types of
Liferay plugins you can develop with Liferay Maven archetypes. 

### Liferay Plugin Types to Develop with Maven [](id=plugin-types-to-develop-with-maven-liferay-portal-6-2-dev-guide-02-en)

You can develop all Liferay plugin types with Maven: portlets, themes,
layout templates, hooks, and Ext. Next, you'll learn how to create each plugin
type using Maven, and we'll point out where each plugin's directory structure is
different than if you created it using the Plugins SDK. We'll often refer to the
previous sections for creating and deploying these plugin types in Maven using
Liferay artifacts.  We'll also reference sections of some other chapters in this
guide, since they're still relevant to Maven developers: they explain how you
develop each type of plugin regardless of development environment. 

Let's start with portlet plugins. 


#### Creating a Portlet Plugin [](id=creating-a-portlet-plugin-maven-liferay-portal-6-2-dev-guide-02-en)

To create a Liferay portlet plugin project, follow the *Creating Liferay Plugins
with Maven* section. 

---

![tip](../../images/tip-pen-paper.png) **Tip**: As you use Maven's Archetype
tool to generate your portlet project, you can filter on group ID `liferay`, or
even the group ID/artifact ID combination `liferay:portlet`, to 
find the Liferay portlet archetypes more easily. 

---

##### Anatomy [](id=portlet-plugin-archetype-anatomy-liferay-portal-6-2-dev-guide-02-en)

A portlet project created from the
`com.liferay.maven.archetypes:liferay-portlet-archetype` has the following
directory structure: 

- portlet-plugin/
    - src/
        - main/
            - java/
            - resources/
            - webapp/
                - css/
                    - main.css
                - js/
                    - main.js
                - WEB-INF/
                    - liferay-display.xml
                    - liferay-plugin-package.properties
                    - liferay-portlet.xml
                    - portlet.xml
                    - web.xml
                - icon.png
                - view.jsp
    - pom.xml

Maven creates the `src/main/java/` directory automatically. It holds the
portlet's Java source code (e.g., `com.liferay.sample.SamplePortlet.java`), and
`portlet-plugin/src/main/webapp` holds its web source code. If you've created
any portlet plugins using the Plugins SDK, you might have noted it uses a
different directory structure. 

The following table illustrates the differences in location of the Java source
and web source code for a Maven project and a Plugins SDK project: 

Location    | Maven project     | Plugins SDK project   |
----------- | ----------------- | --------------------- |
Java source | `src/main/java`   | `docroot/WEB-INF/src` |
Web source  | `src/main/webapp` | `docroot`             |

To view the full directory structure of a portlet developed by Ant, visit our
[Anatomy of a
Portlet](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/anatomy-of-a-portlet-project-liferay-portal-6-2-dev-guide-03-en)
section in this guide. 

##### Deployment [](id=deployment-liferay-portal-6-2-dev-guide-02-en)

To deploy your portlet plugin, follow the instructions detailed above in
*Deploying Liferay Plugins with Maven*. 

Congratulations! You successfully created a Liferay portlet plugin using Maven. 

##### More Information [](id=more-information-liferay-portal-6-2-dev-guide-02-en)

For detailed information on creating portlet plugins, see
[Developing Portlet Applications](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/developing-portlet-applications-liferay-portal-6-2-dev-guide-03-en). 

Next, let's run through a brief example for developing a theme plugin using
Maven.
