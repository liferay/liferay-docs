# Liferay UI Tabs [](id=liferay-ui-tabs)

Tabs let you create dividers in your app that organize content into individual 
sections. Content can be written within the JSP, or included from another JSP if 
you wish. This lets you pack your JSP full of content, without overwhelming your 
users with it all at once. 

To add tabs to your app, use the `<liferay-ui:tabs>` tag and specify each tab's 
name as a comma-separated list for the `names` attribute. For example, if you 
want to add three tabs named `tab1`, `tab2`, and `tab3`, you could add this code 
to your JSP:

    <liferay-ui:tabs names="tab1,tab2,tab3">

    </liferay-ui:tabs>
    
Each tab requires a corresponding section to display content. Nest 
`liferay-ui:section` tags for each of the tabs. Within each section, you can add 
HTML content or add content indirectly by including content from another JSP 
(via the `<%@ includefile="filepath"%>` directive). The example snippet below is 
from the Calendar portlet's `configuration.jsp`:

    <liferay-ui:tabs
        names='<%= "user-settings,display-settings,rss" %>'
        param="tabs2"
        refresh="<%= false %>"
        type="tabs nav-tabs-default"
    >
        <liferay-ui:section>
            <%@ include file="/configuration/user_settings.jspf" %>
        </liferay-ui:section>

        <liferay-ui:section>
            <%@ include file="/configuration/display_settings.jspf" %>
        </liferay-ui:section>

        <liferay-ui:section>
            <%@ include file="/configuration/rss.jspf" %>
        </liferay-ui:section>
    </liferay-ui:tabs>

![Figure 1: Tabs are a useful way to organize configuration options into individual sections within the same UI.](../../../images/liferay-ui-taglib-tabs.png)

The list below gives some of the tab's available attributes along with how to 
configure them. See the 
[Tabs taglibdocs](@platform-ref@/7.1-latest/taglibs/util-taglib/liferay-ui/tabs.html) 
for the full list of attributes.

**backLabel:** A label for the back URL.

**backURL:** A URL for navigating back to.

**formName:** A name for the tab's form.

**names:** The names of the tabs. Each name must be separated by a comma with no 
spaces.

**onClick:** A function to be called on a user clicking any of the tabs.

**param:** A variable for the component.

**portletURL:** A URL to refer to the portlet that contains the component.

**refresh:** Whether the page refreshes when a tab is clicked. The default 
value is `true`.

**tabsValues:** Tab values that must follow the same order as the `names` 
attribute. Each value must be separated by a comma with no spaces.

**type:** A design type for the tabs. Possible values are `tabs` and `pills`.

**url:** A URL to which all the tabs are linked when clicked. You can also set 
each tab's URL individually by using the `url` attribute.

**urls:** Sets the URL the given tab links to.

**value:** An active tab from the value given in the `tabsValues` attribute. No 
matter which tab a user selects, the tab given for this value always remains 
active.

## Related Topics [](id=related-topics)

[Clay Navigation Bars](/develop/tutorials/-/knowledge_base/7-1/clay-navigation-bars)

[Clay Dropdown Menus and Action Menus](/develop/tutorials/-/knowledge_base/7-1/clay-dropdown-menus-and-action-menus)

[Liferay UI Tooltips](/develop/tutorials/-/knowledge_base/7-1/liferay-ui-tooltips)
