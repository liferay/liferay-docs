
# Ext plugins

Ext plugins provide the most powerful method of extending Liferay. This
comes with some tradeoffs in complexity, and so Ext plugins are designed
to be used only in special scenarios in which all other plugin types
cannot meet the needs of the project.

Before deciding to use an Ext plugin it's important to understand the
costs of using such a powerful tool. The main one is the cost in terms
of maintenance. Because Ext plugins allow using internal APIs or even
overwriting files provided in the Liferay core, it's necessary to review
all the changes done when updating to a new version of Liferay (even if
it's a maintenance version or a service pack). Also, unlike the other
types of plugins, Ext plugins require the server to be restarted after
deployment, as well as requiring additional steps when deploying or
redeploying to production systems.

The main use cases in which an Ext plugin may be needed are:

-   Customizing `portal.properties` that are not supported by
    Hook Plugins

-   Customizing Struts Actions

-   Providing custom implementations for any of the Liferay beans
    declared in Liferay's Spring files (use service wrappers from a hook
    instead if possible)

-   Adding JSPs that are referenced from portal properties that can only
    be changed from an ext plugin (be sure to check if the property can
    be modified from a hook plugin before doing this)

-   Direct overwriting of a class (not recommended unless it's strictly
    necessary)

## Creating an Ext plugin

Ext plugins are stored within the `ext` directory of the
Plugins SDK. Navigate to this directory in a terminal and enter the
following command to create a new Ext plugin (Linux and Mac OS X):

    ./create.sh example "Example"

On Windows enter the following instead:

    create.bat example "Example"

You should get a BUILD SUCCESSFUL message from Ant, and there will now
be a new folder inside of the `ext` folder in your Plugins
SDK. Notice that the Plugins SDK automatically appends "-ext" to the
project name when creating this folder.

Once the target has been executed successfully you will find a new
folder called example-ext with the following structure:

    /ext-example/
		/docroot/
			/WEB-INF/
			/sql/
			/ext-impl/
				/src/
			/ext-lib/
				/global/
				/portal/
			/ext-service/
				/src/
			/ext-util-bridges/
				/src/
			/ext-util-java/
				/src/
			/ext-util-taglib/
				/src/
			/ext-web/

The most significant directories in this structure are the ones inside
the `docroot/WEB-INF` directory. In particular you should be
familiar with the following directories:

-   **ext-impl/src:** Contains the `portal-ext.properties`
    configuration file, custom implementation classes, and in advanced
    scenarios, classes that override core classes within
    `portal-impl.jar.`

-   **ext-lib/global:** Place here any libraries that should be copied
    to the global classloader of the application server upon deployment
    of the ext plugin.

-   **ext-lib/portal:** Place here any libraries that should be copied
    inside Liferay's main application. Usually these libraries are
    needed because they are invoked from the classes added within
    `ext-impl/src`.

-   **ext-service/src:** Place here any classes that should be available
    to other plugins. When using Service Builder, it will put the
    interfaces of each service here. Also in advanced scenarios, this
    directory will contain classes that overwrite the classes of
    `portal-service.jar`.

-   **ext-web/docroot:** Contains configuration files for the web
    application, including `WEB-INF/struts-config-ext.xml`
    which will allow customizing Liferay's own core struts actions. You
    can also place any JSPs needed by your customizations here.

-   **Other:** `ext-util-bridges`, `ext-util-java`
    and `ext-util-taglib` are only needed in advanced
    scenarios in which you need to customize the classes of three
    libraries provided with Liferay: `util-bridges.jar`,
    `util-java.jar` and `util-taglib.jar`
    respectively. In most scenarios you can just ignore these
    directories.

By default, several files are added to the plugin. Here are the most
significant ones:

-   Inside `docroot/WEB-INF/ext-impl/src`:

    -   `portal-ext.properties`: this file can be used to
        overwrite any configuration property of Liferay, even those that
        cannot be overridden by a hook plugin (which is always preferred
        when possible). Note that if this file is included it will be
        read instead of any other `portal-ext.properties` in
        the application server. Because of that you may need to copy
        into it the properties related to the database connection, file
        system patches, ... etc.

