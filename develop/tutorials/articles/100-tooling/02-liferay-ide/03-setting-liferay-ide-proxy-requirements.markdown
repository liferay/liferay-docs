# Setting Liferay @ide@ Proxy Requirements

If you have proxy server requirements and want to configure your http(s) proxy		
to work with Liferay @ide@, follow the instructions below.

1.  Navigate to Liferay @ide@'s *Window* &rarr; *Preferences* &rarr; *General*
    &rarr; *Network Connections* menu.

2.  Set the *Active Provider* drop-down selector to *Manual*.

3.  Under *Proxy entries*, select your proxy schema (e.g., HTTP or HTTPS) and
    click the *Edit* button.

    ![Figure 1: You can configure your proxy settings in @ide@'s Network Connections menu.](../../../images/ide-network-connections.png)

3.  Fill in your proxy server's host, port, and authentication settings (if
    necessary).

    **Note:** Do not leave whitespace at the end of your proxy host or port
    settings.

4.  Once you've configured your proxy entry, click *OK* &rarr; *OK*.

5.  If you're working with a Liferay Workspace in @ide@, you'll need to
    configure your proxy server for that environment too. See the
    [Setting Proxy Requirements for Liferay Workspace](/develop/tutorials/-/knowledge_base/7-0/setting-proxy-requirements-for-liferay-workspace)
    for more details.

Awesome! You've successfully configured Liferay @ide@'s proxy settings!