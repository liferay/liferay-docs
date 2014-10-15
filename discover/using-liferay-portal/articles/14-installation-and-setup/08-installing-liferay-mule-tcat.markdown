# Installing Liferay on Mule Tcat [](id=installing-liferay-on-mule-tcat)

Liferay Portal Tcat Edition is a combination of Liferay Portal, the leading open
source portal, and Tcat Server, an enterprise grade administration console for
the Apache Tomcat application server.

Tcat Server provides several tools to make Tomcat more administrator friendly,
especially in organizations used to leveraging administration consoles like
those found in other more complex JEE application servers. You may use the
console to:

-   Monitor and control all Tomcat instances on multiple servers and across
    multiple environments 
-   Deploy and view applications across instances
-   Troubleshoot problems across all instances, including view JMX parameters,
    viewing remote logs and more
-   Provide granular entitlement controls for the above functions and more

This isn't a complete guide to Mule Tcat; it just covers Liferay installation.
For full documentation, you'll find more detailed information here:
[http://www.mulesoft.org/documentation/display/TCAT/Home](http://www.mulesoft.org/documentation/display/TCAT/Home)

To obtain Liferay Portal Tcat Edition, you need access to the Liferay Customer
Portal and the Mulesoft Tcat Server web site. If you are not a current Liferay
customer and would like to try the product, please contact Liferay via email at
sales@liferay.com.

For this section, we'll limit ourselves to the Windows and Linux 64-bit
installation wizards. For more detailed installation instructions, please
consult the Mulesoft Tcat Server installation documents located at:

[http://www.mulesoft.org/documentation/display/TCAT/Home](http://www.mulesoft.org/documentation/display/TCAT/Home)

-   [Installing Tcat Server on
    Linux](http://www.mulesoft.org/documentation/display/TCAT/Installing+Tcat+Server+on+Linux)

-   [Installing Tcat Server on
    Windows](http://www.mulesoft.org/documentation/display/TCAT/Installing+Tcat+Server+on+Microsoft+Windows)

-   [Installing Tcat Server on
    Solaris](http://www.mulesoft.org/documentation/display/TCAT/Installing+Tcat+Server+on+Solaris)

-   [Installing Tcat Server on Mac OS
    X](http://www.mulesoft.org/documentation/display/TCAT/Installing+Tcat+Server+on+Mac+OS+X)

-   [Adding Tcat Server Capabilities to an Existing Apache Tomcat
    Install](http://www.mulesoft.org/documentation/display/TCAT/Add+Tcat+Server+Capabilities+to+an+Existing+Tomcat+Install)

First, download the Liferay Tcat bundle from the Liferay customer portal. You'll
need two files:

-   **Liferay Portal 6.1 EE Tcat Admin:** contains the Tcat administration
    console and Liferay Portal EE and all appropriate plugins.

-   **Liferay Portal 6.1 EE Tcat Agent:** contains a vanilla Tomcat application
    server and the Tcat management agent.

Next, download the appropriate Tcat platform installation at:
[http://www.mulesoft.com/download-tcat](http://www.mulesoft.com/download-tcat).
You may select installation wizards for Windows (32 and 64-bit), Mac, Unix,
Solaris and Linux (32 and 64-bit) as well as a manual installation zip.

After obtaining the software bundles, you can proceed with installation and
configuration of the Administration Console.

## Installing the Administration Console on Windows [](id=installing-the-administration-console-on-windows)

For Windows, Tcat comes with an installer to assist with installation and
configuration. After downloading, execute the installer, accepting the
appropriate license agreement.

Upon accepting the license agreement, the system presents you with two
installation choices, a "standard" or "custom" installation.

![Figure 14.4: Installation selection](../../images/tcat-html_2589582e.png)

You should select the "Custom" installation option to provide better control of
where Tcat Server is installed.

![Figure 14.5: Installation component selection](../../images/tcat-html_m230ba580.png)

After selecting the custom installation option, the Tcat installer prompts you
to select the desired components. 

-   **Tcat Server:** a version of Apache Tomcat that includes the appropriate
    management agents used by Tcat.

-   **Administration Console:** the administration console for monitoring and
    managing all available Tcat servers. This console contains tools used for
    application deployment, log access, server control and other administration
    tools.

-   **Tomcat Documentation:** the documentation that comes normally with an
    Apache Tomcat distribution. You do not need to select this option.

-   **Tomcat Examples:** the examples that comes normally with an Apache Tomcat
    distribution. You do not need to select this option.

-   **Tomcat Manager Application:** the manager application that comes normally
    with an Apache Tomcat distribution. You do not need to select this option.

For this step in the installation process, select the Administration Console in
addition to Tcat Server.

![Figure 14.6: Installation location](../../images/tcat-html_261b594a.png)

After selecting Tcat Server and the Administration Console for installation, the
installation wizard prompts you for an installation directory. Please select the
desired installation directory for the Tcat Server.

![Figure 14.7: Port configurations](../../images/tcat-html_66274d0c.png)

After selecting the appropriate installation location, the installation wizard
prompts you to specify the appropriate port numbers. If this is the first time
installing Tcat and no other Apache Tomcat installations are present, then you
may retain the above ports. However, if there are other installations, you will
need to select new ports. For the purpose of this installation, we will assume
the above ports are correct.

![Figure 14.8: Windows service installation](../../images/tcat-html_7936c58a.png)

To ensure the operating system starts the Tcat Server and Administrator Console,
you must configure the Tcat Server process as a Windows service. The next step
in the installation wizard helps with this process.

In the Windows service installation screen, please select "Install Tcat Server
as a Windows service." You may choose an appropriate service name other than
*TcatServer*.

![Figure 14.9: Start menu shortcuts](../../images/tcat-html_3f911eab.png)

The final step in the installation wizard is to configure Start Menu shortcuts.
You may choose to customize the shortcut location or accept the default.

After configuring the shortcuts, the Tcat Server Windows installer performs the
installation as previously configured.

## Installing the Administraton Console on Linux (Ubuntu) [](id=installing-the-administraton-console-on-linux-ubuntu)

For Linux, Tcat comes with an installer to assist with installation and
configuration. After downloading, execute the installer, accepting the
appropriate license agreement. To execute the installer, make sure:

-   you have added execution permissions to the installer file (`chmod a+x [file
    name]`)

-   you execute it with root privileges (don't worry: the installed service is
    executed by a pre-defined system user with no root privileges)

Upon accepting the license agreement, the system presents you with two
installation choices: a "standard" or "custom" installation.

<!-- The following two figures are difficult to read and should be replaced with
ones having more readable text.  -->

![Figure 14.10: Installation type](../../images/tcat-html_766a1d6e.png)

You should select the "Custom" installation option to provide better control of
where Tcat Server is installed.

![Figure 14.11: Installation component selections](../../images/tcat-html_55494177.png)

After selecting the custom installation option, the Tcat installer prompts you
to select the desired components. 

-   **Tcat Server:** a version of Apache Tomcat that includes the appropriate
    management agents used by Tcat.

-   **Administration Console:** the administration console for monitoring and
    managing all available Tcat servers. This console contains tools used for
    application deployment, log access, server control and other administration
    tools.

-   **Tomcat Documentation:** the documentation that comes normally with an
    Apache Tomcat distribution. You do not need to select this option.

-   **Tomcat Examples:** the examples that comes normally with an Apache Tomcat
    distribution. You do not need to select this option.

-   **Tomcat Manager Application:** the manager application that comes normally
    with an Apache Tomcat distribution. You do not need to select this option.

For this step in the installation process, select the Administration Console in
addition to Tcat Server, which is the default setting.

After selecting Tcat Server and the Administration Console for installation, the
installation wizard prompts you for an installation directory.

![Figure 14.12: Installation location](../../images/tcat-html_7df6aabf.png)

Please select the desired installation directory for the Tcat Server. The
offered default directory is different if the installation process has been
started as root.

<!-- The following figure is difficult to read and should be replaced with a
better one.  -->

![Figure 14.13: Port configuration](../../images/tcat-html_5b760cd.png)

After selecting the appropriate installation location, the installation wizard
prompts you to specify the appropriate port numbers. If this is the first time
installing Tcat and no other Apache Tomcat installations are present, then you
may retain the above ports. However, if there are other installations, you will
need to select new ports. For the purpose of this installation, we will assume
the above ports are correct.

![Figure 14.14: Configure Tcat system user](../../images/tcat-html_749e9b40.png)

For security considerations, Tcat runs as a non-root system user. If the
username specified at this step does not exist, it's created as a system daemon
user.

<!-- The following three figures are difficult to read and should be replaced
with better ones.  -->

![Figure 14.15: Install service](../../images/tcat-html_mf7bb10c.png)

To ensure the operating system starts the Tcat Server and Administrator Console,
you must create a service startup script in `/etc/init.d`. The next step in the
installation wizard helps with this process.

In the service installation screen, you may enter an appropriate service name or
use the default. If you have multiple installations of Tcat, you should select a
more appropriate name than what is supplied by default. 

![Figure 14.16: Symbolic link creation](../../images/tcat-html_m55bbff3f.png)

The final configuration step before installation is the creation of a symbolic
link so the Tcat executable can be accessed more easily. This step is optional
and you may choose to not create the symbolic link.

![Figure 14.17: ](../../images/tcat-html_78e73740.png)

Now that Tcat is installed, you can add Liferay to it. 

## Adding Liferay Portal Packages [](id=adding-liferay-portal-packages)

After completing the TcatServer Administration Console installation, you can
configure the Liferay Portal packages for Tcat.

First, extract the previously downloaded Liferay Portal 6.1 EE Tcat Admin into a
temporary directory. Once extracted, locate the file `tcat-init.groovy` and the
directory `tcat\_init`.

![Figure 14.18: Liferay Portal Tcat packages](../../images/tcat-html_12074416.png)

The `tcat-init.groovy` file contains instructions for the TcatServer
administration console to:

-   Pre-load the Liferay Portal and Plugins WAR files into the application
    repository

-   Load the appropriate Liferay application profile that should be applied to
    all other Tcat managed servers

-   Load the Liferay specific deployment manager

The `tcat\_init` folder contains the managed server profiles, Liferay WAR files
and administration scripts.

Copy the `tcat-init.groovy` file and `tcat\_init` folder to the previously
configured installation location.

![Figure 14.19: The installation directory after copying the `tcat\_init` folder and `tcat-init.groovy` into the TcatServer installation directory.](../../images/tcat-html_mf987314.png)

After successfully completing the installation on Windows, you should see a
TcatServer entry in the Services console, similar to the screen shot below. 

![Figure 14.20: Windows services console](../../images/tcat-html_3b2f5fb4.png)

By default, the TcatServer service is inactive but is set to start automatically
upon boot. Go ahead and choose to start the service.

If you're using Linux, you should see an entry for the Tcat service
initialization script in `/etc/init.d`. The script name is the name you choose
during the installation process.

<!--  We might want to replace this screen shot.  -->

![Figure 14.21: Tcat service startup on Linux](../../images/tcat-html_352642da.png)

To start the Tcat Server Administration Console, execute the service script in
`/etc/init.d`.

## Tcat Server Managed Server Installation [](id=tcat-server-managed-server-installation)

The steps to install the Tcat Server managed server are quite similar to those
for installing the Tcat Server Administration Console.

First, launch the wizard. During the installation component configuration step,
unselect "Administration Console" from the list of components.

![Figure 14.22: Installation location](../../images/tcat-html_261b594a.png)

The next step is to specify an installation location. If you have other
installations of Tcat on this machine, you should choose another destination.
For example, if you have already installed the Tcat Server Administration
Console to `C:\\TcatServer6`, you should perhaps install the managed server to
`C:\\TcatServer6Managed`.

![Figure 14.23: Managed server port configurations](../../images/tcat-html_66274d0c.png)

During the managed server installation, as with the administration console
installation, you will be prompted to configure the appropriate port numbers. If
you do not have another Tcat Server instance (e.g. administration console or
another managed server), you may choose the default ports. Otherwise, you should
select non-conflicting ports. For instance:

-   HTTP port: 8180

-   HTTPS redirect port: 81443

-   Tcat Server shutdown port: 8105

-   AJP connector port: 8109

-   Secure Agent Port: 52443

To ensure the operating system starts the Tcat Server and Administrator Console,
you must configure the Tcat process as a Windows service or Linux daemon. The
next step in the installation wizard helps with this process.

The final step in the installation wizard is to configure Start Menu shortcuts.
You may choose to customize the shortcut location or simply accept the default.
Since this is for a managed server, you may wish to name it similar to the
service name.

After configuring the shortcuts, the Tcat Server installer performs the
installation as previously configured.

Once you have installed the managed server, there is one more step to perform on
the managed server. You must modify the `catalina.properties` file located in
the `conf` directory of your Tcat Server installation.

You will need to change the text:

	common.loader=${catalina.base}/lib,${catalina.base}/lib/\*.jar,${catalina.home}/lib,${catalina.home}/lib/\*.jar

To:

	common.loader=${catalina.base}/lib,${catalina.base}/lib/\*.jar,${catalina.home}/lib,${catalina.home}/lib/\*.jar,${catalina.home}/lib/ext,${catalina.home}/lib/ext/\*.jar

By modifying the `common.loader` property, you instruct Tcat Server to load
everything in the `lib/ext` folder as part of the classpath.

## Registering the Managed Server [](id=registering-the-managed-server)

After completing the installation process and starting the appropriate
TcatServer processes, open a browser to `[SERVER NAME]:[PORT]/console`. Using
the previous installation example, you should point your browser to
`http://localhost:8080/console`. The browser should render the TcatServer
Administration Console shown below. 

![Figure 14.24: Tcat Admin Console log in](../../images/tcat-html_m4bda7997.png)

The default login is: admin/admin.

Once you have logged into the administration console, you will be presented with
a global dashboard that you may customize once you have dismissed the "Tcat
Server - Quick Start" panel.

![Figure 14.25: Customizable Tcat Admin Console](../../images/tcat-html_5d43770f.png)

First make sure that you have started the previously installed managed server.
Clicking on the *Servers* tab in the console, you will see a server listed as
*Unregistered*.

Unregistered servers are servers that have the Tcat management agent installed
but are not added to the administration console. You may add the unregistered
server by selecting either *Register* or *Register & Add To Group*.

If you choose to use *Register & Add To Group*, the server is added to the
desired server group (e.g. Development). For the purpose of this guide, we'll
register the server to the Development group.

![Figure 14.26: Monitoring a registered server](../../images/tcat-html_34603e60.png)

After registering the server, you will have access to view its health status,
log files and more. Feel free to walk through the console to examine its
capabilities, like reviewing log files, current thread status and deployed web
applications.

More information is available at
*[http://www.mulesoft.org/documentation/display/TCAT/Home](http://www.mulesoft.org/documentation/display/TCAT/Home).*

**Managing and Monitoring Servers**

-   [Using the Server
    Dashboard](http://www.mulesoft.org/documentation/display/TCAT/Server+Dashboard)

-   [Working with Server
    Groups](http://www.mulesoft.org/documentation/display/TCAT/Working+with+Servers)

-   [Monitoring Servers (View Apps, Threads,
    etc)](http://www.mulesoft.org/documentation/display/TCAT/Monitoring+a+Server)

-   [Monitoring a JMX
    Agent](http://www.mulesoft.org/documentation/display/TCAT/JMX+Agent+Monitoring)

-   [Scripts to Save JMX Metrics to CSV
    Files](http://www.mulesoft.org/documentation/display/TCAT/Saving+JMX+Metric+Data+to+CSV+Files)

**Managing Tcat Web Applications**

-   [Monitoring
    Applications](http://www.mulesoft.org/documentation/display/TCAT/Monitoring+Applications)

-   [Deploying
    Applications](http://www.mulesoft.org/documentation/display/TCAT/Deploying+Applications)

-   [Using the
    Repository](http://www.mulesoft.org/documentation/display/TCAT/Managing+the+Repository)

-   [Setting Security on Repository
    Artifacts](http://www.mulesoft.org/documentation/display/TCAT/Setting+Security)

## Deploying Liferay Portal [](id=deploying-liferay-portal)

As part of the installation process, the current version of Liferay Portal and a
number of Liferay Plugins have been provisioned into the Tcat repository. You
can view them by clicking on the "Repository" tab.

![Figure 14.27: Tcat Repository profiles](../../images/tcat-html_7e61df5a.png)

There are two components in the Tcat Repository:

-   **Profiles:** application profiles to be applied to the Tcat managed
    servers. Contains configuration, shared libraries and more. 

-   **Applications:** deployable WAR files for web applications and Liferay
    Plugins. 

![Figure 14.28: Tcat Repository: Applications](../../images/tcat-html_m4af27eb5.png)

To begin Liferay deployment, we must first create a Liferay license profile to
be deployed to the managed server:

![Figure 14.29: Creating a new workspace for Liferay license profile](../../images/tcat-html_m2d96341c.png)


1. First create a new workspace under *Profiles*. Select *Profiles* in the left
   navigation and then click the *New Workspace* button. 

2. Within the new workspace *Liferay Portal Trial License*, create another
   folder called `deploy`. 

3. In the `deploy` folder, select *New Artifact* and upload a new license file.

After completing the above, you have successfully uploaded your Liferay Portal
license file into the Tcat repository. The final step is to create a server
profile from the *Administration* tab.

*Server Profiles* shows the list of available Server Profiles and the option to
create a new profile.

![Figure 14.30: Viewing server profiles](../../images/tcat-html_m5c7a2b8c.png)

When creating a server profile for the Liferay Portal Trial License, you will
need to select the workspace folder created in the previous step. This ensures
that when you apply the profile, Tcat uses the latest version of the license
file.

Once you've created the server profile for your Liferay Portal EE license, you
may begin deploying Liferay to the managed server.

![Figure 14.31: Apply Liferay Portal trial license](../../images/tcat-html_53af6680.png)

The first step is to apply two server profiles, one for the license and the
other for Liferay Portal. Applying the license profile does not require a
restart of the server.

![Figure 14.32: Apply Liferay profile](../../images/tcat-html_m1f86eaab.png)

The second profile to be applied is the Liferay server profile. In the example
shown we applied the profile for Liferay Portal EE 6.1.

The Liferay Portal server profile requires a server restart. If you have the
managed server configured as a Windows or Linux service, you may restart the
server directly from the console by selecting the server and clicking "Restart".

With both profiles applied, we can now deploy the Liferay Portal WAR to the
appropriate server.

The *Deployments* tab contains tools that assist in creating a deployment and
targeting it to specific servers. Once you create a deployment, you may target
it to any number of servers or deployment groups.

![Figure 14.33: Choosing a web application for deployment](../../images/tcat-html_m54d58d30.png)

When creating a deployment, you may choose a WAR file already uploaded into the
repository or you may choose to upload a new WAR file. We recommend using a WAR
file from the repository for non-development deployments. This ensures you
consistently deploy the correct version to your environments.

Since we are deploying Liferay Portal for the first time, we choose the ROOT.war
file.

After selecting the appropriate web application, you may choose which Servers to
deploy to. In this example, we deploy to the Tomcat instance labeled *Liferay
Portal Instance 1*. 

![Figure 14.34: Completing Liferay Portal deployment](../../images/tcat-html_68a00002.png)

After choosing to deploy the application, the Tcat console informs you of the
current deployment status (e.g. "Successful"). Assuming you followed the
previous steps and the deployment successfully completes, you will be able to
access Liferay Portal on the target Tomcat instance.

![Figure 14.35: Accessing Liferay Portal on Liferay Portal Instance 1](../../images/tcat-html_2b08ac2c.png)

## Deploying Liferay Plugins on Mule Tcat [](id=deploying-liferay-plugins-on-mule-tcat)

Liferay Portal Tcat Edition works with all the appropriate Liferay EE Plugins,
including:

-   Kaleo workflow engine

-   Knowledge base

-   Chat

-   Private messaging

You may choose to deploy these plugins to the appropriate servers by following
the same steps as for deploying Liferay Portal, specifying the appropriate
plugin WAR file instead of the ROOT.war.

Other plugins that may be downloaded and added to this repository include:

-   Audit

-   Report engine and console

-   Drools integration.

You may download these plugins from the Liferay Customer Portal and manually add
them to the Tcat repository.

![Figure 14.36: Uploading new plugins into Tcat](../../images/tcat-html_19e9e6d6.png)

Mule Tcat provides an enterprise grade solution to managing Tomcat servers. If
you need a complete stack, combining Liferay Portal with Mule Tcat gives you
everything you need to run a fully supported and robust portal environment for
your enterprise. 
