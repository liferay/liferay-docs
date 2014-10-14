# Writing the My Greeting Portlet [](id=writing-the-my-greeting-portlet)

Let's make our portlet do something useful. First, we'll give it two pages:

- **view.jsp**: displays the greeting and provides a link to the *edit* page. 
- **edit.jsp**: shows a form with a text field, allowing the greeting to be
  changed, and including a link back to the *view* page. 

The `MVCPortlet` class handles the rendering of our JSPs, so for this example,
we won't write a single Java class. 

First, since we don't want multiple greetings on the same page, let's make the
My Greeting portlet non-instanceable. Just edit `liferay-portlet.xml`. If your
`portlet` element already has an `instanceable` element, change its value from
`true` to `false`. If you don't already have an `instanceable` element for your
portlet, add it. Here's what it looks like: 

    <portlet>
        <portlet-name>my-greeting</portlet-name>
        <icon>/icon.png</icon>
        <instanceable>false</instanceable>
        <header-portlet-css>/css/main.css</header-portlet-css>
        <footer-portlet-javascript>/js/main.js</footer-portlet-javascript>
        <css-class-wrapper>my-greeting-portlet</css-class-wrapper>
    </portlet>

Now we'll create our JSP templates. Start by editing `view.jsp`, found in your
portlet's `docroot` directory. Replace its current contents with the following:

    <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
    <%@ page import="javax.portlet.PortletPreferences" %>

    <portlet:defineObjects />

    <%
    PortletPreferences prefs = renderRequest.getPreferences();
    String greeting = (String)prefs.getValue(
    "greeting", "Hello! Welcome to our portal.");
    %>

    <p><%= greeting %></p>

    <portlet:renderURL var="editGreetingURL">
        <portlet:param name="mvcPath" value="/edit.jsp" />
    </portlet:renderURL>

    <p><a href="<%= editGreetingURL %>">Edit greeting</a></p>

Next, create `edit.jsp` in the same directory as `view.jsp`, with the following
content:

    <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
    <%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

    <%@ page import="javax.portlet.PortletPreferences" %>

    <portlet:defineObjects />

    <%
    PortletPreferences prefs = renderRequest.getPreferences();
    String greeting = renderRequest.getParameter("greeting");
    if (greeting != null) {
        prefs.setValue("greeting", greeting);
        prefs.store();
    %>

        <p>Greeting saved successfully!</p>

    <%
    }
    %>

    <%
    greeting = (String)prefs.getValue(
        "greeting", "Hello! Welcome to our portal.");
    %>

    <portlet:renderURL var="editGreetingURL">
        <portlet:param name="mvcPath" value="/edit.jsp" />
    </portlet:renderURL>

    <aui:form action="<%= editGreetingURL %>" method="post">
        <aui:input label="greeting" name="greeting" type="text" value="<%=
    greeting %>" />
        <aui:button type="submit" />
    </aui:form>

    <portlet:renderURL var="viewGreetingURL">
        <portlet:param name="mvcPath" value="/view.jsp" />
    </portlet:renderURL>

    <p><a href="<%= viewGreetingURL %>">&larr; Back</a></p>

Deploy the portlet again in Developer Studio or by entering the command `ant
deploy` in your `my-greeting-portlet` folder. Go back to your web browser and
refresh the page; you should now be able to use the portlet to save and display
a custom greeting. 

![Figure 3.5: The *view* page of My Greeting portlet](../../images/portlets-view-my-greeting.png)

![Figure 3.6: The *edit* page of My Greeting portlet](../../images/portlets-edit-my-greeting.png)


+$$$

**Tip:** If your portlet deployed successfully, but you don't see any changes in
your browser after refreshing the page, Tomcat may have failed to rebuild your
JSPs. To fix this, delete the `work` folder in
`liferay-portal-[version]/tomcat-[tomcat-version]` and refresh the page again to
force them to be rebuilt. 

$$$

There are a few important details to note concerning this implementation. First,
the links between pages are created using the `<portlet:renderURL>` tag, which
is defined by the `http://java.sun.com/portlet_2_0` tag library. These URLs have
only one parameter named `mvcPath`. This is used by `MVCPortlet` to determine
which JSP to render for each request. Always use taglibs to generate URLs to
your portlet, because the portlet doesn't own the whole page, only a fragment of
it. The URL must always go to the portal responsible for rendering, and this
applies to your portlet and any others that the user might put in the page. The
portal will be able to interpret the taglib and create a URL with enough
information to render the whole page. 

