# Giving Feedback to Users with Validation Messages [](id=liferay-faces-portal-validation-messages-liferay-portal-6-2-dev-guide-en)

Most of the standard JSF HTML component tags render themselves as HTML markup
such as `<label />`, `<input />`, `<span />`, etc. and assume the current
Liferay theme thanks to the power of CSS. However, the
[`h:messages`](http://java.sun.com/javaee/javaserverfaces/1.2/docs/tlddocs/h/messages.html)
and
[`h:message`](http://java.sun.com/javaee/javaserverfaces/1.2/docs/tlddocs/h/message.html)
tag will not assume the current Liferay theme unless the following JSR 286
standard CSS class names `portlet-msg-error`, `portlet-msg-info`, and
`portlet-msg-warn` are applied: 

        <h:messages errorClass="portlet-msg-error" fatalClass="portlet-msg-error" infoClass="portlet-msg-info" warnClass="portlet-msg-warn" /> 

<!-- Demonstrate using the liferay-ui:message tag for these message types.  - Jim -->

As a convenience, Liferay Faces Portal provides the
[`liferay-ui:message`](http://docs.liferay.com/faces/3.2/vdldoc/liferay-ui/message.html)
Facelet composite component tag that encapsulates the
[`h:message`](http://java.sun.com/javaee/javaserverfaces/1.2/docs/tlddocs/h/message.html)
tag. The `liferay-ui:message` tag automatically applies the JSR 286 standard
class names, as shown above. 

---

 ![Note](../../images/tip.png) **Note:** When running as a portlet, the ICEfaces
 [`ice:messages`](http://www.icefaces.org/docs/latest/tld/ice/messages.html) and
 [`ice:message`](http://www.icefaces.org/docs/latest/tld/ice/message.html)
 component tags automatically apply the JSR 286 standard class names too.
 Additionally, the 
 [`ice:dataTable`](http://www.icefaces.org/docs/latest/tld/ice/dataTable.html)
 component tag applies the following JSR 286 standard class names for
 alternating table rows: 

 - `portlet-section-alternate`
 - `portlet-section-body`

---

Next, we'll look at using Liferay Faces Portal's language capabilities
with JSF Portlets. 
