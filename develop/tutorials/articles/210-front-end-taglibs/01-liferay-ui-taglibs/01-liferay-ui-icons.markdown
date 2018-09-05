# Liferay UI Icons [](id=liferay-ui-icons)

The Liferay UI taglibs provide several icons you can include in your apps. To 
add an icon to your app, use the `liferay-ui:icon` tag and specify the icon with 
either the `icon`, `iconCssClass`, or `image` attribute. An example of each use 
case is shown below.

The `image` attribute specifies 
[Liferay UI icons](https://github.com/liferay/liferay-portal/tree/7.1.x/modules/apps/frontend-theme/frontend-theme-unstyled/src/main/resources/META-INF/resources/_unstyled/images) 
to use (as defined in the Unstyled theme's `images/common` folder). Here's an
example configuration:

    <div class="col-md-3">
    	<liferay-ui:icon image="subscribe" />

    	<span class="ml-2">Subscribe</span>
    </div>

![Figure 1: Use the image attribute to use a theme icon.](../../../images/liferay-ui-taglib-icon-subscribe.png)

The Liferay UI taglib also exposes language flag icons. To use a language flag 
icon, provide the `../language/` relative path before the icon's name. Below is
an example snippet from the Web Content Search portlet that displays the
current language's flag along with a localized message:

    <liferay-ui:icon
        image='<%= "../language/" + languageId %>'
        message='<%= LanguageUtil.format(
            request,
            "this-result-comes-from-the-x-version-of-this-content",
            snippetLocale.getDisplayLanguage(locale),
            false
        ) %>'
    />

The full list of available icons is shown in the figures below:

![Figure 2: The Liferay UI taglib offers multiple icons for use in your app.](../../../images/liferay-ui-taglib-icons.png)

![Figure 3: Liferay UI icons can be configured based on language.](../../../images/liferay-ui-taglib-icon-flags.png)

The `icon` attribute specifies 
[Font Awesome icons](https://fontawesome.com/v3.2.1/icons/) 
to use:

    <liferay-ui:icon icon="angle-down" />

![Figure 4: You can use the icon attribute to include Font Awesome icons in your app.](../../../images/liferay-ui-taglib-icon-angle-down.png)

The `iconCssClass` attribute specifies a 
[glyphicon](http://marcoceppi.github.io/bootstrap-glyphicons/) 
to use:

    <liferay-ui:icon
        iconCssClass="icon-remove-sign"
        label="<%= true %>"
        message="unsubscribe"
        url="<%= unsubscribeURL %>"
    />

![Figure 5: You can use Font Awesome icons in your app.](../../../images/liferay-ui-taglib-icon-css-class.png)

The examples above use some of the icon's available attributes. See the 
[Icon taglibdocs](@platform-ref@/7.1-latest/taglibs/util-taglib/liferay-ui/icon.html) 
for the full list. 

## Related Topics [](id=related-topics)

[Clay Icons](/develop/tutorials/-/knowledge_base/7-1/clay-icons)

[Liferay UI Icon Lists](/develop/tutorials/-/knowledge_base/7-1/liferay-ui-icon-lists)

[Liferay UI Icon Menus](/develop/tutorials/-/knowledge_base/7-1/liferay-ui-icon-menus)
