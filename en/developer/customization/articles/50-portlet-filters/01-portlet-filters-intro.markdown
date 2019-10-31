---
header-id: portlet-filters
---

# Portlet Filters

[TOC levels=1-4]

Portlet filters intercept requests and responses at the start of the
[portlet request processing phase](/docs/7-2/frameworks/-/knowledge_base/f/portlets). 
Portlet filters are commonly used for these things: 

- Transform content
- Add or modify request and response attributes
- Suspend a portlet phase to get user input
- Audit portlet activity

The 
[`javax.portlet.filter`](http://docs.liferay.com/portlet-api/3.0/javadocs/javax/portlet/filter/package-frame.html)
package defines a portlet filter interface for each phase. Here are the steps
for developing a portlet filter: 

1.  Implement the
    [portlet filter interface](http://docs.liferay.com/portlet-api/3.0/javadocs/javax/portlet/filter/package-frame.html)
    for the phase it's intercepting. Here are common interface methods to
    override: 

    `doFilter`: Here's where you take action. This method is invoked at the 
    start of the portlet request processing phase. The request and response
    parameters provide access to portlet content and attributes. The
    `FilterChain` parameter can be used to invoke the next filter in the phase. 

    `init`: Initialize the filter. The `FilterConfig` parameter can be used to 
    prepare the filter. 

    `destroy`: Perform any filter cleanup. 

2.  Target the desired portlet(s). 

3.  Choose how to prioritize the filter among other filters in the phase: 

    -   OSGi Declarative Service Component portlet filters use a service ranking
        property. High ranking filters execute before lower ones. 
    -   `<filter-mapping>` element order in a portlet application's `portlet.xml` 
        file. 
    -   The `ordinal` element value of a filter class annotated with 
        `@PortletLifecycleFilter`. Low ordinal value filters execute before
        higher ones. 

Below is demonstrated applying multiple filters to a portlet's render phase. The
filters are
[OSGi Declarative Service (DS) Components](/docs/7-2/frameworks/-/knowledge_base/f/declarative-services),
but filters can also be applied to a portlet using a `portlet.xml` descriptor or
a `@PortletLifecycleFilter` annotation. See the Portlet 3.0 Specification for
details. The sample code is available
[here](https://portal.liferay.dev/learn/code-samples/-/cs/list/7.2/java8/workspace-gradle/modules/applications/portlets/render-filter-portlet). 

## Sample Portlet

The sample portlet `MembersListPortlet` is a
[Liferay MVC Portlet](/docs/7-2/appdev/-/knowledge_base/a/liferay-mvc-portlet)
that lists names and email addresses when users click its *Load Users*
button. The information is based on `Person` objects that the portlet class
passes to the View template via a request attribute called
`MembersListPortlet.MEMBERLIST_ATTRIBUTE`. 

```java
public void loadUsers(ActionRequest actionRequest, ActionResponse actionResponse) {

    actionRequest.setAttribute(MembersListPortlet.MEMBERLIST_ATTRIBUTE, createStaticUserList());
}
```

Two render filters are applied to the portlet:

1.  Render filter 1 hides parts of the user email addresses (e.g., for 
    privacy) by modifying the request object.

2.  Render filter 2 logs portlet render phase statistics. 

Adding the `MemberList` portlet to a page and clicking the `Load Users` button
renders each `Person`'s name and partially hidden email address, thanks to the
filter `EncodingPersonEmailsRenderFilter`.

```
Sievert Shayne

Sievert.Sha...@...mple.com

Vida Jonas

Vida.Jo...@...mple.com
...
```

If you set the portlet's log level to `debug`, it prints the render phase
statistics. 

```
Portlet com_liferay_code_samples_portal_modules_applications_portlets_render_filter_MembersListPortlet rendered in 7791 ms
Portlet com_liferay_code_samples_portal_modules_applications_portlets_render_filter_MembersListPortlet rendered 2 times with an average 356135 ms render time
```

The first filter modifies portlet content via the request object. 

## Render filter 1 hides parts of user email addresses

`EncodingPersonEmailsRenderFilter` is a `RenderFilter` that hides parts of user
email addresses by modifying a request attribute. Here is the class:

```java
@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + MembersListPortlet.MEMBERSLIST_PORTLET_NAME,
                "service.ranking:Integer=1" 
        },
        service = PortletFilter.class
)
public class EncodingPersonEmailsRenderFilter implements RenderFilter {

    @Override
    public void doFilter(RenderRequest request, RenderResponse response, FilterChain chain)
            throws IOException, PortletException {

        //This is executed before the portlet render
        Optional.ofNullable((List<Person>)request.getAttribute(MembersListPortlet.MEMBERLIST_ATTRIBUTE))
                .ifPresent(personList ->
                        request.setAttribute(MembersListPortlet.MEMBERLIST_ATTRIBUTE, ofuscateEmails(personList)));

        // Invoke the rest of the filters in the chain
        //  (it also invokes the Portlet render method if this is the last filter in the chain
        chain.doFilter(request, response);

    }

    private List<Person> ofuscateEmails(List<Person> list) {
        return list.stream()
                .map(this::ofuscatePersonEmail)
                .collect(Collectors.toList());
    }

    private Person ofuscatePersonEmail(Person person) {
        return new Person(person.getName(),
                          person.getEmail().replaceFirst("(.+)(...)@(...)(.*)", "$1...@...$4"));

    }

    @Override
    public void init(FilterConfig filterConfig) throws PortletException {

    }

    @Override
    public void destroy() {

    }
}
```

The `@Component` annotation declares the filter to be an OSGi DS Component. Here
are its elements and properties:

`immediate = true` sets the component ready to start upon being installed. 

`service = PortletFilter.class` defines the component to be a 
`PortletFilter` service. 

`javax.portlet.name = + MembersListPortlet.MEMBERSLIST_PORTLET_NAME` 
links the filter to the target portlet. Note, multiple portlets can be
listed. 

`service.ranking:Integer=1` sets the filter to execute after filters that 
are ranked higher than `1`. 
 
`EncodingPersonEmailsRenderFilter` *implements* the
[`RenderFilter`](http://docs.liferay.com/portlet-api/3.0/javadocs/javax/portlet/filter/RenderFilter.html)
interface, overriding the `doFilter`, `init`, and `destroy` methods. 

`doFilter` modifies the attribute  `MembersListPortlet.MEMBERLIST_ATTRIBUTE`'s
list of `Person`s by replacing parts of their email addresses with ellipses
(`...`). It delegates the `ofuscatePersonEmail` method to do the modifications.
Then `doFilter` invokes `chain.doFilter(request, response)` to execute the next
`RenderFilter` or next portlet processing phase. 

| **Note:** Filters can also intercept and block the execution of a portlet 
| phase.  In the `doFilter` method, this is usually done by throwing an
| exception or by not calling the next element in the filter chain. 

## RenderFilter 2 Logs Statistics

`MembersListStatsRenderFilter` is a `RenderFilter` that logs the number of times
the portlet is rendered and the average render time. Here's the code:

```java
@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + MembersListPortlet.MEMBERSLIST_PORTLET_NAME,
                "service.ranking:Integer=100"
        },
        service = PortletFilter.class
)
public class MembersListStatsRenderFilter implements RenderFilter {

    //Thread safe - accumulator that keeps the number of times the portlet has been rendered
    private final LongAdder hits = new LongAdder();

    //Thread safe accumulator that keeps total time spent rendering the portlet.
    private final LongAdder accumulatedTimeMs = new LongAdder();

    @Override
    public void doFilter(RenderRequest request, RenderResponse response, FilterChain chain) throws IOException, PortletException {

        long startTime = System.nanoTime();

        chain.doFilter(request, response);

        long renderTime = (System.nanoTime() - startTime) / 1000;
        hits.increment();
        accumulatedTimeMs.add(renderTime);

        if (LOG.isDebugEnabled()) {
            long totalHits = hits.longValue();
            long averageRenderTimeNs = accumulatedTimeMs.longValue() / totalHits;
            LOG.debug("Portlet " + MembersListPortlet.MEMBERSLIST_PORTLET_NAME + " rendered in " + renderTime + " ms");
            LOG.debug("Portlet " + MembersListPortlet.MEMBERSLIST_PORTLET_NAME + " rendered " + hits.longValue()
                    + " times with an average " + averageRenderTimeNs + " ms render time");
        }
    }

    ...

    private static final Log LOG = LogFactoryUtil.getLog(MembersListStatsRenderFilter.class);
}
```

As with `EncodingPersonEmailsRenderFilter`, it's an OSGi DS Component that is a
`PortletFilter` service, starts upon installation, applies to the
`MembersListPortlet`, and has a service ranking. Since its ranking is `100`, it
is executed before render filter `EncodingPersonEmailsRenderFilter`. 

`MembersListStatsRenderFilter`'s `doFilter()` method audits the render phase in
these ways:

1.  Notes the render phase start time. 

2.  Executes `chain.doFilter(request, response)` to invoke all of the other 
    `RenderFilter`s in the `FilterChain`. 

3.  Increments the number of times the portlet renders.

4.  Calculates the average render time.

5.  Logs the times rendered and average render time. 

Consider creating your own filters to intercept portlet processing phases. 

## Related Topics 

[Portlets](/docs/7-2/frameworks/-/knowledge_base/f/portlets)

[JSP Overrides Using Portlet Filters](/docs/7-2/customization/-/knowledge_base/c/jsp-overrides-using-portlet-filters)

[Liferay MVC Portlet](/docs/7-2/appdev/-/knowledge_base/a/liferay-mvc-portlet)
