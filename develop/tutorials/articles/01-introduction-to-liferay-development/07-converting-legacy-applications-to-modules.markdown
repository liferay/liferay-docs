# Converting Legacy Applications to Modules

You've probably heard the term *modularity* discussed quite frequently in
relation to Liferay 7.0. With Liferay 7.0 transforming into a modular platform,
components that Liferay uses will be mostly in the form of modules. For example,
many of the components that you're used to seeing in Liferay are now
[modules](https://github.com/liferay/liferay-portal/tree/master/modules/apps).

A module doesn't use the traditional Liferay application structure from previous
versions. Liferay is following a different structure for modules that you'll
learn about in this tutorial. You, however, are not required to follow
Liferay's standard application structure; you have the freedom to structure your
application's modules however you wish.

The options you have for upgrading your application to Liferay 7.0 is discussed
briefly in the
[Migrating Legacy Applications to New Plugins SDK](/develop/tutorials/-/knowledge_base/7-0/migrating-legacy-applications-to-new-plugins-sdk)
tutorial. Because Liferay 7.0 maintains backwards compatibility, you have the
option to keep your legacy application as a WAR-style application or convert
your application into modules. Before you begin following the steps in this
tutorial to convert your application into modules, you'll learn a few tips and
recommendations for which option to choose.

Before you begin converting your legacy application into modules, it is
recommended that you first migrate your legacy plugin to a Liferay 7.0
compatible WAR file. It is much easier to convert a legacy application to
modules when the API and breaking changes are updated. If you jump from a legacy
6.2 application to 7.0 modules, it will be much more difficult debugging and
figuring out what issues are related to which migration process. This is,
however, not required.

Converting your legacy application to modules is not always the right choice. In
some scenarios, it could make sense to stick with your traditional WAR model.
Below, you'll learn some important tips to help you make your decision on
whether converting your legacy application to modules is the right decision.

**When to convert?**

- If you have a very large application with many lines of code. For example, if
there are many developers that are collaborating on an application concurrently
and making changes frequently, separating the code into modules will increase
productivity and the agility to release more frequently.
- If your plugin has reusable parts that you'd like to consume from elsewhere.
For instance, suppose you have business logic that you're reusing in multiple
different projects. As opposed to copying that code into several different WARs
and deploying that to different customers, you can make those code parts into
modules and consume those services from one single module.

**When to not convert?**

- You have a portlet that's using JSR-168/286 compatibility and you still want
to be able to deploy that into another portlet container. If you want to retain
that compatibility, it is recommended to stay with the traditional WAR model.
- You're using a complex legacy web framework that is really tied to the Java EE
programming model, and the amount of work necessary to make that work with OSGi
is more than you feel is necessary or warranted.
- If your plugin interacts with the app server. By distancing applications from
the app server, it means that they are much more portable because you can remove
a lot of contingent code that does different behaviors based on the app server.
This also unifies the classloading model because different app servers have
different classloaders models, so it's very difficult to manage plugins and
decide how their classloading is going to work.
- If your legacy application's original intent was to have limited-lifetime,
there may not be a good reason to convert.

Your decision to convert to modules utimately comes down to benefits vs. costs.
Obviously, the time to convert your legacy application is a cost. Likewise,
there are many benefits to managing your legacy application as modules. Large
applications can be split into many smaller, easy to manage modules, which
allows for a much better release process. You can fix bugs in sub-modules
without affecting the entire application. Other benefits include being able to
have an incremental release cycle and the ability to update modules
independently.

Now that you have some ammunition to make an informed decision on what's best
for your particular scenario, you'll learn how to convert
your legacy WAR application into modules. Before getting started, it's important
to reiterate that the module structure shown in this tutorial is just one of
many ways for structuring your application's modules. It's also important to
remember that applications come in all different shapes and sizes. There may be
special actions that some applications require. This tutorial will provide the
general process for converting to modules using Liferay's module strucutre.

1.













## Related Topics [](id=related-topics)

[Creating Liferay Applications](/develop/tutorials/-/knowledge_base/7-0/creating-liferay-applications)

[Creating Liferay Components](/develop/tutorials/-/knowledge_base/7-0/creating-liferay-components)

[Consuming Liferay Services](/develop/tutorials/-/knowledge_base/7-0/consuming-liferay-services)
