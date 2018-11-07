# Structure Settings [](id=structure-settings)

After you have created a structure and configured its fields, there is
additional configuration to consider including permissions and remote access to
managing structures.

### Assigning Permissions [](id=assigning-permissions)

Permissions on structures can be set just like any other 
[permission](discover/portal/-/knowledge_base/7.1/roles-and-permissions).
Most users should not be able to edit structures. Structures are coupled with
templates, which require some web development knowledge to create. This is why
only trusted developers should be able to create structures and templates.
Users, of course, should be able to view structures. The *View* permission
enables them to make use of the structures to create content.

![Figure 1: You're able to assign structure permissions via the *Actions* button.](../../../../../images/web-content-structure-permissions.png)

The best practice for structure permissions is to grant or deny them based on
Roles.

### WebDAV URL [](id=webdav-url)

The WebDAV URL feature is available for web content structures and templates so
users can upload and organize resources from both a web interface and the file
explorer of their desktop operating system. With the WebDAV URL, site
administrators can add, browse, edit, and delete structures and templates on
a remote server. After you complete your structure, you can access the WebDAV
URL by re-opening the structure or template and clicking the *Details* section.
If you'd like the see WebDAV in action, see 
[WebDAV Access](/discover/portal/-/knowledge_base/7-1/desktop-access-to-documents-and-media). 

+$$$

**Note:** Some operating systems require a WebDAV server to be class level 2
(to support file locking) before allowing files to be read or written. The
Documents and Media library uses a class level 2 WebDAV server but Web Content
structures and templates do not. This means that @product@'s Document and Media
library supports WebDAV file locking but Web Content structures and templates do
not. However, on operating systems which require WebDAV servers to be class
level 2, it's possible to avoid the restriction by using third-party WebDAV
clients (e.g., [Cyberduck](http://cyberduck.ch)).

$$$

Now that you understand how structures work, you're ready to understand the
other half of @product@'s web content management system: templates.
