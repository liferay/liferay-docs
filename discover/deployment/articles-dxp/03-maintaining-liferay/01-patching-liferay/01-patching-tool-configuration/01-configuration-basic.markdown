# Patching Tool

## Basic configuration [](id=basic-configuration)

The Patching Tool configuration can be prepared two ways:
1. Auto configuration by executing the `auto-discovery` command
2. Preparing the configuration file manually, see the Advanced Configuration section

The automatic configuration is a simple helper which prepares the configuration files by looking for files in the filesystem. By default the Patching Tool will start looking for the @product@ files from the parent folder. To start the process, you's simply need to run:

    patching-tool auto-discovery

If you are running the patching tool from a different location, you can additionally specify the folder as a parameter:

    patching-tool auto-discovery /opt/Liferay/tomcat-8.0.32

Now that you've installed the Patching Tool and run auto-discovery, you're ready
to download and install patches. You can install patches manually or
automatically.

If you did not use an prepackaged installation, the Patching Tool might not find the [Liferay Home](/discover/deployment/-/knowledge_base/7-0/installing-liferay-portal#liferay-home) folder automatically. In this case, the Patching Tool will give an error message with a sample configuration:

    The .liferay-home has not been detected in the given directory tree.

    Configuration:
    patching.mode=binary
    war.path=../tomcat-8.0.32/webapps/ROOT/
    global.lib.path=../tomcat-8.0.32/lib/ext/
    liferay.home=**[please enter manually]**

    The configuration hasn't been saved. Please save this to the default.properties file.

In this case you can either add the folder manually to the configuration or create the `.liferay-home` file and re-run the auto-discovery process.

+$$$

**Checkpoint:** The patching tool is configured. When you run `patching-tool info` you receive information about the product version.

$$$