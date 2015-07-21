# Consuming Liferay Services

Liferay's services are made available as services in Liferay's module framework.
They are created via the
[Declarative Services](http://wiki.osgi.org/wiki/Declarative_Services)
component model. Declarative Services isn't a dependency injection tool but it
can behave and feel like one. In this tutorial, you'll learn how to invoke
Liferay services from your application, both core Liferay services and services
provided by modules. To invoke a service from your application, you need to take
these steps:

1. Add any required dependencies. You need to add the bundle that provides the
   service you'd like to invoke and you need to resolve its dependencies.

2. Determine which class in your project needs to invoke the service. In the
   chosen class, declare the service as a member variable and generate getter
   and setter methods for the service.

3. Annotate the setter method of the service with the `@Reference` annotation:
   `org.osgi.service.component.annotations.Reference`. This annotation is
   provided by the `org.osgi.compendium.jar`.

4. Use the getter method of the service to obtain a service bean. Then invoke
   the service!

5. (Optional) Display the result of your service invocation. If you're
   developing a portlet application, an easy way to display a result is to add
   it to the `PortletRequest` object as an attribute which can be retrieved by a
   JSP.

Suppose you need to invoke two Liferay services, `UserLocalService` and
`BookmarksFolderLocalService`. `UserLocalService` is a core Liferay service
whereas `BookmarksFolderLocalService` is provided by a module. Let's take these
services as examples and follow the steps specified above.

## Add Required Dependencies

`UserLocalService` is provided by Liferay's `portal-service.jar` file, so make
sure that it's declared as a dependency in your project's `ivy.xml` file.
`BookmarksFolderLocalService` is provided by a separate bundle,
`com.liferay.bookmarks.api.jar`. This bundle also needs to be declared as a
dependency. To add these JARs as dependencies, open your project's `ivy.xml`
file and add the following lines inside of the `<dependencies>` element:

    <dependency org="com.liferay.portal" name="portal-service" rev="7.0.0-nightly"/>
    <dependency org="com.liferay" name="com.liferay.bookmarks.api" rev="1.0.0-SNAPSHOT"/>

## Declare the Service Beans and Their Getters and Setters

We'll assume that you're developing a portlet application and you want to
declare the `UserLocalService` and `BookmarksFolderLocalService` beans in your
portlet class. In this case, you should declare your service beans like this:

    private UserLocalService _userLocalService;
    private BookmarksFolderLocalService _bookmarksFolderLocalService;

And you should generate getter and setter methods like this:

    public BookmarksFolderLocalService getBookmarksFolderLocalService() {
        return _bookmarksFolderLocalService;
    }

    public UserLocalService getUserLocalService() {
        return _userLocalService;
    }

    public void setBookmarksFolderLocalService(BookmarksFolderLocalService bookmarksFolderLocalService) {
        _bookmarksFolderLocalService = bookmarksFolderLocalService;
    }

    public void setUserLocalService(UserLocalService userLocalService) {
        _userLocalService = userLocalService;
    }

## Add the @Reference Annotations

Add the `@Reference` annotation to the setter methods:

    @Reference
    public void setBookmarksFolderLocalService(BookmarksFolderLocalService bookmarksFolderLocalService) {
        _bookmarksFolderLocalService = bookmarksFolderLocalService;
    }

    @Reference
    public void setUserLocalService(UserLocalService userLocalService) {
        _userLocalService = userLocalService;
    }

## Obtain the Service Beans and Invoke the Services

In this example, we obtain service instances and invoke the services from the
portlet's `doView` method. But you can do this from any method from the class
where you've declared the service beans and their getters and setters:

    int userCount = getUserLocalService().getUsersCount();
    int bookmarksFolderCount = _bookmarksFolderLocalService.getBookmarksFoldersCount();

## (Optional) Display the Results of Your Service Invocations

It's not necessary to display the results of your service invocations. However,
if you want to do so, using request attributes is an easy way to make the
service invocation results available to a JSP. Simply assign the results of your
service invocations as attributes of the request object:

    request.setAttribute("USER_COUNT", userCount);
    request.setAttribute("BOOKMARKS_FOLDER_COUNT", bookmarksFolderCount);

Here, the type of `request` is `javax.portlet.RenderRequest`. To retrieve the
request attributes from a JSP page, you can do something like this:

    <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

    <%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>

    <portlet:defineObjects />

    <%
    int userCount = GetterUtil.getInteger(renderRequest.getAttribute("USER_COUNT"));
    %>

    <p>The portal has <%= userCount %> users.</p>

Why so many lines for a simple JSP? The first line imports the portlet tag
library which allows the `<portlet:defineObjects />` tag to be used on the
third line. The `<portlet:defineObjects />` tag makes the `renderRequest`
variable available to the page (among other things). The second line imports
Liferay's `GetterUtil` class provides static utility methods like
`getInteger` which handle type conversions. The fourth line gets the
`"USER_COUNT"` attribute and uses `GetterUtil.getInteger` to convert it from
an `Object` to an integer. Finally, the fifth line displays the value.

Great! Now you've seen how easy the
[Declarative Services](http://wiki.osgi.org/wiki/Declarative_Services) component model makes
invoking services in Liferay.
