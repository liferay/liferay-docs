
# Plugin Security Management [](id=lp-6-1-dgen11-plugin-security-management-0)

We all wish cyberspace were free of malicious software and unwanted bugs. Since it isn't, we need to guard ourselves and our portals from these evils. Enter Liferay Portal's Plugin Security Manager! It's like a super-hero in a cape and tights, except, well, it's not. 

In its quest for peace within your portal, the Plugin Security Manager pledges to:

- Protect your portal and host system from unwanted side affects and malicious software introduced by plugins.
- Control plugin access to your portal, host system, and network by requiring that plugins specify ahead of time the portal resources they intend to access.

Let's go over some scenarios that could apply to you with regard to trying new plugins, and then maybe the importance of this will be clear.

- A flashy new plugin has arrived on Liferay Marketplace and you want to give it a whirl. But naturally, you want to know the parts of your system it will access.
- A colleague finds an interesting plugin after scouring the web for something that can help streamline processes at your workplace. Of course, you don't know whether you can truly trust the plugin creator--this plugin was found outside the Liferay Marketplace. If the plugin isn't open source, you have no way of knowing if it does anything nefarious. 
- Upper management requests your corporate branch and other branches use a standard set of plugins on your portal instances. This set of plugins, however, was written by an outside firm, and you need to know there will be no tampering with your proprietary files.

These are just a few scenarios that may ring true for you. When you're responsible for keeping your system running well 24x7, you can't be too cautious in protecting your portal, system and network.

## How plugin security works [](id=lp-6-1-dgen11-how-plugin-security-works-0)

When enabled, Liferay Portal's Plugin Security Manager checks a *Portal Access Control List* that's deployed with the plugin. This list describes what APIs the plugin accesses, so that you can review what it does without having to look at the source code. If the plugin tries to access anything that's not on this list, the plugin's request is stopped dead in its tracks, throwing exceptions to let you know what went wrong. 

Access to APIs is authorized by means of the property values specified in the plugin's `liferay-plugin-package.properties` file. This file must be specified in your plugin's `WEB-INF` directory. These security manager properties are collectively known as the plugin's *Portal Access Control List (PACL)*.  

As you develop plugins for Liferay Marketplace or for distribution within your organization, you'll need to set the security manager properties appropriately. Before we dive into the intricacies of these properties, let's consider a plugin development approach that involves designing an app for the security manager from the ground up.

## Developing Plugins with security in mind [](id=lp-6-1-dgen11-developing-plugins-with-security-in-mind-0)

At the start of plugin developement, you may not have a clear picture of all the aspects of the portal you'll need to access, and that's fine. In fact, we suggest you go ahead and develop your plugin first and address plugin security afterwards. Liferay Portal's Plugin Security Manager uncovers the resources your plugin tries to access by throwing exceptions if a PACL property does not specifically grant access to those resources. So at the end of development, you may as well put the security manager to work and make it earn its keep.

Here is the suggested plugin development approach: 

-	Develop your plugin
-	Turn on the Plugin Security Manager
-	Declare the PACL property values required to satisfy the security manager

The Plugin Security Manager intercepts requests for protected Liferay APIs, letting you know instantly the portal items which you are unauthorized to access. In this way, you can fill in the security manager properties of your plugin's PACL, so that any protected API you're accessing can be declared to users of your plugin. 

### Develop your plugin [](id=lp-6-1-dgen11-develop-your-plugin-0)

First, create your plugin the way you normally would. Design your application, write code, unit test your code, have users beta test your code. In essence, do everything you normally would do. 

### Turn on the Plugin Security Manager [](id=lp-6-1-dgen11-turn-on-the-plugin-security-manager-0)

Next, turn on the security manager (see below), and re-test your application's functionality. If anything you're doing in the application needs to be declared in a PACL, this will be revealed to you. As you test, keep track of any issues the security manager reveals during testing. 

### Declare the PACL properties [](id=lp-6-1-dgen11-declare-the-pacl-properties-0)

Once you've identified what you need to declare, add the appropriate properties to your PACL in `liferay-plugin-package.properties`. Save this file and retest. Make sure everything works. If not, there are other properties you'll have to declare, so add those and test again. Repeat this process until you have an error-free deployment. 

Once this is done, you're ready to distribute your application. 

## Enabling the Security Manager [](id=lp-6-1-dgen11-enabling-the-security-manager-0)

If you want to distribute a plugin either through the Liferay Marketplace or through your web site, you have to assume any potential users have Security Manager turned on. For this reason, you should have it turned on when doing final testing on your applications. 

