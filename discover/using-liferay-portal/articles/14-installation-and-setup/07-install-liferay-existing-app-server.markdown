# Installing Liferay on an Existing Application Server [](id=installing-liferay-on-an-existing-application-server)

This section contains detailed instructions for installing Liferay Portal using
its .war file distribution. This allows system administrators to deploy Liferay
in existing application server installations. It is recommended that you have a
good understanding of how to deploy Java EE applications in your application
server of choice.

## Installing Liferay in Five Easy Steps [](id=installing-liferay-in-five-easy-steps)

There are five generic steps to installing Liferay on an existing application
server:

1. Obtain the Liferay `.war` file and the dependencies archive.

2. Make sure you don't have an application listening at the root (`/`) of your
   server. If you do, move it to a different context or undeploy it.

3. Shut your application server down.

4. Extract the dependencies to a location on your server's global classpath.
   This allows both Liferay and plugins to access these dependencies.

5. Start your application server, deploy the Liferay `.war` file and start it.

The instructions below are specific for each application server that Liferay
supports. Liferay supports a wide combination of application servers and
databases. Because of this, this section assumes MySQL as the database, that the
database has already been created and that you're using the setup wizard. If
you're not using the setup wizard, see the sections above for information on how
to set up Liferay manually.

We also assume your application server is already installed and running
successfully. If you still need to install your application server, please
follow your vendor's instructions first.

The following instructions assume an installation on a local machine. When
installing to a remote server, substitute `localhost` with the host name or IP
of the server.

+$$$

**Tip:** Note that Liferay *requires* JDK 5 or greater. Do not attempt to 
install Liferay 6.x on an application server that runs under Java 1.4 or lower; 
it will not work. If you are running an application server that ships with a JDK 
and that JDK is 1.4 or lower, you'll need to upgrade your application server to 
run current versions of Liferay Portal.

$$$

Without further ado, let's get to the application servers. The first one we'll
cover is Mule Tcat. If you don't have an application server preference, and you
want support all the way down to the application server from Liferay, then Mule
Tcat is your solution. After we cover Mule Tcat, we'll look at all the supported
application servers in alphabetical order. 
