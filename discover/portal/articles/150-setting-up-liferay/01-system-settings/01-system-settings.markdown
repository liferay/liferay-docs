# System Settings [](id=system-settings)

You might be tired of hearing about it by now, but it's worth repeating: 
@product@ is modular. It's composed of many applications, which are divided into 
even smaller "chunks" of functionality. The applications, and sometimes even 
code chunks, are configurable at several scopes, as discussed in the 
introductory article for this section. 

If you need to make configuration changes to an application that take effect in 
a system-wide fashion, where do you do it?  Readers who have been around a while 
will be raising their hands hoping to be called on, sure that the correct answer 
is "why, system-wide application configuration takes place in a properties file, 
most commonly the `portal-ext.properties` file". They then expect to get a 
sticker of 
[Ray](https://web.liferay.com/web/olaf.kock/blog/-/blogs/radio-liferay-episode-45-bryan-ho-on-design-and-ray) 
on their mobile device for their attentiveness. However, that's an incomplete 
answer, so they need to sit back down and listen like the rest of the class. 
There's a brand new way to make configuration changes at the system scope in 
@product-ver@, and you don't need to go messing around in a properties file to 
do it. These settings can now be made in the Control Panel, in *Configuration* 
&rarr; *System Settings*. 

![Figure 1: System Settings are accessed through the Control Panel.](../../../images/system-settings-product-menu.png)

## Editing the Default Configuration [](id=editing-the-default-configuration)

In System Settings, the configuration options are categorized into logical 
groupings based on their functionality, and there's a Search box to make finding 
the app you want to configure easier. 

![Figure 2: System Settings are organized by component.](../../../images/system-settings-nav-search.png)

Changing an app's default configuration options in System Settings isn't 
difficult. Once you find what you're looking for, simply click the name of the 
component you want to configure, or click the actions button 
(![Actions](../../../images/icon-actions.png)) then click *Edit*. Make any 
changes you'd like, then click *Save*. Your configuration changes are saved and 
applied throughout the system. 

![Figure 3: After saving changes to a configuration, the actions *Reset Default Values* and *Export* are available for it.](../../../images/system-settings-actions.png)

If you make some regrettable configuration decisions and can't recall exactly
what you did, start over by clicking the actions button
(![Actions](../../../images/icon-actions.png)), then clicking *Reset Default
Values*. 

## Configuration Scope [](id=configuration-scope)

While browsing the categories of System Settings, you'll notice that each entry
has a *Name* and a *Scope*. This scope is different than the overall
application configuration scope discussed in the introduction to this section.
This is the System Settings application after all, so all of the configuration
done here affects the System scope. The Scope field here refers to the scope at 
which this default, system-wide configuration can be overridden. There are four 
values that you'll see under Scope: 

- *System:* Any configuration made at the system scope here becomes the final
  value for the application in a system-wide fashion. It affects the whole
  system and cannot be overridden anywhere else. 

    ![Figure 4: Some System Settings entries have a System scope.](../../../images/system-settings-system-scope.png)

- *Default Configuration for Application:* Making configuration changes at this
  level affects the application in a system-wide fashion as well. However, while
  these become the system-wide defaults for the application, they can be 
  overridden from each application instance. 

    ![Figure 5: Some System Settings entries have a Default Configuration for Application scope.](../../../images/system-settings-application-scope.png)

- *Default Configuration for All Sites:* Configuration at this scope can be
  overridden in each site. 

    ![Figure 6: Some System Settings entries have a Default Configuration for All Sites scope.](../../../images/system-settings-site-scope.png)

- *Default Settings for All Instances:* Configuration at this scope can be
  overridden in each @product@ instance (usually via Instance Settings). 

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
Settings to configure the application, you can't later decide to use System
Settings. Only changes to Instance Settings will be recognized. 

<!-- Perhaps show a proof-of-concept example here? -->

<!-- Need a Lunar Resort example? -->

## Exporting and Importing Configurations [](id=exporting-and-importing-configurations)

What if you change many default configurations in System Settings, and then need 
to make the same changes in another @product@ installation? Don't worry, you 
don't need to remember and duplicate every choice you made. The System Settings 
application lets you export a single entry's configurations (e.g., the Dynamic 
Data Lists entry's configurations), or all the settings you made in the System 
Settings interface. Then you can deploy them in the target @product@ 
installation. 

To export a single entry's configurations, click the actions button
(![Actions](../../../images/icon-actions.png)), then click *Export*. A `.config` 
file then downloads that contains any configuration changes you saved. 

+$$$

**Note:** If you're running Liferay DE 7.0 Fix Pack 7 or earlier, or Liferay CE 
Portal 7.0 GA3 or earlier, System Settings exports a `.cfg` file instead of a 
`.config` file. The file format was changed to fix a bug with multi-value 
settings, which the `.cfg` format didn't handle properly. See
[the bug report](https://issues.liferay.com/browse/LPS-67890)
for more information. Any @product@ system newer than those listed in this note 
uses the `.config` file format. 

Although `.config` files are now the default file type exported by System 
Settings, you're free to continue using `.cfg` files--they're still supported by 
the configuration framework underlying System Settings. 

$$$

To export all the configuration changes you've made in System Settings, click 
the System Settings options button 
(![Options](../../../images/icon-options.png)), then click *Export All 
Settings*. The `.config` files for all the entries you edited then download in a 
ZIP file. 

To make these configurations active in the destination @product@ system, simply 
unzip and place the `.config` files in the `[Liferay_Home]/osgi/modules` folder. 

Awesome! Now you know what System Settings is and how to use it. All that's left 
is to explore the entries to see what configuration options you can make. If you 
aren't sure what something does, check the documentation for the feature you're 
interested in, as specific configurations are covered there. 
