# Creating Plugins [](id=creating-plugins)

Liferay projects can contain multiple plugins. If you've followed the
instructions from the earlier section on creating new Liferay projects, you
should already have created the event-listing-portlet project. In this section
we'll add two portlets to the event-listing-portlet project: the Location
Listing portlet and the Event Listing portlet. This illustrates the general
process for creating plugins inside of an existing Liferay project. Later in
this guide, when we complete developing the Event Listing and Location Listing
portlets, they'll allow users to add, edit, or remove events or locations,
display lists of events or locations, search for particular events or locations,
and view the details of individual events or locations. For now, we'll show you
how to create both portlets in the event-listing-portlet project. But before we
do, let's clean out the bare-bones default portlet from our
event-listing-portlet project. 

The portlet project wizard conveniently creates a default portlet named
*event-listing-portlet* after the project's name. The portlets we'll create in
this section will be named *locationlisting* and *eventlisting*. To avoid any
confusion between the project's default portlet named *event-listing-portlet*
and the portlet we'll be creating named *eventlisting*, let's remove the default
portlet by removing its descriptors and it's JSP. 
    
1.  Open the portlet's `docroot/WEB-INF/liferay-display.xml` file
    and remove the `<portlet id="event-listing" />` tag.
    
2.  Open the `docroot/WEB-INF/liferay-portlet.xml` file and remove the
    `<portlet>...</portlet>` tags and code residing between those tags.
    
3.  Navigate to the `docroot/WEB-INF/portlet.xml` file and remove the
    `<portlet>...</portlet>` tags and code residing between those tags.	

4.  Remove the `docroot/view.jsp` file. 

Super! You've cleaned out the default portlet from the project. Now you're ready
to start creating the example plugins. 

Your Liferay IDE's Package Explorer shows your Event Listing plugin project.
Since it's a portlet type project it has a skeleton in place for supporting more
portlet plugins. Let's start by creating the Location Listing portlet. 

Use the following steps to create the Location Listing portlet:

1.  Right click on your `event-listing-portlet` project in Liferay IDE's 
    *Package Explorer* and select *New* &rarr; *Liferay Portlet*. 

