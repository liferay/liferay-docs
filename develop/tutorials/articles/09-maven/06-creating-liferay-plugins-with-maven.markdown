# Creating Liferay Plugins with Maven [](id=creating-liferay-plugins-with-maven)

Liferay offers many archetypes to help create Maven projects for multiple plugin
types, including portlet, theme, hook, and layout template plugins. We provide
archetypes for each of these plugin types for various versions of Liferay, so
you almost certainly have the archetype you need. 

*Archetype* is Maven's project templating toolkit. Let's use it to create a
Liferay portlet project. With Archetype, you can use the same steps we detail
below to generate Liferay plugin projects of any type. 

+$$$

**Note:** Make sure Maven is installed and that its executable is in your path
environment variable. 

$$$

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

	![Figure 9.9: You can build a Liferay Plugin Project using Maven by completing the setup wizard.](../../images/create-wizard-maven-ide.png) 

Great! You've successfully created a Liferay portlet project using Maven in
Liferay IDE! Next, let's run through steps for creating your Liferay Maven
plugins using the command line. 

1.  Open the command prompt and navigate to the parent directory in which you
    want to create the plugin project. Archetype will create a sub-directory for
    the plugin project you create.

    +$$$

    **Note:** If you haven't already created a parent project, you may want to
    consider creating one to share common project information. See section
    *Using a Parent Plugin Project* for details.

    $$$

2.  Execute the command

        mvn archetype:generate

    Archetype starts and lists the archetypes available to you. You're prompted
    to *choose* an archetype or *filter* archetypes by group / artifact ID. The
    output looks similar to the following text: 
    
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
        Choose a number or apply filter (format: [groupId:]artifactId, case sensitive contains):

3.  To find the right Liferay archetype for your project, you can either scroll
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

4.  Choose an archetype by entering its number.

5.  You're prompted to choose the archetype version. Enter the number
    corresponding to the Liferay version for the archetype. However, you're not
    required to select the archetype version that corresponds with your Liferay
    instance; older archetype versions are compatible with updated Liferay
    bundles. 

    The snapshot below illustrates choosing the archetype version: 

    ![Figure 9.10: You're prompted by Maven Archetype to enter the archetype version.](../../images/maven-archetype-version.png)

6.  Enter values for the *groupId*, *artifactId*, *version*, and *package*
    coordinates (properties) of your project. Here are some examples: 

        groupId: com.liferay.sample
        artifactId: sample-portlet
        version: 1.0-SNAPSHOT
        package: com.liferay.sample

    This process is illustrated in the snapshot below:

    ![Figure 9.11: When creating your portlet plugin, you must enter your *groupId*, *artifactId*, *version*, and *package* properties.](../../images/maven-portlet-plugin-settings.png)

    For more information on defining Maven coordinates, see
    [http://maven.apache.org/pom.html#Maven_Coordinates](http://maven.apache.org/pom.html#Maven_Coordinates).
    
7.  Enter the letter `Y` to confirm your coordinates.

    Maven's Archetype tool creates a Liferay plugin project directory with a new
    `pom.xml` file and source code. 

+$$$

**Note:** The archetype file is downloaded and installed automatically to your
local repository (e.g.,
`.m2/repository/com/liferay/maven/archetypes/<archetype>`). If you configured a
mirror pointing to your public repository on Nexus, the plugin is installed
there. 

$$$
    
![Figure 9.12: Your archetype and its dependencies are now available in your local repository.](../../images/maven-public-plugin.png)

Following these steps using Liferay IDE or the command line, you can use
Archetype to generate all your Liferay projects!

Plugin projects generated from a Liferay archetype are equipped with a POM
that's ready to work with a parent project. It inherits the values for
`liferay.version` and `liferay.auto.deploy.dir` properties from the parent. 

When your plugin is created, you can package and deploy your project to a
specified Liferay instance. You can even install and deploy the individual
plugin to a remote repository. 

Next we'll go through some brief examples to demonstrate deploying your plugins
to Liferay Portal using Maven. 
