# Configuring Asset Auto Tagging

[Tagging assets](/discover/portal/-/knowledge_base/7-2/tagging-content) 
is a great way to organize content. Typically, the content creator applies tags 
while creating the content. It's also possible, however, to automatically tag 
content. For example, @product@ can scan an image on upload and automatically 
apply tags that describe the image's content. This lets you leverage tags 
without requiring content creators to manually apply them. 

| **Note:** Auto-tagging currently works only for images, text-based documents, 
| and text-based web content. 

Here, you'll learn how to configure asset auto-tagging in general. This is 
required prior to configuring auto-tagging for specific asset types, which is 
documented separately: 

-   [Auto Tagging Images](/discover/portal/-/knowledge_base/7-2/auto-tagging-images)
-   [Auto Tagging Documents](/discover/portal/-/knowledge_base/7-2/auto-tagging-documents)
-   [Auto Tagging Web Content](/discover/portal/-/knowledge_base/7-2/auto-tagging-web-content)

## Configuration Levels

Auto-tagging is enabled by default. You can configure it at three levels:

**Global (System):** For auto-tagging to function on any level, it must be 
enabled globally. You can also set the default auto-tagging configuration for 
every portal instance. 

**Instance:** When enabled globally, auto-tagging is also enabled by default for 
each portal instance. However, you can override the global auto-tagging 
configuration on a per-instance basis. 

**Site:** When enabled for an instance, auto-tagging is also enabled by default 
for all that instance's sites. You can disable it for specific sites. 

### Global Configuration [](id=global-configuration)

Follow these steps to configure auto tagging globally: 

1.  Go to *Control Panel* &rarr; *Configuration* &rarr; *System Settings* &rarr; 
    *Assets*. 

2.  Under *SYSTEM SCOPE*, select *Asset Auto Tagging*. 

3.  The following settings are available:

    **Enable Auto Tagging of Assets:** Whether asset auto tagging is enabled.

    **Maximum Number of Tags:** The maximum number of tags that can be 
    automatically applied to each asset. The default value of `0` means that 
    there is no limit.

4.  Click *Save* to save your changes. 

To set the default auto-tagging configuration for all instances, select 
*Asset Auto Tagging* under *VIRTUAL INSTANCE SCOPE*. The available settings are 
exactly the same as those in the SYSTEM SCOPE. 

![Figure 1: You can configure auto tagging globally in the Assets section of System Settings.](../../../images/auto-tagging-global.png)

### Instance-level Configuration [](id=instance-level-configuration)

When enabled globally, auto-tagging is also enabled by default for each 
instance. You can, however, disable or configure it for each instance. 

Follow these steps to configure auto tagging on the instance level: 

1.  Go to *Control Panel* &rarr; *Configuration* &rarr; *Instance Settings* 
    &rarr; *Assets*. 

2.  Under *VIRTUAL INSTANCE SCOPE*, select *Asset Auto Tagging*. 

3.  The settings here are identical to those in the global configuration, but 
    apply only to the current instance. 

3.  Click *Save* to save your changes. 

![Figure 2: You can also configure auto tagging for each instance.](../../../images/auto-tagging-instance.png)

### Site-level Configuration [](id=site-level-configuration)

When enabled for an instance, auto-tagging is also enabled by default for all 
that instance's sites. You can, however, enable or disable it for each site. 

Follow these steps to configure auto tagging for a site: 

1.  Open the *Menu* 
    (![Product Menu](../../../images/icon-menu.png)), 
    click your site's name, and navigate to *Configuration* &rarr; *Settings*. 

2.  In the *General* tab, expand the *Asset Auto Tagging* section. Use the 
    toggle to enable or disable auto tagging for the site. 

3.  Click *Save* to save your changes. 

![Figure 3: You can enable or disable auto-tagging for a site.](../../../images/auto-tagging-site.png)
