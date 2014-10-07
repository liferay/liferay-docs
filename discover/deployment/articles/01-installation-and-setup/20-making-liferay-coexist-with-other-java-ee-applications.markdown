# Making Liferay Coexist with Other Java EE Applications [](id=making-liferay-coexist-with-other-java-ee-applications)

Liferay Portal by default is configured to sit at the root (i.e., `/`) of your
application server. Dedicating your application server to running only Liferay
Portal is a good practice, allowing for separation between your portal
environment and your web application environment. This is generally a best
practice for portals, which by definition are application development platforms
in and of themselves. For that reason, your instance of Liferay is likely to be
hosting many applications and even integrating several of them together on a
single page. For this reason, you should design your system so your portal
environment has all the resources it needs to do this. Configuring it so it is
the sole consumer of any other `.war` files that get deployed to the application
server helps to make sure your system performs optimally.

If, however, you want Liferay to share space on an application server with other
applications, you can. In this instance, you may not want to make Liferay the
default application in the root context of the server. If you want to install 
Liferay in a context other than the root context, follow the instructions from 
your app server vendor. No additional steps are necessary.

Now that you have Liferay installed in the context you wish, you'll want to
understand Liferay's releases and the process for keeping your installation up
to date. We'll spend a brief amount of time on that next.
