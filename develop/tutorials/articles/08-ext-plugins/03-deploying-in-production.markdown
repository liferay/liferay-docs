# Deploying in production [](id=deploying-in-production)

Often times you can't use Ant to deploy web applications in production or
pre-production environments. Additionally, some application servers such as
WebSphere or Weblogic have their own deployment tools, and Liferay's autodeploy
process won't work. Let's look at two methods for deploying and redeploying Ext
plugins in these scenarios. 

## Method 1: Redeploying Liferay's web application [](id=method-1-redeploying-liferays-web-application)

You can use this method in any application server that supports auto-deploy;
Tomcat and Glassfish are two examples. What's the benefit? The only artifact
that needs to be transferred to the production system is your Ext plugin's
`.war` file, produced using the `ant war` target. This `.war` file is usually
small and easy to transport. Execute these steps on the server: 

1.  Redeploy Liferay:

    If this is your first time deploying your Ext plugin to this server, skip
    this step. Otherwise, start by executing the same steps you first used to
    deploy Liferay on your app server. If you're using a bundle, unzip it again.
    If you installed Liferay manually on an existing application server, you'll
    need to redeploy the Liferay `.war` file and copy both the libraries
    required globally by Liferay and your Ext plugin to the appropriate
    directory within the application server. 

2.  Copy the Ext plugin `.war` into the auto-deploy directory. For a bundled
    Liferay distribution, the `deploy` folder is in Liferay's *root* folder of
    your bundle (e.g., `liferay-portal-6.1.2-ce-ga3/`).

3.  Once the Ext plugin is detected and deployed by Liferay, restart your
    Liferay server. 

## Method 2: Generate an aggregated WAR file [](id=method-2-generate-an-aggregated-war-file)

Some application servers don't support auto-deploy; WebSphere and Weblogic are
two examples. With an aggregated WAR file, all Ext plugins are merged before
deployment to production. A single `.war` file will contain Liferay plus the
changes from all your Ext plugins. Before you deploy the Liferay `.war` file,
copy the dependency `.jar` files for Liferay and all Ext plugins to the global
application server class loader in the production server. The precise location
varies from server to server; see [Using Liferay Portal
6.1](/discover/portal/-/knowledge_base/6-1/installation-and-setup) to get
the details for your application server. 

The first step in creating the aggregated `.war` file is to deploy your Ext
plugin. The remaining steps can differ depending on your application server;
let's proceed by assuming you're using a Liferay Tomcat bundle. Deploy your
plugin, restart the server, then shut it down. The files are now aggregated in
your app server. Create a `.war` file by zipping the `webapps/ROOT` folder of
Tomcat, then copy all the libraries from the `lib/ext` directory of Tomcat to
your application server's global classpath--these files are associated with your
Ext plugins. 

Once your `.war` file is aggregated, perform these actions on your server: 

1.  Redeploy Liferay using the aggregated WAR file. 

2.  Stop the server and copy the new version of the global libraries to the
    appropriate directory in the application server. 

<!-- I really don't like this. Do we know where we got these instructions? We
need to try to avoid using Tomcat as a solution for deploying something to
another app server. It makes the erroneous case that Liferay is somehow
"designed for" or "integrates best with" Tomcat, and that's not the message we
want to send. I think we need to change this and get Tomcat out of it. --> 

Next we'll show you how to migrate your extension environment (from older
verisons of Liferay) into Ext plugins. 
