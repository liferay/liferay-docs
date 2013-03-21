# Hooks 

Sometimes you need to customize even the core features of Liferay. Liferay Hooks
are the preferred plugin, filling a wide variety of the common needs for
overriding Liferay core functionality. Use hooks in place of Ext plugins,
whenever possible, as they're hot-deployable and more forward compatible. Common
scenarios to which hooks are applied include:

- Overriding web resources

- Performing custom actions on portal startup or user login

- Overriding Struts actions

- Overriding and extending portal JSPs

- Modifying portal properties

- Replacing portal services with your own implementation

Like portlets, layout templates, and themes, hooks are created and managed using
the Plugins SDK, or the terminal. So, let's create and deploy a hook using both
environments.

## Creating a Hook

Regardless of whether you create hooks using the Plugins SDK in Developer
Studio, or using your terminal, hooks are stored within the `hooks` directory of
the Plugins SDK. 

***Using Developer Studio:*** 

1. Go to File &rarr; New &rarr; Liferay Project. 

2. Next, go through the following steps to setup your new hook:

    1.1. Enter *example* for your Project name and *Example* for your Display
    name. 

    2.2. Select the Plugins SDK and Portal Runtime you've configured. 

    2.3. Select the *Hook* Plugin Type. 

    2.4. Click *Finish*. 

![Figure 6.1: Creating your hook plugin](../../images/06-hooks-1.png)

The Plugins SDK automatically adds "hook" to the project name after its
creation. With Developer Studio, you can create a completely new plugin or add a
new plugin to an existing plugin project. 

***Using the terminal:*** Navigate to your Plugins SDK directory in a terminal
and enter the appropriate command for your operating system:

1. In Linux and Mac OS X, enter

    ./create.sh example "Example"

2. In Windows, enter

    create.bat example "Example"

After you see a BUILD SUCCESSFUL message from Ant, there will be a new folder
named `example-hook` inside of the `hooks` folder of your Plugins SDK. The
Plugins SDK automatically appends "-hook" to the project name when creating this
folder. 

### Deploying the Hook

***Using Developer Studio:*** Simply drag your hook project onto your server.
Upon deploying your hook, your server will output messages indicating your hook
was read, registered and is now available for use. 

	Reading plugin package for example-hook
	Registering hook for example-hook
	Hook for example-hook is available for use


![Figure 6.2: Deploying your hook plugin](../../images/06-hooks-4.png)

If you ever need to redeploy your plugin while in Developer Studio, right
click your plugin's icon located underneath your server and select *Redeploy*.

![Figure 6.3: Redeploying your hook plugin](../../images/06-hooks-2.png)

***Using the terminal:*** Open a terminal window in your `hooks/example-hook`
directory and enter this command:

    ant deploy

A BUILD SUCCESSFUL message indicates your hook is now being deployed. If you
switch to the terminal window running Liferay, within a few seconds you
should see the message "Hook for example-hook is available for use". 

![note](../../images/tip-pen-paper.png)**Note:** When we covered portlets and
themes, after deployment your newly created portlet or theme was fully
functional. With hooks, there are a few edits and file additions necessary
before our hook is up and running. 

### Anatomy of the Hook

In order to create a useful hook, we need to edit existing files and create
new files within the structure. The full structure of the example-hook is shown
below:

-	`example-hook/`
	
	-	`docroot/`
		
		-	`META-INF/`
		
		-	`WEB-INF/`
			
			-	`lib`
			
			-	`liferay-hook.xml`
			
			-	`liferay-plugin-package.properties`
	
	-	`build.xml`

In Developer Studio's *Package Explorer*, here's what the hook structure looks
like:

![Figure 6.4: Package Explorer view of hook plugin](../../images/06-hooks-3.png)

The next section covers how hook overrides web resources, and we'll demonstrate
replacing a portal JSP using a hook. 

## Overriding web resources

Hooks are commonly used to override web resources, or to overwrite a resource in
`portal-web`. For example, you can use a hook to override JSP files, JSPF files,
Javascript files, CSS files, or images. 

---

![important](../../images/tip-pen-paper.png)**Important:** Note the requisites
for the following resources:

- ***JSPF:*** when modifying a JSPF file, the changes won't take effect unless
you modify the JSP which is including it. 
- ***CSS:*** when modifying a CSS file which is imported by another one, the
changes won't take effect unless you modify the parent CSS file (usually
`main.css`). 

---

