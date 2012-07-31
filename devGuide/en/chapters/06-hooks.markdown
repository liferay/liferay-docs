
# Hooks [](id=hoo-4)

Liferay Hooks are the preferred plugin to use in customizing Liferay's core features. Hooks can fill a wide variety of the common needs for overriding Liferay core functionality. Hooks should be used in place of Ext plugins, whenever possible, as they are hot-deployable and more forward compatible. Some common scenarios to which you can apply a hook are:

-	Performing custom actions on portal startup or user login

-	Overriding or extend portal JSPs

-	Modifying portal properties

-	Replacing portal services with your own implementation

As with portlets, layout templates, and themes, hooks are created and managed using the Plugins SDK. So, let's create and deploy a hook using the SDK.

## Creating a Hook [](id=creating-a-ho-4)

Hooks are stored within the `hooks` directory of the Plugins SDK. We will demonstrate two methods that can be used to create your hooks.

***Using Developer Studio:*** Go to File &rarr; New &rarr; Liferay Project

Next, go through the following steps to setup your new hook:

1. Enter *example* for your Project name and *Example* for your Display name
2. Select the Plugins SDK and Portal Runtime that you've configured
3. Select the *Hook* Plugin Type
4. Click *Finish*

![Figure 6.1: Creating your hook plugin](../../images/06-hooks-1.png)

Notice the Plugins SDK automatically adds "hook" to the project name after its creation. When creating a new plugin in Developer Studio, we can either create a completely new plugin or add a new plugin to an existing plugin project.

***Using the terminal:*** Navigate to your Plugins SDK directory in a terminal and enter the following command to create a new hook (Linux and Mac OS X):

    ./create.sh example "Example"

On Windows, enter the following instead:

    create.bat example "Example"

You should get a BUILD SUCCESSFUL message from Ant, and there will now be a new folder named `example-hook` inside of the `hooks` folder of your Plugins SDK. Notice that the Plugins SDK automatically appends "-hook" to the project name when creating this folder.

### Deploying the Hook [](id=lp-6-1-dgen05-deploying-the-hook-0)

***Using Developer Studio:*** Simply drag your hook project onto your server.

![Figure 6.2: Deploying your hook plugin](../../images/06-hooks-4.png)

Upon deploying your hook, your server will output messages indicating your hook was read, registered and is now available for use.

	Reading plugin package for example-hook
	Registering hook for example-hook
	Hook for example-hook is available for use

If at any time you need to redeploy this plugin while in Developer Studio, right click your plugin's icon located underneath your server and select *Redeploy*.

![Figure 6.3: Redeploying your hook plugin](../../images/06-hooks-2.png)

Unlike our previous portlets and themes examples, the hook plugin does not do anything yet. After a few edits and file additions, you will have a fully functional hook plugin!

***Using the terminal:*** Open a terminal window in your `hooks/example-hook` directory and enter this command:

    ant deploy

You should get a BUILD SUCCESSFUL message, which means that your hook is now being deployed. If you switch to the terminal window running Liferay, and wait for a few seconds, you should see the message "Hook for example-hook is available for use." However, unlike portlets or themes, your new hook doesn't actually do anything yet.

### Anatomy of the Hook [](id=lp-6-1-dgen06-anatomy-of-the-hook-0)

In order to create a useful hook, we will need to edit existing files and create new files within the structure. The full structure of the example-hook is shown below:

-	example-hook/
	-	docroot/
		-	META-INF/
		-	WEB-INF/
			-	lib
			-	liferay-hook.xml
			-	liferay-plugin-package.properties
	-	build.xml
	
In Developer Studio, the hook structure can be viewed in the *Package Explorer* as shown below:

![Figure 6.4: Package Explorer view of hook plugin](../../images/06-hooks-3.png)

## Overriding a JSP [](id=overriding-a-j-4)

One of the simplest tasks a hook can perform is replacing a portal JSP. In this example we will modify your portal's *Terms of Use* page. First, create the directory `hooks/example-hook/docroot/META-INF/custom_jsps`. Next, edit `hooks/example-hook/docroot/WEB-INF/liferay-hook.xml`, and add the following between `<hook></hook>`:

    <custom-jsp-dir>/META-INF/custom_jsps</custom-jsp-dir>

