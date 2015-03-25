# Restoring Entries from the Recycle Bin

This tutorial covers how to restore entries from the Recycle Bin in your apps. 
To learn how to set up the framework for using the Recyle Bin in your apps, 
refer to the [Moving Entries to the Recycle Bin](https://dev.liferay.com/develop/tutorials/-/knowledge_base/moving-entries-to-the-recycle-bin-lp-6-2-develop-tutorial) 
tutorial. Now that you're able to move entries *to* the Recycle Bin, you can 
make sure your app can restore entries *from* the Recycle Bin. Besides, what's 
the point of having a Recycle Bin if you can't restore its entries?

![Figure 1: By implementing the *Restore* functionality, you'll be able to move entries out of the Recycle Bin and make them visible again in their original locations.](../../images/restore-entry-rb.png)

Entries are restored by returning their visibility in their original location
and removing them from the Recycle Bin. Entries are never removed from their 
original location; their visibility is simply turned off and a reference to the 
original entry is placed in the Recycle Bin. This reference is called a 
*trash entry*. Therefore, the restoration process is very similar to the moving 
process: you'll just need to return the entry's visibility in its original 
location and delete its trash entry.

Go through the following steps for implementing the restoration capability: 

1. Create a Service Method to Restore Entries from the Recycle Bin
2. Invoke the Service Method from the Trash Handler

Go ahead and begin this process by creating a service method for the restoration
process.

## Step 1: Create a Service Method to Restore Entries from the Recycle Bin

You'll need to create a service method that removes the trash entry from the
Recycle Bin and makes the asset entry visible again in its original location. 

As an example, the `restoreSongFromTrash()` service method from the Jukebox
portlet's [SongLocalServiceImpl](https://github.com/codyhoag/jukebox-portlet/blob/master/docroot/WEB-INF/src/org/liferay/jukebox/service/impl/SongLocalServiceImpl.java)
restores songs from the Recycle Bin:

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

First, the item's original name is restored with the help of
 [TrashUtil](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portlet/trash/util/TrashUtil.html)'s
`getOriginalTitle()` method. Then the entry's modified date is set to the
current date.

    song.setName(TrashUtil.getOriginalTitle(song.getName()));
    song.setModifiedDate(serviceContext.getModifiedDate(now));

Next, the entry's status is updated by setting it to the original status from
before the entry was trashed. For instance, if the entry was originally a draft
(`STATUS_DRAFT`), it's restored back to a draft. The status is updated by the
following call:

    song.setStatus(trashEntry.getStatus());

There are also several other statuses that are updated, which include the status 
by user ID, by user name, and the status date. These indicate the user that
restored the trash entry and set the date the status was modified.

    song.setStatusByUserId(user.getUserId());
    song.setStatusByUserName(user.getFullName());
    song.setStatusDate(serviceContext.getModifiedDate(now));

Then the asset entry is made visible in its original location by calling:

    assetEntryLocalService.updateVisible(Song.class.getName(), song.getSongId(), true);

Lastly, the trash entry is deleted from the Recycle Bin by calling:

    trashEntryLocalService.deleteEntry(Song.class.getName(), songId);

The entry is restored and no longer resides in the Recycle Bin.

Importantly, after writing your service method, make sure to run 
 [Service Builder](http://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/generate-services-liferay-portal-6-2-dev-guide-04-en)
to generate the corresponding service interface and utility methods. 

To finish implementing the entry restoration process, invoke the service method 
from the entity's trash handler.

## Step 2: Invoke the Service Method from the Trash Handler

Now that your service provides a method for restoring the entry, you must invoke
it from the trash handler's `restoreTrashEntry()` method. The Recycle Bin
framework calls this method when a user clicks the trash entry's *Restore*
button. 

The following `restoreTrashEntry()` method implementation is from the Jukebox
portlet's [SongTrashHandler](https://github.com/liferay-labs/jukebox-portlet/blob/master/docroot/WEB-INF/src/org/liferay/jukebox/trash/SongTrashHandler.java)
class. 

    @Override
    public void restoreTrashEntry(long userId, long classPK)
        throws PortalException, SystemException {

        SongLocalServiceUtil.restoreSongFromTrash(userId, classPK);
    }

When Jukebox users want to restore a song from the Recycle Bin, they simply
click the song's *Restore* button. 

---

 ![note](../../images/tip-pen-paper.png) **Note:** Sometimes, conflicts can 
 occur when restoring entries. For instance, suppose you create a file with the 
 same name of a file that you've trashed. Although the file is in the Recycle 
 Bin, it's still present in its original location, but with its status changed 
 and visibility turned off. The resolution framework avoids these two files
 conflicting. You can learn more about the resolution framework in the 
 [Resolving Recycling Conflicts](https://dev.liferay.com/develop/tutorials/-/knowledge_base/resolving-conflicts-lp-6-2-develop-tutorial)
 tutorial.

---

You now know how to provide the means for users to restore entries from the
Recycle Bin!
