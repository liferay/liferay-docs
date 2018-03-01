# Installing a Server in Liferay @ide@ [](id=installing-a-server-in-liferay-ide)

Installing a server in Liferay @ide@ is easy. In just a few steps you'll have
your server up and running. Follow these steps to install your server:

1.  In the Servers view, click the *No Servers are available* link. If you
    already have a server installed, you can install a new server by
    right-clicking in the Servers view and selecting *New* &rarr; *Server*.
    This brings up a wizard that walks you through the process of defining a new
    server.

2.  Select the type of server you would like to create from the list of
    available options. For a standard server, open the *Liferay, Inc.* folder
    and select the *Liferay 7.x* option. You can change the server name to
    something more unique too; this is the name displayed in the Servers view.
    Then click *Next*. If you're creating a server for the first time, continue
    to the next step.

    ![Figure 1: Choose the type of server you want to create.](../../../images/define-new-server.png)

    **Note:** If you've already configured previous Liferay servers, you'll be
    provided the *Server runtime environment* field, which lets you choose
    previously configured runtime environments. If you want to re-add an
    existing server, select one from the dropdown menu. You can also add a new
    server by selecting *Add*, or you can edit existing servers by selecting
    *Configure runtime environments*. Once you've configured the server runtime
    environment, select *Finish*. If you selected an existing server, your
    server installation is finished; you can skip steps 3-5.

3.  Enter a name for your server. This is the name for the @product@ runtime
    configuration used by @ide@. This is not the display name used in the
    Servers tab.

4.  Browse to the installation folder of the @product@ bundle. For example,
    `C:\liferay-ce-portal-7.1-m1\tomcat-8.0.32`.

    ![Figure 2: Specify the installation folder of the bundle.](../../../images/specify-directory.png)

5.  Select a runtime JRE and click *Finish*. Your new server appears under the
    Servers view.

    ![Figure 3: Your new server appears under the *Servers* view.](../../../images/new-server-added.png)

Your server is now available in Liferay @ide@!

For reference, here's how the @ide@ server buttons work with your @product@
instance:

- *Start* (![Start Server](../../../images/icon-start-server.png)): Starts the
  server.
- *Stop* (![Stop Server](../../../images/icon-stop-server.png)): Stops the
  the server.
- *Debug* (![Debug Server](../../../images/icon-debug-server.png)): Starts the
  server in debug mode. For more information on debugging in @ide@, see the
  [Debugging @product@ source in Liferay @ide@](/develop/tutorials/-/knowledge_base/7-1/debugging-product-source-liferay-ide)
  article.

Now you're ready to use your server in Liferay @ide@!
