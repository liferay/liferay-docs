---
header-id: deleting-entities
---

# Deleting Entities

[TOC levels=1-4]

Now that you know how to 
[create Documents and Media entities](/docs/7-1/tutorials/-/knowledge_base/t/creating-files-folders-and-shortcuts),
you should learn how to delete them. Note that the exact meaning of *delete*
depends on the portal configuration and the delete operation you choose. This is
because the 
[Recycle Bin](/docs/7-1/user/-/knowledge_base/u/restoring-deleted-assets), which is
enabled by default, can be used to recover deleted items. Deletions via
[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html),
however, are permanent. To send items to the Recycle Bin, you must use the
Capabilities API. 

This section of tutorials shows you how to use `DLAppService` to delete entities 
from the Documents and Media library. The last tutorial in this section shows 
you how to move entities to the Recycle Bin via the Capabilities API. 

