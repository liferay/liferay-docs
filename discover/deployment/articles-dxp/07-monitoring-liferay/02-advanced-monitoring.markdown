# Advanced Monitoring: APM Tools; Dynatrace [](id=advanced-monitoring-apm-tools-dynatrace-id)

Advanced performance monitoring tools like
[Dynatrace's](https://www.dynatrace.com)
provide detailed performance information about your @product@ servers and
instances. Dynatrace's web-based dashboards display real time system information
on memory usage, garbage collection, CPU levels, and heap dumps. Each dashboard
is its own detailed report. You can view live information on @product@ server
performance metrics down to the individual user.

+$$$

**Note:** Dynatrace now offers
[*Dynatrace SaaS*](https://www.dynatrace.com/support/dynatrace/).
Its default configuration detects lots of interesting data. Although the
Dynatrace SaaS offering doesn't offer dashboards as deep as those in the
[Liferay FastPack](#liferay-digital-enterprise-fastpack),
its UI is very intuitive. Dynatrace SaaS's big advantage over the installed
Dynatrace app is that it requires no installation, maintenance, or upgrade. 

$$$
 
Using Dynatrace's dashboards with @product@ requires these things:

- Dynatrace client which contains the user interface (UI)
- Dynatrace license
- Dynatrace server 
- installed JVM agents

Finally, sign up for a Dynatrace account to get Dynatrace support.

## Dashboards [](id=dashboards)

Dynatrace contains displays ongoing transactions and processes. Because the
entire @product@ stack can be analyzed, there are dashboards for each component:

- web server
- browser
- application server
- database

Some of the images below are from a single instance (not clustered) of one
application server (Apache Tomcat 8.0.32) connected to a MySQL 5.7 server.
Installations differ but the default dashboards are the same. 

![Figure 1: The top level interface lets you select a component to analyze.](../../images-dxp/dynatrace01.png)

Select the app server (e.g., *Tomcat*).

This dashboard shows the app server's internal state. During a load test, the
graphs are much more interesting. You get a breakdown of CPU usage, memory, and
more. 

![Figure 2: Monitoring the application server during a load gives you valuable information on your system's performance.](../../images-dxp/dynatrace02.png)

The root level shows performance metrics from other parts of the environment.
For example, the database component (e.g., *MySQL*) shows database
transactions: you can view this without installing an agent. Deploying
a separate database agent is optional because the Dynatrace Collector contains
database management functionality out of the box (see 
[Database Monitoring](https://community.dynatrace.com/community/display/DOCDT65/Database+Monitoring).

![Figure 3: You can view live database transactions.](../../images-dxp/dynatrace04-db.png)

One available default dashboard tracks user experience; Dynatrace can monitor
user activity on a web browser or mobile app. Here are the categories for
each visit:

- Satisfied
- Tolerating
- Frustrated

A *satisfied* visit is one in which 1) no action failed; and 2) more than 50% of
all actions were satisfied.

A *frustrated* visit is one in which 1) the last action failed (the web site
does not work); and 2) the user's last action was frustrated (e.g., the web
site was too slow). See 
[How Does UEM Work?](https://community.dynatrace.com/community/pages/viewpage.action?pageId=221381305#HowDoesUEMWork) 
for more information. 

![Figure 4: Browser metrics help you find client-side performance problems.](../../images-dxp/dynatrace05-visits.png)

You can import custom dashboards you've created (e.g., in XML format):

1.  Click the *Dashboard* menu &rarr; *Open*. 

2.  Browse for and select your custom dashboard. 

3.  Click *Open Dashboard*.

But there's more: a custom dashboard created for @product@. 

## Liferay DXP FastPack [](id=liferay-digital-enterprise-fastpack)

The Dynatrace FastPack for Liferay provides a pre-configured Dynatrace profile
custom-tailored for @product@ environments. It contains sensors, a template
system profile with measures and business transactions, and dashboards for
@product@. If you're using UEM, you also get conversion and visitor tagging. 

The Liferay DXP FastPack is available for download on the 
[Dynatrace site](https://community.dynatrace.com/community/display/DL/Liferay+Digital+Enterprise+FastPack). 
These dashboards go beyond dashboards Dynatrace includes out-of-the-box. 

The FastPack is distributed as a `.dtp` file. To install the FastPack, follow 
these steps:

1.  In the Dynatrace Client, click *Tools* &rarr; *Manage Plugins*.

2.  Click *Install Plugin...*.

3.  Browse to and select the `.dtp` file.

4.  Click *OK* in the *Import Resource Pack* confirmation window.

5.  Click *OK* to close the *Configure Plugins* window.

6.  *Liferay* now appears in the Systems Profile left control panel.

7.  Disable any other profile so that the Liferay profile is the only active
    profile.

## Dynatrace Client Configuration [](id=dynatrace-client-configuration)

Java JDK 1.8 (or its equivalent) is a prerequisite to running both Dynatrace
Client and @product@. Make sure to allocate enough CPU cores in the JVM as well
as for running @product@. Install the Fastpack plugin *after* configuring the
Client.

1.  Install the Dynatrace environment. Follow the steps from the
    [Dynatrace Installation Step 1](https://community.compuwareapm.com/community/display/EVAL/Step+1+-+Download+and+install+dynaTrace). 
    The installation files are available for Windows, Unix, and Linux systems.

    Windows users, download and install the full `.msi`
    [file](https://files.dynatrace.com/downloads/appmon/freetrial/dynatrace-full-x86-64.msi)
    (approx. 770MB). 

       - Select *Immediately activate the Dynatrace .NET agent*. 
       - Select *Immediately activate the Dynatrace IIS agent*. 
       - Make sure to start the Dynatrace Server, Collector, and Client 

    Linux users, the installation files are packaged as an executable JAR.
    Download the full
    [JAR file](https://files.dynatrace.com/downloads/appmon/freetrial/dynatrace-full-linux-x86-64.jar).
    Run the command `java -jar dynatrace-full-linux-x86-64.jar` in the JAR's 
    folder. 

2.  Start the Dynatrace instance and deploy the Dynatrace trial license (see
    below). 

    - Request the Dynatrace license key; the key is available either through
      your web credentials or from a link in the welcome email. Place the file
      in a suitable location. When the client starts for the first time and
      prompts for the license, navigate to and select the file. 

    - Once the license has been successfully imported, the Dynatrace Client
      prompts for a server restart. Restart the server. 

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

Congratulations! You installed the Dynatrace Server and Collector! 

### JVM Agent Configuration [](id=dynatrace-agent-configuration)

Deploy a JVM agent on each @product@ server. The agent sits on top of
existing infrastructure (in this example, Apache Tomcat). 

1.  Download the agent `.jar` for your operating system. Use this agent only if
    the Dynatrace Client and the @product@ bundle on the app server are
    connected remotely. You must install the agent on the same machine as each
    @product@ instance. 

2.  The location of the agent must be set in each application server. If the
    client and @product@ are on the same machine, you don't have to install
    anything else (the agent is already included in the full installation), but
    you do have to configure it. This configuration is only for testing and
    demonstration purposes; the Dynatrace Client's JVM requirements can be
    different from @product@'s. Either way, edit the `setenv.bat|sh` file.

    In Unix/Linux `setenv.sh`:

    `-agentpath:${location of the dtagent .dll}"=name=Tomcat_Monitoring,server=liferay-cfe3684:9998` 

    In Windows `setenv.bat`: 

    `-agentpath:"C:\Program Files\Dynatrace\Dynatrace 6.5\agent\lib64\dtagent.dll"=name=Tomcat_Monitoring,server=liferay-cfe3684:9998`
 
    For other application servers, place the property in the file where other
    JVM settings are set (for example, `standalone.conf.bat` for JBoss EAP). 

3.  Connect the @product@ with the JVM agent to the Dynatrace instance. Refer to
    [Dynatrace's documentation](https://community.dynatrace.com/community/display/EVAL/Step+3+-+Connect+Agent+to+Dynatrace)
    for further details. Select the Application Servers tab on the right. The
    example below uses Apache Tomcat but according to Dynatrace, the steps are
    virtually the same for JBoss and WebSphere. 

    - Select *Client &rarr; Monitoring*. 
    - Select *Java*. 
    - Select *App Server*. 
    - Select *Apache 5+*. 
    - Select *Java 5 or later (64 bit)*. 
    - Select whether the Tomcat bundle is *local* or *remote*. 
    - As long as the connector is on, it should find the @product@ instance. For
      testing purposes, the @product@ bundle is local. 

4.  After you import the fast pack, you must do these things:
 
    - Select the Liferay profile as the only active system profile in 
      Dynatrace. 
    - Restart the application server. 

Your Liferay agent starts sending data into the newly imported profile.

## Performance Testing [](id=performance-testing)

Systems administrators must often perform load testing as part of an overall
tuning process. The Dynatrace Client is a great tool to monitor and then analyze
the performance of your system. For demonstration purposes, JMeter was used
to create a simple load test, and the Dynatrace Client captured the 
results illustrated below. 

![Figure 5: Dynatrace Client can generate thread dump reports.](../../images-dxp/dynatrace10-thread-dump.png)

The Dynatrace Client shows garbage collection over time as the number of threads
increase during the load test. 

![Figure 6: Garbage collection statistics at the beginning of the load test.](../../images-dxp/dynatrace07.png)

Here, the Dynatrace Client continues to display in real time the high
consumption during the load test. 

![Figure 7: Garbage collection statistics at the middle of the load test.](../../images-dxp/dynatrace08.png)

One more dashboard to note during a performance test is the CPU Sampling
dashboard. The figure below shows a generated report that captures the CPU
process over 100 seconds. This shows unwanted processes slowing down a @product@
instance.

![Figure 8: Dynatrace Client can generate a CPU Sampling report.](../../images-dxp/dynatrace09-cpu-samplings.png)

All these dashboards come out-of-the-box, and even more dashboards are available
after deploying the Dynatrace FastPack developed for @product@. Dynatrace
provides many useful performance monitoring features. 

## Resources [](id=resources)

[Dynatrace Community](https://community.compuwareapm.com) 

[Dynatrace Installation Step 1](https://community.compuwareapm.com/community/display/EVAL/Step+1+-+Download+and+install+dynaTrace)

[Dynatrace Installation Step 2](https://community.compuwareapm.com/community/display/EVAL/Step+2+-+Activate+License+Key) 

[Dynatrace Installation Step 3](https://community.dynatrace.com/community/display/EVAL/Step+3+-+Connect+Agent+to+Dynatrace)
