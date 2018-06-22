# Setting Proxy Requirements for Liferay Dev Studio [](id=setting-proxy-requirements-for-liferay-ide)

If you have proxy server requirements and want to configure your http(s) proxy		
to work with Liferay Dev Studio, follow the instructions below.

1.  Navigate to Eclipse's *Window* &rarr; *Preferences* &rarr; *General*
    &rarr; *Network Connections* menu.

2.  Set the *Active Provider* drop-down selector to *Manual*.

3.  Under *Proxy entries*, configure both proxy HTTP and HTTPS by clicking the
    field and selecting the *Edit* button.

    ![Figure 1: You can configure your proxy settings in Dev Studio's Network Connections menu.](../../../images/ide-network-connections.png)

4.  For each schema (HTTP and HTTPS), enter your proxy server's host, port, and
    authentication settings (if necessary).

    **Note:** Do not leave whitespace at the end of your proxy host or port
    settings.

5.  Once you've configured your proxy entry, click *OK* &rarr; *OK*.

If you're working with a Liferay Workspace in Dev Studio, you'll need to configure
your proxy settings for that environment too. See the
[Setting Proxy Requirements for Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/setting-proxy-requirements-for-liferay-workspace)
for more details.

Awesome! You've successfully configured Dev Studio's proxy settings!

## Additional Proxy Settings [](id=additional-proxy-settings)

Some Eclipse plugins do not properly check the `core.net` proxy infrastructure
when setting proxy settings via *Window* &rarr; *Preferences* &rarr; *General*
&rarr; *Network Connections*. Therefore, you may need to configure additional
proxy settings.

To do so, open the `eclipse.ini` file associated with your Eclipse installation
and add the following entries:

		-vmargs
		-Dhttp.proxyHost=www.somehost.com
		-Dhttp.proxyPort=1080
		-Dhttp.proxyUser=userId
		-Dhttp.proxyPassword=somePassword
		-Dhttps.proxyHost=www.somehost.com
		-Dhttps.proxyPort=1080
		-Dhttps.proxyUser=userId
		-Dhttps.proxyPassword=somePassword

After saving the file, restart Eclipse. Now your additional proxy settings are
applied!
