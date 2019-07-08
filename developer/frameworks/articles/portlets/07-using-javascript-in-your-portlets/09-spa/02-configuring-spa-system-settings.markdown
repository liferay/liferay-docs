---
header-id: configuring-spa-system-settings
---

# Configuring SPA System Settings

[TOC levels=1-4]

Depending on what behaviors you need to customize, you can configure SPA options 
in one of two places. SPA caching and SPA timeout settings are configured in 
System Settings. If you wish to disable SPA for a certain link, page, or portlet 
in your site, see [Disabling SPA](/docs/7-2/frameworks/-/knowledge_base/f/disabling-spa). 

Follow these steps to configure system settings for SPA:

1.  In the Control Panel, navigate to *Configuration* &rarr; *System Settings*.

2.  Select *Infrastructure* under the *PLATFORM* heading.

3.  Click *Frontend SPA Infrastructure*.

The following configuration options are available:

**Cache Expiration Time**:  The time, in minutes, in which the SPA cache is 
cleared. A negative value means the cache should be disabled. 

**Navigation Exception Selectors**: Defines a CSS selector that SPA should 
ignore. 

**Request Timeout Time**: The time, in milliseconds, in which a SPA request 
times out. A zero value means the request should never timeout. 

**User Notification Timeout**: The time, in milliseconds, in which a 
notification is shown to the user stating that the request is taking longer than 
expected. A zero value means no notification should be shown. 

Great! Now you know how to configure system settings for SPA. 

## Related Topics

- [Disabling SPA](/docs/7-2/frameworks/-/knowledge_base/f/disabling-spa)
- [Specifying How Resources Are Loaded During SPA Navigation](/docs/7-2/frameworks/-/knowledge_base/f/specifying-how-resources-are-loaded-during-navigation)
- [Detaching Global Listeners](/docs/7-2/frameworks/-/knowledge_base/f/detaching-global-listeners)
