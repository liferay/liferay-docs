# Creating SLA Calendars

By default, internal calendar assumes the SLA duration should continue counting
all the time: in other words, 24 hours per day, seven days per week. If you
need a different calendar format, provide your own implementation of the
`WorkflowMetricsSLACalendar` interface. New implementations of this service are
picked up automatically by the Workflow Metrics applications, so it becomes
available as soon as the module holding the service implementation is deployed.
The interface has three methods to implement:

`public Duration getDuration(
  LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime);`

`public LocalDateTime getOverdueLocalDateTime(
  LocalDateTime nowLocalDateTime, Duration remainingDuration);`

`public String getTitle(Locale locale);`

See the `DefaultWorkflowMetricsSLACalendar` from the
`portal-workflow-metrics-service` module for example code. If you define a new
calendar, a new option becomes available in the Add SLA form, allowing you to
choose from the default 24/7 calendar or any custom ones you've provided (for
example, make the timer run for 9 hours per day, 5 days per week).

![Figure x: Write a Custom SLA Calendar if the default, 24/7 calendar isn't sufficient.](../../images/workflow-custom-sla-calendar.png)

---

DRAFT FROM TEAM:


Background For now, the workflow SLA calculation uses a 24/7 Calendar by
default.  This calendar is not enough to cover many real SLA cases. For
example, imagine the workflow that represents a bug tracking (such as Jira) and
needs to track the time spent in the review phase that should be 2 business
days maximum, considering the interval of [9-12, 13-18 ] hours. Therefore, to
track this, you need a Calendar that calculates only the elapsed time that
occurs at the specified time constraint.  Features This feature add ability to
add a new calendar to be used with SLAs. The developer can build a specific
calendar for handle several specific use cases since holidays until complex
work time restrictions .  Ex: 9-17 weekdays, 8-17monday to Saturday and etc.

Steps

Properly  implement the
com.liferay.portal.workflow.metrics.sla.calendar.WorkflowMetricsSLACalendar to
attend the given time restriction Use the created calendar when filling the SLA
definition form Code For this example we’ll use the 24/7 calendar.

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

    @Override
    public Duration getDuration(
        LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime) {

        return Duration.between(startLocalDateTime, endLocalDateTime);
    }

    @Override
    public LocalDateTime getOverdueLocalDateTime(
        LocalDateTime nowLocalDateTime, Duration remainingDuration) {

        return nowLocalDateTime.plus(remainingDuration);
    }


    @Override
    public String getTitle(Locale locale) {
        return _language.get(locale, "default-calendar-title");
    }

    @Reference
    private Language _language;

}
```

```
@Component(property = "sla.calendar.key=default") 
```

- This is a mandatory property that will be used to identify this calendar.

```
Duration getDuration(LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime) 
```

- this method must return the time between the given start and end dates that
    should be computed as elapsed time. For example: given
    {start=2019-05-13T17:00:00 and end=2019-05-13T19:00:00} The 24/7 calendar
    should returns 2 hours as elapsed time  The 9-17 weekdays calendar will
    return  1 hour instead

```
getOverdueLocalDateTime(LocalDateTime now, Duration remainingDuration) 
```

- this method must return the date when this SLA  will be considered overdue
    given the parameter values. For example:  given {now=2019-05-13T17:00:00
    and remainingDuration = 24H} The 24/7 calendar will returns
    2019-05-14T17:00:00 as overdue date The 9-17 weekdays calendar will return
    2019-05-17T09:00:00 String getTitle(Locale locale) - this method must
    return the Calendar’s title for the given Locale. It will show up in the
    calendar dropdown in the SLA definition form page

