# Implementing a Management Bar Navigation Filter

Navigation filters are used to create navigation menus in the Management Bar. 
You can add as many navigation filters to the Management Bar as your app 
requires.
<!-- Can you nest navigation filters to create a nested navigation menu with
child pages? -->
You can learn how to configure the navigation filter next.

## Configuring the Navigation Filter

Follow these steps to configure the navigation filter:

1.  Add the `<liferay-frontend:management-bar-filters>` tag below the 
    `<liferay-frontend:management-bar-buttons>` tags, to contain your management 
    bar filters.

2.  Use the `<liferay-frontend:management-bar-navigation>` tag to add as many 
    navigation menus as your app requires. Use the `navigationKeys` attribute to 
    set the navigation menu options. The `navigationParam` attribute identifies 
    the parameter to use for the navigation filter value. If you're only using 
    one navigation filter, this attribute is not required. Finally, use the 
    `portletURL` attribute to set the URL for the page. Below is an example 
    configuration:

    <!-- How, when, and why do I need to use navigationParam? I'm guessing this
    is needed when you use multiple navigation filters -->

        <liferay-frontend:management-bar-filters>

            <liferay-frontend:management-bar-navigation
            navigationKeys='<%= new String[] {"all", ["navigation-title"]...} %>'
            navigationParam="navigation"
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

4.  If your app has multiple navigation options, check the value of the 
    navigation parameter to render the appropriate JSP page. Below is an example 
    code snippet from `com.liferay.wiki.web` module's [`page_iterator.jsp`](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/collaboration/wiki/wiki-web/src/main/resources/META-INF/resources/wiki/page_iterator.jsp)
    that controls the navigation between its JSP pages using a `navigation` 
    parameter:

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

## Related Topics

[Implementing a Management Bar Sort Filter](/develop/tutorials/-/knowledge_base/7-0/implementing-a-management-bar-sort-filter)

[Implementing the Management Bar Display Styles](/develop/tutorials/-/knowledge_base/7-0/implementing-the-management-bar-display-styles)
