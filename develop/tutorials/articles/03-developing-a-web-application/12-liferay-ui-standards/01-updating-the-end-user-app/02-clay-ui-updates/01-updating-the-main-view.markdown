# Updating the Main View [](id=updating-the-main-view)

Now, you'll update the Guestbook's main view. You'll make the
following changes to the UI of the main view:

- Create an add menu in the Guestbook's portlet toolbar to add entries and
  guestbooks
- Minimalize the Navigation
- Improve the readability of the Actions menu
- Improve the visibility and user experience of the search container results

Go ahead and get started by creating the Add menu next.

## Adding the Add Menu to the Portlet Toolbar [](id=adding-the-add-menu-to-the-portlet-toolbar)

Currently, there are two buttons which allow administrators to add entities to 
the Guestbook: *Add Guestbook* and *Add Entry*. To keep the UI as clean and 
minimal as possible, you'll move this functionality to an add menu within the 
portlet toolbar. To add this menu to the portlet toolbar, you'll need to create 
a `*PortletToolbarContributor` class. You'll use a Blade template to create the 
class. Once the class is created, you can configure the view. 

Get started by creating the portlet toolbar next.

### Creating the Portlet Toolbar Contributor Class [](id=creating-the-portlet-toolbar-contributor-class)

Follow these steps to create the the class:

1.  Right-click in the Project Explorer window and select *File* &rarr; *New* 
    &rarr; *Liferay Module Project*.

2.  Enter `GuestbookPortletToolbarContributor` for the name and select 
    *portlet-toolbar-contributor* for the *Project Template Name*. Leave the 
    default values and click *Next*.

3.  Enter `Guestbook` for the *Component Class Name* and 
    `com.liferay.docs.guestbook` as the *Package Name* and click *Finish*.

4. At the moment, the Portlet Toolbar Contributor is a separate module project. 
   To keep this contained within the Guestbook portlet, you should move it into 
   that project. Copy the `toolbar` folder over to the `src/portlet/` folder of 
   the `guestbook-web` module.

5.  The blade template set up the package and class for you, but it implements 
    the `PortletToolbarContributor` class. For the purposes of the add menu, you 
    need to extend the `BasePortletToolbarContributor` class instead:

        public class GuestbookPortletToolbarContributor
        	extends BasePortletToolbarContributor

6.  Replace the list of imports with the following imports:

        import com.liferay.docs.guestbook.constants.GuestbookPortletKeys;
        import com.liferay.docs.guestbook.service.permission.GuestbookModelPermission;
        import com.liferay.docs.guestbook.service.permission.GuestbookPermission;
        import com.liferay.portal.kernel.log.Log;
        import com.liferay.portal.kernel.log.LogFactoryUtil;
        import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
        import com.liferay.portal.kernel.portlet.toolbar.contributor.BasePortletToolbarContributor;
        import com.liferay.portal.kernel.portlet.toolbar.contributor.PortletToolbarContributor;
        import com.liferay.portal.kernel.servlet.taglib.ui.MenuItem;
        import com.liferay.portal.kernel.servlet.taglib.ui.URLMenuItem;
        import com.liferay.portal.kernel.theme.ThemeDisplay;
        import com.liferay.portal.kernel.util.ParamUtil;
        import com.liferay.portal.kernel.util.Portal;
        import com.liferay.portal.kernel.util.WebKeys;

        import java.util.ArrayList;
        import java.util.List;

        import javax.portlet.PortletRequest;
        import javax.portlet.PortletResponse;
        import javax.portlet.PortletURL;

        import org.osgi.service.component.annotations.Component;
        import org.osgi.service.component.annotations.Reference;

7.  Update the `@Component` reference `property` and `service` attributes to 
    match the following configuration:

        @Component(
        	immediate = true,
        	property = {
        		"javax.portlet.name=" + GuestbookPortletKeys.GUESTBOOK, "mvc.path=-",
        		"mvc.path=/html/guestbookmvcportlet/view.jsp"
        	},
        	service = {
        		GuestbookPortletToolbarContributor.class,
        		PortletToolbarContributor.class
        	}
        )

