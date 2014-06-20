# Customizing Liferay Portal [](id=customizing-liferay-portal-lp-6-2-develop-tutorial)

Liferay Hooks are the best type of plugin for customizing Liferay's core
features. If possible, use hooks whenever you need to override Liferay's core
functionality. It's possible to use Ext plugins for many of the same tasks, but
hooks are hot-deployable and more forward compatible, so we urge you to use them
preferentially. 

In this section of tutorials, you'll learn how to create hooks and you'll
explore their most common uses.

## Next Steps [](id=next-steps-hooks-lp-6-2-develop-tutorial)

Take your pick from a host of hook plugin tutorials: 

<!--
Creating a Hook
-->

[Overriding Web Resources](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/overriding-web-resources-lp-6-2-develop-tutorial)

[Customizing JSPs by Extending the Original](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/customizing-jsps-by-extending-the-original-lp-6-2-develop-tutorial)

[Performing a Custom Action](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/performing-a-custom-action-using-a-hook-lp-6-2-develop-tutorial)

<!--
Customizing Sites and Site Templates with Application Adapters 
Overriding and Adding Struts Actions 
Extending and Overriding *portal.properties* 
Overriding a Portal Service
Overriding a *Language.properties* file
Extending the Indexer Post Processor
Supporting Right-to-Left Languages in Plugins
-->

<!-- Consider adding this text somewhere in the intro once we have more IDE
stuff for hooks. Jim

As with portlets, layout templates, and themes, the easiest way to create and
manage hooks is via Liferay IDE or Developer Studio. However, if you don't want
to use an IDE, you can use the terminal. This section of tutorials demonstrates
how to create and deploy a hook using both Liferay IDE and the terminal.
-->