Now, when your hook is deployed, any JSP you place inside the `custom_jsps` directory will replace a corresponding JSP of the same name and relative path found inside your Liferay instance. The directory structure inside this folder must mirror the one within `liferay-portal-[version]/tomcat-[tomcat-version]/webapps/ROOT`. To override the *Terms of Use*, create the directory `hooks/example-hook/docroot/META-INF/custom_jsps/html/portal/` and then copy into that directory the `terms_of_use.jsp` file from `liferay-portal-[version]/tomcat-[tomcat-version]/webapps/ROOT/html/portal/`.

Next, open your copy of the `terms_of_use.jsp` and make any changes you like. Deploy your hook and wait until it is deployed successfully. Then, create a new user and try to log in. When you get to the *Terms of Use* page, you will see your version of the terms instead of those from the default JSP. Please note that this is not the recommended way of changing the *Terms of Use*, it is simply a convenient example. You can actually replace the *Terms of Use* with web content by setting two properties in `portal-ext.properties`. So, this hook is not necessary, but serves as a good way to demonstrate overriding a JSP using a hook.

Now, if you look inside the `liferay-portal-[version]/tomcat-[tomcat-version]/webapps/ROOT/html/portal` directory you will see that there are now *Terms of Use* use JSP files, one called `terms_of_use.jsp` and another called `terms_of_use.portal.jsp`. `terms_of_use.jsp` is the version from your hook, while `terms_of_use.portal.jsp` is the original. If you now undeploy your hook by deleting its directory in `webapps`, you will see that your replacement JSP is removed and the `.portal.jsp` file is renamed again to take its place. In this manner, you can override any JSP in the Liferay core, while also being able to revert your changes by undeploying your hook. Note, however, that it is not possible to override the same JSP from multiple hooks, as Liferay will not know which version to use. Next, we'll look at a different way to customize a JSP.

## Customizing JSPs without overriding the original [](id=customizing-jsps-without-overriding-the-origin-1)

The drawback of overriding a JSP is that if the original changes (for example to fix a bug) then you have to also change your customized file in order to benefit from the change to the original.

If you wish to avoid this drawback and make your JSP modifications less invasive, you can render the original JSP into a string, and then modify it dynamically afterwards. This makes it possible to change minor elements of a JSP, such as adding a new heading or button, without needing to worry about modifying your hook every time you upgrade Liferay. Here is an example that customizes the search portlet removing the ability to a search provider in the browser:

    <%@ include file="/html/portlet/search/init.jsp" %>

	<liferay-util:buffer var="html">
		<liferay-util:include page="/html/portlet/search/view.portal.jsp" />
	</liferay-util:buffer>
	
	<%
	html = StringUtil.add(html, "Enjoy your search!", "\n");
	%>
	
	<%= html %>

Since this technique involves String manipulation, it's mainly useful for making a small number of changes to a JSP.

## Performing a Custom Action [](id=performing-a-custom-acti-4)

Another common use for hooks is to perform custom actions on certain common portal events, such as user login or system startup. The actions that are performed for each of these events are defined in `portal.properties`, which means that in order to create a custom action we will also need to extend this file. Fortunately, this is extremely easy using a hook.

First, create the directory `example-hook/docroot/WEB-INF/src/com/liferay/sample/hook`, and create the file `LoginAction.java` inside it with the following content:

    package com.liferay.sample.hook;

    import com.liferay.portal.kernel.events.Action;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;

    public class LoginAction extends Action {
		public void run(HttpServletRequest req, HttpServletResponse res) {
			System.out.println("## My custom login action");
		}
    }

Next, create the file `portal.properties` inside `example-hook/docroot/WEB-INF/src` with the following content:

    login.events.pre=com.liferay.sample.hook.LoginAction

Finally, edit `liferay-hook.xml` inside `example-hook/docroot/WEB-INF` and add the following line above `<custom-jsp-dir>`:

    <portal-properties>portal.properties</portal-properties>

