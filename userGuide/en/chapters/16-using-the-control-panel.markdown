# Using the Control Panel [](id=lp-6-1-ugen19-using-the-control-panel-0)

In this chapter, we continue our coverage of Liferay's control panel that we began in chapter 15. We'll cover the following topics:

- Portal settings including mail host names, email notifications, identification, and display settings
- Custom fields
- Montoring
- Plugins configuration
- Server administration, including resources, log levels, properties, captcha data migration, file uploads, mail, external services, scripts, and shutdown settings
- Portal instances

Let's begin with mail host names.

### Mail Host Names [](id=lp-6-1-ugen12-mail-host-names-0)

Mail Host Names appears after Authentication and Users on the Portal Settings page of the control panel. You can enter other mail host names (one per line) besides the one you configured on the General tab. This lets the portal know which mail host names are owned by your organization.

### Email Notifications [](id=lp-6-1-ugen12-email-notifications-0)

There are five tabs under the Email Notifications page of Portal Settings. The Sender tab allows you to set the portal's administrative name and email address. By default, these are `Joe Bloggs` and `test@liferay.com`. You can change them to whatever you want. This name and email address will appear in the From field in all email messages sent by the portal.

The other four tabs are Account Created Notification, Email Verification Notification, Password Changed Notification and Password Reset Notification. These tabs allow you to customize the email messages that are sent to users each time any of those four events occur.

![Figure 16.1: Automated Emails: Account Created Notification](../../images/server-configuration-account-created-notification.png)

![Figure 16.2: Definition of Terms for Automated Emails](../../images/server-configuration-definition-of-terms.png)

A list of tokens, entitled "Definition of Terms," is provided so you can insert certain values (such as the portal URL or the user ID) when you are setting up the custom email messages.

### Identification [](id=lp-6-1-ugen12-identification-0)

The identification section has several links for addresses, phone numbers and other information you can configure in your portal. This allows you to set up contact information for the organization that owns the portal. Developers can query for this information in their applications.

### Miscellaneous: Display Settings [](id=lp-6-1-ugen12-miscellaneous-display-settings-0)

This section allows you to set the default portal language and the time zone. You can also set up a portal-wide logo which appears in the top left corners of portal pages. 

![Figure 16.3: Server Configuration Miscellaneous Display Settings](../../images/server-configuration-miscellaneous-display-settings.png)

Liferay's default theme is configured to display the portal logo. For custom themes, you can choose whether or not to display the logo. Be careful to choose an image file that fits the space. If you pick something too big, it might overlap with the navigation. Next, let's look at how to customize different types of portal assets using custom fields.

<!--  should we provide a range of recommended parameters for custom logos?  -->
## Custom Fields [](id=lp-6-1-ugen12-custom-fields-0)

Custom fields appear beneath Portal Settings in the Portal section of the control panel. Custom fields are a way to add attributes to many types of assets in the portal. For example, if you're using Liferay Portal to create a site for rating books, you might assign the User object a custom field called Favorite Books. If you're using the wiki for book reviews, you might add fields for Book Title and Book Author.

To add a custom field, click on the *Custom Fields* link in the control panel. Then choose a resource, click on the *Edit* link next to it and select *Add Custom Field*.

![Figure 16.4: Custom Fields Resource List](../../images/server-configuration-custom-fields-resource-list.png)

From here you will need to add the custom field key. The key appears as the label for the field on the form. For some portal assets (like the User), custom fields are a separate section of the form. For others, as can be seen above, custom fields are integrated with the default fields on the form. Additionally, developers can access custom fields programatically through the `<liferay-ui:custom-attribute />` tag.

![Figure 16.5: Custom Fields Integrated with the Rest of the Fields on the Wiki Form](../../images/server-configuration-custom-fields-integrated.png)

You can create fields of many different types: text fields (indexed or secret), integers, selection of multiple values and more. Once you've created a field, you cannot change its type.

## Monitoring [](id=lp-6-1-ugen12-monitoring-0)

The next link on the left side of the control panel is for monitoring. You can use the Monitoring page to view all of the live sessions in the portal. For performance reasons, this setting is usually turned off in production.

## Plugins Configuration [](id=lp-6-1-ugen12-plugins-configuration-0)

The Plugins Configuration page contains tabs for three types of plugins: portlets, themes and layouts. You can use these tabs to view which roles can add plugins to pages or you can make the plugins active or inactive.

Note that this is for basic configuration: if you want to view the existing permission configuration for a given portlet and/or modify that configuration for existing roles, this is where you can do that. If you need to add permissions to new roles for a given portlet, use the Roles section of the control panel and the *Actions &rarr; Define Permissions* button. Next, let's look at how to apply server configurations.

## Server Administration [](id=lp-6-1-ugen12-server-administration-0)

