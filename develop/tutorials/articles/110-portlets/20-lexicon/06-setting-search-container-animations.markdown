# Setting Search Container Animations [](id=setting-search-container-animations)
<!-- Can theme contributors include images? -->

If you've taken a tour around @product-ver@'s UI, you've probably noticed some 
new animations in the search containers. These animations let the user know that 
there is no available content. Below is a frame from the animation that is 
displayed for the Blogs portlet:

![Figure 1: ](../../../images/no-content-found-blog.png)

This tutorial covers the following topics:
- Using the default animations in your search container
- Using custom animations in your search container

Get started by learning how to use the default animations in your search 
container first.

## Using the Default Animations in Your Search Container

Each animation corresponds to a matching class. There are three built-in classes 
that you can choose from for the animation:

"taglib-empty-result-message-header" which is the default one

![Figure 2: ](../../../images/no-content-found-blog.png)

"taglib-empty-search-result-message-header" used when searching

![Figure 3: ](../../../images/no-web-content-found-search.png)

"taglib-empty-result-message-header-has-plus-btn" used when there is a plus 
button:

![Figure 4: ](../../../images/no-tags-found-plus-button.png)

To use these animations in your app you can use the following method:

    SearchContainer.setEmptyResultsMessageCssClass()

For example, the Roles Admin application uses the following code to set its 
animation in its [`edit_role_assignments_sites.jsp` file](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/foundation/roles/roles-admin-web/src/main/resources/META-INF/resources/edit_role_assignments_sites.jsp#L46-L48):

    if (!searchTerms.isSearch()) {
            searchContainer.setEmptyResultsMessageCssClass(
            "taglib-empty-result-message-header-has-plus-btn"
            );
    }
    
Alternatively you can use the `emptyResultsMessageCssClass` attribute of your 
`liferay-ui:search-container` tag to set the animation. Below is an example 
snippet from the [`com.liferay.users.admin.web` module's `view_tree.jsp`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/foundation/users-admin/users-admin-web/src/main/resources/META-INF/resources/view_tree.jsp#L158):
    
    <liferay-ui:search-container
      emptyResultsMessage="no-results-were-found"
      emptyResultsMessageCssClass="taglib-empty-result-message-header-has-plus-btn"
      ...
    >

If you don't want to use the default animations packaged with @product-ver@, you 
can use custom animations instead. This is covered next.

## Using Custom Animations

As stated earlier, each animation has a matching CSS class that the search 
container uses. So, to use a custom animation you have to modify the existing 
styles.

There are two approaches you can take:
- Overwrite the existing styles to replace the default animations
- Create new styles to make the animation available to the search container

Regardless of the approach you choose, you must provide the CSS styles in a 
[Theme](/develop/tutorials/-/knowledge_base/7-0/introduction-to-themes),  
[Themelet](/develop/tutorials/-/knowledge_base/7-0/themelets), or 
[Theme Contributor](/develop/tutorials/-/knowledge_base/7-0/theme-contributors). 
These styles point to the animation's source. You can provide the animation 
however you like: a theme or themelet, upload it to your Documents and Media 
repository in your portal instance, etc. As long as you have a valid URL 
(relative or absolute) that points to the animation, you can use it.

The default search container animation styles are provided by the [_empty_result_message.scss](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/foundation/frontend-css/frontend-css-web/src/main/resources/META-INF/resources/taglib/_empty_result_message.scss)

+$$$

**Note:** Search containers can also contain static images for the no results 
message if you prefer. Just use a valid image type instead. All animations must 
be of type `GIF` though.

$$$

You can learn how to replace the default animations next. 

### Replacing Default Empty Results Message Animations

Follow these steps to replace the existing animations with your own:

1.  Make your custom animation available in your theme, themelet, documents and 
    media repo, etc. For example, place the GIF in your theme's `images` folder.
    
2.  Inside your CSS file (`_custom.scss` for example), override the animation 
    styles that you want to replace. For example, to replace the default 
    animation, you would include the following styles for a custom animation 
    located in the `images/emoticons` folder of a theme:
    
        .taglib-empty-result-message {
            .taglib-empty-result-message-header {
                background-image: 
                url(@theme_image_path@/emoticons/[your_custom_animation].gif);
            }
        }

3.  Inside your app's search container, use the `emptyResultsMessageCssClass` 
    attribute, or use the `SearchContainer.setEmptyResultsMessageCssClass()` 
    method to use the CSS class for the animation you replaced. Below is an 
    example configuration that uses the `emptyResultsMessageCssClass` attribute:
    
        <liferay-ui:search-container
            emptyResultsMessage="no-results-were-found"
            emptyResultsMessageCssClass="taglib-empty-result-message-header"
            total="<%= total %>"
        >

    Here is an example configuration that uses the 
    `SearchContainer.setEmptyResultsMessageCssClass()` method:
    
        SearchContainer.setEmptyResultsMessageCssClass("taglib-empty-result-message-header")

Your app will now display your custom animation in the search container instead 
of the default animation. If instead you want to add your custom animation to 
the default ones available, follow the steps in the next section.

### Adding A New Empty Results Message Animation

Adding an animation to the empty results message involves the same steps as 
replacing a default animation. The only difference between replacing an 
animation and adding an animation is that adding an animation requires a new 
CSS class. Follow these steps to create a new style for your custom search 
container animation:

1.  Make your custom animation available in your theme, themelet, documents and 
    media repo, etc. For example, place the GIF in your theme's `images` folder.
    
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

3.  Inside your app's search container, use the `emptyResultsMessageCssClass` 
    attribute, or use the `SearchContainer.setEmptyResultsMessageCssClass()` 
    method to use the new CSS class you just added. Below is an example 
    configuration that uses the `emptyResultsMessageCssClass` attribute:
    
        <liferay-ui:search-container
            emptyResultsMessage="no-results-were-found"
            emptyResultsMessageCssClass="my-custom-message-header"
            total="<%= total %>"
        >

    Here is an example configuration that uses the 
    `SearchContainer.setEmptyResultsMessageCssClass()` method:
    
        SearchContainer.setEmptyResultsMessageCssClass("my-custom-message-header")

Now you know how to set search container animations in your app!

## Related Topics

[Using the Liferay UI Taglib](/develop/tutorials/-/knowledge_base/7-0//develop/tutorials/-/knowledge_base/7-0/using-the-liferay-ui-taglib)

[Introduction to Liferay Search](/develop/tutorials/-/knowledge_base/7-0/introduction-to-liferay-search)