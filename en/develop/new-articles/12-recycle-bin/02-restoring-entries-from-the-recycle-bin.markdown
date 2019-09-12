# Restoring Entries from the Recycle Bin [](id=restoring-entries-from-the-recycle-bin)

This tutorial covers how to implement restoring entries from the Recycle Bin. If
you haven't yet implemented the framework for using the Recycle Bin in your
apps, refer to the tutorial 
[Moving Entries to the Recycle Bin](/develop/tutorials/-/knowledge_base/moving-entries-to-the-recycle-bin).
Once you can move entries *to* the Recycle Bin, you then want to be able to
restore entries *from* the Recycle Bin. What's the point of having a
Recycle Bin if you can't restore its entries?

![Figure 1: By implementing the *Restore* functionality, you can move entries out of the Recycle Bin and make them visible again in their original locations.](../../images/restore-entry-rb.png)

Recycle Bin entries are just pointers to the real entity. When a user wants to
recycle an entity, its visibility is turned off, and a reference to the entity
is placed in the Recycle Bin. This is called a *trash entry*. You can restore
rash entries by first making their entities visible again and then removing the
trash entry. The entry restoration process is similar to the entry moving
process, but in reverse. 

Use these steps to restore an entry: 

1. Create a Service Method to Restore Entries from the Recycle Bin
2. Invoke the Service Method from the Trash Handler

Your first step is to create a service method for restoring the entry.

## Step 1: Create a Service Method to Restore Entries from the Recycle Bin [](id=step-1-create-a-service-method-to-restore-entries-from-the-recycle-bin)

You'll create a service method that removes the trash entry from the Recycle Bin
and makes the asset entry visible again in its original location. 

As an example, the `restoreSongFromTrash` service method from the Jukebox
portlet's [`SongLocalServiceImpl`](https://github.com/liferay-labs/jukebox-portlet/blob/6.2.x/docroot/WEB-INF/src/org/liferay/jukebox/service/impl/SongLocalServiceImpl.java)
class restores songs from the Recycle Bin:

    @Indexable(type = IndexableType.REINDEX)
    @Override
    public Song restoreSongFromTrash(long userId, long songId)
        throws PortalException, SystemException {

        ServiceContext serviceContext = new ServiceContext();

        // Entry

        User user = userPersistence.findByPrimaryKey(userId);
        Date now = new Date();

        TrashEntry trashEntry = trashEntryLocalService.getEntry(
            Song.class.getName(), songId);

        Song song = songPersistence.findByPrimaryKey(songId);

        song.setName(TrashUtil.getOriginalTitle(song.getName()));
        song.setModifiedDate(serviceContext.getModifiedDate(now));
        song.setStatus(trashEntry.getStatus());
        song.setStatusByUserId(user.getUserId());
        song.setStatusByUserName(user.getFullName());
        song.setStatusDate(serviceContext.getModifiedDate(now));

        songPersistence.update(song);

        assetEntryLocalService.updateVisible(
            Song.class.getName(), song.getSongId(), true);

        trashEntryLocalService.deleteEntry(Song.class.getName(), songId);

        return song;
    }

First, the item's original name is restored with the help of [`TrashUtil`](https://docs.liferay.com/portal/6.2/javadocs/com/liferay/portlet/trash/util/TrashUtil.html)'s
method `getOriginalTitle`. The entry's modified date is then set to the current
date. 

    song.setName(TrashUtil.getOriginalTitle(song.getName()));
    song.setModifiedDate(serviceContext.getModifiedDate(now));

Next, the entry's status is updated by setting it to the original status from
before the entry was trashed. If the entry, for example, was originally a draft
(`STATUS_DRAFT`), it's restored back to draft status. The status is updated by
the following call:

    song.setStatus(trashEntry.getStatus());

Status by user ID and by user name are updated to indicate the user that
restored the trash entry. The status modification date is updated too.

    song.setStatusByUserId(user.getUserId());
    song.setStatusByUserName(user.getFullName());
    song.setStatusDate(serviceContext.getModifiedDate(now));

In the following call, the asset entry is made visible in its original location:

    assetEntryLocalService.updateVisible(Song.class.getName(), song.getSongId(), true);

Lastly, the trash entry is deleted from the Recycle Bin:

    trashEntryLocalService.deleteEntry(Song.class.getName(), songId);

At this point, the entry is restored and no longer resides in the Recycle Bin.

Importantly, after writing your service method, make sure to generate the
corresponding service interface and utility methods by running [Service Builder](/develop/tutorials/-/knowledge_base/6-2/running-service-builder-and-understanding-the-generated-code).

To finish implementing the entry restoration process, invoke the service method 
from the entity's trash handler.

## Step 2: Invoke the Service Method from the Trash Handler [](id=step-2-invoke-the-service-method-from-the-trash-handler)

Now that your service provides a method for restoring the entry, you must invoke
it from the trash handler's `restoreTrashEntry` method. The Recycle Bin
framework calls this method when a user clicks the trash entry's *Restore*
button. 

The following `restoreTrashEntry` method implementation is from the Jukebox
portlet's [`SongTrashHandler`](https://github.com/liferay-labs/jukebox-portlet/blob/6.2.x/docroot/WEB-INF/src/org/liferay/jukebox/trash/SongTrashHandler.java)
class. 

    @Override
    public void restoreTrashEntry(long userId, long classPK)
        throws PortalException, SystemException {

        SongLocalServiceUtil.restoreSongFromTrash(userId, classPK);
    }

To restore a song from the Recycle Bin, Jukebox users click the song's *Restore*
button. 

+$$$

**Note:** Sometimes, conflicts can occur when restoring entries. For instance,
suppose you create a file with the same name of a file that you've trashed.
Although the file is in the Recycle Bin, it's still present in its original
location, but with its status changed and visibility turned off. The resolution
framework avoids these two files conflicting. You can learn more about the
resolution framework in the tutorial [Resolving Recycling Conflicts](/develop/tutorials/-/knowledge_base/resolving-recycling-conflicts).

$$$

You now know how to provide the means for users to restore your app's entries
from the Recycle Bin!

## Related Topics [](id=related-topics)

[Recycling Assets with the Recycle Bin](/discover/portal/-/knowledge_base/6-2/recycling-assets-with-the-recycle-bin)

[Service Builder and Services](/develop/tutorials/-/knowledge_base/6-2/service-builder)

[Enabling Search and Indexing](/develop/tutorials/-/knowledge_base/6-2/enabling-search-and-indexing)

[Asset Framework](/develop/tutorials/-/knowledge_base/6-2/asset-framework)

[Asset Enabling Custom Entities](/develop/tutorials/-/knowledge_base/6-2/asset-enabling-custom-entities)