It's very easy to activate the security manager. Set the following property to true: 

	security-manager-enabled=true

As a suggested plugin development approach, disable the security manager (by setting this property to `false`) until after you are done developing your plugin. By default, the security manager is turned off. 

Next, we'll look at exactly what APIs the security manager protects, and how you can declare whether your application uses any of these properties. 


## Portal Access Control List (PACL) Properties [](id=lp-6-1-dgen11-portal-access-control-list-pacl-properties-0)

Liferay Portal's Plugin Security Manager checks all your plugin's API access attempts against the security manager properties specified in your plugin's `liferay-plugin-package.properties` file. If your plugin tries to access a portal resource that is not specified in these properties, the Plugin Security Manager prevents it from happening. Consider this a virtual finger waggin'. To prevent this from happening, you have to tell the Plugin Security Manager up-front the access your plugin needs.

The sections that follow describe the PACL properties: explaining each property's purpose, its possible values, and the syntax to use in specifying its value. 

## Class Loader Security [](id=lp-6-1-dgen11-class-loader-security-0)

Specify the reference IDs of plugins for this plugin to access.

*Example:*

	security-manager-class-loader-reference-ids=\
		1_WAR_flashportlet,\
		flash-portlet

## Expando Bridge [](id=lp-6-1-dgen11-expando-bridge-0)

Specify models having Expando Bridge attributes the plugin is permitted to access. The plugin can also access Expando Bridge attributes via the wrapper classes of the models.

*Example:*

	security-manager-expando-bridge=\
    	com.liferay.portal.model.User

## File Security [](id=lp-6-1-dgen11-file-security-0)

The following properties address file deletion, execution, reading, writing and replacement operations. The `*` character in a path name indicates all files in the current directory. The `-` character in a path name indicates all files in the current directory and in its subdirectories.

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

## Hook Security [](id=lp-6-1-dgen11-hook-security-0)

Set to `true` if the hook plugin is permitted use custom JSPs. This property's default value is `false`.

*Example:*

	security-manager-hook-custom-jsp-dir-enabled=false

Specify entities for which the hook plugin is permitted to customize indexing. Customizing the indexing can involve modifying the search, summary and/or queries for these entities.

*Example:*

	security-manager-hook-indexers=\
		com.liferay.portal.model.User

Specify which language property locales the plugin is permitted to override.

*Example:*

This example grants the plugin permission to override the Great Britain locale of English, the Spain locale of Spanish, and *all* Portuguese locales.

	security-manager-hook-language-properties-locales=\
		en_GB,\
		es_ES,\
		pt

Specify which portal properties your plugin is permitted to override.

*Example:*

	security-manager-hook-portal-properties-keys=\
		phone.number.format.impl

Specify which services your plugin is permitted access.

*Example:*

	security-manager-hook-services=\
		com.liferay.portlet.blogs.service.BlogsEntryLocalService

Specify whether to allow your plugin's [servlet filter hooks](http://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/other-hooks). Setting this to `true` gives your plugin permission to pre-process requests going to the portal's servlets and post-process requests coming from the portal's servlets. This property's default value is `false`.

	security-manager-hook-servlet-filters-enabled=false

Specify struts action paths that the hook plugin is permitted to add or override.

*Example:*

	security-manager-hook-struts-action-paths=\
		/portal/test/pacl/success

## JNDI Security [](id=lp-6-1-dgen11-jndi-security-0)

Specify which services the plugin can look up. You can use regular expressions to make this dynamic. 

*Example:*

In this example, the plugin can look up objects for key names `matthew`, `mark`, `Mark`, `luke`, and `Luke`. In addition, the plugin can look-up objects for key names containing `john` with zero or more characters preceding and/or trailing `john`.

	security-manager-jndi-names=\
		test-pacl-matthew,\
		test-pacl-[mM]ark|test-pacl-[lL]uke,\
		test-pacl-.*john.*

## Message Bus Security [](id=lp-6-1-dgen11-message-bus-security-0)

Specify which services the plugin is permitted to listen on via the portal's message bus.

*Example:*

	security-manager-message-bus-listen=\
		liferay/test_pacl,\
		liferay/test_pacl_listen_success

Specify which message bus destinations the plugin is permitted to call (send messages to) via the portal's message bus.

*Example:*

	security-manager-message-bus-send=\
		liferay/message_bus/default_response,\
		liferay/test_pacl_listen_failure,\
		liferay/test_pacl_listen_success,\
		liferay/test_pacl_send_success

