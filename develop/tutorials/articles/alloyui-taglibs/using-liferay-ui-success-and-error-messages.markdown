# Using liferay-ui:success and liferay-ui:error Messages [](id=using-liferay-uisuccess-and-liferay-uierror-message-lp-6-2-develop-tutorial)

As users perform different actions in your portlet, it's helpful for them to get
feedback as to whether the portlet's actions are succeeding or failing. For
example, on completing an action successfully, you can reassure the user of the
success. Similarly, if the user supplied invalid input to the action, you can
inform him and even describe why it's invalid or hint what might make it valid;
this kind of feedback helps your portlet's users.  

To facilitate such feedback, Liferay provides the means for you to pass an
attribute to your JSPs to indicate an action's success or failure. In your
portlet class, you simply set an attribute in the `actionRequest` that is then
read from the JSP. What's more, the JSP immediately removes the attribute from
the session so the message is only shown once. Liferay provides a helper class
and taglibs to do this operation easily. Figure 1 shows what a success message
can look like in your portlet. 

![Figure 1: Giving feedback on a user's success is easy using the `liferay-ui:success` tag and the `SessionMessage` helper class.](../../images/liferay-ui-success.png)

In this tutorial, we'll show you how to use session messages and the
`liferay-ui:success` and `liferay-ui:error` tags to provide user feedback in a
sample portlet called the My Greeting portlet. Are you ready to give it a try?
Let's do it. 

## Confirming Success with liferay-ui:success [](id=confirming-success-with-liferay-uisuccess-lp-6-2-develop-tutorial)

It's good to let a user know when a portlet was able to execute his action
successfully. So, we'll demonstrate adding a success message for an action
successfully completed in My Greeting portlet. 

1.  As a starting point, use the My Greeting portlet which is available in the
<https://github.com/liferay/liferay-docs> 
GitHub repository 
[here](https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/liferayui/success/begin/my-greeting-portlet).
You'll need to clone the repository if you haven't already done so. Then copy
the `my-greeting-portlet` folder into the `portlets` folder of your Liferay
Plugins SDK. 

2.  Open `MyGreetingPortlet.java`, found in package `com.liferay.samples`, and
add the attribute value `"success"` to the `actionRequest` via the
`SessionMessages` helper class. You can add it at the end of the `processAction`
method, so that the method looks like this: 

    ```
    @Override
    public void processAction(
        ActionRequest actionRequest, ActionResponse actionResponse)
        throws IOException, PortletException {

        PortletPreferences prefs = actionRequest.getPreferences();
        String greeting = actionRequest.getParameter("greeting");

        if (greeting != null) {
            prefs.setValue("greeting", greeting);
            prefs.store();
        }

        SessionMessages.add(actionRequest, "success");
        super.processAction(actionRequest, actionResponse);
    }
    ```
    Make sure to import the `com.liferay.portal.kernel.servlet.SessionMessages` 
    class. 

3.  In `view.jsp`, add a `liferay-ui:success` JSP tag with a message for the
user and add the `liferay-ui` taglib declaration, so that the JSP looks like
this: 

    ```
    <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %> 
    <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %> 
    <%@ page import="javax.portlet.PortletPreferences" %>

    <portlet:defineObjects />

    <liferay-ui:success key="success" message="Greeting saved successfully!"
    />

    <% PortletPreferences prefs = renderRequest.getPreferences(); String
    greeting = (String)prefs.getValue(
        "greeting", "Hello! Welcome to our portal."); %>

    <p><%= greeting %></p>

    <portlet:renderURL var="editGreetingURL">
        <portlet:param name="mvcPath" value="/edit.jsp" />
    </portlet:renderURL>

    <p><a href="<%= editGreetingURL %>">Edit greeting</a></p>
    ```

4.  Redeploy the portlet, go to the edit screen, edit the
greeting, and save it. Similar to the figure below, the portlet shows your
success message and your new greeting.

    ![Figure 2: The `liferay-ui:success` tag provides the means to confirm the success of portlet actions.](../../images/success-saving-greeting.png)

That was easy enough! Now that we've provided the user some positive feedback,
let's provide a way to inform him when his action failed to complete
successfully. 

## Flagging Errors with liferay-ui:error [](id=flagging-errors-with-liferay-uierror-lp-6-2-develop-tutorial)

Error notification operates similarly to success notification. There's an
equivalent utility class to `SessionMessages` called `SessionErrors`, to use for
error notification. And there's a `liferay-ui:error` JSP tag in which you can
supply your error message. 

Let's add error notification to the My Greeting portlet: 

1.  Add the following `liferay-ui:error` tag to your `view.jsp` after the
`liferay-ui:success` tag: 

    ```
    <liferay-ui:error key="error" message="Sorry, an error prevented saving
    your greeting" />
    ```

2. Modify `MyGreetingPortlet.java`'s `processAction` method to flag an error to
the `actionRequest`, on catching an exception. Your `processAction` method should
look like this: 

    ```
    @Override
    public void processAction(
        ActionRequest actionRequest, ActionResponse actionResponse)
        throws IOException, PortletException {

        PortletPreferences prefs = actionRequest.getPreferences();
        String greeting = actionRequest.getParameter("greeting");

        if (greeting != null) {
            try {
                prefs.setValue("greeting", greeting);
                prefs.store();
            }
            catch(Exception e) {
                SessionErrors.add(actionRequest, "error");
            }
        }

        SessionMessages.add(actionRequest, "success");
        super.processAction(actionRequest, actionResponse);
    }
    ```

    Make sure to import the `com.liferay.portal.kernel.servlet.SessionErrors` 
    class.

3.  Redeploy the portlet. 

If an error occurs in processing the action request, your `view.jsp` shows
the error message in your portlet. 

![Figure 3: The sample My Greeting portlet shows an error message on failure to process the portlet action.](../../images/portlet-invalid-data.png)

The final My Greeting portlet implemented in this tutorial, including
its
[`MyGreetingPortlet.java`](https://github.com/liferay/liferay-docs/blob/master/develop/tutorials/code/liferayui/success/end/my-greeting-portlet/docroot/WEB-INF/src/com/liferay/samples/MyGreetingPortlet.java)
and
[`view.jsp`](https://github.com/liferay/liferay-docs/blob/master/develop/tutorials/code/liferayui/success/end/my-greeting-portlet/docroot/view.jsp)
files, is posted on GitHub 
[here](https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/liferayui/success/end/my-greeting-portlet). 

To sum things up, you've added a success message for confirming successful
portlet action execution and you've added an error message for notifying when
something's gone wrong; you can implement these messages in your portlets too.
Your portlet users will be glad to get helpful feedback from your portlets. 

