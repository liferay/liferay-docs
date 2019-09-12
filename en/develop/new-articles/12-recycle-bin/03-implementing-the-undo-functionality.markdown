# Implementing the Undo Functionality [](id=implementing-the-undo-functionality)

Sometimes, users accidentally send the wrong entry to the Recycle Bin. It
seems kind of grueling to navigate away from your page to the Recycle Bin to
restore the item, just to go back to where you originally started, right?
For this reason, the Recycle Bin framework supports an *Undo* button, so you can
conveniently undo the action of sending an entry to the Recycle Bin without
leaving the page. 

![Figure 1: Implementing the Undo button enables users to conveniently retrieve entries back from the Recycle Bin.](../../images/undo-rb.png)

Walk through the following steps to implement the Undo functionality:

1. Add the Undo Tag
2. Call the Action for Restoration
3. Display the `liferay-ui:trash-undo` Tag

Go ahead and implement the Undo button and its related links!

## Step 1: Add the Undo Tag [](id=step-1-add-the-undo-tag)

First, you must use the `liferay-ui:trash-undo` tag in your JSP. Then you need
to set a portlet action URL and pass it to the `liferay-ui:trash-undo` tag.
This maps the tag's *Undo* button to the portlet action that you'll implement in
the next step. 

The tags you add to your JSP should look similar to these tags for restoring a
song in the Jukebox portlet:

    <portlet:actionURL name="restoreSong" var="undoTrashURL" />

    <liferay-ui:trash-undo portletURL="<%= undoTrashURL %>" />

Now that you've added the tag and action URL, go ahead and implement the 
portlet action to restore the entry.

## Step 2: Create a Portlet Action to Initiate Restoration [](id=step-2-create-a-portlet-action-to-initiate-restoration)

You must create a portlet action method that invokes your service method to
restore the entry. 

For example, the following portlet action method from the [JukeboxPortlet](https://github.com/liferay-labs/jukebox-portlet/blob/6.2.x/docroot/WEB-INF/src/org/liferay/jukebox/portlet/JukeboxPortlet.java)
class restores songs from the Recycle Bin:

    public void restoreSong(ActionRequest request, ActionResponse response)
        throws Exception {

        long[] restoreEntryIds = StringUtil.split(
            ParamUtil.getString(request, "restoreEntryIds"), 0L);

        for (long restoreEntryId : restoreEntryIds) {
            SongServiceUtil.restoreSongFromTrash(restoreEntryId);
        }
    }

This method implements the `restoreSong` action that was named in the
`view.jsp`. The action URL maps the `liferay-ui:trash-undo` tag to this method.

Note how it parses entry IDs from the request object. It restores all of these
entries by calling the `restore*` service method. 

Are you wondering how this portlet action gets the IDs of the entries to
restore? You'll learn how to pass this data to the session next. 

## Step 3: Providing Trash Entry Data for the Taglib [](id=step-3-providing-trash-entry-data-for-the-taglib)

The final step for implementing the Undo button is to provide the trashed
entry's information to the `liferay-ui:trash-undo` tag. In order for the
tag to display properly, you must provide some information for the session
messages. The session needs to know which entries were just deleted. Then the
restore method can use that information to restore the entries. 

For example, the following `if` block from the [JukeboxPortlet](https://github.com/liferay-labs/jukebox-portlet/blob/6.2.x/docroot/WEB-INF/src/org/liferay/jukebox/portlet/JukeboxPortlet.java)'s
method `deleteSong` populates the session with the entries that were just
deleted: 

    if (moveToTrash) {
        Song song = SongServiceUtil.moveSongToTrash(songId);

        Map<String, String[]> data = new HashMap<String, String[]>();

        data.put("deleteEntryClassName",
            new String[] {Song.class.getName()});
        data.put("deleteEntryTitle",
            new String[] {TrashUtil.getOriginalTitle(song.getName())});
        data.put("restoreEntryIds",
            new String[] {String.valueOf(songId)});

        SessionMessages.add(request, PortalUtil.getPortletId(request) +
            SessionMessages.KEY_SUFFIX_DELETE_SUCCESS_DATA, data);

        SessionMessages.add(request, PortalUtil.getPortletId(request) +
            SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_SUCCESS_MESSAGE);
    }

It gathers the elements needed to distinguish the entry instances to restore.
For the Jukebox song elements, the song's class name, title, and IDs are
included.

    data.put("deleteEntryClassName",
        new String[] {Song.class.getName()});
    data.put("deleteEntryTitle",
        new String[] {TrashUtil.getOriginalTitle(song.getName())});
    data.put("restoreEntryIds",
        new String[] {String.valueOf(songId)});

Then these elements are added to the session messages, so they appear in the
portlet when it refreshes. 

    SessionMessages.add(request, PortalUtil.getPortletId(request) +
        SessionMessages.KEY_SUFFIX_DELETE_SUCCESS_DATA, data);

    SessionMessages.add(request, PortalUtil.getPortletId(request) +
        SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_SUCCESS_MESSAGE);

In your portlet's delete action, you can similarly populate the session with the
entry information of the entries being deleted. Now you know how to implement
the Undo functionality for your app's trash-enabled entities!

## Related Topics [](id=related-topics)

[Recycling Assets with the Recycle Bin](/discover/portal/-/knowledge_base/6-2/recycling-assets-with-the-recycle-bin)

[Service Builder and Services](/develop/tutorials/-/knowledge_base/6-2/service-builder)

[Enabling Search and Indexing](/develop/tutorials/-/knowledge_base/6-2/enabling-search-and-indexing)

[Asset Framework](/develop/tutorials/-/knowledge_base/6-2/asset-framework)

[Asset Enabling Custom Entities](/develop/tutorials/-/knowledge_base/6-2/asset-enabling-custom-entities)
