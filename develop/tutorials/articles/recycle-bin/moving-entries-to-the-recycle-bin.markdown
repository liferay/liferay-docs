# Moving Entries to the Recycle Bin

This tutorial covers how to add the ability to move entries in your app to the
Recycle Bin. Once you implement this component, users can move entries to the
Recycle Bin with the click of a button. 

![Figure 1: You can easily create a way to move your app's entities to the Recycle Bin.](../../images/move-entities-rb.png)

Run through these steps to provide the means to move entries to the Recycle 
Bin for your app: 

1. Enable Trash for Service Entities
2. Implement a Trash Handler for Each Trash-Enabled Entity
3. Create a Service Method to Move Entries to the Recycle Bin
4. Create a Portlet Action to Initiate Moving Entries to Recycle Bin
5. Implement a Trash Renderer for Each Trash-Enabled Entity

Start off by configuring the app's service. 

## Step 1: Enable Trash for Service Entities

You must enable the trash feature for each entity in your app that you want to
use with the Recycle Bin. In your app's `service.xml` file, insert the
`trash-enabled="true"` attribute within the `<entity>` tag for each of those
entities. Your code should look similar to the following:
    <entity name="Name" local-service="true" remote-service="true" uuid="true" trash-enabled="true">

Run [Service Builder](http://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/generate-services-liferay-portal-6-2-dev-guide-04-en)
to generate back-end trash related classes for the trash-enabled entities. 

Next, you'll need to implement trash handlers for those entities.

## Step 2: Implement a Trash Handler for Each Trash-Enabled Entity

As with many other Liferay frameworks--such as those for workflow, assets, and
indexing--you must implement handler classes for that framework. The Recycle
Bin's handlers are required to manage basic trash operations. You must create an
implementation of the [TrashHandler](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/kernel/trash/TrashHandler.html)
interface for each trash-enabled entity. Recycle Bin trash handlers manage the
entries as they are moved into the Recycle Bin, viewed in the Recycle Bin,
restored, or permanently deleted. As a convenience, Liferay provides the
abstract class [BaseTrashHandler](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/kernel/trash/BaseTrashHandler.html)
that your trash handlers can extend.

Consider the following [TrashHandler](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/kernel/trash/TrashHandler.html)
methods as a minimal set of methods to implement/override in your trash
handlers: 

- `deleteTrashEntry()`
- `getClassName()`
- `getRestoreContainedModelLink()`
- `getRestoreMessage()`
- `hasTrashPermission()`
- `isInTrash()`
- `restoreTrashEntry()`

For example the Jukebox portlet implements a class named [JukeBoxBaseTrashHandler](https://github.com/liferay-labs/jukebox-portlet/blob/master/docroot/WEB-INF/src/org/liferay/jukebox/trash/JukeBoxBaseTrashHandler.java)
that serves as a base implementation. It's leveraged by trash handlers for the
app's song and album entities. As an example trash handler implementation, you
can view the [SongTrashHandler](https://github.com/liferay-labs/jukebox-portlet/blob/master/docroot/WEB-INF/src/org/liferay/jukebox/trash/SongTrashHandler.java)
and its parent base class [JukeBoxBaseTrashHandler](https://github.com/liferay-labs/jukebox-portlet/blob/master/docroot/WEB-INF/src/org/liferay/jukebox/trash/JukeBoxBaseTrashHandler.java).

After you've implemented trash handlers for your trash-enabled entities, you'll
need to specify those handlers in your app's `liferay-portlet.xml` file. For
example, the Jukebox's `SongTrashHandler` class is specified in a
`<trash-handler>` element for the Songs portlet as follows:

    <trash-handler>org.liferay.jukebox.trash.SongTrashHandler</trash-handler>

You can refer to the Jukebox portlet's [liferay-portlet.xml](https://github.com/liferay-labs/jukebox-portlet/blob/master/docroot/WEB-INF/liferay-portlet.xml)
file for examples of defining trash handlers. 

---

![note](../../images/tip-pen-paper.png) **Note:** A trash handler refers to an 
entity, not a portlet. Thus, a trash handler can be declared in any of a 
plugin's portlets. For neatness, however, they are usually declared in the 
principal portlet of the suite.

---

Great! So you have trash handlers ready to manage your trash entries, but you 
still need a way to get the entries into the Recycle Bin. Create a service
method to move entries there.

## Step 3: Create a Service Method to Move Entries to the Recycle Bin

You'll implement a local service method that actually moves entries to the
Recycle Bin. This service method must implement the trash service for the
entity. Your `EntityNameLocalServiceImpl.java` class should look similar to the
following example Jukebox Portlet's [SongLocalServiceImpl](https://github.com/liferay-labs/jukebox-portlet/blob/master/docroot/WEB-INF/src/org/liferay/jukebox/service/impl/SongLocalServiceImpl.java)
code: 

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
an entry is moved to the Recycle Bin, Liferay reindexes the entities
and their corresponding trash entries. This makes trashed entries searchable
from within the Recycle Bin, but not searchable outside of it. Trashed entries
are not included in search results outside of the Recycle Bin.

Another important aspect of this method is its call to
`song.setStatus(WorkflowConstants.STATUS_IN_TRASH)`, which sets the song's
status so that workflows know the song is in the trash.

Next, the asset's visibility is updated so a user can no longer view it outside
the Recycle Bin. Its visibility is deactivated by the following call:

    assetEntryLocalService.updateVisible(Song.class.getName(), song.getSongId(), false);

On first thought, this may seem a bit odd. Why do you have to make the entry 
invisible in its original location? I thought I was moving it to the Recycle Bin?
Importantly, entries that are moved to the Recycle Bin are actually left in
their original location, but with their visibility turned off.

---

![note](../../images/tip-pen-paper.png) **Note:** If you're not using assets
with your entity, you'll need to filter the elements in your UI by status, so
only approved entities are shown. Otherwise, your app will display approved
entities and trashed entities together. Only assets can use the
`updateVisible()` method to update their status.

---

Next, notice that the service method adds a new trash entry in the Recycle Bin:

    TrashEntry trashEntry = trashEntryLocalService.addTrashEntry(
            userId, song.getGroupId(), Song.class.getName(), song.getSongId(),
            song.getUuid(), null, oldStatus, null, typeSettingsProperties);

Lastly, the `moveSongToTrash()` service method invokes the
 [`TrashUtil.getTrashTitle`](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portlet/trash/util/TrashUtil.html#getTrashTitle(long))
method to set the entry's *trash title*, which is an alternative reference to
the entry. The trash title prevents duplicate entry name conflicts, which you
can learn about in more detail in the [Resolving Recycling Conflicts](https://dev.liferay.com/develop/tutorials/-/knowledge_base/resolving-recycling-conflicts-lp-6-2-develop-tutorial) 
tutorial.

## Step 4: Create a Portlet Action to Initiate Moving Entries to Recycle Bin

Great! Now you must provide the means of invoking the service method from your
portlet. You'll implement this using a portlet action that can be triggered from
a JSP.

Take for example the [JukeboxPortlet](https://github.com/liferay-labs/jukebox-portlet/blob/master/docroot/WEB-INF/src/org/liferay/jukebox/portlet/JukeboxPortlet.java)
class, which extends [MVCPortlet](https://github.com/liferay/liferay-portal/blob/master/util-bridges/src/com/liferay/util/bridges/mvc/MVCPortlet.java),
provides the `deleteSong()` portlet action method to invoke the
 [SongLocalServiceImpl](https://github.com/liferay-labs/jukebox-portlet/blob/master/docroot/WEB-INF/src/org/liferay/jukebox/service/impl/SongLocalServiceImpl.java)'s
`moveSongToTrash()` service method. Your class should look similar:

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

Note that the `try` block contains logic for handling whether the entry is to be
moved to the Recycle Bin or permanently deleted.

Now that you have your portlet action written in your class, you can use a JSP 
to invoke your portlet action. For example, the Jukebox portlet's
 [view_song.jsp](https://github.com/liferay-labs/jukebox-portlet/blob/master/docroot/html/songs/view_song.jsp)
file provides buttons named *Move to the Recycle Bin* and *Delete* to trigger
trashing or permanently deleting a song: 

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

Notice this JSP code specifies the `JukeboxPortlet.java`'s `deleteSong` action
method. Also, the button appropriate for recycling or deleting is displayed
depending on the value of the `trashEnabled` boolean variable that was set up in 
the portlet class.

Now that you have layed all the ground work for moving your entries to the 
Recycle Bin, you can set up the frame work to render those trashed entries in 
the Recycle Bin. For this, you must implement a trash renderer for the 
trash-enabled entities. 

## Step 5: Implement a Trash Renderer for Each Trash-Enabled Entity

Now that you have the necessary classes and methods to accomplish moving entries
to the Recycle Bin, you can implement the appropriate renderer so you can see
the trashed entries. Similar to the trash handler, you'll need to create a class
that renders trash entries in the Recycle Bin. If you're already using an asset
renderer, you can reuse it, as long as it implements the `TrashRenderer`
interface too.

As an example of a combined asset renderer and trash renderer implementation,
consider the Jukebox portlet's [SongAssetRenderer](https://github.com/liferay-labs/jukebox-portlet/blob/master/docroot/WEB-INF/src/org/liferay/jukebox/asset/SongAssetRenderer.java).

If you don't have an asset renderer, you'll need to create a trash renderer and
implement a `getTrashRenderer()` method to instantiate and return a trash
renderer based on a trash entry's primary key. For an example of accessing the
trash renderer from a trash handler, consider the following method from the
Document Library class [DLFileShortcutTrashHandler](https://github.com/liferay/liferay-portal/blob/master/portal-impl/src/com/liferay/portlet/documentlibrary/trash/DLFileShortcutTrashHandler.java):

    @Override
    public TrashRenderer getTrashRenderer(long classPK)
        throws PortalException, SystemException {

        DLFileShortcut fileShortcut = getDLFileShortcut(classPK);

        return new DLFileShortcutTrashRenderer(fileShortcut);
    }

It creates a new trash renderer class, [DLFileShortcutTrashRenderer](https://github.com/liferay/liferay-portal/blob/master/portal-impl/src/com/liferay/portlet/documentlibrary/trash/DLFileShortcutTrashRenderer.java),
based on the file shortcut instance. 

Congratulations! You now know how to implement moving your app's entries to the
Recycle Bin.
