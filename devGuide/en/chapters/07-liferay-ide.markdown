# Liferay IDE

Liferay's developers use a variety of tools to develop the product and
as a consequence of that they have always tried hard to allow other
developers to use any tools they wanted for their own development.
Because of this you can develop portals based on Liferay with complex
IDEs Eclipse, Netbeans or IntelliJ Idea or just use text editors such as
Notepad. You can write your persistence layer directly using SQL and
JDBC or you can use advanced object-relational mapping libraries such as
hibernate or iBatis.

But while being agnostic is great, specially for more experienced
developers who can reuse their existing knowledge, it can be
overwhelming for newcomers. For that reason Liferay has also develped
specific development tools that can be used to ease the learning curve
when developing portlets with Liferay. The most significant of these
tools is Liferay IDE, a fully featured Integrated Development
Environment based on Eclipse.

Liferay IDE is an extension for the Eclipse platform that supports
development of plugin projects for the Liferay Portal platform. It is
available as a set of Eclipse plugins installable from an update-site.
The latest version supports developing 5 Liferay plugin types: portlets,
hooks, layout templates, themes, and ext plugins. Liferay IDE requires
the Eclipse Java EE developer package using either Galileo or Helios
versions.

The first two sections below show how to install and set-up Liferay IDE
within your environment. If you are using a copy of Liferay Developer
Studio, which comes with Liferay Portal Enterprise Edition, you can skip
directly to the section titled “Testing the Liferay portal server” since
it comes already preconfigured.

## \
\

## Installation

This section is a detailed guide to install Liferay IDE.

### Requirements

-   Java 5.0 JRE

-   [Eclipse Helios Java EE for developers
    package](http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/heliossr2)

### Installation steps

1.  Install Eclipse Helios or Galileo (unzip download file from above)

2.  Run eclipse executable (eclipse.exe)

3.  When eclipse opens, go to Help \> Install New Software...

![image](../../images/07-liferay-ide_html_m279a9ed9.png)

4.  In the “Work with” field, paste in one of the following update
    sites:

