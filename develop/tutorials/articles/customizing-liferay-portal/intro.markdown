# Customizing Liferay Portal

Liferay Hooks are the best plugin for customizing Liferay's core features. If
possible, use hooks whenever you need to override Liferay's core functionality.
It's possible to use Ext plugins for many of the same tasks, but hooks are
hot-deployable and more forward compatible, so we urge you to use them
preferentially. 

In this section of tutorials, we'll learn how to create hooks and we'll explore
their most common uses.

We'll cover the following topics:

- Creating a Hook
- [Overriding Web Resources](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/overriding-web-resources-lp-6-2-develop-tutorial)
- Customizing JSPs by Extending the Original
- Customizing Sites and Site Templates with Application Adapters 
- Performing a Custom Action 
- Overriding and Adding Struts Actions 
- Extending and Overriding *portal.properties* 
- Overriding a Portal Service
- Overriding a *Language.properties* file
- Extending the Indexer Post Processor
- Supporting Right-to-Left Languages in Plugins

As with portlets, layout templates, and themes, the easiest way to create and
manage hooks is via Liferay IDE or Developer Studio. However, if you don't want
to use an IDE, you can use the terminal. We'll demonstrate how to create and
deploy a hook using both Liferay IDE and the terminal.

