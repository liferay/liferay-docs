# Moving Entities to the Recycle Bin

Instead of deleting entities, you can move them to the 
[Recycle Bin](/discover/portal/-/knowledge_base/7-1/restoring-deleted-assets). 
You should note that the Recycle Bin isn't part of the Documents and Media API. 
And although you could use the Recycle Bin API directly, in the case of 
Documents and Media it's better to use the Capabilities API. 
<!-- 
Additional explanation of rationale behind the Capabilities API is pending 
answer from Adolfo. 
-->

Follow these steps to use the Capabilities API to move an entity to the Recycle 
Bin: 

1.  Use an `if` statement to ensure that the repository supports the Recycle 
    Bin. You do this by calling the repository object's `isCapabilitySupported` 
    method with `TrashCapability.class` as its argument: 

        if (repository.isCapabilitySupported(TrashCapability.class)) {

        }

2.  Inside the `if` statement, get a `TrashCapability` reference by calling the 
    repository object's `getCapability` method with `TrashCapability.class` as 
    its argument. Then call the `TrashCapability` method that moves the entity 
    to the Recycle Bin. For example, this code calls `moveFileEntryToTrash` to 
    move a file to the Recycle Bin: 

        if (repository.isCapabilitySupported(TrashCapability.class)) {

            TrashCapability trashCapability = repository.getCapability(TrashCapability.class);
            trashCapability.moveFileEntryToTrash(user.getUserId(), fileEntry);
        }

    See the 
    [`TrashCapability` Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/repository/capabilities/TrashCapability.html) 
    for information on the other methods you can use to move entities to the 
    Recycle Bin. 

