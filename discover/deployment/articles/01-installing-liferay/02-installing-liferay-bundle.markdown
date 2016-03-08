# Installing a Liferay Bundle [](id=installing-a-liferay-bundle)

Liferay bundles contain the same directory structure regardless of application
server. The top-level folder is named for the release of Liferay. This folder
is called *Liferay Home*. For more information on Liferay Home, please see the
[Liferay Home]() documentation.
<!-- Update link above. -->

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
[http://localhost:8080](http://localhost:8080). For information on the Basic
Configuration page and Liferay's setup wizard, please see the
[Using Liferay's Setup Wizard]() documentation.
<!-- Update link above. -->

In addition to Liferay Portal itself, bundles include a number of pre-installed
Liferay plugins. The exact list of plugins depends on your Liferay Portal
edition and version. Visit the *App Manager* in Liferay's Control Panel to see
all of the installed Liferay plugins. Click on *Store* to access Liferay
Marketplace and browse Liferay plugins and click on *Purchased* to view plugins
that you've already purchased that are ready to be installed.

Bundles are the easiest way to get started with Liferay. They come with a
pre-installed and preconfigured Liferay instance that can be used immediately
to explore many Liferay features. Bundles are the fastest way to create full
production-ready Liferay installations.

Of course, it's not always possible to use a bundle. You may already have an
application server on which Liferay should be installed. In this case, please
refer to our documentation on installing Liferay on your specific app server.
