# Choosing IPv4 or IPv6 [](id=choosing-ipv4-or-ipv6)

@product@ supports both the IPv4 and IPv6 address formats. By default,
@product@ uses IPv4 addresses. If you're on an IPv6 network, you'll need to
change the configuration. If you'd like more information on the basics of these
protocols, you can check out the [reason](http://www.google.com/intl/en/ipv6/)
for using IPv6 addresses, and its [technical
details](http://en.wikipedia.org/wiki/IPv6).

To configure your portal to validate IPv6 addresses, you must complete a few
simple steps:

1. Assuming you're using the Tomcat app server for your portal, edit the
   `setenv.sh` or `setenv.bat` file in the `${TOMCAT_HOME}/bin` folder and set
   `-Djava.net.preferIPv4Stack=false` in `CATALINA_OPTS`.
2. Create a `portal-ext.properties` file in your portal's Liferay Home
   directory (if one does not already exist) and set the
   `tunnel.servlet.hosts.allowed` property to the target hosts you want to
   allow (e.g., *0:0:0:0:0:0:0:1*).

