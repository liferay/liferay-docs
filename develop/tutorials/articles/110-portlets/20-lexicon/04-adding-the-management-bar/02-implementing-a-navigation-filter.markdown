# Implementing a Management Bar Navigation Filter [](id=implementing-a-management-bar-navigation-filter)

Navigation filters are used to create navigation menus in the Management Bar. 
You can add as many navigation filters to the Management Bar as your app 
requires.

You can learn how to configure the navigation filter next.

## Configuring the Navigation Filter [](id=configuring-the-navigation-filter)

Follow these steps to configure the navigation filter:

1.  Add the `<liferay-frontend:management-bar-filters>` tag below the 
    `<liferay-frontend:management-bar-buttons>` tags, to contain your management 
    bar filters.

2.  Use the `<liferay-frontend:management-bar-navigation>` tag to add as many 
    navigation menus as your app requires. Use the `navigationKeys` attribute to 
    set the navigation menu options. The `navigationParam` attribute identifies 
    the parameter to use for the navigation filter value. The default value is 
    `navigation`. If you have more than one navigation menu, you can specify a 
    unique variable with the `navigationParam` to identify each menu. Finally, 
    use the `portletURL` attribute to set the URL for the page. Below is an 
    example configuration with one navigation menu:

        <liferay-frontend:management-bar-filters>

            <liferay-frontend:management-bar-navigation
            navigationKeys='<%= new String[] {"all", ["navigation-title"]...} %>'
            navigationParam="myCustomNavigationVariable"
            portletURL="<%= portletURL %>"
            />
        </liferay-frontend:management-bar-filters>

    If your app doesn't require any navigation filters, you can just provide the 
    *all* filter to display everything. If, however, you need to let the user 
    navigate between pages (JSPs) of your app , you can add additional strings 
    to the `navigationKeys` attribute for each page you need.

3.  Set the navigation filter's default value with `paramUtil`. For example, 
    the configuration below sets the default navigation filter to *all*:

        String navigation = ParamUtil.getString(request, "navigation", "all");

4.  If your app has multiple options in a navigation menu, use the 
    `navigationParam` to check the current value. Below is an example code 
    snippet from `com.liferay.wiki.web` module's [`page_iterator.jsp`](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/collaboration/wiki/wiki-web/src/main/resources/META-INF/resources/wiki/page_iterator.jsp)
    that checks the navigation menu's value to render the proper JSP content. 
    Note that it uses the `navigationParam`'s default value `navigation` to 
    check the current value:

        if (navigation.equals("all-pages")) {
        	portletURL.setParameter("mvcRenderCommandName", "/wiki/view_pages");

        	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request,
          "all-pages"), portletURL.toString());
        }
        else if (navigation.equals("categorized-pages")) {
        	portletURL.setParameter("mvcRenderCommandName",
          "/wiki/view_categorized_pages");

        	portletURL.setParameter("categoryId", String.valueOf(categoryId));
        }
        else if (navigation.equals("draft-pages")) {
        	portletURL.setParameter("mvcRenderCommandName", "/wiki/view_draft_pages");

        	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request,
          "draft-pages"), portletURL.toString());
        }

Now you know how to add navigation filters to a management bar!

## Related Topics [](id=related-topics)

[Implementing a Management Bar Sort Filter](/develop/tutorials/-/knowledge_base/7-0/implementing-a-management-bar-sort-filter)

[Implementing the Management Bar Display Styles](/develop/tutorials/-/knowledge_base/7-0/implementing-the-management-bar-display-styles)
