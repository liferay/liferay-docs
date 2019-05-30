---
header-id: clay-labels-and-links
---

# Clay Labels and Links

[TOC levels=1-4]

Liferay Clay taglibs provide tags for creating labels and links in your app. 
Both of these elements are covered below. 

## Labels

The Liferay Clay taglibs provide a few different labels for your app. Use the 
`clay:label` tag to add a label to your app. You can create color-coded labels, 
removable labels, and labels that contain links. The sections below demonstrate 
all of these options. 

### Color-coded Labels

The Liferay Clay labels come in four different colors: dark-blue for info, 
light-gray for status, orange for pending, red for rejected, and green for 
approved.

Info labels are dark-blue, and since they stand out a bit more than status 
labels, they are best for conveying general information. To use an info label, 
set the `style` attribute to `info`:

```html
<clay:label label="Label text" style="info" />
```

![Figure 1: Info labels convey general information.](../../../../images/clay-taglib-label-info.png)

Status labels are light-gray, and due to their neutral color, they are best for 
conveying basic information. Status labels are the default label and therefore 
require no `style` attribute: 

```html
<clay:label label="Status" />
```

![Figure 2: Status labels are the least flashy and best for displaying basic information.](../../../../images/clay-taglib-label-status.png)

Warning labels are orange, and due to their color, they are best for conveying
a warning message. To use a warning label, set the `style` attribute to
`warning`:

```html
<clay:label label="Pending" style="warning" />
```

![Figure 3: Warning labels notify the user of issues, but nothing app breaking.](../../../../images/clay-taglib-label-warning.png)

Danger labels are red and indicate that something is wrong or has failed. To use 
a danger label, set the `style` attribute to `danger`:

```html
<clay:label label="Rejected" style="danger" />
```

![Figure 4: Danger labels convey a sense of urgency that must be addressed.](../../../../images/clay-taglib-label-danger.png)

Success labels are green and indicate that something has completed successfully. 
To use a success label, set the `style` attribute to `success`:

```html
<clay:label label="Approved" style="success" />
```

![Figure 5: Success labels indicate a successful action.](../../../../images/clay-taglib-label-success.png)

Labels can also be bigger. Set the `size` attribute to `lg` to display large
labels:

```html    
<clay:label label="Approved" size="lg" style="success" />
```

### Removable Labels

If you want to let a user close a label (e.g. a temporary notification), you can 
make the label removable by setting the `closeable` attribute to `true`.

```html
<clay:label closeable="<%= true %>" label="Normal Label" />
```

![Figure 6: Labels can be removable.](../../../../images/clay-taglib-label-removable.png)

### Labels with Links

You can make a label a link by adding the `href` attribute to it just as you 
would an anchor tag:

```html
<clay:label href="#" label="Label Text" />
```

![Figure 7: Labels can also be links.](../../../../images/clay-taglib-label-link.png)

## Links

You can add traditional hyperlinks to your app with the `<clay:link>` tag:

```html
<clay:link href="#" label="link text" />
```

![Figure 8: Clay taglibs also provide link elements.](../../../../images/clay-taglib-link.png)

Now you know how to add links and labels to your apps!

## Related Topics

- [Clay Badges](/docs/7-2/reference/-/knowledge_base/r/clay-badges)
- [Clay Cards](/docs/7-2/reference/-/knowledge_base/r/clay-cards)
- [Clay Form Elements](/docs/7-2/reference/-/knowledge_base/r/clay-form-elements)
