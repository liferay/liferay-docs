# Clay Progress Bars [](id=clay-progress-bars)

You can add progress bars to your app with the `clay:progressbar` tag. These 
indicate the completion percentage of a task and come in three status styles: 
`default` (blue), `warning` (red), and `complete` (green with checkmark). You 
can provide a minimum value (`minValue`) and a maximum value (`maxValue`). 

Default progress bar:

    <clay:progressbar 
        maxValue="<%= 100 %>" 
        minValue="<%= 0 %>" 
        value="<%= 30 %>" 
    />

![Figure 1: You can include progress bars in your apps.](../../../images/clay-taglib-progress-bar.png)

Warning progress bar:

    <clay:progressbar 
        maxValue="<%= 100 %>" 
        minValue="<%= 0 %>" 
        status="warning" 
        value="<%= 70 %>" 
    />

![Figure 2: warning progress bars indicate that the progress has not completed due to an error.](../../../images/clay-taglib-progress-bar-warning.png)

Complete progress bar:

    <clay:progressbar 
        status="complete" 
    />
    
![Figure 3: The complete progress bar indicates the progress is complete.](../../../images/clay-taglib-progress-bar-complete.png)

Clay taglibs make it easy to track progress in your apps.

## Related Topics [](id=related-topics)

[Clay Dropdown Menus and Action Menus](/develop/tutorials/-/knowledge_base/7-1/clay-dropdown-menus-and-action-menus)

[Clay Icons](/develop/tutorials/-/knowledge_base/7-1/clay-icons)

[Clay Navigation Bars](/develop/tutorials/-/knowledge_base/7-1/clay-navigation-bars)
