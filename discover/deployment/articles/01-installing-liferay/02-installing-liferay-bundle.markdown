# Installing @product@

Now that you've performed the steps needed to prepare for your installation,
you're ready to install @product@! Since bundles are the easiest way to complete
an installation, all the installation steps below assume a bundle has been
installed. If you plan to install @product@ manually, please refer to the
article for your app server of choice, and then come back here to complete the
configuration steps. 

Now you're ready. You've created a blank database for @product@ and have
gathered the credentials you need for your mail server. The next step is to
install @product@. 

## Liferay Home

Liferay bundles contain the same folder structure regardless of application
server. The top-level folder is named for the release of Liferay. This folder
is called *Liferay Home*. This folder is usually the parent folder of the
application server's folder. This is why Liferay bundles place the application
server inside the bundle's root folder. On a manual installation, the location
of this folder varies by application server. In a bundle, it's part of the
bundle. If you're doing a manual installation, please refer to the article
covering that app server for its location. 

In the Liferay Home folder there are folders for various purposes:

    - [Liferay Home]
        - `data`
        - `deploy`
        - `logs`
        - `osgi`
        - [Application Server]

`data`: This folder is used to store an embedded HSQL database, Liferay's
file repository, and Liferay's search indexes. Liferay is initially configured
to use the embedded HSQL database but the HSQL database is primarily intended
for demonstration and trial purposes. 

`deploy`: To deploy Liferay plugins, copy them to this folder. Legacy style
`.war` files, Liferay 7 style `.jar` files, and `.lpkg` files from Liferay
Marketplace are supported.

`logs`: This folder contains Liferay's log files. The information in Liferay's
log files can be quite valuable for system administrators, especially when
trying to diagnose a problem.

`osgi`: All the JAR files and a few configuration files for Liferay's OSGi
runtime belong in this folder.

**[Application Server]:** The name of this folder is different depending on
the bundle you're using. This folder contains the application server in
which Liferay has been installed.

If Liferay is unable to create the resources it needs in the Liferay Home
folder or if it finds itself running on certain application servers, it creates
a folder called `liferay` in the home folder of the operating system user that
is running Liferay. In this case, the `liferay` folder becomes Liferay Home.
For example, if the operating system user's name was jbloggs, the Liferay Home
folder could be `/home/bloggs/liferay` or `C:\Users\jbloggs\liferay`.

## Extracting a Liferay Bundle 

Getting a Liferay bundle up and running is as easy as uncompressing the
archive, possibly copying a JDBC driver, and then starting the application
server. Let's use the Liferay Tomcat bundle as an example.

1. Unzip your Liferay bundle.

2. If you're setting up Liferay to be an actual server, copy your database's
   JDBC driver `.jar` file to `[Tomcat]/lib/ext`. If you're using a supported
   open source database or if you're setting up Liferay for demo purposes, you
   can skip this step.

That's it! You've extracted @product@, and it's ready for use. This is much
easier than doing a manual installation on an app server. If, however, that's
what you need to do, please at this point click the link on the left and go
through the installation procedure for your app server of choice. When you're
finished with the installation (and before you've started @product@ for the
first time), come back to this spot, because you need to hook it up to your
database. 

## Connecting @product@ to Your Database

You can connect Liferay through either your app server's data source or the one
that ships with Liferay. Because of its suitability for tuning, it is
recommended that you use the data source that ships with @product@. To do this,
you'll create a configuration file called `portal-ext.properties`, and you'll
place that file in your Liferay Home folder. 

The configuration varies by database, of course, so templates for each one are
provided in the [reference section](/develop/reference/-/knowledge_base/7-0/databse-templates). 
To connect your database, therefore, create a text file called
`portal-ext.properties` in your Liferay Home folder. Copy the relevant template
for your database and paste it into this file. 

Now all you have to do is customize it. Enter the proper host name and user and
password credentials for your database, and then save the file. 

## Running @product@ for the First Time

Next, start your app server, or start the Liferay app in your app server. For
example, if you're using the Liferay-Tomcat bundle, start Tomcat as if you had
downloaded it manually. Tomcat is launched by invoking a script which is found
in its `bin` folder. If you open a command prompt or terminal and go to this
folder, you can launch Tomcat via the following command on Windows:

        startup

    or the following command on Linux/Mac/Unix:

        ./startup.sh

The Liferay Tomcat bundle then starts. If you are on Windows, another command
prompt window appears with Tomcat's console in it. If you are on Linux, you can
see the Tomcat console by issuing the following command:

    tail -f ../logs/catalina.out

