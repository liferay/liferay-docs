---
header-id: clay-navigation-bars
---

# Clay Navigation Bars

[TOC levels=1-4]

Similar to dropdown menus, navigation bars display a list of navigation items. 
The key difference is navigation bars are displayed in a horizontal bar with all 
navigation items visible at all times. The navigation bar also indicates the 
active navigation item with an underline. Navigation bars come in two styles: 
white background with dark-grey text (default) and dark-grey background with 
white text (inverted).

Default navigation bar:

```html
<clay:navigation-bar 
    navigationItems="<%= navigationBarsDisplayContext.getNavigationItems() %>" 
/>
```

![Figure 1: You can include navigation bars in your apps.](../../../../images/clay-taglib-nav-bars.png)

Inverted navigation bar (set `inverted` attribute to `true`):

```html
<clay:navigation-bar 
    inverted="<%= true %>" 
    navigationItems="<%= navigationBarsDisplayContext.getNavigationItems() %>" 
/>
```

![Figure 2: Navigation bars can be inverted if you prefer.](../../../../images/clay-taglib-nav-bars-inverted.png)

## Related Topics

- [Clay Dropdown Menus and Action Menus](/docs/7-2/reference/-/knowledge_base/r/clay-dropdown-menus-and-action-menus)
- [Clay Form Elements](/docs/7-2/reference/-/knowledge_base/r/clay-form-elements)
- [Clay Progress Bars](/docs/7-2/reference/-/knowledge_base/r/clay-progress-bars)
