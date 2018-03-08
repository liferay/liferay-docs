# Using Lexicon Icons in Your App [](id=using-lexicon-icons-in-your-app)

Whether you're updating your app to @product-ver@ or writing a new 
@product-ver@ app, follow the process here to use Lexicon's icons. 
You can find the list of available Lexicon icons on the 
[Lexicon site](https://lexiconcss.wedeploy.io/content/icons-lexicon/).

Lexicon icons are defined with the `icon` attribute. For example, you define the
icon in the management bar, inside the
`liferay-frontend:management-bar-sidenav-toggler-button` taglib:

    <liferay-frontend:management-bar-sidenav-toggler-button
            disabled="<%= false %>"
            href="javascript:;"
            icon="info-circle"
            label="info"
            sidenavId='<%= liferayPortletResponse.getNamespace() + "infoPanelId" %>'
    />

To use Lexicon icons outside the management bar, you have two options:

1.  You can use the [`liferay-ui:icon`
    taglib](@platform-ref@/7.0-latest/taglibs/util-taglib/liferay-ui/icon.html).
    For example:

        <liferay-ui:icon

            icon="icon-name"

            markupView="lexicon"

            message="message-goes-here"

        />

2.  You can use the [`aui:icon`
    taglib](@platform-ref@/7.0-latest/taglibs/util-taglib/aui/icon.html). For
    example:

        <aui:icon

             cssClass="icon-monospaced"

             image="times"

             markupView="lexicon"

        />

Note the addition of the `markupView="lexicon"` attribute. This ensures that the
HTML is rendered with Lexicon markup.

That's it! Now you know how to use Lexicon icons in your apps.

## Related Topics [](id=related-topics)

[Setting Search Container Animations](/develop/tutorials/-/knowledge_base/7-0/setting-search-container-animations)

[Creating Layouts inside Custom Portlets](/develop/tutorials/-/knowledge_base/7-0/creating-layouts-inside-custom-portlets)
