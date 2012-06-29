
# Ext plugins [](id=ext-plugi-4)

Ext plugins provide the most powerful method of extending Liferay. This comes with some tradeoffs in complexity, and so Ext plugins are designed to be used only in special scenarios in which no other plugin type can meet the needs of the project.

Before deciding to use an Ext plugin it's important to understand the costs of using such a powerful tool. The main one is the cost in terms of maintenance. Because Ext plugins allow using internal APIs or even overwriting files provided in the Liferay core, it's necessary to review all the changes done when updating to a new version of Liferay (even if it's a maintenance version or a service pack). Also, unlike the other types of plugins, Ext plugins require the server to be restarted after deployment, and require additional steps when deploying or redeploying to production systems.

The main use cases in which an Ext plugin may be needed are:

-   Specifying custom classes as portal property values. For example, to specify a property that needs a custom class (e.g. `global.startup.events=my.custom.MyStartupAction`), an Ext plugin is the only way to add that custom class to the portal class loader.

-   Providing custom implementations for any of the Liferay beans declared in Liferay's Spring files (use service wrappers from a hook instead if possible)

-   Adding JSPs that are referenced from portal properties that can only be changed from an Ext plugin (be sure to check if the property can be modified from a hook plugin before doing this)

-   Direct overwriting of a class (not recommended unless it's strictly necessary)

As we investigate these use cases we will discuss the following topics as well:

-	*Creating an Ext plugin:* Create an Ext plugin and learn its structure

-	*Developing an Ext plugin:* How to develop and deploy an Ext plugin and learn advanced customization techniques

-	*Deploying in production:* Compares two methods for deployment of an Ext plugin to production

-	*Migrating old extension environments:* How to migrate from extension environments to Ext plugins

-	*Conclusions:* Final thoughts and suggestions regarding Ext plugins

Let's start by creating an Ext plugin.

## Creating an Ext plugin [](id=creating-an-ext-plug-4)

Ext plugins are stored within the `ext` directory of the Plugins SDK (see chapter *The Plugins SDK*). You can create your Ext plugin in Liferay Developer Studio, Liferay IDE, or outside of those environments.

***Using Developer Studio:*** Go to File &rarr; New &rarr; Liferay Project

Next, go through the following steps to setup your new Ext plugin:

1. Fill in *example* for Project name and *Example* for Display name
2. Select the Liferay Plugins SDK and Portal Runtime you've configured
3. Select *Ext* for your Plugin type
4. Click *Finish*

![Figure 7.1: Creating an Ext plugin](../../images/07-ext-plugins-1.png)

Notice the Plugins SDK automatically adds "-ext" to the project name after its creation. When creating a new plugin in Developer Studio, we can either create a completely new plugin or add a new plugin to an existing plugin project. 

***Using the terminal:*** Navigate to the *ext* directory in the Liferay Plugins SDK and enter the following command to create a new Ext plugin (Linux and Mac OS X):
	
	./create.sh example "Example"
	
On Windows, enter the following instead:
	
	create.bat example "Example"
	
You should get a `BUILD SUCCESSFUL` message from Ant, and there should now be a new folder inside of the `ext` folder in your Plugins SDK. Notice that the Plugins SDK automatically appends "-ext" to the project name when creating this folder.

### Anatomy of the Ext Plugin [](id=lp-6-1-dgen07-anatomy-of-the-ext-plugin-0)

You should now find a new folder named `example-ext` with the following structure:

-	example-ext/
	-	docroot/
		-	WEB-INF/
			-	ext-impl/
				-	src/
			-	ext-lib/
				-	global/
				-	portal/
			-	ext-service/
				-	src/
			-	ext-util-bridges/
				-	src/
			-	ext-util-java/
				-	src/
			-	ext-util-taglib/
				-	src/
			-	ext-web/
				-	docroot/
				
You can also view and edit `example-ext` using Developer Studio's *Package Explorer*:

![Figure 7.2: The Ext plugin's Package Explorer](../../images/07-ext-plugins-2.png)

The most significant directories in this structure are the ones inside the `docroot/WEB-INF` directory. In particular, you should be familiar with the following directories:

-   **ext-impl/src:** Contains the `portal-ext.properties` configuration file, custom implementation classes, and in advanced scenarios, classes that override core classes within `portal-impl.jar.`

-   **ext-lib/global:** Any libraries that should be copied to the global classloader of the application server upon deployment of the Ext plugin belong here.

-   **ext-lib/portal:** Any libraries that should be copied inside Liferay's main application belong here. Usually, these libraries are needed because they are invoked from the classes added within `ext-impl/src`.

-   **ext-service/src:** Any classes that should be available to other plugins belong here. When using Service Builder, it puts the interfaces of each service here. Also, in advanced scenarios, this directory contains classes that overwrite the classes of `portal-service.jar`.

-   **ext-web/docroot:** Contains configuration files for the web application, including `WEB-INF/struts-config-ext.xml`, which allows you to customize Liferay's core struts classes. But note, a hook is the recommended means for customizing a struts action. Also, any JSPs that your customizations require belong here.

-   **Other:** `ext-util-bridges`, `ext-util-java` and `ext-util-taglib` are only needed in advanced scenarios in which you need to customize the classes of three libraries provided with Liferay: `util-bridges.jar`, `util-java.jar` and `util-taglib.jar` respectively. In most scenarios, you can just ignore these directories.

By default, several files are added to the plugin. Here are the most significant ones:

-	`build.xml` is the Ant build file for the Ext plugin project.

-	`docroot/WEB-INF/liferay-plugin-package.properties`:  Contains properties of the plugin such as its display name, version, author, and license type.

-   `docroot/WEB-INF/ext-impl/src/portal-ext.properties`: Can be used to override any configuration property of Liferay. However, whenever possible, a hook plugin should be used to override properties. An appropriate case to use an Ext plugin for overriding a property would be if you needed to specify a custom class as a portal property value. As needed, you can use a `portal-ext.properties` file with each of your Ext plugins. But, you should never override the *same* property from multiple `portal-ext.properties` files, as the loading order is not assured and therefore could lead to unintended system behavior.

-   `docroot/WEB-INF/ext-web/docroot/WEB-INF` files:

    -   `portlet-ext.xml`: Can be used to overwrite the definition of a Liferay portlet. In order to do this, copy the complete definition of the desired portlet from `portlet-custom.xml` within Liferay's source code and then apply the necessary changes.

    -   `liferay-portlet-ext.xml`: Similar to the file above, but for the additional definition elements that are specific to Liferay. In order to override it, copy the complete definition of the desired portlet from `liferay-portlet.xml` within Liferay's source code and then apply the necessary changes.

    -   `struts-config-ext.xml` and `tiles-defs-ext.xml`: Can be used to customize the struts actions used by Liferay's core portlets.

---

![tip](../../images/tip-pen-paper.png)**Tip:** After creating an Ext plugin, remove all of the files added by default that are not necessary for the extension. This is important because Liferay keeps track of the files deployed by each Ext plugin. To avoid collisions, it won't allow deploying two Ext plugins if they override the same file. Removing any unnecessary files from an ext plugin makes it easier to use alongside other Ext plugins.

---

You've now created an Ext plugin and have become familiar with its directory structure and its most significant files. Now, it's time to customize Liferay Portal by developing your Ext plugin.

## Developing an Ext plugin [](id=developing-an-ext-plug-4)

Developing an Ext plugin is slightly different than working with other plugin types. The main reason for this is that an Ext plugin, when deployed, makes changes to the Liferay web application itself, instead of staying as a separate component that can be removed at any time. It's important to remember that once an Ext plugin has been deployed, some of its files are *copied* inside the Liferay installation, so the only way to remove its changes is to *redeploy* an unmodified Liferay application again.

The Plugins SDK gives you the capability to deploy and redeploy Ext plugins during your development phase. The redeployment process can involve *cleaning* (i.e. removing) your application server and unzipping your specified Liferay bundle so that you can start from scratch. This guarantees that any changes made to the Ext plugin during development are properly applied and files removed from your plugin by previous changes are not left behind in the Liferay application. This is part of the added complexity when using Ext plugins. Thus, we recommend that you use another plugin type to accomplish your goals, if possible.

Here are the steps that will cover in developing Ext plugins:

-	**Set up:** Configure the Plugin SDK environment to develop Ext plugins for Liferay Portal on your application server

-	**Initial deployment:** How to initially deploy Ext plugins

-	**Redeployment:** How to redeploy Ext plugins

-	**Distribution:** How to package Ext plugins for distribution

-	**Advanced customization techniques:** Cover a variety of examples in using advanced techniques to customize Liferay Portal

Let's get started!

### Set up [](id=lp-6-1-dgen06-set-up-0)

Before attempting to deploy an Ext plugin, it's necessary to edit the `build.{username}.properties` file in the root folder of your Plugins SDK. If this file doesn't exist yet, you should create it. Substitute `{username}` with your user ID on your computer. Once you've opened your build properties file, add the following properties, making sure the individual paths reflect the right locations on your system:

    ext.work.dir={work}

    app.server.dir={work}/liferay-portal-6.1.0-ce-ga1/tomcat-7.0.23

    app.server.zip.name={...}/liferay-portal-tomcat-6.1.0-ce-ga1-20120106155615760.zip

Your `app.server.zip.name` property should specify the path to your Liferay bundle `.zip` file. Your *work* directory, specified by your `ext.work.dir` property, is the destination for the contents of your Liferay bundle `.zip` file. Lastly, your `app.server.dir` property should point to your application server's directory within your *work* directory. Look in your Liferay bundle at the path to the application server directory in order to determine the value you should use for your `app.server.dir` property.

For example, let's specify `C:\work` as our `ext.work.dir` value. Let's say we have a Liferay bundle `.zip` file `C:\downloads\liferay-portal-tomcat-6.1.0-ce-ga1-20120106155615760.zip` which we set as the value for our `app.server.zip.name` property. We discover the *relative path* to the application server *within* our Liferay bundle `.zip` file is `liferay-portal-6.1.0-ce-ga1\tomcat-7.0.23`. So we must specify `C:\work\liferay-portal-6.1.0-ce-ga1\tomcat-7.0.23` as our `app.server.dir` property value.

---

![tip](../../images/tip-pen-paper.png) Note: Some Liferay bundles, come installed with a sample website for a fictional company called 7-Cogs. This sample website is useful for showcasing certain features of Liferay. However, once you've removed it, you don't want the Ant targets to reinstall it every time your bundle is unzipped again. To make sure this doesn't happen, unzip your bundle, delete the `{work}/liferay-portal-6.1.0-ce-ga1/tomcat-7.0.23/webapps/sevencogs-hook` folder, and then re-zip your bundle.

---

Next, we'll make some changes to the Ext plugin we created and deploy it.

### Initial deployment [](id=lp-6-1-dgen06-initial-deployment-0)

Once the environment is set up, we are ready to start customizing. We'll show the full process with a simple example -- customizing the sections of a user profile. Liferay allows this configuration to be made through the `portal-ext.properties` configuration file. But we'll be changing a property that cannot be changed from a hook plugin. In order to make this change, open the `docroot/WEB-INF/ext-impl/src/portal-ext.properties` file and paste the following contents inside:

    users.form.update.main=details,password,organizations,sites,roles

This line removes the sections for user groups, personal sites, and categorizations from the user profile page. We might want to make this change to ensure that these entities won't be used in our portal.

Once we've made this change, we are ready to deploy.

#### Deploy the plugin [](id=lp-6-1-dgen06-deploy-the-plugin-0)

Here are instructions for deploying your plugin from Liferay IDE/Studio or the terminal.

***In Developer Studio:*** Simply drag your `example-ext` project from your *Package Explorer* onto your server.

![Figure 7.3: Drag-and-drop plugin onto server](../../images/07-ext-plugins-7.png)

***In the terminal:*** Open a terminal window in your `ext/example-ext` directory and enter either of these commands:
	
	1.	ant deploy

	2.	ant direct-deploy

---

![tip](../../images/tip-pen-paper.png)**Tip:** The `direct-deploy` target deploys all the plugin changes directly to the appropriate directories in the Liferay application. If instead you deploy the plugin using the `deploy` target, the Liferay server needs to apply the changes from the resulting `.war` file at startup, possibly slowing down your development process. For this reason, using `direct-deploy` is the usually preferred method for deploying Ext plugins during development. But note, `direct-deploy` does not work with WebLogic Server or WebSphere application server environments.

---

Upon deployment, you should get a `BUILD SUCCESSFUL` message, which means that your plugin is now being deployed. If you switch to the console window running Liferay and wait for a few seconds, you should see the message:

	Extension environment for example-ext has been applied. You must reboot the server and redeploy all other plugins

It may not be absolutely necessary to redeploy all other plugins, but it's a best practice. You should certainly redeploy all plugins if any of the changes applied through the Ext plugin could affect the deployment process itself.

The `ant deploy` target builds a `.war` file with all the changes you have made and copies them to the auto-deploy directory inside the Liferay installation. When the server starts, it detects the `.war` file, inspects it, and copies its content to the appropriate destinations within the deployed and running Liferay application inside your application server. You must now restart your application server to *publish* your changes.

#### Publish the plugin [](id=lp-6-1-dgen06-publish-the-plugin-0)

You need to publish your plugin to the Liferay server to complete the deployment process. You can do so in Liferay IDE/Studio or in the terminal.

***Using Developer Studio:*** While selecting the Liferay server in the *Servers* view, select the server's *Publish* option

![Figure 7.4: How to publish the Ext Plugin](../../images/07-ext-plugins-3.png)

***Using the terminal:*** Restart the Liferay server

Let's try our Ext plugin within our portal. Once the server has started, log in as an administrator and go to *Control Panel &rarr; Users and Organizations*. Edit an existing user and verify that the right navigation menu only shows the five sections that were referenced from the `users.form.update.main` property.

![Figure 7.5: You should see these five sections under the User Information heading](../../images/ext-plugin-five-sections.png)

Once you've applied this simple modification to Liferay, we'll proceed with a slightly more complex customization. This will give us an opportunity to learn the proper way to *redeploy* an Ext plugin, which is different from *initial deployment*.

For this example, we'll customize the *details* section of the user profile. We could do that just by overwriting its JSP, but this time we'll use a more powerful method which also allows adding new sections or even merging the existing ones. Liferay allows referring to custom sections from the `portal-ext.properties` and implementing them just by creating a JSP. In our case, we'll modify the property `users.form.update.main` once again and also modify property `users.form.add.main` to set the following:

	users.form.add.main=basic,organizations,personal-site
	users.form.update.main=basic,password,organizations,sites,roles

That is, we removed the *details* section and added a new custom one called *basic*. When Liferay Portal's user administration reads this property, it looks for the implementation of each section based on the following conventions:

-   The section should be implemented in a JSP inside the directory:

	ext-web/docroot/html/portlet/users_admin/user/

-   The name of the JSP should be like the name of the section plus the `.jsp` extension. There is one exception. If the section name has a dash sign (`"-"`), it will be converted to an underscore sign (`"_"`). For example, if the section is called *my-info*, the JSP should be named `my_info.jsp`. This is done to comply with common JSP naming standards.

-   The name of the section that will be shown to the user comes from the language bundles. When using a key/value that is not already among the ones included with Liferay, you should add the key/value to the your Ext plugin's `Language-ext.properties` file and the properties file for each of the language variants for which you want to provide a translation. These files belong in the `ext-impl/src` directory of your Ext plugin.

For our example, we need to create a file within the Ext plugin with the following path:

    ext-web/docroot/html/portlet/users_admin/user/basic.jsp

For the contents of the file, we can write them from scratch or make a copy of the `details.jsp` file from Liferay's source code and modify from there. We decided to do the latter and then remove some fields, leaving the screen name, email address, first name, and last name fiels to simplify user creation and user update. Here's the resulting JSP code:

	<%@ include file="/html/portlet/users_admin/init.jsp" %>
	
	<%
	User selUser = (User)request.getAttribute("user.selUser");
	Contact selContact = (Contact)request.getAttribute("user.selContact");
	%>
	
	<liferay-ui:error-marker key="errorSection" value="details" />
	
	<aui:model-context bean="<%= selUser %>" model="<%= User.class %>" />
	
	<h3><liferay-ui:message key="details" /></h3>
	
	<aui:fieldset column="<%= true %>" cssClass="aui-w50">
		<liferay-ui:success key="verificationEmailSent" message="your-email-verification-code-has-been-sent-and-the-new-email-address-will-be-applied-to-your-account-once-it-has-been-verified" />
	
		<liferay-ui:error exception="<%= DuplicateUserScreenNameException.class %>" message="the-screen-name-you-requested-is-already-taken" />
	
		<liferay-ui:error exception="<%= GroupFriendlyURLException.class %>">
	
			<%
			GroupFriendlyURLException gfurle = (GroupFriendlyURLException)errorException;
			%>
	
			<c:if test="<%= gfurle.getType() == GroupFriendlyURLException.DUPLICATE %>">
				<liferay-ui:message key="the-screen-name-you-requested-is-associated-with-an-existing-friendly-url" />
			</c:if>
		</liferay-ui:error>
	
		<liferay-ui:error exception="<%= ReservedUserScreenNameException.class %>" message="the-screen-name-you-requested-is-reserved" />
		<liferay-ui:error exception="<%= UserScreenNameException.class %>" message="please-enter-a-valid-screen-name" />
	
		<c:if test="<%= !PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.USERS_SCREEN_NAME_ALWAYS_AUTOGENERATE) || (selUser != null) %>">
			<c:choose>
				<c:when test="<%= PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.USERS_SCREEN_NAME_ALWAYS_AUTOGENERATE) || ((selUser != null) && !UsersAdminUtil.hasUpdateScreenName(permissionChecker, selUser)) %>">
					<aui:field-wrapper name="screenName">
						<%= selUser.getScreenName() %>
	
						<aui:input name="screenName" type="hidden" value="<%= selUser.getScreenName() %>" />
					</aui:field-wrapper>
				</c:when>
				<c:otherwise>
					<aui:input name="screenName" />
				</c:otherwise>
			</c:choose>
		</c:if>
	
		<liferay-ui:error exception="<%= DuplicateUserEmailAddressException.class %>" message="the-email-address-you-requested-is-already-taken" />
		<liferay-ui:error exception="<%= ReservedUserEmailAddressException.class %>" message="the-email-address-you-requested-is-reserved" />
		<liferay-ui:error exception="<%= UserEmailAddressException.class %>" message="please-enter-a-valid-email-address" />
	
		<c:choose>
			<c:when test="<%= (selUser != null) && !UsersAdminUtil.hasUpdateEmailAddress(permissionChecker, selUser) %>">
				<aui:field-wrapper name="emailAddress">
					<%= selUser.getDisplayEmailAddress() %>
	
					<aui:input name="emailAddress" type="hidden" value="<%= selUser.getEmailAddress() %>" />
				</aui:field-wrapper>
			</c:when>
			<c:otherwise>
	
				<%
				User displayEmailAddressUser = null;
	
				if (selUser != null) {
					displayEmailAddressUser = (User)selUser.clone();
	
					displayEmailAddressUser.setEmailAddress(displayEmailAddressUser.getDisplayEmailAddress());
				}
				%>
	
				<aui:input bean="<%= displayEmailAddressUser %>" model="<%= User.class %>" name="emailAddress">
					<c:if test="<%= PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.USERS_EMAIL_ADDRESS_REQUIRED) %>">
						<aui:validator name="required" />
					</c:if>
				</aui:input>
			</c:otherwise>
		</c:choose>
	
		<liferay-ui:error exception="<%= ContactFirstNameException.class %>" message="please-enter-a-valid-first-name" />
		<liferay-ui:error exception="<%= ContactFullNameException.class %>" message="please-enter-a-valid-first-middle-and-last-name" />
	
		<aui:input name="firstName" />
	
		<liferay-ui:error exception="<%= ContactLastNameException.class %>" message="please-enter-a-valid-last-name" />
	
		<aui:input name="lastName">
			<c:if test="<%= PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.USERS_LAST_NAME_REQUIRED, PropsValues.USERS_LAST_NAME_REQUIRED) %>">
				<aui:validator name="required" />
			</c:if>
		</aui:input>
	</aui:fieldset>

In our case, we don't need to add a new key to `Language-ext.properties`, because an entry for the key named *basic* is already included in Liferay's language bundle.

We are now ready to redeploy our Ext plugin so we can check the changes we made.

### Redeployment [](id=lp-6-1-dgen06-redeployment-0)

So far, the development process has been very similar to that of other plugin types. The differences appear when you need to redeploy an Ext plugin. As mentioned earlier, when the plugin is first deployed, some of its files are *copied* into the Liferay installation. After making any change to an Ext plugin, we recommend that you consider the following redeployment approaches:

**Clean Redeployment:** If you removed part(s) of your plugin, if there are changes to your plugin that can affect the deployment of plugins, or if your simply want to start with a clean Liferay environment, we recommend you *undeploy* your plugin and *clean* your application server before redeploying your Ext plugins. By *cleaning* the application server, the existing Liferay installation is removed and the bundle specified in your Plugin SDK environment (e.g. value of `app.server.zip.name` in `build.{username}.properties`) is unzipped in its place. Here are the steps to take depending on whether you are working from Liferay IDE/Studio or the terminal:

-	***From Developer Studio:***

	1.	Remove the plugin from the server: While selecting the Ext plugin in the *Servers* view, select the plugin's *Remove* option

		![Figure 7.6: Removing Ext Plugin from the server](../../images/07-ext-plugins-4.png)

	2.	Clean the application server: While selecting the Ext plugin project in the *Package Explorer* view, select the plugin's *Liferay* &rarr; *Clean App Server...* option

		![Figure 7.7: How to clean app server](../../images/07-ext-plugins-5.png)

	3.	Start the Liferay server

		![Figure 7.8: Start the Liferay server](../../images/07-ext-plugins-6.png)

	4.	Drag-and-drop the Ext plugin to the Liferay server

		![Figure 7.9: Drag-and-drop plugin onto server](../../images/07-ext-plugins-7.png)

	5.	While selecting the Liferay server in the *Servers* view, select the server's *Publish* option

		![Figure 7.10: Publish your server](../../images/07-ext-plugins-8.png)

-	***From the terminal:***
	
	1.	Stop the Liferay server

	2.	For each Ext plugin to be deployed ...

		I.	`cd <your-plugin-ext>`

		II.	`ant clean-app-server`

		III.	`ant direct-deploy`

	3.	Start the Liferay server

**Redeployment:** If you've only added parts to your plugin or made modifications that do not affect the plugin deployment process, you can choose the option to redeploy.

-	***Using Developer's Studio:*** Right click your plugin located underneath your server and select *Redeploy*.

	![Figure 7.11: How to redeploy your Ext plugin](../../images/07-ext-plugins-9.png)

-	***Using the terminal:*** Redeploy and publish the plugin as you initially did, as described in section *Initial deployment*.

After your `example-ext` plugin is published on Liferay Portal, you can see the *basic* details page by choosing to add a user or view an existing user.

![Figure 7.12: You should only see user fields for screen name, email address, first name, and last name](../../images/ext-plugin-user-basic-details.png)

After you've completed developing your Ext plugin, you'll want to package it up for distribution and production.

### Distribution [](id=lp-6-1-dgen06-distribution-0)

Once you have finished developing your plugin, you can package it up in a `.war` file for distribution and production deployment.

-	***Using Developer Studio:*** While selecting the Ext plugin project in the *Package Explorer* view, select the project's *Liferay* &rarr; *SDK* &rarr; *war* option

![Figure 7.13: Accessing the *war* option](../../images/07-ext-plugins-10.png)

-	***Using the terminal:***

	1.	`cd <your-plugin-ext>`

	2.	`ant war`

The `.war` file is written to your `[liferay-plugins]/dist` directory.

Now that you've learned the basics of Ext plugin development, let's look at some advanced customizations that you can do.

### Advanced customization techniques [](id=lp-6-1-dgen06-advanced-customization-techniques-0)

This section covers additional customization techniques that are possible through an Ext plugin. As mentioned above, you can change almost everything within Liferay when using an Ext plugin. But you must be very careful.

Always keep in mind that with every new Liferay version, implementation classes may have changed. Thus, if you've changed Liferay source code directly, you may have to merge your changes into the newer Liferay version. The general approach for minimizing conflicts is not to change anything, only extend.

Alternatively, you can extend the class you want to change and override the required methods. Then use some of Liferay's configuration files to reference your subclass as a replacement for the original class.

In the subsections that follow, we'll cover:

-	**Using advanced configuration files**

-	**Changing the API of a core service**

-	**Replacing core classes in portal-impl**

Let's check out how to use advanced configuration files next.

#### Using advanced configuration files [](id=lp-6-1-dgen06-using-advanced-configuration-files-0)

Liferay uses several internal configuration files for easier maintenance and also to configure the libraries and frameworks it depends on, like Struts and Spring. For advanced customization needs, it may be useful to override the configuration specified in these files. Liferay provides a clean way to do this from an Ext plugin without modifying the original files.

Next, we present a list of all these files, along with a description and a reference to the original file in the path where they can be found in the source code of Liferay (you may need to look at them for reference):

-   `ext-impl/src/META-INF/ext-model-hints.xml`

    -   Description: This file allows overwriting the default properties of the fields of the data models used by Liferay's core portlets. These properties determine how the form to create or edit each model is rendered.

    -   Original file in Liferay: `portal-impl/src/META-INF/portal-model-hints.xml`

-   `ext-impl/src/META-INF/ext-spring.xml`

    -   Description: This file allows overwriting the Spring configuration used by Liferay and any of its core portlets. The most common usage is to configure specific datasources or to swap the implementation of a given service with a custom one.

    -   Original files in Liferay: `portal-impl/src/META-INF/*-spring.xml`

-   `ext-impl/src/content/Language-ext_*.properties`

    -   Description: These files allow overwriting the value of any key used by Liferay's UI to support *I18N*.

    -   Original file in Liferay: `portal-impl/src/content/Language-*.properties`

-   `ext-impl/src/META-INF/portal-log4j-ext.xml`

    -   Description: This file allows overwriting the log4j configuration. The most common usage is to increase or decrease the log level of a given package or class, to obtain more information or hide unneeded information from the logs.

    -   Original file in Liferay: `portal-impl/src/META-INF/portal-log4j.xml`

-   `ext-impl/src/com/liferay/portal/jcr/jackrabbit/dependencies/repository-ext.xml`

    -   Description: This file allows overwriting the configuration of the Jackrabbit repository. Refer to the Jackrabbit configuration documentation for details ([http://jackrabbit.apache.org/jackrabbit-](http://jackrabbit.apache.org/jackrabbit-configuration.html)[configuration.html](http://jackrabbit.apache.org/jackrabbit-configuration.html))

    -   Original file in Liferay: `portal-impl/src/com/liferay/portal/jcr/jackrabbit/dependencies/repository.xml`

-   `ext-web/docroot/WEB-INF/portlet-ext.xml`

    -   Description: This file allows overwriting the declaration of the core portlets included in Liferay. The most common usage is to change the init parameters or the roles specified.

    -   Original file in Liferay: `portal-web/docroot/WEB-INF/portlet-custom.xml`

-   `ext-web/docroot/WEB-INF/liferay-portlet-ext.xml`

    -   Description: This file allows overwriting the Liferay-specific declaration of the core portlets included in Liferay. Refer to the `liferay-portlet-app_6_1_0.dtd` file for details on all the available options. Use this file with care since the code of the portlets may be assuming some of these options to be set to certain values.

    -   Original file in Liferay: `portal-web/docroot/WEB-INF/liferay-portlet.xml`

-   `ext-web/docroot/WEB-INF/liferay-display.xml`

    -   Description: This file allows overwriting the portlets that will be shown in the "Add application" pop-up and the categories in which they'll be organized. The most common usage is to change the categorization, hide some portlets or make some Control Panel portlets available to be added to a page.

    -   Original file in Liferay: `portal-web/docroot/WEB-INF/liferay-display.xml`

-   `ext-web/docroot/WEB-INF/liferay-layout-templates-ext.xml`

    -   Description: This file allows specifying custom template files for each of the layout templates provided by default with Liferay. You should not need to do this except for very advanced needs.

    -   Original file in Liferay: `portal-web/docroot/WEB-INF/liferay-layout-templates.xml`

-   `ext-web/docroot/WEB-INF/liferay-look-and-feel-ext.xml`

    -   Description: This file allows changing the properties of the     default themes provided by default with Liferay. You should not         need to do this except for very advanced needs.

    -   Original file in Liferay:         `portal-web/docroot/WEB-INF/liferay-look-and-feel.xml`

#### Changing the API of a core service [](id=lp-6-1-dgen06-changing-the-api-of-a-core-service-0)

One advanced customization need that appears in some scenarios is to change the API of a method provided by one of Liferay's services, like `UserLocalService`, for example.

Is it possible to do that? The short answer is no. The long answer is that you can, but it will require modifying Liferay's source code directly and making manual changes to quite a few files. The good news is that there is a better alternative to achieve the end goal.

The best way to extend an existing service is to create a complementary custom service, for example a `MyUserLocalService` that includes all the new methods. Your custom code can invoke this service instead of the default service and the implementation of your service can invoke the original service as needed.

Note that this technique does not require an Ext plugin since it can be done from portlet plugins. In fact, using Service Builder for an Ext plugin is deprecated but is supported for migration from the old extension environment.

In some advanced circumstances, it's desired to change the implementation of the original service to call your custom one, which can only be done from an Ext plugin. To achieve this, override the Spring definition for `UserLocalServiceUtil` in `ext-spring.xml` and point it to your `MyUserLocalServiceImpl` (instead of `UserLocalServiceImpl`). This way both `MyUserLocalServiceUtil` and `UserLocalServiceUtil` will use the same Spring bean: your new implementation.

#### Replacing core classes in portal-impl [](id=lp-6-1-dgen06-replacing-core-classes-in-portal-impl-0)

If you really need to change a core portal-impl class and this class cannot be replaced in any configuration file, then the best way to avoid conflicts and easily merge with a new portal version is to:

1.  Rename original class (e.g. `DeployUtil` &rarr; `MyDeployUtil`)

2.  Create a new subclass with the old name (e.g. `DeployUtil extends MyDeployUtil`)

3.  Override methods you need to change

4.  Delegate static methods

5.  Use a logger with appropriate class name for both classes (e.g. `DeployUtil`)

This strategy will help you determine what you will need to merge in the future when a new version of Liferay is released.

---

![tip](../../images/tip-pen-paper.png) This is a very advanced technique that, especially if abused, may have a high impact on the maintainability of your code. Try to look for alternatives and if you confirm that this is your only alternative try to apply it only for the short term and get in touch with Liferay's developers to apply the necessary changes to the product source code.

---

## Deploying in production [](id=deploying-in-producti-4)

In production or pre-production environments, it's often not possible to use Ant to deploy web applications. Also, some application servers such as WebSphere or Weblogic have their own deployment tools and it isn't possible to use Liferay's autodeploy process. This section describes two methods for deploying and redeploying Ext plugins in these scenarios.

### Method 1: Redeploying Liferay's web application [](id=lp-6-1-dgen06-method-1-redeploying-liferays-web-application-0)

This method can be used in any application server that supports auto-deploy, such as Tomcat or JBoss. Its main benefit is that the only artifact that needs to be transferred to the production system is your Ext plugin's `.war` file, produced using the `ant war` target. This `.war` file is usually small making it easy to transport. Here are the steps that need to be executed on the server:

1.  Redeploy Liferay:

	If this is the first time you are deploying your Ext plugin to this server, you can skip this step. Otherwise, start by executing the same steps you first used to deploy Liferay on your app server. If you're using a bundle, you can just unzip the bundle again. If you've installed Liferay manually on an existing application server, you'll need to redeploy the Liferay `.war` file and copy the libraries required globally by Liferay and your Ext plugin to the appropriate directory within the application server.

2.  Copy the Ext plugin `.war` into the auto-deploy directory. For a bundled Liferay distribution, the `deploy` folder is in Liferay's *root* folder of your bundle (e.g. `liferay-portal-6.1.0-ce-ga1/`).

3.  Once the Ext plugin is detected and deployed by Liferay, restart the     Liferay server.

### Method 2: Generate an aggregated WAR file [](id=lp-6-1-dgen06-method-2-generate-an-aggregated-war-file-0)

This method can be used for application servers that do not support auto-deploy, such as WebSphere or Weblogic. Its main benefit is that all Ext plugins are merged before deployment to production, so a single `.war` file will contain Liferay plus the changes from one or more Ext plugins. Before deploying the Liferay `.war` file, you'll need to copy the dependency `.jar` files for both Liferay and the Ext plugin to the global application server class loader in the production server. This location varies from server to server; please see [Using Liferay Portal 6.1](http://www.liferay.com/documentation/liferay-portal/6.1/user-guide) for further details for your application server.

To create the aggregated `.war` file, first deploy your Ext plugin. For the remaining steps, let's assume we're using a Liferay Tomcat bundle. Once your plugin is deployed, restart the server so that the plugin is fully deployed and shut the server down again. Now the files are aggregated in your app server. Create a `.war` file by zipping the `webapps/ROOT` folder of Tomcat. Also, copy all the libraries from the `lib/ext` directory of Tomcat, that are associated with all the Ext plugins, to your application server's global classpath, as noted above.

Once you have the aggregated `.war` file follow these steps on the server:

1.  Redeploy Liferay using the aggregated WAR file.

2.  Stop the server and copy the new version of the global libraries to the appropriate directory in the application server.

## Migrating old extension environments [](id=migrating-old-extension-environmen-4)

Ext plugins have been created as an evolution of the extension environment provided in Liferay 5.2 and previous versions of Liferay. Because of this, a common need for projects upgrading from previous versions is to migrate Extension environments into Ext plugins. The good news is that this task is automated and thus relatively easy.

---

![tip](../../images/tip-pen-paper.png)**Tip:** When migrating an extension environment, it's worth considering if all or at least some of the extension environment's features can be moved into other types of plugins such as portlets and hooks. Portlets and hooks are designed to meet specific needs and they are easier to learn. Additionally, they are cheaper to maintain since they often require fewer changes when upgrading to a new version of Liferay.

---

The process of migrating consists of executing a target within the `ext` directory of the Plugins SDK, pointing to the old extension environment and naming the new plugin:

    ant upgrade-ext -Dext.dir=/projects/liferay/ext -Dext.name=my-ext -Dext.display.name="My Ext"

Here is a description of the three parameters used:

-   `ext.dir` is the location of the old Extension Environment.

-   `ext.name` is the name of the Ext plugin that you want to create

-   `ext.display.name` is the display name

After executing the target, you should see the logs of several copy operations that will take files from the extension environment and copy them into the equivalent directory within the Ext plugin (see the section *Creating an Ext plugin* for an explanation of the main directories within the plugin).

When the migration process is complete, some additional tasks will be needed to upgrade the code to the new version of Liferay. Some of the most typical tasks are:

-   Review the uses of Liferay's APIs and adapt them accordingly.

-   Review the changes to the JSPs and merge your changes into the JSPs of the new Liferay version.

-   When using Service Builder you'll need to run `ant build-service` again. It's also recommended to consider moving this code to a portlet plugin, because it is now as powerful and allows for greater modularity and maintainability.

-   If you've implemented portlets in the Extension Environment, migrate them to portlet plugins, as Extension Environment portlets have been deprecated since Liferay Portal 6.0 and support for them is not guaranteed to be available in future Liferay Portal releases.

#### Licensing and Contributing [](id=lp-6-1-dgen06-licensing-and-contributing-0)

Liferay Portal is Open Source software licensed under the LGPL 2.1 license ([http://www.gnu.org/licenses/lgpl-2.1.html](http://www.gnu.org/licenses/lgpl-2.1.html)). If you reuse any code snippet and redistribute it either publicly or to an specific customer, you need to make sure that those modifications are compliant with this license. A common way to do this is to make the source code of your modifications available to the community under the same license, but make sure to read the license text yourself to find the best option that fits your needs.

If the goal of the changes is to fix a bug or to make an improvement that could be of interest to a broader audience, consider contributing it back to the project. That would benefit all other users of the product and also would be good for you since you won't have to maintain the changes when new versions of Liferay come out. You can notify Liferay of bugs or improvements in [issues.liferay.com](issues.liferay.com). There is also a wiki page with instructions on how to contribute to Liferay:

[http://www.liferay.com/community/wiki/-/wiki/Main/Contributing](http://www.liferay.com/community/wiki/-/wiki/Main/Contributing)

## Conclusions [](id=conclusio-8)

Ext plugins are a very powerful way of extending Liferay. There are no limits to what you can use them to customize. For this reason, you should use them carefully. If you find yourself using an Ext plugin, verify if all or part of the desired functionality can be implemented through portlets, hooks or web plugins instead. If you do need to use an Ext plugin, make it as small as possible and make sure you follow the instructions in this guide carefully to avoid issues.
