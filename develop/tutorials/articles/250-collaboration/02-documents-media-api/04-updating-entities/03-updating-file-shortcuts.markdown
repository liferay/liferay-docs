# Updating File Shortcuts

The Documents and Media API also lets you update file shortcuts. You can update 
a shortcut to change which file it points to, or which folder it resides in. You 
can do this via the 
[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
method `updateFileShortcut`: 

    updateFileShortcut(long fileShortcutId, long folderId, long toFileEntryId, ServiceContext serviceContext)

All of this method's parameters are mandatory. To retain one or more of the 
shortcut's original values, you must provide them to this method. For a full 
description of the parameters, see the method's 
[Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#updateFileShortcut-long-long-long-com.liferay.portal.kernel.service.ServiceContext-). 

<!-- Add example -->
