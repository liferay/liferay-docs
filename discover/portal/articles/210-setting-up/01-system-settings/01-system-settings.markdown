# System Settings [](id=system-settings)

@product@ is modular, meaning it's composed of many applications, which are
divided into even smaller "chunks" of functionality. The applications, and
sometimes even code chunks, are configurable at several scopes, as discussed in
the introductory article for this section. 

In System Settings, administrators make system scoped configuration changes and
set system-wide default configurations for other scopes. System Settings is
located in Control Panel &rarr; Configuration &rarr; System Settings. 

![Figure 1: System Settings are accessed through the Control Panel.](../../../images/system-settings-categories.png)

## Editing System Configurations

System Settings is organized into sections (for example, Content) and categories
(for example, Workflow) based on the functionality being configured.  There's
also a Search bar to make finding configuration entries easier. Search for the
name of a specific configuration entry, or even a specific field within an
entry.

![Figure 2: System Settings are organized by component.](../../../images/system-settings-nav-search.png)

Changing an application's configuration values isn't difficult: 

1.  Find the configuration you need, either by searching or navigating through
    the sections and categories.

2.  Open the configuration form for the entry. Click the name of the component
    you want to configure. 

3.  Make any changes you'd like, then click *Save*. Your configuration changes
    are saved and applied throughout the system. 

![Figure 3: After saving changes to a configuration, the actions *Reset Default Values* and *Export* are available for it.](../../../images/system-settings-actions.png)

If you make some regrettable configuration decisions and can't recall exactly
what you did, start over by clicking the actions button
(![Actions](../../../images/icon-actions.png)), then clicking *Reset Default
Values*. 

## Configuration Scope [](id=configuration-scope)

While browsing the categories of System Settings, you'll notice that clicking
into a topic (for example, Blogs) reveals entries at different scopes. All the
settings here act at the system scope. For scopes other than System, these
configurations act as defaults. In other words, they identify where the
system-wide configuration is overridden. System scoped configurations are, of
course, not overridden at more granular scopes. There are four values that
you'll see under Scope:

- *System:* Any System Settings configuration made for system scoped entries
    becomes the final value for the application in a system-wide fashion. It
    affects the whole system and isn't overridden anywhere else. 

    ![Figure 4: Some System Settings entries are system scoped.](../../../images/system-settings-system-scope.png)

- *Virtual Instance Scope:* Sets the default value for each Configuration at the
    Virtual Instance Scope is overridden in Instance Settings.

    ![Figure 7: Some System Settings are virtual instance scoped.](../../../images/system-settings-instance-scope.png)

- *Site Scope:* Configuration at this scope is overridden in each site. 

    ![Figure 6: Some System Settings are site scoped.](../../../images/system-settings-site-scope.png)

- *Widget Scope:* Configuration at this scope is overridden in each Widget
    Instance (like the Blogs example below). 

    ![Figure 5: Some System Settings entries are widget scoped.](../../../images/system-settings-application-scope.png)

If a configuration changed in System Settings is also configurable at a
different scope, the System Settings value acts as a default that can be
overridden. Once a configuration change is made at a more granular scope, making
a change at the system level won't do anything. 

For example, whether to allow comments is configurable for each Blogs Entry. Set
the default behavior at Control Panel &rarr; Configuration &rarr; System
Settings &rarr; Blogs. In the Blogs Entry under Widget Scope, disable the
*Enable Comments* checkbox. 

Now add a Blog Entry to a site's Content &rarr; Blogs application. Then go to a
public page and add the Blogs Widget to the page. Click the Actions button
(![Actions](../../../images/icon-actions.png)) for the Widget and select
*Configuration*. You'll see the same Enable Comments checkbox, and its default
is now false (unchecked). Checking the box in the Widget Configuration screen
breaks its link with the System Settings entry. Changing the System Settings
configuration does nothing to this widget anymore.

If an application is configurable from Instance Settings and System Settings,
use System Settings to configure it whenever possible. If you use Instance
Settings and later want to revert to using the System Settings default
configurations, look for the *Reset Values* button from the Instance Settings
entry. 

![Figure 8: Some Instance Settings entries have a *Reset Values* button so you can safely revert your configuration changes, falling back to the System Settings defaults.](../../../images/instance-settings-reset-values.png)

The Reset Values button removes configuration values from the database so you
can rely on the defaults that were provided out-of-the-box. If there is no Reset
Values button for an Instance Settings entry, once you use Instance Settings to
configure the application, you can't later decide to use System Settings. Only
changes to Instance Settings will be recognized. 

## Exporting and Importing Configurations [](id=exporting-and-importing-configurations)

What if you change many default configurations in System Settings, and then need
to make the same changes in another installation? Don't worry, you
don't need to remember and duplicate every choice you made. The System Settings
application lets you export a single entry's configurations, or all the settings
you made in the System Settings interface. The exported files are deployable to
any other installation of the same version.

To export a single entry's configurations, click the actions button
(![Actions](../../../images/icon-actions.png)), then click *Export*. A `.config` 
file then downloads that contains any configuration changes you saved. 

To export all the configuration changes you've made in System Settings, click 
the System Settings options button 
(![Options](../../../images/icon-options.png)), then click *Export All 
Settings*. The `.config` files for all the entries you edited then download in a 
ZIP file. 

To make these configurations active in the destination system, simply 
unzip and place the `.config` files in the `[Liferay_Home]/osgi/configs` folder. 

Now you know what System Settings is and how to use it. All that's left is to
explore the entries to see what configuration options are available. If you
aren't sure what something does, check the documentation for the feature you're
interested in, as specific configurations are covered there. 
