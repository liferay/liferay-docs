# Implementing the Undo Functionality

Sometimes, you may accidentally send the wrong entry to the Recycle Bin. It
seems kind of grueling to navigate away from your page to the Recycle Bin to
restore the item, just to go back to where you originally started, right?
For this reason, the Recycle Bin framework supports an *Undo* button so you can
conveniently undo the action of sending an entry to the Recycle Bin without
leaving the page. In addition, the undo functionality provides links to the
trashed entry and the Recycle Bin.

![Figure 1: Implementing the Undo button enables users to conveniently reverse sending entries to the Recycle Bin.](../../images/undo-rb.png)

Walk through the following steps to implement the Undo functionality:

1. Add the Undo Tag
2. Call the Action for Restoration
3. Display the Taglib

Go ahead and implement the Undo button and its related links!

## Step 1: Add the Undo Tag

The first thing you'll need to do is add the `<liferay-ui:trash-undo>` taglib in
your JSP. Then, you need to set a portlet action URL and pass it to the
`<liferay-ui:trash-undo>` taglib. This maps the taglib's *Undo* button to the
portlet action that you'll implement in the next step. 

Your tags should look similar to the following in you JSP:

    <portlet:actionURL name="restoreSong" var="undoTrashURL" />

    <liferay-ui:trash-undo portletURL="<%= undoTrashURL %>" />

Now that you've added the taglib and action URL, go ahead and implement the 
portlet action to restore the entry.

## Step 2: Create a Portlet Action to Initiate Restoration

You must create a portlet action method that invokes your service method to
restore the entry. 

For example, the following portlet action method from the [JukeboxPortlet](https://github.com/liferay-labs/jukebox-portlet/blob/master/docroot/WEB-INF/src/org/liferay/jukebox/portlet/JukeboxPortlet.java)
class restores the song from the Recycle Bin:

    public void restoreSong(ActionRequest request, ActionResponse response)
        throws Exception {

        long[] restoreEntryIds = StringUtil.split(
            ParamUtil.getString(request, "restoreEntryIds"), 0L);

        for (long restoreEntryId : restoreEntryIds) {
            SongServiceUtil.restoreSongFromTrash(restoreEntryId);
        }
    }

This method implements the `restoreSong` action that was named in the `view.jsp`.
The action URL maps the `<liferay-ui:trash-undo>` taglib to this method.

Note how it parses entry IDs from the request object. It restores all of these
entries by calling the `restore*` service method. You can learn more about how
this method is set up in the [Moving Entries to the Recycle Bin](https://dev.liferay.com/develop/tutorials/-/knowledge_base/moving-entries-to-the-recycle-bin-lp-6-2-develop-tutorial) 
tutorial.

Are you wondering how this portlet action gets the ID of the entries to restore?
You'll learn how to pass this data to the session next. 

## Step 3: Providing Trash Entry Data for the Taglib

The final step for implementing the Undo button is to provide the trashed
entry's information to the `<liferay-ui:trash-undo>` taglib. In order for the
taglib to display properly, you must provide some information for the session
messages, so that the session knows which entries were just deleted. Once the
session knows which elements were just deleted, the method can use that
information to restore the entries. 

For example, the following `if` statement from the `deleteSong()` method of the
 [JukeboxPortlet](https://github.com/liferay-labs/jukebox-portlet/blob/master/docroot/WEB-INF/src/org/liferay/jukebox/portlet/JukeboxPortlet.java)
class populates the session with the entries that were just deleted:

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

It gathers the elements needed to distinguish each entry instance to restore.
For the Jukebox song element's the song's class name, title, and IDs are
included.

    data.put("deleteEntryClassName",
        new String[] {Song.class.getName()});
    data.put("deleteEntryTitle",
        new String[] {TrashUtil.getOriginalTitle(song.getName())});
    data.put("restoreEntryIds",
        new String[] {String.valueOf(songId)});

Then it adds these elements to the session messages, so the session knows which
elements were deleted. 

    SessionMessages.add(request, PortalUtil.getPortletId(request) +
        SessionMessages.KEY_SUFFIX_DELETE_SUCCESS_DATA, data);

    SessionMessages.add(request, PortalUtil.getPortletId(request) +
        SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_SUCCESS_MESSAGE);

In your portlet's delete action, you can similarly populate the session with the
entry information of the entries being deleted. It's simple really. Now you know
how to implement the Undo functionality for your app's trash-enabled entities!
