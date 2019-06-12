# Creating SLA Calendars

By default, an internal calendar assumes the 
[SLA deadline clock](/docs/7-2/user/-/knowledge_base/u/workflow-metrics-the-service-level-agreement-sla)
should continue counting all the time: in other words, 24 hours per day, seven
days per week. If you need a different calendar format, provide your own
implementation of the `WorkflowMetricsSLACalendar` interface. New
implementations of this service are picked up automatically by the Workflow
Metrics application, so it becomes available as soon as the module holding the
service implementation is deployed. The interface has three methods to
implement:

```java
public interface WorkflowMetricsSLACalendar {

	public Duration getDuration(
		LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime);

	public LocalDateTime getOverdueLocalDateTime(
		LocalDateTime nowLocalDateTime, Duration remainingDuration);

	public String getTitle(Locale locale);

}
```

If you define a new calendar, a new option becomes available in the Add SLA
form, allowing you to choose from the default 24/7 calendar or any custom ones
you've provided. For example, make the timer run for 8 hours per day, from 9-17
by a 24-hour clock, for 5 days per week. Even stop the calendar from counting
during lunch hours if you need to.

![Figure x: Write a Custom SLA Calendar if the default, 24/7 calendar isn't sufficient.](../../images/workflow-custom-sla-calendar.png)

## Dependencies

Along with some artifacts you're probably used to depending on (like
`com.liferay.portal.kernel`, you'll need the
`com.liferay.portal.workflow.metrics.api-[version].jar` artifact. For @product@
version 7.2.10-GA1, here's an example Gradle build dependency declaration:

```groovy
compileOnly group: "com.liferay", name: "com.liferay.portal.workflow.metrics.api", version: "1.0.2"
compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "4.4.0"
compileOnly group: "javax.servlet", name: "javax.servlet-api", version: "3.0.1"
compileOnly group: "org.osgi", name: "org.osgi.service.component.annotations", version: "1.3.0"
```

<!--NOTE, this assumes we're publishing the workflow metrics api publicly, which
I do not have confirmation of yet. -->

## Implementation Steps

Implement a
`com.liferay.portal.workflow.metrics.sla.calendar.WorkflowMetricsSLACalendar` to
define your own SLA calendar logic. When you're finished, use the created
calendar when creating the 
[SLA definition](/docs/7-2/user/-/knowledge_base/u/workflow-metrics-the-service-level-agreement-sla).

Let's walk through the default calendar:

```java
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.workflow.metrics.sla.calendar.WorkflowMetricsSLACalendar;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;


@Component(propetty = "sla.calendar.key=default")
public class DefaultWorkflowMetricsSLACalendar
    implements WorkflowMetricsSLACalendar {
```

The component property `sla.calendar.key` is required to identify this calendar.

```java
    @Override
    public Duration getDuration(
        LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime) {

        return Duration.between(startLocalDateTime, endLocalDateTime);
    }
```

Use `getDuration` to return the time period (as a `Duration`) when the elapsed
SLA time should be computed. The start and end dates are set by administrators
during the creation of the SLA. For example, given a start date
_2019-05-13T16:00:00_ and end date _2019-05-13T18:00:00_, The 24/7 calendar
returns 2 elapsed hours, while the 9-17 weekdays calendar returns 1 hour as the
elapsed time.

```java
    @Override
    public LocalDateTime getOverdueLocalDateTime(
        LocalDateTime nowLocalDateTime, Duration remainingDuration) {

        return nowLocalDateTime.plus(remainingDuration);
    }
```

`getOverdueLocalDateTime` must return the date (as a `LocalDateTime`) when this
SLA will be considered overdue given the parameter values. For example; given
that `nowLocalDateTime`=_2019-05-13T17:00:00_ and `remainingDuration`=_24H_, The
24/7 calendar return a `localDateTime` of _2019-05-14T17:00:00_ as the overdue
date. Given the same parameters, the 9-17 weekdays calendar returns
_2019-05-17T09:00:00_. 

```java
    @Override
    public String getTitle(Locale locale) {
        return _language.get(locale, "default");
    }

    @Reference
    private Language _language;
```

}

Use `getTitle` to provide the title for the given locale. Make sure you 
[properly localize](/docs/7-2/frameworks/-/knowledge_base/f/localizing-your-application)
this extension by providing a `Language.properties` file, and any
`Language_xx.properties` files for translation of the value. At runtime, the
User's locale will be used to return the correct translation.

If the 24/7 default calendar works for you, use it. Otherwise create your own
`WorkflowMetricsSLACalendar`s.

