# Portlet Development

In this chapter we'll create and deploy a simple portlet using the Plugins SDK.
It will allow a customized greeting to be saved in the portlet's preferences and
then display it whenever the portlet is viewed. Last we'll clean up the
portlet's URLs by adding a friendly URL mapping. 

You're free to use any framework you prefer to develop your portlets, including
Struts, Spring MVC, or JSF. Here we'll use the Liferay MVCPortlet framework,
because it's simple, lightweight, and easy to understand. 

You don't have to be a Java developer to take advantage of Liferay's built-in
features (such as user and organization management, page building and content
management). An application developed using Ruby or PHP can be deployed as a
portlet using the Plugins SDK, and it will run seamlessly inside of Liferay. For
examples, check out the liferay-plugins repository from
[Github](http://github.com/liferay).

We'll discuss the following topics as we learn about developing portlets for
Liferay:

- Creating a Portlet project 

- Anatomy of a Portlet project

- Writing the My Greeting Portlet

- Understanding the two phases of Portlet Execution 

- Passing Information from the Action Phase to the Render Phase

- Developing a Portlet with Multiple Actions 

- Adding Friendly URL Mapping to the Portlet 

First, let's create the portlet that we'll use throughout this chapter. 

## Creating a Portlet

Portlet creation using the Plugins SDK is simple. There's a `portlets` folder
inside the Plugins SDK folder, where your portlet projects reside. The first
thing to do is give your portlet a project name (without spaces) and a display
name (which can have spaces). For the greeting portlet, the project name is
`my-greeting`, and the portlet title is `My Greeting`. 

Once you've named your portlet, you're ready to begin creating the project.
There are several different ways to create this portlet. Let's try it using
Liferay Developer Studio first, then by using the terminal. 

***In Developer Studio:***

1. Go to File &rarr; New &rarr; Liferay Project. 

2. Next, go through the following steps to create your new project: 

    2.1. Fill in the *Project* and *Display* names with *my-greeting-portlet*
    and *My Greeting*, respectively. 
    
    2.2. Select the Liferay Plugins SDK and Portal Runtime that you've 
    configured. 

    2.3. Select *Portlet* as your Plugin type. 

    2.4. Click *Finish*. 

![Figure 3.1: Creating the My Greeting portlet](../../images/02-portlet-development-1.png)

With Developer Studio, you can create a new plugin project or if you already
have a project, create a new plugin in an existing project. A single Liferay
project can contain multiple plugins. 

***Using the Terminal:*** Navigate to the `portlets` directory in the terminal
and enter the appropriate command for your operating system:

1. In Linux and Mac OS X, enter

    ./create.sh my-greeting "My Greeting"

2. In Windows, enter

    create.bat my-greeting "My Greeting"

You should get a BUILD SUCCESSFUL message from Ant, and there will now be a new
folder inside of the `portlets` folder in your Plugins SDK. This folder is your
new portlet project. This is where you will be implementing your own
functionality. Notice that the Plugins SDK automatically appends "-portlet" to
the project name when creating this folder.

Alternatively, if you will not be using the Plugins SDK to house your portlet
projects, you can copy your newly created portlet project into your IDE of
choice and work with it there. If you do this, you will need to make sure the
project references some `.jar` files from your Liferay installation, or you may
get compile errors. Since the Ant scripts in the Plugins SDK do this for you
automatically, you don't get these errors when working with the Plugins SDK.

To resolve the dependencies for portlet projects, see the classpath entries in
the `build-common.xml` file in the Plugins SDK project. You can determine from
the `plugin.classpath` and `portal.classpath` entries, which `.jar` files are
necessary to build your newly created portlet project. This is not a recommended
configuration, and we encourage you to keep your projects in the Plugins SDK. 


---

 ![tip](../../images/tip-pen-paper.png)**Tip**: If you are using a source
 control system such as Subversion, CVS, Mercurial, Git, etc., this might be
 a good moment to do an initial check-in of your changes. After building the
 plugin for deployment, several additional files will be generated that should
 *not* be handled by the source control system. 

---

### Deploying the Portlet 

Liferay provides a mechanism called auto-deploy that makes deploying portlets
(and any other plugin types) a breeze. All you need to do is drop the plugin's
WAR file into the deploy directory, and the portal makes the necessary changes
specific to Liferay and then deploys the plugin to the application server. This
is a method of deployment used throughout this guide.

---

 ![note](../../images/tip-pen-paper.png)**Note:** Liferay supports a wide
 variety of application servers. Many, such as Tomcat and Jboss, provide a
 simple way to deploy web applications by just copying a file into a folder and
 Liferay's auto-deploy mechanism takes advantage of that ability. You should be
 aware though, that some application servers, such as Websphere or Weblogic,
 require the use of specific tools to deploy web applications; Liferay's
 auto-deploy process won't work for them. 

---

***Deploying in Developer Studio***: Drag your portlet project onto your
server. When deploying your plugin, your server displays messages indicating
that your plugin was read, registered and is now available for use. 

	Reading plugin package for my-greeting-portlet
	Registering portlets for my-greeting-portlet
	1 portlet for my-greeting-portlet is available for use
	
If at any time you need to redeploy your portlet while in Developer Studio,
right-click your portlet located underneath your server and select *Redeploy*. 

***Deploying in the terminal***: Open a terminal window in your
`portlets/my-greeting-portlet` directory and enter

    ant deploy

A BUILD SUCCESSFUL message indicates your portlet is now being deployed. If you
switch to the terminal window running Liferay, within a few seconds you should
see the message `1 portlet for my-greeting-portlet is available for use`. If
not, double-check your configuration. 

In your web browser, log in to the portal as explained earlier. Hover over
*Add* at the top of the page and click on *More*. Select the *Sample* category,
and then click *Add* next to *My Greeting*. Your portlet appears in the
page below. 

![Figure 3.2: Adding the My Greeting portlet](../../images/portlets-add-my-greeting-portlet.png)

Congratulations, you've just created your first portlet! 

## Anatomy of a Portlet

A portlet project is made up of at least three components:

1. Java Source. 

2. Configuration files. 

3. Client-side files (`.jsp`, `.css`, `.js`, graphics files, etc.). 

When using Liferay's Plugins SDK, these files are stored in a standard directory
structure:

- `PORTLET-NAME/`
    
    - `build.xml`
    
    - `docroot/`
     
        - `css/`
        
        - `js/`
        
        - `WEB-INF/`
            
            - `src/` - this folder is not created by default.
            
            - `liferay-display.xml`
            
            - `liferay-plugin-package.properties`
            
            - `liferay-portlet.xml`
            
            - `portlet.xml`
            
            - `web.xml` - this file is not created by default.
        
        - `icon.png`
        
        - `view.jsp`

The portlet we just created is fully functional and deployable to your Liferay
instance. 

By default, new portlets use the MVCPortlet framework, a light framework that
hides part of the complexity of portlets and makes the most common operations
easier. The default MVCPortlet project uses separate JSPs for each portlet
mode: each of the registered portlet modes has a corresponding JSP with the
same name as the mode. For example, 'edit.jsp' is for edit mode and 'help.jsp'
is for help mode.

The **Java Source** is stored in the `docroot/WEB-INF/src` folder. 

The **Configuration Files** are stored in the `docroot/WEB-INF` folder. Files
stored here include the standard JSR-286 portlet configuration file
`portlet.xml`, as well as three optional Liferay-specific configuration files.
The Liferay-specific configuration files, while optional, are important if your
portlets will be deployed on a Liferay Portal server. Here's a description of
the Liferay-specific files:

- `liferay-display.xml`- Describes the category the portlet appears under in the
  *Add* menu of the Dockbar (the horizontal bar that appears at the top of the
  page to all logged-in users). 

- `liferay-portlet.xml`- Describes Liferay-specific enhancements for JSR-286
  portlets installed on a Liferay Portal server. For example, you can set
  an image icon to represent the app, trigger a job for the scheduler, and much
  more. A complete listing of this file's settings is in its DTD in the
  `definitions` folder in the Liferay Portal source code. 

- `liferay-plugin-package.properties`- Describes the plugin to Liferay's hot
  deployer. You can configure Portal Access Control List (PACL) properties,
  `.jar` dependencies, and more. 

**Client Side Files** are the `.jsp`, `.css`, and `.js` files that you
write to implement your portlet's user interface. These files should go in the
`docroot` folder, either in the root of the folder or in a folder structure that
makes sense for your application. Remember, with portlets you're only dealing
with a portion of the HTML document that is getting returned to the browser. Any
HTML code in your client side files must be free of global tags like `<html>`
or `<head>`.  Additionally, namespace all CSS classes and element IDs to prevent
conflicts with other portlets. Liferay provides two tools, a taglib and API
methods, to generate a namespace for you. 

### A Closer Look at the My Greeting Portlet

If you're new to portlet development, this section will enhance your
understanding of portlet configuration options. 

**docroot/WEB-INF/portlet.xml**

In the Plugins SDK, the portlet descriptor's default content looks like this
(shown using Developer Studio's Portlet Application Configuration Editor):

![Figure 3.3: Portlet XML file of the My Greeting portlet](../../images/02-portlet-development-4.png)

Here's a basic summary of what each element represents:

`portlet-name`: Contains the portlet's canonical name. Each portlet name is
  unique within the portlet application (that is, within the portlet plugin). In
Liferay Portal, this is also referred to as the portlet ID. 

- `display-name`: Contains a short name that's shown by the portal whenever
  this application needs to be identified. It's used by `display-name` elements.
The display name need not be unique. 

- `portlet-class`: Contains the fully qualified name of the class that handles
  invocations to the portlet. 

- `init-param`: Contains a name/value pair as an initialization parameter of
  the portlet. 

- `expiration-cache`: Indicates the time, in seconds, after which the portlet
  output expires. A value of `-1` indicates that the output never expires. 

- `supports`: Contains the supported mime-type, and indicates the portlet modes
  supported for a specific content type. The concept of "portlet modes" is
defined by the portlet specification. Modes are used to separate certain views
of the portlet from others. The portal is aware of the portlet modes and
provides generic ways to navigate between them (for example, using links in the
box surrounding the portlet when it's added to a page), so they're useful for
operations that are common to all or most portlets. The most common usage is to
create an edit screen where each user can specify personal preferences for the
portlet. All portlets must support the view mode. 

- `portlet-info`: Defines portlet information. 

<!-- The definition for portlet-info is insufficient. For further information
about it, look at the JSR-286 reference document. --> 

- `security-role-ref`: Security-role-ref contains the declaration of a security
role reference in the code of the web application. Specifically in Liferay, the
`role-name` references which roles can access the portlet. 

**docroot/WEB-INF/liferay-portlet.xml**: In addition to the standard
`portlet.xml` options, there are optional Liferay-specific enhancements for Java
Standard portlets that are installed on a Liferay Portal server. By default, the
Plugins SDK sets the contents of this descriptor, as shown in Developer Studio:

![Figure 3.4: Liferay-Portlet XML file of the My Greeting portlet](../../images/02-portlet-development-5.png)

Here's a basic summary of what some of the elements represent. 

- `portlet-name`: Contains the canonical name of the portlet. This needs to be
  the same as the `portlet-name` specified in the `portlet.xml` file. 

- `icon`: Path to icon image for this portlet. 

- `instanceable`: Indicates whether multiple instances of this portlet can
appear on the same page. 

- `header-portlet-css`: The path to the `.css` file for this portlet to include
in the `<head>` tag of the page. 

- `footer-portlet-javascript`: The path to the `.js` file for this portlet, to
be included at the end of the page before the `</body>` tag. 

There are many more elements that you should be aware of for more advanced
development. They're all listed in the DTD for this file, which is found in the
`definitions` folder in the Liferay Portal source code.

## Writing the My Greeting Portlet

Let's make our portlet do something useful. First, we'll give it two pages:

- **view.jsp**: displays the greeting and provides a link to the *edit* page. 

- **edit.jsp**: shows a form with a text field, allowing the greeting to be
changed, and including a link back to the *view* page. 

The `MVCPortlet` class handles the rendering of our JSPs, so for this example,
we won't write a single Java class. 

First, since we don't want multiple greetings on the same page, let's make the
My Greeting portlet non-instanceable. Just edit `liferay-portlet.xml`, changing
the value of the element `instanceable` from `true` to `false`. Here's what it
looks like:

    <instanceable>false</instanceable>

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


---

 ![tip](../../images/tip-pen-paper.png)**Tip:** If your portlet deployed
 successfully, but you don't see any changes in your browser after refreshing
 the page, Tomcat may have failed to rebuild your JSPs. To fix this, delete the
`work` folder in `liferay-portal-[version]/tomcat-[tomcat-version]` and refresh
the page again to force them to be rebuilt. 

---

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
variables into the JSP that are useful for portlet developers, such as
`renderRequest`, `portletConfig`, `portletPreferences`, etc. 

<!-- It might be helpful to list these variables here. --> 

**A warning about our newly created portlet:** For the purpose of making this
example easy to follow, we cheated a little bit. The portlet specification
doesn't allow setting preferences from a JSP, because they are executed in what
is known as the render state. There are good reasons for this restriction, and
they're explained in the next section. 

## Understanding the Two phases of Portlet Execution

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

1. First, the user would navigate to an item she wants to buy, and eventually
submit the order, charging an amount on her credit card. After this operation,
the portal would also invoke the navigation portlet with its default view. 

2. Next, say the user clicks a link in the navigation portlet. This initiates an
HTTP request/response cycle, and causes the content of the portlet to change.
But all the parameters are preserved during that cycle, including the ones from
the shopping cart! Since the portal must also show the content of the shopping
portlet, it repeats the last action (the one in which the user clicked a
button), which causes a new charge on the credit card and the start of a new
shipping process! 

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
time. It is the result of a user interaction with the portlet. In this
phase the portlet can change its status, for instance changing the user
preferences of the portlet. Any inserts and modifications in the database or
operations that should not be repeated must be performed in this phase. 

- **Render phase**: The render phase is always invoked for all portlets on the
page after the action phase (which may or not exist). This includes the portlet
that also had executed its action phase. It's important to note that the order
in which the render phase of the portlets in a page gets executed is not
guaranteed by the portlet specification. Liferay has an extension to the
specification through the element `render-weight` in `liferay-portlet.xml`.
Portlets with a higher render weight will be rendered before those with a lower
weight. 

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
as well as makng AJAX requests to the server. Most importanlty, it differs from
the other two in that the portlet has full control of the data that is sent in
response. 

Let's change the `edit.jsp` file to use an *actionURL*, using the JSP tag of
the same name. We'll also remove the previous code that was saving the
preference:

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

## Passing Information from the Action Phase to the Render Phase

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

---

 ![tip](../../images/tip-pen-paper.png)**Tip:** Liferay offers a convenient
 extension to the portlet specification through the `MVCPortlet` class to copy
 all action parameters directly as render parameters. You can achieve this by
 setting the following `init-param` in your `portlet.xml`:

    <init-param>
        <name>copy-request-parameters</name>
        <value>true</value>
    </init-param>

---

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
    greeting = (String)prefs.getValue( "greeting", "Hello! Welcome to our
    portal."); %>

    <p><%= greeting %></p> <portlet:renderURL var="editGreetingURL">
    <portlet:param name="mvcPath" value="/edit.jsp" />

    </portlet:renderURL>

    <p><a href="<%= editGreetingURL %>">Edit greeting</a></p>

After this change, redeploy the portlet, go to the edit screen and save it. You
should see a nice message that looks like this:

![Figure 3.7: The sample "My Greetings" portlet showing a success message](../../images/portlet-greeting-save.png)

There's also an equivalent utility class for error notification; it's commonly
used after catching an exception in the `processAction` method. For example:

    try {
        prefs.setValue("greeting", greeting);
        prefs.store();
    }
    catch(Exception e) {
        SessionErrors.add(actionRequest, "error");
    }

The error, if it exists, is shown in your `view.jsp` using the
`liferay-ui:error` tag:

    <liferay-ui:error key="error" message="Sorry, an error prevented saving
    your greeting" />

If an error occurred, you'd see this in your portlet:

![Figure 3.8: The sample "My Greetings" portlet showing an error message](../../images/portlet-invalid-data.png)

The first message is automatically added by Liferay. The second one is the one
you added in your JSP. 

## Developing a Portlet with Multiple Actions

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

## Adding Friendly URL Mapping to the Portlet

When you click the *Edit greeting* link, you're taken to a page with a URL that
looks like this:

    http://localhost:8080/web/guest/home?p_p_id=mygreeting_WAR_mygreetingportlet&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&p_p_col_id=column-1&_mygreeting_WAR_mygreetingportlet_mvcPath=%2Fedit.jsp
    
Since Liferay 6, there's a built-in feature that can easily change the ugly URL
above to this:

    http://localhost:8080/web/guest/home/-/my-greeting/edit
    
The feature is called friendly URL mapping. It takes unnecessary parameters out
of the URL and allows you to place the important parameters in the URL path,
rather than in the query string. To add this functionality, first edit
`liferay-portlet.xml` and add the following lines directly after `</icon>` and
before `<instanceable>`. 

    <friendly-url-mapper-class>
    	com.liferay.portal.kernel.portlet.DefaultFriendlyURLMapper
    </friendly-url-mapper-class>
    <friendly-url-mapping>my-greeting</friendly-url-mapping>
    <friendly-url-routes>
    	com/liferay/samples/my-greeting-friendly-url-routes.xml
    </friendly-url-routes>

Next, create the file (remove the line break):

    my-greeting-portlet/docroot/WEB-INF/src/com/liferay/samples/my\
    -greeting-friendly-url-routes.xml

Create new directories as necessary. Place the following content into the new
file:

    <?xml version="1.0"?>
    <!DOCTYPE routes PUBLIC "-//Liferay//DTD Friendly URL Routes 6.1.0//EN" 
    "http://www.liferay.com/dtd/liferay-friendly-url-routes_6_1_0.dtd">

    <routes>
        <route>
            <pattern>/{mvcPathName}</pattern>
            <generated-parameter name="mvcPath">/{mvcPathName}.jsp</generated-parameter>
        </route>
    </routes>

Redeploy your portlet, refresh the page, and try clicking either of the links
again. 

![Figure 3.9: Friendly URL for view JSP](../../images/portlets-my-greeting-view-friendly.png)

Notice how much shorter and more user-friendly the URL is, without even having
to modify the JSPs. 

![Figure 3.10: Friendly URL for edit JSP](../../images/portlets-my-greeting-edit-friendly.png)

For more information on friendly URL mapping, there's a detailed discussion in
[*Liferay in Action*](http://manning.com/sezov). Our next step here is to
explore localization of the portlet's user interface. 

## Localize Your Portlet

If your portlets target an international audience, you can localize the user
interface. Localizing your portlet's language is done using language keys for
each language you wish to support. You can translate these manually or 
use a web service to translate them for you. Conveniently, all existing
translated messages in the portal core are accessible from plugin projects. You
can check for the presence of a key in the core `Language.properties` file and
include it in your jsp with the `<liferay-ui:message>` tag:

    <liferay-ui:message key="your-message-key" />

This procedure saves you time, since these keys always have up to date
translations for multiple languages. Additionally, your portlet blends better
into Liferay's UI conventions. 

If you need to add localization keys, follow the instructions below to deliver
a locally tailored portlet to your customers. 

### Your Localization Plan 

First consider some questions that will make our life easier as we develop your
localization:

- Does my plugin contain more than one portlet? This is very important if
  portlets share the same UI elements and messages; you don't want to maintain
  the same data in ten places. 

- Do my portlets need localized titles in portlet headers and administrative
  tools? 

- Do my portlets have to be accessible in the Control Panel? This is important
  if you want to provide your customers fancy *Title* and *Description* features
  like the majority of core liferay portlets. 

Let's proceed, assuming you answered "yes" to all of the above questions. 

### Create Resource Bundles

First, let's create resource bundles files for translating the fictional
portlets My Finances, Asset Ticker and Portfolio Manager. All three portlets
share some attributes:

- They use existing Liferay core messages to handle standard UI cases. 

- They use common financial terms, so we don't want to repeat them for
each portlet. 

- They have special keys like title, description or some context-related
content. 

Assuming you already created a plugin project and added portlets, let's
start:

1. Create a `content` package in your `src` plugin project folder. 

2. Create the `Language.properties` file to define all the keys our portlets
   need. 

3. For each portlet, update its `<portlet>` node in `portlet.xml` to refer to
   the resource bundle correctly:

    <portlet>
        <portlet-name>finances</portlet-name>
        ...
        <resource-bundle>content.Language</resource-bundle>
        ...
    </portlet>
    <portlet>
		<portlet-name>portfolio</portlet-name>
        ...
        <resource-bundle>content.Language</resource-bundle>
        ...
    </portlet>
    <portlet>
        <portlet-name>ticker</portlet-name>
        ...
        <resource-bundle>content.Language</resource-bundle>
        ...
    </portlet>

At this point our portlets are ready to deliver a localized UI. 

**Please note:** It's best to use the Liferay naming convention for language
bundles so your portlets can share properties, and the `build-lang` Ant task
works. 

Once you create `Language.properties` and have filled it with keys and values,
use the Ant `build-lang` task to build the translations. The `build-lang` task
performs the following operations:

- Creates translation files for other languages. 

- Translates English text to target language if you've set up the API key. 

- Keeps all created translations synchronized with `Language.properties`. You can
  run this task any time during development. It significantly reduces the 
  time spent on the maintanance of translations. 

Next, let's localize portlet titles and descriptions. 

<!-- The above is no longer correct. Yahoo shut down their Translate service,
and now you need to edit your build.[username].properties file and provide a
Bing translate key to get the automatic translations to work. --> 

### Portlet Title and Description In Control Panel

You may have noticed that your Control Panel-enabled portlets are missing that
super-fancy must-have portlet title and description in Control Panel. To make
your portlet look cool within the Control Panel, create specially tailored
description and title keys in `Language.properties`. You can do this by creating
a key from the following components:

- `javax.portlet.title.`: prefix that marks the key as title. 

- `javax.portlet.description.`: prefix that marks the key as description. 

- portlet name is defined in the `<portlet-name>` node but with all spacers
removed (eg. My Portlet would be myportlet). 

- token `WAR`.

- plugin name (as created by the create script) but with all spacers/delimiters
  removed (eg. personal-finance-portlet would be personalfinanceportlet). 

For example, if we had a portlet called *Portfolio*, in a portlet project called
*personal-finance-portlet*, our key would look like this:
`javax.portlet.title.portfolio_WAR_personalfinanceportlet`. If you prefer a
shorter key, and you can keep track of which portlet is which, apply the Liferay
core portlet naming convention--set your portlet names as numbers! If we change
our `portlet.xml` this way:

    <portlet>
        <portlet-name>1</portlet-name>
        ...
        <resource-bundle>content.Language</resource-bundle>
        ...
    </portlet>
    <portlet>
        <portlet-name>2</portlet-name>
        ...
        <resource-bundle>content.Language</resource-bundle>
        ...
    </portlet>
    <portlet>
        <portlet-name>3</portlet-name>
        ...
        <resource-bundle>content.Language</resource-bundle>
        ...
    </portlet>

our title and description keys in `Language.properties` would be:

    javax.portlet.description.1_WAR_personalfinanceportlet=...
    javax.portlet.description.2_WAR_personalfinanceportlet=...
    javax.portlet.description.3_WAR_personalfinanceportlet=...
    javax.portlet.title.1_WAR_personalfinanceportlet=...
    javax.portlet.title.2_WAR_personalfinanceportlet=...
    javax.portlet.title.3_WAR_personalfinanceportlet=...


---

 ![tip](../../images/tip-pen-paper.png)**Tip:** Do you know how your portlet
 title is processed? If your portlet doesn't define a resource bundle or
 `javax.portlet.title`, the portal container next checks the `<portlet-info>`
and inner `<portlet-title>` node in the `portlet.xml` descriptor. If they're
missing too, the `<portlet-name>` node value is rendered as portlet title. 

---

---

 ![tip](../../images/tip-pen-paper.png)**Tip:** Be aware that using Struts
 portlet and referring to a `StrutsResource` bundle in your `portlet.xml` engages
 a different title and description algorithm. Titles and long titles are pulled
 using two different keys:

- javax.portlet.long-title.1_WAR_personalfinanceportlet
- javax.portlet.title.1_WAR_personalfinanceportlet

---

### Overriding Liferay portal translations

If you want your translations available throughout the portal, or if you want to
override an existing translation, refer to Chapter 6 of this guide, specifically
the *Overriding a Language.properties File* section. It describes how to use a
hook to override existing Liferay translations. You can share your keys with
other portlets, as well as override existing Liferay translations.  

Next let's use the Plugins SDK to create a plugin that extends another plugin. 

## Creating Plugins to extend Plugins

For Liferay plugins, you can create a new plugin that extends an existing one.
By extending a plugin, you can use all its features in your new plugin while
keeping your changes/extensions separate from the existing plugin's source code. 

To create a plugin which extends another, follow these steps: 

1. Create a new empty plugin in the Plugins SDK. 

2. Remove all the auto-generated files except `build.xml` and the docroot
   folder, which should be empty. 

3. Copy the original WAR file of the plugin you'd like to extend (for example,
   social-networking-portlet-6.1.10.1-ee-ga1.war) to the root folder of your new
   plugin. 

4. Add the following line to your `build.xml` inside of the `<project>` tag to
   reference the original WAR file you are going to extend:

        <property name="original.war.file" value="social-networking-portlet-6.1.10.1-ee-ga1.war" />

5. Copy any files from the original plugin that you're overwriting to your
   new plugin (using the same folder structure) and run the Ant target `merge`.
   Please note that the `merge` target is called whenever the plugin is compiled.
   All you have to do is to check the Ant output:

        dsanz@host:~/sdk/portlets/my-social-networking-portlet$ ant war
        Buildfile: /home/dsanz/sdk/portlets/my-social-networking-portlet/build.xml
        
        compile:
        
        merge:
        [mkdir] Created dir: /home/dsanz/sdk/portlets/my-social-networking-portlet/tmp
        [mkdir] Created dir: /home/dsanz/sdk/portlets/my-social-networking-portlet/tmp/WEB-INF/classes
        [mkdir] Created dir: /home/dsanz/sdk/portlets/my-social-networking-portlet/tmp/WEB-INF/lib
        
        merge-unzip:
        [unzip] Expanding: /home/dsanz/sdk/portlets/my-social-networking-portlet/social-networking-portlet-6.1.10.1-ee-ga1.war into /home/dsanz/sdk/portlets/my-social-networking-portlet/tmp
        [copy] Copying 2 files to /home/dsanz/sdk/portlets/my-social-networking-portlet/tmp
        [mkdir] Created dir: /home/dsanz/sdk/portlets/my-social-networking-portlet/docroot/WEB-INF/classes
        
        ...

This generates a plugin (you can find the WAR file in the `/dist` folder of your
plugins SDK) which combines the original one with your changes. 

### Summary

If localization is important for your portlets, always consider statements in a
*localization plan*, since some portlets in your plugin and hard customer
requests can make a mess in your localization files and keys. If possible,
reuse Liferay core language keys since they're already translated for you. 

If there's no key you can use, you can create your own, as described in this
chapter. Liferay gives you the tools to make localization possible, and uses a
web service to provide rudimentary translations. 

<!-- This summary doesn't summarize the whole chapter. --> 
