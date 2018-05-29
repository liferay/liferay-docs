# Setting Search Container Animations [](id=setting-search-container-animations)

If you've toured @product-ver@'s UI, you've probably noticed animations in the
search containers. These animations show the user when there is no available
content.

![Figure 1: This is a still frame from the Blogs portlet's empty results animation.](../../../images/no-content-found-blog.png)

This tutorial covers the following topics:

- Using the default animations in your search container
- Using custom animations in your search container

First, you'll learn how to use the default animations in your search container.

## Using the Default Animations in Your Search Container [](id=using-the-default-animations-in-your-search-container)

There are three built-in classes for the search container animation:

1.  The default class is *taglib-empty-result-message-header*. This is used for
    most cases.

    ![Figure 2: When no content is found, the default animation is usually shown.](../../../images/no-content-found-blog.png)

2.  *taglib-empty-search-result-message-header* is used when there are no
    search results.

    ![Figure 3: You can use the empty search result animation to show that no search results were found.](../../../images/no-web-content-found-search.png)

3.  *taglib-empty-result-message-header-has-plus-btn* is used when there is no
    content, but you can use the add button to add an entity.

![Figure 4: If you can use the add button to add entities to the app, use the has plus button animation.](../../../images/no-tags-found-plus-button.png)

To use these animations, use the following method:

    SearchContainer.setEmptyResultsMessageCssClass()

For example, the Roles Admin application uses this code to set its animation in
its [`edit_role_assignments_sites.jsp`
file](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/foundation/roles/roles-admin-web/src/main/resources/META-INF/resources/edit_role_assignments_sites.jsp#L46-L48):

    if (!searchTerms.isSearch()) {
            searchContainer.setEmptyResultsMessageCssClass(
            "taglib-empty-result-message-header-has-plus-btn"
            );
    }

Alternatively you can use the `emptyResultsMessageCssClass` attribute of the 
[`liferay-ui:search-container`](https://docs.liferay.com/portal/7.0/taglibs/util-taglib/liferay-ui/search-container.html)
tag to set the animation. For example,

    <liferay-ui:search-container
      emptyResultsMessage="no-results-were-found"
      emptyResultsMessageCssClass="taglib-empty-result-message-header-has-plus-btn"
      ...
    >

If you don't want to use the default animations packaged with @product-ver@, you 
can use custom animations instead. This is covered next.

## Using Custom Animations [](id=using-custom-animations)

As stated earlier, each animation has a matching CSS class that the search
container uses. To use a custom animation, therefore, you must modify the
existing styles.

There are two approaches you can take:

- Overwrite the existing styles to replace the default animations
- Create new styles to make the animation available to the search container

Regardless of the approach you choose, you must provide the CSS styles in a 
[Theme](/develop/tutorials/-/knowledge_base/7-0/introduction-to-themes), 
[Themelet](/develop/tutorials/-/knowledge_base/7-0/themelets), or 
[Theme Contributor](/develop/tutorials/-/knowledge_base/7-0/theme-contributors). 
These styles point to the animation's source. You can provide the animation
however you like: as long as you have a valid URL (relative or absolute) that
points to the animation, you can use it.

The default search container animation styles are provided by the
[_empty_result_message.scss](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/foundation/frontend-css/frontend-css-web/src/main/resources/META-INF/resources/taglib/_empty_result_message.scss)
file.

+$$$

**Note:** Search containers can also contain static images for the no results 
message if you prefer. Just use a valid image type instead. All animations must 
be of type `GIF` though.

$$$

You can learn how to replace the default animations next. 

### Replacing Default Empty Results Message Animations [](id=replacing-default-empty-results-message-animations)

Follow these steps to replace the existing animations with your own:

1.  Make your custom animation available in your theme, themelet, Documents and 
    Media repository, etc. For example, place the `.gif` in your theme's
    `images` folder.
 
2.  Inside your CSS file (`_custom.scss` for example), override the animation 
    styles that you want to replace. For example, to replace the default 
    animation, include the following styles for a custom animation located in
    the `images/emoticons` folder of a theme:
    
        .taglib-empty-result-message {
            .taglib-empty-result-message-header {
                background-image: 
                url(@theme_image_path@/emoticons/[your_custom_animation].gif);
            }
        }

3.  Inside your app's search container, use the `emptyResultsMessageCssClass` 
    attribute, or use the `SearchContainer.setEmptyResultsMessageCssClass()` 
    method. Below is an example configuration that uses the
    `emptyResultsMessageCssClass` attribute:
    
        <liferay-ui:search-container
            emptyResultsMessage="no-results-were-found"
            emptyResultsMessageCssClass="taglib-empty-result-message-header"
            total="<%= total %>"
        >

    Here is an example configuration that uses the 
    `SearchContainer.setEmptyResultsMessageCssClass()` method:
    
        SearchContainer.setEmptyResultsMessageCssClass("taglib-empty-result-message-header")

Your custom animation now appears in the search container instead of the default
animation. If instead you want to add your custom animation to the default ones
available, follow the steps in the next section.

### Adding A New Empty Results Message Animation [](id=adding-a-new-empty-results-message-animation)

Adding an animation to the empty results message involves the same steps as
replacing a default animation. The only difference is you must add a new CSS
class. Follow these steps to create a new style for your custom search container
animation:

1.  Make your custom animation available in your theme, themelet, Documents and 
    Media repository, etc. For example, place the GIF in your theme's `images` folder.
 
2.  Inside your CSS file (`_custom.scss` for example), add the styles for your 
    new class, wrapped with `.taglib-empty-result-message`. For example, the 
    styles below add a custom animation for a class called 
    `my-custom-message-header`:
    
        .taglib-empty-result-message {
            .my-custom-message-header {
                background-image: 
                url(@theme_image_path@/emoticons/[my_custom_animation].gif);
            }
        }

3.  Use the `emptyResultsMessageCssClass` 
    attribute in the Search Container, or use the
    `SearchContainer.setEmptyResultsMessageCssClass()` method to use the new CSS
    class you just added. Below is an example configuration that uses the
    `emptyResultsMessageCssClass` attribute:
    
        <liferay-ui:search-container
            emptyResultsMessage="no-results-were-found"
            emptyResultsMessageCssClass="my-custom-message-header"
            total="<%= total %>"
        >

    Here is an example configuration that uses the 
    `SearchContainer.setEmptyResultsMessageCssClass()` method:
    
        SearchContainer.setEmptyResultsMessageCssClass("my-custom-message-header")

Now you know how to set search container animations in your app!

## Related Topics [](id=related-topics)

[Using the Liferay UI Taglib](/develop/tutorials/-/knowledge_base/7-0/using-the-liferay-ui-taglib)

[Introduction to Liferay Search](/develop/tutorials/-/knowledge_base/7-0/introduction-to-liferay-search)
