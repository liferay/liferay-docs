# Moving Entries to the Recycle Bin [](id=moving-entries-to-the-recycle-bin)

This tutorial covers how to add the ability to move your app's entries to the
Recycle Bin. 

![Figure 1: You can easily create a way to move your app's entries to the Recycle Bin.](../../images/move-entities-rb.png)

Here are the steps for implementing this Recycle Bin component:

1. Enable Trash for Service Entities
2. Implement a Trash Handler for each trash-enabled entity
3. Create a service method to move entries to the Recycle Bin
4. Create a portlet action to initiate moving entries to Recycle Bin
5. Implement a Trash Renderer for each trash-enabled entity

Start off by configuring the app's service. 

## Step 1: Enable Trash for Service Entities [](id=step-1-enable-trash-for-service-entities)

For every entity you want to recycle bin-enable, you must enable the trash
feature. To do this, insert insert the `trash-enabled="true"` attribute inside
your entities' `<entity>` tags in your `service.xml` file. Each trash-enabled
entity element should look similar to this one: 

    <entity name="YourEntity" local-service="true" remote-service="true" uuid="true" trash-enabled="true">

Run [Service Builder](/develop/tutorials/-/knowledge_base/6-2/running-service-builder-and-understanding-the-generated-code)
to generate back-end trash related classes for the entities. 

You'll implement trash handlers for these entities next.

## Step 2: Implement a Trash Handler for Each Trash-Enabled Entity [](id=step-2-implement-a-trash-handler-for-each-trash-enabled-entity)

As with many other Liferay frameworks--such as the workflow, assets, and
indexing frameworks--you must implement a handler class for Recycle Bin. A
Recycle Bin handler class manages moving entries to the Recycle Bin, viewing
them in the Recycle Bin, restoring them, and permanently deleting them. You must
implement the [`TrashHandler`](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/kernel/trash/TrashHandler.html)
interface for each trash-enabled entity. As a convenience, Liferay provides the
extensible abstract class [`BaseTrashHandler`](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/kernel/trash/BaseTrashHandler.html).

Consider the following [`TrashHandler`](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/kernel/trash/TrashHandler.html)
methods as a minimal set of methods a trash handler must implement or override: 

- `deleteTrashEntry`
- `getClassName`
- `getRestoreContainedModelLink`
- `getRestoreMessage`
- `hasTrashPermission`
- `isInTrash`
- `restoreTrashEntry`

For example, the Jukebox portlet's [`JukeBoxBaseTrashHandler`](https://github.com/liferay-labs/jukebox-portlet/blob/6.2.x/docroot/WEB-INF/src/org/liferay/jukebox/trash/JukeBoxBaseTrashHandler.java)
class implements these methods and serves as a base implementation for the app's
song and album entities. As an example trash handler implementation, you
can refer to the [`SongTrashHandler`](https://github.com/liferay-labs/jukebox-portlet/blob/6.2.x/docroot/WEB-INF/src/org/liferay/jukebox/trash/SongTrashHandler.java)
and its base class [`JukeBoxBaseTrashHandler`](https://github.com/liferay-labs/jukebox-portlet/blob/6.2.x/docroot/WEB-INF/src/org/liferay/jukebox/trash/JukeBoxBaseTrashHandler.java).

After you've implemented trash handlers for your trash-enabled entities, 
specify the handlers in your app's `liferay-portlet.xml` file. For example, the
Jukebox's Song portlet specifies the song trash handler like this:

    <trash-handler>org.liferay.jukebox.trash.SongTrashHandler</trash-handler>

You can refer to the Jukebox portlet's [`liferay-portlet.xml`](https://github.com/liferay-labs/jukebox-portlet/blob/6.2.x/docroot/WEB-INF/liferay-portlet.xml)
file to see the trash handlers it specifies. 

+$$$

**Note:** A trash handler refers to an entity, not a portlet. A trash handler
can, therefore, be declared in any of a plugin's portlets. A best practice is to
declare them in the app's principal portlet.

$$$

Great! You have trash handlers ready to manage your trash entries, but you still
need a way to get the entries to the Recycle Bin. You must create a service
method for moving them there.

## Step 3: Create a Service Method to Move Entries to the Recycle Bin [](id=step-3-create-a-service-method-to-move-entries-to-the-recycle-bin)

You'll implement a local service method for moving them to the
Recycle Bin. This service method must implement a trash service for the
entity. Your `-EntityNameLocalServiceImpl` class's trash method should look
similar to the method `moveSongToTrash` in Jukebox Portlet's [`SongLocalServiceImpl`](https://github.com/liferay-labs/jukebox-portlet/blob/6.2.x/docroot/WEB-INF/src/org/liferay/jukebox/service/impl/SongLocalServiceImpl.java)
class: 

	@Indexable(type = IndexableType.REINDEX)
	public Song moveSongToTrash(long userId, Song song)
		throws PortalException, SystemException {

		ServiceContext serviceContext = new ServiceContext();

		// Entry

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		int oldStatus = song.getStatus();

		song.setModifiedDate(serviceContext.getModifiedDate(now));
		song.setStatus(WorkflowConstants.STATUS_IN_TRASH);
		song.setStatusByUserId(user.getUserId());
		song.setStatusByUserName(user.getFullName());
        song.setStatusDate(serviceContext.getModifiedDate(now));

		// Asset

		assetEntryLocalService.updateVisible(
			Song.class.getName(), song.getSongId(), false);

		// Trash

		UnicodeProperties typeSettingsProperties = new UnicodeProperties();

		typeSettingsProperties.put("title", song.getName());

		TrashEntry trashEntry = trashEntryLocalService.addTrashEntry(
			userId, song.getGroupId(), Song.class.getName(), song.getSongId(),
			song.getUuid(), null, oldStatus, null, typeSettingsProperties);

		song.setName(TrashUtil.getTrashTitle(trashEntry.getEntryId()));

		songPersistence.update(song);

		return song;
	}

Notice that this method is annotated as `@Indexable`. That means that every time
an entry is moved to the Recycle Bin, Liferay re-indexes the entities and their
corresponding trash entries. This makes the trashed entries searchable only from 
the Recycle Bin, while regular entries aren't searchable outside of the Recycle Bin.

There's also a call to `song.setStatus(WorkflowConstants.STATUS_IN_TRASH)`,
which sets the song's status, so that the workflow engine knows the song is in
the trash. Note that a portlet need not leverage workflow to implement the
Recycle Bin's components. 

Next, the asset's visibility is updated so that it no longer appears outside the
Recycle Bin. Its visibility is deactivated by the following call:

    assetEntryLocalService.updateVisible(Song.class.getName(), song.getSongId(), false);

On first thought, this may seem a bit odd. Why do you have to make the entry 
invisible in its original location? I thought I was moving it to the Recycle Bin?
Importantly, entries that are moved to the Recycle Bin are actually left in
their original location; they're just not visible. 

+$$$

**Note:** If you're not using assets with your entity, you'll need to filter the
elements in your UI by status, so it only shows approved entities. Otherwise,
trashed entities still appear. The `updateVisible` method only works on asset
enabled entities.

$$$

Next, notice that the service method adds a new trash entry to the Recycle Bin:

    TrashEntry trashEntry = trashEntryLocalService.addTrashEntry(
            userId, song.getGroupId(), Song.class.getName(), song.getSongId(),
            song.getUuid(), null, oldStatus, null, typeSettingsProperties);

Lastly, the `moveSongToTrash` service method invokes `TrashUtil`'s [`getTrashTitle`](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portlet/trash/util/TrashUtil.html#getTrashTitle(long))
method to set the entry's *trash title*. The trash title is an alternative
reference to the entry. The trash title prevents duplicate entry name conflicts,
discussed in the tutorial [Resolving Recycling Conflicts](/develop/tutorials/-/knowledge_base/6-2/resolving-recycling-conflicts).

## Step 4: Create a Portlet Action to Initiate Moving Entries to Recycle Bin [](id=step-4-create-a-portlet-action-to-initiate-moving-entries-to-recycle-bin)

Great! You must now provide the means of invoking the service method from your
portlet. You implement this using a portlet action that you can trigger from
a JSP.

The [`JukeboxPortlet`](https://github.com/liferay-labs/jukebox-portlet/blob/6.2.x/docroot/WEB-INF/src/org/liferay/jukebox/portlet/JukeboxPortlet.java)
class, for example, extends [`MVCPortlet`](https://github.com/liferay/liferay-portal/blob/6.2.x/util-bridges/src/com/liferay/util/bridges/mvc/MVCPortlet.java)
and implements portlet action method `deleteSong` to invoke the
 [`SongLocalServiceImpl`](https://github.com/liferay-labs/jukebox-portlet/blob/6.2.x/docroot/WEB-INF/src/org/liferay/jukebox/service/impl/SongLocalServiceImpl.java)'s
`moveSongToTrash` method. Here's an abbreviated version of the `deleteSong`
method: 

    public void deleteSong(ActionRequest request, ActionResponse response)
        throws Exception {

        long songId = ParamUtil.getLong(request, "songId");

        boolean moveToTrash = ParamUtil.getBoolean(request, "moveToTrash");

        ServiceContext serviceContext = ServiceContextFactory.getInstance(
            Song.class.getName(), request);

        try {
            if (moveToTrash) {
                Song song = SongServiceUtil.moveSongToTrash(songId);
                ...
                SessionMessages.add(request, PortalUtil.getPortletId(request) +
                    SessionMessages.KEY_SUFFIX_DELETE_SUCCESS_DATA, data);
                ...
            }
            else {
                SongServiceUtil.deleteSong(songId, serviceContext);

                SessionMessages.add(request, "songDeleted");
            }
            ...
        }
        ...
    }

Note that the logic in the `try` block handles moving entries to the Recycle Bin
and permanently deleting entries. You can write similar portlet action methods
for deleting your app's entries. 

Now that you've written your portlet action class, you can use a JSP 
to invoke your portlet action. For example, the Jukebox portlet's [`view_song.jsp`](https://github.com/liferay-labs/jukebox-portlet/blob/6.2.x/docroot/html/songs/view_song.jsp)
implements buttons named *Move to the Recycle Bin* and *Delete* to
trash or permanently delete a song: 

    <aui:nav>
        <portlet:actionURL name="deleteSong" var="deleteSongURL">
            <portlet:param name="songId" value="<%= String.valueOf(song.getSongId()) %>" />
            <portlet:param name="moveToTrash" value="<%= String.valueOf(trashEnabled) %>" />
            <portlet:param name="redirect" value="<%= redirect %>" />
        </portlet:actionURL>

        <c:choose>
            <c:when test="<%= trashEnabled %>">
                <aui:nav-item href="<%= deleteSongURL %>" iconCssClass="icon-trash" label="move-to-the-recycle-bin" />
            </c:when>
            <c:otherwise>
                <aui:nav-item href="<%= deleteSongURL %>" iconCssClass="icon-key" label="delete" useDialog="<%= true %>" />
            </c:otherwise>
        </c:choose>
    </aui:nav>

Notice this JSP code specifies the `JukeboxPortlet`'s `deleteSong` action
method. It also displays an appropriate button for recycling or deleting the
entry, depending on whether the portlet class found the entity to be
trash-enabled. 

Now that you laid all the ground work for moving your entries to the Recycle
Bin, you're ready to set up the framework for rendering the trashed entries in
the Recycle Bin. You do this by implementing trash renderers for the
trash-enabled entities. 

## Step 5: Implement a Trash Renderer for Each Trash-Enabled Entity [](id=step-5-implement-a-trash-renderer-for-each-trash-enabled-entity)

In a similar way to creating a trash handler, you create a class to render trash
entries in the Recycle Bin. If you're already using an asset renderer, you can
reuse it, as long as it also implements the
[`TrashRenderer`](https://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/kernel/trash/TrashHandler.html)
interface.

As an example of a combined asset renderer and trash renderer implementation,
consider the Jukebox portlet's [`SongAssetRenderer`](https://github.com/liferay-labs/jukebox-portlet/blob/6.2.x/docroot/WEB-INF/src/org/liferay/jukebox/asset/SongAssetRenderer.java)
class.

If you don't already have an asset renderer, you must create a trash renderer.
In it, implement a `getTrashRenderer` method to instantiate and return a trash
renderer based on the trash entry's primary key. For an example of accessing
the trash renderer from a trash handler, consider the `getTrashRenderer` method
from the Document Library class [`DLFileShortcutTrashHandler`](https://github.com/liferay/liferay-portal/blob/6.2.x/portal-impl/src/com/liferay/portlet/documentlibrary/trash/DLFileShortcutTrashHandler.java):

    @Override
    public TrashRenderer getTrashRenderer(long classPK)
        throws PortalException, SystemException {

        DLFileShortcut fileShortcut = getDLFileShortcut(classPK);

        return new DLFileShortcutTrashRenderer(fileShortcut);
    }

It creates a new trash renderer class, [`DLFileShortcutTrashRenderer`](https://github.com/liferay/liferay-portal/blob/6.2.x/portal-impl/src/com/liferay/portlet/documentlibrary/trash/DLFileShortcutTrashRenderer.java),
based on the file shortcut instance. You can similarly create a
`getTrashRenderer` method in your trash renderer class. 

Congratulations! You now know how to implement moving your app's entries to the
Recycle Bin.

## Related Topics [](id=related-topics)

[Recycling Assets with the Recycle Bin](/discover/portal/-/knowledge_base/6-2/recycling-assets-with-the-recycle-bin)

[Service Builder and Services](/develop/tutorials/-/knowledge_base/6-2/service-builder)

[Enabling Search and Indexing](/develop/tutorials/-/knowledge_base/6-2/enabling-search-and-indexing)

[Asset Framework](/develop/tutorials/-/knowledge_base/6-2/asset-framework)

[Asset Enabling Custom Entities](/develop/tutorials/-/knowledge_base/6-2/asset-enabling-custom-entities)
