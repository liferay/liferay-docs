# Troubleshooting Your LCS Connection [](id=troubleshooting-your-lcs-connection)

If you use LCS to activate @product@, your server must maintain its connection 
to LCS at all times. If this connection is interrupted, your server enters a 
grace period to allow for reconnection. Lengthy interruptions, however, can 
affect your server's uptime. 

+$$$

**Note:** You must use LCS for activation of containerized instances, cloud 
deployments, and instances that use Liferay Analytics Cloud and/or elastic 
subscriptions. Otherwise, you don't have to use LCS for activation. You can 
instead request an XML activation key from Liferay Support. 

$$$

The following sections in this document provide some background information and 
help you troubleshoot problems with your server's LCS connection: 

-   [**LCS Grace Periods:**](#lcs-grace-periods) 
    Describes how grace periods work in LCS. You should read this section before 
    attempting any troubleshooting steps. 
-   [**Troubleshooting:**](#troubleshooting) 
    Presents troubleshooting steps for specific problems. 
-   [**Increasing Log Levels:**](#increasing-log-levels) 
    If you contact Liferay Support, you'll be asked to increase your server's 
    log levels and then provide your log files. This section shows you how to do 
    this. 

+$$$

**Note:** The odds of LCS being unavailable are low. LCS is deployed on a global 
cloud infrastructure set up for automatic failure recovery. Notifications also 
let the LCS team react quickly to any downtime. During LCS updates and new 
version releases, however, LCS is unavailable for a few minutes while changes
are applied. 

$$$

## LCS Grace Periods [](id=lcs-grace-periods)

There are 2 grace period types in LCS: 

1.  **Connection Grace Period:** Occurs when your activated LCS connection is
    interrupted. This gives you time to re-establish the connection. 

2.  **Subscription Grace Period:** Occurs when your subscription is about to
    expire. This gives you time to renew the subscription. 

+$$$

**Note:** These grace periods only apply to servers previously connected and 
activated in LCS. If the subscription check or connection fails when a server 
attempts to connect to LCS for the first time, that server doesn't enter a grace 
period. It's therefore important to verify that an active subscription is 
available before connecting a new server to LCS. To do this, check the 
[Subscriptions tab](https://customer.liferay.com/documentation/7.1/deploy/-/official_documentation/deployment/managing-liferay-dxp-subscriptions) 
in LCS. 

$$$

### Connection Grace Period [](id=connection-grace-period)

If your server's LCS connection is interrupted, the server continues to run and 
enters a grace period that lasts for up to 30 days to allow for reconnection. 
During this grace period, @product@ displays a warning message to
administrators. Upon seeing this message, administrators should contact Liferay
Support and follow the troubleshooting steps below. LCS automatically restores 
your server's activation upon reconnection (you shouldn't need to restart the 
server). If for some reason the connection can't be restored, Liferay Support 
will provide an alternative way to activate your server. 

![Figure 1: A warning message is displayed to administrators if the server can't connect to LCS to validate the subscription.](../../images-dxp/lcs-grace-period.png)

While disconnected from LCS, the LCS client app continually attempts to 
reconnect. If reconnection continues to fail, ensure that your server can access 
`lcs.liferay.com` and `lcs-gateway.liferay.com`. If the LCS client app stops 
attempting to reconnect, there will be no activity in the logs. In this case, 
you can force reconnection by redeploying the app. Follow these steps to do so: 

1.  In your server's 
    [Liferay Home](https://customer.liferay.com/documentation/7.1/deploy/-/official_documentation/deployment/installing-liferay#liferay-home) 
    folder (usually the parent folder of the application server's folder), 
    remove this file: 

        osgi/marketplace/Liferay Connected Services Client.lpkg

2.  Place `Liferay Connected Services Client.lpkg` in `[Liferay Home]/deploy`. 
    If you 
    [connect to LCS through a proxy](https://customer.liferay.com/documentation/7.1/deploy/-/official_documentation/deployment/lcs-preconfiguration#preconfiguring-lcs-to-connect-through-a-proxy), 
    and configured this inside the LCS client app, make sure the app you deploy 
    is also configured to do so. 

You should also ensure that you've enabled email notifications in LCS for server 
disconnection events. To do this, you must create a notification rule that sends 
an email whenever the server shuts down unexpectedly. The documentation on 
[managing your LCS account](https://customer.liferay.com/documentation/7.1/deploy/-/official_documentation/deployment/managing-your-lcs-account) 
explains how to do this. 

### Subscription Grace Period [](id=subscription-grace-period)

At least 90 days before the subscription expires, Liferay will reach out to 
begin the renewal process. 30 days before expiration, Liferay Support sends 
warning messages through 
[LESA](https://web.liferay.com/group/customer/support/-/support/ticket), 
[the LCS site](https://lcs.liferay.com), 
and 
[the Customer Portal](https://www.liferay.com/group/customer). 
After the expiration date, your servers may be placed in an additional grace 
period, which is communicated through the same support channels. If the 
renewal isn't completed during this grace period, then the subscription becomes 
inactive and the @product@ instance enters the 30-day grace period. As soon as 
the renewal is processed, the instance activates and any error or warning 
messages disappear within 24 hours. Note that by using XML activation keys 
(provided by Liferay Support upon request), you can continue to use your 
@product@ instances even after a subscription has expired. 

![Figure 2: LCS sends you a notification prior to the expiration of your subscription.](../../images-dxp/lcs-support-expiration.png)

## Troubleshooting [](id=troubleshooting)

If you encounter issues with LCS, the Liferay Support team is here to help. If 
you need support, open a 
[LESA](https://web.liferay.com/group/customer/support/-/support/ticket) 
ticket. You can begin troubleshooting the following scenarios, which the Liferay 
Support team can also assist you with. 

+$$$

**Note:** Before troubleshooting specific issues or contacting Liferay Support, 
make sure that you've followed the LCS 
[preconfiguration](https://customer.liferay.com/documentation/7.1/deploy/-/official_documentation/deployment/lcs-preconfiguration) 
and 
[activation](https://customer.liferay.com/documentation/7.1/deploy/-/official_documentation/deployment/activating-your-liferay-dxp-server-with-lcs) 
steps correctly. 

$$$

### Server Can't Reach LCS [](id=server-cant-reach-lcs)

If your server can't reach LCS, verify that you can access the public sites
required by LCS: 

-   [`lcs.liferay.com`](https://lcs.liferay.com/) 
    should be viewable in a browser. 
-   `lcs-gateway.liferay.com` should respond on port 443: 

        curl -vk -I "https://lcs-gateway.liferay.com"
        telnet lcs-gateway.liferay.com 443

### Subscription Issues [](id=subscription-issues)

For issues related to your subscription, first review the documentation on 
[managing your subscription](https://customer.liferay.com/documentation/7.1/deploy/-/official_documentation/deployment/managing-liferay-dxp-subscriptions). 
Subscription errors usually involve one of these problems:

-   Your server can reach LCS, but can't locate a subscription.
-   Your server can reach LCS and locate a subscription, but activating your 
    server would exceed the subscription's allowed number of servers or cores. 

In either case, you must verify that a subscription is available and that you're 
not exceeding its allowed number of servers or cores. You can find this 
information on the LCS site's Subscriptions page, as described in 
[the documentation on managing subscriptions](https://customer.liferay.com/documentation/7.1/deploy/-/official_documentation/deployment/managing-liferay-dxp-subscriptions). 
If the environment in which you're trying to activate a server isn't assigned 
the subscription you want to use, then you must create a new environment and 
assign it the correct subscription. Once assigned, you can't change an 
environment's subscription. Follow 
[the initial activation steps](https://customer.liferay.com/documentation/7.1/deploy/-/official_documentation/deployment/activating-your-liferay-dxp-server-with-lcs) 
for instructions on creating a new environment and activating a new server. 

+$$$

**Note:** When shutting down servers, you must ensure that the LCS site receives 
the server shutdown commands. Otherwise, LCS may not release that server's 
activation key for reuse and attempts to activate additional servers may exceed 
the subscription's allowed number of servers. There's a higher 
likelihood of this happening in rolling deployments and/or when using 
containers. For more information, see the 
[KB article on properly unregistering subscriptions](https://customer.liferay.com/documentation/knowledge-base/-/kb/1464875). 

$$$

### Invalid Token [](id=invalid-token)

If the token is invalid, first review the documentation on 
[environment tokens](https://customer.liferay.com/documentation/7.1/deploy/-/official_documentation/deployment/understanding-environment-tokens). 
The following table lists causes and solutions for invalid tokens. 

| &nbsp;Cause | &nbsp;Solution |
| ----------- | -------------- |
| The LCS user who generated the token no longer has permissions. This happens when the user leaves the LCS project or becomes an LCS Environment Manager or LCS Environment Viewer in a different environment. | Regenerate the token. |
| The token's file name is changed after download. | Download the token again from LCS. |
| The token is regenerated. | Use the regenerated token. |

## Increasing Log Levels [](id=increasing-log-levels)

If you contact Liferay Support, you're asked to increase your server's log 
levels and then provide your log files. You can find these log files in 
`[Liferay Home]/logs` 
([Liferay Home](https://customer.liferay.com/documentation/7.1/deploy/-/official_documentation/deployment/installing-liferay#liferay-home) 
is usually the parent folder of the application server's folder). There are 2 
types of log files in this folder: 

1.  **Liferay log files:** The files `liferay.[date].log` and 
    `liferay.[date].xml` are the logs for your @product@ installation. Note that 
    LOG and XML files for the same date contain the same information--the only 
    difference is the file format. 

2.  **LCS log files:** The `lcs-portlet-[date].log` files are the LCS client 
    app's logs. Note that if there's only a single LCS log file, it may appear 
    without a date as `lcs-portlet.log`. When you increase the log levels as 
    described in the following sections, the additional log messages are written 
    to these LCS log files. 

There are 2 ways to increase the log levels: 

1.  **In your @product@ instance's Control Panel:** This is a temporary 
    configuration that resets upon shutting down the server. Note that if the 
    server isn't activated, you can't access the Control Panel. In that case,
    Liferay Support can provide a temporary activation key. 

2.  **In a Log4j configuration:** This is a permanent configuration that 
    persists through server shutdown and restart. 

The following sections cover both options. 

### Control Panel [](id=control-panel)

Follow these steps to increase the log levels via the Control Panel: 

1.  Navigate to *Control Panel* &rarr; *Configuration* &rarr; *Server 
    Administration*. 

2.  Click the *Log Levels* tab. 

3.  Search for "lcs". 

4.  Change the log level for each matching entry to DEBUG. 

5.  While in the Control Panel, you should also navigate to *Configuration* 
    &rarr; *Liferay Connected Services* and take a screenshot of what you see 
    there. This is useful to Liferay Support. 

### Log4j [](id=log4j)

Follow these steps to increase the log levels via Log4j: 

1.  Download the latest LCS client as instructed in the 
    [LCS preconfiguration article](https://customer.liferay.com/documentation/7.1/deploy/-/official_documentation/deployment/lcs-preconfiguration). 
    The app downloads as `Liferay Connected Services Client.lpkg`. If you don't 
    want to download the latest client, you can use the one already installed in 
    your server: it's in `[Liferay Home]/osgi/marketplace` (just make sure to 
    shut down your server before following the rest of the steps in this 
    section). Recall that the 
    [Liferay Home](https://customer.liferay.com/documentation/7.1/deploy/-/official_documentation/deployment/installing-liferay#liferay-home) 
    folder is usually the parent folder of the application server's folder. 

2.  Expand the LPKG file, then expand the `lcs-portlet-[version].war` file 
    inside it. 

3.  Inside the `WAR` file, replace the contents of 
    `WEB-INF\classes\META-INF\portal-log4j.xml` with the following
    configuration: 

        <?xml version="1.0"?>
        <!DOCTYPE log4j:configuration SYSTEM "log4j.dtd">

        <log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/">
                <appender class="org.apache.log4j.rolling.RollingFileAppender" name="RollingFileAppender">
                        <rollingPolicy class="org.apache.log4j.rolling.TimeBasedRollingPolicy">
                                <param name="ActiveFileName" value="@liferay.home@/logs/lcs-portlet.log" />
                                <param name="FileNamePattern" value="@liferay.home@/logs/lcs-portlet.%d{yyyy-MM-dd}.log.zip" />
                        </rollingPolicy>

                        <layout class="org.apache.log4j.EnhancedPatternLayout">
                                <param name="ConversionPattern" value="%d{yyyy/MM/dd HH\:mm\:ss} %-5p [%t][%c{1}:%L] %m%n" />
                        </layout>
                </appender>

                <category name="com.liferay.lcs.task.scheduler">
                        <priority value="ALL" />
                </category>

                <logger additivity="false" name="com.liferay.lcs">
                        <level value="ALL" />
                        <appender-ref ref="RollingFileAppender" />
                </logger>
        </log4j:configuration>

4.  Save the file and repackage the WAR and LPKG (make sure not to change the 
    names of these files). 

5.  Make sure your server is shut down. 

6.  In your installation's 
    [Liferay Home](https://customer.liferay.com/documentation/7.1/deploy/-/official_documentation/deployment/installing-liferay#liferay-home) 
    folder, delete the existing LCS client app: 

        osgi/marketplace/Liferay Connected Services Client.lpkg

7.  Place the `Liferay Connected Services Client.lpkg` that you repackaged in 
    step 4 in `osgi/marketplace`. 

8.  Start your server. 

If you need assistance with the issues in this guide, or any other issues with 
LCS, contact Liferay Support. 
