# Deleting Entities

Now that you know how to 
[create Documents and Media entities](liferay.com), you can learn how to delete 
them. Note that the exact meaning of *delete* depends on the portal 
configuration and how you delete entities. This is because the 
[Recycle Bin](/discover/portal/-/knowledge_base/7-1/restoring-deleted-assets), 
which is enabled by default, can be used to recover deleted items. Also, 
[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
permanently deletes entities. To instead send them to the Recycle Bin, you must 
use the Capabilities API. 
<!-- Pending answer from Adolfo on the Capabilities API requirement for Recycle Bin -->

This section of tutorials shows you how to use `DLAppService` to delete entities 
from the Documents and Media Library. The last tutorial in this section shows 
you how to move entities to the Recycle Bin via the Capabilities API. 

