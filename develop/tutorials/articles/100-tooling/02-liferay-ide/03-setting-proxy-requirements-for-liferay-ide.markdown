# Setting Proxy Requirements for Liferay @ide@ [](id=setting-proxy-requirements-for-liferay-ide)

If you have proxy server requirements and want to configure your http(s) proxy		
to work with Liferay @ide@, follow the instructions below.

1.  Navigate to Eclipse's *Window* &rarr; *Preferences* &rarr; *General*
    &rarr; *Network Connections* menu.

2.  Set the *Active Provider* drop-down selector to *Manual*.

3.  Under *Proxy entries*, configure both proxy HTTP and HTTPS by clicking *Edit* button.

    ![Figure 1: You can configure your proxy settings in @ide@'s Network Connections menu.](../../../images/ide-network-connections.png)

3.  For each schema (HTTP and HTTPS) enter your proxy server's host, port, and authentication settings (if
    necessary).

    **Note:** Do not leave whitespace at the end of your proxy host or port
    settings.

4.  Once you've configured your proxy entry, click *OK* &rarr; *OK*.

# Additional Proxy Settings

1. Some Eclipse plugins do not properly check the core.net proxy infrastructure (see previous section)
    So you may need to configure additional proxy settings in eclipse.ini file

2. Edit the eclipse.ini file associated with your Eclipse installation and add the following entries:

				-vmargs
				-Dhttp.proxyHost=www.somehost.com
				-Dhttp.proxyPort=1080
				-Dhttp.proxyUser=userId
				-Dhttp.proxyPassword=somePassword
				-Dhttps.proxyHost=www.somehost.com
				-Dhttps.proxyPort=1080
				-Dhttps.proxyUser=userId
				-Dhttps.proxyPassword=somePassword

3. Restart Eclipse

# Liferay Workspace

1.  If you're working with a Liferay Workspace in @ide@, you'll need to
    configure your proxy settings for that environment too. See the
    [Setting Proxy Requirements for Liferay Workspace](/develop/tutorials/-/knowledge_base/7-0/setting-proxy-requirements-for-liferay-workspace)
    for more details.

Awesome! You've successfully configured Liferay @ide@'s proxy settings!
