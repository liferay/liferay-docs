---
header-id: patching-tool-configuration-properties
---

# Patching Tool Configuration Properties

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/maintaining-a-liferay-installation/reference/configuring-the-patching-tool.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

Here are the Patching Tool configuration properties. See 
[Configuring the Patching Tool](/docs/7-2/deploy/-/knowledge_base/d/configuring-the-patching-tool) 
for more information on configuring the Patching Tool. 

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

Service Pack detection is available behind a proxy server. To configure your
proxy, use the following settings, making sure to replace `[PROXY_IP_ADDRESS]`
with your proxy server's IP address and replace the port numbers with yours: 

```properties
### Proxy settings

# HTTP Proxy

#proxy.http.host=[PROXY_IP_ADDRESS]
#proxy.http.port=80
#proxy.http.user=user
#proxy.http.password=password

# HTTPS Proxy

proxy.https.host=[PROXY_IP_ADDRESS]
proxy.https.port=80
proxy.https.user=user
proxy.https.password=password

# SOCKS Proxy

#proxy.socks.host=[PROXY_IP_ADDRESS]
#proxy.socks.port=1080
#proxy.socks.user=user
#proxy.socks.password=password
```
