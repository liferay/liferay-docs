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

For example, the
[jsf-login-portlet](https://github.com/liferay/liferay-faces-portal/blob/master/demo/jsf-login-portlet)
demo shows using both `h:message` and `h:messages` tags for its
[`login` view](https://github.com/liferay/liferay-faces-portal/blob/master/demo/jsf-login-portlet/src/main/webapp/WEB-INF/views/login.xhtml):

    <alloy:form>
        <h:messages globalOnly="true" />
        <alloy:fieldset>
            <alloy:field id="handleField" label="#{i18n[loginBackingBean.handleLabel]}">
                <h:inputText id="handle" value="#{loginModelBean.handle}" required="true"
                    ...
                </h:inputText>
                <h:message id="handleMessage" for="handle" style="display: inline;" />
            </alloy:field>
            <alloy:field id="passwordField" label="#{i18n['password']}">
                <h:inputSecret id="password" value="#{loginModelBean.password}" required="true">
                    <f:ajax render="passwordMessage" />
                </h:inputSecret>
                <h:message id="passwordMessage" for="password" style="display: inline;" />
                    ...
            </alloy:field>
        </alloy:fieldset>
        <h:commandButton action="#{loginBackingBean.authenticate}" value="#{i18n['sign-in']}" />
    </alloy:form>

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

[Internationalization](/develop/tutorials/-/knowledge_base/7-0/internationalization)

[Understanding Liferay Faces Bridge](/develop/tutorials/-/knowledge_base/7-0/understanding-liferay-faces-bridge)

[Understanding Liferay Faces Portal](/develop/tutorials/-/knowledge_base/7-0/understanding-liferay-faces-portal)

[Understanding Liferay Faces Alloy](/develop/tutorials/-/knowledge_base/7-0/understanding-liferay-faces-alloy)
