# Testing and Launching Liferay Tomcat Server [](id=testing-and-launching-liferay-tomcat-server)

Once your Liferay Portal Server is set up, you can launch it from the Servers
tab in Eclipse. You have a few options for launching and stopping the server
once it's selected in the Servers tab. 

From the *Servers* tab: 

- Click on the green *Start the Server* button to launch it (or use Ctrl+Alt+R). 
- Click on the red *Stop the Server* button to stop it (or use Ctrl+Alt+S).
  You'll only see this button if the server is running.
- Right click the server and select *Start*. 
- Right click on the server and select *Stop*. 

From Eclipse's *Run* menu:

- Select *Run* to start the server.

<!--Did I get that wrong? The *Terminate* option is grayed out in my LDS's Run
menu though the server is running. -Russ -->

<!-- You should never terminate the server. That kills the process without
closing everything (transactions, database connections, etc.) correctly. -Rich--> 

<!-- We should explain how to properly shutdown a server -Jim -->

Once the server is launched, you can open Liferay portal home from the *Servers*
tab by right clicking your Liferay Tomcat server and selecting *Open Liferay
Portal Home*. 

Next, you'll learn to create new Liferay projects in Liferay IDE. 
