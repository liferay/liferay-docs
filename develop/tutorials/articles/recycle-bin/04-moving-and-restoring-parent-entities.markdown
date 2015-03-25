# Moving and Restoring Parent Entities

What happens if you need to trash a parent entity, such as a parent wiki page, 
or trash an entity like a web content folder that aggregates web content 
articles? The fundamental logic for handling them is similar to handling single 
entities, but there are a few different aspects that must be considered. 

For an exampel throughout this tutorial take a look at how the Jukebox portlet 
is able to move/restore albums to/from the Recycle Bin so you can translate that 
knowledge to container entities in your personal app. 

## Step 1: Mark Container Model Service Entities

The first thing you'll need to do is define each container model as such in your
service. To do this, open your app's `service.xml` file and add the
`container-model="true"` attribute in the container entity's primary key
`<column>` tag.

For example, the Jukebox portlet's [service.xml](https://github.com/liferay-labs/jukebox-portlet/blob/master/docroot/WEB-INF/service.xml)
file marks the album entity as a container model in its `albumId` column: 

    <column name="albumId" type="long" primary="true" container-model="true" />

On running Service Builder for the portlet's service, with its newly designated
container model entities, Service Builder generates several methods that can be
used to obtain the child entities of each parent, or in the case of Jukebox,
songs of each album.

Service Builder generates `*Model.java`, `*ModelClp.java`, and `*ModelImpl.java`
classes to implement the [ContainerModel](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/model/ContainerModel.html)
interface. These implementations enable the Recycle Bin framework to identify
and use these models as container models. 

Next, you'll handle the child entities of the parent entity.

## Step 2: Manage Children Entities

Implementing the moving/restoring parent entities to/from the Recycle Bin is
very similar to the process for single entities. However, because the parents 
hold child entities, the child entities must be accounted for. The service 
method for moving a parent entry to the Recycle Bin must also move the
parent's child entries to the Recycle Bin. Likewise, the service method for
restoring a parent entry from the Recycle Bin must also restore the parent's
child entries. 

On moving a parent entity to the Recycle Bin, the following things must be done
with each of its dependent entities: 

1. Update its status
2. Add a trash version for it
3. Turn off the visibility of its asset
4. Reindex it

For example, look at how Jukebox portlet's [AlbumLocalServiceImpl](https://github.com/liferay-labs/jukebox-portlet/blob/master/docroot/WEB-INF/src/org/liferay/jukebox/service/impl/AlbumLocalServiceImpl.java)
class handles moving an album's songs to the Recycle Bin by calling a local
convenience method `moveDependentsToTrash()`. Here's the source code for the
`moveDependentsToTrash()` method: 

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

Consider how this method updates its status, adds a trash version for it, turns 
off the visibility of its asset, and reindexes it for search purposes.

Here's the breakdown of how the method implements this for the song entity: 

1. *Update its status*: Similar to updating the status for a single entry, the
   status of each album's song must reflect that the song has been moved to the
   Recycle Bin. The following code updates the song's status: 

        song.setStatus(WorkflowConstants.STATUS_IN_TRASH);

        songPersistence.update(song);

2. *Add a trash version for it*: When moving content with versions to the
   Recycle Bin, the trash version entity stores the status of those versions, so
   those statuses can be set back to their original values when the entity is
   restored. When a parent entity with content is sent to the Recycle Bin, each 
   element in that parent also generates a trash version. You can view trash
   versions by navigating inside a trash entry. Adding the trash version for the
   song is done with the following code: 

        trashVersionLocalService.addTrashVersion(trashEntryId,
             Song.class.getName(), song.getSongId(), status, null);

3. *Turn off the visibility of its asset*: The song's visibility is turned off
   in the following code:

        assetEntryLocalService.updateVisible(Song.class.getName(), song.getSongId(), false);

4. *Reindex it*: Since the visibility of the song entity has been modified, it
   the album's songs must be reindexed so they're searchable. Here's the code 
   that accomplishes this: 

        Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(Song.class);

        indexer.reindex(song);

You'll be comforted to know that restoring a parent's child entities from the
Recycle Bin involves similar steps. The `restoreDependentsFromTrash()`
convenience method in the Jukebox portlet's [AlbumLocalServiceImpl](https://github.com/liferay-labs/jukebox-portlet/blob/master/docroot/WEB-INF/src/org/liferay/jukebox/service/impl/AlbumLocalServiceImpl.java)
class for example demonstrates these steps. As a summary, here's what you do for 
each child entity: 

1. Update its status
2. Remove the trash version of it
3. Turn on the visibility of its asset
4. Reindex it

It's really very straightforward, making it easy for you to do in the service
classes for your app's parent entities. 

As a developer, you only need to tell the portal that the parent entity has 
children and how to obtain them. The Recycle Bin UI automatically accounts for 
the parent's children for you. 

Terrific! You've learned how to designate parent models as container model
entities in your service definition, you've provided a means to trash/restore a
parent's child entities when trashing/restoring that parent, and you've learned
how the powerful Recycle Bin UI lets you work with a parent's child entities.
