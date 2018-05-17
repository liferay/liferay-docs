# Clay Navigation Bars [](id=clay-navigation-bars)

Similar to dropdown menus, navigation bars display a list of navigation items. 
The key difference is navigation bars are displayed in a horizontal bar with all 
navigation items visible at all times. The navigation bar also indicates the 
active navigation item with an underline. Navigation bars come in two styles: 
white background with dark-grey text (default) and dark-grey background with 
white text (inverted).

Default navigation bar:

    <clay:navigation-bar 
        navigationItems="<%= navigationBarsDisplayContext.getNavigationItems() %>" 
    />

![Figure 1: You can include navigation bars in your apps.](../../../images/clay-taglib-nav-bars.png)

Inverted navigation bar (set `inverted` attribute to `true`):

    <clay:navigation-bar 
        inverted="<%= true %>" 
        navigationItems="<%= navigationBarsDisplayContext.getNavigationItems() %>" 
    />

![Figure 2: Navigation bars can be inverted if you prefer.](../../../images/clay-taglib-nav-bars-inverted.png)

## Related Topics [](id=related-topics)

[Clay Dropdown Menus and Action Menus](/develop/tutorials/-/knowledge_base/7-1/clay-dropdown-menus-and-action-menus)

[Clay Form Elements](/develop/tutorials/-/knowledge_base/7-1/clay-form-elements)

[Clay Progress Bars](/develop/tutorials/-/knowledge_base/7-1/clay-progress-bars)
