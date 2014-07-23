# Customizing and Extending Functionality with Hooks [](id=customize-and-extend-functionality-hooks-liferay-portal-6-2-dev-guide-en)

Liferay Hooks are the best plugin for customizing Liferay's core features. If
possible, use hooks whenever you need to override Liferay's core functionality.
It's possible to use Ext plugins for many of the same tasks, but hooks are
hot-deployable and more forward compatible, so we urge you to use them
preferentially. 

In this chapter, we'll learn how to create hooks and we'll explore their most
common uses.

We'll cover the following topics:

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
- Supporting Right-to-Left Languages in Plugins
- Other Hooks

As with portlets, layout templates, and themes, the easiest way to create and
manage hooks is via Liferay IDE or Developer Studio. However, if you don't want
to use an IDE, you can use the terminal. We'll demonstrate how to create and
deploy a hook using both Liferay IDE and the terminal.

## Creating a Hook [](id=creating-a-hook-liferay-portal-6-2-dev-guide-10-en)

Regardless of whether you use Liferay IDE or your terminal to create hooks,
hooks projects are stored in the Plugins SDK's `hooks` directory. 

***Using Liferay IDE:***

1.  Go to *File* &rarr; *New* &rarr; *Liferay Project*.

2.  Assign a project name and display name. To demonstrate, we'll use
    *example-hook* and *Example* for the project name and display name,
    respectively. Notice that upon entering *example-hook* as the project
    name, the wizard conveniently inserts *Example* in grayed-out text as the
    plugin's default display name. The wizard derives the default display name
    from the project name, starts it in upper-case, and leaves off the plugin
    type suffix *Hook* because the plugin type is automatically appended to
    the display name in Liferay Portal. The IDE saves the you from repetitively
    appending the plugin type to the display name; in fact, the IDE ignores any
    plugin type suffix if you happen to append it to the display name.

    Enter the following values for the project name and display name:

    - **Project name:** *example-hook*
    - **Display name:** *Example*