8.  Add the following `addPortletTitleAddMenuItems` method just below the class 
    declaration:

        protected void addPortletTitleAddMenuItems(
            List<MenuItem> menuItems, PortletRequest portletRequest,
            PortletResponse portletResponse)
          throws Exception {

          ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
            WebKeys.THEME_DISPLAY);

          LiferayPortletResponse liferayPortletResponse =
            _portal.getLiferayPortletResponse(portletResponse);

          long guestbookId = ParamUtil.getLong(portletRequest, "guestbookId");

          if ((guestbookId > 0) &&
            GuestbookPermission.contains(
              themeDisplay.getPermissionChecker(), guestbookId,
              "ADD_ENTRY")) {

            URLMenuItem addEntryURLMenuItem = new URLMenuItem();

            addEntryURLMenuItem.setLabel("Add Entry");

            PortletURL addEntryURL = liferayPortletResponse.createRenderURL();

            addEntryURL.setParameter(
              "mvcPath", "/html/guestbookmvcportlet/edit_entry.jsp");

            addEntryURLMenuItem.setURL(addEntryURL.toString());

            menuItems.add(addEntryURLMenuItem);
          }

          if (GuestbookModelPermission.contains(
              themeDisplay.getPermissionChecker(),
              themeDisplay.getScopeGroupId(), "ADD_GUESTBOOK")) {

            URLMenuItem addGuestbookURLMenuItem = new URLMenuItem();

            addGuestbookURLMenuItem.setLabel("Add Guestbook");

            PortletURL addGuestbookURL =
              liferayPortletResponse.createRenderURL();

            addGuestbookURL.setParameter(
              "mvcPath", "/html/guestbookmvcportlet/edit_guestbook.jsp");
            addEntryURL.setParameter(
  				    "guestbookId", String.valueOf(guestbookId));
            addGuestbookURLMenuItem.setURL(addGuestbookURL.toString());

            menuItems.add(addGuestbookURLMenuItem);
          }
        }

9.  Finally, replace the `getPortletTitleMenuItems()` method and variable 
    definitions with the following configuration:

        @Override
        protected List<MenuItem> getPortletTitleMenuItems(
          PortletRequest portletRequest, PortletResponse portletResponse) {

          List<MenuItem> menuItems = new ArrayList<>();

          try {
            addPortletTitleAddMenuItems(
              menuItems, portletRequest, portletResponse);
          }
          catch (Exception e) {
            _log.error("Unable to add folder menu item", e);
          }

          return menuItems;
        }

        private static final Log _log = LogFactoryUtil.getLog(
          GuestbookPortletToolbarContributor.class);

        @Reference
        private Portal _portal;

        }

Now that you have the `*PortletToolbarContributor` class written, you can 
configure the view to use the add menu next.

### Configuring the View [](id=configuring-the-view)

Open the `view.jsp` and remove the original `<aui:button-row>` code shown below, 
now that, that functionality is moved to the portlet toolbar:

    <aui:button-row cssClass="guestbook-buttons">
    	<c:if test='<%= GuestbookModelPermission.contains(permissionChecker,
        scopeGroupId, "ADD_GUESTBOOK") %>'>
    		<portlet:renderURL var="addGuestbookURL">
    			<portlet:param name="mvcPath" value="/html/guestbookmvcportlet/edit_guestbook.jsp" />
    		</portlet:renderURL>

    		<aui:button onClick="<%= addGuestbookURL %>" value="Add Guestbook" />
    	</c:if>

    	<c:if test='<%= GuestbookPermission.contains(permissionChecker,
        guestbookId, "ADD_ENTRY") %>'>
    		<portlet:renderURL var="addEntryURL">
    			<portlet:param name="mvcPath" value="/html/guestbookmvcportlet/edit_entry.jsp" />
    			<portlet:param name="guestbookId" value="<%= String.valueOf(guestbookId) %>" />
    		</portlet:renderURL>

    		<aui:button onClick="<%= addEntryURL %>" value="Add Entry" />
    	</c:if>
    </aui:button-row>