The Server Administration page of the control panel lets you perform various tasks related to the portal server itself, as opposed to the resources in the portal. Clicking the link makes this clear: you're immediately presented with a graph showing the resources available in the JVM.

### Resources [](id=lp-6-1-ugen12-resources-0)

The first tab is called *Resources* . This tab contains the aforementioned graph plus several server wide actions that an administrator can execute. These are:

**Garbage collection:** You can send in a request to the JVM to begin the garbage collection task.

**Clearing VM caches:** You can send in a request to the JVM to clear a single VM cache.

**Clearing caches across the cluster:** You can send in a request to the JVM to clear content cached across the entire cluster.

**Clearing database caches:** You can send in a request to the JVM to clear the database cache.

**Reindex all search indexes:** You can send in a request to regenerate all search indexes. If you are not using a Solr search server this will impact portal performance so try to do this at non-peak times.

**Reset Document Library preview and thumbnail files:** You can send in a request to reset the preview and thumbnail files for each item in your portal's Documents and Media libraries.

**Generate Thread Dump:** If you are performance testing, you can generate a thread dump which can be examined later to determine if there are any deadlocks and where they might be.

**Verify database tables of all plugins:** Checks all tables against their indexes for accuracy of data retrieval.

**Clean up Permissions:** This process removes the assignment of some permissions on the Guest, User and Power User roles to simplify the management of "User Customizable Pages". Notably, "Add To Page" permissions is removed from the Guest and User roles for all portlets. Likewise the same permission is reduced in scope for Power Users from portal wide to scoped to "User Personal Site."

### Log Levels [](id=lp-6-1-ugen12-log-levels-0)

The Log Levels tab of the Server Administration page allows you to dynamically modify the log levels for any class hierarchy in the portal. If you have custom code you have deployed which isn't in the list, you can use the *Add Category* tab to add it. If you change the log level near the top of the class hierarchy (such as at `com.liferay`), all the classes under that hierarchy will have their log levels changed. If you are testing something specific, it is much better to be as specific as you can when you change log levels. Modifying them too high in the hierarchy generates a lot more log messages than you need.

### Properties [](id=lp-6-1-ugen12-properties-0)

Liferay and the JVM contain many settings which are defined as properties. There are two subtabs of the properties tab of the Server Administration page: one showing system properties and one showing portal properties.

The system properties tab shows an exhaustive list of system properties for the JVM, as well as many Liferay system properties. This information can be used for debugging purposes or to check the configuration of the currently running portal.

The portal properties tab tab shows an exhaustive list of the portal properties. These properties can be customized; you can peruse the full list of customizable properties in chapter 20. If you need to check the current value of a particular property, it can be viewed from this screen without having to shut down the portal or open any properties files.

### Captcha [](id=lp-6-1-ugen12-captcha-0)

By default, Liferay ships with its own simple captcha service which is designed to thwart bots from registering for accounts on sites powered by Liferay. If you want to instead use Google's reCaptcha service, you can enable this setting from the Captcha tab of the Server Administration page.

Simply check the *Enable ReCaptcha* box and enter your public and private keys into the provided fields, then click *Save*. Liferay Portal will then use reCaptcha instead of simple captcha.

### Data Migration [](id=lp-6-1-ugen12-data-migration-0)

If you are upgrading from a previous release of Liferay Portal or if you need to migrate your data from one system to another, the Data Migration tab helps you to do that without your developers having to write custom scripts.

The first section lets you copy your entire Liferay database from the current database under which it is running to the database you specify in this set of fields. You'll need to enter the driver class name (and the driver will need to be on Liferay's classpath), the JDBC URL of the database to which you'll be copying your data and the credentials of a user with access to that database. Once you have all of this information entered, click *Execute* to copy the data.

The next section helps you migrate your documents. If you want to move off of the Jackrabbit JSR-170 repository to the file system, or to the Jackrabbit repository from the file system, or to any of the other repositories supported by the documents and media library, you can do so very easily. Make sure you have already set up your `portal-ext.properties` file so the hook is properly configured before running this migration. Select the Document Library hook that represents where you want your documents migrated and click *Execute*. Your documents will be migrated to the new repository. You can then shut down Liferay, make the new repository the default in the `portal-ext.properties` file and then restart.

Similarly, you can migrate images from the Image Gallery in the same manner.

### File Uploads [](id=lp-6-1-ugen12-file-uploads-0)

Since Liferay allows users to upload files in various places, you may want to lock down the type of files and the size of files users are allowed to upload. The File Uploads tab of the Server Configuration tab lets you set the overall maximum file size and then override that size for specific applications within Liferay. You can limit the allowed file extensions generally or by application. You have a lot of flexibility as to how you want files to be managed within your portal.

