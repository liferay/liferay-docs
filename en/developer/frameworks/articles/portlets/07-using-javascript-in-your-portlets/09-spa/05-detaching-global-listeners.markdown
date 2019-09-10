---
header-id: detaching-global-listeners
---

# Detaching Global Listeners

[TOC levels=1-4]

SPA provides several improvements that highly benefit your site and users, but 
with great power comes great re-SPA-nsibility. I apologize for that last 
sentence, but the fact remains that there is potentially some additional 
maintenance as a consequence of SPA. In a traditional navigation scenario, every 
page refresh resets everything, so you don't have to worry about what's left 
behind. In a SPA scenario, however, global listeners such as `Liferay.on`, 
`Liferay.after`, or body delegates can become problematic. Every time you 
execute these global listeners, you add yet another listener to the globally 
persisted `Liferay` object. The result is multiple invocations of those 
listeners. This can obviously cause problems if not handled. 

Follow these steps to prevent potential problems:

1.  Listen to the navigation event in order to detach your listeners. For 
    example, you would use the following code to listen to a global `category` 
    event and `destroyPortlet` event:

    ```javascript
    Liferay.on('category', function(event){...});
    Liferay.on('destroyPortlet', function(event){...});
    ```
    
2.  Detach the event listeners when the portlet is destroyed, as shown in the 
    example below:

    ```javascript
    var onCategory = function(event) {...};

    var clearPortletHandlers = function(event) {
        if (event.portletId === '<%= portletDisplay.getRootPortletId() %>') {
            Liferay.detach('onCategoryHandler', onCategory);
            Liferay.detach('destroyPortlet', clearPortletHandlers);
        }
    };


    Liferay.on('category', onCategory);
    Liferay.on('destroyPortlet', clearPortletHandlers);
    ```

Great! Now you know how to properly maintain your global listeners for SPA. 

## Related Topics

- [Available SPA Lifecycle Events](/docs/7-2/reference/-/knowledge_base/r/available-spa-lifecycle-events)
- [Disabling SPA](/docs/7-2/frameworks/-/knowledge_base/f/disabling-spa)
- [Configuring SPA System Settings](/docs/7-2/frameworks/-/knowledge_base/f/configuring-spa-system-settings)