The first time Liferay starts, it'll take a while to create all of its database
tables. Once it has successfully started, it automatically launches a web browser that
displays Liferay's Basic Configuration page. If for some reason your browser
doesn't load the Basic Configuration page, launch your web browser and navigate
to [http://localhost:8080](http://localhost:8080). 

# Using Liferay's Setup Wizard [](id=using-liferays-setup-wizard)

Liferay's Setup Wizard runs when you start Liferay for the first time. To make
it easy to configure Liferay, the first thing you see when browsing to your
newly installed Liferay bundle is a setup wizard. The title of the setup wizard
page is Basic Configuration. This page provides a convenient way to make an
initial Liferay configuration.

There are two sections of the wizard: the portal and the administrator.  For the
portal, you need to supply the following information:

**Portal Name:** the name of the portal you're powering with Liferay.

**Default Language:** choose the default locale of your portal.

For the administrator, you need to supply the following information:

**First Name:** the first name of the default administrator user

**Last Name:** the last name of the default administrator user

**Email:** the email address of the default administrator user

![Figure 1: Supply the information for your portal and your portal's default administrator user on the Basic Configuration page.](../../images/basic-configuration1.png)

The Basic Configuration page also includes a checkbox labeled *Add Sample
Data*. If you check this box, sample data is added to Liferay's database. This
data includes users, sites, and organizations. The sample data allows many
Liferay features to be showcased. If you're installing Liferay on your own
machine to explore its features, the sample data will probably be useful. If,
however, you're installing Liferay on a real server, you should start with a
clean system.

Once you've filled out the form, click *Finish Configuration*. The setup wizard
creates a `portal-setup-wizard.properties` file which stores the settings that
you entered. When you begin customizing your portal's configuration, however,
you should use the `portal-ext.properties` file you created earlier. All the
possible properties that can be placed in this file are documented in [our
reference documentation](http://docs.liferay.com/portal/7.0/propertiesdoc).

+$$$

**Tip:** The wizard is an extremely helpful tool, especially if you're setting
up Liferay for the first time. If you're a Liferay veteran and you already have
your various properties set up, you can disable the setup wizard. If you disable
the setup wizard, you must configure everything manually from the 
`portal-ext.properties` file. To disable the setup wizard, enter
`setup.wizard.enabled=false` in your `portal-ext.properties` file. Note that
property values in `portal-setup-wizard.properties` (the file created in Liferay
Home by the setup wizard) override property values in `portal-ext.properties`.

$$$

After you've entered the information requested by the Basic Configuration page,
Liferay should bring you to its home page. You should set up your mail
configuration next. 

## Configuring Mail 

Now that @product@ is up and running, log in as the administrative user you
created in the setup wizard. Click the menu icon and then go to Control Panel
&rarr; Server Administration &rarr; Mail, and have your mail credentials ready. 

Fill out the form. You're asked for the following information: 

**Incoming POP Server:** The hostname for a server running the Post Office
Protocol. Liferay checks this mailbox for incoming messages, such as message
board replies. 

**Incoming Port:** The port on which the POP server is listening. 

**Use a Secure Network Connection:** Use an encrypted connection when connecting
to the POP server. 

**User Name:** The user ID Liferay should use to log into the POP server. 

**Password:** The password Liferay should use to log into the POP server. 

**Outgoing SMTP Server:** The hostname for a server running the Simple Mail
Transfer Protocol. Liferay uses this server to send emails, such as password
change emails and other notifications. 

**Outgoing Port:** The port on which the SMTP server is listening. 

**Use a Secure Network Connection:** Use an encrypted connection when connecting
to the SMTP server. 

**User Name:** The user ID Liferay should use to log into the SMTP server. 

**Password:** The password Liferay should use to log into the SMTP server. 

**Manually specify additional JavaMail properties to override the above
configuration:** If there are additional properties you need to specify, supply
them here. 

When you're finished setting up your mail configuration, click *Save*. 

Your final step for basic @product@ configuration is to convert the search
implementation from its default demo mode into a production-ready mode. 

## Configuring Elastic Search 

Liferay uses Elasticsearch to index its content. By default, Liferay uses
Elastic as an embedded service. It works, but this is not a supported
configuration for a production server. Feel free to use it while you're testing
or developing, but when you're ready to put your site in production, you'll need
to run Elasticsearch as a standalone process. This is better anyway, because it
frees you to design your infrastructure the way you want it. If you've got
hardware or a VM to spare, you can separate your search infrastructure from
Liferay and reap some performance gains by putting search on a separate box. If
you're more budget-conscious, you can still increase performance by running
Elastic in a separate, individually tunable JVM on the same box. 

Installing Elasticsearch for Liferay is pretty easy and takes only five steps: 

1. Find the version of Elasticsearch that's embedded in the version of Liferay
   you have, and then download that version from [Elastic's](https://www.elastic.co) 
   website. 

2. Install Elasticsearch by extracting its archive to the system where you want
   it to run. 

3. Name your Elastic cluster. 

4. Configure Liferay to connect to your Elastic cluster. 

5. Restart Liferay and reindex your search indexes. 

Now you'll actually perform these steps, and when you're done, you'll have a
production-ready instance of @product@ up and running. 

### Step One: Find the Right Version of Elasticsearch

If Liferay isn't running, start it. Then, using a browser, visit port 9200 of
the machine on which Liferay's running. For example, if you're browsing from the same machine
Liferay's running on, visit this URL: 

    http://localhost:9200

A JSON document is returned that varies slightly, but should look similar to
this: 

    {
      "name" : "Wiz Kid",
      "cluster_name" : "LiferayElasticsearchCluster",
      "version" : {
        "number" : "2.2.0",
        "build_hash" : "8ff36d139e16f8720f2947ef62c8167a888992fe",
        "build_timestamp" : "2016-01-27T13:32:39Z",
        "build_snapshot" : false,
        "lucene_version" : "5.4.1"
      },
      "tagline" : "You Know, for Search"
    }

The version of Elasticsearch that you want is the value of the `"number"` field.
In this example, it's 2.2.0. 

Now that you know the version of Elasticsearch you need, go to
[Elastic's](https://www.elastic.co) website and download that version. 

### Step Two: Install Elasticsearch

Most of this step entails deciding where you want to run Elasticsearch. Do you
want to run it on the same machine as Liferay, or do you want to run it on its
own hardware? The answer to this question comes down to a combination of the
resources you have available and the size of your installation. Regardless of
what you decide, either way you get the benefit of a separately tunable search
infrastructure. 

Once you have a copy of the right version of Elasticsearch, extract it to a
folder on the machine where you want it running. That's all there is to this
step. 

### Step Three: Name Your Elastic Cluster

A *cluster* in Elasticsearch is a collection of nodes (servers) identified as a
cluster by a shared cluster name. The nodes work together to share data and
workload. A one node cluster is discussed here; to create a multi-node cluster,
please refer to [Elastic's documentation](https://www.elastic.co/guide/index.html). 

Now that you've installed Elastic, it sits in a folder on your machine, which is
referred to here as `[Elasticsearch Home]`. To name your cluster, you'll define
the cluster name in both Elasticsearch and in Liferay. First, define it in
Elastic. Edit the following file: 

    [Elasticsearch Home]/config/elasticsearch.yml

Uncomment the line that begins with `cluster.name`. Set the cluster name to
whatever you want to name your cluster: 

    cluster.name: liferay_cluster

Of course, this isn't a very imaginative name; you may choose to name your
cluster `finders_keepers` or something else you can remember more easily. Save
the file. 

Now you can start Elasticsearch. Run the executable for your operating system
from the `[Elasticsearch Home]/bin` folder: 

    ./elasticsearch

Elastic starts, and one of its status messages includes a transport address: 

    2016-05-03 16:33:28,358][INFO ][transport] [Hobgoblin II] publish_address {127.0.0.1:9300}, bound_addresses {[::1]:9300}, {127.0.0.1:9300}

Take note of this address; you'll need to give it to your Liferay server so it
can find Elastic on the network. 

### Step Four: Configure Liferay to Connect to your Elastic Cluster

Now you're ready to configure Liferay. Start Liferay if you haven't already, log
in, and then go to Control Panel &rarr; Configuration &rarr; System Settings
&rarr; Foundation. Find *Elasticsearch* in the list of settings and click on it.
Now you can configure it. Here are the options you need to change: 

**Cluster name:** Enter the name of the cluster as you defined it in Elastic. 

**Operation mode:** Defaults to EMBEDDED. Change it to REMOTE to connect to a
standalone Elasticsearch. 

**Transport addresses:** Enter a delimited list of transport addresses for
Elastic nodes. Here, you'll enter the transport address from the Elastic server
you started. 

When finished, click *Save*. You're almost done. 

### Step Five: Restart Liferay and Reindex

Stop and restart Liferay. When it's back up, log in as an administrative user
and go to Control Panel &rarr; Configuration &rarr; Server Administration and
click the *Execute* button for *Reindex all search indexes*. When you do that,
you should see some messages scroll up in the Elasticsearch log. 

You're almost done! The only thing left is to make sure Marketplace is working
and optionally configure portal security. 

## Configuring Liferay Marketplace

Liferay Marketplace is more than just a store for Liferay applications. Under
the hood, it not only provides the store, it also provides @product@'s
application deployment features. For this reason, you must ensure that
Marketplace works well on your installed system. The first thing you should do
is make sure Marketplace can run and configure itself. The second thing you
should do is enable Liferay's Portal Access Control List, or PACL. 

First, you'll learn about some scenarios in which Marketplace fails to run, but
they can all be worked around. Next, you'll configure PACL. 

### Server is Firewalled without Access to the Internet [](id=server-is-firewalled-without-access-to-the-internet)

Your server might be behind a firewall that prevents access to the Internet. Or
your security policy might not allow direct download and installation from the
Internet. In these cases, you have two options:

1. From an Internet-enabled computer, download the Marketplace plugin from
   [https://www.liferay.com/marketplace/download](https://www.liferay.com/marketplace/download).
   Then allow Liferay to auto deploy it by dropping the downloaded `.lpkg` file
   into the Liferay deploy folder.

2. From an Internet-enabled computer, download the Marketplace plugin. Then use
   the Liferay App Manager to deploy the plugin.

### Application Server Does Not Support Hot Deploy [](id=application-server-does-not-support-hot-deploy)

If your application server does not support hot deploy, you can't leverage
Liferay's auto deploy feature. You can, however, manually deploy the plugin in
two steps:

1. Use Liferay's tools to pre-deploy the file.

2. Then use your app server's tools to do the actual deployment.

### Limited Database Access [](id=limited-database-access)

Some production environments do not have the necessary database permissions for
Liferay and its plugins to maintain their tables. In these cases:

1. Grant the Liferay database user temporary full rights to the database.

2. Install Liferay and start it so that it populates its database.

3. Once the database is created, remove the permissions for creating tables and
   dropping tables from the Liferay database user.

See the Setting Up Liferay's Database with Restrictive Permissions section
above for more information. Note that many sophisticated Liferay apps--not just
the Marketplace plugin--require new tables when deployed. If your environment
restricts database access, you may need to repeat the above steps whenever you
deploy a new app to the Liferay Portal.

## Configuring Liferay Portal Security [](id=configuring-liferay-portal-security)

Liferay Marketplace is an online store for obtaining applications that run on
the Liferay Portal platform. These applications are provided not only by
Liferay, but also by partners and independent developers who want you to
install and use their applications on your server. Many of these applications
are excellent and we recommend that you try them out for yourself.

However, because many of the applications on Marketplace are *not* provided by
Liferay, a question arises: how do you know these applications are doing what
they're advertised to do? There is a vetting process that they go through
before they're allowed on Marketplace, but if the source code is not provided,
there's no way for even Liferay to know if an app has been properly
represented. For this reason, Liferay Portal implements a security feature
known as the Portal Access Control List, or PACL.

PACL forces an application to declare up front the functions from Liferay's
APIs that it calls. Anything that's not declared is not allowed to run. It's
similar to what you might see on your mobile phone when you install an app: you
get to see the Liferay API functions the app uses, and then you can decide if
you want to install that app based on the permissions it requires. This way,
you see right away what portal data that app can access and the app can do
nothing else: you're protected--if you have PACL enabled. So if you plan to use
apps downloaded from Marketplace, it's important to make sure PACL is enabled.

By default, Liferay's bundles have PACL turned off. The reason for this is that
there is a small performance penalty for having PACL enabled. Since the only
reason to have PACL enabled is to install untrusted third party apps from
Marketplace (and not everybody does that), we decided to leave PACL turned off
by default. This way, your portal performs as fast as possible.

The bottom is line that if you intend to use Marketplace apps, you should
enable PACL. We provide manually installation documentation for all the app
servers supported by Liferay. Each of those sections has a subsection that
explains how to enable Java security for that app server, which is a
prerequisite for enabling PACL. Once you have Java security enabled, PACL can
be enabled by adding one line to your `portal-ext.properties` or
`portal-setup-wizard.properties` file:

    portal.security.manager.strategy=liferay

Save the file. If Liferay is running, restart it. Your portal is now configured
to check PACL-enabled Marketplace apps against their declared permissions.

Please note that if you installed @product@ manually, there may be further
configuration you need to do to enable PACL. Please check the relevant
installation instructions for your app server for further information. 

Congratulations! Liferay is now installed and ready for production. 