-   Inside `docroot/WEB-INF/ext-web/docroot/WEB-INF`:

    -   `portlet-ext.xml`: Can be used to overwrite the
        definition of a Liferay portlet. In order to do this, copy the
        complete definition of the desired portlet from
        `portlet-custom.xml` within Liferay's source code and
        then apply the necessary changes.

    -   `liferay-portlet-ext.xml`: Similar to the file above,
        but for the additional definition elements that are specific to
        Liferay. In order to override it, copy the complete definition
        of the desired portlet from `liferay-portlet.xml`
        within Liferay's source code and then apply the necessary
        changes.

    -   `struts-config-ext.xml` and `tiles-defs-ext.xml`:
        Can be used to customize the struts actions used by Liferay's
        core portlets.

![image](../../images/tip-pen-paper.png)Tip: after creating an Ext
plugin, remove all of the files added by default that are not necessary
for the extension. This is important because Liferay keeps track of the
files deployed by each Ext plugin and it won't allow deploying two Ext
plugins if they override the same file to avoid collisions. By removing
any files not really necessary from an ext plugin it will be easier to
use along with other Ext plugins.

## Developing an Ext plugin

Developing an Ext plugin is slightly different than working with other
plugin types. The main reason for the difference is that an Ext plugin
when deployed will make changes to the Liferay web application itself,
instead of staying as a separate component that can be removed at any
time. It's important to remember that *once an Ext plugin has been
deployed, some of its files are copied inside the Liferay installation,
so the only way to remove its changes is to redeploy an unmodified
Liferay application again.*

The Plugins SDK contains several Ant targets that help with the task of
deploying and redeploying during the development phase. In order to do
this it requires a `.zip` file of a Tomcat 6 based Liferay
bundle. The Ant targets will unzip and clean up this installation
whenever needed to guarantee that any change done to the Ext plugin
during development is properly applied and previous changes that have
been removed are not left behind. This is part of the added complexity
when using Ext plugins, and so it is recommended to use another plugin
type to accomplish your goals if it is at all possible.

### Set up

Before attempting to deploy an Ext plugin, it's necessary to edit the
file `build.{username}.properties` in the root folder of the
Plugins SDK. If this file doesn't exist yet you should create it.
Substitute `{username}` with the your user ID on your
computer. Once the file is open, add the following three properties to
the file, making sure the individual paths point to the right locations
on your system:

    app.server.dir={...}/liferay-portal-6.0.6/tomcat-6.0.26
    app.server.zip.name={...}/liferay-portal-tomcat-6.0.6.zip
    ext.work.dir={...}/work

`app.server.zip.name` should point to a `.zip` with
a bundle of Liferay. The directory denoted by the property
`ext.work.dir` will be used to unzip the bundle as well as
remove it and unzip again as needed. `app.server.dir` should
point to the Tomcat directory inside the work directory.

For example, if `ext.work.dir` points to
`C:\ext-work`, and `app.server.zip.name` points to
`C:\files\liferay-portal-tomcat-6.0-${lp.version}.zip`, then
`app.server.dir` should point to
`C:\ext-work\liferay-portal-${lp.version}tomcat-6.0.18`.

### Initial deployment

Once the environment is set up, we are ready to start customizing. We'll
show the full process with a simple example, customizing the sections of
a user profile. Liferay allows doing that through the
`portal-ext.properties` configuration file, but we'll be
changing a property that cannot be changed from a hook plugin. In order
to make this change, open the
`docroot/WEB-INF/ext-impl/src/portal-ext.properties` file and
paste the following contents inside:

    users.form.update.main=details,password,organizations,sites,user-groups,roles,personal-site,categorization

This line removes the sections for user groups, pages and
categorizations. We might want to make this change because we don't want
them in our portal.

Once we've made this change, we are ready to deploy. Open a terminal
window in your `ext/example-ext` directory and enter this
command:

    ant deploy

You should get a BUILD SUCCESSFUL message, which means that your plugin
is now being deployed. If you switch to the terminal window running
Liferay and wait for a few seconds, you should see the message
"Extension environment for example-ext has been applied. You must
restart the server and redeploy all other plugins." Redeploying all
other plugins is not strictly mandatory, but you should do it if some
changes applied through the Ext plugin may affect the deployment process
itself.

The `ant deploy` target builds a `.war` file with
all the changes you have made and copies them to the auto deploy
directory inside the Liferay installation. When the server starts, it
detects the `.war` file, inspects it, and copies its content
to the appropriate destinations within the deployed and running Liferay
inside your application server. You must now restart your application
server.

Once the server has started, log in as an administrator and go to
*Control Panel -\> Users*. Edit an existing user and verify that the
right navigation menu only shows the five sections that were referenced
from the `users.form.update.main` property.

