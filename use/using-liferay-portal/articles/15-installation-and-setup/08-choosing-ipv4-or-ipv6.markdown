# Choosing IPv4 or IPv6 [](id=choosing-ipv4-or-ipv6)

Liferay Portal supports both the IPv4 and IPv6 address formats, though by
default, Liferay uses IPv4 addresses. If you're on an IPv6 network, you'll
need to change the configuration. If you'd like more information on the basics
of these protocols, you can check out the
[reason](http://www.google.com/intl/en/ipv6/) for using IPv6 addresses, and its
[technical details](http://en.wikipedia.org/wiki/IPv6).

To configure your portal to validate IPv6 addresses, you must complete a few
simple steps. First, assuming you're using the Tomcat app server for your
portal, navigate to the `${TOMCAT_HOME}/bin/setenv.bat` file and set
`-Djava.net.preferIPv4Stack=false` in `CATALINA_OPTS`. Then, create a
`portal-ext.properties` file in your portal's home directory (if necessary) and
set the `tunnel.servlet.hosts.allowed` property to the target hosts you want to
allow (e.g., *0:0:0:0:0:0:0:1*). 

Now that you understand all the prerequisites for installing Liferay Portal,
let's go ahead and get it done! First we'll go over installing Liferay from a
bundle, and after this we'll provide instructions for installing Liferay manually
on all the application servers it supports. 
