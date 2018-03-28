# OSGi and Modularity for Liferay Portal 6 Developers [](id=osgi-and-modularity-for-liferay-6-developers)

To create a powerful, reliable platform for developing modular applications,
Liferay sought best-of-breed standards-based frameworks and technologies.
It was imperative not only to meet demands for enterprise digital experiences
but also to offer developers, both experienced with Liferay and new to Liferay,
a clear and elegant way to create apps.

Here were some of the key goals:

- Allow breaking down a large system into smaller pieces of code, whose
boundaries and relationships could be clearly defined.

- Explicitly differentiate public APIs from private APIs.

- Facilitate extensibility of existing code.

- Modernize the development environment, leveraging more state-of-the-art tools
to provide a great developer experience.

It wasn't long before Liferay discovered that OSGi and its supporting
tools/technologies fit the bill!

In this tutorial, you'll learn how @product-ver@ uses OSGi to meet these objectives.
And equally important, you'll find out how easy and fun modular development can
be.

Here are the topics you'll dig into:

1.  [Modules as an Improvement over Traditional Plugins](/develop/tutorials/-/knowledge_base/7-0/osgi-and-modularity-for-liferay-6-developers#modules-as-an-improvement-over-traditional-plugins):
    Development and customization of applications for Liferay has been done
    traditionally in WAR-style plugins (Portlet, Hook, Ext, and Web). In
    @product-ver@, traditional Liferay plugins can be replaced with (or can be
    automatically converted to) modules. You'll see the similarities and
    differences of plugins and modules, and you'll learn the benefits of using
    modules.

2.  [Leveraging Dependencies](/develop/tutorials/-/knowledge_base/7-0/osgi-and-modularity-for-liferay-6-developers#leveraging-dependencies):
    In @product-ver@, you can both declare dependencies among modules and
    combine modules to create applications. Since leveraging dependencies
    provides huge benefits, it's important to devote a large section for it.

3.  [OSGi Services and Dependency Injection](/develop/tutorials/-/knowledge_base/7-0/osgi-and-modularity-for-liferay-6-developers#osgi-services-and-dependency-injection-with-declarative-services):
    OSGi provides a powerful concept called OSGi Services (also known as
    microservices). OSGi's Declarative Services standard provides a clean way to
    inject dependencies in a dynamic environment. This is similar to Spring DI,
    except the changes happen while the system is running. It also offers an
    elegant extensibility model that @product-ver@ leverages extensively.

4.  [Dynamic Deployment](/develop/tutorials/-/knowledge_base/7-0/osgi-and-modularity-for-liferay-6-developers#dynamic-deployment):
    Module deployment is managed by @product-ver@ (not the application server).
    This section demonstrates how to use dynamic deployment for better control
    and efficiency.

After investigating these topics, you'll get hands-on experience creating and
deploying an OSGi module. 

Let's start with learning how modules are better than traditional plugins.
