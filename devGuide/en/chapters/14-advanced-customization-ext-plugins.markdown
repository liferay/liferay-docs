# Advanced Customization with Ext Plugins [](id=advanced-customization-with-ext-plugins-liferay-portal-6-2-dev-guide-12-en)

Ext plugins are powerful tools for extending Liferay. Because they increase the
complexity of your Liferay instance, you should only use an Ext plugin if you're
sure you can't accomplish your goal using a different tool. Check out
[Customizing and Extending Functionality with
Hooks](http://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/customize-and-extend-functionality-hooks-liferay-portal-6-2-dev-guide-en)
for the available alternatives. If a hook won't suffice, keep reading
to discover the use cases for Ext plugins and how to set one up. First let's
talk about why you should avoid Ext plugins when possible. 

As someone once said, "With great power comes great responsibility" (okay, many
people have said that many times).  Before deciding to use an Ext plugin, weigh
the cost of using such a powerful tool. Ext plugins allow the use of internal
APIs or even overwriting files from the Liferay core. When upgrading to a new
version of Liferay (even if it's a maintenance version or a service pack), you
have to review all changes and manually modify your Ext plugin to merge your
changes with Liferay's. Additionally, Ext plugins aren't hot deployable. To
deploy an Ext plugin, you must restart your server. Lastly, with Ext plugins,
additional steps are required to deploy or redeploy to production systems. 

Now that you know the limitations of Ext plugins, let's look at why you'd want
to use them: 

- To specify custom classes as portal property values. For example, to specify a
  property that needs a custom class (e.g.,
  `global.startup.events=my.custom.MyStartupAction`), you need an Ext plugin to
  add your custom class to the portal class loader. 
- To provide custom implementations for any Liferay beans declared in Liferay's
  Spring files (when possible, use service wrappers from a hook instead of an
  Ext plugin). 
- To add JSPs referenced from portal properties that can only be changed
  from an Ext plugin (check whether the property can be modified from a hook
  plugin first). 
- To Overwrite a class (not recommended unless you have no other choice). 

With these use cases in mind, we'll discuss the following topics: 

- Creating an Ext plugin 
- Developing an Ext plugin 
- Deploying in Production 
- Migrating Old Extension Environments 

Let's create an Ext plugin. 

## Creating an Ext Plugin [](id=creating-an-ext-plugin-liferay-portal-6-2-dev-guide-12-en)

You can create Ext plugins in Liferay Developer Studio or in your terminal
environment. The Ext plugin is stored in the `ext` directory of the Plugins SDK
(see [Leveraging the Plugins
SDK](http://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/leveraging-the-plugins-sdk-liferay-portal-6-2-dev-guide-02-en)). 

### Using Developer Studio [](id=create-ext-plugin-in-developer-studio-liferay-portal-6-2-dev-guide-en)

1.  Go to File &rarr; New &rarr; Liferay Project. 

2.  Fill in *example* for project name and *Example* for the display name. 

3.  Leave the *Use default location* checkbox checked. By default, the
	default location is set to your current workspace. If you'd like to change
	where your plugin project is saved in your file system, uncheck the box and
	specify your alternate location. 

4.  Select the *Ant (liferay-plugins-sdk)* option for your build type. If
	you'd like to use *Maven* for your build type, navigate to the [Developing
	Plugins Using
	Maven](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/developing-plugins-using-maven-liferay-portal-6-2-dev-guide-02-en)
	section for details. 

5.  Your configured SDK and Liferay Runtime should already be selected. If you
    haven't yet pointed Liferay IDE to a Plugins SDK, click *Configure SDKs* to
    open the *Installed Plugin SDKs* management wizard. You can also access the
    *New Server Runtime Environment* wizard if you need to set up your runtime
    server; just click the *New Liferay Runtime* button next to the *Liferay
    Portal Runtime* dropdown menu. 

6.  Select *Ext* for your Plugin type. 

7.  Click *Finish*. 

![Figure 14.1: You can even create an Ext plugin project with Liferay IDE.](../../images/ext-create-an-ext-plugin-project.png)

The Plugins SDK automatically appended `-ext` to the project name when naming
the parent folder of your Ext plugin. In Developer Studio, you can either create
a completely new plugin or add a new plugin to an existing plugin project. 

### Using the Terminal [](id=create-ext-plugin-in-terminal-liferay-portal-6-2-dev-guide-en)

Navigate to the *ext* directory in the Liferay Plugins SDK and enter the
appropriate command for your operating system to create a new Ext plugin: 

1.  In Linux and Mac OS, enter

        ./create.sh example "Example"

2.  In Windows, enter

        create.bat example "Example"

A `BUILD SUCCESSFUL` message from Ant tells you there's a new folder named
`example-ext` inside the `ext` folder in your Plugins SDK. The Plugins SDK
automatically named the EXT by appending `-ext` to the project name. 

### Anatomy of the Ext Plugin [](id=anatomy-of-the-ext-plugin-liferay-portal-6-2-dev-guide-12-en)

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

![Figure 12.2: Here's the directory structure in the Ext plugin's Package Explorer](../../images/07-ext-plugins-2.png)

Let's look at a few of the `/docroot/WEB-INF/` subdirectories in more detail: 

`ext-impl/src`: Contains the `portal-ext.properties` configuration file,
custom implementation classes, and in advanced scenarios, classes that override
core classes within `portal-impl.jar`. 

`ext-lib/global`: Contains libraries that should be copied to the
application server's global classloader upon deployment of the Ext plugin. 

`ext-lib/portal`: Contains libraries to be copied inside Liferay's main
application. These libraries are usually necessary because they are invoked from
the classes added in `ext-impl/src`. 

`ext-service/src`: Contains classes that should be available to other
plugins. In advanced scenarios, this directory contains classes that overwrite
the classes of `portal-service.jar`. Service Builder puts the interfaces of each
service here. 

`ext-web/docroot`: Contains the web application's configuration files,
including `WEB-INF/struts-config-ext.xml`, which allows you to customize
Liferay's core struts classes. However, hooks are recommended for customizing a
struts action. Any JSPs that you're customizing also belong here. 

`ext-util-bridges`, `ext-util-java` and `ext-util-taglib`: These folders are
needed only in scenarios where you need to customize the classes of three
libraries provided with Liferay: `util-bridges.jar`, `util-java.jar` and
`util-taglib.jar`, respectively. Otherwise you can ignore these directories. 

By default, several files are added to the plugin. Here are the most
significant: 

- `build.xml`: The Ant build file for the Ext plugin project. 
- `docroot/WEB-INF/liferay-plugin-package.properties`: Contains properties of
  the plugin, including display name, version, author, and license type. 
- `docroot/WEB-INF/ext-impl/src/portal-ext.properties`: Overrides Liferay's
  configuration properties--use a hook plugin to override properties whenever
  it's possible. An example where an Ext plugin is necessary to override a
  property is when specifying a custom class as a portal property value. You can
  use a `portal-ext.properties` file with each of your Ext plugins, but don't
  override the same property from multiple `portal-ext.properties` files--the
  loading order isn't assured, and you can cause unintended system behavior as a
  result. 
- `docroot/WEB-INF/ext-web/docroot/WEB-INF` files: 

    - `portlet-ext.xml`: Used to overwrite the definition of a Liferay portlet.
      To do this, copy the complete definition of the desired portlet from
      `portlet-custom.xml` in Liferay's source code, then apply the necessary
      changes. 
    - `liferay-portlet-ext.xml`: This file is similar to `portlet-ext.xml`, but
      is for additional definition elements specific to Liferay. To override
      these definition elements, copy the complete definition of the desired
      portlet from `liferay-portlet.xml` within Liferay's source code, then
      apply the necessary changes. 
    - `struts-config-ext.xml` and `tiles-defs-ext.xml`: These files are used to
      customize the struts actions used by Liferay's core portlets. 

---

 ![tip](../../images/tip-pen-paper.png) **Tip:** After creating an Ext plugin,
 remove the files you don't need to customize from
 `docroot/WEB-INF/ext-web/docroot/WEB-INF`. Liferay keeps track of the files
 deployed by each Ext plugin and won't let you deploy multiple Ext plugins that
 override the same file. If you remove unnecessary (uncustomized) files, you'll
 avoid collisions with Ext plugins deployed alongside yours. 

---

You've now created an Ext plugin and are familiar with its directory structure
and its most significant files. Let's use your Ext plugin to customize Liferay
Portal. 

## Developing an Ext Plugin [](id=developing-an-ext-plugin-liferay-portal-6-2-dev-guide-12-en)

An Ext plugin changes Liferay itself when deployed; it's not a separate
component that can be easily removed at any time. For this reason, the Ext
plugin development process is different from other plugin types. It's
important to remember that once an Ext plugin is deployed, some of its files are
*copied* inside the Liferay installation; the only way to remove its changes is
by *redeploying* an unmodified Liferay application. 

The Plugins SDK lets you deploy and redeploy Ext plugins during your
development phase. Redeployment involves *cleaning* (i.e. removing) your
application server and unzipping your specified Liferay bundle to start from
scratch. That way any changes made to the Ext plugin during development are
properly applied, and files removed from your plugin by previous changes aren't
left behind in the Liferay application. This added complexity is why we
recommend using another plugin type to accomplish your goals, whenever
possible. 

Before digging in to the details, here's an overview of the steps required to
develop Ext plugins:

- We'll show you how to *configure* your Plugins SDK environment to develop Ext
  plugins for Liferay Portal on your application server. 
- We'll show you how to *deploy* and *publish* your Ext plugins for the first
  time. 
- We'll show you how to to *redeploy* normally or use a *clean redeployment*
  process after making changes to your Ext plugins.
- We'll show you how to package your Ext plugins for distribution. 
- We'll show you examples of Liferay Portal customizations that require advanced
customization techniques. 

Now let's look at each step of the development process in more detail. 

### Set Up [](id=set-up-liferay-portal-6-2-dev-guide-12-en)

Before deploying an Ext plugin, you must edit the `build.[username].properties`
file in the root folder of your Plugins SDK. If the file doesn't yet exist,
create it now. Substitute `[username]` with your user ID on your computer.
Once you've opened your build properties file, add the following
properties--make sure the individual paths reflect the right locations on your
system: 

    ext.work.dir=[work]

    app.server.dir=[work]/liferay-portal-[version]/[app server]

    app.server.zip.name=[...]/liferay-portal-[app server].zip

Your `app.server.zip.name` property should specify the path to your Liferay
bundle `.zip` file. Your `work` directory, specified by the `ext.work.dir`
property, is where you've unzipped your Liferay bundle runtime. The
`app.server.dir` property should point to your application server's directory
in your work directory. Look in your Liferay bundle at the path to the
application server directory to determine the value to use for your
`app.server.dir` property. 

For example, `C:/work` could be your `ext.work.dir` value. If we have a Liferay
bundle `.zip` file
`C:/downloads/liferay-portal-tomcat-6.2.0-ce-ga1-[timestamp].zip` which we
set as the value for our `app.server.zip.name` property, the *relative path* to
the application server *within* our Liferay bundle `.zip` file is
`liferay-portal-6.2.0-ce-ga1\tomcat-7.0.40`. We'd then specify
`C:/work/liferay-portal-6.2.0-ce-ga1/tomcat-7.0.40` as our `app.server.dir`
property value. 

---

 ![note](../../images/tip-pen-paper.png) **Note:** Some Liferay bundles come
 installed with a sample website. It's useful for showcasing certain features of
 Liferay, but if you removed it, you likely don't want it reinstalled each time
 your bundle is unzipped. To prevent the reinstallation of 7-Cogs, unzip your
 bundle, delete the
 `[work]/liferay-portal-[version]/[app server]/webapps/welcome-theme`
 folder, then re-zip your bundle. 

---

Next we'll change our newly created Ext plugin and deploy it. 

### Initial Deployment [](id=initial-ext-plugin-deployment-liferay-portal-6-2-dev-guide-en)

Our environment is set up and we're ready to start customizing. First let's look
at a simple example that customizes the sections of a user profile. The
following example can be done using a hook and the `users.form.add.main`
property, but for demonstration purposes, we'll make the customization by
overriding portal properties using an Ext plugin. Open the
`docroot/WEB-INF/ext-impl/src/portal-ext.properties` file and paste in the
following contents: 

    users.form.update.main=details,password,organizations,sites,roles

We've removed the sections for user groups, personal sites, and categorizations
from the user profile page. This ensures that these sections won't be used in
our portal. 

Now we're ready to deploy. 

#### Deploy the Plugin [](id=deploy-the-ext-plugin-liferay-portal-6-2-dev-guide-12-en)

You can deploy your plugin from Liferay Developer Studio or the terminal. 

**Deploying In Developer Studio:** Drag your `example-ext` project from
your Package Explorer onto your server. 

![Figure 12.3: Drag-and-drop plugin onto server](../../images/07-ext-plugins-7.png)

**Deploying In the terminal:** Open a terminal window in your `ext/example-ext`
directory and enter one of these commands:

    ant deploy

    ant direct-deploy

The `direct-deploy` target deploys all plugin changes directly to the
appropriate directories in the Liferay application. The `deploy` target creates
a `.war` file with your changes and then deploys it to your server. Either way,
your server must be restarted after the deploy occurs. Using `direct-deploy`
is usually preferred for deploying Ext plugins during development. However,
`direct-deploy` does not work in WebLogic Server or WebSphere application
server environments. 


A `BUILD SUCCESSFUL` message indicates your plugin is now being deployed. If you
switch to the console window running Liferay, in few seconds you should see the
message

    Extension environment for example-ext has been applied. You must reboot the
    server and redeploy all other plugins

If any changes applied through the Ext plugin affect the deployment process
itself, you must redeploy all other plugins. Even if the Ext plugin doesn't
affect the deployment process, it's a best practice to redeploy all your other
plugins following initial deployment of the Ext plugin. 

The `ant deploy` target builds a `.war` file with your changes and copies them
to the auto-deploy directory inside the Liferay installation. When the server
starts, it detects the `.war` file, inspects it, and copies its contents to the
appropriate destinations inside the deployed and running Liferay application.

Restart your application server, and let's find out about *publishing* your
changes. 

#### Publish the Plugin [](id=publish-the-ext-plugin-liferay-portal-6-2-dev-guide-12-en)

To complete the deployment process, your Ext plugin must be published to the
Liferay server. As with deployment, you can publish using Liferay Developer
Studio or your terminal. 

**Publishing in Developer Studio:** 

1.  Select the Liferay server in the *Servers* view. 

2.  Select the server's *Publish* option. 

![Figure 12.4: How to publish the Ext Plugin](../../images/07-ext-plugins-3.png)

**Publishing in the terminal:** Restart the Liferay server. 

Let's try Liferay portal, customized by your Ext plugin. Once your server
restarts, log in as an administrator and go to Control Panel &rarr; Users and
Organizations. Edit an existing user and verify that the right navigation menu
only shows the five sections that we referenced from the
`users.form.update.main` property. 

![Figure 12.5: You should see these five sections under the User Information heading](../../images/ext-plugin-five-sections.png)

That was a simple application of an Ext plugin. Let's proceed with a more
complex customization that illustrates the proper way to *redeploy* an Ext
plugin, which is different from *initial deployment*. 

Let's customize the *details* section of the user profile. Rather than override
its JSP, we'll use a more powerful method that lets us add new sections or even
merge existing ones. With Liferay we can refer to custom sections from the
`portal-ext.properties` and implement them just by creating a JSP. We'll modify
the property `users.form.update.main` again and the property
`users.form.add.main` to set the following:

    users.form.add.main=basic,organizations,personal-site
    users.form.update.main=basic,password,organizations,sites,roles

We removed the original *details* section and added a custom one called *basic*.
When Liferay Portal's user administration reads the property, it looks for the
implementation of each section based on the following conventions:

- The section is implemented in a JSP inside the following directory: 

        ext-web/docroot/html/portlet/users_admin/user/

- The name of the JSP uses the name of the section, with the `.jsp` extension.
  If the section name has a dash, (`-`), replace it with an underscore (`_`).
  For example, if the section is called *my-info*, the JSP should be named
  `my_info.jsp` to comply with JSP naming standards. 
- The section name that's shown to the user comes from the language bundles.
  When using a key/value that is not included with Liferay, add it to both your
  Ext plugin's `Language-ext.properties` file and the language-specific
  properties file for each language variant you're providing a translation for.
  These files go in the `ext-impl/src` directory of your Ext plugin. 

For our example, we'll create a file in the Ext plugin with the following path: 

    ext-web/docroot/html/portlet/users_admin/user/basic.jsp

We can write the contents of the file from scratch or just copy the
`details.jsp` file from Liferay's source code and modify from there. Let's do
the latter and then remove some fields, leaving the screen name, email address,
first name, and last name fields to simplify user creation and user update.
Here's the resulting JSP code. Note, make sure to remove the line escape
character `\` instances: 

    <%@ include file="/html/portlet/users_admin/init.jsp" %>

    <%
    User selUser = (User)request.getAttribute("user.selUser");
    Contact selContact = (Contact)request.getAttribute("user.selContact");
    %>

    <liferay-ui:error-marker key="errorSection" value="details" />

    <aui:model-context bean="<%= selUser %>" model="<%= User.class %>" />

    <h3><liferay-ui:message key="details" /></h3>

    <aui:fieldset column="<%= true %>" cssClass="aui-w50">
        <liferay-ui:success
            key="verificationEmailSent"
            message="your-email-verification-code-has-been-sent-and-the-new-\
                email-address-will-be-applied-to-your-account-once-it-has-been-\
                verified"
        />

        <liferay-ui:error
            exception="<%= DuplicateUserScreenNameException.class %>"
            message="the-screen-name-you-requested-is-already-taken"
        />

        <liferay-ui:error exception="<%= GroupFriendlyURLException.class %>">

            <%
            GroupFriendlyURLException gfurle =
                (GroupFriendlyURLException)errorException;
            %>

            <c:if
                test="
                    <%=
                        gfurle.getType() ==
                        GroupFriendlyURLException.DUPLICATE
                    %>"
            >
                <liferay-ui:message
                    key="the-screen-name-you-requested-is-associated-with-an-\
                        existing-friendly-url"
                />
            </c:if>
        </liferay-ui:error>

        <liferay-ui:error
            exception="<%= ReservedUserScreenNameException.class %>"
            message="the-screen-name-you-requested-is-reserved"
        />
        <liferay-ui:error
            exception="<%= UserScreenNameException.class %>"
            message="please-enter-a-valid-screen-name"
        />

        <c:if
            test="
                <%=
                    !PrefsPropsUtil.getBoolean(
                        company.getCompanyId(),
                        PropsKeys.USERS_SCREEN_NAME_ALWAYS_AUTOGENERATE) ||
                    (selUser != null)
                %>"
        >
            <c:choose>
                <c:when
                    test="
                        <%=
                            PrefsPropsUtil.getBoolean(
                                company.getCompanyId(),
                                PropsKeys.USERS_SCREEN_NAME_ALWAYS_AUTOGENERATE)
                            ||
                            (
                                (selUser != null) &&
                                !UsersAdminUtil.hasUpdateScreenName(
                                    permissionChecker, selUser)
                            )
                        %>"
                >
                    <aui:field-wrapper name="screenName">
                        <%= selUser.getScreenName() %>
    
                        <aui:input
                            name="screenName"
                            type="hidden"
                            value="<%= selUser.getScreenName() %>"
                        />
                    </aui:field-wrapper>
                </c:when>
                <c:otherwise>
                    <aui:input name="screenName" />
                </c:otherwise>
            </c:choose>
        </c:if>

        <liferay-ui:error
            exception="<%= DuplicateUserEmailAddressException.class %>"
            message="the-email-address-you-requested-is-already-taken"
        />
        <liferay-ui:error
            exception="<%= ReservedUserEmailAddressException.class %>"
            message="the-email-address-you-requested-is-reserved"
        />
        <liferay-ui:error
            exception="<%= UserEmailAddressException.class %>"
            message="please-enter-a-valid-email-address"
        />

        <c:choose>
            <c:when
                test="
                    <%= (selUser != null) &&
                        !UsersAdminUtil.hasUpdateEmailAddress(
                            permissionChecker, selUser)
                    %>"
            >
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

                    displayEmailAddressUser.setEmailAddress(
                        displayEmailAddressUser.getDisplayEmailAddress());
                }
                %>

                <aui:input
                    bean="<%= displayEmailAddressUser %>"
                    model="<%= User.class %>" name="emailAddress"
                >
                    <c:if test="<%= PrefsPropsUtil.getBoolean(
                        company.getCompanyId(),
                        PropsKeys.USERS_EMAIL_ADDRESS_REQUIRED)%>"
                    >
                        <aui:validator name="required" />
                    </c:if>
                </aui:input>
            </c:otherwise>
        </c:choose>

        <liferay-ui:error
            exception="<%= ContactFirstNameException.class %>"
            message="please-enter-a-valid-first-name"
        />
        <liferay-ui:error
            exception="<%= ContactFullNameException.class %>"
            message="please-enter-a-valid-first-middle-and-last-name"
        />

        <aui:input name="firstName" />

        <liferay-ui:error
            exception="<%= ContactLastNameException.class %>"
            message="please-enter-a-valid-last-name"
        />

        <aui:input name="lastName">
            <c:if test="<%= PrefsPropsUtil.getBoolean(
                company.getCompanyId(),
                PropsKeys.USERS_LAST_NAME_REQUIRED,
                PropsValues.USERS_LAST_NAME_REQUIRED) %>"
            >
                <aui:validator name="required" />
            </c:if>
        </aui:input>
    </aui:fieldset>

We don't need to add a new key to `Language-ext.properties`, because an entry
for the key named *basic* is already included in Liferay's language bundle. 

Let's redeploy our Ext plugin to review the changes we made. 

### Redeployment [](id=ext-plugin-redeployment-liferay-portal-6-2-dev-guide-12-en)

So far, Ext plugin development has been similar to the development of other
plugin types. You've now reached the point of divergence. When the plugin is
first deployed, some of its files are *copied* into the Liferay installation.
After changing an Ext plugin, you'll either *redeploy* or *clean redeploy*,
depending on the specific modifications you made to your plugin following the
initial deployment. Let's talk about each redeployment method and when to use
each one.

**Clean Redeployment:** If you removed part(s) of your plugin, there are changes
to your plugin that can affect the deployment of plugins, or you simply want to
start with a clean Liferay environment, *undeploy* your plugin and *clean* your
application server before redeploying your Ext plugins. By cleaning the
application server, the existing Liferay installation is removed and the bundle
specified in your Plugins SDK environment (e.g., the value of
`app.server.zip.name` in `build.[username].properties`) is unzipped in its
place. The exact steps you take differ based on whether you're developing in
Liferay Developer Studio or your terminal: 

**Using Developer Studio:**

1.  Remove the plugin from the server. While selecting the Ext plugin in the
    *Servers* view, select the plugin's *Remove* option. 

    ![Figure 12.6: Removing Ext Plugin from the server](../../images/07-ext-plugins-4.png)

2.  Clean the application server--while selecting the Ext plugin project in
    the *Package Explorer* view, select the plugin's *Liferay* &rarr; *Clean App
    Server...* option. 

    ![Figure 12.7: How to clean app server](../../images/07-ext-plugins-5.png)

3.  Start the Liferay server. 

    ![Figure 12.8: Start the Liferay server](../../images/07-ext-plugins-6.png)

4.  Drag the Ext plugin and drop it into the Liferay server. 

    ![Figure 12.9: Drag-and-drop plugin onto server](../../images/07-ext-plugins-7.png)

5.  While selecting the Liferay server in the *Servers* view, click the
    *Publish* option. 

    ![Figure 12.10: Publish your server](../../images/07-ext-plugins-8.png)

<!-- Way too many images here. 1 image per 500 words, please. --> 

**Using the terminal:** 
    
1.  Stop the Liferay server. 

2.  For each Ext plugin you're deploying, enter the following into your console: 

        cd [your-plugin-ext]
        ant clean-app-server
        ant direct-deploy

3.  Start the Liferay server. 

**Redeployment:** If you only added to your plugin or made modifications that
don't affect the plugin deployment process, you can redeploy using the following
steps: 

- **Using Developer Studio:** Right-click your plugin located underneath your
  server and select *Redeploy*. 

    ![Figure 12.11: How to redeploy your Ext plugin](../../images/07-ext-plugins-9.png)

- **Using the terminal:** Redeploy in the terminal using the same procedure as
  for initial deployment. Open a terminal window in your `ext/example-ext`
  directory and execute either `ant deploy` or `ant direct-deploy`.

See above in the *Initial deployment* section if you're not sure which command
to use. 

After your `example-ext` plugin is published to Liferay Portal, check out your
*basic* details page by choosing to add a user or view an existing user. 

![Figure 12.12: You should only see user fields for screen name, email address, first name, and last name](../../images/ext-plugin-user-basic-details.png)

That completes the development process. Let's learn how you can package your Ext
plugin for distribution and production. 

### Distribution [](id=ext-plugin-distribution-liferay-portal-6-2-dev-guide-12-en)

Once you're finished developing the plugin, you can package it in a `.war` file
for distribution and production deployment. 

**Using Developer Studio:** With your Ext plugin project selected in the
*Package Explorer* view, select the project's *Liferay* &rarr; *SDK* &rarr;
*war* option. 

![Figure 12.13: Accessing the *war* option](../../images/07-ext-plugins-10.png)

**Using the terminal:** From your Ext plugin's directory (e.g.,
`ext/example-ext`), enter

    ant war

The `.war` file is written to your `[liferay-plugins]/dist` directory.

You really have the hang of building and packaging your Ext plugins! Our
next section covers JBoss 7 requirements for packaging up an Ext plugin
containing a new taglib. If this doesn't apply to you, feel free to skip over
it and to start reading about advanced customization techniques. 

### Ext Plugin Packaging Requirements for JBoss 7 [](id=ext-plugin-jboss-packaging-requirements-liferay-portal-6-2-dev-guide-en)

If you're developing an Ext plugin that defines a new taglib, you need to take
JBoss's classloading behavior into account. Before packaging this kind of Ext
plugin, create a `jboss-deployment-structure.xml` file in the Ext plugin's
`WEB-INF/` folder and add the following contents to it:

    <jboss-deployment-structure xmlns="urn:jboss:deployment-structure:1.0">
            <deployment>
                    <dependencies>
                            <module name="deployment.util-taglib"></module>
                            <module name="com.liferay.portal"></module>
                    </dependencies>
            </deployment>
                    <module name="deployment.util-taglib">
                      <resources>
                                    <resource-root path="WEB-INF/ext-util-taglib/ext-util-taglib.jar" />
                                    <resource-root path="WEB-INF/lib/util-taglib.jar"></resource-root>
                                    <resource-root path="WEB-INF/lib/util-java.jar"></resource-root>
                            </resources>
                            <dependencies>
                                    <module name="javax.faces.api"></module>
                                    <module name="javax.servlet.api" />
                                    <module name="javax.servlet.jsp.api" />
                                    <module name="com.liferay.portal"></module>
                            </dependencies>
                    </module>
    </jboss-deployment-structure>

Also, add the following line to your Ext plugin's
`liferay-plugin-package.properties` file, setting the `util-taglib.jar` as a
dependency:

    portal-dependency-jars=util-taglib.jar

Once you've made these updates, you can package your plugin and deploy it, per
the normal process described previously in this chapter. 

Now that you've learned the basics of Ext plugin development have covered this
requirement for JBoss customizations, let's look at some advanced customizations
that you can do.

### Advanced Customization Techniques [](id=ext-advanced-customization-techniques-liferay-portal-6-2-dev-guide-en)

With Ext plugins, you can change almost everything in Liferay. Let's look at
some additional customization techniques made possible by Ext plugins. As
always, be careful when using Ext plugins. 

With each new version of Liferay, there can be changes to the implementation
classes. If you change Liferay's source code directly, you'll have to merge your
changes into the newer Liferay version. To minimize such conflicts, the best
approach is not to change anything. Rather, you can extend the class you want to
change and override the required methods. Then use the requisite Liferay
configuration files to reference your subclass as a replacement for the original
class. 

In the following subsections, we'll cover these topics:

- Using advanced configuration files
- Changing the API of a core service
- Replacing core classes in portal-impl

Let's learn to use advanced configuration files next. 

#### Using Advanced Configuration Files [](id=using-ext-advanced-configuration-files-liferay-portal-6-2-dev-guide-en)

Liferay uses several internal configuration files for its own architecture; in
addition, there are configuration files for the libraries and frameworks Liferay
depends on, like Struts and Spring. Configuration could be accomplished using
fewer files with more properties in each, but maintenance and use is made easier
by splitting up the configuration properties into several files. For advanced
customization needs, it may be useful to override the configuration specified in
multiple configuration files. Liferay provides a clean way to do this from an
Ext plugin without modifying the original files. 

Below we list all the configuration files in Liferay by their path in your Ext
plugin folder. We provide a description of what the file is for and the path to
the original file in Liferay Portal: 

- `ext-impl/src/META-INF/ext-model-hints.xml`
    - Description: Allows overriding the default properties of the fields of
      the data models used by Liferay's core portlets. These properties
      determine how the form fields for each model are rendered. 
    - Original file in Liferay:
      `portal-impl/src/META-INF/portal-model-hints.xml` 
- `ext-impl/src/META-INF/ext-spring.xml`
    - Description: Allows overriding the Spring configuration used by Liferay
      and any of its core portlets. It's most commonly used to configure
      specific data sources or swap the implementation of a given service with a
      custom one.
    - Original files in Liferay: `portal-impl/src/META-INF/*-spring.xml`
- `ext-impl/src/content/Language-ext_*.properties`
    - Description: Allow overriding the value of any key used by Liferay's UI
      to support *I18N*. 
    - Original file in Liferay: `portal-impl/src/content/Language-*.properties`
- `ext-impl/src/META-INF/portal-log4j-ext.xml`
    - Description: Allows overriding the log4j configuration. It's most
      commonly used to increase or decrease the log level of a given package or
      class, to obtain more information, or hide unneeded information from the
      logs. 
    - Original file in Liferay: `portal-impl/src/META-INF/portal-log4j.xml`
- `ext-impl/src/com/liferay/portal/jcr/jackrabbit/dependencies/\
repository-ext.xml`
    - Description: Allows overriding the configuration of the Jackrabbit
      repository. Refer to the Jackrabbit configuration documentation for
      details 
      ([http://jackrabbit.apache.org/jackrabbit-](http://jackrabbit.apache.org/jackrabbit-configuration.html)[configuration.html](http://jackrabbit.apache.org/jackrabbit-configuration.html)) 
    - Original file in Liferay:
      `portal-impl/src/com/liferay/portal/jcr/jackrabbit/dependencies/\
    repository.xml`
- `ext-web/docroot/WEB-INF/portlet-ext.xml`
    - Description: Allows overriding the declaration of the core portlets
      included in Liferay. It's most commonly used to change the init parameters
      or the roles specified. 
    - Original file in Liferay: `portal-web/docroot/WEB-INF/portlet-custom.xml`
- `ext-web/docroot/WEB-INF/liferay-portlet-ext.xml`
    - Description: Allows overriding the Liferay-specific declaration of the
      core portlets included in Liferay. Refer to the
      `liferay-portlet-app_6_1_0.dtd` file for details on all the available
      options. Use this file with care; the code of the portlets may be assuming
      some of these options to be set to certain values. 
    - Original file in Liferay: `portal-web/docroot/WEB-INF/liferay-portlet.xml`
- `ext-web/docroot/WEB-INF/liferay-display.xml`
    - Description: Allows overriding the portlets that are shown in the
      Add Application pop-up and the categories in which they're organized. It's
      most commonly used to change the categorization, hide certain portlets, or
      make specific Control Panel portlets available to be added to a page. 
    - Original file in Liferay: `portal-web/docroot/WEB-INF/liferay-display.xml`
- `ext-web/docroot/WEB-INF/liferay-layout-templates-ext.xml`
    - Description: Allows specifying custom template files for each of Liferay's
      standard layout templates. This is rarely necessary. 
    - Original file in Liferay:
      `portal-web/docroot/WEB-INF/liferay-layout-templates.xml`
- `ext-web/docroot/WEB-INF/liferay-look-and-feel-ext.xml`
    - Description: Allows changing the properties of Liferay's default themes.
      This is rarely used. 
    - Original file in Liferay:
      `portal-web/docroot/WEB-INF/liferay-look-and-feel.xml`

Let's learn how to configure a Lucene Analyzer next. 

#### Configuring Lucene Analyzers [](id=configuring-lucene-analyzers-with-ext-liferay-portal-6-2-dev-guide-en)

Liferay uses Lucene to facilitate search and indexing within the portal. In old
versions of Liferay, you could configure Lucene analyzers from
`portal-ext.properties`. While convenient, it was problematic to have only a
single analyzer for all portal-indexed fields. For example, it was difficult to
provide correct behaviors for handling both keyword and text fields. 

Since Liferay 6.1, you no longer configure Lucene from `portal-ext.properties`.
Instead, Lucene analyzers are defined in Spring configuration files. The default
analyzer configuration is defined in
`/portal-impl/src/META-INF/search-spring.xml`. Liferay 6.1 introduced per-field
analyzers, allowing Lucene's query parser to identify the correct analyzer to
handle any field. 

There are two common scenarios where it's useful to configure Lucene analyzers:
when creating custom language analyzers to override a Liferay language analyzer,
and when creating a custom indexer to index new fields. Liferay provides a large
number of language analyzers in `search-spring.xml` out of the box and uses
regular expression matching to map localized fields to specific analyzers. If
your language is not included among the defaults, or you're not satisfied with
one of the default language analyzers, you can override it with a custom
analyzer. If you've created a custom indexer to index new fields, you can use
the default analyzer for your new fields, select a specific one (such as one of
the `KeywordAnalyzers`), or define a custom analyzer. 

To customize the Lucene analyzer configuration, you must create an Ext plugin.
The Analyzer classes reference the Lucene APIs directly, so it's not possible
to configure Lucene analyzers from a hook plugin. In your Ext plugin, create a
`/docroot/WEB-INF/ext-impl/src/META-INF/ext-spring.xml` file
and declare the beans you'll override with a custom configuration. 

Here are a few of the pre-configured analyzers from the
`com.liferay.portal.search.lucene.PerFieldAnalyzerWrapper` bean configuration in
`search-spring.xml`:

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

You can define custom analyzers for any fields, including custom fields. 

Let's learn to change the API of a core service next. 

#### Changing the API of a Core Service [](id=changing-core-service-api-with-ext-liferay-portal-6-2-dev-guide-en)

Sometimes you might need to change the API of a method provided by one of
Liferay's services (e.g., `UserLocalService`). This is an advanced customization
need. 

Is it even possible to change the API of a core service? Not directly. Don't
worry, we didn't put this section here just to tell you it's not possible. 
Changing a core service API under normal circumstances requires modifying
Liferay's source code directly and making manual changes to a slew of files.
But that's not the Liferay way: there's a better way to do it. 

The best way to extend an existing service is by creating a custom service
that's complementary (e.g., a `MyUserLocalService` that includes all the new
methods). Your custom code can invoke this service instead of the default
service, and the implementation of your service can invoke the original service
as needed. 

This technique doesn't require an Ext plugin since it can be done from portlet
plugins. In fact, using Service Builder for an Ext plugin is deprecated, but
it's supported for migration from the old extension environment. 

Sometimes it's desirable to change the implementation of the original service to
call your custom one; that's when you'll need an Ext plugin. Override the Spring
definition for `UserLocalServiceUtil` in `ext-spring.xml` and point it to your
`MyUserLocalServiceImpl` (instead of `UserLocalServiceImpl`). Now both
`MyUserLocalServiceUtil` and `UserLocalServiceUtil` will use the same Spring
bean: your new implementation. 

You can also replace core classes in `portal-impl`. Keep reading to find out
how. 

#### Replacing Core Classes in `portal-impl` [](id=replacing-core-classes-in-portal-impl-liferay-portal-6-2-dev-guide-12-en)

If you're sure you need to change a core portal-impl class, and certain it can't
be replaced in a configuration file, here's the best way to do it while avoiding
conflicts when merging with a new portal version: 

1.  Rename the original class (e.g., `DeployUtil` &rarr; `MyDeployUtil`). 

2.  Create a new subclass with the old name (e.g., `DeployUtil extends
    MyDeployUtil`). 

3.  Override any methods you need to change. 

4.  Delegate static methods. 

5.  Use a logger with and appropriate class name for both classes (e.g.,
   `DeployUtil`). 

This strategy will help you determine what you'll need to merge when a new
version of Liferay is released. 

---

 ![tip](../../images/tip-pen-paper.png) **Tip:** This is an advanced technique;
 it may have a large impact on the maintainability of your code, especially if
 abused. Seek alternatives, and if you're sure this is your only option, think
 of it as a short term solution. Contact Liferay's developers about applying the
 necessary changes to the product's source code. 

---

That's it for advanced customization techniques. Let's talk about deploying in
production next. 

## Deploying in Production [](id=deploying-ext-plugin-in-production-liferay-portal-6-2-dev-guide-en)

Often times you can't use Ant to deploy web applications in production or
pre-production environments. Additionally, some application servers such as
WebSphere or WebLogic have their own deployment tools, and Liferay's autodeploy
process won't work. Let's look at two methods for deploying and redeploying Ext
plugins in these scenarios. 

### Method 1: Redeploying Liferay's Web Application [](id=redeploying-liferays-web-application-liferay-portal-6-2-dev-guide-en)

You can use this method in any application server that supports auto-deploy;
Tomcat and Glassfish are two examples. What's the benefit? The only artifact
that needs to be transferred to the production system is your Ext plugin's
`.war` file, produced using the `ant war` target. This `.war` file is usually
small and easy to transport. Execute these steps on the server: 

1.  Redeploy Liferay:

    If this is your first time deploying your Ext plugin to this server, skip
    this step. Otherwise, start by executing the same steps you first used to
    deploy Liferay on your app server. If you're using a bundle, unzip it again.
    If you installed Liferay manually on an existing application server, you'll
    need to redeploy the Liferay `.war` file and copy both the libraries
    required globally by Liferay and your Ext plugin to the appropriate
    directory within the application server. 

2.  Copy the Ext plugin `.war` into the auto-deploy directory. For a bundled
    Liferay distribution, the `deploy` folder is in Liferay's *root* folder of
    your bundle (e.g., `liferay-portal-6.2.0-ce-ga1/`).

3.  Once the Ext plugin is detected and deployed by Liferay, restart your
    Liferay server. 

### Method 2: Generate an Aggregated WAR File [](id=generating-an-aggregated-war-file-liferay-portal-6-2-dev-guide-12-en)

Some application servers don't support auto-deploy; WebSphere and WebLogic are
two examples. With an aggregated WAR file, all Ext plugins are merged before
deployment to production. A single `.war` file will contain Liferay plus the
changes from all your Ext plugins. Before you deploy the Liferay `.war` file,
copy the dependency `.jar` files for Liferay and all Ext plugins to the global
application server class loader in the production server. The precise location
varies from server to server; see [Using Liferay Portal
6.2](http://www.liferay.com/documentation/liferay-portal/6.2/user-guide) to get
the details for your application server. 

The first step in creating the aggregated `.war` file is to deploy your Ext
plugin. The remaining steps can differ depending on your application server;
let's proceed by assuming you're using a Liferay Tomcat bundle. Deploy your
plugin, restart the server, then shut it down. The files are now aggregated in
your app server. Create a `.war` file by zipping the `webapps/ROOT` folder of
Tomcat, then copy all the libraries from the `lib/ext` directory of Tomcat to
your application server's global classpath--these files are associated with your
Ext plugins. 

Once your `.war` file is aggregated, perform these actions on your server: 

1.  Redeploy Liferay using the aggregated WAR file. 

2.  Stop the server and copy the new version of the global libraries to the
    appropriate directory in the application server. 

<!-- I really don't like this. Do we know where we got these instructions? We
need to try to avoid using Tomcat as a solution for deploying something to
another app server. It makes the erroneous case that Liferay is somehow
"designed for" or "integrates best with" Tomcat, and that's not the message we
want to send. I think we need to change this and get Tomcat out of it. --> 

Next we'll show you how to migrate your extension environment (from older
versions of Liferay) into Ext plugins. 

## Migrating Old Extension Environments [](id=migrating-old-extension-environments-liferay-portal-6-2-dev-guide-12-en)

Because Ext plugins are an evolution of the extension environment provided in
Liferay 5.2 and earlier, you might need to migrate your extension environment
into Ext plugins when upgrading Liferay. If you need to do this, we have good
news; migrating is automated and relatively easy. 

---

 ![tip](../../images/tip-pen-paper.png) **Tip:** When migrating an extension
 environment, first consider whether any of the extension environment's features
 can be moved into other types of plugins. Portlets and hooks are designed to
 meet specific needs and they're easier to learn. Additionally, they're easier
 to maintain since they often require fewer changes when upgrading to a new
 version of Liferay. 

---

To successfully migrate, execute an Ant target within the `ext` directory of the
Plugins SDK, pointing to the old extension environment and naming the new
plugin. Be sure to remove the line escape character `\` from the following
example: 

    ant upgrade-ext -Dext.dir=/projects/liferay/ext -Dext.name=my-ext\
    -Dext.display.name="My Ext"


Let's look at the three parameters we used above: 

- `ext.dir`: The location of the old extension environment. 
- `ext.name`: The name of the Ext plugin that you want to create. 
- `ext.display.name`: The display name. 

After executing the target, you should see the logs of several copy operations
that will take files from the extension environment and copy them into the
equivalent directory within the Ext plugin (see the section *Creating an Ext
plugin* for an explanation of the main directories within the plugin).

With the migration process finished, you can upgrade your code to the new
version of Liferay by completing a few additional tasks. Most commonly, you
should do the following: 

- Review the uses of Liferay's APIs and adapt them accordingly. 
- Review any changes to the new version of Liferay's JSPs. Merge your changes
into the JSPs of the new Liferay version. 
- Run `ant build-service` again, to use Service Builder. It's also recommended
to consider moving this code to a portlet plugin, because Service Builder is
deprecated in Ext, and plugins allow for greater modularity and maintainability.
- If you implemented any portlets in the old extension environment, migrate them
to portlet plugins; extension environment portlets have been deprecated since
Liferay Portal 6.0, and support isn't guaranteed in future Liferay Portal
releases. 

#### Licensing and Contributing [](id=licensing-and-contributing-liferay-portal-6-2-dev-guide-12-en)

Liferay Portal is Open Source software licensed under the LGPL 2.1 license
([http://www.gnu.org/licenses/lgpl-2.1.html](http://www.gnu.org/licenses/lgpl-2.1.html)).
If you reuse any code snippet and redistribute it, whether publicly or to a
specific customer, make sure your modifications are compliant with the license.
One common way is to make the source code of your modifications available to the
community under the same license. Make sure you read the license text yourself
to find the option that best fits your needs. 

If your goal in making changes is fixing a bug or improving Liferay, it could be
of interest to a broader audience. Consider contributing it back to the project.
That benefits all users of the product, including you since you won't have to
maintain the changes with each newly released version of Liferay. You can notify
Liferay of bugs or improvements in
[issues.liferay.com](http://issues.liferay.com). There is also a wiki page with
instructions on how to contribute to Liferay: 

[http://www.liferay.com/community/wiki/-/wiki/Main/Contributing](http://www.liferay.com/community/wiki/-/wiki/Main/Contributing)

## Summary [](id=summary-liferay-portal-6-2-dev-guide-12-en)

Ext plugins are a powerful way to extend Liferay. There are no limits to what
you can use them to customize, so use them carefully. Before using an Ext
plugin, see if you can implement all or part of the desired functionality
through a different plugin type: portlets, hooks, and web plugins offer you a
lot of extension capabilities themselves, without introducing the complexity
that's inherent with Ext plugins. If you need to use an Ext plugin, make it as
small as possible and follow the instructions in this guide carefully to avoid
issues.

Next, we'll take a look at some helpful plugin developer references. So get
ready to bookmark plenty of links! 
