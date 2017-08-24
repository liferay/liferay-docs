# Debugging with an IDE [](id=debugging-with-an-ide)

To debug @product@ or its modules, you must first start it in debug mode. There 
are two ways to do this, depending on how you're running your @product@ server. 
If you're running your server inside Liferay @ide@, then you can simply click 
the button to start the server in debug mode. If you're running the server 
outside of Liferay @ide@, however, then things are a bit more complicated. This 
article shows you both ways of starting the server in debug mode. 

## Running the Server in Liferay @ide@ [](id=running-the-server-in-liferay-ide)

If you're running the @product@ server inside Liferay @ide@, follow these 
instructions to start it in debug mode:

1.  If you need instructions on setting up a @product@ server to run in Liferay 
    @ide@, see 
    [this tutorial](/develop/tutorials/-/knowledge_base/7-0/installing-a-server-in-liferay-ide). 

2.  Start the server in debug mode. To do this, click the debug button in the 
    Servers pane of Liferay @ide@. 

    ![Figure 1: The red box in this screenshot highlights the debug button. Click this button to start the server in debug mode.](../../images/troubleshooting-debug.png)

That's it! The next section shows you how to start the server in debug mode if 
you're running it outside of Liferay @ide@.

## Running the Server Outside of Liferay @ide@ [](id=running-the-server-outside-of-liferay-ide)

If you're running the @product@ server outside of Liferay @ide@, starting it in 
debug mode is a bit more complicated. To do this in Tomcat, first edit your 
`setenv.sh` or `setenv.bat` file to adjust the value of the `CATALINA_OPTS` 
variable: 

    CATALINA_OPTS="$CATALINA_OPTS -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=8000"

Note that you can change the debug port from `8000` to whatever port you like. 
Once you've configured `CATALINA_OPTS` this way, start Tomcat normally (e.g., 
with `./catalina.sh run` or `./catalina.sh start`). To check that @product@ 
started in debug mode, check the `catalina.out` file in Tomcat's `logs` folder 
for this line: 

    Listening for transport dt_socket at address: 8000

This means that @product@ started in debug mode. 

Once @product@ has started in debug mode, connect your debugger of choice to the 
port you configured in `CATALINA_OPTS`. Once you connect, you can debug 
@product@'s source code and the source code of any @product@ modules. Simply add 
a breakpoint to the code you want to debug, make sure your debugger is aware of 
that code, and your breakpoint should be hit. For example, in Eclipse you can 
use the *Attach Source* button to make the debugger aware of your module's 
source code. 

Here's an example of debugging in Eclipse (Liferay IDE or Developer Studio): 

1.  Select *Run* &rarr; *Debug Configurations*. 

2.  Right-click on *Remote Java Application* and select *New*. 

3.  Name your debug configuration. It often makes sense to name it after the 
    @product@ version you're debugging against. For example, if you're debugging 
    against Liferay Portal 7.0.3 CE GA4, you could name your debug configuration 
    7.0.3-ga4. 

4.  Click *Apply*. 

5.  With your debug configuration selected, open the *Connect* tab and make sure 
    the host and port are configured correctly. If you're debugging against a 
    locally running Liferay instance and you configured the debug port as port 
    `8000`, specify the host as `localhost` and the port as `8000`. 

6.  Click the *Source* tab of your debug configuration. 

7.  Click the *Add* button to attach any source code that's not included in the 
    default configuration. Typically, you'll want at least @product@'s source 
    code in your debug configuration, along with the source code of any modules 
    you're developing. 

8.  Click *Apply* to save your debug configuration. 

9.  Click *Debug* to launch the debugger. 

If you get a `failed to connect to VM` message, make sure that @product@ started 
in debug mode. 

## Related Topics [](id=related-topics)

[Liferay @ide@](/develop/tutorials/-/knowledge_base/7-0/liferay-ide)

[Installing a Server in Liferay @ide@](/develop/tutorials/-/knowledge_base/7-0/installing-a-server-in-liferay-ide)
