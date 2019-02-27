# Moving Entities to the Recycle Bin [](id=moving-entities-to-the-recycle-bin)

Follow these steps to use the Capabilities API to move an entity to the Recycle 
Bin. For an explanation of why you should use the Capabilities API for this, see 
[Deleting Entities](liferay.com). 

1.  Verify that the repository supports the Recycle Bin. Do this by calling the 
    [repository object's](/develop/tutorials/-/knowledge_base/7-2/documents-and-media-api#specifying-repositories) 
    `isCapabilityProvided` method with `TrashCapability.class` as its argument. 
    This example does so in `if` statement's condition: 

        if (repository.isCapabilityProvided(TrashCapability.class)) {

            // The code to move the entity to the Recycle Bin
            // You'll write this in the next step
        }

2.  Move the entity to the Recycle Bin if the repository supports it. To do 
    this, first get a `TrashCapability` reference by calling the repository 
    object's `getCapability` method with `TrashCapability.class` as its 
    argument. Then call the `TrashCapability` method that moves the entity to 
    the Recycle Bin. For example, this code calls `moveFileEntryToTrash` to move 
    a file to the Recycle Bin: 

        if (repository.isCapabilityProvided(TrashCapability.class)) {

            TrashCapability trashCapability = repository.getCapability(TrashCapability.class);
            trashCapability.moveFileEntryToTrash(user.getUserId(), fileEntry);
        }

    See the `TrashCapability` 
    [Javadoc](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/repository/capabilities/TrashCapability.html) 
    for information on the methods you can use to move other types of entities 
    to the Recycle Bin. 

## Related Topics [](id=related-topics)

[Deleting Files](liferay.com)

[Deleting Folders](liferay.com)

[Deleting File Shortcuts](liferay.com)

[Moving Folders and Files](liferay.com)
