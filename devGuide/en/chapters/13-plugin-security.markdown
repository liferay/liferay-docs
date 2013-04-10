
# Plugin Security Management 

We all wish cyberspace were free of malicious software and unwanted bugs. Since
it isn't, we need to guard ourselves and our portals from these evils. Enter
Liferay Portal's Plugin Security Manager! It's like a super-hero in a cape and
tights, except, well, it's not.

In its quest for peace within your portal, the Plugin Security Manager pledges
to:

- Protect your portal and host system from unwanted side affects and malicious
software introduced by plugins.
- Control plugin access to your portal, host system, and network by requiring
that plugins specify ahead of time the portal resources they intend to access.

Let's go over some scenarios that could apply to you with regard to trying new
plugins, and then maybe the importance of this will be clear.

- A flashy new plugin has arrived on Liferay Marketplace and you want to give it
a whirl. But naturally, you want to know the parts of your system it will
access.
- A colleague finds an interesting plugin after scouring the web for something
that can help streamline processes at your workplace. Of course, you don't know
whether you can truly trust the plugin creator--this plugin was found outside
the Liferay Marketplace. If the plugin isn't open source, you have no way of
knowing if it does anything nefarious.
- Upper management requests your corporate branch and other branches use a
standard set of plugins on your portal instances. This set of plugins, however,
was written by an outside firm, and you need to know there will be no tampering
with your proprietary files.

These are just a few scenarios that may ring true for you. When you're
responsible for keeping your system running well 24x7, you can't be too cautious
in protecting your portal, system and network.

## How plugin security works

When the Plugin Security Manager is enabled for your plugin, it checks your
plugin's *Portal Access Control List (PACL)*. This list describes what APIs the
plugin accesses, so people deploying the plugin can review what it does without
seeing its source code. If the plugin tries to access anything that's not on
this list, the plugin's request is stopped dead in its tracks with the security
manager logging information on the attempt to access unauthorized APIs or
resources. 

Access to APIs and resources is authorized by means of property values specified
in the plugin's `liferay-plugin-package.properties` file. This file must be
specified in your plugin's `WEB-INF` directory. These security manager
properties are collectively known as the plugin's PACL. 

As you develop plugins for Liferay Marketplace or for distribution within your
organization, you'll need to set the security manager properties appropriately.
Before we dive into the intricacies of these properties, let's consider a plugin
development approach that involves designing an app for the security manager
from the ground up. 

## Developing plugins with security in mind

At the start of plugin developement, you may not have a clear picture of all the
aspects of the portal you'll need to access, and that's fine. In fact, we
suggest you go ahead and develop your plugin first and address plugin security
afterwards. 

Here is the suggested plugin development approach: 

- Develop your plugin 
- Build your plugin's PACL 
- Enable the Plugin Security Manager 

Let's go over each part of this approach. 

### Develop your plugin

First, create your plugin the way you normally would. Design your application,
write code, unit test your code, have users beta test your code. In essence, do
everything you normally would do. Do all of this with the Plugin Security
Manager disabled via your plugin's `liferay-plugin-package.properties` file: 

	security-manager-enabled=false

Before the Plugin Security Manager is enabled, you must specify the resources
your plugin accesses. Let's build a list of these resources in your plugin's
PACL. 

### Build your plugin's PACL

Rather than tediously figuring out all of the resources your plugin accesses on
your own, let Liferay's PACL Policy Generation tool to give you a head start.
The generation tool detects resources your plugin accesses and writes
corresponding PACL properties to a policy file. You can then merge the PACL
properties from your policy file with those in your plugin's
`liferay-plugin-package.properties` file. 

Here's how you generate a PACL policy for your plugin: 

1. Specify your Liferay home in your `portal-ext.properties` file, if you
haven't done so already. For example: 

        liferay.home=C:/liferay-portal-[version]/bundles

    You must restart Liferay for the property to take affect.

2. Turn on the security manager's *generate* mode in your plugin by setting the
following property in your `liferay-plugin-package.properties` file: 

        security-manager-enabled=generate

