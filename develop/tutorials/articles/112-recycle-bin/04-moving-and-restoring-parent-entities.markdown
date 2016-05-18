# Moving and Restoring Parent Entities [](id=moving-and-restoring-parent-entities)

What happens if you need to trash a parent entity, such as a parent wiki page, 
or a web content folder full of articles? Don't sweat it. Moving parent entries
to and from the Recycle Bin is similar to moving non-parent entries. 

Albums, for example, in the [Jukebox portlet](https://github.com/liferay-labs/jukebox-portlet)
are parent entities--they aggregate songs. And Jukebox portlet users can
move/restore albums to/from the Recycle Bin. This tutorial uses code from the
Jukebox portlet to demonstrate moving/restoring parent entities. You can
translate the logic for your app's parent entities. 

## Step 1: Mark Container Model Service Entities [](id=step-1-mark-container-model-service-entities)

First, define each container/parent model as such in your app's services. To do
this, open your app's `service.xml` file and add the `container-model="true"`
attribute setting in the container entity's primary key `column` element.

For example, the Jukebox portlet's [`service.xml`](https://github.com/liferay-labs/jukebox-portlet/blob/6.2.x/docroot/WEB-INF/service.xml)
file marks the album entity as a container model in the following element: 

    <column name="albumId" type="long" primary="true" container-model="true" />

On running Service Builder, it generates methods you can use to obtain each
parent entity's children. In the case of Jukebox, the album entity has songs as
its child entities. Service Builder generates `-Model`, `-ModelClp`, and
`-ModelImpl` classes to implement the [`ContainerModel`](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/model/ContainerModel.html)
interface. These implementations enable the Recycle Bin framework to identify
and use these models as container models. 

Next, you'll handle the child entities.

## Step 2: Manage Children Entities [](id=step-2-manage-children-entities)

Because parent entities hold child entities, the child entities must be
recycled if the parent entity is. You'll implement service methods both to
recycle and to restore parent and child entities. 

On moving a parent entry to the Recycle Bin, you must perform these actions on
it and its children: 

1. Update its status
2. Add a trash version for it
3. Turn off the visibility of its asset
4. Re-index it

For example, look at how the method `moveDependentsToTrash` from [`AlbumLocalServiceImpl`](https://github.com/liferay-labs/jukebox-portlet/blob/6.2.x/docroot/WEB-INF/src/org/liferay/jukebox/service/impl/AlbumLocalServiceImpl.java)
class handles moving an album's songs to the Recycle Bin. Here's the method's
code: 

	protected void moveDependentsToTrash(List<Song> songs, long trashEntryId)
		throws PortalException, SystemException {

		for (Song song : songs) {

			// Entry

			if (song.isInTrash()) {
				continue;
			}

			int oldStatus = song.getStatus();

			song.setStatus(WorkflowConstants.STATUS_IN_TRASH);

			songPersistence.update(song);

			// Trash

			int status = oldStatus;

			if (oldStatus == WorkflowConstants.STATUS_PENDING) {
				status = WorkflowConstants.STATUS_DRAFT;
			}

			if (oldStatus != WorkflowConstants.STATUS_APPROVED) {
				trashVersionLocalService.addTrashVersion(
					trashEntryId, Song.class.getName(), song.getSongId(),
					status, null);
			}

			// Asset

			assetEntryLocalService.updateVisible(
				Song.class.getName(), song.getSongId(), false);

			// Indexer

			Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
				Song.class);

			indexer.reindex(song);
		}
	}

This method updates the entry's status, adds a trash version for it, turns 
off the visibility of its asset, and reindexes it for search purposes.

Here's how the method implements this for the song entity: 

1. *Update its status:* Similar to updating the status for a single entry, the
   status of each album's song must reflect that the song has been moved to the
   Recycle Bin. The following code updates the song's status: 

        song.setStatus(WorkflowConstants.STATUS_IN_TRASH);

        songPersistence.update(song);

2. *Add a trash version for it:* When moving content with versions to the
   Recycle Bin, the trash version entity stores the status of those versions, so
   those statuses can be set back to their original values when the entity is
   restored. When a parent entity with content is sent to the Recycle Bin, each 
   element in that parent also generates a trash version. You can view trash
   versions by navigating inside a trash entry. The following invocation adds
   the trash version for the song: 

        trashVersionLocalService.addTrashVersion(trashEntryId,
             Song.class.getName(), song.getSongId(), status, null);

3. *Turn off the visibility of its asset:* The song's visibility is turned off
   in the following code:

        assetEntryLocalService.updateVisible(Song.class.getName(), song.getSongId(), false);

4. *Re-index it:* Since the visibility of the song entity has been modified, 
   the album's songs must be re-indexed so they're searchable. Here's the code 
   that accomplishes this: 

        Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(Song.class);

        indexer.reindex(song);

You'll be comforted to know that restoring a parent's child entities from the
Recycle Bin involves similar steps. The `restoreDependentsFromTrash`
convenience method in the Jukebox portlet's [`AlbumLocalServiceImpl`](https://github.com/liferay-labs/jukebox-portlet/blob/6.2.x/docroot/WEB-INF/src/org/liferay/jukebox/service/impl/AlbumLocalServiceImpl.java)
class, for example, demonstrates these steps. As a summary, here's what you do
for each child entity: 

1. Update its status
2. Remove the trash version of it
3. Turn on the visibility of its asset
4. Re-index it

Restoring entities placed in the trash is straightforward when done in the
service classes for your app's parent entities. 

As a developer, you only need to tell the portal that the parent entity has 
children and how to obtain them. The Recycle Bin UI automatically accounts for 
the parent's children. 

Terrific! You've learned how to designate parent models as container model
entities in your service definition. You've provided a means to trash/restore a
parent's child entities when trashing/restoring that parent, and you've learned
how the Recycle Bin UI lets you work with a parent's child entities.

## Related Topics [](id=related-topics)

[Recycling Assets with the Recycle Bin](/discover/portal/-/knowledge_base/6-2/recycling-assets-with-the-recycle-bin)

[Service Builder and Services](/develop/tutorials/-/knowledge_base/6-2/service-builder)

[Enabling Search and Indexing](/develop/tutorials/-/knowledge_base/6-2/enabling-search-and-indexing)

[Asset Framework](/develop/tutorials/-/knowledge_base/6-2/asset-framework)

[Asset Enabling Custom Entities](/develop/tutorials/-/knowledge_base/6-2/asset-enabling-custom-entities)