1.  [http://releases.liferay.com/tools/ide/eclipse/helios/stable/](http://releases.liferay.com/tools/ide/eclipse/helios/stable/)

2.  [http://releases.liferay.com/tools/ide/eclipse/helios/nightly/](http://releases.liferay.com/tools/ide/eclipse/helios/nightly/)
    (Nightly builds are unstable but contain new features more quickly)

5.  After pasting in URL press enter

![image](../../images/07-liferay-ide_html_1ed38889.png)

5.  The Liferay IDE features should be detected, select it before
    clicking Next

After calculating dependencies, click Next to complete installation.

After installation restart Eclipse to verify installation is complete.

After restarting Eclipse go to Help \> About and you should see and icon
badge for Liferay IDE that shows you have it properly installed

![image](../../images/07-liferay-ide_html_m51a5c105.png)

\

### Alternative installation

The next set of instructions describe how to install Liferay IDE without
typing the URL of an update site:

1.  Instead of typing in a URL, you can download the the archived update
    site from this link
    [liferay-ide-eclipse-updatesite-1.2.2.zip](http://sourceforge.net/projects/lportal/files/Liferay%20IDE/1.2.2/liferay-ide-eclipse-updatesite-1.2.2.zip/download)
    .

2.  In Add Site dialog, click the "Archive" button and browse to the
    location of the downloaded zip file.

3.  Installation proceeds the same as previous section.

## Set up

This section describes the setup necessary to begin doing Liferay
development and testing your developments.

### Requirements

Before getting started you should satisfy the next requirements:

1.  Liferay Portal 6.0 (6.0.5 or greater) downloaded and unzipped

2.  Liferay plug-ins SDK (6.0.5 or greater) downloaded and unzipped

3.  Eclipse Helios Java EE Developers + Liferay IDE (see the
    Installation steps)

Note: Earlier versions of Liferay, eg 5.2.x, are not supported yet by
the Liferay IDE

### Liferay Plugins SDK Setup

Before you can begin creating new Liferay plugin projects, a Liferay
Plugins SDK and Liferay Portal must be installed and configured in the
IDE.

1.  Open Eclipse with Liferay IDE installed.

2.  Open Preference page for Liferay \> Installed SDKs (Go to Window \>
    Preferences \> Liferay \> Installed SDKs)

![image](../../images/07-liferay-ide_html_m4624ae4d.png)

\
\

3.  Add your SDK using the add button which brings up the Add SDK Dialog

Browse to the location of your Plug-ins SDK installation

The default name is the name of the directory but you can change it if
you wish

Select OK and you should see your SDK in the list of Installed SDKs

![image](../../images/07-liferay-ide_html_m36c80e29.png)

\
\

Note: multiple SDKs can be added to the preferences but you will need to
select at least one SDK to be the default which is represented by the
SDK that has the checkbox selected.

### Liferay Portal Tomcat Runtime / Server Setup

\1. In Eclipse open the Runtime environments preference page (Go to
Window \> Preferences \> Server \> Runtime environments)

![image](../../images/07-liferay-ide_html_5dab0a6d.png)\

\
\

\2. Click Add to add a new Liferay runtime and find “Liferay v6.0
(Tomcat 6)” under the Liferay, Inc. category and click Next.

![image](../../images/07-liferay-ide_html_51045cb8.png)\

\
\

\3. Click Browse and select the location of the liferay-portal-6.0.x
directory

\
\

![image](../../images/07-liferay-ide_html_m32b5ee0a.png)\
\

\4. Once you have selected the Liferay portal directory, if a bundle JRE
is present, this bundle will be automatically selected as the JRE to use
for launching the server. However, if there is no JRE bundle (Mac and
Linux users) then you will need to select the JRE to use for launch.

![image](../../images/07-liferay-ide_html_m348fae55.png)

\5. Click finish and you should see the Liferay portal runtime in the
list of runtimes in the preference page

![image](../../images/07-liferay-ide_html_m45bc3448.png)

\6. Click ok to save the runtime preferences.

\7. If you didn't choose to create a server you will need to create one
from the servers view before you can test the server.

![image](../../images/07-liferay-ide_html_m38e3dcb7.png)

\8. Find the Liferay, Inc category and select the Liferay v6 Server and
choose the Liferay v6 Runtime that you had previously created

\
\

![image](../../images/07-liferay-ide_html_57961a14.png)\
\

## Testing/Launching Liferay Tomcat Server

Once your Liferay Portal Server setup is complete you can launch the
server from the servers tab of Eclipse. Simply select the server and
then click on the green “Start the Server button.” Once the server is
running a red “Terminate” button will appear which you can use to stop
the server

\
\

![image](../../images/07-liferay-ide_html_69e30c2d.png)

\

![image](../../images/07-liferay-ide_html_73531fcd.png)

\

Alternatively, you can start/stop the server by selecting “Run” or
“Terminate” from the Run menu of Eclipse.

\

Once the server is finish launching you can open the portal home from
the context menu of the Liferay Tomcat server in the Servers view:

\

![image](../../images/07-liferay-ide_html_1bbde26f.png)

## Creating New Liferay Projects

Now that an SDK and portal server have been configured you can create a
new Liferay plug-in project. Go to File \> New Project... \> Liferay \>
Liferay Plug-in Project. \
\
![image](../../images/07-liferay-ide_html_m3915d6e7.png)

\

In the Liferay project wizard page, the newly configured SDK and Liferay
Runtime should be pre-selected. Also the plugin type “portlet” is the
default. Simply type in a project name and cooresponding display name
and click Finish.

\
![image](../../images/07-liferay-ide_html_5da8edc3.png)

If it worked you should see a new plugin project in the package
explorer. \
\
![image](../../images/07-liferay-ide_html_m32906e8b.png)

## Deploying New Liferay Projects to Liferay Server

Now with the newly created project, right-click the Liferay Server in
the Servers view and select “Add and Remove...” \
\
![image](../../images/07-liferay-ide_html_m26feb51a.png)

\

Select the new project and click “Add” to move it to be deployed to the
server. \
\
![image](../../images/07-liferay-ide_html_m3c777799.png)\
\
and then click "Finish"

\

You should see the project get deployed to Liferay Tomcat server and in
the console see a message about 1 new portlet being available:\
\
![image](../../images/07-liferay-ide_html_7f355ff8.png)

\

Open Liferay Portal Home http://localhost:8080/ in most cases and login
within the following credentials:

user: **test@liferay.com**

password: **test**

Click Add \> More, expand the Sample Category and click the Add link
next to the Demo application. You should see the correctly deployed
portlet on the page.![image](../../images/07-liferay-ide_html_mfcc7588.png)

## Importing Existing Projects into Liferay IDE

If you have been following the examples of this guide using the Plugins
SDK or have previous plugins developed with it that you want to keep
developing with Liferay IDE then this section is for you. It also shows
other options to import. Specifically it shows how to import from:

\

1.  Existing Liferay projects that are not in Eclipse workspace

2.  Projects already in Eclipse but not Liferay IDE (don’t have Liferay
    facet or target runtime)

3.  Existing Liferay IDE projects from another version of Liferay IDE or
    workspace

\

The following subsections describe the steps for each of them with more
detail.

### Importing existing Liferay Project from a Plugins SDK

An existing Liferay project that has been created by the Plugins SDK but
has not yet been added to an Eclipse workspace or have not been added to
the current workspace open in Eclipse/Liferay IDE. These projects may or
may not have .project or .classpath files. Whether they do or don’t we
will follow the same steps and use the same wizard.

There are two options to create projects from existing sources,
depending on whether you want to create one single project or multiple
projects from the same SDK. Let's see both in detail.

\

Note: The two next sections assume that you have Liferay plug-ins SDK
directory structure and you have previously created projects inside of
that directory structure

#### Create one single project from one plugin in an existing sources

This option will create one project for a single plugin that already
exists inside a Plugins SDK.

1.  In Eclipse, go to File \> New \> Project... \> Liferay \> Liferay
    Project from Existing Source

![image](../../images/07-liferay-ide_html_m5f174547.png)

    Or you can invoke the same wizard from the Liferay shortcut bar

![image](../../images/07-liferay-ide_html_21d4e999.png)

\

2.  Browse to the location of the project folder. Note: the project
    folder should be a sub-directory of one of the plugin types, e.g.
    portlets, hooks, themes, etc. If not it will not be able to be
    imported correctly.Once you select the plugin project folder you
    should see the plugin type and SDK version values get updated to
    correct values. If the SDK is not recent enough or project type is
    not correct it will be marked with an error.

![image](../../images/07-liferay-ide_html_m594aa2ed.png)

\

3.  Once you select the plugin project folder you should see the plugin
    type and SDK version values get updated to correct values. If the
    SDK is not recent enough or project type is not correct it will be
    marked with error

![image](../../images/07-liferay-ide_html_m439f6b9a.png)

4.  Next you will need to select a Liferay Runtime to configure on the
    project once it is imported. If you don’t have a Liferay Runtime,
    use the New... button to create a new Liferay portal runtime (tomcat
    bundle only supported).

![image](../../images/07-liferay-ide_html_m6e4096f.png)

5.  Click Finish to perform the import

\

6.  Read the section below on verifying the success of an import process
    to make sure that your project was configured correctly as a Liferay
    IDE project.

#### Create multiple projects for all plugins in a Plugins SDK

This option will transverse an existing Plugins SDK and will allow
creating one project for each of the plugins it finds inside in one
single step.

1.  In Eclipse go to File \> Import... \> Liferay \> Liferay Plugin SDK
    projects

![image](../../images/07-liferay-ide_html_m68bdfad4.png)

2.  First you must select the Plugins SDK that you want to import
    projects from in the combo box at the top of the wizard.

![image](../../images/07-liferay-ide_html_m2671a1b2.png)

3.  If you don’t have any SDKs configured in Liferay IDE use the
    “configure” link to add a setting that points to the Plugins SDK
    that you want to import projects from.

\

4.  To configure a Plugins SDK on the Installed SDKs on the pref page
    just click “Add” and then Browse to the directory of the Plugins SDK
    root directory.

5.  Once you have a configured Plugins SDK, you can select it in the
    Combo box and then the SDK location and version will be filled in.
    If either are not valid it will be marked with an error.

![image](../../images/07-liferay-ide_html_m10d4c2ac.png)\

6.  After the SDK is selected the list of projects that are available
    for import will be shown in the table. If the projects are already
    in the workspace they will be disabled. If the project is available
    for import it will have a empty checkbox that can be selected.

![image](../../images/07-liferay-ide_html_m2338b271.png)

6.  Select which projects that you wish to import.

\

8.  Select the Liferay runtime that you want to setup for the imported
    projects.

\

9.  If you don’t have a liferay runtime you can add one with the
    “New...” button.

![image](../../images/07-liferay-ide_html_m223f67fe.png)

9.  Click Finish.

\

11. Read the section below on verifying the success of an import process
    to make sure that your project was configured correctly as a Liferay
    IDE project.

### Importing an existing Eclipse Project that is not aware of the
Liferay IDE

If your project is not in your Eclipse workspace, you can use the first
set of steps above. If your project is already in your workspace (see it
in project explorer) but is not yet a Liferay IDE project, the following
steps can be used to convert the project.

\

1.  In Eclipse, right click the eclipse project that you want to
    convert, select Liferay \> Convert to Liferay plug-in project.

    Note: If you don’t have a convert action available it means the
    project is either already a Liferay IDE project or it is not a
    faceted project with Java and Dynamic Web project facets configured
    and will need to be configured accordingly

\

![image](../../images/07-liferay-ide_html_55640f04.png)\

2.  When the convert dialog wizard opens your project should be
    auto-selected and the SDK location and SDK version should be
    auto-detected. If they are not valid an error message will be
    displayed.

\

![image](../../images/07-liferay-ide_html_m5a26f80e.png)

\

3.  Select the Liferay runtime that you wish to set on the project. If
    you don’t have a Liferay Runtime define use the “New...” action to
    create one.

![image](../../images/07-liferay-ide_html_m223f67fe.png)

4.  Click Finish.

5.  Read the section below on verifying the success of an import process
    to make sure that your project was configured correctly as a Liferay
    IDE project.

### Importing an existing Liferay IDE project

This section describes the steps that can be followed if you have
previously created or converted a Liferay IDE project in your workspace
but it is no longer in the current workspace there are a couple of
options for importing this project.

\

1.  Open Liferay IDE, go to File \> Import ... \> General \> Existing
    Projects into Workspace

2.  Use option Select root directory, then click Browse

3.  Select the directory of the previous Liferay IDE project

\

4.  Then in the list of projects you should see the one project you
    selected

\

5.  Click Finish

\

6.  Read the section below on verifying the success of an import process
    to make sure that your project was configured correctly as a Liferay
    IDE project.

If you have any errors, it may be that either the SDK name used in that
project or the runtime id used doesn’t exist in your new workspace. You
can modify the SDK name in the Project Properties \> Liferay page and
you can modify the targeted runtime in the Project properties \>
Targeted Runtimes page.

### Verifying that the import has succeeded

Follow the following steps to verify that either of the previous import
processes has been successful.

1.  Once the project import process is finished, you should see a new
    project inside Eclipse and it should have a “L” overlay image to
    show its a Liferay project.

![image](../../images/07-liferay-ide_html_7b76626b.png)

2.  Secondly, to make sure the project is now a “Liferay IDE” project is
    to check the target runtime project property (right-click project \>
    properties \> target runtimes) and also check the project facets to
    make sure both Liferay runtime and Liferay plug-in facets are
    properly configured.

![image](../../images/07-liferay-ide_html_m558c3ddb.png)

![image](../../images/07-liferay-ide_html_m593b8c8.png)

## Developing on a Remote Server (new)