Replacing a portal JSP is a simple task with hooks. Let's demonstrate by
creating and deploying a hook to modify your portal's *Terms of Use* page. 

1. Create the directory `hooks/example-hook/docroot/META-INF/custom_jsps`. 

2. Edit `hooks/example-hook/docroot/WEB-INF/liferay-hook.xml`, adding the
following between `<hook></hook>`:

    <custom-jsp-dir>/META-INF/custom_jsps</custom-jsp-dir>

Now, when your hook is deployed, any JSP you place inside the `custom_jsps`
directory will replace a corresponding JSP of the same name and relative path
found inside your Liferay instance. The directory structure inside this folder
must mirror the one within
`liferay-portal-[version]/tomcat-[tomcat-version]/webapps/ROOT`. 

3. To override the *Terms of Use*, create the directory
`hooks/example-hook/docroot/META-INF/custom_jsps/html/portal/` and then copy
into that directory the `terms_of_use.jsp` file from
`liferay-portal-[version]/tomcat-[tomcat-version]/webapps/ROOT/html/portal/`.

4. Open your copy of the `terms_of_use.jsp` and make any changes you like. 

5. Deploy your hook and wait until it is deployed successfully. 

6. Create a new user and try to log in. When you get to the *Terms of Use* page,
you'll see your version of the terms instead of those from the default JSP. 

Now, in the
`liferay-portal-[version]/tomcat-[tomcat-version]/webapps/ROOT/html/portal`
directory, there are two *Terms of Use* JSP files, one called `terms_of_use.jsp`
and another called `terms_of_use.portal.jsp`. `terms_of_use.jsp` is the version
from your hook, while `terms_of_use.portal.jsp` is the original. If you undeploy
your hook by deleting its directory in `webapps`, your replacement JSP is
removed, and the `.portal.jsp` file is automatically renamed and takes its
place. This way, you can override any JSP in the Liferay core, while also being
able to revert your changes just by undeploying your hook. Note, however, that
it is not possible to override the same JSP from multiple hooks; Liferay won't
know which version to use. 

![note](../../images/tip-pen-paper.png)**Note:** We don't recommend changing the
*Terms of Use* with a hook. You can replace the *Terms of Use* with web content
simply by setting two properties in `portal-ext.properties`. Although the hook
we used isn't necessary, it's a good way to demonstrate overriding a JSP using a
hook. 

Next, we'll look at a different way to customize a JSP. 

## Customizing JSPs without overriding the original

Overriding a JSP isn't recommended, because if the original file changes (for
example, to fix a bug), you have to change your customized file, as well, in
order to benefit from the change to the original.

To avoid this drawback and make your JSP modifications less invasive, you can
render the original JSP into a string, and modify it dynamically afterwards.
This way you can change minor elements of a JSP, like adding a new heading or
button, without modifying your hook every time you upgrade Liferay. Here's an
example that customizes the search portlet, removing the ability to use a search
provider in the browser:

    <%@ include file="/html/portlet/search/init.jsp" %>

	<liferay-util:buffer var="html">
		<liferay-util:include page="/html/portlet/search/view.portal.jsp" />
	</liferay-util:buffer>

	<%
	html = StringUtil.add(html, "Enjoy your search!", "\n");
	%>

	<%= html %>

Since this technique involves String manipulation, it's mainly useful for making
a small number of changes to a JSP. 

## Customizing Sites and Site Templates with Application Adapters

The standard hook features we've demonstrated to this point are scoped to the
portal. Wouldn't it be nice if you could customize specific sites without
propagating the customizations throughout the entire portal? You can! Using
*Application Adapters* hook plugins, you can use hooks to make changes at the
site level. In Liferay 6.1, Application Adapters are used for overwriting JSPs. 

