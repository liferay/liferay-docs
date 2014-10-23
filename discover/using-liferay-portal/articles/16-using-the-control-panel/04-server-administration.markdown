# Server Administration [](id=server-administration)

The Server Administration page of the control panel lets you perform various
tasks related to the portal server itself, as opposed to the resources in the
portal. Clicking the link makes this clear: you're immediately presented with a
graph showing the resources available in the JVM.

## Resources [](id=resources)

The first tab is called *Resources* . This tab contains the aforementioned graph
plus several server wide actions that an administrator can execute. These are:

**Garbage collection:** You can send in a request to the JVM to begin the
garbage collection task.

**Clearing VM caches:** You can send in a request to the JVM to clear a single
VM cache.

**Clearing caches across the cluster:** You can send in a request to the JVM to
clear content cached across the entire cluster.

**Clearing database caches:** You can send in a request to the JVM to clear the
database cache.

**Reindex all search indexes:** You can send in a request to regenerate all
search indexes. If you are not using a Solr search server this will impact
portal performance so try to do this at non-peak times.

**Reset Document Library preview and thumbnail files:** You can send in a
request to reset the preview and thumbnail files for each item in your portal's
Documents and Media libraries.

**Generate Thread Dump:** If you are performance testing, you can generate a
thread dump which can be examined later to determine if there are any deadlocks
and where they might be.

**Verify database tables of all plugins:** Checks all tables against their
indexes for accuracy of data retrieval.

**Clean up Permissions:** This process removes the assignment of some
permissions on the Guest, User and Power User roles to simplify the management
of "User Customizable Pages". Notably, "Add To Page" permissions is removed from
the Guest and User roles for all portlets. Likewise the same permission is
reduced in scope for Power Users from portal wide to scoped to "User Personal
Site."

## Log Levels [](id=log-levels)

The Log Levels tab of the Server Administration page allows you to dynamically
modify the log levels for any class hierarchy in the portal. If you have custom
code you have deployed which isn't in the list, you can use the *Add Category*
tab to add it. If you change the log level near the top of the class hierarchy
(such as at `com.liferay`), all the classes under that hierarchy will have their
log levels changed. If you are testing something specific, it is much better to
be as specific as you can when you change log levels. Modifying them too high in
the hierarchy generates a lot more log messages than you need.

## Properties [](id=properties)

Liferay and the JVM contain many settings which are defined as properties. There
are two subtabs of the properties tab of the Server Administration page: one
showing system properties and one showing portal properties.

The system properties tab shows an exhaustive list of system properties for the
JVM, as well as many Liferay system properties. This information can be used for
debugging purposes or to check the configuration of the currently running
portal.

The portal properties tab tab shows an exhaustive list of the portal properties.
These properties can be customized; you can peruse the full list of customizable
properties in chapter 20. If you need to check the current value of a particular
property, it can be viewed from this screen without having to shut down the
portal or open any properties files.

## Captcha [](id=captcha)

By default, Liferay ships with its own simple captcha service which is designed
to thwart bots from registering for accounts on sites powered by Liferay. If you
want to instead use Google's reCaptcha service, you can enable this setting from
the Captcha tab of the Server Administration page.

Simply check the *Enable ReCaptcha* box and enter your public and private keys
into the provided fields, then click *Save*. Liferay Portal will then use
reCaptcha instead of simple captcha.

## Data Migration [](id=data-migration)

If you are upgrading from a previous release of Liferay Portal or if you need to
migrate your data from one system to another, the Data Migration tab helps you
to do that without your developers having to write custom scripts.

The first section lets you copy your entire Liferay database from the current
database under which it is running to the database you specify in this set of
fields. You'll need to enter the driver class name (and the driver will need to
be on Liferay's classpath), the JDBC URL of the database to which you'll be
copying your data and the credentials of a user with access to that database.
Once you have all of this information entered, click *Execute* to copy the data.

The next section helps you migrate your documents. If you want to move off of
the Jackrabbit JSR-170 repository to the file system, or to the Jackrabbit
repository from the file system, or to any of the other repositories supported
by the documents and media library, you can do so very easily. Make sure you
have already set up your `portal-ext.properties` file so the hook is properly
configured before running this migration. Select the Document Library hook that
represents where you want your documents migrated and click *Execute*. Your
documents will be migrated to the new repository. You can then shut down
Liferay, make the new repository the default in the `portal-ext.properties` file
and then restart.

