# Giving Feedback to Users with Validation Messages [](id=giving-feedback-to-users-with-validation-messages)

For this tutorial, you'll learn about giving feedback to your users with
validation messages, and how easy JSF makes this process. 

Liferay Faces Bridge automatically applies the JSR 286 standard CSS class names
`portlet-msg-error`, `portlet-msg-info`, and `portlet-msg-warn` to the 
[`h:message`](http://java.sun.com/javaee/javaserverfaces/1.2/docs/tlddocs/h/message.html)
 and 
[`h:messages`](http://java.sun.com/javaee/javaserverfaces/1.2/docs/tlddocs/h/messages.html)
tags. This means you can use the `h:message` and `h:messages` tags and have
access to the standard validation messages. 

+$$$

**Note:** When running as a portlet, the ICEfaces
[`ice:message`](http://www.icefaces.org/docs/latest/tld/ice/message.html) and
[`ice:messages`](http://www.icefaces.org/docs/latest/tld/ice/messages.html)
component tags automatically apply the JSR 286 standard class names too.
Additionally, the
[`ice:dataTable`](http://www.icefaces.org/docs/latest/tld/ice/dataTable.html)
component tag applies the following JSR 286 standard class names for alternating
table rows: 

- `portlet-section-alternate`
- `portlet-section-body`

$$$

You're now equipped to give feedback to users with validation messages in your
JSF portlet. 

## Related Topics [](id=related-topics)

[Localizing JSF Portlets](/develop/tutorials/-/knowledge_base/6-2/localizing-jsf-portlets)

[Liferay Faces Alloy UI Components](/develop/tutorials/-/knowledge_base/6-2/liferay-faces-alloy-ui-components)

[Liferay Faces Bridge UI Components](/develop/tutorials/-/knowledge_base/6-2/liferay-faces-bridge-ui-components)

[Understanding Liferay Faces Bridge](/develop/tutorials/-/knowledge_base/6-2/understanding-liferay-faces-bridge)

[Understanding Liferay Faces Portal](/develop/tutorials/-/knowledge_base/6-2/understanding-liferay-faces-portal)

[Understanding Liferay Faces Alloy](/develop/tutorials/-/knowledge_base/6-2/understanding-liferay-faces-alloy)
