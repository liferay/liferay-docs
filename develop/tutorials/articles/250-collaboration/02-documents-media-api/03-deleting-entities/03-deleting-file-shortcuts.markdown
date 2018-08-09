# Deleting File Shortcuts

The Documents and Media API also lets you delete file shortcuts. To do so, use 
the 
[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
method 
[`deleteFileShortcut`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#deleteFileShortcut-long-) 
with the ID of the shortcut you want to delete: 

    deleteFileShortcut(long fileShortcutId)

<!-- Add example -->
