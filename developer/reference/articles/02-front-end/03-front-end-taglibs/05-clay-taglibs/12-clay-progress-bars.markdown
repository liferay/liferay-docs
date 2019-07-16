---
header-id: clay-progress-bars
---

# Clay Progress Bars

[TOC levels=1-4]

You can add progress bars to your app with the `clay:progressbar` tag. These 
indicate the completion percentage of a task and come in three status styles: 
`default` (blue), `warning` (red), and `complete` (green with checkmark). You 
can provide a minimum value (`minValue`) and a maximum value (`maxValue`). 

Default progress bar:

```html
<clay:progressbar 
    maxValue="<%= 100 %>" 
    minValue="<%= 0 %>" 
    value="<%= 30 %>" 
/>
```

![Figure 1: You can include progress bars in your apps.](../../../../images/clay-taglib-progress-bar.png)

Warning progress bar:

```html
<clay:progressbar 
    maxValue="<%= 100 %>" 
    minValue="<%= 0 %>" 
    status="warning" 
    value="<%= 70 %>" 
/>
```

![Figure 2: warning progress bars indicate that the progress has not completed due to an error.](../../../../images/clay-taglib-progress-bar-warning.png)

Complete progress bar:

```html
<clay:progressbar 
    status="complete" 
/>
```

![Figure 3: The complete progress bar indicates the progress is complete.](../../../../images/clay-taglib-progress-bar-complete.png)

Clay taglibs make it easy to track progress in your apps.

## Related Topics

- [Clay Dropdown Menus and Action Menus](/docs/7-2/reference/-/knowledge_base/r/clay-dropdown-menus-and-action-menus)
- [Clay Icons](/docs/7-2/reference/-/knowledge_base/r/clay-icons)
- [Clay Navigation Bars](/docs/7-2/reference/-/knowledge_base/r/clay-navigation-bars)
