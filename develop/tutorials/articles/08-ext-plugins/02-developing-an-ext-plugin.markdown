# Developing an Ext Plugin [](id=developing-an-ext-plugin)

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

Now let's look at each step of the development proces in more detail. 

## Set up [](id=set-up)

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
`C:/downloads/liferay-portal-tomcat-6.1.2-ce-ga3.zip` which we
set as the value for our `app.server.zip.name` property, the *relative path* to
the application server *within* our Liferay bundle `.zip` file is
`liferay-portal-6.1.2-ce-ga3\tomcat-7.0.40`. We'd then specify
`C:/work/liferay-portal-6.1.2-ce-ga3/tomcat-7.0.40` as our `app.server.dir`
property value. 

+$$$

**Note:** Some Liferay bundles come installed with a sample website. It's useful
for showcasing certain features of Liferay, but if you removed it, you likely
don't want it reinstalled each time your bundle is unzipped. To prevent the
reinstallation of 7-Cogs, unzip your bundle, delete the
`[work]/liferay-portal-[version]/[app-server]-[version]/webapps/welcome-theme`
folder, then re-zip your bundle. 

$$$

Next we'll change our newly created Ext plugin and deploy it. 

## Initial deployment [](id=initial-deployment)

Our environment is set up and we're ready to start customizing. First let's look
at a simple example that customizes the sections of a user profile. The
configuration can be made through the `portal-ext.properties` configuration
file, but a hook plugin won't let us modify the property we're customizing.
Open the `docroot/WEB-INF/ext-impl/src/portal-ext.properties` file and paste in
the following contents: 

    users.form.update.main=details,password,organizations,sites,roles

We've removed the sections for user groups, personal sites, and categorizations
from the user profile page. This ensures that these sections won't be used in
our portal. 

Now we're ready to deploy. 

### Deploy the plugin [](id=deploy-the-plugin)

You can deploy your plugin from Liferay Developer Studio or the terminal. 

**Deploying In Developer Studio:** Drag your `example-ext` project from
your Package Explorer onto your server. 

![Figure 8.3: Drag-and-drop plugin onto server](../../images/07-ext-plugins-7.png)

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

### Publish the plugin [](id=publish-the-plugin)

To complete the deployment process, your Ext plugin must be published to the
Liferay server. As with deployment, you can publish using Liferay Developer
Studio or your terminal. 

**Publishing in Developer Studio:** 

1.  Select the Liferay server in the *Servers* view. 

2.  Select the server's *Publish* option. 

![Figure 8.4: How to publish the Ext Plugin](../../images/07-ext-plugins-3.png)

**Publishing in the terminal:** Restart the Liferay server. 

Let's try Liferay portal, customized by your Ext plugin. Once your server
restarts, log in as an administrator and go to Control Panel &rarr; Users and
Organizations. Edit an existing user and verify that the right navigation menu
only shows the five sections that we referenced from the
`users.form.update.main` property. 

![Figure 8.5: You should see these five sections under the User Information heading](../../images/ext-plugin-five-sections.png)

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

<!-- We have to try to keep code to 80 columns (85 in Markdown, since we have to
indent to get code formatting) so that the code fits on a printed page, for when
we do the print version of this book. -->

We don't need to add a new key to `Language-ext.properties`, because an entry
for the key named *basic* is already included in Liferay's language bundle. 

Let's redeploy our Ext plugin to review the changes we made. 

## Redeployment [](id=redeployment)

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

    ![Figure 8.6: Removing Ext Plugin from the server](../../images/07-ext-plugins-4.png)

2.  Clean the application server--while selecting the Ext plugin project in
    the *Package Explorer* view, select the plugin's *Liferay* &rarr; *Clean App
    Server...* option. 

    ![Figure 8.7: How to clean app server](../../images/07-ext-plugins-5.png)

3.  Start the Liferay server. 

    ![Figure 8.8: Start the Liferay server](../../images/07-ext-plugins-6.png)

4.  Drag the Ext plugin and drop it into the Liferay server. 

    ![Figure 8.9: Drag-and-drop plugin onto server](../../images/07-ext-plugins-7.png)

