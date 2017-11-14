# Collaborating on Files [](id=collaborating-on-files)

Not only does @product@'s Documents and Media enable you to publish files
easily, but it also provides a terrific environment for collaborating on
documents. There are many common collaboration scenarios. For example, you might
have a spreadsheet that you and your peers need to populate, or a proposal that
you and other stakeholders must produce. You might be asked to review and edit a
document. All these cases involve multiple users modifying documents and require
an environment that helps manage document changes. @product@'s Documents and 
Media provides these capabilities. 

@product@'s document management apps support accessing apps on different
kinds of devices and environments. Not only can you access documents from your
site via your browser, but you can also access them from your native file
browser and use Liferay Sync to access them from your tablet or mobile device.
And Liferay Sync makes sure you're you can access the latest document versions. 

To demonstrate using these features, we'll join the Lunar Resort Recreation
Department members collaboration on an important document: the Lunar Resort's
upcoming activities newsletter. Resort representatives from each activity group
must contribute content to entice resort guests to sign up for activities. Staff
members involved include Chip Shot (golf instructor), Wilma Carwin (lunar rover
race instructor), and Tenzing Nepal (lunar sherpa). Each of them use different
devices and bring their own style and attitude to the collaboration. So perhaps
you'll be able to relate to their effort.

Before the group leads can start working on the newsletter, the department
director (who's associated with the organization's content administrator Role)
creates a folder called *Newsletters* in the organization's document library. To
follow along with the example, create the *Newsletters* folder in an
organization's document library on your site. This folder will hold all of the
department's newsletters.

Next, open your favorite document editor, enter the title "Upcoming Lunar
Activities", and save the document as `upcoming-activities.docx`. Upload the
file to the document library and enable the leads to edit the file by
associating them with Role that you grant permission to edit the file. To enable
the activity leads to access the newsletter from the department's private
Organization Site, add a Documents and Media app to its home page. Then turn on
the *Show actions* display option in the app's configuration window. Make sure
to assign the leads a role with permissions to access the folder. From the
Documents and Media app on Recreation Department's private home page, the leads
can now access the *Newsletters* folder and the initial document. The
newsletter's next collaborator will be Tenzing Nepal, the resort's veteran lunar
sherpa.

Tenzing is as agile online as he is on a mountainside. He often edits his
documents across several versions so they read just as he likes them. He prefers
to check documents out from within the web interface and download them to his
computer to work on them. Let's follow Tenzing as he produces multiple versions
of the newsletter. 

## Creating New File Versions [](id=creating-new-file-versions)

To prevent other users from accidentally overwriting changes you're making to a
document, the Document Library allows you to *checkout* the document. The user
who checks out a file is the only user who can checkin changes to the file.
Checking in a file produces a new file version. 

Tenzing Nepal is the first resort instructor to edit the activities newsletter.
You can imitate Tenzing's actions or follow along as you create new versions of
your own files. Inside the Documents and Media app, select the
`upcoming-activities.docx` file's *Checkout* action. Upon checkout, the file's
status is *Draft*. While the file is checked out, you're the sole editor; all
other users must wait until after you've checked in the file to edit it. While
you have the file checked out, you can download it, replace the file entry's
file, move the file entry to another Document Library folder, check it in, or
cancel checkout. Tenzing proceeds with the next step towards contributing to the
file.

Unless you're using
[Liferay Sync](/discover/portal/-/knowledge_base/7-0/using-liferay-sync-on-your-desktop)
or a local drive mapped to the file's WebDAV (explained in the next section)
URL, you'll typically follow the basic workflow below to edit a Document Library
file from your machine:

1. Checkout the file.
2. Download the file and edit it locally.
3. Edit the file locally.
4. From the file's Edit screen in Documents and Media, browse to and select the
   local file. Save and Checkin
5. Check in the file.

Let's continue as Tenzing, following the above worklow. (1) You've already
checked out the file. (2) Download the file to your machine by clicking the
*Download* action. (3) Then open the file in your favorite document editor, add
information on the upcoming Lunar hikes, and saves your changes. You're now
ready to apply the changes to file in Documents and Media.

In the file entry's screen in Documents and Media, click on the *Edit* action.
Then click *Browse*, navigate to the copy of the `upcoming-activities.docx` file
you edited on your desktop, and select it. Then click the *Save and Check In*
button and the *Describe Your Changes* screen appears. This screen allows you to
describe your changes and specify whether to apply a major or minor version
increment (default). For example, if a file's current version is 1.0, the file's
next minor version is 1.1 and next major version would be 2.0. To increment the
file to a new *Major Version*, you must select the *Changes Create a Major
Version* checkbox. Your changes will otherwise result in a new Minor Version.

Select the *Changes Create a Major Version* checkbox, describe your changes in
the *Change Log* field, and click *Save*. The file's major version number is
incremented and its status is now *Approved*.

