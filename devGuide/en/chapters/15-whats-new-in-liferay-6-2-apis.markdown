# What's New in Liferay 6.2 APIs? [](id=whats-new-in-liferay-6-2-apis-liferay-portal-6-2-dev-guide-en)

<!-- NOTE TO CONTRIBUTERS

Add content describing your API changes. If a Dev Guide chapter/section related
to your feature already exists, integrate your content with it and provide a
reference here. Otherwise, add a new section here in this APIs chapter with
content describing the your feature's new or modified API. 

For example,

    ## FeatureXYZ

    Describe your new/modified API here

Briefly describe your feature, even if it's a feature from the
previous release. Explain what the new or modified API does and how to use
it, by way of code examples. 

If your feature is already described in another chapter of the Dev Guide,
consider describing the API change there instead of here in this chapter. If you
do write about it in another chapter, still mention the API change here in this
chapter and refer to that other chapter/section, to help readers locate
your API change.

For example,

    ## Message Bus FeatureXYZ

    You can now to x, y, and z in the Message Bus API. See the Using Message Bus
    section of [Link to some section] for details. 

-->

Liferay Portal 6.2 offers a host of new features and updates to the previous
release. Our guide to *Using Liferay Portal 6.2* shows you how to use these
features and updates and this guide shows you how to leverage them in the
applications you develop. In this chapter, we want to highlight some of the
changes to Liferay Portal's application programming interface (API). We've added
APIs for the new features and improved APIs for previously existing features. In
some cases we've modified portal's API and removed previously deprecated
interfaces. The Javadoc for Liferay Portal's entire API is availabe at
[http://docs.liferay.com/portal/6.2/](http://docs.liferay.com/portal/6.2/). But
we'll describe some of the most notable additions and changes here in this
chapter. 

To start things off, we'll take a look at the new things you can do with the
Application Display Templates API. 

## Application Display Templates [](id=application-display-templates-liferay-portal-6-2-dev-guide-14-en)

A portlet's Display Settings (*Options* &rarr; *Configuration* &rarr; *Setup*
&rarr; *Display Settings*) let you customize its display. They come built in
with Liferay, so you don't have to do anything special to enable them for your
custom portlets. But what if you need settings in addition to Liferay's default
display settings? You could develop a theme or hook with the display options you
need, but it'd be nice if you could apply particular display options to specific
portlet instances without having to redeploy any plugins. Ideally, you should be
able to provide authorized portal users the ability to apply custom display
settings to portlets. This saves you from having to change portlet configuration
code every time you need new settings. 

Be of good cheer! That's precisely what Application Display Templates (ADTs)
provide-- the ability to add custom display settings to your portlets from the
portal. This isn't actually a new concept in Liferay. In some portlets (e.g.,
*Web Content*, *Documents and Media*, and *Dynamic Data Lists*), you can already
add as many display options (or templates) as you want. Now you can add them to
your custom portlets, too.

You can use the Application Display Templates API to add this new feature to
your plugins. Let's get started learning how.

### Using the Application Display Templates API [](id=application-display-templates-api-liferay-portal-6-2-dev-guide-14-en)

To leverage the ADT API, there are several steps you need to follow. These
steps involve registering your portlet to use ADTs, defining permissions, and
exposing the ADT functionality to users. We'll demonstrate these steps by
enabling Application Display Templates for our Location Listing Portlet. Be
aware that your specific implementation will look slightly different.

