
# Hooks

Liferay Hooks are the newest type of plugin which Liferay Portal supports. They were introduced late in the development cycle for Liferay Portal 5.1.x, and are now the preferred way to customize Liferay's core features. As with portlets, layout templates, and themes, they are created using the Plugins SDK.

Hooks can fill a wide variety of the common needs for overriding Liferay core functionality. Whenever possible, hooks should be used in place of Ext plugins, as they are hot-deployable and more forward compatible. Some common scenarios which require the use of a hook are the need to perform custom actions on portal startup or user login, overwrite or extend portal JSPs, modify portal properties, or replace a portal service with your own implementation.

## Creating a Hook

Hooks are stored within the `hooks` directory of the plugins directory. Navigate to this directory in terminal and enter the following command to create a new hook (Linux and Mac OS X):

    ./create.sh example "Example"

On Windows, enter the following instead:

    create.bat example "Example"

You should get a BUILD SUCCESSFUL message from Ant, and there will now be a new folder inside of the `hooks` folder in your Plugins SDK. Notice that the Plugins SDK automatically appends "-hook" to the project name when creating this folder.

### Deploying the Hook

Open a terminal window in your `hooks/example-hook` directory and enter this command:

    ant deploy

You should get a BUILD SUCCESSFUL message, which means that your hook is now being deployed. If you switch to the terminal window running Liferay, and wait for a few seconds, you should see the message "Hook for example-hook is available for use." However, unlike portlets or themes, your new hook doesn't actually do anything yet.

## Overriding a JSP

One of the simplest tasks a hook can perform is replacing a portal JSP. In this example we will modify the Terms of Use page. First, create the directory `hooks/example-hook/docroot/META-INF/custom_jsps`. Next, edit `hooks/example-hook/docroot/WEB-INF/liferay-hook.xml`, and add the following between `<hook></hook>`:

    <custom-jsp-dir>/META-INF/custom_jsps</custom-jsp-dir>

Now, any JSP you place inside the *custom_jsps* directory will replace its original inside your Liferay instance when your hook is deployed. The directory structure inside this folder must mirror the one within `liferay-portal-[version]/tomcat-[tomcat-version]/webapps/ROOT`. To override the Terms of Use, copy `liferay-portal-[version]/tomcat-[tomcat-version]/webapps/ROOT/html/portal/terms_of_use.jsp` to `hooks/example-hook/docroot/META-INF/custom_jsps/html/portal/terms_of_use.jsp`. You will have to create all the intervening directories first.

Next, open your copy of the `terms_of_use.jsp` and make a few changes. Deploy your hook and wait until it is deployed successfully. Then, create a new user and try to log in. When you get to the Terms of Use page, you will see your version instead of the default. Please note that this is not the recommended way of changing the Terms of Use, it is simply a convenient example. You can actually replace the Terms of Use with web content by setting two properties in `portal-ext.properties`. A hook is not necessary.

If you look inside the `liferay-portal-[version]/tomcat-[tomcat-version]/webapps/ROOT/html/portal` directory you will see that there are now two terms of use files, one called `terms_of_use.jsp` and another called `terms_of_use.portal.jsp`. `terms_of_use.jsp` is the version from your hook, while `terms_of_use.portal.jsp` is the original. If you now undeploy your hook by deleting its directory in `webapps`, you will see that your replacement JSP is removed and the `.portal.jsp` file is renamed again to take its place. In this manner, you can override any JSP in the Liferay core, while also being able to revert your changes by undeploying your hook. Note however that it is not possible to override the same JSP from multiple hooks, as Liferay will not know which version to use.

### Customizing JSPs without overriding the original

The drawback of overridding a JSP is that if the original changes (for example to fix a bug) then you have to also change your customized file in order to benefit from the original's change.

If you wish to avoid this drawback and make your JSP modifications even less invasive, it is possible to render the original JSP into a string, and then modify it dynamically afterwards. This makes it possible to change minor elements of a JSP, such as adding a new heading or button, without needing to worry about modifying your hook every time you upgrade Liferay. Here is an example that customizes the search portlet to remove the ability to a search provider in the browser:

    <liferay-util:buffer var="html">
		<liferay-util:include page="/html/portlet/search/search.portal.jsp" />
    </liferay-util:buffer>

    <%
	int x = html.indexOf("<div class="add-search-provider">");
	int y = html.indexOf("</div>", x);

	if (x != -1) {
		html = StringUtil.remove(html, html.substring(x, y + 6),
				StringPool.BLANK);
	}
    %>

    <%= html %>

Since this technique involves String manipulation, it's mainly useful for small numbers of changes.

## Performing a Custom Action

Another common use of hooks is to perform custom actions on certain common portal events, such as user log in or system startup. The actions that are performed on each of these events are defined in `portal.properties`, which means that in order to create a custom action we will also need to extend this file. Fortunately, this is extremely easy using a hook.