+$$$

**Note**: If you *Edit* a file without checking it out, the Edit screen displays
a checkbox *Customize the Version Number Increment and Describe My Changes*. For
your file modification, if you want to select between major/minor version types
and/or want to enter a change log then select the checkbox. On clicking
*Publish*, the *Describe Your Changes* screen appears so you can specify your
change's version increment type and description.

$$$

Since the file is checked in, any user can now check it out. Tenzing realizes
there are some minor details he wants to add. As Tenzing, checkout the file once
again. Download the file locally, make the changes (any changes you like), and
save the local file. Then edit the file entry in Documents in Media, browse to
and select your local file, and click *Save and Check In*. This time, leave the
major version selection unchecked and click *Save*. The file is checked in and
it's minor version number is incremented. 

![Figure 1: No file is "written in stone". Version history actions let you inspect, delete, and reinstate file versions.](../../../../images/dm-file-version-actions.png)

In the file entry's screen, the *Version History* table lists the versions of
the file. The department director created version 1.0, Tenzing created major
version 2.0, and Tenzing also created minor version 2.1. Each file version has
an Actions icon (![Actions](../../../../images/icon-actions.png)). You
can perform the following actions on each file version that's been checked in
and approved (if no workflow is associated with a folder, the file's approved on
checkin):

**Download**: Download the version of the file to your machine.

**View**: View the file entry screen for the version of the file.

**Revert**: Check in a new major version of the file based on that file version.

**Delete Version**: Remove the file version from the Document Library. All other
file versions remain intact.

You've used the basic Document Library workflow to edit files locally and upload
them to create new file versions of the newsletter. Next up is accessing
documents from your desktop file manager. To demonstrate this, you'll step into
the shoes of lunar rover race instructor Wilma Carwin.

Wilma updates the newsletter in a different manner. She doesn't care much for
high tech. The only online device she uses is the workstation in the garage
office. Wilma only uses it when she has to; otherwise she'd rather be behind the
wheel of a rover doing donuts in the dirt and banking turns off the canyon
walls. Fortunately for Wilma, the resort IT team mapped the rec department's
document library to a network drive on her workstation. This lets her edit
documents without having to meddle with the Lunar Resort site. It's worth
explaining how they integrated her file manager with the document library. 

## Desktop Access to Documents and Media [](id=desktop-access-to-documents-and-media)

You can access the Document Library from your computer's file manager via
WebDAV. WebDAV stands for Web-based Distributed Authoring and Versioning. It's a
set of methods based on HTTP that allows users to create, edit, move, or delete
files stored on web servers. WebDAV is supported by most major operating systems
and desktop environments, including Linux (both KDE and GNOME), Mac OS, and
Windows.

At the Lunar Resort, Activity Department members use WebDAV to access their
Document Library. WebDAV provides an alternative way to do this using a file
manager instead of a web browser. To access the Document Library folder from
your file browser, you need to use log in credentials for the @product@
instance and the WebDAV URL of the folder you'd like to access.

Navigate to the Documents and Media app hosting the folder you'd like to access.
Click on the folder's Actions icon ![Actions](../../../../images/icon-actions.png)
and select *Access from Desktop*.

![Figure 2: Select *Access from Desktop* to get the folder's WebDAV URL.](../../../../images/dm-access-from-desktop-action.png)

Copy the WebDAV URL. On Windows, map a network drive drive to the WebDAV URL.
Enter your @product@ credentials when you're prompted for them. The document
library folder appears in the network drive. From your file browser, you can now
add, edit, move, or delete files in this folder.

On Mac OS X, select *Go* &rarr; *Connect to Server* in Finder. Then enter the
WebDAV URL of the folder you'd like to access in the Server Address field, click
*Connect* and you should be prompted for your @product@ credentials.

On Linux, you must slightly modify the WebDAV URL of your folder in your file
manager. For KDE's Dolphin, change the URL's protocol so that it says 
`webdav://` instead of `http://`. For GNOME's Nautilus, change the URL's 
protocol so that it says `dav://` instead of `http://`. Then press *Enter* and
you're prompted for your @product@ credentials.

Note that @product@ increments the version numbers of files edited and uploaded 
via WebDAV so you don't have to worry that using your file manager will bypass 
the functionality of @product@'s web interface. 

Lunar rover race instructor Wilma Carwin doesn't mind editing her Documents and
Media files from her computer's file manager, because it's convenient and
easiest for her. She opens the document `upcoming-activites.docx` and adds news
about various types of rover racing lessons offered and the upcoming Lunar Grand
Prix. She saves the file and closes it. The file in Documents and Media is now
"Approved" and its minor version is incremented. Voila! She's done her part
collaborating on the newsletter.

Following Wilma Carwin's demonstration, you've learned how to map your local
file manager to a Document Library folder and collaborate on files from your
desktop environment. 
