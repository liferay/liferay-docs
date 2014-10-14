# Invoking the API Locally [](id=invoking-the-api-locally)

Each service provides a local interface to clients running in the same JVM as
Liferay Portal. There are two ways to invoke a service API's methods: 

- Spring injection: If your application uses Spring and has access to the portal
context. 

- `-ServiceUtil` classes: These classes mask the complexity of service
implementations. This is a good option if you're not familiar with Spring. 

Let's invoke a service using its `-ServiceUtil` class. The following JSP code
snippet demonstrates how to get a list of the most recent bloggers from an
organization. 

    <%@ page import="com.liferay.portlet.blogs.service.BlogsStatsUserLocalServiceUtil" %>
    <%@ page import="com.liferay.portlet.blogs.util.comparator.StatsUserLastPostDateComparator" %>
    ...
    <%@
    List statsUsers = BlogsStatsUserLocalServiceUtil.getOrganizationStatsUsers(
        organizationId, 0, max, new StatsUserLastPostDateComparator());
    %>

This JSP code invokes the static method `getOrganizationStatsUsers()` from the
`-LocalServiceUtil` class `BlogsStatsUserLocalServiceUtil`. 

+$$$

**Note:** Permission checks are not performed when you invoke services locally
(i.e., from the same JVM that's Liferay Portal runs on). To ensure permission
checks are performed, use the remote variant of the API, even from a local
context. 

$$$

<!-- I don't believe the above tip is true anymore. If the -ServiceUtil class
uses the filterFindBy implementation, permission checks are performed. -Rich --> 

Next, find out how you can invoke Liferay's service APIs remotely. 
