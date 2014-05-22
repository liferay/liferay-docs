# Customizing Sites and Site Templates with Application Adapters

<!-- The ending example hook can be found here:
https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/plat-fws/application-adap/end/example-hook
-->

Many JSP hooks are often scoped to the portal, but what if you need to customize
specific sites without propagating the customizations throughout the entire
portal? You can! *Application Adapters* are special hooks that let you make
changes at the site level. They are used for overriding JSPs. 

There's a Sample Application Adapter in the [Liferay Plugins Repository](https://github.com/liferay/liferay-plugins/tree/master/hooks/sample-application-adapter-hook).
How do we build an Application Adapter of our own? 

To create an Application Adapter, you need a hook with custom JSPs, and you need
to turn the hook's global custom JSP setting off. You can do this by configuring
your `liferay-hook.xml` with the following directives: 

    <custom-jsp-dir>/META-INF/custom_jsps</custom-jsp-dir>
    <custom-jsp-global>false</custom-jsp-global>

When you deploy your hook, Liferay installs the Application Adapter under the
name of the hook. An Application Adapter hook named *Foo* becomes available to
sites and site templates under the name *Foo Hook*. 

Since Application Adapters are used to override JSPs, let's discuss the perks of
including the original JSP when overriding it. 

## Including an Original JSP

If you override a JSP from the portal, we recommend you include the original
JSP (when possible). 

For example, you can include the original JSP file for global hooks by
referencing the original JSP file from a `<liferay-util:include>` tag in your
new JSP and appending the suffix `.portal.jsp` to the original file's name.
Here's what including your portlet's original `view.jsp` in a global hook looks
like: 

    <%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

    <liferay-util:include page="/html/portlet/yourportlet/view.portal.jsp" />

For Application Adapter hooks, you include the original JSP in your new JSP by
setting the `<liferay-util:include>` tag's `useCustomPage` attribute to `false`,
as below: 

    <%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

    <liferay-util:include
        page="/html/portlet/yourportlet/view.jsp"
        useCustomPage="false"
    />

Also notice the view JSP is specified as `view.jsp`, *not* `view.portal.jsp` as
for global hooks. In the next section, you'll create and test an application
adapter. 

## Creating an Application Adapter

In this tutorial, you'll create your own Application Adapter hook. It will
override the Navigation portlet's `view.jsp`, while including the original
Navigation portlet's JSP with some custom text after its contents. 

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

2. Create a `docroot/META-INF/custom_jsps/html/portlet/navigation` directory in
   your hook project, create a new `view.jsp` file in this directory, and
   insert code to include the original JSP:

        <%@ taglib uri="http://liferay.com/tld/util" 
        prefix="liferay-util" %>

        <liferay-util:include
            page="/html/portlet/navigation/view.jsp" 
            useCustomPage="false"
        />

        <p>
        This was modified by the Example Application Adapter.
        </p>

3. Deploy your Application Adapter hook plugin. 

4. In your web browser, navigate to the Liferay site where you'll use the
   Application Adapter. 

5. Select *Admin* &rarr; *Configuration* to access the Site Settings section of
   the Site Administration interface. From the *Application Adapter* field's
   drop-down selector menu, select *Example*. Then click *Save*. 

    ![Figure 11.3: Your *Application Adapters* are easily accessible from the Site Settings section of the Site Administration interface.](../../images/06-hooks-select-site-app-adapter.png)

6. Navigate to your site's pages, add the Navigation portlet to a page, and
   make sure that the modification message from your Application Adapter hook
   plugin's `view.jsp` file is displayed there.

7. Navigate to a different site's Navigation portlet to verify that only the
   content of the portlet's *original* `view.jsp` file displays. 

Using Application Adapter hook plugins to override Liferay's core functionality
at the site scope is easy!

You can also apply Application Adapters to Site Templates. 

Suppose you want to make an Enterprise Resource Planning (ERP) solution for a
company's departments. Your ERP solution requires an extension of Liferay's Wiki
portlet so you implement that extension as an Application Adapter. Then you
incorporate the Application Adapter in a Site Template (named *ERP site*) for
the company's ERP sites. The company's administrative user creates the sites by
going to *Control Panel* &rarr; *Sites* and adding sites based on the "ERP site"
template. The added sites include your Application Adapter automatically. 

In this tutorial, you learned that when overriding a JSP, it's recommended to
include the original JSP. Then, you created an Application Adapter for yourself
and tried it out in Liferay Portal.
