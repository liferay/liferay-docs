# Setting Proxy Requirements for Liferay Workspace [](id=setting-proxy-requirements-for-liferay-workspace)

Many developers are forced to work with a proxy server due to their company's
firewall prohibiting them from downloading dependencies. For example, if you're
working behind a firewall and you want to use Liferay Workspace, you may not
have access to critical dependencies that must be downloaded from the internet.
To fix this problem, you'll need to configure your http(s) proxy to work with
Workspace. You'll configure your proxy settings in your Workspace environment
next.

1.  Open your `~/.gradle/gradle.properties` file. Create this file if it does
		not exist.

2.  Add the following properties to the file:

				systemProp.http.proxyHost=
				systemProp.http.proxyPort=
				systemProp.http.proxyUser=
				systemProp.http.proxyPassword=
				systemProp.https.proxyHost=
				systemProp.https.proxyPort=
				systemProp.https.proxyUser= 
				systemProp.https.proxyPassword=

3.  Configure the properties applicable to your proxy server (e.g., HTTP or
		HTTPS). For example, examine the sample HTTP proxy settings below:

		    systemProp.http.proxyHost=www.somehost.com
		    systemProp.http.proxyPort=8080
		    systemProp.http.proxyUser=userID
		    systemProp.http.proxyPassword=somePassword

Excellent! Your proxy settings are set in Gradle, which allows your Workspace to
download the necessary dependencies for your Workspace projects.
