# Moving Entities to the Recycle Bin [](id=moving-entities-to-the-recycle-bin)

Instead of deleting entities, you can move them to the 
[Recycle Bin](/discover/portal/-/knowledge_base/7-1/restoring-deleted-assets). 
Note the Recycle Bin isn't part of the Documents and Media API. Although you can 
use the Recycle Bin API directly, in the case of Documents and Media it's better 
to use the Capabilities API. This is because some third-party repositories 
(e.g., SharePoint) don't support Recycle Bin functionality. When working with 
such repositories, you can't use the Recycle Bin API. The Capabilities API can 
verify that the repository you're working in supports the Recycle Bin. It's 
therefore a best practice to always use the Capabilities API when moving 
entities to the Recycle Bin. 

Follow these steps to use the Capabilities API to move an entity to the Recycle 
Bin: 

1.  Check to ensure that the repository supports the Recycle Bin. You do this by 
    calling the 
    [repository object's](liferay.com) 
    `isCapabilityProvided` method with `TrashCapability.class` as its argument. 
    This example does so as an `if` statement's condition: 

        if (repository.isCapabilityProvided(TrashCapability.class)) {
            // The code to move the entity to the Recycle Bin
            // You'll write this in the next step
        }

2.  If the check in the first step returns `true`, move the entity to the 
    Recycle Bin. To do this, first get a `TrashCapability` reference by calling 
    the repository object's `getCapability` method with `TrashCapability.class` 
    as its argument. Then call the `TrashCapability` method that moves the 
    entity to the Recycle Bin. For example, this code calls 
    `moveFileEntryToTrash` to move a file to the Recycle Bin: 

        if (repository.isCapabilityProvided(TrashCapability.class)) {

            TrashCapability trashCapability = repository.getCapability(TrashCapability.class);
            trashCapability.moveFileEntryToTrash(user.getUserId(), fileEntry);
        }

    See the 
    [`TrashCapability` Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/repository/capabilities/TrashCapability.html) 
    for information on the other methods you can use to move entities to the 
    Recycle Bin. 

