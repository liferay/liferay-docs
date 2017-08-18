# Debugging with an IDE [](id=debugging-with-an-ide)

To debug @product@ or its modules, you must first start it in debug mode. One 
way to do this is to configure your application server to start in debug mode. 
To do this in Tomcat, edit your `setenv.sh` or `setenv.bat` file to adjust the 
value of the `CATALINA_OPTS` variable: 

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
