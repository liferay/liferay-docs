---
header-id: modifying-the-web-xml-with-ext-plugins
---

# Modifying the web.xml with Ext Plugins

[TOC levels=1-4]

A supported use case for using Ext Plugins in @product@ is modifying its
`web.xml` file. Before beginning, make sure you've reviewed the generalized
[Customization with Ext Plugins](/docs/7-2/reference/-/knowledge_base/r/customizing-core-functionality-with-ext)
section.

As an example, you'll create a sample Ext plugin that modifies @product@'s
existing `web.xml file` (e.g., in the `/tomcat-[version]/webapps/ROOT/WEB-INF`
folder). You'll modify the session timeout configuration, which is set to 30
(minutes) by default:

```xml
<session-config>
    <session-timeout>30</session-timeout>
    <cookie-config>
        <http-only>true</http-only>
    </cookie-config>
</session-config>
```

The Ext plugin will update the session timeout to one minute.

1.  Navigate into your Liferay Workspace's `/ext` folder and run the following
    command:

    ```bash
    blade create -t war-core-ext modify-session-timeout
    ```

    Your Ext plugin is generated and now resides in the workspace's `/ext`
    folder with the name you assigned.

2.  In the `ext-web/docroot/WEB-INF` folder, open the `web.xml` file, which was
    generated for you by default.

3. Insert the following logic between the `<web-app>` tags:

    ```xml
    <session-config>
        <session-timeout>1</session-timeout>
        <cookie-config>
            <http-only>true</http-only>
        </cookie-config>
    </session-config>
    ```

Notice that the `<session-timeout>` tag has been updated to `1`.

That's it! Now when your Ext plugin is deployed, your @product@ installation
will timeout after one minute of inactivity. Follow the instructions in the
[Deploy the Plugin](/docs/7-2/reference/-/knowledge_base/r/deploying-an-ext-plugin)
article for help deploying the Ext plugin to your server.