The updated portlet toolbar is shown in the figure below:

![Figure 1: The Updated Add menu keeps the main UI free of clutter.](../../../../../images/portlet-toolbar-add-menu.png)

Next, you'll update the Guestbook's Navigation.

## Updating the Navigation [](id=updating-the-navigation)

The current navigation uses tabs. While these are effective, the Clay design
guidelines minimalize this UI by placing the Guestbook navigation into a navbar
instead.

Follow these steps to update the navigation:

1.  Still inside the `view.jsp`, change the `<aui:nav>` tag's `cssClass` 
    attribute from `nav-tabs` to `navbar-nav`:

        <aui:nav cssClass="navbar-nav">

2.  Wrap the `<aui:nav>...</aui:nav>` tags with the `<aui:nav-bar>` tags shown
    below:

        <aui:nav-bar markupView="lexicon">
        ...
        </aui:nav-bar>

The navigation is updated. Next you'll update the Actions menu.

## Updating the Actions Menu [](id=updating-the-actions-menu)

Follow these steps to update the Actions menu:

1.  Open the `guestbook-web` module's `guestbook_actions.jsp` and update the 
    `<liferay-ui:icon-menu>` tag to match the Lexicon guidelines with the 
    configuration below:

        <liferay-ui:icon-menu
            direction="left-side"
            icon="<%= StringPool.BLANK %>"
            markupView="lexicon"
            message="<%= StringPool.BLANK %>"
            showWhenSingleIcon="<%= true %>"
        >

2.  To follow the Clay guidelines, the Action menu should only display an icon
    if it is one action. In this case, the Actions menu contains a few different
    actions, therefore the icons should be removed. Remove the `image` attribute
    from the edit icon so that it matches the configuration shown below:
    
        <liferay-ui:icon
            message="Edit"
            url="<%= editURL.toString() %>"
        />

3.  Replace the `image` attribute for the permissions icon with a `message` 
    attribute that matches the configuration below:

        <liferay-ui:icon
            message="Permissions"
            url="<%= permissionsURL %>"
        />

You'll update the search container next.

## Updating the Search Container [](id=updating-the-search-container)

In this section you'll update the Search Container's UI to be more user-friendly. 
Follow these steps to update the Search Container:

1.  Open the `view.jsp` and wrap the `<liferay-ui:search-container>` tags with a 
    fluid container:

        <div class="container-fluid-1280">
            <liferay-ui:search-container ...>
                ...
            </liferay-ui:search-container>
        </div>
        
    This will allow the contents of the Search Container to expand to the entire 
    width of the Search Container.
    
2.  Add the `emptyResultsMessage` attribute to the 
    `<liferay-ui:search-container>` taglib with the configuration below:
    
        <liferay-ui:search-container
            emptyResultsMessage="no-results-were-found"
            total="<%= EntryLocalServiceUtil.getEntriesCount(scopeGroupId, guestbookId) %>"
        >

    Now your users are greeted with a nice smiley face letting them know that 
    unfortunately no results were found for their search.
    
3.  Finally, add the `table-cell-content` CSS class to the name and message 
    search container columns in the list view to indicate them as the principal 
    columns:
    
        <liferay-ui:search-container-column-text
            cssClass="table-cell-content"
            property="message"
        />

        <liferay-ui:search-container-column-text
            cssClass="table-cell-content"
            property="name"
        />

The updated Search Container and Actions menu reflect the figure below:

![Figure 2: The Search Container is all kinds of user-friendly now.](../../../../../images/updated-search-container-nav-tabs-actions-menu-ui.png)

In the next section, you'll update the Guestbook app's edit view.
