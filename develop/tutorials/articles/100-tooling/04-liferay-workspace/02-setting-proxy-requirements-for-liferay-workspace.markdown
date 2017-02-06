# Setting Proxy Requirements for Liferay Workspace [](id=setting-proxy-requirements-for-liferay-workspace)

If you're working behind a corporate firewall that requires using a proxy server to access
external repositories, you will need to add some extra configuration to make Liferay Workspace
work within your environment.  

## Using Gradle

1.  Open your `~/.gradle/gradle.properties` file. Create this file if it does
		not exist.

2.  Add the following properties to the file:

				systemProp.http.proxyHost=www.somehost.com
				systemProp.http.proxyPort=1080
				systemProp.https.proxyHost=www.somehost.com
				systemProp.https.proxyPort=1080

3.  If the proxy server requires authentication, also add the following:

				systemProp.http.proxyUser=userId
				systemProp.http.proxyPassword=somePassword
				systemProp.https.proxyUser=userId
				systemProp.https.proxyPassword=somePassword

## Using Maven

1.  Open your `~/.m2/settings.xml` file. Create this file if it does
		not exist.

2.  Add the following snippet of xml to the file:

```
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
  <!-- proxies
   | This is a list of proxies which can be used on this machine to connect to the network.
   | Unless otherwise specified (by system property or command-line switch), the first proxy
   | specification in this list marked as active will be used.
   |-->
  <proxies>
    <!-- proxy
     | Specification for one proxy, to be used in connecting to the network.
     |-->
    <proxy>
      <id>httpProxy</id>
      <active>true</active>
      <protocol>http</protocol>
      <host>www.somehost.com</host>
      <port>1080</port>
    </proxy>
    <proxy>
      <id>httpsProxy</id>
      <active>true</active>
      <protocol>https</protocol>
      <host>www.somehost.com</host>
      <port>1080</port>
    </proxy>
  </proxies>
</settings>
```

3.  If the proxy server requires authentication, also add the following:

```
    <proxy>
      <id>httpProxy</id>
      <active>true</active>
      <protocol>http</protocol>
      <host>www.somehost.com</host>
      <port>1080</port>
      <username>userID</username>
      <password>somePassword</password>
    </proxy>
```

Excellent! Your proxy settings are now set.
