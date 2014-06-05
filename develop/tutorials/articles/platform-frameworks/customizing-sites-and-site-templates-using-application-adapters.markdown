# Customizing Sites and Site Templates with Application Adapters

<!-- The ending example hook can be found here:
https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/plat-fws/application-adap/end/example-hook
-->

Many JSP hooks are often scoped to the portal, but what if you need to customize
specific sites without propagating the customizations throughout the entire
portal? You can! *Application Adapters* are special hooks that let you make
changes at the site level. They are used for overriding JSPs. 

There's a Sample Application Adapter in the [Liferay Plugins Repository](https://github.com/liferay/liferay-plugins/tree/master/hooks/sample-application-adapter-hook).
Are you ready to build an application adapter of our own? Move on to the next
section to learn how!

## Creating an Application Adapter

In this tutorial, you'll create your own Application Adapter hook. It will
override a portlet's `view.jsp`, while including the portlet's original JSP with
some custom text after its contents. 

Here's how to do it: 

1. Modify your hook's `liferay-hook.xml` to specify the location of your custom
   JSP and set the global custom JSP setting to `false`: 

        <hook>
            <custom-jsp-dir>/META-INF/custom_jsps</custom-jsp-dir>
            <custom-jsp-global>false</custom-jsp-global>
        </hook>

    You'll need to create the `docroot/META-INF/custom_jsps` folder in your
    hook, if it does not exist. You can do this by either manually creating the
    folder, or navigating to your `liferay-hook.xml` file's *Overview* tab and
    selecting *Custom JSPs*. Then in the menu that appears, check the *Customize
    Liferay Portal JSPs* and click the button with three yellow diamonds. 

2. Create a `docroot/META-INF/custom_jsps/html/portlet/yourportlet` directory in
   your hook project and create a new `view.jsp` file in this directory. It's
   recommended to include the original JSP (if possible) when overriding a JSP.
   The following code snippet includes the original JSP and adds a custom
   message to the bottom of the portlet you're modifying: 

        <%@ taglib uri="http://liferay.com/tld/util" 
        prefix="liferay-util" %>

        <liferay-util:include
            page="/html/portlet/yourportlet/view.jsp" 
            useCustomPage="false"
        />

        <p>
        This was modified by your Application Adapter.
        </p>

    Within the `<liferay-util:include>` tag, you must specify the `view.jsp`
    path you're extending, and set the `useCustomPage` attribute to false. 

    **Note:** When extending an original JSP in a global hook, you must specify
    your `view.jsp` as `view.portal.jsp` within the `<liferay-util:include>`
    tag. However, when using an application adapter hook, you specify the
    `view.jsp` normally and set the `useCustomPage` attribute to false. 

3. Deploy your application adapter hook plugin. Liferay installs the application
   adapter under the name of the hook. An application adapter hook named
   *Example Hook* becomes available to sites and site templates under the name
   *Example*.

4. In your web browser, navigate to the Liferay site where you'll use the
   application adapter. 

5. Select *Admin* &rarr; *Configuration* &rarr; *Site Settings* of the Site
   Administration interface. From the Application Adapter field's drop-down
   selector menu, select your application adapter. Then click *Save*. 

    ![Figure 11.3: Your application adapters are easily accessible from the Site Settings section of the Site Administration interface.](../../images/06-hooks-select-site-app-adapter.png)

6. Navigate to your site's pages, add the portlet your hook is modifying to a
   page, and make sure that the modification you made from your Application
   Adapter hook plugin's `view.jsp` file is displayed there. 

7. Navigate to a different site and add the same portlet to verify that only the
   content of the portlet's *original* `view.jsp` file displays. 

Using application adapter hook plugins to override Liferay's core functionality
at the site scope is easy! 

You can also apply application adapters to site templates. 

Suppose you want to make an Enterprise Resource Planning (ERP) solution for a
company's departments. Your ERP solution requires an extension of Liferay's Wiki
portlet so you implement that extension as an application adapter. Then, you
incorporate the application adapter in a site template (named *ERP site*) for
the company's ERP sites. The company's administrative user creates the sites by
going to *Control Panel* &rarr; *Sites* and adding sites based on the "ERP site"
template. The added sites include your application adapter automatically. 

In this tutorial, you learned that application adapters are used to scope
customizations to specific sites in your portal instance. Then you created one
for yourself and learned best practices along the way. 