Similarly, you can migrate images from the Image Gallery in the same manner.

## File Uploads [](id=file-uploads)

Since Liferay allows users to upload files in various places, you may want to
lock down the type of files and the size of files users are allowed to upload.
The File Uploads tab of the Server Configuration tab lets you set the overall
maximum file size and then override that size for specific applications within
Liferay. You can limit the allowed file extensions generally or by application.
You have a lot of flexibility as to how you want files to be managed within your
portal.

## Mail [](id=mail)

Rather than using the `portal-ext.properties` file as we did in the installation
chapter, you can configure a mail server from the Mail tab of the Server
Configuration tab. If the portal is to receive mail (see our coverage of the
Message Boards portlet in chapter 7), you can connect a POP mail server. If the
portal is to send mail, which it needs to do to send notifications to users, you
can connect to an SMTP server here as well and this is highly recommended.

Note that if you add your mail server settings here, they will override anything
in your `portal-ext.properties` file.

## External Services [](id=external-services)

Liferay Portal enables users to upload and share content via the Documents and
Media library, a customizable and permissionable online repository. Users can
upload files of any type to the Documents and Media library. Liferay ships with
PDFBox and uses it to generate automatic previews for certain types of
documents, by default. You can also install three additional tools that offer
higher quality previews and document conversion functionality: OpenOffice or
LibreOffice, ImageMagick and Xuggler. With Liferay configured to use these
tools, you can generate automatic previews for many types of files including
text files, office suite files, PDFs, images, audio files and videos. Users will
also be able to use the conversion functionality to download documents in a
variety of formats. Please see chapter 4 on Documents and Media for more
information.

