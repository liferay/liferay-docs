# Setting Proxy Requirements for Liferay Workspace [](id=setting-proxy-requirements-for-liferay-workspace)

If you're working behind a corporate firewall that requires using a proxy server
to access external repositories, you need to add some extra configuration to
make Liferay Workspace work within your environment. You'll learn how to set
proxy requirements for both Gradle and Maven environments.

## Using Gradle [](id=using-gradle)

1.  Open your `~/.gradle/gradle.properties` file. Create this file if it does
    not exist.

2.  Add the following properties to the file:

        systemProp.http.proxyHost=www.somehost.com
        systemProp.http.proxyPort=1080
        systemProp.https.proxyHost=www.somehost.com
        systemProp.https.proxyPort=1080

    Make sure to replace the proxy host and port values with your own.

3.  If the proxy server requires authentication, also add the following
    properties:

        systemProp.http.proxyUser=userId
        systemProp.http.proxyPassword=yourPassword
        systemProp.https.proxyUser=userId
        systemProp.https.proxyPassword=yourPassword

Excellent! Your proxy settings are set in your Liferay Workspace's Gradle
environment.

## Using Maven [](id=using-maven)

1.  Open your `~/.m2/settings.xml` file. Create this file if it does not exist.

2.  Add the following XML snippet to the file:

        <?xml version="1.0" encoding="UTF-8"?>
            <settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
                <proxies>
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

    Make sure to replace the proxy host and port values with your own.

3.  If the proxy server requires authentication, also add the `username` and
    `password` proxy properties. For example, the HTTP proxy authentication
    configuration would look like this:

        <proxy>
          <id>httpProxy</id>
          <active>true</active>
          <protocol>http</protocol>
          <host>www.somehost.com</host>
          <port>1080</port>
          <username>userID</username>
          <password>somePassword</password>
        </proxy>

Excellent! Your Maven proxy settings are now set.
