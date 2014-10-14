# Developer Mode [](id=developer-mode)

Do you want to develop Liferay resources without having to redeploy to see your
portal modifications? Use Liferay Developer Mode! In Developer mode, all caches
are removed, so any changes you make are visible right away. Also, you won't
have to reboot the server as often in Developer Mode.

Before you use Developer Mode, you'll have to add the
`portal-developer.properties` file to your application server's configuration
file. Each application server has a different configuration file or UI to
specify system properties; so you'll need to find out the specific method for
your application server. Let's demonstrate using the Tomcat application server.

In your `setenv.sh` file (`setenv.bat` in Windows), find the `CATALINA_OPTS`
variable and add the following to the list of options:
    
    -Dexternal-properties=portal-developer.properties

The following is an example of the `CATALINA_OPTS` variable lines with the
`external-properties` option appended to the end (all code must be on one line):

    CATALINA_OPTS=
        "$CATALINA_OPTS
         -Dfile.encoding=UTF8
         -Dorg.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES\
             =false
         -Duser.timezone=GMT
         -Xmx1024m
         -XX:MaxPermSize=256m
         -Dexternal-properties=portal-developer.properties"

If you're already using the system property `external-properties` to load other
properties files, add `portal-developer.properties` to the list and use a comma
to separate it from other entries.

+$$$

**Note:** Older versions of Liferay 6.1 bundled with Tomcat use the `JAVA_OPTS`
variable line. This caused some performance issues and was changed to
`CATALINA_OPTS`. If you have the `JAVA_OPTS` variable line in your `setenv.sh`
or `setenv.bat` file, change the variable line to `CATALINA_OPTS` to take full
advantage of the performance upgrade. More information on this change can be
referenced in [LPS-26464](https://issues.liferay.com/browse/LPS-26464).

$$$

How does Developer Mode let you see your changes more quickly? In Developer
Mode, there are several changes to the normal order of operations. Some of these
key behaviors are listed below, along with the portal property override
settings that trigger them:

- CSS files are loaded individually rather than being combined and loaded as a
  single CSS file (`theme.css.fast.load=false`).
- Layout template caching is disabled (`layout.template.cache.enabled=false`).
- The server does not launch a browser when starting up
  (`browser.launcher.url=`).
- FreeMarker Templates for themes and web content are not cached, so changes
  are applied immediately
  (`freemarker.engine.resource.modification.check.interval=0`). 
- Minification of CSS and JavaScript resources is disabled
  (`minifier.enabled=false`).

Individual file loading of your styling and behaviors, combined with disabled
caching for layout and FreeMarker templates, let's you see your changes
more quickly. 

Now, when you modify your theme's `custom.css` file directly in your Liferay
Bundle, you can see your changes applied as you make them! Make sure you copy
any changes you make back into your `_diffs` folder, or they'll be overwritten
when you redeploy your theme. 

Let's add a thumbnail image for our theme now. 