Once we've applied this simple modification to Liferay, we can go ahead
with a slightly more complex customization. This will give us an
opportunity to learn the proper way to redeploy an Ext plugin, which is
different from the initial deployment.

For this example we'll customize the *details* view of the user profile.
We could do that just by overwriting its JSP, but this time we'll use a
more powerful method which also allows adding new sections or even
merging the existing ones. Liferay allows referring to custom sections
from the `portal-ext.properties` and implementing them just by
creating a JSP. In our case we'll modify the property
`users.form.update.main` once again to set the following
value:

    users.form.update.main=basic,password,organizations,sites,user-groups,roles,personal-site,categorization

That is, we removed the section *details* and added a new custom one
called *basic*. When Liferay's user administration reads this property
it looks for the implementation of each section based on the following
conventions:

-   The section should be implemented in a JSP inside the directory:

	html/portlet/enterprise_admin/user

-   The name of the JSP should be like the name of the section plus the
    `.jsp` extension. There is one exception. If the section
    name has a dash sign ("-"), it will be converted to an underscore
    sign ("_"). For example, if the section is called *my-info*, the
    JSP should be named `my_info.jsp`. This is done to comply
    to common standards of JSP naming.

-   The name of the section that will be shown to the user will be
    looked for in the language bundles. When using a key/value that is
    not already among the ones included with Liferay, you should add it
    to the `Language-ext.properties` and each of the language
    variants for which we want to provide a translation. Within the Ext
    plugin these files should be placed within `ext-impl/src`.

In our example, we'll need to create a file within the Ext plugin in the
following path:

    ext-web/docroot/html/portlet/enterprise_admin/user/basic.jsp

For the contents of the file, you can write them from scratch or make a
copy of the `details.jsp` file from Liferay's source code and
modify from there. In this case we've decided to do the latter and then
remove some fields to simplify the creation of a user. The result is
this:

    <%@ include file="/html/portlet/enterprise_admin/init.jsp" %>
    <%
	User selUser = (User)request.getAttribute("user.selUser");
    %>
    
    <liferay-ui:error-marker key="errorSection" value="details" />
    <aui:model-context bean="<%= selUser %>" model="<%= User.class %>" />
    
    <h3><liferay-ui:message key="details" /></h3>
    
    <aui:fieldset column="<%= true %>" cssClass="aui-w50">
		<liferay-ui:error exception="<%= DuplicateUserScreenNameException.class%>"
			message="the-screen-name-you-requested-is-already-taken" />
		
		<liferay-ui:error exception="<%= ReservedUserScreenNameException.class%>"
			message="the-screen-name-you-requested-is-reserved" />
		
		<liferay-ui:error exception="<%= UserScreenNameException.class %>"
			message="please-enter-a-valid-screen-name" />
		
		<aui:input name="screenName" />
		
		<liferay-ui:error exception="<%=DuplicateUserEmailAddressException.class %>"
			message="the-email-address-you-requested-is-already-taken" />
		
		<liferay-ui:error exception="<%= ReservedUserEmailAddressException.class%>"
			message="the-email-address-you-requested-is-reserved" />
		
		<liferay-ui:error exception="<%= UserEmailAddressException.class %>"
			message="please-enter-a-valid-email-address" />
		
		<aui:input name="emailAddress" />
		
		<liferay-ui:error exception="<%= ContactFirstNameException.class %>"
			message="please-enter-a-valid-first-name" />
		
		<liferay-ui:error exception="<%= ContactFullNameException.class %>" 
			message="please-enter-a-valid-first-middle-and-last-name" />
		
		<aui:input name="firstName" />
		
		<liferay-ui:error exception="<%= ContactLastNameException.class %>"
			message="please-enter-a-valid-last-name" />
		
		<aui:input name="lastName" />
    </aui:fieldset>

In our case, we don't need to add a new key to
`Language-ext.properties`, because "basic" is already included
in Liferay's language bundle. We are ready to redeploy.

### Redeployment

So far, the process has been very similar to that of other plugin types.
The differences start when redeploying an Ext plugin that has already
been deployed. As mentioned earlier, when the plugin was first deployed
*some of its files were copied within the Liferay installation.* After
making any change to the plugin the recommended steps to redeploy are
first to stop the application server, and then to execute the following
ant targets:

    ant clean-app-server direct-deploy

These ant targets first remove the work bundle (unzipping the one that
was referred to through `build.{username}.properties`), and
then deploy all the changes directly to the appropriate directories. The
`direct-deploy` target is faster because the changes are
applied directly, while the Liferay server does it on start up if you
use the `deploy` target. For that reason it is usually
preferred during development.

