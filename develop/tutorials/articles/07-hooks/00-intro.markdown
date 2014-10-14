# Hooks [](id=hooks)

Liferay Hooks are the best plugin for customizing Liferay's core features.  If
possible, use hooks whenever you need to override Liferay's core functionality.
It's possible to use Ext plugins for many of the same tasks, but hooks are
hot-deployable and more forward compatible, so we urge you to use them
preferentially. 

In this chapter we'll learn to create hooks and discuss their most common uses. 

Our chapter topics include these items:

- Creating a Hook
- Overriding Web Resources
- Customizing JSPs by Extending the Original
- Customizing Sites and Site Templates with Application Adapters 
- Performing a Custom Action 
- Overriding and Adding Struts Actions 
- Extending and Overriding *portal.properties* 
- Overriding a Portal Service
- Overriding a *Language.properties* file
- Extending the Indexer Post Processor
- Other Hooks

Like portlets, layout templates, and themes, hooks are created and managed using
the Plugins SDK, or the terminal. Let's create and deploy a hook using both
environments. 
