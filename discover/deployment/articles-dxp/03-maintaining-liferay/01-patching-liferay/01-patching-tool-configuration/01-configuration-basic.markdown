# Patching Tool Basic configuration [](id=patching-tool-basic-configuration)

There are two ways to configure the Patching Tool:

1. Automatically by executing the `auto-discovery` command

2. Manually by editing the configuration file (see the Advanced Configuration section)

Automatic configuration generates the configuration files by looking for @product@
files in the file system. By default the Patching Tool looks for the @product@
files in the parent folder. To start the process run

    patching-tool auto-discovery

If @product@ is not installed in the parent folder, you can specify its
location: 

    patching-tool auto-discovery /opt/Liferay/tomcat-8.0.32

That's it! Now that you've installed the Patching Tool and run auto-discovery,
you're ready to download and install patches. You can install patches manually
or automatically.

If you specified the wrong folder or @product@ is not installed in the parent
folder, the Patching Tool won't be able to find the [Liferay Home](/discover/deployment/-/knowledge_base/7-0/installing-liferay-portal#liferay-home)
folder and shows an error: 

    The .liferay-home has not been detected in the given directory tree.

    Configuration:
    patching.mode=binary
    war.path=../tomcat-8.0.32/webapps/ROOT/
    global.lib.path=../tomcat-8.0.32/lib/ext/
    liferay.home=**[please enter manually]**

    The configuration hasn't been saved. Please save this to the default.properties file.

In this case you can either add the folder manually to the configuration or
create the `.liferay-home` file and re-run the auto-discovery process.


When the patching tool is configured, you can run `patching-tool info` and receive information about the product version.