LibreOffice is available here: [LibreOffice](http://www.libreoffice.org),
ImageMagick is available here: [ImageMagick](http://www.imagemagick.org) and
Xuggler is available here: [Xuggler](http://xuggle.com/xuggler/). Make sure to
choose the correct versions of these applications for your operating system. You
can build Xuggler 3.4.1012, which works with Liferay 6.1, from source or you can
install it from the binaries:

- [Source](http://com.xuggle.s3.amazonaws.com/xuggler/xuggler-3.4.FINAL/xuggle-xuggler.3.4.1012-src.tar.gz)

- [Linux
  (32-bit)](http://com.xuggle.s3.amazonaws.com/xuggler/xuggler-3.4.FINAL/xuggle-xuggler.3.4.1012-i686-pc-linux-gnu.sh)

- [Linux
  (64-bit)](http://com.xuggle.s3.amazonaws.com/xuggler/xuggler-3.4.FINAL/xuggle-xuggler.3.4.1012-x86_64-unknown-linux-gnu.sh)

- [Mac OS
  X](http://com.xuggle.s3.amazonaws.com/xuggler/xuggler-3.4.FINAL/xuggle-xuggler.3.4.1012-i386-apple-darwin9.8.0.sh)

- [Windows](http://com.xuggle.s3.amazonaws.com/xuggler/xuggler-3.4.FINAL/xuggle-xuggler.3.4.1012-win32-setup.exe)

Once you've installed these tools, you can use the External Services tab of the
control panel to configure Liferay to use them.

### OpenOffice/LibreOffice Configuration [](id=openoffice-libreoffice-configuration)

OpenOffice and LibreOffice are open source office suites which are usually run
in graphical mode to create documents but they can also be run in "server" mode.
When run in server mode, OpenOffice and LibreOffice can be used to convert
documents to and from all of the file types it supports. Once configured,
Liferay makes use of this feature to automatically convert content on the fly.
You can install OpenOffice or LibreOffice on the same machine upon which Liferay
is running or you can connect to a separate host. 

If you've installed OpenOffice or LibreOffice on the same machine that's running
Liferay, you can start it in server mode with the following command:

    soffice --headless --accept="socket,host=127.0.0.1,port=8100;urp;"
    --nofirststartwizard

Once OpenOffice or LibreOffice has been installed and is running in server mode,
you can configure Liferay to use it either in your `portal-ext.properties` file
or from the control panel. To enable OpenOffice/LibreOffice in your
`portal-ext.properties` file, add the following line:

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

To configure Liferay to use OpenOffice/LibreOffice from the control panel,
navigate to the *Server Administration* &rarr; *External Services* page and
check the *Enabled* box for OpenOffice. If OpenOffice/LibreOffice is running on
a non-default port, you must also specify the port number. By default,
OpenOffice runs on port 8100, which is the default port in the control panel. If
you have something else running on this port, find an open port and specify it
both in the command to start OpenOffice/LibreOffice in server mode and on the
control panel's External Services configuration page. When you are finished,
click *Save*. Now Liferay can perform many types of document conversions.

### ImageMagick Configuration [](id=imagemagick-configuration)

Once you've installed the correct version of
[*ImageMagick*](http://www.imagemagick.org) for your operating system, which
should include the installation of Ghostscript, you need to configure Liferay to
use ImageMagick. You can do this either in your `portal-ext.properties` file or
from the control panel. To enable ImageMagick in your `portal-ext.properties`
file, add the following lines and make sure the search path points to the
directories for the ImageMagick and Ghostscript executables. You may also need
to configure the path for fonts used by Ghostscript when in Mac or Unix
environments.

    imagemagick.enabled=true
    imagemagick.global.search.path[apple]=/opt/local/bin:/opt/local/share/ghostscript/fonts:/opt/local/share/fonts/urw-fonts
    imagemagick.global.search.path[unix]=/usr/local/bin:/usr/local/share/ghostscript/fonts:/usr/local/share/fonts/urw-fonts
    imagemagick.global.search.path[windows]=C:\\Program Files\\ImageMagick

To enable ImageMagick from the control panel, navigate to the *Server
Administration* &rarr; *External Services* page, check the *Enabled* checkbox
for ImageMagick and verify the paths to the ImageMagick and Ghostscript
executables are correct.

Note that some older versions of ImageMagick are unable to properly run with
Liferay. If this is the case, update to the latest version (ImageMagick 6.7.9-6
2012-09-25 Q16 or later). To check for the latest ImageMagick versions, visit
[http://www.imagemagick.org/script/binary-releases.php](http://www.imagemagick.org/script/binary-releases.php).
See
[http://issues.liferay.com/browse/LPS-30291](http://issues.liferay.com/browse/LPS-30291)
for information on efforts to identify incompatible application versions with
Liferay.

### Xuggler Configuration [](id=xuggler-configuration)

Once you've installed the correct version of
[*Xuggler*](http://www.xuggle.com/xuggler) for your operating system, you need
to configure your environment variables. Depending on where you installed
Xuggler, a configuration similar to the following should work on Unix-like
systems:

    export XUGGLE_HOME=/usr/local/xuggler
    export LD_LIBRARY_PATH=$XUGGLE_HOME/lib:$LD_LIBRARY_PATH
    export PATH=$XUGGLE_HOME/bin:$PATH

Once your environment variables are set up correctly, you can configure Liferay
to use Xuggler either in your `portal-properties` file or from the control
panel. If you'd like to use your portal-ext.properties file, just add the
following line:

    xuggler.enabled=true

To configure Liferay to use Xuggler in the control panel, navigate to the
*Server Administration* &rarr; *External Services* page and check *Enabled*.
That's it! You've successfully configured the Documents and Media library to use
Xuggler for audio and video files.

## Script [](id=script)

Liferay includes a scripting console which lets administrators execute migration
or management code instantly. Several scripting languages are supported,
including JavaScript, Groovy, Python, Ruby and Beanshell. For further
information about Liferay's APIs, see the JavaDoc or [*Liferay in
Action*](http://manning.com/sezov).

## Shutdown [](id=shutdown)

If you ever need to shut down your Liferay Portal server while users are logged
in, you can use the Shutdown tab to inform your logged-in users of the impending
shutdown. You can define the number of minutes until the shutdown and a custom
message that will be displayed.

Users will see your message at the top of their portal pages for the duration of
time you specified. When the time expires, all portal pages will display a
message saying the portal has been shut down. At this point, the server will
need to be restarted to restore access. Next, let's examine how to manage
multiple portal instances.