3. Deploy your plugin. 

    The PACL Policy Generation tool writes a PACL policy file: 

        ${liferay.home}/pacl-policy/${servletContextName}.policy

    The security manager performs security checks on your plugin; but rather
    than throwing errors on failed checks, the generator tool writes suggested
    rules for resolving the failures. 

4. Lastly, merge the properties found in your newly generated PACL policy file
into your plugin's `liferay-plugin-package.properties` file. It's just a matter
of merging the properties that start with the "security-manager-" prefix. 

Now that your plugin has a thoroughly specified list of resources it accesses,
enable the security manager and do final testing of your PACL properties. We
cover enabling the security manager in the next section. 

## Enabling the Security Manager

If you want to distribute plugins either through the Liferay Marketplace or
through your web site, you have to assume any potential users have Security
Manager turned on. For this reason, you should have it turned on when doing
final testing on your plugins (i.e., applications). 

It's very easy to activate the security manager. Set the following
`liferay-plugin-package.properties` property to true: 

	security-manager-enabled=true

Then re-deploy your plugin and re-test it's functionality. If anything you're
doing in the application needs to be declared in a PACL, this will be revealed
to you in errors logged by the security manager. As you test, keep track of any
security related errors so that you authorize access to those resources by
specifying them in your PACL properties in your
`liferay-plugin-package.properties` file. Save changes to the file, re-deploy
the plugin, and re-test it. Make sure everything works. If not, there are other
properties you'll have to declare. Refer to the Portal Access Control List
(PACL) Properties section later in this chapter to see all of the PACL
properties and example values. 

Repeat the process of adjusting PACL properties and re-testing until you have an
error-free deployment. Then, you can distribute your plugin on Liferay
Marketplace. You can do so with confidence because you've specified all of the
resources your application uses up front in the application's PACL and your
application satisfies Liferay Portal's Security Manager. 

Next, we'll look at exactly what APIs the security manager protects, and how you
can declare whether your application uses any of these properties. 

## Portal Access Control List (PACL) Properties 

Liferay Portal's Plugin Security Manager checks all your plugin's API access
attempts against the security manager properties specified in your plugin's
`liferay-plugin-package.properties` file. If your plugin tries to access a
portal resource that is not specified in these properties, the Plugin Security
Manager prevents it from happening. Consider this a virtual finger waggin'. To
prevent this from happening, you have to tell the Plugin Security Manager
up-front the access your plugin needs.

The sections that follow describe the PACL properties: explaining each
property's purpose, its possible values, and the syntax to use in specifying its
value.

## AWT Security

Specify the AWT operations the plugin is permitted to access. 

*Example:*

    security-manager-awt-operations=\
        accessClipboard,\
        accessEventQueue,\
        accessSystemTray,\
        createRobot,\
        fullScreenExclusive,\
        listenToAllAWTEvents,\
        readDisplayPixels,\
        replaceKeyboardFocusManager,\
        setAppletStub,\
        setWindowAlwaysOnTop,\
        showWindowWithoutWarningBanner,\
        toolkitModality,\
        watchMousePointer

## Class Loader Security 

Specify the reference IDs of plugins for this plugin to access.

*Example:*

	security-manager-class-loader-reference-ids=\
		1_WAR_flashportlet,\
		flash-portlet

## Environment Variable Security

Specify regular expression patterns used to match environment variables
that the plugin is permitted to access. 

*Example:*

    security-manager-environment-variables=\
        java.home,\
        java.vendor,\
        java.version,\
        java.vm.v.*

## Expando Bridge

Specify models having Expando Bridge attributes the plugin is permitted to
access. The plugin can also access Expando Bridge attributes via the
wrapper classes of the models. 

*Example:*

	security-manager-expando-bridge=\
        com.liferay.portal.model.User

## File Security 

The following properties address file deletion, execution, reading, writing and
replacement operations. The `*` character in a path name indicates all files in
the current directory. The `-` character in a path name indicates all files in
the current directory and in its subdirectories.

Specify files the plugin is permitted to delete.

*Example:*

	security-manager-files-delete=\
		../webapps/chat-portlet/WEB-INF/src/com/liferay/chat/temp/-

Specify files the plugin is permitted to execute.

*Example:*

	security-manager-files-execute=\
		/bin/bash,\
		C:\\WINDOWS\\system32\\ping.exe

