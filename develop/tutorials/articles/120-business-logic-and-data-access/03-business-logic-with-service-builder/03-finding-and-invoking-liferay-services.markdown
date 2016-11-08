# Finding and Invoking Liferay Services [](id=finding-and-invoking-liferay-services)

In this tutorial, you'll learn how to search for portal services and portlet
services. You can find Liferay's services by searching for them in the Javadocs:
[http://docs.liferay.com/portal/7.0/javadocs/](http://docs.liferay.com/portal/7.0/javadocs/).

First, you'll learn how to find a portal service using Liferay's Javadocs. 

## Finding Liferay Portal Services [](id=finding-liferay-portal-services)

Searching for Liferay Portal services is easy and intuitive. The first two
options, `portal-impl` and `portal-kernel`, are the most popular options when
searching for Liferay's Javadocs. In summary, the `portal-kernel` directory
provides interfaces and utils, and the `portal-impl` directory provides
service implementations that implement those interfaces. The remaining options
are miscellaneous util and test classes that are used in @product@.

Liferay's Javadocs are easy to browse and well-organized. Here's how to find the
*Organization* services, for example: 

1. In your browser, open up the Javadocs:
   [http://docs.liferay.com/portal/7.0/javadocs/](http://docs.liferay.com/portal/7.0/javadocs/) 
   You're offered several options, which were discussed earlier. Select
   *portal-kernel*.

2. Under *Portal Kernel*, click on the link for the
   `com.liferay.portal.kernel.service` package, since the services for the
   Organization entity belong to the *Portal* scope. 

3. Find and click on the `*LocalService` class (in this case,
   `OrganizationLocalService`) in the *Class Summary* table or the *Classes*
   list at the bottom of the page. 

That was easy! What if you want to find module services? 

## Finding Liferay Module Services [](id=finding-liferay-module-services)

Searching for Liferay module services is also easy. The Javadocs for modules are
hosted on Liferay's Nexus repository, and can be viewed by downloading and
extracting the module's `*javadoc.jar` file. You can learn move about how a
module's Java API is organized by reading the
[Java API](/develop/reference/-/knowledge_base/7-0/finding-liferay-api-modules#java-api)
section.

Here's an example of how to find services for a bookmarks entry:

1. Navigate to Liferay's
[Nexus repository](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/)
and select `com.liferay.bookmarks.api`. Then select the appropriate version.

2. Select the `com.liferay.bookmarks.api-[VERSION]-javadoc.jar` link, which
   downloads that JAR file. Extract the JAR file, once downloaded.

3. Open the extracted contents and select the `index.html` file.

4. Select the `com.liferay.bookmarks.service` package from the main view, and
   then select the `BookmarksEntryLocalService` class in the *Class Summary*
   table or the *Classes* list.

Awesome! You've successfully located the bookmark entry's services.

Another easy way to search for services in module projects is by importing them
into your IDE. For Liferay IDE, you can right-click in the Package Explorer and
navigate to *Import* &rarr; *Liferay Module Project(s)*. Then browse for your
module, select the build type, and click *Finish*. Now you can peruse your
module services from Liferay IDE.

Now you're ready to invoke Liferay services. 

<!--
Searching for one of Liferay's built-in plugin services is also easy. Instead of
clicking the link for the service package of the *portal*, click the link for
the service package of the *portlet*. The portlet service packages use the
naming convention `com.liferay.portlet.[portlet-name].service`, where
`[portlet-name]` is replaced with the actual name of the portlet. 

Here's how you find services for a user's blogs statistics:

1. In your browser, open the Javadocs:
   [http://docs.liferay.com/portal/6.2/javadocs/](http://docs.liferay.com/portal/6.2/javadocs/)

2. Under *Portlet Services*, click on the link for the
   `com.liferay.portlet.blogs.service` package in the *Packages* frame, since
   the services are a part of the Blogs portlet. 

3. Find and click on the `*ServiceUtil` class (in this case
   `BlogsStatsUserLocalServiceUtil`) in the *Class Summary* table or the
   *Classes* list. 

Now you're ready to invoke Liferay services. To invoke Liferay services
remotely, your Liferay instance must be configured to allow remote web service
access. Please see the
[Service Security Layers](/develop/tutorials/-/knowledge_base/7-0/service-security-layers) 
tutorial for details.
-->
<!-- The above text does not yet apply, since module Javadoc is only hosted in a
JAR on Nexus. Update this when module Javadoc is hosted on docs.liferay.com.
-Cody -->

## Invoking Liferay Services Locally [](id=invoking-liferay-services-locally)

Every Liferay service provides a local interface to clients running in the same
JVM as Liferay Portal. Many local services (e.g., `*LocalService` classes) are
called by remote services (e.g., `*Service` classes). The remote classes mask
the complexity of the local service implementations and include permission
checks. The core Liferay services that are provided as part of Liferay Portal
were generated by the same Service Builder tool that you can use in your
applications. You can invoke a remote Liferay service by calling the appropriate
`*LocalService` or `*Service` class. The following code found in the
`journal-content-web` module demonstrates how to retrieve the portal instance's
group by calling Liferay's `GroupLocalService`:

    Group group = _groupLocalService.getCompanyGroup(companyId);

By Liferay convention, the `_groupLocalService` instance variable is created and
set for usage in the class it's called from:

    private GroupLocalService _groupLocalService;

    @Reference(unbind = "-")
    protected void setGroupLocalService(GroupLocalService groupLocalService) {
        _groupLocalService = groupLocalService;
    }

The `@Reference(unbind="-")` annotation retrieves a reference to a service of
type `GroupLocalService`, and ignores how this service has been published and
who published it.

Besides the services Service Builder made available for your application, you
can also access any service published within the OSGi Registry. This means the
following services are available:

- Beans defined in Liferay's core
- Beans created in other module app contexts
- Services declared using Declarative Service
- Services registered using the OSGi low level API

Some types of portlets don't have access to the OSGi Registry using Declarative
Services (e.g., Spring MVC and JSF). You can call OSGi services in these
portlets by using [Service Trackers](/develop/tutorials/-/knowledge_base/7-0/service-trackers).

You'll learn more about referencing OSGi services next.

## Referencing OSGi Services [](id=referencing-osgi-services)

All the services created within your Service Builder application are wired using
an internal Spring Application Context. This uses AOP proxies to give your
services the ability to deal with transactions, indexing, and security.

In many cases, however, you'll need to reference an external service (i.e.,
something that is not defined within your Spring Application Context). Liferay
has included the ability to reference OSGi services from your Spring beans using
the `@ServiceReference` annotation.

You'll step through a simple example next.

Suppose you're building an application with a simple entity defined in your
`service.xml` file. The application needs to send an SMS every time a new entity
is created, and the `SMSService` is provided by a module installed in the
system.

How would you get a reference from the `-LocalServiceImpl` (Spring bean) to an
*external* service? You can do this by using the `@ServiceReference` annotation:

    @ServiceReference
    private SMSService _smsService;

Using this annotation lets you retrieve a reference to an OSGi service from a
regular Spring bean. This provides some nice benefits. If the `SMSService` is
not available, the whole Spring context is not created until the service is
available. Likewise, if the `SMSService` suddenly disappears, the whole Spring
Application Context is destroyed. This makes our Spring apps much more robust
and versatile.

Fortunately, Service Builder generates this kind of code every time you have a
reference to an entity which is not defined in your `service.xml` file. For
example, imagine that your entity has a reference provided in your `service.xml`
file, to the Group entity:

    <reference entity="Group" package-path="com.liferay.portal" />

The generated code for this entity would look like the following:

    @ServiceReference(type = com.liferay.portal.kernel.service.GroupLocalService.class)
    protected com.liferay.portal.kernel.service.GroupLocalService groupLocalService;

Great! You know how to find Liferay's core and module services, and can invoke
them from your application. You also learned about referencing OSGi services.

## Related Topics [](id=related-topics)

[Invoking Local Services](/develop/tutorials/-/knowledge_base/7-0/invoking-local-services)

[Invoking Remote Services](/develop/tutorials/-/knowledge_base/7-0/invoking-remote-services)

[JSON Web Services Invoker](/develop/tutorials/-/knowledge_base/7-0/json-web-services-invoker)

[Service Trackers](/develop/tutorials/-/knowledge_base/7-0/service-trackers)
