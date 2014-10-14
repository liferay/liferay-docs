# Developing a Portlet with Multiple Actions [](id=developing-a-portlet-with-multiple-actions)

Right now our portlet only has two views: the default view and edit view. Adding
more views is easy, and you can link to them using the `mvcPath` parameter in
your `renderURL`. But we only have one action. What if we want to add another
action, like sending an email to the user? 

You can have as many actions as you want in a portlet. Implement each one as a
method that receives two parameters: an `ActionRequest` and an `ActionResponse`.
Name the method whatever you want, but note that the method name must match the
URL name that points to it. 

Let's rewrite the example from the previous section using a custom name for the
action method that sets the greeting, and add a second action method for sending
emails. 

    public class MyGreetingPortlet extends MVCPortlet {
        public void setGreeting(
                ActionRequest actionRequest, ActionResponse actionResponse)
        throws IOException, PortletException {
            PortletPreferences prefs = actionRequest.getPreferences();
            String greeting = actionRequest.getParameter("greeting");

            if (greeting != null) {
                try {
                    prefs.setValue("greeting", greeting);
                    prefs.store();
                    SessionMessages.add(actionRequest, "success");
                }
                catch(Exception e) {
                    SessionErrors.add(actionRequest, "error");
                }
            }
        }

        public void sendEmail(
                ActionRequest actionRequest, ActionResponse actionResponse)
        throws IOException, PortletException {
            // Add code here to send an email
        }
    }

We no longer need to invoke the `processAction` method of the super class, since
we're not overriding it. 

This name change also requires a simple change in the URL so its name matches
the method that is invoked to execute the action. In the `edit.jsp`, modify the
`actionURL` so it looks like this:

    <portlet:actionURL var="editGreetingURL" name="setGreeting">
        <portlet:param name="mvcPath" value="/edit.jsp" />
    </portlet:actionURL>

Now you know all the basics of portlet development, and can use your Java
knowledge to build portlets that get integrated in Liferay. Let's put the
finishing touches on your portlet by first learning about an extension to
Liferay's portlet specification that generates more elegant URLs for your
portlets. 
