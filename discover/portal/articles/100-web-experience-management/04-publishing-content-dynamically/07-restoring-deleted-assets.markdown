# Restoring Deleted Assets [](id=restoring-deleted-assets)

Have you ever had that life-altering experience where you deleted an important
file and immediately regretted deleting it? The deed is usually followed by a
palm to the forehead or a sick feeling. Good news! @product@ is here to turn that
frown upside down with the *Recycle Bin* feature. With the Recycle Bin, the
*Move to the Recycle Bin* action replaces *Delete* for certain asset types.
Content is now temporarily stored in the Recycle Bin. This allows the content to
be restored back to its original state. Recycled items can expire after a
certain period of time, resulting in their permanent deletion. Before diving
into how the Recycle Bin works, you'll look at how to configure it.

## Configuring the Recycle Bin [](id=configuring-the-recycle-bin)

The Recycle Bin supports instance-wide scope or site-specific scope. The
instance-wide scope of the Recycle Bin is set by adding the `trash.enabled`
property to your `portal-ext.properties` file. By default, the Recycle Bin is
enabled instance-wide. You'll go into more detail for adding this property and
several others to your properties file later in the section. First, you'll
explore the UI and see what the Recycle Bin can do.

First, you'll configure the Recycle Bin for site-specific scoping. Choose the
site you'd like configure for the Recycle Bin from the Site Administration menu.
Then click *Configuration* &rarr; *Site Settings*. Next, select the top
*Advanced* tab and click *Recycle Bin*. You'll notice a few configurable
options:

**Enable Recycle Bin:** enable and disable settings for the Recycle Bin's
site-specific scope.

**Trash Entries Max Age:** customize the number of minutes a file is kept in the
Recycle Bin until its permanent deletion (default is 43200 minutes, or 30 days).

![Figure 1: The Recycle Bin offers several configurable options for your site.](../../../images/recycle-bin-site-settings.png)

When you've finished configuring your Recycle Bin settings, click *Save*.

+$$$

**Note:** If you disable the Recycle Bin while it's still holding recycled
items, the recycled items reappear in the Recycle Bin if it is re-enabled.

$$$

You can also configure the Recycle Bin via properties in the `portal.properties`
file. Remember that it's a best practice not to edit the `portal.properties`
directly, but to create a separate `portal-ext.properties` file containing the
properties to override. There are some additional options not available in the
GUI that you can set:

`trash.search.limit=500`: set the limit for results used when performing
searches in the Recycle Bin (default is 500).

`trash.entry.check.interval=60`: set the interval in minutes for how often the
trash handler runs to delete trash entries that have been in the Recycle Bin
longer than the maximum age (default is 60).

Also, as was mentioned earlier, there are properties to enable the Recycle bin
instance-wide and set trash entries' maximum age.

`trash.enabled=true`: set this property to *false* to disable the Recycle Bin
for all sites in the portal (default is *true*).

`trash.entries.max.age=43200`: set the number of minutes trash entries should be
held before being permanently deleted.

