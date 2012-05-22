
# Plugin Security Management

We all wish cyberspace was free of malicious software and unwanted bugs. Since it's not, we need to guard ourselves and our portals from these evils. Enter Liferay Portal's Plugin Security Manager! (I'm sure you are imagining a super-hero in a cape and tights just about now.)

In its quest for peace within your portal, the Plugin Security Manager pledges to

- Protect your portal and host system from unwanted side affects and malicious software introduced by a plugins.
- Control plugin access to your portal, host system, and network by requiring plugins specify ahead-of-time the portal resources they intend to access.

It almost takes your breath away, doesn't it! Alright, alright ... so maybe you're not as star struck about these kinds of things. But, let's go over some scenarios that could apply to you with regards to trying new plugins.

- A flashy new plugin has arrived on Liferay Marketplace and you want to give it a whirl. But naturally, you want to know parts of your system it will access.
- A colleague finds an interesting plugin after scouring the web to find a plugin that could help streamline processes at your workplace. But, of course you don't know whether you can truly trust the plugin creator. We certainly don't recommend you try foreign plugins found outside of Liferay Marketplace; but we can't prevent you from doing so. Regardless, it behooves you to make sure the plugin doesn't launch a "time bomb" or unintentionally bring you portal "to its knees" by hogging up resources.
- Upper management requests your corporate branch and other branches use a standard set of plugins on your portal instances. However, competitor branch XZY wrote the plugin and you want to make sure your proprietary files are not tampered with by the plugin.

These are just a few scenarios that may ring true for you. No matter your plugin deployment circumstances, you can't be too cautious in protecting your portal, system and network.

## How it works

When enabled, Liferay Portal's Plugin Security Manager will throw exceptions on any unauthorized access attempts by plugins. The plugin's request is stopped dead in its tracks. Access is authorized by means of the property values specified in the plugin's `liferay-plugin-package.properties` file. This file must be specified in your plugin's `WEB-INF` directory. These *security manager* properties are collectively known as the plugin's *Portal Access Control List (PACL)*.  Resources that the plugin creator intends the plugin to access must be specified in the plugin's PACL prior to deployment. Makes sense, right?

As you develop plugins for Liferay Marketplace or for distribution within your organization, you'll need to set your security manager properties appropriately. Before we dive into the intricacies of these properties, let's consider a plugin development approach that involves specifying security manager properties.

## Plugin Development Approach

At the start of plugin developement, you may not have a clear picture of all the aspects of the portal you'll need to access -- that's fine. In fact, we suggest you go ahead and develop your plugin first and address plugin security afterwards. Liferay Portal's Plugin Security Manager uncovers the resources your plugin tries to access by throwing exceptions if a PCAL property does not specifically grant access to those resources. So you may as well put the security manager to work and make it "earn its keep."

Here is the suggested plugin development approach

-	Develop your plugin
-	Turn on the Plugin Security Manager
-	Declare the PACL property values required to satisfy the security manager

The Plugin Security Manager is one tough customer letting you know real quick the portal items for which you are unauthorized to access. No problem, you'll fill in the security manager properties of your plugin's PACL to meet your plugin's needs.

## Portal Access Control List (PACL) Properties

Liferay Portal's Plugin Security Manager checks all of your plugin's portal access attempts against the security manager properties specified in your plugin's `liferay-plugin-package.properties`. If your plugin is trying to access a portal resource that is not specified in these properties, the Plugin Security Manager is gonna give you a finger waggin'. So, you're gonna have to tell the Plugin Security Manager up-front the access your plugin needs.

The sections that follow, describe the PACL properties explaining each property's purpose, its possible values, and the syntax to use in specifying its value.

## Enabling the Security Manager

Before you distribute your plugin, turn on the security manager by setting the value of this property to `true`, to expose the portal resources the plugin uses. As a suggested plugin development approach, disable the security manager setting this property to `false` until after you are done developing your plugin. This property's default value is `false`.

	security-manager-enabled=true

## Class Loader Security

Specify the reference IDs of plugins for this plugin to access.

*Example:*

	security-manager-class-loader-reference-ids=\
		1_WAR_flashportlet,\
		flash-portlet

## File Security

The following properties address file deletion, execution, reading, writing and replacement operations. The `*` character in a pathname indicates all files in the current directory. The `-` character in a pathname indicates all files in the current directory and in its subdirectories.

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

## Hook Security

Set to `true` if the hook plugin is permitted use custom JSPs. This property's default value is `false`.

*Example:*

	security-manager-hook-custom-jsp-dir-enabled=false

Specify entities for which the hook plugin is permitted to customize indexing. Customizing the indexing can involve modifying the search, summary and/or queries for these entities.

*Example:*

	security-manager-hook-indexers=\
		com.liferay.portal.model.User

Specify which language property locales the plugin is permitted to overwrite.

*Example:*

This example grants the plugin permission to overwrite the Great Britain locale of English, the Spain locale of Spanish, and *all* Portuguese locales.

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

## JNDI Security

Specify which services the plugin can look up.

*Example:*

In this example, the plugin can look-up objects for key names `matthew`, `mark`, `Mark`, `luke`, and `Luke`. In addition, the plugin can look-up objects for key names containing `john` with zero or more characters preceding and/or trailing `john`.

	security-manager-jndi-names=\
		test-pacl:matthew,\
		test-pacl:[mM]ark|test-pacl:[lL]uke,\
		test-pacl:.*john.*

## Message Bus Security

Specify which services the plugin is permitted to listen on via the portal's message bus.

*Example:*

	security-manager-message-bus-listen=\
		liferay/test_pacl,\
		liferay/test_pacl_listen_success

Specify which services the plugin is permitted to call (send messages to) via the portal's message bus.

*Example:*

	security-manager-message-bus-send=\
		liferay/message_bus/default_response,\
		liferay/test_pacl_listen_failure,\
		liferay/test_pacl_listen_success,\
		liferay/test_pacl_send_success

## Portlet Service Security

Specify portlet service classes and/or methods the plugin is permitted to access. Use `#` as a delimiter between a class and its method.

*Example:*

	security-manager-services[chat-portlet]=\
		com.liferay.chat.service.EntryLocalService,\
		com.liferay.chat.service.StatusLocalService#getStatuses

## Portal Service Security

Specify portal service classes and/or methods the plugin is permitted to access. Use `#` as a delimiter between a class and its method.

*Example:*

	security-manager-services[portal]=\
		com.liferay.portal.service.GroupLocalService,\
		com.liferay.portal.service.PortalService#getBuildNumber,\
		com.liferay.portal.service.persistence.CompanyPersistence,\
		com.liferay.portlet.blogs.service.BlogsEntryLocalService,\
		com.liferay.portlet.blogs.service.BlogsStatsUserLocalService

## Bean Property Security

Specify bean properties the plugin is permitted to set.

*Example:*

	security-manager-set-bean-property=\
		com.liferay.portal.kernel.dao.orm.PortalCustomSQLUtil

## Socket Security

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

## SQL Security

Specify tables which the plugin is permitted to perform the applicable operations. These property names use the following convention

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

## Conclusion

In this chapter, we've discussed the reason for plugin security management, how the Plugin Security Manager checks each plugin against its portal access control list (PACL), and how to specify PACL properties for the plugins you create and deploy.

Now you have a better understanding and can face Liferay Portal's Plugin Security Manager with reverence and thankfulness. And you can be a "law abiding" citizen by specifying the portal access required by each plugin you create.

Let's take a look at Liferay Marketplace next!
