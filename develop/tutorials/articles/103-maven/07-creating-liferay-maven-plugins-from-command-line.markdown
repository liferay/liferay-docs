# Creating Liferay Maven Plugins from the Command Line [](id=creating-liferay-maven-plugins-from-the-command-lin)

To learn how to use Liferay IDE to create Liferay Maven plugins, please refer to
the 
[Creating Liferay Maven Plugins from Liferay IDE](/develop/tutorials/-/knowledge_base/6-2/creating-liferay-maven-plugins-from-liferay-ide)
tutorial. This tutorial explains how to use Maven archetypes to generate
Liferay plugin projects using the command line.

## Using the Maven Archetype Tool to Create a Liferay Plugin [](id=using-the-maven-archetype-tool-to-create-a-liferay-plugin)

Follow these steps to create a Liferay plugin from the command line:

1. Open the command prompt or terminal and navigate to the parent directory in
   which you want to create the plugin project. Create a sub-directory
   for each plugin project you create.

    +$$$

    **Note:** If you haven't already created a parent project, you may want to
    consider creating one to share common project information. See the
    [Using Liferay Maven Parent Plugin Projects](/develop/tutorials/-/knowledge_base/6-2/using-liferay-maven-parent-plugin-projects)
    tutorial for details.

    $$$

2. Execute the following command: 

        mvn archetype:generate

    Archetype starts and lists the archetypes available to you. You're prompted
    to *choose* an archetype or *filter* archetypes by group / artifact ID. The
    output looks like this: 

        ...
        39: remote -> com.liferay.maven.archetypes:liferay-hook-archetype
        (Provides an archetype to create Liferay hooks.)
        40: remote -> com.liferay.maven.archetypes:liferay-layouttpl-archetype
        (Provides an archetype to create Liferay layout templates.)
        41: remote -> com.liferay.maven.archetypes:liferay-portlet-archetype
        (Provides an archetype to create Liferay portlets.)
        42: remote -> com.liferay.maven.archetypes:liferay-portlet-icefaces-archetype
        (Provides an archetype to create Liferay ICEfaces portlets.)
        43: remote -> com.liferay.maven.archetypes:liferay-portlet-jsf-archetype
        (Provides an archetype to create Liferay JSF portlets.)
        ...
        Choose a number or apply filter (format: [groupId:]artifactId,
        case sensitive contains):

    +$$$
    
    **Important:** Sometimes, after a new Liferay release, Liferay's most
    up-to-date Maven artifacts may not be available from the default *Maven
    Central* repository. In this case, you must use the artifacts stored at
    [repository.liferay.com](https://repository.liferay.com). To do this, use
    the `-DarchetypeCatalog` option to point to the Liferay Repository when
    generating Maven archetypes. For example:

        mvn archetype:generate -DarchetypeCatalog=https://repository.liferay.com/nexus/content/repositories/liferay-public-releases

    You'll also need to configure a couple other files to ensure the generation
    command completes successfully. You can refer to the
    [Installing Artifacts from the Liferay Repository](/develop/tutorials/-/knowledge_base/6-2/managing-liferay-maven-artifacts#installing-artifacts-from-the-liferay-repository)
    section of the *Managing Liferay Maven Artifacts* tutorial to configure
    Maven to access the Liferay Repository for Liferay Maven artifacts.

    $$$

3. To find the right Liferay archetype for your project, you can either scroll
   up to find it or apply filters to narrow the set of results. Filtering on
   *liferay* as your group ID, and a plugin type (*portlet*, *hook*, *theme*,
   etc.) can help you focus on more applicable Liferay archetypes. 

    Entering `liferay:portlet` as a filter gives a listing of Liferay portlet
    archetypes: 

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

4. Choose an archetype by entering its number. 

5. You're prompted to choose the archetype version. Enter the number
   corresponding to the Liferay version for the archetype. You're not required,
   however, to select the archetype version that corresponds with your Liferay
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
        12: 6.1.30.1
        ...
        23: 6.2.1
        24: 6.2.10.4
        25: 6.2.10.5
        26: 6.2.10.6
        27: 6.2.10.7
        28: 6.2.10.8
        29: 6.2.10.9
        30: 7.0.0-m1
        31: 7.0.0-m2
        Choose a number: 31:

6. Enter values for the *groupId*, *artifactId*, *version*, and *package*
   coordinates (properties) of your project. Here are some examples: 

        groupId: com.liferay.sample
        artifactId: sample-portlet
        version: 1.0-SNAPSHOT
        package: com.liferay.sample

    This process is illustrated in the snapshot below:

    ![Figure 1: When creating your portlet plugin, you must enter your *groupId*, *artifactId*, *version*, and *package* properties.](../../images/maven-portlet-plugin-settings.png)

    For more information on defining Maven coordinates, see
    [http://maven.apache.org/pom.html#Maven_Coordinates](http://maven.apache.org/pom.html#Maven_Coordinates).

7. Enter the letter *Y* to confirm your coordinates.

    Maven's Archetype tool creates a Liferay plugin project directory with a new
    `pom.xml` file and source code. 

+$$$

**Note:** The archetype file is
downloaded and installed automatically to your local repository (e.g.,
`.m2/repository/com/liferay/maven/archetypes/[archetype]`). If you
configured a mirror pointing to your public repository on Nexus, the
plugin is installed there. 

$$$

Following these steps, you can use Archetype to generate all your Liferay plugin
projects! 

Plugin projects generated from a Liferay archetype are equipped with a POM
that's ready to work with a parent project. It inherits the values for
`liferay.version` and `liferay.auto.deploy.dir` properties from the parent. 

When your plugin is created, you can package and deploy your project to a
specified Liferay instance. You can even install and deploy the individual
plugin to a remote repository. 

## Related Topics [](id=related-topics)

[Deploying Liferay Plugins with Maven](/develop/tutorials/-/knowledge_base/6-2/deploying-liferay-plugins-with-maven)

[Developing Liferay Theme Plugins with Maven](/develop/tutorials/-/knowledge_base/6-2/developing-liferay-theme-plugins-with-maven)

[Developing Liferay Layout Template Plugins with Maven](/develop/tutorials/-/knowledge_base/6-2/developing-liferay-layout-template-plugins-with-mav)

[Developing Liferay Plugins with Additional Maven Archetypes](/develop/tutorials/-/knowledge_base/6-2/developing-liferay-plugins-with-additional-maven-ar)

