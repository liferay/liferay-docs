# Installing Liferay on an existing application server [](id=installing-liferay-on-an-existing-application-server)

This section contains detailed instructions for installing Liferay Portal using
its .war file distribution. This allows system administrators to deploy Liferay
in existing application server installations. It is recommended that you have a
good understanding of how to deploy Java EE applications in your application
server of choice.

## Installing Liferay in five easy steps [](id=installing-liferay-in-five-easy-steps)

There are five generic steps to installing Liferay on an existing application
server:

1. Obtain the Liferay `.war` file and the dependencies archive.

2. Shut your application server down.

3. Extract the dependencies to a location on your server's global classpath.
   This allows both Liferay and plugins to access these dependencies.

4. Start your application server. 

5. Deploy the Liferay `.war` file.

The instructions below are specific for each application server that Liferay
supports. Liferay supports a wide combination of application servers and
databases. Because of this, this section assumes MySQL as the database, that the
database has already been created, and that you're using the setup wizard. If
you're not using the setup wizard, see the sections above for information on how
to set up Liferay manually.

We also assume your application server is already installed and running
successfully. If you still need to install your application server, please
follow your vendor's instructions first.

Since Liferay uses the UTF-8 character encoding, make sure that your application
server has the Java `-Dfile.encoding=UTF-8` parameter set before you proceed. Note
that different application servers provide different means for setting this
parameter. For example, in Tomcat this parameter goes in the `setenv.sh` or
`setenv.bat` script. For other application servers, this parameter might go in a
different script or might have to be set through an administration console.

The following instructions assume an installation on a local machine. When
installing to a remote server, substitute `localhost` with the host name or IP
of the server.

+$$$

**Tip:** Note that Liferay *requires* JDK 6 or
greater. Do not attempt to install Liferay 6.2 on an application server that
runs under Java 5 or lower; it will not work. If you are running an
application server that ships with a JDK and that JDK is version 5 or lower,
you'll need to upgrade your application server to run current versions of
Liferay Portal.

$$$

Without further ado, let's get to the application servers. The first one we'll
cover is Mule Tcat. If you don't have an application server preference, and you
want support all the way down to the application server from Liferay, then Mule
Tcat is your solution. After we cover Mule Tcat, we'll look at all the supported
application servers in alphabetical order.
