# Capturing Web Sites with the Bookmarks Portlet [](id=capturing-web-sites-with-the-bookmarks-portlet)

Many of us enjoy collecting things we value. They may be stamps, comic books,
sea shells, or fabulous shoes. The list goes on and on. But have you considered
URLs collectible? Having a thorough collection of links can be a great way to
add value to your portal's usability.

With Liferay's Bookmarks application, users collect and manage URLs in the
portal. They can add, edit, delete, export and import bookmarks. Users can use
links to access regularly visited web sites. Administrators can publish links
tailored to specific groups of users. Both internal pages as well as external
sites can be bookmarked.

## Adding and Using Bookmarks

Navigate to your portal and add the Bookmarks application to your page by
selecting *Add* &rarr; *Applications*. The portlet looks like this by default:

![Figure 13.1: Initially, no bookmarks are listed in this form until they're created.](../../images/bookmarks-add-portlet.png)

Across the top of the portlet are links labeled Home, Recent, and Mine. There is
also a Search field and button.

**Home:** returns you to the top level of the portlet.

**Recent:** displays a list of the latest bookmarks that have been added.

**Mine:** displays a list of the bookmarks you added to the portlet.

**Search:** lets you search for bookmarks by name, category, or tags.

When you select the *Permissions* button on the right, a list of Roles and their
associated permissions appears. The options are Add Entry, Add Folder,
Permissions, and View. When you are finished selecting the permissions, click
*Save*.

Clicking the *Add Folder* button in the Bookmarks application reveals the *New
Folder* form. Here you can choose the folder's name, a description of its
contents, and who can view it. Under More Options, you can set portlet
permissions for various Roles to the folder. Click *Save* when you are finished.

To create a bookmark, click the *Add Bookmark* button. This form is similar to
the New Folder form but has a few more options.

![Figure 13.2: When you use the Add Bookmark form, you must enter a valid URL in the required field.](../../images/bookmarks-add-new-form.png)

Click *Select* to choose the folder for the new bookmark. Click *Remove* to
delete a bookmark from the selected folder. As stated above, a removed link goes
into the list of general bookmarks that aren't associated with a folder. These
are listed in the bookmarks section, below the folders, in the portlet.

Below the Permissions there are additional options for Categorization and
Related Assets, just like in other Liferay applications. Please see chapter 6 on
the Asset Framework for further information about this.

Once you have added a new bookmark, it appears in the portlet. From here, you
can manage your bookmark using familiar Liferay editing features. Collecting and
organizing your links is a snap when you use Liferay's Bookmarks application.

## Organizing Bookmarks by Folder

You can store all your important links in one place and you can manage this data
easily using folders. You can create, edit, and delete bookmark folders. You get
to decide how many bookmarks or folders are displayed on a page. Bookmark
folders can have any number of subfolders.

Here's an example of what one bookmarks portlet might look like. Bookmark
Folders are displayed above individual bookmarks. 

![Figure 13.3: Individual bookmarks, not associated with a folder, are listed separately.](../../images/bookmarks-folder-view-wide.png)

In this example, there are six bookmark folders, three of which have
subfolders. The columns showing the number of folders and the number of entries
show the subfolders and the entries contained within each top level folder. Note
that total number of bookmarks includes those in the subfolders.

Using the Actions button on the right, you can edit the folder, manage
folder permissions, delete the folder, add a subfolder, or add a bookmark to
the folder. 

As your collection of links grows, you may need to add more subfolders to keep
things in order. Should you decide a link needs to move from one folder to
another, you can manage this using the Edit option for that link. 

### Moving a Link

Just for fun, let's move a link from the main bookmarks folder into a subfolder
one level down. We'll move the Liferay link into the Trivia subfolder in the
Sports & Games folder. The Trivia subfolder is a child of the Sports & Games
folder. We'll move the link to the Sports & Games folder first before moving it
into the Trivia subfolder.

To achieve this, we select *Edit* from the Actions button for the link. In the
Edit view, find the section called *Folder*. When applicable, this section 
contains breadcrumb links to allow you to navigate freely among the other folder
levels. Currently, there are two buttons here labeled Select and Remove. Click
the *Select* button and a new window appears, like the one below. Clicking the
*Remove* button here doesn't affect the link. It is used solely for moving links
out of folders and subfolders back into the main Bookmarks view.

![Figure 13.4: Select the Choose button next to the desired folder.](../../images/bookmarks-choosing-subfolder.png)

Now choose the *Sports & Games* folder. When you do this, notice how the link's
Folder section changes to reflect the new location of the link. When you choose
*Save*, you return to the Bookmarks portlet. Notice that the Liferay link is no
longer under the Bookmarks section. Select *Sports & Games* to reveal its
contents. In the example below, you can see the Liferay link is now in the
bookmarks section of the Sports & Games folder.

![Figure 13.5: When you move a link to a folder, it remains in the bookmarks section until it's moved into a subfolder.](../../images/bookmarks-link-move.png)

Select *Edit* from the Actions button next to the Liferay link, then click
*Select*. Navigate to the *# of Folders* number next to the Sports & Games
folder and choose the *Trivia* subfolder. Again, notice the change in the
folder-level breadcrumbs. Click *Save* and the Sports & Games folder view
appears. change in the link's folder section, which now displays *Trivia*. Click
*Save* and the Sports & Games folder view appears. Select *Trivia* to see your
link in the subfolder's bookmarks list. Piece of cake, right?

To move a link out of a subfolder and into a higher-level folder, edit the link
in the subfolder and choose *Select*. In the resulting window, select the
appropriate folder from the breadcrumbs at the top. In this example, we selected
*Sports & Games* for consistency. This opens a new window, like the one below,
showing the other folder options.

![Figure 13.6: In this view, you can move the link into several different locations.](../../images/bookmarks-link-move3.png)

You can choose one of the other subfolders from the list, or you can choose
*Home* from the breadcrumbs at the top. When you verify that the desired folder
is the one currently displayed in the breadcrumbs, click *Choose This Folder*.
Then click *Save* and you're done. If you don't like any of the subfolders
listed, you can place the link into a new subfolder by using the *Add Subfolder*
button.

If you choose *Remove* instead of Select in the above example, you take the link
out of both subfolders and return it to the main bookmarks view.

Now that you have an understanding of how bookmark folders are used, let's
create our own plugin repository.
