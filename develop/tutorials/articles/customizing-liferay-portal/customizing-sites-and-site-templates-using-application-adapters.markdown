# Customizing Sites and Site Templates with Application Adapters [](id=customizing-sites-and-site-templates-with-application-adapters)

<!-- The ending example hook can be found here:
https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/plat-fws/application-adap/end/example-hook
-->

JSP hooks are often scoped to the portal, but what if you need to customize
specific sites without propagating the customizations throughout the entire
portal? You can! *Application Adapters* are special hooks that let you make
changes at the site level. You use them to override JSPs. 

There's a sample Application Adapter in the
[Liferay Plugins Repository](https://github.com/liferay/liferay-plugins/tree/6.2.x/hooks/sample-application-adapter-hook).
Are you ready to build an Application Adapter of our own? In this tutorial,
you'll create your own Application Adapter hook.

Here's how to do it: 

1. [Create a hook plugin](/develop/tutorials/-/knowledge_base/6-2/creating-a-hook-project-in-the-plugins-sdk). 

2. Modify your hook's `liferay-hook.xml` to specify the location of your custom
   JSP and set the global custom JSP setting to `false`: 

        <hook>
            <custom-jsp-dir>/META-INF/custom_jsps</custom-jsp-dir>
            <custom-jsp-global>false</custom-jsp-global>
        </hook>

    You'll need to create the `docroot/META-INF/custom_jsps` folder in your
    hook if it does not exist. You can do this by creating the folder manually
    or navigating to your `liferay-hook.xml` file's *Overview* tab and selecting
    *Custom JSPs*. Then in the menu that appears, check the *Customize Liferay
    Portal JSPs* and click the button with three yellow diamonds. 

3.  In your project's `docroot/META-INF/custom_jsps/html/portlet/` folder,
    create a folder with the same name as the portlet you're overriding. In
    the new folder, create a JSP file with the same name as the JSP file you're
    overriding. In this new JSP, you'll implement application customizations. 

    It's recommended to include the original JSP (if possible) when overriding a
    JSP. You can include an original JSP by using a `<liferay-util:include>`
    tag. For example, if you wanted to include an original JSP named `view.jsp`
    from a portlet named `yourportlet`, you could add a directive to include the
    `liferay-util` taglib and specify a `<liferay-util:include>` tag to include
    the original JSP. You can include content before or after the original
    content The following example code snippet includes an original JSP named
    `view.jsp` and adds custom content below it:

        <%@ taglib uri="http://liferay.com/tld/util" 
        prefix="liferay-util" %>

        <liferay-util:include
            page="/html/portlet/yourportlet/view.jsp" 
            useCustomPage="false"
        />

        <!-- Custom Content -->

        <p>
        This was modified by your Application Adapter.
        </p>

    Within the `<liferay-util:include>` tag, you must specify the path of the
    original JSP you're extending and set the `useCustomPage` attribute to
    `false`. 

    +$$$

    **Note:** When extending an original JSP in a global hook, you must end the
    JSP file's name with the suffix `.portal.jsp` within the
    `<liferay-util:include>` tag. However, when using an application adapter
    hook, you specify the the JSP normally and set the `useCustomPage` attribute
    to `false`. 

    $$$

4. Deploy your application adapter hook plugin. Liferay installs the application
   adapter under the name of the hook. 

5. In your web browser, navigate to the Liferay site where you'll use the
   application adapter. 

6. Select *Admin* &rarr; *Configuration* &rarr; *Site Settings* of the Site
   Administration interface. From the Application Adapter field's drop-down
   selector menu, select your application adapter. Then click *Save*. 

    ![Figure 1: Your application adapters are easily accessible from the Site Settings section of the Site Administration interface.](../../images/06-hooks-select-site-app-adapter.png)

7. Navigate to your site's pages, add the portlet your hook is modifying to a
   page, and make sure that the modification you made from your Application
   Adapter hook plugin's JSP file is displayed there. 

8. Navigate to a different site and add the same portlet to verify that only the
   content of the portlet's *original* JSP file displays. 

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

