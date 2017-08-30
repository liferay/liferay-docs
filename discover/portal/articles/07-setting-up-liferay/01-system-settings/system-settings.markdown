# System Settings [](id=system-settings)

You might be tired of hearing about it by now, but it's worth repeating: Liferay
is modular. It's composed of many applications, which are divided into even
smaller "chunks" of functionality. The applications, and sometimes even code
chunks, are configurable at several scopes, as discussed in the introductory
article for this section. 

If you need to make configuration changes to an application that take effect in
a system-wide fashion, where do you do it?  Readers who have been around a while
will be raising their hands hoping to be called on, sure that the correct answer
is "why, system-wide application configuration takes place in a properties file,
most commonly the `portal-ext.properties` file". They then expect to get a
sticker of [Ray](https://www.liferay.com/ray) on their mobile device for their
attentiveness. However, that's an incomplete answer, so they need to just sit
back down and listen like the rest of the class. There's a brand new way to make
configuration changes at the system scope in @product-ver@, and you don't need to go
messing around in a properties file to do it. These settings can now be made in
the Control Panel, in *Configuration* &rarr; *System Settings*. 

![Figure 1: System Settings are accessed through the Control Panel.](../../../images/system-settings-product-menu.png)

## Editing the Default Configuration [](id=editing-the-default-configuration)

Once you navigate to System Settings, you'll see that the configuration options
are categorized into logical groupings based on their functionality, and that
there's a Search box to make finding the app you want to configure easier. 

![Figure 2: System Settings are organized by component.](../../../images/system-settings-nav-search.png)

Changing an app's default configuration options in System Settings isn't
difficult. Once you find what you're looking for, simply click the name of the
component you want to configure, or click the actions button
(![Actions](../../../images/icon-actions.png)), then click *Edit*. Make any changes
you'd like, then click *Save*. Now your configuration changes are saved and take
place throughout the entire system. 

![Figure 3: After saving changes to a configuration, actions *Reset Default Values* and *Export* are available for it.](../../../images/system-settings-actions.png)

If you make some regrettable configuration decisions and can't recall exactly
what you did, start over by clicking the actions button
(![Actions](../../../images/icon-actions.png)), then clicking *Reset Default
Values*. 

## Configuration Scope [](id=configuration-scope)

While browsing the categories of System Settings, you'll notice that each entry
has a *Name* and a *Scope*. This scope is different than the overall
application configuration scope discussed in the introduction to this section.
This is the System Settings application after all, so all of the configuration
done here affects the System scope. So what is the Scope field here referring
to? It's the scope at which this default, system-wide configuration can be
overridden. There are four values that you'll see under Scope: 

- *System:* Any configuration made at the system scope here becomes the final
  value for the application in a system wide fashion. It affects the whole
system and cannot be overridden anywhere else. 

    ![Figure 4: Some System Settings entries have a System scope.](../../../images/system-settings-system-scope.png)

- *Default Configuration for Application:* Making configuration changes at this
  level affects the application in a system-wide fashion as well. However, while
these become the system wide defaults for the application, they can be
overridden from each application instance. 

    ![Figure 5: Some System Settings entries have a Default Configuration for Application scope.](../../../images/system-settings-application-scope.png)

- *Default Configuration for All Sites:* Configuration at this scope can be
  overridden in each site. 

    ![Figure 6: Some System Settings entries have a Default Configuration for All Sites scope.](../../../images/system-settings-site-scope.png)

- *Default Settings for All Instances:* Configuration at this scope can be
  overridden in each instance (usually via Instance Settings). 

    ![Figure 7: Some System Settings entries have a Default Configuration for All Instances scope.](../../../images/system-settings-instance-scope.png)

If an application is configurable from Instance Settings and System Settings,
use System Settings to configure it whenever possible. If you use Instance
Settings and later want to revert to using the System Settings default
configurations, look for the *Reset Values* button from the Instance Settings
entry. 

![Figure 8: Some Instance Settings entries have a *Reset Values* button so you can safely revert your configuration changes, falling back to the System Settings defaults.](../../../images/instance-settings-reset-values.png)

The Reset Values button removes configuration values from the @product@ database
so you can rely on the defaults specified in System Settings. If there is no
Reset Values button for an Instance Settings entry, once you use Instance
Settings to configure the application, you cannot later decide to use System
Settings. Only changes to Instance Settings will be recognized. 

<!-- Perhaps show a proof-of-concept example here? -->

<!-- Need a Lunar Resort example? -->

## Exporting and Importing Configurations [](id=exporting-and-importing-configurations)

What if you change a lot of default configurations in System Settings, and then
you need to make the same configurations in another Liferay installation? Don't
worry, you won't need to remember every choice you make, then manually duplicate
all the configurations. The System Settings application lets you export
individual configurations (for example, just the configurations made to the
Dynamic Data Lists entry), or export all of the settings you've made in the
System Settings interface. Then you can just deploy them in the target Liferay
installation. 

In the case of a single entry, just click the actions button
(![Actions](../../../images/icon-actions.png)), then click *Export*. A `.config` file
will be downloaded, containing any configuration changes you've saved. 

+$$$

**Note:** If you're running Liferay DE 7.0 Fix Pack 8 or later, the `.config`
file format is exported from System Settings. If you're on an earlier fix pack,
the `.cfg` file format is used. 

If you're on Liferay Portal 7.0 GA3 or earlier, the `.cfg` file is exported from
System Settings. 

The file format was changed to fix a bug with multi-value settings, which the
`.cfg` file format did not handle properly. See
[the bug report](https://issues.liferay.com/browse/LPS-67890)
for more information. Any @product@ system later than those listed above uses the
`.config` file format. 

While `.config` files are now the default file type exported by System Settings
for the reason described above, you're free to continue using `.cfg` files.
They're still supported by the underlying configuration framework used by System
Settings. 

$$$

To export all of the configuration changes you've made in System Settings, click
the System Settings options button (![Options](../../../images/icon-options.png)),
then click *Export All Settings*. You'll get a zip file with `.config` files
corresponding to all of the entries that you edited. 

To make these configurations active in the destination Liferay system, simply
unzip and place the `.config` files in the `[Liferay_Home]/osgi/modules` folder. 

Now you know what System Settings is and how to use it. All that's left is
exploring the entries to see what configuration options you can make. If you
aren't sure what something does, check the documentation for the feature you're
interested in, as specific configurations will be covered there. 
