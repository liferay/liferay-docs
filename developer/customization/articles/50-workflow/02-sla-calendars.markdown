---
header-id: creating-sla-calendars
---

# Creating SLA Calendars

[TOC levels=1-4]

By default, an internal calendar assumes the
[SLA deadline clock](/docs/7-2/customization/-/knowledge_base/c/creating-sla-calendars)
should continue counting all the time: in other words, 24 hours per day, seven
days per week. If you need a different calendar format, provide your own
implementation of the `WorkflowMetricsSLACalendar` interface. New
implementations of this service are picked up automatically by the Workflow
Metrics application, so they become available as soon as the module holding the
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
you've provided. For example, you can make the timer run for 8 hours per day,
from 9-17 by a 24-hour clock, for 5 days per week. If you need to, you can even
stop the calendar from counting during lunch hours!

![Figure 1: Write a Custom SLA Calendar if the default, 24/7 calendar isn't sufficient.](../../images/workflow-custom-sla-calendar.png)

## Dependencies

Along with some artifacts you're probably used to depending on (like
`com.liferay.portal.kernel`), you'll need the
`com.liferay.portal.workflow.metrics.sla.api-[version].jar` artifact. For @product@
version 7.2.10-GA1, here's an example Gradle build dependency declaration:

```groovy
compileOnly group: "com.liferay", name: "com.liferay.portal.workflow.metrics.sla.api", version: "1.1.0"
compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "4.4.0"
compileOnly group: "javax.servlet", name: "javax.servlet-api", version: "3.0.1"
compileOnly group: "org.osgi", name: "org.osgi.service.component.annotations", version: "1.3.0"
```

## Implementation Steps

Implement a
`com.liferay.portal.workflow.metrics.sla.calendar.WorkflowMetricsSLACalendar` to
define your own SLA calendar logic. When you're finished, use the created
calendar when creating the 
[SLA definition](/docs/7-2/customization/-/knowledge_base/c/creating-sla-calendars).

1.  Declare the component and the class:

    ```java
    import com.liferay.portal.kernel.language.Language;
    import com.liferay.portal.workflow.metrics.sla.calendar.WorkflowMetricsSLACalendar;
    import java.time.Duration;
    import java.time.LocalDateTime;
    import java.util.Locale;

    import org.osgi.service.component.annotations.Component;
    import org.osgi.service.component.annotations.Reference;


    @Component(property = "sla.calendar.key=default")
    public class DefaultWorkflowMetricsSLACalendar
        implements WorkflowMetricsSLACalendar {
    ```

    The component property `sla.calendar.key` is required to identify this calendar.

2.  Override `getDuration` to return the time `Duration` when elapsed SLA time
    should be computed. The start and end dates that this method receives
    represent the time a workflow task has been running. For example, given
    a task that started at _2019-05-13T16:00:00_ and finished at
    _2019-05-13T18:00:00_, then The 24/7 calendar returns 2 elapsed hours, while
    a 9-17 weekdays calendar returns 1 hour as the elapsed time.

    ```java
    @Override
    public Duration getDuration(
        LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime) {

        return Duration.between(startLocalDateTime, endLocalDateTime);
    }
    ```

3.  `getOverdueLocalDateTime` must return the date (as a `LocalDateTime`) when
    this SLA is considered overdue given the parameter values. For example,
    given that `nowLocalDateTime`=_2019-05-13T17:00:00_ and
    `remainingDuration`=_24H_, The 24/7 calendar returns a `localDateTime` of
    _2019-05-14T17:00:00_ as the overdue date. Given the same parameters, the
    9-17 weekdays calendar should return _2019-05-17T09:00:00_. The remaining
    duration of time left in the SLA is available in the method as a `Duration`
    object; your job is to write logic that considers your calendar and create
    a `LocalDateTime` with the proper overdue date/time.

    ```java
    @Override
    public LocalDateTime getOverdueLocalDateTime(
        LocalDateTime nowLocalDateTime, Duration remainingDuration) {

        return nowLocalDateTime.plus(remainingDuration);
    }
    ```

4.  Use `getTitle` to provide the title for the given locale. Make sure you
    [properly localize](/docs/7-2/frameworks/-/knowledge_base/f/localizing-your-application)
    this extension by providing a `Language.properties` file and any
    `Language_xx.properties` files for translation of the value. At runtime, the
    User's locale is used to return the correct translation.

    ```java
    @Override
    public String getTitle(Locale locale) {
        return _language.get(locale, "default");
    }

    @Reference
    private Language _language;
    ```

If the 24/7 default calendar works for you, use it. Otherwise create your own
`WorkflowMetricsSLACalendar`s.
