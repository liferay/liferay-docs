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
configuration changes at the system scope in Liferay 7, and you don't need to go
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

![Figure 3: Each System Settings entry will have a configuration Scope.](../../../images/system-settings-actions.png)

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

<!-- Perhaps show a proof-of-concept example here? -->

<!-- Need a Lunar Resort example? -->

+$$$

**Setting SPA Cache Configuration Options**

In prior versions of Liferay, all SPA render requests that didn't belong to a 
portlet (no `p_p_id` in the URL) were cached indefinitely. This can confuse
users, as the content they view will be cached, rather than the latest fresh
content. Since Liferay CE 7.0 GA2 and DXP 7.0 GA1, administrators can configure
an expiration time for the Senna cache in System Settings.

To set an expiration time for the SPA cache, open the Control Panel and go to 
*Configuration* &rarr; *System Settings*. Click the *Foundation* heading and
select the *Frontend SPA Infrastructure* setting. Set a positive value 
in minutes for the duration of the cache during SPA navigation. Leaving
the default value of -1 caches the content indefinitely.

$$$

## Exporting and Importing Configurations [](id=exporting-and-importing-configurations)

What if you change a lot of default configurations in System Settings, and then you
need to make the same configurations in another Liferay installation <!--Another
cluster?-->? Don't worry, you won't need to remember every choice you make, then
manually duplicate all the configurations. The System Settings application lets
you export individual configurations (for example, just the configurations made
to the Dynamic Data Lists entry), or export all of the settings you've made in
the System Settings interface. Then you can just deploy them in the target
Liferay installation.

In the case of a single entry, just click the actions button
(![Actions](../../../images/icon-actions.png)), then click *Export*. A `.cfg` file
will be downloaded, containing any configuration changes you've saved. 

To export all of the configuration changes you've made in System Settings, click
the System Settings options button (![Options](../../../images/icon-options.png)),
then click *Export All Settings*. You'll get a zip file with `.cfg` files
corresponding to all of the entries that you edited.

To make these configurations active in the destination Liferay system, simply
unzip and place the `.cfg` files in the `[Liferay_Home]/osgi/modules` folder.

Now you know what System Settings is and how to use it. All that's left is
exploring the entries to see what configuration options you can make. If you
aren't sure what something does, check the documentation for the feature you're
interested in, as specific configurations will be covered there.

