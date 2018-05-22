# Clay Alerts [](id=clay-alerts)

Clay alerts come in two types: embedded and stripe. This tutorial covers both 
types and provides several examples of each.

## Embedded Alerts [](id=embedded-alerts)

Embedded alerts are usually used inside forms. The element that contains it
determines an embedded alert's width. The close action is not required for
embedded alerts. The following embedded alerts can be created with Clay
taglibs:

Danger alert (embedded):

    <clay:alert
    	message="This is an error message."
    	style="danger"
    	title="Error"
    />

![Figure 1: The danger alert notifies the user of an error or issue.](../../../images/clay-taglib-alert-danger.png)

Success alert (embedded):

    <clay:alert
    	message="This is a success message."
    	style="success"
    	title="Success"
    />

![Figure 2: The success alert notifies the user when an action is successful.](../../../images/clay-taglib-alert-success.png)

Info alert (embedded):

    <clay:alert
    	message="This is an info message."
    	title="Info"
    />

![Figure 3: The info alert displays general information to the user.](../../../images/clay-taglib-alert-info.png)

Warning alert (embedded):

    <clay:alert
    	message="This is a warning message."
    	style="warning"
    	title="Warning"
    />

![Figure 4: The warning alert displays a warning message to the user.](../../../images/clay-taglib-alert-warning.png)

## Stripe Alerts [](id=stripe-alerts)

Stripe alerts are placed below the last navigation element (either the header or 
the navigation bar), and they usually appear on *Save* action, communicating 
the status of the action once received from the server. Unlike embedded alerts, 
stripe alerts require the close action. A stripe alert is always the full width 
of the container and pushes all the content below it. The following stripe 
alerts can be created with Clay taglibs:

Danger alert (stripe):

    <clay:stripe
    	message="This is an error message."
    	style="danger"
    	title="Error"
    />

![Figure 5: The danger striped alert notifies the user that an action has failed.](../../../images/clay-taglib-alert-danger-stripe.png)

Success alert (stripe):

    <clay:stripe
    	message="This is a success message."
    	style="success"
    	title="Success"
    />

![Figure 6: The success striped alert notifies the user that an action has completed successfully.](../../../images/clay-taglib-alert-success-stripe.png)

Info alert (stripe):

    <clay:stripe
    	message="This is an info message."
    	title="Info"
    />

![Figure 7: The info striped alert displays general information about an action to the user.](../../../images/clay-taglib-alert-info-stripe.png)

Warning alert (stripe):

    <clay:stripe
    	message="This is a warning message."
    	style="warning"
    	title="Warning"
    />
 
![Figure 8: The warning striped alert warns the user about an action.](../../../images/clay-taglib-alert-warning-stripe.png)

Now you know how to alert users!

## Related Topics [](id=related-topics)

[Clay Buttons](/develop/tutorials/-/knowledge_base/7-1/clay-buttons)

[Clay Form Elements](/develop/tutorials/-/knowledge_base/7-1/clay-form-elements)

[Clay Labels and Links](/develop/tutorials/-/knowledge_base/7-1/clay-labels-and-links)
