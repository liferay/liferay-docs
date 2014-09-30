# Liferay Home [](id=liferay-home)

Liferay Portal uses a special folder defined as *Liferay Home*. This folder is
usually one folder higher than the location of the application server itself.
This is why the bundles place the application server one folder in from the
bundle's root folder.

If Liferay is unable to create the resources it needs in this folder or if it
finds itself running on certain application servers, it creates a folder called
`liferay` in the home folder of the user ID that is running Liferay, and that
becomes Liferay Home.

The home folder is important to the operation of Liferay. The aforementioned
folders (`data` and `deploy`) are created there, and Liferay's properties
configuration files are also stored there. 

We recommend using the setup wizard to configure your database and mail sessions
when you first configure Liferay. This creates a
`portal-setup-wizard.properties` file where all the settings from the setup
wizard are stored. When you begin customizing your portal's configuration, we
recommend you create a separate properties file for that, called
`portal-ext.properties`. This allows you to keep your server configuration
properties separate from core properties like your database and mail server
configurations.  All the possible properties that can
be placed in this file are documented in [our reference documentation](http://docs.liferay.com/portal/6.2/propertiesdoc). 

+$$$

**Note:** To avoid using the setup wizard so you
can configure everything manually from a `portal-ext.properties` file, you must
disable the Setup Wizard by specifying `setup.wizard.enabled=false` in the
`portal-ext.properties`. Also, note that property values in
`portal-setup-wizard.properties` (the file created in Liferay Home by the Setup
Wizard) override property values in `portal-ext.properties`. 

$$$

Let's move on and discuss Liferay Portal security.
