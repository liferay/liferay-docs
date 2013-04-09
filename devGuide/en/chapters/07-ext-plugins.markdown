# Ext plugins 

Ext plugins are a powerful tool for extending Liferay. Because they increase the
complexity of your Liferay instance, you should only employ an Ext plugin if
you're sure you can't accomplish your goal using a different tool; check out the
hook plugins in Chapter 6 for the available alternatives. If a hook won't
suffice, keep reading to discover the use cases for Ext plugins and how to set
one up. First let's talk about why you should avoid Ext plugins when possible. 

As someone once said, "With great power comes great responsibility" (okay, many
people have said that many times; let's not argue about who said it first).
Before deciding to use an Ext plugin, weigh the costs of using such a powerful
tool. Ext plugins allow using internal APIs or even overwriting files provided
in the Liferay core; when updating to a new version of Liferay (even if it's a
maintenance version or a service pack), you have to review all changes and
manually modify your Ext plugin wherever necessary. Additionally, Ext plugins
aren't hot deployable--you'll have to restart the server after each
redeployment. Lastly, with Ext plugins, additional steps are required to deploy
or redeploy to production systems. 

Now you know why not to use an Ext plugin, but they can be useful. Let's look at
their primary uses: 

- Specifying custom classes as portal property values. For example, to specify a
property that needs a custom class (e.g.
`global.startup.events=my.custom.MyStartupAction`), you need an Ext plugin to
add your custom class to the portal class loader. 

- Providing custom implementations for any Liferay beans declared in Liferay's
Spring files (when possible, use service wrappers from a hook instead of an Ext
plugin). 

- Adding any JSPs referenced from portal properties that can only be changed
from an Ext plugin (check whether the property can be modified from a hook
plugin first). 

- Overwriting a class (not recommended unless it's strictly necessary). 

With these use cases in mind, we'll discuss the following topics: 

- Creating an Ext plugin 

- Developing an Ext plugin 

- Deploying in production 

- Migrating old extension environments 

Let's create an Ext plugin. 

## Creating an Ext plugin 

You can create Ext plugins in Liferay Developer Studio or in your terminal
environment. The Ext plugin is stored in the `ext` directory of the Plugins SDK
(see Chapter 2, The Plugins SDK). 

***Using Developer Studio:*** 

1. Go to File &rarr; New &rarr; Liferay Project. 

2. Use the following steps to setup your new Ext plugin: 

    2.1. Fill in *example* for Project name and *Example* for Display name. 

    2.2. Select the Liferay Plugins SDK and Portal Runtime you've configured. 

    2.3. Select *Ext* for your Plugin type. 

    2.4. Click *Finish*. 

![Figure 7.1: Creating an Ext plugin](../../images/07-ext-plugins-1.png)

The Plugins SDK automatically appended "-ext" to the project name when naming
the parent folder of your Ext plugin. In Developer Studio, you can either create
a completely new plugin or add a new plugin to an existing plugin project. 

***Using the terminal:*** Navigate to the *ext* directory in the Liferay Plugins
SDK and enter the appropriate command for your operating system to create a new
Ext plugin: 

1. In Linux and Mac OS, enter
	
	./create.sh example "Example"
	
2. In Windows, enter
	
	create.bat example "Example"
	
A `BUILD SUCCESSFUL` message from Ant tells you there's a new folder named
`example-ext` inside the `ext` folder in your Plugins SDK. The Plugins SDK
automatically named the EXT by appending "-ext" to the project name. 

### Anatomy of the Ext Plugin 

The structure of your new `example-ext` folder looks like this: 

- `example-ext/`

    - `docroot/`

        - `WEB-INF/`

            - `ext-impl/`

                - `src/`

            -  `ext-lib/`

                - `global/`

                - `portal/`

            - `ext-service/`

                - `src/`

            - `ext-util-bridges/`

                - `src/`

            - `ext-util-java/`

                - `src/`

            - `ext-util-taglib/`

                - `src/`

            - `ext-web/`

                - `docroot/`


![Figure 7.2: Here's the directory structure in the Ext plugin's Package Explorer](../../images/07-ext-plugins-2.png)

Let's look at a few of the `/docroot/WEB-INF/` subdirectories in more detail: 

- `ext-impl/src`: Contains the `portal-ext.properties` configuration file,
custom implementation classes, and in advanced scenarios, classes that override
core classes within `portal-impl.jar.` 

- `ext-lib/global`: Contains libraries that should be copied to the
application server's global classloader upon deployment of the Ext plugin. 

- `ext-lib/portal`: Contains libraries to be copied inside Liferay's main
application. These libraries are usually necessary because they are invoked from
the classes added within `ext-impl/src`. 

- `ext-service/src`: Contains classes that should be available to other
plugins. In advanced scenarios, this directory contains classes that overwrite
the classes of `portal-service.jar`. Service Builder puts the interfaces of each
service here. 

- `ext-web/docroot`: Contains the web application's configuration files,
including `WEB-INF/struts-config-ext.xml`, which allows you to customize
Liferay's core struts classes. However, hooks are recommended for customizing a
struts action. Any JSPs that you're customizing also belong here. 

- `ext-util-bridges`, `ext-util-java` and `ext-util-taglib`: Needed only in
scenarios where you need to customize the classes of three libraries provided
with Liferay: `util-bridges.jar`, `util-java.jar` and `util-taglib.jar`,
respectively. Otherwise you can ignore these directories. 

By default, several files are added to the plugin. Here are the most
significant: 

- `build.xml`: The Ant build file for the Ext plugin project. 

- `docroot/WEB-INF/liferay-plugin-package.properties`: Contains properties of
the plugin, including display name, version, author, and license type. 

- `docroot/WEB-INF/ext-impl/src/portal-ext.properties`: Overrides Liferay's
configuration properties--use a hook plugin to override properties whenever it's
possible. An example where an Ext plugin is necessary to override a property is
when specifying a custom class as a portal property value. You can use a
`portal-ext.properties` file with each of your Ext plugins, but don't override
the same property from multiple `portal-ext.properties` files--the loading order
isn't assured, and you can cause unintended system behavior as a result. 

- `docroot/WEB-INF/ext-web/docroot/WEB-INF` files: 

    - `portlet-ext.xml`: Used to overwrite the definition of a Liferay portlet.
    To do this, copy the complete definition of the desired portlet from
    `portlet-custom.xml` within Liferay's source code, then apply the necessary
    changes. 

    - `liferay-portlet-ext.xml`: Similar to `portlet-ext.xml`, but for
    additional definition elements specific to Liferay. To override these
    definition elemnets, copy the complete definition of the desired portlet
    from `liferay-portlet.xml` within Liferay's source code, then apply the
    necessary changes. 

    - `struts-config-ext.xml` and `tiles-defs-ext.xml`: Customize the struts
    actions used by Liferay's core portlets. 

---

 ![tip](../../images/tip-pen-paper.png)**Tip:** After creating an Ext plugin,
 remove the files you don't need to customize from
 `docroot/WEB-INF/ext-web/docroot/WEB-INF`. Liferay keeps track of the files
 deployed by each Ext plugin and won't let you deploy mutiple Ext plugins that
 override the same file. If you remove unnecessary (uncustomized) files, you'll
 avoid collisions with Ext plugins deployed alongside your. 

---

You've now created an Ext plugin and are familiar with its directory structure
and its most significant files. Let's customize Liferay Portal by developing
your Ext plugin. 

## Developing an Ext Plugin 

Because an Ext plugin changes the Liferay web applicaiton itself when deployed,
it's not a separate component that can be easily removed at any time. For this
reason, Ext plugin development is done differently than for other plugin types.
It's important to remember that once an Ext plugin is deployed, some of its
files are *copied* inside the Liferay installation; the only way to remove its
changes is by *redeploying* an unmodified Liferay application. 

The Plugins SDK let's you deploy and redeploy Ext plugins during your
development phase. Redeployment involves *cleaning* (i.e. removing) your
application server and unzipping your specified Liferay bundle to start from
scratch. That way any changes made to the Ext plugin during development are
properly applied, and files removed from your plugin by previous changes aren't
left behind in the Liferay application. This added complexity with Ext plugins
is why we recommend using another plugin type to accomplish your goals, whenever
possible. 

Let's look at the steps required to develop Ext plugins: 

- Set up: Configure your Plugins SDK environment to develop Ext plugins for
Liferay Portal on your application server. 

- Initial deployment: Deploy and publish your Ext plugin. 

- Redeployment: After making changes to your Ext plugin, you'll either need to
*redeploy* it as normal or use a *clean redeployment* process. 

- Distribution: How to package Ext plugins for distribution. 

- Advanced customization techniques: Here we'll provide examples that
require advanced techniques to customize Liferay Portal. 

Now let's look at each development step in more detail. 

### Set up 

Before deploying an Ext plugin, the `build.{username}.properties` file in the
root folder of your Plugins SDK needs editing. If the file doesn't exist yet,
create it. Substitute `{username}` with your user ID on your computer. Once
you've opened your build properties file, add the following properties, making
sure the individual paths reflect the right locations on your system:

    ext.work.dir={work}

    app.server.dir={work}/liferay-portal-6.1.0-ce-ga1/tomcat-7.0.23

    app.server.zip.name={...}/liferay-portal-tomcat-6.1.0-ce-ga1-20120106155615760.zip

Your `app.server.zip.name` property should specify the path to your Liferay
bundle `.zip` file. Your *work* directory, specified by your `ext.work.dir`
property, is the destination for the contents of your Liferay bundle `.zip`
file. Lastly, your `app.server.dir` property should point to your application
server's directory within your *work* directory. Look in your Liferay bundle at
the path to the application server directory in order to determine the value you
should use for your `app.server.dir` property.

For example, let's make `C:\work` our `ext.work.dir` value. If we have a Liferay
bundle `.zip` file
`C:\downloads\liferay-portal-tomcat-6.1.0-ce-ga1-20120106155615760.zip` which we
set as the value for our `app.server.zip.name` property, we discover the
*relative path* to the application server *within* our Liferay bundle `.zip`
file is `liferay-portal-6.1.0-ce-ga1\tomcat-7.0.23`. So we must specify
`C:\work\liferay-portal-6.1.0-ce-ga1\tomcat-7.0.23` as our `app.server.dir`
property value. 

---

 ![note](../../images/tip-pen-paper.png)**Note:** Some Liferay bundles come
 installed with a sample website for a fictional company called 7-Cogs. This
 sample website is useful for showcasing certain features of Liferay. However,
 once you've removed it, you don't want the Ant targets to reinstall it every
 time your bundle is unzipped again. To make sure this doesn't happen, unzip
 your bundle, delete the
 `{work}/liferay-portal-6.1.0-ce-ga1/tomcat-7.0.23/webapps/sevencogs-hook`
 folder, then re-zip your bundle. 

---

Next, we'll make some changes to the Ext plugin we created and deploy it.

### Initial deployment

With our environment set up, we're ready to start customizing. We'll show the
process using a simple example -- customizing the sections of a user profile.
Liferay allows this configuration to be made through the `portal-ext.properties`
configuration file, but the property we'll be changing can't be changed from a
hook plugin. To make our change, open the
`docroot/WEB-INF/ext-impl/src/portal-ext.properties` file and paste the
following contents inside:

    users.form.update.main=details,password,organizations,sites,roles

This removes the sections for user groups, personal sites, and categorizations
from the user profile page. We might make this change to ensure that these
entities won't be used in our portal. 

Now we're ready to deploy. 

#### Deploy the plugin

You can deploy your plugin from Liferay Developer Studio or the terminal. 

***Deploying In Developer Studio:*** Simply drag your `example-ext` project from
your *Package Explorer* onto your server. 

![Figure 7.3: Drag-and-drop plugin onto server](../../images/07-ext-plugins-7.png)

***Deploying In the terminal:*** Open a terminal window in your `ext/example-ext`
directory and enter either of these commands:
	
	- ant deploy

	- ant direct-deploy

---

 ![tip](../../images/tip-pen-paper.png)**Tip:** The `direct-deploy` target
 deploys all plugin changes directly to the appropriate directories in the
 Liferay application. If you use the `deploy` target, the Liferay server needs
 to apply the changes from the resulting `.war` file at startup, possibly
 slowing down your development process. Using `direct-deploy` is usually
 preferred for deploying Ext plugins during development. However,
 `direct-deploy` does not work in WebLogic Server or WebSphere application
 server environments. 

---

A `BUILD SUCCESSFUL` message indicates your plugin is now being deployed. If you
switch to the console window running Liferay, within few seconds you should see
the message:

	Extension environment for example-ext has been applied. You must reboot the
	server and redeploy all other plugins

You may not have to redeploy all other plugins, but it's a best practice.
Definitely redeploy all plugins if any of the changes applied through the Ext
plugin could affect the deployment process itself. 

The `ant deploy` target builds a `.war` file with all the changes you made and
copies them to the auto-deploy directory inside the Liferay installation. When
the server starts, it detects the `.war` file, inspects it, and copies its
content to the appropriate destinations within the deployed and running Liferay
application, inside your application server. Now you restart your application
server to *publish* your changes. 

#### Publish the plugin

Publishing your plugin to the Liferay server completes the deployment process.
As with deployment, you can publish using Liferay Developer Studio or your
terminal. 

***Publishing in Developer Studio:*** 

1. Select the Liferay server in the *Servers* view. 

2. Select the server's *Publish* option. 

![Figure 7.4: How to publish the Ext Plugin](../../images/07-ext-plugins-3.png)

***Publishing in the terminal:*** Restart the Liferay server.

Let's try our Ext plugin within our portal. Once your server has started, log in
as an administrator and go to *Control Panel &rarr; Users and Organizations*.
Edit an existing user and verify that the right navigation menu only shows the
five sections that we referenced from the `users.form.update.main` property. 

![Figure 7.5: You should see these five sections under the User Information heading](../../images/ext-plugin-five-sections.png)

Now that you've applied a simple modification to Liferay with an Ext plugin,
we'll proceed with a more complex customization. It's an opportunity to learn
the proper way to *redeploy* an Ext plugin, which is different from *initial
deployment*. 

For this example, let's customize the *details* section of the user profile. We
could just overwrite its JSP, but let's use a more powerful method that lets us
add new sections or even merge existing ones. With Liferay we can refer to
custom sections from the `portal-ext.properties` and implement them just by
creating a JSP. We'll modify the property `users.form.update.main` again and the
property `users.form.add.main` to set the following:

	users.form.add.main=basic,organizations,personal-site
	users.form.update.main=basic,password,organizations,sites,roles

We removed the original *details* section and added a custom one called *basic*.
When Liferay Portal's user administration reads the property, it looks for the
implementation of each section based on the following conventions:

- The section should be implemented in a JSP inside the directory:

        ext-web/docroot/html/portlet/users_admin/user/

- The name of the JSP should use the name of the section, with the `.jsp`
extension. If the section name has a dash sign (`"-"`), it get's converted to an
underscore sign (`"_"`). For example, if the section is called *my-info*, the
JSP should be named `my_info.jsp` to comply with common JSP naming standards. 

- The name of the section that's shown to the user comes from the language
bundles. When using a key/value that is not included with Liferay, add the
key/value to your Ext plugin's `Language-ext.properties` file and the
language-specific properties file for each language variant you're providing a
translation for. These files go in the `ext-impl/src` directory of your Ext
plugin. 

For our example, we'll create a file within the Ext plugin with the following
path:

    ext-web/docroot/html/portlet/users_admin/user/basic.jsp

We can write the contents of the file from scratch or just copy the
`details.jsp` file from Liferay's source code and modify from there. Let's do
the latter and then remove some fields, leaving the screen name, email address,
first name, and last name fields to simplify user creation and user update.
Here's the resulting JSP code:

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

In our case, we don't need to add a new key to `Language-ext.properties`,
because an entry for the key named *basic* is already included in Liferay's
language bundle.

We are now ready to redeploy our Ext plugin so we can check the changes we made.

### Redeployment

So far, the development process has been very similar to that of other plugin
types. The differences appear when you need to redeploy an Ext plugin. As
mentioned earlier, when the plugin is first deployed, some of its files are
*copied* into the Liferay installation. After making any change to an Ext
plugin, we recommend that you consider the following redeployment approaches:

**Clean Redeployment:** If you removed part(s) of your plugin, if there are
changes to your plugin that can affect the deployment of plugins, or if you
simply want to start with a clean Liferay environment, we recommend you
*undeploy* your plugin and *clean* your application server before redeploying
your Ext plugins. By *cleaning* the application server, the existing Liferay
installation is removed and the bundle specified in your Plugin SDK environment
(e.g. value of `app.server.zip.name` in `build.{username}.properties`) is
unzipped in its place. Here are the steps to take depending on whether you are
working from Liferay IDE/Studio or the terminal:

-	***From Developer Studio:***

	1. Remove the plugin from the server: While selecting the Ext plugin in the
	*Servers* view, select the plugin's *Remove* option

		![Figure 7.6: Removing Ext Plugin from the server](../../images/07-ext-plugins-4.png)

	2. Clean the application server: While selecting the Ext plugin project in
	the *Package Explorer* view, select the plugin's *Liferay* &rarr; *Clean App
	Server...* option

		![Figure 7.7: How to clean app server](../../images/07-ext-plugins-5.png)

	3.	Start the Liferay server

		![Figure 7.8: Start the Liferay server](../../images/07-ext-plugins-6.png)

	4.	Drag-and-drop the Ext plugin to the Liferay server

		![Figure 7.9: Drag-and-drop plugin onto server](../../images/07-ext-plugins-7.png)

	5. While selecting the Liferay server in the *Servers* view, select the
	server's *Publish* option

		![Figure 7.10: Publish your server](../../images/07-ext-plugins-8.png)

-	***From the terminal:***
	
	1.	Stop the Liferay server

	2.	For each Ext plugin to be deployed ...

		I.	`cd <your-plugin-ext>`

		II.	`ant clean-app-server`

		III.	`ant direct-deploy`

	3.	Start the Liferay server

**Redeployment:** If you've only added parts to your plugin or made
modifications that do not affect the plugin deployment process, you can choose
the option to redeploy.

- ***Using Developer's Studio:*** Right click your plugin located underneath
your server and select *Redeploy*.

	![Figure 7.11: How to redeploy your Ext plugin](../../images/07-ext-plugins-9.png)

- ***Using the terminal:*** Redeploy and publish the plugin as you initially
did, as described in section *Initial deployment*.

After your `example-ext` plugin is published on Liferay Portal, you can see the
*basic* details page by choosing to add a user or view an existing user.

![Figure 7.12: You should only see user fields for screen name, email address, first name, and last name](../../images/ext-plugin-user-basic-details.png)

After you've completed developing your Ext plugin, you'll want to package it up
for distribution and production.

### Distribution 

Once you have finished developing your plugin, you can package it up in a `.war`
file for distribution and production deployment.

- ***Using Developer Studio:*** While selecting the Ext plugin project in the
*Package Explorer* view, select the project's *Liferay* &rarr; *SDK* &rarr;
*war* option

![Figure 7.13: Accessing the *war* option](../../images/07-ext-plugins-10.png)

-	***Using the terminal:***

	1.	`cd <your-plugin-ext>`

	2.	`ant war`

The `.war` file is written to your `[liferay-plugins]/dist` directory.

Now that you've learned the basics of Ext plugin development, let's look at some
advanced customizations that you can do.

### Advanced customization techniques 

This section covers additional customization techniques that are possible
through an Ext plugin. As mentioned above, you can change almost everything
within Liferay when using an Ext plugin. But you must be very careful.

Always keep in mind that with every new Liferay version, implementation classes
may have changed. Thus, if you've changed Liferay source code directly, you may
have to merge your changes into the newer Liferay version. The general approach
for minimizing conflicts is not to change anything, only extend.

Alternatively, you can extend the class you want to change and override the
required methods. Then use some of Liferay's configuration files to reference
your subclass as a replacement for the original class.

In the subsections that follow, we'll cover:

-	**Using advanced configuration files**

-	**Changing the API of a core service**

-	**Replacing core classes in portal-impl**

Let's check out how to use advanced configuration files next.

#### Using advanced configuration files 

Liferay uses several internal configuration files for easier maintenance and
also to configure the libraries and frameworks it depends on, like Struts and
Spring. For advanced customization needs, it may be useful to override the
configuration specified in these files. Liferay provides a clean way to do this
from an Ext plugin without modifying the original files.

Next, we present a list of all these files, along with a description and a
reference to the original file in the path where they can be found in the source
code of Liferay (you may need to look at them for reference):

-   `ext-impl/src/META-INF/ext-model-hints.xml`

    - Description: This file allows overwriting the default properties of the
    fields of the data models used by Liferay's core portlets. These properties
    determine how the form to create or edit each model is rendered.

    -   Original file in Liferay: `portal-impl/src/META-INF/portal-model-hints.xml`

-   `ext-impl/src/META-INF/ext-spring.xml`

    - Description: This file allows overwriting the Spring configuration used by
    Liferay and any of its core portlets. The most common usage is to configure
    specific datasources or to swap the implementation of a given service with a
    custom one.

    -   Original files in Liferay: `portal-impl/src/META-INF/*-spring.xml`

-   `ext-impl/src/content/Language-ext_*.properties`

    - Description: These files allow overwriting the value of any key used by
    Liferay's UI to support *I18N*.

    -   Original file in Liferay: `portal-impl/src/content/Language-*.properties`

-   `ext-impl/src/META-INF/portal-log4j-ext.xml`

    - Description: This file allows overwriting the log4j configuration. The
    most common usage is to increase or decrease the log level of a given
    package or class, to obtain more information or hide unneeded information
    from the logs.

    -   Original file in Liferay: `portal-impl/src/META-INF/portal-log4j.xml`

-   `ext-impl/src/com/liferay/portal/jcr/jackrabbit/dependencies/repository-ext.xml`

    - Description: This file allows overwriting the configuration of the
    Jackrabbit repository. Refer to the Jackrabbit configuration documentation
    for details
    ([http://jackrabbit.apache.org/jackrabbit-](http://jackrabbit.apache.org/jackrabbit-configuration.html)[configuration.html](http://jackrabbit.apache.org/jackrabbit-configuration.html))

    -   Original file in Liferay: `portal-impl/src/com/liferay/portal/jcr/jackrabbit/dependencies/repository.xml`

-   `ext-web/docroot/WEB-INF/portlet-ext.xml`

    - Description: This file allows overwriting the declaration of the core
    portlets included in Liferay. The most common usage is to change the init
    parameters or the roles specified.

    -   Original file in Liferay: `portal-web/docroot/WEB-INF/portlet-custom.xml`

-   `ext-web/docroot/WEB-INF/liferay-portlet-ext.xml`

    - Description: This file allows overwriting the Liferay-specific declaration
    of the core portlets included in Liferay. Refer to the
    `liferay-portlet-app_6_1_0.dtd` file for details on all the available
    options. Use this file with care since the code of the portlets may be
    assuming some of these options to be set to certain values.

    -   Original file in Liferay: `portal-web/docroot/WEB-INF/liferay-portlet.xml`

-   `ext-web/docroot/WEB-INF/liferay-display.xml`

    - Description: This file allows overwriting the portlets that will be shown
    in the "Add application" pop-up and the categories in which they'll be
    organized. The most common usage is to change the categorization, hide some
    portlets or make some Control Panel portlets available to be added to a
    page.

    -   Original file in Liferay: `portal-web/docroot/WEB-INF/liferay-display.xml`

-   `ext-web/docroot/WEB-INF/liferay-layout-templates-ext.xml`

    - Description: This file allows specifying custom template files for each of
    the layout templates provided by default with Liferay. You should not need
    to do this except for very advanced needs.

    -   Original file in Liferay: `portal-web/docroot/WEB-INF/liferay-layout-templates.xml`

-   `ext-web/docroot/WEB-INF/liferay-look-and-feel-ext.xml`

    - Description: This file allows changing the properties of the default
    themes provided by default with Liferay. You should not need to do this
    except for very advanced needs.

    -   Original file in Liferay: `portal-web/docroot/WEB-INF/liferay-look-and-feel.xml`

#### Configuring Lucene Analyzers 

Liferay uses Lucene to facilitate search and indexing within the portal. In old
versions of Liferay, Lucene analyzers could be configured from
`portal-ext.properties`. This was convenient but there was only a single
analyzer for all portal-indexed fields. This made it difficult to provide
correct behaviors for handling both keyword and text fields, for example.

In Liferay 6.1, this functionality has been upgraded. Lucene analyzers are now
defined in Spring configuration files. The default analyzer configuration is
defined in `/portal-impl/src/META-INF/search-spring.xml`. Liferay 6.1 introduced
per-field analyzers, allowing Lucene's query parser to identify the correct
analyzer to handle any field.

There are two common scenarios where it's useful to configure Lucene analyzers.
The first scenario involves language analyzers. Liferay provides a large number
of language analyzers in `search-spring.xml` out of the box and uses regular
expression matching to map localized fields to specific analyzers. However, if
your language is not included among the defaults or you're not satisfied with
one of the default language analyzers, you can override a default analyzer with
a custom analyzer. The second scenario involves creating custom indexers. If
you've created a custom indexer to index new fields, you can use the default
analyzer for your new fields, select a specific one (such as one of the
`KeywordAnalyzers`), or define a custom analyzer.

In order to customize the Lucene analyzer configuration, you need to create an
Ext plugin. Since the Analyzer classes reference the Lucene APIs directly, it's
not possible to configure Lucene analyzers from a hook plugin. Within your Ext
plugin, create a `/docroot/WEB-INF/ext-impl/src/META-INF/ext-spring.xml` file
and declare the beans that you'd like to override with a custom configuration.
Here are a few of the pre-configured analyzers from the
`com.liferay.portal.search.lucene.PerFieldAnalyzerWrapper` bean configuration
in `search-spring.xml`:

    <entry key="tag"
           value-ref="com.liferay.portal.search.lucene.LikeKeywordAnalyzer" /> 
    <entry key="templateId" 
           value-ref="org.apache.lucene.analysis.KeywordAnalyzer" />
    <entry key="treePath"
           value-ref="com.liferay.portal.search.lucene.LikeKeywordAnalyzer" /> 
    <entry key="type" 
           value-ref="org.apache.lucene.analysis.KeywordAnalyzer" /> 
    <entry key="userName"
           value-ref="com.liferay.portal.search.lucene.LikeKeywordAnalyzer" /> 
    <entry key=".*_ar" 
           value-ref="org.apache.lucene.analysis.ar.ArabicAnalyzer" />
    <entry key=".*_de_DE"
           value-ref="org.apache.lucene.analysis.de.GermanAnalyzer" /> 
    <entry key=".*_el_GR" 
           value-ref="org.apache.lucene.analysis.el.GreekAnalyzer" />
    <entry key=".*_fa_IR"
           value-ref="org.apache.lucene.analysis.fa.PersianAnalyzer" /> 
    <entry key=".*_fr_[A-Z]{2}"
           value-ref="org.apache.lucene.analysis.fr.FrenchAnalyzer" />

Note that you can define custom analyzers for any fields, including custom
fields.

#### Changing the API of a core service 

One advanced customization need that appears in some scenarios is to change the
API of a method provided by one of Liferay's services, like `UserLocalService`,
for example.

Is it possible to do that? The short answer is no. The long answer is that you
can, but it will require modifying Liferay's source code directly and making
manual changes to quite a few files. The good news is that there is a better
alternative to achieve the end goal.

The best way to extend an existing service is to create a complementary custom
service, for example a `MyUserLocalService` that includes all the new methods.
Your custom code can invoke this service instead of the default service and the
implementation of your service can invoke the original service as needed.

Note that this technique does not require an Ext plugin since it can be done
from portlet plugins. In fact, using Service Builder for an Ext plugin is
deprecated but is supported for migration from the old extension environment.

In some advanced circumstances, it's desired to change the implementation of the
original service to call your custom one, which can only be done from an Ext
plugin. To achieve this, override the Spring definition for
`UserLocalServiceUtil` in `ext-spring.xml` and point it to your
`MyUserLocalServiceImpl` (instead of `UserLocalServiceImpl`). This way both
`MyUserLocalServiceUtil` and `UserLocalServiceUtil` will use the same Spring
bean: your new implementation.

#### Replacing core classes in portal-impl 

If you really need to change a core portal-impl class and this class cannot be
replaced in any configuration file, then the best way to avoid conflicts and
easily merge with a new portal version is to:

1.  Rename original class (e.g. `DeployUtil` &rarr; `MyDeployUtil`)

2. Create a new subclass with the old name (e.g. `DeployUtil extends
MyDeployUtil`)

3.  Override methods you need to change

4.  Delegate static methods

5. Use a logger with appropriate class name for both classes (e.g. `DeployUtil`)

This strategy will help you determine what you will need to merge in the future
when a new version of Liferay is released.

---

 ![tip](../../images/tip-pen-paper.png)**Tip:** This is a very advanced
 technique that, especially if abused, may have a high impact on the
 maintainability of your code. Try to look for alternatives and if you confirm
 that this is your only alternative try to apply it only for the short term and
 get in touch with Liferay's developers to apply the necessary changes to the
 product source code.

---

## Deploying in production 

In production or pre-production environments, it's often not possible to use Ant
to deploy web applications. Also, some application servers such as WebSphere or
Weblogic have their own deployment tools and it isn't possible to use Liferay's
autodeploy process. This section describes two methods for deploying and
redeploying Ext plugins in these scenarios.

### Method 1: Redeploying Liferay's web application 

This method can be used in any application server that supports auto-deploy,
such as Tomcat or JBoss. Its main benefit is that the only artifact that needs
to be transferred to the production system is your Ext plugin's `.war` file,
produced using the `ant war` target. This `.war` file is usually small making it
easy to transport. Here are the steps that need to be executed on the server:

1.  Redeploy Liferay:

	If this is the first time you are deploying your Ext plugin to this server,
	you can skip this step. Otherwise, start by executing the same steps you
	first used to deploy Liferay on your app server. If you're using a bundle,
	you can just unzip the bundle again. If you've installed Liferay manually on
	an existing application server, you'll need to redeploy the Liferay `.war`
	file and copy the libraries required globally by Liferay and your Ext plugin
	to the appropriate directory within the application server.

2. Copy the Ext plugin `.war` into the auto-deploy directory. For a bundled
Liferay distribution, the `deploy` folder is in Liferay's *root* folder of your
bundle (e.g. `liferay-portal-6.1.0-ce-ga1/`).

3. Once the Ext plugin is detected and deployed by Liferay, restart the Liferay
server.

### Method 2: Generate an aggregated WAR file 

This method can be used for application servers that do not support auto-deploy,
such as WebSphere or Weblogic. Its main benefit is that all Ext plugins are
merged before deployment to production, so a single `.war` file will contain
Liferay plus the changes from one or more Ext plugins. Before deploying the
Liferay `.war` file, you'll need to copy the dependency `.jar` files for both
Liferay and the Ext plugin to the global application server class loader in the
production server. This location varies from server to server; please see [Using
Liferay Portal
6.1](http://www.liferay.com/documentation/liferay-portal/6.1/user-guide) for
further details for your application server.

To create the aggregated `.war` file, first deploy your Ext plugin. For the
remaining steps, let's assume we're using a Liferay Tomcat bundle. Once your
plugin is deployed, restart the server so that the plugin is fully deployed and
shut the server down again. Now the files are aggregated in your app server.
Create a `.war` file by zipping the `webapps/ROOT` folder of Tomcat. Also, copy
all the libraries from the `lib/ext` directory of Tomcat, that are associated
with all the Ext plugins, to your application server's global classpath, as
noted above.

Once you have the aggregated `.war` file follow these steps on the server:

1.  Redeploy Liferay using the aggregated WAR file.

2. Stop the server and copy the new version of the global libraries to the
appropriate directory in the application server.

## Migrating old extension environments 

Ext plugins have been created as an evolution of the extension environment
provided in Liferay 5.2 and previous versions of Liferay. Because of this, a
common need for projects upgrading from previous versions is to migrate
Extension environments into Ext plugins. The good news is that this task is
automated and thus relatively easy.

---

 ![tip](../../images/tip-pen-paper.png)**Tip:** When migrating an extension
 environment, it's worth considering if all or at least some of the extension
 environment's features can be moved into other types of plugins such as
 portlets and hooks. Portlets and hooks are designed to meet specific needs and
 they are easier to learn. Additionally, they are cheaper to maintain since they
 often require fewer changes when upgrading to a new version of Liferay.

---

The process of migrating consists of executing a target within the `ext`
directory of the Plugins SDK, pointing to the old extension environment and
naming the new plugin:

    ant upgrade-ext -Dext.dir=/projects/liferay/ext -Dext.name=my-ext -Dext.display.name="My Ext"

Here is a description of the three parameters used:

-   `ext.dir` is the location of the old Extension Environment.

-   `ext.name` is the name of the Ext plugin that you want to create

-   `ext.display.name` is the display name

After executing the target, you should see the logs of several copy operations
that will take files from the extension environment and copy them into the
equivalent directory within the Ext plugin (see the section *Creating an Ext
plugin* for an explanation of the main directories within the plugin).

When the migration process is complete, some additional tasks will be needed to
upgrade the code to the new version of Liferay. Some of the most typical tasks
are:

-   Review the uses of Liferay's APIs and adapt them accordingly.

- Review the changes to the JSPs and merge your changes into the JSPs of the new
Liferay version.

- When using Service Builder you'll need to run `ant build-service` again. It's
also recommended to consider moving this code to a portlet plugin, because it is
now as powerful and allows for greater modularity and maintainability.

- If you've implemented portlets in the Extension Environment, migrate them to
portlet plugins, as Extension Environment portlets have been deprecated since
Liferay Portal 6.0 and support for them is not guaranteed to be available in
future Liferay Portal releases.

#### Licensing and Contributing 

Liferay Portal is Open Source software licensed under the LGPL 2.1 license
([http://www.gnu.org/licenses/lgpl-2.1.html](http://www.gnu.org/licenses/lgpl-2.1.html)).
If you reuse any code snippet and redistribute it either publicly or to an
specific customer, you need to make sure that those modifications are compliant
with this license. A common way to do this is to make the source code of your
modifications available to the community under the same license, but make sure
to read the license text yourself to find the best option that fits your needs.

If the goal of the changes is to fix a bug or to make an improvement that could
be of interest to a broader audience, consider contributing it back to the
project. That would benefit all other users of the product and also would be
good for you since you won't have to maintain the changes when new versions of
Liferay come out. You can notify Liferay of bugs or improvements in
[issues.liferay.com](http://issues.liferay.com). There is also a wiki page with
instructions on how to contribute to Liferay:

[http://www.liferay.com/community/wiki/-/wiki/Main/Contributing](http://www.liferay.com/community/wiki/-/wiki/Main/Contributing)

## Conclusions 

Ext plugins are a very powerful way of extending Liferay. There are no limits to
what you can use them to customize. For this reason, you should use them
carefully. If you find yourself using an Ext plugin, verify if all or part of
the desired functionality can be implemented through portlets, hooks or web
plugins instead. If you do need to use an Ext plugin, make it as small as
possible and make sure you follow the instructions in this guide carefully to
avoid issues.

Next we'll learn how to develop Liferays using the Maven build framework.