There'a a Sample Application Adapter in the [Liferay Plugins
Repository](https://github.com/liferay/liferay-plugins/tree/master/hooks/sample-application-adapter-hook).
How do we build an Applicaiton Adapter of our own? 

### Required settings

To create an Application Adapter, you need a hook with custom JSPs and
the hook's global custom JSP setting turned off. First, configure your
`liferay-hook.xml` with the following:

        <custom-jsp-dir>/META-INF/custom_jsps</custom-jsp-dir>
        <custom-jsp-global>false</custom-jsp-global>

When you deploy your hook, Liferay installs the Application Adapter to your
instance, under the name of the hook. So, for an Application Adapter hook named
"Social Office", the hook becomes available to sites and site templates as an
Application Adapter named "Social Office Hook". 

### Including an original JSP

If you overwrite a JSP from the portal, we recommend you include the original
JSP (when possible). 

As we demonstrated previously, including the original JSP file
for global hooks is accomplished by referencing the original JSP file from an
`<liferay-util:include>` tag, and appending the suffix `.portal.jsp` to the
original file's name. For example, here's what including the original Navigation
portlet's view JSP in a global hook looks like:

        <%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

        <liferay-util:include page="/html/portlet/navigation/view.portal.jsp" />

For Application Adapter hooks, we include the original JSP simply by setting the
`<liferay-util:include>` tag's `useCustomPage` attribute to `false`, as below:

        <%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

        <liferay-util:include page="/html/portlet/navigation/view.jsp" useCustomPage="false" />

Note here that the view JSP is specified as `view.jsp`, *not* `view.portal.jsp`. 

### Creating an Application Adapter

Let's create an Application Adapter hook plugin, named `example-hook`. We'll use
it to overwrite the Navigation portlet's `view.jsp`, while including the
original Navigation portlet's JSP with some custom text after its contents. 

Here's how we do it:

1. Modify your hook's `liferay-hook.xml` to specify the location of your custom
JSP and set the global custom JSP setting to `false`:

        <hook>
            <custom-jsp-dir>/META-INF/custom_jsps</custom-jsp-dir>
            <custom-jsp-global>false</custom-jsp-global>
        </hook>

2. Create a new `view.jsp` file in your hook's
`docroot/META-INF/custom_jsps/html/portlet/navigation` directory and insert code
to include the original JSP:

        <%@ taglib uri="http://liferay.com/tld/util" 
        prefix="liferay-util" %>

        <liferay-util:include page="/html/portlet/navigation/view.jsp" 
        useCustomPage="false" />

        <p>
        This was modified by the Example Application Adapter.
        </p>

3. Deploy your Application Adapter hook plugin. 

4. Open your Liferay Portal instance in your web browser and go to whichever
site you want to use the Application Adapter for.

5. Select *Manage* &rarr; *Site Settings* and, from *Application Adapter*
field's drop-down menu, select *example* and click *Save*. 

    ![Figure 6.5: Your *Application Adapters* are easily accessible in your Site's settings.](../../images/06-hooks-select-site-app-adapter.png)

6. Navigate back to your site's Navigation portlet to verify the modification
message displayed by your Application Adapter hook plugin's `view.jsp` file.

    ![Figure 6.6: You are able to view the custom message of your *Example Application Adapter*.](../../images/06-hooks-app-adapter-content-displays.png)

7. Navigate to a different site's Navigation portlet to verify that only the
content of the portlet's *original* `view.jsp` file displays.

See, using Application Adapter hook plugins to overwite Liferay's core
functionality at the site scope is easy! 

Application Adapters can also be applied to Site Templates. You can package your
solutions with Site Templates, leveraging Application Adapters while making them
seamless to the user. 

Let's say you want to make an Enterprise Resource Planning (ERP) solution for a
company's departments. Your ERP solution requires an extension of Liferay's Wiki
Portlet, so you implement that extension as an Application Adapter. Then, You
incorporate the Application Adapter in a Site Template (named "ERP site") for
the company's ERP sites. The company's administrative user creates the sites by
going to *Control Panel* &rarr; *Sites* and adding sites based on the "ERP site"
template. The added sites include your Application Adapter automatically. 

Now that you know all about using Application Adapters, let's look at performing
custom actions through hooks. 

## Performing a Custom Action

Hooks are useful for performing custom actions on certain common portal events,
like user login or system startup. The actions performed for each of these
events are defined in `portal.properties`, so to create a custom action we'll
also need to extend this file. Fortunately, hooks make this extremely easy.
Here's how:

1. Create the directory
`example-hook/docroot/WEB-INF/src/com/liferay/sample/hook`, and create the file
`LoginAction.java` inside it with the following content:

    package com.liferay.sample.hook;

    import com.liferay.portal.kernel.events.Action;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;

    public class LoginAction extends Action {
		public void run(HttpServletRequest req, HttpServletResponse res) {
			System.out.println("## My custom login action");
		}
    }

2. Create the file `portal.properties` inside `example-hook/docroot/WEB-INF/src`
with the following content:

    login.events.pre=com.liferay.sample.hook.LoginAction

3. Edit `liferay-hook.xml` inside `example-hook/docroot/WEB-INF` and add the
following line above `<custom-jsp-dir>`:

    <portal-properties>portal.properties</portal-properties>

4. Redeploy your hook. Once deployment is complete, log out and back in, and you
should see your custom message, *## My custom login action*, output to the
terminal window running Liferay. 

Using hooks, you can define custom actions for other events, too. Some actions
will need to extend `com.liferay.portal.kernel.events.Action`, and others will
need to extend `com.liferay.portal.struts.SimpleAction`. 

---

![important](../../images/tip-pen-paper.png)**Important:** To ensure better
forward compatibility, use hooks rather than Ext plugins for customizing Struts
actions. For more information on these events, see the [Properties
Reference](http://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/configuring-liferay-s-properti-1)
chapter of *Using Liferay Portal* or find the actual `portal.properties`
configuration file for your version of Liferay in the [Portal
Properties](http://www.liferay.com/community/wiki/-/wiki/Main/Portal+Properties)
wiki page. 

---

Now you know how to perform a custom action by extending a portal action. Let's
take a look at overriding and adding Struts actions from a hook plugin. 

## Overriding and adding Struts actions

If you'd like to add a new Struts action to Liferay portal from a hook plugin,
or override other existing actions, we have good news. *Struts action hooks* let
you do just that. 

Let's start by considering the interfaces used for Struts actions. There are
two:

-   `com.liferay.portal.kernel.struts.StrutsAction`
-   `com.liferay.portal.kernel.struts.StrutsPortletAction`

The `StrutsAction` interface is for regular Struts actions from the portal, like
`/c/portal/update_email_address`. The `StrutsPortletAction` interface is used
for similar Struts actions, but from portlets. 

Struts actions are defined as classes, and they're all connected in a
`struts-config.xml` file. A `struts-config.xml` for Liferay portal running on
Apache Tomcat is found in the
`liferay-portal-<version>/tomcat-<version>/webapps/ROOT/WEB-INF` directory. The
`struts-config.xml` file links actions to specific JSP pages. Each action
performs a specific task and then returns a *forward*, which is an object
containing a name and path. The forward defines what page the user will go to
after the action completes. When a user submits a form that maps to one of these
actions, the action class is loaded, executed, and returns a forward. 

You can use a Struts action hook to wrap/override existing Struts actions or to
create a new Struts path; we'll demonstrate both here. We'll override the Struts
actions located in the `struts-config.xml` using a Struts action hook to point
to a custom class, then we'll create a new Struts path: `/c/portal/sample` and
navigate to it. Let's get started! 

First lets' override the login portlet's Struts action using the example-hook
we created earlier in this chapter. 

Note the current action in your portal's `struts-config.xml` file:

    <action path="/login/login"
        type="com.liferay.portlet.login.action.LoginAction">
        <forward name="portlet.login.login" path="portlet.login.login" />
        <forward name="portlet.login.login_redirect" path="portlet.login.login_redirect" />
    </action>

1. Navigate to your `example-hook/docroot/WEB-INF` folder and open the
`liferay-hook.xml` file. 

2. Insert the following code before the closing `</hook>` tag:

        <struts-action>
            <struts-action-path>/portal/sample</struts-action-path>
            <struts-action-impl>com.liferay.sample.hook.action.ExampleStrutsAction</struts-action-impl>
        </struts-action>
        <struts-action>
            <struts-action-path>/login/login</struts-action-path>
            <struts-action-impl>com.liferay.sample.hook.action.ExampleStrutsPortletAction</struts-action-impl>
        </struts-action>

3. Create a new package `com.liferay.sample.hook.action` in your
`example-hook/docroot/WEB-INF/src` folder. 

4. In this new package, create a class named *ExampleStrutsPortletAction*. This
class will wrap the login portlet Struts action. Insert the following code:

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
                ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest
                        .getAttribute(WebKeys.THEME_DISPLAY);
    
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

5. Create a new class named *ExampleStrutsAction* in the
`com.liferay.sample.hook.action` package. This class will implement your new
portal Struts action. Insert the following code:

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

    Note, we've overridden the `execute(HttpServletRequest,
    HttpServletResponse)` method of BaseStrutsAction, but not the
    `execute(StrutsAction, HttpServletRequest, HttpServletResponse)` method, so
    the original Struts action's `execute()` method will be ignored. That's fine
    for our example. 

    **Best Practice**

	Typically, when overriding an existing Struts action, it's best to
	override the method that takes the original Struts action handle as a
	parameter and execute that original Struts action, if that's your
	intention. Think of the original action as a servlet filter or aspect.
	If you override the method that *takes* the original action handle as a
	parameter and don't explicitly execute it, the original action won't be
	executed. If you override the execute method that *does not take*
	original action as a parameter, you are ignoring the original action and
	it won't be executed. 
	
	---

	![Tip](../../images/tip-pen-paper.png)**Warning:** There's a classloading
	bug that manifests itself under certain conditions when executing an
	original Struts action. The issue is fixed in
	[LPS-30162](http://issues.liferay.com/browse/LPS-30162). As a workaround, if
	you don't have the fix pack for this fix, you can set the context
	classloader to the portal classloader ant execute the original action in a
	`try` block. Then in a corresponding `finally` block, reset the classloader
	back to the original classloader.

	Here's an example for the temporary workaround:
	
		public class SampleLayoutStrutsAction extends BaseStrutsAction {
	
			@Override
			public String execute(StrutsAction originalStrutsAction,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	
				System.out.println("SampleLayoutStrutsAction.execute() - " +
					request.getRequestURI());
	
				Thread currentThread = Thread.currentThread();
				ClassLoader contextClassLoader = currentThread.getContextClassLoader();
				currentThread.setContextClassLoader(PortalClassLoaderUtil.getClassLoader());
	
				try {
					return originalStrutsAction.execute(request, response);
				}
				finally {
					currentThread.setContextClassLoader(contextClassLoader);
				}
			}
	
		}
	
	---

    That's it for implementing the override of the Struts actions! Let's get our
    new Struts path working next. 

6. Create a JSP named `sample.jsp` in the
`example-hook/docroot/META-INF/custom_jsps/html/portal` directory. Insert the
following code:

        <%
        String name = (String)request.getAttribute("name");
        %>
        Hello <%= name %>!

7. Add `/portal/sample` to your portal's list of paths that don't require
authentication. 

    7.1. Copy your existing `auth.public.paths` property assignment from your
    portal's `portal.properties` into your `portal-ext.properties` file. 

    7.2. Add `/portal/sample` to the end of the value list. It'll look similar
    to the assignment below:

        auth.public.paths=\
            /asset/get_categories,\
            ...
            /wiki/rss,\
            /portal/sample

8. Restart your portal server. 

Congratulations! Your Struts action hook plugin is complete! Now when you access
the *Sign In* portlet, this message prints to your console:

    Wrapped /login/ action

When you actually log in, this message prints to your console:

    Wrapped /login/ action2
    Wrapped /login/ action

Both custom Struts actions are executed via your Struts action hook! 

Try your new Struts path by accessing it from your browser (e.g.
`http://localhost:8080/c/portal/sample`).

![Figure 6.7: Your new Struts action displays *Hello World!* in your
browser.](../../images/06-hooks-5.png)

Extending and overriding portal properties is just as easy, so let's do that
next. 

## Extending and Overriding *portal.properties*

In our hook that created a custom login action above, we modified the
`login.events.pre` portal property of the `portal.properties` file. This
property accepts *multiple* values, so our value was appended to the existing
`login.events.pre` values, and we could repeatedly modify the property from
additional hooks. However, some portal properties only accept a *single* value,
such as the `terms.of.use.required` property, which can be either `true` or
`false`. Only modify single value properties from one hook; otherwise Liferay
won't know which value to use. 

To determine whether a property accepts multiple values or not, look in the
[Configuring Liferay's
Properties](http://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/configuring-liferay-s-properties)
section of [Using Liferay Portal
6.1](http://www.liferay.com/documentation/liferay-portal/6.1/user-guide) or in
the `portal.properties` file. 

---

    ![note](../../images/tip-pen-paper.png)**Note:** There are portal properties
    you can't override in a hook. To find A complete list of those you can, look
    in the `liferay-hook-[liferay version].dtd` in the `definitions` folder of
    the Liferay source code. In addition to defining custom actions, hooks can
    also override portal properties to define model listeners, validators,
    generators, and content sanitizers. 

---

Next, let's override a portal service with a hook. 

## Overriding a Portal Service 

All the functionality provided by Liferay is encapsulated behind a layer of
services accessed from portlets at the frontend. This architecture let's you
change how a core portlet of Liferay behaves without changing the portlet
itself, by customizing the backend services that it uses. This section explains
how to leverage this architecture, from a hook plugin, to customize portal
service behavior. 

Liferay automatically generates dummy wrapper classes for its services. For
example, `UserLocalServiceWrapper` is created as a wrapper for
`UserLocalService` -- a service for adding, removing, and retrieving user
accounts. To modify the functionality of `UserLocalService` from our hook, just
create a class that extends `UserLocalServiceWrapper`, override its methods
whose behavior you want to modify, and instruct Liferay to use your service
class instead of the original.

1. Inside `example-hook/docroot/WEB-INF/src/com/liferay/sample/hook` create
a new file called `MyUserLocalServiceImpl.java` with the following content:

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

 ![tip](../../images/tip-pen-paper.png)**Note:** the wrapper class
 (`MyUserLocalServiceImpl` in this example) will be loaded in the hook's class
 loader. That means that it will have access to any other class included within
 the same WAR file; but it *won't* have access to *internal* classes of Liferay.

---

2. Edit `liferay-hook.xml`, located in the `example-hook/docroot/WEB-INF`
directory, adding the following after `</custom-jsp-dir>`:

    <service>
		<service-type>com.liferay.portal.service.UserLocalService</service-type>
		<service-impl>com.liferay.sample.hook.MyUserLocalServiceImpl</service-impl>
    </service>

Redeploy your hook and refresh your browser. In the terminal window running
Liferay you should see *## MyUserLocalServiceImpl.getUserById(...)* messages
printed by your hook.

Some other Liferay services that you may need to extend to meet advanced
requirements:

- **OrganizationLocalService:** adds, deletes and retrieves organizations. Also
assigns users to organizations and retrieves the list of organizations of a
given user.

- **GroupLocalService:** adds, deletes and retrieves sites.

- **LayoutLocalService:** adds, deletes, retrieves and manages pages of sites,
organizations and users.

For a complete list of the available services and thir methods check the
[Liferay Portal 6.1 Javadocs](http://docs.liferay.com/portal/6.1/javadocs/) or
look-up the Javadocs for your version of Liferay at
[http://docs.liferay.com/portal](http://docs.liferay.com/portal) and selecting
its *javadocs* link. 

## Overriding a *Language.properties* File

In addition to the uses of hooks we've already discussed, you can override a
`Language.properties` file from a hook. This let's you change any of the
messages displayed by Liferay to suit your needs. The process is similar to
those described above. You create a *Language* file for the language whose
messages you want to customize, then refer to it from your `liferay-hook.xml`.
For example, to override the Spanish and French message translations, create
*Language* files of the same name and similar path in your hook project and
refer to them in your `liferay-hook.xml` file as in the following:

    <hook>
		...
		<language-properties>content/Language_es.properties</language-properties>
		<language-properties>content/Language_fr.properties</language-properties>
		...
    </hook>

---

 ![tip](../../images/tip-pen-paper.png)**Tip:** As always, please check the DTD
 of each Liferay XML file you modify for the elements and attributes that can be
 included in the XML and the specified order for those elements. 

---

## Other hooks 

Since hooks are the preferred plugin type to use in customizing Liferay's core
features, the Liferay team is happy to keep new hooks coming to you. This
section is a placeholder for hooks which are available in Liferay Portal 6.1,
but aren't yet fully documented. For now, here's a summary of these hooks:

- **Servlet filter hook:** Servlet filters allow you to pre-process requests
going *to* a servlet and post-process responses coming *from* a servlet. As
server requests are received that match URL patterns or match servlet names
specified in your servlet filter mappings, your specified servlet filters are
applied. Hook elements `servlet-filter` and `servlet-filter-mapping` have been
added to `liferay-hook.xml` to give you the ability to configure your servlet
filters. For a working example, see the
[sample-servlet-filter-hook](https://github.com/liferay/liferay-plugins/tree/master/hooks/sample-servlet-filter-hook)
in the Plugin SDK. 

- **CMIS extension hook:** The Documents and Media Library now supports multiple
CMIS repositories mounted for each Documents and Media Portlet. If a repository
does not fully implement CMIS or has native features that you'd like to
leverage, you can use a *CMIS extension hook* to apply your desired extension
implementation. 

## Conclusion

In this chapter we discussed some of the many uses of hooks. You learned how to
perform custom portal actions, override and extend custom portal JSPs, modify
portal properties, and replace portal services. Next, we'll talk about using Ext
plugins to make customizations that you can't make with any other Liferay
plugin type. 
