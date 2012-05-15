name=Test Portal Access Control List
module-group-id=liferay
module-incremental-version=1
tags=test
short-description=
change-log=
page-url=http://www.liferay.com
author=Liferay, Inc.
licenses=LGPL

// Specify plugins that must be deployed in order for the plugin to function correctly
required-deployment-contexts=\
    chat-portlet,\
    flash-portlet

// Set to false to disable the security manager
security-manager-enabled=true

// Specify reference ids of plugins that the plugin is permitted to access
security-manager-class-loader-reference-ids=\
    1_WAR_flashportlet,\
    flash-portlet

// Specify files the plugin is permitted to delete
security-manager-files-delete=\
    ../webapps/chat-portlet/WEB-INF/src/com/liferay/chat/util/ChatUtil.java

// Specify files the plugin is permitted to execute    
security-manager-files-execute=\
    /bin/bash,\
    C:\\WINDOWS\\system32\\ping.exe

// Specify files the plugin is permitted to read    
security-manager-files-read=\
    ../webapps/chat-portlet/images/*,\
    ../webapps/chat-portlet/WEB-INF/liferay-releng.properties,\
    ../webapps/chat-portlet/WEB-INF/src/-,\
    /bin/bash,\
    C:\\WINDOWS\\system32\\ping.exe

// Specify files the plugin in permitted to overwrite
security-manager-files-write=\
    ../webapps/chat-portlet/images/*,\
    ../webapps/chat-portlet/WEB-INF/*,\
    ../webapps/chat-portlet/WEB-INF/src/com/liferay/chat/util/ChatUtil.java

// Set to true if the plugin is permitted use custom JSPs    
security-manager-hook-custom-jsp-dir-enabled=false

// Specify entities the plugin is permitted to customize indexing with a hook
// Modify the search, summary and/or queries on an entity such as User
// see http://www.liferay.com/documentation/liferay-portal/6.1/development/-/blogs/11643681/maximized
security-manager-hook-indexers=\
    com.liferay.portal.model.User

// Specify which language property locales the plugin is permitted to overwrite
// For example, the entry below grants the plugin permission to overwrite the Great Britain locale of English, the Spain locale of Spanish, and all locales of Portuguese.
security-manager-hook-language-properties-locales=\
    en_GB,\
    es_ES,\
    pt

// Specify which portal properties your plugin is permitted to override    
security-manager-hook-portal-properties-keys=\
    phone.number.format.impl

// Specify which services your plugin is permitted to access    
security-manager-hook-services=\
    com.liferay.portlet.blogs.service.BlogsEntryLocalService

// setting to true opens up a lot to the plugin. Be careful
// See summary of hook in http://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/other-hooks    
security-manager-hook-servlet-filters-enabled=false

// e.g. for SAML
// See http://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/other-hooks
security-manager-hook-struts-action-paths=\
    /portal/test/pacl/success

// Specify which services the plugin can look up.
// Can lookup matthew,
// Can lookup mark, Mark OR luke, or Luke. (TODO what was the significance of the OR?)
// WRT .*john.*, I'm not sure what starting '.' means and whether the ending
// asterisk means any namespace/resource found directly within *john OR whether
// it includes nested namespace/resources found within *john. 
// test-pacl:.*john.* - matches 'john' with zero or more characters preceding and/or trailing it.  
security-manager-jndi-names=\
    test-pacl:matthew,\
    test-pacl:[mM]ark|test-pacl:[lL]uke,\
    test-pacl:.*john.*

// Specify which services the plugin is permitted to listen on and
// call (send messages) via the message bus
security-manager-message-bus-listen=\
    liferay/test_pacl,\
    liferay/test_pacl_listen_success

// Specify which services the plugin is permitted to listen on and
// call (send messages) via the message bus
security-manager-message-bus-send=\
    liferay/message_bus/default_response,\
    liferay/test_pacl_listen_failure,\
    liferay/test_pacl_listen_success,\
    liferay/test_pacl_send_success

// Service classes and/or class method the plugin is permitted to access
// e.g. permitted EntryLocalService class
// e.g. permitted to access StatusLocalService#getStatuses method    
security-manager-services[chat-portlet]=\
    com.liferay.chat.service.EntryLocalService,\
    com.liferay.chat.service.StatusLocalService#getStatuses

// Similary to above, but WRT to portal services the plugin is permitted to access    
security-manager-services[portal]=\
    com.liferay.portal.service.GroupLocalService,\
    com.liferay.portal.service.PortalService#getBuildNumber,\
    com.liferay.portal.service.persistence.CompanyPersistence,\
    com.liferay.portlet.blogs.service.BlogsEntryLocalService,\
    com.liferay.portlet.blogs.service.BlogsStatsUserLocalService

// Bean properties the plugin is permitted to set on the portal    
security-manager-set-bean-property=\
    com.liferay.portal.kernel.dao.orm.PortalCustomSQLUtil

// Connections the plugin is permitted to make with the outside world    
security-manager-sockets-connect=\
    www.cbs.com:80,\
    www.google.com:80,\
    www.google.com:443,\
    www.yahoo.com:443

// Sockets the plugin can listen on
// e.g. 4316-4318 is range of sockets including 4316 through and including 4318    
security-manager-sockets-listen=\
    4316-4318,\
    4320,\
    4321

// Tables which the plugin is permitted to perform the applicable operations
security-manager-sql-tables-[operation]    
    
// Plugin can do any and all operations to the specified tables    
security-manager-sql-tables-all=\
    TestPACL_Foo

// Can create the tables    
security-manager-sql-tables-create=\
    TestPACL_CreateSuccess,\
    TestPACL_DropSuccess,\
    TestPACL_InsertSuccess,\
    TestPACL_ReplaceSuccess,\
    TestPACL_TruncateSuccess

// Can delete records from tables    
security-manager-sql-tables-delete=\
    ClassName_

// Can drop tables    
security-manager-sql-tables-drop=\
    TestPACL_CreateSuccess,\
    TestPACL_DropSuccess,\
    TestPACL_InsertSuccess,\
    TestPACL_ReplaceSuccess,\
    TestPACL_TruncateSuccess

// Can insert records into tables    
security-manager-sql-tables-insert=\
    TestPACL_InsertSuccess

// Can perform record replacement in tables    
security-manager-sql-tables-replace=\
    TestPACL_ReplaceSuccess

// Can query tables    
security-manager-sql-tables-select=\
    Counter,\
    TestPACL_Bar

// Can delete all of the records from tables    
security-manager-sql-tables-truncate=\
    TestPACL_TruncateSuccess

// Can update records in tables    
security-manager-sql-tables-update=\
    ListType 
