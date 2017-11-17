# Server Administration [](id=server-administration)

The Server Administration application (accessed by clicking *Control Panel
&rarr; Configuration &rarr; Server Administration*) lets you perform tasks
related to the @product@ server. Clicking the link displays a graph showing the 
resources available in the JVM.

![Figure 1: Server Administration is where you configure and monitor many aspects of
the server.](../../../images/server-admin-resources.png)

+$$$

**Note:** Liferay DXP customers can use Liferay Connected Services (LCS) to
obtain additional performance metrics. See the [LCS
documentation](/discover/deployment/-/knowledge_base/7-0/managing-liferay-with-liferay-connected-services) for more information.

$$$

The Server Administration functionality is broken into several tabs:

- [Resources](#resources)
- [Log Levels](#log-levels)
- [Properties](#properties)
- [CAPTCHA](#captcha)
- [Data Migration](#data-migration)
- [File Uploads](#file-uploads)
- [Mail](#mail)
- [External Services](#external-services)
- [Script](#script)
- [Shutdown](#shutdown)

## Resources [](id=resources)

The Resources tab contains several server wide actions that an administrator can
execute. These include the following:

**Run the garbage collector**
: Takes out the trash.

**Generate a thread dump**
: Generates a thread dump for later scrutiny to determine the presence 
and locations of any deadlocks. Useful during performance testing, but note
that you need to add a logger category for
`com.liferay.server.admin.web.internal.portlet.action.EditServerMVCActionCommand`
and set it to `INFO` before executing.

**Clear content cached by this VM**
: Clears content stored in the local cache. Only local JVM scope Ehcache content 
is cleared, not clustered Ehcache.

**Clear content cached across the cluster**
: Clears the content of the entire clustered Ehcache.

**Clear the database cache**
: Clears the database cache. Does not clear any Ehcache content except database 
results at the persistence layer.

**Clear the direct servlet cache**
: Clears the direct servlet cache. In case emergency fixes need to be applied, 
this action allows an administrator to manually clear out the cache to force 
JSPs to reload.

The direct servlet context optimizes JSP serving performance by caching and 
accessing the generated servlets directly instead of accessing them over the 
application server's dispatcher chain. This function is only suitable for cases 
where no filter is required for the JSPs; it should be enabled for production 
mode to improve performance, but disabled for development mode to allow JSP 
servlets to be reloaded on the fly. See the Direct Servlet Context section of 
the `portal.properties` file for details.

**Reindex all search indexes**
: Regenerates all search indexes. If you are not using a Solr search server 
this will impact portal performance so try to do this at non-peak times.

**Reindex all spell check indexes**
: Regenerates all spell check indexes.

**Reindex com.liferay...SomeEntity**
: Reindexes a single entity.

**Verify database tables of all plugins**
: Checks all tables against their indexes for data retrieval accuracy.

**Verify Membership Policies**
: Checks that existing site membership policies have been correctly applied and 
automatically makes updates. In the event that the Liferay database is changed 
manually or is hacked--resulting in a user being assigned to a site in violation 
of a site membership policy--this action triggers the verification methods of 
all implemented site membership policies. Changes are automatically made to any 
memberships in violation.

**Reset preview and thumbnail files for the Documents and Media portlet**
: Applies to each item in your portal's Documents and Media 
libraries.

**Clean up permissions**
: Removes permissions on the Guest, User,and Power User roles to simplify the 
management of "User Customizable Pages". The "Add To Page" permissions is 
removed from the Guest and User roles for all portlets, and is reduced in 
scope for PowerUsers from portal-wide to "User Personal Site."

**Clean up portlet preferences**
: This actions cleans up database entries in the event that porlet preferences 
become orphaned in the @product@ database.

## Log Levels [](id=log-levels)

The Log Levels tab of the Server Administration page allows for dynamic
modifications of log levels for any class hierarchy in the portal. Custom
code not on the list can be added with the *Add Category* tab. 
Changes to the log level near the top of the class hierarchy (such as at
`com.liferay`) will also change log levels for all the classes under that 
hierarchy. Modifications unnecessarily high in the hierarchy
generate too many messages to be useful.

## Properties [](id=properties)

Liferay and the JVM contain many settings which are defined as properties. There
are two subtabs of the properties tab of the Server Administration page: System
Properties and Portal Properties.

The system properties tab shows an exhaustive list of system properties for the
JVM, as well as many Liferay system properties. This information can be used for
debugging purposes or to check the configuration of the currently running
portal.

The portal properties tab tab shows an exhaustive list of the current portal
property values, so you don't have to shut down @product@ or open the properties
file directly. Portal properties are customizable; you can peruse the full list
of available properties at
[ttps://docs.liferay.com/portal/7.0-latest/propertiesdoc/portal.properties.html](https://docs.liferay.com/portal/7.0-latest/propertiesdoc/portal.properties.html).

## CAPTCHA [](id=captcha)

By default, @product@ ships with its own CAPTCHA service which is designed to
thwart bots from registering for accounts on sites powered by @product@. 

Alternatively, Google's reCAPTCHA service can be enabled from the CAPTCHA tab 
of Server Administration. Check the *Enable ReCAPTCHA* box and enter your 
public and private keys into the provided fields, then click *Save*. @product@ 
will then use reCAPTCHA instead of the native CAPTCHA solution.

## Data Migration [](id=data-migration)

The Data Migration tab allows users to upgrade @product@ or migrate its data 
from one system to another without writing custom scripts.

The first section, under *Migrate data from one database to another.* was useful
in past versions of @product@ for copying the entire @product@ database to a new
database. However, the database migration tool cannot be used in the current
version of @product@ and will be removed in the next version of @product@.

The second section, *Migrate documents from one repository to another.*, helps
you migrate your documents to a new repository on a different disk or in a new 
format. Before running this operation,
[configure the repository
hook](/discover/deployment/-/knowledge_base/7-0/document-repository-configuration)
in a `portal-ext.properties` file. Then, in Server Administration, select the
Document Library hook that represents the desired destination and 
click *Execute*. Shut down @product@ and make the new repository the default in 
the `portal-ext.properties` file, then restart.

## File Uploads [](id=file-uploads)

Since Liferay allows users to upload files in various places, you may want to
restrict the type and size of files users are allowed to upload. The File 
Uploads tab of the Server Configuration tab lets you set the overall
maximum file size and then grant exceptions to that limit for specific 
applications. You can also limit the allowed file extensions generally or by 
application.

## Mail [](id=mail)

Instead of using a `portal-ext.properties` file to configure a mail server, you
can configure a mail server from the Mail tab of the Server Configuration
section of the Control Panel. If your portal is to receive mail, you can connect
a POP mail server. If your portal is to send mail, which is useful for sending
notifications to users, you can connect to an SMTP server. We highly recommend
setting up mail servers for your portal.

Note that if you configure mail server settings here in the Control Panel, these
settings will override any mail server settings in your `portal-ext.properties`
file.

## External Services [](id=external-services)

Liferay Portal enables users to upload and share content via the Documents and
Media library, a customizable and permissions-enabled online repository. Users
can upload files of any type to the Documents and Media library. Liferay ships
with PDFBox for generating automatic previews for certain types of documents.
You can also install three additional tools that offer higher quality previews
and document conversion functionality: OpenOffice or LibreOffice, ImageMagick
and Xuggler. With Liferay configured to use these tools, you can generate
automatic previews for many types of files including text files, office suite
files, PDFs, images, audio files and videos. Users will also be able to use the
conversion functionality to download documents in a variety of formats. Please
see the [article on publishing files in
@product@](/discover/portal/-/knowledge_base/7-0/publishing-files).

LibreOffice is available here: [LibreOffice](http://www.libreoffice.org),
ImageMagick is available here: [ImageMagick](http://www.imagemagick.org), and
Xuggler is available here: [Xuggler](http://xuggle.com/xuggler/). Make sure to
choose the correct versions of these applications for your operating system. We
recommend that you install the latest stable versions. LibreOffice and
ImageMagick must be installed manually, but you can install Xuggler from
the Control Panel.

+$$$

**Tip:** If you're running Liferay on a Linux server and experience a problem
enabling Xuggler, check your server's glibc version. You might have to update
glibc to version 2.6 or later in order for Xuggler to work.

$$$

Once you've installed these tools, you can use the External Services tab of the
Control Panel to configure Liferay to use them.

### OpenOffice/LibreOffice configuration [](id=openoffice-libreoffice-configuration)

OpenOffice and LibreOffice are open source office suites which are usually run
in graphical mode to create documents but they can also be run in "server" mode.
When run in server mode, OpenOffice and LibreOffice can be used to convert
documents to and from all of the file types it supports. Once configured,
Liferay makes use of this feature to automatically convert content on the fly.
You can install OpenOffice or LibreOffice on the same machine where Liferay
is running or you can connect to a separate host. 

If you've installed OpenOffice or LibreOffice on the same machine as Liferay, 
you can start it in server mode with the following command:

    soffice --headless --accept="socket,host=127.0.0.1,port=8100;urp;"
    --nofirststartwizard

Once OpenOffice or LibreOffice is installed and running in server mode,
configure Liferay to use it either in `portal-ext.properties` or from the
Control Panel. To enable OpenOffice/LibreOffice in your `portal-ext.properties`
file, add the following line:

    openoffice.server.enabled=true

If OpenOffice or LibreOffice is running on another server or on a non-default
port, you must also specify these values. The default values are as follows:

    openoffice.server.host=127.0.0.1
    openoffice.server.port=8100

By default, when Liferay uses OpenOffice or LibreOffice to perform conversions,
it uses a cache. The first time a document is converted, a copy is saved in the
Liferay temp folder `/liferay/document_conversion/`. When Liferay receives a
conversion request, it checks this folder to see if the converted document
already exists. If the converted document is found, Liferay returns it to the
user. Otherwise, it performs a fresh conversion and saves a copy in the temp
folder. If the cache is turned off, Liferay will always regenerate the file
regardless of whether a previously existing conversion already exists in the
temp folder. You can turn the cache off by setting the following property:

    openoffice.cache.enabled=false

To configure Liferay to use OpenOffice/LibreOffice from the Control Panel,
navigate to the *Server Administration* &rarr; *External Services* page and
check the *Enabled* box for OpenOffice. If OpenOffice/LibreOffice is running on
a non-default port--the default is port 8100--you must also specify the port 
number.  If you have something else running on this port, find an open port and 
specify it both in the command to start OpenOffice/LibreOffice in server mode 
and on the Control Panel's External Services configuration page. When you are 
finished,click *Save*. Now Liferay can perform many types of document 
conversions.

### ImageMagick configuration [](id=imagemagick-configuration)

Before configuring ImageMagick to generate image and PDF previews in @product@,
install it and its dependency, Ghostscript. This differs by operating system: on
Linux, both are likely already installed. They are not likely to be installed 
on Windows but may be on Macs.

1.  Download and install [*ImageMagick*](http://www.imagemagick.org).

2.  Download and install [*Ghostscript*](http://www.ghostscript.com).

Once installed, enable ImageMagick through the Control Panel or a
`portal-ext.properties` file. If using `portal-ext.properties`, add the
following lines and make sure the search path points to the directories
containing the ImageMagick and Ghostscript executables. You may also need to
configure the path for fonts used by Ghostscript when in Mac or Unix
environments.

    imagemagick.enabled=true
    imagemagick.global.search.path[apple]=/opt/local/bin:/opt/local/share/ghostscript/fonts:/opt/local/share/fonts/urw-fonts
    imagemagick.global.search.path[unix]=/usr/local/bin:/usr/local/share/ghostscript/fonts:/usr/local/share/fonts/urw-fonts
    imagemagick.global.search.path[windows]=C:\\Program Files\\ImageMagick

To enable ImageMagick from the Control Panel:

1.  Navigate to the *Server Administration* &rarr; *External Services* page. 

2.  Check *Enabled* for ImageMagick and verify the paths to the 
    ImageMagick and Ghostscript executables are correct.

Some older versions of ImageMagick don't run properly with Liferay. If
this is the case, update to the latest version (ImageMagick 6.7.9-6 2012-09-25
Q16 or later). To check for the latest ImageMagick versions, visit
[ImageMagick's website](http://www.imagemagick.org/script/binary-releases.php).
See [LPS-30291](http://issues.liferay.com/browse/LPS-30291) for information on
efforts to identify incompatible application versions with Liferay.

### Xuggler configuration [](id=xuggler-configuration)

From the control panel, navigate to the *Server Administration* &rarr; 
*External Services* page. From the dropdown, select the Xuggler `.jar` file that 
matches your operating system. Then click on *Install*. 

After installing, restart your application server and enable Xuggler for your 
portal. You can enable Xuggler either from the Control Panel or by specifying 
the portal property that enables Xuggler. 

To enable Xuggler from portal properties, add the following line to your
`portal-ext.properties` file and restart your application server. 

    xuggler.enabled=true

To enable Xuggler from the Control Panel, navigate to the *Server
Administration* &rarr; *External Services* page, check *Enabled*, and click
on *Save*.

That's it! You've successfully configured Documents and Media to use Xuggler for
audio and video files. 

## Script [](id=script)

The Script tab includes a scripting console for executing migration or
management code. The Groovy scripting language is supported out of the box. For
more information, see the [Scripting
article](/discover/portal/-/knowledge_base/7-0/using-liferays-script-engine) for
more information on how to use the scripting console and examples.

## Shutdown [](id=shutdown)

If you ever need to shut down your Liferay Portal server while users are logged
in, you can use the Shutdown tab to inform your logged-in users of the impending
shutdown. You can define the number of minutes until the shutdown and a custom
message to display.

Users will see your message at the top of their portal pages for the duration of
time you specified. When the time expires, all portal pages will display a
message saying the portal has been shut down. At this point, the server will
need to be restarted to restore access. Next, let's examine how to manage
multiple portal instances.
