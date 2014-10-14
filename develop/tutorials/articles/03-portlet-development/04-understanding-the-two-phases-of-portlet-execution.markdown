# Understanding the Two phases of Portlet Execution [](id=understanding-the-two-phases-of-portlet-execution)

Our portlet needs two execution phases, the action phase and the render phase.
Multiple execution phases can be confusing to developers used to regular servlet
development or used to other environments such as PHP, Python or Ruby. However,
once you're acquainted with them, you'll find the action and render phase to be
simple and useful. Let's talk about why they're necessary before defining each
phase. 

Our portlet doesn't own the entire HTML page, but shares the page with other
portlets and the portal itself. The portal generates the page by invoking one or
more portlets and adding some additional HTML around them. When a user invokes
an action within a portlet, each of the page's portlets are rendered anew.
The portal can't just allow each portlet to repeat its last invocation, and the
scenario described below illustrates why. 

Pretend we have a page with two portlets: a navigation portlet and a shopping
portlet. Here's what would happen to a user if portals didn't have two execution
phases: 

1.  First, the user would navigate to an item she wants to buy, and eventually
    submit the order, charging an amount on her credit card. After this
    operation, the portal would also invoke the navigation portlet with its
    default view. 

2.  Next, say the user clicks a link in the navigation portlet. This initiates
    an HTTP request/response cycle, and causes the content of the portlet to
    change. But all the parameters are preserved during that cycle, including
    the ones from the shopping cart! Since the portal must also show the content
    of the shopping portlet, it repeats the last action (the one in which the
    user clicked a button), which causes a new charge on the credit card and the
    start of a new shipping process! 

Why does this happen? Because the portal cannot know at runtime which portlets a
user has added to a page. Obviously, when writing a standard web application,
developers can take design it so that certain URLs perform actions, and certain
URLs navigate to other pages. Since an end user of a portal can add any portlet
to a page, the portal must separate "actions" from a simple re-draw (or
re-render) of the portlet. 

Obviously, we'd like to avoid the situation described in step 2 above, but
without the two phases, the portal wouldn't know whether the last operation on a
portlet was an action. It would have no option but to repeat the last action
over and over to obtain the content of the portlet (at least until the Credit
Card reached its limit). 

Fortunately, that's not how portals work. To prevent situations like the one
described above, the portlet specification defines two phases for every request
of a portlet, allowing the portal to differentiate *when an action is being
performed* (and should not be repeated) and *when the content is being produced*
(rendered):

- **Action phase**: The action phase can only be invoked for one portlet at a
  time. It is the result of a user interaction with the portlet. In this phase
  the portlet can change its status, for instance changing the user preferences
  of the portlet. Any inserts and modifications in the database or operations
  that should not be repeated must be performed in this phase. 
- **Render phase**: The render phase is always invoked for all portlets on the
  page after the action phase (which may or not exist). This includes the
  portlet that also had executed its action phase. It's important to note that
  the order in which the render phase of the portlets in a page gets executed is
  not guaranteed by the portlet specification. Liferay has an extension to the
  specification through the element `render-weight` in `liferay-portlet.xml`.
  Portlets with a higher render weight will be rendered before those with a
  lower weight. 

In our example so far, we've used a portlet class called `MVCPortlet`. That's
all the portlet needs if it only has a render phase. In order to be able to add
custom code that's executed in the action phase (and thus is *not* executed when
the portlet is shown again) you must create a subclass of `MVCPortlet` or
create a subclass of `GenericPortlet` directly (if you don't want to use
Liferay's lightweight framework). 

Our example above could be enhanced by creating the following class:

    package com.liferay.samples;

    import java.io.IOException;
    import javax.portlet.ActionRequest;
    import javax.portlet.ActionResponse;
    import javax.portlet.PortletException;
    import javax.portlet.PortletPreferences;
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

            super.processAction(actionRequest, actionResponse);
        }
    }

Create the above class, and its package, in the directory `docroot/WEB-INF/src`
in your portal project. 

The file `portlet.xml` must also be changed so that it points to your new class:

    <portlet>
    <portlet-name>my-greeting</portlet-name>
    <display-name>My Greeting</display-name>
    <portlet-class>com.liferay.samples.MyGreetingPortlet</portlet-class>
    <init-param>
        <name>view-template</name>
        <value>/view.jsp</value>
    </init-param>
    ...

Finally, make a minor change in the `edit.jsp` file, changing the URL to which
the form is sent in order to let the portal know to execute the action phase.
There are three types of URLs that can be generated by a portlet:

- *renderURL*: Invokes a portlet using only its render phase. 
- *actionURL*: Executes an action phase before rendering all the portlets in the
  page. 
- *resourceURL*: Is used to retrieve images, XML, JSON or any other type of
  resource. It's often used to dynamically generate images or other media types,
  as well as makng AJAX requests to the server. Most importanlty, it differs
  from the other two in that the portlet has full control of the data that is
  sent in response. 

Let's change the `edit.jsp` file to use an *actionURL*, using the JSP tag of
the same name. We'll also remove the previous code that was saving the
preference. Overwrite the `edit.jsp` file contents with the following:

    <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
    <%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

    <%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
    <%@ page import="com.liferay.portal.kernel.util.Validator" %>
    <%@ page import="javax.portlet.PortletPreferences" %>

    <portlet:defineObjects />

    <%
        PortletPreferences prefs = renderRequest.getPreferences();
        String greeting = (String)prefs.getValue(
            "greeting", "Hello! Welcome to our portal.");
    %>

    <portlet:actionURL var="editGreetingURL">
        <portlet:param name="mvcPath" value="/edit.jsp" />
    </portlet:actionURL>

    <aui:form action="<%= editGreetingURL %>" method="post">
            <aui:input label="greeting" name="greeting" type="text" value="<%=
        greeting %>" />
            <aui:button type="submit" />
    </aui:form>

    <portlet:renderURL var="viewGreetingURL">
            <portlet:param name="mvcPath" value="/view.jsp" />
    </portlet:renderURL>

    <p><a href="<%= viewGreetingURL %>">&larr; Back</a></p>

Deploy the portlet again after making these changes; everything should work
exactly like before. Well, almost. Unless you paid close attention, you may have
missed something: the portlet no longer shows a message to the user that the
preference has been saved after she clicks the save button. To implement that,
information must pass from the action phase to the render phase, so that the JSP
knows that the preference has just been saved and can show a message to the
user. 
