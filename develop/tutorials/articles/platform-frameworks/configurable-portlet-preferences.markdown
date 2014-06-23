# Using Configurable Portlet Preferences [](id=using-configurable-portlet-preferences-lp-6-2-develop-tutorial)

<!-- The beginning and ending test portlets can be found at the following:
https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/plat-fws/prefs/begin/event-listing-portlet
https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/plat-fws/prefs/end/event-listing-portlet
-->

Portlet Preferences are properties for storing basic portlet configuration data.
Preferences are often used by administrators to provide customized views of a
portlet to subsets of users or even all of a portlet's users. Portlet
preferences are sometimes made accessible to users themselves for configuring a
portlet just the way they like it. Liferay simplifies making portlet preferences
configurable in your portlet's JSPs. In this tutorial, we'll show you how to
create a default configuration JSP page and add a portlet preferences control to
it. Figure 1 shows what a configurable portlet preference looks like in a
portlet's Configuration menu.

![Figure 1: Portlet preferences let you customize your portlet in many different ways.](../../images/show-location-address-pref.png)

First, we'll show you how to create the default Setup tab for your portlet's
configuration page.

## Creating a Default Setup Tab in the Portlet's Configuration Page [](id=creating-a-default-setup-tab-in-the-port-liferay-portal-6-2-dev-guide-03-en)

Before we can begin adding configurable portlet preferences, we must first
create a place for them to reside in your portlet's Configuration menu. In this
example, we'll create a Setup tab in the Configuration menu for your portlet
preference.

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

Notice that we've listed the *default* configuration action class. We'll update
this tag with a custom configuration class later in the exercise. If you
redeploy your portlet and open your portlet's *Configuration* page, you'll find
the new Setup tab. 

![Figure 2: Simply by specifying Liferay's default configuration action class in your portlet's `liferay-portlet.xml` file, you provide your portlet with a *Setup* tab for adding your portlet's configuration setup options.](../../images/portlet-default-configuration-jsp.png)

The Setup tab is empty for now, but we'll add a portlet preference control to it
next. 

## Implementing a Configurable Portlet Preference [](id=implementing-a-configurable-portlet-preference-lp-6-2-develop-tutorial)

Now that we have our Setup tab in the Configuration menu, we need to add a
custom option to it. The portlet preference we'll implement allows
administrators to hide the address portion of the locations in the Location
Listing portlet. 

In order to add a configurable portlet preference to the portlet, we must do the
following things: 

1. Specify a Configuration JSP in the `portlet.xml`
2. Create the Configuration JSP for Displaying the Portlet Preference Options
3. Create a Configuration Action Implementation Class for Processing the
Portlet Preference Value
4. Modify the View JSP to Respond to the Current Portlet Preference Value

Let's specify a configuration JSP file, first. 

### Step 1: Specify a Configuration JSP in the `portlet.xml` [](id=step-1-specify-a-configuration-jsp-in-the-portlet-x-lp-6-2-develop-tutorial)

Your portlet will need a way to display configuration options to the user.
Liferay checks to see if your portlet specifies a configuration JSP via a
`config-template` initialization parameter in your `portlet.xml` file. Let's
specify one for your portlet. 

Open the `portlet.xml` file and declare your initialization parameters with the
`<init-param>...</init-param>` tags within your portlet's `<portlet>` element.
Here's an example code snippet of what this declaration would look like:

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

Let's create the JSP file for your configuration page next.

### Step 2: Create the Configuration JSP for Displaying the Portlet Preference Options [](id=step-2-create-the-configuration-jsp-for-displaying--lp-6-2-develop-tutorial)

We'll create a configuration JSP file and add JavaScript to let the user select
a portlet preference value. For our example, we'll provide a custom option in
the configuration page's Setup tab, allowing administrators to show or hide
location address information. 

Create a `configuration.jsp` file in your `docroot` directory (or the directory
where your `view.jsp` file resides).

Now let's begin creating our portlet preference for the configuration page's
setup content. The following code is an example for a boolean portlet preference
showing a location address in the `configuration.jsp` file: 

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

---

 ![Note](../../images/tip-pen-paper.png) **Note:** Make sure to declare the
 required taglib directives in your `configuration.jsp`, or `init.jsp` if one
 exists in your portlet.

---

You may also need to insert the `<portlet:defineObjects />` tag to access
implicit variables. This tag provides useful portlet variables such as
*renderRequest*, *portletConfig*, and *portletPreferences*.

Your `configuration.jsp` is all set to display your portlet preference options.
Let's implement a custom class to handle the configuration action. 

### Step 3: Create a Configuration Action Implementation Class for Processing the Portlet Preference Value [](id=step-3-create-a-configuration-action-implementation-lp-6-2-develop-tutorial)

Now let's create a custom configuration action class for accessing the portlet
preference. We'll have it extend the
`DefaultConfigurationAction` class.
   
Create a package named `com.samples.portlet.yourportlet.action` in the
portlet's `docroot/WEB-INF/src` directory. In the new package, create a class
named `ConfigurationActionImpl`, and specify
[`DefaultConfigurationAction`](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/kernel/portlet/DefaultConfigurationAction.html)
as its superclass.

Add configuration action code that extends the DefaultConfigurationAction class.
The following example code is used to configure a Show Location Address
checkbox: 

    package com.samples.portlet.yourportlet.action;

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
`liferay-portlet.xml`. Replace the existing
`<configuration-action-class>...</configuration-action-class>` with
`<configuration-action-class>com.samples.portlet.yourportlet.action.ConfigurationActionImpl</configuration-action-class>`.
Here's a snippet to show you where it goes in the context of the
`liferay-portlet.xml` file: 

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
preference, let's update the view JSP to respond to the portlet preference. 

### Step 4: Modify the View JSP to Respond to the Current Portlet Preference Value [](id=step-4-modify-the-view-jsp-to-respond-to-the-curren-lp-6-2-develop-tutorial)

Let's add logic in our `view.jsp` to show/hide the location addresses based on
the value of our portlet preference key `showLocationAddress`. 

In the `view.jsp` file, we'll get the value of the `showLocationAddress` portlet
preference key. If its value is `true`, we'll display all of the location
fields, including the address fields; otherwise, we'll hide the address fields. 

You can view a sample
[`view.jsp`](https://github.com/liferay/liferay-docs/blob/master/develop/tutorials/code/plat-fws/prefs/end/event-listing-portlet/docroot/html/locationlisting/view.jsp)
file to see how to handle our portlet preference inside a view JSP. Let's
explore the sample `view.jsp` code that handles the portlet preference. 

We start by getting the value of the `showLocationAddress` portlet preference
key and assigning it to a boolean variable `showLocationAddress_view`: 

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
preference in action! Navigate to your portlet's *Configuration* page. You now
have the *show-location-address* checkbox available. 

![Figure 3: Your new portlet preference is available in the *Configuration* page.](../../images/show-location-address-pref.png)

Each time you click *Save* after modifying the *show-location-address* checkbox,
the `ConfigurationActionImpl.processAction(...)` method of the
`ConfigurationActionImpl` class prints out the value of the
`showLocationAddress` portlet preference key: 

    showLocationAddress=true in ConfigurationActionImpl.processAction().

By unchecking the checkbox, the location addresses are hidden from view in your
portlet.

![Figure 4: Liferay Portal makes it easy to customize a portlet UI. Privileged users can adjust the preference settings within the portlet's configuration page.](../../images/portlet-preferences-modifying-view.png)

Great job! You added a new Setup tab to your portlet's configuration page and
you added a configurable portlet preference to that page. Now you know how to
use Liferay's portlet preferences in the portlets you develop. 

