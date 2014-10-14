# Deploying New Liferay Projects to Liferay Server [](id=deploying-new-liferay-projects-to-liferay-server)

You have a plugin project, but you need to deploy it onto your Liferay
Server. Let's do it now. There are two ways to do it, and the first is very
easy. Select your project, drag it down, and drop it on your server. Voila! Your
plugin is deployed. 

Alternatively, you can use the following procedure: 

1.  Select your new plugin project and right click the Liferay Server in
    the *Servers* tab; 

2.  Select *Add and Remove...*. 

3.  Select your plugin project and click *Add* to deploy it to the server. 

4.  Click *Finish*. 

You should see the project get deployed to Liferay Tomcat server; in the console
is a message indicating your new portlet is available for use. 

![Figure 10.6: Verifying the output in the Console view](../../images/ide-console-portlet-available.png)

If you've never started this instance of Liferay before, open *Liferay Portal
Home* (`http://localhost:8080/` in most cases) and follow the instructions in
the setup wizard. 

Once you're logged in, click *Add* &rarr; *More*, expand the *Sample* category,
and click the *Add* link next to your demo application. Your portlet then
appears on the page. 

Great, now you can create projects in Liferay IDE! Next, let's learn how to
create new plugins inside of existing projects in Liferay IDE. 
