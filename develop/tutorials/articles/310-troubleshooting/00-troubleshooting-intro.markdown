# Troubleshooting FAQ [](id=troubleshooting)

When coding on any platform, you can sometimes run into issues that have no
clear resolution. This can be particularly frustrating. If you have issues
building, deploying, or running modules, you want to resolve them fast. These
frequently asked questions and answers help you troubleshoot problems that arise
based on the underlying OSGi platform, and then correct them. 

Here are the troubleshooting sections:

-   [Modules](#troubleshooting-modules)
-   [Services and Components](#troubleshooting-services-and-components)

## Modules [](id=troubleshooting-modules)

### How can I configure dependencies on @product@ artifacts? [](id=how-can-i-configure-dependencies-on-product-artifacts)
 
See
[Configuring Dependencies](/develop/tutorials/-/knowledge_base/7-0/configuring-dependencies). 

### What are optional package imports and how can I specify them? [](id=what-are-optional-package-imports-and-how-can-i-specify-them)

When developing @product@ modules, you can declare *optional* package imports.
An optional package import is one your module can use if it's available, but can
still function without it.
[Specifying optional package imports](/develop/tutorials/-/knowledge_base/7-0/declaring-optional-import-package-requirements)
is straightforward. 

### How can I connect to a JNDI data source from my module? [](id=how-can-i-connect-to-a-jndi-data-source-from-my-module)

Connecting to an application server's JNDI data sources from @product@'s OSGi
environment is almost the same as connecting to them from the Java EE
environment. In an OSGi environment, the only difference is that you must
[use @product@'s class loader to load the application server's JNDI classes](/develop/tutorials/-/knowledge_base/7-0/connecting-to-data-sources-using-jndi). 

### How can I make sure my module works? [](id=how-can-i-make-sure-my-module-works)

[The Testing tutorials demonstrate several ways to test @product@ modules](/develop/tutorials/-/knowledge_base/7-0/testing):

- Unit testing
- Integration testing
- Functional testing  

### My module has an unresolved requirement. What can I do? [](id=my-module-has-an-unresolved-requirement-what-can-i-do)

If one of your bundles imports a package that no other bundle in the Liferay
OSGi runtime exports, @product@ reports an unresolved requirement:

    ! could not resolve the bundles: ...
    Unresolved requirement: Import-Package: ...
    ...
    Unresolved requirement: Require-Capability ...

To satisfy the requirement,
[find a module that provides the capability, add it to your build file's dependencies, and deploy it](/develop/tutorials/-/knowledge_base/7-0/resolving-bundle-requirements). 

### An IllegalContextNameException reports that my bundle's context name does not follow Bundle-SymbolicName syntax. How can I fix the context name? [](id=an-illegalcontextnameexception-reports-that-my-bundles-context-name-does-no)

[Adjust the `Bundle-SymbolicName` to adhere to the syntax](/develop/tutorials/-/knowledge_base/7-0/resolving-bundle-symbolicname-syntax-issues). 

### Why aren't my module's JavaScript and CSS changes showing? [](id=why-arent-my-modules-javascript-and-css-changes-showing)

[Incorrect component properties or stale browser cache can prevent JavaScript and CSS changes from showing](/develop/tutorials/-/knowledge_base/7-0/why-arent-my-modules-javascript-and-css-changes-showing). 

### Why aren't my fragment's JSP overrides showing? [](id=why-arent-my-fragments-jsp-overrides-showing)

[Make sure your `Fragment-Host`'s bundle version is compatible with the host's bundle version](/develop/tutorials/-/knowledge_base/7-0/why-arent-jsp-overrides-i-made-using-fragments-showing). 

### The application server and database started, but @product@ failed to connect to the database. What happened and how can I fix this? [](id=the-application-server-and-database-started-but-product-failed-to-connect-t)

@product@ initialization can fail while attempting to connect to a database server that isn't ready.
[Configuring @product@ startup to retry JDBC connections](/develop/tutorials/-/knowledge_base/7-0/portal-failed-to-initialize-because-the-database-wasnt-ready)
facilitates connecting @product@ to databases. 

### How can I adjust my module's logging? [](id=how-can-i-adjust-my-modules-logging)

See
[Adjusting Module Logging](/develop/tutorials/-/knowledge_base/7-0/adjusting-module-logging). 

### How can I implement logging in my module or plugin? [](id=how-can-i-implement-logging-in-my-module-or-plugin)

[Use Simple Logging Facade for Java \(SLF4J\) to log messages](/develop/tutorials/-/knowledge_base/7-0/implementing-logging).

## Services and Components [](id=troubleshooting-services-and-components)

### How can I detect unresolved OSGi components? [](id=how-can-i-detect-unresolved-osgi-components)

@product@ module components that use Service Builder use Dependency Manager (DM)
and most other @product@ module components use Declarative Services (DS).
[Gogo shell commands and tools help you find and inspect unsatisfied component references for DM and DS components](/develop/tutorials/-/knowledge_base/7-0/detecting-unresolved-osgi-components). 

### What is the safest way to call non-OSGi code that uses OSGi services? [](id=what-is-the-safest-way-to-call-non-osgi-code-that-uses-osgi-services)

See
[Calling Non-OSGi Code that Uses OSGi Services](/develop/tutorials/-/knowledge_base/7-0/calling-non-osgi-code-that-uses-osgi-services). 

### How can I use files to configure components? [](id=how-can-i-use-files-to-configure-modules)

See
[Using Files to Configure Module Components](/develop/tutorials/-/knowledge_base/7-0/using-files-to-configure-product-modules). 

### How can I use OSGi services from Ext Plugins? [](id=how-can-i-use-osgi-services-from-ext-plugins)

[The registry API lets Ext Plugins use OSGi services ](/develop/tutorials/-/knowledge_base/7-0/using-osgi-services-from-ext-plugins). 
 
