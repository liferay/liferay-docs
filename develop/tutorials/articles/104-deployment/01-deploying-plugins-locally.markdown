# Deploying Plugins to a Local Portal Instance [](id=deploying-plugins-to-a-local-portal-instance)

Liferay IDE provides multiple options for deploying plugins: you can drag and
drop your project onto the server or right click the server and select *Add and
Remove...*. It's almost as easy using an Ant target directly from the Plugins
SDK. Simply open a terminal window, navigate to your project's directory (e.g.,
`portlets/[portlet name]`) in your Plugins SDK and enter

    ant deploy

A BUILD SUCCESSFUL message indicates that Ant built your portlet's WAR file and
that it's now being deployed. If you switch to the terminal window running
Liferay, within a few seconds you should see a message, like the following message, indicating that your plugin is available to use in the portal:

    1 portlet for [portlet name] is available for use

If you don't see such a message, double-check your plugin's configuration. 

Adding newly deployed portlet plugins from within Liferay Portal is easy. In
your web browser, log in to the portal. Click on the *Add* icon on the left side
of the page and select the *Applications* tab. Select your portlet's category
(*Sample* is the default category), and then click *Add* next to *[portlet
title]*. Your portlet appears in the page.

## Related Topics [](id=related-topics)

[Deploying Liferay Plugins with Maven](/develop/tutorials/-/knowledge_base/6-2/deploying-liferay-plugins-with-maven)

[Using Liferay IDE's Remote Server Adapter](/develop/tutorials/-/knowledge_base/6-2/using-liferay-ides-remote-server-adapter)

