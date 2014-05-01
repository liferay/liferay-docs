# Developing Applications with Liferay Developer Studio

To develop an application, you need a developer environment. Liferay provides a
great environment with Liferay Developer Studio. 

Let's get your Liferay development environment installed. 

## Installing Liferay Developer Studio

1. Download and install [Java](http://java.oracle.com). Liferay runs on Java, so
you'll need it to run everything else. 

2. Download [Liferay Developer Studio](https://www.liferay.com/downloads/liferay-projects/liferay-ide).
Installing it is easy: unzip it to a convenient location on your system.

3. Launch Developer studio by running the `DeveloperStudio` executable. 

4. The First Start wizard appears next. This leads you through the process
of getting your Liferay Portal runtime installed. The fastest way to do this is
with the defaults: leave *Configure Liferay Portal EE bundled with the Studio
installation* checked and click *Next*. 

5. Browse to select your license key provided by Liferay and click *Next*. 

6. Though Liferay Portal ships with an embedded database, as a developer,
you'll want to use a database you can query with your own tools. For this
reason, the next page lets you connect the Liferay runtime to any database
Liferay supports. Create the database in your DBMS of choice before completing
this step. Note that after the wizard completes, you may need to copy
your database's JDBC driver into the `lib/ext` folder of the Liferay bundle that
ships with Developer Studio. When finished, click *Next*. 

![Figure 1.x: The First Start wizard makes it easy to configure the runtime that ships with Liferay Developer Studio](../../images/lds-first-launch-3.png)

7. Next, you can connect Studio to Liferay's Javadocs and source, for code
lookups and to help debugging. Browse to the Javadocs or paste in the URL to
where they're stored online (pictured). For source, you can point to a
downloaded .zip of the source code or a version checked out from a code
repository. Click *Finish*. 

![Figure 1.x: Pointing Studio to the Javadocs and Liferay source can help with debugging your code.](../../images/lds-first-launch-4.png)

After this, the wizard completes, and you have a fully configured development
environment, ready to begin work! 

## Next Steps

Nowt that you have your development environment installed, you'll want to jump
in and create an app: 

[Writing Your First Liferay Application](http://www.liferay.com)