Specify files the plugin is permitted to read.

*Example:*

	security-manager-files-read=\
		../webapps/chat-portlet/images/*,\
		../webapps/chat-portlet/WEB-INF/liferay-releng.properties,\
		../webapps/chat-portlet/WEB-INF/src/-,\
		/bin/bash,\
		C:\\WINDOWS\\system32\\ping.exe

Specify files the plugin is permitted to overwrite.

*Example:*

	security-manager-files-write=\
		../webapps/chat-portlet/images/*,\
		../webapps/chat-portlet/WEB-INF/*,\
		../webapps/chat-portlet/WEB-INF/src/com/liferay/chat/util/ChatUtil.java

## Bean Security

Specify bean properties the plugin is permitted to acquire. 

*Example:*

    security-manager-get-bean-property=\
        com.liferay.portal.kernel.xml.SAXReaderUtil,\
        com.liferay.portal.util.PortalUtil

Specify bean properties the plugin is permitted to set. 

*Example:*

    security-manager-set-bean-property=\
        com.liferay.portal.kernel.dao.orm.PortalCustomSQLUtil

## Hook Security

Set to `true` if the hook plugin is permitted use custom JSPs. By default, the
hook plugin is not permitted to use custom JSPs. 

*Example:*

	security-manager-hook-custom-jsp-dir-enabled=false

Specify entities for which the hook plugin is permitted to customize indexing.
Customizing the indexing can involve modifying the search, summary and/or
queries for these entities. 

*Example:*

	security-manager-hook-indexers=\
		com.liferay.portal.model.User

Specify which language property locales the plugin is permitted to override. 

*Example:*

This example grants the plugin permission to override the Great Britain locale
of English, the Spain locale of Spanish, and *all* Portuguese locales. 

	security-manager-hook-language-properties-locales=\
		en_GB,\
		es_ES,\
		pt

Specify which portal properties the plugin is permitted to override. 

*Example:*

	security-manager-hook-portal-properties-keys=\
		phone.number.format.impl

Specify which services the plugin is permitted to access. 

*Example:*

	security-manager-hook-services=\
		com.liferay.portlet.blogs.service.BlogsEntryLocalService

Specify whether to allow the plugin's servlet filter hooks. Setting this to
`true` gives the plugin permission to pre-process requests going to the portal's
servlets and post-process requests coming from the portal's servlets. By
default, the plugin's servlet filter hooks are not allowed. 

	security-manager-hook-servlet-filters-enabled=false

Specify struts action paths that the hook plugin is permitted to add or
override. 

*Example:*

	security-manager-hook-struts-action-paths=\
		/portal/test/pacl/success

## JNDI Security

Specify which services the plugin can look up. You can use regular expressions
to make this dynamic. 

*Example:*

Using the sample values below, the plugin can look up objects for key names
`matthew`, `mark`, `Mark`, `luke`, and `Luke`. In addition, the plugin can
look-up objects for key names containing `john` with zero or more characters
preceding and/or trailing `john`. 

	security-manager-jndi-names=\
		test-pacl-matthew,\
		test-pacl-[mM]ark|test-pacl-[lL]uke,\
		test-pacl-.*john.*

## Message Bus Security 

Specify which services the plugin is permitted to listen on via the portal's
message bus.

*Example:*

	security-manager-message-bus-listen=\
		liferay/test_pacl,\
		liferay/test_pacl_listen_success

Specify which message bus destinations the plugin is permitted to call (send
messages to) via the portal's message bus.

*Example:*

	security-manager-message-bus-send=\
		liferay/message_bus/default_response,\
		liferay/test_pacl_listen_failure,\
		liferay/test_pacl_listen_success,\
		liferay/test_pacl_send_success

## Portlet Bag Pool Security

Specify regular expression patterns used to match any portlet IDs that the
plugin is permitted to access from the portlet bag pool. 

*Example:*

    security-manager-portlet-bag-pool-portlet-ids=\
        1_WAR_flashportlet,\
        .*_WAR_chatportlet

## Search Engine Security

Specify the IDs of search engines the plugin is permitted to access. 

*Example:*

	security-manager-search-engine-ids=\
		SYSTEM_ENGINE

## Portal Service Security

Specify portal service classes and/or methods the plugin is permitted to access.
Use the `#` character as a delimiter between a class and its method. 

*Example:*

	security-manager-services[portal]=\
		com.liferay.portal.service.GroupLocalService,\
		com.liferay.portal.service.PortalService#getBuildNumber,\
		com.liferay.portal.service.persistence.CompanyPersistence,\
		com.liferay.portlet.blogs.service.BlogsEntryLocalService,\
		com.liferay.portlet.blogs.service.BlogsStatsUserLocalService

## Portlet Service Security

For each portlet the plugin accesses, replicate this property substituting
`some-portlet` in the `[` square brackets `]` with the name of the accessible
portlet. 

Specify portlet service classes and/or methods the plugin is permitted to
access. Use the `#` character as a delimiter between a class and its method. 

*Example:*

	security-manager-services[some-portlet]=\
		com.liferay.chat.service.EntryLocalService,\
		com.liferay.chat.service.StatusLocalService#getStatuses

## Socket Security 

Specify sockets permitted to accept connections in the plugin.

*Example:*

	security-manager-sockets-accept=\
		localhost:4320

Specify connections the plugin is permitted to make with the outside world.

*Example:*

	security-manager-sockets-connect=\
		www.cbs.com:80,\
		www.google.com:80,\
		www.google.com:443,\
		www.yahoo.com:443

Specify sockets the plugin can listen on.

*Example:*

This example specifies socket range `4316-4318`, socket `4320`, and socket
`4321`.

	security-manager-sockets-listen=\
		4316-4318,\
		4320,\
		4321

## SQL Security 

Specify tables in the Liferay database on which the plugin is permitted to
perform the applicable operations. These property names use the following
convention:

	security-manager-sql-tables-[operation]    
    
Specify tables on which the plugin can do any and all operations.

*Example:*

	security-manager-sql-tables-all=\
		TestPACL_Foo

Specify tables the plugin can create.

*Example:*

	security-manager-sql-tables-create=\
		TestPACL_CreateSuccess,\
		TestPACL_DropSuccess,\
		TestPACL_InsertSuccess,\
		TestPACL_ReplaceSuccess,\
		TestPACL_TruncateSuccess

Specify tables from which the plugin can delete records.

*Example:*

	security-manager-sql-tables-delete=\
		ClassName_

Specify tables the plugin can drop.

*Example:*

	security-manager-sql-tables-drop=\
		TestPACL_CreateSuccess,\
		TestPACL_DropSuccess,\
		TestPACL_InsertSuccess,\
		TestPACL_ReplaceSuccess,\
		TestPACL_TruncateSuccess

Specify tables into which the plugin can insert records.

*Example:*

	security-manager-sql-tables-insert=\
		TestPACL_InsertSuccess

Specify tables in which the plugin can replace records.

*Example:*

	security-manager-sql-tables-replace=\
		TestPACL_ReplaceSuccess

Specify tables the plugin can query.

*Example:*

	security-manager-sql-tables-select=\
		Counter,\
		TestPACL_Bar

Specify tables from which the plugin can delete all records.

*Example:*

	security-manager-sql-tables-truncate=\
		TestPACL_TruncateSuccess

Specify tables the plugin can update.

*Example:*

	security-manager-sql-tables-update=\
		ListType

## Thread Security 

Specify names of thread pool executors the plugin is permitted to access.

*Example:*

	security-manager-thread-pool-executor-names=\
		liferay/test_pacl,\
		liferay/test_pacl_listen_failure,\
		liferay/test_pacl_listen_success

## Summary 

In this chapter, we've discussed the reasons for plugin security management, how
the Plugin Security Manager checks each plugin against its portal access control
list (PACL), and how to specify PACL properties for the plugins you create and
deploy.

Now you have a better understanding of how plugin security works, and can use
Liferay Portal's Plugin Security Manager effectively to specify exactly what
services your plugin needs in order to function. Anyone running Liferay Portal
with Security Manager turned on will know you're a "law abiding" citizen,
because you've specified what services your applications need to access in order
to function.

Next, we'll show you what it takes to develop apps for publishing to *Liferay
Marketplace*. 

