---
header-id: clay-alerts
---

# Clay Alerts

[TOC levels=1-4]

Clay alerts come in two types: embedded and stripe. Both types, along with 
several examples of each, are shown below. 

## Embedded Alerts

Embedded alerts are usually used inside forms. The element that contains it
determines an embedded alert's width. The close action is not required for
embedded alerts. The following embedded alerts can be created with Clay
taglibs:

Danger alert (embedded):

```html
<clay:alert
	message="This is an error message."
	style="danger"
	title="Error"
/>
```

![Figure 1: The danger alert notifies the user of an error or issue.](../../../../images/clay-taglib-alert-danger.png)

Success alert (embedded):

```html
<clay:alert
	message="This is a success message."
	style="success"
	title="Success"
/>
```

![Figure 2: The success alert notifies the user when an action is successful.](../../../../images/clay-taglib-alert-success.png)

Info alert (embedded):

```html
<clay:alert
	message="This is an info message."
	title="Info"
/>
```

![Figure 3: The info alert displays general information to the user.](../../../../images/clay-taglib-alert-info.png)

Warning alert (embedded):

```html
<clay:alert
	message="This is a warning message."
	style="warning"
	title="Warning"
/>
```

![Figure 4: The warning alert displays a warning message to the user.](../../../../images/clay-taglib-alert-warning.png)

## Stripe Alerts

Stripe alerts are placed below the last navigation element (either the header or 
the navigation bar), and they usually appear on *Save* action, communicating 
the status of the action once received from the server. Unlike embedded alerts, 
stripe alerts require the close action. A stripe alert is always the full width 
of the container and pushes all the content below it. The following stripe 
alerts can be created with Clay taglibs:

Danger alert (stripe):

```html
<clay:stripe
	message="This is an error message."
	style="danger"
	title="Error"
/>
```

![Figure 5: The danger striped alert notifies the user that an action has failed.](../../../../images/clay-taglib-alert-danger-stripe.png)

Success alert (stripe):

```html
<clay:stripe
	message="This is a success message."
	style="success"
	title="Success"
/>
```

![Figure 6: The success striped alert notifies the user that an action has completed successfully.](../../../../images/clay-taglib-alert-success-stripe.png)

Info alert (stripe):

```html
<clay:stripe
	message="This is an info message."
	title="Info"
/>
```

![Figure 7: The info striped alert displays general information about an action to the user.](../../../../images/clay-taglib-alert-info-stripe.png)

Warning alert (stripe):

```html
<clay:stripe
	message="This is a warning message."
	style="warning"
	title="Warning"
/>
```
 
![Figure 8: The warning striped alert warns the user about an action.](../../../../images/clay-taglib-alert-warning-stripe.png)

Now you know how to alert users!

## Related Topics

- [Clay Buttons](/docs/7-2/reference/-/knowledge_base/r/clay-buttons)
- [Clay Form Elements](/docs/7-2/reference/-/knowledge_base/r/clay-form-elements)
- [Clay Labels and Links](/docs/7-2/reference/-/knowledge_base/r/clay-labels-and-links)
