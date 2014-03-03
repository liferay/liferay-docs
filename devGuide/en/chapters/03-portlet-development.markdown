# Portlet Development [](id=portlet-developme-3)

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
- Localizing your Portlet 
- Implementing Configurable Portlet Preferences

First, let's create the portlet that we'll use throughout this chapter. 

## Creating a Portlet [](id=creating-a-portl-3)

Portlet creation using the Plugins SDK is simple. There's a `portlets` folder
inside the Plugins SDK folder, where your portlet projects reside. The first
thing to do is give your portlet a project name (without spaces) and a display
name (which can have spaces). For the greeting portlet, the project name is
`my-greeting`, and the portlet title is `My Greeting`. 

Once you've named your portlet, you're ready to begin creating the project.
There are several different ways to create this portlet. Let's try it using
Liferay Developer Studio first, then by using the terminal. 

***In Developer Studio:***

1.  Go to File &rarr; New &rarr; Liferay Project. 

2.  Fill in the *Project name* and *Display name* with *my-greeting-portlet* and 
    *My Greeting*, respectively.  
    
3.  Leave the *Use default location* checkbox checked. By default, the
	default location is set to your current workspace. If you'd like to change
	where your plugin project is saved in your file system, uncheck the box and
	specify your alternate location. 

    ![Figure 3.1: Creating portlet projects with Liferay IDE is easy.](../../images/03-new-portlet-project.png)

