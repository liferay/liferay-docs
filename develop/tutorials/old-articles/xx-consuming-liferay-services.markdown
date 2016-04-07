# Consuming Liferay Services [](id=consuming-liferay-services)

Liferay's services are registered as components in Liferay's module framework.
They are created using the
[Declarative Services](http://wiki.osgi.org/wiki/Declarative_Services)
component model. Declarative Services isn't exactly a dependency injection tool,
but it can behave like one. The declarative services component model makes it
easy to publish and consume OSGi services from Liferay's module framework. In
this tutorial, you'll learn how to invoke Liferay services (from the core or
from modules) from your application. You can find the code referenced by this
tutorial in a complete project on Github here:
[https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/osgi/modules/com.liferay.docs.exampleserviceconsumerportlet](https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/osgi/modules/com.liferay.docs.exampleserviceconsumerportlet).

To invoke a service from your application, follow these steps:

1.  Add any required dependencies. You need to add the module that provides the
    service you want to invoke, and you need to resolve its dependencies.

2.  Determine which class or classes in your project need to invoke the service.
    In each chosen class, declare the service as a member variable and generate
    getter and setter methods for the service.

3.  Annotate the service's setter method with the `@Reference` annotation:
    `org.osgi.service.component.annotations.Reference`. This annotation is
    provided by the `org.osgi.compendium.jar`.

4.  Use the service's getter method to obtain a service bean. Then invoke the
    service.

5.  (Optional) Display the result of your service invocation. If you're
    developing a portlet application, an easy way to display a result is to add
    it to the `PortletRequest` object as an attribute which can be retrieved by
    a JSP.

Suppose you need to invoke two Liferay services, `UserLocalService` and
`BookmarksFolderLocalService`. `UserLocalService` is a core Liferay service
whereas `BookmarksFolderLocalService` is provided by a module. Let's take these
services as examples and follow the steps specified above.

## Add Required Dependencies [](id=add-required-dependencies)

`UserLocalService` is provided by Liferay's `portal-service.jar` file, so make
sure that it's declared as a dependency in your project's build file. If you
used Blade Tools to create an MVC portlet, Liferay's `portal-service.jar` file
is already declared as a dependency:

    compile 'com.liferay.portal:portal-service:7.0.0-SNAPSHOT'

`BookmarksFolderLocalService` is provided by a
separate module, `com.liferay.bookmarks.api.jar`. This module also needs to be
declared as a dependency. To add this JAR as a dependency, open your project's
`build.gradle` file and add the following lines inside of the `dependencies
{...}` block:

    compile 'com.liferay:com.liferay.bookmarks.api:1.0.0-SNAPSHOT

## Declare the Service Beans and Their Getters and Setters [](id=declare-the-service-beans-and-their-getters-and-setters)

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

    public void setBookmarksFolderLocalService(
            BookmarksFolderLocalService bookmarksFolderLocalService) {

        _bookmarksFolderLocalService = bookmarksFolderLocalService;
    }

    public void setUserLocalService(UserLocalService userLocalService) {
        _userLocalService = userLocalService;
    }

## Add the @Reference Annotations [](id=add-the-reference-annotations)

Add the `@Reference` annotation to the setter methods:

    @Reference
    public void setBookmarksFolderLocalService(
            BookmarksFolderLocalService bookmarksFolderLocalService) {

        _bookmarksFolderLocalService = bookmarksFolderLocalService;
    }

    @Reference
    public void setUserLocalService(UserLocalService userLocalService) {
        _userLocalService = userLocalService;
    }

## Obtain the Service Beans and Invoke the Services [](id=obtain-the-service-beans-and-invoke-the-services)

In this example, you obtain service instances and invoke the services from the
portlet's `render` method. But you can do this from any method in the class
where you've declared the service beans and their getters and setters:

    int userCount = getUserLocalService().getUsersCount();
    int bookmarksFolderCount =
        getBookmarksFolderLocalService().getBookmarksFoldersCount();

## (Optional) Display the Results of Your Service Invocations [](id=optional-display-the-results-of-your-service-invocations)

If you want to send the results of your service invocations to the view layer of
your application, you can do this using request attributes. Assign the
results of your service invocations as attributes of the request object:

    request.setAttribute("userCount", userCount);
    request.setAttribute("bookmarksFolderCount", bookmarksFolderCount);