You can deploy several Ext plugins to the same server, but you will have
to redeploy each of them after executing the
`clean-app-server` target.

Once you have finished the development of the plugin you can execute the
following ant target to generate a `.war` file for
distribution:

    ant war

The file will be available within the `dist` directory in the
root of the plugins SDK.

### Advanced customization techniques

This section covers additional customization techniques that are
possible through an Ext plugin. As mentioned above, you can change
almost everything within Liferay when using the Ext plugin, therefore be
careful when using such a powerful tool.

Always keep in mind that with every new Liferay version, implementation
classes may have changed. Thus if you've changed Liferay source code
directly, you may have to merge your changes into the newer Liferay
version. General approach for minimizing conflicts is – don't change
anything, only extend.

The alternative is to extend the class you want to change and override
methods needed. Then use some of Liferay's configuration files to
reference your subclass as a replacement of the original class.

This and other advanced techniques are described in detail in the
following sections.

#### Advanced configuration files

Liferay uses several internal configuration files for easier maintenance
and also to configure the libraries and frameworks it depends on, such
as Struts or Spring. For advanced customization needs it may be useful
to override the configuration specified in these files, so Liferay
provides a clean way to do that from an Ext plugin without modifying the
original files.

Next is a list of all of these files, along with a description and a
reference to the original file in the path where they can be found in
the source code of Liferay (you may need to look at them for reference):

-   `ext-impl/src/META-INF/ext-model-hints.xml`

    -   Description: This file allows overwriting the default properties
        of the fields of the data models used by Liferay's core
        portlets. These properties determine how the form to create or
        edit each model is rendered.

    -   Original file in Liferay:
        `portal-impl/src/META-INF/portal-model-hints.xml`

-   `ext-impl/src/META-INF/ext-spring.xml`

    -   Description: This file allows overwriting the Spring
        configuration used by Liferay and any of its core portlets. The
        most common usage is to configure specific datasources or to
        swap the implementation of a given service with a custom one.

    -   Original files in Liferay:
        `portal-impl/src/META-INF/*-spring.xml`

-   `ext-impl/src/content/Language-ext_*.properties`

    -   Description: These files allow overwriting the value of any key
        used by Liferay's UI to support *I18N*.

    -   Original file in Liferay:
        `portal-impl/src/content/Language-*.properties`

-   `ext-impl/src/META-INF/portal-log4j-ext.xml`

    -   Description: This file allows overwriting the log4j
        configuration. The most common usage is to increase or decrease
        the log level of a given package or class to obtain more
        information or hide unneeded information from the logs
        respectively.

    -   Original file in Liferay:
        `portal-impl/src/META-INF/portal-log4j.xml`