First, create the directory `example-hook/docroot/WEB-INF/src/com/sample/hook`, and create the file `LoginAction.java` inside it with the following content:

    package com.sample.hook;

    import com.liferay.portal.kernel.events.Action;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;

    public class LoginAction extends Action {
		public void run(HttpServletRequest req, HttpServletResponse res) {
			System.out.println("## My custom login action");
		}
    }

Next, create the file `portal.properties` inside `example-hook/docroot/WEB-INF/src` with the following content:

    login.events.pre=com.sample.hook.LoginAction

Finally, edit `liferay-hook.xml` inside `example-hook/docroot/WEB-INF` and add the following line above `<custom-jsp-dir>`:

    <portal-properties>portal.properties</portal-properties>

Deploy your hook again and wait for it to complete. Then log out and back in, and you should see our custom message in the terminal window running Liferay.

There are several other events that you can define custom actions for using hooks. Some of these actions must extend from `com.liferay.portal.kernel.events.Action`, while others must extend `com.liferay.portal.struts.SimpleAction`. For more information on these events, see the `portal.properties` configuration file for your version of Liferay in: [http://www.liferay.com/community/wiki/-/wiki/Main/Portal+Properties](http://www.liferay.com/community/wiki/-/wiki/Main/Portal+Properties)

### Extending and Overriding *portal.properties*

In our hook, we modified the `login.events.pre` portal property. Since this property accepts a list of values, our value was appended to the existing values. It is safe to modify these portal properties from multiple hooks, and they will not interfere with one another. Some portal properties only accept a single value, such as the `terms.of.use.required` property, which can be either `true` or `false`. You should only modify these properties from one hook, otherwise Liferay will not know which value to use. You can determine which type a particular property is by looking in `portal.properties`.

Not all portal properties can be overridden in a hook. A complete list of the available properties can be found in the DTD for `liferay-hook.xml` in the `definitions` folder of the Liferay source code. In addition to defining custom actions, hooks can also override portal properties to define model listeners, validators, generators, and content sanitizers.

## Overriding a Portal Service

All of the functionality provided by Liferay is encapsulated behind a layer of services that is accessed from the frontend layer (the portlets). One of the benefits of this architecture is that it is possible to change how a core portlet of Liferay behaves without changing the portlet itself, customizing the backend services that it uses. This section explains how to do that from a hook plugin.

Liferay automatically generates dummy wrapper classes for all of its services, for example `UserLocalServiceWrapper` is created as a wrapper of the `UserLocalService` that is used to add, remove and retrieve user accounts. To modify the functionality of `UserLocalService` from our hook, all we have to do is create a class that extends from `UserLocalServiceWrapper`, override some of its methods, and then instruct Liferay to use our class instead of the original.

First, inside `example-hook/docroot/WEB-INF/src/com/sample/hook` create a new file called `MyUserLocalServiceImpl.java` with the following content:

    package com.sample.hook;

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
![tip](../../images/tip-pen-paper.png)Note that the wrapper class (`MyUserLocalServiceImpl` in this example) will be loaded in the hook's class loader. That means that it will have access to any other class included within the same WAR file, but it won't have access to internal classes of Liferay.
---

Next, edit `liferay-hook.xml` inside `example-hook/docroot/WEB-INF` and add the following after `</custom-jsp-dir>`:

    <service>
		<service-type>com.liferay.portal.service.UserLocalService</service-type>
		<service-impl>com.sample.hook.MyUserLocalServiceImpl</service-impl>
    </service>

Redeploy your hook, then refresh your browser. In the terminal window containing Liferay you should see the messages printed by our hook.

Here are some other services of Liferay that you may need to extend to meet advanced requirements:

-   **OrganizationLocalService:** add, delete and retrieve organizations. Also assign users to organizations and retrieve the list of organizations of a given user.

-   **GroupLocalService:** add, delete and retrieve sites.

-   **LayoutLocalService:** add, delete, retrieve and manage pages of 	sites, organizations and users.

For a complete list of the services available and the methods of each of them check the javadocs distributed with your version of Liferay.

## Structs Action Hook (new TODO)

## Servlet Filter Hook (new TODO)

## Overriding a *Language.properties* File

In addition to the three capabilities of hooks already discussed, it is also possible to override `Language.properties` files from a hook, allowing you to change any of the messages displayed by Liferay to suit your needs. The process is extremely similar to any of the ones we have just described. All you need to do is to create a Language file for the language whose messages you want to customize and then refer to it from the `liferay-hook.xml`. For example, to override the translations to Spanish and French the following two lines would be added to the file:

    <hook>
		...
		<language-properties>content/Language_es.properties</language-properties>
		<language-properties>content/Language_fr.properties</language-properties>
		...
    </hook>

