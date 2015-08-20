# Consuming Liferay Services [](id=consuming-liferay-services)

Liferay's services are registered in Liferay's module framework. They are
created using the
[Declarative Services](http://wiki.osgi.org/wiki/Declarative_Services)
component model. Declarative Services isn't a dependency injection tool but it
can behave and feel like one. The declarative services component model makes it
easy to publish and consume services from Liferay's module framework. In this
tutorial, you'll learn how to invoke Liferay services (from the core or modules)
from your application. You can find the code referenced by this tutorial in a
complete project on Github here:
[https://github.com/jrao/liferay-docs/tree/liferay7-development-tutorials/develop/tutorials/code/liferay-plugins-sdk-7.0.0/portlets/example-service-consumer-portlet](https://github.com/jrao/liferay-docs/tree/liferay7-development-tutorials/develop/tutorials/code/liferay-plugins-sdk-7.0.0/portlets/example-service-consumer-portlet)

To invoke a service from your application, follow these steps:

1.  Add any required dependencies. You need to add the bundle that provides the
    service you want to invoke, and you need to resolve its dependencies.

2.  Determine the class in your project to invoke the service. In the chosen
    class, declare the service as a member variable and generate getter and setter
    methods for the service.

3.  Annotate the service's setter method with the `@Reference` annotation:
    `org.osgi.service.component.annotations.Reference`. This annotation is
    provided by the `org.osgi.compendium.jar`.

4.  Use the service's getter method to obtain a service bean. Then invoke the
    service.

5.  (Optional) Display the result of your service invocation. If you're
    developing a portlet application, an easy way to display a result is to add
    it to the `PortletRequest` object as an attribute which can be retrieved by a
    JSP.

Suppose you need to invoke two Liferay services, `UserLocalService` and
`BookmarksFolderLocalService`. `UserLocalService` is a core Liferay service
whereas `BookmarksFolderLocalService` is provided by a module. Let's take these
services as examples and follow the steps specified above.

## Add Required Dependencies [](id=add-required-dependencies)

`UserLocalService` is provided by Liferay's `portal-service.jar` file, so make
sure that it's declared as a dependency in your project's `ivy.xml` file.
`BookmarksFolderLocalService` is provided by a separate bundle,
`com.liferay.bookmarks.api.jar`. This bundle also needs to be declared as a
dependency. To add these JARs as dependencies, open your project's `ivy.xml`
file and add the following lines inside the `<dependencies>` element:

    <dependency org="com.liferay.portal" name="portal-service" rev="7.0.0-nightly"/>
    <dependency org="com.liferay" name="com.liferay.bookmarks.api" rev="1.0.0-SNAPSHOT"/>

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
portlet's `render` method. But you can do this from any method from the class
where you've declared the service beans and their getters and setters:

    int userCount = getUserLocalService().getUsersCount();
    int bookmarksFolderCount =
            getBookmarksFolderLocalService().getBookmarksFoldersCount();

## (Optional) Display the Results of Your Service Invocations [](id=optional-display-the-results-of-your-service-invocations)

It's not necessary to display the results of your service invocations. However,
if you want to do so, using request attributes is an easy way to make the
service invocation results available to a JSP. Simply assign the results of your
service invocations as attributes of the request object:

    request.setAttribute("userCount", userCount);
    request.setAttribute("bookmarksFolderCount", bookmarksFolderCount);

Here, the type of `request` is `javax.portlet.RenderRequest`. To retrieve the
request attributes from a JSP page, you can follow the pattern of this
`view.jsp` file:

    <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
    <jsp:useBean id="userCount" type="java.lang.Integer" scope="request"/>
    <jsp:useBean id="bookmarksFolderCount" type="java.lang.Integer" scope="request"/>

    <portlet:defineObjects />

    <p>The portal has <%= userCount %> users. </p>
    <p>The portal has <%= bookmarksFolderCount %> bookmarks folders. </p>

This is a simple JSP that's easy to understand. The first line declares the
portlet tag library which allows the `<portlet:defineObjects />` tag to be used
on the third line. While not absolutely necessary for this example, it makes
several standard variables available on the page. The two `<jsp:useBean>` tags
get the two variables from the request object and make them available as
variables on the page. Finally, the last two lines display the values.

For reference, here's the complete `ExampleServiceConsumerPortlet` class:

    package com.liferay.docs.consumer.portlet;

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

    @Component(immediate = true, 
        property = { "com.liferay.portlet.display-category=category.sample",
            "com.liferay.portlet.instanceable=true", 
            "javax.portlet.display-name=Example Service Consumer Portlet",
            "javax.portlet.security-role-ref=power-user,user", 
            "javax.portlet.init-param.template-path=/",
            "javax.portlet.init-param.view-template=/view.jsp" }, 
        service = Portlet.class)

    public class ExampleServiceConsumerPortlet extends MVCPortlet {

        private UserLocalService _userLocalService;
        private BookmarksFolderLocalService _bookmarksFolderLocalService;

        public UserLocalService getUserLocalService() {
            return _userLocalService;
        }

        @Reference
        public void setUserLocalService(UserLocalService _userLocalService) {
            this._userLocalService = _userLocalService;
        }

        public BookmarksFolderLocalService getBookmarksFolderLocalService() {
            return _bookmarksFolderLocalService;
        }

        @Reference
        public void setBookmarksFolderLocalService(BookmarksFolderLocalService _bookmarksFolderLocalService) {
            this._bookmarksFolderLocalService = _bookmarksFolderLocalService;
        }
        
        public void render (RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException, IOException { 
            int userCount = getUserLocalService().getUsersCount();
            int bookmarksFolderCount = getBookmarksFolderLocalService().getBookmarksFoldersCount();
            
            renderRequest.setAttribute("userCount", userCount);
            renderRequest.setAttribute("bookmarksFolderCount", bookmarksFolderCount);
            
            super.render(renderRequest, renderResponse);
 
            
        }

    }

Great! Now you've seen how easy the
[Declarative Services](http://wiki.osgi.org/wiki/Declarative_Services) component 
model makes invoking services in Liferay.

## Related Topics [](id=related-topics)

[Creating a Portlet Application](/develop/tutorials/-/knowledge_base/7-0/creating-a-portlet-application)

[Using JSPs in a Portlet Application](/develop/tutorials/-/knowledge_base/7-0/using-jsps-in-a-portlet-application)

[Publishing Liferay Services](/develop/tutorials/-/knowledge_base/7-0/publishing-liferay-services)
