# Modifying the web.xml with Ext Plugins [](id=modifying-the-web-xml-with-ext-plugins)

A supported use case for using Ext Plugins in Liferay DXP is modifying Liferay
Digital Enterprise's `web.xml` file. Before beginning, make sure you've reviewed
the generalized
[Customization with Ext Plugins](/documentation/7.0/develop/tutorials/-/official_documentation/tutorials/advanced-customization-with-ext-plugins)
tutorial.

As an example, you'll create a sample Ext plugin that modifies your Liferay
Digital Enterprise's existing `web.xml file` (e.g., in the
`/tomcat-[version]/webapps/ROOT/WEB-INF` folder). You'll modify the session
timeout configuration, which is set to 30 (minutes) by default:

    <session-config>
        <session-timeout>30</session-timeout>
        <cookie-config>
            <http-only>true</http-only>
        </cookie-config>
    </session-config>

The Ext plugin will update the session timeout to one minute.

1.  Navigate into your Plugins SDK's `/ext` folder and run the following
    command:

        create.[bat|sh]  modify-session-timeout "Modify Session Timeout"

    Your Ext plugin is generated and now resides in the Plugins SDK's `/ext`
    folder with the name you assigned followed by `-ext` (e.g.,
    `modify-session-timeout-ext`).

2.  In the `docroot/WEB-INF/ext-web/docroot/WEB-INF` folder, open the `web.xml`
    file, which was generated for you by default.

3. Insert the following logic between the `<web-app>` tags:

        <session-config>
            <session-timeout>1</session-timeout>
            <cookie-config>
                <http-only>true</http-only>
            </cookie-config>
        </session-config>

Notice that the `<session-timeout>` tag has been updated to `1`.

That's it! Now when your Ext plugin is deployed, your Liferay Digital Enterprise
installation will timeout after one minute of inactivity. Follow the
instructions in the
[Deploy the Plugin](/documentation/7.0/develop/tutorials/-/official_documentation/tutorials/advanced-customization-with-ext-plugins#deploy-the-plugin)
section for help deploying the Ext plugin to your server.
