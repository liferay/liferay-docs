 # Configuring Audits [](id=configuring-audits)

Audits are enabled by default. The Audit app reports audit events, but you can 
also report them in @product@'s logs or console, enable them for scheduled jobs, 
or disable them entirely. 

There are two main ways to configure @product@:

1.  Edit a configuration via the Control Panel. This saves the configuration to 
    the database. 

2.  Edit a configuration via an OSGi configuration file (`.config` file) in your
    `[LIFERAY_HOME]/osgi/configs` folder.

These methods apply to each of the audit configuration options explained below.

## Reporting Audit Events in Liferay's Logs and Console [](id=reporting-audit-events-in-liferays-logs-and-console)

Follow these steps to use the Control Panel to configure the reporting of log 
events in @product@'s log and console: 

1.  Go to *Control Panel* &rarr; *Configuration* &rarr; *System Settings*, and 
    select *Audit* from the *Security* section. 

2.  In the *SYSTEM SCOPE* column on the left, select 
    *Logging Message Audit Message Processor*. 

3.  Select the *Enabled* checkbox to report audit events in @product@'s log. 

4.  Select the *Output to Console* checkbox to report audit events in the 
    console. 

5.  In the *Log Message Format* selector menu, select the format for the audit 
    events (CSV or JSON). 

6.  Click *Save* when you're finished. 

Alternatively, you can make the same configuration via an OSGi configuration 
file: 

1.  Create a file called `com.liferay.portal.security.audit.router.configuration.LoggingAuditMessageProcessorConfiguration.config`. 

2.  Add these properties to the file: 

        enabled="true"
        logMessageFormat="CSV"
        #logMessageFormat="JSON"
        outputToConsole="true"

    Note that these are the same options set in the Control Panel. Edit them as 
    you see fit. 

3.  Deploy the file to the `[LIFERAY_HOME]/osgi/modules` folder. Note that the 
    [Liferay Home folder](/discover/deployment/-/knowledge_base/7-1/installing-liferay#liferay-home) 
    is typically the application server's parent folder. 

Regardless of your configuration approach, you must also extend @product@'s 
`log4j-ext.xml` file to configure Log4j (@product@'s logging implementation) to
log messages produced by the appropriate class to the appropriate file. To do
so, create a `portal-log4j-ext.xml` file in 
`[LIFERAY_HOME]/tomcat-[version]/webapps/ROOT/WEB-INF/classes/META-INF` 
with this configuration: 

    <?xml version="1.0"?>
    <!DOCTYPE log4j:configuration SYSTEM "log4j.dtd">

    <log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/">

        <!-- additional audit logging -->

        <appender name="auditFile" class="org.apache.log4j.rolling.RollingFileAppender">
            <rollingPolicy class="org.apache.log4j.rolling.TimeBasedRollingPolicy">
                <param name="FileNamePattern" value="@liferay.home@/logs/audit.%d{yyyy-MM-dd}.log" />
            </rollingPolicy>
            <layout class="org.apache.log4j.EnhancedPatternLayout">
                <param name="ConversionPattern" value="%d{ABSOLUTE} %-5p [%t][%c{1}:%L] %m%n" />
            </layout>
        </appender>

        <category name="com.liferay.portal.security.audit.router.internal.LoggingAuditMessageProcessor">
            <priority value="INFO" />
            <appender-ref ref="auditFile"/>
        </category>
    </log4j:configuration>

This configures Log4j to record INFO level messages from the class 
`com.liferay.portal.security.audit.router.internal.LoggingAuditMessageProcessor`
to a file called `audit.yyyy-MM-dd.log` in the `[LIFERAY_HOME]/logs` folder. 
Adjust the audit file properties or log level to your liking. 

## Configuring Audit Events for Scheduled Liferay Jobs [](id=configuring-audit-events-for-scheduled-liferay-jobs)

By default, scheduled jobs don't trigger audit events. Follow these steps to 
enable them via the Control Panel: 

1.  Go to *Control Panel* &rarr; *Configuration* &rarr; *System Settings*, and 
    select *Infrastructure* from the *Platform* section. 

2.  In the *SYSTEM SCOPE* column on the left, select *Scheduler Engine Helper*. 

3.  Select the checkbox for *Audit scheduler job enabled* and click
    *Save*. 

Alternatively, you can make the same configuration via an OSGi configuration 
file: 

1.  Create a file called 
`com.liferay.portal.scheduler.configuration.SchedulerEngineHelperConfiguration.config`. 

2.  Add this property to the file: 

        auditSchedulerJobEnabled=true

3.  Deploy the file to the `[LIFERAY_HOME]/osgi/modules` folder. Note that the 
    [Liferay Home folder](/discover/deployment/-/knowledge_base/7-1/installing-liferay#liferay-home) 
    is typically the application server's parent folder. 

Auditing scheduled jobs is a smart choice if there's a chance someone with a 
dubious competence level would try to schedule jobs, as you'll find out below in
the conclusion of our story. 

## Enabling or Disabling Audit Events Entirely [](id=enabling-or-disabling-audit-events-entirely)

Audit events are enabled by default. Follow these steps to disable them via the 
Control Panel: 

1.  Go to *Control Panel* &rarr; *Configuration* &rarr; *System Settings* and 
    then click *Audit* in the *Security* section. 

2.  Uncheck the *Enabled* box. Note that when auditing is enabled, you can 
    adjust the audit message max queue size from its default value. 

Alternatively, you can enable or disable auditing via an OSGi configuration 
file: 

1.  Create a file called 
    `com.liferay.portal.scheduler.configuration.SchedulerEngineHelperConfiguration.config`. 

2.  Add these properties to the file. You can adjust their values as desired: 

        enabled="true"
        auditMessageMaxQueueSize="200"

3.  Deploy the file to the `[LIFERAY_HOME]/osgi/modules` folder. Note that the 
    [Liferay Home folder](/discover/deployment/-/knowledge_base/7-1/installing-liferay#liferay-home) 
    is typically the application server's parent folder. 

## The End of the Story [](id=the-end-of-the-story)

"Okay," says Harry, "let's fire up the audit system and see if we can figure out 
what happened." 

You and Dick stand behind Harry's chair and watch as he enters a query into a
form on the Audit app. After clicking *search*, the screen fills up with audit 
events. 

"Wow, that's a lot of unassign events." Harry says. "And look who the culprit
is," he adds sarcastically. 

"Who's Melvin Dooitrong?" Dick asks. 

"That's my new intern," Harry says. "I'm gonna kill him." Harry pushes out his
chair and walks down the row of cubes to the end, where a kid no more than 20
years old with disheveled hair sits, earbuds in his ears. 

"Hey Melvin," Harry says as Melvin turns around to face him. "Didn't I ask you
to move that set of users from Site membership to Organization membership?" 

"Yeah," Melvin says, "I did that already." 

"How'd you do it?"

"It was going to take a while to do it manually, so I wrote a script and
executed it in the scripting host," Melvin replies, matter-of-factly. 

"You did, did you? Well, guess what? Your script removed *everybody* from *all*
Sites."

"*What?*" 

"Yeah, and now you're going to start adding them back, one by one, manually,
starting with Mr. Jones...." 

Tom and Dick back away slowly from Melvin's cube as Harry and Melvin continue to
have their---let's call it a discussion. One thing is clear: they're having a
better day than Melvin is. 
