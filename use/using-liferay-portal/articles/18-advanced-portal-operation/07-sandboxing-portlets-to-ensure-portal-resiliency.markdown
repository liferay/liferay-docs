# Sandboxing Portlets to Ensure Portal Resiliency [](id=sandboxing-portlets-to-ensure-portal-resiliency-lp-6-2-use-useportal)

![EE Only Feature](../../images/ee-feature-web.png)

The performance, health, and stability of a portal deployment is heavily
dependent upon the portlet modules deployed to it. If one portlet leaks memory
or is extremely slow, your entire portal can crash due to a dreaded
`OutOfMemoryError` or can slow to a crawl. 

Liferay Portal 6.2 introduces a sandboxing feature that enables you to run
troublesome portlets in their own container (or "sandbox"), reducing any adverse
impact they may have on the health and stability of your portal. The feature is
available in Liferay's Sandbox App. The app lets you create sandboxes to run
portlets in separate JVMs, freeing your portal's JVM from the resource
consumption of those portlets. We refer to the portal's JVM instance as the
*Master Portal Instance* (*MPI*) and the sandbox JVMs as *Slave Portal
Instances* (*SPIs*). Since SPIs run on the same host as the MPI, communication
between them is very fast. The fact that sandboxed portlets are running in SPIs
is transparent to your users. Portal users continue to use these portlets as
they normally do.

As a portal administrator, you'll be pleased to know that the app not only gives
you the ability to section off plugins into SPIs, but also gives you the
means to revive a SPI. The Liferay Sandbox App comes with a *SPI Administration*
UI that lets you create, start, stop, and restart SPIs. In addition, it lets you
configure options to restart SPIs that terminate unexpectedly, automatically. 

The sandboxing feature has two limitations. First, only portlet and web
plugins can be deployed on an SPI. Second, the portal ignores SPI portlet
implementation classes that are not remote-safe. Implementation classes (such as
asset renderers and pollers) that register with the portal fall into this
category and are ignored by the portal. Therefore, the sooner you test and
resolve any performance issues in such SPI portlets, the sooner you can deploy
them back onto the Master Portal Instance to leverage such implementation
classes in those portlets. 

