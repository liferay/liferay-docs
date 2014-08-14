# Microsoft Office integration [](id=microsoft-office-integration)

Liferay lets you open Microsoft Office files that reside in
Documents and Media. You can open the files from Microsoft Internet
Explorer or open them directly from your Microsoft Office applications.
Let's open a file from your browser first.

Liferay lets you launch Microsoft Office right from folders in Documents and
Media. First, find the Microsoft Office file that you want to edit. Then
click the drop-down icon in the upper-left corner of the document's thumbnail
and click the *Open in MS Office* link. Liferay launches Microsoft Office 
and prompts you to log in using your Portal credentials. Once logged in, the
file opens in your Microsoft Office application. 

![Figure 5.7: Open Microsoft Office files from within your Internet Explorer browser by clicking the *Open in MS Office* link.](../../images/05-open-in-MS-office.png)

<!-- Needs to be replaced for 6.2 -->
<!-- This feature only works for 32 bit browsers/Microsoft Office. -->

When you save the file, it is stored automatically in the Documents and Media
folder from which you opened it. 

Note that 64-bit Microsoft Internet Explorer and 64-bit Microsoft Office
versions do not currently support this feature. It is only supported on 32-bit
Microsoft Internet Explorer versions with 32-bit versions of Microsoft Office.
See
[http://issues.liferay.com/browse/LPS-28718](http://issues.liferay.com/browse/LPS-28718)
for more information. 

Next we'll show you how to navigate your Documents and Media folders and
open a file from the Microsoft Office application. 

Liferay lets you access Microsoft Office files in Documents and Media directly
from your Microsoft Office applications. Liferay implements the MS-DWSS
SharePoint protocol to allow saving and retrieving documents from Liferay Portal
as if it were a SharePoint server. You can conveniently update your Microsoft
Office (Office) files without having to exit your Office program.

For example, if you are working in Microsoft Word locally on your
machine, you can open a file from Documents and Media to view or edit it.
Simply select *File*&rarr; *Open* in Word and enter
`http://localhost:8080/sharepoint/` in the file name field. Click *Open* and log
in using your Portal credentials.

![Figure 5.8: Enter the URL of your `sharepoint` location on Liferay to access Documents and Media.](../../images/office-path.png)

In the list of folders displayed, navigate to *guest* &rarr; *document_library*
and select *All Files* to see your Documents and Media files.
Open the desired Word file to make changes. Click *Save* when you are finished
and close the file. 

Now anyone with appropriate permission can see the latest version of the file
with these updates. Liferay takes care of version control as well as file
check out and check in. Users can add comments, ratings, and tags.

Liferay's integration with Microsoft Office lets users leverage Documents and
Media in managing their Office files. Collaboration is simplified as users share
their most up-to-date versions of Office files.

Now let's look at configuring the Documents and Media portlet.
