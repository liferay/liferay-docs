# OSGi and Modularity [](id=osgi-and-modularity)

Modularity makes writing software, especially as a team, fun! Here are some
benefits: 

- The framework is lightweight, fast, and secure. 
- The framework uses the OSGi standard. If you have experience using this with
    another project, you can apply your existing knowledge to Liferay. 
- Modules publish services to and consume services from a service registry.
    Service contracts are loosely coupled from service providers and consumers,
    and the registry manages the contracts automatically. 
- Modules' dependencies are managed automatically by the container, dynamically
    (no restart required). 
- The container manages module life cycles dynamically. Modules can be installed,
    started, updated, stopped, and uninstalled while @product@ is running, making
    deployment a snap. 
- OSGi provides a way to hide internal classes so they cannot be seen. 
- Modules are semantically versioned and declare dependencies on specific
    versions of other modules. This allows two applications that depend on
    different versions of the same library to each depend on their own versions
    of the library. 
- You can use your existing developer tools and environment to develop modules. 

There are many benefits to modular software development, and we can only scratch
the surface here. Once you start, you may find it hard to go back to doing
things any other way. 

## A Typical Liferay Application [](id=a-typical-liferay-application)

If you look at a typical application from Liferay's source, you'll generally
find at least four modules: 

- An API module
- A Service (provider) module
- A Test module
- A Web (consumer) module

This is exactly what you'll find for some smaller applications, like the
Mentions application that lets users mention other users with the `@username`
nomenclature in comments, blogs, or other applications. Larger applications like
the Documents and Media library have more modules. In the case of the Documents
and Media library, there are separate modules for different document storage
back-ends. In the case of the Wiki, there are separate modules for different
Wiki engines. 

All of this is for extensibility. If you have a document storage back-end that
@product@ doesn't yet support, you can implement Liferay's document storage API
for your solution by developing a module for it and thus extend Liferay's
Documents and Media library. If there's a Wiki dialect that you like better than
what Liferay's wiki provides, you can write a module for it and extend Liferay's
wiki. 

Are you excited yet? Are you ready to start developing? Here are some resources
for you to learn more. 

## Related Topics [](id=related-topics)

[BLADE CLI](/develop/tutorials/-/knowledge_base/7-0/blade-cli)

[Creating A Liferay Workspace](/develop/tutorials/-/knowledge_base/7-0/creating-a-liferay-workspace)

[Migrating A Legacy Application](/develop/tutorials/-/knowledge_base/7-0/migrating-a-legacy-application)


