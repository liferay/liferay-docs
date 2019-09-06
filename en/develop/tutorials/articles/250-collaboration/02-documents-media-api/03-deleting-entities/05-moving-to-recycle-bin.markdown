---
header-id: moving-entities-to-the-recycle-bin
---

# Moving Entities to the Recycle Bin

[TOC levels=1-4]

Instead of deleting entities, you can move them to the 
[Recycle Bin](/docs/7-1/user/-/knowledge_base/u/restoring-deleted-assets). 
Note that the Recycle Bin isn't part of the Documents and Media API. Although 
you can use the Recycle Bin API directly, in the case of Documents and Media 
it's better to use the Capabilities API. This is because some third-party 
repositories (e.g., SharePoint) don't support Recycle Bin functionality. The 
Capabilities API lets you verify that the repository you're working in supports 
the Recycle Bin. It's therefore a best practice to always use the Capabilities 
API when moving entities to the Recycle Bin. 

Follow these steps to use the Capabilities API to move an entity to the Recycle 
Bin: 

1.  Verify that the repository supports the Recycle Bin. Do this by calling the 
    [repository object's](/docs/7-1/tutorials/-/knowledge_base/t/getting-started-with-the-documents-and-media-api#specifying-repositories) 
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

    See the 
    [`TrashCapability` Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/repository/capabilities/TrashCapability.html) 
    for information on the methods you can use to move other types of entities 
    to the Recycle Bin. 

## Related Topics

[Deleting Files](/docs/7-1/tutorials/-/knowledge_base/t/deleting-files)

[Deleting Folders](/docs/7-1/tutorials/-/knowledge_base/t/deleting-folders)

[Deleting File Shortcuts](/docs/7-1/tutorials/-/knowledge_base/t/deleting-file-shortcuts)

[Moving Folders and Files](/docs/7-1/tutorials/-/knowledge_base/t/moving-folders-and-files)