The Liferay Sandbox App is available on the Liferay
[Marketplace](http://www.liferay.com/marketplace). You can purchase, install,
and deploy the app as described in this guide's chapter on [Leveraging the Liferay Marketplace](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/leveraging-the-liferay-marketplace-liferay-portal-6-2-user-guide-14-en). 

Before creating and using sandboxes, we must enable the portal's resiliency
functionality and optimize the database connection settings for your sandboxes. 

## Configuring the Portal for Sandboxing

The two types of portal properties you must modify for your portal to use
sandboxing are the [Portal Resiliency](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#Portal%20Resiliency) 
properties and [Database Connection](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#JDBC) 
properties. You can set these in your [`portal-ext.properties`](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/manual-configuration-liferay-portal-6-2-user-guide-15-en)
file. 

You must enable Portal Resiliency by setting the `portal.resiliency.enabled`
property to `true`. In addition, you can optionally enable the portal to show
special footers in sandboxed portlets. The footers display at the bottom of each
sandboxed portlet, indicating the sandbox that is servicing the request. The
footer helps you verify that a portlet is sandboxed and which sandbox it's in.
To enable both of these resiliency properties, specify the following entries in
your `portal-ext.properties` file:

    portal.resiliency.enabled=true
    portal.resiliency.portlet.show.footer=true

If you hadn't previously configured your database connection pools using your
portal properties, you must do so in order to use the sandboxing feature. If
you've been using JNDI to configure data sources on your app server, please
convert to using Liferay's built-in data source by specifying it via
[JDBC](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#JDBC)
properties in your `portal-ext.properties` file. Note, if you attempt to create
or start a sandbox while having incorrect JDBC settings, the Sandbox
Administration console displays a warning. 

After you've configured your portal for sandboxing and restarted it, deploy the
sandbox app. Then navigate to the Control Panel to see the *SPI Administration*
link displayed in the *Configuration* section.

![Figure 18.11: The Sandboxing App comes with an *SPI Administration* UI that's accessible from the portal's Control Panel.](../../images/sandboxing-spi-admin-available-in-control-panel.png)

Click the *SPI Administration* link to start creating SPIs for running new or
troublesome portlets. 

## Creating an SPI

You can create and administer SPIs from the SPI Administration page accessible
in the Configuration section of the Control Panel.

To add a new SPI, simply click on the *Add SPI* button. 

![Figure 18.12: Click the *Add SPI* button to define a new "sandbox" (SPI).](../../images/sandboxing-add-spi-button.png)

The Add SPI panel divides the SPI's fields into General, SPI Configurations, and
Advanced Configurations sections. 

![Figure 18.13: You can name your SPI, describe it, and configure it from the *Add SPI* panel.](../../images/sandboxing-add-spi-general-details.png)

In the General section, you must provide a unique name for the SPI and describe
the SPI. 

The SPI Configurations contains some of the most important settings for the SPI.
It is broken into 4 sections: SPI Runtime, SPI Applications, Java Runtime,
and Recovery Options. 

![Figure 18.14: From the *SPI Runtime* section of your SPI, you can set its maximum number of working threads based the number of threads available to your portal that you'd like to designate for the SPI. You must also set a unique connector port for the SPI.](../../images/sandboxing-configure-spi-runtime.png)

Let's set the SPI's runtime options first. 

**Maximum Worker Threads:** Enter the maximum number of worker threads that the
SPI can use to process requests. By default, it is set to `100`. You should tune
this value, however, according to the number of threads allocated in the
application server hosting your portal. This parameter functions similarly to
the settings in most JEE application servers. 

**Connector Port:** Enter the port number on which the SPI listens for requests
from Liferay Portal. Each SPI runs on an embedded Apache Tomcat server instance.
Each of the portal's SPIs must use a unique port and you must ensure that no
other processes are using that port. 

---

 ![Note](../../images/tip.png) **Note**: The SPIs and MPI serialize parameters
 and return values passed between them. The sandboxing feature uses an IPC
 framework called
 [Intraband](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#Intraband),
 to support communication between the MPI and SPIs. 

---

Next, we'll pull those troublesome apps into the SPI. 

The SPI Applications panel provides a way to select applications to be hosted
in the SPI. All requests that call these applications are processed by the
SPI. 

![Figure 18.15: All the portlets and web plugins that you've installed on your portal are available for moving into a SPI.](../../images/sandboxing-configure-spi-apps.png)

The panel lists non-core portlets and web plugins that have been installed on
the portal. Since theme, layout template, hook, and Liferay EXT plugins are not
supported in the sandbox, they're excluded from this list. In addition, the
following applications are explicitly excluded: 

- `marketplace-portlet`
- `resource-importer-web`
- `spi-admin-portlet`
- `spi-provider-web`
- `wurfl-commercial-web`
- `wurfl-web`

Next, we'll set the Java runtime resources for the SPI's JVM. 

The **JVM Arguments** panel allows you to specify arguments to be passed to the
Java Virtual Machine (JVM) running the SPI. These include memory settings,
debugger options, etc. If you do not specify any values, the system
automatically uses these values:

    -Xmx512m -XX:PermSize=200m

Consider the JVM's performance tuning recommendations when setting these
arguments. You can also consult the [*Liferay Deployment Checklist*](https://www.liferay.com/marketplace/isvs?p_p_auth=VgLAX0A7&_101_assetEntryId=35608452&p_p_lifecycle=0&p_p_id=101&p_p_state=maximized&_101_struts_action=%2Fasset_publisher%2Fview_content&redirect=https%3A%2F%2Fwww.liferay.com%2Fdocumentation%2Fliferay-portal%2F6.2%2Fuser-guide%3Fp_p_id%3D3%26p_p_lifecycle%3D0%26p_p_state%3Dmaximized%26p_p_mode%3Dview%26_3_entryClassName%3Dcom.liferay.portlet.documentlibrary.model.DLFileEntry%26_3_modifiedselection%3D0%26_3_documentsSearchContainerPrimaryKeys%3D19_PORTLET_35946202%252C19_PORTLET_35722876%252C19_PORTLET_35809176%252C19_PORTLET_35954267%252C19_PORTLET_35824382%252C19_PORTLET_35857084%252C19_PORTLET_35782527%252C19_PORTLET_35810672%252C19_PORTLET_35958625%252C19_PORTLET_35746448%252C19_PORTLET_35972499%252C19_PORTLET_35799656%252C19_PORTLET_35845480%252C19_PORTLET_35773034%252C19_PORTLET_35880698%252C19_PORTLET_35987674%252C19_PORTLET_35866420%252C19_PORTLET_35916979%252C19_PORTLET_35915417%252C19_PORTLET_35815297%26_3_keywords%3DLiferay%2BDeployment%2BChecklist%26_3_format%3D%26_3_modifiedfrom%3D%26_3_formDate%3D1395500651344%26_3_modified%3D%26_3_assetCategoryTitles%3D%26_3_groupId%3D14%26_3_modifiedto%3D%26_3_cur%3D1%26_3_struts_action%3D%252Fsearch%252Fsearch%26_3_assetTagNames%3D&p_p_mode=view&_101_type=document)
for guidance on setting the JVM arguments. 

In development, you may also choose to add debug settings to the JVM arguments. 

![Figure 18.16: You can specify Java runtime options optimal for your SPI's apps.](../../images/sandboxing-configure-spi-java-runtime.png)

In the event that the SPI terminates unexpectedly, you may want to consider
automatically restarting it and having the Sandboxing App send notifications to
you and other stakeholders. 

The Sandboxing App lets you configure email notifications for when an SPI crashes
and lets you configure for the app to automatically restart the SPI. 

![Figure 18.17: You can have the SPI notify you and others if the SPI's apps crash it. And you can configure the maximum number of times to automatically revive the SPI, before requiring manual intervention.](../../images/sandboxing-configure-spi-recovery.png)

**Use Default Notification Options:** Select this checkbox to use the globally
defined notification options. Otherwise the notification options specified in
this panel are used. 

**Notification Recipients:** Enter a set of comma-delimited email addresses of
people to be notified in the event that the SPI fails. This option is disabled
if the *Use Default Notification Options* checkbox is selected. 

**Use Default Restart Options:** Select this checkbox to use the globally
defined restart options. Otherwise the restart options specified in this panel
are used. 

**Maximum Restart Attempts:** Enter the maximum number of times to attempt
restarting the SPI in the event that the SPI fails. Once Liferay reaches the
maximum number of restart attempts for the SPI, it refrains from restarting the
SPI. At that point, manual operator intervention is required to restart it.
This option is disabled if the *Use Default Restart Options* checkbox is
selected. 

### Advanced Configurations

The Advanced Configurations section contains a series of optional parameters
that should rarely be modified. 

**Java Executable:** Enter the path to your JVM, specifically the path to your
Java executable file (i.e., the `java` or `java.exe` file). You should generally
not modify this value, assuming your Java executable is in your system's `PATH`. 

**SPI Ping Interval:** Enter the number of milliseconds to wait between pings
that the SPI sends to the portal to ensure the portal is alive. Pinging the
portal prevents SPIs from becoming "zombie" processes, in the event that the
portal terminates unexpectedly. 

**SPI Register Timeout:** Enter the number of milliseconds allotted for the SPI
to start. The default value is `300000` milliseconds (`300` seconds). This
should be ample time for a SPI to start. However, if you have a large number
of applications in the sandbox, or the server has an insufficient CPU or
insufficient memory resources, you may need to increase the amount of time. 

**SPI Shutdown Timeout:** Enter the maximum amount of time (in milliseconds)
that the SPI should need to gracefully shutdown. As with the SPI Register
Timeout, this value may need to be increased on slower or overloaded machines. 

You may also choose to allocate certain embedded Liferay functions (e.g., blogs,
bookmarks, etc.) to an SPI. This is generally not recommended, but is made
possible by the Sandboxing App. The SPI Core Applications panel lets you drag
apps onto the SPI in the same way you can with the SPI Applications panel. 

Now that you know how to add a SPI and configure it properly, let's learn how to
operate the SPI. 

## Starting, Stopping, and Modifying an SPI

The Portal automatically starts SPIs on startup. However, when you first add a
new SPI, you must start it manually. 

![Figure 18.18: When you first create a SPI, you'll need to start it manually. You can edit and delete SPIs that are not running.](../../images/sandboxing-start-stop-spi.png)

Once successfully started, you can stop or restart a SPI. You can't, however,
delete an SPI that is running. You must first stop the SPI. 

You can edit an SPI's configuration too. Configuration changes made to a running
SPI take effect after it's restarted. 

![Figure 18.19: SPI configuration modifications only take affect after the SPI has been restarted.](../../images/sandboxing-modify-spi.png)

As you can see, operating SPIs is straightforward and easy to do. 

If you have multiple SPIs, you may want to use global settings to configure the
default options for them. Let's consider how to configure global settings for
your SPIs next. 

## Configure Global Settings

The SPI Administration console allows you to configure a series of global
settings. You can access them by clicking on the configuration gear icon as
shown in the figure below. 

![Figure 18.20: Click on the global settings gear icon, in the upper right corner of the SPI Administration console, to set default configuration options for all of the portal's SPIs.](../../images/sandboxing-global-settings.png)

Once you've opened the configuration panel, the SPI Administration console
enables you to configure global notification and set restart options for your
SPIs. 

Note that option values explicitly configured in an SPI take precedence over
the global settings with respect to that SPI.

![Figure 18.21: You can set default notification and restart options for all of the portal's SPIs.](../../images/sandboxing-global-recovery-options.png)

Let's look at the global notification options first. 

The notification options allow you to configure both the notfication email
content and specify the recipients of the notification email. These values are
used by all defined SPIs. 

![Figure 18.22: Via the SPI Administration's global configuration panel, you can set specific email notification options, including the sender's address, the sender's name, default recipients, a default email subject template, and a default email body template.](../../images/sandboxing-global-notification-options.png)

**Notification Email From Address:** Enter a default origin email address to use
for notification emails sent from the SPIs. 

**Notification Email From Name:** Enter a default name to use for the sender of
the notification emails. 

**Notification Recipients:** Enter a default comma-delimited list of email
addresses to receive the notification emails. 

**Notification Email Subject:** Enter a subject template for the notification
emails. 

**Notification Email Body:** Enter a body template for the notification emails.

That's simple enough. Let's take a look at the restart options too. 

The restart options section allows you to configure how many times each SPI
is restarted in the event that it terminates unexpectedly. In the example
below, all SPIs are restarted 3 times before requiring administrator
intervention to restart them. 

![Figure 18.23: You can set default restart options for your SPIs from the SPI Administration's global configuration panel.](../../images/sandboxing-global-restart-options.png)

Let's recap what Liferay's Sandboxing App does for you. It lets you isolate
portlets and web plugins that are known troublemakers or that you are simply
just unsure about. You put them in their own sandbox JVM (or SPI), so they can
still be used in your portal but are kept out of your portal's JVM. As an
administrator, you can group plugins into SPIs and configure each SPI's runtime,
notification, and recovery options. In addition, you can configure global
default settings for your portal's SPIs. With the Sandboxing App, you can ensure
your portal's resiliency while leveraging all the portlets (even leaky ones)
that your users require. 

Liferay Portal can serve portlets that are installed on the system, or it can
serve portlets installed on another portal server. This is called Web Services
for Remote Portlets. Have you ever wondered how to use WSRP in Liferay? We'll
cover this next!
