---
header-id: setting-empty-results-messages
---

# Setting Empty Results Messages

[TOC levels=1-4]

If you've toured the UI, you've probably noticed messages or possibly even 
animations in the search containers when no results are found. 

![Figure 1: This is a still frame from the Web Content portlet's empty results animation.](../../../../images/no-web-content-found.png)

You can configure your apps to use empty results messages and animations too, 
thanks to the `liferay-frontend:empty-results-message` tag. 

Follow these steps:

1.  Add the `liferay-frontend` taglib declaration into your portlet's 
    `init.jsp`:

    ```markup
    <%@ taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %>
    ```

2.  Add an [`empty-result-message` tag](@app-ref@/frontend-taglib/latest/taglibdocs/liferay-frontend/empty-result-message.html) 
    to your portlet's view:

    ```markup
    <liferay-frontend:empty-result-message />
    ```

3.  Configure the tag's attributes to define your search container's empty 
    results message, with or without an animation or image. The attributes are 
    described in the table below:
    
    | Attribute | Description |
    | --- | --- |
    | `actionDropdownItems` | Specifies the action or actions to display for the empty results in either a dropdown menu, a link, or a button, depending on the number of available actions. |
    | `animationType` | The CSS class for the animation. Four values are available by default with these CSS classes: `EmptyResultMessageKeys.AnimationType.EMPTY` (`taglib-empty-state`), `EmptyResultMessageKeys.AnimationType.SEARCH` (`taglib-search-state`), `EmptyResultMessageKeys.AnimationType.SUCCESS` (`taglib-success-state`), and `EmptyResultMessageKeys.AnimationType.NONE`. You can also specify a custom CSS class if you prefer. |
    | `componentId` | Specifies the ID for the `actionDropdownItems` component (dropdown menu, link, or button)|
    | `description` | The descriptive text to display beneath the main message. |
    | `elementType` | The type of element to replace the `x` parameter in the main message's language key `no-x-yet`. |

    An example configuration is shown below:

    ```markup
    <liferay-frontend:empty-result-message
        actionDropdownItems="<%= (availableSegmentsEntries.size() > 0) ? 
        editAssetListDisplayContext.getAssetListEntryVariationActionDropdownItems() : null %>"
        animationType="<%= EmptyResultMessageKeys.AnimationType.NONE %>"
        componentId='<%= renderResponse.getNamespace() + "emptyResultMessageComponent" %>'
        description='<%= LanguageUtil.get(request, "no-personalized-variations-were-found") %>'
        elementType='<%= LanguageUtil.get(request, "personalized-variations") %>'
    />
    ```

    | **Note:** You can replace the available default animations with your own 
    | by overriding the `taglib-empty-state`, `taglib-search-state`, and 
    | `taglib-success-state` CSS classes provided by 
    | [_empty_result_message.scss](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/frontend-css/frontend-css-web/src/main/resources/META-INF/resources/taglib/_empty_result_message.scss), 
    | or by replacing the existing animations in the `@theme_image_path@/states/` 
    | folder. Alternatively, you can also provide a new CSS class that defines 
    | the animation and use it for the `animationType` attribute's value. 

empty_state.gif:

![Figure 2: If you can use the add button to add entities to the app, use the empty state animation.](../../../../images/empty_state.gif)

search_state.gif:

![Figure 3: If you can use the add button to add entities to the app, use the search state animation.](../../../../images/search_state.gif)

success_state.gif:

![Figure 4: If you can use the add button to add entities to the app, use the success state animation.](../../../../images/success_state.gif)

| **Note:** Empty results messages can also contain static images if you prefer. 
| Just use a valid image type instead. All animations must be of type `GIF` 
| though. 

Great! Now you know how to configure your app to display an empty results 
message. 

## Related Topics

- [Using the Liferay Front-End Taglib](/docs/7-2/reference/-/knowledge_base/r/using-liferay-front-end-taglibs-in-your-portlet)
- [Applying the Add Button Pattern](/docs/7-2/frameworks/-/knowledge_base/f/applying-the-add-button-pattern)