## Search Engine IDs [](id=lp-6-1-dgen11-search-engine-ids-0)

Specify the IDs of search engines the plugin is permitted to access.

*Example:*
	security-manager-search-engine-ids=\
		SYSTEM_ENGINE

## Portlet Service Security [](id=lp-6-1-dgen11-portlet-service-security-0)

Specify portlet service classes and/or methods the plugin is permitted to access. Use `#` as a delimiter between a class and its method.

*Example:*

	security-manager-services[chat-portlet]=\
		com.liferay.chat.service.EntryLocalService,\
		com.liferay.chat.service.StatusLocalService#getStatuses

## Portal Service Security [](id=lp-6-1-dgen11-portal-service-security-0)

Specify portal service classes and/or methods the plugin is permitted to access. Use `#` as a delimiter between a class and its method.

*Example:*

	security-manager-services[portal]=\
		com.liferay.portal.service.GroupLocalService,\
		com.liferay.portal.service.PortalService#getBuildNumber,\
		com.liferay.portal.service.persistence.CompanyPersistence,\
		com.liferay.portlet.blogs.service.BlogsEntryLocalService,\
		com.liferay.portlet.blogs.service.BlogsStatsUserLocalService

## Bean Property Security [](id=lp-6-1-dgen11-bean-property-security-0)

Specify bean properties the plugin is permitted to get.

*Example:*
	
	security-manager-get-bean-property=\
		com.liferay.portal.kernel.cache.CacheRegistryUtil,\
		com.liferay.portal.kernel.cache.key.CacheKeyGeneratorUtil,\
		com.liferay.portal.kernel.configuration.ConfigurationFactoryUtil,\
		com.liferay.portal.kernel.dao.db.DBFactoryUtil,\
		com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil,\
		com.liferay.portal.kernel.dao.orm.FinderCacheUtil,\
		com.liferay.portal.kernel.dao.orm.PortalCustomSQLUtil,\
		com.liferay.portal.kernel.deploy.DeployManagerUtil,\
		com.liferay.portal.kernel.executor.PortalExecutorManagerUtil,\
		com.liferay.portal.kernel.format.PhoneNumberFormatUtil,\
		com.liferay.portal.kernel.language.LanguageUtil,\
		com.liferay.portal.kernel.messaging.MessageBusUtil,\
		com.liferay.portal.kernel.servlet.DirectRequestDispatcherFactoryUtil,\
		com.liferay.portal.kernel.servlet.DirectServletRegistryUtil,\
		com.liferay.portal.kernel.spring.util.SpringFactoryUtil,\
		com.liferay.portal.kernel.util.FileUtil,\
		com.liferay.portal.kernel.util.HtmlUtil,\
		com.liferay.portal.kernel.util.HttpUtil,\
		com.liferay.portal.kernel.util.InfrastructureUtil,\
		com.liferay.portal.kernel.util.PropsUtil,\
		com.liferay.portal.kernel.xml.SAXReaderUtil,\
		com.liferay.portal.util.PortalUtil

Specify bean properties the plugin is permitted to set.

*Example:*

	security-manager-set-bean-property=\
		com.liferay.portal.kernel.dao.orm.PortalCustomSQLUtil

## Socket Security [](id=lp-6-1-dgen11-socket-security-0)

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

This example specifies socket range `4316-4318`, socket `4320`, and socket `4321`.

	security-manager-sockets-listen=\
		4316-4318,\
		4320,\
		4321

## SQL Security [](id=lp-6-1-dgen11-sql-security-0)

Specify tables in the Liferay database on which the plugin is permitted to perform the applicable operations. These property names use the following convention: 

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

## Thread Security [](id=lp-6-1-dgen11-thread-security-0)

Specify names of thread pool executors the plugin is permitted to access.

*Example:*

	security-manager-thread-pool-executor-names=\
		liferay/test_pacl,\
		liferay/test_pacl_listen_failure,\
		liferay/test_pacl_listen_success

## Summary [](id=lp-6-1-dgen11-summary-0)

In this chapter, we've discussed the reasons for plugin security management, how the Plugin Security Manager checks each plugin against its portal access control list (PACL), and how to specify PACL properties for the plugins you create and deploy.

Now you have a better understanding of how plugin security works, and can use Liferay Portal's Plugin Security Manager effectively to specify exactly what services your plugin needs in order to function. Anyone running Liferay Portal with Security Manager turned on will know you're a "law abiding" citizen, because you've specified what services your applications need to access in order to function. 

Let's look at Liferay Marketplace next!