2.  The *New Liferay Portlet* dialog box appears with your plugin project 
    *event-listing-portlet* selected as the *Portlet plugin project* by default.
    It's a good idea to name your *Portlet class* after the name of your
    portlet. We'll name the class *LocationListingPortlet* in this example. Name
    your *Java package* after the plugin's parent project, so it will be
    *com.nosester.portlet.eventlisting*, and leave the *Superclass* as
    *com.liferay.util.bridges.mvc.MVCPortlet*. Alternatively, you could have
    selected *com.liferay.portal.kernel.portlet.LiferayPortlet* or
    *javax.portlet.GenericPortlet* for your superclass. 

    ![Figure 10.7: Liferay IDE's portlet creation wizard makes creating a portlet class is easy.](../../images/create-a-portlet-class.png)

    Here are the portlet class values to specify for the example Location
    Listing portlet: 

    - **Portlet plugin project:** *event-listing-portlet*
    - **Source folder:** */event-listing-portlet/docroot/WEB-INF/src*
    - **Portlet class:** *LocationListingPortlet*
    - **Java package:** *com.nosester.portlet.eventlisting*
    - **Superclass:** *com.liferay.util.bridges.mvc.MVCPortlet*

    Click *Next*.

3.  In the next window of the *New Liferay Portlet* wizard, you'll specify
    deployment descriptor details for your portlet. First enter the *Name* of
    your portlet--in our example, this will be *locationlisting*. Next, enter
    the portlet's *Display name* and *Title*; we'll specify both as *Location
    Listing Portlet*. In this window, you can also specify which portlet modes
    you'd like your portlet to have. *View* mode is automatically selected.
    There are also options for creating resources: you can specify the folder
    where JSP files will be created as well as whether or not a resource bundle
    file will be created. We'll leave the *Create JSP files* box flagged,
    specify *html/locationlisting* as the JSP folder, and flag the *Create
    resource bundle file* box.

    Here are the portlet deployment descriptor details to specify for the
    Location Listing portlet: 

    - **Name:** *locationlisting*
    - **Display name:** *Location Listing Portlet*
    - **Title:** *Location Listing Portlet*
    - **JSP folder:** *html/locationlisting*

    Click *Next*. 

    ![Figure 10.8: Liferay IDE's portlet creation wizard let's you specify the deployment descriptors for your portlets.](../../images/ide-specify-portlet-deployment-descriptors.png)

4.  This window lets you specify portlet deployment descriptor details that are
    specific to Liferay. You can set the file paths of your portlet's custom
    icon, main CSS file, and main JavaScript file. You can also specify a CSS
    class wrapper. Next, you can also choose the category for your portlet (it's
    categorized under *Sample* by default), and choose whether or not to add it
    to the *Control Panel* of your Liferay Portal. Accept the default, leaving
    the *Add to Control Panel* box unflagged. 

5.  The last step is to specify modifiers, interfaces, and method stubs to
    generate in the Portlet class. Accept the defaults and click *Finish*. 

Use the following steps to create the Event Listing portlet:

1.  Right-click your event-listing-portlet project &rarr; *New* &rarr; *Liferay
    Portlet*. Specify *EventListingPortlet* as the name of the portlet class,
    enter *com.nosester.portlet.eventlisting* as its Java package, and select
    *com.liferay.util.bridges.mvc.MVCPortlet* as it's superclass. 

    ![Figure 10.9: Creating portlet classes is simple with Liferay IDE's portlet creation wizard.](../../images/create-a-portlet-class-eventlistingportlet.png)

    Here are the portlet class values to specify for the example Event
    Listing portlet: 

    - **Portlet plugin project:** *event-listing-portlet*
    - **Source folder:** */event-listing-portlet/docroot/WEB-INF/src*
    - **Portlet class:** *EventListingPortlet*
    - **Java package:** *com.nosester.portlet.eventlisting*
    - **Superclass:** *com.liferay.util.bridges.mvc.MVCPortlet*

    Click *Next*. 

2.  In this window we'll specify the portlet's deployment descriptor details. 

    Here are the portlet deployment descriptor details to specify for the
    Event Listing portlet: 

    - **Name:** *eventlisting*
    - **Display name:** *Event Listing Portlet*
    - **Title:** *Event Listing Portlet*
    - **JSP folder:** *html/eventlisting*

    Click *Next*. 

3.  This window lets you specify portlet deployment descriptor details that are
    specific to Liferay. You can set the file paths of your portlet's custom
    icon, main CSS file, and main JavaScript file. You can also specify a CSS
    class wrapper. In the *Liferay Display* section, you can choose the category
    for your portlet (it's categorized under *Sample* by default), and choose
    whether or not to add it to the *Control Panel* of your Liferay Portal.
    Accept the default, leaving the *Add to Control Panel* box unflagged and
    click *Next*. 

4.  The last step in creating your portlet with the wizard is to specify
    modifiers, interfaces, and method stubs to generate in the Portlet class.
    Accept the defaults and click *Finish*. 

By default, new portlets use the MVCPortlet framework, a light framework that
hides part of the complexity of portlets and makes the most common operations
easier. The default MVCPortlet project uses separate JSPs for each portlet
mode: each of the registered portlet modes has a corresponding JSP with the
same name as the mode. For example, `edit.jsp` is for edit mode and `help.jsp`
is for help mode. 

Let's redeploy the plugin project to make our portlet plugins available in the
portal. In the *Servers* tab, simply right click the *event-listing-portlet*
project, then click *Redeploy*.

Now you've created and deployed the *Location Listing* portlet and the
*Event Listing* portlet from the same project. Eventually, when the Location
Listing portlet is complete it will allow users to enter viable event locations.

If you've been following along to create the Event Listing portlet project,
you're done! Next, let's find out how to import existing projects into Liferay
IDE. 
