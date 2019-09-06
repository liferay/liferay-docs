---
header-id: using-the-upgrade-planner-with-proxy-requirements
---

# Using the Upgrade Planner with Proxy Requirements

[TOC levels=1-4]

If you have proxy server requirements and want to configure your http(s) proxy		
to work with the Liferay Upgrade Planner, follow the instructions below.

1.  In Dev Studio's `DeveloperStudio.ini`/`eclipse.ini` file, add the following
    parameters:

    ```ini
    -Djdk.http.auth.proxying.disabledSchemes=
    -Djdk.http.auth.tunneling.disabledSchemes=
    ```

2.  Launch Dev Studio.

3.  Go to *Window* &rarr; *Preferences* &rarr; *General* &rarr; *Network
    Connections*.

4.  Set the *Active Provider* drop-down selector to *Manual*.

5.  Under *Proxy entries*, configure both proxy HTTP and HTTPS by clicking the
    field and selecting the *Edit* button.

    ![Figure 1: You can configure your proxy settings in Dev Studio's Network Connections menu.](../../../images/upgrade-planner-proxy.png)

6.  For each schema (HTTP and HTTPS), enter your proxy server's host, port, and
    authentication settings (if necessary). Do not leave whitespace at the end
    of your proxy host or port settings.

7.  Once you've configured your proxy entry, click *Apply and Close*.

Awesome! You've successfully configured the Upgrade Planner's proxy settings!
