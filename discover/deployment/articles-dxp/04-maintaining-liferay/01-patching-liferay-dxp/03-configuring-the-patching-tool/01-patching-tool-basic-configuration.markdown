# Patching Tool Basic configuration [](id=patching-tool-basic-configuration)

There are two ways to configure the Patching Tool:

1.  Automatically by executing the `auto-discovery` command

2.  Manually by editing the configuration file (see
    [Patching Tool Advanced Configuration](https://customer.liferay.com/documentation/7.1/deploy/-/official_documentation/deployment/patching-tool-advanced-configuration))

Automatic configuration generates the configuration files by looking for
@product@ files in the local file system. By default the Patching Tool looks for
them in its parent folder. To start the process, run this command in your
Patching Tool folder (`patching-tool`):

    patching-tool auto-discovery

If @product@ is not installed in the parent folder, specify its location: 

    patching-tool auto-discovery /opt/Liferay/tomcat-8.0.32

If you specified the wrong location of @product@ or it is not in the parent
folder, the Patching Tool can't find the
[Liferay Home](/discover/deployment/-/knowledge_base/7-1/installing-liferay-portal#liferay-home)
and reports an error like this: 

    The .liferay-home has not been detected in the given directory tree.

    Configuration:
    patching.mode=binary
    war.path=../tomcat-8.0.32/webapps/ROOT/
    global.lib.path=../tomcat-8.0.32/lib/ext/
    liferay.home=**[please enter manually]**

    The configuration hasn't been saved. Please save this to the default.properties file.

Here are ways to resolve the Liferay Home issue:

- Specify the Liferay Home path in the `default.properties` file.
- If the Liferay Home is in the Patching Tool's tree, create a `.liferay-home` 
  file in the Liferay Home folder and re-run the  auto-discovery process. 

When the Patching Tool is configured, running `patching-tool info` reports
product version information. 

That's it! Now that you've installed and configured the Patching Tool, you're
ready to download and install patches. 