5.  While selecting the Liferay server in the *Servers* view, click the
    *Publish* option. 

    ![Figure 8.10: Publish your server](../../images/07-ext-plugins-8.png)

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

	![Figure 8.11: How to redeploy your Ext plugin](../../images/07-ext-plugins-9.png)

- **Using the terminal:** Redeploy in the terminal using the same procedure as
  for initial deployment. Open a terminal window in your `ext/example-ext`
  directory and execute either `ant deploy` or `ant direct-deploy`.

See above in the *Initial deployment* section if you're not sure which command
to use. 

After your `example-ext` plugin is published to Liferay Portal, check out your
*basic* details page by choosing to add a user or view an existing user. 

![Figure 8.12: You should only see user fields for screen name, email address, first name, and last name](../../images/ext-plugin-user-basic-details.png)

That completes the development process. Let's learn how you can package your Ext
plugin for distribution and production. 

## Distribution [](id=distribution)

Once you're finished developing the plugin, you can package it in a `.war` file
for distribution and production deployment. 

**Using Developer Studio:** With your Ext plugin project selected in the
*Package Explorer* view, select the project's *Liferay* &rarr; *SDK* &rarr;
*war* option. 

![Figure 8.13: Accessing the *war* option](../../images/07-ext-plugins-10.png)

**Using the terminal:** From your Ext plugin's directory (e.g.,
`ext/example-ext`), enter

    ant war

The `.war` file is written to your `[liferay-plugins]/dist` directory.

You really have the hang of building and packaging your Ext plugins! Our
next section covers JBoss 7 requirements for packaging up an Ext plugin
containing a new taglib. If this doesn't apply to you, feel free to skip over
it and to start reading about advanced customization techniques. 

## Ext Plugin Packaging Requirements for JBoss 7 [](id=ext-plugin-packaging-requirements-for-jboss-7)

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

## Advanced customization techniques [](id=advanced-customization-techniques)

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

### Using advanced configuration files [](id=using-advanced-configuration-files)

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
    - Original file in Liferay: `portal-impl/src/META-INF/portal-model-hints.xml`
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
- `ext-impl/src/com/liferay/portal/jcr/jackrabbit/dependencies/repository-ext.xml`
    - Description: Allows overriding the configuration of the Jackrabbit
      repository. Refer to the Jackrabbit configuration documentation for
      details 
      ([http://jackrabbit.apache.org/jackrabbit-](http://jackrabbit.apache.org/jackrabbit-configuration.html)[configuration.html](http://jackrabbit.apache.org/jackrabbit-configuration.html)) 
    - Original file in Liferay:
      `portal-impl/src/com/liferay/portal/jcr/jackrabbit/dependencies/repository.xml`
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

### Configuring Lucene Analyzers [](id=configuring-lucene-analyzers)

Liferay uses Lucene to facilitate search and indexing within the portal. In old
versions of Liferay, you could configure Lucene analyzers from
`portal-ext.properties`. While convenient, it was problematic to have only a
single analyzer for all portal-indexed fields. For example, it was difficult to
provide correct behaviors for handling both keyword and text fields. 

In Liferay 6.1, you no longer configure Lucene from `portal-ext.properties`.
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

### Changing the API of a core service [](id=changing-the-api-of-a-core-service)

Sometimes you might need to change the API of a method provided by one of
Liferay's services (e.g., `UserLocalService`). This is an advanced customization
need. 

Is it even possible to change the API of a core service? Not directly. Don't
worry, we didn't put this section here just to tell you it's not possible. 
Changing a core service API under normal circumstances requires modifying
Liferay's source code directly and making manual changes to a slew of files.
But that's not the Liferay way: there's a better way to do it. 

The best way to extend an existing service is by creating a custom service
that's complementary ( e.g., a `MyUserLocalService` that includes all the new
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

### Replacing core classes in portal-impl [](id=replacing-core-classes-in-portal-impl)

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

+$$$

**Tip:** This is an advanced technique; it may have a large impact on the
maintainability of your code, especially if abused. Seek alternatives, and if
you're sure this is your only option, think of it as a short term solution.
Contact Liferay's developers about applying the necessary changes to the
product's source code. 

$$$

That's it for advanced customization techniques. Let's talk about deploying in
porudction next. 
