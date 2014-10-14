# Enabling the Security Manager [](id=enabling-the-security-manager)

If you want to distribute plugins, either on the Liferay Marketplace or through
your web site, you have to assume users will insist the Security Manager is
enabled in your plugin. For this reason, you should enable it when testing your
plugins and on packaging it for distribution. 

It's very easy to activate the security manager. Set the following
`liferay-plugin-package.properties` property to true: 

	security-manager-enabled=true

Next, we'll look at exactly what APIs the Security Manager protects, and how you
can declare whether your application uses any of these properties. 