Visit the
[portal.properties](https://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html#Trash)
file to view all of the configurable properties for the Recycle Bin.

Next, you should make sure permissions are set properly for users who can
handle/view the assets in the Recycle Bin. Users who had *View* permissions on
a document when it was recycled can also view that document in the Recycle
Bin. Users who had *Update* or *Delete* permissions on a document when it was
recycled can restore the document. 

Now that you've successfully configured the Recycle Bin, you'll look at how to
use it.

## Using the Recycle Bin [](id=using-the-recycle-bin)

The Recycle Bin is temporary storage configured for multiple asset types across
you Liferay instance. Instead of offering a specific Recycle Bin for each asset
type, @product@ provides a central master Recycle Bin where different asset types
can be stored. This provides an easy search and recovery process.

![Figure 2: The Recycle Bin provides a seamless administrative experience for deleting and removing content.](../../../images/recycle-bin-overview.png)

You can recycle several different types of assets, including:

- Blogs
- Bookmarks
- Documents and Media
- Message Boards (and attachments)
- Web Content
- Wiki (and attachments)

<!-- TODO: Planned for RB compatibility - Calendar (LPS-35264), Pages
(LPS-29964), Structures (LPS-29970), Templates (LPS-29970). -->

+$$$

**Note:** The wiki and Message Board attachments are stored in a separate
Recycle Bin instance unique to the attachment's app. For instance, when moving a
wiki attachment to the Recycle Bin, it can only be restored from the Wiki app's
*Removed Attachments* Menu, not the site-scoped Recycle Bin.

$$$

For a quick example to show how easy the Recycle Bin is to use, you'll practice
sending a web content article to the Recycle Bin and then restoring it. You'll
run through two different methods of restoring the file.

1. Navigate to Site Administration and select *Content* &rarr; *Web Content*.

2. Select the *Add* button (![Add Web Content](../../../images/icon-add.png))
   and click *Basic Web Content*.

3. Enter some text for the Title and Content and click *Publish*.

4. Click the article's *Actions* button
   (![Actions](../../../images/icon-actions.png)) and click *Move to the Recycle
   Bin*.

	Note that the *Delete* button is not listed. @product@ avoids the risk of
	accidental deletion of your files by funneling the content through the
	Recycle Bin.

5. After deleting the file, a success message appears, offering an *Undo*
option. Click *Undo*. The web content is retrieved from the Recycle Bin and
stored in its original place.

6. Select the *Move to the Recycle Bin* button again.

7. Navigate back to Site Administration and click the Recycle Bin button from
   the Content dropdown.

8. Find your sample web content and click its *Actions* button.

9. You can restore or delete the content. Select *Restore*.

	![Figure 3: In the Recycle Bin, you have the option of restoring or permanently deleting the content.](../../../images/recycle-bin-restore.png)

10. Navigate back to the Web Content screen and notice that your sample web
content was restored back to its original place.

Congratulations! You now know the two general processes of sending and restoring
content to/from the Recycle Bin. For other asset types, the Recycle Bin works
similarly.

Some @product@ applications, such as Web Content and Documents and Media, support
folders into which their content can be organized. You can also send folders and
sub-folders to the Recycle Bin. Keep in mind that this sends the entire
folder/sub-folder structure and all files to the Recycle Bin. Folders and
sub-folders are restored and deleted the same way as a single file. Also,
expired web content can be moved to the Recycle Bin and restored, just like a
regular file.

In step 9 of the example, you probably noticed the *Delete* button from within
the Recycle Bin. This is the permanent delete button. Once you select this, your
file cannot be retrieved and is gone forever. There is also an *Empty the
Recycle Bin* option accessible from the
(![Options](../../../images/icon-options.png)) button at the top of the Recycle
Bin screen. This permanently deletes all the files from the Recycle Bin. Next,
you'll learn how to use the drag and drop feature.

### Drag and Drop [](id=drag-and-drop)

A quick and easy way to dispose of your unwanted assets is to drag and drop them
into the Recycle Bin. While you're in the Control Panel, you can simply select
an asset and drag it to the Recycle Bin portlet located on the Control Panel
menu. When you click and begin dragging the asset, a message appears near your
cursor notifying you of the amount of files ready to be moved, and the
Recycle Bin is highlighted, showing you where the files can be dropped. After
you drop the asset onto the Recycle Bin portlet, the asset is removed from its
original location and transferred to the Recycle Bin.

![Figure 4: A quick and easy way of disposing your items is the drag and drop method.](../../../images/recycle-bin-drag.png)

Next, you'll explore the Recycle Bin's intelligence and behind the scenes
support that aids in a seamless recycling experience.

### Recycle Bin Intelligence and Support [](id=recycle-bin-intelligence-and-support)

Have you ever wondered if it's possible to check the IQ of a software feature?
Unfortunately, there is no tangible way to do this; however, if there were, the
Liferay Recycle Bin would be at the top of its class. As you've learned already,
it supports multiple asset types, a drag and drop feature, an Undo option, and
many more. Have you ever wondered what happens to file shortcuts if their linked
assets are recycled? What if you restore a file that has the same name as
another file currently stored in your site/instance? The Recycle Bin already
knows how to handle these types of issues for a seamless user experience.

When documents with shortcuts are moved to the Recycle Bin, the shortcuts are
invalidated. This ensures @product@ has active links and shortcuts at all times.
This cuts down on maintenance time and backtracking after recycling an important
asset.

Another important trait is the duplicate name recognition feature. When a file
is restored, the Recycle Bin scans the corresponding asset type files currently
in the site/instance to check for duplicate file names. If a duplicate file name
is found, the Recycle Bin prompts you to overwrite the existing file or rename
the file name you're trying to restore. 

For example, suppose you have the document `file1` stored in the Recycle Bin and
you have a separate document you created later with the same name in the
document library. If you try to restore the `file1` document, the Recycle Bin
recognizes duplicate names and prompts you to overwrite the existing document
in the document library or rename the document you're trying to restore.

![Figure 5: The Recycle Bin always scans your site/instance for duplicate file names during the restoration process.](../../../images/recycle-bin-duplicate-name.png)

Although the Recycle Bin prohibits the restoration of files that match
pre-existing file names in your site/instance, it will store files with matching
names.

Have you thought about how the Recycle Bin works during the staging process?
Although you learned earlier that there is only one master Recycle Bin, the
staging process requires a bit more flexibility with the Recycle Bin to maximize
its productivity. Therefore, when staging is enabled, there is a new and
separate Recycle Bin: the *Staging* Recycle Bin. The original Recycle Bin, or
*Live* Recycle Bin, holding unstaged material is still viewable while in
staging; however, it is never used.

During staging, everything you recycle is sent to the Staging Recycle Bin. This
prevents staged and unstaged recycled content from mixing. Do you see why this
would be a problem? Consider you have an unstaged document currently on your
live site. Next, you enable staging and delete that document. If you were to
turn staging off and return to the live site, without separate Recycle Bins, the
live document would be located on your site and in the Recycle Bin! Because of
this, the separate Staging Recycle Bin is necessary and only used during the
staging process. Finally, when you publish your staged material to live, the
Staging Recycle Bin content is transferred to the Live Recycle Bin.

+$$$

**Note:** The Staging Recycle Bin saves its contents until the staged material
has been published to the live site. This means that you can turn the staging
mode on and off without losing your recycled material.

$$$

The Recycle Bin saves you time by letting you restore content that's been
recycled. Instead of recreating or re-uploading content, you'll be tailoring
your Liferay instance to fully leverage its capabilities.
