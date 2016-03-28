# Migrating Existing Code to Liferay 7 [](id=migrating-existing-code-to-liferay-7)

You've probably heard the term *modularity* discussed frequently in relation to
Liferay 7.0. With Liferay 7.0 being a modular platform, Liferay applications are
now composed of one or more
[modules](https://dev.liferay.com/participate/liferaypedia/-/wiki/Main/Module).

A module doesn't use the traditional Liferay application structure from previous
versions. Liferay is following a different structure for modules that you'll
learn about in these tutorials. You, however, are not required to follow
Liferay's standard application structure; you have the freedom to structure your
application's modules however you wish.

A module in Liferay is very similar to a standard Java application with some
additional metadata and annotations. The annotations replace the need for XML
file descriptors and are easier to manage since everything is in one place.  

Liferay, however, is still a JSR-286, standards compliant portal. For this reason, it
remains backwards compatible for WAR-style applications. This means two things:
you can write both module and WAR-style applications, and you can easily upgrade
your old 6.2 applications to new 7.0 APIs without converting them to modules
first. In fact before you begin converting your legacy application into modules, we recommend
that you first migrate your legacy plugin to a Liferay 7.0 compatible WAR-style
application. It is much easier to convert a legacy application to modules after
adapting to the new API and resolving [breaking changes](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/what-are-the-breaking-changes-for-liferay-7-0).
If you jump from a legacy 6.2 application to 7.0 modules, it will be much more
difficult to debug and figure out which issues are related to API changes and
which are related to the migration process.

The migration process can be considered as two general steps: 

[**Step 1:  Converting your 6.2 WAR-style plugins to a 7.0 WAR-style plugins**](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api)

[**Step 2:  Converting your WAR-style plugins to OSGi modules**](/develop/tutorials/-/knowledge_base/7-0/modularizing-legacy-plugins)

Converting your legacy application to modules might not always be the best
choice. In some scenarios, it could make better sense to stick with traditional
WAR model plugins and stop after completing Step 1. Below, you'll learn some
important tips to guide your decision on whether to convert your legacy
application to modules.

**When to convert?**

-   If you have a very large application with many lines of code. For example, if
    there are many developers that are collaborating on an application
    concurrently and making changes frequently, separating the code into modules
    will increase productivity and provide the agility to release more
    frequently.
-   If your plugin has reusable parts that you'd like to consume from elsewhere.
    For instance, suppose you have business logic that you're reusing in
    multiple different projects. Instead of copying that code into several
    different WARs and deploying those WARs to different customers, you can
    convert your application to modules and consume their services from other
    modules.

**When not to convert?**

-   You have a portlet that's JSR-168/286 compatible and you still want to be
    able to deploy it into another portlet container. If you want to retain that
    compatibility, it is recommended to stay with the traditional WAR model.
-   You're using a complex legacy web framework that is heavily tied to the Java
    EE programming model, and the amount of work necessary to make that work
    with OSGi is more than you feel is necessary or warranted.
-   If your plugin interacts with the app server. Module-based applications are
    not as portable when they directly interact with the app server.
- If your legacy application's original intent is to have a limited-lifetime.

Your decision to convert to modules ultimately comes down to benefits vs.
costs. Obviously, the time to convert your legacy application is a cost.
There are, however, many benefits to managing your legacy application as
modules.

A large application can be split into many small independent, easy to manage
modules. These small modules also allow for incremental release cycles. In
multi-module projects, this also means that a certain module can be updated
independently. For instance, if a JSP is changed due to a security issue, the
web (client) module can be updated while the persistence modules can remain
unchanged. This could mean applications that usually had to wait for new
Liferay releases could see independent releases between Liferay versions.

Module dependencies are explicitly listed within a module. A module will refuse
to run unless all dependencies are met, thus eliminating obscure run time
errors. Another common deployment issue for legacy applications was multiple
versions of the same library in an environment. The class loader would merge
classes from multiple versions of a library, leading to very hard to
troubleshoot and obscure problems. Module versions can be stated explicitly in
the dependency, eliminating these types of issues.

Now that you have some ammunition to make an informed decision on whether to
stop after converting your application's plugins to WAR-style Liferay 7 plugins,
or to continue on with modularizing those plugins. The next tutorial takes you
through the first step of adapting your WAR-style plugins to Liferay 7's APIs.