-   `ext-impl/src/com/liferay/portal/jcr/jackrabbit/dependencies/repository-ext.xml`

    -   Description: This file allows overwriting the configuration of
        the Jackrabbit repository. Refer to the Jackrabbit configuration
        documentation for details
        ([http://jackrabbit.apache.org/jackrabbit-](http://jackrabbit.apache.org/jackrabbit-configuration.html)[configuration.html](http://jackrabbit.apache.org/jackrabbit-configuration.html))

    -   Original file in Liferay:
        `portal-impl/src/com/liferay/portal/jcr/jackrabbit/dependencies/repository.xml`

-   `ext-web/docroot/WEB-INF/portlet-ext.xml`

    -   Description: This file allows overwriting the declaration of the
        core portlets included in Liferay. The most common usage is to
        change the init parameters or the roles specified.

    -   Original file in Liferay:
        `portal-web/docroot/WEB-INF/portlet-custom.xml`

-   `ext-web/docroot/WEB-INF/liferay-portlet-ext.xml`

    -   Description: This file allows overwriting the Liferay-specific
        declaration of the core portlets included in Liferay. Refer to
        the `liferay-portlet-app_6_1_0.dtd` file for details on all the
        available options. Use this file with care since the code of the
        portlets may be assuming some of these options to be set to
        certain values.

    -   Original file in Liferay:
        `portal-web/docroot/WEB-INF/liferay-portlet.xml`

-   `ext-web/docroot/WEB-INF/liferay-display.xml`

    -   Description: This file allows overwriting the portlets that will
        be shown in the "Add application" pop-up and the categories in
        which they'll be organized. The most common usage is to change
        the categorization, hide some portlets or make some Control
        Panel portlets available to be added to a page.

    -   Original file in Liferay:
        `portal-web/docroot/WEB-INF/liferay-display.xml`

-   `ext-web/docroot/WEB-INF/liferay-layout-templates-ext.xml`

    -   Description: This file allows specifying custom template files
        for each of the layout templates provided by default with
        Liferay. You should not need to do this except for very advanced
        needs.

    -   Original file in Liferay:
        `portal-web/docroot/WEB-INF/liferay-layout-templates.xml`

-   `ext-web/docroot/WEB-INF/liferay-look-and-feel-ext.xml`

    -   Description: This file allows changing the properties of the
        default themes provided by default with Liferay. You should not
        need to do this except for very advanced needs.

    -   Original file in Liferay:
        `portal-web/docroot/WEB-INF/liferay-look-and-feel.xml`

#### Changing the API of a core service

One advanced customization need that appears in some scenarios is to
change the API of a method provided by one of Liferay's services, for
example the `UserLocalService`.

Is it possible to do that? The short answer is no. The long answer is
that you can, but it will require modifying Liferay's source code
directly and making manual changes to quite a few files. The good news is
that there is a better alternative to achieve the end goal.

The best way to extend an existing service is to create a complementary
custom service, for example a `MyUserLocalService` that includes all the
new methods. Your custom code can invoke this service instead of the
default service and the implementation of your service can invoke the
original service as needed.

Note that this technique does not require an Ext plugin since it can be
done from portlet plugins. In fact, using Service Builder for an Ext plugin
is deprecated but is supported for migration from the old extension
environment.

In some advanced circumstances it's desired to change the implementation
of the original service to call your custom one, which can only be done
from an Ext plugin. To achieve this, override the Spring definition for
`UserLocalServiceUtil` in `ext-spring.xml` and point it to your
`MyUserLocalServiceImpl` (instead of `UserLocalServiceImpl`). This way
both `MyUserLocalServiceUtil` and `UserLocalServiceUtil` will use the same
Spring bean: your new implementation.

#### Replacing core classes in portal-impl

If you really need to change a core portal-impl class and this class
cannot be replaced in any configuration file, then the best way to avoid
conflicts and easily merge with a new portal version is to:

1.  Rename original class (e.g. `DeployUtil` -\>  `MyDeployUtil`)

2.  Create new subclass with old the name (e.g `DeployUtil extends MyDeployUtil`)

3.  Override methods you need to change

4.  Delegate static methods

5.  Use a logger with appropriate class name for both classes (e.g., `DeployUtil`)

This strategy will help you determine what you will need to be merged in the
future when a new version of Liferay is released.

![image](../../images/tip-pen-paper.png)Tip: This is a very advanced
technique that may have a high impact on the maintainability of your
code, especially if abused. Try to look for alternatives and if you
confirm that this is your only alternative try to apply only for the
short term and get in touch with Liferay's developers to apply the
necessary changes to the product source code.

#### Licencing and Contributing

Liferay Portal is Open Source software licensed under the LGPL 2.1
license
([http://www.gnu.org/licenses/lgpl-2.1.html](http://www.gnu.org/licenses/lgpl-2.1.html)).
If you reuse any code snippet and redistribute it either publicly or to
an specific customer, you need to make sure that those modifications are
compliant with this license. A common way to do this is to make the
source code of your modifications available to the community under the
same license, but make sure to read the license text yourself to find
the best option that fits your needs.

If the goal of the changes is to fix a bug or to make an improvement
that could be of interest to a broader audience, consider contributing
it back to the project. That would benefit all other users of the
product and also would be good for you since you won't have to maintain
the changes when new versions of Liferay come out. You can notify
Liferay of bugs or improvements in [issues.liferay.com](issues.liferay.com).
There is also a wiki page with instructions on how to contribute to Liferay:

[http://www.liferay.com/community/wiki/-/wiki/Main/Contributing](http://www.liferay.com/community/wiki/-/wiki/Main/Contributing)

## Deploying in production

In production or pre-production environments it's often not possible to
use Ant to deploy web applications. Also, some application servers such
as WebSphere or Weblogic have their own deployment tools and it isn't
possible to use Liferay's autodeploy process. This section describes two
methods for deploying and redeploying Ext plugins in production that can
be used in each of these scenarios.

### Method 1: Redeploying Liferay's web application

This method can be used in any application server that supports auto
deploy, such as Tomcat or JBoss. Its main benefit is that the only
artifact that needs to be transferred to the production system is the
`.war` file which the Ext plugin produced using the
`ant war` target, which is usually a small file. Here are the
steps that need to be executed on the server:

1.  Redeploy Liferay. To do this, follow the same steps you used when
    first deploying Liferay on the app server. If you are using a
    bundle, you can just unzip the bundle again. If you've installed
    Liferay manually on an existing application server, you'll need to
    redeploy the `.war` file and copy the global libraries to
    the appropriate directory within the application server. If this is
    the first time the Ext plugin is deployed, you can skip this step.

2.  Copy the Ext plugin `.war` into the auto deploy directory.
    For a bundled Liferay distribution, the *deploy* folder is in the
    *root* folder.

3.  Once the Ext plugin is detected and deployed by Liferay, restart the
    Liferay server.

### Method 2: Generate an aggregated WAR file

This method can be used for application servers that do not support
autodeploy, such as WebSphere or Weblogic. Its main benefit is that all
Ext plugins are merged before deployment to production, so a single
`.war` file will contain Liferay plus the changes from one or
more Ext plugins. Before deploying the `.war` file, you'll
need to copy the dependency `.jar` files for both Liferay and the
Ext plugin to the global application server class loader in the
production server. This location varies from server to server; please
see *Using Liferay Portal* for further details for your application server.

To create the aggregated `.war` file, deploy the Ext plugin
first to the Liferay bundle you are using in your
development environment. For the remaining steps, we'll assume we're using a
Liferay Tomcat bundle. Once it's deployed, restart the
server so that the plugin is fully deployed and shut it down again. Now
the aggregated file is ready. Create a `.war` file by zipping
the `webapps/ROOT` folder of Tomcat. Also, copy all the
libraries from the `lib/ext` directory of Tomcat that are
associated to all the Ext plugins to your application server's global
classpath, as noted above. These steps will be automated with Ant
targets in the next version of Liferay, but for now, they need to be
done manually.

Once you have the aggregated `.war` file follow these steps on
the server:

1.  Redeploy Liferay using the aggregated WAR file.

2.  Stop the server and copy the new version of the global libraries to
    the appropriate directory in the application server.

## Migrating old extension environments

Ext plugins have been created as an evolution of the extension
environment provided in Liferay 5.2 and previous versions of Liferay.
Because of this a common need for projects upgrading from previous
versions might be to migrate Extension environments into Ext plugins.
The good news is that this task is automated and thus relatively easy.

![image](../../images/tip-pen-paper.png)Tip: When migrating an
extension environment, it's worth considering if all or at least some of
its features can be moved into other types of plugins such as portlets
and hooks. The benefit of using portlets and hooks is that since they
are focused on specific goals they are easier to learn. Additionally
they are cheaper to maintain since they often require fewer changes when
upgrading to a new version of Liferay.

The process of migrating consists of executing a target within the ext
directory from Plugins SDK, pointing to the old extension environment
and naming the new plugin:

    ant upgrade-ext -Dext.dir=/projects/liferay/ext -Dext.name=my-ext
    -Dext.display.name="My Ext"

Here is a description of the three parameters used:

-   `ext.dir` is the location of the old Extension Environment.

-   `ext.name` is the name of the Ext plugin that you want to
    create

-   `ext.display.name` is the display name

After executing the target you should see the logs of several copy
operations that will take files from the extension environment and copy
them into the equivalent directory within the Ext plugin (read the
section *Creating an Ext plugin* for an explanation of the main
directories within the plugin).

When the migration process is complete, some additional tasks will be
needed to upgrade the code to the new version of Liferay. Some of the
most typical tasks are:

-   Review the uses of Liferay's APIs and adapt them accordingly.

-   Review the changes to the JSPs and merge your changes into the JSPs
    of the new Liferay version.

-   When using Service Builder you will need to run
    `ant build-service` again. It's also
    recommended to consider moving this code to a portlet plugin,
    because it is now as powerful and allows for greater modularity and
    maintainability.

-   If you've implemented portlets in Ext, migrate them to portlet
    plugins, as this capability is deprecated and is not guaranteed to
    be available in future releases.

## Conclusions

Ext plugins are a very powerful way of extending Liferay. There are no
limits in what can be customized using them and for that reason they
have to be used carefully. If you find yourself using an Ext plugin,
verify if all or part of the desired functionality can be implemented
through portlets, hooks or web plugins instead.

If you really need to use an Ext plugin make it as small as possible and
make sure you follow the instructions in this guide carefully to avoid
issues.
