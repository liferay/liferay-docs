# Configuring Asset Auto Tagging

[Tagging assets](/discover/portal/-/knowledge_base/7-2/tagging-content) 
is a great way to organize content. Typically, the content creator applies tags 
while creating the content. It's also possible, however, to *automatically* tag 
content. For example, @product@ can scan an image on upload and automatically 
apply tags that describe the image's content. This lets you leverage tags 
without requiring content creators to manually apply them. 

| **Note:** Auto-tagging currently only works for images, text-based documents, 
| and text-based web content. 

This article shows you how to configure asset auto-tagging in general. 
Configuring auto-tagging for specific asset types is documented separately. For 
example, see the article 
[Auto-tagging Images](/discover/portal/-/knowledge_base/7-1/auto-tagging-images) 
for instructions on configuring image auto-tagging. 

## Configuring Auto-tagging [](id=configuring-auto-tagging)

Auto-tagging is disabled by default. You can configure it at three levels:

-   **Global:** For auto-tagging to function on any level, you must enable it 
    globally. You can then disable it for individual instances and sites if you 
    wish. 

-   **Instance:** When enabled globally, auto-tagging is also enabled by default 
    for each instance. You can disable it for specific instances. 

-   **Site:** When enabled for an instance, auto-tagging is also enabled by 
    default for all that instance's sites. You can disable it for specific 
    sites. 

### Global Configuration [](id=global-configuration)

Follow these steps to configure auto-tagging globally: 

1.  Go to *Control Panel* &rarr; *Configuration* &rarr; *System Settings*, 
    then select *Assets*. 

2.  Under *SYSTEM SCOPE*, select *Asset Auto Tagging*. 

3.  The following settings are available:

    -   **Enabled:** Whether auto-tagging of assets is enabled.
    -   **Maximum Number of Tags:** The maximum number of tags that can be 
        automatically applied to each asset. A value of `0` means that there is 
        no limit.

4.  Click *Save* to save your changes. 

![Figure 1: You can configure auto-tagging globally in the Assets section of System Settings.](../../../images/auto-tagging-global.png)

### Instance-level Configuration [](id=instance-level-configuration)

When enabled globally, auto-tagging is also enabled by default for each 
instance. You can, however, disable or configure it for each instance. 

Follow these steps to configure auto-tagging on the instance level: 

1.  Go to *Control Panel* &rarr; *Configuration* &rarr; *Instance Settings*, 
    then select the *Asset Auto Tagging* tab. 

2.  The settings here are identical to those in the global configuration, but 
    apply only to the instance. Also note that there may be different 
    auto-tagging providers here that you can configure for specific asset types. 
    For example, the provider for image auto-tagging is 
    *TensorFlow Image Auto Tagging*. Such providers are documented elsewhere. 

3.  Click *Save* to save your changes. 

![Figure 2: You can also configure auto-tagging on the instance level.](../../../images/auto-tagging-instance.png)

### Site-level Configuration [](id=site-level-configuration)

When enabled for an instance, auto-tagging is also enabled by default for all 
that instance's sites. You can, however, enable or disable it for each site. 

Follow these steps to configure auto-tagging for a site: 

1.  Open the *Menu* 
    (![Product Menu](../../../images/icon-menu.png)), 
    click your site's name, and navigate to *Configuration* &rarr; 
    *Site Settings*. 

2.  In the *General* tab, expand the *Asset Auto Tagging* section. Use the 
    toggle to enable or disable auto-tagging for the site. 

3.  Click *Save* to save your changes. 

![Figure 3: You can enable or disable auto-tagging for a site.](../../../images/auto-tagging-site.png)
