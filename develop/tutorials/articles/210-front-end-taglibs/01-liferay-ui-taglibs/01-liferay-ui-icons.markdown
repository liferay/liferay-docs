# Liferay UI Icons [](id=liferay-ui-icons)

The Liferay UI taglibs provide several icons you can include in your apps. To 
add an icon to your app, use the `liferay-ui:icon` tag and specify the icon with 
either the `icon`, `iconCssClass`, or `image` attribute. An example of each use 
case is shown below.

The `image` attribute specifies Liferay UI icons to use. Below is an example 
snippet from the Web Content Search portlet that displays the current language's 
flag along with the search results: 

    <liferay-ui:icon 
        image='<%= "../language/" + languageId %>' 
        message='<%= LanguageUtil.format(
            request, 
            "this-result-comes-from-the-x-version-of-this-content", 
            snippetLocale.getDisplayLanguage(locale), 
            false
        ) %>' 
    />
    
![Figure 1: Liferay UI icons can be cofigured based on language.](../../../images/liferay-ui-taglib-icon-image.png)

The full list of available Liferay UI icons are listed in the Unstyled theme's 
[`images/common` folder](https://github.com/liferay/liferay-portal/tree/7.1.x/modules/apps/foundation/frontend-theme/frontend-theme-unstyled/src/main/resources/META-INF/resources/_unstyled/images/common).

The `icon` attribute specifies Font Awesome icons to use:

    <liferay-ui:icon icon="angle-down" />

![Figure 2: You can use the icon attribute to include Font Awesome icons in your app.](../../../images/liferay-ui-taglib-icon-angle-down.png)

See the [Font Awesome site](https://fontawesome.com/v3.2.1/icons/) 
for a full list of the available icons.

The `iconCssClass` attribute specifies a glyphicon to use:

    <liferay-ui:icon
        iconCssClass="icon-remove-sign"
        label="<%= true %>"
        message="unsubscribe"
        url="<%= unsubscribeURL %>"
    />

![Figure 3: You can use Font Awesome icons in your app.](../../../images/liferay-ui-taglib-icon-css-class.png)

See 
[http://marcoceppi.github.io/bootstrap-glyphicons/](http://marcoceppi.github.io/bootstrap-glyphicons/) 
for the full list of available glyphicons. 

The examples above use some of the icon's available attributes, see the 
[Icon taglibdocs](@platform-ref@/7.1-latest/taglibs/util-taglib/liferay-ui/icon.html) 
for the full list. 

## Related Topics [](id=related-topics)

[Clay Icons](/develop/tutorials/-/knowledge_base/7-1/clay-icons)

[Liferay UI Icon Lists](/develop/tutorials/-/knowledge_base/7-1/liferay-ui-icon-lists)

[Liferay UI Icon Menus](/develop/tutorials/-/knowledge_base/7-1/liferay-ui-icon-menus)
