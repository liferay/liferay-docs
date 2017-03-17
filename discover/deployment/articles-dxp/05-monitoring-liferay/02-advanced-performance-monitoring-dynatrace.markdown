# Advanced Monitoring: APM Tools - Dynatrace [](id=advanced-monitoring-apm-tools-dynatrace-idadvanced-monitoring-apm-tools-dynatrace)  

Advanced performance monitoring tools like
[Dynatrace's](https://www.dynatrace.com) are available for system administrators
looking for more detailed information about how their @product@ servers and
instances are performing. Dynatrace's web-based dashboards can display
real time system info on memory usage, garbage collection, CPU levels, and heap
dumps. Each dashboard is its own detailed report. System administrators can view
up to date information on @product@ servers' performance metrics down to
the individual user.
 
To use Dynatrace's dashboards with @product@, three things are needed: a
Dynatrace client which contains the user interface (UI), a Dynatrace license,
and the Dynatrace agent.
 
Lastly, system administrators should also sign up for a Dynatrace account if
they have not already done so; this way they get access to Dynatrace's support
teams.

## Dashboards 

Multiple dashboards are available to display ongoing transactions and processes.
Because the entire @product@ stack can be analyzed, there are dashboards for
every component: web server, browser, application server, and database. Some of
the images below are from a single instance (not clustered) of one application
server (Apache Tomcat 8.0.32) connected to a MySQL 5.7 server. Installations
differ but the default dashboards are the same. 

![Figure 1: The top level gives you an interface for selecting a component to analyze.](../../images-dxp/dynatrace01.png)

Select _Tomcat_.

This dashboard shows the internal state of the application server. During a load
test, these graphs would be much more interesting. You get a breakdown of CPU
usage, memory, and more. 

![Figure 2: Monitoring the application server during a load gives you valuable information on your system's performance.](../../images-dxp/dynatrace02.png)

Returning to the root level allows administrators to view performance metrics
from other parts of the environment. For example, clicking MySQL shows database
transactions.

<!-- Don't you need a separate agent on your database server for this to work?
-Rich -->

![Figure 3: Database transactions can be viewed as they happen.](../../images-dxp/dynatrace04-db.png)

One available default dashboard tracks the browser-based performance. Categories
include "satisfied" and "frustrated" visits.

<!-- What do "satisfied" and "frustrated" mean? What is browser-based
performance measuring, JavaScript or something else? -Rich --> 

![Figure 4: Browser metrics help you find client-side performance problems.](../../images-dxp/dynatrace05-visits.png)

Click the Dashboard menu &rarr; Open. Here you can import existing dashboards
in XML format.

<!-- Perhaps link to where this XML format is documented? -Rich --> 

## Liferay Digital Enterprise FastPack [](id=liferay-digital-enterprise-fastpack)

<!-- You should introduce this. What is the FastPack? Do we work directly with
Dynatrace? -Rich -->

The Liferay Digital Enterprise FastPack is available for download on the 
[Dynatrace site](https://community.dynatrace.com/community/display/DL/Liferay+Digital+Enterprise+FastPack). 
The FastPack contains special dashboards and a profile developed specially with
Liferay. These dashboards go beyond the out of the box dashboards already
available through Dynatrace. To use them, open each XML file in a text editor.
Replace the values for the elements `server://` with the server name (e.g.
liferay-abc123); do not use the default values.

<!-- So a FastPack contains XML files? I'm sure editing them in a text editor
doesn't install them for use. Please document or point to Dynatrace's
documentation on how you'd install them and use them once you've customized
them. -Rich -->

![Figure 5: The Liferay Digital Enterprise Fastpack contains a Liferay configuration for Dynatrace.](../../images-dxp/dynatrace06.png)

<!-- We can't use the above image for these reasons: 1) There are no
measurements in the dashboards. The image should show data, like it's being
used. 2) The Activate Windows ghost image is showing; this is unprofessional and
cannot appear in any of our screenshots. -Rich -->

## Dynatrace Client Configuration [](id=dynatrace-client-configuration)

As a prerequisite to running both the latest Dynatrace Client, FastPack, and
@product@, Java JDK 1.8 (or its equivalent) must be installed.

1.  Install the Dynatrace environment. Follow the steps from the
    [Dynatrace Installation Step 1](https://community.compuwareapm.com/community/display/EVAL/Step+1+-+Download+and+install+dynaTrace). 
    The installation files are available for Windows, Unix, and Linux systems.

    Windows users, download then install the full `.msi`
    [file](https://files.dynatrace.com/downloads/appmon/freetrial/dynatrace-full-x86-64.msi)
    (approx. 770MB). 

       I. Be sure to check *Immediately activate the Dynatrace .NET agent*. 
       II. Be sure to check *Immediately activate the Dynatrace IIS agent*. 
       III. Be sure to start all three: Dynatrace Server, Collector, and Client 

    Linux users, the installation files are packaged as executable jar. Download
    the full
    [jar](https://files.dynatrace.com/downloads/appmon/freetrial/dynatrace-full-linux-x86-64.jar).
    Run the command `java -jar dynatrace-full-linux-x86-64.jar` wherever the jar
    has been downloaded. 

2.  Start the Dynatrace instance and deploy the Dynatrace trial license (see
below). 

    a. Request the Dynatrace license key; the key is available either through
    your web credentials or from a link in the welcome email. Place the file in
    a suitable location. When the client starts for the first time and prompts
    for the license, navigate to the file. 

    b. Once the license has been successfully imported, the Dynatrace Client
    will prompt for a server restart.

3.  After the server restarts, verify that the following services have started: 

    - Dynatrace Server 
    - Dynatrace Front-End
    - Dynatrace Collector

    You can do this by checking to see if the processes are running: 

        # ps -A | grep dt    

    You should see output like this: 

        3954 ?00:00:43 dtcollector 
        5924 ?00:01:54 dtserver 
        5949 ?00:00:42 dtfrontendserver 

    Use `netstat` to make sure the client is listening on the proper ports: 

        # netstat -an | grep 021    

    You should see output like this: 

         tcp  0  0 :::2021 :::* LISTEN (port for dynaTrace client connections)    
         tcp  0  0 :::8021 :::* LISTEN (the dynaTrace server's web interface) 

        # netstat -an | grep 99    
 
    You should see output like this: 

        tcp        0      0 :::6699    :::*     LISTEN    (port for dynaTrace collector connections)    
        tcp        0      0 :::9998    :::*     LISTEN    (port for dynaTrace agent connections)

Congratulations! You have the Dynatrace client installed! 

### Dynatrace Agent Configuration [](id=dynatrace-agent-configuration)

Deploy the Dynatrace agent to the @product@ servers. The agent sits on top of existing
infrastructure (in this case, Apache Tomcat). 

1.  Download the agent `.jar` for your operating system.  Use this agent only if
    the Dynatrace Client and the @product@ bundle on Tomcat are connected
    remotely. Administrators must install the agent on the same machine
    @product@ is on. 

2.  The location of the agent must be set in each application server. If the
    client and @product@ are on the same machine, you don't have to install
    anything else (the agent is already included in the full installation), but
    you do have to configure it. This configuration is only for testing and
    demonstration purposes; the Dynatrace Client's JVM requirements can be
    different from @product@'s. Either way, edit the setenv.bat|sh: 

        `-agentpath:${location of the dtagent .dll}"=name=Tomcat_Monitoring,server=liferay-cfe3684:9998`    

    For example in Windows: 

        -agentpath:"C:\Program Files\Dynatrace\Dynatrace 6.5\agent\lib64\dtagent.dll"=name=Tomcat_Monitoring,server=liferay-cfe3684:9998     
 
    For other application servers, place the property in the file where other
    JVM settings are set (for example, `standalone.conf.bat` for JBoss EAP). 

3.  Connect the @product@ with the Dynatrace agent to the Dynatrace instance.
    Refer to 
    [Dynatrace's documentation](https://community.dynatrace.com/community/display/EVAL/Step+3+-+Connect+Agent+to+Dynatrace)
    for further details. 
    Click the Application Servers tab on the right. The example below uses
    Apache Tomcat but according to Dynatrace, the steps are virtually the same
    for JBoss and WebSphere. 

    a. Select *Client &rarr; Monitoring*. 
    b. Select *Java*. 
    c. Select *App Server*. 
    d. Select *Apache 5+*. 
    e. Select *Java 5 or later (64 bit)*. 
    f. Select whether the Tomcat bundle is *local* or *remote*. 
    g. As long as the connector is on, it should find the @product@ instance. For testing
    purposes, the @product@ bundle is local. 

4.  After you import the fast pack, you must 
     a. Select the Liferay profile as the only active system profile in Dynatrace, and 
     b. Restart the application server. 

5.  Then your Liferay agent starts sending data into the newly imported profile.


## Security 

Dynatrace will mask confidential data.

<!-- More detail is needed for this, or we should just remove it. -Rich -->

## Resources [](id=resources)

1. [Dynatrace Community](https://community.compuwareapm.com) 
2. [Dynatrace Installation Step 1](https://community.compuwareapm.com/community/display/EVAL/Step+1+-+Download+and+install+dynaTrace)
3. [Dynatrace Installation Step 2](https://community.compuwareapm.com/community/display/EVAL/Step+2+-+Activate+License+Key) 
4. [Dynatrace Installation Step 3](https://community.dynatrace.com/community/display/EVAL/Step+3+-+Connect+Agent+to+Dynatrace)
