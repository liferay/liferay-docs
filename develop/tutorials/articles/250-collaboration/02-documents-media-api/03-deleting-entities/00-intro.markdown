# Deleting Entities [](id=deleting-entities)

Now that you know how to 
[create Documents and Media entities](/develop/tutorials/-/knowledge_base/7-1/creating-entities), 
you can learn how to delete them. Note that the exact meaning of *delete* 
depends on the portal configuration and the delete operation you choose. This is 
because the 
[Recycle Bin](/discover/portal/-/knowledge_base/7-1/restoring-deleted-assets), 
which is enabled by default, can be used to recover deleted items. Deletions via
[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html), 
however, are permanent. To send items to the Recycle Bin, you must use the 
Capabilities API. 

This section of tutorials shows you how to use `DLAppService` to delete entities 
from the Documents and Media library. The last tutorial in this section shows 
you how to move entities to the Recycle Bin via the Capabilities API. 

