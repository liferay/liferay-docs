# How plugin security works [](id=how-plugin-security-works)

When the Plugin Security Manager is enabled for your plugin, it checks your
plugin's *Portal Access Control List (PACL)*. This list describes what APIs the
plugin accesses, so people deploying the plugin can review what it does without
seeing its source code. If the plugin tries to access anything that's not on
this list, the plugin's request is stopped dead in its tracks with the security
manager logging information on the attempt to access unauthorized APIs or
resources. 

Access to APIs and resources is authorized by means of property values specified
in the plugin's `liferay-plugin-package.properties` file. This file must be
specified in your plugin's `WEB-INF` directory. These security manager
properties are collectively known as the plugin's PACL. 

As you develop plugins for Liferay Marketplace or for distribution within your
organization, you'll need to set the security manager properties appropriately.
Before we dive into the intricacies of these properties, let's consider a plugin
development approach that involves designing an app for the security manager
from the ground up. 
