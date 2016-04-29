Introduction^[[a]](#cmnt1)^^[[b]](#cmnt2)^^[[c]](#cmnt3)^^[[d]](#cmnt4)^^[[e]](#cmnt5)^^[[f]](#cmnt6)^^[[g]](#cmnt7)^^[[h]](#cmnt8)^^[[i]](#cmnt9)^ {#h.nuhvi6d0gjpl .c4 .c30}
===================================================================================================================================================

The default themes (admin and classic) of Liferay 7 include an
implementation of the Lexicon Experience Language, that means that you
will be able to use components from Lexicon and they will look very good
:)

General recommendations {#h.ee57zij0xy0t .c4 .c30}
=======================

1.  Always start with mockups
2.  Use available taglibs whenever possible
3.  When doing markup manually, use Lexicon markup not Bootstrap markup

1.  the Lexicon implementation in Liferay 7 is based on Bootstrap but it
    doesn’t include all of its elements and extends others. By using
    only the Lexicon markup you will ensure the best result in terms of
    UX and visual look

Strategy {#h.54jbnxaldwd3 .c4 .c30}
--------

When applying a new style of design it’s important to have consistency
across applications that the end user or administrator will use
together. For this reason we suggest a breadth first, depth second
approach. The idea is that we first apply the most general patterns of
lexicon-based designs to all portlets (or as many as possible) and we
later go back one by one to dig deeper and improve its UX iteratively
until it’s perfect. The first phase (called Breadth first) should
contain enough improvements to present a good UI and UX to the user,
consistent with other portlets.

Considering this, each team needs to decide what they want to include in
Phase 1 for each portlet based on what’s most important for that portlet
and the cost of changing the design. The final plan is documented here:

[https://docs.google.com/spreadsheets/d/1rvSp-ZMl8JryOh66WprPlV1BqemdFEPi1rvlcfJsjE8/edit\#gid=56848740](https://www.google.com/url?q=https://docs.google.com/spreadsheets/d/1rvSp-ZMl8JryOh66WprPlV1BqemdFEPi1rvlcfJsjE8/edit%23gid%3D56848740&sa=D&ust=1461617444698000&usg=AFQjCNGChPXyF5bM_nX10TyE8bE6V1wybw)

Implementing a Lexicon-based design leveraging Liferay’s taglibs {#h.p1o75hfpnazn .c4 .c30}
================================================================

These are general recommended steps, adapt them as you see fit for your
case. In most cases it’s not mandatory that you follow this exact order,
although it doesn’t hurt either and it will make it easier for you to
know how far along you are in the task ;)

Basic improvements {#h.ghoube9c728l .c4 .c30}
------------------

### 1) Move the title in the inner views and the associated back to the portlet titles {#h.heooyng7e8o .c4 .c30}

1.  Use renderResponse.setTitle() in the action commands to set the
    title
2.  Next, use portletDisplay.setShowBackIcon(true);
    portletDisplay.setURLBack(redirect); to set the back link

### 2) Apply the new add button pattern (if the portlet needs one) {#h.d0jk1kdumk5d .c4 .c30}

The new add button should be applied in the main screen of a portlet, or
in any inside screen where it makes sense. However it should never be
applied within pop-ups.

This pattern places an “Add” button in the bottom right corner of the
portlet instead of in the management bar where it used to be. The button
may have one action or display a menu like this:

![](images/image04.png)

This button can be easily added using the add-menu taglib. Here is an
example:

\<liferay-frontend:add-menu\>

  \<liferay-frontend:add-menu-item title='\<%= LanguageUtil.get(request,
"add-category") %\>' url="\<%= addCategoryURL.toString() %\>" /\>

\</liferay-frontend:add-menu\>

If there is only one item the plus icon will act as a button. If you add
more than one menu-item then the user will be presented with a menu to
choose from.

### 3) Apply the design to the navigation bar {#h.wgks0s8xvh9j .c4 .c30}

Lexicon requires all administration applications to have a navigation
bar.

If your portlet already has a navigation bar implemented with the
nav-bar taglib, you can reuse it by adding the attribute
markupView=”lexicon” as shown in the next example.

If the portlet didn’t have a nav-bar yet, you will need to add it with
one item (tab):

\<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon"\>

  \<aui:nav cssClass="navbar-nav"\>

        ...

     \<aui:nav-item

        href="\<%= viewArticlesHomeURL %\>"

        label="folders"

        selected="\<%= selected %\>"

     /\>

  \</aui:nav\>

\</aui:nav-bar\>

If you want the bar to have a search box, you can use the nav-bar-search
inner taglib as follows:

\<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon"\>

  ...

  \<aui:nav-bar-search\>

    \<aui:form action="\<%= portletURL() %\>" name="searchFm"\>

       \<liferay-ui:input-search autoFocus="\<%=
windowState.equals(WindowState.MAXIMIZED) %\>" \
                 markupView="lexicon" /\>

    \</aui:form\>

  \</aui:nav-bar-search\>

\</aui:nav-bar\>

### 4) Introduce the management bar {#h.zf7z0l962rt2 .c4 .c30}

Use the management-bar taglib and its inner taglibs:

![](images/image05.png)

management-bar-buttons

![](images/image00.png)

management-bar-display-buttons

![](images/image06.png)

management-bar-action-buttons

![](images/image03.png)

management-bar-filters

![](images/image01.png)

management-bar-sort

![](images/image02.png)

Keep in mind that when there are no elements displayed all the elements
from the management bar should be disabled except the information
button. See an example about how to achieve this:

-   For the checkbox to select all entries see the attribute
    ‘checkBoxDisabled’:
-   You can disable the management bar with attribute ‘disabled’:

\<liferay-frontend:management-bar

  disabled="\<%= total == 0 %\>"

  includeCheckBox="\<%= true %\>"

  searchContainerId="\<%= searchContainerId %\>"

\>

-   Also you can disable individual buttons:

\<liferay-frontend:management-bar-display-buttons

  disabled="\<%= total == 0 %\>"

  displayViews='\<%= new String[] {"descriptive", "list"} %\>'

  portletURL="\<%= displayStyleURL %\>"

  selectedDisplayStyle="\<%= displayStyle %\>"

/\>

-   If you want to have actions buttons and you want to see how many
    items do you have selected, you should add the searchContainerId it
    should be the same that you have in your search container taglib

### 5) Ensure the application body is fluid {#h.r9f3gilxbhxi .c4 .c30}

A fluid application takes all the available screen real state, from left
to right. To achieve this add “container-fluid-1280” class in a DIV (or
equivalent) that contain all the content of the portlet (excluding the
nav bar and management bar)

If you already have a DIV element that contains the complete view (or
views) of your application add this class to it. Otherwise add an
uppermost div element for this purpose:

\<div class="container-fluid-1280"\>

...

\</div\>

Ensure you do this for all views of the portlet for all content under
the nav and management bars.

### 6) Apply the design to the search iterator {#h.g1r5eq4mm9s0 .c4 .c30}

Specify markupView=”lexicon” to the search-iterator taglib invocation

\<liferay-ui:search-iterator

       displayStyle="\<%= displayStyle %\>" \
       markupView="lexicon" \
       searchContainer="\<%= searchContainer %\>" /\>

If the results contain different set of entries (folders and documents,
categories and threds, etc.) you will need to use a ResultRowSplitter to
divide the results. The result row splitter is an attribute that is
provided to the liferay-ui:search-iterator taglib as follows:

\<liferay-ui:search-iterator

       displayStyle="\<%= displayStyle %\>" \
       markupView="lexicon"

       markupView="\<%= new DLResultRowSplitter() %\>" \
       searchContainer="\<%= searchContainer %\>" /\>

You will need to create a java class that implements ResultRowSplitter
interface. That class is responsible of dividing and categorizing the
results based on the different entry types.

The action menu style should be changed to use the vertical ellipsis
icon (icon-ellipsis-vertical). The taglib should look like this:

\<liferay-ui:icon-menu \
       direction="left-side" \
       icon="\<%= StringPool.BLANK %\>" \
       markupView="lexicon" \
       message="\<%= StringPool.BLANK %\>" \
       showWhenSingleIcon="\<%= true %\>"\>

### 7) Improve forms {#h.w2uxvixj43uv .c4 .c30}

Encapsulate your fieldsets with the following taglib:

\<aui:fieldset-group markupView="lexicon"\>

…

\</aui:fieldset-group\>

The fieldset inside fieldset-group should be collapsible and you can
specify that through the following taglib attributes:

\<aui:fieldset collapsed="\<%= true %\>" collapsible="\<%= true %\>"
label="permissions"\>

      \<liferay-ui:input-permissions  modelName="\<%=
WikiNode.class.getName() %\>"  /\>

\</aui:fieldset\>

The buttons should have the “btn-lg” css class:

\<aui:button-row\>

      \<aui:button cssClass="btn-lg" type="submit" /\>

      \<aui:button cssClass="btn-lg" href="\<%= redirect %\>"
type="cancel" /\>

\</aui:button-row\>

### 8) Replace icons where is needed {#h.kyjoydllw37o .c4 .c30}

#### Actions button {#h.vp1lie6asgtb .c4 .c30}

They should no longer have icons. Please remove them
everywhere^[[j]](#cmnt10)^

#### Other locations {#h.wqeektc2wlx4 .c4 .c30}

To replace old icons for the new ones, find the right icon at
[http://liferay.github.io/lexicon/content/icons-lexicon/](https://www.google.com/url?q=http://liferay.github.io/lexicon/content/icons-lexicon/&sa=D&ust=1461617444895000&usg=AFQjCNHFUkUv8DyelQwYtMPC-rQrgMHUTg),
and use it the liferay-ui:icon taglib like this:

+--------------------------------------+--------------------------------------+
| Old Markup                           | New Markup                           |
+--------------------------------------+--------------------------------------+
| \<liferay-ui:icon                    | \<liferay-ui:icon                    |
|                                      |                                      |
|      iconCssClass="icon-remove"      |      icon="times"                    |
|                                      |                                      |
|      message="remove"                |      markupView="lexicon"            |
|                                      |                                      |
| /\>                                  |      message="remove"                |
|                                      |                                      |
|                                      | /\>                                  |
+--------------------------------------+--------------------------------------+

If the icon does not exist on the Lexicon page, you should contact Juan
Hidalgo. The best way to do that is send a screenshot with the icon and
a short description for this.

### 9) Move actions of an entity or view to the upper right ellipsis icon {#h.n1f2mnigidmi .c4 .c30}

In previous versions of Liferay it was common to have a series of
buttons or menus with actions in certain view. With Liferay 7 the
proposed pattern is to move all this actions to the upper right menu,
leaving only the primary action (often an “Add” operation) visible.

To add an action to the upper right menu you will need to create two
classes. First, a Factory specifies the portlet where the action will be
added and the screen in which it will be shown as well as the order (by
specifying a weight). It also creates an instance of the second class.
Here is an example:

@Component(

  immediate = true,

  property = {

     "javax.portlet.name=" +

        ConfigurationAdminPortletKeys.SYSTEM\_SETTINGS

  },

  service = PortletConfigurationIconFactory.class

)

public class ExportAllConfigurationIconFactory

  extends BasePortletConfigurationIconFactory {

  @Override

  public PortletConfigurationIcon create(PortletRequest portletRequest)
{

     return new ExportAllConfigurationIcon(portletRequest);

  }

  @Override

  public double getWeight() {

     return 1;

  }

}

In this example, the action will be shown in the home page of the System
Settings portlet. To make it appear in a secondary screen you can use
the “path” property as in the following alternative example:

@Component(

  immediate = true,

  property = {

     "javax.portlet.name=" +

        ConfigurationAdminPortletKeys.SYSTEM\_SETTINGS,

     "path=/view\_factory\_instances"

  },

  service = PortletConfigurationIconFactory.class

)

public class ExportFactoryInstancesIconFactory

  extends BasePortletConfigurationIconFactory {

  @Override

  public PortletConfigurationIcon create(PortletRequest portletRequest)
{

     return new ExportFactoryInstancesIcon(portletRequest);

  }

  @Override

  public double getWeight() {

     return 1;

  }

}

The value of the “path” property will depend on the MVC framework being
used to develop the portlet:

-   MVCPortlet: provide the path (often a JSP) that is used in the
    mvcPath parameter.
-   MVCPortlet + MVCCommands: the path should contain the
    mvcRenderCommandName where the actions should be displayed (such as
    /document\_library/edit\_folder for example).

The second class that is instanced from the factory specifies the label
of the action, whether it should be invoked with a GET or POST method
and the URL (or onClick JavaScript method) that should be invoked when
the action is clicked. It can also implement some custom code to
determine whether the action should be shown for the current request.
Some examples of this could be to check for permissions of the current
user. Here is an example of this class:

public class ExportAllConfigurationIcon extends
BasePortletConfigurationIcon {

  public ExportAllConfigurationIcon(PortletRequest portletRequest) {

     super(portletRequest);

  }

  @Override

  public String getMessage() {

     return "export-all-settings";

  }

  @Override

  public String getMethod() {

     return "GET";

  }

  @Override

  public String getURL() {

     LiferayPortletURL liferayPortletURL =

        (LiferayPortletURL)PortalUtil.getControlPanelPortletURL(

           portletRequest,
ConfigurationAdminPortletKeys.SYSTEM\_SETTINGS,

           PortletRequest.RESOURCE\_PHASE);

     liferayPortletURL.setResourceID("export");

     return liferayPortletURL.toString();

  }

  @Override

  public boolean isShow() {

     return true;

  }

}

For portlets that can be added to a page if the desired behaviour is to
always include the configuration options the following init-parameter
should be added to the portlet:

"javax.portlet.init-param.always-display-default-configuration-icons=true"

Further improvements based on mockups {#h.kqtpkxy5kcwb .c4 .c30}
-------------------------------------

Each app will require additional specific improvements which should be
determined by a UX designer and documented in mockups.

If there isn’t a designer in your team or he/she doesn’t have prior
experience creating Lexicon based designs, contact Juan Hidalgo.

### Review the mockup for the application {#h.8cwm04sp1was .c4 .c30}

After applying the Basic Improvements developers should check the
mockups to find additional necessary improvements.

Whenever you have any doubt,

### Selection / Assignment {#h.vqeoxpgix7a1 .c4 .c30}

Lexicon provides several patterns for selection of items. This also
include an specific a common selection case assignment of users/user
groups/roles/etc.

These patterns often replace existing current-available patterns.

In order to determine what changes to make you should always refer to a
mockup. Here are some examples:

1.  Selection of user groups in a pop-up in Site Teams:

site-teams/edit\_team\_assignments\_users\_group.jsp

site-teams/select\_user\_groups.jsp

How to implement different views

On control panel portlets we have three different views by default:
icon, descriptive and list.
e.g. site-teams-web/user\_group\_columns.jspf 

-   Icon

        

        First of all, we should set the behaviour on different devices:

        For vertical cards:

        \<%

row.setCssClass("col-md-2 col-sm-4 col-xs-6");

%\>

For horizontal cards:

\<%

row.setCssClass("col-md-3 col-sm-4 col-xs-12");

%\>

        

        Then we should add a search container column text taglib:

        \<liferay-ui:search-container-column-text\>

                \<%-- include a vertical card or a horizontal card --%\>

\</liferay-ui:search-container-column-text\>

We have three different taglib for vertical cards

\<liferay-frontend:vertical-card /\>

\<liferay-frontend:user-vertical-card /\>                

\<liferay-frontend:icon-vertical-card /\>

        And another taglib for horizontal cards:

\<liferay-frontend:horizontal-card /\>

-   Descriptive

        We should have three columns:

        The first one usually contains an icon or image or user portrait

                Icon:

\<liferay-ui:search-container-column-icon /\>

        Image:

\<liferay-ui:search-container-column-image /\>

User:

        \<liferay-ui:search-container-column-text\>

\<liferay-ui:user-portrait /\>

        \</liferay-ui:search-container-column-text\>

The second one contains the information

\<liferay-ui:search-container-column-text

 colspan="\<%= 2 %\>"

\>

  \<h5\>

     \<aui:a href=""\>\<%= team.getName() %\>\</aui:a\>

  \</h5\>

  \<h6 class="text-default"\>

     \<span\>\<%= team.getDescription() %\>\</span\>

  \</h6\>

\</liferay-ui:search-container-column-text\>

And the third one usually contains the actions

\<liferay-ui:search-container-column-jsp

  path="/team\_action.jsp"

/\>

-   List

        It Is the view that we usually have in our portlets:

\<liferay-ui:search-container-column-text

  cssClass="text-strong"

  href="\<%= rowURL %\>"

  name="name"

  property="name"

/\>

\<liferay-ui:search-container-column-text

  href="\<%= rowURL %\>"

  name="description"

  property="description"

/\>

\<liferay-ui:search-container-column-jsp

  cssClass="list-group-item-field"

  path="/team\_action.jsp"

/\>

We should set to \<liferay-ui:search-iterator /\> the
displayStyle attribute with the current display style and markupView
attribute to lexicon.

Known issues {#h.s4cen7gbr8bt .c4 .c30}
------------

-   Lexicon Icons are now available to use in portal, we will change
    them bit by bit:\
    http://liferay.github.io/lexicon/content/icons-lexicon/
