# Implementing Configurable Portlet Preferences [](id=implementing-configurable-portlet-preferences)

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
Layer](/develop/tutorials/-/knowledge_base/6-1/generating-your-service-layer)
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

## Creating a Default Setup Tab in the Portlet's Configuration Page [](id=creating-a-default-setup-tab-in-the-portlets-configuration-page)

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

## Step 1: Specify a Configuration JSP in the portlet.xml [](id=step-1-specify-a-configuration-jsp-in-the-portlet-xml)

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

## Step 2: Create the Configuration JSP for Displaying the Portlet Preference Options [](id=step-2-create-the-configuration-jsp-for-displaying-the-portlet-preference-o)

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

## Step 3: Create a Configuration Action Implementation Class for Processing the Portlet Preference Value [](id=step-3-create-a-configuration-action-implementation-class-for-processing-th)

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

+$$$

**Note:** You won't need to store portlet preferences by calling
`preferences.store()` since they're automatically stored in the
`DefaultConfigurationAction` class, which your configuration class extends.

$$$

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

## Step 4: Modify the View JSP to Respond to the Current Portlet Preference Value [](id=step-4-modify-the-view-jsp-to-respond-to-the-current-portlet-preference-val)

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