Second, notice that the form in `edit.jsp` has the prefix `aui`, signifying that
it's part of the Alloy UI tag library. Alloy greatly simplifies the code
required to create attractive and accessible forms by providing tags that render
both the label and the field at once. You can also use regular HTML or any other
taglibs to create forms based on your own preferences. 

Another JSP tag you may have noticed is `<portlet:defineObjects/>`. The portlet
specification defined this tag in order to be able to insert a set of implicit
variables into the JSP that are useful for portlet developers, including
`renderRequest`, `portletConfig`, `portletPreferences`, etc. Note that the
JSR-286 specification defines four lifecycle methods for a portlet:
processAction, processEvent, render, and serveResource. Some of the variables
defined by the `<portlet:defineObjects/>` tag are only available to a JSP if the
JSP was included during the appropriate phase of the portlet lifecycle. The
`<portlet:defineObjects>` tag makes the following portlet objects available to a
JSP:

- `RenderRequest renderRequest`: represents the request sent to the portlet to
  handle a render. `renderRequest` is only available to a JSP if the JSP was
  included during the render request phase.
- `ResourceRequest resourceRequest`: represents the request sent to the portlet
  for rendering resources. `resourceRequest` is only available to a JSP if the
  JSP was included during the resource-serving phase.
- `ActionRequest actionRequest`: represents the request sent to the portlet to
  handle an action. `actionRequest` is only available to a JSP if the JSP was
  included during the action-processing phase.
- `EventRequest eventRequest`: represents the request sent to the portlet to
  handle an event. `eventRequest` is only available to a JSP if the JSP was
  included during the event-processing phase.
- `RenderResponse renderResponse`: represents an object that assists the
  portlet in sending a response to the portal. `renderResponse` is only
  available to a JSP if the JSP was included during the render request phase.
- `ResourceResponse resourceResponse`: represents an object that assists the
  portlet in rendering a resource. `resourceResponse` is only available to a JSP
  if the JSP was included in the resource-serving phase.
- `ActionResponse actionResponse`: represents the portlet response to an action
  request. `actionResponse` is only available to a JSP if the JSP was included
  in the action-processing phase.
- `EventResponse eventResponse`: represents the portlet response to an event
  request. `eventResponse` is only available to a JSP if the JSP was included
  in the event-processing phase.
- `PortletConfig portletConfig`: represents the portlet's configuration
  including, the portlet's name, initialization parameters, resource bundle, and
  application context. `portletConfig` is always available to a portlet JSP,
  regardless of the request-processing phase in which it was included.
- `PortletSession portletSession`: provides a way to identify a user across more
  than one request and to store transient information about a user. A
  `portletSession` is created for each user client. `portletSession` is always
  available to a portlet JSP, regardless of the request-processing phase in
  which it was included. `portletSession` is `null` if no session exists.
- `Map<String, Object> portletSessionScope`: provides a Map equivalent to the
  `PortletSession.getAtrributeMap()` call or an empty Map if no session
  attributes exist.
- `PortletPreferences portletPreferences`: provides access to a portlet's
  preferences. `portletPreferences` is always available to a portlet JSP,
  regardless of the request-processing phase in which it was included.
- `Map<String, String[]> portletPreferencesValues`: provides a Map equivalent to
  the `portletPreferences.getMap()` call or an empty Map if no portlet
  preferences exist.

The variables made available by the `<portlet:defineObjects/>` tag reference are 
the same portlet API objects that are stored in the request object of the JSP.
For more information about these objects, please refer to
the Liferay's Portlet 2.0 Javadocs at
[http://docs.liferay.com/portlet-api/2.0/javadocs/](http://docs.liferay.com/portlet-api/2.0/javadocs/).

**A warning about our newly created portlet:** For the purpose of making our
example easy to follow, we cheated a little bit. The portlet specification
doesn't allow setting preferences from a JSP, because they are executed in what
is known as the render state. There are good reasons for this restriction, and
they're explained in the next section. 