3.  Select the build type, Plugins SDK, and Liferay runtime. 

    If you select the Maven build type, you'll be prompted to enter an artifact
    version, group ID, and active profile for your project. See [Developing
    Plugins Using 
    Maven](http://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/developing-plugins-using-maven-liferay-portal-6-2-dev-guide-02-en)
    for more information. Otherwise, select the Ant build type, a Plugins SDK
    and a Liferay runtime.  

    For this demonstration, make the following selections: 

    - **Build type:** *Ant*
    - **Plugins SDK:** `[a configured Plugins SDK]`
    - **Liferay runtime:** `[a configured Liferay runtime]`
    
    For more information, see sections *Setting Up the Liferay Plugins SDK* and *Liferay Portal
    Runtime and Server Setup* from  
    [Developing Apps with Liferay IDE](http://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/developing-apps-with-liferay-ide-liferay-portal-6-2-dev-guide-02-en) 

4.  Select the *Hook* Plugin Type. 

5.  Click *Finish*. 

Figure 11.1 shows the values you specified for the hook plugin.

![Figure 11.1: Creating a hook plugin is easy with Liferay IDE. Just click *File* &rarr; *New* &rarr; *Liferay Project*, enter a project name and display name, select a build type, Plugins SDK, and Liferay Runtime, select *Hook*, and then click *Finish*.](../../images/hooks-create-hook-project-with-sdk.png)

The Plugins SDK automatically names the hook by appending "-hook" to the project
name. With Liferay IDE, you can create a hook in a completely new plugin project
or create a hook in an existing plugin project. Use *File* &rarr; *New* &rarr;
*Liferay Project* to create a new plugin project and *File* &rarr; *New* &rarr;
*Liferay Hook* to create a hook in an existing plugin project.

***Using the terminal:*** Navigate to your Plugins SDK directory in a terminal
and enter the appropriate command for your operating system:

1.  In Linux and Mac OS X, enter

        ./create.sh example "Example"

2.  In Windows, enter

        create.bat example "Example"

A BUILD SUCCESSFUL message from Ant tells you there's a new folder named
`example-hook` inside the Plugins SDK's `hooks` folder. The Plugins SDK
automatically named the hook by appending "-hook" to the project name. 

Now that you've created a hook, let's go ahead and deploy it.

### Deploying the Hook [](id=deploying-the-hook-liferay-portal-6-2-dev-guide-10-en)

***Using Liferay IDE:*** Click and drag your hook project onto your server.
Upon deployment, your server displays messages indicating that your hook was
read, registered and is now available for use. 

    Reading plugin package for example-hook
    Registering hook for example-hook
    Hook for example-hook is available for use

Voila! Your hook deployed.

---

 ![Note](../../images/tip-pen-paper.png) **Note:** If the Liferay server
 prints the following message to your console, the *Marketplace Portlet* and 
 *Portal Compatibility Hook* must not already be deployed on your server.

    Plugin example-hook requires marketplace-portlet, portal-compat-hook

 For Liferay 6.2.0 CE GA1, you can fork and clone Liferay's *liferay-plugins*
 project from GitHub, checkout the respective branch and/or tag, and deploy
 each plugin. You can install the Plugins SDK in Liferay IDE and import each
 plugin and deploy them. Here is information on each of the plugins: 

- *Marketplace Portlet* (`marketplace-portlet`) - is available at
`liferay-plugins/portlets/marketplace-portlet`.
- *Portal Compatibility Hook* (`portal-compat-hook`) - is available at 
`liferay-plugins/hooks/portal-compat-hook`.

---

If you ever need to redeploy your plugin while in Liferay IDE, right-click
your plugin's icon located underneath your server and select *Redeploy*. 

***Using the terminal:*** Open a terminal window in your `hooks/example-hook`
directory and enter

    ant deploy

A BUILD SUCCESSFUL message indicates your hook is now being deployed. If you
switch to the terminal window running Liferay, in a few seconds you
should see the message "Hook for example-hook is available for use". 

 ![Note](../../images/tip-pen-paper.png) **Note:** When we created portlets and
 themes, they were fully functional upon deployment. Hooks aren't like that
 because they're Liferay customizations. The default customization is the
 original implementation! 

### Anatomy of the Hook [](id=anatomy-of-the-hook-liferay-portal-6-2-dev-guide-10-en)

To make your hook useful, you need to customize something in Liferay. You begin
by mirroring the structure of Liferay's code that you plan to customize. A hook
plugin is built to contain this: 

- `example-hook/`
    - `docroot/WEB-INF/src/`
    - `docroot/`
        - `META-INF/`
            - `MANIFEST.MF`
        - `WEB-INF/`
            - `lib/`
            - `liferay-hook.xml`
            - `liferay-plugin-package.properties`
            - `web.xml`
    - `build.xml`

In Liferay IDE's *Package Explorer*, here's what the hook structure looks
like:

![Figure 11.2: In Liferay IDE, the folder structure of a newly created hook plugin looks like this.](../../images/hooks-file-structure.png)

The particular files you'll work on depend on the Liferay features you're
overriding with your hook. We'll start by making one of the most common hook
plugin customizations: a customization of Liferay's web resources. 

## Overriding Web Resources [](id=overriding-web-resources-with-hook-liferay-portal-6-2-dev-guide-en)

Hooks are commonly used to override web resources, found in `portal-web` in
Liferay's source. You can use a hook to override JSP files, JSPF files,
JavaScript files, CSS files, or images. 

---

 ![Important](../../images/tip-pen-paper.png) **Important:** Some resources have
 additional requisites:

 - ***JSPF:*** Changes won't take effect unless you modify the JSP that
   includes it. 
 - ***CSS:*** When modifying a CSS file imported by another CSS file, the
 changes won't take effect unless you modify the parent CSS file (usually
 `main.css`). 

---

Replacing a portal JSP is a simple task with hooks. Let's create and deploy a
hook to modify your portal's *Terms of Use* page. 

1.  Use the hook project we created earlier or create a new hook project. 

2.  Open the `liferay-hook.xml` file from your project's `docroot/WEB-INF`
    folder in Liferay IDE. By default, it opens in *Overview* mode. This mode,
    gives you a graphical user interface for developing your hook. You can
    toggle between overview mode and source mode via their respective tabs for
    the `liferay-hook.xml` file's editor. 

3.  Select the *Custom JSPs* folder from the outline to bring up the custom JSP
    options. Select the checkbox *Customize Liferay Portal JSPs* and create
    the default custom JSP folder `/META-INF/custom_jsps`, by clicking the icon
    that has the three yellow diamonds. 

    ![Figure 7.3: Liferay IDE lets you specify a folder for the custom JSPs you're developing. Create the folder by clicking the icon that has the three yellow diamonds.](../../images/hook-create-custom-jsps-folder.png)

4.  Add to the listing of custom JSPs by clicking the plus icon and specifying
    Portal's `html/portal/terms_of_use.jsp` file. Hint, the browse icon on the
    right-hand side within the custom JSP text field simplifies finding the JSP
    you want to customize. It lets you scroll through the JSPs that are
    accessible and lets you specify key words to narrow your search. 

    ![Figure 7.4: Liferay IDE simplifies adding a custom JSP to your hook by providing a powerful overview mode for editing your `liferay-hook.xml` file.](../../images/add-jsp-customization-with-hook.png)

    <!-- We'll need to retake this Figure's screenshot as the ability to add a custom 
    JSP using the graphical editor is broken. Jim -->

5.  Open your hook's `docroot/META-INF/custom_jsps/html/portal/terms_of_use.jsp`
    file and modify it as necessary. 

    Note, lots of errors will show in the editor because the resources used in
    the JSP (e.g., `PortalUtil`) are not available in the project; but they'll
    be available from the portal once the hook plugin is deployed to the portal
    server. 

6.  Deploy your hook and wait until it is deployed successfully. 

7.  Create a new user and log in. The *Terms of Use* page should include the
    changes you made above. 

Now there are two *Terms of Use* JSP files in the
`liferay-portal-[version]/tomcat-[tomcat-version]/webapps/ROOT/html/portal`
directory. One is called `terms_of_use.jsp` and another
`terms_of_use.portal.jsp`. `terms_of_use.jsp` is your hook's version, while
`terms_of_use.portal.jsp` is the original. To revert back to the original,
undeploy your hook. Your replacement JSP is removed, and the `.portal.jsp` file
is automatically renamed, taking its place. You can override any JSP in the
Liferay core, while retaining thew ability to easily revert your changes.
However, it's not possible to override the same JSP from multiple hooks; Liferay
won't know which version to use. 

---

 ![Note](../../images/tip-pen-paper.png) **Note:** We don't recommend changing
 Liferay's *Terms of Use* with a hook. You can replace the *Terms of Use* with a
 piece of web content simply by specifying values for these two properties in
 `portal-ext.properties`:

    terms.of.use.journal.article.group.id=
    terms.of.use.journal.article.id=

 Although our example hook doesn't provide any new functionality, it
 demonstrates how to override Liferay's JSP files. 

---

Next, we'll look at a different way to customize a JSP.

## Customizing JSPs by Extending the Original [](id=customize-jsp-without-overriding-original--liferay-portal-6-2-dev-guide-en)

If we can replace a JSP with a hook plugin, why learn another way to accomplish
the same thing? Good question. Let's say you want to preserve the original JSP's
content and functionality, but you want to add more to the JSP. And when you
upgrade Liferay, you want to benefit from any changes made to that upgraded JSP.
Well, you can; simply include the original JSP and then add more stuff to it. 

Here's an example that customizes the search page of the Blogs portlet. Specifically, it
adds helpful text to aid the user in searching for content. Since this technique
involves string manipulation, it's mainly useful for making a small number of
changes to a JSP.

1.  Use the hook project we created earlier or create a new hook project. 

2.  Open the `liferay-hook.xml` file from your project's `docroot/WEB-INF`
    folder in Liferay IDE and select the file's *Overview* mode tab.  

3.  Select the *Custom JSPs* folder from the outline to bring up the custom JSP
    options. Select the checkbox *Customize Liferay Portal JSPs* and create
    the default custom JSP folder `/META-INF/custom_jsps` by clicking the icon
    that has the three yellow diamonds. 

    Add to the listing of custom JSPs by clicking the plus icon and specifying
    Portal's `html/portlet/blogs/search.jsp` file. Hint, the browse icon on the
    right-hand side within the custom JSP text field simplifies finding the JSP
    you want to customize. 

    Click OK and save the `liferay-hook.xml` file. Liferay IDE pulls a copy of
    the Liferay Portal JSP into your project so you can modify it. 

4.  Open the JSP file `docroot/META-INF/custom_jsps/html/blogs/search.jsp` that
    Liferay IDE pulled into your project. 

5.  Replace the JSPs code with the following: 

        <%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>
        
        <%@ page import="com.liferay.portal.kernel.util.StringUtil" %>
        
        <liferay-util:buffer var="html">
            <liferay-util:include page="/html/portlet/blogs/search.portal.jsp" />
        </liferay-util:buffer>
        
        <%
        html = StringUtil.add(
            html,
            "Didn't find what you were looking for? Refine your search and " +
                "try again!",
            "\n");
        %>

        <%= html %>

    Notice how this code assigns the original JSP's HTML content to the variable
    `html`. We proceed to add some more content of our own to that HTML and then
    display it. 

6.  Deploy the hook plugin and add the Blogs portlet to a page. 

7.  Add a blog and then use the Blog portlet's search. 

Your custom message now shows below the search results. 

Next, we'll explore application adapters and what they can do for your sites and
site templates.

## Customizing Sites and Site Templates with Application Adapters [](id=application-adapter-hook-liferay-portal-6-2-dev-guide-en)

The JSP hooks that we've demonstrated so far are scoped to the portal. What if
you need to customize specific sites without propagating the customizations
throughout the entire portal? You can! *Application Adapters* are special hooks
that let you make changes at the site level. They are used for overriding JSPs. 

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

Now, let's discuss the perks of including the original JSP when overriding it.

### Including an Original JSP [](id=including-an-original-jsp-with-hook-liferay-portal-6-2-dev-guide-en)

If you override a JSP from the portal, we recommend you include the original
JSP (when possible). 

As we already demonstrated, including the original JSP file for global hooks is
accomplished by referencing the original JSP file from a
`<liferay-util:include>` tag and appending the suffix `.portal.jsp` to the
original file's name. Here's what including the original Navigation portlet's
view JSP in a global hook looks like:

    <%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

    <liferay-util:include page="/html/portlet/navigation/view.portal.jsp" />

For Application Adapter hooks, we include the original JSP by setting the
`<liferay-util:include>` tag's `useCustomPage` attribute to `false`, as below:

    <%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

    <liferay-util:include
        page="/html/portlet/navigation/view.jsp"
        useCustomPage="false"
    />

The view JSP is specified as `view.jsp`, *not* `view.portal.jsp` as for global
hooks. In the next section, we'll create and test an application adapter. 

### Creating an Application Adapter [](id=create-an-application-adapter-hook-liferay-portal-6-2-dev-guide-10-en)

Let's create an Application Adapter hook named `example-hook`. It will override
the Navigation portlet's `view.jsp`, while including the original Navigation
portlet's JSP with some custom text after its contents.

Here's how we do it:

1.  Modify your hook's `liferay-hook.xml` to specify the location of your custom
    JSP and set the global custom JSP setting to `false`:

        <hook>
            <custom-jsp-dir>/META-INF/custom_jsps</custom-jsp-dir>
            <custom-jsp-global>false</custom-jsp-global>
        </hook>

2.  Create a `docroot/META-INF/custom_jsps/html/portlet/navigation` directory in
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

3.  Deploy your Application Adapter hook plugin. 

4.  In your web browser, navigate to the Liferay site where you'll use the
    Application Adapter. 

5.  Select *Admin* &rarr; *Configuration* to access the Site Settings section of
    the Site Administration interface. From the *Application Adapter* field's
    drop-down selector menu, select *Example*. Then click *Save*. 

    ![Figure 11.3: Your *Application Adapters* are easily accessible from the Site Settings section of the Site Administration interface.](../../images/06-hooks-select-site-app-adapter.png)

6.  Navigate to your site's pages, add the Navigation portlet to a page, and
    make sure that the modification message from your Application Adapter hook
    plugin's `view.jsp` file is displayed there.

7.  Navigate to a different site's Navigation portlet to verify that only the
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

That's it for Application Adapters. Let's learn about performing custom actions
through hooks. 

## Performing a Custom Action [](id=perform-a-custom-action-with-hook-liferay-portal-6-2-dev-guide-en)

Hooks are useful for triggering custom actions on common portal events, like
user login or system startup. The actions for each of these events are defined
in `portal.properties` so we need to extend this file to create a custom action.
Hooks make this a simple task.

1.  In your hook project, create the directory
    `docroot/WEB-INF/src/com/liferay/sample/hook` and create a file called
    `LoginAction.java` inside it with this content:

        package com.liferay.sample.hook;

        import com.liferay.portal.kernel.events.Action;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;

        public class LoginAction extends Action {
            public void run(HttpServletRequest req, HttpServletResponse res) {
                System.out.println("## My custom login action");
            }
        }

2.  Create a `portal.properties` file inside your hook project's
    `docroot/WEB-INF/src` folder with this content:

        login.events.pre=com.liferay.sample.hook.LoginAction

3.  Edit your `docroot/WEB-INF/liferay-hook.xml` file, adding the following line
    above `<custom-jsp-dir>`:

        <portal-properties>portal.properties</portal-properties>

4.  Redeploy your hook. Once deployment is complete, log out and back in, and
    you should see your custom message, *## My custom login action*, displayed
    in the terminal window running Liferay. 

Custom action hook plugins aren't limited to the login event. You can define
custom actions for other events, too. For actions that require access to the
`HttpServletRequest` object, extend `com.liferay.portal.kernel.events.Action`;
for others, extend `com.liferay.portal.struts.SimpleAction`.

---

 ![Important](../../images/tip-pen-paper.png) **Important:** For better forward
 compatibility, use hooks to customize Struts actions rather than Ext plugins.

---

Extending and overriding portal properties is just as easy, so let's do that
next.

## Extending and Overriding *portal.properties* [](id=extend-override-portal-properties-liferay-portal-6-2-dev-guide-en)

In our hook that created a custom login action, we modified the
`login.events.pre` portal property. This property accepts *multiple* values, so
our value was appended to the existing `login.events.pre` values. We can
repeatedly modify the property from additional hooks because it accepts
multiple values. Some portal properties only accept a *single* value, such as
the `terms.of.use.required` property, which is either `true` or `false`. Only
modify single value properties from a single hook plugin; otherwise Liferay
won't know which value to use.

---

 ![Note](../../images/tip-pen-paper.png) **Note:** Hooks support customizing a
 specific list of predefined properties.  For a list of portal properties that
 can be overridden via hook, see the
 [liferay-hook_6_2_0.dtd](http://docs.liferay.com/portal/6.2/definitions). In
 addition to defining custom actions, hooks can override portal properties to
 define model listeners, validators, generators, and content sanitizers. If you
 want to customize a property that's not found in this list, you must use an Ext
 plugin (see [Advanced Customization with Ext Plugins](http://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/advanced-customization-with-ext-plugins-liferay-portal-6-2-dev-guide-12-en)).
 For more information about the properties themselves, you can view an online
 version of Liferay's [portal
 properties](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html)
 file.

---

Next, let's learn how to override and add Struts actions from a hook plugin. 

## Overriding and Adding Struts Actions [](id=override-and-add-struts-actions-with-hook-liferay-portal-6-2-dev-guide-en)

Do you want to add a new Struts action to Liferay or override existing Struts
actions? *Struts action hooks* let you do just that. 

Let's consider the interfaces used for Struts actions. There are two:

- `com.liferay.portal.kernel.struts.StrutsAction`
- `com.liferay.portal.kernel.struts.StrutsPortletAction`

The `StrutsAction` interface is for regular Struts actions from the portal, like
`/c/portal/update_email_address`. The `StrutsPortletAction` interface is used
for similar Struts actions, but from portlets. 

Struts actions are defined as classes, and they're all connected in a
`struts-config.xml` file. The `struts-config.xml` for a Liferay Portal instance
running on Apache Tomcat can be found in the
`liferay-portal-[version]/tomcat-[version]/webapps/ROOT/WEB-INF` directory. The
`struts-config.xml` file links actions to specific JSP pages. Each action
performs a specific task and then returns a *forward*, an object containing a
name and path. The forward defines the page the portal sends the user to after
the action completes. When a user submits a form that maps to one of these
actions, the action class is loaded, executed, and returns a forward. 

A Struts action hook can wrap or override existing Struts actions or create a
new Struts path; we'll demonstrate both here. We'll override the Struts actions
in the `struts-config.xml` file by using a Struts action hook to point to a
custom class. Then we'll create a new Struts path: `/c/portal/sample` and
navigate to it. Let's get started! 

First, let's override the Sign In portlet's Struts action using the example-hook
project that we've been using in this chapter. 

Here's the current action in your portal's `struts-config.xml` file:

    <action path="/login/login"
        type="com.liferay.portlet.login.action.LoginAction">

        <forward
            name="portlet.login.login"
            path="portlet.login.login"
        />
        <forward
            name="portlet.login.login_redirect"
            path="portlet.login.login_redirect"
        />
    </action>

1.  Navigate to your `example-hook/docroot/WEB-INF` folder and open
    `liferay-hook.xml`. 

2.  Insert the following code between the `<hook>...</hook>` tags:

		<portal-properties>portal.properties</portal-properties>
		<custom-jsp-dir>/META-INF/custom_jsps</custom-jsp-dir>
        <struts-action>
            <struts-action-path>/portal/sample</struts-action-path>
            <struts-action-impl>
            com.liferay.sample.hook.action.ExampleStrutsAction
            </struts-action-impl>
        </struts-action>
        <struts-action>
            <struts-action-path>/login/login</struts-action-path>
            <struts-action-impl>
            com.liferay.sample.hook.action.ExampleStrutsPortletAction
            </struts-action-impl>
        </struts-action>

3.  Create a new package `com.liferay.sample.hook.action` in your
    `example-hook/docroot/WEB-INF/src` folder. 

4.  In your new package, create a class named *ExampleStrutsPortletAction*, which
    will wrap the login portlet Struts action. Insert the following code:

        package com.liferay.sample.hook.action;

        import com.liferay.portal.kernel.struts.BaseStrutsPortletAction;
        import com.liferay.portal.kernel.struts.StrutsPortletAction;
        import com.liferay.portal.theme.ThemeDisplay;
        import com.liferay.portal.kernel.util.WebKeys;
        import javax.portlet.ActionRequest;
        import javax.portlet.ActionResponse;
        import javax.portlet.PortletConfig;
        import javax.portlet.RenderRequest;
        import javax.portlet.RenderResponse;
        import javax.portlet.ResourceRequest;
        import javax.portlet.ResourceResponse;

        public class ExampleStrutsPortletAction extends BaseStrutsPortletAction {

            public void processAction(
                    StrutsPortletAction originalStrutsPortletAction,
                    PortletConfig portletConfig, ActionRequest actionRequest,
                    ActionResponse actionResponse)
                throws Exception {
                ThemeDisplay themeDisplay =
                    (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

                Long currentuser = themeDisplay.getUserId();

                if (currentuser != null) {
                    System.out.println("Wrapped /login/ action2");

                }
                originalStrutsPortletAction.processAction(
                    originalStrutsPortletAction, portletConfig, actionRequest,
                    actionResponse);
            }

            public String render(
                    StrutsPortletAction originalStrutsPortletAction,
                    PortletConfig portletConfig, RenderRequest renderRequest,
                    RenderResponse renderResponse)
                throws Exception {

                System.out.println("Wrapped /login/ action");

                return originalStrutsPortletAction.render(
                    null, portletConfig, renderRequest, renderResponse);

            }

            public void serveResource(
                    StrutsPortletAction originalStrutsPortletAction,
                    PortletConfig portletConfig, ResourceRequest resourceRequest,
                    ResourceResponse resourceResponse)
                throws Exception {

                originalStrutsPortletAction.serveResource(
                    originalStrutsPortletAction, portletConfig, resourceRequest,
                    resourceResponse);

            }

        }

5.  Create a new class named `ExampleStrutsAction` in the
    `com.liferay.sample.hook.action` package. It will implement your new portal
    Struts action. Insert the following code:

        package com.liferay.sample.hook.action;

        import com.liferay.portal.kernel.struts.BaseStrutsAction;
        import com.liferay.portal.kernel.util.ParamUtil;

        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;

        public class ExampleStrutsAction extends BaseStrutsAction {

            public String execute(
                HttpServletRequest request, HttpServletResponse response)
                throws Exception {
    
                String name = ParamUtil.get(request, "name", "World");
    
                request.setAttribute("name", name);
    
                return "/portal/sample.jsp";
            }

        }

We've overridden the `execute(HttpServletRequest, HttpServletResponse)` method
of `BaseStrutsAction`, but not the `execute(StrutsAction, HttpServletRequest,
HttpServletResponse)` method. The original Struts action's `execute()` method
is ignored. That's fine for our example.

---

 ![Note](../../images/tip.png) **Best practice**: When overriding an existing
 Struts action, it's usually best to override the method that takes the original
 Struts action handle as a parameter and execute that original Struts action.
 Think of the original action as a servlet filter or aspect. If you override the
 method that *takes* the original action handle as a parameter and don't
 explicitly execute it, the original action won't be executed. If you override
 the `execute` method that *does not take* the original action as a parameter,
 you are ignoring the original action and it won't be executed.

---

That's it for overriding the Struts actions! Now let's get our new Struts
path working. 

1.  Create a `sample.jsp` file in the
    `example-hook/docroot/META-INF/custom_jsps/html/portal` directory. Insert
    the following code:

        <%
        String name = (String)request.getAttribute("name");
        %>
        Hello <%= name %>!

2.  Add `/portal/sample` to your portal's list of paths that don't require
    authentication by copying your existing `auth.public.paths` property
    assignment from your portal's `portal.properties` into your
    `portal-ext.properties` file and adding `/portal/sample` to the end of the
    value list. It looks similar to the assignment below:

            auth.public.paths=\
                /asset/get_categories,\
                ...
                /wiki/rss,\
                /portal/sample

3.  Restart your portal server. 

Congratulations! Your Struts action hook plugin is complete! Now when you access
the *Sign In* portlet, this message prints to your console:

    Wrapped /login/ action

When you actually log in, this message prints to your console:

    Wrapped /login/ action2
    Wrapped /login/ action

Both custom Struts actions are executed via your Struts action hook! 

Try your new Struts path by accessing it from your browser (e.g.,
`http://localhost:8080/c/portal/sample`). 

![Figure 11.4: Your new Struts action displays *Hello World!* in your browser.](../../images/06-hooks-5.png)

Let's continue our hooks expedition by overriding a portal service.

## Overriding a Portal Service [](id=override-a-portal-service-with-hook-liferay-portal-6-2-dev-guide-en)

All the functionality provided by Liferay is enclosed in a layer of services
that are accessed by the controller layer in portlets. This is a standard
architecture, and it lets you change how a core portlet of Liferay behaves
without changing the portlet itself; you're customizing the backend services
that the portlet uses. You can leverage this architecture to customize portal
service behavior, and hook plugins are your tool for doing so. 

When extending Liferay Portal with hooks, you should try to avoid implementing
the portal's interfaces directly. In some cases, patches are added to the
interfaces in fix packs to fix an issue (e.g., adding a new method to a
service). If you implement the API directly, a patch can break your
customization. However, if you extend the basic implementation, a patch won't
break your customization. Therefore, the best practice is to extend the Liferay
Portal's base implementations. For example, if you'd like to modify the
implementation of the `UserLocalService` interface, then extend
`UserLocalServiceWrapper`. If you'd like to modify the `SanitizerUtil` class,
then extend `BaseSanitizer`. 

---

 ![tip](../../images/tip-pen-paper.png) **Tip:** Your `portal.properties` file
 also provides options to extend portal services. For example, you can extend
 `BaseSanitizer` to use a custom sanitizer by setting the `sanitizer.impl`
 property. By setting this property to your custom sanitizer class, you're
 extending the `BaseSanitizer` already included in Liferay Portal.

---

Liferay generates dummy wrapper classes for all its services. For example,
`UserLocalServiceWrapper` is created as a wrapper for `UserLocalService`, a
service for adding, removing, and retrieving user accounts. To modify the
functionality of `UserLocalService` from our hook, create a class that extends
`UserLocalServiceWrapper`, override the methods you want to modify, and instruct
Liferay to use your service class instead of the original. 

1.  Inside your example-hook project's
    `/docroot/WEB-INF/src/com/liferay/sample/hook` folder, create a new file
    called `MyUserLocalServiceImpl.java` with the following content:

        package com.liferay.sample.hook;
    
        import com.liferay.portal.kernel.exception.PortalException;
        import com.liferay.portal.kernel.exception.SystemException;
        import com.liferay.portal.model.User;
        import com.liferay.portal.service.UserLocalService;
        import com.liferay.portal.service.UserLocalServiceWrapper;
    
        public class MyUserLocalServiceImpl extends UserLocalServiceWrapper {
    
            public MyUserLocalServiceImpl(UserLocalService userLocalService) {
                super(userLocalService);
            }
    
            public User getUserById(long userId)
                throws PortalException, SystemException {
    
                System.out.println(
                    "## MyUserLocalServiceImpl.getUserById(" + userId + ")");
    
                return super.getUserById(userId);
            }
        }

    ---

    ![tip](../../images/tip-pen-paper.png) **Note:** The wrapper class
    (`MyUserLocalServiceImpl` in this example) will be loaded in the hook's
    class loader. That means it will have access to any other class included in
    the same WAR file, but *not* the *internal* classes of Liferay.

    ---

2.  Edit `liferay-hook.xml`, located in the `example-hook/docroot/WEB-INF`
    directory, by adding the following after `</custom-jsp-dir>`:

        <service>
            <service-type>
            com.liferay.portal.service.UserLocalService
            </service-type>
            <service-impl>
            com.liferay.sample.hook.MyUserLocalServiceImpl
            </service-impl>
        </service>

Redeploy your hook and refresh your browser. In the terminal window running
Liferay, you should see *## MyUserLocalServiceImpl.getUserById(...)* messages
displayed by your hook. 

There are other Liferay services that you may need to extend to meet advanced
requirements:

- **OrganizationLocalService:** Adds, deletes and retrieves organizations. Also
  assigns users to organizations and retrieves the list of organizations of a
  given user. 
- **GroupLocalService:** Adds, deletes and retrieves sites. 
- **LayoutLocalService:** Adds, deletes, retrieves and manages pages of sites,
  organizations and users. 

For a complete list of available services and their methods, check the [Liferay
Portal 6.2 Javadocs](http://docs.liferay.com/portal/6.2/javadocs/) or access the
Javadocs for your version of Liferay at
[http://docs.liferay.com/portal](http://docs.liferay.com/portal) and click on
the *javadocs/* link. 

Now that you know how to override a portal service, let's learn how to override
a `Language.properties` file.

## Overriding a *Language.properties* File [](id=override-a-language-properties-hook-liferay-portal-6-2-dev-guide-en)

Hooks let you change any of the messages displayed by Liferay to suit your
needs. To do so, create a *Language* file for the locale of the messages you
want to customize, then refer to your file from your `liferay-hook.xml` file.
For example, to override the Spanish and French message translations of portal's
`Language.properties` file, create *Language* files for them in the same
directory and refer to these language files in your `liferay-hook.xml` file,
like this:

    <hook>
        ...
        <language-properties>content/Language_es.properties</language-properties>
        <language-properties>content/Language_fr.properties</language-properties>
        ...
    </hook>

---

 ![Tip](../../images/tip-pen-paper.png) **Tip:** Check the DTD of each Liferay
 XML file, you modify for the elements and attributes that can be included in the
 XML and the specified order for those elements. You can find the Liferay DTDs
 online here: [http://docs.liferay.com/portal/6.2/definitions](http://docs.liferay.com/portal/6.2/definitions).

---

Great! You now know how to customize language keys. Next, let's discuss
extending your Indexer Post Processor.

## Extending the Indexer Post Processor [](id=extending-the-indexer-post-processor-liferay-portal-6-2-dev-guide-10-en)

Would you like to modify the search summaries, indexes, and queries available in
your portal instance? Developing an Indexer Post Processor hook lets you do just
that. The indexer hook implements a post processing system on top of the
existing indexer to allow plugin hook developers to modify their search, index,
and query capabilities. Let's run through a simple example to preview what you
can accomplish with an indexer hook. For our example, we're going to add *Job
Title* into the User Indexer so we can search for users by their Job Title.

1. In your existing example-hook project, open the `liferay-hook.xml` file and
insert the following lines before the closing `</hook>` tag:

		<indexer-post-processor>
        	<indexer-class-name>com.liferay.portal.model.User</indexer-class-name>
        	<indexer-post-processor-impl>com.liferay.hook.indexer.SampleIndexerPostProcessor</indexer-post-processor-impl>
        </indexer-post-processor>

    The `<indexer-class-name/>` tag clarifies the model entity for the indexer.
    Furthermore, the `<indexer-post-processor-impl/>` tag clarifies the
    implementation of the interface.

2. Create a new class in the `docroot/WEB-INF/src/com/liferay/hook/indexer`
directory of your example-hook named *SampleIndexerPostProcessor*. Then replace
the Java source file's contents with the following lines:

		package com.liferay.hook.indexer;

		import java.util.Locale;
		import javax.portlet.PortletURL;
		import com.liferay.portal.kernel.log.Log;
		import com.liferay.portal.kernel.log.LogFactoryUtil;
		import com.liferay.portal.kernel.search.BaseIndexerPostProcessor;
		import com.liferay.portal.kernel.search.BooleanQuery;
		import com.liferay.portal.kernel.search.Document;
		import com.liferay.portal.kernel.search.Field;
		import com.liferay.portal.kernel.search.SearchContext;
		import com.liferay.portal.kernel.search.Summary;
		import com.liferay.portal.model.User;


			public class SampleIndexerPostProcessor extends BaseIndexerPostProcessor
			{
				public void postProcessContextQuery(BooleanQuery booleanQuery, SearchContext searchcontext)
						throws Exception {
					if(_log.isDebugEnabled())
						_log.debug(" postProcessContextQuery()");
					}

				public void postProcessDocument(Document document, Object object)
						throws Exception {
					User userEntity = (User) object;
					String indexerUserTitle = "";
					try {
						indexerUserTitle = userEntity.getJobTitle();
					} catch (Exception e) {}
					if(indexerUserTitle.length() > 0)
						document.addText(Field.TITLE, indexerUserTitle);
				}

				public void postProcessFullQuery(BooleanQuery fullQuery, SearchContext searchcontext)
						throws Exception {
					if(_log.isDebugEnabled())
						_log.debug(" postProcessFullQuery()");
				}

				public void postProcessSearchQuery(BooleanQuery searchquery, SearchContext searchcontext)
						throws Exception {
					if(_log.isDebugEnabled())
						_log.debug(" postProcessSearchQuery()");
				}

				public void postProcessSummary(Summary summary, Document document, Locale locale,
						String snippet, PortletURL portletURL) {
					if(_log.isDebugEnabled())
						_log.debug("postProcessSummary()");
				}
				private static Log _log = LogFactoryUtil.getLog(SampleIndexerPostProcessor.class);
			}

	Notice the `SampleIndexerPostProcessor` class extends Liferay's
	`BaseIndexerPostProcessor` base implementation. Then we add our own logic to
	enable users to search for *Job Title* amongst all the portal's users. Thus,
	we've added a new feature for the User Indexer. Let's give it a try!

Navigate to the *Control Panel* &rarr; *Users and Organizations* and make sure a
user has a job title, which can be added in any user's *My Account* interface.
Then test out the indexer hook by searching for that job title.

![Figure 11.5: In this example, searching for *Blogger* returns two users with the matching job title.](../../images/indexer-hook-search.png)

As you can see, hooks serve to enhance the functionality of your portal and
applications. Next, we will take a look at Liferay's Right to Left Language Support and how it can enhance 
both your portal and plugins.

## Supporting Right-to-Left Languages in Plugins [](id=displaying-rtl-content-with-rtl-hook-liferay-portal-6-2-dev-guide-11-en)

Middle Eastern languages, such as Hebrew and Arabic, are written right-to-left
(RTL). However, many sites are multilingual, requiring both RTL and
left-to-right (LTR) content. Conveniently, browsers use [language and direction](http://www.w3.org/TR/html401/struct/dirlang.html) 
HTML attributes to adapt and align page content automatically. Sites, however, 
may consist of elements that are absolutely positioned on the page by a style
sheet; these elements aren't automatically aligned by the browser. Instead, you
must adapt your style sheets to handle such elements. Since style sheets are
usually designed for LTR languages, the typical challenge is creating
alternative versions of the CSS for RTL languages. This can be an arduous task. 

Thankfully, Liferay's *Right to Left Language Support* app automatically adapts
Liferay Portal styles for RTL languages. When you deploy it, it mirrors
your site's content for RTL languages. The app is available on the Liferay
[Marketplace](http://www.liferay.com/marketplace). You can purchase, install,
and deploy the app as described in the [Leveraging the Liferay Marketplace](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/leveraging-the-liferay-marketplace-liferay-portal-6-2-user-guide-14-en)
chapter of [Using Liferay Portal](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/). 

As a before-and-after example, the figure below shows a page displayed in
English, an LTR language. 

![Figure 11.6: Here is a site displaying English content left-to-right.](../../images/rtl-hook-001.png)

Compare it to the following figure of a page displayed in Hebrew, an RTL
language. 

![Figure 11.7: Here is a site displaying Hebrew content right-to-left using Right to Left Language Support.](../../images/rtl-hook-002.png)

You get the point, right? The Right to Left Language Support (RTL Support) app
does the heavy lifting of rendering the RTL content appropriately! 

Now that you've seen Liferay Portal and its apps rendered using RTL Support,
have you wondered how you might leverage RTL Support in your custom plugins?
You'll learn how to use RTL Support with your plugins next. 

### Applying the RTL Support to Custom Plugins [](id=applying-the-rtl-hook-to-custom-plugins-liferay-portal-6-2-dev-guide-11-en)

You can use RTL Support with any plugin type, though theme plugins are the most
common. The following steps focus on using RTL Support in a custom theme, but
they also mention what's needed to make similar changes to support using RTL
Support in the other plugin types. 

1. Make sure to deploy the Right to Left Language Support app to your
   application server. 
	
	If upon initial deployment you don't notice any changes when switching to an
	RTL language, reload the page to force a clean cache. 

    Note, since dynamic generation of CSS from SASS is not yet supported in the
    context of the RTL Support app, the hook loads the current theme's merged
    CSS files, disregarding whether
    [theme CSS fast loading](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#Theme)
    is disabled. Disabling CSS fast load (i.e., setting
    [`theme.css.fast.load=false`](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#Theme))
    is commonly done while debugging for development. 

2. Restart your application server. 

3. Extract the contents of the Liferay RTL Hook web application. 

4. Copy the `rtl-hook.jar` and its dependencies, `ant.jar`, `jodd.jar`,
   `jruby.jar` and `rhino.jar`, from your RTL hook web app's `WEB-INF/lib`
   folder to the `docroot/WEB-INF/lib` folder of your plugin project. 

5. Add the following filtering elements before the closing `</web-app>` tag in
   your project's `docroot/WEB-INF/web.xml` file. 

        <filter>
            <filter-name>Dynamic CSS Filter</filter-name>
            <filter-class>com.liferay.rtl.hook.filter.dynamiccss.DynamicCSSFilter</filter-class>
        </filter>
        <filter-mapping>
            <filter-name>Dynamic CSS Filter</filter-name>
            <url-pattern>*.css</url-pattern>
        </filter-mapping>

6. Add targets for building and cleaning your plugin's RTL CSS files. 

    For example, here are the targets to add to the `build.xml` file of your
    theme plugin project: 

        <target name="build-css" depends="clean-rtl-css, build-common-theme.build-css, build-rtl-css" />

        <target name="build-rtl-css">
            <java
                classname="com.liferay.rtl.tools.RtlCssBuilder"
                classpathref="plugin.classpath"
                fork="true"
                newenvironment="true"
            >
                <jvmarg value="-Dliferay.lib.portal.dir=${app.server.lib.portal.dir}" />
                <arg value="sass.dir=/" />
                <arg value="sass.docroot.dir=${basedir}/docroot" />
            </java>
        </target>

        <target name="clean-rtl-css">
            <delete failonerror="false" includeemptydirs="true">
                <fileset dir="${basedir}/docroot" includes="**/.sass-cache/*_rtl.*" />
            </delete>
        </target>

    If you're using the RTL hook with another type of plugin project, 
    rename the `build-common-theme.build-css` dependency target reference
    appropriately for your plugin type: 

    *Portlet:* `build-common-portlet.build-css`

    *Hook:* `build-common-hook.build-css`

    *Ext:* `build-common-ext.build-css`

    *Web App:* `build-common-web.build-css` 
 
7. Deploy your plugin to the portal. 

Ant echoes *Generated RTL cache for ...* messages that mention the cache that
the RTL hook generates in your plugin. In your plugin's `css/.sass-cache`
folder, the hook creates `*_rtl.css` versions of each of your `*.css` files. 

You now know how to use the Right to Left Language Support app in your custom
plugins. Now it's time to learn how you can extend the Right to Left Language
Support app's style with your own custom CSS for RTL languages. 

### Defining Custom CSS for RTL Languages [](id=defining-custom-css-for-rtl-languages-liferay-portal-6-2-dev-guide-11-en)

As you learned in the previous section, the Right to Left Language Support (RTL
Support) app automatically generates RTL versions of your CSS files by applying
rules, such as changing `margin-left` to `margin-right`. You may, however, want
to extend the generated CSS by defining your own custom styles for RTL
languages. You can achieve this by following these steps:

1. Create a CSS file with the suffix `_rtl` in the same location as a
   CSS file that you want to extend. For example, create a file `main_rtl.css`
   to extend a file named `main.css`. 

2. Edit the `_rtl` file, adding *only* the lines that define your custom
   styles for RTL languages.

3. Deploy your plugin.

    Check the your plugin's `css/.sass-cache` folder to see that the generated
    `_rtl.css` file in this folder not only contains the automatically generated
    CSS from the original file, but also contains your custom CSS code at the
    end. 

4. In your site, add the Language portlet to a page and change the current
   language to an RTL language (e.g., Hebrew).

   Notice your custom RTL styles applied to your site. 

Now your plugin styles are automatically adapted for RTL languages. In case you 
have defined any custom styles, they are also applied.

Whether you want to adapt the portal and/or your custom plugins to RTL
languages, the RTL Support app makes it easy to offer your users the ideal
viewing experience. 

In the next section, you'll explore more hooks that allow for customizing
Liferay's core features.

## Other Hooks [](id=other-hooks-liferay-portal-6-2-dev-guide-10-en)

Since hooks are the preferred plugin type for customizing Liferay's core
features, the Liferay team is happy to keep providing you new hooks. This
section is a placeholder for hooks that are available in Liferay Portal 6.2,
but aren't yet fully documented.

**Servlet filter hook:** Servlet filters allow you to pre-process requests going
*to* a servlet and post-process responses coming *from* a servlet. As server
requests are received that match URL patterns or match servlet names specified
in your servlet filter mappings, your specified servlet filters are applied.
Hook elements `servlet-filter` and `servlet-filter-mapping` have been added to
`liferay-hook.xml` so you can configure your servlet filters. For a working
example, see the
[sample-servlet-filter-hook](https://github.com/liferay/liferay-plugins/tree/master/hooks/sample-servlet-filter-hook)
in the Plugins SDK. 

## Summary [](id=summary-liferay-portal-6-2-dev-guide-10-en)

In this chapter, we discussed some of the many uses of the versatile hook
plugin, the preferred tool for customizing Liferay. You learned how to perform
custom portal actions, override and extend custom portal JSPs, modify portal
properties, and replace portal services and language properties. 

Next, we'll introduce you to Liferay's powerful UI framework: AlloyUI. 
