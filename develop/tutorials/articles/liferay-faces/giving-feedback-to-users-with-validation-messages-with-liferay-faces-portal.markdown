# Giving Feedback to Users with Validation Messages with Liferay Faces Portal

For this tutorial, you'll learn about applying the JSR 286 standard class names
required to give feedback to users with validation messages using Liferay Faces
Portal. 

Most of the standard JSF HTML component tags render themselves as HTML markup
such as `<label />`, `<input />`, `<span />`, etc., and assume the current
Liferay theme thanks to the power of CSS. However, the
[`h:message`](http://java.sun.com/javaee/javaserverfaces/1.2/docs/tlddocs/h/message.html)
and
[`h:messages`](http://java.sun.com/javaee/javaserverfaces/1.2/docs/tlddocs/h/messages.html)tag
will not assume the current Liferay theme unless the `portlet-msg-error`,
`portlet-msg-info`, and `portlet-msg-warn` JSR 286 standard CSS class names are
applied: 

    <h:messages errorClass="portlet-msg-error" fatalClass="portlet-msg-error" infoClass="portlet-msg-info" warnClass="portlet-msg-warn" /> 

<!-- Demonstrate using the liferay-ui:message tag for these message types.  - Jim -->

As a convenience, Liferay Faces Portal provides the
[`liferay-ui:message`](http://docs.liferay.com/faces/3.2/vdldoc/liferay-ui/message.html)
Facelet composite component tag that encapsulates the
[`h:message`](http://java.sun.com/javaee/javaserverfaces/1.2/docs/tlddocs/h/message.html)
tag. The `liferay-ui:message` tag automatically applies the JSR 286 standard
class names, as shown above. 

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

You're now equipped to give feedback to users with validation messages using
Liferay Faces Portal. 

## Related Topics

<!-- Add once JSF tutorials are finished. -Cody -->
