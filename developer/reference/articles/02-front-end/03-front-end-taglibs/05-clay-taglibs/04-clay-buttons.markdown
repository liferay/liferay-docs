---
header-id: clay-buttons
---

# Clay Buttons

[TOC levels=1-4]

Buttons come in several types and variations. This tutorial covers the 
different styles and variations of buttons you can create with the Clay 
taglibs. 

## Types

**Primary button:** Used for the most important actions. Two primary buttons 
should not be together or near each other. 

Primary button with label:

```html
<clay:button label="Primary" />
```

![Figure 1: A primary button is bright blue, grabbing the user's attention.](../../../../images/clay-taglib-button-primary.png)

**Secondary button:** Used for secondary actions. There can be multiple 
secondary buttons together or near each other. 

```html
<div class="col">
    <clay:button label="Secondary" style="secondary" />
</div>
<div class="col">
    <clay:button ariaLabel="Wiki" icon="wiki" style="secondary" />
</div>
```

![Figure 2: A secondary button draws less attention than a primary button and is meant for secondary actions.](../../../../images/clay-taglib-button-secondary.png)

**Borderless button:** Used in cases such as toolbars where the secondary button 
would be too heavy for the design. This keeps the design clean.

```html
<div class="col">
    <clay:button label="Borderless" style="borderless" />
</div>
<div class="col">
    <clay:button ariaLabel="Page Template" icon="page-template" style="borderless" />
</div>
```

![Figure 3: Borderless buttons remove the dark outline from the button.](../../../../images/clay-taglib-button-borderless.png)

**Link button:** Used for Cancel actions.

```html
<div class="col">
    <clay:button label="Link" style="link" />
</div>
<div class="col">
    <clay:button ariaLabel="Add Role" icon="add-role" style="link" />
</div>
```

![Figure 4: You can also turn buttons into links.](../../../../images/clay-taglib-button-link.png)

You can use labels or icons for your buttons. Below is an example of a Primary 
button with an icon:

```html    
<clay:button ariaLabel="Workflow" icon="workflow" />
```

![Figure 5: Buttons can also display icons.](../../../../images/clay-taglib-button-primary-icon.png)

You can disable a button by adding the `disabled` attribute:

```html
<div class="col">
    <clay:button disabled="<%= true %>" label="Primary" />
</div>
<div class="col">
    <clay:button ariaLabel="Workflow" disabled="<%= true %>" icon="workflow" />
</div>
```

![Figure 6: Buttons can be disabled if you don't want the user to interact with them.](../../../../images/clay-taglib-button-primary-disabled.png)

## Variations

Button with icon and text:

```html
<clay:button icon="share" label="Share" />
```

![Figure 7: Buttons can display both icons and text.](../../../../images/clay-taglib-button-icon-text.png)

Button with monospaced text:

```html
<clay:button icon="indent-less" monospaced="<%= true %>" style="secondary" />
```

![Figure 8: Buttons can display monospaced text.](../../../../images/clay-taglib-button-monospaced.png)

Block level button: 

```html
<clay:button block="<%= true %>" label="Button" />
 ```
 
![Figure 9: Block level buttons span the entire width of the container.](../../../../images/clay-taglib-button-block-level.png)

Plus button:

```html
<clay:button icon="plus" monospaced="<%= true %>" style="secondary" />
```

![Figure 10: A plus button is used for add actions in an app.](../../../../images/clay-taglib-button-plus.png)

Action button:

```html
<clay:button icon="ellipsis-v" monospaced="<%= true %>" style="borderless" />
 ```
 
![Figure 11: An action button is used to display actions menus.](../../../../images/clay-taglib-button-action.png)

## Related Topics

- [Clay Alerts](/docs/7-2/reference/-/knowledge_base/r/clay-alerts)
- [Clay Buttons](/docs/7-2/reference/-/knowledge_base/r/clay-buttons)
- [Clay Labels and Links](/docs/7-2/reference/-/knowledge_base/r/clay-labels-and-links)
