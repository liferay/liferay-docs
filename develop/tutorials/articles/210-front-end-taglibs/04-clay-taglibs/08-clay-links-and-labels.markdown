# Clay Labels and Links [](id=clay-labels-and-links)

Liferay Clay taglibs provide tags for creating labels and links in your app. 
This tutorial shows how to add both of these UI elements to your apps. 

## Labels [](id=labels)

The Liferay Clay taglibs provide a few different labels for your app. Use the 
`clay:label` tag to add a label to your app. You can create color-coded labels, 
removable labels, and labels that contain links. The sections below demonstrate 
all of these options. 

### Color-coded Labels [](id=color-coded-labels)

The Liferay Clay labels come in four different colors: dark-blue for info, 
light-gray for status, orange for pending, red for rejected, and green for 
approved.

Info labels are dark-blue, and since they stand out a bit more than status 
labels, they are best for conveying general information. To use an info label, 
set the `style` attribute to `info`:

    <clay:label label="Label text" style="info" />

![Figure 1: Info labels convey general information.](../../../images/clay-taglib-label-info.png)

Status labels are light-gray, and due to their neutral color, they are best for 
conveying basic information. Status labels are the default label and therefore 
require no `style` attribute: 

		<clay:label label="Status" />

![Figure 2: Status labels are the least flashy and best for displaying basic information.](../../../images/clay-taglib-label-status.png)

Warning labels are orange, and due to their color, they are best for conveying
a warning message. To use a warning label, set the `style` attribute to
`warning`:

		<clay:label label="Pending" style="warning" />

![Figure 3: Warning labels notify the user of issues, but nothing app breaking.](../../../images/clay-taglib-label-warning.png)

Danger labels are red and indicate that something is wrong or has failed. To use 
a danger label, set the `style` attribute to `danger`:

		<clay:label label="Rejected" style="danger" />

![Figure 4: Danger labels convey a sense of urgency that must be addressed.](../../../images/clay-taglib-label-danger.png)

Success labels are green and indicate that something has completed successfully. 
To use a success label, set the `style` attribute to `success`:

    <clay:label label="Approved" style="success" />

![Figure 5: Success labels indicate a successful action.](../../../images/clay-taglib-label-success.png)

Labels can also be bigger. Set the `size` attribute to `lg` to display large
labels:
    
    <clay:label label="Approved" size="lg" style="success" />

### Removable Labels [](id=removable-labels)

If you want to let a user close a label (e.g. a temporary notification), you can 
make the label removable by setting the `closeable` attribute to `true`.

		<clay:label closeable="<%= true %>" label="Normal Label" />

![Figure 6: Labels can be removable.](../../../images/clay-taglib-label-removable.png)

### Labels with Links [](id=labels-with-links)

You can make a label a link by adding the `href` attribute to it just as you 
would an anchor tag:

    <clay:label href="#" label="Label Text" />
    
![Figure 7: Labels can also be links.](../../../images/clay-taglib-label-link.png)

## Links [](id=links)

You can add traditional hyperlinks to your app with the `<clay:link>` tag:

    <clay:link href="#" label="link text" />
    
![Figure 8: Clay taglibs also provide link elements.](../../../images/clay-taglib-link.png)

Now you know how to add links and labels to your apps!

## Related Topics [](id=related-topics)

[Clay Badges](/develop/tutorials/-/knowledge_base/7-1/clay-badges)

[Clay Cards](/develop/tutorials/-/knowledge_base/7-1/clay-cards)

[Clay Form Elements](/develop/tutorials/-/knowledge_base/7-1/clay-form-elements)
