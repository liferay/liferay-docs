# Patching Tool Advanced Configuration [](id=patching-tool-advanced-configuration)

By default, the Patching Tool's configuration file called `default.properties`
is in the tool's folder.

A Patching Tool configuration file typically looks like this:

    patching.mode=binary
    war.path=../tomcat-9.0.6/webapps/ROOT/
    global.lib.path=../tomcat-9.0.6/lib/ext/
    liferay.home=../

The properties above (described fully [below](#using-profiles-with-the-patching-tool)) 
define the location of
[Liferay Home](/discover/deployment/-/knowledge_base/7-1/installing-product#liferay-home),
the patching mode (binary or source), the path to where WAR files are deployed
in the app server, and the global library path. The tool's auto-discovery bases
the OSGi module framework paths on the Liferay Home. If, however, you changed
the OSGi module framework paths to something different than those under the
default folder `[Liferay Home]/osgi`, you must manually specify the following
properties: 

    module.framework.core.path=path_to_modules_core_dir
    module.framework.marketplace.path=path_to_modules_marketplace_dir
    module.framework.modules.path=path_to_modules_modules_dir
    module.framework.portal.path=path_to_modules_portal_dir
    module.framework.static.path=path_to_modules_static_dir
 
Using auto-discovery and working with the default profile `default.properties`
is the easiest way to use the Patching Tool, and is great for smaller, single
server installations. But many @product@ installations serve millions of pages
per day, and the Patching Tool has been designed for this as well. So if you're
running a small, medium, or large cluster of @product@ machines, you can use the
Patching Tool profiles to manage patching for all of them. 

## Using Profiles with the Patching Tool [](id=using-profiles-with-the-patching-tool)

You can create profiles for multiple runtimes by running auto-discovery or
creating them manually. To auto-discover other runtimes, run the Patching Tool
with parameters like this: 

    ./patching-tool.sh [name of profile] auto-discovery [path/to/runtime]

This runs the same discovery process, but on the path you specify. It writes the
profile information to a file called `[name of profile].properties`.
Alternatively, you can manually create profile property files in your
`patching-tool` folder. 

The Patching Tool configuration properties (profile properties) are next. 

## Configuration Properties [](id=configuration-properties)

Here are the Patching Tool configuration properties: 

**patching.mode:** This can be `binary` (the default) or `source` if you're
patching a source tree. Patches contain both binary and source patches. If your
development team extends @product@, have them patch their source tree. 

**patches.folder:** Specify where to store patches. The default location is
`./patches`. 

**war.path:** Specify the location of the @product@ installation inside your
application server. Alternatively, you can specify a `.war` file here, and you
can patch a @product@ `.war` for installation to your application server. 

**global.lib.path:** Specify the location for storing `.jar` files on the global
classpath. If you're not sure, search for `portal-kernel.jar`; it's on
the global classpath. This property is only valid if your `patching.mode` is
`binary`. 

**liferay.home:** Specify the default location for the `data`, `osgi`, and 
`tools` folders.

**source.path:** Specify the location of your @product@ source tree. This 
property is only valid if your `patching.mode` is `source`. 

+$$$

**Note:** To patch the @product-ver@ source code, please upgrade to
Patching Tool 2.0.4+.

$$$ 

Service Pack detection is available behind a proxy server. To configure your
proxy, use the following settings, making sure to replace `[PROXY_IP_ADDRESS]`
with your proxy server's IP address and replace the port numbers with yours: 

    ### Proxy settings

    # HTTP Proxy

    #proxy.http.host=[PROXY_IP_ADDRESS]
    #proxy.http.port=80
    #proxy.http.user=user
    #proxy.http.password=password

    # HTTPS Proxy

    proxy.https.host=[PROXY_IP_ADDRESS]
    proxy.https.port=808
    proxy.https.user=user
    proxy.https.password=password

    # SOCKS Proxy

    #proxy.socks.host=[PROXY_IP_ADDRESS]
    #proxy.socks.port=1080
    #proxy.socks.user=user
    #proxy.socks.password=password

You can have as many profiles as you want and use the same Patching Tool to
patch all of them. This helps to keep all your installations in sync.
