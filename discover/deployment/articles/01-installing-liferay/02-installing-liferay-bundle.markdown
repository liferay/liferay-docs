# Installing a Liferay Bundle

Liferay bundles contain the same directory structure regardless of application
server. The top-level folder is named for the release of Liferay. This folder
is called *Liferay Home*.

Inside of the Liferay Home folder, there are folders for various purposes:

    - [Liferay Home]
        - `data`
        - `logs`
        - `osgi`
        - [Application Server]

`data`: This folder is used to store an embedded HSQL database, Liferay's
Jackrabbit JSR-170 file repository, and Liferay's search indexes. Liferay is
initially configured to use the embedded HSQL database but the HSQL database is
primarily intended for demonstration and trial purposes. Liferay's database
connection can be configured on the Basic Configuration page that appears on
Liferay's initial startup.

`logs`: This folder contains Liferay's log files. The information in Liferay's
log files can be quite valuable for system administrators, especially when
trying to diagnose a problem.

`osgi`: All of the JAR files and a few configuration files for Liferay's OSGi
runtime belong in this folder. To deploy Liferay plugins, copy them to the
`osgi/modules` folder.

**[Application Server]:** The name of this folder is different depending on
the bundle you've downloaded. This folder contains the application server in
which Liferay has been installed.

In addition to Liferay Portal itself, bundles are shipped with a number of
plugins already installed. The exact list of plugins depends on your Liferay
portal edition and version. Visit the *App Manager* in Liferay's Control Panel
to see all of the installed Liferay plugins. Click on *Store* to access Liferay
Marketplace and browse Liferay plugins and click on *Purchased* to view plugins
that you've already purchased that are ready to be installed.

Getting a Liferay bundle up and running is as easy as uncompressing the
archive, copying a JDBC driver, and then starting the application server. Let's
use the Liferay Tomcat bundle as an example.

1. Unzip your Liferay bundle.

2. If you're setting up Liferay to be an actual server, copy your database's
   JDBC driver `.jar` file to `[Tomcat]/lib/ext` (see the setup wizard section
   below). If you're setting up Liferay for demo purposes, you can skip this
   step.

3. Start Tomcat as if you had downloaded it manually. Tomcat is launched by
   invoking a script which is found in its `bin` folder. If you open a command
   prompt or terminal and go to this folder, you can launch Tomcat via the
   following command on Windows:

        startup

    or the following command on Linux/Mac/Unix:

        ./startup.sh

The Liferay Tomcat bundle then starts. If you are on Windows, another command
prompt window appears with Tomcat's console in it. If you are on Linux, you can
see the Tomcat console by issuing the following command:

    tail -f ../logs/catalina.out

Once Tomcat has started, it automatically launches a web browser that displays
Liferay's Basic Configuration page. If for some reason your browser doesn't
load the Basic Configuration page, launch your web browser and navigate to
[http://localhost:8080](http://localhost:8080).

The Basic Configuration includes a checkbox labeled *Add Sample Data*. If you
check this box, sample data is added to Liferay's database including users,
sites, and organizations. The sample data allows many Liferay features to be
showcased.

If you're installing Liferay on your own machine to explore its features, the
sample data will probably be useful. If, however, you're installing Liferay on
a real server, you should start with a clean system.

Bundles are the easiest way to get started with Liferay. They come
preconfigured with a Liferay instance that can be used immediately to explore
many Liferay features. Bundles are the fastest way to create full
production-ready Liferay installations.

Of course, it's not always possible to use a bundle. You may already have an
application server on which Liferay should be installed. In this case, please
refer to our documentation on installing Liferay on your specific app server.
