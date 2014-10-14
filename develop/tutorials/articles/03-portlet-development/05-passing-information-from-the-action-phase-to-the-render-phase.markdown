# Passing Information from the Action Phase to the Render Phase [](id=passing-information-from-the-action-phase-to-the-render-phase)

There are two ways to pass information from the action phase to the render
phase. The first way is through render parameters. In the `processAction` method
you can invoke the `setRenderParameter` method to add a new parameter to the
request. The render phase can read this: 

    actionResponse.setRenderParameter("parameter-name", "value");

From the render phase (in our case, the JSP), this value is read like this: 

    renderRequest.getParameter("parameter-name");

It's important to be aware that when invoking an action URL, the parameters
specified in the URL are only readable from the action phase (that is within
the `processAction` method). In order to pass parameter values to the render
phase you must read them from the `actionRequest` and then invoke the
`setRenderParameter` method for each parameter needed. 

+$$$

**Tip:** Liferay offers a convenient extension to the portlet specification
through the `MVCPortlet` class to copy all action parameters directly as render
parameters. You can achieve this by setting the following `init-param` in your
`portlet.xml`:

    <init-param>
        <name>copy-request-parameters</name>
        <value>true</value>
    </init-param>

$$$

One final note about render parameters: the portal remembers them for all later
executions of the portlet until the portlet is invoked with *different*
parameters. That is, if a user clicks a link in our portlet and a render
parameter is set, and then the user continues browsing through other portlets on
the page, each time the page is reloaded, the portal renders our portlet
using the render parameters that we initially set. If we used render parameters
in our example, then the success message will be shown not only right after
saving, but also every time the portlet is rendered until the portlet is invoked
again *without* that render parameter. 

The second way of passing information from the action phase to the render phase
is not unique to portlets, so it might be familiar to you--using the session.
Your code can set an attribute in the `actionRequest` that is then read from the
JSP. In our case, the JSP would also immediately remove the attribute from the
session so the message is only shown once. Liferay provides a helper class and
taglib to do this operation easily. In the `processAction` method, you need to
use the `SessionMessages` class:

    package com.liferay.samples;

    import java.io.IOException;
    import javax.portlet.ActionRequest;
    import javax.portlet.ActionResponse;
    import javax.portlet.PortletException;
    import javax.portlet.PortletPreferences;
    import com.liferay.portal.kernel.servlet.SessionMessages;
    import com.liferay.util.bridges.mvc.MVCPortlet;

    public class MyGreetingPortlet extends MVCPortlet {
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
    }

Next, in `view.jsp`, add the `liferay-ui:success` JSP tag and add the taglib
declarations below:

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

After this change, redeploy the portlet, go to the edit screen and save it. You
should see a nice message that looks like this:

![Figure 3.7: The sample "My Greeting" portlet showing a success message](../../images/portlet-greeting-save.png)

There's also an equivalent utility class for error notification; it's commonly
used after catching an exception in the `processAction` method. For example:

    try {
        prefs.setValue("greeting", greeting);
        prefs.store();
        SessionMessages.add(actionRequest, "success");
    }
    catch(Exception e) {
        SessionErrors.add(actionRequest, "error");
    }

The error, if it exists, is shown in your `view.jsp` using the
`liferay-ui:error` tag:

    <liferay-ui:error key="error" message="Sorry, an error prevented saving
    your greeting" />

If an error occurred, you'd see this in your portlet:

![Figure 3.8: The sample "My Greeting" portlet showing an error message](../../images/portlet-invalid-data.png)

The first message is automatically added by Liferay. The second one is the one
you added in your JSP. 
