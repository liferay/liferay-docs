---
header-id: clay-badges
---

# Clay Badges

[TOC levels=1-4]

Badges help highlight important information such as notifications or new and 
unread messages. Badges have circular borders and are only used to specify a 
number. This covers the different types of Clay badges you can add to your app. 

## Badge Types

The following badge styles are available:

Primary badge:

```html
<div class="col-md-1">
    <clay:badge label="8" />

    <div>Primary</div>
</div>
```

![Figure 1: A primary badge is bright blue, commanding attention like the primary button of a form.](../../../../images/clay-taglib-badge-primary.png)

Secondary badge:

```html
<div class="col-md-1">
    <clay:badge label="87" style="secondary" />

    <div>Secondary</div>
</div>
```

![Figure 2: A secondary badge is light-grey and draws less focus than a primary button.](../../../../images/clay-taglib-badge-secondary.png)

Info badge:

```html
<div class="col-md-1">
    <clay:badge label="91" style="info" />

    <div>Info</div>
</div>
```

![Figure 3: A info badge is dark blue and meant for numbers related to general information.](../../../../images/clay-taglib-badge-info.png)

Error badge:

```html
<div class="col-md-1">
    <clay:badge label="130" style="danger" />

    <div>Error</div>
</div>
```

![Figure 4: An error badge displays numbers related to an error.](../../../../images/clay-taglib-badge-error.png)

Success badge:

```html
<div class="col-md-1">
    <clay:badge label="1111" style="success" />

    <div>Success</div>
</div>
```

![Figure 5: A success badge displays numbers related to a successful action.](../../../../images/clay-taglib-badge-success.png)

Warning badge:

```html
<div class="col-md-1">
    <clay:badge label="21" style="warning" />

    <div>Warning</div>
</div>
```

![Figure 6: A warning badge displays numbers related to warnings that should be addressed.](../../../../images/clay-taglib-badge-warning.png)

Now you know how to use badges to keep track of values in your app.

## Related Topics

- [Clay Labels and Links](/docs/7-2/reference/-/knowledge_base/r/clay-labels-and-links)
- [Clay Cards](/docs/7-2/reference/-/knowledge_base/r/clay-cards)
- [Clay Stickers](/docs/7-2/reference/-/knowledge_base/r/clay-stickers)