4.  Select the *Ant (liferay-plugins-sdk)* option for your build type. If
	you'd like to use *Maven* for your build type, navigate to the [Using Liferay
	IDE with
	Maven](http://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/use-liferay-ide-with-maven-liferay-portal-6-1-dev-guide-en)
	section for details. 

5.  Your configured SDK and Liferay Runtime should already be selected. If you
    haven't yet pointed Liferay IDE to a Plugins SDK, click *Configure SDKs* to
    open the *Installed Plugin SDKs* management wizard. You can also access the
    *New Server Runtime Environment* wizard if you need to set up your runtime
    server; just click the *New Liferay Runtime* button next to the *Liferay
    Portal Runtime* dropdown menu. 

6.  Select *Portlet* as your Plugin type. 

7.  Click *Next*. 

8. In the next window, make sure that the *Liferay MVC* framework is selected
   and click *Finish*. 

With Developer Studio, you can create a new plugin project or if you already
have a project, create a new plugin in an existing project. A single Liferay
project can contain multiple plugins. 

***Using the Terminal:*** Navigate to the `portlets` directory in the terminal
and enter the appropriate command for your operating system:

1.  In Linux and Mac OS X, enter

        ./create.sh my-greeting "My Greeting"

2.  In Windows, enter

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

### Deploying the Portlet [](id=lp-6-1-dgen03-deploying-the-portlet-0)

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

## Anatomy of a Portlet [](id=anatomy-of-a-portl-4)

A portlet project is made up of at least three components:

1.  Java Source. 

2.  Configuration files. 

3.  Client-side files (`.jsp`, `.css`, `.js`, graphics files, etc.). 

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

### A Closer Look at the My Greeting Portlet [](id=lp-6-1-dgen03-a-closer-look-at-the-my-greeting-portlet-0)

If you're new to portlet development, this section will enhance your
understanding of portlet configuration options. 

**docroot/WEB-INF/portlet.xml**

In the Plugins SDK, the portlet descriptor's default content looks like this
(shown using Developer Studio's Portlet Application Configuration Editor):

![Figure 3.3: Portlet XML file of the My Greeting portlet](../../images/02-portlet-development-4.png)

Here's a basic summary of what each element represents:

- `portlet-name`: Contains the portlet's canonical name. Each portlet name is
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
  provides generic ways to navigate between them (for example, using links in
  the box surrounding the portlet when it's added to a page), so they're useful
  for operations that are common to all or most portlets. The most common usage
  is to create an edit screen where each user can specify personal preferences
  for the portlet. All portlets must support the view mode. 
- `portlet-info`: Defines information that can be used for the portlet title-bar
  and for the portal's categorization of the portlet. The JSR-286 specification
  defines a few resource elements that can be used for these purposes: `title`,
  `short-title`, and `keywords`. You can either include resource elements 
  directly in a `portlet-info` element or you can place them in resource
  bundles.

    Specifying the information directly into the `portlet-info` element in your
    `portlet.xml` file is straightforward. For example, to you could specify a
    weather portlet's information, like this:

        <portlet>
            ...
            <portlet-info>
                <title>Weather Portlet</title>
                <short-title>Weather></short-title>
                <keywords>weather,forecast</keywords>
            </portlet-info>
            ...
        </portlet>

    Alternatively, you can specify this same information as resources in a
    resource bundle file for your portlet. For example, you could create file
    `docroot/WEB-INF/src/content/Language.properties`, in your portlet project,
    to specify your portlet's title, short title, and keywords:

        # Default Resource Bundle
        #
        # filename: Language.properties
        # Portlet Info resource bundle example
        javax.portlet.title=Weather Portlet
        javax.portlet.short-title=Weather
        javax.portlet.keywords=weather,forecast

    To use the resource bundle, you'd reference it in your `portlet.xml`
    file, in your `portlet` element just before your `portlet-info` element:

        <portlet>
            ...
            <resource-bundle>content.Language</resource-bundle>
            <portlet-info>...</portlet-info>
            ...
        </portlet>

    As a best practice, if you're not planning on supporting localized title,
    short title, and keywords values for your portlet, simply specify them
    within the `<portlet-info>` element in your `portlet.xml` file. Otherwise,
    if you're ready to provide localized values, use a resource bundle for 
    specifying your default values and specify the localized values in separate
    resource bundles.

    ---
    
    ![note](../../images/tip-pen-paper.png) **Note:** You should not specify
    values for a portlet's title, short title, and keywords in both a portlet's 
    `<portlet-info>` element in `portlet.xml` and in a resource bundle. But if
    by accident you do, the values in the resource bundle take precedence over
    the values in the `<portlet-info>` element.

    ---

    Specifying *localized* values for your portlet's title, short title, and
    keywords in resource bundles is easy. For example, if you're supporting
    German and English locales, you'd create `Language_de.properties` and
    `Language_en.properties` files, respectively, in your portlet's
    `docroot/WEB-INF/src/content/` directory. This is the same directory as your
    default resource bundle file `Language.properties`. The contents of the
    German and English resource bundles may look like the following: 

        # English Resource Bundle
        #
        # filename: Language_en.properties
        # Portlet Info resource bundle example
        javax.portlet.title=Weather Portlet
        javax.portlet.short-title=Weather
        javax.portlet.keywords=weather,forecast
        
        # German Resource Bundle
        #
        # filename: Language_de.properties
        # Portlet Info resource bundle example
        javax.portlet.title=Wetter Portlet
        javax.portlet.short-title=Wetter
        javax.portlet.keywords=wetter,vorhersage

    You'd reference your default bundle and these localized bundles in your
    `portlet.xml` file, like this:

        <portlet>
            ...
            <resource-bundle>content.Language</resource-bundle>
            <resource-bundle>content.Language_de</resource-bundle>
            <resource-bundle>content.Language_en</resource-bundle>
            <portlet-info>...</portlet-info>
            ...
        </portlet>

    If you're mavenizing your portlet, make sure to copy your `content` folder
    into your portlet's `src/main/webapp/WEB-INF/classes` folder. 

    For more information, see the JSR-286 portlet specification, at
    [http://www.jcp.org/en/jsr/detail?id=286](http://www.jcp.org/en/jsr/detail?id=286).

- `security-role-ref`: Contains the declaration of a security role reference in
  the code of the web application. Specifically in Liferay, the `role-name`
  references which roles can access the portlet. 

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

## Writing the My Greeting Portlet [](id=writing-the-my-greeting-portl-4)

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

## Understanding the Two phases of Portlet Execution [](id=understanding-the-two-phases-of-portlet-executi-4)

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

## Passing Information from the Action Phase to the Render Phase [](id=passing-information-from-the-action-phase-to-the-render-pha-4)

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

## Developing a Portlet with Multiple Actions [](id=developing-a-portlet-with-multiple-actio-4)

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

## Adding Friendly URL Mapping to the Portlet [](id=adding-friendly-url-mapping-to-the-portlet)

When you click the *Edit greeting* link, you're taken to a page with a URL that
looks like this:

    http://localhost:8080/web/guest/home?p_p_id=mygreeting_WAR_mygreetingportlet
        &p_p_lifecycle=0&p_p_state=normal&p_p_mode=view\&p_p_col_id=column-1&_my
        greeting_WAR_mygreetingportlet_mvcPath=%2Fedit.jsp
    
Since Liferay 6, there's a built-in feature that can easily change the ugly URL
above to this:

    http://localhost:8080/web/guest/home/-/my-greeting/edit
    
The feature is called friendly URL mapping. It takes unnecessary parameters out
of the URL and allows you to place the important parameters in the URL path,
rather than in the query string. To add this functionality, first edit
`liferay-portlet.xml` and add the following lines directly after `</icon>` and
before `<instanceable>` (remove the line breaks): 

    <friendly-url-mapper-class>com.liferay.portal.kernel.portlet.DefaultFriendl\
    yURLMapper</friendly-url-mapper-class>
    <friendly-url-mapping>my-greeting</friendly-url-mapping>
    <friendly-url-routes>com/liferay/samples/my-greeting-friendly-url-routes.xml\
    </friendly-url-routes>

Next, create the file (remove the line break):

    my-greeting-portlet/docroot/WEB-INF/src/com/liferay/samples/my\
    -greeting-friendly-url-routes.xml

Place the following content into the new file (remove the line break after
`{mvcPathName}.jsp`): 

    <?xml version="1.0"?>
    <!DOCTYPE routes PUBLIC "-//Liferay//DTD Friendly URL Routes 6.1.0//EN" 
    "http://www.liferay.com/dtd/liferay-friendly-url-routes_6_1_0.dtd">

    <routes>
        <route>
            <pattern>/{mvcPathName}</pattern>
            <generated-parameter name="mvcPath">/{mvcPathName}.jsp\
            </generated-parameter>
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

## Localizing your Portlet [](id=localize-your-portlet)

If your portlets target an international audience, you can localize the user
interface. Localizing your portlet's language is done using language keys for
each language you wish to support. You can translate these manually or use a web
service to translate them for you. Conveniently, all existing translated
messages in the portal core are accessible from plugin projects. You can check
for the presence of specific language keys in the core `Language.properties`
file found in `portal-impl/src/content`. Leveraging portal's core language keys
saves you time, since these keys always have up to date translations for
multiple languages. Additionally, your portlet blends better into Liferay's UI
conventions.

You can use a language key in your JSP via a `<liferay-ui:message />` tag. 

    <liferay-ui:message key="message-key" />

You specify the message key corresponding to the language key in the
`Language.properties` file you want to display. For example, to welcome a user
in their language, specify the message key named `welcome`.

    <liferay-ui:message key="welcome" />

This key maps to the of the word "Welcome", in your translation of it to the
user's locale. Here is the `welcome` language key from Liferay's
`Language.properties` file.

    welcome=Welcome

Let's add the `welcome` language key in front of our greeting in the
`view.jsp` file of the `my-greeting-portlet` we created earlier. Replace its
current greeting paragraph with this:

    <p><liferay-ui:message key="welcome" />! <%= greeting %></p>

Revisit the page to see the word "Welcome", from `Language.properties`, now
precedes your greeting!

Note, in order to use the `<liferay-ui:message />` tag, or any of the
`liferay-ui` tags, you must include the following line in your JSP. It imports
the `liferay-ui` tag library. 

    <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

The `<liferay-ui:message />` tag also supports passing strings as arguments to
a language key. For example, the `welcome-x` key expects one argument. Here is
`welcome-x` key from the `Language.properties` file:

    welcome-x=Welcome{0}!

It references `{0}`, which denotes the first argument of the argument list. An
arbitrary number of arguments can be passed in via message tag, but only those
arguments expected by the language key are used. The arguments are referenced in
order as `{0}`, `{1}`, etc. Let's pass in the user's screen name as an argument
to the `welcome-x` language key in the "My Greeting" portlet. 

1.  Open the `view.jsp` file. 

2.  Add the following lines near the top of the JSP, just above the
    `<portlet:defineObjects />` tag. The first line imports the `liferay-theme`
    tag library. The second line defines the library's objects, providing access
    to the `user` object holding the user's screen name. 

        <%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme"%>

        <liferay-theme:defineObjects />

3.  Replace the current welcome message tag and exclamation point,
    `<liferay-ui:message key="welcome" />!`, in the JSP with the following:

        <liferay-ui:message key="welcome-x" /> <%= user.getScreenName() %>

When you refesh your page, your "My Greeting" portlet greets you by your screen
name!

![Figure 3.11: By passing the user's screen name as an argument to Liferay's `welcome-x` language key, we were able to display a personalized greeting.](../../images/portlets-welcome-user-screenname.png)

Other message tags you'll want to use are the `<liferay-ui:success />` and
`<liferay-ui:error />` tags. The `<liferay-ui:success />` helps you give
positive feedback, marked up in a pleasant green background. The
`<liferay-ui:error />` tag helps you warn your users of invalid input or
exceptional conditions. The error messages are marked up in an appropriate red
background. 

The `<liferay-ui:success />` tag is triggered when its key value is found in the
`SessionMessages` object. Earlier in our `MyGreetingPortlet` class, we triggered
the success message `<liferay-ui:success key="success" ... />` by adding its key
to the `SessionMessages` object with the following call: 

    SessionMessages.add(actionRequest, "success");

Similarly, the `<liferay-ui:error />` tag is triggered when its key value is
found in the `SessionErrors` object. Likewise, in our `MyGreetingPortlet` class,
we triggered the error message `<liferay-ui:error key="error" ... />` by adding
its key to the `SessionErrors` object with the following call: 

    SessionErrors.add(actionRequest, "error");

That's all you need to do to leverage Liferay's core localization keys. If you
need to add localization keys, follow the instructions below to deliver locally
tailored portlets to your customers. 

### Your Localization Plan [](id=your-localization-plan)

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

We'll cover these different cases in the following sections. 

### Create Resource Bundles [](id=create-resource-bundles)

First, let's create a resource bundle for providing textual translations of
three related fictional portlets named My Finances, Asset Ticker and Portfolio
Manager. All three portlets share some attributes:

- They use existing Liferay core messages to handle standard UI cases. 
- They use common financial terms, so we don't want to repeat them for each
  portlet. 
  
Let's explore sharing translated text between these portlets. 

Assuming you already created a plugin project and added portlets, let's
start:

1.  Create a `content` package in your `src` plugin project folder. 

2.  Create the `Language.properties` file to define all the keys our portlets
    need. 

3.  For each portlet, update its `<portlet>` node in `portlet.xml` to refer to
    the resource bundle correctly:

        <portlet>
            <portlet-name>finances</portlet-name>
            ...
            <resource-bundle>content/Language</resource-bundle>
            <portlet-info>...</portlet-info>
            ...
        </portlet>
        <portlet>
            <portlet-name>portfolio</portlet-name>
            ...
            <resource-bundle>content/Language</resource-bundle>
            <portlet-info>...</portlet-info>
            ...
        </portlet>
        <portlet>
            <portlet-name>ticker</portlet-name>
            ...
            <resource-bundle>content/Language</resource-bundle>
            <portlet-info>...</portlet-info>
            ...
        </portlet>

    Make sure to put each `resource-bundle` element in its proper place in the
    `portlet` element. See the `portlet.xml` file's schema
    [http://java.sun.com/xml/ns/portlet/portlet-app_2_0.xsd](http://java.sun.com/xml/ns/portlet/portlet-app_2_0.xsd)
    for details. 

At this point our portlets are ready to deliver a localized UI.

**Please note:** It's best to use the Liferay naming convention for language
bundles so your portlets can share properties, and the Plugins SDK Ant task used
to build the translations works.

In order for a user to see a message in his own locale, the message value must
be specified in a resource bundle file with a name ending in his locale's two
character code. For example, a resource bundle file named
`Language_es.properties` containing a message property with key `welcome` must
be present with a Spanish translation of the word "Welcome". Good news, Plugins
SDK provides a means for you to get translations for your default resource
bundle.

The Plugins SDK uses the Bing Translator service
[http://www.microsofttranslator.com/](http://www.microsofttranslator.com/) to
translate all of the resources in your Language.properties file to multiple
languages. It provides a base translation for you to start with. To create base
translations using the Bing Translator service, you'll need to do the following:

1.  Signup for an Azure Marketplace account and register your application. Be
    sure to write down your ID and secret given to you for your application.

2.  Edit the `portal-ext.properties` file in your Liferay Home directory by
    adding the following two lines replaced with your values:

        microsoft.translator.client.id=your-id
        microsoft.translator.client.secret=your-secret

3.  In Developer Studio, right-click on the `Language.properties` file &rarr;
    Liferay &rarr; Build Languages.

    If prompted, choose the option to force Eclipse to accept the
    `Language.properties` file as UTF-8. Make sure you are connected to the
    Internet when you execute this. 

When the build completes, you'll find the generated files with all of the
translations, in the same folder as your `Language.properties` file.



---

 ![Note](../../images/tip-pen-paper.png) **Note:** If you're mavenizing your
 portlet, make sure to copy your `content` folder into your portlet's
 `src/main/webapp/WEB-INF/classes` folder. 

---

By using Studio's language building capability, you can keep all created
translations synchronized with your default `Language.properties`. You can run
it any time during development. It significantly reduces the time spent on the
maintanance of translations. Of course, you'll want to have someone fluent in
that language review the translation before deploying the translation to a
Production environment. 

Now that you know how to create a shared resource bundle, let's consider a case
in which you must use separate resource bundles for each portlet. In order to
localize messages used in the Control Panel for a Control Panel-enabled portlet,
you must use separate resource bundles. We'll show you how to implement them. 

### Portlet Title and Description In Control Panel [](id=lp-6-1-dgen03-portlet-title-and-description-in-control-panel-0)

You may have noticed that your Control Panel-enabled portlets are missing that
super-fancy must-have portlet title and description in the Control Panel. To
make your portlet look cool within the Control Panel, create specially tailored
description and title keys in separate `Language.properties` files for each
portlet in your project. You'll use the `javax.portlet.title` and
`javax.portlet.description` language keys. 

For demonstration purposes, let's consider a project that has one portlet named
`eventlisting` and another portlet named `locationlisting`. We'll need to create
a resource bundle for each of them to specify their localized title and
description values. 

---

 ![Note](../../images/tip-pen-paper.png) **Note:** If your project only has one
 portlet, it's best to put your resource bundle directly in the `content`
 folder. Specifying your bundle in file `content/Language.properties` lets you
 leverage the Plugins SDK's language building capabilities, via right-clicking
 on the `Language.properties` file &rarr; Liferay &rarr; Build Languages in
 Developer Studio or executing `ant build-lang` from the terminal. 

---

Here's what you'd do to localize the title and description for each portlet in
the project: 

1.  If you haven't done so already, configure each portlet to display in the
    Control Panel. For our example, we would display them in the *Content*
    portion and give them an arbitrary *weight* value for determining where
    they're to be placed in the column with respect to the other portlets.
    Here's a sample of how to specify this in our project's
    `liferay-portlet.xml` file: 
                   
		<portlet>
			<portlet-name>eventlisting</portlet-name>
			<icon>/icon.png</icon>
			<control-panel-entry-category>content</control-panel-entry-category>
			<control-panel-entry-weight>1.5</control-panel-entry-weight>
			....
		</portlet>
		<portlet>
			<portlet-name>locationlisting</portlet-name>
			<icon>/icon.png</icon>
			<control-panel-entry-category>content</control-panel-entry-category>
			<control-panel-entry-weight>1.6</control-panel-entry-weight>
			....
		</portlet>

2.  Create a namespaced folder to hold each portlet's resource bundle. It's a
    best practice to name each resource bundle folder based on the name of its
    portlet. 

    For example, you could create a resource bundler folder
    `content/eventlisting` for the `eventlisting` portlet and a folder
    `content/locationlisting` for the `locationlisting` portlet. 

3.  Create a `Language.properties` file in the resource bundle folders you just
    created. Specify the `javax.portlet.title` and `javax.portlet.description`
    language key/values in each of these `Language.properties` files. 

    The `eventlisting` portlet could have the following
    key/value pairs in its `content/eventlisting/Language.properties` file:

        javax.portlet.title=Event Listing Portlet
        javax.portlet.description=Lists important upcoming events.
 
    And the `locationlisting` portlet could have these key/value pairs
    in its `content/locationlisting/Language.properties` file: 

        javax.portlet.title=Location Listing Portlet
        javax.portlet.description=Lists event locations.

4.  Specify the resource bundles for the portlets in the project's `portlet.xml`
    file. The example `portlet.xml` file code snippet below demonstrates
    specifying the resource bundles for the `eventlisting` and `locationlisting`
    example portlets: 

        <portlet>
            <portlet-name>eventlisting</portlet-name>
            ...
            <resource-bundle>content.eventlisting.Language</resource-bundle>
            <portlet-info>...</portlet-info>
            ...
        </portlet>
        <portlet>
            <portlet-name>locationlisting</portlet-name>
            ...
            <resource-bundle>content.locationlisting.Language</resource-bundle>
            <portlet-info>...</portlet-info>
            ...
        </portlet>

5.  Redeploy your plugin project.

6.  Go to the Control Panel and select the Event Locations portlet.  

7.  Add `en` to your portal context in your URL to interface with the portal in
    Spanish. For example, your URL would start like this:

        http://localhost:8080/es/group/control_panel/...

Portal's Control Panel displays your portlet's localized title and description. 

![Figure 3.12: It's easy to localize titles and descriptions for multiple portlets in your project.](../../images/localized-portlet-title-desc-in-control-panel.png)

You're becoming an expert localizer!

---

 ![Tip](../../images/tip-pen-paper.png)**Tip:** Do you know how your portlet
 title is processed? If your portlet doesn't define a resource bundle or
 `javax.portlet.title`, the portal container next checks the `<portlet-info>`
 and inner `<portlet-title>` node in the `portlet.xml` descriptor. If they're
 missing too, the `<portlet-name>` node value is rendered as portlet title. 

---

---

 ![Note](../../images/tip-pen-paper.png)**Note:** Be aware that using Struts
 portlet and referring to a `StrutsResource` bundle in your `portlet.xml`
 engages a different title and description algorithm. Titles and long titles are
 pulled using two different keys:

 - `javax.portlet.long-title` 
 - `javax.portlet.title`

---

Now that you're comfortable localizing portlet content, you may want to learn
how to make translations available throughout the portal or how to override an
existing translation. For instructions on doing that, refer to Chapter 7 of this
guide, specifically the *Overriding a Language.properties File* section. It
describes how to use a hook to override existing Liferay translations. You can
share your keys with other portlets, as well as override existing Liferay
translations. 

Next, let's learn how to configure your portlets' preferences using
configuration actions.

## Implementing Configurable Portlet Preferences  [](id=lp-6-1-dgen03-implementing-configurable-portlet-preferences--0)

Portlet Preferences are properties for storing basic portlet configuration data.
Preferences are often used by administrators to provide customized views of a
portlet to subsets of users or even all of a portlet's users. Portlet
preferences are sometimes made accessible to users themselves for configuring a
portlet just the way they like it. Liferay simplifies making portlet preferences
configurable in your portlet's JSPs. In this section, we'll show you how to
create a default configuration JSP page and add a portlet preferences control to
it. 

We'll use the Location Listing Portlet we developed in the [Generating Your
Service
Layer](https://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/service-builder-liferay-portal-6-1-dev-guide-en)
chapter. We'll create a configuration page and add a custom option to it,
allowing administrators to hide the address portion of the locations. Let's dive
into portlet preferences by running through an example of creating a
configuration page for the Location Listing Portlet and setting up a new portlet
preference for it. 

First, if your Location Listing Portlet doesn't already have a setup tab in its
configuration page, you'll need to follow the steps below to create one. To
check to see whether your portlet has a setup tab, click the portlet's options
icon in the upper right corner and select *Configuration*. If you already have a
*Setup* tab, you can skip the next section. Otherwise we'll show you how to
create the default setup tab for your portlet's configuration page. 

### Creating a Default Setup Tab in the Portlet's Configuration Page  [](id=lp-6-1-dgen03-creating-a-default-setup-tab-in-the-portlets-configuration--0)

Open the `liferay-portlet.xml` file and add the element
`<configuration-action-class>com.liferay.portal.kernel.portlet.DefaultConfigurationAction</configuration-action-class>`
below your Location Listing Portlet's `<icon>...</icon>` tag. Here's a snippet
to show you where it goes in the context of your `liferay-portlet.xml` file: 

    ....
	<portlet>
		<portlet-name>locationlisting</portlet-name>
		<icon>/icon.png</icon>
		<configuration-action-class>com.liferay.portal.kernel.portlet.DefaultConfigurationAction</configuration-action-class>
		<header-portlet-css>/css/main.css</header-portlet-css>
		<footer-portlet-javascript>
			/js/main.js
		</footer-portlet-javascript>
		<css-class-wrapper>locationlisting-portlet</css-class-wrapper>
	</portlet>
	....

Notice that we've listed the *default* configuration action class. We'll update
this tag with a custom configuration class later in the exercise. If you
redeploy your portlet and open your portlet's *Configuration* page, you'll find
the new *Setup* tab. It's empty for now. But we'll add a portlet preference
control to it shortly. 

![Figure 3.13: Simply by specifying Liferay's default configuration action class in your portlet's `liferay-portlet.xml` file, you provide your portlet with a *Setup* tab for adding your portlet's configuration setup options.](../../images/portlet-default-configuration-jsp.png)

In order to add a configurable portlet preference to the portlet, we must do the
following things: 

1. Specify a Configuration JSP in the `portlet.xml`
2. Create the Configuration JSP for Displaying the Portlet Preference Options
3. Create a Configuration Action Implementation Class for Processing the
Portlet Preference Value
4. Modify the View JSP to Respond to the Current Portlet Preference Value

Let's specify a configuration JSP file, first. 

### Step 1: Specify a Configuration JSP in the `portlet.xml`  [](id=lp-6-1-dgen03-step-1-specify-a-configuration-jsp-in-the-portletxml--0)

Your portlet will need a way to display configuration options to the user.
Liferay checks to see if your portlet specifies a configuration JSP via a
`config-template` initialization parameter in your `portlet.xml` file. Let's
specify one for the Location Listing Portlet. 

Open the `portlet.xml` file and insert the following lines after the Location
Listing Portlet's `<portlet-class>...</portlet-class>` tag: 

     <init-param>
         <name>config-template</name>
         <value>/html/locationlisting/configuration.jsp</value>
     </init-param>

### Step 2: Create the Configuration JSP for Displaying the Portlet Preference Options  [](id=lp-6-1-dgen03-step-2-create-the-configuration-jsp-for-displaying-the-port-0)

We'll create a configuration JSP file and add JavaScript to let the user select
a portlet preference value. For our example, we'll provide a custom option in
the configuration page's setup tab, allowing administrators to show or hide
location address information. 

In the `docroot/html/locationlisting` directory, create a file named
`configuration.jsp`, if you don't have one already. 

Now let's begin creating our portlet preference for the configuration page's
setup content. Assuming that you began with a blank `configuration.jsp` file,
add the following code to it: 

	<%@include file="/html/init.jsp" %>

	<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

	<%  
	boolean showLocationAddress_cfg = GetterUtil.getBoolean(portletPreferences.getValue("showLocationAddress", StringPool.TRUE));
	%>

	<aui:form action="<%= configurationURL %>" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

		<aui:input name="preferences--showLocationAddress--" type="checkbox" value="<%= showLocationAddress_cfg %>" />

		<aui:button-row>
			<aui:button type="submit" />
		</aui:button-row>
	</aui:form>

The `showLocationAddress_cfg` variable holds the current value of whether to
show the location addresses or not. The input checkbox lets the user set the
value of the `showLocationAddress` portlet preference *key*. In order for the
value to be persisted, the input must follow the naming convention
`preferences--somePreferenceKey--`. For our example, the input name
`preferences--showLocationAddress--` maps the input value to a portlet
preference key named `showLocationAddress`. 

You've probably noticed some JSP compile errors and warnings with respect to the
code we've added. We'll address them by adding directives to the `init.jsp` that
our configuration JSP includes. Adding the directives will allow the JSP to
access the classes and taglibs we're using. 

In your `init.jsp` file, add the following directives:

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
    <%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>

    <%@ page import="com.liferay.portal.kernel.util.Constants" %>
    <%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
    <%@ page import="com.liferay.portal.kernel.util.StringPool" %>

    <liferay-theme:defineObjects />

The `tablib` directives access the JSP Standard Tag Library (JSTL), Liferay's
theme taglib, and Liferay's portlet taglib. Then, we added directives for
importing the classes we're using. Lastly, we inserted the
`<portlet:defineObjects />` tag to access implicit variables that we'll need. It
provides useful portlet variables such as *renderRequest*, *portletConfig*, and
*portletPreferences*. 

Your `configuration.jsp` is all set to display your portlet preference options.
Let's implement a custom class to handle the configuration action. 

### Step 3: Create a Configuration Action Implementation Class for Processing the Portlet Preference Value  [](id=lp-6-1-dgen03-step-3-create-a-configuration-action-implementation-class-f-0)

Now let's create a custom configuration action class for accessing the portlet
preference. We'll have it extend the
`DefaultConfigurationAction` class.
   
Create a package named `com.nosester.portlet.eventlisting.action` in the
portlet's `docroot/WEB-INF/src` directory. In the new package, create a class
named `ConfigurationActionImpl`, and specify
[`DefaultConfigurationAction`](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/kernel/portlet/DefaultConfigurationAction.html)
as its superclass. 

Replace the contents of `ConfigurationActionImpl.java` with the following code: 

    package com.nosester.portlet.eventlisting.action;

    import javax.portlet.ActionRequest;
    import javax.portlet.ActionResponse;
    import javax.portlet.PortletConfig;
    import javax.portlet.PortletPreferences;

    import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;

    public class ConfigurationActionImpl extends DefaultConfigurationAction {

        @Override
        public void processAction(
            PortletConfig portletConfig, ActionRequest actionRequest,
            ActionResponse actionResponse) throws Exception {  

            super.processAction(portletConfig, actionRequest, actionResponse);

            PortletPreferences prefs = actionRequest.getPreferences();

            String showLocationAddress = prefs.getValue(
                "showLocationAddress", "true");

            System.out.println("showLocationAddress=" + showLocationAddress +
                " in ConfigurationActionImpl.processAction().");
        }
    }

Notice we've extended the `DefaultConfigurationAction` class and added a new
`processAction()` method. The super-class's `processAction()` method is
responsible for reading the portlet preferences from the configuration form and
storing them in the database. Usually, you'd add appropriate validation logic
for the parameters received from the form. Our bare-bones example simply
demonstrates accessing the preferences from the action request. 

Another common method to include in a custom configuration action class is a
`render()` method. The render method is invoked when the user clicks the
configuration icon. For this example, we'll stick with the original render
method from the `DefaultConfigurationAction` class we're extending.

---

 ![Note](../../images/tip-pen-paper.png) **Note:** You won't need to store
 portlet preferences by calling `preferences.store()` since they're
 automatically stored in the `DefaultConfigurationAction` class, which your
 configuration class extends.

---

Lastly, let's specify our new custom configuration class in the
`liferay-portlet.xml`. Replace the existing `<configuration-action-class>...</configuration-action-class>`
with
`<configuration-action-class>com.nosester.portlet.eventlisting.action.ConfigurationActionImpl</configuration-action-class>`.
Here's a snippet to show you where it goes in the context of the
`liferay-portlet.xml` file: 

    ....
    <portlet>
        <portlet-name>locationlisting</portlet-name>
        <icon>/icon.png</icon>
        <configuration-action-class>com.nosester.portlet.eventlisting.action.ConfigurationActionImpl</configuration-action-class>
        <header-portlet-css>/css/main.css</header-portlet-css>
        <footer-portlet-javascript>
            /js/main.js
        </footer-portlet-javascript>
        <css-class-wrapper>locationlisting-portlet</css-class-wrapper>
    </portlet>
    ....

Since your configuration action implementation is ready to process your portlet
preference, let's update the view JSP to respond to the portlet preference. 

### Step 4: Modify the View JSP to Respond to the Current Portlet Preference Value  [](id=lp-6-1-dgen03-step-4-modify-the-view-jsp-to-respond-to-the-current-portle-0)

Let's add logic in our `view.jsp` to show/hide the location addresses based on
the value of our portlet preference key `showLocationAddress`. 

In the `view.jsp` file, we'll get the value of the `showLocationAddress` portlet
preference key. If its value is `true`, we'll display all of the location
fields, including the address fields; otherwise, we'll hide the address fields. 

Below are the contents of the entire `view.jsp` file. We'll point out the code
that handles the portlet preference, after this code listing: 

    <%@ include file="/html/init.jsp" %>

    This is the <b>Location Listing Portlet</b> in View mode.

    <%
        String redirect = PortalUtil.getCurrentURL(renderRequest);
    %>

    <aui:button-row>
        <portlet:renderURL var="addLocationURL">
            <portlet:param name="mvcPath" value="/html/locationlisting/edit_location.jsp" />
            <portlet:param name="redirect" value="<%= redirect %>" />
        </portlet:renderURL>

        <aui:button onClick="<%= addLocationURL.toString() %>" value="add-location" />
    </aui:button-row>

    <%
    boolean showLocationAddress_view = GetterUtil.getBoolean(portletPreferences.getValue("showLocationAddress", StringPool.TRUE));
    %>

    <liferay-ui:search-container emptyResultsMessage="location-empty-results-message">
        <liferay-ui:search-container-results
            results="<%= LocationLocalServiceUtil.getLocationsByGroupId(scopeGroupId, searchContainer.getStart(), searchContainer.getEnd()) %>"
            total="<%= LocationLocalServiceUtil.getLocationsCountByGroupId(scopeGroupId) %>"
        />

        <liferay-ui:search-container-row
            className="com.nosester.portlet.eventlisting.model.Location"
            keyProperty="locationId"
            modelVar="location" escapedModel="<%= true %>"
        >
            <liferay-ui:search-container-column-text
                name="name"
                value="<%= location.getName() %>"
            />

            <liferay-ui:search-container-column-text
                name="description"
                property="description"
            />

            <c:choose>
                <c:when test="<%= showLocationAddress_view == true %>">
                    <liferay-ui:search-container-column-text
                        name="street-address"
                        property="streetAddress"
                    />

                    <liferay-ui:search-container-column-text
                        name="city"
                        property="city"
                    />

                    <liferay-ui:search-container-column-text
                        name="state-province"
                        property="stateOrProvince"
                    />

                    <liferay-ui:search-container-column-text
                        name="country"
                        property="country"
                    />
                </c:when>
            </c:choose>

            <liferay-ui:search-container-column-jsp
                align="right"
                path="/html/locationlisting/location_actions.jsp"
            />
        </liferay-ui:search-container-row>

        <liferay-ui:search-iterator />

    </liferay-ui:search-container>

Let's breakdown the above code. We start by getting the value of the
`showLocationAddress` portlet preference key and assigning it to a boolean
variable `showLocationAddress_view`: 

    <%  
    boolean showLocationAddress_view = GetterUtil.getBoolean(portletPreferences.getValue("showLocationAddress", StringPool.TRUE));
    %>

If no `showLocationAddress` key is found, the value will default to `true` based
on the `StringPool.TRUE` default value we pass to the method
`portletPreferences.getValue(key, default)`. Then, we wrap the street address,
city, state, and country column text elements in a conditional code block using
`<c:choose ><c:when test="..."> ... <c:when></c:choose>` tags: 

    <liferay-ui:search-container-row ... />
        ...
        <c:choose>
            <c:when test="<%= showLocationAddress_view == true %>">
                <liferay-ui:search-container-column-text
                    name="street-address"
                    property="streetAddress"
                />

                <liferay-ui:search-container-column-text
                    name="city"
                    property="city"
                />

                <liferay-ui:search-container-column-text
                    name="state-province"
                    property="stateOrProvince"
                />

                <liferay-ui:search-container-column-text
                    name="country"
                    property="country"
                />
            </c:when>
        </c:choose>
        ...
    </liferay-ui:search-container-row>

If `showLocationAddress_view` is true, *all* of the location's fields are
displayed. If it is false, then the address fields are omitted. 

That's it! You've created a custom configuration page and added a portlet
preference to your portlet. Let's see the configuration page and portlet
preference in action! Navigate to your Location Listing Portlet's
*Configuration* page. You now have the *show-location-address* checkbox
available. 

![Figure 3.14: Your new portlet preference is available in the *Configuration* page.](../../images/show-location-address-pref.png)

Each time you click *Save* after modifying the *show-location-address* checkbox,
the `ConfigurationActionImpl.processAction(...)` method of the
`ConfigurationActionImpl` class prints out the value of the
`showLocationAddress` portlet preference key: 

    showLocationAddress=true in ConfigurationActionImpl.processAction().

By unchecking the checkbox, the location addresses are hidden from view in the
Location Listing Portlet.

![Figure 3.15: Liferay Portal makes it easy to customize a portlet UI. Privileged users can adjust the preference settings within the portlet's configuration page.](../../images/portlet-preferences-modifying-view.png)

Great job! Now you know how to use Liferay's portlet preferences in the portlets
you develop. 

Next, let's use the Plugins SDK to create a plugin that extends another plugin.

## Creating Plugins to extend Plugins [](id=lp-6-1-dgen02-creating-plugins-to-extend-plugins-0)

For Liferay plugins, you can create a new plugin that extends an existing one.
By extending a plugin, you can use all its features in your new plugin while
keeping your changes/extensions separate from the existing plugin's source code. 

To create a plugin which extends another, follow these steps: 

1.  Create a new empty plugin in the Plugins SDK. 

2.  Remove all the auto-generated files except `build.xml` and the docroot
    folder, which should be empty. 

3.  Copy the original WAR file of the plugin you'd like to extend (for example,
    `social-networking-portlet-6.1.10.1-ee-ga1.war`) to the root folder of your
    new plugin. 

4.  Add the following line to your `build.xml` inside of the `<project>` tag to
    reference the original WAR file you are going to extend:

        <property
            name="original.war.file"
            value="social-networking-portlet-6.1.10.1-ee-ga1.war"
        />

5.  Copy any files from the original plugin that you're overwriting to your
    new plugin (using the same folder structure) and run the Ant target `merge`.
    Please note that the `merge` target is called whenever the plugin is
    compiled. All you have to do is to check the Ant output:

        dsanz@host:~/sdk/portlets/my-social-networking-portlet$ ant war
        Buildfile: /home/dsanz/sdk/portlets/my-social-networking-portlet/build.xml
        
        compile:
        
        merge:
        [mkdir] Created dir: /home/dsanz/sdk/portlets/my-social-networking-portlet/tmp
        [mkdir] Created dir:
        /home/dsanz/sdk/portlets/my-social-networking-portlet/tmp/WEB-INF/classes 
        [mkdir] Created dir:
        /home/dsanz/sdk/portlets/my-social-networking-portlet/tmp/WEB-INF/lib 
        
        merge-unzip:
        [unzip] Expanding:
        /home/dsanz/sdk/portlets/my-social-networking-portlet/social-networking-portlet-
        6.1.10.1-ee-ga1.war into /home/dsanz/sdk/portlets/my-social-networking-portlet/t
        mp 
        [copy] Copying 2 files to
        /home/dsanz/sdk/portlets/my-social-networking-portlet/tmp 
        [mkdir] Created dir:
        /home/dsanz/sdk/portlets/my-social-networking-portlet/docroot/WEB-INF/classes 
        
        ...

6.  If the plugin that you're extending contains a service, you need to
    overwrite the `ClpSerializer.java` file. The Service Builder-generated
    `ClpSerializer.java` file contains a hard-coded project for
    `_servletContextName`. You need to change this to the name of your plugin.

This generates a plugin (you can find the WAR file in the `/dist` folder of your
plugins SDK) which combines the original one with your changes. 

In summary, if localization is important for your portlets, always consider
statements in a *localization plan*, since some portlets in your plugin and hard
customer requests can make a mess in your localization files and keys. If
possible, reuse Liferay core language keys since they're already translated for
you. 

If there's no key you can use, you can create your own, as described in this
chapter. Liferay gives you the tools to make localization possible, and uses a
web service to provide rudimentary translations. 

## Summary [](id=lp-6-1-dgen03-summary-0)

You've covered a lot of ground learning Liferay Portlet development. You created
a portlet project, studied its anatomy, and created the "My Greeting Portlet".
You understood the Action phase and Render phase, and have have passed
information between them in a portlet. You've enhanced a portlet with multiple
actions and have mapped a friendly URL to it. Lastly, you've found how easy it
is to start localizing your portlets. You're really on a roll! 

Now that you know how to create portlets, you'll need to consider a few things,
such as persisting your objects to a database, maintaining separatation between
your persistence layer, business logic, and presentation layer, and allowing for
flexible implementations. Lastly, you'll want the ability to publish your
portlet's operations as services. So how do you address all of this? Hibernate
probably comes to mind for persisting your data model, and Spring probably comes
to mind with regards to supporting implementation flexibility. Sounds
complicated, right? No need to worry! Liferay's Service Builder helps you build
portlet services while hiding the complexities of using Spring and Hibernate
under the hood. We'll cover Service Builder next. 