### Mail [](id=lp-6-1-ugen12-mail-0)

Rather than using the `portal-ext.properties` file as we did in the installation chapter, you can configure a mail server from the Mail tab of the Server Configuration tab. If the portal is to receive mail (see our coverage of the Message Boards portlet in chapter 7), you can connect a POP mail server. If the portal is to send mail, which it needs to do to send notifications to users, you can connect to an SMTP server here as well and this is highly recommended.

Note that if you add your mail server settings here, they will override anything in your `portal-ext.properties` file.

### External Services [](id=lp-6-1-ugen12-external-services-0)

Liferay Portal enables users to upload and share content via the Documents and Media library, a customizable and permissionable online repository. Users can upload files of any type to the Documents and Media library. Liferay ships with PDFBox and uses it to generate automatic previews for certain types of documents, by default. You can also install three additional tools that offer higher quality previews and document conversion functionality: OpenOffice or LibreOffice, ImageMagick and Xuggler. With Liferay configured to use these tools, you can generate automatic previews for many types of files incuding text files, office suite files, PDFs, images, audio files and videos. Users will also be able to use the conversion functionality to download documents in a variety of formats. Please see chapter 4 on Documents and Media for more information.

LibreOffice is available here: [LibreOffice](http://www.libreoffice.org), ImageMagick is available here: [ImageMagick](http://www.imagemagick.org) and Xuggler is available here: [Xuggler](http://xuggle.com/xuggler/). Make sure to choose the correct versions of these applications for your operating system. Once you've installed these tools, you can use the External Services tab of the control panel to configure Liferay to use them.

#### OpenOffice/LibreOffice configuration [](id=lp-6-1-ugen19-openofficelibreoffice-configuration-0)

OpenOffice and LibreOffice are open source office suites which are usually run in graphical mode to create documents but they can also be run in "server" mode. When run in server mode, OpenOffice and LibreOffice can be used to convert documents to and from all of the file types it supports. Once configured, Liferay makes use of this feature to automatically convert content on the fly. You can install OpenOffice or LibreOffice on the same machine upon which Liferay is running or you can connect to a separate host. 

If you've installed OpenOffice or LibreOffice on the same machine that's running Liferay, you can start it in server mode with the following command:

    soffice --headless --accept="socket,host=127.0.0.1,port=8100;urp;"
    --nofirststartwizard

Once OpenOffice or LibreOffice has been installed and is running in server mode, you can configure Liferay to use it either in your `portal-ext.properties` file or from the control panel. To enable OpenOffice/LibreOffice in your `portal-ext.properties` file, add the following line:

    openoffice.server.enabled=true
    
If OpenOffice or LibreOffice is running on another server or on a non-default port, you must also specify these values. The default values are as follows:

    openoffice.server.host=127.0.0.1
    openoffice.server.port=8100

By default, when Liferay uses OpenOffice or LibreOffice to perform conversions, it uses a cache. The first time a document is converted, a copy is saved in the Liferay temp folder `/liferay/document_conversion/`. When Liferay receives a conversion request, it checks this folder to see if the converted document already exists. If the converted document is found, Liferay returns it to the user. Otherwise, it performs a fresh conversion and saves a copy in the temp folder. If the cache is turned off, Liferay will always regenerate the file regardless of whether a previously existing conversion already exists in the temp folder. You can turn the cache off by setting the following property:

    openoffice.cache.enabled=false

To configure Liferay to use OpenOffice/LibreOffice from the control panel, navigate to the *Server Administration* &rarr; *External Services* page and check the *Enabled* box for OpenOffice. If OpenOffice/LibreOffice is running on a non-default port, you must also specify the port number. By default, OpenOffice runs on port 8100, which is the default port in the control panel. If you have something else running on this port, find an open port and specify it both in the command to start OpenOffice/LibreOffice in server mode and on the control panel's External Services configuration page. When you are finished, click *Save*. Now Liferay can perform many types of document conversions.

#### ImageMagick configuration [](id=lp-6-1-ugen19-imagemagick-configuration-0)

Once you've installed the correct version of [*ImageMagick*](http://www.imagemagick.org) for your operating system, which should include the installation of Ghostscript, you need to configure Liferay to use ImageMagick. You can do this either in your `portal-ext.properties` file or from the control panel. To enable ImageMagick in your `portal-ext.properties` file, add the following lines and make sure the search path points to the directories for the ImageMagick and Ghostscript executables. You may also need to configure the path for fonts used by Ghostscript when in Mac or Unix environments.

    imagemagick.enabled=true
    imagemagick.global.search.path[apple]=/opt/local/bin:/opt/local/share/ghostscript/fonts:/opt/local/share/fonts/urw-fonts
    imagemagick.global.search.path[unix]=/usr/local/bin:/usr/local/share/ghostscript/fonts:/usr/local/share/fonts/urw-fonts
    imagemagick.global.search.path[windows]=C:\\Program Files\\ImageMagick

To enable ImageMagick from the control panel, navigate to the *Server Administration* &rarr; *External Services* page, check the *Enabled* checkbox for ImageMagick and verify the paths to the ImageMagick and Ghostscript executables are correct.

#### Xuggler configuration [](id=lp-6-1-ugen19-xuggler-configuration-0)

Once you've installed the correct version of [*Xuggler*](http://www.xuggle.com/xuggler) for your operating system, you need to configure your environment variables. Depending on where you installed Xuggler, a configuration similiar to the following should work on Unix-like systems:

    export XUGGLE_HOME=/usr/local/xuggler
    export LD_LIBRARY_PATH=$XUGGLE_HOME/lib:$LD_LIBRARY_PATH
    export PATH=$XUGGLE_HOME/bin:$PATH

Once your environment variables are set up correctly, you can configure Liferay to use Xuggler either in your `portal-properties` file or from the control panel. If you'd like to use your portal-ext.properties file, just add the following line:

    xuggler.enabled=true

To configure Liferay to use Xuggler in the control panel, navigate to the *Server Administration* &rarr; *External Services* page and check *Enabled*. That's it! You've successfully configured the Documents and Media library to use Xuggler for audio and video files.

### Script [](id=lp-6-1-ugen12-script-0)

Liferay includes a scripting console which lets administrators execute migration or management code instantly. Several scripting languages are supported, including JavaScript, Groovy, Python, Ruby and Beanshell. For further information about Liferay's APIs, see the JavaDoc or [*Liferay in Action*](http://manning.com/sezov).

### Shutdown [](id=lp-6-1-ugen12-shutdown-0)

If you ever need to shut down your Liferay Portal server while users are logged in, you can use the Shutdown tab to inform your logged-in users of the impending shutdown. You can define the number of minutes until the shutdown and a custom message that will be displayed.

Users will see your message at the top of their portal pages for the duration of time you specified. When the time expires, all portal pages will display a message saying the portal has been shut down. At this point, the server will need to be restarted to restore access. Next, let's examine how to manage multiple portal instances.

## Portal Instances [](id=lp-6-1-ugen12-portal-instances-0)

Liferay Portal allows you to run more than one portal instance on a single server. The Portal Instances page of the control panel lets you manage these instances. Data for each portal instance are kept separate from every other portal instance. All portal data, however, is kept in the same database.

Each portal instance requires its own domain name. Liferay will direct users to the proper portal instance based on this domain name. So before you configure an instance, configure its domain name in your network first. When you're ready to add an instance, click the *Add* button here.

You'll be prompted for four fields and a check box:

**Web ID:** A general convention is to use the domain name for this. It's a user-generated ID for the instance.

**Virtual Host:** Put the domain name you configured in your network here. When **users** are directed to your Liferay server via this domain name, Liferay will then be able to send them to the proper portal instance.

**Mail Domain:** Enter the domain name for the mail host for this instance. Liferay will use this to send email notifications from the portal.

**Max Users:** Enter the maximum numbers of user accounts you would like your portal instance to support.

**Active:** Use this check box to choose whether to create an active or an inactive portal instance.

When you are finished filling out the form, click *Save*. Now navigate to the portal using your new domain name. You will see you are brought to what looks like a clean install of Liferay. This is your new portal instance which can now be configured any way you like.

## Plugins Installation [](id=lp-6-1-ugen12-plugins-installation-0)

The *Plugins Installation* page of the control panel shows all of the plugins currently installed. These are divided into tabs for portlets, themes, layout templates, hook plugins and web plugins. If you want to install a new plugin, click the *Install More Portlets* button. You will then be brought to the Plugin Installer, where you can browse Liferay's repository of portlets or install your own plugins. We covered the plugins installer and explained how to install plugins manually in chapter 13.

## Summary [](id=summ-33)

In this chapter, we finished our overview of Liferay's control panel that we began in chapter 15. We saw how to configure mail host names, email notifications, identification, and portal display settings. We showed how you add custom fields to various portal entities such as users, pages, documents, wiki articles, message board posts, and more. 

Next, we saw how to view and configure overall server settings. We saw how to view the memory currently being used by the server, as well as how to initiate garbage collection, a thread dump, search engine re-indexing and the clearing of various caches. We learned how to debug parts of the portal by changing log levels and by viewing the various properties defined in the portal.

Finally, we learned how to properly notify users that the portal is about to shut down and how to set up exernal services like OpenOffice integration. We looked at how to create multiple portal instances on a single installation of Liferay and we showed how to view currently installed plugins.

We hope this information helps you become an effective Liferay Portal Administrator.
