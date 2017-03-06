# Advanced Monitoring: APM Tools - Dynatrace [](id=advanced-monitoring-apm-tools-dynatrace-idadvanced-monitoring-apm-tools-dynatrace)  
Advanced performance monitoring tools like
[Dynatrace's](https://www.dynatrace.com) are available for system administrators
looking for more detailed information about how their @product@ servers and
instances are performing. Using Dynatrace's web-based dashboards can display
information various topics such as memory leaks, garbage collection, CPU levels,
and heap dumps analyses. Each dashboard is its own detailed report. One huge
advantage of using Dynatrace is that Dynatrace offers a real time connection
(compared to competitors who conduct sampling instead). System administrators
can view very up to date information on @product@ servers' performance metrics
down to the individual user.
 
To use Dynatrace's dashboards with @product@, three things are needed: a
Dynatrace client which contains the user interface (UI), a Dynatrace license,
and the Dynatrace agent.
 
Lastly, system administrators should also sign up for a Dynatrace account if
they have not already done so; this way they can have access to Dynatrace's
support teams.

## Dashboards [](id=dashboards)

Multiple dashboards are available to display
ongoing transactions and processes. Because the entire Liferay DXP stack can be
analyzed, there are dashboards for every aspect: web server, browser,
application server, and database. Some of the images below are from a single
instance (not clustered) of one application server (Apache Tomcat 8.0.32)
connected to a MySQL 5.7 server. Each person's setup will differ but the default
dashboards are the same. 

![Figure 1 shows the top level menu to select which environment to analyze.](../../images-dxp/dynatrace01.png)

Select _Tomcat_.

This is what systems administrators are most interested in viewing: monitoring
the app server - where the rubber meets the road.

![Figure 2 shows how the application server is doing.](../../images-dxp/dynatrace02.png)

Returning to the root level allows the administrators to view performance
metrics from other parts of the environment. For example, clicking MySQL will
show database transactions.

![Figure 3 shows the database transactions](../../images-dxp/dynatrace04-db.png)

One available default dashboard tracks the browser-based performance. Categories
include "satisfied" and "frustrated" visits.

![Figure 4 shows the browser-based metrics](../../images-dxp/dynatrace05-visits.png)

Click the Dashboard menu > Open. Here administrators can import existing
dashboards in XML format.

## Liferay Digital Enterprise FastPack [](id=liferay-digital-enterprise-fastpack)
The Liferay Digital Enterprise FastPack is available for download on
the [Dynatrace site](https://community.dynatrace.com/community/display/DL/Liferay+Digital+Enterprise+FastPack). 
The FastPack contains special dashboards and a
profile developed specially with Liferay. These dashboards go beyond the out of
the box dashboards already available through Dynatrace.    
To use them, open each XML file in a text editor. Replace the values for the elements `server://`
with the server name (e.g. liferay-abc123); do not use the default values.

![Figure 5 shows a custom dashboard for Database Performance taken from the FastPack](../../images-dxp/dynatrace06.png)


## Dynatrace Client Configuration [](id=dynatrace-client-configuration)

As a prerequisite to running both the latest Dynatrace Client, FastPack, and
@product@, Java JDK 1.8 (or its equivalent) must be installed.

1.  Install the Dynatrace environment. Follow the steps outlined from the
    [Dynatrace Installation Step 1](https://community.compuwareapm.com/community/display/EVAL/Step+1+-+Download+and+install+dynaTrace). The installation files are available for Windows, Unix, and Linux systems.     
    a. For Windows users, download then install the full `.msi` [file](https://files.dynatrace.com/downloads/appmon/freetrial/dynatrace-full-x86-64.msi) (approx. 770MB).    
            I. Be sure to check the "Immediately activate the Dynatrace .NET agent."    
            II. Be sure to check the "Immediately activate the Dynatrace IIS agent."    
            III. Be sure to start all three: Dynatrace Server, Collector, and Client    
    b. For Linux users, the installation files are packaged as executable jar. Download
    the full [jar](https://files.dynatrace.com/downloads/appmon/freetrial/dynatrace-full-linux-x86-64.jar). Run the command `java -jar dynatrace-full-linux-x86-64.jar` wherever the jar has been downloaded.   

2.  Start the Dynatrace instance and deploy the Dynatrace trial license. (See
below.)     
        a. Request the Dynatrace license key; the key is available
    either through the  administrator's web credentials or as a downloadable file
    in the welcome email. In the latter case, download the file and place it at
    a location. When the client starts for the first time and prompts for the
    license, navigate to the location.    
        b. Once the license has been successfully imported, the Dynatrace Client will prompt for a server restart.

3.  After the client has been restarted, verify that the following have started:     
    a. Dynatrace Server     
    b. Dynatrace Front-End    
    c. Dynatrace Collector
    
    This can be done in the command line (Unix/Linux style):    
     ``` 
    # ps -A | grep dt    
    3954 ?00:00:43 dtcollector    
    5924 ?00:01:54 dtserver    
    5949 ?00:00:42 dtfrontendserver    
    ```    
    * TCP ports    
    ``` # netstat -an | grep 021    
     tcp  0  0 :::2021 :::* LISTEN(port for dynaTrace client connections)    
     tcp  0  0 :::8021 :::* LISTEN(the dynaTrace server's web interface) 
     ```

    ```    
        # netstat -an | grep 99    
        tcp        0      0 :::6699    :::*     LISTEN    (port for dynaTrace collector connections)    
        tcp        0      0 :::9998    :::*     LISTEN    (port for dynaTrace agent connections)
    ```

### Dynatrace Agent Configuration [](id=dynatrace-agent-configuration)

Deploy the Dynatrace agent to the @product@ servers. The agent sits on top of existing
infrastructure (in this case, Apache Tomcat).    

1.  Different agent jars are available depending on which operating system;    
     a. For example, those on a Linux x64 distribution can use [this one](https://files.dynatrace.com/downloads/OnPrem/dynaTrace/6.5/6.5.0.1289/dynatrace-agent-6.5.0.1289-unix.jar).    
     b. The Windows agent can be found [here](https://files.dynatrace.com/downloads/OnPrem/dynaTrace/6.5/6.5.0.1289/dynatrace-agent-6.5.0.1289-x86.msi). Use this agent only if the Dynatrace 
Client and the @product@ bundle on Tomcat are connected remotely. Administrators
must install the Windows agent (.msi) on the same machine where the _@product@_
is located.      

2.  The location of the agent must be set in each application server.    
    a. If the client and the bundle are on the same machine, no
further installations are required; further configuration is still required. The
agent is already included in the full installation. (This configuration is only
for testing and demonstration purposes; the Dynatrace Client's JVM requirements
can be different from @product@'s.)     
    b. Regardless, in both instances, edit the setenv.bat|sh with the following:
    
        i. `-agentpath:${location of the dtagent .dll}"=name=Tomcat_Monitoring,server=liferay-cfe3684:9998`    
        ii. For example in Windows: `-agentpath:"C:\Program Files\Dynatrace\Dynatrace
6.5\agent\lib64\dtagent.dll"=name=Tomcat_Monitoring,server=liferay-cfe3684:9998 `    
    c. For other application servers, place the property in the file where other JVM
settings are set (for example, `standalone.conf.bat` for JBoss EAP)    

3.  Connect the @product@ with the Dynatrace agent to the Dynatrace instance. The steps are
    available on [Dynatrace Installation Step 3](https://community.dynatrace.com/community/display/EVAL/Step+3+-+Connect+Agent+to+Dynatrace). Click the Application Servers tab on the right.  
    The example uses Apache Tomcat but according to
    Dynatrace, the steps are virtually the same for JBoss and WebSphere.    
    a. Configure the Client -> Monitoring    
    b. Select Java    
    c. Select App Server    
    d. Select Apache 5+     
    e. Select Java 5 or later (64 bit)    
    f. Select whether the Tomcat bundle is local or remote.    
    g. As long as the connector is on, it should find the @product@ instance. For testing
    purposes, the @product@ bundle is local. 

4.  After you import the fast pack, you will need to:    
     a. Select the Liferay profile as the the only active system profile in Dynatrace, and    
     b. Restart the application server.    

5.  Only then your Liferay agent will start sending data into the newly imported profile.


## Security [](id=security)

Dynatrace will mask confidential data.

## Resources [](id=resources)
1. [Dynatrace Community](https://community.compuwareapm.com) 
2. [Dynatrace Installation Step 1](https://community.compuwareapm.com/community/display/EVAL/Step+1+-+Download+and+install+dynaTrace)
3. [Dynatrace Installation Step 2](https://community.compuwareapm.com/community/display/EVAL/Step+2+-+Activate+License+Key) 
4. [Dynatrace Installation Step 3](https://community.dynatrace.com/community/display/EVAL/Step+3+-+Connect+Agent+to+Dynatrace)
