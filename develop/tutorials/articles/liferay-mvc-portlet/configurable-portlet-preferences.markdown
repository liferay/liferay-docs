# Using Configurable Portlet Preferences [](id=using-configurable-portlet-preferences)

<!-- The beginning and ending test portlets can be found at the following:
https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/liferay-mvc/prefs/begin/event-listing-portlet
https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/liferay-mvc/prefs/end/event-listing-portlet
-->

Portlet Preferences are properties for storing basic portlet configuration data.
Administrators often use preferences to provide a customized view of a portlet
to subsets of users or even all of a portlet's users. Portlet preference
controls are sometimes made accessible to users themselves for configuring
portlets just the way they like them. Liferay simplifies making portlet
preferences configurable in portlet JSPs. In this tutorial, you'll learn how to
create a default configuration JSP page and how to add a portlet preference
control to it. Figure 1 shows what a configurable portlet preference looks like
in a portlet's Configuration menu. 

![Figure 1: Portlet preferences, such as this portlet's checkbox for showing location addresses, let you customize your portlet in many different ways.](../../images/show-location-address-pref.png)

First, you'll learn how to create the default Setup tab for your portlet's
configuration page. 

## Creating a Default Setup Tab in the Portlet's Configuration Page [](id=creating-a-default-setup-tab-in-the-port-liferay-portal-6-2-dev-guide-03-en)

Before you can begin adding configurable portlet preferences, you must first
create a place for them to reside in your portlet's Configuration menu. The
following steps explain how you can put preference controls in a Setup tab in
the Configuration menu for your portlet preference.

1. Open your portlet's `liferay-portlet.xml` file.

2. Add the element
   `<configuration-action-class>com.liferay.portal.kernel.portlet.DefaultConfigurationAction</configuration-action-class>`
   inside the `<portlet>` element. Here's an example snippet to show you where
   it goes in the context of your `liferay-portlet.xml` file:

        ...
        <portlet>
            <portlet-name>yourportlet</portlet-name>
            <icon>/icon.png</icon>
            <configuration-action-class>com.liferay.portal.kernel.portlet.DefaultConfigurationAction</configuration-action-class>
            <header-portlet-css>/css/main.css</header-portlet-css>
            ...
        </portlet>
        ...

Notice that you've specified the *default* configuration action class. You can
update this tag with a custom configuration class, as demonstrated later in this
tutorial. If you redeploy your portlet and open your portlet's *Configuration*
page, you'll find the new Setup tab. 

![Figure 2: Simply by specifying Liferay's default configuration action class in your portlet's `liferay-portlet.xml` file, you provide your portlet with a *Setup* tab for adding your portlet's configuration setup options.](../../images/portlet-default-configuration-jsp.png)

The Setup tab is empty for now, but you can add a portlet preference control to
it, next. 

## Implementing a Configurable Portlet Preference [](id=implementing-a-configurable-portlet-preference)

Now that you have your Setup tab in the Configuration menu, you can add a custom
option to it. 

In order to add a configurable portlet preference to the portlet, you must do
the following things: 

1. Specify a Configuration JSP in the `portlet.xml`
2. Create the Configuration JSP for Displaying the Portlet Preference Options
3. Create a Configuration Action Implementation Class for Processing the
Portlet Preference Value
4. Modify the View JSP to Respond to the Current Portlet Preference Value

You can specify a configuration JSP file, first. 

### Step 1: Specify a Configuration JSP in the portlet.xml [](id=step-1-specify-a-configuration-jsp-in-the-portlet-x)

Your portlet needs a way to display configuration options to the user. Liferay
checks to see if your portlet specifies a configuration JSP via a
`config-template` initialization parameter in your `portlet.xml` file. You can
specify one for your portlet. 

Open the `portlet.xml` file and declare your initialization parameters with the
`<init-param>...</init-param>` tags within your portlet's `<portlet>` element.
Here's an example code snippet that sets a portlet's configuration JSP path to
`/html/yourportlet/configuration.jsp`: 

    <portlet>
        <portlet-name>yourportlet</portlet-name>
        <display-name>Your Portlet</display-name>
        <portlet-class>
            com.liferay.samples.portlet.yourportlet.YourPortlet
        </portlet-class>
        <init-param>
            <name>config-template</name>
            <value>/html/yourportlet/configuration.jsp</value>
        </init-param>
        <expiration-cache>0</expiration-cache>
        ...
    </portlet>

Next, you can create the configuration JSP file that you just specified. 

### Step 2: Create the Configuration JSP for Displaying the Portlet Preference Options [](id=step-2-create-the-configuration-jsp-for-displaying-)

You must create a configuration JSP file and write JavaScript to let the user
control the portlet preference. 

1.  Create the JSP file in a location matching the path you specified for the
`config-template` parameter in your `portlet.xml` file. 

2.  Add an action URL for the configuration. For example, you can specify one
like this:

        <liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

3.  Retrieve the current value of your portlet preference and save it to a local
variable. For example, you could retrieve a portlet preference named
`somePreferenceKey` and save it to a local Java variable with the following
code:

        <%  
        boolean somePreferenceKey_cfg = GetterUtil.getBoolean(portletPreferences.getValue("somePreferenceKey", StringPool.TRUE));
        %>

    Liferay's static 
    [GetterUtil](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/kernel/util/GetterUtil.html)
    class provides helpful methods for processing values from portlet
    preferences. 

4.  Add a form to present the current setup and to enable the user to submit any
changes via the action URL that you specified in step 2. For example, you could
specify the form using an `<aui:form>` with a submit button. Here's a form into
which you could present the portlet preference for the user to control: 

        <aui:form action="<%= configurationURL %>" method="post" name="fm">
            <aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

            <!-- Preference control goes here -->

            <aui:button-row>
                <aui:button type="submit" />
            </aui:button-row>
        </aui:form>

    The sample code above passes the value of a generic variable named
    `Constants.CMD` to signify that this form's action is an update to the
    portlet. 

5.  You must add your portlet preference control within your configuration
page's form. You can use an `<aui:input>` tag to display the control and get the
user's input. In order for the value to be persisted, the `<aui:input>` tag's
`name` attribute value must follow the naming convention
`preferences--somePreferenceKey--`, where you'd replace `somePreferenceKey` with
the name of your portlet preference. 

    For example, here is an input checkbox that lets the user set the value of a
    portlet preference named `somePreferenceKey`: 

        <aui:input name="preferences--somePreferenceKey--" type="checkbox" value="<%= somePreferenceKey_cfg %>" />

+$$$

**Note:** Make sure to declare
directives for any taglibs your configuration JSP requires, or declare the
directives in one of the JSPs that the configuration JSP includes. 

You may also need to declare the `<portlet:defineObjects />` tag to access
implicit variables. This tag provides useful portlet variables such as
*renderRequest*, *portletConfig*, and *portletPreferences*. 

$$$

Your configuration JSP is all set to display your portlet preference options.
You're ready to implement a custom class to handle the configuration action. If
you don't need to use the portlet preference in your portlet's controller logic,
then you can skip the next step. 

### Step 3: Create a Configuration Action Implementation Class for Processing the Portlet Preference Value [](id=step-3-create-a-configuration-action-implementation)

You can create a custom configuration action class for accessing the portlet
preference and invoking any preference handling logic that you want. The class
must extend the
[`DefaultConfigurationAction`](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/kernel/portlet/DefaultConfigurationAction.html)
class.

You can optionally override `DefaultConfigurationAction`'s `processAction()`
method to add any processing logic for the portlet preference. For example, you
could override the method and access your preference with code similar to this: 

    @Override
    public void processAction(
        PortletConfig portletConfig, ActionRequest actionRequest,
        ActionResponse actionResponse) throws Exception {  

        super.processAction(portletConfig, actionRequest, actionResponse);

        PortletPreferences prefs = actionRequest.getPreferences();

        String somePreferenceKey = prefs.getValue(
            "somePreferenceKey", "true");

        // Add any preference processing here.
    }

The super-class's `processAction()` method is responsible for reading the
portlet preferences from the configuration form and storing them in the
database. Usually, you'd add appropriate validation logic for the parameters
received from the form. 

+$$$

**Note:** You won't need to store
portlet preferences by calling `preferences.store()` since they're automatically
stored in the `DefaultConfigurationAction` class, which your configuration class
extends. 

$$$

Another common method to override in a custom configuration action class is the
[`DefaultConfigurationAction`](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/kernel/portlet/DefaultConfigurationAction.html)'s
`render()` method. The render method is invoked when the user clicks the
configuration icon. 

No matter what methods you override, make sure to import any classes you need.
Here are some common classes you may need to import: 

    import javax.portlet.ActionRequest;
    import javax.portlet.ActionResponse;
    import javax.portlet.PortletConfig;
    import javax.portlet.PortletPreferences;

Lastly, you must specify your new custom configuration class in the
`liferay-portlet.xml` file. Replace the value of the existing
`<configuration-action-class>...</configuration-action-class>` with your custom
configuration class' fully qualified class name. Here's a snippet to show you
where it goes in the context of the `liferay-portlet.xml` file: 

    ...
    <portlet>
        <portlet-name>yourportlet</portlet-name>
        <icon>/icon.png</icon>
        <configuration-action-class>com.samples.portlet.yourportlet.action.ConfigurationActionImpl</configuration-action-class>
        <header-portlet-css>/css/main.css</header-portlet-css>
        ...
    </portlet>
    ...

Since your configuration action implementation is ready to process your portlet
preference, you can update your portlet's view JSP to respond to the portlet
preference. 

### Step 4: Modify the View JSP to Respond to the Current Portlet Preference Value [](id=step-4-modify-the-view-jsp-to-respond-to-the-curren)

In your view JSP, you can implement logic to display your portlet based on its
configured preferences. You can access portlet preferences via the implicit
variable `portletPreferences`. To access this variable, make sure to include the
`<portlet:defineObjects />` tag in your JSP. 

Here's an example of accessing a boolean value stored in a portlet preference
named `showLocationAddress`: 

    <%  
    boolean showLocationAddress_view = GetterUtil.getBoolean(portletPreferences.getValue("showLocationAddress", StringPool.TRUE));
    %>

You can view a sample
[`view.jsp`](https://github.com/liferay/liferay-docs/blob/master/develop/tutorials/code/plat-fws/prefs/end/event-listing-portlet/docroot/html/locationlisting/view.jsp)
file to see an example of handling portlet preference inside a view JSP. The JSP
retrieves the value of a portlet preference named `showLocationAddress` and if
the preference's value is true, *all* of the location's fields are displayed. If
it is false, then the address fields are omitted. 

![Figure 3: Liferay Portal makes it easy to customize a portlet UI. Privileged users can adjust the preference settings within the portlet's configuration page.](../../images/portlet-preferences-modifying-view.png)

That's it! You've created a custom configuration page and added a portlet
preference to your portlet. 

To see the configuration page and portlet preference in action! Navigate to your
portlet's *Configuration* page to see the portlet preference control that you
added. 

![Figure 4: Your new portlet preference is available in your portlet's *Configuration* page.](../../images/show-location-address-pref.png)

Great job! You added a new Setup tab to your portlet's configuration page and
you added a configurable portlet preference to that page. Now you know how to
use Liferay's portlet preferences in the portlets you develop. 