Here, the type of `request` is `javax.portlet.RenderRequest`. To retrieve the
request attributes from a JSP page, you can follow the pattern of this
`view.jsp` file:

    <%@ include file="/init.jsp" %>

    <%
    int userCount = GetterUtil.getInteger(renderRequest.getAttribute("USER_COUNT"));

    int bookmarksFolderCount = GetterUtil.getInteger(renderRequest.getAttribute("BOOKMARKS_FOLDER_COUNT"));
    %>

    <p>The portal has <%= userCount %> users.</p>

    <p>The portal has <%= bookmarksFolderCount %> bookmarks folders.</p>

This is a simple JSP that's easy to understand. The first line imports the
`init.jsp` file which declares the portlet tag library and uses the
`<portlet:defineObjects />` tag. This tag makes the `renderRequest` variable
available to the page. The two lines in the scriptlet retrieve the values of the
`USER_COUNT` and `BOOKMARKS_FOLDER_COUNT` attributes from the request object and
make them available as variables on the page. The `GetterUtil` class is a
Liferay helper class that was imported by `init.jsp`: `<%@ page
import="com.liferay.portal.kernel.util.GetterUtil" %>`. Finally, the last two
lines display the values of the `userCount` and `bookmarksFolderCount`
variables.

For reference, here's the complete `ExampleServiceConsumerPortlet` class:

    package com.liferay.docs.exampleserviceconsumerportlet;

    import java.io.IOException;

    import javax.portlet.Portlet;
    import javax.portlet.PortletException;
    import javax.portlet.RenderRequest;
    import javax.portlet.RenderResponse;

    import org.osgi.service.component.annotations.Component;
    import org.osgi.service.component.annotations.Reference;

    import com.liferay.bookmarks.service.BookmarksFolderLocalService;
    import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
    import com.liferay.portal.service.UserLocalService;

    @Component(
        immediate = true,
        property = {
            "com.liferay.portlet.display-category=category.sample",
            "com.liferay.portlet.instanceable=true",
            "javax.portlet.display-name=Example Service Consumer Portlet",
            "javax.portlet.init-param.template-path=/",
            "javax.portlet.init-param.view-template=/view.jsp",
            "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
    )
    public class ExampleServiceConsumerPortlet extends MVCPortlet {

        @Override
        public void doView(RenderRequest request, RenderResponse response)
                throws IOException, PortletException {

            int userCount = getUserLocalService().getUsersCount();
            request.setAttribute("USER_COUNT", userCount);

            int bookmarksFolderCount =
                getBookmarksFolderLocalService().getBookmarksFoldersCount();
            request.setAttribute("BOOKMARKS_FOLDER_COUNT", bookmarksFolderCount);

            super.doView(request, response);
        }

        public BookmarksFolderLocalService getBookmarksFolderLocalService() {
            return _bookmarksFolderLocalService;
        }

        public UserLocalService getUserLocalService() {
            return _userLocalService;
        }

        @Reference
        public void setBookmarksFolderLocalService(
                BookmarksFolderLocalService bookmarksFolderLocalService) {

            _bookmarksFolderLocalService = bookmarksFolderLocalService;
        }

        @Reference
        public void setUserLocalService(UserLocalService userLocalService) {
            _userLocalService = userLocalService;
        }

        private UserLocalService _userLocalService;
        private BookmarksFolderLocalService _bookmarksFolderLocalService;

    }

Great! Now you've seen how easy the
[Declarative Services](http://wiki.osgi.org/wiki/Declarative_Services) component 
model makes invoking services in Liferay.

## Related Topics [](id=related-topics)

[Introduction to Blade Tools](/develop/tutorials/-/knowledge_base/7-0/installing-blade-cli)

[Creating Liferay Applications](/develop/tutorials/-/knowledge_base/7-0/creating-liferay-applications)

[Creating Liferay Components](/develop/tutorials/-/knowledge_base/7-0/creating-liferay-components)

[Customizing Liferay Services](/develop/tutorials/-/knowledge_base/7-0/customizing-liferay-services)

[Using BLADE to Create Modules](/develop/tutorials/-/knowledge_base/7-0/using-blade-to-create-modules)
