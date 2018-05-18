# Modularizing Plugins [](id=modularizing-plugins)

As described in
[Benefits of Liferay 7 for Liferay 6 Developers](/develop/tutorials/-/knowledge_base/7-1/benefits-of-liferay-7-for-liferay-6-developers),
applications that comprise OSGi modules offer considerable advantages over
monolithic applications. 

The main benefit is that modular development practices structure code in ways
that reduce maintenance costs. These practices involve, for example, defining
contracts (such as APIs) more clearly, hiding internal classes, and handling
dependencies more carefully. Related to this, Java package dependencies are
explicitly listed within a module. Modules run only when all their dependencies
are met. This eliminates many obscure run time errors. 

Splitting large applications into small independent modules lets you focus on
smaller release cycles for those modules. Individual modules can be updated
independently of the others. For instance, you might fix a JSP's security issue
in an application's web (client) module. The issue only affects that
module--none of the application's other modules need change. 

The scenarios described below can help you decide whether to convert an
application to modules. 

**When not to convert?**

-   You have a portlet that's JSR-168/286 compatible and you still want to be
    able to deploy it to another portlet container. In this case, it's best to
    stay with the traditional WAR model. (To eliminate this reason for not
    converting, Liferay is discussing with other vendors the possibility of
    making modular portlets a standard.) 
-   You're using a complex web framework that is heavily tied to the Java EE
    programming model and the amount of effort necessary to make it work with
    OSGi is more than you feel is necessary or warranted. 
-   You want to minimize effort to get your application working on
    @product-ver@. 

**When to convert?**

-   You have a very large application with many lines of code. If
    you have lots of developers making changes, separating the code into
    modules can make it easier and faster to get releases out. 
-   Your application has reusable parts that you want to consume outside of it.
    For instance, your application has business logic that you're reusing in
    different projects. Modules let you consume their services from other
    modules. 
-   In general, you want to start reaping the benefits of modular development. 

You can now make an informed decision on whether to stick with your
[upgraded traditional application](/develop/tutorials/-/knowledge_base/7-1/upgrading-plugins-to-liferay-7)
as is or modularize it to leverage modularity. 
