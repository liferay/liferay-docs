# Making Liferay Coexist with Other Java EE Applications [](id=making-liferay-coexist-with-other-java-ee-applications-lp-6-2-use-useportal)

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
default application in the root context of the server.

There are two steps to modifying this behavior:

1. Deploy Liferay in a context other than root (for example `/portal`).

2. Modify the `portal-ext.properties` file to tell Liferay the context to which
   it has been deployed.

    To change the file, open it in a text editor. Place the `portal.ctx`
    property at the top of the file:

        portal.ctx=/

This default setting defines Liferay Portal as the application that sits at the
root context. If you change it to something else, say `/portal`, for example,
you can then deploy Liferay in that context and it will live there instead of at
the root context.

A full discussion of the `portal-ext.properties` file appears in Chapter 20.

**Note for WebLogic Users:** WebLogic also requires that you modify the
`weblogic.xml` file which is included with Liferay. In this file are tags for
the context root:

    <context-root>/</context-root>

Change this so it matches the path you set in your `portal-ext.properties` file.
You will have to modify the `weblogic.xml` file inside the Liferay `.war` before
you deploy it. Extract the file from the `.war` file, modify it and then put it
back in the `.war` file. Then deploy the modified Liferay `.war` file to the
server in the proper context.

Now that you have Liferay installed in the context you wish, you'll want to
understand Liferay's releases and the process for keeping your installation up
to date. We'll spend a brief amount of time on that next.