1.  Create and register your custom `PortletDisplayTemplateHandler` class.

    To join the exclusive ADT club, your portlet must sign a contract,
    committing itself to fulfill all the necessary Application Display Template
    requirements. In other words, you have to create your own
    `PortletDisplayTemplateHandler` implementation by extending the
    `BasePortletDisplayTemplateHandler` methods. You can check the
    [TemplateHandler](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/kernel/template/TemplateHandler.html)
    interface Javadoc to learn about each method. Here's what our template
    handler class looks like for the Location Listing portlet:

        package com.nosester.portlet.eventlisting.template;

        import java.util.List;
        import java.util.Locale;
        import java.util.Map;

        import com.liferay.portal.kernel.language.LanguageUtil;
        import com.liferay.portal.kernel.portletdisplaytemplate.BasePortletDisplayTemplateHandler;
        import com.liferay.portal.kernel.template.TemplateVariableGroup;
        import com.liferay.portal.kernel.util.StringPool;
        import com.liferay.portlet.portletdisplaytemplate.util.PortletDisplayTemplateConstants;
        import com.nosester.portlet.eventlisting.model.Location;
        import com.nosester.portlet.eventlisting.util.PortletKeys;

        public class LocationListingPortletDisplayTemplateHandler extends
                BasePortletDisplayTemplateHandler {

            public String getClassName() {
                return Location.class.getName();
            }

            public String getName(Locale locale) {
                String locations = LanguageUtil.get(locale, "locations");

                return locations.concat(StringPool.SPACE).concat(
                        LanguageUtil.get(locale, "template"));
            }

            public String getResourceName() {
                return PortletKeys.LOCATION_LISTING_PORTLET_ID;
            }

            @Override
            public Map<String, TemplateVariableGroup> getTemplateVariableGroups(
                    long classPK, String language, Locale locale)
                throws Exception {

                Map<String, TemplateVariableGroup> templateVariableGroups =
                    super.getTemplateVariableGroups(classPK, language, locale);

                TemplateVariableGroup templateVariableGroup =
                    templateVariableGroups.get("fields");

                templateVariableGroup.empty();

                templateVariableGroup.addCollectionVariable(
                    "locations", List.class, PortletDisplayTemplateConstants.ENTRIES,
                    "location", Location.class, "curLocation", "name");

                return templateVariableGroups;
            }
        }

    Each of the methods in this class have a significant role in defining and
    implementing ADTs for your custom portlet. View the list below for a
    detailed explanation for each method defined specifically for ADTs:
    - **getClassName():** Defines the type of entry your portlet is rendering.
    - **getName():** Declares the name of your ADT type (typically, the name of
    the portlet).
    - **getResourceName():** Specifies which resource is using the ADT (e.g., a
    portlet) for permission checking.
    - **getTemplateVariableGroups():** Defines the variables exposed in the
    template editor.

2. Now that we've created the template handler, declare it with the
   `<template-handler>...</template-handler>` tags in the Location Listing
   Portlet's `<portlet>` element of your `liferay-portlet.xml` file:

        <?xml version="1.0"?>
        <!DOCTYPE liferay-portlet-app PUBLIC "-//Liferay//DTD Portlet Application 6.2.0//EN" "http://www.liferay.com/dtd/liferay-portlet-app_6_2_0.dtd">
        
        <liferay-portlet-app>
            ...
            <portlet>
                <portlet-name>locationlisting</portlet-name>
                <icon>/icon.png</icon>
                <configuration-action-class>com.liferay.portal.kernel.portlet.DefaultConfigurationAction</configuration-action-class>
                <template-handler>com.nosester.portlet.eventlisting.template.LocationListingPortletDisplayTemplateHandler</template-handler>
                <instanceable>false</instanceable>
                ...
            </portlet>
            ...
        </liferay-portlet-app>

3. Since the ability to add ADTs is new to your portlet, we need to configure
   permissions so that administrative users can grant permissions to the roles
   that will be allowed to create and manage display templates. Just add the
   action key `ADD_PORTLET_DISPLAY_TEMPLATE` to your portlet's
   `docroot/WEB-INF/src/resource-actions/default.xml` file:

        <?xml version="1.0"?>
        <!DOCTYPE resource-action-mapping PUBLIC "-//Liferay//DTD Resource Action Mapping 6.2.0//EN" "http://www.liferay.com/dtd/liferay-resource-action-mapping_6_2_0.dtd">
        <resource-action-mapping>
            ...
            <portlet-resource>
                <portlet-name>locationlisting</portlet-name>
                <permissions>
                    <supports>
                        <action-key>ADD_PORTLET_DISPLAY_TEMPLATE</action-key>
                        <action-key>ADD_TO_PAGE</action-key>
                        <action-key>CONFIGURATION</action-key>
                        <action-key>VIEW</action-key>
                    </supports>
                ...
                </permissions>
            </portlet-resource>
        ...
        </resource-action-mapping>
	 