Deploy your hook again and wait for deployment to complete. Then log out and back in, and you should see your custom message, *## My custom login action*, output to the terminal window running Liferay.

There are several other events for which you can define custom actions using hooks. Some of these actions must extend `com.liferay.portal.kernel.events.Action`, while others must extend `com.liferay.portal.struts.SimpleAction`. Importantly, to ensure better forward compatibility, it is recommended to use hooks rather than Ext plugins for customizing struts actions. For more information on these events, see the [Configuring Liferay's Properties](http://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/configuring-liferay-s-properties) section of [Using Liferay Portal 6.1](http://www.liferay.com/documentation/liferay-portal/6.1/user-guide) or lookup the actual `portal.properties` configuration file for your version of Liferay in the  [Portal Properties](http://www.liferay.com/community/wiki/-/wiki/Main/Portal+Properties) wiki page.

You've learned how to perform a custom action by extending a portal action. So, as you might have expected, it is just as easy to extend or override portal properties. Let's take a look!

## Extending and Overriding *portal.properties* [](id=extending-and-overriding-<em>portal-properties<-e-1)

In our hook, we modified the `login.events.pre` portal property. Since this property accepts a list of values, our value was appended to the existing  `login.events.pre` values of the `portal.properties` file. From multiple hooks, it is safe to modify portal properties that take *multiple* values. But, some portal properties only accept a *single* value, such as the `terms.of.use.required` property which can be either `true` or `false`. Properties that only accept a single value should only be modifified from one hook; otherwise Liferay does not know which value to use. You can look up properties in the [Configuring Liferay's Properties](http://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/configuring-liferay-s-properties) section of [Using Liferay Portal 6.1](http://www.liferay.com/documentation/liferay-portal/6.1/user-guide) or in the `portal.properties` file to determine a property's type.

Not all portal properties can be overridden in a hook. A complete list of the available properties can be found in the `liferay-hook-[liferay version].dtd` in the `definitions` folder of the Liferay source code. In addition to defining custom actions, hooks can also override portal properties to define model listeners, validators, generators, and content sanitizers. Next, let's see how you can override a portal service with a hook.

## Overriding a Portal Service [](id=overriding-a-portal-servi-4)

All of the functionality provided by Liferay is encapsulated behind a layer of services that is accessed from the frontend layer (the portlets). One of the benefits of this architecture is that it is possible to change how a core portlet of Liferay behaves, without changing the portlet itself, by customizing the backend services that it uses. This section explains how to leverage this architecture, from a hook plugin, to customize portal service behavior.

Liferay automatically generates dummy wrapper classes for all of its services. For example, `UserLocalServiceWrapper` is created as a wrapper for `UserLocalService` -- a service for adding, removing, and retrieving user accounts. To modify the functionality of `UserLocalService` from our hook, all you have to do is create a class that extends `UserLocalServiceWrapper`, override its methods whose behavior you want to modify, and instruct Liferay to use your service class instead of the original.

First, inside `example-hook/docroot/WEB-INF/src/com/liferay/sample/hook` create a new file called `MyUserLocalServiceImpl.java` with the following content:

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

![tip](../../images/tip-pen-paper.png)**Note:** the wrapper class (`MyUserLocalServiceImpl` in this example) will be loaded in the hook's class loader. That means that it will have access to any other class included within the same WAR file; but it *won't* have access to *internal* classes of Liferay.

---

Next, edit `liferay-hook.xml` inside `example-hook/docroot/WEB-INF` and add the following after `</custom-jsp-dir>`:

    <service>
		<service-type>com.liferay.portal.service.UserLocalService</service-type>
		<service-impl>com.liferay.sample.hook.MyUserLocalServiceImpl</service-impl>
    </service>

Redeploy your hook and refresh your browser. In the terminal window containing Liferay you should see *## MyUserLocalServiceImpl.getUserById(...)* messages printed by your hook.

Here are some other Liferay services that you may need to extend to meet advanced requirements:

-   **OrganizationLocalService:** adds, deletes and retrieves organizations. Also assigns users to organizations and retrieves the list of organizations of a given user.

-   **GroupLocalService:** adds, deletes and retrieves sites.

-   **LayoutLocalService:** adds, deletes, retrieves and manages pages of 	sites, organizations and users.

For a complete list of the services available and the methods of each of them check the [Liferay Portal 6.1 Javadocs](http://docs.liferay.com/portal/6.1/javadocs/) or look-up the Javadocs for your version of Liferay by browsing for your Liferay version at [http://docs.liferay.com/portal](http://docs.liferay.com/portal) and selecting its *javadocs* link.

## Overriding a *Language.properties* File [](id=overriding-a-<em>language-properties<-em>-fi-1)

In addition to the capabilities of hooks already discussed thus far, you can also override a `Language.properties` file from a hook, allowing you to change any of the messages displayed by Liferay to suit your needs. The process is extremely similar to the ones we have just described. All you need to do is create a *Language* file for the language whose messages you want to customize and then refer to it from your `liferay-hook.xml`. For example, to override the Spanish and French message translations, create *Language* files of the same name and similar path in your hook project and refer to them in your `liferay-hook.xml` file as in the following:

    <hook>
		...
		<language-properties>content/Language_es.properties</language-properties>
		<language-properties>content/Language_fr.properties</language-properties>
		...
    </hook>

---

![tip](../../images/tip-pen-paper.png)**Tip:** As always, please check the DTD of each Liferay XML file you modify for the elements and attributes that can be included in the XML and the specified order for those elements.

---

## Other hooks [](id=other-hooks)

Since hooks are the preferred plugin type to use in customizing Liferay's core features, the Liferay team is happy to keep new hooks coming to you. This section is a placeholder for hooks which are available in Liferay Portal 6.1, but are not yet fully documented. So, for now, here is a summary of these hooks.

-	**Struts action hook:** If you've wanted to add new struts actions to Liferay portal from a hook plugin or have wanted the ability to override other existing actions, you now have the means to do it with *struts action hooks*. Found in the Plugin SDK, the [sample-struts-action-hook](https://github.com/liferay/liferay-plugins/tree/master/hooks/sample-struts-action-hook) demonstrates the hook's capability. See also article [Overriding and adding struts actions from hook plugins ](http://www.liferay.com/about-us/news/-/blogs/7132115) by Mika Koivisto for more details.

-	**Servlet filter hook:** Servlet filters allow you to pre-process requests going *to* a servlet and post-process responses coming *from* a servlet. As server requests are received that match URL patterns or match servlet names specified in your servlet filter mappings, your specified servlet filters are applied. Hook elements `servlet-filter` and `servlet-filter-mapping` have been added to `liferay-hook.xml` to give you the ability to configure your servlet filters. For a working example, see the [sample-servlet-filter-hook](https://github.com/liferay/liferay-plugins/tree/master/hooks/sample-servlet-filter-hook) in the Plugin SDK.

-	**CMIS extension hook:** The Documents and Media Library now supports multiple CMIS repositories mounted for each Documents and Media Portlet. But, in cases where a repository does not fully implement CMIS or where a repository has native features that you'd like to leverage, you can use a *CMIS extension hook* to apply your desired extension implementation.

-	**Application adapter:** Unlike standard hooks, application adapters allow you to scope changes to a Site. In Liferay 6.1, overwriting of JSPs is supported by application adapters; that is, you can create application adapters that allow you to overwrite JSPs for a specific Sites in your portal. See [Application Adapters](http://www.liferay.com/community/wiki/-/wiki/Main/Application+Adapters) by Julio Camarero for more details. Download the Plugin SDK [sample-application-adapter-hook](https://github.com/liferay/liferay-plugins/tree/master/hooks/sample-application-adapter-hook) and try it out.

## Conclusion [](id=conclusi-2)

In this chapter, you've learned how to perform custom portal actions, override and extend custom portal JSPs, modify portal properties, and replace portal services. These are just a few of the ways you can use Liferay hooks. Next, you'll learn how you can, as a last resort, use Ext plugins to make customizations that you can't make with any of the other Liferay plugin types.

