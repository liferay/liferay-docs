# Adding Portal Runtimes and Servers

You can run Liferay on any application server supported by Liferay Portal. Here,
for demonstration purposes, we'll set up our Liferay runtime on the Tomcat
application server. The steps you'd follow for any other supported application
server would be similar. For a list of Liferay bundles with other application
servers, please visit [Liferay's Downloads
page](https://www.liferay.com/downloads/liferay-portal/available-releases). For
instructions on installing Liferay manually on other application servers, please
refer to the [Installation and
Setup](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/installation-and-setup-liferay-portal-6-2-user-guide-15-en)
chapter of *Using Liferay Portal 6.2*. 

Let's add, configure, and fire up your server!

## Adding and Configuring Your Liferay Server

1.  In Eclipse, open the *Server Runtime Environments* dialog box--go to
    *Window* &rarr; *Preferences* &rarr; *Server* &rarr; *Runtime Environments*. 

    ![Figure 2.2: Liferay IDE provides wizards for creating new Liferay server runtime environments.](../../images/ide-prefs-runtime-env.png)

2.  Click *Add* to add a new Liferay runtime; find *Liferay v6.2 (Tomcat 7)*
    under the *Liferay, Inc.* category and click *Next*.

3.  Click *Browse* and select your `liferay-portal-6.2.x/tomcat-7.x` directory. 

4.  If you've selected the Liferay portal directory and a bundle JRE is present,
    it is automatically selected as the server's launch JRE. If no JRE bundle
    is present, then you must select the JRE to use for launch by clicking
    *Installed JREs...*. 

    ![Figure 2.3: If you have multiple JREs installed on your system, choose the one which should run Liferay.](../../images/ide-server-jre.png)

5.  Click *Finish*; you should see your Liferay portal runtime listed in
    *Preferences* &rarr; *Server* &rarr; *Runtime Environments*.

6.  Click *OK* to save your runtime preferences. 

7.  If you haven't created a server, create one now from the *Servers* view in 
    Liferay IDE; then you can test the server. Note that you need to be in the 
    Liferay perspective of Eclipse to see the Servers view. You can get there by 
    selecting Window &rarr; Open Perspective &rarr; Other... and then selecting 
    Liferay from the list.

    ![Figure 2.4: If you haven't created a Liferay server yet, you can do so from the *Servers* tab in Liferay IDE.](../../images/ide-add-new-server.png)

8.  Scroll to the *Liferay, Inc* folder and select *Liferay v6.2... Server*.
    Choose the *Liferay v6.2...* runtime environment that you just created. 

Now your server is set up. Let's launch it and perform some tests! 

## Launching and Testing Your Liferay Server

Once your Liferay Portal Server is set up, you can launch it from the Servers
tab in Eclipse. You have a few options for launching and stopping the server
once it's selected in the Servers tab. 

From the *Servers* tab: 

- Click on the green *Start the Server* button to launch it (or use
  *Ctrl+Alt+R*). 
- Click on the red *Stop the Server* button to stop it (or use *Ctrl+Alt+S*).
  You'll only see this button if the server is running.
- Right click the server and select *Start*. 
- Right click on the server and select *Stop*. 

Once the server is launched, you can open Liferay portal home from the *Servers*
tab by right clicking your Liferay Tomcat server and selecting *Open Liferay
Portal Home*. 
