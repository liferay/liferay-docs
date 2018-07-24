 # Configuring Audits [](id=configuring-audits)

Audits are enabled by default. As described above, the Control Panel app reports
audit events, but you can also report them in Liferay's logs or console, enable
them for scheduled jobs, or disable them entirely. 

There are two main ways to configure @product@:

1.  Edit a configuration via Liferay's Control Panel and the configuration is
    saved to Liferay's database.

2.  Edit a configuration via an OSGi configuration file (`.config` file) in your
    `[LIFERAY_HOME]/osgi/configs` folder.

These methods apply to each of the audit configuration options explained below.

## Reporting Audit Events in Liferay's Logs and Console [](id=reporting-audit-events-in-liferays-logs-and-console)

**In the Control Panel:** Go to *Control Panel* &rarr; *Configuration* &rarr;
*System Settings* and find the *Security* section. Click *Audit* &rarr; *Logging
Message Audit Message Processor*.

Check *Enabled* to report audit events in Liferay's log. Check *Output to
Console* if you also want them in the console. Audit events are available in
either CSV or JSON formats. Select the one you prefer and click *Save*.

To report audit events in Liferay's logs and console via an OSGi configuration
file, create a file called
`com.liferay.portal.security.audit.router.configuration.LoggingAuditMessageProcessorConfiguration.config`
in `[LIFERAY_HOME]/osgi/modules` folder containing these properties:

	enabled="true"
	logMessageFormat="CSV"
	#logMessageFormat="JSON"
	outputToConsole="true"

As you can see, these are the same options from the Control Panel. Edit them
you see fit.

Regardless of configuration approach, you must also extend Liferay's
`log4j-ext.xml` file to configure Log4J (@product@'s logging implementation) to
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

This configures Log4J to record INFO level messages from the
`com.liferay.portal.security.audit.router.internal.LoggingAuditMessageProcessor`
class to a file called `audit.yyyy-MM-dd.log` in the `[LIFERAY_HOME]/logs`
folder. Adjust the audit file properties or log level to your liking.

## Configuring Audit Events for Scheduled Liferay Jobs [](id=configuring-audit-events-for-scheduled-liferay-jobs)

By default, scheduled jobs don't trigger audit events. To enable them via the
Control Panel, go to *Control Panel* &rarr; *Configuration* &rarr; *System
Settings*. Find the *Platform* section and click *Infrastructure* &rarr;
*Scheduler Engine Helper*. Check the *Audit scheduler job enabled* box and click
*Save*.

To enable audit events for scheduled jobs via an OSGi configuration file,
create a
`com.liferay.portal.scheduler.configuration.SchedulerEngineHelperConfiguration.config`
file in your `[LIFERAY_HOME]/osgi/modules` folder with this configuration:

	auditSchedulerJobEnabled=true

Auditing scheduled jobs is a smart choice if there's a chance someone with a
dubious competence level would try to schedule jobs, as you'll find out below in
the conclusion of our story. 

## Enabling or Disabling Audit Events Entirely [](id=enabling-or-disabling-audit-events-entirely)

Audit events are enabled by default. To disable them via the Control Panel, go
to *Control Panel* &rarr; *Configuration* &rarr; *System Settings* &rarr; and
click *Audit* in the *Security* section. Uncheck the *Enabled* box. When
auditing is enabled, you can adjust the audit message max queue size from its
default value.

To enable or disable auditing entirely from an OSGi configuration file, create a
file called
`com.liferay.portal.scheduler.configuration.SchedulerEngineHelperConfiguration.config`
in your `[LIFERAY_HOME]/osgi/modules` folder with this configuration:

	enabled="true"
	auditMessageMaxQueueSize="200"

These are the default values which you can adjust as desired.

## The End of the Story [](id=the-end-of-the-story)

"Okay," says Harry, "let's fire up Liferay's audit system and see if we can
figure out what happened." 

You and Dick stand behind Harry's chair and watch as he enters a query into a
form on the audit portlet. After clicking *search*, the screen fills up with
audit events. 

"Wow, that's a lot of unassign events." Harry says. "And look who the culprit
is," he adds sarcastically. 

"Who's Melvin Dooitrong?" Dick asks. 

"That's my new intern," Harry says. "I'm gonna kill him." Harry pushes out his
chair and walks down the row of cubes to the end, where a kid no more than 20
years old with disheveled hair sits, earbuds in his ears. 

"Hey Melvin," Harry says as Melvin turns around to face him. "Didn't I ask you
to move that set of users from site membership to organization membership?" 

"Yeah," Melvin says, "I did that already." 

"How'd you do it?"

"It was going to take a while to do it manually, so I wrote a script and
executed it in the scripting host," Melvin replies, matter-of-factly. 

"You did, did you? Well, guess what? Your script removed *everybody* from *all*
sites."

"*What?*" 

"Yeah, and now you're going to start adding them back, one by one, manually,
starting with Mr. Jones...." 

Tom and Dick back away slowly from Melvin's cube as Harry and Melvin continue to
have their--let's call it a discussion. One thing is clear: they're having a
better day than Melvin is.
