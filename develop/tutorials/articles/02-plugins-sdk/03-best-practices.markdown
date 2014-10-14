# Best Practices [](id=best-practices)

The Plugins SDK can house all of your plugin projects enterprise-wide, or you
can have separate Plugins SDK projects for each plugin. For example, if you have
an internal Intranet using Liferay with some custom portlets, you can keep those
portlets and themes in their own Plugins SDK project in your source code
repository. If you also have an external instance of Liferay for your public
Internet web site, you can have a separate Plugins SDK that also has those
projects. Or, you can further separate your projects by having a different
Plugins SDK project for each portlet or theme project. 

It's also possible to use use the Plugins SDK as a simple cross-platform project
generator. Create a plugin project using the Plugins SDK and then copy the
resulting project folder to your IDE of choice. You'll have to manually modify
the Ant scripts, but this process makes it possible to create plugins with the
Plugins SDK while conforming to the strict standards some organizations have for
their Java projects.

If you've read this far and aren't sure you want to develop your plugins using
the Plugins SDK, you also have the option to use Maven.

As an alternative to developing plugins using the SDK, you can leverage the
Apache Maven build management framework. You'll be happy to know that we have
archetypes to help you build various types of plugins including Liferay
portlets, themes, and layout templates. 

To learn more about developing plugins using Maven, see Chapter 9. 
