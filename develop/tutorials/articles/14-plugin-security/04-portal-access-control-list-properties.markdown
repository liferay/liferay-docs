# Portal Access Control List (PACL) Properties [](id=portal-access-control-list-pacl-properties)

Liferay Portal's Plugin Security Manager checks all your plugin's API access
attempts against the security manager properties specified in your plugin's
`liferay-plugin-package.properties` file. If your plugin tries to access a
portal resource that is not specified in these properties, the Plugin Security
Manager prevents it from happening. Consider this a virtual finger waggin'. To
prevent this from happening, you have to tell the Plugin Security Manager
up-front the access your plugin needs.

The sections that follow describe the PACL properties: explaining each
property's purpose, its possible values, and the syntax to use in specifying its
value.