4. Now that your portlet officially supports ADTs, you'll want to expose the
   ADT option to your users. Just include the `liferay-ui:ddm-template-selector`
   taglib in the JSP file you're using to control your portlet's configuration
   mode (e.g., `config.jsp` if you choose to have it created through Liferay
   Developer Studio's New Portlet wizard), providing the required information.
   We'll add the display settings to the Location Listing Portlet's
   `configuration.jsp` file:
 
        <%@ include file="../init.jsp" %>
        ...
        <aui:form action="<%= configurationURL %>" method="post" name="fm">
            <aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

            <aui:fieldset>
                <div class="display-template">

                    <%
                    TemplateHandler templateHandler = TemplateHandlerRegistryUtil.getTemplateHandler(Location.class.getName());
                    %>

                    <liferay-ui:ddm-template-selector
                        classNameId="<%= PortalUtil.getClassNameId(templateHandler.getClassName()) %>"
                        displayStyle="<%= displayStyle %>"
                        displayStyleGroupId="<%= displayStyleGroupId %>"
                        refreshURL="<%= PortalUtil.getCurrentURL(request) %>"
                        showEmptyOption="<%= true %>"
                    />
                </div>
            </aui:fieldset>
        ...
        </aui:form>

    In this JSP, the `TemplateHandler` object is initialized. Then, we specify
    the `liferay-ui:ddm-template-selector` taglib, which implements the Display
    Template drop-down menu in the Location Listing Portlet's Configuration
    menu.

5. You're almost finished, but you still have to extend your view code to
   render your portlet with the selected ADT. Here is where you decide exactly
   which part of your view will be rendered by the ADT and what will be
   available in the template context. To do this, add the following code
   outlined below to your Location Listing Portlet's `view.jsp` file:
 
        <%@ include file="/html/init.jsp" %>

        This is the <b>Location Listing Portlet</b> in View mode.
        ...
        <%
        String displayStyle = GetterUtil.getString(portletPreferences.getValue("displayStyle", StringPool.BLANK));
        long displayStyleGroupId = GetterUtil.getLong(portletPreferences.getValue("displayStyleGroupId", null), scopeGroupId);

        long portletDisplayDDMTemplateId = PortletDisplayTemplateUtil.getPortletDisplayTemplateDDMTemplateId(displayStyleGroupId, displayStyle);

        boolean showLocationAddress_view = GetterUtil.getBoolean(portletPreferences.getValue("showLocationAddress", StringPool.TRUE));
        %>

        <c:choose>
            <c:when test="<%= portletDisplayDDMTemplateId > 0 %>">
                <% List<Location> locations = LocationLocalServiceUtil.getLocationsByGroupId(scopeGroupId); %>

                <%= PortletDisplayTemplateUtil.renderDDMTemplate(pageContext, portletDisplayDDMTemplateId, locations) %>
            </c:when>
            <c:otherwise>
                <liferay-ui:search-container emptyResultsMessage="location-empty-results-message">
                    <liferay-ui:search-container-results
                        results="<%= LocationLocalServiceUtil.getLocationsByGroupId(scopeGroupId, searchContainer.getStart(), searchContainer.getEnd()) %>"
                        total="<%= LocationLocalServiceUtil.getLocationsCountByGroupId(scopeGroupId) %>"
                    />
                ...
                </liferay-ui:search-container>
            </c:otherwise>
        </c:choose>

    In this code snippet, we initialized variables dealing with the display
    settings (`displayStyle`, `displayStyleGroupId`, and
    `portletDisplayDDMTemplateId`), and then used a do-otherwise statement to
    choose between rendering the ADT, or displaying what was originally in the
    `view.jsp`. If the `portletDisplayDDMTemplateId` exists, the locations list
    is initialized and the ADT is rendered using the page context, template ID,
    and locations.

Now that our portlet supports ADTs, you can create your own scripts to change
the display of your portlet. We'll experiment by adding our own custom ADT.

1. Navigate to *Admin* &rarr; *Configuration* &rarr; *Application Display
   Templates*. Then select *Add* &rarr; *Locations Template*. Give your ADT a
   name and insert the following FreeMarker code into the editor, and click
   *Save*:
   
        <#if entries?has_content>
            Quick List:
            <ul>
            <#list entries as curLocation>
                <li>${curLocation.name} - ${curLocation.streetAddress}, ${curLocation.city}, ${curLocation.stateOrProvince}</li>
            </#list>
            </ul>
        </#if>

2. Go back to your Location Listing Portlet and select *Options* &rarr;
   *Configuration* and click the *Display Template* drop-down. Select the ADT
   you created, and click *Save*.

![Figure 15.1: The Quick List template gives your locations in a bullet list format.](../../images/quick-list-template.png)
    
For the Location Listing Portlet, we've created a basic FreeMarker script that
takes our locations from the default table format and displays them and their
selected fields in a bullet list format.
    
Once your script is uploaded into the portal and saved, users with the specified
roles can select the template when they're configuring the display settings of
your portlet on a page. You can visit the [Using Application Display
Templates](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/using-application-display-templates-liferay-portal-6-2-user-guide-07-en)
section in *Using Liferay Portal* for more details on using ADTs.

Next, we'll provide some recommendations for using ADTs in Liferay Portal.

### Recommendations [](id=adt-recommendations-liferay-portal-6-2-dev-guide-14-en)

You've harnessed a lot of power by learning to leverage the ADT API. Be
careful, for with great power, comes great responsibility! To that end, let's
talk about some practices you can use to to optimize your portlet's performance
and security. 
 
First let's talk about security. You may want to hide some classes or packages
from the template context, to limit the operations that ADTs can perform on
your portal. Liferay provides some portal properties to define the restricted
classes, packages, and variables. You can override the following portal
properties via the `portal-ext.properties` file.

    freemarker.engine.restricted.classes 
    freemarker.engine.restricted.packages
    freemarker.engine.restricted.variables
    velocity.engine.restricted.classes 
    velocity.engine.restricted.packages
    velocity.engine.restricted.variables

In particular, you may want to add `serviceLocator` to the list of default
values assigned to the `freemarker.engine.restricted.variables` and
`velocity.engine.restricted.variables` portal properties. Make sure to only add
to the classes, packages, and variables restricted by default by
`portal.properties`. Descriptions of Liferay Portal's FreeMarker engine and
Velocity engine properties are available on
[docs.liferay.com](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html). 

Application Display Templates introduce additional processing tasks when your
portlet is rendered. To minimize negative effects on performance, make your
templates as minimal as possible by focusing on the presentation, while using
the existing API for complex operations. The best way to make Application
Display Templates efficient is to know your template context well, and
understand what you can use from it. Fortunately, you don't need to memorize
the context information, thanks to Liferay's advanced template editor!

The template editor provides fields, general variables, and util variables
customized for the portlet on which you decide to create an ADT. These variable
references can be found on the left-side panel of the template editor. You can
use them by simply placing your cursor where you'd like the variable placed, and
clicking the desired variable to place it there. You can learn more about the
template editor in the [Using Application Display
Templates](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/using-application-display-templates-liferay-portal-6-2-user-guide-07-en)
section of *Using Liferay Portal*.

Finally, don't forget to run performance tests and tune the template cache
options by overriding the following portal properties: 

    freemarker.engine.resource.modification.check.interval 
    velocity.engine.resource.modification.check.interval

The cool thing about ADTs is the power they provide to your Liferay portlets,
providing infinite ways of editing your portlet to provide new interfaces for
your portal users. We stepped through how to configure ADTs for a custom portlet
like the Location Listing Portlet, tried out a sample template, and ran through
important recommendations for using ADTs, which included security and
performance.

Next, we'll show you some of the changes to consider in using AlloyUI 2.0 and
Twitter&reg; Bootstrap. 

## AlloyUI 2.0 / Bootstrap Migration [](id=alloyui-2-0-bootstrap-migration-liferay-portal-6-2-dev-guide-02-en)

Liferay 6.2 uses Twitter&reg; Bootstrap-based theming for a slick, vibrant look
and feel with instant access to the Twitter&reg; Bootstrap (Bootstrap) theme
library. But there are a number of changes that needed to be made to AlloyUI in
order to accommodate and properly use Bootstrap. In this section, we'll explain
the reasoning behind the changes to AlloyUI and we'll explain how to migrate
plugins to use AlloyUI 2.0 and Bootstrap. 

Here is an outline of the types of changes you'll need to understand and handle
in migrating your plugins:

- Removal of the "aui-" prefixes from all classes
- Module deprecations
- CSS classes replaced with Bootstrap equivalents
- Component output and markup changes
- Icon removals, in favor of using Bootstrap icons

<!-- Add Bullet - Only Use AlloyUI Classes in CSS via Their Modules Jim -->

The good news is that Liferay provides a tool for making these changes. But
before we show you that tool, we'll explain the impact of each of these AlloyUI
changes with respect to Liferay 6.1 plugins. First, let's look at the removal of
the "aui-" class name prefix. 

### Removal of the "aui-" Prefix from All Classes [](id=remove-aui-prefix-from-classes-liferay-portal-6-2-dev-guide-en)

The "aui-" class prefix was hindering developers from copying and pasting
examples from Bootstrap's site into their Liferay plugin code. So we've removed
the prefix from all of AlloyUI's CSS and JavaScript classes. You'll need to
update any references to the classes that have been removed. For example, you
should remove the "aui-" prefix from the class reference
`.aui-ace-autocomplete`, converting the reference to `.ace-autocomplete`. There
are plenty more class references like this one that you'll need to update. 

There are a number of HTML tags that AlloyUI 1.5 styled by defining custom CSS
classes. For example, AlloyUI previously styled the HTML `<fieldset>` tag in a
class named `.aui-fieldset`. But since Bootstrap provides styling for these tags,
we now leverage the styling by wrapping the Bootstrap code (see
[aui.css](https://github.com/liferay/liferay-portal/blob/6.2.0-ga1/portal-web/docroot/html/themes/_styled/css/aui.css)).
For migrating such classes as `.aui-fieldset` to AlloyUI 2.0, simply remove
the "aui-" prefix but append the `.aui` parent class name.

For example, you'd replace this ...

    .aui-fieldset {
        // Styling
    }

... with this ...

    .aui .fieldset {
        // Styling
    }

You can check Bootstrap's
[\_forms.scss](https://github.com/liferay/alloy-bootstrap/blob/v2.3.2/lib/_forms.scss)
file for the HTML tags that Bootstrap styles. 

Next, let's consider the modules that have been deprecated in AlloyUI 2.0. 

### AlloyUI Module Deprecations [](id=alloyui-module-deprecations-liferay-portal-6-2-dev-guide-14-en)

Because extensive changes were needed for a number of AlloyUI modules, many of
the original modules were deprecated. In some cases the original modules were
deprecated with no replacement; in other cases we used the original name for the
new module implementation and have simply renamed the old module by adding a
"-deprecated" suffix to it. AlloyUI 2.0's module API is documented at
[http://alloyui.com/api/](http://alloyui.com/api/), but we've listed the
deprecated modules here: 

	aui-autocomplete-deprecated
	aui-autosize-deprecated
	aui-button-item-deprecated
	aui-chart-deprecated
	aui-color-picker-base-deprecated
	aui-color-picker-deprecated
	aui-color-picker-grid-plugin-deprecated
	aui-color-util-deprecated
	aui-data-set-deprecated
	aui-datasource-control-base-deprecated
	aui-datasource-control-deprecated
	aui-datepicker-base-deprecated
	aui-datepicker-deprecated
	aui-datepicker-select-deprecated
	aui-delayed-task-deprecated
	aui-dialog-iframe-deprecated
	aui-editable-deprecated
	aui-form-base-deprecated
	aui-form-combobox-deprecated
	aui-form-deprecated
	aui-form-field-deprecated
	aui-form-select-deprecated
	aui-form-textarea-deprecated
	aui-form-textfield-deprecated
	aui-input-text-control-deprecated
	aui-io-deprecated
	aui-io-plugin-deprecated
	aui-io-request-deprecated
	aui-live-search-deprecated
	aui-loading-mask-deprecated
	aui-overlay-base-deprecated
	aui-overlay-context-deprecated
	aui-overlay-context-panel-deprecated
	aui-overlay-deprecated
	aui-overlay-manager-deprecated
	aui-overlay-mask-deprecated
	aui-panel-deprecated
	aui-resize-base-deprecated
	aui-resize-constrain-deprecated
	aui-resize-deprecated
	aui-scroller-deprecated
	aui-simple-anim-deprecated
	aui-skin-deprecated
	aui-state-interaction-deprecated
	aui-swf-deprecated
	aui-template-deprecated
	aui-textboxlist-deprecated
	aui-tooltip-deprecated
	aui-tpl-snippets-base-deprecated
	aui-tpl-snippets-checkbox-deprecated
	aui-tpl-snippets-deprecated
	aui-tpl-snippets-input-deprecated
	aui-tpl-snippets-select-deprecated
	aui-tpl-snippets-textarea-deprecate

Note, some of these modules have new implementations with the same name,
excluding the suffix "-deprecated". Liferay's AlloyUI Upgrade Tool tacks the
"-deprecated" suffix onto module references it finds. It's up to you to migrate
to the new AlloyUI 2.0 modules. 

Next, let's consider the CSS classes that have been replaced by Bootstrap
equivalent components. 

<!-- Section: Only Use AlloyUI Classes in CSS via Their Modules

In CSS, a plugin should only use AlloyUI classes via their modules. This ensures
that the AlloyUI classes are available on the site pages. 

We need to explain this change. Maybe use a change to
portal-web/docroot/html/css/portal/preview.css as was done for
https://issues.liferay.com/browse/LPS-34974 ? Jim -->


### CSS Classes Replaced with Bootstrap Equivalents [](id=css-classes-replaced-with-bootstrap-classes-liferay-portal-6-2-dev-guide-en)

Many of the CSS classes used in AlloyUI 1.5 were replaced with Bootstrap classes
or were removed because they didn't blend well with Bootstrap. You may find this
with CSS classes in your plugins. Consider replacing your classes with
Bootstrap's CSS classes. See
[http://liferay.github.io/alloy-bootstrap/base-css.html](http://liferay.github.io/alloy-bootstrap/base-css.html) for more information on these CSS classes. 

Next, let's consider the component output and markup changes in AlloyUI 2.0. 

### Component Output and Markup Changes [](id=alloyui-output-and-markup-changes-liferay-portal-6-2-dev-guide-14-en)

AlloyUI 2.0 introduces appealing new changes in its output and some practical
changes to its markup. These changes help facilitate building UIs with a
consistent look and feel, and they help improve UI performance. You can try many
of these component changes via the pages of examples and tutorials found on
[http://alloyui.com/](http://alloyui.com/). You'll have to take a look at the
AlloyUI 2.0 [API documentation](http://alloyui.com/api/) to understand a number
of the markup changes--but here are some common changes: 

-   Buttons work a little differently in AlloyUI 2.0. By default, they now
    submit the form. If you don't want that default behavior, you should prevent 
    it by using a DOM event call like this: `event.domEvent.preventDefault();`.
-   In `A.Modal`, you now use `close-panel` instead of the old `aui-btn-cancel`
    tablib. 
-   To delegate selectors for buttons, simply use `.selector-button` instead of
    `.selector-button input`.

There are plenty more changes, but at least these are a few to get you started.
And remember that the Liferay AlloyUI Upgrade Tool--that we'll introduce
shortly--will help you out as well. Next, let's consider the changes in the
icons available. 

### Icon Removals, in Favor of Using Bootstrap Icons [](id=icons-removed-replaced-with-bootstrap-liferay-portal-6-2-dev-guide-en)

We replaced many icons with those provided by Bootstrap. We also added icons
from the Font Awesome project. These icons look great and provide a consistent
look and feel throughout Liferay and our plugins. You'll need to update your
plugin's references for icons that have been removed. In cases where you use the
`liferay:icon` taglib, you simply need to change the value of its `image`
attribute to that of a different icon. Consider using the new icons available in
Bootstrap, such as their [icons from
Glyphicons](http://liferay.github.io/alloy-bootstrap/base-css.html#icons). 

We've given you the "dime" tour of the types of changes you'll need to
accommodate in the plugins you're migrating from Liferay 6.1 to 6.2, but to
really jump-start your migration process, we'll show you Liferay's AlloyUI
Upgrade Tool. So, put on your work gloves and get ready to power through
migrating your plugins! 

### Upgrading Plugins with the Liferay AlloyUI Upgrade Tool [](id=upgrade-to-alloyui-2-0-with-tool-liferay-portal-6-2-dev-guide-en)

To access the liferay-aui-upgrade-tool project and install it locally, you'll
need an account on GitHub and the Git tool on your machine. Visit
[https://github.com/](https://github.com/) for instructions on setting up the
account and see [http://git-scm.com/](http://git-scm.com/) for instructions on
installing Git. 

Here are some simple steps for forking the liferay-aui-upgrade-tool project on
GitHub and installing the project locally: 

1.  Go to the AlloyUI project repository at
    [https://github.com/liferay/liferay-aui-upgrade-tool](https://github.com/liferay/liferay-aui-upgrade-tool).

2.  Click *Fork* to copy Liferay's liferay-aui-upgrade-tool repository to your
    account on GitHub. 

3.  In your terminal or in GitBash, navigate to the location where you want to
    put the liferay-aui-upgrade-tool project. Then download a clone of the
    repository by executing the following  command, replacing `[username]` with
    your GitHub user name: 

        git clone git@github.com:[username]/liferay-aui-upgrade-tool

5.  Navigate into your new liferay-aui-upgrade-tool repository directory and
    associate a remote branch to Liferay's liferay-aui-upgrade-tool repository
    so you'll be able to fetch its latest changes form time to time:  

        cd liferay-aui-upgrade-tool
        git remote add upstream git@github.com:liferay/liferay-aui-upgrade-tool

6.  Lastly, create your own branch named `2.0.x` based on Liferay's `2.0.x`
    branch, by execute the following command:

        git checkout -b 2.0.x upstream/2.0.x

You now have all of the liferay-aui-upgrade-tool project's source code. The
project's tool you use to upgrade plugins to AlloyUI 2.0 is called *laut*, which
stands for Liferay AUI Upgrade Tool. You build the upgrade tool using
[Node.js](http://nodejs.org), which is a platform for building applications. You
can download it from [http://nodejs.org/](http://nodejs.org/). Linux, OS X, or
UNIX users can download its source in a `.tar.gz` file, unzip it, un-tar it, and
build it per the instructions in its `README.md` file. Windows users can
download the `.msi` installer file and run it. 

---

 ![Warning](../../images/tip.png) **Warning:** On Windows, only install to
 locations that have UNIX-friendly paths. Paths like `C:\Program Files (x86)`
 that contain space characters and parentheses can prevent software from working
 properly. 

---

To build the upgrade tool with NodeJS, execute the following command (exclude
`[sudo]` on Windows): 

    [sudo] npm install -g laut

To get the usage summary of the upgrade tool, run it with the `--help` option. 

    laut --help

By default, the upgrade tool expects to convert files with extension `js`,
`jsp`, `jspf`, and `css`. You can specify a list of file extensions as arguments
to the tool's `-e` option. With the `-f` option you can specify individual files
or directories to search through and convert. It's common for users to simply
specify a single directory for the tool to search and convert all of the files
with the default extensions. 

    laut -f some-directory

### Example: Upgrading the Microblogs Portlet to AlloyUI 2.0 [](id=example-upgrade-portlet-alloy-2-0-liferay-portal-6-2-dev-guide-en)

Let's use the upgrade tool to upgrade Liferay's CE 6.1 Microblogs portlet from
using AlloyUI version 1.5 to using AlloyUI 2.0. Here's how to run it on the
Microblogs portlet in its liferay-plugins repository:

    laut -f /home/joe.bloggs/liferay-plugins/portlets/microblogs-portlet

Let's take a look at the changes the upgrade tool made to the portlet's JSPs. 

In the `view.jsp`, the upgrade tool renamed the `aui-io` module to
`aui-io-deprecated`. The tool replaces module references, even if a 2.0 module
exists with the same name, for a couple of different reasons. First, assuming
that you look at a diff of the modifications the tool makes, you'll notice that
the module has been deprecated. Knowing that, you can investigate whether there
is a new AlloyUI 2.0 module that you should start using instead. You should
investigate the API for the 2.0 module to find out how it works and to determine
how you might use it. Second, by using the deprecated module, you're assured
that your code will not run into interpretation errors; it may even exhibit the
same behavior as before. You must investigate if the deprecated module's
behavior has changed. It's up to you as to when and how to start using a 2.0
module. 

In 2.0 many of the "aui-" prefixes were dropped and in some cases modules where
completely renamed. For example, the upgrade tool modified the Microblogs
portlet's `edit_microblogs_entry.jsp` replacing module reference
`aui-helper-hidden` with its new 2.0 module named `hide`.  

Let's take a look at a different type of change done in the Microblogs portlet's
`edit_microblogs_entry.jsp`. Notice that the "aui-" prefix is deleted from all
AlloyUI class names, replacing `aui-button-holder`, `aui-button-disabled`, and
`aui-button-submit` class references with `button-holder`, `button-disabled` and
`button-submit`, respectively. Keep this type of change throughout your
portlet's files. 

---

 ![Warning](../../images/tip.png) **Warning:** Make sure to add the `.aui`
 parent class reference in front of a classname if you're extending the
 styling of a class that Bootstrap already styles. See the previous section on
 *Removal of the aui- Prefix from All Classes* for details. 

---

Lastly, consider the changes done to the Microblogs portlet's `main.js` file.
The script now uses the `liferay-util-window` module in place of the old
`aui-dialog` module. The upgrade tool took things a step further changing so
that the script properly uses the `liferay-util-window` module to get pop-up
windows for the portlet. Below are code snippets of what it like before and
after running the upgrade tool.

The Microblogs portlet's `main.js` code **before** upgrading:

    AUI().use(
        'aui-base',
        'aui-dialog',
        'aui-io-plugin',
        ...
			getPopup: function() {
				var instance = this;
		
				if (!instance._popup) {
					instance._popup = new A.Dialog(
						{
							centered: true,
							constrain2view: true,
							cssClass: 'microblogs-portlet',
							modal: true,
							resizable: false,
							width: 475
						}
					).plug(
						A.Plugin.IO,
						{
							autoLoad: false
						}
					).render();
				}
		
				return instance._popup;
            },
        ...
    );

The Microblogs portlet's `main.js` code **after** upgrading:

    AUI().use(
        'aui-base',
        'liferay-util-window',
        'aui-io-plugin-deprecated',
        ...
            getPopup: function() {
                var instance = this;
            
                if (!instance._popup) {
                    instance._popup = Liferay.Util.Window.getWindow(
                    {
                        dialog: {
                            centered: true,
                            constrain2view: true,
                            cssClass: 'microblogs-portlet',
                            modal: true,
                            resizable: false,
                            width: 475
                        }
                    }
                    ).plug(
                        A.Plugin.IO,
                        {
                            autoLoad: false
                        }
                    ).render();
                }
            
                return instance._popup;
            },
        ...
    );

The Liferay AlloyUI Upgrade Tool gives you a great jump-start on migrating your
plugins to AlloyUI 2.0. Of course, you should review the upgrade changes and
test the changes before redeploying your plugin into a production environment.
And remember that the tool may not pick up all of the changes that need to be
made to your plugin. But you'll be happy that the tool does a good bit of the
monotonous conversion work for you. 

No matter which Liferay APIs you're using, you'll need to understand Liferay's
deprecation policy. That way you'll know when methods from our API's are
deprecated, and you can make any necessary changes. We'll describe the
deprecation policy next. 

## Liferay's Deprecation Policy [](id=liferays-deprecation-policy-liferay-portal-6-2-dev-guide-02-en)

Methods in Liferay's APIs are deprecated when they're no longer called by
Liferay internally. Method deprecation occurs during major and minor releases of
Liferay. A change in the first or second digits of consecutive Liferay releases
indicates a major or minor release, respectively. For example, the release of
Liferay Portal 6.2.0 after 5.2.0 was a major release; whereas the release of
6.2.0 after 6.1.30 was a minor release. Major and minor releases can have API
deprecations. 

APIs should not be deprecated between maintenance releases. Maintenance releases
are signified by a change in the third digit of the release number. For example,
the release of Liferay Portal 6.1.30 after 6.1.20 was a maintenance release and
therefore should have no API deprecations. 

To understand Liferays releases, see [Using Liferay Portal
6.2](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/understanding-liferays-releases-liferay-portal-6-2-user-guide-15-en)

## Summary [](id=summary-liferay-portal-6-2-dev-guide-14-en)

That about wraps up our chapter on Liferay's APIs. Next, we'll reflect on what
we've learned in this guide and conclude our journey together. 
