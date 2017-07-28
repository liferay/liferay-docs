# Patching Tool Advanced Configuration [](id=patching-tool-advanced-configuration)

By default, the Patching Tool's configuration file is in its installation folder
and called `default.properties`.

A Patching Tool configuration file typically looks like this:

    patching.mode=binary
    war.path=../tomcat-8.0.32/webapps/ROOT/
    global.lib.path=../tomcat-8.0.32/lib/ext/
    liferay.home=../

The properties above (described fully [below](#using-profiles-with-the-patching-tool)) 
define the location of Liferay Home, the patching mode
(binary or source), the path to where WAR files are deployed in the app server,
and the global library path. If auto-discovery found your Liferay Home folder,
the location of @product@'s OSGi-based module framework can be calculated from
this. If, however, you customized the folder structure, you'll have to specify
manually the following properties: 

    module.framework.core.path=path_to_modules_core_dir
    module.framework.marketplace.path=path_to_modules_marketplace_dir
    module.framework.modules.path=path_to_modules_modules_dir
    module.framework.portal.path=path_to_modules_portal_dir
    module.framework.static.path=path_to_modules_static_dir

For most installations, you don't have to do this, as the `osgi` folder is in
its default location. If you've customized the location of the module framework,
however, you'll have to specify the above locations. Since you moved them, you
should know where they are.

## Using Profiles with the Patching Tool [](id=using-profiles-with-the-patching-tool)

When you ran the auto-discovery task after installing the Patching Tool, it
created a default profile that points to the application server it discovered.
This is the easiest way to use the Patching Tool, and is great for smaller,
single server installations. But many @product@ installations are sized to serve
millions of pages per day, and the Patching Tool has been designed for this as
well. So if you're running a small, medium, or large cluster of @product@
machines, you can use the Patching Tool to manage all of them using profiles. 

The auto-discovery task creates a properties file called `default.properties`.
This file contains the detected configuration for your application server. But
you're not limited to only one server which the tool can detect. You can have it
auto-discover other runtimes, or you can manually create new profiles yourself. 

To have the Patching Tool auto-discover other runtimes, you must use a few
more command line parameters: 

    ./patching-tool.[sh|bat] [name of profile] auto-discovery [path/to/runtime]
 
This runs the same discovery process, but on a path you choose, and the
profile information goes into a `[name of profile].properties` file. 

Alternatively, you can manually create your profiles. Using a text editor,
create a `[profile name].properties` file in the same folder as the Patching
Tool script. 

Below is a description of all the supported properties. 

## Configuration Properties [](id=configuration-properties)

**patching.mode:** This can be `binary` (the default) or `source`, if you're
patching a source tree. @product@ patches contain both binary and source patches.
If your development team is extending @product@, you'll want to provide the
patches you install to your development team so they can patch their source
tree. 

**patches.folder:** Specify the location where you'll copy your patches. By
default, this is `./patches`. 

**war.path:** Specify the location of the @product@ installation inside your
application server.  Alternatively, you can specify a .war file here, and you
can patch a @product@ .war for installation to your application server. 

**global.lib.path:** Specify the location where `.jar` files on the global
classpath are stored. If you're not sure, search for your `portal-kernel.jar`
file; it's on the global classpath. This property is only valid if your
`patching.mode` is `binary`. 

**liferay.home:** Specify the location where by default the `data`,
`osgi`, and `tools` folders reside.

**source.path:** Specify the location of your @product@ source tree. This property
is only valid if your `patching.mode` is `source`. 

+$$$

**Note:** To patch the @product-ver@ source code, please upgrade to
Patching Tool 2.0.4+.

$$$ 

Service Pack detection is available behind a proxy server. Use the following
settings to configure your proxy: 

    ### Proxy settings

    # HTTP Proxy

    #proxy.http.host=192.168.211.39
    #proxy.http.port=80
    #proxy.http.user=user
    #proxy.http.password=password

    # HTTPS Proxy

    proxy.https.host=192.168.211.39
    proxy.https.port=808
    proxy.https.user=user
    proxy.https.password=password

    # SOCKS Proxy

    #proxy.socks.host=192.168.211.39
    #proxy.socks.port=1080
    #proxy.socks.user=user
    #proxy.socks.password=password

You can have as many profiles as you want and use the same Patching Tool to
patch all of them. This helps to keep all your installations in sync.
